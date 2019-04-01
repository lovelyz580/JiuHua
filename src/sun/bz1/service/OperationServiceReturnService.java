package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.BackMoneyDao;
import sun.bz1.dao.CreditChangeDao;
import sun.bz1.dao.OrderPriceDao;
import sun.bz1.dao.OrderTableDao;
import sun.bz1.dao.ReturnTableDao;
import sun.bz1.dao.TaskDao;
import sun.bz1.dao.UserCustomerIncomeMoneyDao;
import sun.bz1.dao.UserDao;
import sun.bz1.entity.BackMoney;
import sun.bz1.entity.CreditChange;
import sun.bz1.entity.OrderPrice;
import sun.bz1.entity.OrderTable;
import sun.bz1.entity.ReturnTable;
import sun.bz1.entity.Task;
import sun.bz1.entity.User;
import sun.bz1.entity.UserCustomerIncomeMoney;
import util.MyBatisUtil;

/**
 * 维修单维修工、安装队退回操作
 * 
 * Service
 * 
 * @author WJF on 2018/09/17
 */

@Service
public class OperationServiceReturnService {
	
	@Autowired
	private OrderTableDao orderTableDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private ReturnTableDao returnTableDao;
	
	@Autowired
	private OrderPriceDao orderPriceDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CreditChangeDao creditChangeDao;
	
	@Autowired
	private UserCustomerIncomeMoneyDao customerIncomeMoneyDao;
	
	@Autowired
	private BackMoneyDao backMoneyDao;
	
	private Logger logger = Logger.getLogger(OperationCustomerCancelService.class);

	/**
	 * 维修单维修工、安装队退回，后台重新派单
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、更新维修单表(维修人员、状态)
	 * 3、更新任务表(状态)
	 * 4、添加退回表
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
	 * 11、后台重新派单
	 * 
	 * @param orderTable
	 * @param task
	 * @param returnTable
	 * @param serviceUser
	 * @param creditChange
	 * @param orderPrice
	 * @param userCustomer
	 * @param customerIncomeMoney
	 * @param backMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/18
	 */
	public int returnOrder(OrderTable orderTable, Task task, ReturnTable returnTable, 
			User serviceUser, CreditChange creditChange, OrderPrice orderPrice, User userCustomer, 
			UserCustomerIncomeMoney customerIncomeMoney, BackMoney backMoney) {
		int updateOrderTableNum = 0;
		int updateTaskNum = 0;
		int insertReturnTableNum = 0;
		int updateUserServiceNum = 0;
		int insertCreditChangeNum = 0;
		int updateUserCustomerNum = 0;
		int insertCustomerIncomeMoney = 0;
		int insertBackMoney = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(维修人员、状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			// 更新任务表(状态)
			updateTaskNum = taskDao.updateByTask(session, task);
			
			// 添加退回表
			insertReturnTableNum = returnTableDao.insertByReturnTable(session, returnTable);
			
			// 更新用户表(维修人员信用值-10)
			updateUserServiceNum = userDao.updateByUser(session, serviceUser);
			
			// 添加信用值变化表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange);
			
			// 删除维修单对应维修单价表对应数据
			orderPriceDao.deleteByOrderPrice(session, orderPrice);
						
			// 更新用户表(客户余额)
			updateUserCustomerNum = userDao.updateByUser(session, userCustomer);
			
			// 添加用户_客户_收入_金额表
			insertCustomerIncomeMoney = customerIncomeMoneyDao.insertByUserCustomerIncomeMoney(session, customerIncomeMoney);
			
			// 添加平台_收益金额表
			insertBackMoney = backMoneyDao.insertByBackMoney(session, backMoney);
			
			if (updateOrderTableNum == 0 || updateTaskNum == 0 || insertReturnTableNum == 0 ||
					updateUserServiceNum == 0 || insertCreditChangeNum == 0 || updateUserCustomerNum == 0 ||
					insertCustomerIncomeMoney == 0 || insertBackMoney == 0) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationServiceReturnService--returnOrder--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
}
