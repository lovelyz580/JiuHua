package util;

/**
 * 全局设置
 * 
 * @author WJF on 2018/09/03
 */

public class Config {

	/**
	 * 版本号
	 */
	public static final String VERSION = "1.0";
	
	/** 
	 * 各个数据表ID的标识
	 */
	public static final String SIGN_AREA = "JDQY"; // 接单区域表
	public static final String SIGN_SCALE = "JDGM"; // 接单规模表
	
	public static final String SIGN_USER = "YHBG"; // 用户表
	
	public static final String SIGN_CREDITCHANGE = "XYBH"; // 信用值_变化表
	
//	public static final String SIGN_USERMONEY = "YHJE"; // 用户_金额表
	public static final String SIGN_USERCUSTOMERINCOMEMONEY = "KHSR"; // 用户_客户_收入_金额表
	public static final String SIGN_USERCUSTOMEREXPENDITUREMONEY = "KHZC"; // 用户_客户_支出_金额表
	public static final String SIGN_USERSERVICEINCOMEMONEY= "WXSR"; // 用户_维修工、安装队_收入_金额表
	public static final String SIGN_USERSERVICEEXPENDITUREMONEY = "WXZC"; // 用户_维修工、安装队_支出_金额表
	
	public static final String SIGN_USEREXTRACTMONEY = "YHTX"; // 用户_提现表
	
	public static final String SIGN_EVALUATESESRVICE = "PJWX"; // 客户评价维修人员表
	public static final String SIGN_EVALUATESESRVICESETUP = "WPJX"; // 客户评价维修人员_评价项_设置表
	public static final String SIGN_EVALUATECUSTOMER = "PJKH"; // 维修人员评价客户表
	public static final String SIGN_EVALUATECUSTOMERSETUP = "KPJX"; // 维修人员评价客户_评价项_设置表
	
	public static final String SIGN_BUILDINGTYPE = "JZLX"; // 建筑类型表
	
	public static final String SIGN_GOODS = "CPBG"; // 产品表
	public static final String SIGN_PROJECT = "XMBG"; // 项目表
	
	public static final String SIGN_INTERCEPT = "LBJB"; // 拦标价表
	public static final String SIGN_PRICE = "WXDJ"; // 维修单价表
	
	public static final String SIGN_ORDERDETAIL = "WXXQ"; // 维修单详情表

	public static final String SIGN_ORDERDETAILRECORD = "XQJL"; // 维修单详情记录表
	
	public static final String SIGN_ANNOUNCEMENT = "WXGG"; // 维修公告表(发布公告时在该表中添加数据，公告结束时修改公告状态)
	public static final String SIGN_TASK = "WXRW"; // 维修任务表(接收维修时在该表中添加数据，维修结束时修改任务状态)
	
	public static final String SIGN_DISTRIBUTION = "FPJL"; // 维修单分配记录表
	public static final String SIGN_SNATCH = "QDJL"; // 维修单抢单记录表
	
	public static final String SIGN_RETURNTABLE = "WXTH"; // 维修人员退回维修单表(维修人员退回维修单时在该表中添加数据)
	public static final String SIGN_CANCELTABLE = "KHQX"; // 客户取消维修单表(客户取消维修单时在该表中添加数据)
	
	public static final String SIGN_APPLYCHECK = "WXYS"; // 维修验收表(验收成功或失败时在该表中添加数据)
	
	public static final String SIGN_DISPUTE = "JFBG"; // 纠纷表 
	
	public static final String SIGN_BACKMONEY = "PTJE"; // 平台_收益金额表
	
	public static final String SIGN_NEWS = "XWBG"; // 新闻表

	public static final String SIGN_USERPAYMENT = "YHFK"; // 用户付款表

	public static final String SIGN_DISTRIBUTECONFIRM = "PDQR"; // 新闻表

	public static final String SIGN_ORDERDETAILCONFIRM = "XQQR"; // 维修单详情表

	public static final String SIGN_LOG = "RZ"; // 日志表

	public static final String SIGN_IMAGE = "SYTP"; // 首页图片表

	public static final String SIGN_SHAREVIDEO = "FXSP"; // 分享视频表

	public static final String SIGN_ORDERBREAKCARD = "WXJG"; // 维修价格表

	public static final String SIGN_PROPERTYCOMPANY = "DCGS"; // 地产公司表
	
	/**
	 * 短信相关数据
	 */
	public static final String SMS_KEYID = "Lx7";
	public static final String SMS_KEYSECRET = "EVGFd2G";
	public static final String SMS_SIGNNAME = ""; // 短信签名
	public static final String SMS_TEMPLATECODE = ""; // 短信模板 注册时
	public static final String SMS_TEMPLATECODEFORGETPASSWORD = "SM5015"; // 短信模板 忘记密码时
	
}
