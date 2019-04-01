package sun.bz1.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.entity.roleandstaff;
import util.MyBatisUtil;

/**
 * 角色和人员业务层
 * 
 * @author Administrator
 * 
 */
@Service
public class roleandstaffService {
	private Logger logger = Logger
			.getLogger(systemfunctionandroleService.class);

	@Autowired
	private sun.bz1.dao.roleandstaffDao roleandstaffDao;

	/**
	 * 人员关联角色查询
	 * 
	 */
	public List<roleandstaff> selectRoleandstaff(roleandstaff roleandstaff) {
		List<roleandstaff> data = null;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			data = roleandstaffDao.selectRoleandstaff(session, roleandstaff);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return data;

	}
	
	/**
	 * 人员关联角色数量查询
	 * 
	 */
	public Integer selectRoleandstaffCount(roleandstaff roleandstaff) {
		Integer count =0;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			count = roleandstaffDao.selectRoleandstaffCount(session, roleandstaff);

			session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return count;

	}

	/**
	 * 新增关系表
	 */
	public boolean insertRoleandstaff(List<roleandstaff> roleandstaffList) {
		Boolean bR = false;
		try {

			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			roleandstaffDao.insertRoleandstaff(session, roleandstaffList);
			session.commit();
			session.close();

			bR = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bR;

	}

	/**
	 * 删除关系表
	 */
	public boolean deleteRoleandstaff(roleandstaff roleandstaff) {
		Boolean bR = false;
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			roleandstaffDao.deleteRoleandstaff(session, roleandstaff);
			session.commit();
			session.close();
			bR = true;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bR;

	}
}
