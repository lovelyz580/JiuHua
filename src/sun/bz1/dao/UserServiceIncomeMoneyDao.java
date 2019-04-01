package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.UserServiceIncomeMoney;
import sun.bz1.entity.UserServiceIncomeMoneyAndOrderTableAndUser;

/**
 * 用户_客户_收入_金额表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/19
 */

@Repository
public class UserServiceIncomeMoneyDao {
    
	private Logger logger = Logger.getLogger(UserServiceIncomeMoneyDao.class);

	public int insert(SqlSession session, UserServiceIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserServiceIncomeMoney.insert", incomeMoney);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, UserServiceIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserServiceIncomeMoney.insertSelective", incomeMoney);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, UserServiceIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserServiceIncomeMoney.updateByPrimaryKeySelective", incomeMoney);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, UserServiceIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserServiceIncomeMoney.updateByPrimaryKey", incomeMoney);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserServiceIncomeMoney.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public UserServiceIncomeMoney selectByPrimaryKey(SqlSession session, Integer id) {
		UserServiceIncomeMoney UserServiceIncomeMoney = new UserServiceIncomeMoney();

		try {
			logger.info("UserServiceIncomeMoneyDao");

			UserServiceIncomeMoney = session.selectOne("sun.bz1.dao.mapper.UserServiceIncomeMoney.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return UserServiceIncomeMoney;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.UserServiceIncomeMoney.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceIncomeMoney实体添加
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int insertByUserServiceIncomeMoney(SqlSession session, UserServiceIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserServiceIncomeMoney.insertByUserServiceIncomeMoney", incomeMoney);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--insertByUserServiceIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceIncomeMoney实体更新
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int updateByUserServiceIncomeMoney(SqlSession session, UserServiceIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserServiceIncomeMoney.updateByUserServiceIncomeMoney", incomeMoney);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--updateByUserServiceIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserServiceIncomeMoney.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public List<UserServiceIncomeMoneyAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		List<UserServiceIncomeMoneyAndOrderTableAndUser> UserServiceIncomeMoneyList = new ArrayList<UserServiceIncomeMoneyAndOrderTableAndUser>();

		try {
			logger.info("UserServiceIncomeMoneyDao");

			UserServiceIncomeMoneyList = session.selectList("sun.bz1.dao.mapper.UserServiceIncomeMoney.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return UserServiceIncomeMoneyList;
	}

	/**
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserServiceIncomeMoney.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public List<UserServiceIncomeMoneyAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		List<UserServiceIncomeMoneyAndOrderTableAndUser> UserServiceIncomeMoneyList = new ArrayList<UserServiceIncomeMoneyAndOrderTableAndUser>();

		try {
			logger.info("UserServiceIncomeMoneyDao");

			UserServiceIncomeMoneyList = session.selectList("sun.bz1.dao.mapper.UserServiceIncomeMoney.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return UserServiceIncomeMoneyList;
	}
	
	/**
	 * 根据UserServiceIncomeMoney实体删除
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByUserServiceIncomeMoney(SqlSession session, UserServiceIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserServiceIncomeMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.UserServiceIncomeMoney.deleteByUserServiceIncomeMoney", incomeMoney);
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyDao--deleteByUserServiceIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}
	
}