package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.UserCustomerExpenditureMoney;
import sun.bz1.entity.UserCustomerExpenditureMoneyAndOrderTableAndUser;

/**
 * 用户_客户_支出_金额表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/19
 */

@Repository
public class UserCustomerExpenditureMoneyDao {

	private Logger logger = Logger.getLogger(UserCustomerExpenditureMoneyDao.class);

	public int insert(SqlSession session, UserCustomerExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.insert", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, UserCustomerExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.insertSelective", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, UserCustomerExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.updateByPrimaryKeySelective", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, UserCustomerExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.updateByPrimaryKey", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public UserCustomerExpenditureMoney selectByPrimaryKey(SqlSession session, Integer id) {
		UserCustomerExpenditureMoney UserCustomerExpenditureMoney = new UserCustomerExpenditureMoney();

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			UserCustomerExpenditureMoney = session.selectOne("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return UserCustomerExpenditureMoney;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerExpenditureMoney实体添加
	 * 
	 * @param expenditureMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int insertByUserCustomerExpenditureMoney(SqlSession session, UserCustomerExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.insertByUserCustomerExpenditureMoney", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--insertByUserCustomerExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerExpenditureMoney实体更新
	 * 
	 * @param expenditureMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int updateByUserCustomerExpenditureMoney(SqlSession session, UserCustomerExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.updateByUserCustomerExpenditureMoney", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--updateByUserCustomerExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerExpenditureMoneyAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, UserCustomerExpenditureMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据UserCustomerExpenditureMoneyAndOrderTableAndUser实体联表查询
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
	public List<UserCustomerExpenditureMoneyAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, UserCustomerExpenditureMoneyAndOrderTableAndUser unionData) {
		List<UserCustomerExpenditureMoneyAndOrderTableAndUser> UserCustomerExpenditureMoneyList = new ArrayList<UserCustomerExpenditureMoneyAndOrderTableAndUser>();

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			UserCustomerExpenditureMoneyList = session.selectList("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return UserCustomerExpenditureMoneyList;
	}

	/**
	 * 根据UserCustomerExpenditureMoneyAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, UserCustomerExpenditureMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerExpenditureMoneyAndOrderTableAndUser实体联表模糊查询
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
	public List<UserCustomerExpenditureMoneyAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, UserCustomerExpenditureMoneyAndOrderTableAndUser unionData) {
		List<UserCustomerExpenditureMoneyAndOrderTableAndUser> UserCustomerExpenditureMoneyList = new ArrayList<UserCustomerExpenditureMoneyAndOrderTableAndUser>();

		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			UserCustomerExpenditureMoneyList = session.selectList("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return UserCustomerExpenditureMoneyList;
	}
	
	/**
	 * 根据UserCustomerExpenditureMoney实体删除
	 * 
	 * @param expenditureMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByUserCustomerExpenditureMoney(SqlSession session, UserCustomerExpenditureMoney expenditureMoney) {
		int num = 0;
	
		try {
			logger.info("UserCustomerExpenditureMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.UserCustomerExpenditureMoney.deleteByUserCustomerExpenditureMoney", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyDao--deleteByUserCustomerExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}
	
}