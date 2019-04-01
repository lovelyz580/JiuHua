package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.BackMoneyDao;
import sun.bz1.entity.BackMoney;
import sun.bz1.entity.BackMoneyAndOrderTable;
import sun.bz1.entity.BackMoneyAndOrderTableAndUserPaymentAndUser;
import util.MyBatisUtil;

/**
 * 平台_收益金额表
 * 
 * Service
 * 
 * @author WJF on 2018/09/10
 */

@Service
public class BackMoneyService {

	@Autowired
	private BackMoneyDao backMoneyDao;

	private Logger logger = Logger.getLogger(BackMoneyService.class);
	
	/**
   	 * 根据BackMoney实体添加
   	 * 
   	 * @param backMoney
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/10
   	 */
	public int insertByBackMoney(BackMoney backMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = backMoneyDao.insertByBackMoney(session, backMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--insertByBackMoney--error:" + e.getMessage());
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
	public int updateByBackMoney(BackMoney backMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = backMoneyDao.updateByBackMoney(session, backMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--updateByBackMoney--error:" + e.getMessage());
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
	public int selectTwoTablesCountByUnionData(BackMoneyAndOrderTable unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = backMoneyDao.selectTwoTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--selectTwoTablesCountByUnionData--error:" + e.getMessage());
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
	public List<BackMoneyAndOrderTable> selectTwoTablesByUnionData(BackMoneyAndOrderTable unionData) {
		List<BackMoneyAndOrderTable> backMoneyList = new ArrayList<BackMoneyAndOrderTable>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			backMoneyList = backMoneyDao.selectTwoTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--selectTwoTablesByUnionData--error:" + e.getMessage());
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
	public int selectTwoTablesCountBySelectData(BackMoneyAndOrderTable unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = backMoneyDao.selectTwoTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--selectTwoTablesCountBySelectData--error:" + e.getMessage());
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
	public List<BackMoneyAndOrderTable> selectTwoTablesBySelectData(BackMoneyAndOrderTable unionData) {
		List<BackMoneyAndOrderTable> backMoneyList = new ArrayList<BackMoneyAndOrderTable>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			backMoneyList = backMoneyDao.selectTwoTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--selectTwoTablesBySelectData--error:" + e.getMessage());
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
	public int selectFourTablesCountBySelectDataIncomeReport(BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = backMoneyDao.selectFourTablesCountBySelectDataIncomeReport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--selectFourTablesCountBySelectDataIncomeReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成客户给平台付款的报表)
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
	public List<BackMoneyAndOrderTableAndUserPaymentAndUser> selectFourTablesBySelectDataIncomeReport(BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		List<BackMoneyAndOrderTableAndUserPaymentAndUser> backMoneyList = new ArrayList<BackMoneyAndOrderTableAndUserPaymentAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			backMoneyList = backMoneyDao.selectFourTablesBySelectDataIncomeReport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--selectFourTablesBySelectDataIncomeReport--error:" + e.getMessage());
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
	public int selectFourTablesCountBySelectDataServiceReport(BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = backMoneyDao.selectFourTablesCountBySelectDataServiceReport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--selectFourTablesCountBySelectDataServiceReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成平台付款给维修的报表)
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
	public List<BackMoneyAndOrderTableAndUserPaymentAndUser> selectFourTablesBySelectDataServiceReport(BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		List<BackMoneyAndOrderTableAndUserPaymentAndUser> backMoneyList = new ArrayList<BackMoneyAndOrderTableAndUserPaymentAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			backMoneyList = backMoneyDao.selectFourTablesBySelectDataServiceReport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--selectFourTablesBySelectDataServiceReport--error:" + e.getMessage());
		}

		return backMoneyList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + backMoneyDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				BackMoney backMoney = new BackMoney();
				backMoney.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + backMoneyDao.deleteByBackMoney(session, backMoney);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BackMoneyService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
