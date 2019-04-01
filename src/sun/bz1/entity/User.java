package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/03
 */

public class User {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 用户ID(YHBG+UUID)
	 */
	private String userid;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 用户密码
	 */
	private String userpassword;

	/**
	 * 用户密码盐
	 */
	private String usersalt;

	/**
	 * 用户加密密码
	 */
	private String userencryptpassword;

	/**
	 * 用户真实姓名
	 */
	private String userrealname;

	/**
	 * 用户性别(Y:男/X:女)
	 */
	private String usersex;

	/**
	 * 用户手机号码
	 */
	private String userphone;

	/**
	 * 用户身份证号码
	 */
	private String usercodeid;

	/**
	 * 用户邮箱
	 */
	private String useremail;

	/**
	 * 用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String userrole;

	/**
	 * 用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String userstate;

	/**
	 * 用户信用值
	 */
	private Double usercredit;
	
	/**
	 * 用户余额
	 */
	private Double usermoney;

	/**
	 * 用户短信验证码
	 */
	private String usersms;

	/**
	 * 用户短信验证码创建时间
	 */
	private Date usersmstime;

	/**
	 * 用户微信OpenId
	 */
	private String useropenid;
	
	/**
	 * 用户位置信息(当前位置信息)
	 */
	private String userposition;
	
	/**
	 * 用户位置纬度
	 */
	private Double userlatitude;
	
	/**
	 * 用户位置经度
	 */
	private Double userlongitude;

	/**
	 * 用户创建人ID(YHBG+UUID)
	 */
	private String usercreateuserid;

	/**
	 * 用户创建时间
	 */
	private Date usercreatetime;
	
	/**
	 * 用户更新人ID(YHBG+UUID)
	 */
	private String userupdateuserid;

	/**
	 * 用户更新时间
	 */
	private Date userupdatetime;

	/**
	 * 用户备注
	 */
	private String userremark;
	
	/**
	 * 用户邀请人ID
	 */
	private String userinvitationuserid;

	/**
	 * 排序
	 */
	private Integer usersort;
	/**
	 * 银行卡号
	 */
	private String useraccount;
	/**
	 * 银行开户行地址
	 */
	private String useraccountaddress;

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
	 * 根据idlist删除信息时使用
	 */
	private List<Integer> idlist;
	
	/**
  	 * 根据uuidlist删除信息时使用
  	 */
  	private List<String> uuidlist;
  	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
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
		this.usersalt = usersalt == null ? null : usersalt.trim();
	}

	public String getUserencryptpassword() {
		return userencryptpassword;
	}

	public void setUserencryptpassword(String userencryptpassword) {
		this.userencryptpassword = userencryptpassword == null ? null : userencryptpassword.trim();
	}

	public String getUserrealname() {
		return userrealname;
	}

	public void setUserrealname(String userrealname) {
		this.userrealname = userrealname == null ? null : userrealname.trim();
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex == null ? null : usersex.trim();
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone == null ? null : userphone.trim();
	}

	public String getUsercodeid() {
		return usercodeid;
	}

	public void setUsercodeid(String usercodeid) {
		this.usercodeid = usercodeid == null ? null : usercodeid.trim();
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail == null ? null : useremail.trim();
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole == null ? null : userrole.trim();
	}

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate == null ? null : userstate.trim();
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
		this.usersms = usersms == null ? null : usersms.trim();
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
		this.useropenid = useropenid == null ? null : useropenid.trim();
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
		this.usercreateuserid = usercreateuserid == null ? null : usercreateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getUsercreatetime() {
		return usercreatetime;
	}

	public void setUsercreatetime(Date usercreatetime) {
		this.usercreatetime = usercreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public String getUserupdateuserid() {
		return userupdateuserid;
	}

	public void setUserupdateuserid(String userupdateuserid) {
		this.userupdateuserid = userupdateuserid;
	}

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
		this.userremark = userremark == null ? null : userremark.trim();
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

	public List<Integer> getIdlist() {
		return idlist;
	}

	public void setIdlist(List<Integer> idlist) {
		this.idlist = idlist;
	}

	public List<String> getUuidlist() {
		return uuidlist;
	}

	public void setUuidlist(List<String> uuidlist) {
		this.uuidlist = uuidlist;
	}

	public String getUserinvitationuserid() {
		return userinvitationuserid;
	}

	public void setUserinvitationuserid(String userinvitationuserid) {
		this.userinvitationuserid = userinvitationuserid;
	}

	public Integer getUsersort() {
		return usersort;
	}

	public void setUsersort(Integer usersort) {
		this.usersort = usersort;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getUseraccountaddress() {
		return useraccountaddress;
	}

	public void setUseraccountaddress(String useraccountaddress) {
		this.useraccountaddress = useraccountaddress;
	}
}