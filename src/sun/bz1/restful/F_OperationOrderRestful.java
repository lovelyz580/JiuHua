package sun.bz1.restful;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.*;
import sun.bz1.service.*;
import util.*;

/**
 * 维修单操作
 * 
 * 维修单保存、平台派单、发布维修信息、维修工、安装队竞标、客户确认竞标
 * 
 * Restful
 * 
 * @author WJF on 2018/09/12
 */

@Controller
@RequestMapping("/operation_order")
public class F_OperationOrderRestful {
	
	@Autowired
	private OperationOrderService operationService;
	
	@Autowired
	private OrderTableService orderTableService;

	@Autowired
	private OrderDetailRecordService orderDetailRecordService;
	
//	@Autowired
//	private AreaService areaService;
	
	@Autowired
	private ScaleService scaleService;
	
	@Autowired
	private InterceptTravelService interceptTravelService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CreditMinSetupService setupService;
	
	@Autowired
	private ReturnTableService returnTableService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private SnatchService snatchService;
	
	@Autowired
	private BuildingTypeService buildingTypeService;

	@Autowired
	private WechatFormService wechatFormService;

	@Autowired
	private WechatService wechatService;

    @Autowired
    private DistributeConfirmService distributeConfirmService;

	@Autowired
	private OrderDetailConfirmService orderDetailConfirmService;

	@Autowired
	private OrderPriceService orderPriceService;

	@Autowired
	private InterceptService interceptService;
	// 格式化时间
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	private Logger logger = Logger.getLogger(F_OperationOrderRestful.class);
	/**
	 * 维修单保存
	 * 
	 * 操作过程：
	 * 1、添加维修单表
	 * 2、添加维修单详情表
	 * 
	 * 传递OrderTable实体
	 * 传递OrderDetailListEntity实体(List<OrderDetail>)
	 * 
	 * 需在OrderTable实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 * 
	 * @param models
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/saveOrder")
	public LayuiDataTemplet<OrderTable> saveOrder(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null
		
		// JSON转换为实体类
		OrderTable orderTable = null;
		OrderDetailListEntity orderDetailListEntity = null;
		try {
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
			orderDetailListEntity = JsonUtil.json2obj((String)models.get("orderDetailListEntity"), OrderDetailListEntity.class);
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
		// OrderDetailListEntity实体(List<OrderDetail>)
		List<OrderDetail> orderDetailList = null;
		if (orderDetailListEntity == null) {
			returnData.setMsg("传递的维修单详情数据错误！");
			return returnData;
		} else {
			orderDetailList = orderDetailListEntity.getOrderDetailList();
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
			returnData.setMsg("当前操作人ID为空，无法派单！");
			return returnData;
		}

		// 1、添加维修单表
		// 数据
		// 维修单ID(D+年月日+时分秒+6位随机数)
		// 生成6位随机数
		String random = String.valueOf((Math.random() * 1000000)).substring(0, 6);
		if (random.indexOf(".") != -1) {
			random = random.replace(".", "0"); // 将随机数中的.转换为0
		}
		String orderId = "D" + sdf.format(new Date()) + random; // 维修单ID(D+年月日+时分秒+6位随机数)

		//把自己定义的某个时间增加一段时间
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = orderTable.getOrderbiddingendtime();//获取竞标截止日期
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 8);//增加8个小时到 上午8点截止
		Timestamp ts = new Timestamp(calendar.getTimeInMillis());
		orderTable.setOrderbiddingendtime(ts);//竞标截止日期加8个小时到上午8点---上班时间

		orderTable.setOrderid(orderId); // 维修单ID(D+年月日+时分秒+6位随机数)
		orderTable.setOrderstate("BJ");//维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		orderTable.setOrdercreateuserid(currentUserId); // 当前操作人员ID // 维修单创建人ID(YHBG+UUID)
		orderTable.setOrdercreatetime(new Date()); // 维修单创建时间
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修单更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修单更新时间

		// 2、添加维修单详情表
		// 数据
		for (int i = 0; i < orderDetailList.size(); i++) {
			OrderDetail orderDetail = orderDetailList.get(i);
			orderDetail.setOrderdetailid(Config.SIGN_ORDERDETAIL + UUID.randomUUID().toString()); // 维修单详情ID(WXXQ+UUID)
			orderDetail.setOrderid(orderId); // 维修单ID(D+年月日+时分秒+6位随机数)
		}

		// 保存
		int count = 0;
		count = operationService.saveOrder(orderTable, orderDetailList);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("保存失败！");
		} else {
			// 返回数据List
			List<OrderTable> orderTableList = new ArrayList<OrderTable>();
			orderTableList.add(orderTable);

			returnData.setCount(count);
			returnData.setData(orderTableList);
			returnData.setMsg("保存成功！");
		}

		return returnData;
	}

	/**
	 * 维修单编辑和保存
	 *
	 * 操作过程：
	 * 1、更新维修单表
	 * 2、查询订单详情表
	 * 3、添加订单详情记录表并删除订单详情表中的数据
	 * 4、添加维修单详情表
	 *
	 * 传递OrderTable实体
	 * 传递OrderDetailListEntity实体(List<OrderDetail>)
	 *
	 * 需在OrderTable实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param models
	 * @return
	 *
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/saveAndUpdateOrder")
	public LayuiDataTemplet<OrderTable> saveAndUpdateOrder(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// JSON转换为实体类
		OrderTable orderTable = null;
		OrderDetailListEntity orderDetailListEntity = null;
		try {
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
			orderDetailListEntity = JsonUtil.json2obj((String)models.get("orderDetailListEntity"), OrderDetailListEntity.class);
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
		// OrderDetailListEntity实体(List<OrderDetail>)
		List<OrderDetail> orderDetailList = null;
		if (orderDetailListEntity == null) {
			returnData.setMsg("传递的维修单详情数据错误！");
			return returnData;
		} else {
			orderDetailList = orderDetailListEntity.getOrderDetailList();
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
			returnData.setMsg("当前操作人ID为空，无法派单！");
			return returnData;
		}

		// 1、更新维修单表
		// 数据
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修单更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修单更新时间

		//2、查询订单详情表
		OrderDetail orderDetailSelect = new OrderDetail();
		orderDetailSelect.setOrderid(orderTable.getOrderid());//订单ID
		List<OrderDetail> orderDetails = orderDetailService.selectByOrderDetail(orderDetailSelect);//查询结果

		//3、添加订单详情记录表并删除订单详情表中的数据
		for (int j = 0; j < orderDetails.size(); j ++) {
			OrderDetailRecord orderDetailRecord = new OrderDetailRecord();
			orderDetailRecord.setOrderdetailrecordid(Config.SIGN_ORDERDETAILRECORD + UUID.randomUUID().toString()); // 维修单详情记录ID(WXXQ+UUID)
			orderDetailRecord.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			orderDetailRecord.setGoodsid(orderDetails.get(j).getGoodsid());//维修清单项目ID
			orderDetailRecord.setProjectid(orderDetails.get(j).getProjectid());//维修清单ID
			orderDetailRecord.setOrderdetailnumber(orderDetails.get(j).getOrderdetailnumber());//维修数量
			orderDetailRecord.setOrderdetailintercepttotalmoney(orderDetails.get(j).getOrderdetailintercepttotalmoney());//维修推荐总价
			orderDetailRecord.setOrderdetailrecordcreatetime(new Date());//创建时间
			//添加数据
			orderDetailRecordService.insertByOrderDetailRecord(orderDetailRecord);
			//删除数据
			orderDetailService.deleteByOrDetail(orderDetails.get(j));
		}
		// 4、添加维修单详情表
		// 数据
		for (int i = 0; i < orderDetailList.size(); i++) {
			OrderDetail orderDetail = orderDetailList.get(i);
			orderDetail.setOrderdetailid(Config.SIGN_ORDERDETAIL + UUID.randomUUID().toString()); // 维修单详情ID(WXXQ+UUID)
			orderDetail.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		}

		// 保存
		int count = 0;
		count = operationService.saveAndUpdateOrder(orderTable, orderDetailList);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("更新失败！");
		} else {
			// 返回数据List
			List<OrderTable> orderTableList = new ArrayList<OrderTable>();
			orderTableList.add(orderTable);

			returnData.setCount(count);
			returnData.setData(orderTableList);
			returnData.setMsg("更新成功！");
		}

		return returnData;
	}

	/**
	 * 平台派单
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 *
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费推荐总价 收入金额)
	 *
	 * 5、更新用户表(客户余额)
	 * 6、添加用户_客户_支出_金额表
	 * 7、添加平台_收益金额表
	 *
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/backDistribution")
	public LayuiDataTemplet<OrderTable> backDistribution(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable>  returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

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
			returnData.setMsg("当前操作人ID为空，无法派单！");
			return returnData;
		}

		//付款方式
		String paymentMethod = orderTable.getOrderpaymentmethodcontent();

		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法派单！");
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

		// 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
		String serviceUserId = ""; // 维修人员ID(YHBG+UUID)
		double orderPriceTotalMoney = 0; // 维修总价(根据维修单价计算得出)
		double distance = 0; // 距离
		double orderTravelTotalMoney = 0; // 维修差旅费总价(根据差旅费(每公里)*距离计算得出)

// 		// (1).根据orderTable实体中的维修地点，获取对应的接单区域ID
//		String areaId;
//		Area area = new Area();
//		area.setAreaname(orderTable.getOrderaddress());
//		area.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
//		List<Area> areaList = new ArrayList<Area>();
//		areaList = areaService.selectByArea(area); // 查询
//		if (areaList.size() == 0) {
//			returnData.setMsg("找不到该接单区域，无法派单！");
//			return returnData;
//		} else {
//			areaId = areaList.get(0).getAreaid();
//		}

		// (2).根据orderTable实体中的维修推荐总价，获取对应的接单规模ID
		String scaleId = null;
		Scale selectScale = new Scale();
		selectScale.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<Scale> scaleList = new ArrayList<Scale>();
		scaleList = scaleService.selectByScale(selectScale); // 查询
		if (scaleList.size() == 0) {
			returnData.setMsg("接单规模暂无数据，无法派单！");
			return returnData;
		} else {
			for (int i = 0; i < scaleList.size(); i++) {
				Scale scale = scaleList.get(i);

				// 接单规模金额初始值(小数值) == 维修推荐总价
				if (Double.doubleToLongBits(scale.getScalemin()) ==
						Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney())) {
					scaleId = scale.getScaleid();
					break;
				}

				// 接单规模金额结束值(大数值) == 维修推荐总价
				if (Double.doubleToLongBits(scale.getScalemax()) ==
						Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney())) {
					scaleId = scale.getScaleid();
					break;
				}

				// 接单规模金额初始值(小数值) < 维修推荐总价，并且接单规模金额结束值(大数值) > 维修推荐总价
				if (Double.doubleToLongBits(scale.getScalemin()) < Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney()) &&
						Double.doubleToLongBits(scale.getScalemax()) > Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney())) {
					scaleId = scale.getScaleid();
					break;
				}
			}

			if (scaleId == null) {
				returnData.setMsg("该规模没有对应的维修工或安装队，无法派单！");
				return returnData;
			}
		}

		// (3).根据orderTable实体中的建筑类型ID，获取对应的建筑类型系数
		double buildingtypecoefficient = 0; // 建筑类型系数
		BuildingType buildingType = new BuildingType();
		buildingType.setBuildingtypeid(orderTable.getBuildingtypeid()); // 建筑类型ID(JZLX+UUID)
		buildingType.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<BuildingType> buildingList = new ArrayList<BuildingType>();
		buildingList = buildingTypeService.selectByBuildingType(buildingType); // 查询
		if (buildingList.size() == 0) {
			returnData.setMsg("找不到该建筑类型，无法派单！");
			return returnData;
		} else {
			buildingtypecoefficient = buildingList.get(0).getBuildingtypecoefficient();
		}

		// (4).根据接单状态、接单区域、接单规模，匹配维修工、安装队
		UserAndUserServiceAndUserCustomer selectUser = new UserAndUserServiceAndUserCustomer();
		selectUser.setAreacode(orderTable.getOrderposition() + ","); // 腾讯地图上的城市代码(会有多个，以逗号分隔，以逗号结尾)
		selectUser.setScaleid(scaleId); // 接单规模ID(JDGM+UUID)
		selectUser.setUserservicestate("Y"); // 接单状态为接单
		selectUser.setUserservicerole(orderTable.getOrderprojecttype()); // 用户接单类别(WX:维修工/AZ:安装队)
		selectUser.setUserrole(orderTable.getOrderprojecttype() + ","); // 用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
		selectUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		//获取订单详情中数据的项目清单
		String goodsId = "";
		OrderDetail orderDetail1 = new OrderDetail();
		orderDetail1.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList1 = new ArrayList<OrderDetail>();
		orderDetailList1 = orderDetailService.selectByOrderDetail(orderDetail1); // 查询
		if (orderDetailList1.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据！");
			return returnData;
		} else{
			List<String> list = new ArrayList<String>();//创建新的list
			for (int i = 0; i < orderDetailList1.size(); i++){
				String str = orderDetailList1.get(i).getGoodsid();
				if(!list.contains(str)){   //查看新集合中是否有指定的元素，如果没有则加入
					list.add(str);
				}
			}
			for (int j = 0; j < list.size(); j++){
				goodsId = goodsId + list.get(j) + ",";
			}
		}
		selectUser.setGoodid(goodsId); // 项目清单ID

		List<UserAndUserServiceAndUserCustomer> serviceUserList = new ArrayList<UserAndUserServiceAndUserCustomer>();
		serviceUserList = userService.selectBackDistributionServiceUser(selectUser); // 查询
		if (serviceUserList.size() == 0) {
			returnData.setMsg("该维修单没有匹配的维修工或安装队，无法派单！");
			return returnData;
		}

		// (5).根据匹配出来的维修工、安装队，判断该人员的信用值是否大于最低信用值
		List<UserAndUserServiceAndUserCustomer> selectCreditServiceUserList = new ArrayList<UserAndUserServiceAndUserCustomer>();
		// 查询最低信用值
		double creditMin = -9999;
		creditMin = setupService.selectBySetup(new CreditMinSetup()).get(0).getCreditmin();
		if (Double.doubleToLongBits(creditMin) == -9999) {
			returnData.setMsg("最低信用值暂无数据，无法派单！");
			return returnData;
		} else {
			// 判断
			for (int i = 0; i < serviceUserList.size(); i++) {
				UserAndUserServiceAndUserCustomer user = serviceUserList.get(i);
				if (Double.doubleToLongBits(user.getUsercredit()) >= Double.doubleToLongBits(creditMin)) {
					// 信用值 >= 最低信用值
					selectCreditServiceUserList.add(user); // 为List添加数据
				}
			}

			if (selectCreditServiceUserList.size() == 0) {
				returnData.setMsg("该维修单匹配的维修工或安装队的信用值都过低，无法派单！");
				return returnData;
			}
		}

		// (6).根据匹配出来的维修工、安装队，判断该人员是否退回过该维修单，如果退回过，则不再给该人员派此单
		ReturnTableAndOrderTableAndUser returnTable = new ReturnTableAndOrderTableAndUser();
		returnTable.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		returnTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<ReturnTableAndOrderTableAndUser> returnTableList = null;
		returnTableList = returnTableService.selectThreeTablesBySelectData(returnTable);
		if (returnTableList != null  && returnTableList.size() != 0) {
			// 循环退回单List
			for (int i = 0; i < returnTableList.size(); i++) {
				// 循环人员List
				for (int j = 0; j < selectCreditServiceUserList.size(); j++) {
					if (returnTableList.get(i).getReturnuserid().equals(selectCreditServiceUserList.get(j).getUserid())) {
						selectCreditServiceUserList.remove(i);
						break;
					}
				}
			}
		}
		//判断派单确认表中是否有维修安装取消的人
        DistributeConfirm selectDistributeConfirmUserId = new DistributeConfirm();
        selectDistributeConfirmUserId.setOrderid(orderTable.getOrderid());//订单编号
        selectDistributeConfirmUserId.setPagenumber(-1);//不分页
        selectDistributeConfirmUserId.setDistributeconfirmstate("SQX");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
        List<DistributeConfirm> selectDistributeConfirmList = null;
        selectDistributeConfirmList = distributeConfirmService.selectByDistributeConfirm(selectDistributeConfirmUserId);
        if (selectDistributeConfirmList != null  && selectDistributeConfirmList.size() != 0) {
            // 循环派单确认List
            for (int i = 0; i < selectDistributeConfirmList.size(); i++) {
                // 循环人员List
                for (int j = 0; j < selectCreditServiceUserList.size(); j++) {
                    if (selectDistributeConfirmList.get(i).getDistributeconfirmserviceuserid().equals(selectCreditServiceUserList.get(j).getUserid())) {
                        selectCreditServiceUserList.remove(i);
                        break;
                    }
                }
            }
        }
        //判断派单确认表中是否有客户取消的人
        DistributeConfirm selectDistributeConfirmCQX = new DistributeConfirm();
        selectDistributeConfirmCQX.setOrderid(orderTable.getOrderid());//订单编号
        selectDistributeConfirmCQX.setPagenumber(-1);//不分页
        selectDistributeConfirmCQX.setDistributeconfirmstate("CQX");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
        List<DistributeConfirm> selectDistributeConfirmCQXList = null;
        selectDistributeConfirmCQXList = distributeConfirmService.selectByDistributeConfirm(selectDistributeConfirmCQX);
        if (selectDistributeConfirmCQXList != null  && selectDistributeConfirmCQXList.size() != 0) {
            // 循环派单确认List
            for (int i = 0; i < selectDistributeConfirmCQXList.size(); i++) {
                // 循环人员List
                for (int j = 0; j < selectCreditServiceUserList.size(); j++) {
                    if (selectDistributeConfirmCQXList.get(i).getDistributeconfirmserviceuserid().equals(selectCreditServiceUserList.get(j).getUserid())) {
                        selectCreditServiceUserList.remove(i);
                        break;
                    }
                }
            }
        }

		if (serviceUserList.size() == 0) {
			returnData.setMsg("该维修单没有匹配的维修工或安装队，无法派单！");
			return returnData;
		}

		// (7).根据匹配出来的不同的维修工、安装队的差旅费(每公里)和距离，计算维修差旅费总价(差旅费(每公里)*距离)
		// 循环人员List
		for (int i = 0; i < selectCreditServiceUserList.size(); i++) {
			double travelTotalMoney = 0; // 维修差旅费总价

			// 距离(单位：米)
			double userDistance = PositionUtil.getDistance(orderTable.getOrderlatitude(), orderTable.getOrderlongitude(),
					selectCreditServiceUserList.get(i).getUserlatitude(), selectCreditServiceUserList.get(i).getUserlongitude());

			// 距离*差旅费(每公里)
			if (selectCreditServiceUserList.get(i).getUserservicetravelmoney() != null) {
				travelTotalMoney = userDistance / 1000 * selectCreditServiceUserList.get(i).getUserservicetravelmoney();
			}

			// 为人员添加数据
			selectCreditServiceUserList.get(i).setDistance(userDistance); // 距离
			selectCreditServiceUserList.get(i).setTraveltotalmoney(travelTotalMoney); // 维修差旅费总价
		}

        List<UserAndUserServiceAndUserCustomer> ServiceUserList2 = new ArrayList<UserAndUserServiceAndUserCustomer>();
		// (8).根据匹配出来的不同的维修工、安装队的维修单价，计算维修总价
		// 维修总价 = 维修人员报价 * 报价系数(报价系数在下方)
		// 根据OrderTable实体中的维修单ID，获取维修单详情中的产品ID、项目ID
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
		if (orderDetailList.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据，无法派单！");
			return returnData;
		} else {
			// 根据人员、产品ID、项目ID，计算维修总价
			List<UserAndUserServiceAndUserCustomer> finalServiceUserList = new ArrayList<UserAndUserServiceAndUserCustomer>();

			// 循环人员List，循环维修单详情List，根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价，循环计算维修单价，得出维修总价
			// 循环人员List
			for (int i = 0; i < selectCreditServiceUserList.size(); i++) {
				double orderPriceTotalMoneyNocredit = 0;//维修总价不用信用值系数算出来的，派单的价格
				double priceTotalMoney = 0; // 维修总价
				double totalMoney = 0; // 总价(维修总价+维修差旅费总价)

				// 循环维修单详情List
				for (int j = 0; j < orderDetailList.size(); j++) {
					// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
					PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
					price.setPriceupdateuserid(selectCreditServiceUserList.get(i).getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
					price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
					price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
					price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
					price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
					price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

					List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
					priceList = priceService.selectFourTablesByUnionData(price); // 查询
					if (priceList.size() == 0) {
						// 某一款产品ID、项目ID，该人员没有对应的维修单价
						priceTotalMoney = 0;
						break;
					} else {
						// 循环计算维修单价，得出维修总价
						priceTotalMoney += priceList.get(0).getPricemoney() * orderDetailList.get(j).getOrderdetailnumber();

					}
				}

				// 如果维修总价不为0，则计算最终维修总价，并为最终维修人员List添加数据
				if (priceTotalMoney != 0) {

					// * 建筑系数
					orderPriceTotalMoneyNocredit = priceTotalMoney * buildingtypecoefficient;
					/**
					 * 维修总价 = 维修人员报价 * 报价系数 * 建筑系数
					 *
					 * 信用值在90分以上：报价系数为90%
					 * 信用值在80-89分：报价系数为95%
					 * 信用值在70-79分：报价系数为100%
					 * 信用值在60-69分：报价系数为105%
					 * 信用值在40-59分：报价系数为110%
					 */
					double credit = selectCreditServiceUserList.get(i).getUsercredit(); // 维修工、安装队人员所对应的信用值

					// * 报价系数
					if (Double.doubleToLongBits(credit) >= 90) {
						priceTotalMoney = priceTotalMoney * 0.9;
					} else if (Double.doubleToLongBits(credit) >= 80 && Double.doubleToLongBits(credit) < 90) {
						priceTotalMoney = priceTotalMoney * 0.95;
					} else if (Double.doubleToLongBits(credit) >= 70 && Double.doubleToLongBits(credit) < 80) {
						priceTotalMoney = priceTotalMoney * 1;
					} else if (Double.doubleToLongBits(credit) >= 60 && Double.doubleToLongBits(credit) < 70) {
						priceTotalMoney = priceTotalMoney * 1.05;
					} else if (Double.doubleToLongBits(credit) >= 40 && Double.doubleToLongBits(credit) < 60) {
						priceTotalMoney = priceTotalMoney * 1.1;
					} else {
						priceTotalMoney = priceTotalMoney * 1.2;
					}

					// 四舍五入，保存两位小数
					BigDecimal bg = new BigDecimal(priceTotalMoney).setScale(2, RoundingMode.UP);
					priceTotalMoney = bg.doubleValue();

					// * 建筑系数
					priceTotalMoney = priceTotalMoney * buildingtypecoefficient;

					// 总价(维修总价+维修差旅费总价)
					totalMoney = priceTotalMoney + selectCreditServiceUserList.get(i).getTraveltotalmoney();

					// 为人员添加数据
					selectCreditServiceUserList.get(i).setPricetotalmoney(priceTotalMoney); // 维修总价
					selectCreditServiceUserList.get(i).setTotalmoney(totalMoney); // 总价(维修总价+维修差旅费总价)
					selectCreditServiceUserList.get(i).setOrderPriceTotalMoneyNocredit(orderPriceTotalMoneyNocredit);//维修总价不包含信用值系数

					finalServiceUserList.add(selectCreditServiceUserList.get(i)); // 为List添加数据
				}
			}

			if (finalServiceUserList.size() == 0) {
				returnData.setMsg("该维修单的某些维修详情数据维修工或安装队没有维修总价，无法派单！");
				return returnData;
			}

			// (9).查询总价最低的作为维修人员
			Collections.sort(finalServiceUserList); // 按照总价从低到高排序

			serviceUserId = finalServiceUserList.get(0).getUserid(); // 维修人员ID(YHBG+UUID)
			//orderPriceTotalMoney = finalServiceUserList.get(0).getPricetotalmoney(); // 维修总价(根据维修单价计算得出)
			orderPriceTotalMoney = finalServiceUserList.get(0).getOrderPriceTotalMoneyNocredit();; // 维修总价(根据维修单价计算得出 不包含根据信用值系数计算)
			distance= finalServiceUserList.get(0).getDistance(); // 距离
			orderTravelTotalMoney = finalServiceUserList.get(0).getTraveltotalmoney(); // 维修差旅费总价(根据差旅费*距离计算得出)

            //发送服务通知时用到
            ServiceUserList2.add(finalServiceUserList.get(0));
		}

