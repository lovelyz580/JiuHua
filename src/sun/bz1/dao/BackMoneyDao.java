package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.BackMoney;
import sun.bz1.entity.BackMoneyAndOrderTable;
import sun.bz1.entity.BackMoneyAndOrderTableAndUserPaymentAndUser;

/**
 * 平台_收益金额表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/10
 */

@Repository
public class BackMoneyDao {

	private Logger logger = Logger.getLogger(BackMoneyDao.class);

	public int insert(SqlSession session, BackMoney backMoney) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.BackMoney.insert", backMoney);
		} catch (Exception e) {
			logger.error("BackMoneyDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, BackMoney backMoney) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.BackMoney.insertSelective", backMoney);
		} catch (Exception e) {
			logger.error("BackMoneyDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, BackMoney backMoney) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.update("sun.bz1.dao.mapper.BackMoney.updateByPrimaryKeySelective", backMoney);
		} catch (Exception e) {
			logger.error("BackMoneyDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, BackMoney backMoney) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.update("sun.bz1.dao.mapper.BackMoney.updateByPrimaryKey", backMoney);
		} catch (Exception e) {
			logger.error("BackMoneyDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.BackMoney.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public BackMoney selectByPrimaryKey(SqlSession session, Integer id) {
		BackMoney backMoney = new BackMoney();

		try {
			logger.info("BackMoneyDao");

			backMoney = session.selectOne("sun.bz1.dao.mapper.BackMoney.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return backMoney;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.BackMoney.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("BackMoneyDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BackMoney实体添加
	 * 
	 * @param backMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int insertByBackMoney(SqlSession session, BackMoney backMoney) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.insert("sun.bz1.dao.mapper.BackMoney.insertByBackMoney", backMoney);
		} catch (Exception e) {
			logger.error("BackMoneyDao--insertByBackMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BackMoney实体更新
	 * 
	 * @param backMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int updateByBackMoney(SqlSession session, BackMoney backMoney) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.update("sun.bz1.dao.mapper.BackMoney.updateByBackMoney", backMoney);
		} catch (Exception e) {
			logger.error("BackMoneyDao--updateByBackMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BackMoneyAndOrderTable实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int selectTwoTablesCountByUnionData(SqlSession session, BackMoneyAndOrderTable unionData) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.BackMoney.selectTwoTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectTwoTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据BackMoneyAndOrderTable实体联表查询
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
	public List<BackMoneyAndOrderTable> selectTwoTablesByUnionData(SqlSession session, BackMoneyAndOrderTable unionData) {
		List<BackMoneyAndOrderTable> backMoneyList = new ArrayList<BackMoneyAndOrderTable>();

		try {
			logger.info("BackMoneyDao");

			backMoneyList = session.selectList("sun.bz1.dao.mapper.BackMoney.selectTwoTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectTwoTablesByUnionData--error:" + e.getMessage());
		}

		return backMoneyList;
	}

	/**
	 * 根据BackMoneyAndOrderTable实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int selectTwoTablesCountBySelectData(SqlSession session, BackMoneyAndOrderTable unionData) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.BackMoney.selectTwoTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectTwoTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BackMoneyAndOrderTable实体联表模糊查询
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
	public List<BackMoneyAndOrderTable> selectTwoTablesBySelectData(SqlSession session, BackMoneyAndOrderTable unionData) {
		List<BackMoneyAndOrderTable> backMoneyList = new ArrayList<BackMoneyAndOrderTable>();

		try {
			logger.info("BackMoneyDao");

			backMoneyList = session.selectList("sun.bz1.dao.mapper.BackMoney.selectTwoTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectTwoTablesBySelectData--error:" + e.getMessage());
		}

		return backMoneyList;
	}

	/**
	 * 根据BackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成客户给平台付款的报表)
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/13
	 */
	public int selectFourTablesCountBySelectDataIncomeReport(SqlSession session, BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.BackMoney.selectFourTablesCountBySelectDataIncomeReport", unionData);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectFourTablesCountBySelectDataIncomeReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BBackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成客户给平台付款的报表)
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
	 * @author ZY on 2018/12/13
	 */
	public List<BackMoneyAndOrderTableAndUserPaymentAndUser> selectFourTablesBySelectDataIncomeReport(SqlSession session, BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		List<BackMoneyAndOrderTableAndUserPaymentAndUser> backMoneyList = new ArrayList<BackMoneyAndOrderTableAndUserPaymentAndUser>();

		try {
			logger.info("BackMoneyDao");

			backMoneyList = session.selectList("sun.bz1.dao.mapper.BackMoney.selectFourTablesBySelectDataIncomeReport", unionData);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectFourTablesBySelectDataIncomeReport--error:" + e.getMessage());
		}

		return backMoneyList;
	}


	/**
	 * 根据BackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成平台付款给维修的报表)
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/13
	 */
	public int selectFourTablesCountBySelectDataServiceReport(SqlSession session, BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.selectOne("sun.bz1.dao.mapper.BackMoney.selectFourTablesCountBySelectDataServiceReport", unionData);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectFourTablesCountBySelectDataServiceReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BBackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成平台付款给维修的报表)
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
	 * @author ZY on 2018/12/13
	 */
	public List<BackMoneyAndOrderTableAndUserPaymentAndUser> selectFourTablesBySelectDataServiceReport(SqlSession session, BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		List<BackMoneyAndOrderTableAndUserPaymentAndUser> backMoneyList = new ArrayList<BackMoneyAndOrderTableAndUserPaymentAndUser>();

		try {
			logger.info("BackMoneyDao");

			backMoneyList = session.selectList("sun.bz1.dao.mapper.BackMoney.selectFourTablesBySelectDataServiceReport", unionData);
		} catch (Exception e) {
			logger.error("BackMoneyDao--selectFourTablesBySelectDataServiceReport--error:" + e.getMessage());
		}

		return backMoneyList;
	}
	
	/**
	 * 根据BackMoney实体删除
	 * 
	 * @param backMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int deleteByBackMoney(SqlSession session, BackMoney backMoney) {
		int num = 0;

		try {
			logger.info("BackMoneyDao");

			num = session.delete("sun.bz1.dao.mapper.BackMoney.deleteByBackMoney", backMoney);
		} catch (Exception e) {
			logger.error("BackMoneyDao--deleteByBackMoney--error:" + e.getMessage());
		}

		return num;
	}
	
}