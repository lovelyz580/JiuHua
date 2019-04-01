package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单分配记录表、维修单表、被分配人员(用户表)、分配人员(用户表)
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class DistributionAndOrderTableAndUser {
	
	/**
	 * 维修单分配记录表-序号(自增主键)
	 */
    private Integer id;

    /**
	 * 维修单分配记录表-分配记录ID(FPJL+UUID)
	 */
    private String distributionid;

    /**
	 * 维修单分配记录表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;
    
    /**
	 * 维修单分配记录表-被分配人员ID(维修人员)(YHBG+UUID)
	 */
    private String serviceuserid;

    /**
	 * 维修单分配记录表-分配人员ID(YHBG+UUID)
	 */
    private String distributionuserid;

    /**
	 * 维修单分配记录表-分配时间
	 */
    private Date distributiontime;

    /**
	 * 维修单分配记录表-分配备注
	 */
    private String distributionremark;
    
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
	 * 被分配人员(用户表)-用户名
	 */
	private String serviceuser_username;

	/**
	 * 被分配人员(用户表)-用户真实姓名
	 */
	private String serviceuser_userrealname;

	/**
	 * 被分配人员(用户表)-用户性别(Y:男/X:女)
	 */
	private String serviceuser_usersex;

	/**
	 * 被分配人员(用户表)-用户手机号码
	 */
	private String serviceuser_userphone;

	/**
	 * 被分配人员(用户表)-用户身份证号码
	 */
	private String serviceuser_usercodeid;

	/**
	 * 被分配人员(用户表)-用户邮箱
	 */
	private String serviceuser_useremail;

	/**
	 * 被分配人员(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String serviceuser_userrole;

	/**
	 * 被分配人员(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String serviceuser_userstate;

	/**
	 * 被分配人员(用户表)-用户信用值
	 */
	private Double serviceuser_usercredit;
	
	/**
	 * 被分配人员(用户表)-用户余额
	 */
	private Double serviceuser_usermoney;

	/**
	 * 被分配人员(用户表)-用户微信OpenId
	 */
	private String serviceuser_useropenid;
	
	/**
	 * 被分配人员(用户表)-用户位置信息(当前位置信息)
	 */
	private String serviceuser_userposition;
	
	/**
	 * 被分配人员(用户表)-用户位置纬度
	 */
	private Double serviceuser_userlatitude;
	
	/**
	 * 被分配人员(用户表)-用户位置经度
	 */
	private Double serviceuser_userlongitude;

	/**
	 * 被分配人员(用户表)-用户备注
	 */
	private String serviceuser_userremark;
	
	/**
	 * 分配人员(用户表)-用户名
	 */
	private String distributionuser_username;

	/**
	 * 分配人员(用户表)-用户真实姓名
	 */
	private String distributionuser_userrealname;

	/**
	 * 分配人员(用户表)-用户性别(Y:男/X:女)
	 */
	private String distributionuser_usersex;

	/**
	 * 分配人员(用户表)-用户手机号码
	 */
	private String distributionuser_userphone;

	/**
	 * 分配人员(用户表)-用户身份证号码
	 */
	private String distributionuser_usercodeid;

	/**
	 * 分配人员(用户表)-用户邮箱
	 */
	private String distributionuser_useremail;

	/**
	 * 分配人员(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String distributionuser_userrole;

	/**
	 * 分配人员(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String distributionuser_userstate;

	/**
	 * 分配人员(用户表)-用户信用值
	 */
	private Double distributionuser_usercredit;
	
	/**
	 * 分配人员(用户表)-用户余额
	 */
	private Double distributionuser_usermoney;

	/**
	 * 分配人员(用户表)-用户微信OpenId
	 */
	private String distributionuser_useropenid;
	
	/**
	 * 分配人员(用户表)-用户位置信息(当前位置信息)
	 */
	private String distributionuser_userposition;
	
	/**
	 * 分配人员(用户表)-用户位置纬度
	 */
	private Double distributionuser_userlatitude;
	
	/**
	 * 分配人员(用户表)-用户位置经度
	 */
	private Double distributionuser_userlongitude;

	/**
	 * 分配人员(用户表)-用户备注
	 */
	private String distributionuser_userremark;
	
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
	 * 根据维修单分配记录表的分配时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectdistributionstarttime;

	/**
	 * 根据维修单分配记录表的分配时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectdistributionendtime;
	
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

	public String getDistributionid() {
		return distributionid;
	}

	public void setDistributionid(String distributionid) {
		this.distributionid = distributionid;
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

	public String getDistributionuserid() {
		return distributionuserid;
	}

	public void setDistributionuserid(String distributionuserid) {
		this.distributionuserid = distributionuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getDistributiontime() {
		return distributiontime;
	}

	public void setDistributiontime(Date distributiontime) {
		this.distributiontime = distributiontime;
	}

	public String getDistributionremark() {
		return distributionremark;
	}

	public void setDistributionremark(String distributionremark) {
		this.distributionremark = distributionremark;
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

	public String getDistributionuser_username() {
		return distributionuser_username;
	}

	public void setDistributionuser_username(String distributionuser_username) {
		this.distributionuser_username = distributionuser_username;
	}

	public String getDistributionuser_userrealname() {
		return distributionuser_userrealname;
	}

	public void setDistributionuser_userrealname(String distributionuser_userrealname) {
		this.distributionuser_userrealname = distributionuser_userrealname;
	}

	public String getDistributionuser_usersex() {
		return distributionuser_usersex;
	}

	public void setDistributionuser_usersex(String distributionuser_usersex) {
		this.distributionuser_usersex = distributionuser_usersex;
	}

	public String getDistributionuser_userphone() {
		return distributionuser_userphone;
	}

	public void setDistributionuser_userphone(String distributionuser_userphone) {
		this.distributionuser_userphone = distributionuser_userphone;
	}

	public String getDistributionuser_usercodeid() {
		return distributionuser_usercodeid;
	}

	public void setDistributionuser_usercodeid(String distributionuser_usercodeid) {
		this.distributionuser_usercodeid = distributionuser_usercodeid;
	}

	public String getDistributionuser_useremail() {
		return distributionuser_useremail;
	}

	public void setDistributionuser_useremail(String distributionuser_useremail) {
		this.distributionuser_useremail = distributionuser_useremail;
	}

	public String getDistributionuser_userrole() {
		return distributionuser_userrole;
	}

	public void setDistributionuser_userrole(String distributionuser_userrole) {
		this.distributionuser_userrole = distributionuser_userrole;
	}

	public String getDistributionuser_userstate() {
		return distributionuser_userstate;
	}

	public void setDistributionuser_userstate(String distributionuser_userstate) {
		this.distributionuser_userstate = distributionuser_userstate;
	}

	public Double getDistributionuser_usercredit() {
		return distributionuser_usercredit;
	}

	public void setDistributionuser_usercredit(Double distributionuser_usercredit) {
		this.distributionuser_usercredit = distributionuser_usercredit;
	}
	
	public Double getDistributionuser_usermoney() {
		return distributionuser_usermoney;
	}

	public void setDistributionuser_usermoney(Double distributionuser_usermoney) {
		this.distributionuser_usermoney = distributionuser_usermoney;
	}

	public String getDistributionuser_userposition() {
		return distributionuser_userposition;
	}

	public void setDistributionuser_userposition(String distributionuser_userposition) {
		this.distributionuser_userposition = distributionuser_userposition;
	}

	public Double getDistributionuser_userlatitude() {
		return distributionuser_userlatitude;
	}

	public void setDistributionuser_userlatitude(Double distributionuser_userlatitude) {
		this.distributionuser_userlatitude = distributionuser_userlatitude;
	}

	public Double getDistributionuser_userlongitude() {
		return distributionuser_userlongitude;
	}

	public void setDistributionuser_userlongitude(Double distributionuser_userlongitude) {
		this.distributionuser_userlongitude = distributionuser_userlongitude;
	}

	public String getDistributionuser_useropenid() {
		return distributionuser_useropenid;
	}

	public void setDistributionuser_useropenid(String distributionuser_useropenid) {
		this.distributionuser_useropenid = distributionuser_useropenid;
	}

	public String getDistributionuser_userremark() {
		return distributionuser_userremark;
	}

	public void setDistributionuser_userremark(String distributionuser_userremark) {
		this.distributionuser_userremark = distributionuser_userremark;
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

	public String getSelectdistributionstarttime() {
		return selectdistributionstarttime;
	}

	public void setSelectdistributionstarttime(String selectdistributionstarttime) {
		this.selectdistributionstarttime = selectdistributionstarttime;
	}

	public String getSelectdistributionendtime() {
		return selectdistributionendtime;
	}

	public void setSelectdistributionendtime(String selectdistributionendtime) {
		this.selectdistributionendtime = selectdistributionendtime;
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
