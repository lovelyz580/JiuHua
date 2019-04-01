package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户_提现表、提现人员(用户表)、提现审核人员(用户表)
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/10
 */

public class UserExtractMoneyAndUser {

	/**
	 * 用户_提现表-序号(自增主键)
	 */
	private Integer id;

	/**
	 * 用户提现ID(YHTX+UUID)
	 */
	private String userextractmoneyid;

	/**
	 * 提现用户ID(YHBG+UUID)
	 */
	private String userextractmoneyuserid;

	/**
	 * 用户_提现表-提现金额
	 */
	private Double userextractmoney;

	/**
	 * 用户_提现表-提现姓名
	 */
	private String userextractmoneyname;

	/**
	 * 用户_提现表-提现银行卡号
	 */
	private String userextractmoneybankcard;

	/**
	 * 用户_提现表-提现开户行
	 */
	private String userextractmoneybankaddress;

	/**
	 * 用户_提现表-提现状态(Y:有效/N:无效)
	 */
	private String userextractmoneystate;

	/**
	 * 用户_提现表-提现时间
	 */
	private Date userextractmoneytime;

	/**
	 * 用户_提现表-提现审核人员ID(YHBG+UUID)
	 */
	private String userextractmoneycheckuserid;

	/**
	 * 用户_提现表-提现审核通过状态(Y:通过/N:未通过)
	 */
	private String userextractmoneycheckstate;

	/**
	 * 用户_提现表-提现审核通过未通过原因
	 */
	private String userextractmoneycheckcontent;

	/**
	 * 用户_提现表-提现审核时间
	 */
	private Date userextractmoneychecktime;

	/**
	 * 用户_提现表-提现备注
	 */
	private String userextractmoneyremark;

	/**
	 * 提现人员(用户表)-用户名
	 */
	private String userextract_username;

	/**
	 * 提现人员(用户表)-用户真实姓名
	 */
	private String userextract_userrealname;

	/**
	 * 提现人员(用户表)-用户性别(Y:男/X:女)
	 */
	private String userextract_usersex;

	/**
	 * 提现人员(用户表)-用户手机号码
	 */
	private String userextract_userphone;

	/**
	 * 提现人员(用户表)-用户身份证号码
	 */
	private String userextract_usercodeid;

	/**
	 * 提现人员(用户表)-用户邮箱
	 */
	private String userextract_useremail;

	/**
	 * 提现人员(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String userextract_userrole;

	/**
	 * 提现人员(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String userextract_userstate;

	/**
	 * 提现人员(用户表)-用户信用值
	 */
	private Double userextract_usercredit;
	
	/**
	 * 提现人员(用户表)-用户余额
	 */
	private Double userextract_usermoney;

	/**
	 * 提现人员(用户表)-用户微信OpenId
	 */
	private String userextract_useropenid;
	
	/**
	 * 提现人员(用户表)-用户位置信息(当前位置信息)
	 */
	private String userextract_userposition;
	
	/**
	 * 提现人员(用户表)-用户位置纬度
	 */
	private Double userextract_userlatitude;
	
	/**
	 * 提现人员(用户表)-用户位置经度
	 */
	private Double userextract_userlongitude;

	/**
	 * 提现人员(用户表)-用户备注
	 */
	private String userextract_userremark;

	/**
	 * 提现审核人员(用户表)-用户名
	 */
	private String userextractmoneycheck_username;

	/**
	 * 提现审核人员(用户表)-用户真实姓名
	 */
	private String userextractmoneycheck_userrealname;

	/**
	 * 提现审核人员(用户表)-用户性别(Y:男/X:女)
	 */
	private String userextractmoneycheck_usersex;

	/**
	 * 提现审核人员(用户表)-用户手机号码
	 */
	private String userextractmoneycheck_userphone;

	/**
	 * 提现审核人员(用户表)-用户身份证号码
	 */
	private String userextractmoneycheck_usercodeid;

	/**
	 * 提现审核人员(用户表)-用户邮箱
	 */
	private String userextractmoneycheck_useremail;

	/**
	 * 提现审核人员(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String userextractmoneycheck_userrole;

	/**
	 * 提现审核人员(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String userextractmoneycheck_userstate;

	/**
	 * 提现审核人员(用户表)-用户信用值
	 */
	private Double userextractmoneycheck_usercredit;
	
