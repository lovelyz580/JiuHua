package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.AnnouncementDao;
import sun.bz1.dao.BackMoneyDao;
import sun.bz1.dao.CancelTableDao;
import sun.bz1.dao.CreditChangeDao;
import sun.bz1.dao.OrderTableDao;
import sun.bz1.dao.TaskDao;
import sun.bz1.dao.UserCustomerIncomeMoneyDao;
import sun.bz1.dao.UserDao;
import sun.bz1.dao.UserServiceIncomeMoneyDao;
import sun.bz1.entity.Announcement;
import sun.bz1.entity.BackMoney;
import sun.bz1.entity.CancelTable;
import sun.bz1.entity.CreditChange;
import sun.bz1.entity.OrderTable;
import sun.bz1.entity.Task;
import sun.bz1.entity.User;
import sun.bz1.entity.UserCustomerIncomeMoney;
import sun.bz1.entity.UserServiceIncomeMoney;
import util.MyBatisUtil;

/**
 * 维修单客户取消操作
 * 
 * 维修单维修工、安装队确认取消操作
 * 
 * Service
 * 
 * @author WJF on 2018/09/17
 */

@Service
public class OperationCustomerCancelService {
	
	@Autowired
	private OrderTableDao orderTableDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private CancelTableDao cancelTableDao;
	
	@Autowired
	private AnnouncementDao announcementDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CreditChangeDao creditChangeDao;
	
	@Autowired
	private UserCustomerIncomeMoneyDao customerIncomeMoneyDao;
	
	@Autowired
	private UserServiceIncomeMoneyDao serviceIncomeMoneyDao;
	
	@Autowired
	private BackMoneyDao backMoneyDao;
	
	private Logger logger = Logger.getLogger(OperationCustomerCancelService.class);

