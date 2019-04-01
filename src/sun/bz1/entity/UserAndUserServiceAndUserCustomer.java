package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户表、用户_维修工、安装队表、用户_客户表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/06
 */

public class UserAndUserServiceAndUserCustomer implements Comparable<UserAndUserServiceAndUserCustomer> {

	/**
	 * 用户表-序号(自增主键)
	 */
	private Integer id;

	/**
	 * 用户表-用户ID(YHBG+UUID)
	 */
	private String userid;

	/**
	 * 用户表-用户名
	 */
	private String username;

	/**
	 * 用户表-用户密码
	 */
	private String userpassword;

	/**
	 * 用户表-用户密码盐
	 */
	private String usersalt;

	/**
	 * 用户表-用户加密密码
	 */
	private String userencryptpassword;

	/**
	 * 用户表-用户真实姓名
	 */
	private String userrealname;

	/**
	 * 用户表-用户性别(Y:男/X:女)
	 */
	private String usersex;

	/**
	 * 用户表-用户手机号码
	 */
	private String userphone;

	/**
	 * 用户表-用户身份证号码
	 */
	private String usercodeid;

	/**
	 * 用户表-用户邮箱
	 */
	private String useremail;

	/**
	 * 用户表-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String userrole;

	/**
	 * 用户表-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String userstate;

	/**
	 * 用户表-用户信用值
	 */
	private Double usercredit;
	
	/**
	 * 用户表-用户余额
	 */
	private Double usermoney;

	/**
	 * 用户表-用户短信验证码
	 */
	private String usersms;

	/**
	 * 用户表-用户短信验证码创建时间
	 */
	private Date usersmstime;

	/**
	 * 用户表-用户微信OpenId
	 */
	private String useropenid;
	
	/**
	 * 用户表-用户位置信息(当前位置信息)
	 */
	private String userposition;
	
	/**
	 * 用户表-用户位置纬度
	 */
	private Double userlatitude;
	
	/**
	 * 用户表-用户位置经度
	 */
	private Double userlongitude;

	/**
	 * 用户表-用户创建人ID(YHBG+UUID)
	 */
	private String usercreateuserid;

	/**
	 * 用户表-用户创建时间
	 */
	private Date usercreatetime;
	
	/**
	 * 用户表-用户更新人ID(YHBG+UUID)
	 */
	private String userupdateuserid;

	/**
	 * 用户表-用户更新时间
	 */
	private Date userupdatetime;

	/**
	 * 用户表-用户备注
	 */
	private String userremark;
	
	/**
	 * 用户表-用户邀请人ID
	 */
	private String userinvitationuserid;
	/**
	 * 用户表-用户邀请人姓名
	 */
	private String userinvitationusername;
	/**
	 *  用户表-排序
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
	
	/**
	 * 用户_维修工、安装队表-接单区域接单区域名称(省市区，地点文字)(会有多个，以逗号分隔，以逗号结尾)
	 */
    private String areaid;

    /**
	 * 用户_维修工、安装队表-接单规模ID(JDGM+UUID)
	 */
    private String scaleid;
    
    /**
	 * 用户_维修工、安装队表-用户接单类别(WX:维修工/AZ:安装队)
	 */
    private String userservicerole;

    /**
	 * 用户_维修工、安装队表-用户接单状态(Y:接单/N:不接单)
	 */
    private String userservicestate;
    
    /**
	 * 用户_维修工、安装队表-用户差旅费(每公里)
	 */
    private Double userservicetravelmoney;
    
    /**
	 * 用户_维修工、安装队表-用户维修类型
	 */
    private String userservicetype;
    
    /**
	 * 用户_维修工、安装队表-用户维修优势
	 */
    private String userserviceadvantage;

    /**
	 * 用户_维修工、安装队表-用户_维修工、安装队备注
	 */
    private String userserviceremark;
	/**
	 * 接单区域代码
	 */
	private String areacode;

	/**
	 * 接单项目id
	 */
	private String goodid;
	/**
	 * 传入的任务类别ID
	 */
	private String goodsid;
	/**
	 * 传入的清单id
	 */
	private String projectid;
	/**
	 * 传入的单价
	 */
	private Double pricemoney;
	/**
	 * 是否为年度安装队Y/是、N/否
	 */
	private String useryearteam;