//		// 添加维修单分配记录表
//		Distribution distribution = new Distribution();
//		// 数据
//		distribution.setDistributionid(Config.SIGN_DISTRIBUTION + UUID.randomUUID().toString()); // 分配记录ID(FPJL+UUID)
//		distribution.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
//		distribution.setServiceuserid(serviceUserId); // 被分配人员ID(维修人员)(YHBG+UUID)
//		distribution.setDistributionuserid(currentUserId); // 当前操作人员ID // 分配人员ID(YHBG+UUID)
//		distribution.setDistributiontime(new Date()); // 分配时间

		// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		double interceptTravelTotalMoney = 0; // 维修差旅费推荐总价
		InterceptTravel selectInterceptTravel = new InterceptTravel();
		selectInterceptTravel.setIntercepttraveltype(orderTable.getOrdertype()); // 差旅费(每公里)拦标价类型(WX:维修工/AZ:安装队)
		selectInterceptTravel.setIntercepttravelstate("Y"); // 差旅费(每公里)拦标价状态(Y:有效/N:无效)
		List<InterceptTravel> interceptTravelList = null;
		interceptTravelList = interceptTravelService.selectByInterceptTravel(selectInterceptTravel);
		if (interceptTravelList != null && interceptTravelList.size() != 0) {
			interceptTravelTotalMoney = interceptTravelList.get(0).getIntercepttravelmoney() * distance;
		}

		//3、添加派单确认表
        DistributeConfirm distributeConfirm = new DistributeConfirm();
        distributeConfirm.setDistributeconfirmid(Config.SIGN_DISTRIBUTECONFIRM + UUID.randomUUID().toString());//派单记录ID
        distributeConfirm.setOrderid(orderTable.getOrderid());//订单编号
        distributeConfirm.setDistributeconfirmserviceuserid(serviceUserId);//派单的维修人员userid
        distributeConfirm.setDistributeconfirmorderdistance(distance);//距离
        distributeConfirm.setDistributeconfirmorderintercepttotalmoney(orderTable.getOrderintercepttotalmoney());//下单时推荐总价
        distributeConfirm.setDistributeconfirmorderpricetotalmoney(orderPriceTotalMoney);//派单人添加单价的维修总价
        distributeConfirm.setDistributeconfirmordertraveltotalmoney(orderTravelTotalMoney);//派单人的差率费总价
        distributeConfirm.setDistributeconfirmother4(interceptTravelTotalMoney);// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
        distributeConfirm.setDistributeconfirmcreateuserid(orderTable.getOrdercreateuserid());//派单确认创建人
        distributeConfirm.setDistributeconfirmcreatetime(new Date());//创建时间
        distributeConfirm.setDistributeconfirmstate("N");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
        distributeConfirm.setDistributeconfirmupdateuserid(orderTable.getOrdercreateuserid());//更新人
        distributeConfirm.setDistributeconfirmupdatetime(new Date());//更新时间

        //查询是否已经加入派单确认表
        DistributeConfirm selectDistributeConfirm = new DistributeConfirm();
        selectDistributeConfirm.setPagenumber(-1);//不分页
        selectDistributeConfirm.setOrderid(orderTable.getOrderid());//订单
        selectDistributeConfirm.setDistributeconfirmserviceuserid(serviceUserId);//派单的维修人员userid

        List<DistributeConfirm> distributeConfirmList = null;
        distributeConfirmList = distributeConfirmService.selectByDistributeConfirm(selectDistributeConfirm);//查询
        if (distributeConfirmList.size() > 0) {
            returnData.setMsg("该订单正在等待确认，请勿重新派单！");
            return returnData;
        }

        //4、更新维修单表状态
        orderTable.setOrdertype("PD"); // 维修类型(PD:派单/QD:抢单)
        orderTable.setOrderstate("PDQR");//派单正在确认的状态
        orderTable.setOrderserviceuserid(serviceUserId); // 维修人员ID(YHBG+UUID)
        orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderpaymentmethodcontent(paymentMethod);//付款方式
        orderTable.setOrderupdatetime(new Date()); // 维修更新时间

        //4、添加维修任务表
        Task task = new Task();
        // 数据
        task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
        task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
        task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)
		/*// 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
		// 数据
		orderTable.setOrdertype("PD"); // 维修类型(PD:派单/QD:抢单)
		orderTable.setOrderstate("JS"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		orderTable.setOrderserviceuserid(serviceUserId); // 维修人员ID(YHBG+UUID)
		orderTable.setOrderdistance(distance); // 维修距离
		orderTable.setOrderintercepttraveltotalmoney(interceptTravelTotalMoney); // 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		orderTable.setOrderpricetotalmoney(orderPriceTotalMoney); // 维修总价(根据维修单价计算得出)
		orderTable.setOrdertraveltotalmoney(orderTravelTotalMoney); // 维修差旅费总价(根据差旅费*距离计算得出)
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间

		// 4、添加维修任务表
		Task task = new Task();
		// 数据
		task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
		task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)

		// 5、添加维修单对应维修单价表
		List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
		// 循环维修单详情List
		for (int j = 0; j < orderDetailList.size(); j++) {
			// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
			PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
			price.setPriceupdateuserid(serviceUserId); // 维修单价维护人(维修人员)ID(YHBG+UUID)
			price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
			price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
			price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
			price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
			price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
			priceList = priceService.selectFourTablesByUnionData(price); // 查询
			for (int i = 0; i < priceList.size(); i++) {
				OrderPrice orderPrice = new OrderPrice();
				orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
				orderPrice.setOrderpriceupdateuserid(serviceUserId); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
				orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
				orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
				orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
				orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
				orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
				orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
				orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
				orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
				orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注

				orderPriceList.add(orderPrice);
			}
		}*/

		// 金额数据：客户支出(平台按 维修推荐总价+维修差旅费总价 收入金额)

		// 客户需要支出的金额 = 维修推荐总价 + 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		double expenditureMoney = orderTable.getOrderintercepttotalmoney() + interceptTravelTotalMoney;

		/*// 6、更新用户表(客户余额)
		User customerUser = new User();
		// 查询
		UserAndUserServiceAndUserCustomer selectCustomerUser = new UserAndUserServiceAndUserCustomer();
		selectCustomerUser.setUserid(orderTable.getOrdercreateuserid()); // 客户ID(YHBG+UUID)
		selectCustomerUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> customerUserList = null;
		customerUserList = userService.selectThreeTablesByUnionData(selectCustomerUser); // 查询
		if (customerUserList == null || customerUserList.size() == 0) {
			returnData.setMsg("找不到该客户，无法派单！");
			return returnData;
		} else {
			// 数据
			customerUser.setUserid(customerUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			customerUser.setUsermoney(customerUserList.get(0).getUsermoney() - expenditureMoney); // 用户余额
			customerUser.setUserupdateuserid(currentUserId); // 当前操作人员ID // 用户更新人ID(YHBG+UUID)
			customerUser.setUserupdatetime(new Date()); // 用户更新时间

//			if (customerUser.getUsermoney() < 0) {
//				returnData.setMsg("该客户当前余额无法支付本次维修，无法派单！");
//				return returnData;
//			}
		}*/

		/*// 7、添加用户_客户_支出_金额表
		UserCustomerExpenditureMoney customerExpenditureMoney = new UserCustomerExpenditureMoney();
		customerExpenditureMoney.setUsercustomerexpendituremoneyid(Config.SIGN_USERCUSTOMEREXPENDITUREMONEY + UUID.randomUUID().toString()); // 客户支出金额ID(KHZC+UUID)
		customerExpenditureMoney.setUsercustomerexpendituremoneyuserid(customerUser.getUserid()); // 金额用户(YHBG+UUID)
		customerExpenditureMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		customerExpenditureMoney.setUsercustomerexpendituremoneysource("PTPD"); // 金额来源(PTPD:平台派单(客户按维修推荐总价+维修差旅费推荐总价支出金额)/KHTX:客户提现(客户支出金额))
		customerExpenditureMoney.setUsercustomerexpendituremoney(-expenditureMoney); // 金额(可正可负)
		customerExpenditureMoney.setUsercustomerexpendituremoneycreatetime(new Date()); // 金额创建时间*/

		/*// 8、添加平台_收益金额表
		BackMoney backMoney = new BackMoney();
		// 数据
		backMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 平台金额ID(PTJE+UUID)
		backMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		backMoney.setBackmoney(expenditureMoney); // 金额(可正可负) // 维修推荐总价(下单时，对应的推荐总价)
		backMoney.setBackmoneyother1("PTPD"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户))
		backMoney.setBackmoneycreatetime(new Date()); // 金额创建时间*/

		// 平台派单
		int count = 0;
		//count = operationService.backDistribution(orderTable, task, orderPriceList, customerUser, customerExpenditureMoney, backMoney);
		//count = operationService.backDistribution(orderTable, task, orderPriceList);
        count = operationService.insertDistributeAndUpdateOrder(distributeConfirm, orderTable, task);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("平台派单失败！");
		} else {

            // 查询数据
            UserAndUserServiceAndUserCustomer selectData = new UserAndUserServiceAndUserCustomer();
            selectData.setUserid(orderTable.getOrdercreateuserid()); // 查询用户
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
                        templateId = "UIMqKoW_cRYMlO6Zs9IfHANwvJOs2WVO90poC9ZOlGM";
                        formId = wechatFormList.get(0).getFormid();

                        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String token = F_WechatRestful.getAccessToken(wechatService);
                        //String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
                        String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", ServiceUserList2.get(0).getUserrealname(), orderTable.getOrderprojectname(), (orderTable.getOrderpricetotalmoney() + orderTable.getOrdertraveltotalmoney()) + "", dateFormate.format(orderTable.getOrderupdatetime()));
                        boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
                        List<Integer> idList = new ArrayList<Integer>();
                        idList.add(wechatFormList.get(0).getId());
                        wechatFormService.deleteByIdList(idList);

                        logger.error("服务通知result" + result);
                        System.out.println("服务通知result" + result);

                    }
                }
            }


            //发通知给维修人员
            // 查询数据
            UserAndUserServiceAndUserCustomer selectServiceData = new UserAndUserServiceAndUserCustomer();
            selectServiceData.setUserid(orderTable.getOrderserviceuserid()); // 查询
            selectServiceData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

            // 查询数据
            List<UserAndUserServiceAndUserCustomer> userServiceList = userService.selectThreeTablesBySelectData(selectServiceData);
            if (userServiceList.size() != 0){
                for (int i = 0; i < userServiceList.size(); i ++){
                    WechatForm wechatForm = new WechatForm();
                    wechatForm.setPagenumber(-1);//不分页
                    wechatForm.setUserid(userServiceList.get(i).getUserid());
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
                        templateId = "kT2OTxraicVvL3VmwzO7pQmTo1gDx7XXnYxSpeRONro";
                        formId = wechatFormList.get(0).getFormid();

                        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String token = F_WechatRestful.getAccessToken(wechatService);
                        //String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
                        String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", "您有一单任务！", orderTable.getOrdercontactperson(), dateFormate.format(orderTable.getOrderplantime()), "");
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
			returnData.setMsg("平台派单成功！");
		}

		return returnData;
	}


	/**
	 * 平台派单最新的派单
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 *
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费推荐总价 收入金额)
	 *
	 * 5、更新用户表(客户余额)
	 * 6、添加用户_客户_支出_金额表
	 * 7、添加平台_收益金额表
	 *
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2019/01/19
	 */
	@ResponseBody
	@RequestMapping("/backDistributionNew")
	public LayuiDataTemplet<OrderTable> backDistributionNew(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable>  returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

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
			returnData.setMsg("当前操作人ID为空，无法派单！");
			return returnData;
		}
		//付款方式
		String paymentMethod = orderTable.getOrderpaymentmethodcontent();

		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法派单！");
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

		// 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
		String serviceUserId = ""; // 维修人员ID(YHBG+UUID)
		double orderPriceTotalMoney = 0; // 维修总价(根据维修单价计算得出)
		double distance = 0; // 距离
		double orderTravelTotalMoney = 0; // 维修差旅费总价(根据差旅费(每公里)*距离计算得出)

