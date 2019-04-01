package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.UserServiceExpenditureMoney;
import sun.bz1.entity.UserServiceExpenditureMoneyAndOrderTableAndUser;

/**
 * 用户_维修工、安装队_支出_金额表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/19
 */

@Repository
public class UserServiceExpenditureMoneyDao {

	private Logger logger = Logger.getLogger(UserServiceExpenditureMoneyDao.class);

	public int insert(SqlSession session, UserServiceExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserServiceExpenditureMoney.insert", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, UserServiceExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserServiceExpenditureMoney.insertSelective", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, UserServiceExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserServiceExpenditureMoney.updateByPrimaryKeySelective", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, UserServiceExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserServiceExpenditureMoney.updateByPrimaryKey", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserServiceExpenditureMoney.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public UserServiceExpenditureMoney selectByPrimaryKey(SqlSession session, Integer id) {
		UserServiceExpenditureMoney UserServiceExpenditureMoney = new UserServiceExpenditureMoney();

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			UserServiceExpenditureMoney = session.selectOne("sun.bz1.dao.mapper.UserServiceExpenditureMoney.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return UserServiceExpenditureMoney;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.UserServiceExpenditureMoney.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceExpenditureMoney实体添加
	 * 
	 * @param expenditureMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int insertByUserServiceExpenditureMoney(SqlSession session, UserServiceExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserServiceExpenditureMoney.insertByUserServiceExpenditureMoney", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--insertByUserServiceExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceExpenditureMoney实体更新
	 * 
	 * @param expenditureMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int updateByUserServiceExpenditureMoney(SqlSession session, UserServiceExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserServiceExpenditureMoney.updateByUserServiceExpenditureMoney", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--updateByUserServiceExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceExpenditureMoneyAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, UserServiceExpenditureMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserServiceExpenditureMoney.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据UserServiceExpenditureMoneyAndOrderTableAndUser实体联表查询
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
	public List<UserServiceExpenditureMoneyAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, UserServiceExpenditureMoneyAndOrderTableAndUser unionData) {
		List<UserServiceExpenditureMoneyAndOrderTableAndUser> UserServiceExpenditureMoneyList = new ArrayList<UserServiceExpenditureMoneyAndOrderTableAndUser>();

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			UserServiceExpenditureMoneyList = session.selectList("sun.bz1.dao.mapper.UserServiceExpenditureMoney.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return UserServiceExpenditureMoneyList;
	}

	/**
	 * 根据UserServiceExpenditureMoneyAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, UserServiceExpenditureMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserServiceExpenditureMoney.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceExpenditureMoneyAndOrderTableAndUser实体联表模糊查询
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
	public List<UserServiceExpenditureMoneyAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, UserServiceExpenditureMoneyAndOrderTableAndUser unionData) {
		List<UserServiceExpenditureMoneyAndOrderTableAndUser> UserServiceExpenditureMoneyList = new ArrayList<UserServiceExpenditureMoneyAndOrderTableAndUser>();

		try {
			logger.info("UserServiceExpenditureMoneyDao");

			UserServiceExpenditureMoneyList = session.selectList("sun.bz1.dao.mapper.UserServiceExpenditureMoney.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return UserServiceExpenditureMoneyList;
	}
	
	/**
	 * 根据UserServiceExpenditureMoney实体删除
	 * 
	 * @param expenditureMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByUserServiceExpenditureMoney(SqlSession session, UserServiceExpenditureMoney expenditureMoney) {
		int num = 0;
	
		try {
			logger.info("UserServiceExpenditureMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.UserServiceExpenditureMoney.deleteByUserServiceExpenditureMoney", expenditureMoney);
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyDao--deleteByUserServiceExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}
	
}