package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.UserExtractMoney;
import sun.bz1.entity.UserExtractMoneyAndUser;

/**
 * 用户_提现表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/10
 */

@Repository
public class UserExtractMoneyDao {
	
	private Logger logger = Logger.getLogger(UserExtractMoneyDao.class);

	public int insert(SqlSession session, UserExtractMoney userExtractMoney) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserExtractMoney.insert", userExtractMoney);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, UserExtractMoney userExtractMoney) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserExtractMoney.insertSelective", userExtractMoney);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, UserExtractMoney userExtractMoney) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserExtractMoney.updateByPrimaryKeySelective", userExtractMoney);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, UserExtractMoney userExtractMoney) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserExtractMoney.updateByPrimaryKey", userExtractMoney);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserExtractMoney.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public UserExtractMoney selectByPrimaryKey(SqlSession session, Integer id) {
		UserExtractMoney UserExtractMoney = new UserExtractMoney();

		try {
			logger.info("UserExtractMoneyDao");

			UserExtractMoney = session.selectOne("sun.bz1.dao.mapper.UserExtractMoney.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return UserExtractMoney;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.UserExtractMoney.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserExtractMoney实体添加
	 * 
	 * @param UserExtractMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int insertByUserExtractMoney(SqlSession session, UserExtractMoney userExtractMoney) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.UserExtractMoney.insertByUserExtractMoney", userExtractMoney);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--insertByUserExtractMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserExtractMoney实体更新
	 * 
	 * @param UserExtractMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int updateByUserExtractMoney(SqlSession session, UserExtractMoney userExtractMoney) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.update("sun.bz1.dao.mapper.UserExtractMoney.updateByUserExtractMoney", userExtractMoney);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--updateByUserExtractMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserExtractMoneyAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int selectTwoTablesCountByUnionData(SqlSession session, UserExtractMoneyAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserExtractMoney.selectTwoTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--selectTwoTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserExtractMoney实体联表查询
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
	 * @author WJF on 2018/09/10
	 */
	public List<UserExtractMoneyAndUser> selectTwoTablesByUnionData(SqlSession session, UserExtractMoneyAndUser unionData) {
		List<UserExtractMoneyAndUser> UserExtractMoneyList = new ArrayList<UserExtractMoneyAndUser>();

		try {
			logger.info("UserExtractMoneyDao");

			UserExtractMoneyList = session.selectList("sun.bz1.dao.mapper.UserExtractMoney.selectTwoTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--selectTwoTablesByUnionData--error:" + e.getMessage());
		}

		return UserExtractMoneyList;
	}
	
	/**
	 * 根据UserExtractMoneyAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int selectTwoTablesCountBySelectData(SqlSession session, UserExtractMoneyAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserExtractMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.UserExtractMoney.selectTwoTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--selectTwoTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserExtractMoneyAndUser实体联表模糊查询
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
	 * @author WJF on 2018/09/10
	 */
	public List<UserExtractMoneyAndUser> selectTwoTablesBySelectData(SqlSession session, UserExtractMoneyAndUser unionData) {
		List<UserExtractMoneyAndUser> applyCheckList = new ArrayList<UserExtractMoneyAndUser>();

		try {
			logger.info("UserExtractMoneyDao");

			applyCheckList = session.selectList("sun.bz1.dao.mapper.UserExtractMoney.selectTwoTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserExtractMoneyDao--selectTwoTablesBySelectData--error:" + e.getMessage());
		}

		return applyCheckList;
	}
	
}