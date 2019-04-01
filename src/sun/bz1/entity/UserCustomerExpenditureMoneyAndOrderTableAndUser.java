package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户_客户_支出_金额表、维修单表、金额用户(用户表)
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/19
 */

public class UserCustomerExpenditureMoneyAndOrderTableAndUser {

	/**
	 * 用户_客户_支出_金额表-序号(自增主键)
	 */
	private Integer id;

	/**
	 * 用户_客户_支出_金额表-客户支出金额ID(KHZC+UUID)
	 */
	private String usercustomerexpendituremoneyid;

	/**
	 * 用户_客户_支出_金额表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 用户_客户_支出_金额表-金额用户ID(YHBG+UUID)
	 */
	private String usercustomerexpendituremoneyuserid;

	/**
	 * 用户_客户_支出_金额表-金额(可正可负)
	 */
	private Double usercustomerexpendituremoney;

	/**
	 * 用户_客户_支出_金额表-金额来源(PTPD:平台派单(客户按维修推荐总价+维修差旅费推荐总价支出金额)/KHTX:客户提现(客户支出金额))
	 */
	private String usercustomerexpendituremoneysource;

	/**
	 * 用户_客户_支出_金额表-金额创建时间
	 */
	private Date usercustomerexpendituremoneycreatetime;

	/**
	 * 用户_客户_支出_金额表-金额其他字段1
	 */
	private String usercustomerexpendituremoneyother1;

	/**
	 * 用户_客户_支出_金额表-金额其他字段2
	 */
	private String usercustomerexpendituremoneyother2;

	/**
	 * 用户_客户_支出_金额表-金额备注
	 */
	private String usercustomerexpendituremoneyremark;
    
    /**
	 * 维修单表-维修项目名称
	 */
	private String orderprojectname;

	/**
	 * 维修单表-维修联系人(报修人)名称
	 */
	private String ordercontactperson;

	/**
	 * 维修单表-维修联系电话
	 */
	private String ordercontactphone;

	/**
	 * 维修单表-接单区域ID(JDQY+UUID)
	 */
	private String orderaddress;

	/**
	 * 维修单表-维修建筑类型ID(JZLX+UUID)
	 */
	private String buildingtypeid;

	/**
	 * 维修单表-维修计划时间
	 */
	private Date orderplantime;
	
	/**
	 * 维修单表-维修竞标结束时间(抢单类型时，才会添加这个字段的数据
	 */
	private Date orderbiddingendtime;
	
	/**
	 * 维修单表-维修项目类型(WX:维修工/AZ:安装队)
	 */
	private String orderprojecttype;
	
	/**
	 * 维修单表-维修位置信息(当前位置信息)
	 */
	private String orderposition;
	
	/**
	 * 维修单表-维修位置纬度
	 */
	private Double orderlatitude;
	
	/**
	 * 维修单表-维修位置经度
	 */
	private Double orderlongitude;
	
	/**
	 * 维修单表-维修距离(确认维修人员后，才会添加这个字段的数据)
	 */
	private Double orderdistance;

	/**
	 * 维修单表-维修拦标总价(下单时，对应的拦标总价)
	 */
	private Double orderintercepttotalmoney;
	
	/**
	 * 维修单表-维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)(确认维修人员后，才会添加这个字段的数据)
	 */
	private Double orderintercepttraveltotalmoney;

	/**
	 * 维修单表-维修总价(根据维修单价计算得出)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中，维修总价+维修差旅费总价)
	 */
	private Double orderpricetotalmoney;
	
	/**
	 * 维修单表-维修差旅费总价(根据差旅费*距离计算得出)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中，维修总价+维修差旅费总价)
	 */
	private Double ordertraveltotalmoney;
	
	/**
	 * 维修单表-总价(维修总价+维修差旅费总价)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中)
	 * 
	 * 业务字段，数据表中没有该字段
	 */
	private Double ordertotalmoney;
	
	/**
	 * 维修单表-维修类型(PD:派单/QD:抢单)
	 */
	private String ordertype;

	/**
	 * 维修单表-维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
	 */
	private String orderstate;

	/**
	 * 维修单表-维修人员ID(YHBG+UUID)(确认维修人员后，才会添加这个字段的数据)
	 */
	private String orderserviceuserid;

