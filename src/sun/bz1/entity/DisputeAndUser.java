package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 纠纷表、客户ID(用户表)、维修人员ID(用户表)、纠纷回复人员(用户表)
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class DisputeAndUser {
	
	
	@Override
	public String toString() {
		return "DisputeAndUser [id=" + id + ", disputeid=" + disputeid
				+ ", customeruserid=" + customeruserid + ", serviceuserid="
				+ serviceuserid + ", orderid=" + orderid + ", disputetitle="
				+ disputetitle + ", disputestate=" + disputestate
				+ ", dispute=" + dispute + ", disputecreatetime="
				+ disputecreatetime + ", disputereplycontent="
				+ disputereplycontent + ", disputereplyuserid="
				+ disputereplyuserid + ", disputereplytime=" + disputereplytime
				+ ", disputeremark=" + disputeremark
				+ ", customeruser_username=" + customeruser_username
				+ ", customeruser_userrealname=" + customeruser_userrealname
				+ ", customeruser_usersex=" + customeruser_usersex
				+ ", customeruser_userphone=" + customeruser_userphone
				+ ", customeruser_usercodeid=" + customeruser_usercodeid
				+ ", customeruser_useremail=" + customeruser_useremail
				+ ", customeruser_userrole=" + customeruser_userrole
				+ ", customeruser_userstate=" + customeruser_userstate
				+ ", customeruser_usercredit=" + customeruser_usercredit
				+ ", customeruser_usermoney=" + customeruser_usermoney
				+ ", customeruser_useropenid=" + customeruser_useropenid
				+ ", customeruser_userposition=" + customeruser_userposition
				+ ", customeruser_userlatitude=" + customeruser_userlatitude
				+ ", customeruser_userlongitude=" + customeruser_userlongitude
				+ ", customeruser_userremark=" + customeruser_userremark
				+ ", serviceuser_username=" + serviceuser_username
				+ ", 哈哈哈哈哈哈哈哈哈serviceuser_userrealname=" + serviceuser_userrealname
				+ ", serviceuser_usersex=" + serviceuser_usersex
				+ ", serviceuser_userphone=" + serviceuser_userphone
				+ ", serviceuser_usercodeid=" + serviceuser_usercodeid
				+ ", serviceuser_useremail=" + serviceuser_useremail
				+ ", serviceuser_userrole=" + serviceuser_userrole
				+ ", serviceuser_userstate=" + serviceuser_userstate
				+ ", serviceuser_usercredit=" + serviceuser_usercredit
				+ ", serviceuser_usermoney=" + serviceuser_usermoney
				+ ", serviceuser_useropenid=" + serviceuser_useropenid
				+ ", serviceuser_userposition=" + serviceuser_userposition
				+ ", serviceuser_userlatitude=" + serviceuser_userlatitude
				+ ", serviceuser_userlongitude=" + serviceuser_userlongitude
				+ ", serviceuser_userremark=" + serviceuser_userremark
				+ ", disputereplyuser_username=" + disputereplyuser_username
				+ ", disputereplyuser_userrealname="
				+ disputereplyuser_userrealname + ", disputereplyuser_usersex="
				+ disputereplyuser_usersex + ", disputereplyuser_userphone="
				+ disputereplyuser_userphone + ", disputereplyuser_usercodeid="
				+ disputereplyuser_usercodeid + ", disputereplyuser_useremail="
				+ disputereplyuser_useremail + ", disputereplyuser_userrole="
				+ disputereplyuser_userrole + ", disputereplyuser_userstate="
				+ disputereplyuser_userstate + ", disputereplyuser_usercredit="
				+ disputereplyuser_usercredit
				+ ", disputereplyuser_useropenid="
				+ disputereplyuser_useropenid
				+ ", disputereplyuser_userposition="
				+ disputereplyuser_userposition
				+ ", disputereplyuser_userlatitude="
				+ disputereplyuser_userlatitude
				+ ", disputereplyuser_userlongitude="
				+ disputereplyuser_userlongitude
				+ ", disputereplyuser_userremark="
				+ disputereplyuser_userremark + ", version=" + version
				+ ", pagenumber=" + pagenumber + ", pagesize=" + pagesize
				+ ", selectdisputecreatestarttime="
				+ selectdisputecreatestarttime
				+ ", selectdisputecreateendtime=" + selectdisputecreateendtime
				+ ", selectdisputereplystarttime="
				+ selectdisputereplystarttime + ", selectdisputereplyendtime="
				+ selectdisputereplyendtime + "]";
	}

	/**
	 * 纠纷表-序号(自增主键)
	 */
	private Integer id;

	/**
	 * 纠纷表-纠纷ID(JFBG+UUID)
	 */
	private String disputeid;

	/**
	 * 纠纷表-客户ID(YHBG+UUID)
	 */
	private String customeruserid;

	/**
	 * 纠纷表-维修人员ID(YHBG+UUID)
	 */
	private String serviceuserid;

	/**
	 * 纠纷表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 纠纷表-纠纷标题
	 */
	private String disputetitle;

	/**
	 * 纠纷表-纠纷状态(Y:已处理/N:未处理)
	 */
	private String disputestate;

	/**
	 * 纠纷表-纠纷内容
	 */
	private String dispute;

	/**
	 * 纠纷表-纠纷创建时间
	 */
	private Date disputecreatetime;

	/**
	 * 纠纷表-纠纷回复内容
	 */
	private String disputereplycontent;

	/**
	 * 纠纷表-纠纷回复人员(YHBG+UUID)
	 */
	private String disputereplyuserid;

	/**
	 * 纠纷表-纠纷回复时间
	 */
	private Date disputereplytime;

	/**
	 * 纠纷表-纠纷备注
	 */
	private String disputeremark;
	
	/**
	 * 客户ID(用户表)-用户名
	 */
	private String customeruser_username;

	/**
	 * 客户ID(用户表)-用户真实姓名
	 */
	private String customeruser_userrealname;

	/**
	 * 客户ID(用户表)-用户性别(Y:男/X:女)
	 */
	private String customeruser_usersex;

	/**
	 * 客户ID(用户表)-用户手机号码
	 */
	private String customeruser_userphone;

	/**
	 * 客户ID(用户表)-用户身份证号码
	 */
	private String customeruser_usercodeid;

	/**
	 * 客户ID(用户表)-用户邮箱
	 */
	private String customeruser_useremail;

	/**
	 * 客户ID(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String customeruser_userrole;

	/**
	 * 客户ID(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String customeruser_userstate;

	/**
	 * 客户ID(用户表)-用户信用值
	 */
	private Double customeruser_usercredit;
	
	/**
	 * 客户ID(用户表)-用户余额
	 */
	private Double customeruser_usermoney;

	/**
	 * 客户ID(用户表)-用户微信OpenId
	 */
	private String customeruser_useropenid;
	
	/**
	 * 客户ID(用户表)-用户位置信息(当前位置信息)
	 */
	private String customeruser_userposition;
	
	/**
	 * 客户ID(用户表)-用户位置纬度
	 */
	private Double customeruser_userlatitude;
	
	/**
	 * 客户ID(用户表)-用户位置经度
	 */
	private Double customeruser_userlongitude;

	/**
	 * 客户ID(用户表)-用户备注
	 */
	private String customeruser_userremark;
	
	/**
	 * 维修人员ID(用户表)-用户名
	 */
	private String serviceuser_username;

	/**
	 * 维修人员ID(用户表)-用户真实姓名
	 */
	private String serviceuser_userrealname;

	/**
	 * 维修人员ID(用户表)-用户性别(Y:男/X:女)
	 */
	private String serviceuser_usersex;

	/**
	 * 维修人员ID(用户表)-用户手机号码
	 */
	private String serviceuser_userphone;

	/**
	 * 维修人员ID(用户表)-用户身份证号码
	 */
	private String serviceuser_usercodeid;

	/**
	 * 维修人员ID(用户表)-用户邮箱
	 */
	private String serviceuser_useremail;

	/**
	 * 维修人员ID(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String serviceuser_userrole;

	/**
	 * 维修人员ID(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String serviceuser_userstate;

	/**
	 * 维修人员ID(用户表)-用户信用值
	 */
	private Double serviceuser_usercredit;
	
	/**
	 * 维修人员ID(用户表)-用户余额
	 */
	private Double serviceuser_usermoney;

	/**
	 * 维修人员ID(用户表)-用户微信OpenId
	 */
	private String serviceuser_useropenid;
	
	/**
	 * 维修人员ID(用户表)-用户位置信息(当前位置信息)
	 */
	private String serviceuser_userposition;
	
	/**
	 * 维修人员ID(用户表)-用户位置纬度
	 */
	private Double serviceuser_userlatitude;
	
	/**
	 * 维修人员ID(用户表)-用户位置经度
	 */
	private Double serviceuser_userlongitude;

	/**
	 * 维修人员ID(用户表)-用户备注
	 */
	private String serviceuser_userremark;
	
	/**
	 * 维修人员ID(用户表)-用户名
	 */
	private String disputereplyuser_username;

	/**
	 * 维修人员ID(用户表)-用户真实姓名
	 */
	private String disputereplyuser_userrealname;

	/**
	 * 维修人员ID(用户表)-用户性别(Y:男/X:女)
	 */
	private String disputereplyuser_usersex;

	/**
	 * 维修人员ID(用户表)-用户手机号码
	 */
	private String disputereplyuser_userphone;

	/**
	 * 维修人员ID(用户表)-用户身份证号码
	 */
	private String disputereplyuser_usercodeid;

	/**
	 * 维修人员ID(用户表)-用户邮箱
	 */
	private String disputereplyuser_useremail;

	/**
	 * 维修人员ID(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String disputereplyuser_userrole;

	/**
	 * 维修人员ID(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String disputereplyuser_userstate;

	/**
	 * 维修人员ID(用户表)-用户信用值
	 */
	private Double disputereplyuser_usercredit;

	/**
	 * 维修人员ID(用户表)-用户余额
	 */
	private Double disputereplyuser_usermoney;

	/**
	 * 维修人员ID(用户表)-用户微信OpenId
	 */
	private String disputereplyuser_useropenid;
	
	/**
	 * 维修人员ID(用户表)-用户位置信息(当前位置信息)
	 */
	private String disputereplyuser_userposition;
	
	/**
	 * 维修人员ID(用户表)-用户位置纬度
	 */
	private Double disputereplyuser_userlatitude;
	
	/**
	 * 维修人员ID(用户表)-用户位置经度
	 */
	private Double disputereplyuser_userlongitude;

	/**
	 * 维修人员ID(用户表)-用户备注
	 */
	private String disputereplyuser_userremark;
	
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
	 * 根据纠纷表的纠纷创建时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectdisputecreatestarttime;

	/**
	 * 根据纠纷表的纠纷创建时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectdisputecreateendtime;
	
	/**
	 * 根据纠纷表的纠纷回复时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectdisputereplystarttime;

	/**
	 * 根据纠纷表的纠纷回复时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectdisputereplyendtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisputeid() {
		return disputeid;
	}

	public void setDisputeid(String disputeid) {
		this.disputeid = disputeid;
	}

	public String getCustomeruserid() {
		return customeruserid;
	}

	public void setCustomeruserid(String customeruserid) {
		this.customeruserid = customeruserid;
	}

	public String getServiceuserid() {
		return serviceuserid;
	}

	public void setServiceuserid(String serviceuserid) {
		this.serviceuserid = serviceuserid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getDisputetitle() {
		return disputetitle;
	}

	public void setDisputetitle(String disputetitle) {
		this.disputetitle = disputetitle;
	}

	public String getDisputestate() {
		return disputestate;
	}

	public void setDisputestate(String disputestate) {
		this.disputestate = disputestate;
	}

	public String getDispute() {
		return dispute;
	}

	public void setDispute(String dispute) {
		this.dispute = dispute;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getDisputecreatetime() {
		return disputecreatetime;
	}

	public void setDisputecreatetime(Date disputecreatetime) {
		this.disputecreatetime = disputecreatetime;
	}

	public String getDisputereplycontent() {
		return disputereplycontent;
	}

	public void setDisputereplycontent(String disputereplycontent) {
		this.disputereplycontent = disputereplycontent;
	}

	public String getDisputereplyuserid() {
		return disputereplyuserid;
	}

	public void setDisputereplyuserid(String disputereplyuserid) {
		this.disputereplyuserid = disputereplyuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getDisputereplytime() {
		return disputereplytime;
	}

	public void setDisputereplytime(Date disputereplytime) {
		this.disputereplytime = disputereplytime;
	}

	public String getDisputeremark() {
		return disputeremark;
	}

	public void setDisputeremark(String disputeremark) {
		this.disputeremark = disputeremark;
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

	public String getDisputereplyuser_username() {
		return disputereplyuser_username;
	}

	public void setDisputereplyuser_username(String disputereplyuser_username) {
		this.disputereplyuser_username = disputereplyuser_username;
	}

	public String getDisputereplyuser_userrealname() {
		return disputereplyuser_userrealname;
	}

	public void setDisputereplyuser_userrealname(String disputereplyuser_userrealname) {
		this.disputereplyuser_userrealname = disputereplyuser_userrealname;
	}

	public String getDisputereplyuser_usersex() {
		return disputereplyuser_usersex;
	}

	public void setDisputereplyuser_usersex(String disputereplyuser_usersex) {
		this.disputereplyuser_usersex = disputereplyuser_usersex;
	}

	public String getDisputereplyuser_userphone() {
		return disputereplyuser_userphone;
	}

	public void setDisputereplyuser_userphone(String disputereplyuser_userphone) {
		this.disputereplyuser_userphone = disputereplyuser_userphone;
	}

	public String getDisputereplyuser_usercodeid() {
		return disputereplyuser_usercodeid;
	}

	public void setDisputereplyuser_usercodeid(String disputereplyuser_usercodeid) {
		this.disputereplyuser_usercodeid = disputereplyuser_usercodeid;
	}

	public String getDisputereplyuser_useremail() {
		return disputereplyuser_useremail;
	}

	public void setDisputereplyuser_useremail(String disputereplyuser_useremail) {
		this.disputereplyuser_useremail = disputereplyuser_useremail;
	}

	public String getDisputereplyuser_userrole() {
		return disputereplyuser_userrole;
	}

	public void setDisputereplyuser_userrole(String disputereplyuser_userrole) {
		this.disputereplyuser_userrole = disputereplyuser_userrole;
	}

	public String getDisputereplyuser_userstate() {
		return disputereplyuser_userstate;
	}

	public void setDisputereplyuser_userstate(String disputereplyuser_userstate) {
		this.disputereplyuser_userstate = disputereplyuser_userstate;
	}

	public Double getDisputereplyuser_usercredit() {
		return disputereplyuser_usercredit;
	}

	public void setDisputereplyuser_usercredit(Double disputereplyuser_usercredit) {
		this.disputereplyuser_usercredit = disputereplyuser_usercredit;
	}

	public Double getDisputereplyuser_usermoney() {
		return disputereplyuser_usermoney;
	}

	public void setDisputereplyuser_usermoney(Double disputereplyuser_usermoney) {
		this.disputereplyuser_usermoney = disputereplyuser_usermoney;
	}

	public String getDisputereplyuser_useropenid() {
		return disputereplyuser_useropenid;
	}

	public void setDisputereplyuser_useropenid(String disputereplyuser_useropenid) {
		this.disputereplyuser_useropenid = disputereplyuser_useropenid;
	}

	public String getDisputereplyuser_userposition() {
		return disputereplyuser_userposition;
	}

	public void setDisputereplyuser_userposition(String disputereplyuser_userposition) {
		this.disputereplyuser_userposition = disputereplyuser_userposition;
	}

	public Double getDisputereplyuser_userlatitude() {
		return disputereplyuser_userlatitude;
	}

	public void setDisputereplyuser_userlatitude(Double disputereplyuser_userlatitude) {
		this.disputereplyuser_userlatitude = disputereplyuser_userlatitude;
	}

	public Double getDisputereplyuser_userlongitude() {
		return disputereplyuser_userlongitude;
	}

	public void setDisputereplyuser_userlongitude(Double disputereplyuser_userlongitude) {
		this.disputereplyuser_userlongitude = disputereplyuser_userlongitude;
	}

	public String getDisputereplyuser_userremark() {
		return disputereplyuser_userremark;
	}

	public void setDisputereplyuser_userremark(String disputereplyuser_userremark) {
		this.disputereplyuser_userremark = disputereplyuser_userremark;
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

	public String getSelectdisputecreatestarttime() {
		return selectdisputecreatestarttime;
	}

	public void setSelectdisputecreatestarttime(String selectdisputecreatestarttime) {
		this.selectdisputecreatestarttime = selectdisputecreatestarttime;
	}

	public String getSelectdisputecreateendtime() {
		return selectdisputecreateendtime;
	}

	public void setSelectdisputecreateendtime(String selectdisputecreateendtime) {
		this.selectdisputecreateendtime = selectdisputecreateendtime;
	}

	public String getSelectdisputereplystarttime() {
		return selectdisputereplystarttime;
	}

	public void setSelectdisputereplystarttime(String selectdisputereplystarttime) {
		this.selectdisputereplystarttime = selectdisputereplystarttime;
	}

	public String getSelectdisputereplyendtime() {
		return selectdisputereplyendtime;
	}

	public void setSelectdisputereplyendtime(String selectdisputereplyendtime) {
		this.selectdisputereplyendtime = selectdisputereplyendtime;
	}
	
}
