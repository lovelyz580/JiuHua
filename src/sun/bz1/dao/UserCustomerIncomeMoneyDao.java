package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.UserCustomerIncomeMoney;
import sun.bz1.entity.UserCustomerIncomeMoneyAndOrderTableAndUser;

/**
 * 用户_客户_收入_金额表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/19
 */

@Repository
public class UserCustomerIncomeMoneyDao {

	private Logger logger = Logger.getLogger(UserCustomerIncomeMoneyDao.class);

	public int insert(SqlSession session, UserCustomerIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserCustomerIncomeMoney.insert", incomeMoney);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, UserCustomerIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserCustomerIncomeMoney.insertSelective", incomeMoney);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, UserCustomerIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserCustomerIncomeMoney.updateByPrimaryKeySelective", incomeMoney);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, UserCustomerIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserCustomerIncomeMoney.updateByPrimaryKey", incomeMoney);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserCustomerIncomeMoney.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public UserCustomerIncomeMoney selectByPrimaryKey(SqlSession session, Integer id) {
		UserCustomerIncomeMoney UserCustomerIncomeMoney = new UserCustomerIncomeMoney();

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			UserCustomerIncomeMoney = session.selectOne("sun.bz1.dao.mapper.UserCustomerIncomeMoney.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return UserCustomerIncomeMoney;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.UserCustomerIncomeMoney.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerIncomeMoney实体添加
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int insertByUserCustomerIncomeMoney(SqlSession session, UserCustomerIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserCustomerIncomeMoney.insertByUserCustomerIncomeMoney", incomeMoney);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--insertByUserCustomerIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerIncomeMoney实体更新
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int updateByUserCustomerIncomeMoney(SqlSession session, UserCustomerIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserCustomerIncomeMoney.updateByUserCustomerIncomeMoney", incomeMoney);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--updateByUserCustomerIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerIncomeMoneyAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, UserCustomerIncomeMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserCustomerIncomeMoney.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据UserCustomerIncomeMoneyAndOrderTableAndUser实体联表查询
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
	public List<UserCustomerIncomeMoneyAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, UserCustomerIncomeMoneyAndOrderTableAndUser unionData) {
		List<UserCustomerIncomeMoneyAndOrderTableAndUser> UserCustomerIncomeMoneyList = new ArrayList<UserCustomerIncomeMoneyAndOrderTableAndUser>();

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			UserCustomerIncomeMoneyList = session.selectList("sun.bz1.dao.mapper.UserCustomerIncomeMoney.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return UserCustomerIncomeMoneyList;
	}

	/**
	 * 根据UserCustomerIncomeMoneyAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, UserCustomerIncomeMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserCustomerIncomeMoney.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerIncomeMoneyAndOrderTableAndUser实体联表模糊查询
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
	public List<UserCustomerIncomeMoneyAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, UserCustomerIncomeMoneyAndOrderTableAndUser unionData) {
		List<UserCustomerIncomeMoneyAndOrderTableAndUser> UserCustomerIncomeMoneyList = new ArrayList<UserCustomerIncomeMoneyAndOrderTableAndUser>();

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			UserCustomerIncomeMoneyList = session.selectList("sun.bz1.dao.mapper.UserCustomerIncomeMoney.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return UserCustomerIncomeMoneyList;
	}
	
	/**
	 * 根据UserCustomerIncomeMoney实体删除
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByUserCustomerIncomeMoney(SqlSession session, UserCustomerIncomeMoney incomeMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerIncomeMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.UserCustomerIncomeMoney.deleteByUserCustomerIncomeMoney", incomeMoney);
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyDao--deleteByUserCustomerIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}
	
}