	/**
	 * 维修单表-维修创建人ID(YHBG+UUID)
	 */
	private String ordercreateuserid;

	/**
	 * 维修单表-维修创建时间
	 */
	private Date ordercreatetime;

	/**
	 * 维修单表-维修更新人ID(YHBG+UUID)
	 */
	private String orderupdateuserid;

	/**
	 * 维修单表-维修更新时间
	 */
	private Date orderupdatetime;

	/**
	 * 维修单表-维修申请验收人ID(YHBG+UUID)(申请验收后，才会添加这个字段的数据)
	 */
	private String orderapplycheckuserid;

	/**
	 * 维修单表-维修申请验收时间(申请验收后，才会添加这个字段的数据)
	 */
	private Date orderapplychecktime;

	/**
	 * 维修单表-维修单备注
	 */
	private String orderremark;

	/**
	 * 维修单表-多次申请验收，是否全部申请验收：ALL_CHECK
	 */
	private String ordercheckallstate;

	/**
	 * 金额用户(用户表)-用户名
	 */
	private String usermoneyuser_username;

	/**
	 * 金额用户(用户表)-用户真实姓名
	 */
	private String usermoneyuser_userrealname;

	/**
	 * 金额用户(用户表)-用户性别(Y:男/X:女)
	 */
	private String usermoneyuser_usersex;

	/**
	 * 金额用户(用户表)-用户手机号码
	 */
	private String usermoneyuser_userphone;

	/**
	 * 金额用户(用户表)-用户身份证号码
	 */
	private String usermoneyuser_usercodeid;

	/**
	 * 金额用户(用户表)-用户邮箱
	 */
	private String usermoneyuser_useremail;

	/**
	 * 金额用户(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String usermoneyuser_userrole;

	/**
	 * 金额用户(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String usermoneyuser_userstate;

	/**
	 * 金额用户(用户表)-用户信用值
	 */
	private Double usermoneyuser_usercredit;
	
	/**
	 * 金额用户(用户表)-用户余额
	 */
	private Double usermoneyuser_usermoney;

	/**
	 * 金额用户(用户表)-用户微信OpenId
	 */
	private String usermoneyuser_useropenid;
	
	/**
	 * 金额用户(用户表)-用户位置信息(当前位置信息)
	 */
	private String usermoneyuser_userposition;
	
	/**
	 * 金额用户(用户表)-用户位置纬度
	 */
	private Double usermoneyuser_userlatitude;
	
	/**
	 * 金额用户(用户表)-用户位置经度
	 */
	private Double usermoneyuser_userlongitude;

	/**
	 * 金额用户(用户表)-用户备注
	 */
	private String usermoneyuser_userremark;

	// 版本信息
	/**
	 * 版本号
	 */
	private String version;

	// 分页数据
	/**
	 * 当前页数(如果不进行分页，该条数据默认为-1)
	 */
	private Integer pagenumber;

	/**
	 * 每页的数据量
	 */
	private Integer pagesize;

