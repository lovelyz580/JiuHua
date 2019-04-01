package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.*;
import sun.bz1.entity.*;
import util.MyBatisUtil;

/**
 * 维修验收表(验收成功或失败时在该表中添加数据)
 * 
 * Service
 * 
 * @author ZY on 2018/09/15
 */
@Service
public class OperationCheckService {

	@Autowired
	private ApplyCheckDao applyCheckDao;
	
	@Autowired
	private OrderTableDao orderTableDao;
	
	@Autowired
	private UserDao  userDao;
	
	@Autowired
	private CreditChangeDao creditChangeDao;
	
	@Autowired
	private OrderPriceDao orderPriceDao;
	
	@Autowired
	private UserCustomerIncomeMoneyDao customerIncomeMoneyDao;
	
	@Autowired
	private UserServiceIncomeMoneyDao serviceIncomeMoneyDao;
	
	@Autowired
	private BackMoneyDao backMoneyDao;
	
	@Autowired
	private TaskDao taskDao;

	@Autowired
	private ApplyCheckPlatformDao applyCheckPlatformDao;
	
	private Logger logger = Logger.getLogger(OperationCheckService.class);
	
	/**
	 * 申请验收
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、更新维修单表(状态)
	 * 3、添加验收表
	 * 
	 * @param orderTable
	 * @param applyCheck
	 * @return
	 * 
	 * @author ZY on 2018/09/15
	 */
	public int saveOrderAndCheck(OrderTable orderTable, ApplyCheck applyCheck, ApplyCheckPlatform applyCheckPlatform) {
		int updateOrderTableNum = 0;
		int insertApplyCheckNum = 0;
		int insertApplyCheckPlatformNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			// 添加验收表
			insertApplyCheckNum = applyCheckDao.insertByApplyCheck(session, applyCheck);

			// 添加验收平台_维修人员表
			insertApplyCheckPlatformNum = applyCheckPlatformDao.insertByApplyCheckPlatform(session, applyCheckPlatform);
			
			if (updateOrderTableNum == 0 || insertApplyCheckNum == 0 || insertApplyCheckPlatformNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCheckService--saveOrderAndCheck--error:" + e.getMessage());
		}

		return insertApplyCheckNum;
	}
	
