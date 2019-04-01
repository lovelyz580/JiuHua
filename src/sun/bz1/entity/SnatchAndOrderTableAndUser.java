package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单抢单记录表、维修单表、抢单人员(维修工、安装队)(用户表)、抢单确认人员(用户表)
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class SnatchAndOrderTableAndUser {

	/**
	 * 维修单抢单记录表-序号(自增主键)
	 */
	private Integer id;

	/**
	 * 维修单抢单记录表-抢单记录ID(QDJL+UUID)
	 */
	private String snatchid;

	/**
	 * 维修单抢单记录表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 维修单抢单记录表-抢单人员ID(维修工、安装队)(YHBG+UUID)
	 */
	private String serviceuserid;
	
	/**
	 * 维修单抢单记录表-抢单维修总价
	 */
	private Double snatchmoney;
	
	/**
	 * 维修单抢单记录表-抢单维修差旅费总价
	 */
	private Double snatchtravelmoney;
	
	/**
	 * 维修单抢单记录表-总价(维修总价+维修差旅费总价)
	 * 
	 * 业务字段，数据表中没有该字段
	 */
	private Double snatchtotalmoney;

	/**
	 * 维修单抢单记录表-抢单状态(Y:已抢到/N:未抢到)
	 */
	private String snatchstate;
	
	/**
	 * 维修单抢单记录表-抢单报名时间
	 */
	private Date snatchenrolltime;

	/**
	 * 维修单抢单记录表-抢单确认人员ID(YHBG+UUID)(只有抢到维修单的才会添加这个字段的数据)
	 */
	private String snatchconfirmuserid;

	/**
	 * 维修单抢单记录表-抢单确认时间(只有抢到维修单的才会添加这个字段的数据)
	 */
	private Date snatchconfirmtime;

	/**
	 * 维修单抢单记录表-抢单备注
	 */
	private String snatchremark;

	/**
	 * 维修单抢单记录表-竞标中的日工资
	 */
	private Double snatchdaymoney;

	/**
	 * 维修单抢单记录表-竞标中的天数
	 */
	private Integer snatchday;

	/**
	 * 维修单抢单记录表-竞标中的人数
	 */
	private Integer snatchpeoplenumber;

	/**
	 * 维修单抢单记录表-竞标中的材料费
	 */
	private Double snatchmaterialmoney;

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
	 * 维修单表-维修状态维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
	 */
	private String orderstate;

	/**
	 * 维修单表-维修工、安装队ID(YHBG+UUID)(确认维修工、安装队后，才会添加这个字段的数据)
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
	 * 地产公司ID(DCGS+UUID)
	 */
	private String orderpropertycompanyid;
	/**
	 * 付款方式内容
	 */
	private String orderpaymentmethodcontent;

	/**
	 * 维修单表--多次申请验收，是否全部申请验收：ALL_CHECK
	 */
	private String ordercheckallstate;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户名
	 */
	private String serviceuser_username;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户真实姓名
	 */
	private String serviceuser_userrealname;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户性别(Y:男/X:女)
	 */
	private String serviceuser_usersex;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户手机号码
	 */
	private String serviceuser_userphone;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户身份证号码
	 */
	private String serviceuser_usercodeid;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户邮箱
	 */
	private String serviceuser_useremail;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String serviceuser_userrole;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String serviceuser_userstate;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户信用值
	 */
	private Double serviceuser_usercredit;
	
	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户余额
	 */
	private Double serviceuser_usermoney;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户微信OpenId
	 */
	private String serviceuser_useropenid;
	
	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户位置信息(当前位置信息)
	 */
	private String serviceuser_userposition;
	
	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户位置纬度
	 */
	private Double serviceuser_userlatitude;
	
	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户位置经度
	 */
	private Double serviceuser_userlongitude;

	/**
	 * 抢单人员(维修工、安装队)(用户表)-用户备注
	 */
	private String serviceuser_userremark;

	/**
	 * 抢单确认人员(用户表)-用户名
	 */
	private String snatchconfirmuser_username;

	/**
	 * 抢单确认人员(用户表)-用户真实姓名
	 */
	private String snatchconfirmuser_userrealname;

	/**
	 * 抢单确认人员(用户表)-用户性别(Y:男/X:女)
	 */
	private String snatchconfirmuser_usersex;

	/**
	 * 抢单确认人员(用户表)-用户手机号码
	 */
	private String snatchconfirmuser_userphone;

	/**
	 * 抢单确认人员(用户表)-用户身份证号码
	 */
	private String snatchconfirmuser_usercodeid;

	/**
	 * 抢单确认人员(用户表)-用户邮箱
	 */
	private String snatchconfirmuser_useremail;

	/**
	 * 抢单确认人员(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String snatchconfirmuser_userrole;

	/**
	 * 抢单确认人员(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String snatchconfirmuser_userstate;

	/**
	 * 抢单确认人员(用户表)-用户信用值
	 */
	private Double snatchconfirmuser_usercredit;
	
	/**
	 * 抢单确认人员(用户表)-用户余额
	 */
	private Double snatchconfirmuser_usermoney;

	/**
	 * 抢单确认人员(用户表)-用户微信OpenId
	 */
	private String snatchconfirmuser_useropenid;
	
	/**
	 * 抢单确认人员(用户表)-用户位置信息(当前位置信息)
	 */
	private String snatchconfirmuser_userposition;
	
	/**
	 * 抢单确认人员(用户表)-用户位置纬度
	 */
	private Double snatchconfirmuser_userlatitude;
	
	/**
	 * 抢单确认人员(用户表)-用户位置经度
	 */
	private Double snatchconfirmuser_userlongitude;

	/**
	 * 抢单确认人员(用户表)-用户备注
	 */
	private String snatchconfirmuser_userremark;
	
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
	 * 根据维修单抢单记录表的抢单报名时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectsnatchenrollstarttime;

	/**
	 * 根据维修单抢单记录表的抢单报名时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectsnatchenrollendtime;
	
	/**
	 * 根据维修单抢单记录表的抢单确认时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectsnatchconfirmstarttime;

	/**
	 * 根据维修单抢单记录表的抢单确认时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectsnatchconfirmendtime;

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

	public String getSnatchid() {
		return snatchid;
	}

	public void setSnatchid(String snatchid) {
		this.snatchid = snatchid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getServiceuserid() {
		return serviceuserid;
	}

	public void setServiceuserid(String serviceuserid) {
		this.serviceuserid = serviceuserid;
	}
	
	public Double getSnatchmoney() {
		return snatchmoney;
	}

	public void setSnatchmoney(Double snatchmoney) {
		this.snatchmoney = snatchmoney;
	}

	public Double getSnatchtravelmoney() {
		return snatchtravelmoney;
	}

	public void setSnatchtravelmoney(Double snatchtravelmoney) {
		this.snatchtravelmoney = snatchtravelmoney;
	}

	public Double getSnatchtotalmoney() {
		Double money = snatchmoney;
		Double travelmoney = snatchtravelmoney;
		
		if (money == null) {
			money = 0.0;
		} 
		
		if (travelmoney == null) {
			travelmoney = 0.0;
		}
		
		snatchtotalmoney = money + travelmoney;
		
		return snatchtotalmoney;
	}

	public void setSnatchtotalmoney(Double snatchtotalmoney) {
		this.snatchtotalmoney = snatchtotalmoney;
	}

	public String getSnatchstate() {
		return snatchstate;
	}

	public void setSnatchstate(String snatchstate) {
		this.snatchstate = snatchstate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getSnatchenrolltime() {
		return snatchenrolltime;
	}

	public void setSnatchenrolltime(Date snatchenrolltime) {
		this.snatchenrolltime = snatchenrolltime;
	}

	public String getSnatchconfirmuserid() {
		return snatchconfirmuserid;
	}

	public void setSnatchconfirmuserid(String snatchconfirmuserid) {
		this.snatchconfirmuserid = snatchconfirmuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getSnatchconfirmtime() {
		return snatchconfirmtime;
	}

	public void setSnatchconfirmtime(Date snatchconfirmtime) {
		this.snatchconfirmtime = snatchconfirmtime;
	}

	public String getSnatchremark() {
		return snatchremark;
	}

	public void setSnatchremark(String snatchremark) {
		this.snatchremark = snatchremark;
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

	public String getSnatchconfirmuser_username() {
		return snatchconfirmuser_username;
	}

	public void setSnatchconfirmuser_username(String snatchconfirmuser_username) {
		this.snatchconfirmuser_username = snatchconfirmuser_username;
	}

	public String getSnatchconfirmuser_userrealname() {
		return snatchconfirmuser_userrealname;
	}

	public void setSnatchconfirmuser_userrealname(String snatchconfirmuser_userrealname) {
		this.snatchconfirmuser_userrealname = snatchconfirmuser_userrealname;
	}

	public String getSnatchconfirmuser_usersex() {
		return snatchconfirmuser_usersex;
	}

	public void setSnatchconfirmuser_usersex(String snatchconfirmuser_usersex) {
		this.snatchconfirmuser_usersex = snatchconfirmuser_usersex;
	}

	public String getSnatchconfirmuser_userphone() {
		return snatchconfirmuser_userphone;
	}

	public void setSnatchconfirmuser_userphone(String snatchconfirmuser_userphone) {
		this.snatchconfirmuser_userphone = snatchconfirmuser_userphone;
	}

	public String getSnatchconfirmuser_usercodeid() {
		return snatchconfirmuser_usercodeid;
	}

	public void setSnatchconfirmuser_usercodeid(String snatchconfirmuser_usercodeid) {
		this.snatchconfirmuser_usercodeid = snatchconfirmuser_usercodeid;
	}

	public String getSnatchconfirmuser_useremail() {
		return snatchconfirmuser_useremail;
	}

	public void setSnatchconfirmuser_useremail(String snatchconfirmuser_useremail) {
		this.snatchconfirmuser_useremail = snatchconfirmuser_useremail;
	}

	public String getSnatchconfirmuser_userrole() {
		return snatchconfirmuser_userrole;
	}

	public void setSnatchconfirmuser_userrole(String snatchconfirmuser_userrole) {
		this.snatchconfirmuser_userrole = snatchconfirmuser_userrole;
	}

	public String getSnatchconfirmuser_userstate() {
		return snatchconfirmuser_userstate;
	}

	public void setSnatchconfirmuser_userstate(String snatchconfirmuser_userstate) {
		this.snatchconfirmuser_userstate = snatchconfirmuser_userstate;
	}

	public Double getSnatchconfirmuser_usercredit() {
		return snatchconfirmuser_usercredit;
	}

	public void setSnatchconfirmuser_usercredit(Double snatchconfirmuser_usercredit) {
		this.snatchconfirmuser_usercredit = snatchconfirmuser_usercredit;
	}

	public Double getSnatchconfirmuser_usermoney() {
		return snatchconfirmuser_usermoney;
	}

	public void setSnatchconfirmuser_usermoney(Double snatchconfirmuser_usermoney) {
		this.snatchconfirmuser_usermoney = snatchconfirmuser_usermoney;
	}

	public String getSnatchconfirmuser_useropenid() {
		return snatchconfirmuser_useropenid;
	}

	public void setSnatchconfirmuser_useropenid(String snatchconfirmuser_useropenid) {
		this.snatchconfirmuser_useropenid = snatchconfirmuser_useropenid;
	}

	public String getSnatchconfirmuser_userposition() {
		return snatchconfirmuser_userposition;
	}

	public void setSnatchconfirmuser_userposition(String snatchconfirmuser_userposition) {
		this.snatchconfirmuser_userposition = snatchconfirmuser_userposition;
	}

	public Double getSnatchconfirmuser_userlatitude() {
		return snatchconfirmuser_userlatitude;
	}

	public void setSnatchconfirmuser_userlatitude(Double snatchconfirmuser_userlatitude) {
		this.snatchconfirmuser_userlatitude = snatchconfirmuser_userlatitude;
	}

	public Double getSnatchconfirmuser_userlongitude() {
		return snatchconfirmuser_userlongitude;
	}

	public void setSnatchconfirmuser_userlongitude(Double snatchconfirmuser_userlongitude) {
		this.snatchconfirmuser_userlongitude = snatchconfirmuser_userlongitude;
	}

	public String getSnatchconfirmuser_userremark() {
		return snatchconfirmuser_userremark;
	}

	public void setSnatchconfirmuser_userremark(String snatchconfirmuser_userremark) {
		this.snatchconfirmuser_userremark = snatchconfirmuser_userremark;
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

	public String getSelectsnatchenrollstarttime() {
		return selectsnatchenrollstarttime;
	}

	public void setSelectsnatchenrollstarttime(String selectsnatchenrollstarttime) {
		this.selectsnatchenrollstarttime = selectsnatchenrollstarttime;
	}

	public String getSelectsnatchenrollendtime() {
		return selectsnatchenrollendtime;
	}

	public void setSelectsnatchenrollendtime(String selectsnatchenrollendtime) {
		this.selectsnatchenrollendtime = selectsnatchenrollendtime;
	}

	public String getSelectsnatchconfirmstarttime() {
		return selectsnatchconfirmstarttime;
	}

	public void setSelectsnatchconfirmstarttime(String selectsnatchconfirmstarttime) {
		this.selectsnatchconfirmstarttime = selectsnatchconfirmstarttime;
	}

	public String getSelectsnatchconfirmendtime() {
		return selectsnatchconfirmendtime;
	}

	public void setSelectsnatchconfirmendtime(String selectsnatchconfirmendtime) {
		this.selectsnatchconfirmendtime = selectsnatchconfirmendtime;
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

	public String getOrdercheckallstate() {
		return ordercheckallstate;
	}

	public void setOrdercheckallstate(String ordercheckallstate) {
		this.ordercheckallstate = ordercheckallstate;
	}

	public Double getSnatchdaymoney() {
		return snatchdaymoney;
	}

	public void setSnatchdaymoney(Double snatchdaymoney) {
		this.snatchdaymoney = snatchdaymoney;
	}

	public Integer getSnatchday() {
		return snatchday;
	}

	public void setSnatchday(Integer snatchday) {
		this.snatchday = snatchday;
	}

	public Integer getSnatchpeoplenumber() {
		return snatchpeoplenumber;
	}

	public void setSnatchpeoplenumber(Integer snatchpeoplenumber) {
		this.snatchpeoplenumber = snatchpeoplenumber;
	}

	public Double getSnatchmaterialmoney() {
		return snatchmaterialmoney;
	}

	public void setSnatchmaterialmoney(Double snatchmaterialmoney) {
		this.snatchmaterialmoney = snatchmaterialmoney;
	}

	public String getOrderpropertycompanyid() {
		return orderpropertycompanyid;
	}

	public void setOrderpropertycompanyid(String orderpropertycompanyid) {
		this.orderpropertycompanyid = orderpropertycompanyid;
	}

	public String getOrderpaymentmethodcontent() {
		return orderpaymentmethodcontent;
	}

	public void setOrderpaymentmethodcontent(String orderpaymentmethodcontent) {
		this.orderpaymentmethodcontent = orderpaymentmethodcontent;
	}
}