	/**
	 * 客户取消
	 * 
	 * 操作过程：
	 * 
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 
	 * 2、判断是否可以取消维修单
	 * 派单模式下，已申请验收、已验收合格、已验收不合格、已客户取消、已确认取消、已退回、已关闭状态的维修单不允许取消
	 * 
	 * 3、对数据表的操作
	 * 
	 * (1).派单模式
	 * (1.1).更新维修单表(状态)
	 * (1.2).更新任务表(状态)
	 * (1.3).添加客户取消维修单表
	 * OperationCustomerCancelService中的customerCancel1()方法
	 * 
	 * (2).抢单模式
	 * (2.1).发布状态的维修单可以取消，并且不影响客户的信用值
	 * (2.1.1).更新维修单表(状态)
	 * (2.1.2).更新公告表(状态)
	 * (2.1.3).添加客户取消维修单表
	 * OperationCustomerCancelService中的customerCancel2()方法
	 * 
	 * (2.2).抢单状态的维修单可以取消，但需要扣减客户信用值10分
	 * (2.2.1).更新维修单表(状态)
	 * (2.2.2).更新公告表(状态)
	 * (2.2.3).添加客户取消维修单表
	 * (2.2.4).更新用户表(客户信用值)
	 * (2.2.5).添加信用值变化表
	 * OperationCustomerCancelService中的customerCancel3()方法
	 * 
	 * (2.3).接收状态的维修单，同派单模式的操作
	 * (2.3.1).更新维修单表(状态)
	 * (2.3.2).更新任务表(状态)
	 * (2.3.3).添加客户取消维修单表
	 * OperationCustomerCancelService中的customerCancel1()方法
	 * 
	 * @param orderTable
	 * @param task
	 * @param cancelTable
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int customerCancel1(OrderTable orderTable, Task task, CancelTable cancelTable) {
		int updateOrderTableNum = 0;
		int updateTaskNum = 0;
		int insertCancelTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			// 更新任务表(状态)
			updateTaskNum = taskDao.updateByTask(session, task);
			
			// 添加客户取消表
			insertCancelTableNum = cancelTableDao.insertByCancelTable(session, cancelTable);
			
			if (updateOrderTableNum == 0 || updateTaskNum == 0 || insertCancelTableNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCustomerCancelService--customerCancel1--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	public int customerCancel2(OrderTable orderTable, Announcement announcement, CancelTable cancelTable) {
		int updateOrderTableNum = 0;
		int updateAnnouncementNum = 0;
		int insertCancelTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			// 更新公告表(状态)
			updateAnnouncementNum = announcementDao.updateByAnnouncement(session, announcement);
			
			// 添加客户取消表
			insertCancelTableNum = cancelTableDao.insertByCancelTable(session, cancelTable);
			
			if (updateOrderTableNum == 0 || updateAnnouncementNum == 0 || insertCancelTableNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCustomerCancelService--customerCancel2--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	public int customerCancel3(OrderTable orderTable, Announcement announcement, 
			CancelTable cancelTable, User user, CreditChange creditChange) {
		int updateOrderTableNum = 0;
		int updateAnnouncementNum = 0;
		int insertCancelTableNum = 0;
		int updateUserNum = 0;
		int insertCreditChangeNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			// 更新公告表(状态)
			updateAnnouncementNum = announcementDao.updateByAnnouncement(session, announcement);
			
			// 添加客户取消表
			insertCancelTableNum = cancelTableDao.insertByCancelTable(session, cancelTable);
			
			// 更新用户表(客户信用值)
			updateUserNum = userDao.updateByUser(session, user);
			
			// 添加信用值变化表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange);
			
			if (updateOrderTableNum == 0 || updateAnnouncementNum == 0 || insertCancelTableNum == 0 ||
					updateUserNum == 0 || insertCreditChangeNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCustomerCancelService--customerCancel3--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * 维修工、安装队确认取消
	 * 
	 * 操作过程：
	 * 1、根据CancelTable实体中的维修单ID，获取对应的维修单数据
	 * 2、更新维修单表(状态)
	 * 3、更新任务表(状态)
	 * 4、更新取消表
	 * 
	 * 金额数据：
	 * (1).从平台中退还客户扣除维修人员已发生的费用以及违约金后的金额
	 * (2).从平台中支出维修人员已发生的金额
	 * 
	 * 5、更新用户表(客户信用值-10、客户余额)
	 * 6、添加信用值变化表
	 * 7、添加用户_客户_收入_金额表
	 * 8、添加平台_收益金额表
	 * 9、更新用户表(维修人员余额)
	 * 10、添加用户_维修工、安装队_收入_金额表
	 * 11、添加平台_收益金额表
	 * 
	 * @param orderTable
	 * @param task
	 * @param cancelTable
	 * @param customerUser
	 * @param creditChange
	 * @param customerIncomeMoney
	 * @param customerBackMoney
	 * @param serviceUser
	 * @param serviceIncomeMoney
	 * @param serviceBackMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int confirmCancel(OrderTable orderTable, Task task, CancelTable cancelTable, 
			User customerUser, CreditChange creditChange, UserCustomerIncomeMoney customerIncomeMoney, BackMoney customerBackMoney,
			User serviceUser, UserServiceIncomeMoney serviceIncomeMoney, BackMoney serviceBackMoney) {
		int updateOrderTableNum = 0;
		int updateTaskNum = 0;
		int updateCancelTableNum = 0;
		int updateCustomerUserNum = 0;
		int insertCreditChangeNum = 0;
		
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			// 更新任务表(状态)
			updateTaskNum = taskDao.updateByTask(session, task);
			
			// 更新客户取消表
			updateCancelTableNum = cancelTableDao.updateByCancelTable(session, cancelTable);
			
			// 更新用户表(客户信用值-10、客户余额)
			updateCustomerUserNum = userDao.updateByUser(session, customerUser);
			
			// 添加信用值变化表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange);
			
			if (updateOrderTableNum == 0 || updateTaskNum == 0 || updateCancelTableNum == 0 ||
					updateCustomerUserNum == 0 || insertCreditChangeNum == 0) {
				return 0;
			}
			
			// 添加用户_客户_收入_金额表
			customerIncomeMoneyDao.insertByUserCustomerIncomeMoney(session, customerIncomeMoney);
			
			// 添加平台_收益金额表
			backMoneyDao.insertByBackMoney(session, customerBackMoney);
			
			// 更新用户表(维修人员余额)
			userDao.updateByUser(session, serviceUser);
						
			// 添加用户_维修工、安装队_收入_金额表
			serviceIncomeMoneyDao.insertByUserServiceIncomeMoney(session, serviceIncomeMoney);
						
			// 添加平台_收益金额表
			backMoneyDao.insertByBackMoney(session, serviceBackMoney);
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationCustomerCancelService--confirmCancel--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
}
