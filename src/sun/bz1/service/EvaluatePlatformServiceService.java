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
 * 客户评价平台_维修人员表
 * 
 * Service
 * 
 * @author ZY on 2019/01/21
 */

@Service
public class EvaluatePlatformServiceService {

	@Autowired
	private EvaluatePlatformServiceDao evaluateDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CreditChangeDao creditChangeDao;

	@Autowired
	private OrderTableDao orderTableDao;

	private Logger logger = Logger.getLogger(EvaluatePlatformServiceService.class);
	
	/**
   	 * 根据EvaluatePlatformService实体添加
   	 * 根据User实体更新
   	 * 根据CreditChange实体添加
   	 * 
   	 * @param evaluate
   	 * @return
   	 * 
   	 * @author ZY on 2019/01/21
   	 */
	public int insertByEvaluatePlatformService(EvaluatePlatformService evaluate) {
		int insertEvaluateNum = 0;


		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			insertEvaluateNum = evaluateDao.insertByEvaluatePlatformService(session, evaluate); // 添加客户评价维修人员表

			if (insertEvaluateNum == 0 ) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformServiceService--insertByEvaluatePlatformService--error:" + e.getMessage());
		}

		return insertEvaluateNum;
	}

	/**
	 * 根据EvaluatePlatformService实体更新
	 * 根据User实体更新
   	 * 根据CreditChange实体更新
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author ZY on 2019/01/21
	 */
	public int updateByEvaluatePlatformService(EvaluatePlatformService evaluate) {
		int updateEvaluateNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			updateEvaluateNum = evaluateDao.updateByEvaluatePlatformService(session, evaluate); // 更新客户评价维修人员表

			
			if (updateEvaluateNum == 0 ) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformServiceService--updateByEvaluatePlatformService--error:" + e.getMessage());
		}

		return updateEvaluateNum;
	}

	/**
	 * 根据EvaluatePlatformServiceAndOrderTableAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2019/01/21
	 */
	public int selectThreeTablesCountByUnionData(EvaluateServiceAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = evaluateDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformServiceService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
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
	 * @author ZY on 2019/01/21
	 */
	public List<EvaluateServiceAndOrderTableAndUser> selectThreeTablesByUnionData(EvaluateServiceAndOrderTableAndUser unionData) {
		List<EvaluateServiceAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateServiceAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = evaluateDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformServiceService--selectThreeTablesByUnionData--error:" + e.getMessage());
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
	 * @author ZY on 2019/01/21
	 */
	public int selectThreeTablesCountBySelectData(EvaluateServiceAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = evaluateDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformServiceService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
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
	 * @author ZY on 2019/01/21
	 */
	public List<EvaluateServiceAndOrderTableAndUser> selectThreeTablesBySelectData(EvaluateServiceAndOrderTableAndUser unionData) {
		List<EvaluateServiceAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateServiceAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = evaluateDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformServiceService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return evaluateList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2019/01/21
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
			logger.error("EvaluatePlatformServiceService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据UUIDList删除信息
	 * 
	 * 根据EvaluatePlatformService实体删除
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
				EvaluatePlatformService deleteEvaluate = new EvaluatePlatformService();
				deleteEvaluate.setEvaluateplatformserviceid(evaluateIdList.get(i));
				
				// 删除
				deleteNum = deleteNum + evaluateDao.deleteByEvaluatePlatformService(session, deleteEvaluate);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateCustomerService--deleteByUUIDList--error:" + e.getMessage());
		}

		return deleteNum;
	}

	/**
	 * 根据EvaluatePlatformService实体添加
	 * 根据User实体更新
	 * 根据CreditChange实体添加
	 *
	 * @param evaluate
	 * @return
	 *
	 * @author ZY on 2018/10/23
	 */
	public int insertByEvaluatePlatformServiceUpdateOrderState(EvaluatePlatformService evaluate, User customerUser, CreditChange creditChange, OrderTable orderTable) {
		int insertEvaluateNum = 0;
		int updateUserNum = 0;
		int insertCreditChangeNum = 0;
		int updateOrderTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			insertEvaluateNum = evaluateDao.insertByEvaluatePlatformService(session, evaluate); // 添加客户评价维修人员表
			updateUserNum = userDao.updateByUser(session, customerUser); // 更新用户表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange); // 添加信用值变化表
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);//更新订单状态

			if (insertEvaluateNum == 0 || updateUserNum == 0 || insertCreditChangeNum == 0 || updateOrderTableNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluatePlatformServiceService--insertByEvaluateServiceUpdateOrderState--error:" + e.getMessage());
		}

		return insertEvaluateNum;
	}
	
}
