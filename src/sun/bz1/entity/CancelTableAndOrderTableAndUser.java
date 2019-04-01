package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 客户取消维修单表(客户取消维修单时在该表中添加数据)、维修单表、维修取消人员(用户表)-
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/08
 */

public class CancelTableAndOrderTableAndUser {
	
	/**
	 * 客户取消维修单表-序号(自增主键)
	 */
	private Integer id;

	/**
	 * 客户取消维修单表-维修取消ID(KHQX+UUID)
	 */
	private String cancelid;

	/**
	 * 客户取消维修单表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 客户取消维修单表-维修取消内容(原因)
	 */
	private String cancelcontent;

	/**
	 * 客户取消维修单表-维修取消维修人员确认是否扣除已发生的费用状态(Y:扣除/N:不扣除)
	 */
	private String cancelstate;

	/**
	 * 客户取消维修单表-维修取消已发生的费用总价
	 */
	private Double cancelhappenedmoney;

	/**
	 * 客户取消维修单表-维修取消平台违约金总价
	 */
	private Double canceldefaultmoney;

	/**
	 * 客户取消维修单表-维修取消人ID(YHBG+UUID)
	 */
	private String canceluserid;

	/**
	 * 客户取消维修单表-维修取消时间
	 */
	private Date canceltime;

	/**
	 * 客户取消维修单表-维修取消备注
	 */
	private String cancelremark;

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
	 * 维修单表-用户位置信息(当前位置信息)
	 */
	private String orderposition;
	
	/**
	 * 维修单表-用户位置纬度
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
	 * 多次申请验收，是否全部申请验收：ALL_CHECK
	 */
	private String ordercheckallstate;

	/**
	 * 维修取消人员(用户表)-用户名
	 */
	private String username;

	/**
	 * 维修取消人员(用户表)-用户密码
	 */
	private String userpassword;

	/**
	 * 维修取消人员(用户表)-用户密码盐
	 */
	private String usersalt;

	/**
	 * 维修取消人员(用户表)-用户加密密码
	 */
	private String userencryptpassword;

	/**
	 * 维修取消人员(用户表)-用户真实姓名
	 */
	private String userrealname;

	/**
	 * 维修取消人员(用户表)-用户性别(Y:男/X:女)
	 */
	private String usersex;

	/**
	 * 维修取消人员(用户表)-用户手机号码
	 */
	private String userphone;

	/**
	 * 维修取消人员(用户表)-用户身份证号码
	 */
	private String usercodeid;

	/**
	 * 维修取消人员(用户表)-用户邮箱
	 */
	private String useremail;

	/**
	 * 维修取消人员(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String userrole;

	/**
	 * 维修取消人员(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String userstate;

	/**
	 * 维修取消人员(用户表)-用户信用值
	 */
	private Double usercredit;
	
	/**
	 * 维修取消人员(用户表)-用户余额
	 */
	private Double usermoney;

	/**
	 * 维修取消人员(用户表)-用户短信验证码
	 */
	private String usersms;

	/**
	 * 维修取消人员(用户表)-用户短信验证码创建时间
	 */
	private Date usersmstime;

	/**
	 * 维修取消人员(用户表)-用户微信OpenId
	 */
	private String useropenid;
	
	/**
	 * 维修取消人员(用户表)-用户位置信息(当前位置信息)
	 */
	private String userposition;
	
	/**
	 * 维修取消人员(用户表)-用户位置纬度
	 */
	private Double userlatitude;
	
	/**
	 * 维修取消人员(用户表)-用户位置经度
	 */
	private Double userlongitude;

	/**
	 * 维修取消人员(用户表)-用户创建人ID(YHBG+UUID)
	 */
	private String usercreateuserid;

	/**
	 * 维修取消人员(用户表)-用户创建时间
	 */
	private Date usercreatetime;

	/**
	 * 维修取消人员(用户表)-用户更新人ID(YHBG+UUID)
	 */
	private String userupdateuserid;

	/**
	 * 维修取消人员(用户表)-用户更新时间
	 */
	private Date userupdatetime;

	/**
	 * 维修取消人员(用户表)-用户备注
	 */
	private String userremark;

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
	 * 根据客户取消维修单表的退回时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectcancelstarttime;

