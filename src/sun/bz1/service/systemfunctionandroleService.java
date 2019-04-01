package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.TBase;
import sun.bz1.entity.role;
import sun.bz1.entity.systemfunction;
import sun.bz1.entity.systemfunctionandrole;
import util.MyBatisUtil;

@Service
public class systemfunctionandroleService {
	
	private Logger logger=Logger.getLogger(systemfunctionandroleService.class);
	@Autowired
    private sun.bz1.dao.systemfunctionandroleDao systemfunctionandroleDao;

	/**
	 * 查询关联表
	 * 
	 * @param tbase
	 * @return
	 */
	public List<systemfunctionandrole> selectSystemfunctionandrole(
			systemfunctionandrole systemfunctionandrole) {
		List<systemfunctionandrole> data=new ArrayList<systemfunctionandrole>();
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			data = systemfunctionandroleDao
					.selectSystemfunctionandrole(session, systemfunctionandrole);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return data;
		
	}
	
	/**
	 * 查询关联数量
	 * 
	 * @param tbase
	 * @return
	 */
	public Integer selectSystemfunctionandroleCount(
			systemfunctionandrole systemfunctionandrole) {
		Integer count=0;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			count = systemfunctionandroleDao
					.selectSystemfunctionandroleCount(session, systemfunctionandrole);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return count;
		
	}
	
	/**
	 * 新增角色对应功能
	 * @param tbase
	 * @return
	 */
	public boolean insertSystemfunctionandrole(List<systemfunctionandrole> systemfunctionandrole)
	{ 
		Boolean bR = false;
		try {
			
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			systemfunctionandroleDao.insertSystemfunctionandrole(session, systemfunctionandrole);
            session.commit();
			session.close();

			bR = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return bR;
		
	}
	/**
	 * 删除角色对应功能
	 * @param tbase
	 * @return
	 */
	public Boolean deleteSystemfunctionandrole(systemfunctionandrole systemfunctionandrole)
	{ 
		Boolean bR = false;
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			systemfunctionandroleDao.deleteSystemfunctionandrole(session, systemfunctionandrole);
            session.commit();
			session.close();
            bR = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return bR;
		
	}

}
