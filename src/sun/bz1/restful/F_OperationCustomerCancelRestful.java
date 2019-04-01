package sun.bz1.restful;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.Announcement;
import sun.bz1.entity.BackMoney;
import sun.bz1.entity.CancelTable;
import sun.bz1.entity.CreditChange;
import sun.bz1.entity.OrderTable;
import sun.bz1.entity.Task;
import sun.bz1.entity.User;
import sun.bz1.entity.UserAndUserServiceAndUserCustomer;
import sun.bz1.entity.UserCustomerIncomeMoney;
import sun.bz1.entity.UserServiceIncomeMoney;
import sun.bz1.service.OperationCustomerCancelService;
import sun.bz1.service.OrderTableService;
import sun.bz1.service.UserService;
import util.Config;
import util.JsonUtil;
import util.VersionCompare;

/**
 * 维修单客户取消操作
 * 
 * 维修单维修工、安装队确认取消操作
 * 
 * Restful
 * 
 * @author WJF on 2018/09/17
 */

@Controller
@RequestMapping("/operation_customer_cancel")
public class F_OperationCustomerCancelRestful {

	@Autowired
	private OperationCustomerCancelService operationService;
	
	@Autowired
	private OrderTableService orderTableService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 客户取消
	 * 
	 * 操作过程：
	 * 
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 
	 * 2、判断是否可以取消维修单
	 * 派单模式下，维修人员退回、客户取消、维修人员确认取消、申请验收、验收合格、验收不合格、关闭订单状态的维修单不允许取消
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
	 * 传递OrderTable实体
	 * 传递CancelTable实体
	 * 
	 * 需在OrderTable实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 * 
	 * @param models
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	@ResponseBody
	@RequestMapping("/customerCancel")
	public LayuiDataTemplet<OrderTable> customerCancel(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null
		
		// JSON转换为实体类
		OrderTable orderTable = null;
		CancelTable cancelTable = null;
		try {
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
			cancelTable = JsonUtil.json2obj((String)models.get("cancelTable"), CancelTable.class);
		} catch (Exception e1) {
			e1.printStackTrace();
							
			returnData.setMsg("JSON转换为实体类发生异常！");
			return returnData;
		} 
				
		// 判断传递的数据
		// OrderTable实体
		if (orderTable == null) {
			returnData.setMsg("传递的维修单数据错误！");
			return returnData;
		} 
				
		// 判断传递的数据
		// CancelTable实体
		if (cancelTable == null) {
			returnData.setMsg("传递的取消数据错误！");
			return returnData;
		}
		
		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
				if (compare < 0) {
					returnData.setMsg("版本较低，请更新版本！");
					return returnData;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			returnData.setMsg("版本比较发生异常！");
			return returnData;
		}
		
		// 当前操作人员ID
		String currentUserId = orderTable.getCurrentuserid(); 
		if (currentUserId == null || currentUserId.isEmpty()) {
			returnData.setMsg("当前操作人ID为空，无法取消！");
			return returnData;
		}
		
		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法取消！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}
		
		// 2、判断是否可以取消维修单
		if ("PD".equals(orderTable.getOrdertype())) {
			// 派单模式下，维修人员退回、客户取消、维修人员确认取消、申请验收、验收合格、验收不合格、关闭订单状态的维修单不允许取消
			if ("WXTH".equals(orderTable.getOrderstate()) || "KHQX".equals(orderTable.getOrderstate()) ||
					"QRQX".equals(orderTable.getOrderstate()) || "SQYS".equals(orderTable.getOrderstate()) || 
					"YSHG".equals(orderTable.getOrderstate()) || "YSBHG".equals(orderTable.getOrderstate()) || 
					"GBDD".equals(orderTable.getOrderstate())) {
				returnData.setMsg("该维修单当前状态，无法取消！");
				return returnData;
			}
		} 
		
		// 3、对表格的操作
		int count = 0;
		
