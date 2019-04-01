package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.TBase;
import sun.bz1.entity.systemfunction;
import util.MyBatisUtil;

/**
 * 系统功能业务层
 * 
 * 2018-10-7 -zz
 */
@Service
public class systemfunctionService {

	private Logger logger = Logger.getLogger(systemfunctionService.class);
	@Autowired
	sun.bz1.dao.systemfunctionDao systemfunctionDao;

	/**
	 * 查看系统功能控制层
	 * 
	 */
	public List<systemfunction> selectSystemFunction(
			systemfunction systemfunction) {
		List<systemfunction> data = null;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			data = systemfunctionDao.selectSystemFunction(session,
					systemfunction);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return data;

	}

	/**
	 * 查看数量控制层
	 * 
	 */
	public Integer selectCount(systemfunction systemfunction) {
		Integer count = 0;

		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			count = systemfunctionDao.selectCount(session, systemfunction);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return count;

	}

	/**
	 * 查看角色对应系统功能控制层
	 * 
	 */
	public List<systemfunction> selectSystemFunctionMore(
			List<Integer> systemfunctionSid) {
		List<systemfunction> data = null;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			data = systemfunctionDao.selectSystemFunctionMore(session,
					systemfunctionSid);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return data;

	}

	/**
	 * 查看角色对应功能数量控制层
	 * 
	 */
	public Integer selectSystemFunctionMoreCount(List<Integer> systemfunctionSid) {
		Integer count = 0;

		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			count = systemfunctionDao.selectSystemFunctionMoreCount(session,
					systemfunctionSid);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return count;

	}

	/**
	 * 新增系统功能控制层
	 * 
	 */
	public Boolean insertSystemFunction(
			systemfunction systemfunction) {
		Boolean bR = false;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			systemfunctionDao.insertSystemFunction(session, systemfunction);
			session.commit();
			session.close();

			bR = true;
	   } catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bR;

	}

	/**
	 * 修改系统功能控制层
	 * 
	 */
	public Boolean updataSystemFunction(
			systemfunction systemfunction) {
		Boolean bR = false;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			systemfunctionDao.updataSystemFunction(session, systemfunction);
			session.commit();
			session.close();

			bR = true;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bR;
   }

	/**
	 * 删除系统功能控制层
	 * 
	 */
	public Boolean delSystemFunction(List<Integer> delList) {
		Boolean bR = false;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			systemfunctionDao.delSystemFunction(session, delList);
			session.commit();
			session.close();

			bR = true;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bR;

	}

}
