package sun.bz1.restful;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.LayuiDataTemplet;
import sun.bz1.entity.*;
import sun.bz1.service.*;
import util.Config;
import util.VersionCompare;
import util.WechatFormUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户付款表
 * 
 * Restful
 * 
 * @author ZY on 2018/10/20
 */

@Controller
@RequestMapping("/userPayment")
public class F_UserPaymentRestful {

	@Autowired
	private UserPaymentService userPaymentService;

	@Autowired
	private WechatFormService wechatFormService;

	@Autowired
	private UserService userService;

	@Autowired
	private WechatService wechatService;

	@Autowired
	private OrderTableService orderTableService;

	private Logger logger = Logger.getLogger(F_OperationOrderRestful.class);

	/**
	 * 根据UserPayment实体添加
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	@ResponseBody
	@RequestMapping("/insertByUserPayment")
	public LayuiDataTemplet<UserPayment> insertByUserPayment(@RequestBody UserPayment userPayment) {
		LayuiDataTemplet<UserPayment> returnData = new LayuiDataTemplet<UserPayment>();
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不能为空，验证版本号
		try {
			if (null != userPayment.getVersion() && !userPayment.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(userPayment.getVersion(), Config.VERSION);
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
		userPayment.setUserpaymentid(Config.SIGN_USERPAYMENT + UUID.randomUUID().toString()); // 用户付款ID(YHFK+UUID)
		userPayment.setUserpaymentcreateuserid(userPayment.getRemitteruserid()); // 创建人ID
		userPayment.setUserpaymentcreatetime(new Date()); // 用户付款创建时间
		userPayment.setUserpaymentupdateuserid(userPayment.getRemitteruserid());//用户付款更新人
		userPayment.setUserpaymentupdatetime(new Date());//更新时间

		// 添加
		int count = 0;
		count = userPaymentService.insertByUserPayment(userPayment);

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
	 * 根据UserPayment实体更新
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	@ResponseBody
	@RequestMapping("/updateByUserPayment")
	public LayuiDataTemplet<UserPayment> updateByUserPayment(@RequestBody UserPayment userPayment) {
		LayuiDataTemplet<UserPayment> returnData = new LayuiDataTemplet<UserPayment>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (userPayment.getVersion() != null && !userPayment.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(userPayment.getVersion(), Config.VERSION);
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
		if (userPayment.getId() == null || userPayment.getId() == 0) {
			if (userPayment.getUserpaymentid() == null || userPayment.getUserpaymentid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		userPayment.setUserpaymentupdateuserid(userPayment.getUserpaymentupdateuserid());//更新人、确认收款时是平台人员
		userPayment.setUserpaymentupdatetime(new Date()); // 更新时间

		// 更新
		int count = 0;
		count = userPaymentService.updateByUserPayment(userPayment);

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
	 * 根据UserPayment实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	@ResponseBody
	@RequestMapping("/selectByUserPayment")
	public LayuiDataTemplet<UserPayment> selectByUserPayment(@RequestBody UserPayment userPayment) {
		LayuiDataTemplet<UserPayment> returnData = new LayuiDataTemplet<UserPayment>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (userPayment.getVersion() != null && !userPayment.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(userPayment.getVersion(), Config.VERSION);
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
		if (userPayment.getPagenumber() != null) {
			// 计算偏移量
			if (userPayment.getPagenumber() != -1) {
				if (userPayment.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = userPayment.getPagenumber();
				int pageSize = userPayment.getPagesize();
				userPayment.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				userPayment.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = userPaymentService.selectCountByUserPayment(userPayment);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<UserPayment> userPaymentList = userPaymentService.selectByUserPayment(userPayment); // 查询数据
				returnData.setData(userPaymentList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据UserPayment实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<UserPayment> selectBySelectData(@RequestBody UserPayment userPayment) {
		LayuiDataTemplet<UserPayment> returnData = new LayuiDataTemplet<UserPayment>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (userPayment.getVersion() != null && !userPayment.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(userPayment.getVersion(), Config.VERSION);
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
		if (userPayment.getPagenumber() != null) {
			// 计算偏移量
			if (userPayment.getPagenumber() != -1) {
				if (userPayment.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = userPayment.getPagenumber();
				int pageSize = userPayment.getPagesize();
				userPayment.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				userPayment.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = userPaymentService.selectCountBySelectData(userPayment); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<UserPayment> userPaymentList = userPaymentService.selectBySelectData(userPayment); // 查询数据
				returnData.setData(userPaymentList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<UserPayment> deleteByIdList(@RequestBody UserPayment userPayment) {
		LayuiDataTemplet<UserPayment> returnData = new LayuiDataTemplet<UserPayment>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (userPayment.getVersion() != null && !userPayment.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(userPayment.getVersion(), Config.VERSION);
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
		count = userPaymentService.deleteByIdList(userPayment.getIdlist());

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
	 * 根据UserPayment实体添加
     *
     * 线下交易客户对平台对公转账
	 *
	 * @param userPayment
	 * @return
	 *
	 * @author ZY on 2018/10/20
	 */
	@ResponseBody
	@RequestMapping("/insertByUserPaymentAndUpdateOrderAndUpdateMoney")
	public LayuiDataTemplet<UserPayment> insertByUserPaymentAndUpdateOrderAndUpdateMoney(@RequestBody UserPayment userPayment) {
		LayuiDataTemplet<UserPayment> returnData = new LayuiDataTemplet<UserPayment>();
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不能为空，验证版本号
		try {
			if (null != userPayment.getVersion() && !userPayment.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(userPayment.getVersion(), Config.VERSION);
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

		// 添加用户付款表数据
		userPayment.setUserpaymentid(Config.SIGN_USERPAYMENT + UUID.randomUUID().toString()); // 用户付款ID(YHFK+UUID)
		userPayment.setUserpaymentcreateuserid(userPayment.getRemitteruserid()); // 创建人ID
		userPayment.setUserpaymentcreatetime(new Date()); // 用户付款创建时间
		userPayment.setUserpaymentupdateuserid(userPayment.getRemitteruserid());//用户付款更新人
		userPayment.setUserpaymentupdatetime(new Date());//更新时间
		userPayment.setUserpaymentother1("DGZH");// 线下付款(对公账户)：DGZH,线上付款：XSFK
        //针对人付款人和订单编号查询客户是否已付款
        UserPayment userPayment1 = new UserPayment();
        userPayment1.setPagenumber(-1);//不分页
        userPayment1.setOrderid(userPayment.getOrderid());//订单编号
        userPayment1.setRemitteruserid(userPayment.getRemitteruserid());//付款人
        List<UserPayment> userPaymentList = null;
        userPaymentList = userPaymentService.selectByUserPayment(userPayment1);
		if (userPaymentList.size() > 0) {
			Date createTime1 = userPaymentList.get(0).getUserpaymentcreatetime();
			Date nowTime1 = new Date();
			long cha1 = nowTime1.getTime() - createTime1.getTime();
			if (cha1 / (1000 * 60 * 60) > 1) {
				returnData.setMsg("一个小时内不能重复操作！");
				return returnData;
			}
		}

		//更新订单表状态
		OrderTable orderTable = new OrderTable();
		orderTable.setOrderid(userPayment.getOrderid());//订单id
		orderTable.setOrderstate("YFK");//修改订单状态线下付款客户给平台付款
		orderTable.setOrderupdatetime(new Date());//更新时间

		//添加用户_客户_收入_金额表
		UserCustomerIncomeMoney customerIncomeMoney = new UserCustomerIncomeMoney();
		// 数据
		customerIncomeMoney.setUsercustomerincomemoneyid(Config.SIGN_USERCUSTOMERINCOMEMONEY + UUID.randomUUID().toString()); // 客户收入金额ID(KHSR+UUID)
		customerIncomeMoney.setUsercustomerincomemoneyuserid(userPayment.getRemitteruserid()); // 客户ID(YHBG+UUID)
		customerIncomeMoney.setOrderid(userPayment.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		customerIncomeMoney.setUsercustomerincomemoneysource("DGZH"); // 金额来源(WXTH:维修人员退回(客户收入金额)/QRQXKH:确认取消(客户收入金额)/GHWX:更换维修人员(客户收入金额)/WTPD:委托平台派单(客户收入金额)/PTXTKH:平台协调(客户收入金额)/KHCZ:客户充值(客户收入金额))
		customerIncomeMoney.setUsercustomerincomemoney(-userPayment.getOrderpricetotalmoney()); // 金额(可正可负)
		customerIncomeMoney.setUsercustomerincomemoneycreatetime(new Date()); // 金额创建时间*/

		// 添加
		int count = 0;
		count = userPaymentService.insertByUserPaymentAndUpdateOrderAndUpdateMoney(userPayment, orderTable, customerIncomeMoney);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("更新订单状态失败！");
		} else {
			OrderTable selectOrderTable = new OrderTable();
			selectOrderTable.setOrderid(orderTable.getOrderid());
			selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<OrderTable> orderList = null;
			orderList = orderTableService.selectByOrderTable(selectOrderTable);
			//维修师傅的服务通知
			// 查询数据
			UserAndUserServiceAndUserCustomer selectData = new UserAndUserServiceAndUserCustomer();
			selectData.setUserid(orderList.get(0).getOrderserviceuserid()); // 查询
			selectData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			// 查询数据
			List<UserAndUserServiceAndUserCustomer> userList = userService.selectThreeTablesBySelectData(selectData);
			if (userList.size() != 0){
				for (int i = 0; i < userList.size(); i ++){
					WechatForm wechatForm = new WechatForm();
					wechatForm.setPagenumber(-1);//不分页
					wechatForm.setUserid(userList.get(i).getUserid());
					List<WechatForm> wechatFormList = wechatFormService.selectByWechatForm(wechatForm); // 查询数据
					if (wechatFormList.size() != 0) {
						String openId = "";
						String templateId = "";
						String formId = "";
						for (int j = 0; j < wechatFormList.size(); j ++) {
							Date createTime = wechatFormList.get(j).getCreatedate();
							Date nowTime = new Date();
							long cha = nowTime.getTime() - createTime.getTime();
							if (cha / (1000 * 60 * 60 * 24) > 6){
								List<Integer> deleteList = new ArrayList<Integer>();
								deleteList.add(wechatFormList.get(j).getId());
								wechatFormList.remove(j);
								wechatFormService.deleteByIdList(deleteList);
							}
						}
						openId = wechatFormList.get(0).getOpenid();
						templateId = "MTizY8yuXuaqZvdWXsdgpR4MgG6d-2bLMTqVepgADYk";
						formId = wechatFormList.get(0).getFormid();

						SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String token = F_WechatRestful.getAccessToken(wechatService);
						//String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
						String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", "客户已向平台付款：" + userPayment.getOrderpricetotalmoney() + "元", orderList.get(0).getOrderid(), orderList.get(0).getOrderprojectname(), "");
						boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
						List<Integer> idList = new ArrayList<Integer>();
						idList.add(wechatFormList.get(0).getId());
						wechatFormService.deleteByIdList(idList);

						logger.error("服务通知result" + result);
						System.out.println("服务通知result" + result);

					}
				}
			}

			//通知客户付款的服务通知
			// 查询数据
			UserAndUserServiceAndUserCustomer selectDataPay = new UserAndUserServiceAndUserCustomer();
			selectDataPay.setUserid(orderList.get(0).getOrdercreateuserid()); // 查询
			selectDataPay.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			// 查询数据
			List<UserAndUserServiceAndUserCustomer> userPayList = userService.selectThreeTablesBySelectData(selectDataPay);
			if (userPayList.size() != 0){
				for (int i = 0; i < userPayList.size(); i ++){
					WechatForm wechatForm = new WechatForm();
					wechatForm.setPagenumber(-1);//不分页
					wechatForm.setUserid(userPayList.get(i).getUserid());
					List<WechatForm> wechatFormList = wechatFormService.selectByWechatForm(wechatForm); // 查询数据
					if (wechatFormList.size() != 0) {
						String openId = "";
						String templateId = "";
						String formId = "";
						for (int j = 0; j < wechatFormList.size(); j ++) {
							Date createTime = wechatFormList.get(j).getCreatedate();
							Date nowTime = new Date();
							long cha = nowTime.getTime() - createTime.getTime();
							if (cha / (1000 * 60 * 60 * 24) > 6){
								List<Integer> deleteList = new ArrayList<Integer>();
								deleteList.add(wechatFormList.get(j).getId());
								wechatFormList.remove(j);
								wechatFormService.deleteByIdList(deleteList);
							}
						}
						openId = wechatFormList.get(0).getOpenid();
						templateId = "MTizY8yuXuaqZvdWXsdgpR4MgG6d-2bLMTqVepgADYk";
						formId = wechatFormList.get(0).getFormid();

						SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String token = F_WechatRestful.getAccessToken(wechatService);
						//String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
						String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", "已向平台付款：" + (orderList.get(0).getOrderpricetotalmoney() + orderList.get(0).getOrdertraveltotalmoney()) + "元", orderList.get(0).getOrderid(), orderList.get(0).getOrderprojectname(), "");
						boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
						List<Integer> idList = new ArrayList<Integer>();
						idList.add(wechatFormList.get(0).getId());
						wechatFormService.deleteByIdList(idList);

						logger.error("服务通知result" + result);
						System.out.println("服务通知result" + result);

					}
				}
			}
			returnData.setCount(count);
			returnData.setMsg("更新订单状态成功！");
		}

		return returnData;
	}

	/**
	 * 根据UserPaymentAndOrderTableAndUser实体联表查询
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
	 * @author ZY on 2018/10/22
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionData")
	public LayuiDataTemplet<UserPaymentAndOrderTableAndUser> selectThreeTablesByUnionData(@RequestBody UserPaymentAndOrderTableAndUser unionData) {
		LayuiDataTemplet<UserPaymentAndOrderTableAndUser> returnData = new LayuiDataTemplet<UserPaymentAndOrderTableAndUser>(); // 返回数据
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
			count = userPaymentService.selectThreeTablesCountByUnionData(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<UserPaymentAndOrderTableAndUser> userList = userPaymentService.selectThreeTablesByUnionData(unionData); // 查询数据
				returnData.setData(userList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据UserPayment实体添加
	 *
	 * 同时添加客户的收入记录表
	 *
	 * @param userPayment
	 * @return
	 *
	 * @author ZY on 2018/10/20
	 */
	@ResponseBody
	@RequestMapping("/insertByUserPaymentAndUpdateMoney")
	public LayuiDataTemplet<UserPayment> insertByUserPaymentAndUpdateMoney(@RequestBody UserPayment userPayment) {
		LayuiDataTemplet<UserPayment> returnData = new LayuiDataTemplet<UserPayment>();
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不能为空，验证版本号
		try {
			if (null != userPayment.getVersion() && !userPayment.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(userPayment.getVersion(), Config.VERSION);
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
		userPayment.setUserpaymentid(Config.SIGN_USERPAYMENT + UUID.randomUUID().toString()); // 用户付款ID(YHFK+UUID)
		userPayment.setUserpaymentcreateuserid(userPayment.getRemitteruserid()); // 创建人ID
		userPayment.setUserpaymentcreatetime(new Date()); // 用户付款创建时间
		userPayment.setUserpaymentupdateuserid(userPayment.getRemitteruserid());//用户付款更新人
		userPayment.setUserpaymentupdatetime(new Date());//更新时间
		userPayment.setUserpaymentother1("DGZH");// 线下付款(对公账户)：DGZH,线上付款：XSFK

		//添加用户_客户_收入_金额表
		UserCustomerIncomeMoney customerIncomeMoney = new UserCustomerIncomeMoney();
		// 数据
		customerIncomeMoney.setUsercustomerincomemoneyid(Config.SIGN_USERCUSTOMERINCOMEMONEY + UUID.randomUUID().toString()); // 客户收入金额ID(KHSR+UUID)
		customerIncomeMoney.setUsercustomerincomemoneyuserid(userPayment.getRemitteruserid()); // 客户ID(YHBG+UUID)
		customerIncomeMoney.setOrderid(userPayment.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		customerIncomeMoney.setUsercustomerincomemoneysource("DGZH"); // 金额来源(WXTH:维修人员退回(客户收入金额)/QRQXKH:确认取消(客户收入金额)/GHWX:更换维修人员(客户收入金额)/WTPD:委托平台派单(客户收入金额)/PTXTKH:平台协调(客户收入金额)/KHCZ:客户充值(客户收入金额))
		customerIncomeMoney.setUsercustomerincomemoney(-userPayment.getOrderpricetotalmoney()); // 金额(可正可负)
		customerIncomeMoney.setUsercustomerincomemoneycreatetime(new Date()); // 金额创建时间*/

		// 添加
		int count = 0;
		count = userPaymentService.insertByUserPaymentAndUpdateMoney(userPayment, customerIncomeMoney);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("添加失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("添加成功！");
		}

		return returnData;
	}

}
