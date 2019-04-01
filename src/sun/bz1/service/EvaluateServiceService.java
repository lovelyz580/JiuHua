package sun.bz1.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.*;
import sun.bz1.entity.*;
import util.MyBatisUtil;

/**
 * 客户评价维修人员表
 * 
 * Service
 * 
 * @author WJF on 2018/09/05
 */

@Service
public class EvaluateServiceService {

	@Autowired
	private EvaluateServiceDao evaluateDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CreditChangeDao creditChangeDao;

	@Autowired
	private OrderTableDao orderTableDao;

	@Autowired
	private EvaluatePlatformServiceDao evaluatePlatformServiceDao;

	private Logger logger = Logger.getLogger(EvaluateServiceService.class);
	
	/**
   	 * 根据EvaluateService实体添加
   	 * 根据User实体更新
   	 * 根据CreditChange实体添加
   	 * 
   	 * @param evaluate
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/05
   	 */
	public int insertByEvaluateService(EvaluateService evaluate, User customerUser, CreditChange creditChange) {
		int insertEvaluateNum = 0;
		int updateUserNum = 0;
		int insertCreditChangeNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			insertEvaluateNum = evaluateDao.insertByEvaluateService(session, evaluate); // 添加客户评价维修人员表
			updateUserNum = userDao.updateByUser(session, customerUser); // 更新用户表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange); // 添加信用值变化表

			if (insertEvaluateNum == 0 || updateUserNum == 0 || insertCreditChangeNum == 0) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceService--insertByEvaluateService--error:" + e.getMessage());
		}

		return insertEvaluateNum;
	}

	/**
	 * 根据EvaluateService实体更新
	 * 根据User实体更新
   	 * 根据CreditChange实体更新
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByEvaluateService(EvaluateService evaluate, User customerUser, CreditChange creditChange) {
		int updateEvaluateNum = 0;
		int updateUserNum = 0;
		int updateCreditChangeNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			updateEvaluateNum = evaluateDao.updateByEvaluateService(session, evaluate); // 更新客户评价维修人员表
			updateUserNum = userDao.updateByUser(session, customerUser); // 更新用户表
			updateCreditChangeNum = creditChangeDao.updateByCreditChange(session, creditChange); // 更新信用值变化表
			
			if (updateEvaluateNum == 0 || updateUserNum == 0 || updateCreditChangeNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceService--updateByEvaluateService--error:" + e.getMessage());
		}

		return updateEvaluateNum;
	}

	/**
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountByUnionData(EvaluateServiceAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = evaluateDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表查询
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
	public List<EvaluateServiceAndOrderTableAndUser> selectThreeTablesByUnionData(EvaluateServiceAndOrderTableAndUser unionData) {
		List<EvaluateServiceAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateServiceAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = evaluateDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return evaluateList;
	}

	/**
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountBySelectData(EvaluateServiceAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = evaluateDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表模糊查询
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
	public List<EvaluateServiceAndOrderTableAndUser> selectThreeTablesBySelectData(EvaluateServiceAndOrderTableAndUser unionData) {
		List<EvaluateServiceAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateServiceAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = evaluateDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceService--selectThreeTablesBySelectData--error:" + e.getMessage());
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
			logger.error("EvaluateServiceService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据UUIDList删除信息
	 * 
	 * 根据EvaluateService实体删除
   	 * 根据User实体更新
   	 * 根据CreditChange实体删除
	 * 
	 * @param evaluateIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByUUIDList(List<String> evaluateIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < evaluateIdList.size(); i++) {
				// 根据UUID查询客户评价维修人员数据
				EvaluateServiceAndOrderTableAndUser selectEvaluate = new EvaluateServiceAndOrderTableAndUser();
				selectEvaluate.setEvaluateserviceid(evaluateIdList.get(i));
				selectEvaluate.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
				List<EvaluateServiceAndOrderTableAndUser> evaluateList = null;
				evaluateList = evaluateDao.selectThreeTablesByUnionData(session, selectEvaluate); // 查询
				if (evaluateList == null) {
					return 0;
				}
				
				// 客户评价维修人员数据
				EvaluateServiceAndOrderTableAndUser evaluate = evaluateList.get(0);
				
				// 更新用户表(客户信用值)
				User serviceUser = new User();
				// 查询
				UserAndUserServiceAndUserCustomer selectServiceUser = new UserAndUserServiceAndUserCustomer();
				selectServiceUser.setUserid(evaluate.getCustomeruserid()); // 客户ID(YHBG+UUID)
				selectServiceUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
				List<UserAndUserServiceAndUserCustomer> serviceUserList = null;
				serviceUserList = userDao.selectThreeTablesByUnionData(session, selectServiceUser); // 查询
				if (serviceUserList == null || serviceUserList.size() == 0) {
					return 0;
				} else {
					// 数据
					serviceUser.setUserid(serviceUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
					serviceUser.setUsercredit(serviceUserList.get(0).getUsercredit() - evaluate.getEvaluateservicescore()); // 信用值
					serviceUser.setUserupdateuserid(evaluate.getCustomeruserid()); // 当前操作人员(客户)ID // 用户更新人ID(YHBG+UUID)
					serviceUser.setUserupdatetime(new Date()); // 用户更新时间
					
					// 更新
					userDao.updateByUser(session, serviceUser); 
				}
								
				// 查询之前添加的信用值变化表，并删除
				// 查询
				CreditChange selectCreditChange = new CreditChange();
				selectCreditChange.setEvaluatid(evaluate.getEvaluateserviceid());
				selectCreditChange.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
				List<CreditChange> creditChangeList = null;
				creditChangeList = creditChangeDao.selectByCreditChange(session, selectCreditChange);
				if (creditChangeList == null || creditChangeList.size() == 0) {
					return 0;
				} else {
					// 删除
					creditChangeDao.deleteByPrimaryKey(session, creditChangeList.get(0).getId());
				}
				
				// 删除客户评价维修人员表
				EvaluateService deleteEvaluate = new EvaluateService();
				deleteEvaluate.setEvaluateserviceid(evaluateIdList.get(i));
				
				// 删除
				deleteNum = deleteNum + evaluateDao.deleteByEvaluateService(session, deleteEvaluate);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateCustomerService--deleteByUUIDList--error:" + e.getMessage());
		}

		return deleteNum;
	}

	/**
	 * 根据EvaluateService实体添加
	 * 根据User实体更新
	 * 根据CreditChange实体添加
	 *
	 * @param evaluate
	 * @return
	 *
	 * @author ZY on 2018/10/23
	 */
	public int insertByEvaluateServiceUpdateOrderState(EvaluateService evaluate,EvaluatePlatformService evaluatePlatformService, User customerUser, CreditChange creditChange, OrderTable orderTable) {
		int insertEvaluateNum = 0;
		int updateUserNum = 0;
		int insertCreditChangeNum = 0;
		int updateOrderTableNum = 0;
		int insertEvaluatePlatformService = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			insertEvaluateNum = evaluateDao.insertByEvaluateService(session, evaluate); // 添加客户评价维修人员表
			updateUserNum = userDao.updateByUser(session, customerUser); // 更新用户表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange); // 添加信用值变化表
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);//更新订单状态
			insertEvaluatePlatformService = evaluatePlatformServiceDao.insertByEvaluatePlatformService(session, evaluatePlatformService);

			if (insertEvaluateNum == 0 || updateUserNum == 0 || insertCreditChangeNum == 0 || updateOrderTableNum == 0|| insertEvaluatePlatformService == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceService--insertByEvaluateServiceUpdateOrderState--error:" + e.getMessage());
		}

		return insertEvaluateNum;
	}
	
}
