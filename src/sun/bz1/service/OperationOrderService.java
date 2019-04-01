package sun.bz1.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.*;
import sun.bz1.entity.*;
import util.MyBatisUtil;

/**
 * 维修单操作
 * 
 * 维修单保存、平台派单、发布维修信息、维修工、安装队抢单、客户确认抢单
 * 
 * Service
 * 
 * @author WJF on 2018/09/12
 */

@Service
public class OperationOrderService {
	
	@Autowired
	private OrderTableDao orderTableDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private OrderPriceDao orderPriceDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserCustomerExpenditureMoneyDao customerExpenditureMoneyDao;
	
	@Autowired
	private BackMoneyDao backMoneyDao;
	
	@Autowired
	private AnnouncementDao announcementDao;
	
	@Autowired
	private SnatchDao snatchDao;

    @Autowired
    private DistributeConfirmDao distributeConfirmDao;

	@Autowired
	private OrderDetailConfirmDao orderDetailConfirmDao;

	@Autowired
	private OrderDetailRecordDao orderDetailRecordDao;

	private Logger logger = Logger.getLogger(OperationOrderService.class);
	
	/**
	 * 维修单保存
	 * 
	 * 操作过程：
	 * 1、添加维修单表
	 * 2、添加维修单详情表
	 * 
	 * @param orderTable
	 * @param orderDetailList
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	public int saveOrder(OrderTable orderTable, List<OrderDetail> orderDetailList) {
		int insertOrderTableNum = 0;
		int insertOrderDetailNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 添加维修单表
			insertOrderTableNum = orderTableDao.insertByOrderTable(session, orderTable);
			
			// 添加维修单详情表
			for (int i = 0; i < orderDetailList.size(); i++) {
				insertOrderDetailNum = insertOrderDetailNum + 
						orderDetailDao.insertByOrderDetail(session, orderDetailList.get(i));
			}
			
			if (insertOrderTableNum == 0 || insertOrderDetailNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--saveOrder--error:" + e.getMessage());
		}

		return insertOrderTableNum;
	}

	/**
	 * 维修单保存
	 *
	 * 操作过程：
	 * 1、添加维修单表
	 * 2、添加维修单详情表
	 *
	 * @param orderTable
	 * @param orderDetailList
	 * @return
	 *
	 * @author ZY on 2018/11/08
	 */
	public int saveAndUpdateOrder(OrderTable orderTable, List<OrderDetail> orderDetailList) {
		int updateOrderTableNum = 0;
		int insertOrderDetailNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 编辑维修单表
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

			// 添加维修单详情表
			for (int i = 0; i < orderDetailList.size(); i++) {
				insertOrderDetailNum = insertOrderDetailNum +
						orderDetailDao.insertByOrderDetail(session, orderDetailList.get(i));
			}

			if (updateOrderTableNum == 0 || insertOrderDetailNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--saveAndUpdateOrder--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * 平台派单
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 * 5、添加维修单对应维修单价表
	 * 
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费总价 收入金额)
	 * 
	 * 6、更新用户表(客户余额)
	 * 7、添加用户_客户_支出_金额表
	 * 8、添加平台_收益金额表
	 * 
	 * @param orderTable
	 * @param task
	 * @param orderPriceList
	 * @param
	 * @param
	 * @param
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	public int backDistribution(OrderTable orderTable, Task task, List<OrderPrice> orderPriceList) {
		int insertTaskNum = 0;
		int updateOrderTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(类型、状态、维修人员、维修金额)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			// 添加维修任务表
			insertTaskNum = taskDao.insertByTask(session, task);
			
			// 添加维修单对应维修单价表
			for (int i = 0; i < orderPriceList.size(); i++) {
				orderPriceDao.insert(session, orderPriceList.get(i));		
			}
			
			if (updateOrderTableNum == 0 || insertTaskNum == 0 ) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--backDistribution--error:" + e.getMessage());
		}

		return insertTaskNum;
	}