	/**
	 * 根据客户取消维修单表的退回时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectcancelendtime;

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

	public String getCancelid() {
		return cancelid;
	}

	public void setCancelid(String cancelid) {
		this.cancelid = cancelid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCancelcontent() {
		return cancelcontent;
	}

	public void setCancelcontent(String cancelcontent) {
		this.cancelcontent = cancelcontent;
	}

	public String getCancelstate() {
		return cancelstate;
	}

	public void setCancelstate(String cancelstate) {
		this.cancelstate = cancelstate;
	}

	public Double getCancelhappenedmoney() {
		return cancelhappenedmoney;
	}

	public void setCancelhappenedmoney(Double cancelhappenedmoney) {
		this.cancelhappenedmoney = cancelhappenedmoney;
	}

	public Double getCanceldefaultmoney() {
		return canceldefaultmoney;
	}

	public void setCanceldefaultmoney(Double canceldefaultmoney) {
		this.canceldefaultmoney = canceldefaultmoney;
	}

	public String getCanceluserid() {
		return canceluserid;
	}

	public void setCanceluserid(String canceluserid) {
		this.canceluserid = canceluserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getCanceltime() {
		return canceltime;
	}

	public void setCanceltime(Date canceltime) {
		this.canceltime = canceltime;
	}

	public String getCancelremark() {
		return cancelremark;
	}

	public void setCancelremark(String cancelremark) {
		this.cancelremark = cancelremark;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsersalt() {
		return usersalt;
	}

	public void setUsersalt(String usersalt) {
		this.usersalt = usersalt;
	}

	public String getUserencryptpassword() {
		return userencryptpassword;
	}

	public void setUserencryptpassword(String userencryptpassword) {
		this.userencryptpassword = userencryptpassword;
	}

	public String getUserrealname() {
		return userrealname;
	}

	public void setUserrealname(String userrealname) {
		this.userrealname = userrealname;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUsercodeid() {
		return usercodeid;
	}

	public void setUsercodeid(String usercodeid) {
		this.usercodeid = usercodeid;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}

	public Double getUsercredit() {
		return usercredit;
	}

	public void setUsercredit(Double usercredit) {
		this.usercredit = usercredit;
	}

	public Double getUsermoney() {
		return usermoney;
	}

	public void setUsermoney(Double usermoney) {
		this.usermoney = usermoney;
	}

	public String getUsersms() {
		return usersms;
	}

	public void setUsersms(String usersms) {
		this.usersms = usersms;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getUsersmstime() {
		return usersmstime;
	}

	public void setUsersmstime(Date usersmstime) {
		this.usersmstime = usersmstime;
	}

	public String getUseropenid() {
		return useropenid;
	}

	public void setUseropenid(String useropenid) {
		this.useropenid = useropenid;
	}

	public String getUserposition() {
		return userposition;
	}

	public void setUserposition(String userposition) {
		this.userposition = userposition;
	}

	public Double getUserlatitude() {
		return userlatitude;
	}

	public void setUserlatitude(Double userlatitude) {
		this.userlatitude = userlatitude;
	}

	public Double getUserlongitude() {
		return userlongitude;
	}

	public void setUserlongitude(Double userlongitude) {
		this.userlongitude = userlongitude;
	}

	public String getUsercreateuserid() {
		return usercreateuserid;
	}

	public void setUsercreateuserid(String usercreateuserid) {
		this.usercreateuserid = usercreateuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getUsercreatetime() {
		return usercreatetime;
	}

	public void setUsercreatetime(Date usercreatetime) {
		this.usercreatetime = usercreatetime;
	}

	public String getUserupdateuserid() {
		return userupdateuserid;
	}

	public void setUserupdateuserid(String userupdateuserid) {
		this.userupdateuserid = userupdateuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getUserupdatetime() {
		return userupdatetime;
	}

	public void setUserupdatetime(Date userupdatetime) {
		this.userupdatetime = userupdatetime;
	}

	public String getUserremark() {
		return userremark;
	}

	public void setUserremark(String userremark) {
		this.userremark = userremark;
	}

	public String getSelectcancelstarttime() {
		return selectcancelstarttime;
	}

	public void setSelectcancelstarttime(String selectcancelstarttime) {
		this.selectcancelstarttime = selectcancelstarttime;
	}

	public String getSelectcancelendtime() {
		return selectcancelendtime;
	}

	public void setSelectcancelendtime(String selectcancelendtime) {
		this.selectcancelendtime = selectcancelendtime;
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
}