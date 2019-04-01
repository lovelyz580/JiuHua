package sun.bz1.restful;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.ApplyCheck;
import sun.bz1.entity.ApplyCheckAndOrderTableAndUser;
import sun.bz1.entity.BackMoney;
import sun.bz1.entity.OrderTable;
import sun.bz1.entity.User;
import sun.bz1.entity.UserAndUserServiceAndUserCustomer;
import sun.bz1.entity.UserCustomerIncomeMoney;
import sun.bz1.entity.UserServiceIncomeMoney;
import sun.bz1.service.ApplyCheckService;
import sun.bz1.service.OperationCheckService;
import sun.bz1.service.OrderTableService;
import sun.bz1.service.UserService;
import util.Config;
import util.VersionCompare;

/**
 * 维修验收表(验收成功或失败时在该表中添加数据)
 * 
 * Restful
 * 
 * @author WJF on 2018/09/08
 */

@Controller
@RequestMapping("/apply_check")
public class F_ApplyCheckRestful {

	@Autowired
	private ApplyCheckService applyCheckService;
	
	@Autowired
	private OrderTableService orderTableService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OperationCheckService operationCheckService;
	
	// 格式化时间
	private SimpleDateFormat sdfOfDate = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdfOfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 根据ApplyCheck实体添加
	 * 
	 * @param applyCheck
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/insertByApplyCheck")
	public LayuiDataTemplet<ApplyCheck> insertByApplyCheck(@RequestBody ApplyCheck applyCheck) {
		LayuiDataTemplet<ApplyCheck> returnData = new LayuiDataTemplet<ApplyCheck>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (applyCheck.getVersion() != null && !applyCheck.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(applyCheck.getVersion(), Config.VERSION);
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

		// 添加数据
		applyCheck.setApplycheckid(Config.SIGN_APPLYCHECK + UUID.randomUUID().toString()); // 维修验收ID(WXYS+UUID)
		applyCheck.setApplycheckstate("SQYS"); // 维修验收状态(SQYS:申请验收)(HG:合格)((派单模式)GHWX:更换维修人员)((抢单模式)WTPD:委托平台派单/PTXT:平台协调)(ZDYS:自动验收)
		applyCheck.setApplycheckapplytime(new Date()); // 维修验收申请时间
		// 维修自动验收时间(维修申请14天后，客户不验收，则平台自动验收)
		try {
			// 计算出自动验收时间(当前时间14天后)
			Calendar curr = Calendar.getInstance();
			curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + 7);
			Date automaticDate = curr.getTime();
			Date automaticTime = sdfOfTime.parse(sdfOfDate.format(automaticDate) + " 00:00:00"); // 格式化时间
			applyCheck.setApplycheckautomatictime(automaticTime); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 添加
		int count = 0;
		count = applyCheckService.insertByApplyCheck(applyCheck);
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("添加失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("添加成功！");
		}

		return returnData;
	}
	
	/**
	 * 根据ApplyCheck实体更新
	 * 
	 * @param applyCheck
	 * @return
	 * 
	 * @author WJF on 2018/10/15
	 */
	@ResponseBody
	@RequestMapping("/updateByApplyCheck")
	public LayuiDataTemplet<ApplyCheck> updateByApplyCheck(@RequestBody ApplyCheck applyCheck) {
		LayuiDataTemplet<ApplyCheck> returnData = new LayuiDataTemplet<ApplyCheck>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (applyCheck.getVersion() != null && !applyCheck.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(applyCheck.getVersion(), Config.VERSION);
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

		// 更新数据
		
		// 更新
		int count = 0;
		count = applyCheckService.updateByApplyCheck(applyCheck);
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("更新失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("更新成功！");
		}

		return returnData;
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
	 * @author ZY on 2018/11/08
	 */
	@ResponseBody
	@RequestMapping("/updateMoneyByApplyCheck")
	public LayuiDataTemplet<ApplyCheck> updateMoneyByApplyCheck(@RequestBody ApplyCheck applyCheck) {
		LayuiDataTemplet<ApplyCheck> returnData = new LayuiDataTemplet<ApplyCheck>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为d0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (applyCheck.getVersion() != null && !applyCheck.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(applyCheck.getVersion(), Config.VERSION);
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

		// 更新判断
		if (applyCheck.getId() == null || applyCheck.getId() == 0) {
			if (applyCheck.getApplycheckid() == null || applyCheck.getApplycheckid().isEmpty()) {
				if (applyCheck.getOrderid() == null || applyCheck.getOrderid().isEmpty()) {
					returnData.setMsg("缺少更新条件，更新失败！");
					return returnData;
				}
			}
		}
				
		// 更新数据
		int count = 0;
		
		if("PTXT".equals(applyCheck.getApplycheckstate()) && null != applyCheck.getApplycheckmoney()){
			// 如果修改的验收状态是平台协调，并且验收最终结算总价不为空
			// 当前操作人员ID
			String currentUserId = applyCheck.getApplycheckbackuserid(); 
			if (currentUserId == null || currentUserId.isEmpty()) {
				returnData.setMsg("当前操作人(平台操作人)ID为空，无法更新！");
				return returnData;
			}
							
			// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
			OrderTable orderTable = new OrderTable();
			orderTable.setOrderid(applyCheck.getOrderid());
			orderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<OrderTable> orderList = null;
			orderList = orderTableService.selectByOrderTable(orderTable);
			if (orderList == null || orderList.size() == 0) {
				returnData.setMsg("找不到该维修单，无法更新！");
				return returnData;
			} else {
				orderTable = orderList.get(0);
			}
			
//			// 2、根据ApplyCheck实体中的验收ID，获取对应的验收数据
//			OrderTable selectOrderTable = new OrderTable();
//			selectOrderTable.setOrderid(applyCheck.getOrderid());
//			selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
//			List<OrderTable> orderList = null;
//			orderList = orderTableService.selectByOrderTable(selectOrderTable);
//			if (orderList == null || orderList.size() == 0) {
//				returnData.setMsg("找不到该维修单，无法更新！");
//				return returnData;
//			} else {
//				orderTable = orderList.get(0);
//			}
			
			// 3、更新验收表
			// 数据
			// 传递的数据
			
			// 金额数据：
			// (1).从平台中协商的金额支出金额给维修人员
			// (2).从平台中协商的金额支出金额给客户
			// (3).平台客服产生的费用由过错方承担
			
			// 4、更新用户表(维修人员余额)
			User serviceUser = new User();
			// 查询
			UserAndUserServiceAndUserCustomer selectServiceUser = new UserAndUserServiceAndUserCustomer();
			selectServiceUser.setUserid(orderTable.getOrderserviceuserid()); // 维修人员ID(YHBG+UUID)
			selectServiceUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<UserAndUserServiceAndUserCustomer> serviceUserList = null; 
			serviceUserList = userService.selectThreeTablesByUnionData(selectServiceUser);
			if (serviceUserList == null || serviceUserList.size() == 0) {
				returnData.setMsg("没有找到该维修人员，无法更新！");
				return returnData;
			}
			// 数据
			serviceUser.setUserid(serviceUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			serviceUser.setUserupdateuserid(currentUserId); // 当前操作人员ID // 用户更新人ID(YHBG+UUID)
			serviceUser.setUserupdatetime(new Date()); // 用户更新时间
			// 用户余额
			double serviceMoney = 0.0;
			if ("WX".equals(applyCheck.getApplycheckbackfault()) && applyCheck.getApplycheckbackmoney() != null) {
				 // 维修人员为过错方，需要支付平台协调服务最终结算总价
				serviceMoney = applyCheck.getApplycheckmoney() - applyCheck.getApplycheckbackmoney(); 
			} else {
				serviceMoney = applyCheck.getApplycheckmoney();
			}
			serviceUser.setUsermoney(serviceUserList.get(0).getUsermoney() + serviceMoney); // 用户余额
					
			// 5、添加用户_维修工、安装队_收入_金额表
			UserServiceIncomeMoney serviceIncomeMoney = new UserServiceIncomeMoney();
			// 数据
			serviceIncomeMoney.setUserserviceincomemoneyid(Config.SIGN_USERSERVICEINCOMEMONEY + UUID.randomUUID().toString()); // 维修人员收入金额ID(WXSR+UUID)
			serviceIncomeMoney.setUserserviceincomemoneyuserid(serviceUser.getUserid()); // 维修人员ID(YHBG+UUID)
			serviceIncomeMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			serviceIncomeMoney.setUserserviceincomemoneysource("PTXTWX"); // 金额来源(QRQXWX:确认取消(维修人员收入金额)/YSHG:验收合格(维修人员收入金额)/PTXTWX:平台协调(维修人员收入金额)/WXCZ:维修人员充值(维修人员收入金额))
			serviceIncomeMoney.setUserserviceincomemoney(serviceMoney); // 金额(可正可负)
			serviceIncomeMoney.setUserserviceincomemoneycreatetime(new Date()); // 金额创建时间
					
			// 6、添加平台_收益金额表
			BackMoney serviceBackMoney = new BackMoney();
			// 数据
			serviceBackMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 金额变化ID(PTJE+UUID)
			serviceBackMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			serviceBackMoney.setBackmoneyother1("PTXTWX"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户))
			serviceBackMoney.setBackmoney(-serviceMoney); // 金额(可正可负)
			serviceBackMoney.setBackmoneycreatetime(new Date()); // 金额创建时间	
			
			// 7、更新用户表(客户余额)
			User customerUser = new User();
			// 查询
			UserAndUserServiceAndUserCustomer selectCustomerUser = new UserAndUserServiceAndUserCustomer();
			selectCustomerUser.setUserid(orderTable.getOrdercreateuserid()); // 客户ID(YHBG+UUID)
			selectCustomerUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<UserAndUserServiceAndUserCustomer> customerUserList = null; 
			customerUserList = userService.selectThreeTablesByUnionData(selectCustomerUser);
			if (customerUserList == null || customerUserList.size() == 0) {
				returnData.setMsg("没有找到该客户，无法更新！");
				return returnData;
			}
			// 数据
			customerUser.setUserid(customerUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			customerUser.setUserupdateuserid(currentUserId); // 当前操作人员ID // 用户更新人ID(YHBG+UUID)
			customerUser.setUserupdatetime(new Date()); // 用户更新时间
			// 用户余额
			double customerMoney = 0.0;
			if ("AZ".equals(applyCheck.getApplycheckbackfault()) && applyCheck.getApplycheckbackmoney() != null) {
				 // 客户为过错方，需要支付平台协调服务最终结算总价
				customerMoney = applyCheck.getApplycheckmoney() - applyCheck.getApplycheckbackmoney(); 
			} else {
				customerMoney = applyCheck.getApplycheckmoney();
			}
			customerUser.setUsermoney(customerUserList.get(0).getUsermoney() + customerMoney);
								
			// 8、添加用户_客户_收入_金额表
			UserCustomerIncomeMoney customerIncomeMoney = new UserCustomerIncomeMoney();
			// 数据
			customerIncomeMoney.setUsercustomerincomemoneyid(Config.SIGN_USERCUSTOMERINCOMEMONEY + UUID.randomUUID().toString()); // 客户收入金额ID(KHSR+UUID)
			customerIncomeMoney.setUsercustomerincomemoneyuserid(customerUser.getUserid()); // 客户ID(YHBG+UUID)
			customerIncomeMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			customerIncomeMoney.setUsercustomerincomemoneysource("PTXTKH"); // 金额来源(WXTH:维修人员退回(客户收入金额)/QRQXKH:确认取消(客户收入金额)/GHWX:更换维修人员(客户收入金额)/WTPD:委托平台派单(客户收入金额)/PTXTKH:平台协调(客户收入金额)/KHCZ:客户充值(客户收入金额))
			customerIncomeMoney.setUsercustomerincomemoney(customerMoney); // 金额(可正可负)
			customerIncomeMoney.setUsercustomerincomemoneycreatetime(new Date()); // 金额创建时间
								
			// 9、添加平台_收益金额表
			BackMoney customerBackMoney = new BackMoney();
			// 数据
			customerBackMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 金额变化ID(PTJE+UUID)
			customerBackMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			customerBackMoney.setBackmoneyother1("PTXTKH"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户))
			customerBackMoney.setBackmoney(-customerMoney); // 金额(可正可负)
			customerBackMoney.setBackmoneycreatetime(new Date()); // 金额创建时间	
			
			// 更新
			count = operationCheckService.updateApplyCheckUnqualifiedAndToService(applyCheck,
					serviceUser, serviceIncomeMoney, serviceBackMoney,
					customerUser, customerIncomeMoney, customerBackMoney);
		} else {
			// 更新
			count = applyCheckService.updateByApplyCheck(applyCheck);
		}
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("更新失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("更新成功！");
		}

		return returnData;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表查询
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
	 * @author WJF on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionData")
	public LayuiDataTemplet<ApplyCheckAndOrderTableAndUser> selectThreeTablesByUnionData(@RequestBody ApplyCheckAndOrderTableAndUser unionData) {
		LayuiDataTemplet<ApplyCheckAndOrderTableAndUser> returnData = new LayuiDataTemplet<ApplyCheckAndOrderTableAndUser>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (unionData.getVersion() != null && !unionData.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(unionData.getVersion(), Config.VERSION);
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

		// 分页数据
		// 使用limit分页查询，根据偏移量查询
		// 第一个参数为第一个返回记录行的偏移量，第二个参数为返回记录行的最大数目
		// MySQL > SELECT * FROM table LIMIT 5, 10; // 查询第6-15行数据
		if (unionData.getPagenumber() != null) {
			// 计算偏移量
			if (unionData.getPagenumber() != -1) {
				if (unionData.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = unionData.getPagenumber();
				int pageSize = unionData.getPagesize();
				unionData.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				unionData.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = applyCheckService.selectThreeTablesCountByUnionData(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<ApplyCheckAndOrderTableAndUser> applyCheckList = applyCheckService.selectThreeTablesByUnionData(unionData); // 查询数据
				returnData.setData(applyCheckList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表查询 按时间倒序(针对订单的倒序排列，需要有剩余金额)
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
	 * @author ZY on 2018/11/16
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionDataByTimeDesc")
	public LayuiDataTemplet<ApplyCheckAndOrderTableAndUser> selectThreeTablesByUnionDataByTimeDesc(@RequestBody ApplyCheckAndOrderTableAndUser unionData) {
		LayuiDataTemplet<ApplyCheckAndOrderTableAndUser> returnData = new LayuiDataTemplet<ApplyCheckAndOrderTableAndUser>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (unionData.getVersion() != null && !unionData.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(unionData.getVersion(), Config.VERSION);
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

		// 分页数据
		// 使用limit分页查询，根据偏移量查询
		// 第一个参数为第一个返回记录行的偏移量，第二个参数为返回记录行的最大数目
		// MySQL > SELECT * FROM table LIMIT 5, 10; // 查询第6-15行数据
		if (unionData.getPagenumber() != null) {
			// 计算偏移量
			if (unionData.getPagenumber() != -1) {
				if (unionData.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = unionData.getPagenumber();
				int pageSize = unionData.getPagesize();
				unionData.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				unionData.setPagesize(pageSize); // 每页的数据量
			}

			//按照状态查询只查询HG的数据
			//unionData.setApplycheckstate("HG");
			// 查询数量
			int count = 0;
			count = applyCheckService.selectThreeTablesCountByUnionData(unionData);

			// 返回数据
			if (count == 0) {
			    //查询第一次验收时，剩余价格
			    OrderTable orderTable = new OrderTable();
                orderTable.setPagenumber(-1);//不分页
                orderTable.setOrderid(unionData.getOrderid());//订单ID
                List<OrderTable> orderTables = null;
                orderTables = orderTableService.selectByOrderTable(orderTable);
                ApplyCheckAndOrderTableAndUser applyCheckAndOrderTableAndUser = new ApplyCheckAndOrderTableAndUser();
                applyCheckAndOrderTableAndUser.setOrderid(unionData.getOrderid());//订单编号
                if (orderTables.get(0).getOrdertype().equals("PD")) {
                    applyCheckAndOrderTableAndUser.setServicesurplusmoney(orderTables.get(0).getOrderpricetotalmoney() + orderTables.get(0).getOrdertraveltotalmoney());
                    applyCheckAndOrderTableAndUser.setSurplusmoney(orderTables.get(0).getOrderpricetotalmoney() + orderTables.get(0).getOrdertraveltotalmoney());
                } else if (orderTables.get(0).getOrdertype().equals("QD")) {
                    applyCheckAndOrderTableAndUser.setServicesurplusmoney(orderTables.get(0).getOrderpricetotalmoney() + orderTables.get(0).getOrdertraveltotalmoney());
                    applyCheckAndOrderTableAndUser.setSurplusmoney(orderTables.get(0).getOrderintercepttotalmoney() + orderTables.get(0).getOrdertraveltotalmoney());
                }

                List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();
                returnData.setData(applyCheckList);
                applyCheckList.add(applyCheckAndOrderTableAndUser);
				returnData.setMsg("第一次申请验收查询剩余价格！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				/*//按照状态查询只查询HG的数据
				unionData.setApplycheckstate("HG");*/
				List<ApplyCheckAndOrderTableAndUser> applyCheckList = applyCheckService.selectThreeTablesByUnionDataByTimeDesc(unionData); // 查询数据
				double money = 0;
                double serviceMoney = 0;
				for (int i = 0; i < applyCheckList.size(); i ++){
				    double applyCheckMoney = applyCheckList.get(i).getApplycheckmoney();
                    double orderPriceTotalMoney = applyCheckList.get(i).getOrderpricetotalmoney();
                    double orderInterceptTotalMoney = applyCheckList.get(i).getOrderintercepttotalmoney();
					if (!applyCheckList.get(i).getApplycheckstate().equals("BHG")) {
                    if (applyCheckList.get(0).getOrdertype().equals("PD")) {
                        applyCheckList.get(i).setCustomermoney(orderInterceptTotalMoney * (applyCheckMoney/orderPriceTotalMoney));
                    } else if (applyCheckList.get(0).getOrdertype().equals("QD")){
                        applyCheckList.get(i).setCustomermoney(applyCheckMoney);
                    }

						money = money + applyCheckList.get(i).getCustomermoney();
						serviceMoney = serviceMoney + applyCheckList.get(i).getApplycheckmoney();
					} else {
						applyCheckList.get(i).setCustomermoney(0.0);
					}
				}
				if (applyCheckList.get(0).getOrdertype().equals("QD")) {
					applyCheckList.get(0).setSurplusmoney((applyCheckList.get(0).getOrderpricetotalmoney() + applyCheckList.get(0).getOrdertraveltotalmoney()) - money);
                    applyCheckList.get(0).setServicesurplusmoney((applyCheckList.get(0).getOrderpricetotalmoney() + applyCheckList.get(0).getOrdertraveltotalmoney()) - serviceMoney);
				} else if (applyCheckList.get(0).getOrdertype().equals("PD")) {
					applyCheckList.get(0).setSurplusmoney((applyCheckList.get(0).getOrderintercepttotalmoney() + applyCheckList.get(0).getOrdertraveltotalmoney()) - money);
                    applyCheckList.get(0).setServicesurplusmoney((applyCheckList.get(0).getOrderpricetotalmoney() + applyCheckList.get(0).getOrdertraveltotalmoney()) - serviceMoney);
				}
				returnData.setData(applyCheckList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表模糊查询
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
	 * @author WJF on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesBySelectData")
	public LayuiDataTemplet<ApplyCheckAndOrderTableAndUser> selectThreeTablesBySelectData(@RequestBody ApplyCheckAndOrderTableAndUser unionData) {
		LayuiDataTemplet<ApplyCheckAndOrderTableAndUser> returnData = new LayuiDataTemplet<ApplyCheckAndOrderTableAndUser>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (unionData.getVersion() != null && !unionData.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(unionData.getVersion(), Config.VERSION);
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

		// 分页数据
		// 使用limit分页查询，根据偏移量查询
		// 第一个参数为第一个返回记录行的偏移量，第二个参数为返回记录行的最大数目
		// MySQL > SELECT * FROM table LIMIT 5, 10; // 查询第6-15行数据
		if (unionData.getPagenumber() != null) {
			// 计算偏移量
			if (unionData.getPagenumber() != -1) {
				if (unionData.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = unionData.getPagenumber();
				int pageSize = unionData.getPagesize();
				unionData.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				unionData.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = applyCheckService.selectThreeTablesCountBySelectData(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<ApplyCheckAndOrderTableAndUser> applyCheckList = applyCheckService.selectThreeTablesBySelectData(unionData); // 查询数据
				returnData.setData(applyCheckList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param applyCheck
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<ApplyCheck> deleteByIdList(@RequestBody ApplyCheck applyCheck) {
		LayuiDataTemplet<ApplyCheck> returnData = new LayuiDataTemplet<ApplyCheck>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (applyCheck.getVersion() != null && !applyCheck.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(applyCheck.getVersion(), Config.VERSION);
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

		// 删除
		int count = 0;
		count = applyCheckService.deleteByIdList(applyCheck.getIdlist());

		// 返回数据
		if (count == 0) {
			returnData.setMsg("删除失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("删除成功！");
		}

		return returnData;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
   	 * 
	 * @param applyCheck
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/deleteByOrderIdList")
	public LayuiDataTemplet<ApplyCheck> deleteByOrderIdList(@RequestBody ApplyCheck applyCheck) {
		LayuiDataTemplet<ApplyCheck> returnData = new LayuiDataTemplet<ApplyCheck>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (applyCheck.getVersion() != null && !applyCheck.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(applyCheck.getVersion(), Config.VERSION);
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

		// 删除
		int count = 0;
		count = applyCheckService.deleteByOrderIdList(applyCheck.getUuidlist());

		// 返回数据
		if (count == 0) {
			returnData.setMsg("删除失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("删除成功！");
		}

		return returnData;
	}
	
}