// 		// (1).根据orderTable实体中的维修地点，获取对应的接单区域ID
//		String areaId;
//		Area area = new Area();
//		area.setAreaname(orderTable.getOrderaddress());
//		area.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
//		List<Area> areaList = new ArrayList<Area>();
//		areaList = areaService.selectByArea(area); // 查询
//		if (areaList.size() == 0) {
//			returnData.setMsg("找不到该接单区域，无法派单！");
//			return returnData;
//		} else {
//			areaId = areaList.get(0).getAreaid();
//		}

		// (2).根据orderTable实体中的维修推荐总价，获取对应的接单规模ID
		String scaleId = null;
		Scale selectScale = new Scale();
		selectScale.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<Scale> scaleList = new ArrayList<Scale>();
		scaleList = scaleService.selectByScale(selectScale); // 查询
		if (scaleList.size() == 0) {
			returnData.setMsg("接单规模暂无数据，无法派单！");
			return returnData;
		} else {
			for (int i = 0; i < scaleList.size(); i++) {
				Scale scale = scaleList.get(i);

				// 接单规模金额初始值(小数值) == 维修推荐总价
				if (Double.doubleToLongBits(scale.getScalemin()) ==
						Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney())) {
					scaleId = scale.getScaleid();
					break;
				}

				// 接单规模金额结束值(大数值) == 维修推荐总价
				if (Double.doubleToLongBits(scale.getScalemax()) ==
						Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney())) {
					scaleId = scale.getScaleid();
					break;
				}

				// 接单规模金额初始值(小数值) < 维修推荐总价，并且接单规模金额结束值(大数值) > 维修推荐总价
				if (Double.doubleToLongBits(scale.getScalemin()) < Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney()) &&
						Double.doubleToLongBits(scale.getScalemax()) > Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney())) {
					scaleId = scale.getScaleid();
					break;
				}
			}

			if (scaleId == null) {
				returnData.setMsg("该规模没有对应的维修工或安装队，无法派单！");
				return returnData;
			}
		}

		// (3).根据orderTable实体中的建筑类型ID，获取对应的建筑类型系数
		double buildingtypecoefficient = 0; // 建筑类型系数
		BuildingType buildingType = new BuildingType();
		buildingType.setBuildingtypeid(orderTable.getBuildingtypeid()); // 建筑类型ID(JZLX+UUID)
		buildingType.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<BuildingType> buildingList = new ArrayList<BuildingType>();
		buildingList = buildingTypeService.selectByBuildingType(buildingType); // 查询
		if (buildingList.size() == 0) {
			returnData.setMsg("找不到该建筑类型，无法派单！");
			return returnData;
		} else {
			buildingtypecoefficient = buildingList.get(0).getBuildingtypecoefficient();
		}

		// (4).根据接单状态、接单区域、接单规模，匹配维修工、安装队
		UserAndUserServiceAndUserCustomer selectUser = new UserAndUserServiceAndUserCustomer();
		selectUser.setAreacode(orderTable.getOrderposition() + ","); // 腾讯地图上的城市代码(会有多个，以逗号分隔，以逗号结尾)
		selectUser.setScaleid(scaleId); // 接单规模ID(JDGM+UUID)
		selectUser.setUserservicestate("Y"); // 接单状态为接单
		selectUser.setUseryearteam("Y");//是年度安装队
		selectUser.setUserservicerole(orderTable.getOrderprojecttype()); // 用户接单类别(WX:维修工/AZ:安装队)
		selectUser.setUserrole(orderTable.getOrderprojecttype() + ","); // 用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
		selectUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		//获取订单详情中数据的项目清单
		String goodsId = "";
		OrderDetail orderDetail1 = new OrderDetail();
		orderDetail1.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList1 = new ArrayList<OrderDetail>();
		orderDetailList1 = orderDetailService.selectByOrderDetail(orderDetail1); // 查询
		if (orderDetailList1.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据！");
			return returnData;
		} else{
			List<String> list = new ArrayList<String>();//创建新的list
			for (int i = 0; i < orderDetailList1.size(); i++){
				String str = orderDetailList1.get(i).getGoodsid();
				if(!list.contains(str)){   //查看新集合中是否有指定的元素，如果没有则加入
					list.add(str);
				}
			}
			for (int j = 0; j < list.size(); j++){
				goodsId = goodsId + list.get(j) + ",";
			}
		}
		selectUser.setGoodid(goodsId); // 项目清单ID

		List<UserAndUserServiceAndUserCustomer> serviceUserList = new ArrayList<UserAndUserServiceAndUserCustomer>();
		serviceUserList = userService.selectBackDistributionServiceUser(selectUser); // 查询
		if (serviceUserList.size() == 0) {
			returnData.setMsg("系统正在为您指派施工队伍！");
			return returnData;
		}

		// (5).根据匹配出来的维修工、安装队，判断该人员的信用值是否大于最低信用值
		List<UserAndUserServiceAndUserCustomer> selectCreditServiceUserList = new ArrayList<UserAndUserServiceAndUserCustomer>();
		// 查询最低信用值
		double creditMin = -9999;
		creditMin = setupService.selectBySetup(new CreditMinSetup()).get(0).getCreditmin();
		if (Double.doubleToLongBits(creditMin) == -9999) {
			returnData.setMsg("最低信用值暂无数据，无法派单！");
			return returnData;
		} else {
			// 判断
			for (int i = 0; i < serviceUserList.size(); i++) {
				UserAndUserServiceAndUserCustomer user = serviceUserList.get(i);
				if (Double.doubleToLongBits(user.getUsercredit()) >= Double.doubleToLongBits(creditMin)) {
					// 信用值 >= 最低信用值
					selectCreditServiceUserList.add(user); // 为List添加数据
				}
			}

			if (selectCreditServiceUserList.size() == 0) {
				returnData.setMsg("该维修单匹配的维修工或安装队的信用值都过低，无法派单！");
				return returnData;
			}
		}
		if(orderTable.getOrderprojecttype().equals("AZ")) {
			//判断接单量是否大于的安装队伍填的接单量
			for (int j = 0; j < selectCreditServiceUserList.size(); j++) {
				OrderTableAndBuildingType orderTableAndBuildingType = new OrderTableAndBuildingType();
				orderTableAndBuildingType.setOrderserviceuserid(selectCreditServiceUserList.get(j).getUserid());//用户ID
				orderTableAndBuildingType.setPagenumber(-1);//不分页
				List<OrderTableAndBuildingTypeAndOrderDetailList> orderAllList = orderTableService.selectThreeTablesByUnionData(orderTableAndBuildingType); // 查询数据
				int userYearNumber = 0;
				if (orderAllList.size() > 0) {
					for (int i = 0; i < orderAllList.size(); i++) {
						if (orderAllList.get(i).getOrderDetailList() != null) {
							List<OrderDetail> orderAllDetailList = orderAllList.get(i).getOrderDetailList();
							for (int k = 0; k < orderAllDetailList.size(); k++) {
								userYearNumber = userYearNumber + orderAllDetailList.get(k).getOrderdetailnumber();
							}
						}

					}
				}
				if (null != selectCreditServiceUserList.get(j).getUseryearnumber()){
					if (userYearNumber >= selectCreditServiceUserList.get(j).getUseryearnumber()) {
						selectCreditServiceUserList.remove(j);
						break;
					}
				}

			}
			//如果所有的年度安装队都完成了年工作量就重新查找安装队
			if (selectCreditServiceUserList.size() == 0) {

				serviceUserList = userService.selectBackDistributionServiceUser(selectUser); // 查询
				if (serviceUserList.size() == 0) {
					returnData.setMsg("系统正在为您指派施工队伍！");
					return returnData;
				}

				// .根据匹配出来的维修工、安装队，判断该人员的信用值是否大于最低信用值
				// 查询最低信用值
				if (Double.doubleToLongBits(creditMin) == -9999) {
					returnData.setMsg("最低信用值暂无数据，无法派单！");
					return returnData;
				} else {
					// 判断
					for (int i = 0; i < serviceUserList.size(); i++) {
						UserAndUserServiceAndUserCustomer user = serviceUserList.get(i);
						if (Double.doubleToLongBits(user.getUsercredit()) >= Double.doubleToLongBits(creditMin)) {
							// 信用值 >= 最低信用值
							selectCreditServiceUserList.add(user); // 为List添加数据
						}
					}
				}
			}
		}


		// (6).根据匹配出来的维修工、安装队，判断该人员是否退回过该维修单，如果退回过，则不再给该人员派此单
		ReturnTableAndOrderTableAndUser returnTable = new ReturnTableAndOrderTableAndUser();
		returnTable.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		returnTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<ReturnTableAndOrderTableAndUser> returnTableList = null;
		returnTableList = returnTableService.selectThreeTablesBySelectData(returnTable);
		if (returnTableList != null  && returnTableList.size() != 0) {
			// 循环退回单List
			for (int i = 0; i < returnTableList.size(); i++) {
				// 循环人员List
				for (int j = 0; j < selectCreditServiceUserList.size(); j++) {
					if (returnTableList.get(i).getReturnuserid().equals(selectCreditServiceUserList.get(j).getUserid())) {
						selectCreditServiceUserList.remove(i);
						break;
					}
				}
			}
		}
		//判断派单确认表中是否有维修安装取消的人
		DistributeConfirm selectDistributeConfirmUserId = new DistributeConfirm();
		selectDistributeConfirmUserId.setOrderid(orderTable.getOrderid());//订单编号
		selectDistributeConfirmUserId.setPagenumber(-1);//不分页
		selectDistributeConfirmUserId.setDistributeconfirmstate("SQX");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
		List<DistributeConfirm> selectDistributeConfirmList = null;
		selectDistributeConfirmList = distributeConfirmService.selectByDistributeConfirm(selectDistributeConfirmUserId);
		if (selectDistributeConfirmList != null  && selectDistributeConfirmList.size() != 0) {
			// 循环派单确认List
			for (int i = 0; i < selectDistributeConfirmList.size(); i++) {
				// 循环人员List
				for (int j = 0; j < selectCreditServiceUserList.size(); j++) {
					if (selectDistributeConfirmList.get(i).getDistributeconfirmserviceuserid().equals(selectCreditServiceUserList.get(j).getUserid())) {
						selectCreditServiceUserList.remove(i);
						break;
					}
				}
			}
		}
		//判断派单确认表中是否有客户取消的人
		DistributeConfirm selectDistributeConfirmCQX = new DistributeConfirm();
		selectDistributeConfirmCQX.setOrderid(orderTable.getOrderid());//订单编号
		selectDistributeConfirmCQX.setPagenumber(-1);//不分页
		selectDistributeConfirmCQX.setDistributeconfirmstate("CQX");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
		List<DistributeConfirm> selectDistributeConfirmCQXList = null;
		selectDistributeConfirmCQXList = distributeConfirmService.selectByDistributeConfirm(selectDistributeConfirmCQX);
		if (selectDistributeConfirmCQXList != null  && selectDistributeConfirmCQXList.size() != 0) {
			// 循环派单确认List
			for (int i = 0; i < selectDistributeConfirmCQXList.size(); i++) {
				// 循环人员List
				for (int j = 0; j < selectCreditServiceUserList.size(); j++) {
					if (selectDistributeConfirmCQXList.get(i).getDistributeconfirmserviceuserid().equals(selectCreditServiceUserList.get(j).getUserid())) {
						selectCreditServiceUserList.remove(i);
						break;
					}
				}
			}
		}

		if (serviceUserList.size() == 0) {
			returnData.setMsg("系统正在为您指派施工队伍！");
			return returnData;
		}

		// (7).根据匹配出来的不同的维修工、安装队的差旅费(每公里)和距离，计算维修差旅费总价(差旅费(每公里)*距离)
		// 循环人员List
		for (int i = 0; i < selectCreditServiceUserList.size(); i++) {
			double travelTotalMoney = 0; // 维修差旅费总价

			// 距离(单位：米)
			double userDistance = PositionUtil.getDistance(orderTable.getOrderlatitude(), orderTable.getOrderlongitude(),
					selectCreditServiceUserList.get(i).getUserlatitude(), selectCreditServiceUserList.get(i).getUserlongitude());

			// 距离*差旅费(每公里)
			if (selectCreditServiceUserList.get(i).getUserservicetravelmoney() != null) {
				travelTotalMoney = userDistance / 1000 * selectCreditServiceUserList.get(i).getUserservicetravelmoney();
			}

			// 为人员添加数据
			selectCreditServiceUserList.get(i).setDistance(userDistance); // 距离
			selectCreditServiceUserList.get(i).setTraveltotalmoney(travelTotalMoney); // 维修差旅费总价
		}

		List<UserAndUserServiceAndUserCustomer> ServiceUserList2 = new ArrayList<UserAndUserServiceAndUserCustomer>();
		// (8).根据匹配出来的不同的维修工、安装队的维修单价，计算维修总价
		// 维修总价 = 维修人员报价 * 报价系数(报价系数在下方)
		// 根据OrderTable实体中的维修单ID，获取维修单详情中的产品ID、项目ID
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
		if (orderDetailList.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据，无法派单！");
			return returnData;
		} else {
			// 根据人员、产品ID、项目ID，计算维修总价
			List<UserAndUserServiceAndUserCustomer> finalServiceUserList = new ArrayList<UserAndUserServiceAndUserCustomer>();

			// 循环人员List，循环维修单详情List，根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价，循环计算维修单价，得出维修总价
			// 循环人员List
			for (int i = 0; i < selectCreditServiceUserList.size(); i++) {
				double orderPriceTotalMoneyNocredit = 0;//维修总价不用信用值系数算出来的，派单的价格
				double priceTotalMoney = 0; // 维修总价
				double totalMoney = 0; // 总价(维修总价+维修差旅费总价)

				// 循环维修单详情List
				for (int j = 0; j < orderDetailList.size(); j++) {
					// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
					PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
					price.setPriceupdateuserid(selectCreditServiceUserList.get(i).getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
					price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
					price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
					price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
					price.setPriceareacode(orderTable.getOrderposition());//维修区域代码
					price.setPricebuildingtypeid(orderTable.getBuildingtypeid());//维修建筑类型
					price.setPricepropertycompanyid(orderTable.getOrderpropertycompanyid());//建筑公司ID
					price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
					price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

					List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
					priceList = priceService.selectFourTablesByUnionData(price); // 查询
					if (priceList.size() == 0) {
						// 某一款产品ID、项目ID，该人员没有对应的维修单价
						priceTotalMoney = 0;
						break;
					} else {
						// 循环计算维修单价，得出维修总价
						priceTotalMoney += priceList.get(0).getPricemoney() * orderDetailList.get(j).getOrderdetailnumber();

					}
				}

				// 如果维修总价不为0，则计算最终维修总价，并为最终维修人员List添加数据
				if (priceTotalMoney != 0) {

					// * 建筑系数
					orderPriceTotalMoneyNocredit = priceTotalMoney * buildingtypecoefficient;
					/**
					 * 维修总价 = 维修人员报价 * 报价系数 * 建筑系数
					 *
					 * 信用值在90分以上：报价系数为90%
					 * 信用值在80-89分：报价系数为95%
					 * 信用值在70-79分：报价系数为100%
					 * 信用值在60-69分：报价系数为105%
					 * 信用值在40-59分：报价系数为110%
					 */
					double credit = selectCreditServiceUserList.get(i).getUsercredit(); // 维修工、安装队人员所对应的信用值

					// * 报价系数
					if (Double.doubleToLongBits(credit) >= 90) {
						priceTotalMoney = priceTotalMoney * 0.9;
					} else if (Double.doubleToLongBits(credit) >= 80 && Double.doubleToLongBits(credit) < 90) {
						priceTotalMoney = priceTotalMoney * 0.95;
					} else if (Double.doubleToLongBits(credit) >= 70 && Double.doubleToLongBits(credit) < 80) {
						priceTotalMoney = priceTotalMoney * 1;
					} else if (Double.doubleToLongBits(credit) >= 60 && Double.doubleToLongBits(credit) < 70) {
						priceTotalMoney = priceTotalMoney * 1.05;
					} else if (Double.doubleToLongBits(credit) >= 40 && Double.doubleToLongBits(credit) < 60) {
						priceTotalMoney = priceTotalMoney * 1.1;
					} else {
						priceTotalMoney = priceTotalMoney * 1.2;
					}

					// 四舍五入，保存两位小数
					BigDecimal bg = new BigDecimal(priceTotalMoney).setScale(2, RoundingMode.UP);
					priceTotalMoney = bg.doubleValue();

					// * 建筑系数
					priceTotalMoney = priceTotalMoney * buildingtypecoefficient;

					// 总价(维修总价+维修差旅费总价)
					totalMoney = priceTotalMoney + selectCreditServiceUserList.get(i).getTraveltotalmoney();

					// 为人员添加数据
					selectCreditServiceUserList.get(i).setPricetotalmoney(priceTotalMoney); // 维修总价
					selectCreditServiceUserList.get(i).setTotalmoney(totalMoney); // 总价(维修总价+维修差旅费总价)
					selectCreditServiceUserList.get(i).setOrderPriceTotalMoneyNocredit(orderPriceTotalMoneyNocredit);//维修总价不包含信用值系数

					finalServiceUserList.add(selectCreditServiceUserList.get(i)); // 为List添加数据
				}
			}

			if (finalServiceUserList.size() == 0) {
				returnData.setMsg("该维修单的某些维修详情数据维修师傅或安装队伍没有维修总价，无法派单！");
				return returnData;
			}

			// (9).查询总价最低的作为维修人员
			Collections.sort(finalServiceUserList); // 按照总价从低到高排序

			serviceUserId = finalServiceUserList.get(0).getUserid(); // 维修人员ID(YHBG+UUID)
			//orderPriceTotalMoney = finalServiceUserList.get(0).getPricetotalmoney(); // 维修总价(根据维修单价计算得出)
			orderPriceTotalMoney = finalServiceUserList.get(0).getOrderPriceTotalMoneyNocredit();; // 维修总价(根据维修单价计算得出 不包含根据信用值系数计算)
			distance= finalServiceUserList.get(0).getDistance(); // 距离
			orderTravelTotalMoney = finalServiceUserList.get(0).getTraveltotalmoney(); // 维修差旅费总价(根据差旅费*距离计算得出)

			//发送服务通知时用到
			ServiceUserList2.add(finalServiceUserList.get(0));
		}

//		// 添加维修单分配记录表
//		Distribution distribution = new Distribution();
//		// 数据
//		distribution.setDistributionid(Config.SIGN_DISTRIBUTION + UUID.randomUUID().toString()); // 分配记录ID(FPJL+UUID)
//		distribution.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
//		distribution.setServiceuserid(serviceUserId); // 被分配人员ID(维修人员)(YHBG+UUID)
//		distribution.setDistributionuserid(currentUserId); // 当前操作人员ID // 分配人员ID(YHBG+UUID)
//		distribution.setDistributiontime(new Date()); // 分配时间

		// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		double interceptTravelTotalMoney = 0; // 维修差旅费推荐总价
		InterceptTravel selectInterceptTravel = new InterceptTravel();
		selectInterceptTravel.setIntercepttraveltype(orderTable.getOrdertype()); // 差旅费(每公里)拦标价类型(WX:维修工/AZ:安装队)
		selectInterceptTravel.setIntercepttravelstate("Y"); // 差旅费(每公里)拦标价状态(Y:有效/N:无效)
		List<InterceptTravel> interceptTravelList = null;
		interceptTravelList = interceptTravelService.selectByInterceptTravel(selectInterceptTravel);
		if (interceptTravelList != null && interceptTravelList.size() != 0) {
			interceptTravelTotalMoney = interceptTravelList.get(0).getIntercepttravelmoney() * distance;
		}

		//3、添加派单确认表
		DistributeConfirm distributeConfirm = new DistributeConfirm();
		distributeConfirm.setDistributeconfirmid(Config.SIGN_DISTRIBUTECONFIRM + UUID.randomUUID().toString());//派单记录ID
		distributeConfirm.setOrderid(orderTable.getOrderid());//订单编号
		distributeConfirm.setDistributeconfirmserviceuserid(serviceUserId);//派单的维修人员userid
		distributeConfirm.setDistributeconfirmorderdistance(distance);//距离
		distributeConfirm.setDistributeconfirmorderintercepttotalmoney(orderTable.getOrderintercepttotalmoney());//下单时推荐总价
		distributeConfirm.setDistributeconfirmorderpricetotalmoney(orderPriceTotalMoney);//派单人添加单价的维修总价
		distributeConfirm.setDistributeconfirmordertraveltotalmoney(orderTravelTotalMoney);//派单人的差率费总价
		distributeConfirm.setDistributeconfirmother4(interceptTravelTotalMoney);// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		distributeConfirm.setDistributeconfirmcreateuserid(orderTable.getOrdercreateuserid());//派单确认创建人
		distributeConfirm.setDistributeconfirmcreatetime(new Date());//创建时间
		distributeConfirm.setDistributeconfirmstate("N");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
		distributeConfirm.setDistributeconfirmupdateuserid(orderTable.getOrdercreateuserid());//更新人
		distributeConfirm.setDistributeconfirmupdatetime(new Date());//更新时间

		//查询是否已经加入派单确认表
		DistributeConfirm selectDistributeConfirm = new DistributeConfirm();
		selectDistributeConfirm.setPagenumber(-1);//不分页
		selectDistributeConfirm.setOrderid(orderTable.getOrderid());//订单
		selectDistributeConfirm.setDistributeconfirmserviceuserid(serviceUserId);//派单的维修人员userid

		List<DistributeConfirm> distributeConfirmList = null;
		distributeConfirmList = distributeConfirmService.selectByDistributeConfirm(selectDistributeConfirm);//查询
		if (distributeConfirmList.size() > 0) {
			returnData.setMsg("该订单正在等待确认，请勿重新派单！");
			return returnData;
		}

		//4、更新维修单表状态
		orderTable.setOrdertype("PD"); // 维修类型(PD:派单/QD:抢单)
		orderTable.setOrderstate("PDQR");//派单正在确认的状态
		orderTable.setOrderserviceuserid(serviceUserId); // 维修人员ID(YHBG+UUID)
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderpaymentmethodcontent(paymentMethod);//付款方式
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间

		//4、添加维修任务表
		Task task = new Task();
		// 数据
		task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
		task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)
		/*// 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
		// 数据
		orderTable.setOrdertype("PD"); // 维修类型(PD:派单/QD:抢单)
		orderTable.setOrderstate("JS"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		orderTable.setOrderserviceuserid(serviceUserId); // 维修人员ID(YHBG+UUID)
		orderTable.setOrderdistance(distance); // 维修距离
		orderTable.setOrderintercepttraveltotalmoney(interceptTravelTotalMoney); // 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		orderTable.setOrderpricetotalmoney(orderPriceTotalMoney); // 维修总价(根据维修单价计算得出)
		orderTable.setOrdertraveltotalmoney(orderTravelTotalMoney); // 维修差旅费总价(根据差旅费*距离计算得出)
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间

		// 4、添加维修任务表
		Task task = new Task();
		// 数据
		task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
		task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)

		// 5、添加维修单对应维修单价表
		List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
		// 循环维修单详情List
		for (int j = 0; j < orderDetailList.size(); j++) {
			// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
			PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
			price.setPriceupdateuserid(serviceUserId); // 维修单价维护人(维修人员)ID(YHBG+UUID)
			price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
			price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
			price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
			price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
			price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
			priceList = priceService.selectFourTablesByUnionData(price); // 查询
			for (int i = 0; i < priceList.size(); i++) {
				OrderPrice orderPrice = new OrderPrice();
				orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
				orderPrice.setOrderpriceupdateuserid(serviceUserId); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
				orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
				orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
				orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
				orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
				orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
				orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
				orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
				orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
				orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注

				orderPriceList.add(orderPrice);
			}
		}*/

		// 金额数据：客户支出(平台按 维修推荐总价+维修差旅费总价 收入金额)

		// 客户需要支出的金额 = 维修推荐总价 + 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		double expenditureMoney = orderTable.getOrderintercepttotalmoney() + interceptTravelTotalMoney;

		/*// 6、更新用户表(客户余额)
		User customerUser = new User();
		// 查询
		UserAndUserServiceAndUserCustomer selectCustomerUser = new UserAndUserServiceAndUserCustomer();
		selectCustomerUser.setUserid(orderTable.getOrdercreateuserid()); // 客户ID(YHBG+UUID)
		selectCustomerUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> customerUserList = null;
		customerUserList = userService.selectThreeTablesByUnionData(selectCustomerUser); // 查询
		if (customerUserList == null || customerUserList.size() == 0) {
			returnData.setMsg("找不到该客户，无法派单！");
			return returnData;
		} else {
			// 数据
			customerUser.setUserid(customerUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			customerUser.setUsermoney(customerUserList.get(0).getUsermoney() - expenditureMoney); // 用户余额
			customerUser.setUserupdateuserid(currentUserId); // 当前操作人员ID // 用户更新人ID(YHBG+UUID)
			customerUser.setUserupdatetime(new Date()); // 用户更新时间

//			if (customerUser.getUsermoney() < 0) {
//				returnData.setMsg("该客户当前余额无法支付本次维修，无法派单！");
//				return returnData;
//			}
		}*/

		/*// 7、添加用户_客户_支出_金额表
		UserCustomerExpenditureMoney customerExpenditureMoney = new UserCustomerExpenditureMoney();
		customerExpenditureMoney.setUsercustomerexpendituremoneyid(Config.SIGN_USERCUSTOMEREXPENDITUREMONEY + UUID.randomUUID().toString()); // 客户支出金额ID(KHZC+UUID)
		customerExpenditureMoney.setUsercustomerexpendituremoneyuserid(customerUser.getUserid()); // 金额用户(YHBG+UUID)
		customerExpenditureMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		customerExpenditureMoney.setUsercustomerexpendituremoneysource("PTPD"); // 金额来源(PTPD:平台派单(客户按维修推荐总价+维修差旅费推荐总价支出金额)/KHTX:客户提现(客户支出金额))
		customerExpenditureMoney.setUsercustomerexpendituremoney(-expenditureMoney); // 金额(可正可负)
		customerExpenditureMoney.setUsercustomerexpendituremoneycreatetime(new Date()); // 金额创建时间*/

		/*// 8、添加平台_收益金额表
		BackMoney backMoney = new BackMoney();
		// 数据
		backMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 平台金额ID(PTJE+UUID)
		backMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		backMoney.setBackmoney(expenditureMoney); // 金额(可正可负) // 维修推荐总价(下单时，对应的推荐总价)
		backMoney.setBackmoneyother1("PTPD"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户))
		backMoney.setBackmoneycreatetime(new Date()); // 金额创建时间*/

		// 平台派单
		int count = 0;
		//count = operationService.backDistribution(orderTable, task, orderPriceList, customerUser, customerExpenditureMoney, backMoney);
		//count = operationService.backDistribution(orderTable, task, orderPriceList);
		count = operationService.insertDistributeAndUpdateOrder(distributeConfirm, orderTable, task);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("平台派单失败！");
		} else {

			// 查询数据
			UserAndUserServiceAndUserCustomer selectData = new UserAndUserServiceAndUserCustomer();
			selectData.setUserid(orderTable.getOrdercreateuserid()); // 查询用户
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
						templateId = "UIMqKoW_cRYMlO6Zs9IfHANwvJOs2WVO90poC9ZOlGM";
						formId = wechatFormList.get(0).getFormid();

						SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String token = F_WechatRestful.getAccessToken(wechatService);
						//String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
						String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", ServiceUserList2.get(0).getUserrealname(), orderTable.getOrderprojectname(), (orderTable.getOrderpricetotalmoney() + orderTable.getOrdertraveltotalmoney()) + "", dateFormate.format(orderTable.getOrderupdatetime()));
						boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
						List<Integer> idList = new ArrayList<Integer>();
						idList.add(wechatFormList.get(0).getId());
						wechatFormService.deleteByIdList(idList);

						logger.error("服务通知result" + result);
						System.out.println("服务通知result" + result);

					}
				}
			}


			//发通知给维修人员
			// 查询数据
			UserAndUserServiceAndUserCustomer selectServiceData = new UserAndUserServiceAndUserCustomer();
			selectServiceData.setUserid(orderTable.getOrderserviceuserid()); // 查询
			selectServiceData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			// 查询数据
			List<UserAndUserServiceAndUserCustomer> userServiceList = userService.selectThreeTablesBySelectData(selectServiceData);
			if (userServiceList.size() != 0){
				for (int i = 0; i < userServiceList.size(); i ++){
					WechatForm wechatForm = new WechatForm();
					wechatForm.setPagenumber(-1);//不分页
					wechatForm.setUserid(userServiceList.get(i).getUserid());
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
						templateId = "kT2OTxraicVvL3VmwzO7pQmTo1gDx7XXnYxSpeRONro";
						formId = wechatFormList.get(0).getFormid();

						SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String token = F_WechatRestful.getAccessToken(wechatService);
						//String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
						String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", "您有一单任务！", orderTable.getOrdercontactperson(), dateFormate.format(orderTable.getOrderplantime()), "");
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
			returnData.setMsg("平台派单成功！");
		}

		return returnData;
	}


    /**
     * 客户确认派单人
     *
     * 1、更新派单确认表状态
     *2、更新订单表信息
     * 3、添加维修任务表
     * 4、添加订单价格详情表数据
     *
     * @param orderTable
     * @return
     *
     * @author ZY on 2018/11/22
     */
    @ResponseBody
    @RequestMapping("/updateByDistributeConfirm")
    public LayuiDataTemplet<OrderTable> updateByDistributeConfirm(@RequestBody OrderTable orderTable) {
        LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null

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

        // 更新判断
        if (orderTable.getId() == null || orderTable.getId() == 0) {
            if (orderTable.getOrderid() == null || orderTable.getOrderid().isEmpty()) {
                returnData.setMsg("缺少更新条件，更新失败！");
                return returnData;
            }
        }
        //查询订单表
        OrderTable selectOrderTable = new OrderTable();
        selectOrderTable.setOrderid(orderTable.getOrderid());
        selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
        List<OrderTable> orderTableList = null;
        orderTableList = orderTableService.selectByOrderTable(selectOrderTable);
        if (orderTableList == null || orderTableList.size() == 0) {
            returnData.setMsg("找不到该订单信息！");
            return returnData;
        } else {
            orderTable = orderTableList.get(0);
        }

        DistributeConfirm distributeConfirm = new DistributeConfirm();
        //查询派单确认表信息
        // 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
        DistributeConfirm selectDistributeConfirm = new DistributeConfirm();
        selectDistributeConfirm.setOrderid(orderTable.getOrderid());
        selectDistributeConfirm.setDistributeconfirmserviceuserid(orderTable.getOrderserviceuserid());
        selectDistributeConfirm.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
        List<DistributeConfirm> distributeConfirmList = null;
        distributeConfirmList = distributeConfirmService.selectByDistributeConfirm(selectDistributeConfirm);
        if (distributeConfirmList == null || distributeConfirmList.size() == 0) {
            returnData.setMsg("找不到该派单确认信息！");
            return returnData;
        } else {
            distributeConfirm = distributeConfirmList.get(0);
        }

        // 1、更新数据
        distributeConfirm.setDistributeconfirmstate("CY");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
        distributeConfirm.setDistributeconfirmupdateuserid(distributeConfirm.getDistributeconfirmcreateuserid());//更新人
        distributeConfirm.setDistributeconfirmupdatetime(new Date()); // 更新时间

        //2、更新订单表信息
        orderTable.setOrderid(distributeConfirm.getOrderid());//订单编号
        orderTable.setOrderstate("JS");//接收状态
        orderTable.setOrderpricetotalmoney(distributeConfirm.getDistributeconfirmorderpricetotalmoney());//维修工自己的维修总价
        orderTable.setOrderserviceuserid(distributeConfirm.getDistributeconfirmserviceuserid());//维修人员ID
        orderTable.setOrderdistance(distributeConfirm.getDistributeconfirmorderdistance());//距离
        orderTable.setOrderintercepttraveltotalmoney(distributeConfirm.getDistributeconfirmother4());//推荐差旅费总价
        orderTable.setOrdertraveltotalmoney(distributeConfirm.getDistributeconfirmordertraveltotalmoney());//差旅费总价
        orderTable.setOrderupdatetime(new Date());//更新时间
        orderTable.setOrderupdateuserid(distributeConfirm.getDistributeconfirmcreateuserid());//更新人

        //查询订单清单详情数据
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderid(distributeConfirm.getOrderid());
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
        if (orderDetailList.size() == 0) {
            returnData.setMsg("该维修单没有维修详情数据，无法派单！");
            return returnData;
        }

        /*// 3、添加维修任务表
        Task task = new Task();
        // 数据
        task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
        task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
        task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)*/

        // 4、添加维修单对应维修单价表
        List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
        // 循环维修单详情List
        for (int j = 0; j < orderDetailList.size(); j++) {
            // 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
            PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
            price.setPriceupdateuserid(distributeConfirm.getDistributeconfirmserviceuserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
            price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
            price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
            price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
            price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
            price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

            List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
            priceList = priceService.selectFourTablesByUnionData(price); // 查询
            for (int i = 0; i < priceList.size(); i++) {
                OrderPrice orderPrice = new OrderPrice();
                orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
                orderPrice.setOrderpriceupdateuserid(distributeConfirm.getDistributeconfirmserviceuserid()); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
                orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
                orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
                orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
                orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
                orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
                orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
                orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
                orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
                orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注

                orderPriceList.add(orderPrice);
            }
        }

        // 更新
        int count = 0;
        count = operationService.updateDistributeBackDistribution(distributeConfirm, orderTable, orderPriceList);

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
     * 客户或者维修、安装确认派单时取消接单
     *
     * 1、更新派单确认表状态
     *2、更新订单表信息
     * 3、添加维修任务表
     * 4、添加订单价格详情表数据
     *
     * @param distributeConfirm
     * @return
     *
     * @author ZY on 2018/11/22
     */
    @ResponseBody
    @RequestMapping("/updateByDistributeConfirmCancel")
    public LayuiDataTemplet<OrderTable> updateByDistributeConfirmCancel(@RequestBody DistributeConfirm distributeConfirm) {
        LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null

        // 版本号不为空，则验证版本号
        try {
            if (distributeConfirm.getVersion() != null && !distributeConfirm.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(distributeConfirm.getVersion(), Config.VERSION);
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


        //查询订单表
        DistributeConfirm selectDistributeConfirm = new DistributeConfirm();
        selectDistributeConfirm.setOrderid(distributeConfirm.getOrderid());
        selectDistributeConfirm.setDistributeconfirmserviceuserid(distributeConfirm.getDistributeconfirmserviceuserid());
        selectDistributeConfirm.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
        List<DistributeConfirm> distributeConfirmList = null;
        distributeConfirmList = distributeConfirmService.selectByDistributeConfirm(selectDistributeConfirm);
        if (distributeConfirmList == null || distributeConfirmList.size() == 0) {
            returnData.setMsg("找不到该订单派单信息！");
            return returnData;
        }

        // 1、更新数据
        //distributeConfirm.setDistributeconfirmupdateuserid(distributeConfirm.getDistributeconfirmcreateuserid());//更新人
        distributeConfirm.setDistributeconfirmupdatetime(new Date()); // 更新时间

        OrderTable orderTable = new OrderTable();
        //2、更新订单表信息
        orderTable.setOrderid(distributeConfirm.getOrderid());//订单编号
        orderTable.setOrderstate("BJ");//改成BJ可以重新派单或者参与竞标
        orderTable.setOrderserviceuserid("");//维修人员ID
        orderTable.setOrderupdatetime(new Date());//更新时间
        orderTable.setOrderupdateuserid(distributeConfirm.getDistributeconfirmupdateuserid());//更新人

        //查询订单清单详情数据
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderid(distributeConfirm.getOrderid());
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
        if (orderDetailList.size() == 0) {
            returnData.setMsg("该维修单没有维修详情数据，无法派单！");
            return returnData;
        }

        // 3、添加维修任务表
        Task task = new Task();
        // 数据
        task.setOrderid(distributeConfirm.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)


        // 更新
        int count = 0;
        count = operationService.updateByDistributeConfirmCancel(distributeConfirm, orderTable, task);

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
	 * 符合订单条件的派单人
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 *
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费推荐总价 收入金额)
	 *
	 * 5、更新用户表(客户余额)
	 * 6、添加用户_客户_支出_金额表
	 * 7、添加平台_收益金额表
	 *
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/backDistributionUser")
	public LayuiDataTemplet<UserAndUserServiceAndUserCustomer> backDistributionUser(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<UserAndUserServiceAndUserCustomer>  returnData = new LayuiDataTemplet<UserAndUserServiceAndUserCustomer>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

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
		/*if (currentUserId == null || currentUserId.isEmpty()) {
			returnData.setMsg("当前操作人ID为空，无法派单！");
			return returnData;
		}*/

		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法派单！");
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

		// 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
		String serviceUserId = ""; // 维修人员ID(YHBG+UUID)
		double orderPriceTotalMoney = 0; // 维修总价(根据维修单价计算得出)
		double distance = 0; // 距离
		double orderTravelTotalMoney = 0; // 维修差旅费总价(根据差旅费(每公里)*距离计算得出)

// 		// (1).根据orderTable实体中的维修地点，获取对应的接单区域ID
//		String areaId;
//		Area area = new Area();
//		area.setAreaname(orderTable.getOrderaddress());
//		area.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
//		List<Area> areaList = new ArrayList<Area>();
//		areaList = areaService.selectByArea(area); // 查询
//		if (areaList.size() == 0) {
//			returnData.setMsg("找不到该接单区域，无法派单！");
//			return returnData;
//		} else {
//			areaId = areaList.get(0).getAreaid();
//		}

		// (2).根据orderTable实体中的维修推荐总价，获取对应的接单规模ID
		String scaleId = null;
		Scale selectScale = new Scale();
		selectScale.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<Scale> scaleList = new ArrayList<Scale>();
		scaleList = scaleService.selectByScale(selectScale); // 查询
		if (scaleList.size() == 0) {
			returnData.setMsg("接单规模暂无数据，无法派单！");
			return returnData;
		} else {
			for (int i = 0; i < scaleList.size(); i++) {
				Scale scale = scaleList.get(i);

				// 接单规模金额初始值(小数值) == 维修推荐总价
				if (Double.doubleToLongBits(scale.getScalemin()) ==
						Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney())) {
					scaleId = scale.getScaleid();
					break;
				}

				// 接单规模金额结束值(大数值) == 维修推荐总价
				if (Double.doubleToLongBits(scale.getScalemax()) ==
						Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney())) {
					scaleId = scale.getScaleid();
					break;
				}

				// 接单规模金额初始值(小数值) < 维修推荐总价，并且接单规模金额结束值(大数值) > 维修推荐总价
				if (Double.doubleToLongBits(scale.getScalemin()) < Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney()) &&
						Double.doubleToLongBits(scale.getScalemax()) > Double.doubleToLongBits(orderTable.getOrderintercepttotalmoney())) {
					scaleId = scale.getScaleid();
					break;
				}
			}

			if (scaleId == null) {
				returnData.setMsg("该规模没有对应的维修工或安装队，无法派单！");
				return returnData;
			}
		}

		// (3).根据orderTable实体中的建筑类型ID，获取对应的建筑类型系数
		double buildingtypecoefficient = 0; // 建筑类型系数
		BuildingType buildingType = new BuildingType();
		buildingType.setBuildingtypeid(orderTable.getBuildingtypeid()); // 建筑类型ID(JZLX+UUID)
		buildingType.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<BuildingType> buildingList = new ArrayList<BuildingType>();
		buildingList = buildingTypeService.selectByBuildingType(buildingType); // 查询
		if (buildingList.size() == 0) {
			returnData.setMsg("找不到该建筑类型，无法派单！");
			return returnData;
		} else {
			buildingtypecoefficient = buildingList.get(0).getBuildingtypecoefficient();
		}

		// (4).根据接单状态、接单区域、接单规模，匹配维修工、安装队
		UserAndUserServiceAndUserCustomer selectUser = new UserAndUserServiceAndUserCustomer();
		selectUser.setAreacode(orderTable.getOrderposition() + ","); // 腾讯地图上的城市代码(会有多个，以逗号分隔，以逗号结尾)
		selectUser.setScaleid(scaleId); // 接单规模ID(JDGM+UUID)
		selectUser.setUserservicerole(orderTable.getOrderprojecttype()); // 用户接单类别(WX:维修工/AZ:安装队)
		selectUser.setUserrole(orderTable.getOrderprojecttype() + ","); // 用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
		selectUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		//获取订单详情中数据的项目清单
		String goodsId = "";
		OrderDetail orderDetail1 = new OrderDetail();
		orderDetail1.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList1 = new ArrayList<OrderDetail>();
		orderDetailList1 = orderDetailService.selectByOrderDetail(orderDetail1); // 查询
		if (orderDetailList1.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据！");
			return returnData;
		} else{
			List<String> list = new ArrayList<String>();//创建新的list
			for (int i = 0; i < orderDetailList1.size(); i++){
				String str = orderDetailList1.get(i).getGoodsid();
				if(!list.contains(str)){   //查看新集合中是否有指定的元素，如果没有则加入
					list.add(str);
				}
			}
			for (int j = 0; j < list.size(); j++){
				goodsId = goodsId + list.get(j) + ",";
			}
		}
		selectUser.setGoodid(goodsId); // 项目清单ID

		List<UserAndUserServiceAndUserCustomer> serviceUserList = new ArrayList<UserAndUserServiceAndUserCustomer>();
		serviceUserList = userService.selectBackDistributionServiceUser(selectUser); // 查询
		if (serviceUserList.size() == 0) {
			returnData.setMsg("该维修单没有匹配的维修工或安装队，无法派单！");
			return returnData;
		}

		// (5).根据匹配出来的维修工、安装队，判断该人员的信用值是否大于最低信用值
		List<UserAndUserServiceAndUserCustomer> selectCreditServiceUserList = new ArrayList<UserAndUserServiceAndUserCustomer>();
		// 查询最低信用值
		double creditMin = -9999;
		creditMin = setupService.selectBySetup(new CreditMinSetup()).get(0).getCreditmin();
		if (Double.doubleToLongBits(creditMin) == -9999) {
			returnData.setMsg("最低信用值暂无数据，无法派单！");
			return returnData;
		} else {
			// 判断
			for (int i = 0; i < serviceUserList.size(); i++) {
				UserAndUserServiceAndUserCustomer user = serviceUserList.get(i);
				if (Double.doubleToLongBits(user.getUsercredit()) >= Double.doubleToLongBits(creditMin)) {
					// 信用值 >= 最低信用值
					selectCreditServiceUserList.add(user); // 为List添加数据
				}
			}

			if (selectCreditServiceUserList.size() == 0) {
				returnData.setMsg("该维修单匹配的维修工或安装队的信用值都过低，无法派单！");
				return returnData;
			}
		}

		// (6).根据匹配出来的维修工、安装队，判断该人员是否退回过该维修单，如果退回过，则不再给该人员派此单
		ReturnTableAndOrderTableAndUser returnTable = new ReturnTableAndOrderTableAndUser();
		returnTable.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		returnTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<ReturnTableAndOrderTableAndUser> returnTableList = null;
		returnTableList = returnTableService.selectThreeTablesBySelectData(returnTable);
		if (returnTableList != null  && returnTableList.size() != 0) {
			// 循环退回单List
			for (int i = 0; i < returnTableList.size(); i++) {
				// 循环人员List
				for (int j = 0; j < selectCreditServiceUserList.size(); j++) {
					if (returnTableList.get(i).getReturnuserid().equals(selectCreditServiceUserList.get(j).getUserid())) {
						selectCreditServiceUserList.remove(i);
						break;
					}
				}
			}
		}
		if (serviceUserList.size() == 0) {
			returnData.setMsg("该维修单没有匹配的维修工或安装队，无法派单！");
			return returnData;
		}

		// (7).根据匹配出来的不同的维修工、安装队的差旅费(每公里)和距离，计算维修差旅费总价(差旅费(每公里)*距离)
		// 循环人员List
		for (int i = 0; i < selectCreditServiceUserList.size(); i++) {
			double travelTotalMoney = 0; // 维修差旅费总价

			// 距离(单位：米)
			double userDistance = PositionUtil.getDistance(orderTable.getOrderlatitude(), orderTable.getOrderlongitude(),
					selectCreditServiceUserList.get(i).getUserlatitude(), selectCreditServiceUserList.get(i).getUserlongitude());

			// 距离*差旅费(每公里)
			if (selectCreditServiceUserList.get(i).getUserservicetravelmoney() != null) {
				travelTotalMoney = userDistance / 1000 * selectCreditServiceUserList.get(i).getUserservicetravelmoney();
			}

			// 为人员添加数据
			selectCreditServiceUserList.get(i).setDistance(userDistance); // 距离
			selectCreditServiceUserList.get(i).setTraveltotalmoney(travelTotalMoney); // 维修差旅费总价
		}

		// (8).根据匹配出来的不同的维修工、安装队的维修单价，计算维修总价
		// 维修总价 = 维修人员报价 * 报价系数(报价系数在下方)
		// 根据OrderTable实体中的维修单ID，获取维修单详情中的产品ID、项目ID
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
		if (orderDetailList.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据，无法派单！");
			return returnData;
		} else {
			// 根据人员、产品ID、项目ID，计算维修总价
			List<UserAndUserServiceAndUserCustomer> finalServiceUserList = new ArrayList<UserAndUserServiceAndUserCustomer>();

			// 循环人员List，循环维修单详情List，根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价，循环计算维修单价，得出维修总价
			// 循环人员List
			for (int i = 0; i < selectCreditServiceUserList.size(); i++) {
				double orderPriceTotalMoneyNocredit = 0;//维修总价不用信用值系数算出来的，派单的价格
				double priceTotalMoney = 0; // 维修总价
				double totalMoney = 0; // 总价(维修总价+维修差旅费总价)

				// 循环维修单详情List
				for (int j = 0; j < orderDetailList.size(); j++) {
					// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
					PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
					price.setPriceupdateuserid(selectCreditServiceUserList.get(i).getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
					price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
					price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
					price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
					price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
					price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

					List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
					priceList = priceService.selectFourTablesByUnionData(price); // 查询
					if (priceList.size() == 0) {
						// 某一款产品ID、项目ID，该人员没有对应的维修单价
						priceTotalMoney = 0;
						break;
					} else {
						// 循环计算维修单价，得出维修总价
						priceTotalMoney += priceList.get(0).getPricemoney() * orderDetailList.get(j).getOrderdetailnumber();

					}
				}

				// 如果维修总价不为0，则计算最终维修总价，并为最终维修人员List添加数据
				if (priceTotalMoney != 0) {

					// * 建筑系数
					orderPriceTotalMoneyNocredit = priceTotalMoney * buildingtypecoefficient;
					/**
					 * 维修总价 = 维修人员报价 * 报价系数 * 建筑系数
					 *
					 * 信用值在90分以上：报价系数为90%
					 * 信用值在80-89分：报价系数为95%
					 * 信用值在70-79分：报价系数为100%
					 * 信用值在60-69分：报价系数为105%
					 * 信用值在40-59分：报价系数为110%
					 */
					double credit = selectCreditServiceUserList.get(i).getUsercredit(); // 维修工、安装队人员所对应的信用值

				/*	// * 报价系数
					if (Double.doubleToLongBits(credit) >= 90) {
						priceTotalMoney = priceTotalMoney * 0.9;
					} else if (Double.doubleToLongBits(credit) >= 80 && Double.doubleToLongBits(credit) < 90) {
						priceTotalMoney = priceTotalMoney * 0.95;
					} else if (Double.doubleToLongBits(credit) >= 70 && Double.doubleToLongBits(credit) < 80) {
						priceTotalMoney = priceTotalMoney * 1;
					} else if (Double.doubleToLongBits(credit) >= 60 && Double.doubleToLongBits(credit) < 70) {
						priceTotalMoney = priceTotalMoney * 1.05;
					} else if (Double.doubleToLongBits(credit) >= 40 && Double.doubleToLongBits(credit) < 60) {
						priceTotalMoney = priceTotalMoney * 1.1;
					} else {
						priceTotalMoney = priceTotalMoney * 1.2;
					}*/

					// 四舍五入，保存两位小数
					BigDecimal bg = new BigDecimal(priceTotalMoney).setScale(2, RoundingMode.UP);
					priceTotalMoney = bg.doubleValue();

					// * 建筑系数
					priceTotalMoney = priceTotalMoney * buildingtypecoefficient;

					// 总价(维修总价+维修差旅费总价)
					totalMoney = priceTotalMoney + selectCreditServiceUserList.get(i).getTraveltotalmoney();

					// 为人员添加数据
					selectCreditServiceUserList.get(i).setPricetotalmoney(priceTotalMoney); // 维修总价
					selectCreditServiceUserList.get(i).setTotalmoney(totalMoney); // 总价(维修总价+维修差旅费总价)
					selectCreditServiceUserList.get(i).setOrderPriceTotalMoneyNocredit(orderPriceTotalMoneyNocredit);//维修总价不包含信用值系数

					//添加是不是当前派单人
					if (null != orderTable.getOrderserviceuserid() && orderTable.getOrderserviceuserid().equals(selectCreditServiceUserList.get(i).getUserid())){
						selectCreditServiceUserList.get(i).setBackdistributionstate("Y");
					} else {
						selectCreditServiceUserList.get(i).setBackdistributionstate("N");
					}

					finalServiceUserList.add(selectCreditServiceUserList.get(i)); // 为List添加数据
				}
			}

			if (finalServiceUserList.size() == 0) {
				returnData.setMsg("该维修单的某些维修详情数据维修工或安装队没有维修总价，无法派单！");
				return returnData;
			} else {
				Collections.sort(finalServiceUserList); // 按照总价从低到高排序
				returnData.setCount(finalServiceUserList.size());
				returnData.setData(finalServiceUserList);
				returnData.setMsg("查询成功！");
			}

			/*// (9).查询总价最低的作为维修人员
			Collections.sort(finalServiceUserList); // 按照总价从低到高排序

			serviceUserId = finalServiceUserList.get(0).getUserid(); // 维修人员ID(YHBG+UUID)
			//orderPriceTotalMoney = finalServiceUserList.get(0).getPricetotalmoney(); // 维修总价(根据维修单价计算得出)
			orderPriceTotalMoney = finalServiceUserList.get(0).getOrderPriceTotalMoneyNocredit();; // 维修总价(根据维修单价计算得出 不包含根据信用值系数计算)
			distance= finalServiceUserList.get(0).getDistance(); // 距离
			orderTravelTotalMoney = finalServiceUserList.get(0).getTraveltotalmoney(); // 维修差旅费总价(根据差旅费*距离计算得出)*/
		}

//		// 添加维修单分配记录表
//		Distribution distribution = new Distribution();
//		// 数据
//		distribution.setDistributionid(Config.SIGN_DISTRIBUTION + UUID.randomUUID().toString()); // 分配记录ID(FPJL+UUID)
//		distribution.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
//		distribution.setServiceuserid(serviceUserId); // 被分配人员ID(维修人员)(YHBG+UUID)
//		distribution.setDistributionuserid(currentUserId); // 当前操作人员ID // 分配人员ID(YHBG+UUID)
//		distribution.setDistributiontime(new Date()); // 分配时间

		// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		/*double interceptTravelTotalMoney = 0; // 维修差旅费推荐总价
		InterceptTravel selectInterceptTravel = new InterceptTravel();
		selectInterceptTravel.setIntercepttraveltype(orderTable.getOrdertype()); // 差旅费(每公里)拦标价类型(WX:维修工/AZ:安装队)
		selectInterceptTravel.setIntercepttravelstate("Y"); // 差旅费(每公里)拦标价状态(Y:有效/N:无效)
		List<InterceptTravel> interceptTravelList = null;
		interceptTravelList = interceptTravelService.selectByInterceptTravel(selectInterceptTravel);
		if (interceptTravelList != null && interceptTravelList.size() != 0) {
			interceptTravelTotalMoney = interceptTravelList.get(0).getIntercepttravelmoney() * distance;
		}*/

		// 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
		// 数据
		/*orderTable.setOrdertype("PD"); // 维修类型(PD:派单/QD:抢单)
		orderTable.setOrderstate("JS"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		orderTable.setOrderserviceuserid(serviceUserId); // 维修人员ID(YHBG+UUID)
		orderTable.setOrderdistance(distance); // 维修距离
		orderTable.setOrderintercepttraveltotalmoney(interceptTravelTotalMoney); // 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		orderTable.setOrderpricetotalmoney(orderPriceTotalMoney); // 维修总价(根据维修单价计算得出)
		orderTable.setOrdertraveltotalmoney(orderTravelTotalMoney); // 维修差旅费总价(根据差旅费*距离计算得出)
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间*/

		// 4、添加维修任务表
		/*Task task = new Task();
		// 数据
		task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
		task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)*/

		// 5、添加维修单对应维修单价表
		/*List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
		// 循环维修单详情List
		for (int j = 0; j < orderDetailList.size(); j++) {
			// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
			PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
			price.setPriceupdateuserid(serviceUserId); // 维修单价维护人(维修人员)ID(YHBG+UUID)
			price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
			price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
			price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
			price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
			price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
			priceList = priceService.selectFourTablesByUnionData(price); // 查询
			for (int i = 0; i < priceList.size(); i++) {
				OrderPrice orderPrice = new OrderPrice();
				orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
				orderPrice.setOrderpriceupdateuserid(serviceUserId); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
				orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
				orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
				orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
				orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
				orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
				orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
				orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
				orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
				orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注

				orderPriceList.add(orderPrice);
			}
		}*/

		// 金额数据：客户支出(平台按 维修推荐总价+维修差旅费总价 收入金额)

		// 客户需要支出的金额 = 维修推荐总价 + 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		//double expenditureMoney = orderTable.getOrderintercepttotalmoney() + interceptTravelTotalMoney;

		/*// 6、更新用户表(客户余额)
		User customerUser = new User();
		// 查询
		UserAndUserServiceAndUserCustomer selectCustomerUser = new UserAndUserServiceAndUserCustomer();
		selectCustomerUser.setUserid(orderTable.getOrdercreateuserid()); // 客户ID(YHBG+UUID)
		selectCustomerUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> customerUserList = null;
		customerUserList = userService.selectThreeTablesByUnionData(selectCustomerUser); // 查询
		if (customerUserList == null || customerUserList.size() == 0) {
			returnData.setMsg("找不到该客户，无法派单！");
			return returnData;
		} else {
			// 数据
			customerUser.setUserid(customerUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			customerUser.setUsermoney(customerUserList.get(0).getUsermoney() - expenditureMoney); // 用户余额
			customerUser.setUserupdateuserid(currentUserId); // 当前操作人员ID // 用户更新人ID(YHBG+UUID)
			customerUser.setUserupdatetime(new Date()); // 用户更新时间

//			if (customerUser.getUsermoney() < 0) {
//				returnData.setMsg("该客户当前余额无法支付本次维修，无法派单！");
//				return returnData;
//			}
		}*/

		/*// 7、添加用户_客户_支出_金额表
		UserCustomerExpenditureMoney customerExpenditureMoney = new UserCustomerExpenditureMoney();
		customerExpenditureMoney.setUsercustomerexpendituremoneyid(Config.SIGN_USERCUSTOMEREXPENDITUREMONEY + UUID.randomUUID().toString()); // 客户支出金额ID(KHZC+UUID)
		customerExpenditureMoney.setUsercustomerexpendituremoneyuserid(customerUser.getUserid()); // 金额用户(YHBG+UUID)
		customerExpenditureMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		customerExpenditureMoney.setUsercustomerexpendituremoneysource("PTPD"); // 金额来源(PTPD:平台派单(客户按维修推荐总价+维修差旅费推荐总价支出金额)/KHTX:客户提现(客户支出金额))
		customerExpenditureMoney.setUsercustomerexpendituremoney(-expenditureMoney); // 金额(可正可负)
		customerExpenditureMoney.setUsercustomerexpendituremoneycreatetime(new Date()); // 金额创建时间*/

		/*// 8、添加平台_收益金额表
		BackMoney backMoney = new BackMoney();
		// 数据
		backMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 平台金额ID(PTJE+UUID)
		backMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		backMoney.setBackmoney(expenditureMoney); // 金额(可正可负) // 维修推荐总价(下单时，对应的推荐总价)
		backMoney.setBackmoneyother1("PTPD"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户))
		backMoney.setBackmoneycreatetime(new Date()); // 金额创建时间*/

		// 平台派单
		/*int count = 0;
		//count = operationService.backDistribution(orderTable, task, orderPriceList, customerUser, customerExpenditureMoney, backMoney);
		count = operationService.backDistribution(orderTable, task, orderPriceList);*/

		// 返回数据


		return returnData;
	}

	/**
	 * 符合订单条件的派单人(20190114最新版的)（可查询、可查全部的人）
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 *
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费推荐总价 收入金额)
	 *
	 * 5、更新用户表(客户余额)
	 * 6、添加用户_客户_支出_金额表
	 * 7、添加平台_收益金额表
	 *
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param unionData
	 * @return
	 *
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/backDistributionUserNew")
	public LayuiDataTemplet<UserAndUserServiceAndUserCustomer> backDistributionUserNew(@RequestBody UserAndUserServiceAndUserCustomer unionData) {
		LayuiDataTemplet<UserAndUserServiceAndUserCustomer>  returnData = new LayuiDataTemplet<UserAndUserServiceAndUserCustomer>(); // 返回数据
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


		OrderTable orderTable = new OrderTable();
		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(unionData.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法派单！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
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


            unionData.setSelectuserrole(orderTable.getOrderprojecttype());//添加角色类型
            unionData.setUserservicestate("Y");//接单

			//通过传入的任务类别、清单项目、单价查询单价表中的人
			if (!unionData.getGoodsid().equals("")&&!unionData.getProjectid().equals("")) {
				PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
				price.setPagenumber(-1);//不分页
				price.setGoodsid(unionData.getGoodsid());//任务类别ID
				price.setProjectid(unionData.getProjectid());//项目清单ID
				price.setPricemoney(unionData.getPricemoney());//单价
				List<PriceAndGoodsAndProjectAndUser> priceAndGoodsAndProjectAndUserList = priceService.selectFourTablesByUnionData(price);
				if (priceAndGoodsAndProjectAndUserList.size() > 0) {
					List<String> userList = new ArrayList<String>();
					for (int i = 0; i < priceAndGoodsAndProjectAndUserList.size(); i++) {
						userList.add(priceAndGoodsAndProjectAndUserList.get(i).getPriceupdateuserid());
					}
					unionData.setUserlist(userList);
				} else {
					returnData.setMsg("暂无数据！");
					return returnData;
				}
			}

            // 查询数量

            int count = 0;
            count = userService.selectThreeTablesCountBySelectDataNewUser(unionData); // 查询数量

            // 返回数据
            if (count == 0) {
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                List<UserAndUserServiceAndUserCustomer> userList = userService.selectThreeTablesBySelectDataNewUser(unionData); // 查询数据

                returnData.setData(userList);
            }
        } else {
            returnData.setMsg("传递的分页数据(页数)错误！");
        }

		return returnData;
	}

	/**
	 * 后台管理人员确认派单人和更改派单人(添加到派单确认表中)
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
	 * 传递User实体(抢单的维修工、安装队)
	 * 传递OrderTable实体
	 *
	 * 需在User实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param models
	 * @return
	 *
	 * @author ZY on 2018/11/12
	 */
	@ResponseBody
	@RequestMapping("/confirmBackDistributionUser")
	public LayuiDataTemplet<OrderTable> confirmBackDistributionUser(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// JSON转换为实体类
		UserAndUserServiceAndUserCustomer userAndUserServiceAndUserCustomer = null;
		OrderTable orderTable = null;
		try {
			userAndUserServiceAndUserCustomer = JsonUtil.json2obj((String)models.get("userAndUserServiceAndUserCustomer"), UserAndUserServiceAndUserCustomer.class);
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
		} catch (Exception e1) {
			e1.printStackTrace();

			returnData.setMsg("JSON转换为实体类发生异常！");
			return returnData;
		}

		// 判断传递的数据
		// 传递User实体(抢单的维修工、安装队)
		if (userAndUserServiceAndUserCustomer == null) {
			returnData.setMsg("传递的用户数据错误！");
			return returnData;
		}

		// 判断传递的数据
		// 传递OrderTable实体
		if (orderTable == null) {
			returnData.setMsg("传递的维修单数据错误！");
			return returnData;
		}

		// 版本号不为空，则验证版本号
		try {
			if (userAndUserServiceAndUserCustomer.getVersion() != null && !userAndUserServiceAndUserCustomer.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(userAndUserServiceAndUserCustomer.getVersion(), Config.VERSION);
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
		/*if (currentUserId == null || currentUserId.isEmpty()) {
			returnData.setMsg("当前操作人ID为空，无法确认派单！");
			return returnData;
		}*/

		//页面传过来的当前选中人距离
		double distance = 0;
		//distance = userAndUserServiceAndUserCustomer.getDistance();
		//页面传过来的当前选中人总价
		double orderPriceTotalMoney = 0;
		//orderPriceTotalMoney = orderTable.getOrderpricetotalmoney();
		//页面传过来的当前选中人差旅费总价
		double orderTravelTotalMoney = 0;
		//orderTravelTotalMoney = orderTable.getOrdertraveltotalmoney();
		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法派单！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}
		//获取订单中详情数据
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
		if (orderDetailList.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据，无法派单！");
			return returnData;
		}

		int count = 0;

        if (null != orderTable.getOrdertype() && orderTable.getOrdertype().equals("QD")){
            returnData.setMsg("当前订单是竞标模式！不能使用派单功能。");
            return returnData;
        }

		if (null == orderTable.getOrderserviceuserid() || orderTable.getOrderserviceuserid().equals("")){
			// 2、根据OrderTable实体中的维修人员ID(YHBG+UUID)，获取对应的维修人员数据
			UserAndUserServiceAndUserCustomer userUnionData = new UserAndUserServiceAndUserCustomer();
			userUnionData.setUserid(userAndUserServiceAndUserCustomer.getUserid()); // 用户ID(YHBG+UUID)
			userUnionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<UserAndUserServiceAndUserCustomer> selectUserList = null;
			selectUserList = userService.selectThreeTablesByUnionData(userUnionData); // 查询
			if (selectUserList != null && selectUserList.size() != 0) {
				userUnionData = selectUserList.get(0);
			}

			// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
			double interceptTravelTotalMoney = 0; // 维修差旅费推荐总价
			InterceptTravel selectInterceptTravel = new InterceptTravel();
			selectInterceptTravel.setIntercepttraveltype(orderTable.getOrdertype()); // 差旅费(每公里)拦标价类型(WX:维修工/AZ:安装队)
			selectInterceptTravel.setIntercepttravelstate("Y"); // 差旅费(每公里)拦标价状态(Y:有效/N:无效)
			List<InterceptTravel> interceptTravelList = null;
			interceptTravelList = interceptTravelService.selectByInterceptTravel(selectInterceptTravel);
			if (interceptTravelList != null && interceptTravelList.size() != 0) {
				interceptTravelTotalMoney = interceptTravelList.get(0).getIntercepttravelmoney() * distance;
			}

			// 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
			// 数据
			orderTable.setOrdertype("PD"); // 维修类型(PD:派单/QD:抢单)
			orderTable.setOrderstate("JS"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
			orderTable.setOrderserviceuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修人员ID(YHBG+UUID)
			orderTable.setOrderdistance(distance); // 维修距离
			orderTable.setOrderintercepttraveltotalmoney(interceptTravelTotalMoney); // 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
			//orderTable.setOrderpricetotalmoney(orderPriceTotalMoney); // 维修总价(根据维修单价计算得出)
			orderTable.setOrdertraveltotalmoney(orderTravelTotalMoney); // 维修差旅费总价(根据差旅费*距离计算得出)
			orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
			orderTable.setOrderupdatetime(new Date()); // 维修更新时间

			// 4、添加维修任务表
			Task task = new Task();
			// 数据
			task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
			task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)

			// 5、添加维修单对应维修单价表
		List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
		// 循环维修单详情List
		for (int j = 0; j < orderDetailList.size(); j++) {
			// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
			PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
			price.setPriceupdateuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
			price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
			price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
			price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
			price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
			price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
			priceList = priceService.selectFourTablesByUnionData(price); // 查询
            if (priceList.size() == 0){
                returnData.setMsg("该维修师傅、安装队伍" + orderDetailList.get(j).getProjectname() +"未维护单价，请联系维护单价后再选择！");
                return returnData;
            } else {
                // 循环计算维修单价，得出维修总价
                orderPriceTotalMoney += priceList.get(0).getPricemoney() * orderDetailList.get(j).getOrderdetailnumber();
            }

			for (int i = 0; i < priceList.size(); i++) {
				OrderPrice orderPrice = new OrderPrice();
				orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
				orderPrice.setOrderpriceupdateuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
				orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
				orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
				orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
				orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
				orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
				orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
				orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
				orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
				orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注

				orderPriceList.add(orderPrice);
			}
		}

            // 四舍五入，保存两位小数
            BigDecimal bg = new BigDecimal(orderPriceTotalMoney).setScale(2, RoundingMode.UP);
            orderPriceTotalMoney = bg.doubleValue();

            // (3).根据orderTable实体中的建筑类型ID，获取对应的建筑类型系数
            double buildingtypecoefficient = 0; // 建筑类型系数
            BuildingType buildingType = new BuildingType();
            buildingType.setBuildingtypeid(orderTable.getBuildingtypeid()); // 建筑类型ID(JZLX+UUID)
            buildingType.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
            List<BuildingType> buildingList = new ArrayList<BuildingType>();
            buildingList = buildingTypeService.selectByBuildingType(buildingType); // 查询
            if (buildingList.size() == 0) {
                returnData.setMsg("找不到该建筑类型，无法派单！");
                return returnData;
            } else {
                buildingtypecoefficient = buildingList.get(0).getBuildingtypecoefficient();
            }

            // * 建筑系数
            orderPriceTotalMoney = orderPriceTotalMoney * buildingtypecoefficient;
            orderTable.setOrderpricetotalmoney(orderPriceTotalMoney); // 维修总价(根据维修单价计算得出)

            //添加到派单确认表中
            DistributeConfirm distributeConfirm = new DistributeConfirm();
            distributeConfirm.setDistributeconfirmid(Config.SIGN_DISTRIBUTECONFIRM + UUID.randomUUID().toString());//派单记录ID
            distributeConfirm.setOrderid(orderTable.getOrderid());//订单编号
            distributeConfirm.setDistributeconfirmserviceuserid(userAndUserServiceAndUserCustomer.getUserid());//派单的维修人员userid
            distributeConfirm.setDistributeconfirmorderdistance(distance);//距离
            distributeConfirm.setDistributeconfirmorderintercepttotalmoney(orderTable.getOrderintercepttotalmoney());//下单时推荐总价
            distributeConfirm.setDistributeconfirmorderpricetotalmoney(orderPriceTotalMoney);//派单人添加单价的维修总价
            distributeConfirm.setDistributeconfirmordertraveltotalmoney(orderTravelTotalMoney);//派单人的差率费总价
            distributeConfirm.setDistributeconfirmother4(interceptTravelTotalMoney);// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
            distributeConfirm.setDistributeconfirmcreateuserid(currentUserId);//派单确认创建人
            distributeConfirm.setDistributeconfirmcreatetime(new Date());//创建时间
            distributeConfirm.setDistributeconfirmstate("CY");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
            distributeConfirm.setDistributeconfirmupdateuserid(currentUserId);//更新人
            distributeConfirm.setDistributeconfirmupdatetime(new Date());//更新时间

			count = operationService.backDistributionAndConfirm(orderTable, task, orderPriceList,distributeConfirm);

		} else {
			if (!orderTable.getOrdertype().equals("PD")){
				returnData.setMsg("当前订单不是派单模式！");
				return returnData;
			}

			// 2、根据OrderTable实体中的维修人员ID(YHBG+UUID)，获取对应的维修人员数据
			UserAndUserServiceAndUserCustomer userUnionData = new UserAndUserServiceAndUserCustomer();
			userUnionData.setUserid(userAndUserServiceAndUserCustomer.getUserid()); // 用户ID(YHBG+UUID)
			userUnionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<UserAndUserServiceAndUserCustomer> selectUserList = null;
			selectUserList = userService.selectThreeTablesByUnionData(userUnionData); // 查询
			if (selectUserList != null && selectUserList.size() != 0) {
				userUnionData = selectUserList.get(0);
			}

			// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
			double interceptTravelTotalMoney = 0; // 维修差旅费推荐总价
			InterceptTravel selectInterceptTravel = new InterceptTravel();
			selectInterceptTravel.setIntercepttraveltype(orderTable.getOrdertype()); // 差旅费(每公里)拦标价类型(WX:维修工/AZ:安装队)
			selectInterceptTravel.setIntercepttravelstate("Y"); // 差旅费(每公里)拦标价状态(Y:有效/N:无效)
			List<InterceptTravel> interceptTravelList = null;
			interceptTravelList = interceptTravelService.selectByInterceptTravel(selectInterceptTravel);
			if (interceptTravelList != null && interceptTravelList.size() != 0) {
				interceptTravelTotalMoney = interceptTravelList.get(0).getIntercepttravelmoney() * distance;
			}

			// 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
			// 数据
			//orderTable.setOrdertype("PD"); // 维修类型(PD:派单/QD:抢单)
			orderTable.setOrderstate("JS"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
			orderTable.setOrderserviceuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修人员ID(YHBG+UUID)
			orderTable.setOrderdistance(distance); // 维修距离
			orderTable.setOrderintercepttraveltotalmoney(interceptTravelTotalMoney); // 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
			//orderTable.setOrderpricetotalmoney(orderPriceTotalMoney); // 维修总价(根据维修单价计算得出)
			orderTable.setOrdertraveltotalmoney(orderTravelTotalMoney); // 维修差旅费总价(根据差旅费*距离计算得出)
			orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
			orderTable.setOrderupdatetime(new Date()); // 维修更新时间

			//删除之前派单人单价记录表信息
			OrderPrice deleteOrderPrice = new OrderPrice();
			deleteOrderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)

			// 5、添加维修单对应维修单价表
			List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
			// 循环维修单详情List
			for (int j = 0; j < orderDetailList.size(); j++) {
				// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
				PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
				price.setPriceupdateuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
				price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
				price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
				price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
				price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
				price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

				List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
				priceList = priceService.selectFourTablesByUnionData(price); // 查询
                //

                if (priceList.size() == 0){
                    returnData.setMsg("该维修师傅、安装队伍" + orderDetailList.get(j).getProjectname() +"未维护单价，请联系维护单价后再选择！");
                    return returnData;
                } else {
                    // 循环计算维修单价，得出维修总价
                    orderPriceTotalMoney += priceList.get(0).getPricemoney() * orderDetailList.get(j).getOrderdetailnumber();
                }

				for (int i = 0; i < priceList.size(); i++) {
					OrderPrice orderPrice = new OrderPrice();
					orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
					orderPrice.setOrderpriceupdateuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
					orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
					orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
					orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
					orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
					orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
					orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
					orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
					orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
					orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注

					orderPriceList.add(orderPrice);
				}
			}
            // 四舍五入，保存两位小数
            BigDecimal bg = new BigDecimal(orderPriceTotalMoney).setScale(2, RoundingMode.UP);
            orderPriceTotalMoney = bg.doubleValue();

            // (3).根据orderTable实体中的建筑类型ID，获取对应的建筑类型系数
            double buildingtypecoefficient = 0; // 建筑类型系数
            BuildingType buildingType = new BuildingType();
            buildingType.setBuildingtypeid(orderTable.getBuildingtypeid()); // 建筑类型ID(JZLX+UUID)
            buildingType.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
            List<BuildingType> buildingList = new ArrayList<BuildingType>();
            buildingList = buildingTypeService.selectByBuildingType(buildingType); // 查询
            if (buildingList.size() == 0) {
                returnData.setMsg("找不到该建筑类型，无法派单！");
                return returnData;
            } else {
                buildingtypecoefficient = buildingList.get(0).getBuildingtypecoefficient();
            }

            // * 建筑系数
            orderPriceTotalMoney = orderPriceTotalMoney * buildingtypecoefficient;
            orderTable.setOrderpricetotalmoney(orderPriceTotalMoney); // 维修总价(根据维修单价计算得出)

            //查询派单确认表信息
            DistributeConfirm distributeConfirm = new DistributeConfirm();
            // 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
            DistributeConfirm selectDistributeConfirm = new DistributeConfirm();
            selectDistributeConfirm.setOrderid(orderTable.getOrderid());
            selectDistributeConfirm.setDistributeconfirmserviceuserid(orderTable.getOrderserviceuserid());
            selectDistributeConfirm.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
            List<DistributeConfirm> distributeConfirmList = null;
            distributeConfirmList = distributeConfirmService.selectByDistributeConfirm(selectDistributeConfirm);
            if (distributeConfirmList == null || distributeConfirmList.size() == 0) {

                //添加到派单确认表中
                distributeConfirm.setDistributeconfirmid(Config.SIGN_DISTRIBUTECONFIRM + UUID.randomUUID().toString());//派单记录ID
                distributeConfirm.setOrderid(orderTable.getOrderid());//订单编号
                distributeConfirm.setDistributeconfirmserviceuserid(userAndUserServiceAndUserCustomer.getUserid());//派单的维修人员userid
                distributeConfirm.setDistributeconfirmorderdistance(distance);//距离
                distributeConfirm.setDistributeconfirmorderintercepttotalmoney(orderTable.getOrderintercepttotalmoney());//下单时推荐总价
                distributeConfirm.setDistributeconfirmorderpricetotalmoney(orderPriceTotalMoney);//派单人添加单价的维修总价
                distributeConfirm.setDistributeconfirmordertraveltotalmoney(orderTravelTotalMoney);//派单人的差率费总价
                distributeConfirm.setDistributeconfirmother4(interceptTravelTotalMoney);// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
                distributeConfirm.setDistributeconfirmcreateuserid(currentUserId);//派单确认创建人
                distributeConfirm.setDistributeconfirmcreatetime(new Date());//创建时间
                distributeConfirm.setDistributeconfirmstate("CY");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
                distributeConfirm.setDistributeconfirmupdateuserid(currentUserId);//更新人
                distributeConfirm.setDistributeconfirmupdatetime(new Date());//更新时间

                count = operationService.updateBackDistributionAndInsertConfirm(orderTable, deleteOrderPrice, orderPriceList, distributeConfirm);
            } else {
                distributeConfirm = distributeConfirmList.get(0);
                // 1、更新数据
                distributeConfirm.setDistributeconfirmstate("CY");//维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
                distributeConfirm.setDistributeconfirmupdateuserid(currentUserId);//更新人
                distributeConfirm.setDistributeconfirmupdatetime(new Date()); // 更新时间
                count = operationService.updateBackDistribution(orderTable, deleteOrderPrice, orderPriceList, distributeConfirm);
            }


		}

		// 返回数据
		if (count == 0) {
			returnData.setMsg("更新派单人失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("更新派单人成功！");
		}

		return returnData;
	}


	/**
	 * 后台管理人员更改派单人（中途变更派单人）
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
	 * 传递User实体(抢单的维修工、安装队)
	 * 传递OrderTable实体
	 *
	 * 需在User实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param models
	 * @return
	 *
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/confirmBackDistributionUserHalfway")
	public LayuiDataTemplet<OrderTable> confirmBackDistributionUserHalfway(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// JSON转换为实体类
		UserAndUserServiceAndUserCustomer userAndUserServiceAndUserCustomer = null;
		OrderTable orderTable = null;
		OrderDetailListEntity orderDetailListEntity = null;
		try {
			userAndUserServiceAndUserCustomer = JsonUtil.json2obj((String)models.get("userAndUserServiceAndUserCustomer"), UserAndUserServiceAndUserCustomer.class);
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
			orderDetailListEntity = JsonUtil.json2obj((String)models.get("orderDetailListEntity"), OrderDetailListEntity.class);
		} catch (Exception e1) {
			e1.printStackTrace();

			returnData.setMsg("JSON转换为实体类发生异常！");
			return returnData;
		}

		// 判断传递的数据
		// 传递User实体(抢单的维修工、安装队)
		if (userAndUserServiceAndUserCustomer == null) {
			returnData.setMsg("传递的用户数据错误！");
			return returnData;
		}

		// 判断传递的数据
		// 传递OrderTable实体
		if (orderTable == null) {
			returnData.setMsg("传递的维修单数据错误！");
			return returnData;
		}

		// 判断传递的数据
		// OrderDetailListEntity实体(List<OrderDetail>)
		List<OrderDetail> orderDetailListLeave = null;
		if (orderDetailListEntity == null) {
			returnData.setMsg("传递的剩余工作维修单详情数据错误！");
			return returnData;
		} else {
			orderDetailListLeave = orderDetailListEntity.getOrderDetailList();
		}

		// 版本号不为空，则验证版本号
		try {
			if (userAndUserServiceAndUserCustomer.getVersion() != null && !userAndUserServiceAndUserCustomer.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(userAndUserServiceAndUserCustomer.getVersion(), Config.VERSION);
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
		/*if (currentUserId == null || currentUserId.isEmpty()) {
			returnData.setMsg("当前操作人ID为空，无法确认竞标！");
			return returnData;
		}*/

		//剩余工作清单列表的推荐总价
		double finishOrderInterceptTotalmoney = orderTable.getOrderintercepttotalmoney();
		//页面传过来的当前选中人距离
		double distance = 0;
		distance = userAndUserServiceAndUserCustomer.getDistance();
		//页面传过来的当前选中人总价
		double orderPriceTotalMoney = 0;
		orderPriceTotalMoney = orderTable.getOrderpricetotalmoney();
		//页面传过来的当前选中人差旅费总价
		double orderTravelTotalMoney = 0;
		orderTravelTotalMoney = orderTable.getOrdertraveltotalmoney();
		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法确认竞标！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}
		//获取订单中详情数据
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
		if (orderDetailList.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据，无法派单！");
			return returnData;
		}

		int count = 0;

		if (null == orderTable.getOrderserviceuserid() || orderTable.getOrderserviceuserid().equals("")){
			// 2、根据OrderTable实体中的维修人员ID(YHBG+UUID)，获取对应的维修人员数据
			UserAndUserServiceAndUserCustomer userUnionData = new UserAndUserServiceAndUserCustomer();
			userUnionData.setUserid(userAndUserServiceAndUserCustomer.getUserid()); // 用户ID(YHBG+UUID)
			userUnionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<UserAndUserServiceAndUserCustomer> selectUserList = null;
			selectUserList = userService.selectThreeTablesByUnionData(userUnionData); // 查询
			if (selectUserList != null && selectUserList.size() != 0) {
				userUnionData = selectUserList.get(0);
			}

			// 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
			double interceptTravelTotalMoney = 0; // 维修差旅费推荐总价
			InterceptTravel selectInterceptTravel = new InterceptTravel();
			selectInterceptTravel.setIntercepttraveltype(orderTable.getOrdertype()); // 差旅费(每公里)拦标价类型(WX:维修工/AZ:安装队)
			selectInterceptTravel.setIntercepttravelstate("Y"); // 差旅费(每公里)拦标价状态(Y:有效/N:无效)
			List<InterceptTravel> interceptTravelList = null;
			interceptTravelList = interceptTravelService.selectByInterceptTravel(selectInterceptTravel);
			if (interceptTravelList != null && interceptTravelList.size() != 0) {
				interceptTravelTotalMoney = interceptTravelList.get(0).getIntercepttravelmoney() * distance;
			}

			// 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
			// 数据
			orderTable.setOrdertype("PD"); // 维修类型(PD:派单/QD:抢单)
			orderTable.setOrderstate("JS"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
			orderTable.setOrderserviceuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修人员ID(YHBG+UUID)
			orderTable.setOrderdistance(distance); // 维修距离
			orderTable.setOrderintercepttraveltotalmoney(interceptTravelTotalMoney); // 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
			orderTable.setOrderpricetotalmoney(orderPriceTotalMoney); // 维修总价(根据维修单价计算得出)
			orderTable.setOrdertraveltotalmoney(orderTravelTotalMoney); // 维修差旅费总价(根据差旅费*距离计算得出)
			orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
			orderTable.setOrderupdatetime(new Date()); // 维修更新时间

			// 4、添加维修任务表
			Task task = new Task();
			// 数据
			task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
			task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)

			// 5、添加维修单对应维修单价表
			List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
			// 循环维修单详情List
			for (int j = 0; j < orderDetailList.size(); j++) {
				// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
				PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
				price.setPriceupdateuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
				price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
				price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
				price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
				price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
				price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

				List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
				priceList = priceService.selectFourTablesByUnionData(price); // 查询
				for (int i = 0; i < priceList.size(); i++) {
					OrderPrice orderPrice = new OrderPrice();
					orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
					orderPrice.setOrderpriceupdateuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
					orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
					orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
					orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
					orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
					orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
					orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
					orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
					orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
					orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注

					orderPriceList.add(orderPrice);
				}
			}

			count = operationService.backDistribution(orderTable, task, orderPriceList);

		} else {
			if (!orderTable.getOrdertype().equals("PD")){
				returnData.setMsg("当前订单不是派单模式！");
				return returnData;
			}

			//1、修改完成订单的数量
			OrderDetail orderDetailAll = new OrderDetail();
			orderDetailAll.setOrderid(orderTable.getOrderid());//订单ID
			List<OrderDetail> orderDetails = null;
			orderDetails = orderDetailService.selectByOrderDetail(orderDetailAll);
			if (orderDetails.size() == 0){
				returnData.setMsg("该订单没有清单列表！");
				return returnData;
			}
			int finishNumber = 0;
			for (int i = 0; i < orderDetails.size(); i++){
				for (int j = 0; j < orderDetailListLeave.size(); j++){
					if (orderDetails.get(i).getGoodsid().equals(orderDetailListLeave.get(j).getGoodsid()) && orderDetails.get(i).getProjectid().equals(orderDetailListLeave.get(j).getProjectid())){
						finishNumber = orderDetails.get(i).getOrderdetailnumber() - orderDetailListLeave.get(j).getOrderdetailnumber();
						if (finishNumber < 0 ){
							returnData.setMsg(orderDetailListLeave.get(j).getGoodsname() + "----" + orderDetailListLeave.get(j).getProjectname() +"所填写的剩余清单数量超出之前清单数量！");
							return  returnData;
						} else {
							OrderDetail updateOrderDetail =  new OrderDetail();
							updateOrderDetail.setOrderdetailid(orderDetails.get(i).getOrderdetailid());//订单清单的详情ID
							updateOrderDetail.setOrderdetailnumber(finishNumber);
							updateOrderDetail.setOrderdetailintercepttotalmoney(orderDetails.get(i).getOrderdetailintercepttotalmoney() - orderDetailListLeave.get(j).getOrderdetailintercepttotalmoney());
							orderDetailService.updateByOrderDetail(updateOrderDetail);
						}
					}
				}
			}

			// 2、根据orderTable实体中的建筑类型ID，获取对应的建筑类型系数
			double buildingtypecoefficient = 0; // 建筑类型系数
			BuildingType buildingType = new BuildingType();
			buildingType.setBuildingtypeid(orderTable.getBuildingtypeid()); // 建筑类型ID(JZLX+UUID)
			buildingType.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<BuildingType> buildingList = new ArrayList<BuildingType>();
			buildingList = buildingTypeService.selectByBuildingType(buildingType); // 查询
			if (buildingList.size() == 0) {
				returnData.setMsg("找不到该建筑类型，无法派单！");
				return returnData;
			} else {
				buildingtypecoefficient = buildingList.get(0).getBuildingtypecoefficient();
			}
			//查询已完成清单的总价
			OrderDetail orderDetailFinish = new OrderDetail();
			orderDetailFinish.setOrderid(orderTable.getOrderid());//订单ID
			List<OrderDetail> orderDetailsFinish = null;
			orderDetailsFinish = orderDetailService.selectByOrderDetail(orderDetailFinish);
			double priceTotalMoney = 0; // 维修总价

			///3、循环维修单详情List
			for (int j = 0; j < orderDetailsFinish.size(); j++) {
				// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
				PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
				price.setPriceupdateuserid(orderTable.getOrderserviceuserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
				price.setGoodsid(orderDetailsFinish.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
				price.setProjectid(orderDetailsFinish.get(j).getProjectid()); // 项目ID(XMBG+UUID)
				price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
				price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
				price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

				List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
				priceList = priceService.selectFourTablesByUnionData(price); // 查询
				if (priceList.size() == 0) {
					// 某一款产品ID、项目ID，该人员没有对应的维修单价
					priceTotalMoney = 0;
					break;
				} else {
					// 循环计算维修单价，得出维修总价
					priceTotalMoney += priceList.get(0).getPricemoney() * orderDetailsFinish.get(j).getOrderdetailnumber();

				}
			}

			// 如果维修总价不为0，则计算最终维修总价，并为最终维修人员List添加数据
			if (priceTotalMoney != 0) {

				// 四舍五入，保存两位小数
				BigDecimal bg = new BigDecimal(priceTotalMoney).setScale(2, RoundingMode.UP);
				priceTotalMoney = bg.doubleValue();

				// * 建筑系数
				priceTotalMoney = priceTotalMoney * buildingtypecoefficient;
			}
			OrderTable finishOrderTable = new OrderTable();
			finishOrderTable.setOrderid(orderTable.getOrderid());
			finishOrderTable.setOrderintercepttotalmoney(orderTable.getOrderintercepttotalmoney() - finishOrderInterceptTotalmoney);//完成清单的推荐总价
			finishOrderTable.setOrderpricetotalmoney(priceTotalMoney);//完成清单的总价

			// 4、将剩余的清单添加新的维修单表
			// 数据
			// 维修单ID(D+年月日+时分秒+6位随机数)
			// 生成6位随机数
			String random = String.valueOf((Math.random() * 1000000)).substring(0, 6);
			if (random.indexOf(".") != -1) {
				random = random.replace(".", "0"); // 将随机数中的.转换为0
			}
			String orderIdHalfway = "D" + sdf.format(new Date()) + random; // 维修单ID(D+年月日+时分秒+6位随机数)
			//之前订单中剩下的清单总价
			double priceTotalMoneyHalfway = 0;
			// 5、添加维修单对应维修单价表
			List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
			// 循环维修单详情List
			for (int j = 0; j < orderDetailListLeave.size(); j++) {
				// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
				PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
				price.setPriceupdateuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
				price.setGoodsid(orderDetailListLeave.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
				price.setProjectid(orderDetailListLeave.get(j).getProjectid()); // 项目ID(XMBG+UUID)
				price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
				price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
				price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

				List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
				priceList = priceService.selectFourTablesByUnionData(price); // 查询
				if (priceList.size() == 0) {
					// 某一款产品ID、项目ID，该人员没有对应的维修单价
					priceTotalMoney = 0;
					break;
				} else {
					// 循环计算维修单价，得出维修总价
					priceTotalMoneyHalfway += priceList.get(0).getPricemoney() * orderDetailListLeave.get(j).getOrderdetailnumber();

					for (int i = 0; i < priceList.size(); i++) {
						OrderPrice orderPrice = new OrderPrice();
						orderPrice.setOrderid(orderIdHalfway); // 维修单ID(D+年月日+时分秒+6位随机数)
						orderPrice.setOrderpriceupdateuserid(userAndUserServiceAndUserCustomer.getUserid()); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
						orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
						orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
						orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
						orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
						orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
						orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
						orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
						orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
						orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注

						orderPriceList.add(orderPrice);
					}

				}
			}

			// 如果维修总价不为0，则计算最终维修总价，并为最终维修人员List添加数据
			if (priceTotalMoneyHalfway != 0) {
				// 四舍五入，保存两位小数
				BigDecimal bg = new BigDecimal(priceTotalMoneyHalfway).setScale(2, RoundingMode.UP);
				priceTotalMoneyHalfway = bg.doubleValue();

				// * 建筑系数
				priceTotalMoneyHalfway = priceTotalMoneyHalfway * buildingtypecoefficient;
			}

			OrderTable orderTableHalfway = new OrderTable();
			orderTableHalfway.setOrderid(orderIdHalfway); // 维修单ID(D+年月日+时分秒+6位随机数)
			orderTableHalfway.setOrderprojectname(orderTable.getOrderprojectname());//项目名称
			orderTableHalfway.setOrdercontactperson(orderTable.getOrdercontactperson());//联系人
			orderTableHalfway.setOrdercontactphone(orderTable.getOrdercontactphone());//联系电话
			orderTableHalfway.setOrderaddress(orderTable.getOrderaddress());//地址
			orderTableHalfway.setBuildingtypeid(orderTable.getBuildingtypeid());//建筑类型
			orderTableHalfway.setOrderplantime(orderTable.getOrderplantime());//完工时间
			orderTableHalfway.setOrderprojecttype(orderTable.getOrderprojecttype());//维修类型
			orderTableHalfway.setOrderposition(orderTable.getOrderposition());//维修地址代码
			orderTableHalfway.setOrderlatitude(orderTable.getOrderlatitude());//经度
			orderTableHalfway.setOrderlongitude(orderTable.getOrderlongitude());//纬度
			orderTableHalfway.setOrderintercepttotalmoney(finishOrderInterceptTotalmoney);//推荐价总价
			orderTableHalfway.setOrderpricetotalmoney(priceTotalMoneyHalfway);//维修工总价
			orderTableHalfway.setOrdertype("PD");//派单模式
			orderTableHalfway.setOrderstate("JS");//维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
			orderTableHalfway.setOrderserviceuserid(userAndUserServiceAndUserCustomer.getUserid());//变更的维修人员
			orderTableHalfway.setOrdercreateuserid(orderTable.getOrdercreateuserid());  // 维修单创建人ID(YHBG+UUID)
			orderTableHalfway.setOrdercreatetime(new Date()); // 维修单创建时间
			orderTableHalfway.setOrderupdateuserid(orderTable.getOrderupdateuserid());  // 维修单更新人ID(YHBG+UUID)
			orderTableHalfway.setOrderupdatetime(new Date()); // 维修单更新时间
			orderTableHalfway.setOrderbiddingendtime(orderTable.getOrderbiddingendtime());//竞标结束时间
			orderTableHalfway.setOrderdistance(distance);//距离
			orderTableHalfway.setOrderintercepttraveltotalmoney(0D);
			orderTableHalfway.setOrdertraveltotalmoney(0D);

			// 6、添加维修单详情表
			// 数据
			for (int i = 0; i < orderDetailListLeave.size(); i++) {
				OrderDetail orderDetailLeave = orderDetailListLeave.get(i);
				orderDetailLeave.setOrderdetailid(Config.SIGN_ORDERDETAIL + UUID.randomUUID().toString()); // 维修单详情ID(WXXQ+UUID)
				orderDetailLeave.setOrderid(orderIdHalfway); // 维修单ID(D+年月日+时分秒+6位随机数)
			}


			count = operationService.updateBackDistributionHalfway(finishOrderTable, orderTableHalfway, orderPriceList, orderDetailListLeave);

		}

		// 返回数据
		if (count == 0) {
			returnData.setMsg("更新派单人失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("更新派单人成功！");
		}

		return returnData;
	}


	/**
	 * 发布维修信息
	 *
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、添加维修公告表
	 * 3、更新维修单表(类型、状态)
	 *
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/release")
	public LayuiDataTemplet<OrderTable> release(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

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
			returnData.setMsg("当前操作人ID为空，无法发布！");
			return returnData;
		}
		//付款方式
		String paymentMethod = orderTable.getOrderpaymentmethodcontent();

		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法发布！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}

		//查询一分钟内是否已经申请验收

			Date createTime1 = orderTable.getOrderupdatetime();
			Date nowTime1 = new Date();
			long cha1 = nowTime1.getTime() - createTime1.getTime();
			if (cha1 / (5000) < 1) {
				returnData.setMsg("三秒内不能重复发布信息！");
				return returnData;
			}

		// 2、添加维修公告表
		Announcement announcement = new Announcement();
		// 数据
		announcement.setAnnouncementid(Config.SIGN_ANNOUNCEMENT + UUID.randomUUID().toString()); // 维修公告ID(WXGG+UUID)
		announcement.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		announcement.setAnnouncementstate("Y"); // 维修公告状态(Y:进行中/N:已完成)

		// 3、更新维修单表(类型、状态)
		// 数据
		orderTable.setOrdertype("QD"); // 维修类型(PD:派单/QD:抢单)
		orderTable.setOrderstate("FB"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderpaymentmethodcontent(paymentMethod);//付款方式
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间

		// 发布信息
		int count = 0;
		count = operationService.release(announcement, orderTable);

		// 返回数据
		if (count == 0) {
		    if (orderTable.getOrderprojecttype().equals("WX")){
                returnData.setMsg("发布维修信息失败！");
            } else if (orderTable.getOrderprojecttype().equals("AZ")){
                returnData.setMsg("发布安装信息失败！");
            }

		} else {
			// 查询数据
			UserAndUserServiceAndUserCustomer selectData = new UserAndUserServiceAndUserCustomer();
			selectData.setUserrole(orderTable.getOrderprojecttype() + ","); // 查询角色
			selectData.setUserservicestate("Y"); // 查询角色
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
						templateId = "XaOSynaBuaBKdRTvWvWKRFE9BR7QcI1FOlwh39OZcF8";
						formId = wechatFormList.get(0).getFormid();

						SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String token = F_WechatRestful.getAccessToken(wechatService);
						//String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
						String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", orderTable.getOrderprojectname(), dateFormate.format(orderTable.getOrdercreatetime()), dateFormate.format(orderTable.getOrderbiddingendtime()), "");
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
            if (orderTable.getOrderprojecttype().equals("WX")) {
                returnData.setMsg("发布维修信息成功！");
            } else if (orderTable.getOrderprojecttype().equals("AZ")){
                returnData.setMsg("发布安装信息成功！");
            }

		}

		return returnData;
	}

	/**
	 * 维修工、安装队抢单
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
	 * 传递User实体(抢单的维修工、安装队)
	 * 传递OrderTable实体
	 *
	 * 需在User实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param models
	 * @return
	 *
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/snatch")
	public LayuiDataTemplet<OrderTable> snatch(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// JSON转换为实体类
		User user = null;
		OrderTable orderTable = null;
		try {
			user = JsonUtil.json2obj((String)models.get("user"), User.class);
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
		} catch (Exception e1) {
			e1.printStackTrace();

			returnData.setMsg("JSON转换为实体类发生异常！");
			return returnData;
		}

		// 判断传递的数据
		// 传递User实体(维修工、安装队)
		if (user == null) {
			returnData.setMsg("传递的用户数据错误！");
			return returnData;
		}

		// 判断传递的数据
		// 传递OrderTable实体
		if (orderTable == null) {
			returnData.setMsg("传递的维修单数据错误！");
			return returnData;
		}

		// 版本号不为空，则验证版本号
		try {
			if (user.getVersion() != null && !user.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
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
			returnData.setMsg("当前操作人ID为空，无法竞标！");
			return returnData;
		}

		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法竞标！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}

		// 2、判断抢单是否已结束(维修竞标结束时间)
		if (orderTable.getOrderbiddingendtime() == null) {
			returnData.setMsg("维修单的维修竞标结束时间为空，无法竞标！");
			return returnData;
		} else {
			if (orderTable.getOrderbiddingendtime().getTime() - new Date().getTime() < 0) {
				returnData.setMsg("已超过维修单的维修竞标结束时间，无法竞标！");
				return returnData;
			}
		}

		// 3、判断抢单的人员的身份类别是否符合维修单的维修项目类型
		if ("WX".equals(orderTable.getOrderprojecttype())) {
			if ("AZ,".equals(user.getUserrole())) {
				returnData.setMsg("该维修单为维修项目，安装队不能对该维修单竞标！");
				return returnData;
			}
		} else if ("AZ".equals(orderTable.getOrderprojecttype())) {
			if ("WX,".equals(user.getUserrole())) {
				returnData.setMsg("该维修单为安装项目，维修工不能对该维修单竞标！");
				return returnData;
			}
		} else {
			returnData.setMsg("该维修单的维修项目错误，无法竞标！");
			return returnData;
		}

		// 4、判断抢单的人员的信用值是否大于最低信用值
		// 查询最低信用值
		double creditMin = setupService.selectBySetup(new CreditMinSetup()).get(0).getCreditmin();

		// 查询抢单的人员的信用值
		double userCredit = -1;
		UserAndUserServiceAndUserCustomer unionData = new UserAndUserServiceAndUserCustomer();
		unionData.setUserid(user.getUserid()); // 用户ID(YHBG+UUID)
		unionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> selectUserList = null;
		selectUserList = userService.selectThreeTablesByUnionData(unionData); // 查询
		if (selectUserList != null && selectUserList.size() != 0) {
			userCredit = selectUserList.get(0).getUsercredit();
		}

		// 判断
		if (Double.doubleToLongBits(creditMin) > Double.doubleToLongBits(userCredit)) {
			returnData.setMsg("竞标的人员的信用值小于最低信用值，无法竞标！");
			return returnData;
		}

		// 5、计算维修差旅费总价(差旅费(每公里)*距离)
		double travelTotalMoney = 0; // 维修差旅费总价
		if (selectUserList != null && selectUserList.size() != 0) {
			// 距离(单位：米)
			double userDistance = PositionUtil.getDistance(orderTable.getOrderlatitude(), orderTable.getOrderlongitude(),
					selectUserList.get(0).getUserlatitude(), selectUserList.get(0).getUserlongitude());

			// 距离*差旅费(每公里)
			if (selectUserList.get(0).getUserservicetravelmoney() != null) {
				travelTotalMoney = userDistance / 1000 * selectUserList.get(0).getUserservicetravelmoney();
			}
		}

		// 6、计算维修总价
		// 根据不同的维修工、安装队的维修单价，计算维修总价
		// 维修总价 = 维修人员报价 * 报价系数(报价系数在下方)
		double priceTotalMoney = 0; // 维修总价

		// 根据orderTable实体中的建筑类型ID，获取对应的建筑类型系数
		double buildingtypecoefficient = 0; // 建筑类型系数
		BuildingType buildingType = new BuildingType();
		buildingType.setBuildingtypeid(orderTable.getBuildingtypeid()); // 建筑类型ID(JZLX+UUID)
		buildingType.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<BuildingType> buildingList = new ArrayList<BuildingType>();
		buildingList = buildingTypeService.selectByBuildingType(buildingType); // 查询
		if (buildingList.size() == 0) {
			returnData.setMsg("建筑类型暂无数据，无法竞标！");
			return returnData;
		} else {
			buildingtypecoefficient = buildingList.get(0).getBuildingtypecoefficient();
		}

		// 根据OrderTable实体中的维修单ID，获取维修单详情中的产品ID、项目ID
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
		if (orderDetailList.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据，无法竞标！");
			return returnData;
		} else {
			// 根据人员、产品ID、项目ID，计算维修总价
			// 循环维修单详情List，根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价，循环计算维修单价，得出维修总价
			for (int i = 0; i < orderDetailList.size(); i++) {
				// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
				PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
				price.setPriceupdateuserid(user.getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
				price.setGoodsid(orderDetailList.get(i).getGoodsid()); // 产品ID(CPBG+UUID)
				price.setProjectid(orderDetailList.get(i).getProjectid()); // 项目ID(XMBG+UUID)
				price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
				price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
				price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

				List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
				priceList = priceService.selectFourTablesByUnionData(price); // 查询
				if (priceList.size() == 0) {
					// 某一款产品ID、项目ID，该人员没有对应的维修单价
					priceTotalMoney = 0;
					break;
				} else {
					// 循环计算维修单价，得出维修总价
					priceTotalMoney += priceList.get(0).getPricemoney() * orderDetailList.get(i).getOrderdetailnumber();
				}
			}

			// 如果维修总价不为0，则计算最终维修总价，并为最终维修人员List添加数据
			if (priceTotalMoney == 0) {
				returnData.setMsg("该维修单的某些维修详情数据该竞标人员没有对应的维修单价，无法竞标！");
				return returnData;
			} else {
				/**
				 * 维修总价 = 维修人员报价 * 报价系数 * 建筑类型系数
				 *
				 * 信用值在90分以上：报价系数为90%
				 * 信用值在80-89分：报价系数为95%
				 * 信用值在70-79分：报价系数为100%
				 * 信用值在60-69分：报价系数为105%
				 * 信用值在40-59分：报价系数为110%
				 */
				// * 报价系数
				/*if (userCredit >= 90) {
					priceTotalMoney = priceTotalMoney * 0.9;
				} else if (userCredit >= 80 && userCredit < 90) {
					priceTotalMoney = priceTotalMoney * 0.95;
				} else if (userCredit >= 70 && userCredit < 80) {
					priceTotalMoney = priceTotalMoney * 1;
				} else if (userCredit >= 60 && userCredit < 70) {
					priceTotalMoney = priceTotalMoney * 1.05;
				} else if (userCredit >= 40 && userCredit < 60) {
					priceTotalMoney = priceTotalMoney * 1.1;
				} else {
					priceTotalMoney = priceTotalMoney * 1.2;
				}*/

				// 四舍五入，保存两位小数
				BigDecimal bg = new BigDecimal(priceTotalMoney).setScale(2, RoundingMode.UP);
				priceTotalMoney = bg.doubleValue();

				// * 建筑类型系数
				priceTotalMoney = priceTotalMoney * buildingtypecoefficient;
			}
		}
		
		// 7、添加抢单表
		Snatch snatch = new Snatch();
		// 数据
		snatch.setSnatchid(Config.SIGN_SNATCH + UUID.randomUUID().toString()); // 抢单记录ID(QDJL+UUID)
		snatch.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		snatch.setServiceuserid(user.getUserid()); // 用户ID(YHBG+UUID)
		snatch.setSnatchmoney(priceTotalMoney); // 抢单维修总价
		snatch.setSnatchtravelmoney(travelTotalMoney); // 抢单维修差旅费总价
		snatch.setSnatchstate("N"); // 抢单状态(Y:已抢到/N:未抢到)
		snatch.setSnatchenrolltime(new Date()); // 抢单报名时间
				
		// 8、更新维修单表(状态)
		// 数据
		orderTable.setOrderstate("QD"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间
		
		// 维修工、安装队抢单
		int count = 0;
		count = operationService.snatch(snatch, orderTable);
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("维修工、安装队竞标失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("维修工、安装队竞标成功！");
		}

		return returnData;
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
	 * 传递User实体(抢单的维修工、安装队)
	 * 传递OrderTable实体
	 * 
	 * 需在User实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 * 
	 * @param models
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/confirmSnatch")
	public LayuiDataTemplet<OrderTable> confirmSnatch(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null
		
		// JSON转换为实体类
		User user = null;
		OrderTable orderTable = null;
		try {
			user = JsonUtil.json2obj((String)models.get("user"), User.class);
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
		} catch (Exception e1) {
			e1.printStackTrace();
					
			returnData.setMsg("JSON转换为实体类发生异常！");
			return returnData;
		} 
		
		// 判断传递的数据
		// 传递User实体(抢单的维修工、安装队)
		if (user == null) {
			returnData.setMsg("传递的用户数据错误！");
			return returnData;
		} 
				
		// 判断传递的数据
		// 传递OrderTable实体
		if (orderTable == null) {
			returnData.setMsg("传递的维修单数据错误！");
			return returnData;
		}
		
		// 版本号不为空，则验证版本号
		try {
			if (user.getVersion() != null && !user.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
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
		/*if (currentUserId == null || currentUserId.isEmpty()) {
			returnData.setMsg("当前操作人ID为空，无法确认竞标！");
			return returnData;
		}*/
		
		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法确认竞标！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}
		
		// 2、根据OrderTable实体中的维修人员ID(YHBG+UUID)，获取对应的维修人员数据
		UserAndUserServiceAndUserCustomer userUnionData = new UserAndUserServiceAndUserCustomer();
		userUnionData.setUserid(user.getUserid()); // 用户ID(YHBG+UUID)
		userUnionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> selectUserList = null;
		selectUserList = userService.selectThreeTablesByUnionData(userUnionData); // 查询
		if (selectUserList != null && selectUserList.size() != 0) {
			userUnionData = selectUserList.get(0);
		}
		
		// 3、更新抢单表
		Snatch snatch = new Snatch();
		// 数据
		snatch.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		snatch.setServiceuserid(user.getUserid()); // 用户ID(YHBG+UUID)
		snatch.setSnatchstate("Y"); // 抢单状态(Y:已抢到/N:未抢到)
		snatch.setSnatchconfirmuserid(currentUserId); // 当前操作人员ID // 抢单确认人员ID(YHBG+UUID)
		snatch.setSnatchconfirmtime(new Date()); // 抢单确认时间
		
		//查询是客户是否确认竞标人
		SnatchAndOrderTableAndUser snatchAndOrderTableAndUser = new SnatchAndOrderTableAndUser();
		snatchAndOrderTableAndUser.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		snatchAndOrderTableAndUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		snatchAndOrderTableAndUser.setSnatchstate("Y");; // 查询确认竞标人   Y
		List<SnatchAndOrderTableAndUser> snatchAndOrderTableAndUserList = new ArrayList<SnatchAndOrderTableAndUser>();
		snatchAndOrderTableAndUserList = snatchService.selectThreeTablesByUnionData(snatchAndOrderTableAndUser); // 查询
		if(snatchAndOrderTableAndUserList.size() > 0) {
			returnData.setMsg("该订单已经确认竞标人，无法重新选择竞标人！");
			return returnData;
		}
		
		// 4、更新公告表(状态)
		Announcement announcement = new Announcement();
		// 数据
		announcement.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		announcement.setAnnouncementstate("N"); // 维修公告状态(Y:进行中/N:已完成)
		
		// 5、更新维修单(状态、维修人员、维修总价、维修差旅费总价)
		// 数据
		// 查询抢单表中的维修金额
		SnatchAndOrderTableAndUser selectSnatch = new SnatchAndOrderTableAndUser();
		selectSnatch.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		selectSnatch.setServiceuserid(user.getUserid()); // 用户ID(YHBG+UUID)
		selectSnatch.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();
		snatchList = snatchService.selectThreeTablesByUnionData(selectSnatch); // 查询
		if (snatchList.size() == 0) {
			returnData.setMsg("该维修单的竞标表中没有维修总价，无法确认竞标！");
			return returnData;
		} else {
			double userDistance = PositionUtil.getDistance(orderTable.getOrderlatitude(), orderTable.getOrderlongitude(), 
					userUnionData.getUserlatitude(), userUnionData.getUserlongitude()); // 距离(单位：米)
			double priceTotalMoney = snatchList.get(0).getSnatchmoney(); // 维修总价
			double travelTotalMoney = snatchList.get(0).getSnatchtravelmoney(); // 维修差旅费总价
           /* double orderdaymoney = snatchList.get(0).getSnatchdaymoney();//日工资
            int orderday = snatchList.get(0).getSnatchday();//天数
            int orderpeoplenumber = snatchList.get(0).getSnatchpeoplenumber();//人数
            double ordermaterialmoney = snatchList.get(0).getSnatchmaterialmoney();//材料费*/
			
			orderTable.setOrderserviceuserid(user.getUserid()); // 维修人员ID(YHBG+UUID)
			orderTable.setOrderstate("JS"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
			orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
			orderTable.setOrderdistance(userDistance/1000); // 维修距离
			orderTable.setOrderpricetotalmoney(priceTotalMoney); // 维修总价(根据维修单价计算得出)
			orderTable.setOrdertraveltotalmoney(travelTotalMoney); // 维修差旅费总价(根据差旅费*距离计算得出)
            orderTable.setOrderdaymoney(snatchList.get(0).getSnatchdaymoney());//日工资
            orderTable.setOrderday(snatchList.get(0).getSnatchday());//天数
            orderTable.setOrderpeoplenumber(snatchList.get(0).getSnatchpeoplenumber());//人数
            orderTable.setOrdermaterialmoney(snatchList.get(0).getSnatchmaterialmoney());//材料费
			orderTable.setOrderupdatetime(new Date()); // 维修更新时间
		}
		
		/*// 6、添加维修任务表
		Task task = new Task();
		// 数据
		task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
		task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)*/
		
		// 7、添加维修单对应维修单价表
	/*
		List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
		PriceAndGoodsAndProjectAndUser priceUnionData = new PriceAndGoodsAndProjectAndUser();
		priceUnionData.setPriceupdateuserid(user.getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
		List<PriceAndGoodsAndProjectAndUser> priceList = priceService.selectFourTablesByUnionData(priceUnionData); // 查询
		for (int i = 0; i < priceList.size(); i++) {
			OrderPrice orderPrice = new OrderPrice();
			orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			orderPrice.setOrderpricecreateuserid(user.getUserid()); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
			orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
			orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
			orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
			orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
			orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
			orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
			orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
			orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
			orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注
							
			orderPriceList.add(orderPrice);
		}
	*/
		
		// 客户确认抢单
		int count = 0;
		count = operationService.confirmSnatch(snatch, announcement, orderTable);
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("客户确认竞标失败！");
		} else {
            // 查询数据
            UserAndUserServiceAndUserCustomer selectData = new UserAndUserServiceAndUserCustomer();
            selectData.setUserid(user.getUserid()); // 查询
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
                        templateId = "kT2OTxraicVvL3VmwzO7pQmTo1gDx7XXnYxSpeRONro";
                        formId = wechatFormList.get(0).getFormid();

                        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String token = F_WechatRestful.getAccessToken(wechatService);
                        //String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
                        String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", "恭喜您中标", orderTable.getOrdercontactperson(), dateFormate.format(orderTable.getOrderplantime()), "");
                        boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
                        List<Integer> idList = new ArrayList<Integer>();
                        idList.add(wechatFormList.get(0).getId());
                        wechatFormService.deleteByIdList(idList);

                        logger.error("服务通知result" + result);
                        System.out.println("服务通知result" + result);

                    }
                }
            }

            //给没有中标的人发送服务通知
            //查询是未中标标的人
            SnatchAndOrderTableAndUser snatchAndOrderTableAndUserNo = new SnatchAndOrderTableAndUser();
            snatchAndOrderTableAndUser.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
            snatchAndOrderTableAndUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
            snatchAndOrderTableAndUser.setSnatchstate("N");; // 查询确认竞标人   Y
            List<SnatchAndOrderTableAndUser> snatchAndOrderTableAndUserNoList = new ArrayList<SnatchAndOrderTableAndUser>();
            snatchAndOrderTableAndUserNoList = snatchService.selectThreeTablesByUnionData(snatchAndOrderTableAndUser); // 查询
            for (int k =0; k< snatchAndOrderTableAndUserNoList.size(); k++){
                // 查询数据
                UserAndUserServiceAndUserCustomer selectDataNo = new UserAndUserServiceAndUserCustomer();
                selectDataNo.setUserid(snatchAndOrderTableAndUserNoList.get(k).getServiceuserid()); // 查询维修中标人
                selectDataNo.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

                // 查询数据
                List<UserAndUserServiceAndUserCustomer> userNoList = userService.selectThreeTablesBySelectData(selectDataNo);
                if (userNoList.size() != 0){
                    for (int i = 0; i < userNoList.size(); i ++){
                        WechatForm wechatForm = new WechatForm();
                        wechatForm.setPagenumber(-1);//不分页
                        wechatForm.setUserid(userNoList.get(i).getUserid());
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
                            templateId = "kT2OTxraicVvL3VmwzO7pQmTo1gDx7XXnYxSpeRONro";
                            formId = wechatFormList.get(0).getFormid();

                            SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            String token = F_WechatRestful.getAccessToken(wechatService);
                            //String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
                            String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", "很遗憾您未中标，请等待下次机会！", orderTable.getOrdercontactperson(), dateFormate.format(orderTable.getOrderplantime()), "");
                            boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
                            List<Integer> idList = new ArrayList<Integer>();
                            idList.add(wechatFormList.get(0).getId());
                            wechatFormService.deleteByIdList(idList);

                            logger.error("服务通知result" + result);
                            System.out.println("服务通知result" + result);

                        }
                    }
                }
            }
			returnData.setCount(count);
			returnData.setMsg("客户确认竞标成功！");
		}

		return returnData;
	}

	/**
	 * 后台管理订单中更改竞标人
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
	 * 传递User实体(抢单的维修工、安装队)
	 * 传递OrderTable实体
	 *
	 * 需在User实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param models
	 * @return
	 *
	 * @author ZY on 2018/11/09
	 */
	@ResponseBody
	@RequestMapping("/updateConfirmSnatch")
	public LayuiDataTemplet<OrderTable> updateConfirmSnatch(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// JSON转换为实体类
		User user = null;
		OrderTable orderTable = null;
		try {
			user = JsonUtil.json2obj((String)models.get("user"), User.class);
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
		} catch (Exception e1) {
			e1.printStackTrace();

			returnData.setMsg("JSON转换为实体类发生异常！");
			return returnData;
		}

		// 判断传递的数据
		// 传递User实体(抢单的维修工、安装队)
		if (user == null) {
			returnData.setMsg("传递的用户数据错误！");
			return returnData;
		}

		// 判断传递的数据
		// 传递OrderTable实体
		if (orderTable == null) {
			returnData.setMsg("传递的维修单数据错误！");
			return returnData;
		}

		// 版本号不为空，则验证版本号
		try {
			if (user.getVersion() != null && !user.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
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
		/*if (currentUserId == null || currentUserId.isEmpty()) {
			returnData.setMsg("当前操作人ID为空，无法确认竞标！");
			return returnData;
		}*/

		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法更改竞标人！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}

		//2、查询之前中标人的中标信息
		SnatchAndOrderTableAndUser beforeSnatchAndOrderTableAndUser = new SnatchAndOrderTableAndUser();
		beforeSnatchAndOrderTableAndUser.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		beforeSnatchAndOrderTableAndUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		beforeSnatchAndOrderTableAndUser.setServiceuserid(orderTable.getOrderserviceuserid());; // 之前中标人的userid
		List<SnatchAndOrderTableAndUser> beforeSnatchAndOrderTableAndUserList = new ArrayList<SnatchAndOrderTableAndUser>();
		beforeSnatchAndOrderTableAndUserList = snatchService.selectThreeTablesByUnionData(beforeSnatchAndOrderTableAndUser); // 查询
		if (beforeSnatchAndOrderTableAndUserList.size() == 0){
			returnData.setMsg("未找到订单之前的中标人！");
			return  returnData;
		}
		//3、竞标表更新之前中标人状态为N
		Snatch beforeSnatch = new Snatch();
		beforeSnatch.setOrderid(orderTable.getOrderid());// 维修单ID(D+年月日+时分秒+6位随机数)
		beforeSnatch.setServiceuserid(orderTable.getOrderserviceuserid());//之前中标人userid
		beforeSnatch.setSnatchstate("N");//状态变更为未中标
		beforeSnatch.setSnatchconfirmuserid(currentUserId);//更新人当前操作人
		beforeSnatch.setSnatchconfirmtime(new Date());//更新时间


		// 4、根据OrderTable实体中的维修人员ID(YHBG+UUID)，获取对应的维修人员数据
		UserAndUserServiceAndUserCustomer userUnionData = new UserAndUserServiceAndUserCustomer();
		userUnionData.setUserid(user.getUserid()); // 用户ID(YHBG+UUID)
		userUnionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> selectUserList = null;
		selectUserList = userService.selectThreeTablesByUnionData(userUnionData); // 查询
		if (selectUserList != null && selectUserList.size() != 0) {
			userUnionData = selectUserList.get(0);
		}

		// 5、更新抢单表
		Snatch snatch = new Snatch();
		// 数据
		snatch.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		snatch.setServiceuserid(user.getUserid()); // 用户ID(YHBG+UUID)
		snatch.setSnatchstate("Y"); // 抢单状态(Y:已抢到/N:未抢到)
		snatch.setSnatchconfirmuserid(currentUserId); // 当前操作人员ID // 抢单确认人员ID(YHBG+UUID)
		snatch.setSnatchconfirmtime(new Date()); // 抢单确认时间

		// 6、更新公告表(状态)
		Announcement announcement = new Announcement();
		// 数据
		announcement.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		announcement.setAnnouncementstate("N"); // 维修公告状态(Y:进行中/N:已完成)

		// 7、更新维修单(状态、维修人员、维修总价、维修差旅费总价)
		// 数据
		// 查询抢单表中的维修金额
		SnatchAndOrderTableAndUser selectSnatch = new SnatchAndOrderTableAndUser();
		selectSnatch.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		selectSnatch.setServiceuserid(user.getUserid()); // 用户ID(YHBG+UUID)
		selectSnatch.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();
		snatchList = snatchService.selectThreeTablesByUnionData(selectSnatch); // 查询
		if (snatchList.size() == 0) {
			returnData.setMsg("该维修单的竞标表中没有维修总价，无法确认竞标！");
			return returnData;
		} else {
			double userDistance = PositionUtil.getDistance(orderTable.getOrderlatitude(), orderTable.getOrderlongitude(),
					userUnionData.getUserlatitude(), userUnionData.getUserlongitude()); // 距离(单位：米)
			double priceTotalMoney = snatchList.get(0).getSnatchmoney(); // 维修总价
			double travelTotalMoney = snatchList.get(0).getSnatchtravelmoney(); // 维修差旅费总价

			orderTable.setOrderserviceuserid(user.getUserid()); // 维修人员ID(YHBG+UUID)
			//orderTable.setOrderstate("JS"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
			orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
			orderTable.setOrderdistance(userDistance/1000); // 维修距离
			orderTable.setOrderpricetotalmoney(priceTotalMoney); // 维修总价(根据维修单价计算得出)
			orderTable.setOrdertraveltotalmoney(travelTotalMoney); // 维修差旅费总价(根据差旅费*距离计算得出)
			orderTable.setOrderdaymoney(snatchList.get(0).getSnatchdaymoney());//日工资
			orderTable.setOrderday(snatchList.get(0).getSnatchday());//天数
			orderTable.setOrderpeoplenumber(snatchList.get(0).getSnatchpeoplenumber());//人数
			orderTable.setOrdermaterialmoney(snatchList.get(0).getSnatchmaterialmoney());//材料费
			orderTable.setOrderupdatetime(new Date()); // 维修更新时间
		}

		// 8、添加维修任务表
		Task task = new Task();
		// 数据
		task.setTaskid(Config.SIGN_TASK + UUID.randomUUID().toString()); // 维修任务ID(WXRW+UUID)
		task.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		task.setTaskstate("Y"); // 维修任务状态(Y:进行中/N:已完成)

		// 后台管理人员更改中标人
		int count = 0;
		count = operationService.updateConfirmSnatch(beforeSnatch, snatch, orderTable,task);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("更改竞标人失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("更改竞标人成功！");
		}

		return returnData;
	}
    /**
     * (维修工)竞标的时候填写日工资*人数*天数+材料费+差旅费
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
     * 传递User实体(抢单的维修工、安装队)
     * 传递OrderTable实体
     *
     * 需在User实体中添加版本信息(可以为空)
     * 需在OrderTable实体中添加当前操作人员ID
     *
     * @param models
     * @return
     *
     * @author ZY on 2018/10/10
     */
    @ResponseBody
    @RequestMapping("/snatchUpdatePriceWX")
    public LayuiDataTemplet<OrderTable> snatchUpdatePriceWX(@RequestBody Map<String, Object> models) {
        LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null

        // JSON转换为实体类
        User user = null;
        OrderTable orderTable = null;
        OrderDetailListEntity orderDetailListEntity = null;
        try {
            user = JsonUtil.json2obj((String)models.get("user"), User.class);
            orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
            orderDetailListEntity = JsonUtil.json2obj((String)models.get("orderDetailListEntity"), OrderDetailListEntity.class);
        } catch (Exception e1) {
            e1.printStackTrace();

            returnData.setMsg("JSON转换为实体类发生异常！");
            return returnData;
        }

        // 判断传递的数据
        // 传递User实体(维修工、安装队)
        if (user == null) {
            returnData.setMsg("传递的用户数据错误！");
            return returnData;
        }

        // 判断传递的数据
        // 传递OrderTable实体
        if (orderTable == null) {
            returnData.setMsg("传递的维修单数据错误！");
            return returnData;
        }

        // 判断传递的数据
        // OrderDetailListEntity实体(List<OrderDetail>)
        List<OrderDetail> orderDetailList = null;
        if (orderDetailListEntity == null) {
            returnData.setMsg("传递的维修单详情数据错误！");
            return returnData;
        } else {
            orderDetailList = orderDetailListEntity.getOrderDetailList();
        }

        // 版本号不为空，则验证版本号
        try {
            if (user.getVersion() != null && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
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
            returnData.setMsg("当前操作人ID为空，无法竞标！");
            return returnData;
        }

        //前台传过来的差旅费
        if (null == orderTable.getAccepttraveltotalmoney()){
            orderTable.setAccepttraveltotalmoney(0D);
        }
        double accepttraveltotalmoney = orderTable.getAccepttraveltotalmoney();
        //日工资
        if (null == orderTable.getOrderdaymoney()){
            orderTable.setOrderdaymoney(0D);
        }
        double orderdaymoney = orderTable.getOrderdaymoney();
        //天数
        if (null == orderTable.getOrderday()){
            orderTable.setOrderday(0);
        }
        int orderday = orderTable.getOrderday();
        // 人数
        if (null == orderTable.getOrderpeoplenumber()){
            orderTable.setOrderpeoplenumber(0);
        }
        int orderpeoplenumber = orderTable.getOrderpeoplenumber();
        //材料费
        if (null == orderTable.getOrdermaterialmoney()){
            orderTable.setOrdermaterialmoney(0D);
        }
        double ordermaterialmoney = orderTable.getOrdermaterialmoney();
        // 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
        OrderTable selectOrderTable = new OrderTable();
        selectOrderTable.setOrderid(orderTable.getOrderid());
        selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
        List<OrderTable> orderList = null;
        orderList = orderTableService.selectByOrderTable(selectOrderTable);
        if (orderList == null || orderList.size() == 0) {
            returnData.setMsg("找不到该维修单，无法竞标！");
            return returnData;
        } else {
            orderTable = orderList.get(0);
        }

        // 2、判断抢单是否已结束(维修竞标结束时间)
        if (orderTable.getOrderbiddingendtime() == null) {
            returnData.setMsg("维修单的维修竞标结束时间为空，无法竞标！");
            return returnData;
        } else {
            if (orderTable.getOrderbiddingendtime().getTime() - new Date().getTime() < 0) {
                returnData.setMsg("已超过维修单的维修竞标结束时间，无法竞标！");
                return returnData;
            }
        }

        // 3、判断抢单的人员的身份类别是否符合维修单的维修项目类型
        if ("WX".equals(orderTable.getOrderprojecttype())) {
            if ("AZ,".equals(user.getUserrole())) {
                returnData.setMsg("该维修单为维修项目，安装队不能抢该维修单！");
                return returnData;
            }
        } else if ("AZ".equals(orderTable.getOrderprojecttype())) {
            if ("WX,".equals(user.getUserrole())) {
                returnData.setMsg("该维修单为安装项目，维修工不能抢该维修单！");
                return returnData;
            }
        } else {
            returnData.setMsg("该维修单的维修项目错误，无法竞标！");
            return returnData;
        }

        // 4、判断抢单的人员的信用值是否大于最低信用值
        // 查询最低信用值
        double creditMin = setupService.selectBySetup(new CreditMinSetup()).get(0).getCreditmin();

        // 查询抢单的人员的信用值
        double userCredit = -1;
        UserAndUserServiceAndUserCustomer unionData = new UserAndUserServiceAndUserCustomer();
        unionData.setUserid(user.getUserid()); // 用户ID(YHBG+UUID)
        unionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
        List<UserAndUserServiceAndUserCustomer> selectUserList = null;
        selectUserList = userService.selectThreeTablesByUnionData(unionData); // 查询
        if (selectUserList != null && selectUserList.size() != 0) {
            userCredit = selectUserList.get(0).getUsercredit();
        }

        // 判断
        if (Double.doubleToLongBits(creditMin) > Double.doubleToLongBits(userCredit)) {
            returnData.setMsg("竞标的人员的信用值小于最低信用值，无法竞标！");
            return returnData;
        }

        // 5、计算维修差旅费总价(差旅费(每公里)*距离)
        double travelTotalMoney = 0; // 维修差旅费总价
        if (selectUserList != null && selectUserList.size() != 0) {
            // 距离(单位：米)
            double userDistance = PositionUtil.getDistance(orderTable.getOrderlatitude(), orderTable.getOrderlongitude(),
                    selectUserList.get(0).getUserlatitude(), selectUserList.get(0).getUserlongitude());

            // 距离*差旅费(每公里)
            if (selectUserList.get(0).getUserservicetravelmoney() != null) {
                travelTotalMoney = userDistance / 1000 * selectUserList.get(0).getUserservicetravelmoney();
            }
        }

        // 6、计算维修总价
        // 根据不同的维修工、安装队的维修单价，计算维修总价
        // 维修总价 = 维修人员报价 * 报价系数(报价系数在下方)
        double priceTotalMoney = 0; // 维修总价

        // 根据orderTable实体中的建筑类型ID，获取对应的建筑类型系数
        double buildingtypecoefficient = 0; // 建筑类型系数
        BuildingType buildingType = new BuildingType();
        buildingType.setBuildingtypeid(orderTable.getBuildingtypeid()); // 建筑类型ID(JZLX+UUID)
        buildingType.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
        List<BuildingType> buildingList = new ArrayList<BuildingType>();
        buildingList = buildingTypeService.selectByBuildingType(buildingType); // 查询
        if (buildingList.size() == 0) {
            returnData.setMsg("建筑类型暂无数据，无法竞标！");
            return returnData;
        } else {
            buildingtypecoefficient = buildingList.get(0).getBuildingtypecoefficient();
        }

       /* // 针对本次维修单将维修清单数据保存到order_price表中(维修工本次维修的价钱)
        List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
        for (int i = 0; i < orderDetailList.size(); i++) {
            OrderPrice orderPrice = new OrderPrice();
            orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
            orderPrice.setOrderpriceupdateuserid(user.getUserid()); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
            orderPrice.setGoodsid(orderDetailList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
            orderPrice.setProjectid(orderDetailList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
            orderPrice.setOrderpricetype(orderTable.getOrderprojecttype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
            orderPrice.setOrderpricemoney(orderDetailList.get(i).getOrderpricemoney()); // 维修单对应维修单价的单价
            orderPrice.setOrderpricestate("Y"); // 维修单对应维修单价的状态(Y:有效/N:无效)
            orderPrice.setOrderpricecreateuserid(currentUserId); // 维修单对应维修单价的创建人ID(YHBG+UUID)
            orderPrice.setOrderpricecreatetime(new Date()); // 维修单对应维修单价的创建时间
            orderPrice.setOrderpriceupdatetime(new Date()); // 维修单对应维修单价的更新时间
            orderPrice.setOrderpriceremark(orderDetailList.get(i).getOrderdetailremark()); // 维修单对应维修单价的备注

            orderPriceList.add(orderPrice);

            // 计算总价
            priceTotalMoney += orderDetailList.get(i).getOrderpricemoney() * orderDetailList.get(i).getOrderdetailnumber();
        }*/

        /**
         * 维修总价 = 维修人员报价 * 报价系数 * 建筑类型系数
         *
         * 信用值在90分以上：报价系数为90%
         * 信用值在80-89分：报价系数为95%
         * 信用值在70-79分：报价系数为100%
         * 信用值在60-69分：报价系数为105%
         * 信用值在40-59分：报价系数为110%
         */
        // * 报价系数
		/*if (userCredit >= 90) {
			priceTotalMoney = priceTotalMoney * 0.9;
		} else if (userCredit >= 80 && userCredit < 90) {
			priceTotalMoney = priceTotalMoney * 0.95;
		} else if (userCredit >= 70 && userCredit < 80) {
			priceTotalMoney = priceTotalMoney * 1;
		} else if (userCredit >= 60 && userCredit < 70) {
			priceTotalMoney = priceTotalMoney * 1.05;
		} else if (userCredit >= 40 && userCredit < 60) {
			priceTotalMoney = priceTotalMoney * 1.1;
		} else {
			priceTotalMoney = priceTotalMoney * 1.2;
		}*/

		//维修单总价（不加差旅费）日工资*天数*人数 + 材料费
        priceTotalMoney = (orderdaymoney * orderday * orderpeoplenumber) + ordermaterialmoney;
        // 四舍五入，保存两位小数
        BigDecimal bg = new BigDecimal(priceTotalMoney).setScale(2, RoundingMode.UP);
        priceTotalMoney = bg.doubleValue();

        // * 建筑类型系数
        priceTotalMoney = priceTotalMoney * buildingtypecoefficient;

        // 7、添加抢单表
        Snatch snatch = new Snatch();
        // 数据
        snatch.setSnatchid(Config.SIGN_SNATCH + UUID.randomUUID().toString()); // 抢单记录ID(QDJL+UUID)
        snatch.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
        snatch.setServiceuserid(user.getUserid()); // 用户ID(YHBG+UUID)
        snatch.setSnatchmoney(priceTotalMoney); // 抢单维修总价
        //snatch.setSnatchtravelmoney(travelTotalMoney); // 抢单维修差旅费总价
        snatch.setSnatchtravelmoney(accepttraveltotalmoney); // 抢单维修差旅费总价(从前端传过来的总价主要针对竞标时添加)
        snatch.setSnatchdaymoney(orderdaymoney);//日工资
        snatch.setSnatchday(orderday);//天数
        snatch.setSnatchpeoplenumber(orderpeoplenumber);//人数
        snatch.setSnatchmaterialmoney(ordermaterialmoney);//材料费
        snatch.setSnatchstate("N"); // 抢单状态(Y:已抢到/N:未抢到)
        snatch.setSnatchenrolltime(new Date()); // 抢单报名时间

        //查询该维修工是否竞标判断不能多次点击竞标
        SnatchAndOrderTableAndUser snatchAndOrderTableAndUser = new SnatchAndOrderTableAndUser();
        snatchAndOrderTableAndUser.setPagenumber(-1);//不分页
        snatchAndOrderTableAndUser.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
        snatchAndOrderTableAndUser.setServiceuserid(user.getUserid()); // 用户ID(YHBG+UUID)
        List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();
        snatchList = snatchService.selectThreeTablesByUnionData(snatchAndOrderTableAndUser);
        if (snatchList.size() > 0){
            returnData.setMsg("维修工、安装队已对该维修单竞标，不能重复竞标！");

            return returnData;
        }

        //查询该订单是否已经确认竞标人，已经确认竞标人的订单别人无法参与
        SnatchAndOrderTableAndUser snatchAndOrderTableAndUser2 = new SnatchAndOrderTableAndUser();
        snatchAndOrderTableAndUser2.setPagenumber(-1);//不分页
        snatchAndOrderTableAndUser2.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
        snatchAndOrderTableAndUser2.setSnatchstate("Y"); // 已确认竞标人
        List<SnatchAndOrderTableAndUser> snatchList2 = new ArrayList<SnatchAndOrderTableAndUser>();
        snatchList2 = snatchService.selectThreeTablesByUnionData(snatchAndOrderTableAndUser2);
        if (snatchList2.size() > 0){
            returnData.setMsg("该维修单已经确认竞标人，无法参与竞标！");

            return returnData;
        }

        // 8、更新维修单表(状态)
        // 数据
        orderTable.setOrderstate("QD"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
        orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
        orderTable.setOrderupdatetime(new Date()); // 维修更新时间

        // 维修工、安装队抢单
        int count = 0;
        count = operationService.snatchUpdatePriceWX(snatch, orderTable);

        // 返回数据
        if (count == 0) {
            returnData.setMsg("维修工、安装队竞标失败！");
        } else {

            //查询竞标总数
            SnatchAndOrderTableAndUser snatchAndOrderTableAndUserAll = new SnatchAndOrderTableAndUser();
            snatchAndOrderTableAndUserAll.setPagenumber(-1);//不分页
            snatchAndOrderTableAndUserAll.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
            List<SnatchAndOrderTableAndUser> snatchAllList = new ArrayList<SnatchAndOrderTableAndUser>();
            snatchAllList = snatchService.selectThreeTablesByUnionData(snatchAndOrderTableAndUserAll);

            // 查询数据
            UserAndUserServiceAndUserCustomer selectData = new UserAndUserServiceAndUserCustomer();
            selectData.setUserid(orderTable.getOrdercreateuserid()); // 查询用户
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
                        templateId = "UIMqKoW_cRYMlO6Zs9IfHGmUgUsUxfMZeC1JQ-erGTc";
                        formId = wechatFormList.get(0).getFormid();

                        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String token = F_WechatRestful.getAccessToken(wechatService);
                        //String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
                        String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", orderTable.getOrderprojectname(), dateFormate.format(snatch.getSnatchenrolltime()), snatchAllList.size() + "人", "该订单又有1人参与竞标！");
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
            returnData.setMsg("维修工、安装队竞标成功！");
        }

        return returnData;
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
	 * 传递User实体(抢单的维修工、安装队)
	 * 传递OrderTable实体
	 * 
	 * 需在User实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 * 
	 * @param models
	 * @return
	 * 
	 * @author ZY on 2018/10/10
	 */
	@ResponseBody
	@RequestMapping("/snatchUpdatePrice")
	public LayuiDataTemplet<OrderTable> snatchUpdatePrice(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null
		
		// JSON转换为实体类
		User user = null;
		OrderTable orderTable = null;
		OrderDetailListEntity orderDetailListEntity = null;
		try {
			user = JsonUtil.json2obj((String)models.get("user"), User.class);
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
			orderDetailListEntity = JsonUtil.json2obj((String)models.get("orderDetailListEntity"), OrderDetailListEntity.class);
		} catch (Exception e1) {
			e1.printStackTrace();
					
			returnData.setMsg("JSON转换为实体类发生异常！");
			return returnData;
		} 
		
		// 判断传递的数据
		// 传递User实体(维修工、安装队)
		if (user == null) {
			returnData.setMsg("传递的用户数据错误！");
			return returnData;
		} 
				
		// 判断传递的数据
		// 传递OrderTable实体
		if (orderTable == null) {
			returnData.setMsg("传递的维修单数据错误！");
			return returnData;
		}
		
		// 判断传递的数据
		// OrderDetailListEntity实体(List<OrderDetail>)
		List<OrderDetail> orderDetailList = null;
		if (orderDetailListEntity == null) {
			returnData.setMsg("传递的维修单详情数据错误！");
			return returnData;
		} else {
			orderDetailList = orderDetailListEntity.getOrderDetailList();
		}
		
		// 版本号不为空，则验证版本号
		try {
			if (user.getVersion() != null && !user.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
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
			returnData.setMsg("当前操作人ID为空，无法竞标！");
			return returnData;
		}
		
		//前台传过来的差旅费
		if (null == orderTable.getAccepttraveltotalmoney()){
			orderTable.setAccepttraveltotalmoney(0D);
		}
		double accepttraveltotalmoney = orderTable.getAccepttraveltotalmoney();
        //日工资
        if (null == orderTable.getOrderdaymoney()){
            orderTable.setOrderdaymoney(0D);
        }
        double orderdaymoney = orderTable.getOrderdaymoney();
        //天数
        if (null == orderTable.getOrderday()){
            orderTable.setOrderday(0);
        }
        double orderday = orderTable.getOrderday();
        // 人数
        if (null == orderTable.getOrderpeoplenumber()){
            orderTable.setOrderpeoplenumber(0);
        }
        double orderpeoplenumber = orderTable.getOrderpeoplenumber();
        //材料费
        if (null == orderTable.getOrdermaterialmoney()){
            orderTable.setOrdermaterialmoney(0D);
        }
        double ordermaterialmoney = orderTable.getOrdermaterialmoney();
		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法竞标！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}
		
		// 2、判断抢单是否已结束(维修竞标结束时间)
		if (orderTable.getOrderbiddingendtime() == null) {
			returnData.setMsg("维修单的维修竞标结束时间为空，无法竞标！");
			return returnData;
		} else {
			if (orderTable.getOrderbiddingendtime().getTime() - new Date().getTime() < 0) {
				returnData.setMsg("已超过维修单的维修竞标结束时间，无法竞标！");
				return returnData;
			}
		}
		
		// 3、判断抢单的人员的身份类别是否符合维修单的维修项目类型
		if ("WX".equals(orderTable.getOrderprojecttype())) {
			if ("AZ,".equals(user.getUserrole())) {
				returnData.setMsg("该维修单为维修项目，安装队不能抢该维修单！");
				return returnData;
			}
		} else if ("AZ".equals(orderTable.getOrderprojecttype())) {
			if ("WX,".equals(user.getUserrole())) {
				returnData.setMsg("该维修单为安装项目，维修工不能抢该维修单！");
				return returnData;
			}
		} else {
			returnData.setMsg("该维修单的维修项目错误，无法竞标！");
			return returnData;
		}
		
		// 4、判断抢单的人员的信用值是否大于最低信用值
		// 查询最低信用值
		double creditMin = setupService.selectBySetup(new CreditMinSetup()).get(0).getCreditmin();
		
		// 查询抢单的人员的信用值
		double userCredit = -1;
		UserAndUserServiceAndUserCustomer unionData = new UserAndUserServiceAndUserCustomer();
		unionData.setUserid(user.getUserid()); // 用户ID(YHBG+UUID)
		unionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> selectUserList = null;
		selectUserList = userService.selectThreeTablesByUnionData(unionData); // 查询
		if (selectUserList != null && selectUserList.size() != 0) {
			userCredit = selectUserList.get(0).getUsercredit();
		}
		
		// 判断
		if (Double.doubleToLongBits(creditMin) > Double.doubleToLongBits(userCredit)) {
			returnData.setMsg("竞标的人员的信用值小于最低信用值，无法竞标！");
			return returnData;
		}
		
		// 5、计算维修差旅费总价(差旅费(每公里)*距离)
		double travelTotalMoney = 0; // 维修差旅费总价
		if (selectUserList != null && selectUserList.size() != 0) {
			// 距离(单位：米)
			double userDistance = PositionUtil.getDistance(orderTable.getOrderlatitude(), orderTable.getOrderlongitude(), 
				selectUserList.get(0).getUserlatitude(), selectUserList.get(0).getUserlongitude());
						
			// 距离*差旅费(每公里)
			if (selectUserList.get(0).getUserservicetravelmoney() != null) {
				travelTotalMoney = userDistance / 1000 * selectUserList.get(0).getUserservicetravelmoney();
			}
		}
				
		// 6、计算维修总价
		// 根据不同的维修工、安装队的维修单价，计算维修总价
		// 维修总价 = 维修人员报价 * 报价系数(报价系数在下方)
		double priceTotalMoney = 0; // 维修总价
		
		// 根据orderTable实体中的建筑类型ID，获取对应的建筑类型系数
		double buildingtypecoefficient = 0; // 建筑类型系数
		BuildingType buildingType = new BuildingType();
		buildingType.setBuildingtypeid(orderTable.getBuildingtypeid()); // 建筑类型ID(JZLX+UUID)
		buildingType.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<BuildingType> buildingList = new ArrayList<BuildingType>();
		buildingList = buildingTypeService.selectByBuildingType(buildingType); // 查询
		if (buildingList.size() == 0) {
			returnData.setMsg("建筑类型暂无数据，无法竞标！");
			return returnData;
		} else {
			buildingtypecoefficient = buildingList.get(0).getBuildingtypecoefficient();
		}
		
		// 针对本次维修单将维修清单数据保存到order_price表中(维修工本次维修的价钱)
		List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
		for (int i = 0; i < orderDetailList.size(); i++) {
			OrderPrice orderPrice = new OrderPrice();
			orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			orderPrice.setOrderpriceupdateuserid(user.getUserid()); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
			orderPrice.setGoodsid(orderDetailList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
			orderPrice.setProjectid(orderDetailList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
			orderPrice.setOrderpricetype(orderTable.getOrderprojecttype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
			orderPrice.setOrderpricemoney(orderDetailList.get(i).getOrderpricemoney()); // 维修单对应维修单价的单价
			orderPrice.setOrderpricestate("Y"); // 维修单对应维修单价的状态(Y:有效/N:无效)
			orderPrice.setOrderpricecreateuserid(currentUserId); // 维修单对应维修单价的创建人ID(YHBG+UUID)
			orderPrice.setOrderpricecreatetime(new Date()); // 维修单对应维修单价的创建时间
			orderPrice.setOrderpriceupdatetime(new Date()); // 维修单对应维修单价的更新时间
			orderPrice.setOrderpriceremark(orderDetailList.get(i).getOrderdetailremark()); // 维修单对应维修单价的备注
							
			orderPriceList.add(orderPrice);
			
			// 计算总价
			priceTotalMoney += orderDetailList.get(i).getOrderpricemoney() * orderDetailList.get(i).getOrderdetailnumber();
		}
		
		/**
		 * 维修总价 = 维修人员报价 * 报价系数 * 建筑类型系数
		 * 
		 * 信用值在90分以上：报价系数为90%
		 * 信用值在80-89分：报价系数为95%
		 * 信用值在70-79分：报价系数为100% 
		 * 信用值在60-69分：报价系数为105%
		 * 信用值在40-59分：报价系数为110%
		 */
		// * 报价系数
		/*if (userCredit >= 90) {
			priceTotalMoney = priceTotalMoney * 0.9;
		} else if (userCredit >= 80 && userCredit < 90) {
			priceTotalMoney = priceTotalMoney * 0.95;
		} else if (userCredit >= 70 && userCredit < 80) {
			priceTotalMoney = priceTotalMoney * 1;
		} else if (userCredit >= 60 && userCredit < 70) {
			priceTotalMoney = priceTotalMoney * 1.05;
		} else if (userCredit >= 40 && userCredit < 60) {
			priceTotalMoney = priceTotalMoney * 1.1;
		} else {
			priceTotalMoney = priceTotalMoney * 1.2;
		}*/
		
		// 四舍五入，保存两位小数
		BigDecimal bg = new BigDecimal(priceTotalMoney).setScale(2, RoundingMode.UP);
		priceTotalMoney = bg.doubleValue();
		
		// * 建筑类型系数
		priceTotalMoney = priceTotalMoney * buildingtypecoefficient;
		
		// 根据OrderTable实体中的维修单ID，获取维修单详情中的产品ID、项目ID
	/*
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
		if (orderDetailList.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据，无法抢单！");
			return returnData;
		} else {
			// 根据人员、产品ID、项目ID，计算维修总价
			// 循环维修单详情List，根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价，循环计算维修单价，得出维修总价
			for (int i = 0; i < orderDetailList.size(); i++) {
				// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
				PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
				price.setPriceupdateuserid(user.getUserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
				price.setGoodsid(orderDetailList.get(i).getGoodsid()); // 产品ID(CPBG+UUID)
				price.setProjectid(orderDetailList.get(i).getProjectid()); // 项目ID(XMBG+UUID)
				price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
				price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
				price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
								 
				List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
				priceList = priceService.selectFourTablesByUnionData(price); // 查询
				if (priceList.size() == 0) {
					// 某一款产品ID、项目ID，该人员没有对应的维修单价
					priceTotalMoney = 0;
					break;
				} else {
					// 循环计算维修单价，得出维修总价
					priceTotalMoney += priceList.get(0).getPricemoney() * orderDetailList.get(i).getOrderdetailnumber();
				}
			}
						 
			// 如果维修总价不为0，则计算最终维修总价，并为最终维修人员List添加数据
			if (priceTotalMoney == 0) {
				returnData.setMsg("该维修单的某些维修详情数据该抢单人员没有对应的维修单价，无法抢单！");
				return returnData;
			} else {
	*/
				/**
				 * 维修总价 = 维修人员报价 * 报价系数 * 建筑类型系数
				 * 
				 * 信用值在90分以上：报价系数为90%
				 * 信用值在80-89分：报价系数为95%
				 * 信用值在70-79分：报价系数为100% 
				 * 信用值在60-69分：报价系数为105%
				 * 信用值在40-59分：报价系数为110%
				 */
	/*
				// * 报价系数
				if (userCredit >= 90) {
					priceTotalMoney = priceTotalMoney * 0.9;
				} else if (userCredit >= 80 && userCredit < 90) {
					priceTotalMoney = priceTotalMoney * 0.95;
				} else if (userCredit >= 70 && userCredit < 80) {
					priceTotalMoney = priceTotalMoney * 1;
				} else if (userCredit >= 60 && userCredit < 70) {
					priceTotalMoney = priceTotalMoney * 1.05;
				} else if (userCredit >= 40 && userCredit < 60) {
					priceTotalMoney = priceTotalMoney * 1.1;
				} else {
					priceTotalMoney = priceTotalMoney * 1.2;
				}
				
				// * 建筑类型系数
				priceTotalMoney = priceTotalMoney * buildingtypecoefficient;
			 }
		 }
	 */
		
		// 7、添加抢单表
		Snatch snatch = new Snatch();
		// 数据
		snatch.setSnatchid(Config.SIGN_SNATCH + UUID.randomUUID().toString()); // 抢单记录ID(QDJL+UUID)
		snatch.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		snatch.setServiceuserid(user.getUserid()); // 用户ID(YHBG+UUID)
		snatch.setSnatchmoney(priceTotalMoney); // 抢单维修总价
		//snatch.setSnatchtravelmoney(travelTotalMoney); // 抢单维修差旅费总价
		snatch.setSnatchtravelmoney(accepttraveltotalmoney); // 抢单维修差旅费总价(从前端传过来的总价主要针对竞标时添加)
		snatch.setSnatchstate("N"); // 抢单状态(Y:已抢到/N:未抢到)
		snatch.setSnatchenrolltime(new Date()); // 抢单报名时间
		
		//查询该维修工是否竞标判断不能多次点击竞标
		SnatchAndOrderTableAndUser snatchAndOrderTableAndUser = new SnatchAndOrderTableAndUser();
		snatchAndOrderTableAndUser.setPagenumber(-1);//不分页
		snatchAndOrderTableAndUser.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		snatchAndOrderTableAndUser.setServiceuserid(user.getUserid()); // 用户ID(YHBG+UUID)
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();
		snatchList = snatchService.selectThreeTablesByUnionData(snatchAndOrderTableAndUser);
		if (snatchList.size() > 0){
			returnData.setMsg("维修工、安装队已对该维修单竞标，不能重复竞标！");
			
			return returnData;
		}
		
		//查询该订单是否已经确认竞标人，已经确认竞标人的订单别人无法参与
		SnatchAndOrderTableAndUser snatchAndOrderTableAndUser2 = new SnatchAndOrderTableAndUser();
		snatchAndOrderTableAndUser2.setPagenumber(-1);//不分页
		snatchAndOrderTableAndUser2.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		snatchAndOrderTableAndUser2.setSnatchstate("Y"); // 已确认竞标人
		List<SnatchAndOrderTableAndUser> snatchList2 = new ArrayList<SnatchAndOrderTableAndUser>();
		snatchList2 = snatchService.selectThreeTablesByUnionData(snatchAndOrderTableAndUser2);
		if (snatchList2.size() > 0){
			returnData.setMsg("该维修单已经确认竞标人，无法参与竞标！");
			
			return returnData;
		}
				
		// 8、更新维修单表(状态)
		// 数据
		orderTable.setOrderstate("QD"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间
		
		// 维修工、安装队抢单
		int count = 0;
		count = operationService.snatchUpdatePrice(snatch, orderTable, orderPriceList);
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("维修工、安装队竞标失败！");
		} else {

		    //查询竞标总数
            SnatchAndOrderTableAndUser snatchAndOrderTableAndUserAll = new SnatchAndOrderTableAndUser();
            snatchAndOrderTableAndUserAll.setPagenumber(-1);//不分页
            snatchAndOrderTableAndUserAll.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
            List<SnatchAndOrderTableAndUser> snatchAllList = new ArrayList<SnatchAndOrderTableAndUser>();
            snatchAllList = snatchService.selectThreeTablesByUnionData(snatchAndOrderTableAndUserAll);

            // 查询数据
            UserAndUserServiceAndUserCustomer selectData = new UserAndUserServiceAndUserCustomer();
            selectData.setUserid(orderTable.getOrdercreateuserid()); // 查询用户
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
                        templateId = "UIMqKoW_cRYMlO6Zs9IfHGmUgUsUxfMZeC1JQ-erGTc";
                        formId = wechatFormList.get(0).getFormid();

                        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String token = F_WechatRestful.getAccessToken(wechatService);
                        //String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
                        String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", orderTable.getOrderprojectname(), dateFormate.format(snatch.getSnatchenrolltime()), snatchAllList.size() + "人", "该订单又有1人参与竞标！");
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
			returnData.setMsg("维修工、安装队竞标成功！");
		}

		return returnData;
	}
	
	/**
	 * 平台派单（主要针对派单后维修工到达现场后，维修数量和项目和单子中不对应，用户对清单和数据量的维护）
	 * 
	 * 操作过程：
	 * 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
	 * 2、匹配维修工、安装队，计算维修总价和维修差旅费总价
	 * 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
	 * 4、添加维修任务表
	 * 
	 * 金额数据：客户支出(平台按 维修推荐总价+维修差旅费推荐总价 收入金额)
	 * 
	 * 5、更新用户表(客户余额)
	 * 6、添加用户_客户_支出_金额表
	 * 7、添加平台_收益金额表
	 * 
	 * 需在OrderTable实体中添加当前操作人员ID
	 * 
	 * @param models
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/backDistributionAndUpdate")
	public LayuiDataTemplet<OrderTable> backDistributionAndUpdate(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null
		
		// JSON转换为实体类
				OrderTable orderTable = null;
				OrderDetailListEntity orderDetailListEntity = null;
				try {
					orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
					orderDetailListEntity = JsonUtil.json2obj((String)models.get("orderDetailListEntity"), OrderDetailListEntity.class);
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
				// OrderDetailListEntity实体(List<OrderDetail>)
				List<OrderDetail> orderDetailList = null;
				if (orderDetailListEntity == null) {
					returnData.setMsg("传递的维修单详情数据错误！");
					return returnData;
				} else {
					orderDetailList = orderDetailListEntity.getOrderDetailList();
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
			returnData.setMsg("当前操作人ID为空，无法派单！");
			return returnData;
		}
		
		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法派单！");
			return returnData;
		} else {
			orderTable = orderList.get(0);
		}
		
		

		
		// 3、更新维修单表(类型、状态、维修人员、维修总价、维修差旅费)
		// 数据
		//orderTable.setOrdertype("PD"); // 维修类型(PD:派单/QD:抢单)
		//orderTable.setOrderstate("JS"); // 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
		//orderTable.setOrderserviceuserid(serviceUserId); // 维修人员ID(YHBG+UUID)
		//orderTable.setOrderdistance(distance); // 维修距离
		//orderTable.setOrderintercepttraveltotalmoney(interceptTravelTotalMoney); // 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)
		//orderTable.setOrdertraveltotalmoney(orderTravelTotalMoney); // 维修差旅费总价(根据差旅费*距离计算得出)
		OrderTable updateOrderTable = new OrderTable();
		
		updateOrderTable.setOrderid(orderTable.getOrderid());//订单编号
		updateOrderTable.setOrderupdatetime(new Date()); // 维修更新时间
		
		
		// 2、添加维修单详情表
				// 数据
				for (int i = 0; i < orderDetailList.size(); i++) {
					OrderDetail orderDetail = orderDetailList.get(i);
					List<OrderDetail> detailList = new ArrayList<OrderDetail>();
					detailList = orderDetailService.selectByOrderDetail(orderDetail);
					if (detailList.size() > 0) {
						orderDetailList.remove(i);
						break;
					}
					orderDetail.setOrderdetailid(Config.SIGN_ORDERDETAIL + UUID.randomUUID().toString()); // 维修单详情ID(WXXQ+UUID)
					orderDetail.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
				}
		// 5、添加维修单对应维修单价表
		List<OrderPrice> orderPriceList = new ArrayList<OrderPrice>();
		// 循环维修单详情List
		for (int j = 0; j < orderDetailList.size(); j++) {
			// 根据人员ID、产品ID、项目ID、维修单价状态查询该人员的维修单价
			PriceAndGoodsAndProjectAndUser price = new PriceAndGoodsAndProjectAndUser();
			price.setPriceupdateuserid(orderTable.getOrderserviceuserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
			price.setGoodsid(orderDetailList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
			price.setProjectid(orderDetailList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
			price.setPricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
			price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
			price.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
					 
			List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
			priceList = priceService.selectFourTablesByUnionData(price); // 查询
			if (priceList.size() == 0) {
				
				returnData.setMsg("该维修师傅、安装师傅某些项目清单未维护价格，请维护完之后重新提交！");
				return returnData;
			}
			for (int i = 0; i < priceList.size(); i++) {
				OrderPrice orderPrice = new OrderPrice();
				orderPrice.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
				orderPrice.setOrderpriceupdateuserid(orderTable.getOrderserviceuserid()); // 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
				orderPrice.setGoodsid(priceList.get(i).getGoodsid()); // 维修单对应维修单价的产品ID(CPBG+UUID)
				orderPrice.setProjectid(priceList.get(i).getProjectid()); // 维修单对应维修单价的项目ID(XMBG+UUID)
				orderPrice.setOrderpricetype(priceList.get(i).getPricetype()); // 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
				orderPrice.setOrderpricemoney(priceList.get(i).getPricemoney()); // 维修单对应维修单价的单价
				orderPrice.setOrderpricestate(priceList.get(i).getPricestate()); // 维修单对应维修单价的状态(Y:有效/N:无效)
				orderPrice.setOrderpricecreateuserid(priceList.get(i).getPricecreateuserid()); // 维修单对应维修单价的创建人ID(YHBG+UUID)
				orderPrice.setOrderpricecreatetime(priceList.get(i).getPricecreatetime()); // 维修单对应维修单价的创建时间
				orderPrice.setOrderpriceupdatetime(priceList.get(i).getPriceupdatetime()); // 维修单对应维修单价的更新时间
				orderPrice.setOrderpriceremark(priceList.get(i).getProjectremark()); // 维修单对应维修单价的备注
								
				orderPriceList.add(orderPrice);
			}
		}
		
		
		// 平台派单
		int count = 0;
		count = operationService.backDistributionAndUpdate(orderTable, orderDetailList, orderPriceList);
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("平台派单失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("平台派单成功！");
		}

		return returnData;
	}

	/**
	 * 维修单详情确认表保存
	 *
	 * 操作过程：
	 * 1、添加维修单详情确认表
	 *
	 * 传递OrderTable实体
	 * 传递OrderDetailListEntity实体(List<OrderDetail>)
	 *
	 * 需在OrderTable实体中添加版本信息(可以为空)
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param models
	 * @return
	 *
	 * @author ZY on 2018/11/26
	 */
	@ResponseBody
	@RequestMapping("/saveOrderDetailConfirm")
	public LayuiDataTemplet<OrderTable> saveOrderDetailConfirm(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// JSON转换为实体类
		OrderTable orderTable = null;
		OrderDetailConfirmListEntity orderDetailConfirmListEntity = null;
		try {
			orderTable = JsonUtil.json2obj((String)models.get("orderTable"), OrderTable.class);
			orderDetailConfirmListEntity = JsonUtil.json2obj((String)models.get("orderDetailConfirmListEntity"), OrderDetailConfirmListEntity.class);
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
		// OrderDetailListEntity实体(List<OrderDetail>)
		List<OrderDetailConfirm> orderDetailConfirmList = null;
		if (orderDetailConfirmListEntity == null) {
			returnData.setMsg("传递的维修单详情数据错误！");
			return returnData;
		} else {
			orderDetailConfirmList = orderDetailConfirmListEntity.getOrderDetailConfirmList();
		}

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负.数，相等则返回0
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

		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setPagenumber(-1);//不分页
		selectOrderTable.setOrderid(orderTable.getOrderid());
		List<OrderTable> orderList = new ArrayList<OrderTable>();
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList.size() > 0) {
			orderTable = orderList.get(0);
		}
		//1、更新订单表状态
		orderTable.setOrderstate("BGQR");//状态
		//2、查询之前变更确认表中的数据 有如果就删除
		OrderDetailConfirm beforeOrderDetailConfirm = new OrderDetailConfirm();
		beforeOrderDetailConfirm.setOrderid(orderTable.getOrderid());
		List<OrderDetailConfirm> beforeOrderDetailConfirmList = new ArrayList<OrderDetailConfirm>();
		beforeOrderDetailConfirmList = orderDetailConfirmService.selectByOrderDetailConfirm(beforeOrderDetailConfirm);
		//删除的数据
		List<OrderDetailConfirm> deleteOrderDetailConfirmList = new ArrayList<OrderDetailConfirm>();
		if (beforeOrderDetailConfirmList.size() > 0) {
			deleteOrderDetailConfirmList.addAll(beforeOrderDetailConfirmList);
		}
		// 3、添加维修单详情表
		// 数据
		for (int i = 0; i < orderDetailConfirmList.size(); i++) {
			OrderDetailConfirm orderDetailConfirm = orderDetailConfirmList.get(i);
			orderDetailConfirm.setOrderdetailconfirmid(Config.SIGN_ORDERDETAILCONFIRM + UUID.randomUUID().toString()); // 维修单详情ID(WXXQ+UUID)
			orderDetailConfirm.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			//查询 推荐价格
			InterceptAndGoodsAndProject interceptAndGoodsAndProject = new InterceptAndGoodsAndProject();
			interceptAndGoodsAndProject.setPagenumber(-1);//不分页
			interceptAndGoodsAndProject.setGoodsid(orderDetailConfirm.getGoodsid());//产品ID
			interceptAndGoodsAndProject.setProjectid(orderDetailConfirm.getProjectid());//清单id
			List<InterceptAndGoodsAndProject> interceptList = new ArrayList<InterceptAndGoodsAndProject>();
			interceptList = interceptService.selectThreeTablesByUnionData(interceptAndGoodsAndProject);
			if (interceptList.size() >0 ){
				orderDetailConfirm.setOrderdetailconfirmintercepttotalmoney(interceptList.get(0).getInterceptmoney() * orderDetailConfirm.getOrderdetailconfirmnumber());
			} else {
				returnData.setMsg("您的项目清单中平台为维护价格，请联系平台客服维护价格！");
				return returnData;
			}
		}

		for (int j = 0; j < orderDetailConfirmList.size(); j++) {

			if (orderDetailConfirmList.get(j).getOrderdetailconfirmpricemoney() == null || orderDetailConfirmList.get(j).getOrderdetailconfirmpricemoney() == 0) {
				returnData.setMsg("提交的某些清单未维护价格请维护后重新提交！");
				return returnData;
			}

		}

		// 保存
		int count = 0;
		count = operationService.saveOrderDetailConfirm(orderTable, deleteOrderDetailConfirmList, orderDetailConfirmList);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("保存失败！");
		} else {
			// 返回数据List
			List<OrderTable> orderTableList = new ArrayList<OrderTable>();
			orderTableList.add(orderTable);

			returnData.setCount(count);
			returnData.setData(orderTableList);
			returnData.setMsg("保存成功！");
		}

		return returnData;
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
	 * 需在OrderTable实体中添加当前操作人员ID
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/11/26
	 */
	@ResponseBody
	@RequestMapping("/orderDetailConfirmByCustomer")
	public LayuiDataTemplet<OrderTable> orderDetailConfirmByCustomer(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable>  returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

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
			returnData.setMsg("当前操作人ID为空，无法派单！");
			return returnData;
		}

		// 1、根据OrderTable实体中的维修单ID，获取对应的维修单数据
		OrderTable selectOrderTable = new OrderTable();
		selectOrderTable.setOrderid(orderTable.getOrderid());
		selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<OrderTable> orderList = null;
		orderList = orderTableService.selectByOrderTable(selectOrderTable);
		if (orderList == null || orderList.size() == 0) {
			returnData.setMsg("找不到该维修单，无法派单！");
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


		// 2、根据OrderTable实体中的维修单ID，获取维修单详情中的产品ID、项目ID
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderid(orderTable.getOrderid());
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = orderDetailService.selectByOrderDetail(orderDetail); // 查询
		if (orderDetailList.size() == 0) {
			returnData.setMsg("该维修单没有维修详情数据，无法派单！");
			return returnData;
		}
			double orderPriceTotalMoneyNocredit = 0;//维修总价不用信用值系数算出来的，派单的价格
			double priceTotalMoney = 0; // 维修总价
			double totalMoney = 0; // 总价(维修总价+维修差旅费总价)
			double intercepttotalmoney = 0;

		//3、添加订单详情记录表并删除订单详情表中的数据
		List<OrderDetailRecord> orderDetailRecordList = new ArrayList<OrderDetailRecord>();
		for (int i = 0; i < orderDetailList.size(); i ++) {
			OrderDetailRecord orderDetailRecord = new OrderDetailRecord();
			orderDetailRecord.setOrderdetailrecordid(Config.SIGN_ORDERDETAILRECORD + UUID.randomUUID().toString()); // 维修单详情记录ID(WXXQ+UUID)
			orderDetailRecord.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
			orderDetailRecord.setGoodsid(orderDetailList.get(i).getGoodsid());//维修清单项目ID
			orderDetailRecord.setProjectid(orderDetailList.get(i).getProjectid());//维修清单ID
			orderDetailRecord.setOrderdetailnumber(orderDetailList.get(i).getOrderdetailnumber());//维修数量
			orderDetailRecord.setOrderdetailintercepttotalmoney(orderDetailList.get(i).getOrderdetailintercepttotalmoney());//维修推荐总价
			orderDetailRecord.setOrderdetailrecordcreatetime(new Date());//创建时间

			orderDetailRecordList.add(orderDetailRecord);

		}

		//查询订单详情确认表中的数据
		OrderDetailConfirm orderDetailConfirm = new OrderDetailConfirm();
		orderDetailConfirm.setOrderid(orderTable.getOrderid());//订单编号
		List<OrderDetailConfirm> orderDetailConfirmList = new ArrayList<OrderDetailConfirm>();
		orderDetailConfirmList = orderDetailConfirmService.selectByOrderDetailConfirm(orderDetailConfirm);

		//4、将查询出来的详情确认表中的数据加到新的订单详情表中
		List<OrderDetail> newOrderDetailList = new ArrayList<OrderDetail>();
		for (int i = 0; i < orderDetailConfirmList.size(); i++){
			OrderDetail newOrderDetail = new OrderDetail();
			newOrderDetail.setOrderdetailid(Config.SIGN_ORDERDETAIL + UUID.randomUUID().toString());//订单详情uuid
			newOrderDetail.setOrderid(orderDetailConfirmList.get(i).getOrderid());//订单编号
			newOrderDetail.setGoodsid(orderDetailConfirmList.get(i).getGoodsid());//产品ID(CPBG+UUID)
			newOrderDetail.setProjectid(orderDetailConfirmList.get(i).getProjectid());//项目ID(XMBG+UUID)
			newOrderDetail.setOrderdetailnumber(orderDetailConfirmList.get(i).getOrderdetailconfirmnumber());//数量
			newOrderDetail.setOrderdetailintercepttotalmoney(orderDetailConfirmList.get(i).getOrderdetailconfirmintercepttotalmoney());//推荐价\

			//推荐价总价
			intercepttotalmoney = intercepttotalmoney + orderDetailConfirmList.get(i).getOrderdetailconfirmintercepttotalmoney();
			//维修单总价
			priceTotalMoney += orderDetailConfirmList.get(i).getOrderdetailconfirmpricemoney() * orderDetailConfirmList.get(i).getOrderdetailconfirmnumber();
			newOrderDetailList.add(newOrderDetail);
		}

		//5、查询针对订单单价表中的数据单价
		// 循环维修单详情List
		List<OrderPrice> newOrderPriceList = new ArrayList<OrderPrice>();
		for (int j = 0; j < orderDetailConfirmList.size(); j++) {
			//根据根据订单编号、人员ID、产品ID、项目ID、维修单价状态查询该人员的针对订单的价格
			OrderPriceAndGoodsAndProjectAndUser orderPrice = new OrderPriceAndGoodsAndProjectAndUser();
			orderPrice.setOrderid(orderTable.getOrderid());
			orderPrice.setOrderpriceupdateuserid(orderTable.getOrderserviceuserid()); // 维修单价维护人(维修人员)ID(YHBG+UUID)
			orderPrice.setGoodsid(orderDetailConfirmList.get(j).getGoodsid()); // 产品ID(CPBG+UUID)
			orderPrice.setProjectid(orderDetailConfirmList.get(j).getProjectid()); // 项目ID(XMBG+UUID)
			orderPrice.setOrderpricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
			orderPrice.setOrderpricestate("Y"); // 维修单价状态(Y:有效/N:无效)

			List<OrderPriceAndGoodsAndProjectAndUser> orderPriceList = new ArrayList<OrderPriceAndGoodsAndProjectAndUser>();
			orderPriceList = orderPriceService.selectFourTablesByUnionData(orderPrice);

			if (orderPriceList.size() == 0) {

				OrderPrice newOrderPrice = new OrderPrice();
				newOrderPrice.setOrderid(orderTable.getOrderid());
				newOrderPrice.setOrderpricemoney(orderDetailConfirmList.get(j).getOrderdetailconfirmpricemoney());
				newOrderPrice.setOrderpriceupdateuserid(orderTable.getOrderserviceuserid());//维修单价维护人(维修人员)ID(YHBG+UUID)
				newOrderPrice.setGoodsid(orderDetailConfirmList.get(j).getGoodsid());//产品ID(CPBG+UUID)
				newOrderPrice.setProjectid(orderDetailConfirmList.get(j).getProjectid());// 项目ID(XMBG+UUID)
				newOrderPrice.setOrderpricetype(orderTable.getOrderprojecttype()); // 维修项目类型(WX:维修工/AZ:安装队)
				newOrderPrice.setOrderpricestate("Y"); // 维修单价状态(Y:有效/N:无效)
				newOrderPrice.setOrderpricecreateuserid(orderTable.getOrderserviceuserid());//维修单价维护人(维修人员)ID(YHBG+UUID)
				newOrderPrice.setOrderpricecreatetime(new Date());
				newOrderPrice.setOrderpriceupdatetime(new Date());

				newOrderPriceList.add(newOrderPrice);
			}


		}

		//6、更新维修单表状态
		orderTable.setOrderstate("JS");//订单状态
		orderTable.setOrderpricetotalmoney(priceTotalMoney);//维修工总价
		orderTable.setOrderintercepttotalmoney(intercepttotalmoney);//推荐总价
		orderTable.setOrderupdateuserid(currentUserId); // 当前操作人员ID // 维修更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修更新时间

		// 平台派单
		int count = 0;
		//count = operationService.backDistribution(orderTable, task, orderPriceList, customerUser, customerExpenditureMoney, backMoney);
		//count = operationService.backDistribution(orderTable, task, orderPriceList);
		count = operationService.orderDetailConfirmByCustomer(orderTable, orderDetailList,newOrderDetailList, orderDetailRecordList, newOrderPriceList);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("确认失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("确认成功！");
		}

		return returnData;
	}

}