	/**
	 * 年协议量
	 */
	private Integer useryearnumber;
	/**
	 * 年百分比
	 */
	private Double useryearpercent;

	/**
	 * 接单区域名称
	 */
	private String areaname;

	/**
	 * 接单项目id
	 */
	private String goodsname;
    
    /**
	 * 用户_客户表-用户城市
	 */
	private String usercustomercity;

	/**
	 * 用户_客户表-用户单位
	 */
	private String usercustomercompany;

	/**
	 * 用户_客户表-用户所属组织
	 */
	private String usercustomerorganization;

	/**
	 * 用户_客户表-用户地址
	 */
	private String usercustomeraddress;

	/**
	 * 用户_客户表-用户_客户备注
	 */
	private String usercustomerremark;
	
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
	 * 用户身份类别(KH:客户/WX:维修工或安装队)
	 * 
	 * userrole字段为null
	 * 
	 * 查询时使用
	 */
	private String selectuserrole;

	/**
	 * 派单状态
	 *
	 * 更改派单人时状态
	 */
	private String backdistributionstate;
	/**
	 * 维修总价
	 * 
	 * 平台派单时使用
	 */
	private Double pricetotalmoney;
	
	/**
	 * 维修总价（不包含信用值系数总价--系数只计算选谁，不能加到总价中）
	 * 
	 * 平台派单时使用
	 */
	private Double orderPriceTotalMoneyNocredit;
	
	/**
	 * 距离
	 * 
	 * 平台派单时使用
	 */
	private Double distance;
	
	/**
	 * 维修差旅费总价
	 * 
	 * 平台派单时使用
	 */
	private Double traveltotalmoney;
	
	/**
	 * 总价(维修总价+维修差旅费总价)
	 * 
	 * 平台派单时使用
	 */
	private Double totalmoney;

	//生成报表时需要的字段
	/**
	 * 登录小程序次数
	 */
	private Double loginnumber;

	/**
	 * 参与派单次数
	 */
	private Double pdnumber;

	/**
	 * 参与竞标次数
	 */
	private Double qdnumber;

	/**
	 * 发布清单列表数
	 */
	private Double projectnumber;

	/**
	 * 派单完成数量
	 */
	private Double pdfinishnumber;

	/**
	 * 派单未完成数量
	 */
	private Double pdunfinishednumber;

	/**
	 * 竞标完成数量
	 */
	private Double qdfinishnumber;

	/**
	 * 竞标未完成数量
	 */
	private Double qdunfinishednumber;

	/**
	 * 利润
	 */
	private Double profittotalmoney;

	/**
	 * 订单合格数量
	 */
	private Double hgnumber;

	/**
	 * 订单不合格数量
	 */
	private Double bhgnumber;

	/**
	 * 推荐人数
	 */
	private Double recommendnumber;

	/**
	 * 发出评价平均分
	 */
	private Double sendevaluate;
	/**
	 * 收到评价平均分
	 */
	private Double getevaluate;

	/**
	 * 用户表-用户信用值最小值（范围查询）
	 */
	private Double usercreditmin;

	/**
	 * 用户表-用户信用值最大值（范围查询）
	 */
	private Double usercreditmax;

	/**
	 * 需要传的订单ID
	 */
	private String orderid;

	/**
	 * 根据areacodelist查询区域
	 */
	private List<String> areacodelist;

	/**
	 * 根据userlist查询用户
	 */
	private List<String> userlist;

	/**
	 * Token验证相关数据
	 * 
	 * userId
	 * 
	 * 登录时用到
	 */
	private String tokenUserId;
	
	/**
	 * Token验证相关数据
	 * 
	 * token
	 * 
	 * 登录时用到
	 */
	private String token;

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
		this.userid = userid;
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

	public Double getUsermoney() {
		return usermoney;
	}

	public void setUsermoney(Double usermoney) {
		this.usermoney = usermoney;
	}

	public Double getUsercredit() {
		return usercredit;
	}