		if ("PD".equals(orderTable.getOrdertype())) {
			// (1).派单模式
			// (1.1).更新维修单表(状态)
			// 数据
			orderTable.setOrderstate("KHQX"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
			orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
			orderTable.setOrderupdatetime(new Date()); // 维修更新时间
				
			// (1.2).更新任务表(状态)
			Task task = new Task();
			// 数据
			task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			task.setTaskstate("N"); // 维修任务状态(Y:进行中/N:已完成)		
					
			// (1.3).添加客户取消维修单表
			// 数据
			cancelTable.setCancelid(Config.SIGN_CANCELTABLE + UUID.randomUUID().toString()); // 维修取消ID(KHQX+UUID)
			// 维修取消内容(原因)、维修取消维修人员确认是否扣除已发生的费用状态(Y:扣除/N:不扣除)、维修取消已发生的费用总价、维修取消已发生的费用总价
			// 以上这些数据都在传递的CancelTable实体中
			cancelTable.setCanceluserid(currentUserId); // 当前操作人员ID // 维修取消人ID(YHBG+UUID)
			cancelTable.setCanceltime(new Date()); // 维修取消时间
			
			// 保存
			count = operationService.customerCancel1(orderTable, task, cancelTable);
		} else if ("QD".equals(orderTable.getOrdertype())) {
			// (2).抢单模式
			if ("FB".equals(orderTable.getOrderstate())) {
				// (2.1).发布状态的维修单可以取消，并且不影响客户的信用值
				// (2.1.1).更新维修单表(状态)
				// 数据
				orderTable.setOrderstate("QRQX"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
				orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
				orderTable.setOrderupdatetime(new Date()); // 维修更新时间
					
				// (2.1.2).更新公告表(状态)
				Announcement announcement = new Announcement();
				// 数据
				announcement.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
				announcement.setAnnouncementstate("N"); // 维修公告状态(Y:进行中/N:已完成)		
						
				// (2.1.3).添加客户取消维修单表
				// 数据
				cancelTable.setCancelid(Config.SIGN_CANCELTABLE + UUID.randomUUID().toString()); // 维修取消ID(KHQX+UUID)
				cancelTable.setCancelstate("N"); // 维修取消维修人员确认是否扣除已发生的费用状态(Y:扣除/N:不扣除)
				cancelTable.setCancelhappenedmoney(0.0); // 维修取消已发生的费用总价
				cancelTable.setCanceldefaultmoney(0.0); // 维修取消平台违约金总价
				cancelTable.setCanceluserid(currentUserId); // 当前操作人员ID // 维修取消人ID(YHBG+UUID)
				cancelTable.setCanceltime(new Date()); // 维修取消时间
				
				// 保存
				count = operationService.customerCancel2(orderTable, announcement, cancelTable);
			} else if ("QD".equals(orderTable.getOrderstate())) {
				// (2.2).抢单状态的维修单可以取消，但需要扣减客户信用值10分
				// (2.2.1).更新维修单表(状态)
				// 数据
				orderTable.setOrderstate("QRQX"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
				orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
				orderTable.setOrderupdatetime(new Date()); // 维修更新时间
					
				// (2.2.2).更新公告表(状态)
				Announcement announcement = new Announcement();
				// 数据
				announcement.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
				announcement.setAnnouncementstate("N"); // 维修公告状态(Y:进行中/N:已完成)		
						
				// (2.2.3).添加客户取消维修单表
				// 数据
				cancelTable.setCancelid(Config.SIGN_CANCELTABLE + UUID.randomUUID().toString()); // 维修取消ID(KHQX+UUID)
				cancelTable.setCancelstate("N"); // 维修取消维修人员确认是否扣除已发生的费用状态(Y:扣除/N:不扣除)
				cancelTable.setCancelhappenedmoney(0.0); // 维修取消已发生的费用总价
				cancelTable.setCanceldefaultmoney(0.0); // 维修取消平台违约金总价
				cancelTable.setCanceluserid(currentUserId); // 当前操作人员ID // 维修取消人ID(YHBG+UUID)
				cancelTable.setCanceltime(new Date()); // 维修取消时间
				
				// (2.2.4).更新用户表(客户信用值)
				User userCustomer = new User();
				// 查询
				UserAndUserServiceAndUserCustomer selectUser = new UserAndUserServiceAndUserCustomer();
				selectUser.setUserid(orderTable.getOrdercreateuserid()); // 用户ID(YHBG+UUID)
				selectUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
				List<UserAndUserServiceAndUserCustomer> userList = null;
				userList = userService.selectThreeTablesByUnionData(selectUser); // 查询
				if (orderList == null || orderList.size() == 0) {
					returnData.setMsg("找不到该客户，无法取消！");
					return returnData;
				} else {
					// 数据
					userCustomer.setUserid(userList.get(0).getUserid()); // 用户ID(YHBG+UUID)
					userCustomer.setUsercredit(userList.get(0).getUsercredit() - 10); // 信用值
				}
				
				// (2.2.5).添加信用值变化表
				// 数据
				CreditChange creditChange = new CreditChange();
				creditChange.setCreditchangeid(Config.SIGN_CREDITCHANGE + UUID.randomUUID().toString()); // 信用值变化ID(XYBH+UUID)
				creditChange.setUserid(orderTable.getOrdercreateuserid()); // 用户ID(YHBG+UUID)
				creditChange.setCreditchangetype("KHQXQR"); // 信用值变化类型信用值变化类型(CSZ:初始值/WXTHDD:维修人员退回订单/KHQXQR:客户取消订单/KHYSHG:客户验收合格/KHGHWX:客户更换维修人员/KHWTPD:客户委托平台派单/PLWX:客户评论维修人员/PLKH:维修人员评论客户)
				creditChange.setCreditchange(-10.0); // 信用值变化值
				creditChange.setCreditchangecreatetime(new Date()); // 信用值变化创建时间
				
				// 保存
				count = operationService.customerCancel3(orderTable, announcement, cancelTable, userCustomer, creditChange);
			} else if ("JS".equals(orderTable.getOrderstate())) {
				// (3).接收状态的维修单，同派单模式的操作
				// (2.3.1).更新维修单表(状态)
				// 数据
				orderTable.setOrderstate("KHQX"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
				orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
				orderTable.setOrderupdatetime(new Date()); // 维修更新时间
					
				// (2.3.2).更新任务表(状态)
				Task task = new Task();
				// 数据
				task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
				task.setTaskstate("N"); // 维修任务状态(Y:进行中/N:已完成)		
						
				// (2.3.3).添加客户取消维修单表
				// 数据
				cancelTable.setCancelid(Config.SIGN_CANCELTABLE + UUID.randomUUID().toString()); // 维修取消ID(KHQX+UUID)
				// 维修取消内容(原因)、维修取消维修人员确认是否扣除已发生的费用状态(Y:扣除/N:不扣除)、维修取消已发生的费用总价、维修取消已发生的费用总价
				// 以上这些数据都在传递的CancelTable实体中
				cancelTable.setCanceluserid(currentUserId); // 当前操作人员ID // 维修取消人ID(YHBG+UUID)
				cancelTable.setCanceltime(new Date()); // 维修取消时间
				
				// 取消
				count = operationService.customerCancel1(orderTable, task, cancelTable);
			} else {
				returnData.setMsg("该维修单的维修状态错误，无法取消！");
				return returnData;
			}
		} else {
			returnData.setMsg("该维修单的维修类型错误，无法取消！");
			return returnData;
		}
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("客户取消失败！");
		} else {
			// 返回数据List
			List<OrderTable> orderTableList = new ArrayList<OrderTable>();
			orderTableList.add(orderTable);
			
			returnData.setCount(count);
			returnData.setData(orderTableList);
			returnData.setMsg("客户取消成功！");
		}

		return returnData;
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
	 * 传递CancelTable实体
	 * 
	 * 需在CancelTable实体中添加版本信息(可以为空)
	 * 
	 * @param cancelTable
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	@ResponseBody
	@RequestMapping("/confirmCancel")
	public LayuiDataTemplet<CancelTable> confirmCancel(@RequestBody CancelTable cancelTable) {
		LayuiDataTemplet<CancelTable> returnData = new LayuiDataTemplet<CancelTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null
		
		// 版本号不为空，则验证版本号
		try {
			if (cancelTable.getVersion() != null && !cancelTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(cancelTable.getVersion(), Config.VERSION);
				if (compare < 0) {
					returnData.setMsg("版本较低，请更新版本！");
					return returnData;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			returnData.setMsg("版本比较发生异常！");
			return returnData;
		}
		
		// 当前操作人员ID
		String currentUserId = cancelTable.getCanceluserid(); 
		if (currentUserId == null || currentUserId.isEmpty()) {
			returnData.setMsg("当前操作人ID为空，无法确认取消！");
			return returnData;
		}
		
		// 1、根据CancelTable实体中的维修单ID，获取对应的维修单数据
		OrderTable orderTable = new OrderTable();
		orderTable.setOrderid(cancelTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		orderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(orderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法确认取消！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}
		
		// 金额的默认数据
		if (orderTable.getOrderintercepttotalmoney() == null) {
			orderTable.setOrderintercepttotalmoney(0.0);
		}
		if (orderTable.getOrderintercepttraveltotalmoney() == null) {
			orderTable.setOrderintercepttraveltotalmoney(0.0);
		}
		if (orderTable.getOrderpricetotalmoney() == null) {
			orderTable.setOrderpricetotalmoney(0.0);
		}
		if (orderTable.getOrdertraveltotalmoney() == null) {
			orderTable.setOrdertraveltotalmoney(0.0);
		}
		
		// 2、更新维修单表(状态)
		// 数据
		orderTable.setOrderstate("QRQX"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间
		
		// 3、更新任务表(状态)
		Task task = new Task();
		// 数据
		task.setOrderid(cancelTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		task.setTaskstate("N"); // 维修任务状态(Y:进行中/N:已完成)
		
		// 4、更新取消表
		// 数据
		// 传递的数据
		
		// 金额数据：
		// (1).从平台中退还客户扣除维修人员已发生的费用以及违约金后的金额
		// (2).从平台中支出维修人员已发生的金额
		
		// 5、更新用户表(客户信用值、客户余额)
		User customerUser = new User();
		double customerMoney = 0.0; // 余额
		// 查询
		UserAndUserServiceAndUserCustomer selectCustomerUser = new UserAndUserServiceAndUserCustomer();
		selectCustomerUser.setUserid(orderTable.getOrdercreateuserid()); // 客户ID(YHBG+UUID)
		selectCustomerUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> customerUserList = null;
		customerUserList = userService.selectThreeTablesByUnionData(selectCustomerUser); // 查询
		if (customerUserList == null || customerUserList.size() == 0) {
			returnData.setMsg("找不到该客户，无法确认取消！");
			return returnData;
		} else {
			// 数据
			customerUser.setUserid(customerUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			customerUser.setUsercredit(customerUserList.get(0).getUsercredit() - 10); // 信用值
			
			// 余额
			if (cancelTable.getCancelhappenedmoney() != null && 
					Double.doubleToLongBits(cancelTable.getCancelhappenedmoney()) != 0) {
				cancelTable.setCancelstate("Y"); // 维修取消维修人员确认是否扣除已发生的费用状态(Y:扣除/N:不扣除)
				
				// 金额(可正可负) = (维修推荐总价 + 维修差旅费推荐总价) - 维修取消已发生的费用总价 - (维修推荐总价 * 10%)(违约金)
				customerMoney = (orderTable.getOrderintercepttotalmoney() + orderTable.getOrderintercepttraveltotalmoney()) - cancelTable.getCancelhappenedmoney() - (orderTable.getOrderintercepttotalmoney() * 0.1);
				
				// 余额
				customerUser.setUsermoney(customerUserList.get(0).getUsermoney() + customerMoney);
			}
			
			customerUser.setUserupdateuserid(currentUserId); // 当前操作人员ID // 用户更新人ID(YHBG+UUID)
			customerUser.setUserupdatetime(new Date()); // 用户更新时间
		}
		
		// 6、添加信用值变化表
		// 数据
		CreditChange creditChange = new CreditChange();
		creditChange.setCreditchangeid(Config.SIGN_CREDITCHANGE + UUID.randomUUID().toString()); // 信用值变化ID(XYBH+UUID)
		creditChange.setUserid(customerUser.getUserid()); // 客户ID(YHBG+UUID)
		creditChange.setCreditchangetype("KHQXQR"); // 信用值变化类型信用值变化类型(CSZ:初始值/WXTHDD:维修人员退回订单/KHQXQR:客户取消订单/KHYSHG:客户验收合格/KHGHWX:客户更换维修人员/KHWTPD:客户委托平台派单/PLWX:客户评论维修人员/PLKH:维修人员评论客户)
		creditChange.setCreditchange(-10.0); // 信用值变化值
		creditChange.setCreditchangecreatetime(new Date()); // 信用值变化创建时间
		
		UserCustomerIncomeMoney customerIncomeMoney = null;
		BackMoney customerBackMoney = null;
		if (customerMoney != 0.0) {
			// 7、添加用户_客户_收入_金额表
			customerIncomeMoney = new UserCustomerIncomeMoney();
			// 数据
			customerIncomeMoney.setUsercustomerincomemoneyid(Config.SIGN_USERCUSTOMERINCOMEMONEY + UUID.randomUUID().toString()); // 客户收入金额ID(KHSR+UUID)
			customerIncomeMoney.setUsercustomerincomemoneyuserid(customerUser.getUserid()); // 客户ID(YHBG+UUID)
			customerIncomeMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			customerIncomeMoney.setUsercustomerincomemoneysource("QRQXKH"); // 金额来源(WXTH:维修人员退回(客户收入金额)/QRQXKH:确认取消(客户收入金额)/GHWX:更换维修人员(客户收入金额)/WTPD:委托平台派单(客户收入金额)/PTXTKH:平台协调(客户收入金额)/KHCZ:客户充值(客户收入金额))
			customerIncomeMoney.setUsercustomerincomemoney(customerMoney); // 金额(可正可负)
			customerIncomeMoney.setUsercustomerincomemoneycreatetime(new Date()); // 金额创建时间
								
			// 8、添加平台_收益金额表
			customerBackMoney = new BackMoney();
			// 数据
			customerBackMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 金额变化ID(PTJE+UUID)
			customerBackMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			customerBackMoney.setBackmoneyother1("QRQXKH"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户))
			customerBackMoney.setBackmoney(-customerMoney); // 金额(可正可负)
			customerBackMoney.setBackmoneycreatetime(new Date()); // 金额创建时间
		}
		
		// 9、更新用户表(维修人员余额)
		// 查询用户信息
		User serviceUser = new User();
		double serviceMoney = 0.0; // 余额
		// 查询
		UserAndUserServiceAndUserCustomer selectServiceUser = new UserAndUserServiceAndUserCustomer();
		selectServiceUser.setUserid(orderTable.getOrderserviceuserid()); // 维修人员ID(YHBG+UUID)
		selectServiceUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> serviceUserList = null;
		serviceUserList = userService.selectThreeTablesByUnionData(selectServiceUser); // 查询
		if (serviceUserList == null || serviceUserList.size() == 0) {
			returnData.setMsg("找不到该维修人员，无法确认取消！");
			return returnData;
		} else {
			// 数据
			serviceUser.setUserid(serviceUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			serviceUser.setUserupdateuserid(currentUserId); // 当前操作人员ID // 用户更新人ID(YHBG+UUID)
			serviceUser.setUserupdatetime(new Date()); // 用户更新时间
			
			// 余额
			if (cancelTable.getCancelhappenedmoney() != null && 
					Double.doubleToLongBits(cancelTable.getCancelhappenedmoney()) != 0) {
				// 金额(可正可负) = 维修取消已发生的费用总价
				serviceMoney = cancelTable.getCancelhappenedmoney();
				
				// 余额
				serviceUser.setUsermoney(serviceUserList.get(0).getUsermoney() + serviceMoney);
			}
		}
		
		UserServiceIncomeMoney serviceIncomeMoney = null;
		BackMoney serviceBackMoney = null;
		if (serviceMoney != 0.0) {
			// 10、添加用户_维修工、安装队_收入_金额表
			serviceIncomeMoney = new UserServiceIncomeMoney();
			// 数据
			serviceIncomeMoney.setUserserviceincomemoneyid(Config.SIGN_USERSERVICEINCOMEMONEY + UUID.randomUUID().toString()); // 维修人员收入金额ID(WXSR+UUID)
			serviceIncomeMoney.setUserserviceincomemoneyuserid(serviceUser.getUserid()); // 维修人员ID(YHBG+UUID)
			serviceIncomeMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			serviceIncomeMoney.setUserserviceincomemoneysource("QRQXWX"); // 金额来源(QRQXWX:确认取消(维修人员收入金额)/YSHG:验收合格(维修人员收入金额)/PTXTWX:平台协调(维修人员收入金额)/WXCZ:维修人员充值(维修人员收入金额))
			serviceIncomeMoney.setUserserviceincomemoney(serviceMoney); // 金额(可正可负)
			serviceIncomeMoney.setUserserviceincomemoneycreatetime(new Date()); // 金额创建时间
								
			// 11、添加平台_收益金额表
			serviceBackMoney = new BackMoney();
			// 数据
			serviceBackMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 金额变化ID(PTJE+UUID)
			serviceBackMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			serviceBackMoney.setBackmoneyother1("QRQXWX"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户))
			serviceBackMoney.setBackmoney(-serviceMoney); // 金额(可正可负)
			serviceBackMoney.setBackmoneycreatetime(new Date()); // 金额创建时间
		}

		// 维修工、安装队确认取消
		int count = 0;
		count = operationService.confirmCancel(orderTable, task, cancelTable, customerUser, 
				creditChange, customerIncomeMoney, customerBackMoney, serviceUser, serviceIncomeMoney, serviceBackMoney);
	
		// 返回数据
		if (count == 0) {
			returnData.setMsg("维修工、安装队确认取消失败！");
		} else {
			// 返回数据List
			List<CancelTable> cancelTableList = new ArrayList<CancelTable>();
			cancelTableList.add(cancelTable);
					
			returnData.setCount(count);
			returnData.setData(cancelTableList);
			returnData.setMsg("维修工、安装队确认取消成功！");
		}

		return returnData;
	}
	
}