	/**
	 * 验收合格
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、更新维修单表(状态)
	 * 3、更新验收表(状态)
	 * 4、更新任务表(状态)
	 * 
	 * 金额数据：从平台中按 维修总价+维修差旅费总价 支出金额给维修人员
	 * 
	 * 5、更新用户表(维修人员信用值+5、余额)
	 * 6、添加信用值变化表
	 * 7、添加用户_维修工、安装队_收入_金额表
	 * 8、添加平台_收益金额表
	 * 
	 * @param orderTable
	 * @param applyCheck
	 * @param task
	 * @return
	 * 
	 * @author ZY on 2018/09/15
	 */
	public int updateApplyCheckQualified(OrderTable orderTable, ApplyCheck applyCheck,ApplyCheckPlatform applyCheckPlatform, Task task, User serviceUser, CreditChange creditChange) {
		int updateOrderTableNum = 0;
		int updateApplyCheckNum = 0;
		int updateTaskNum = 0;
		int updateUserNum = 0;
		int insertCreditChangeNum = 0;
		int insertServiceIncomeMoneyNum = 0;
		int insertBackMoneyNum = 0;
		int updateApplyCheckPlatformNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
						
			// 更新验收表(状态)
			updateApplyCheckNum = applyCheckDao.updateByApplyCheck(session, applyCheck);

			// 更新验收平台_维修人员表(状态)
			updateApplyCheckPlatformNum = applyCheckPlatformDao.updateByApplyCheckPlatform(session, applyCheckPlatform);
			
			// 更新任务表(状态)
			updateTaskNum = taskDao.updateByTask(session, task);
			
			// 更新用户表(维修人员信用值+5、余额)
			updateUserNum = userDao.updateByUser(session, serviceUser);
			
			// 添加信用值变化表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange);
			
			/*// 添加用户_维修工、安装队_收入_金额表
			insertServiceIncomeMoneyNum = serviceIncomeMoneyDao.insertByUserServiceIncomeMoney(session, serviceIncomeMoney);
			
			// 添加平台_收益金额表
			insertBackMoneyNum = backMoneyDao.insertByBackMoney(session, backMoney);*/
			
			if (updateOrderTableNum == 0 || updateApplyCheckNum == 0 || updateTaskNum == 0 || updateUserNum == 0 || insertCreditChangeNum == 0 || updateApplyCheckPlatformNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCheckService--updateApplyCheckQualified--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * 验收不合格更换维修人员(派单模式)
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、更新维修单表(状态)
	 * 3、更新验收表(状态)
	 * 4、更新任务表(状态)
	 * 5、更新用户表(维修人员信用值-10)
	 * 6、添加信用值变化表
	 * 7、删除维修单对应维修单价表对应数据
	 * 
	 * 金额数据：从平台中按 维修推荐总价+维修差旅费推荐总价 支出金额给客户
	 * 
	 * 8、更新用户表(客户余额)
	 * 9、添加用户_客户_收入_金额表
	 * 10、添加平台_收益金额表
	 * 
	 * @param orderTable
	 * @param applyCheck
	 * @param task
	 * @param serviceUser
	 * @param creditChange
	 * @param orderPrice
	 * @return
	 * 
	 * @author ZY on 2018/09/17
	 */
	public int updateApplyCheckUnqualifiedAndChange(OrderTable orderTable, ApplyCheck applyCheck, Task task,
			User serviceUser, CreditChange creditChange, OrderPrice orderPrice) {
		int updateOrderTableNum = 0;
		int updateApplyCheckNum = 0;
		int updateTaskNum = 0;
		int updateServiceUserNum = 0;
		int insertCreditChangeNum = 0;
		int updateCustomerUserNum = 0;
		int insertCustomerIncomeMoneyNum = 0;
		int inasertBackMoneyNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
						
			// 更新验收表(状态)
			updateApplyCheckNum = applyCheckDao.updateByApplyCheck(session, applyCheck);
			
			// 更新任务表(状态)
			updateTaskNum = taskDao.updateByTask(session, task);
			
			// 更新用户表(维修人员信用值-10)
			updateServiceUserNum = userDao.updateByUser(session, serviceUser);
			
			// 添加信用值变化表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange);
						
			// 删除维修单对应维修单价表对应数据
			orderPriceDao.deleteByOrderPrice(session, orderPrice);
			
			/*// 更新用户表(客户余额)
			updateCustomerUserNum = userDao.updateByUser(session, customerUser);
			
			// 添加用户_客户_收入_金额表
			insertCustomerIncomeMoneyNum = customerIncomeMoneyDao.insertByUserCustomerIncomeMoney(session, customerIncomeMoney);
			
			// 添加平台_收益金额表
			inasertBackMoneyNum = backMoneyDao.insertByBackMoney(session, backMoney);*/
			
			if (updateOrderTableNum == 0 || updateApplyCheckNum == 0 || updateTaskNum == 0 || updateServiceUserNum == 0 ) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCheckService--updateApplyCheckUnqualifiedAndChange--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * 验收不合格委托平台派单(抢单模式)
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、更新维修单表(状态)
	 * 3、更新验收表(状态)
	 * 4、更新任务表(状态)
	 * 5、更新用户表(维修人员信用值-10)
	 * 6、添加信用值变化表
	 * 7、删除维修单对应维修单价表对应数据
	 * 
	 * 金额数据：从平台中按 维修推荐总价+维修差旅费推荐总价 支出金额给客户
	 * 
	 * 8、更新用户表(客户余额)
	 * 9、添加用户_客户_收入_金额表
	 * 10、添加平台_收益金额表
	 * 
	 * @param orderTable
	 * @param applyCheck
	 * @param task
	 * @param serviceUser
	 * @param creditChange
	 * @param orderPrice
	 * @return
	 * 
	 * @author ZY on 2018/09/17
	 */
	public int updateApplyCheckUnqualifiedAndPicketing(OrderTable orderTable, ApplyCheck applyCheck, Task task,
			User serviceUser, CreditChange creditChange, OrderPrice orderPrice) {
		int updateOrderTableNum = 0;
		int updateApplyCheckNum = 0;
		int updateTaskNum = 0;
		int updateServiceUserNum = 0;
		int insertCreditChangeNum = 0;
		int updateCustomerUserNum = 0;
		int insertCustomerIncomeMoneyNum = 0;
		int inasertBackMoneyNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
						
			// 更新验收表(状态)
			updateApplyCheckNum = applyCheckDao.updateByApplyCheck(session, applyCheck);
			
			// 更新任务表(状态)
			updateTaskNum = taskDao.updateByTask(session, task);
			
			// 更新用户表(维修人员信用值-10)
			updateServiceUserNum = userDao.updateByUser(session, serviceUser);
			
			// 添加信用值变化表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange);
						
			// 删除维修单对应维修单价表对应数据
			orderPriceDao.deleteByOrderPrice(session, orderPrice);
						
			/*// 更新用户表(客户余额)
			updateCustomerUserNum = userDao.updateByUser(session, customerUser);
			
			// 添加用户_客户_收入_金额表
			insertCustomerIncomeMoneyNum = customerIncomeMoneyDao.insertByUserCustomerIncomeMoney(session, customerIncomeMoney);
			
			// 添加平台_收益金额表
			inasertBackMoneyNum = backMoneyDao.insertByBackMoney(session, backMoney);*/
			
			if (updateOrderTableNum == 0 || updateApplyCheckNum == 0 || updateTaskNum == 0 || updateServiceUserNum == 0 || 
					insertCreditChangeNum == 0 ) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCheckService--updateApplyCheckUnqualifiedAndPicketing--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}

	/**
	 * 验收不合格转平台客服(抢单模式)
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、更新维修单表(状态)
	 * 3、更新验收表(状态)
	 * 4、更新任务表(状态)
	 * 
	 * @param orderTable
	 * @param applyCheck
	 * @param task
	 * @return
	 * 
	 * @author ZY on 2018/09/17
	 */
	public int updateApplyCheckUnqualifiedAndChangeService(OrderTable orderTable, ApplyCheck applyCheck, Task task) {
		int updateOrderTableNum = 0;
		int updateApplyCheckNum = 0;
		int updateTaskNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
						
			// 更新验收表(状态)
			updateApplyCheckNum = applyCheckDao.updateByApplyCheck(session, applyCheck);
			
			// 更新任务表(状态)
			updateTaskNum = taskDao.updateByTask(session, task);
			
			if (updateOrderTableNum == 0 || updateApplyCheckNum == 0 || updateTaskNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCheckService--updateApplyCheckUnqualifiedAndChangeService--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}

	/**
	 * 验收不合格 最新的更改订单验收状态和不合格原因  (2019-01-20文档)
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、更新维修单表(状态)
	 * 3、更新验收表(状态)
	 * 4、更新任务表(状态)
	 *
	 * 传递OrderTable实体
	 *
	 * 需在OrderTable实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param orderTable
	 * @param applyCheck
	 * @return
	 *
	 * @author ZY on 2018/09/17
	 */
	public int updateApplyCheckUnqualifiedNew(OrderTable orderTable, ApplyCheck applyCheck) {
		int updateOrderTableNum = 0;
		int updateApplyCheckNum = 0;
		int updateTaskNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

			// 更新验收表(状态)
			updateApplyCheckNum = applyCheckDao.updateByApplyCheck(session, applyCheck);


			if (updateOrderTableNum == 0 || updateApplyCheckNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCheckService--updateApplyCheckUnqualifiedNew--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * 根据ApplyCheck实体更新
	 *
	 * 如果修改的验收状态是平台协调，并且验收最终结算总价不为空，则需要做以下操作：
	 * 1、根据ApplyCheck实体中的维修单ID，获取对应的维修单数据
	 * 2、根据ApplyCheck实体中的验收ID，获取对应的验收数据
	 * 3、更新验收表
	 *
	 * 金额数据：
	 * (1).从平台中协商的金额支出金额给维修人员
	 * (2).从平台中协商的金额支出金额给客户
	 * (3).平台客服产生的费用由过错方承担
	 *
	 * 4、更新用户表(维修人员余额)
	 * 5、添加用户_维修工、安装队_收入_金额表
	 * 6、添加平台_收益金额表
	 * 7、更新用户表(客户余额)
	 * 8、添加用户_客户_收入_金额表
	 * 9、添加平台_收益金额表
	 * 
	 * @param applyCheck
	 * @return
	 * 
	 * @author ZY on 2018/09/17
	 */
	public int updateApplyCheckUnqualifiedAndToService(ApplyCheck applyCheck,
			User serviceUser, UserServiceIncomeMoney serviceIncomeMoney, BackMoney serviceBackMoney, 
			User customerUser, UserCustomerIncomeMoney customerIncomeMoney, BackMoney customerBackMoney) {
		int updateApplyCheckNum = 0;
		int updateServiceUserNum = 0;
		int insertServiceIncomeMoneyNum = 0;
		int insertServiceBackMoneyNum = 0;
		int updateCustomerUserNum = 0;
		int insertCustomerIncomeMoneyNum = 0;
		int insertCustomerBackMoneyNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			 
			// 更新验收表
			updateApplyCheckNum = applyCheckDao.updateByApplyCheck(session, applyCheck);
			
			// 更新用户表(维修人员余额)
			updateServiceUserNum = userDao.updateByUser(session, serviceUser);
			
			// 添加用户_维修工、安装队_收入_金额表
			insertServiceIncomeMoneyNum = serviceIncomeMoneyDao.insertByUserServiceIncomeMoney(session, serviceIncomeMoney);
			
			// 添加平台_收益金额表
			insertServiceBackMoneyNum = backMoneyDao.insertByBackMoney(session, serviceBackMoney);
			
			// 更新用户表(客户余额)
			updateCustomerUserNum = userDao.updateByUser(session, customerUser);
			
			// 添加用户_客户_收入_金额表
			insertCustomerIncomeMoneyNum = customerIncomeMoneyDao.insertByUserCustomerIncomeMoney(session, customerIncomeMoney);
			
			// 添加平台_收益金额表
			insertCustomerBackMoneyNum = backMoneyDao.insertByBackMoney(session, customerBackMoney);
			
			if (updateApplyCheckNum == 0 || updateServiceUserNum == 0 || insertServiceIncomeMoneyNum == 0 || 
					insertServiceBackMoneyNum == 0 || updateCustomerUserNum == 0 ||
							insertCustomerIncomeMoneyNum == 0 || insertCustomerBackMoneyNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCheckService--updateApplyCheckUnqualifiedAndToService--error:" + e.getMessage());
		}

		return updateApplyCheckNum;
	}

}