	public void setUsercredit(Double usercredit) {
		this.usercredit = usercredit;
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

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getScaleid() {
		return scaleid;
	}

	public void setScaleid(String scaleid) {
		this.scaleid = scaleid;
	}

	public String getUserservicerole() {
		return userservicerole;
	}

	public void setUserservicerole(String userservicerole) {
		this.userservicerole = userservicerole;
	}

	public String getUserservicestate() {
		return userservicestate;
	}

	public void setUserservicestate(String userservicestate) {
		this.userservicestate = userservicestate;
	}

	public Double getUserservicetravelmoney() {
		return userservicetravelmoney;
	}

	public void setUserservicetravelmoney(Double userservicetravelmoney) {
		this.userservicetravelmoney = userservicetravelmoney;
	}

	public String getUserservicetype() {
		return userservicetype;
	}

	public void setUserservicetype(String userservicetype) {
		this.userservicetype = userservicetype;
	}

	public String getUserserviceadvantage() {
		return userserviceadvantage;
	}

	public void setUserserviceadvantage(String userserviceadvantage) {
		this.userserviceadvantage = userserviceadvantage;
	}

	public String getUserserviceremark() {
		return userserviceremark;
	}

	public void setUserserviceremark(String userserviceremark) {
		this.userserviceremark = userserviceremark;
	}

	public String getUsercustomercity() {
		return usercustomercity;
	}

	public void setUsercustomercity(String usercustomercity) {
		this.usercustomercity = usercustomercity;
	}

	public String getUsercustomercompany() {
		return usercustomercompany;
	}

	public void setUsercustomercompany(String usercustomercompany) {
		this.usercustomercompany = usercustomercompany;
	}

	public String getUsercustomerorganization() {
		return usercustomerorganization;
	}

	public void setUsercustomerorganization(String usercustomerorganization) {
		this.usercustomerorganization = usercustomerorganization;
	}

	public String getUsercustomeraddress() {
		return usercustomeraddress;
	}

	public void setUsercustomeraddress(String usercustomeraddress) {
		this.usercustomeraddress = usercustomeraddress;
	}

	public String getUsercustomerremark() {
		return usercustomerremark;
	}

	public void setUsercustomerremark(String usercustomerremark) {
		this.usercustomerremark = usercustomerremark;
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

	public String getSelectuserrole() {
		return selectuserrole;
	}

	public void setSelectuserrole(String selectuserrole) {
		this.selectuserrole = selectuserrole;
	}

	public Double getPricetotalmoney() {
		return pricetotalmoney;
	}

	public void setPricetotalmoney(Double pricetotalmoney) {
		this.pricetotalmoney = pricetotalmoney;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getTraveltotalmoney() {
		return traveltotalmoney;
	}

	public void setTraveltotalmoney(Double traveltotalmoney) {
		this.traveltotalmoney = traveltotalmoney;
	}

	public Double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}

	public String getTokenUserId() {
		return tokenUserId;
	}

	public void setTokenUserId(String tokenUserId) {
		this.tokenUserId = tokenUserId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

	public String getUserinvitationuserid() {
		return userinvitationuserid;
	}

	public void setUserinvitationuserid(String userinvitationuserid) {
		this.userinvitationuserid = userinvitationuserid;
	}

	
	public Double getOrderPriceTotalMoneyNocredit() {
		return orderPriceTotalMoneyNocredit;
	}

	public void setOrderPriceTotalMoneyNocredit(Double orderPriceTotalMoneyNocredit) {
		this.orderPriceTotalMoneyNocredit = orderPriceTotalMoneyNocredit;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getGoodid() {
		return goodid;
	}

	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}

	public String getBackdistributionstate() {
		return backdistributionstate;
	}

	public Integer getUsersort() {
		return usersort;
	}

	public void setUsersort(Integer usersort) {
		this.usersort = usersort;
	}

	public void setBackdistributionstate(String backdistributionstate) {
		this.backdistributionstate = backdistributionstate;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getUserinvitationusername() {
		return userinvitationusername;
	}

	public void setUserinvitationusername(String userinvitationusername) {
		this.userinvitationusername = userinvitationusername;
	}

	public Double getLoginnumber() {
		return loginnumber;
	}

	public void setLoginnumber(Double loginnumber) {
		this.loginnumber = loginnumber;
	}

	public Double getPdnumber() {
		return pdnumber;
	}

	public void setPdnumber(Double pdnumber) {
		this.pdnumber = pdnumber;
	}

	public Double getQdnumber() {
		return qdnumber;
	}

	public void setQdnumber(Double qdnumber) {
		this.qdnumber = qdnumber;
	}

	public Double getProjectnumber() {
		return projectnumber;
	}

	public void setProjectnumber(Double projectnumber) {
		this.projectnumber = projectnumber;
	}

	public Double getPdfinishnumber() {
		return pdfinishnumber;
	}

	public void setPdfinishnumber(Double pdfinishnumber) {
		this.pdfinishnumber = pdfinishnumber;
	}

	public Double getPdunfinishednumber() {
		return pdunfinishednumber;
	}

	public void setPdunfinishednumber(Double pdunfinishednumber) {
		this.pdunfinishednumber = pdunfinishednumber;
	}

	public Double getQdfinishnumber() {
		return qdfinishnumber;
	}

	public void setQdfinishnumber(Double qdfinishnumber) {
		this.qdfinishnumber = qdfinishnumber;
	}

	public Double getQdunfinishednumber() {
		return qdunfinishednumber;
	}

	public void setQdunfinishednumber(Double qdunfinishednumber) {
		this.qdunfinishednumber = qdunfinishednumber;
	}

	public Double getProfittotalmoney() {
		return profittotalmoney;
	}

	public void setProfittotalmoney(Double profittotalmoney) {
		this.profittotalmoney = profittotalmoney;
	}

	public Double getHgnumber() {
		return hgnumber;
	}

	public void setHgnumber(Double hgnumber) {
		this.hgnumber = hgnumber;
	}

	public Double getBhgnumber() {
		return bhgnumber;
	}

	public void setBhgnumber(Double bhgnumber) {
		this.bhgnumber = bhgnumber;
	}

	public Double getRecommendnumber() {
		return recommendnumber;
	}

	public void setRecommendnumber(Double recommendnumber) {
		this.recommendnumber = recommendnumber;
	}

	public Double getSendevaluate() {
		return sendevaluate;
	}

	public void setSendevaluate(Double sendevaluate) {
		this.sendevaluate = sendevaluate;
	}

	public Double getGetevaluate() {
		return getevaluate;
	}

	public void setGetevaluate(Double getevaluate) {
		this.getevaluate = getevaluate;
	}

	public Double getUsercreditmin() {
		return usercreditmin;
	}

	public void setUsercreditmin(Double usercreditmin) {
		this.usercreditmin = usercreditmin;
	}

	public Double getUsercreditmax() {
		return usercreditmax;
	}

	public void setUsercreditmax(Double usercreditmax) {
		this.usercreditmax = usercreditmax;
	}

	public List<String> getAreacodelist() {
		return areacodelist;
	}

	public void setAreacodelist(List<String> areacodelist) {
		this.areacodelist = areacodelist;
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

	public String getUseryearteam() {
		return useryearteam;
	}

	public void setUseryearteam(String useryearteam) {
		this.useryearteam = useryearteam;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getUseryearnumber() {
		return useryearnumber;
	}

	public void setUseryearnumber(Integer useryearnumber) {
		this.useryearnumber = useryearnumber;
	}

	public Double getUseryearpercent() {
		return useryearpercent;
	}

	public void setUseryearpercent(Double useryearpercent) {
		this.useryearpercent = useryearpercent;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public Double getPricemoney() {
		return pricemoney;
	}

	public void setPricemoney(Double pricemoney) {
		this.pricemoney = pricemoney;
	}

	public List<String> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<String> userlist) {
		this.userlist = userlist;
	}

	/**
	 * 自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
	 * 
	 * 平台派单时使用
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public int compareTo(UserAndUserServiceAndUserCustomer user) {
		if (this.totalmoney >= user.getTotalmoney()) {
			return 1;
		}
		
		return -1;
	}
	
}