	// 操作数据
	/**
	 * 根据用户_金额表的金额创建时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectmoneycreatestarttime;

	/**
	 * 根据用户_金额表的金额创建时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectmoneycreateendtime;

	/**
	 * 根据维修单表的维修计划时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectorderplanstarttime;

	/**
	 * 根据维修单表的维修计划时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectorderplanendtime;
	
	/**
	 * 根据维修单表的维修竞标结束时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectorderbiddingendstarttime;

	/**
	 * 根据维修单表的维修竞标结束时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectorderbiddingendendtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsercustomerexpendituremoneyid() {
		return usercustomerexpendituremoneyid;
	}

	public void setUsercustomerexpendituremoneyid(String usercustomerexpendituremoneyid) {
		this.usercustomerexpendituremoneyid = usercustomerexpendituremoneyid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUsercustomerexpendituremoneyuserid() {
		return usercustomerexpendituremoneyuserid;
	}

	public void setUsercustomerexpendituremoneyuserid(String usercustomerexpendituremoneyuserid) {
		this.usercustomerexpendituremoneyuserid = usercustomerexpendituremoneyuserid;
	}

	public Double getUsercustomerexpendituremoney() {
		return usercustomerexpendituremoney;
	}

	public void setUsercustomerexpendituremoney(Double usercustomerexpendituremoney) {
		this.usercustomerexpendituremoney = usercustomerexpendituremoney;
	}

	public String getUsercustomerexpendituremoneysource() {
		return usercustomerexpendituremoneysource;
	}

	public void setUsercustomerexpendituremoneysource(String usercustomerexpendituremoneysource) {
		this.usercustomerexpendituremoneysource = usercustomerexpendituremoneysource;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getUsercustomerexpendituremoneycreatetime() {
		return usercustomerexpendituremoneycreatetime;
	}

	public void setUsercustomerexpendituremoneycreatetime(Date usercustomerexpendituremoneycreatetime) {
		this.usercustomerexpendituremoneycreatetime = usercustomerexpendituremoneycreatetime;
	}

	public String getUsercustomerexpendituremoneyother1() {
		return usercustomerexpendituremoneyother1;
	}

	public void setUsercustomerexpendituremoneyother1(String usercustomerexpendituremoneyother1) {
		this.usercustomerexpendituremoneyother1 = usercustomerexpendituremoneyother1;
	}

	public String getUsercustomerexpendituremoneyother2() {
		return usercustomerexpendituremoneyother2;
	}

	public void setUsercustomerexpendituremoneyother2(String usercustomerexpendituremoneyother2) {
		this.usercustomerexpendituremoneyother2 = usercustomerexpendituremoneyother2;
	}

	public String getUsercustomerexpendituremoneyremark() {
		return usercustomerexpendituremoneyremark;
	}

	public void setUsercustomerexpendituremoneyremark(String usercustomerexpendituremoneyremark) {
		this.usercustomerexpendituremoneyremark = usercustomerexpendituremoneyremark;
	}

	public String getOrderprojectname() {
		return orderprojectname;
	}

	public void setOrderprojectname(String orderprojectname) {
		this.orderprojectname = orderprojectname;
	}

	public String getOrdercontactperson() {
		return ordercontactperson;
	}

	public void setOrdercontactperson(String ordercontactperson) {
		this.ordercontactperson = ordercontactperson;
	}

	public String getOrdercontactphone() {
		return ordercontactphone;
	}

	public void setOrdercontactphone(String ordercontactphone) {
		this.ordercontactphone = ordercontactphone;
	}

	public String getOrderaddress() {
		return orderaddress;
	}

	public void setOrderaddress(String orderaddress) {
		this.orderaddress = orderaddress;
	}

	public String getBuildingtypeid() {
		return buildingtypeid;
	}

	public void setBuildingtypeid(String buildingtypeid) {
		this.buildingtypeid = buildingtypeid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getOrderplantime() {
		return orderplantime;
	}

	public void setOrderplantime(Date orderplantime) {
		this.orderplantime = orderplantime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getOrderbiddingendtime() {
		return orderbiddingendtime;
	}

	public void setOrderbiddingendtime(Date orderbiddingendtime) {
		this.orderbiddingendtime = orderbiddingendtime;
	}
	
	public String getOrderprojecttype() {
		return orderprojecttype;
	}

	public void setOrderprojecttype(String orderprojecttype) {
		this.orderprojecttype = orderprojecttype;
	}
	
	public String getOrderposition() {
		return orderposition;
	}

	public void setOrderposition(String orderposition) {
		this.orderposition = orderposition;
	}

	public Double getOrderlatitude() {
		return orderlatitude;
	}

	public void setOrderlatitude(Double orderlatitude) {
		this.orderlatitude = orderlatitude;
	}

	public Double getOrderlongitude() {
		return orderlongitude;
	}

	public void setOrderlongitude(Double orderlongitude) {
		this.orderlongitude = orderlongitude;
	}

	public Double getOrderdistance() {
		return orderdistance;
	}

	public void setOrderdistance(Double orderdistance) {
		this.orderdistance = orderdistance;
	}

	public Double getOrderintercepttotalmoney() {
		return orderintercepttotalmoney;
	}

	public void setOrderintercepttotalmoney(Double orderintercepttotalmoney) {
		this.orderintercepttotalmoney = orderintercepttotalmoney;
	}

	public Double getOrderintercepttraveltotalmoney() {
		return orderintercepttraveltotalmoney;
	}

	public void setOrderintercepttraveltotalmoney(Double orderintercepttraveltotalmoney) {
		this.orderintercepttraveltotalmoney = orderintercepttraveltotalmoney;
	}

	public Double getOrderpricetotalmoney() {
		return orderpricetotalmoney;
	}

	public void setOrderpricetotalmoney(Double orderpricetotalmoney) {
		this.orderpricetotalmoney = orderpricetotalmoney;
	}

	public Double getOrdertraveltotalmoney() {
		return ordertraveltotalmoney;
	}

	public void setOrdertraveltotalmoney(Double ordertraveltotalmoney) {
		this.ordertraveltotalmoney = ordertraveltotalmoney;
	}

	public Double getOrdertotalmoney() {
		Double pricetotalmoney = orderpricetotalmoney;
		Double traveltotalmoney = ordertraveltotalmoney;
		
		if (pricetotalmoney == null) {
			pricetotalmoney = 0.0;
		} 
		
		if (traveltotalmoney == null) {
			traveltotalmoney = 0.0;
		}
		
		ordertotalmoney = pricetotalmoney + traveltotalmoney;
		
		return ordertotalmoney;
	}

	public void setOrdertotalmoney(Double ordertotalmoney) {
		this.ordertotalmoney = ordertotalmoney;
	}
	
	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}

	public String getOrderserviceuserid() {
		return orderserviceuserid;
	}

	public void setOrderserviceuserid(String orderserviceuserid) {
		this.orderserviceuserid = orderserviceuserid;
	}

	public String getOrdercreateuserid() {
		return ordercreateuserid;
	}

	public void setOrdercreateuserid(String ordercreateuserid) {
		this.ordercreateuserid = ordercreateuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getOrdercreatetime() {
		return ordercreatetime;
	}

	public void setOrdercreatetime(Date ordercreatetime) {
		this.ordercreatetime = ordercreatetime;
	}

	public String getOrderupdateuserid() {
		return orderupdateuserid;
	}

	public void setOrderupdateuserid(String orderupdateuserid) {
		this.orderupdateuserid = orderupdateuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getOrderupdatetime() {
		return orderupdatetime;
	}

	public void setOrderupdatetime(Date orderupdatetime) {
		this.orderupdatetime = orderupdatetime;
	}

	public String getOrderapplycheckuserid() {
		return orderapplycheckuserid;
	}

	public void setOrderapplycheckuserid(String orderapplycheckuserid) {
		this.orderapplycheckuserid = orderapplycheckuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getOrderapplychecktime() {
		return orderapplychecktime;
	}

	public void setOrderapplychecktime(Date orderapplychecktime) {
		this.orderapplychecktime = orderapplychecktime;
	}

	public String getOrderremark() {
		return orderremark;
	}

	public void setOrderremark(String orderremark) {
		this.orderremark = orderremark;
	}

	public String getUsermoneyuser_username() {
		return usermoneyuser_username;
	}

	public void setUsermoneyuser_username(String usermoneyuser_username) {
		this.usermoneyuser_username = usermoneyuser_username;
	}

	public String getUsermoneyuser_userrealname() {
		return usermoneyuser_userrealname;
	}

	public void setUsermoneyuser_userrealname(String usermoneyuser_userrealname) {
		this.usermoneyuser_userrealname = usermoneyuser_userrealname;
	}

	public String getUsermoneyuser_usersex() {
		return usermoneyuser_usersex;
	}

	public void setUsermoneyuser_usersex(String usermoneyuser_usersex) {
		this.usermoneyuser_usersex = usermoneyuser_usersex;
	}

	public String getUsermoneyuser_userphone() {
		return usermoneyuser_userphone;
	}

	public void setUsermoneyuser_userphone(String usermoneyuser_userphone) {
		this.usermoneyuser_userphone = usermoneyuser_userphone;
	}

	public String getUsermoneyuser_usercodeid() {
		return usermoneyuser_usercodeid;
	}

	public void setUsermoneyuser_usercodeid(String usermoneyuser_usercodeid) {
		this.usermoneyuser_usercodeid = usermoneyuser_usercodeid;
	}

	public String getUsermoneyuser_useremail() {
		return usermoneyuser_useremail;
	}

	public void setUsermoneyuser_useremail(String usermoneyuser_useremail) {
		this.usermoneyuser_useremail = usermoneyuser_useremail;
	}

	public String getUsermoneyuser_userrole() {
		return usermoneyuser_userrole;
	}

	public void setUsermoneyuser_userrole(String usermoneyuser_userrole) {
		this.usermoneyuser_userrole = usermoneyuser_userrole;
	}

	public String getUsermoneyuser_userstate() {
		return usermoneyuser_userstate;
	}

	public void setUsermoneyuser_userstate(String usermoneyuser_userstate) {
		this.usermoneyuser_userstate = usermoneyuser_userstate;
	}

	public Double getUsermoneyuser_usercredit() {
		return usermoneyuser_usercredit;
	}

	public void setUsermoneyuser_usercredit(Double usermoneyuser_usercredit) {
		this.usermoneyuser_usercredit = usermoneyuser_usercredit;
	}

	public Double getUsermoneyuser_usermoney() {
		return usermoneyuser_usermoney;
	}

	public void setUsermoneyuser_usermoney(Double usermoneyuser_usermoney) {
		this.usermoneyuser_usermoney = usermoneyuser_usermoney;
	}

	public String getUsermoneyuser_useropenid() {
		return usermoneyuser_useropenid;
	}

	public void setUsermoneyuser_useropenid(String usermoneyuser_useropenid) {
		this.usermoneyuser_useropenid = usermoneyuser_useropenid;
	}

	public String getUsermoneyuser_userposition() {
		return usermoneyuser_userposition;
	}

	public void setUsermoneyuser_userposition(String usermoneyuser_userposition) {
		this.usermoneyuser_userposition = usermoneyuser_userposition;
	}

	public Double getUsermoneyuser_userlatitude() {
		return usermoneyuser_userlatitude;
	}

	public void setUsermoneyuser_userlatitude(Double usermoneyuser_userlatitude) {
		this.usermoneyuser_userlatitude = usermoneyuser_userlatitude;
	}

	public Double getUsermoneyuser_userlongitude() {
		return usermoneyuser_userlongitude;
	}

	public void setUsermoneyuser_userlongitude(Double usermoneyuser_userlongitude) {
		this.usermoneyuser_userlongitude = usermoneyuser_userlongitude;
	}

	public String getUsermoneyuser_userremark() {
		return usermoneyuser_userremark;
	}

	public void setUsermoneyuser_userremark(String usermoneyuser_userremark) {
		this.usermoneyuser_userremark = usermoneyuser_userremark;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(Integer pagenumber) {
		this.pagenumber = pagenumber;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public String getSelectmoneycreatestarttime() {
		return selectmoneycreatestarttime;
	}

	public void setSelectmoneycreatestarttime(String selectmoneycreatestarttime) {
		this.selectmoneycreatestarttime = selectmoneycreatestarttime;
	}

	public String getSelectmoneycreateendtime() {
		return selectmoneycreateendtime;
	}

	public void setSelectmoneycreateendtime(String selectmoneycreateendtime) {
		this.selectmoneycreateendtime = selectmoneycreateendtime;
	}

	public String getSelectorderplanstarttime() {
		return selectorderplanstarttime;
	}

	public void setSelectorderplanstarttime(String selectorderplanstarttime) {
		this.selectorderplanstarttime = selectorderplanstarttime;
	}

	public String getSelectorderplanendtime() {
		return selectorderplanendtime;
	}

	public void setSelectorderplanendtime(String selectorderplanendtime) {
		this.selectorderplanendtime = selectorderplanendtime;
	}

	public String getSelectorderbiddingendstarttime() {
		return selectorderbiddingendstarttime;
	}

	public void setSelectorderbiddingendstarttime(String selectorderbiddingendstarttime) {
		this.selectorderbiddingendstarttime = selectorderbiddingendstarttime;
	}

	public String getSelectorderbiddingendendtime() {
		return selectorderbiddingendendtime;
	}

	public void setSelectorderbiddingendendtime(String selectorderbiddingendendtime) {
		this.selectorderbiddingendendtime = selectorderbiddingendendtime;
	}

	public String getOrdercheckallstate() {
		return ordercheckallstate;
	}

	public void setOrdercheckallstate(String ordercheckallstate) {
		this.ordercheckallstate = ordercheckallstate;
	}
}