	/**
	 * 提现审核人员(用户表)-用户余额
	 */
	private Double userextractmoneycheck_usermoney;

	/**
	 * 提现审核人员(用户表)-用户微信OpenId
	 */
	private String userextractmoneycheck_useropenid;
	
	/**
	 * 提现审核人员(用户表)-用户位置信息(当前位置信息)
	 */
	private String userextractmoneycheck_userposition;
	
	/**
	 * 提现审核人员(用户表)-用户位置纬度
	 */
	private Double userextractmoneycheck_userlatitude;
	
	/**
	 * 提现审核人员(用户表)-用户位置经度
	 */
	private Double userextractmoneycheck_userlongitude;

	/**
	 * 提现审核人员(用户表)-用户备注
	 */
	private String userextractmoneycheck_userremark;

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
	 * 根据维用户_提现表的提现时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectuserextractmoneystarttime;

	/**
	 * 根据维用户_提现表的提现时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectuserextractmoneyendtime;

	/**
	 * 根据维用户_提现表的提现审核时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectuserextractmoneycheckstarttime;

	/**
	 * 根据维用户_提现表的提现审核时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectuserextractmoneycheckendtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserextractmoneyid() {
		return userextractmoneyid;
	}

	public void setUserextractmoneyid(String userextractmoneyid) {
		this.userextractmoneyid = userextractmoneyid;
	}

	public String getUserextractmoneyuserid() {
		return userextractmoneyuserid;
	}

	public void setUserextractmoneyuserid(String userextractmoneyuserid) {
		this.userextractmoneyuserid = userextractmoneyuserid;
	}

	public Double getUserextractmoney() {
		return userextractmoney;
	}

	public void setUserextractmoney(Double userextractmoney) {
		this.userextractmoney = userextractmoney;
	}

	public String getUserextractmoneyname() {
		return userextractmoneyname;
	}

	public void setUserextractmoneyname(String userextractmoneyname) {
		this.userextractmoneyname = userextractmoneyname;
	}

	public String getUserextractmoneybankcard() {
		return userextractmoneybankcard;
	}

	public void setUserextractmoneybankcard(String userextractmoneybankcard) {
		this.userextractmoneybankcard = userextractmoneybankcard;
	}

	public String getUserextractmoneybankaddress() {
		return userextractmoneybankaddress;
	}

	public void setUserextractmoneybankaddress(String userextractmoneybankaddress) {
		this.userextractmoneybankaddress = userextractmoneybankaddress;
	}

	public String getUserextractmoneystate() {
		return userextractmoneystate;
	}

	public void setUserextractmoneystate(String userextractmoneystate) {
		this.userextractmoneystate = userextractmoneystate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getUserextractmoneytime() {
		return userextractmoneytime;
	}

	public void setUserextractmoneytime(Date userextractmoneytime) {
		this.userextractmoneytime = userextractmoneytime;
	}

	public String getUserextractmoneycheckuserid() {
		return userextractmoneycheckuserid;
	}

	public void setUserextractmoneycheckuserid(String userextractmoneycheckuserid) {
		this.userextractmoneycheckuserid = userextractmoneycheckuserid;
	}

	public String getUserextractmoneycheckstate() {
		return userextractmoneycheckstate;
	}

	public void setUserextractmoneycheckstate(String userextractmoneycheckstate) {
		this.userextractmoneycheckstate = userextractmoneycheckstate;
	}

	public String getUserextractmoneycheckcontent() {
		return userextractmoneycheckcontent;
	}

	public void setUserextractmoneycheckcontent(String userextractmoneycheckcontent) {
		this.userextractmoneycheckcontent = userextractmoneycheckcontent;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getUserextractmoneychecktime() {
		return userextractmoneychecktime;
	}

	public void setUserextractmoneychecktime(Date userextractmoneychecktime) {
		this.userextractmoneychecktime = userextractmoneychecktime;
	}

	public String getUserextractmoneyremark() {
		return userextractmoneyremark;
	}

	public void setUserextractmoneyremark(String userextractmoneyremark) {
		this.userextractmoneyremark = userextractmoneyremark;
	}

	public String getUserextract_username() {
		return userextract_username;
	}

	public void setUserextract_username(String userextract_username) {
		this.userextract_username = userextract_username;
	}

	public String getUserextract_userrealname() {
		return userextract_userrealname;
	}

	public void setUserextract_userrealname(String userextract_userrealname) {
		this.userextract_userrealname = userextract_userrealname;
	}

	public String getUserextract_usersex() {
		return userextract_usersex;
	}

	public void setUserextract_usersex(String userextract_usersex) {
		this.userextract_usersex = userextract_usersex;
	}

	public String getUserextract_userphone() {
		return userextract_userphone;
	}

	public void setUserextract_userphone(String userextract_userphone) {
		this.userextract_userphone = userextract_userphone;
	}

	public String getUserextract_usercodeid() {
		return userextract_usercodeid;
	}

	public void setUserextract_usercodeid(String userextract_usercodeid) {
		this.userextract_usercodeid = userextract_usercodeid;
	}

	public String getUserextract_useremail() {
		return userextract_useremail;
	}

	public void setUserextract_useremail(String userextract_useremail) {
		this.userextract_useremail = userextract_useremail;
	}

	public String getUserextract_userrole() {
		return userextract_userrole;
	}

	public void setUserextract_userrole(String userextract_userrole) {
		this.userextract_userrole = userextract_userrole;
	}

	public String getUserextract_userstate() {
		return userextract_userstate;
	}

	public void setUserextract_userstate(String userextract_userstate) {
		this.userextract_userstate = userextract_userstate;
	}

	public Double getUserextract_usercredit() {
		return userextract_usercredit;
	}

	public void setUserextract_usercredit(Double userextract_usercredit) {
		this.userextract_usercredit = userextract_usercredit;
	}

	public Double getUserextract_usermoney() {
		return userextract_usermoney;
	}

	public void setUserextract_usermoney(Double userextract_usermoney) {
		this.userextract_usermoney = userextract_usermoney;
	}

	public String getUserextract_useropenid() {
		return userextract_useropenid;
	}

	public void setUserextract_useropenid(String userextract_useropenid) {
		this.userextract_useropenid = userextract_useropenid;
	}

	public String getUserextract_userposition() {
		return userextract_userposition;
	}

	public void setUserextract_userposition(String userextract_userposition) {
		this.userextract_userposition = userextract_userposition;
	}

	public Double getUserextract_userlatitude() {
		return userextract_userlatitude;
	}

	public void setUserextract_userlatitude(Double userextract_userlatitude) {
		this.userextract_userlatitude = userextract_userlatitude;
	}

	public Double getUserextract_userlongitude() {
		return userextract_userlongitude;
	}

	public void setUserextract_userlongitude(Double userextract_userlongitude) {
		this.userextract_userlongitude = userextract_userlongitude;
	}

	public String getUserextract_userremark() {
		return userextract_userremark;
	}

	public void setUserextract_userremark(String userextract_userremark) {
		this.userextract_userremark = userextract_userremark;
	}

	public String getUserextractmoneycheck_username() {
		return userextractmoneycheck_username;
	}

	public void setUserextractmoneycheck_username(String userextractmoneycheck_username) {
		this.userextractmoneycheck_username = userextractmoneycheck_username;
	}

	public String getUserextractmoneycheck_userrealname() {
		return userextractmoneycheck_userrealname;
	}

	public void setUserextractmoneycheck_userrealname(String userextractmoneycheck_userrealname) {
		this.userextractmoneycheck_userrealname = userextractmoneycheck_userrealname;
	}

	public String getUserextractmoneycheck_usersex() {
		return userextractmoneycheck_usersex;
	}

	public void setUserextractmoneycheck_usersex(String userextractmoneycheck_usersex) {
		this.userextractmoneycheck_usersex = userextractmoneycheck_usersex;
	}

	public String getUserextractmoneycheck_userphone() {
		return userextractmoneycheck_userphone;
	}

	public void setUserextractmoneycheck_userphone(String userextractmoneycheck_userphone) {
		this.userextractmoneycheck_userphone = userextractmoneycheck_userphone;
	}

	public String getUserextractmoneycheck_usercodeid() {
		return userextractmoneycheck_usercodeid;
	}

	public void setUserextractmoneycheck_usercodeid(String userextractmoneycheck_usercodeid) {
		this.userextractmoneycheck_usercodeid = userextractmoneycheck_usercodeid;
	}

	public String getUserextractmoneycheck_useremail() {
		return userextractmoneycheck_useremail;
	}

	public void setUserextractmoneycheck_useremail(String userextractmoneycheck_useremail) {
		this.userextractmoneycheck_useremail = userextractmoneycheck_useremail;
	}

	public String getUserextractmoneycheck_userrole() {
		return userextractmoneycheck_userrole;
	}

	public void setUserextractmoneycheck_userrole(String userextractmoneycheck_userrole) {
		this.userextractmoneycheck_userrole = userextractmoneycheck_userrole;
	}

	public String getUserextractmoneycheck_userstate() {
		return userextractmoneycheck_userstate;
	}

	public void setUserextractmoneycheck_userstate(String userextractmoneycheck_userstate) {
		this.userextractmoneycheck_userstate = userextractmoneycheck_userstate;
	}

	public Double getUserextractmoneycheck_usercredit() {
		return userextractmoneycheck_usercredit;
	}

	public void setUserextractmoneycheck_usercredit(Double userextractmoneycheck_usercredit) {
		this.userextractmoneycheck_usercredit = userextractmoneycheck_usercredit;
	}

	public Double getUserextractmoneycheck_usermoney() {
		return userextractmoneycheck_usermoney;
	}

	public void setUserextractmoneycheck_usermoney(Double userextractmoneycheck_usermoney) {
		this.userextractmoneycheck_usermoney = userextractmoneycheck_usermoney;
	}

	public String getUserextractmoneycheck_useropenid() {
		return userextractmoneycheck_useropenid;
	}

	public void setUserextractmoneycheck_useropenid(String userextractmoneycheck_useropenid) {
		this.userextractmoneycheck_useropenid = userextractmoneycheck_useropenid;
	}

	public String getUserextractmoneycheck_userposition() {
		return userextractmoneycheck_userposition;
	}

	public void setUserextractmoneycheck_userposition(String userextractmoneycheck_userposition) {
		this.userextractmoneycheck_userposition = userextractmoneycheck_userposition;
	}

	public Double getUserextractmoneycheck_userlatitude() {
		return userextractmoneycheck_userlatitude;
	}

	public void setUserextractmoneycheck_userlatitude(Double userextractmoneycheck_userlatitude) {
		this.userextractmoneycheck_userlatitude = userextractmoneycheck_userlatitude;
	}

	public Double getUserextractmoneycheck_userlongitude() {
		return userextractmoneycheck_userlongitude;
	}

	public void setUserextractmoneycheck_userlongitude(Double userextractmoneycheck_userlongitude) {
		this.userextractmoneycheck_userlongitude = userextractmoneycheck_userlongitude;
	}

	public String getUserextractmoneycheck_userremark() {
		return userextractmoneycheck_userremark;
	}

	public void setUserextractmoneycheck_userremark(String userextractmoneycheck_userremark) {
		this.userextractmoneycheck_userremark = userextractmoneycheck_userremark;
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

	public String getSelectuserextractmoneystarttime() {
		return selectuserextractmoneystarttime;
	}

	public void setSelectuserextractmoneystarttime(String selectuserextractmoneystarttime) {
		this.selectuserextractmoneystarttime = selectuserextractmoneystarttime;
	}

	public String getSelectuserextractmoneyendtime() {
		return selectuserextractmoneyendtime;
	}

	public void setSelectuserextractmoneyendtime(String selectuserextractmoneyendtime) {
		this.selectuserextractmoneyendtime = selectuserextractmoneyendtime;
	}

	public String getSelectuserextractmoneycheckstarttime() {
		return selectuserextractmoneycheckstarttime;
	}

	public void setSelectuserextractmoneycheckstarttime(String selectuserextractmoneycheckstarttime) {
		this.selectuserextractmoneycheckstarttime = selectuserextractmoneycheckstarttime;
	}

	public String getSelectuserextractmoneycheckendtime() {
		return selectuserextractmoneycheckendtime;
	}

	public void setSelectuserextractmoneycheckendtime(String selectuserextractmoneycheckendtime) {
		this.selectuserextractmoneycheckendtime = selectuserextractmoneycheckendtime;
	}
	
}
