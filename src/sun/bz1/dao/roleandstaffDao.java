package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.roleandstaff;

/**
 * 角色和人员关联表
 * 
 * @author Administrator
 * 
 */

@Repository
public class roleandstaffDao {

	private Logger logger = Logger.getLogger(systemfunctionandroleDao.class);

	/**
	 * 查询全部关联
	 * 
	 */
	public List<roleandstaff> selectRoleandstaff(SqlSession session,
			roleandstaff roleandstaff) {
		List<roleandstaff> data = new ArrayList<roleandstaff>();
		try {
			logger.info("roleandstaff");
			data = session.selectList(
					"sun.bz1.dao.mapper.roleandstaffMapper.selectText",
					roleandstaff);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return data;
	}
	/**
	 * 查询全部关联数量
	 * 
	 */
	public Integer selectRoleandstaffCount(SqlSession session,
			roleandstaff roleandstaff) {
		Integer count=0;
		try {
			logger.info("roleandstaff");
			count = session.selectOne(
					"sun.bz1.dao.mapper.roleandstaffMapper.selectTextCount",
					roleandstaff);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return count;
	}

	/**
	 * 新增关联
	 * 
	 */

	public Boolean insertRoleandstaff(SqlSession session,
			List<roleandstaff> roleandstaffList) {
		Boolean bR = false;
		try {
			logger.info("roleandstaff");
			session.insert("sun.bz1.dao.mapper.roleandstaffMapper.addText",
					roleandstaffList);
			bR = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bR;
	}

	/**
	 * 删除关联
	 * 
	 */
	public Boolean deleteRoleandstaff(SqlSession session,
			roleandstaff roleandstaff) {
		Boolean bR = false;
		try {
			logger.info("roleandstaff");
			session.delete("sun.bz1.dao.mapper.roleandstaffMapper.deletText",
					roleandstaff);
			bR = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bR;
	}
}