	/**
	 * 平台派单(添加上派单确认表中)
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 * 5、添加维修单对应维修单价表
	 *
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费总价 收入金额)
	 *
	 * 6、更新用户表(客户余额)
	 * 7、添加用户_客户_支出_金额表
	 * 8、添加平台_收益金额表
	 *
	 * @param orderTable
	 * @param task
	 * @param orderPriceList
	 * @param
	 * @param
	 * @param
	 * @return
	 *
	 * @author WJF on 2018/09/12
	 */
	public int backDistributionAndConfirm(OrderTable orderTable, Task task, List<OrderPrice> orderPriceList, DistributeConfirm distributeConfirm) {
		int insertTaskNum = 0;
		int updateOrderTableNum = 0;
		int insertDistributeConfirmNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			//添加派单确认表
			insertDistributeConfirmNum = distributeConfirmDao.insertByDistributeConfirm(session, distributeConfirm);

			// 更新维修单表(类型、状态、维修人员、维修金额)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

			// 添加维修任务表
			insertTaskNum = taskDao.insertByTask(session, task);

			// 添加维修单对应维修单价表
			for (int i = 0; i < orderPriceList.size(); i++) {
				orderPriceDao.insert(session, orderPriceList.get(i));
			}

			if (updateOrderTableNum == 0 || insertTaskNum == 0 || insertDistributeConfirmNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--backDistributionAndConfirm--error:" + e.getMessage());
		}

		return insertTaskNum;
	}

