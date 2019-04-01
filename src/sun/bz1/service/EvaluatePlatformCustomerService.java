package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.*;
import sun.bz1.entity.*;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 维修人员评价平台_客户表
 * 
 * Service
 * 
 * @author ZY on 2019/01/21
 */

@Service
public class EvaluatePlatformCustomerService {

	@Autowired
	private EvaluatePlatformCustomerDao evaluateDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CreditChangeDao creditChangeDao;
	@Autowired
	private OrderTableDao orderTableDao;

	private Logger logger = Logger.getLogger(EvaluatePlatformCustomerService.class);
	
	/**
   	 * 根据EvaluatePlatformCustomer实体添加
   	 * 根据User实体更新
   	 * 根据CreditChange实体添加
   	 * 
   	 * @param evaluate
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/05
   	 */
	public int insertByEvaluatePlatformCustomer(EvaluatePlatformCustomer evaluate) {
		int insertEvaluateNum = 0;


		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			insertEvaluateNum = evaluateDao.insertByEvaluatePlatformCustomer(session, evaluate); // 添加维修人员评价客户表

			
			if (insertEvaluateNum == 0 ) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformCustomerService--insertByEvaluatePlatformCustomer--error:" + e.getMessage());
		}

		return insertEvaluateNum;
	}

	/**
	 * 根据EvaluatePlatformCustomer实体更新
	 * 根据User实体更新
   	 * 根据CreditChange实体更新
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByEvaluatePlatformCustomer(EvaluatePlatformCustomer evaluate) {
		int updateEvaluateNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			updateEvaluateNum = evaluateDao.updateByEvaluatePlatformCustomer(session, evaluate); // 更新维修人员评价客户表
			
			if (updateEvaluateNum == 0 ) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformCustomerService--updateByEvaluatePlatformCustomer--error:" + e.getMessage());
		}

		return updateEvaluateNum;
	}

	/**
	 * 根据EvaluateCustomerAndOrderTableAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountByUnionData(EvaluateCustomerAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = evaluateDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformCustomerService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerAndOrderTableAndUser实体联表查询
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
	 * @author WJF on 2018/09/05
	 */
	public List<EvaluateCustomerAndOrderTableAndUser> selectThreeTablesByUnionData(EvaluateCustomerAndOrderTableAndUser unionData) {
		List<EvaluateCustomerAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateCustomerAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = evaluateDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformCustomerService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return evaluateList;
	}

	/**
	 * 根据EvaluateCustomerAndOrderTableAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountBySelectData(EvaluateCustomerAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = evaluateDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformCustomerService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerAndOrderTableAndUser实体联表模糊查询
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
	 * @author WJF on 2018/09/05
	 */
	public List<EvaluateCustomerAndOrderTableAndUser> selectThreeTablesBySelectData(EvaluateCustomerAndOrderTableAndUser unionData) {
		List<EvaluateCustomerAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateCustomerAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = evaluateDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformCustomerService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return evaluateList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + evaluateDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformCustomerService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据UUIDList删除信息
	 * 
	 * 根据EvaluatePlatformCustomer实体删除
   	 * 根据User实体更新
   	 * 根据CreditChange实体删除
	 * 
	 * @param UUIDList
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByUUIDList(List<String> evaluateIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < evaluateIdList.size(); i++) {
				// 根据UUID查询维修人员评价客户数据
				EvaluateCustomerAndOrderTableAndUser selectEvaluate = new EvaluateCustomerAndOrderTableAndUser();
				selectEvaluate.setEvaluatecustomerid(evaluateIdList.get(i));
				selectEvaluate.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
				List<EvaluateCustomerAndOrderTableAndUser> evaluateList = null;
				evaluateList = evaluateDao.selectThreeTablesByUnionData(session, selectEvaluate); // 查询
				if (evaluateList == null) {
					return 0;
				}
				
				// 维修人员评价客户数据
				EvaluateCustomerAndOrderTableAndUser evaluate = evaluateList.get(0);
				
				// 更新用户表(客户信用值)
				User customerUser = new User();
				// 查询
				UserAndUserServiceAndUserCustomer selectCustomerUser = new UserAndUserServiceAndUserCustomer();
				selectCustomerUser.setUserid(evaluate.getCustomeruserid()); // 客户ID(YHBG+UUID)
				selectCustomerUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
				List<UserAndUserServiceAndUserCustomer> customerUserList = null;
				customerUserList = userDao.selectThreeTablesByUnionData(session, selectCustomerUser); // 查询
				if (customerUserList == null || customerUserList.size() == 0) {
					return 0;
				} else {
					// 数据
					customerUser.setUserid(customerUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
					customerUser.setUsercredit(customerUserList.get(0).getUsercredit() - evaluate.getEvaluatecustomerscore()); // 信用值
					customerUser.setUserupdateuserid(evaluate.getServiceuserid()); // 当前操作人员(维修人员)ID // 用户更新人ID(YHBG+UUID)
					customerUser.setUserupdatetime(new Date()); // 用户更新时间
					
					// 更新
					userDao.updateByUser(session, customerUser); 
				}
								
				// 查询之前添加的信用值变化表，并删除
				// 查询
				CreditChange selectCreditChange = new CreditChange();
				selectCreditChange.setEvaluatid(evaluate.getEvaluatecustomerid());
				selectCreditChange.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
				List<CreditChange> creditChangeList = null;
				creditChangeList = creditChangeDao.selectByCreditChange(session, selectCreditChange);
				if (creditChangeList == null || creditChangeList.size() == 0) {
					return 0;
				} else {
					// 删除
					creditChangeDao.deleteByPrimaryKey(session, creditChangeList.get(0).getId());
				}
				
				// 删除维修人员评价客户表
				EvaluatePlatformCustomer deleteEvaluate = new EvaluatePlatformCustomer();
				deleteEvaluate.setEvaluateplatformcustomerid(evaluateIdList.get(i));
				
				// 删除
				deleteNum = deleteNum + evaluateDao.deleteByEvaluatePlatformCustomer(session, deleteEvaluate);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformCustomerService--deleteByUUIDList--error:" + e.getMessage());
		}

		return deleteNum;
	}

	/**
	 * 根据EvaluatePlatformCustomer实体添加
	 * 根据User实体更新
	 * 根据CreditChange实体添加
	 *
	 * @param evaluate
	 * @return
	 *
	 * @author WJF on 2018/09/05
	 */
	public int insertByEvaluatePlatformCustomerUpdateOrderState(EvaluatePlatformCustomer evaluate, User customerUser, CreditChange creditChange, OrderTable orderTable) {
		int insertEvaluateNum = 0;
		int updateUserNum = 0;
		int insertCreditChangeNum = 0;
		int updateOrderTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			insertEvaluateNum = evaluateDao.insertByEvaluatePlatformCustomer(session, evaluate); // 添加维修人员评价客户表
			updateUserNum = userDao.updateByUser(session, customerUser); // 更新用户表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange); // 添加信用值变化表
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);//更新订单状态

			if (insertEvaluateNum == 0 || updateUserNum == 0 || insertCreditChangeNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformCustomerService--insertByEvaluatePlatformCustomer--error:" + e.getMessage());
		}

		return insertEvaluateNum;
	}
	
}
