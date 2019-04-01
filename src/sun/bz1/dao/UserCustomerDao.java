package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.UserCustomer;

/**
 * 用户_客户表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/05
 */

@Repository
public class UserCustomerDao {

	private Logger logger = Logger.getLogger(UserCustomerDao.class);

	public int insert(SqlSession session, UserCustomer userCustomer) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.insert("sun.bz1.dao.mapper.UserCustomer.insert", userCustomer);
		} catch (Exception e) {
			logger.error("UserCustomerDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, UserCustomer userCustomer) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.insert("sun.bz1.dao.mapper.UserCustomer.insertSelective", userCustomer);
		} catch (Exception e) {
			logger.error("UserCustomerDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, UserCustomer userCustomer) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.update("sun.bz1.dao.mapper.UserCustomer.updateByPrimaryKeySelective", userCustomer);
		} catch (Exception e) {
			logger.error("UserCustomerDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, UserCustomer userCustomer) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.update("sun.bz1.dao.mapper.UserCustomer.updateByPrimaryKey", userCustomer);
		} catch (Exception e) {
			logger.error("UserCustomerDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserCustomer.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserCustomerDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public UserCustomer selectByPrimaryKey(SqlSession session, Integer id) {
		UserCustomer UserCustomer = new UserCustomer();

		try {
			logger.info("UserCustomerDao");

			UserCustomer = session.selectOne("sun.bz1.dao.mapper.UserCustomer.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserCustomerDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return UserCustomer;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.delete("sun.bz1.dao.mapper.UserCustomer.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserCustomerDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomer实体添加
	 * 
	 * @param userCustomer
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int insertByUserCustomer(SqlSession session, UserCustomer userCustomer) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.insert("sun.bz1.dao.mapper.UserCustomer.insertByUserCustomer", userCustomer);
		} catch (Exception e) {
			logger.error("UserCustomerDao--insertByUserCustomer--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomer实体更新
	 * 
	 * @param userCustomer
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByUserCustomer(SqlSession session, UserCustomer userCustomer) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.update("sun.bz1.dao.mapper.UserCustomer.updateByUserCustomer", userCustomer);
		} catch (Exception e) {
			logger.error("UserCustomerDao--updateByUserCustomer--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomer实体查询
	 * 
	 * 查询数量
	 * 
	 * @param userCustomer
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectCountByUserCustomer(SqlSession session, UserCustomer userCustomer) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserCustomer.selectCountByUserCustomer", userCustomer);
		} catch (Exception e) {
			logger.error("UserCustomerDao--selectCountByUserCustomer--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomer实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param userCustomer
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public List<UserCustomer> selectByUserCustomer(SqlSession session, UserCustomer userCustomer) {
		List<UserCustomer> UserCustomerList = new ArrayList<UserCustomer>();

		try {
			logger.info("UserCustomerDao");

			UserCustomerList = session.selectList("sun.bz1.dao.mapper.UserCustomer.selectByUserCustomer", userCustomer);
		} catch (Exception e) {
			logger.error("UserCustomerDao--selectByUserCustomer--error:" + e.getMessage());
		}

		return UserCustomerList;
	}
	
	/**
	 * 根据UserCustomer实体删除
	 * 
	 * @param userCustomer
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int deleteByUserCustomer(SqlSession session, UserCustomer userCustomer) {
		int num = 0;

		try {
			logger.info("UserCustomerDao");

			num = session.delete("sun.bz1.dao.mapper.UserCustomer.deleteByUserCustomer", userCustomer);
		} catch (Exception e) {
			logger.error("UserCustomerDao--deleteByUserCustomer--error:" + e.getMessage());
		}

		return num;
	}
	
}