	/**
	 * 平台派单(添加派单确认表)
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 * 5、添加维修单对应维修单价表
	 *
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费总价 收入金额)
	 *
	 * 6、更新用户表(客户余额)
	 * 7、添加用户_客户_支出_金额表
	 * 8、添加平台_收益金额表
	 *
	 * @param orderTable
	 * @param orderPriceList
	 * @param
	 * @param
	 * @param
	 * @return
	 *
	 * @author ZY on 2018/11/09
	 */
	public int updateBackDistributionAndInsertConfirm(OrderTable orderTable, OrderPrice deleteOrderPrice, List<OrderPrice> orderPriceList, DistributeConfirm distributeConfirm) {
		int updateOrderTableNum = 0;
		int updateDistributeConfirmNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			//更新派单确认表
			updateDistributeConfirmNum = distributeConfirmDao.insertByDistributeConfirm(session, distributeConfirm);

			// 更新维修单表(类型、状态、维修人员、维修金额)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

			// 删除维修单对应维修单价表对应数据
			orderPriceDao.deleteByOrderPrice(session, deleteOrderPrice);

			// 添加维修单对应维修单价表
			for (int i = 0; i < orderPriceList.size(); i++) {
				orderPriceDao.insert(session, orderPriceList.get(i));
			}

			if (updateOrderTableNum == 0 || updateDistributeConfirmNum ==0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--updateBackDistributionAndInsertConfirm--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}

	/**
	 * 平台派单（更新派单确认表）
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 * 5、添加维修单对应维修单价表
	 *
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费总价 收入金额)
	 *
	 * 6、更新用户表(客户余额)
	 * 7、添加用户_客户_支出_金额表
	 * 8、添加平台_收益金额表
	 *
	 * @param orderTable
	 * @param orderPriceList
	 * @param
	 * @param
	 * @param
	 * @return
	 *
	 * @author ZY on 2018/11/09
	 */
	public int updateBackDistribution(OrderTable orderTable, OrderPrice deleteOrderPrice, List<OrderPrice> orderPriceList, DistributeConfirm distributeConfirm) {
		int updateOrderTableNum = 0;
		int updateDistributeConfirmNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			//更新派单确认表
			updateDistributeConfirmNum = distributeConfirmDao.updateByDistributeConfirm(session, distributeConfirm);

			// 更新维修单表(类型、状态、维修人员、维修金额)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

			// 删除维修单对应维修单价表对应数据
			orderPriceDao.deleteByOrderPrice(session, deleteOrderPrice);

			// 添加维修单对应维修单价表
			for (int i = 0; i < orderPriceList.size(); i++) {
				orderPriceDao.insert(session, orderPriceList.get(i));
			}

			if (updateOrderTableNum == 0 || updateDistributeConfirmNum ==0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--updateBackDistribution--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}

	/**
	 * 平台派单(中途变更派单人)
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 * 5、添加维修单对应维修单价表
	 *
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费总价 收入金额)
	 *
	 * 6、更新用户表(客户余额)
	 * 7、添加用户_客户_支出_金额表
	 * 8、添加平台_收益金额表
	 *
	 * @param finishOrderTable
	 * @param orderTableHalfway
	 * @param orderPriceList
	 * @param
	 * @param
	 * @param
	 * @return
	 *
	 * @author ZY on 2018/11/09
	 */
	public int updateBackDistributionHalfway(OrderTable finishOrderTable, OrderTable orderTableHalfway, List<OrderPrice> orderPriceList, List<OrderDetail> orderDetailListLeave) {
		int updateOrderTableNum = 0;
		int insertOrderTableNum = 0;
		int insertOrderDetailNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(类型、状态、维修人员、维修金额)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, finishOrderTable);

			// 针对剩余数据添加新的订单
			insertOrderTableNum = orderTableDao.insertByOrderTable(session, orderTableHalfway);

			// 添加维修单对应维修单价表
			for (int i = 0; i < orderPriceList.size(); i++) {
				orderPriceDao.insert(session, orderPriceList.get(i));
			}

			// 添加维修单详情表
			for (int i = 0; i < orderDetailListLeave.size(); i++) {
				insertOrderDetailNum = insertOrderDetailNum +
						orderDetailDao.insertByOrderDetail(session, orderDetailListLeave.get(i));
			}

			if (updateOrderTableNum == 0 || insertOrderTableNum == 0 || insertOrderDetailNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--updateBackDistributionHalfway--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * 发布维修信息
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、添加维修公告表
	 * 3、更新维修单表(类型、状态)
	 * 
	 * @param announcement
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	public int release(Announcement announcement, OrderTable orderTable) {
		int insertAnnouncementNum = 0;
		int updateOrderTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 添加维修公告表
			insertAnnouncementNum = announcementDao.insertByAnnouncement(session, announcement);
			
			// 更新维修单(类型、状态)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			if (insertAnnouncementNum == 0 || updateOrderTableNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--release--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * 维修工、安装队抢单
	 * 
	 *操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、判断抢单是否已结束(维修竞标结束时间)
	 * 3、判断抢单的人员的身份类别是否符合维修单的维修项目类型
	 * 4、判断抢单的人员的信用值是否大于最低信用值
	 * 5、计算维修差旅费总价(差旅费(每公里)*距离)
	 * 6、计算维修总价
	 * 7、添加抢单表
	 * 8、更新维修单表(状态)
	 * 
	 * @param snatch
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	public int snatch(Snatch snatch, OrderTable orderTable) {
		int insertSnatchNum = 0;
		int updateOrderTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 添加抢单表
			insertSnatchNum = snatchDao.insertBySnatch(session, snatch);
			
			// 更新维修单(类型)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			if (insertSnatchNum == 0 || updateOrderTableNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--snatch--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * 客户确认抢单
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、根据OrderTable实体中的维修人员ID(YHBG+UUID)，获取对应的维修人员数据
	 * 3、更新抢单表
	 * 4、更新公告表(状态)
	 * 5、更新维修单(状态、维修人员、维修总价、维修差旅费总价)
	 * 6、添加维修任务表
	 * 7、添加维修单对应维修单价表
	 * 
	 * @param snatch
	 * @param announcement
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	public int confirmSnatch(Snatch snatch, Announcement announcement, OrderTable orderTable) {
		int updateSnatchNum = 0;
		int updateAnnouncement = 0;
		int updateOrderTableNum = 0;
		//int insertTaskNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新抢单表
			updateSnatchNum = snatchDao.updateBySnatch(session, snatch);
			
			// 更新公告表(状态)
			updateAnnouncement = announcementDao.updateByAnnouncement(session, announcement);
			
			// 更新维修单(状态、维修人员、维修总价)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			/*// 添加维修任务表
			insertTaskNum = taskDao.insertByTask(session, task);*/
			
			// 添加维修单对应维修单价表
		/*
			for (int i = 0; i < orderPriceList.size(); i++) {
				orderPriceDao.insert(session, orderPriceList.get(i));		
			}
		*/
			
			if (updateSnatchNum == 0 || updateAnnouncement == 0 || 
					updateOrderTableNum == 0 /*|| insertTaskNum == 0*/) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--confirmSnatch--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}

	/**
	 * 客户确认抢单
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、查询之前中标人的中标信息
	 * 3、竞标表更新之前中标人状态为N
	 * 4、根据OrderTable实体中的维修人员ID(YHBG+UUID)，获取对应的维修人员数据
	 * 5、更新抢单表
	 * 6、更新公告表(状态)
	 * 7、更新维修单(状态、维修人员、维修总价、维修差旅费总价)
	 *
	 * @param snatch
	 * @param beforeSnatch
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/11/09
	 */
	public int updateConfirmSnatch(Snatch beforeSnatch, Snatch snatch,  OrderTable orderTable, Task task) {
		int updateBeforeSnatchNum = 0;
		int updateSnatchNum = 0;
		int updateOrderTableNum = 0;
		int insertTaskNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			//更新之前的中标人的竞标表
			updateBeforeSnatchNum = snatchDao.updateBySnatch(session, beforeSnatch);

			// 更新抢单表
			updateSnatchNum = snatchDao.updateBySnatch(session, snatch);

			// 更新维修单(状态、维修人员、维修总价)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

			// 添加维修任务表
			insertTaskNum = taskDao.insertByTask(session, task);


			// 添加维修单对应维修单价表
		/*
			for (int i = 0; i < orderPriceList.size(); i++) {
				orderPriceDao.insert(session, orderPriceList.get(i));
			}
		*/

			if (updateBeforeSnatchNum == 0 || updateSnatchNum == 0 || updateOrderTableNum == 0 || insertTaskNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--updateConfirmSnatch--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}

	/**
	 * (维修)竞标的时候填写日工资*人数*天数+材料费+差旅费
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、判断抢单是否已结束(维修竞标结束时间)
	 * 3、判断抢单的人员的身份类别是否符合维修单的维修项目类型
	 * 4、判断抢单的人员的信用值是否大于最低信用值
	 * 5、计算维修差旅费总价(差旅费(每公里)*距离)
	 * 6、计算维修总价
	 * 7、添加抢单表
	 * 8、更新维修单表(状态)
	 *
	 * @param snatch
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/12/15
	 */
	public int snatchUpdatePriceWX(Snatch snatch, OrderTable orderTable) {
		int insertSnatchNum = 0;
		int updateOrderTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 添加抢单表
			insertSnatchNum = snatchDao.insertBySnatch(session, snatch);

			// 更新维修单(类型)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);


			if (insertSnatchNum == 0 || updateOrderTableNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--snatchUpdatePriceWX--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * (安装队)竞标的时候可以更改自己针对本次的订单的单价
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、判断抢单是否已结束(维修竞标结束时间)
	 * 3、判断抢单的人员的身份类别是否符合维修单的维修项目类型
	 * 4、判断抢单的人员的信用值是否大于最低信用值
	 * 5、计算维修差旅费总价(差旅费(每公里)*距离)
	 * 6、计算维修总价
	 * 7、添加抢单表
	 * 8、更新维修单表(状态)
	 * 
	 * @param snatch
	 * @param orderTable
	 * @param orderPriceList
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	public int snatchUpdatePrice(Snatch snatch, OrderTable orderTable, List<OrderPrice> orderPriceList) {
		int insertSnatchNum = 0;
		int updateOrderTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 添加抢单表
			insertSnatchNum = snatchDao.insertBySnatch(session, snatch);
			
			// 更新维修单(类型)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			
			// 添加维修单对应维修单价表
			for (int i = 0; i < orderPriceList.size(); i++) {
				orderPriceDao.insertByOrderPrice(session, orderPriceList.get(i));		
			}
						
			if (insertSnatchNum == 0 || updateOrderTableNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--snatchUpdatePrice--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}
	
	/**
	 * 维修单保存
	 * 
	 * 操作过程：
	 * 1、添加维修单表
	 * 2、添加维修单详情表
	 * 
	 * @param orderTable
	 * @param orderDetailList
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	public int backDistributionAndUpdate(OrderTable orderTable, List<OrderDetail> orderDetailList, List<OrderPrice> orderPriceList) {
		int insertOrderTableNum = 0;
		int insertOrderDetailNum = 0;
		int insertOrderPriceNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 添加维修单表
			insertOrderTableNum = orderTableDao.insertByOrderTable(session, orderTable);
			
			// 添加维修单详情表
			for (int i = 0; i < orderDetailList.size(); i++) {
				insertOrderDetailNum = insertOrderDetailNum + 
						orderDetailDao.insertByOrderDetail(session, orderDetailList.get(i));
			}
			
			// 添加维修单对应维修单价表
			for (int i = 0; i < orderPriceList.size(); i++) {
				orderPriceDao.insert(session, orderPriceList.get(i));		
			}
			
			if (insertOrderTableNum == 0 || insertOrderDetailNum == 0 || insertOrderPriceNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--saveOrder--error:" + e.getMessage());
		}

		return insertOrderTableNum;
	}


    /**
     * 添加派单确认表
     *
     * 1、添加派单确认表
     * 2、更新维修单表(状态)

     * @param distributeConfirm
     * @param orderTable
     * @param
     * @param
     * @param
     * @return
     *
     * @author ZY on 2018/11/22
     */
    public int insertDistributeAndUpdateOrder(DistributeConfirm distributeConfirm, OrderTable orderTable, Task task) {
        int insertDistributeConfirmNum = 0;
        int updateOrderTableNum = 0;
        int inertTaskNum = 0;

        try {
            SqlSession session = MyBatisUtil.getInstance().getSqlSession();

            //添加派单确认表
            insertDistributeConfirmNum = distributeConfirmDao.insertByDistributeConfirm(session, distributeConfirm);

            // 更新维修单表(类型、状态、维修人员、维修金额)
            updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

            //添加任务表
            inertTaskNum = taskDao.insertByTask(session, task);


            if (updateOrderTableNum == 0 || insertDistributeConfirmNum == 0 || inertTaskNum == 0) {
                return 0;
            }

            session.commit();
            session.close();
        } catch (Exception e) {
            logger.error("OperationOrderService--insertDistributeAndUpdateOrder--error:" + e.getMessage());
        }

        return insertDistributeConfirmNum;
    }

    /**
     *  客户确认派单人后
     *
     * 1、更新派单确认表状态
     *2、更新订单表信息
     * 3、添加维修任务表
     * 4、添加订单价格详情表数据
     *
     * @param orderTable
     * @param orderPriceList
     * @param
     * @param
     * @param
     * @return
     *
     * @author ZY on 2018/11/22
     */
    public int updateDistributeBackDistribution(DistributeConfirm distributeConfirm, OrderTable orderTable,  List<OrderPrice> orderPriceList) {
        int updateDistributeConfirmNum = 0;
        int insertTaskNum = 0;
        int updateOrderTableNum = 0;

        try {
            SqlSession session = MyBatisUtil.getInstance().getSqlSession();

            //更新派单确认表
            updateDistributeConfirmNum = distributeConfirmDao.updateByDistributeConfirm(session, distributeConfirm);

            // 更新维修单表(类型、状态、维修人员、维修金额)
            updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

           /* // 添加维修任务表
            insertTaskNum = taskDao.insertByTask(session, task);*/

            // 添加维修单对应维修单价表
            for (int i = 0; i < orderPriceList.size(); i++) {
                orderPriceDao.insert(session, orderPriceList.get(i));
            }

            if (updateOrderTableNum == 0 || updateDistributeConfirmNum == 0 ) {
                return 0;
            }

            session.commit();
            session.close();
        } catch (Exception e) {
            logger.error("OperationOrderService--updateDistributeBackDistribution--error:" + e.getMessage());
        }

        return updateDistributeConfirmNum;
    }

	/**
	 *  客户或者维修、安装确认派单时取消接单
	 *
	 * 1、更新派单确认表状态
	 *2、更新订单表信息
	 * 3、添加维修任务表
	 * 4、添加订单价格详情表数据
	 *
	 * @param orderTable
	 * @param
	 * @param
	 * @param
	 * @return
	 *
	 * @author ZY on 2018/11/22
	 */
	public int updateByDistributeConfirmCancel(DistributeConfirm distributeConfirm, OrderTable orderTable, Task task) {
		int updateDistributeConfirmNum = 0;
		int deleteTaskNum = 0;
		int updateOrderTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			//更新派单确认表
			updateDistributeConfirmNum = distributeConfirmDao.updateByDistributeConfirm(session, distributeConfirm);

			// 更新维修单表(类型、状态、维修人员、维修金额)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

            // 删除维修任务表
			deleteTaskNum = taskDao.deleteByTask(session, task);

			if (updateOrderTableNum == 0 || updateDistributeConfirmNum == 0|| deleteTaskNum ==0 ) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--updateDistributeBackDistribution--error:" + e.getMessage());
		}

		return updateDistributeConfirmNum;
	}

	/**
	 * 维修单详情确认表保存
	 *
	 * 操作过程：
	 * 1、添加维修单详情表
	 *
	 * @param orderTable
	 * * @param deleteOrderDetailConfirmList
	 * * @param orderDetailConfirmList
	 * @return
	 *
	 * @author ZY on 2018/11/26
	 */
	public int saveOrderDetailConfirm(OrderTable orderTable,List<OrderDetailConfirm> deleteOrderDetailConfirmList,  List<OrderDetailConfirm> orderDetailConfirmList) {
		int insertOrderDetailConfirmNum = 0;
		int udpateOrderTableNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			//更新订单表状态
			udpateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);


			// 删除之前维修单详情确认表
			if (deleteOrderDetailConfirmList.size() > 0) {
				for (int i = 0; i < deleteOrderDetailConfirmList.size(); i++) {
					orderDetailConfirmDao.deleteByOrderDetailConfirm(session, deleteOrderDetailConfirmList.get(i));
				}
			}

			// 添加维修单详情确认表
			for (int i = 0; i < orderDetailConfirmList.size(); i++) {
				insertOrderDetailConfirmNum = insertOrderDetailConfirmNum +
						orderDetailConfirmDao.insertByOrderDetailConfirm(session, orderDetailConfirmList.get(i));
			}

			if (insertOrderDetailConfirmNum == 0 || udpateOrderTableNum ==0 ) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--saveOrderDetailConfirm--error:" + e.getMessage());
		}

		return udpateOrderTableNum;
	}

	/**
	 * 客户确认订单变更
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、根据OrderTable实体中的维修单ID，获取维修单详情中的产品ID、项目ID
	 * 3、添加订单详情记录表并删除订单详情表中的数据
	 * 4、将查询出来的详情确认表中的数据加到新的订单详情表中
	 * 5、查询针对订单单价表中的数据单价(总价)
	 * 6、更新维修单表状态
	 *
	 * @param orderTable
	 * @param orderDetailList
	 * @param orderDetailRecordList
	 * @param
	 * @param
	 * @param
	 * @return
	 *
	 * @author ZY on 2018/11/26
	 */
	public int orderDetailConfirmByCustomer(OrderTable orderTable, List<OrderDetail> orderDetailList,List<OrderDetail> newOrderDetailList,  List<OrderDetailRecord> orderDetailRecordList, List<OrderPrice> newOrderPriceList) {
		int updateOrderTableNum = 0;
		int deleteOrderDetailNum = 0;
		int insertOrderDetailNum =0;
		int insertOrderDetailRecordNum = 0;
		int insertOrderPriceNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			// 更新维修单表(类型、状态、维修人员、维修金额)
			updateOrderTableNum = orderTableDao.updateByOrderTable(session, orderTable);


			// 删除之前订单详情数据
			for (int i = 0; i < orderDetailList.size(); i++) {
				deleteOrderDetailNum = deleteOrderDetailNum + orderDetailDao.deleteByOrderDetail(session, orderDetailList.get(i));
			}
			// 添加新的订单信息
			for (int i = 0; i < newOrderDetailList.size(); i++) {
				insertOrderDetailNum = insertOrderDetailNum + orderDetailDao.insertByOrderDetail(session, newOrderDetailList.get(i));
			}

			// 添加新的订单单价信息
			if (newOrderPriceList.size() > 0) {
				for (int i = 0; i < newOrderPriceList.size(); i++) {
					orderPriceDao.insertByOrderPrice(session, newOrderPriceList.get(i));
				}
			}


			// 添加维修单对应维修单价表
			for (int i = 0; i < orderDetailRecordList.size(); i++) {
				insertOrderDetailRecordNum = insertOrderDetailRecordNum + orderDetailRecordDao.insertByOrderDetailRecord(session, orderDetailRecordList.get(i));
			}

			if (updateOrderTableNum == 0 || deleteOrderDetailNum == 0 || insertOrderDetailNum == 0 || insertOrderDetailRecordNum == 0 ) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OperationOrderService--backDistribution--error:" + e.getMessage());
		}

		return updateOrderTableNum;
	}

}
