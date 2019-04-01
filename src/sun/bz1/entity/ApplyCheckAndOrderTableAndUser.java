package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修验收表(验收成功或失败时在该表中添加数据)、维修单表、验收人员(用户表)、平台操作人员(用户表)
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class ApplyCheckAndOrderTableAndUser {

	/**
	 * 维修验收表-序号(自增主键)
	 */
    private Integer id;

    /**
	 * 维修验收表-维修验收ID(WXYS+UUID)
	 */
    private String applycheckid;

    /**
	 * 维修验收表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 维修验收表-维修验收状态(SQYS:申请验收)(HG:合格)((派单模式)GHWX:更换维修人员)((抢单模式)WTPD:委托平台派单/PTXT:平台协调)(ZDYS:自动验收)
	 */
    private String applycheckstate;

	/**
	 * 维修验收表-状态为不合格的状态
	 */
	private String applycheckbhgstate;

    /**
	 * 维修验收表-维修验收图片路径
	 */
    private String applycheckimage;

    /**
	 * 维修验收表-维修验收视频路径
	 */
    private String applycheckvideo;
    
    /**
	 * 维修验收表-维修验收申请时间
	 */
    private Date applycheckapplytime;
    
    /**
	 * 维修验收表-维修自动验收时间(维修申请14天后，客户不验收，则平台自动验收)
	 */
    private Date applycheckautomatictime;

    /**
	 * 维修验收表-维修验收最终结算总价(合格、平台协调状态时，才会添加这个字段的数据)
	 */
    private Double applycheckmoney;
    
    /**
	 * 维修验收表-维修验收平台协调服务最终结算总价(平台协调状态时，才会添加这个字段的数据)
	 */
    private Double applycheckbackmoney;
    
    /**
	 * 维修验收表-维修验收平台协调最终过错方(WX:维修工/AZ:安装队)(平台协调状态时，才会添加这个字段的数据)
	 */
    private String applycheckbackfault;

    /**
	 * 维修验收表-维修验收平台协调标题(平台协调状态时，才会添加这个字段的数据)
	 */
    private String applychecktitle;

    /**
	 * 维修验收表-维修验收平台协调内容(平台协调状态时，才会添加这个字段的数据)
	 */
    private String applycheckcontent;

    /**
	 * 维修验收表-维修验收人ID(YHBG+UUID)
	 */
    private String applycheckuserid;

    /**
	 * 维修验收表-维修验收时间
	 */
    private Date applychecktime;

    /**
	 * 维修验收表-维修平台操作人ID(YHBG+UUID)(平台协调状态并且平台人员强制操作时，才会添加这个字段的数据)
	 */
    private String applycheckbackuserid;

    /**
	 * 维修验收表-维修平台操作时间(平台协调状态并且平台人员强制操作时，才会添加这个字段的数据)
	 */
    private Date applycheckbacktime;

    /**
	 * 维修验收表-维修验收备注
	 */
    private String applycheckremark;

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
	 * 维修单表-用户位置经度
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
	 * 维修单表-维修人userid
	 */
	private String orderserviceuserid;

	/**
	 * 维修单表-维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
	 */
	private String orderstate;

	/**
	 * 维修单表-维修人员ID(YHBG+UUID)(确认维修人员后，才会添加这个字段的数据)
	 */
	private String orderapplycheckbackuserid;

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
	 * 维修单表-多次申请验收，是否全部申请验收：ALL_CHECK
	 */
	private String ordercheckallstate;


	/**
	 * 验收人员(用户表)-用户名
	 */
	private String applycheckuser_username;

	/**
	 * 验收人员(用户表)-用户真实姓名
	 */
	private String applycheckuser_userrealname;

	/**
	 * 验收人员(用户表)-用户性别(Y:男/X:女)
	 */
	private String applycheckuser_usersex;

	/**
	 * 验收人员(用户表)-用户手机号码
	 */
	private String applycheckuser_userphone;

	/**
	 * 验收人员(用户表)-用户身份证号码
	 */
	private String applycheckuser_usercodeid;

	/**
	 * 验收人员(用户表)-用户邮箱
	 */
	private String applycheckuser_useremail;

	/**
	 * 验收人员(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String applycheckuser_userrole;

	/**
	 * 验收人员(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String applycheckuser_userstate;

	/**
	 * 验收人员(用户表)-用户信用值
	 */
	private Double applycheckuser_usercredit;
	
	/**
	 * 验收人员(用户表)-用户余额
	 */
	private Double applycheckuser_usermoney;

	/**
	 * 验收人员(用户表)-用户微信OpenId
	 */
	private String applycheckuser_useropenid;
	
	/**
	 * 验收人员(用户表)-用户位置信息(当前位置信息)
	 */
	private String applycheckuser_userposition;
	
	/**
	 * 验收人员(用户表)-用户位置纬度
	 */
	private Double applycheckuser_userlatitude;
	
	/**
	 * 验收人员(用户表)-用户位置经度
	 */
	private Double applycheckuser_userlongitude;

	/**
	 * 验收人员(用户表)-用户备注
	 */
	private String applycheckuser_userremark;

	/**
	 * 平台操作人员(用户表)-用户名
	 */
	private String applycheckbackuser_username;

	/**
	 * 平台操作人员(用户表)-用户真实姓名
	 */
	private String applycheckbackuser_userrealname;

	/**
	 * 平台操作人员(用户表)-用户性别(Y:男/X:女)
	 */
	private String applycheckbackuser_usersex;

	/**
	 * 平台操作人员(用户表)-用户手机号码
	 */
	private String applycheckbackuser_userphone;

	/**
	 * 平台操作人员(用户表)-用户身份证号码
	 */
	private String applycheckbackuser_usercodeid;

	/**
	 * 平台操作人员(用户表)-用户邮箱
	 */
	private String applycheckbackuser_useremail;

	/**
	 * 平台操作人员(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String applycheckbackuser_userrole;

	/**
	 * 平台操作人员(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String applycheckbackuser_userstate;

	/**
	 * 平台操作人员(用户表)-用户信用值
	 */
	private Double applycheckbackuser_usercredit;
	
	/**
	 * 平台操作人员(用户表)-用户余额
	 */
	private Double applycheckbackuser_usermoney;

	/**
	 * 平台操作人员(用户表)-用户微信OpenId
	 */
	private String applycheckbackuser_useropenid;
	
	/**
	 * 平台操作人员(用户表)-用户位置信息(当前位置信息)
	 */
	private String applycheckbackuser_userposition;
	
	/**
	 * 平台操作人员(用户表)-用户位置纬度
	 */
	private Double applycheckbackuser_userlatitude;
	
	/**
	 * 平台操作人员(用户表)-用户位置经度
	 */
	private Double applycheckbackuser_userlongitude;

	/**
	 * 平台操作人员(用户表)-用户备注
	 */
	private String applycheckbackuser_userremark;

	/**
	 * 验收剩余金额客户看到的
	 */
	private Double surplusmoney;

    /**
     * 验收剩余金额客户看到的
     */
    private Double customermoney;
    /**
     * 验收剩余金额维修看到的
     */
    private Double servicesurplusmoney;

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
	 * 根据维修验收表的维修验收时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectapplycheckstarttime;

	/**
	 * 根据维修验收表的维修验收时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectapplycheckendtime;
	
	/**
	 * 根据维修验收表的维修平台操作时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectapplycheckbackstarttime;

	/**
	 * 根据维修验收表的维修平台操作时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectapplycheckbackendtime;

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

	public String getApplycheckid() {
		return applycheckid;
	}

	public void setApplycheckid(String applycheckid) {
		this.applycheckid = applycheckid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getApplycheckstate() {
		return applycheckstate;
	}

	public void setApplycheckstate(String applycheckstate) {
		this.applycheckstate = applycheckstate;
	}

	public String getApplycheckimage() {
		return applycheckimage;
	}

	public void setApplycheckimage(String applycheckimage) {
		this.applycheckimage = applycheckimage;
	}

	public String getApplycheckvideo() {
		return applycheckvideo;
	}

	public void setApplycheckvideo(String applycheckvideo) {
		this.applycheckvideo = applycheckvideo;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getApplycheckapplytime() {
		return applycheckapplytime;
	}

	public void setApplycheckapplytime(Date applycheckapplytime) {
		this.applycheckapplytime = applycheckapplytime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getApplycheckautomatictime() {
		return applycheckautomatictime;
	}

	public void setApplycheckautomatictime(Date applycheckautomatictime) {
		this.applycheckautomatictime = applycheckautomatictime;
	}

	public Double getApplycheckmoney() {
		return applycheckmoney;
	}

	public void setApplycheckmoney(Double applycheckmoney) {
		this.applycheckmoney = applycheckmoney;
	}

	public Double getApplycheckbackmoney() {
		return applycheckbackmoney;
	}

	public void setApplycheckbackmoney(Double applycheckbackmoney) {
		this.applycheckbackmoney = applycheckbackmoney;
	}

	public String getApplycheckbackfault() {
		return applycheckbackfault;
	}

	public void setApplycheckbackfault(String applycheckbackfault) {
		this.applycheckbackfault = applycheckbackfault;
	}

	public String getApplychecktitle() {
		return applychecktitle;
	}

	public void setApplychecktitle(String applychecktitle) {
		this.applychecktitle = applychecktitle;
	}

	public String getApplycheckcontent() {
		return applycheckcontent;
	}

	public void setApplycheckcontent(String applycheckcontent) {
		this.applycheckcontent = applycheckcontent;
	}

	public String getApplycheckuserid() {
		return applycheckuserid;
	}

	public void setApplycheckuserid(String applycheckuserid) {
		this.applycheckuserid = applycheckuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getApplychecktime() {
		return applychecktime;
	}

	public void setApplychecktime(Date applychecktime) {
		this.applychecktime = applychecktime;
	}

	public String getApplycheckbackuserid() {
		return applycheckbackuserid;
	}

	public void setApplycheckbackuserid(String applycheckbackuserid) {
		this.applycheckbackuserid = applycheckbackuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getApplycheckbacktime() {
		return applycheckbacktime;
	}

	public void setApplycheckbacktime(Date applycheckbacktime) {
		this.applycheckbacktime = applycheckbacktime;
	}

	public String getApplycheckremark() {
		return applycheckremark;
	}

	public void setApplycheckremark(String applycheckremark) {
		this.applycheckremark = applycheckremark;
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

	public String getOrderapplycheckbackuserid() {
		return orderapplycheckbackuserid;
	}

	public void setOrderapplycheckbackuserid(String orderapplycheckbackuserid) {
		this.orderapplycheckbackuserid = orderapplycheckbackuserid;
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

	public String getApplycheckuser_username() {
		return applycheckuser_username;
	}

	public void setApplycheckuser_username(String applycheckuser_username) {
		this.applycheckuser_username = applycheckuser_username;
	}

	public String getApplycheckuser_userrealname() {
		return applycheckuser_userrealname;
	}

	public void setApplycheckuser_userrealname(String applycheckuser_userrealname) {
		this.applycheckuser_userrealname = applycheckuser_userrealname;
	}

	public String getApplycheckuser_usersex() {
		return applycheckuser_usersex;
	}

	public void setApplycheckuser_usersex(String applycheckuser_usersex) {
		this.applycheckuser_usersex = applycheckuser_usersex;
	}

	public String getApplycheckuser_userphone() {
		return applycheckuser_userphone;
	}

	public void setApplycheckuser_userphone(String applycheckuser_userphone) {
		this.applycheckuser_userphone = applycheckuser_userphone;
	}

	public String getApplycheckuser_usercodeid() {
		return applycheckuser_usercodeid;
	}

	public void setApplycheckuser_usercodeid(String applycheckuser_usercodeid) {
		this.applycheckuser_usercodeid = applycheckuser_usercodeid;
	}

	public String getApplycheckuser_useremail() {
		return applycheckuser_useremail;
	}

	public void setApplycheckuser_useremail(String applycheckuser_useremail) {
		this.applycheckuser_useremail = applycheckuser_useremail;
	}

	public String getApplycheckuser_userrole() {
		return applycheckuser_userrole;
	}

	public void setApplycheckuser_userrole(String applycheckuser_userrole) {
		this.applycheckuser_userrole = applycheckuser_userrole;
	}

	public String getApplycheckuser_userstate() {
		return applycheckuser_userstate;
	}

	public void setApplycheckuser_userstate(String applycheckuser_userstate) {
		this.applycheckuser_userstate = applycheckuser_userstate;
	}

	public Double getApplycheckuser_usercredit() {
		return applycheckuser_usercredit;
	}

	public void setApplycheckuser_usercredit(Double applycheckuser_usercredit) {
		this.applycheckuser_usercredit = applycheckuser_usercredit;
	}
	
	public Double getApplycheckuser_usermoney() {
		return applycheckuser_usermoney;
	}

	public void setApplycheckuser_usermoney(Double applycheckuser_usermoney) {
		this.applycheckuser_usermoney = applycheckuser_usermoney;
	}

	public String getApplycheckuser_useropenid() {
		return applycheckuser_useropenid;
	}

	public void setApplycheckuser_useropenid(String applycheckuser_useropenid) {
		this.applycheckuser_useropenid = applycheckuser_useropenid;
	}

	public String getApplycheckuser_userposition() {
		return applycheckuser_userposition;
	}

	public void setApplycheckuser_userposition(String applycheckuser_userposition) {
		this.applycheckuser_userposition = applycheckuser_userposition;
	}

	public Double getApplycheckuser_userlatitude() {
		return applycheckuser_userlatitude;
	}

	public void setApplycheckuser_userlatitude(Double applycheckuser_userlatitude) {
		this.applycheckuser_userlatitude = applycheckuser_userlatitude;
	}

	public Double getApplycheckuser_userlongitude() {
		return applycheckuser_userlongitude;
	}

	public void setApplycheckuser_userlongitude(Double applycheckuser_userlongitude) {
		this.applycheckuser_userlongitude = applycheckuser_userlongitude;
	}

	public String getApplycheckuser_userremark() {
		return applycheckuser_userremark;
	}

	public void setApplycheckuser_userremark(String applycheckuser_userremark) {
		this.applycheckuser_userremark = applycheckuser_userremark;
	}

	public String getApplycheckbackuser_username() {
		return applycheckbackuser_username;
	}

	public void setApplycheckbackuser_username(String applycheckbackuser_username) {
		this.applycheckbackuser_username = applycheckbackuser_username;
	}

	public String getApplycheckbackuser_userrealname() {
		return applycheckbackuser_userrealname;
	}

	public void setApplycheckbackuser_userrealname(String applycheckbackuser_userrealname) {
		this.applycheckbackuser_userrealname = applycheckbackuser_userrealname;
	}

	public String getApplycheckbackuser_usersex() {
		return applycheckbackuser_usersex;
	}

	public void setApplycheckbackuser_usersex(String applycheckbackuser_usersex) {
		this.applycheckbackuser_usersex = applycheckbackuser_usersex;
	}

	public String getApplycheckbackuser_userphone() {
		return applycheckbackuser_userphone;
	}

	public void setApplycheckbackuser_userphone(String applycheckbackuser_userphone) {
		this.applycheckbackuser_userphone = applycheckbackuser_userphone;
	}

	public String getApplycheckbackuser_usercodeid() {
		return applycheckbackuser_usercodeid;
	}

	public void setApplycheckbackuser_usercodeid(String applycheckbackuser_usercodeid) {
		this.applycheckbackuser_usercodeid = applycheckbackuser_usercodeid;
	}

	public String getApplycheckbackuser_useremail() {
		return applycheckbackuser_useremail;
	}

	public void setApplycheckbackuser_useremail(String applycheckbackuser_useremail) {
		this.applycheckbackuser_useremail = applycheckbackuser_useremail;
	}

	public String getApplycheckbackuser_userrole() {
		return applycheckbackuser_userrole;
	}

	public void setApplycheckbackuser_userrole(String applycheckbackuser_userrole) {
		this.applycheckbackuser_userrole = applycheckbackuser_userrole;
	}

	public String getApplycheckbackuser_userstate() {
		return applycheckbackuser_userstate;
	}

	public void setApplycheckbackuser_userstate(String applycheckbackuser_userstate) {
		this.applycheckbackuser_userstate = applycheckbackuser_userstate;
	}

	public Double getApplycheckbackuser_usercredit() {
		return applycheckbackuser_usercredit;
	}

	public void setApplycheckbackuser_usercredit(Double applycheckbackuser_usercredit) {
		this.applycheckbackuser_usercredit = applycheckbackuser_usercredit;
	}

	public Double getApplycheckbackuser_usermoney() {
		return applycheckbackuser_usermoney;
	}

	public void setApplycheckbackuser_usermoney(Double applycheckbackuser_usermoney) {
		this.applycheckbackuser_usermoney = applycheckbackuser_usermoney;
	}

	public String getApplycheckbackuser_useropenid() {
		return applycheckbackuser_useropenid;
	}

	public void setApplycheckbackuser_useropenid(String applycheckbackuser_useropenid) {
		this.applycheckbackuser_useropenid = applycheckbackuser_useropenid;
	}

	public String getApplycheckbackuser_userposition() {
		return applycheckbackuser_userposition;
	}

	public void setApplycheckbackuser_userposition(String applycheckbackuser_userposition) {
		this.applycheckbackuser_userposition = applycheckbackuser_userposition;
	}

	public Double getApplycheckbackuser_userlatitude() {
		return applycheckbackuser_userlatitude;
	}

	public void setApplycheckbackuser_userlatitude(Double applycheckbackuser_userlatitude) {
		this.applycheckbackuser_userlatitude = applycheckbackuser_userlatitude;
	}

	public Double getApplycheckbackuser_userlongitude() {
		return applycheckbackuser_userlongitude;
	}

	public void setApplycheckbackuser_userlongitude(Double applycheckbackuser_userlongitude) {
		this.applycheckbackuser_userlongitude = applycheckbackuser_userlongitude;
	}

	public String getApplycheckbackuser_userremark() {
		return applycheckbackuser_userremark;
	}

	public void setApplycheckbackuser_userremark(String applycheckbackuser_userremark) {
		this.applycheckbackuser_userremark = applycheckbackuser_userremark;
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

	public String getSelectapplycheckstarttime() {
		return selectapplycheckstarttime;
	}

	public void setSelectapplycheckstarttime(String selectapplycheckstarttime) {
		this.selectapplycheckstarttime = selectapplycheckstarttime;
	}

	public String getSelectapplycheckendtime() {
		return selectapplycheckendtime;
	}

	public void setSelectapplycheckendtime(String selectapplycheckendtime) {
		this.selectapplycheckendtime = selectapplycheckendtime;
	}

	public String getSelectapplycheckbackstarttime() {
		return selectapplycheckbackstarttime;
	}

	public void setSelectapplycheckbackstarttime(String selectapplycheckbackstarttime) {
		this.selectapplycheckbackstarttime = selectapplycheckbackstarttime;
	}

	public String getSelectapplycheckbackendtime() {
		return selectapplycheckbackendtime;
	}

	public void setSelectapplycheckbackendtime(String selectapplycheckbackendtime) {
		this.selectapplycheckbackendtime = selectapplycheckbackendtime;
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

	public Double getSurplusmoney() {
		return surplusmoney;
	}

	public void setSurplusmoney(Double surplusmoney) {
		this.surplusmoney = surplusmoney;
	}

    public Double getCustomermoney() {
        return customermoney;
    }

    public void setCustomermoney(Double customermoney) {
        this.customermoney = customermoney;
    }

    public Double getServicesurplusmoney() {
        return servicesurplusmoney;
    }

    public void setServicesurplusmoney(Double servicesurplusmoney) {
        this.servicesurplusmoney = servicesurplusmoney;
    }

	public String getApplycheckbhgstate() {
		return applycheckbhgstate;
	}

	public void setApplycheckbhgstate(String applycheckbhgstate) {
		this.applycheckbhgstate = applycheckbhgstate;
	}

	public String getOrderserviceuserid() {
		return orderserviceuserid;
	}

	public void setOrderserviceuserid(String orderserviceuserid) {
		this.orderserviceuserid = orderserviceuserid;
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
