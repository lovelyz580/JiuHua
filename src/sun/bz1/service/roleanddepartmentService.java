package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.TBase;
import sun.bz1.entity.role;
import sun.bz1.entity.roleanddepartment;
import sun.bz1.entity.systemfunctionandrole;
import util.MyBatisUtil;

/**
 * 角色和部门关联表
 * 
 * @author Administrator
 * 
 */

@Service
public class roleanddepartmentService {

	private Logger logger = Logger
			.getLogger(systemfunctionandroleService.class);

	@Autowired
	sun.bz1.dao.roleanddepartmentDao roleanddepartmentDao;

	/**
	 * 根据部门查询角色
	 * 
	 * @param Tbase
	 * @return
	 */
	public List<roleanddepartment> selectRoleanddepartment(
			roleanddepartment roleanddepartment) {
		List<roleanddepartment> list = new ArrayList<roleanddepartment>();
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			list = roleanddepartmentDao.selectRoleanddepartment(session,
					roleanddepartment);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return list;

	}
	
	/**
	 * 根据部门查询角色数量
	 * 
	 * @param Tbase
	 * @return
	 */
	public Integer selectRoleanddepartmentCount(
			roleanddepartment roleanddepartment) {
		Integer count = 0;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			count = roleanddepartmentDao.selectRoleanddepartmentCount(session,
					roleanddepartment);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return count;

	}

	/**
	 * 关联表新增的方法
	 * 
	 * @param tbase
	 * @return
	 */

	public boolean insertRoleanddepartment(
			List<roleanddepartment> roleanddepartmentList) {
		Boolean bR = false;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			roleanddepartmentDao.insertRoleanddepartment(session,
					roleanddepartmentList);
			session.commit();
			session.close();

			bR = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bR;

	}

	/**
	 * 关联表删除的方法
	 * 
	 * @param tbase
	 * @return
	 */

	public boolean deleteRoleanddepartment(roleanddepartment roleanddepartment) {
		Boolean bR = false;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			roleanddepartmentDao.deleteRoleanddepartment(session,
					roleanddepartment);
			session.commit();
			session.close();

			bR = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bR;

	}

}
