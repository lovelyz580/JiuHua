package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.UserServiceEntity;

/**
 * 用户_维修工、安装队表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/05
 */

@Repository
public class UserServiceDao {
	
	private Logger logger = Logger.getLogger(UserServiceDao.class);

	public int insert(SqlSession session, UserServiceEntity userServiceEntity) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.insert("sun.bz1.dao.mapper.UserService.insert", userServiceEntity);
		} catch (Exception e) {
			logger.error("UserServiceDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, UserServiceEntity userServiceEntity) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.insert("sun.bz1.dao.mapper.UserService.insertSelective", userServiceEntity);
		} catch (Exception e) {
			logger.error("UserServiceDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, UserServiceEntity userServiceEntity) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.update("sun.bz1.dao.mapper.UserService.updateByPrimaryKeySelective", userServiceEntity);
		} catch (Exception e) {
			logger.error("UserServiceDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, UserServiceEntity userServiceEntity) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.update("sun.bz1.dao.mapper.UserService.updateByPrimaryKey", userServiceEntity);
		} catch (Exception e) {
			logger.error("UserServiceDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserService.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserServiceDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public UserServiceEntity selectByPrimaryKey(SqlSession session, Integer id) {
		UserServiceEntity userService = new UserServiceEntity();

		try {
			logger.info("UserServiceDao");

			userService = session.selectOne("sun.bz1.dao.mapper.UserService.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserServiceDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return userService;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.delete("sun.bz1.dao.mapper.UserService.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserServiceDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserService实体添加
	 * 
	 * @param userServiceEntity
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int insertByUserService(SqlSession session, UserServiceEntity userServiceEntity) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.insert("sun.bz1.dao.mapper.UserService.insertByUserService", userServiceEntity);
		} catch (Exception e) {
			logger.error("UserServiceDao--insertByUserService--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserService实体更新
	 * 
	 * @param userServiceEntity
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByUserService(SqlSession session, UserServiceEntity userServiceEntity) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.update("sun.bz1.dao.mapper.UserService.updateByUserService", userServiceEntity);
		} catch (Exception e) {
			logger.error("UserServiceDao--updateByUserService--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserService实体查询
	 * 
	 * 查询数量
	 * 
	 * @param userServiceEntity
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectCountByUserService(SqlSession session, UserServiceEntity userServiceEntity) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserService.selectCountByUserService", userServiceEntity);
		} catch (Exception e) {
			logger.error("UserServiceDao--selectCountByUserService--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserService实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param userServiceEntity
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public List<UserServiceEntity> selectByUserService(SqlSession session, UserServiceEntity userServiceEntity) {
		List<UserServiceEntity> userServiceList = new ArrayList<UserServiceEntity>();

		try {
			logger.info("UserServiceDao");

			userServiceList = session.selectList("sun.bz1.dao.mapper.UserService.selectByUserService", userServiceEntity);
		} catch (Exception e) {
			logger.error("UserServiceDao--selectByUserService--error:" + e.getMessage());
		}

		return userServiceList;
	}
	
	/**
	 * 根据UserService实体删除
	 * 
	 * @param userServiceEntity
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int deleteByUserService(SqlSession session, UserServiceEntity userServiceEntity) {
		int num = 0;

		try {
			logger.info("UserServiceDao");

			num = session.delete("sun.bz1.dao.mapper.UserService.deleteByUserService", userServiceEntity);
		} catch (Exception e) {
			logger.error("UserServiceDao--deleteByUserService--error:" + e.getMessage());
		}

		return num;
	}

}