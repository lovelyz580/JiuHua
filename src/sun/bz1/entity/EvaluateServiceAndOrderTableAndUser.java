package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 客户评价维修人员表、维修单表、维修人员ID(被评价用户)(用户表)、客户ID(评价用户)(用户表)
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class EvaluateServiceAndOrderTableAndUser {

	/**
	 * 客户评价维修人员表-序号(自增主键)
	 */
	private Integer id;

	/**
	 * 客户评价维修人员表-评价ID(PJWX+UUID)
	 */
	private String evaluateserviceid;

	/**
	 * 客户评价维修人员表-维修人员ID(被评价用户)(YHBG+UUID)
	 */
	private String serviceuserid;

	/**
	 * 客户评价维修人员表-客户ID(评价用户)(YHBG+UUID)
	 */
	private String customeruserid;

	/**
	 * 客户评价维修人员表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 客户评价维修人员表-总得分
	 */
	private Double evaluateservicescore;

	/**
	 * 客户评价维修人员表-评价意见
	 */
	private String evaluateserviceopinion;

	/**
	 * 客户评价维修人员表-评价创建时间
	 */
	private Date evaluateservicecreatetime;

	/**
	 * 客户评价维修人员表-评价备注
	 */
	private String evaluateserviceremark;

	/**
	 * 客户评价维修人员表-评价项ID(WPJX+UUID)多个用,隔开
	 */
	private String evaluateservicesetupid;

	/**
	 * 客户评价维修人员表-评价项名称多个用,隔开
	 */
	private String evaluateservicesetupname;

	/**
	 * 客户评价维修人员表-得分用于展示实体星星多个用,隔开
	 */
	private String evaluateservicesetupscore;
	
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
	 * 维修人员ID(被评价用户)(用户表)-用户名
	 */
	private String serviceuser_username;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户真实姓名
	 */
	private String serviceuser_userrealname;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户性别(Y:男/X:女)
	 */
	private String serviceuser_usersex;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户手机号码
	 */
	private String serviceuser_userphone;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户身份证号码
	 */
	private String serviceuser_usercodeid;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户邮箱
	 */
	private String serviceuser_useremail;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String serviceuser_userrole;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String serviceuser_userstate;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户信用值
	 */
	private Double serviceuser_usercredit;
	
	/**
	 * 维修人员ID(被评价用户)(用户表)-用户余额
	 */
	private Double serviceuser_usermoney;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户微信OpenId
	 */
	private String serviceuser_useropenid;
	
	/**
	 * 维修人员ID(被评价用户)(用户表)-用户位置信息(当前位置信息)
	 */
	private String serviceuser_userposition;
	
	/**
	 * 维修人员ID(被评价用户)(用户表)-用户位置纬度
	 */
	private Double serviceuser_userlatitude;
	
	/**
	 * 维修人员ID(被评价用户)(用户表)-用户位置经度
	 */
	private Double serviceuser_userlongitude;

	/**
	 * 维修人员ID(被评价用户)(用户表)-用户备注
	 */
	private String serviceuser_userremark;

	/**
	 * 客户ID(评价用户)(用户表)-用户名
	 */
	private String customeruser_username;

	/**
	 * 客户ID(评价用户)(用户表)-用户真实姓名
	 */
	private String customeruser_userrealname;

	/**
	 * 客户ID(评价用户)(用户表)-用户性别(Y:男/X:女)
	 */
	private String customeruser_usersex;

	/**
	 * 客户ID(评价用户)(用户表)-用户手机号码
	 */
	private String customeruser_userphone;

	/**
	 * 客户ID(评价用户)(用户表)-用户身份证号码
	 */
	private String customeruser_usercodeid;

	/**
	 * 客户ID(评价用户)(用户表)-用户邮箱
	 */
	private String customeruser_useremail;

	/**
	 * 客户ID(评价用户)(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String customeruser_userrole;

	/**
	 * 客户ID(评价用户)(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String customeruser_userstate;

	/**
	 * 客户ID(评价用户)(用户表)-用户信用值
	 */
	private Double customeruser_usercredit;
	
	/**
	 * 客户ID(评价用户)(用户表)-用户余额
	 */
	private Double customeruser_usermoney;

	/**
	 * 客户ID(评价用户)(用户表)-用户微信OpenId
	 */
	private String customeruser_useropenid;
	
	/**
	 * 客户ID(评价用户)(用户表)-用户位置信息(当前位置信息)
	 */
	private String customeruser_userposition;
	
	/**
	 * 客户ID(评价用户)(用户表)-用户位置纬度
	 */
	private Double customeruser_userlatitude;
	
	/**
	 * 客户ID(评价用户)(用户表)-用户位置经度
	 */
	private Double customeruser_userlongitude;

	/**
	 * 客户ID(评价用户)(用户表)-用户备注
	 */
	private String customeruser_userremark;

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
	 * 根据客户评价维修人员表的评价创建时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectevaluateservicecreatestarttime;

	/**
	 * 根据客户评价维修人员表的评价创建时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectevaluateservicecreateendtime;
	
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

	public String getEvaluateserviceid() {
		return evaluateserviceid;
	}

	public void setEvaluateserviceid(String evaluateserviceid) {
		this.evaluateserviceid = evaluateserviceid;
	}

	public String getServiceuserid() {
		return serviceuserid;
	}

	public void setServiceuserid(String serviceuserid) {
		this.serviceuserid = serviceuserid;
	}

	public String getCustomeruserid() {
		return customeruserid;
	}

	public void setCustomeruserid(String customeruserid) {
		this.customeruserid = customeruserid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Double getEvaluateservicescore() {
		return evaluateservicescore;
	}

	public void setEvaluateservicescore(Double evaluateservicescore) {
		this.evaluateservicescore = evaluateservicescore;
	}

	public String getEvaluateserviceopinion() {
		return evaluateserviceopinion;
	}

	public void setEvaluateserviceopinion(String evaluateserviceopinion) {
		this.evaluateserviceopinion = evaluateserviceopinion;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getEvaluateservicecreatetime() {
		return evaluateservicecreatetime;
	}

	public void setEvaluateservicecreatetime(Date evaluateservicecreatetime) {
		this.evaluateservicecreatetime = evaluateservicecreatetime;
	}

	public String getEvaluateserviceremark() {
		return evaluateserviceremark;
	}

	public void setEvaluateserviceremark(String evaluateserviceremark) {
		this.evaluateserviceremark = evaluateserviceremark;
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

	public String getServiceuser_username() {
		return serviceuser_username;
	}

	public void setServiceuser_username(String serviceuser_username) {
		this.serviceuser_username = serviceuser_username;
	}

	public String getServiceuser_userrealname() {
		return serviceuser_userrealname;
	}

	public void setServiceuser_userrealname(String serviceuser_userrealname) {
		this.serviceuser_userrealname = serviceuser_userrealname;
	}

	public String getServiceuser_usersex() {
		return serviceuser_usersex;
	}

	public void setServiceuser_usersex(String serviceuser_usersex) {
		this.serviceuser_usersex = serviceuser_usersex;
	}

	public String getServiceuser_userphone() {
		return serviceuser_userphone;
	}

	public void setServiceuser_userphone(String serviceuser_userphone) {
		this.serviceuser_userphone = serviceuser_userphone;
	}

	public String getServiceuser_usercodeid() {
		return serviceuser_usercodeid;
	}

	public void setServiceuser_usercodeid(String serviceuser_usercodeid) {
		this.serviceuser_usercodeid = serviceuser_usercodeid;
	}

	public String getServiceuser_useremail() {
		return serviceuser_useremail;
	}

	public void setServiceuser_useremail(String serviceuser_useremail) {
		this.serviceuser_useremail = serviceuser_useremail;
	}

	public String getServiceuser_userrole() {
		return serviceuser_userrole;
	}

	public void setServiceuser_userrole(String serviceuser_userrole) {
		this.serviceuser_userrole = serviceuser_userrole;
	}

	public String getServiceuser_userstate() {
		return serviceuser_userstate;
	}

	public void setServiceuser_userstate(String serviceuser_userstate) {
		this.serviceuser_userstate = serviceuser_userstate;
	}

	public Double getServiceuser_usercredit() {
		return serviceuser_usercredit;
	}

	public void setServiceuser_usercredit(Double serviceuser_usercredit) {
		this.serviceuser_usercredit = serviceuser_usercredit;
	}

	public Double getServiceuser_usermoney() {
		return serviceuser_usermoney;
	}

	public void setServiceuser_usermoney(Double serviceuser_usermoney) {
		this.serviceuser_usermoney = serviceuser_usermoney;
	}

	public String getServiceuser_useropenid() {
		return serviceuser_useropenid;
	}

	public void setServiceuser_useropenid(String serviceuser_useropenid) {
		this.serviceuser_useropenid = serviceuser_useropenid;
	}

	public String getServiceuser_userposition() {
		return serviceuser_userposition;
	}

	public void setServiceuser_userposition(String serviceuser_userposition) {
		this.serviceuser_userposition = serviceuser_userposition;
	}

	public Double getServiceuser_userlatitude() {
		return serviceuser_userlatitude;
	}

	public void setServiceuser_userlatitude(Double serviceuser_userlatitude) {
		this.serviceuser_userlatitude = serviceuser_userlatitude;
	}

	public Double getServiceuser_userlongitude() {
		return serviceuser_userlongitude;
	}

	public void setServiceuser_userlongitude(Double serviceuser_userlongitude) {
		this.serviceuser_userlongitude = serviceuser_userlongitude;
	}

	public String getServiceuser_userremark() {
		return serviceuser_userremark;
	}

	public void setServiceuser_userremark(String serviceuser_userremark) {
		this.serviceuser_userremark = serviceuser_userremark;
	}

	public String getCustomeruser_username() {
		return customeruser_username;
	}

	public void setCustomeruser_username(String customeruser_username) {
		this.customeruser_username = customeruser_username;
	}

	public String getCustomeruser_userrealname() {
		return customeruser_userrealname;
	}

	public void setCustomeruser_userrealname(String customeruser_userrealname) {
		this.customeruser_userrealname = customeruser_userrealname;
	}

	public String getCustomeruser_usersex() {
		return customeruser_usersex;
	}

	public void setCustomeruser_usersex(String customeruser_usersex) {
		this.customeruser_usersex = customeruser_usersex;
	}

	public String getCustomeruser_userphone() {
		return customeruser_userphone;
	}

	public void setCustomeruser_userphone(String customeruser_userphone) {
		this.customeruser_userphone = customeruser_userphone;
	}

	public String getCustomeruser_usercodeid() {
		return customeruser_usercodeid;
	}

	public void setCustomeruser_usercodeid(String customeruser_usercodeid) {
		this.customeruser_usercodeid = customeruser_usercodeid;
	}

	public String getCustomeruser_useremail() {
		return customeruser_useremail;
	}

	public void setCustomeruser_useremail(String customeruser_useremail) {
		this.customeruser_useremail = customeruser_useremail;
	}

	public String getCustomeruser_userrole() {
		return customeruser_userrole;
	}

	public void setCustomeruser_userrole(String customeruser_userrole) {
		this.customeruser_userrole = customeruser_userrole;
	}

	public String getCustomeruser_userstate() {
		return customeruser_userstate;
	}

	public void setCustomeruser_userstate(String customeruser_userstate) {
		this.customeruser_userstate = customeruser_userstate;
	}

	public Double getCustomeruser_usercredit() {
		return customeruser_usercredit;
	}

	public void setCustomeruser_usercredit(Double customeruser_usercredit) {
		this.customeruser_usercredit = customeruser_usercredit;
	}

	public Double getCustomeruser_usermoney() {
		return customeruser_usermoney;
	}

	public void setCustomeruser_usermoney(Double customeruser_usermoney) {
		this.customeruser_usermoney = customeruser_usermoney;
	}

	public String getCustomeruser_useropenid() {
		return customeruser_useropenid;
	}

	public void setCustomeruser_useropenid(String customeruser_useropenid) {
		this.customeruser_useropenid = customeruser_useropenid;
	}

	public String getCustomeruser_userposition() {
		return customeruser_userposition;
	}

	public void setCustomeruser_userposition(String customeruser_userposition) {
		this.customeruser_userposition = customeruser_userposition;
	}

	public Double getCustomeruser_userlatitude() {
		return customeruser_userlatitude;
	}

	public void setCustomeruser_userlatitude(Double customeruser_userlatitude) {
		this.customeruser_userlatitude = customeruser_userlatitude;
	}

	public Double getCustomeruser_userlongitude() {
		return customeruser_userlongitude;
	}

	public void setCustomeruser_userlongitude(Double customeruser_userlongitude) {
		this.customeruser_userlongitude = customeruser_userlongitude;
	}

	public String getCustomeruser_userremark() {
		return customeruser_userremark;
	}

	public void setCustomeruser_userremark(String customeruser_userremark) {
		this.customeruser_userremark = customeruser_userremark;
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

	public String getSelectevaluateservicecreatestarttime() {
		return selectevaluateservicecreatestarttime;
	}

	public void setSelectevaluateservicecreatestarttime(String selectevaluateservicecreatestarttime) {
		this.selectevaluateservicecreatestarttime = selectevaluateservicecreatestarttime;
	}

	public String getSelectevaluateservicecreateendtime() {
		return selectevaluateservicecreateendtime;
	}

	public void setSelectevaluateservicecreateendtime(String selectevaluateservicecreateendtime) {
		this.selectevaluateservicecreateendtime = selectevaluateservicecreateendtime;
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

	public String getEvaluateservicesetupid() {
		return evaluateservicesetupid;
	}

	public void setEvaluateservicesetupid(String evaluateservicesetupid) {
		this.evaluateservicesetupid = evaluateservicesetupid;
	}

	public String getEvaluateservicesetupname() {
		return evaluateservicesetupname;
	}

	public void setEvaluateservicesetupname(String evaluateservicesetupname) {
		this.evaluateservicesetupname = evaluateservicesetupname;
	}

	public String getEvaluateservicesetupscore() {
		return evaluateservicesetupscore;
	}

	public void setEvaluateservicesetupscore(String evaluateservicesetupscore) {
		this.evaluateservicesetupscore = evaluateservicesetupscore;
	}

	public String getOrdercheckallstate() {
		return ordercheckallstate;
	}

	public void setOrdercheckallstate(String ordercheckallstate) {
		this.ordercheckallstate = ordercheckallstate;
	}
}
