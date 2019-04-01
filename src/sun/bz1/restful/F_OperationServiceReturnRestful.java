package sun.bz1.restful;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.BackMoney;
import sun.bz1.entity.CreditChange;
import sun.bz1.entity.OrderPrice;
import sun.bz1.entity.OrderTable;
import sun.bz1.entity.ReturnTable;
import sun.bz1.entity.Task;
import sun.bz1.entity.User;
import sun.bz1.entity.UserAndUserServiceAndUserCustomer;
import sun.bz1.entity.UserCustomerIncomeMoney;
import sun.bz1.service.OperationServiceReturnService;
import sun.bz1.service.OrderTableService;
import sun.bz1.service.UserService;
import util.Config;
import util.VersionCompare;

/**
 * 维修单维修工、安装队退回操作
 * 
 * Restful
 * 
 * @author WJF on 2018/09/18
 */

@Controller
@RequestMapping("/operation_service_return")
public class F_OperationServiceReturnRestful {
	
	@Autowired
	private OperationServiceReturnService operationService;
	
	@Autowired
	private OrderTableService orderTableService;
	
	@Autowired
	private UserService userService;
	
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
	 * 传递ReturnTable实体
	 * 
	 * 需在ReturnTable实体中添加版本信息(可以为空)
	 * 
	 * @param returnTable
	 * @return
	 * 
	 * @author WJF on 2018/09/18
	 */
	@ResponseBody
	@RequestMapping("/returnOrder")
	public LayuiDataTemplet<ReturnTable> returnOrder(@RequestBody ReturnTable returnTable) {
		LayuiDataTemplet<ReturnTable> returnData = new LayuiDataTemplet<ReturnTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null
		
		// 版本号不为空，则验证版本号
		try {
			if (returnTable.getVersion() != null && !returnTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(returnTable.getVersion(), Config.VERSION);
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
		String currentUserId = returnTable.getReturnuserid(); 
		if (currentUserId == null || currentUserId.isEmpty()) {
			returnData.setMsg("当前操作人ID为空，无法退回！");
			return returnData;
		}
		
		// 1、根据CancelTable实体中的维修单ID，获取对应的维修单数据
		OrderTable orderTable = new OrderTable();
		orderTable.setOrderid(returnTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		orderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(orderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法退回！");
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
		
		// 2、更新维修单表(维修人员、状态)
		// 数据
		orderTable.setOrderstate("WXTH"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		orderTable.setOrderserviceuserid(""); // 维修人员ID(YHBG+UUID)
		orderTable.setOrderpricetotalmoney(0.0); // 维修总价(根据维修单价计算得出)
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间
		
		// 3、更新任务表(状态)
		Task task = new Task();
		// 数据
		task.setOrderid(returnTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		task.setTaskstate("TH"); // 维修任务状态(Y:进行中/N:已完成/TH:已退回)
		
		// 4、添加退回表
		// 数据
		returnTable.setReturnid(Config.SIGN_RETURNTABLE + UUID.randomUUID().toString()); // 维修退回ID(WXTH+UUID)
		returnTable.setReturntime(new Date()); // 维修退回时间
		
		// 5、更新用户表(维修人员信用值-10)
		// 查询用户信息
		User serviceUser = new User();
		// 查询
		UserAndUserServiceAndUserCustomer selectUserService = new UserAndUserServiceAndUserCustomer();
		selectUserService.setUserid(orderTable.getOrderserviceuserid()); // 维修人员ID(YHBG+UUID)
		selectUserService.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> userServiceList = null;
		userServiceList = userService.selectThreeTablesByUnionData(selectUserService); // 查询
		if (userServiceList == null || userServiceList.size() == 0) {
			returnData.setMsg("找不到该维修人员，无法退回！");
			return returnData;
		} else {
			// 数据
			serviceUser.setUserid(userServiceList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			serviceUser.setUsercredit(userServiceList.get(0).getUsercredit() - 10); // 用户信用值
			serviceUser.setUserupdateuserid(currentUserId); // 当前操作人员ID // 用户更新人ID(YHBG+UUID)
			serviceUser.setUserupdatetime(new Date()); // 用户更新时间
		}
		
		// 6、添加信用值变化表
		// 数据
		CreditChange creditChange = new CreditChange();
		creditChange.setCreditchangeid(Config.SIGN_CREDITCHANGE + UUID.randomUUID().toString()); // 信用值变化ID(XYBH+UUID)
		creditChange.setUserid(orderTable.getOrdercreateuserid()); // 维修人员ID(YHBG+UUID)
		creditChange.setCreditchangetype("WXTHDD"); // 信用值变化类型信用值变化类型(CSZ:初始值/WXTHDD:维修人员退回订单/KHQXQR:客户取消订单/KHYSHG:客户验收合格/KHGHWX:客户更换维修人员/KHWTPD:客户委托平台派单/PLWX:客户评论维修人员/PLKH:维修人员评论客户)
		creditChange.setCreditchange(-10.0); // 信用值变化值
		creditChange.setCreditchangecreatetime(new Date()); // 信用值变化创建时间
		
		// 7、删除维修单对应维修单价表对应数据
		OrderPrice orderPrice = new OrderPrice();
		orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		
		// 金额数据：从平台中按 维修推荐总价+维修差旅费推荐总价 支出金额给客户
		
		// 8、更新用户表(客户余额)
		// 查询用户信息
		User customerUser = new User();
		// 查询
		UserAndUserServiceAndUserCustomer selectCustomerUser = new UserAndUserServiceAndUserCustomer();
		selectCustomerUser.setUserid(orderTable.getOrdercreateuserid()); // 客户ID(YHBG+UUID)
		selectCustomerUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> customerUserList = null;
		customerUserList = userService.selectThreeTablesByUnionData(selectCustomerUser); // 查询
		if (customerUserList == null || customerUserList.size() == 0) {
			returnData.setMsg("找不到该客户，无法退回！");
			return returnData;
		} else {
			// 数据
			customerUser.setUserid(customerUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			customerUser.setUsermoney(customerUserList.get(0).getUsermoney() + orderTable.getOrderintercepttotalmoney() + orderTable.getOrderintercepttraveltotalmoney()); // 用户余额
			customerUser.setUserupdateuserid(currentUserId); // 当前操作人员ID // 用户更新人ID(YHBG+UUID)
			customerUser.setUserupdatetime(new Date()); // 用户更新时间
		}
		
		// 9、添加用户_客户_收入_金额表
		UserCustomerIncomeMoney customerIncomeMoney = new UserCustomerIncomeMoney();
		// 数据
		customerIncomeMoney.setUsercustomerincomemoneyid(Config.SIGN_USERCUSTOMERINCOMEMONEY + UUID.randomUUID().toString()); // 客户收入金额ID(KHSR+UUID)
		customerIncomeMoney.setUsercustomerincomemoneyuserid(customerUser.getUserid()); // 客户ID(YHBG+UUID)
		customerIncomeMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		customerIncomeMoney.setUsercustomerincomemoneysource("WXTH"); // 金额来源(WXTH:维修人员退回(客户收入金额)/QRQXKH:确认取消(客户收入金额)/GHWX:更换维修人员(客户收入金额)/WTPD:委托平台派单(客户收入金额)/PTXTKH:平台协调(客户收入金额)/KHCZ:客户充值(客户收入金额))
		customerIncomeMoney.setUsercustomerincomemoney(orderTable.getOrderintercepttotalmoney() + orderTable.getOrderintercepttraveltotalmoney()); // 金额(可正可负)
		customerIncomeMoney.setUsercustomerincomemoneycreatetime(new Date()); // 金额创建时间
					
		// 10、添加平台_收益金额表
		BackMoney backMoney = new BackMoney();
		// 数据
		backMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 金额变化ID(PTJE+UUID)
		backMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		backMoney.setBackmoneyother1("WXTH"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户))
		backMoney.setBackmoney(-(orderTable.getOrderintercepttotalmoney() + orderTable.getOrderintercepttraveltotalmoney())); // 金额(可正可负)
		backMoney.setBackmoneycreatetime(new Date()); // 金额创建时间
		
		// 维修工、安装队退回
		int count = 0;
		count = operationService.returnOrder(orderTable, task, returnTable, 
				serviceUser, creditChange, orderPrice, customerUser, customerIncomeMoney, backMoney);
				
		// 11、后台重新派单
		// 数据
		F_OperationOrderRestful operationOrderRestful = new F_OperationOrderRestful();
		operationOrderRestful.backDistribution(orderTable);
	
		// 返回数据
		if (count == 0) {
			returnData.setMsg("维修工、安装队退回失败！");
		} else {
			// 返回数据List
			List<ReturnTable> returnTableList = new ArrayList<ReturnTable>();
			returnTableList.add(returnTable);
					
			returnData.setCount(count);
			returnData.setData(returnTableList);
			returnData.setMsg("维修工、安装队退回成功！");
		}

		return returnData;
	}
	
}
