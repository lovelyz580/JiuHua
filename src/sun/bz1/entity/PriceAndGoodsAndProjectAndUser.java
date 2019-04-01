package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单价表、产品表、项目表、维修单价维护人员(维修工、安装队)(用户表)
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class PriceAndGoodsAndProjectAndUser {
	
	/**
	 * 维修单价表-序号(自增主键)
	 */
	private Integer id;

	/**
	 * 维修单价表-维修单价ID(WXDJ+UUID)
	 */
	private String priceid;

	/**
	 * 维修单价表-维修单价维护人(维修工、安装队)ID(YHBG+UUID)
	 */
	private String priceupdateuserid;

	/**
	 * 维修单价表-产品ID(CPBG+UUID)
	 */
	private String goodsid;

	/**
	 * 维修单价表-项目ID(XMBG+UUID)
	 */
	private String projectid;

	/**
	 * 维修单价表-维修单价类型(WX:维修工/AZ:安装队)
	 */
	private String pricetype;

	/**
	 * 维修单价表-维修单价
	 */
	private Double pricemoney;

	/**
	 * 维修单价表-维修单价状态(Y:有效/N:无效)
	 */
	private String pricestate;

	/**
	 * 维修单价表-维修单价创建人ID(YHBG+UUID)
	 */
	private String pricecreateuserid;

	/**
	 * 维修单价表-维修单价创建时间
	 */
	private Date pricecreatetime;

	/**
	 * 维修单价表-维修单价更新时间
	 */
	private Date priceupdatetime;

	/**
	 * 维修单价表-维修单价备注
	 */
	private String priceremark;

	/**
	 * 维修单价的区域代码
	 */
	private String priceareaprovincecode;

	/**
	 * 维修单价的省级区域名称
	 */
	private String priceareaprovincename;

	/**
	 * 维修单价的区域代码
	 */
	private String priceareacode;

	/**
	 * 维修单价的区域名称
	 */
	private String priceareaname;

	/**
	 * 维修单价的建筑类型ID
	 */
	private String pricebuildingtypeid;

	/**
	 * 维修单价的建筑类型名称
	 */
	private String pricebuildingtypename;

	/**
	 *维修单价的地产公司ID
	 */
	private String pricepropertycompanyid;

	/**
	 *维修单价的地产公司名称
	 */
	private String pricepropertycompanyname;
	
	/**
	 * 产品表-产品名称
	 */
	private String goodsname;

	/**
	 * 产品表-产品类型(WX:维修工/AZ:安装队)
	 */
	private String goodstype;

	/**
	 * 产品表-产品状态(Y:有效/N:无效)
	 */
	private String goodsstate;

	/**
	 * 产品表-产品备注
	 */
	private String goodsremark;

	/**
	 * 项目表-项目名称
	 */
	private String projectname;

	/**
	 * 项目表-项目类型(WX:维修工/AZ:安装队)
	 */
	private String projecttype;

	/**
	 * 项目表-项目状态(Y:有效/N:无效)
	 */
	private String projectstate;

	/**
	 * 项目表-项目备注
	 */
	private String projectremark;
	
	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户名
	 */
	private String priceupdateuser_username;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户真实姓名
	 */
	private String priceupdateuser_userrealname;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户性别(Y:男/X:女)
	 */
	private String priceupdateuser_usersex;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户手机号码
	 */
	private String priceupdateuser_userphone;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户身份证号码
	 */
	private String priceupdateuser_usercodeid;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户邮箱
	 */
	private String priceupdateuser_useremail;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String priceupdateuser_userrole;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String priceupdateuser_userstate;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户信用值
	 */
	private Double priceupdateuser_usercredit;
	
	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户余额
	 */
	private Double priceupdateuser_usermoney;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户微信OpenId
	 */
	private String priceupdateuser_useropenid;
	
	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户位置信息(当前位置信息)
	 */
	private String priceupdateuser_userposition;
	
	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户位置纬度
	 */
	private Double priceupdateuser_userlatitude;
	
	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户位置经度
	 */
	private Double priceupdateuser_userlongitude;

	/**
	 * 维修单价维护人员(维修工、安装队)(用户表)-用户备注
	 */
	private String priceupdateuser_userremark;
	
	/**
	 * 拦标价表-拦标价
	 */
	private Double interceptrealmoney;

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
	 * 根据维修单价表的维修单价创建时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectpricecreateuserstarttime;

	/**
	 * 根据维修单价表的维修单价创建时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectpricecreateuserendtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPriceid() {
		return priceid;
	}

	public void setPriceid(String priceid) {
		this.priceid = priceid;
	}

	public String getPriceupdateuserid() {
		return priceupdateuserid;
	}

	public void setPriceupdateuserid(String priceupdateuserid) {
		this.priceupdateuserid = priceupdateuserid;
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

	public String getPricetype() {
		return pricetype;
	}

	public void setPricetype(String pricetype) {
		this.pricetype = pricetype;
	}

	public Double getPricemoney() {
		return pricemoney;
	}

	public void setPricemoney(Double pricemoney) {
		this.pricemoney = pricemoney;
	}

	public String getPricestate() {
		return pricestate;
	}

	public void setPricestate(String pricestate) {
		this.pricestate = pricestate;
	}

	public String getPricecreateuserid() {
		return pricecreateuserid;
	}

	public void setPricecreateuserid(String pricecreateuserid) {
		this.pricecreateuserid = pricecreateuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getPricecreatetime() {
		return pricecreatetime;
	}

	public void setPricecreatetime(Date pricecreatetime) {
		this.pricecreatetime = pricecreatetime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getPriceupdatetime() {
		return priceupdatetime;
	}

	public void setPriceupdatetime(Date priceupdatetime) {
		this.priceupdatetime = priceupdatetime;
	}

	public String getPriceremark() {
		return priceremark;
	}

	public void setPriceremark(String priceremark) {
		this.priceremark = priceremark;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}

	public String getGoodsstate() {
		return goodsstate;
	}

	public void setGoodsstate(String goodsstate) {
		this.goodsstate = goodsstate;
	}

	public String getGoodsremark() {
		return goodsremark;
	}

	public void setGoodsremark(String goodsremark) {
		this.goodsremark = goodsremark;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getProjectstate() {
		return projectstate;
	}

	public void setProjectstate(String projectstate) {
		this.projectstate = projectstate;
	}

	public String getProjectremark() {
		return projectremark;
	}

	public void setProjectremark(String projectremark) {
		this.projectremark = projectremark;
	}

	public String getPriceupdateuser_username() {
		return priceupdateuser_username;
	}

	public void setPriceupdateuser_username(String priceupdateuser_username) {
		this.priceupdateuser_username = priceupdateuser_username;
	}

	public String getPriceupdateuser_userrealname() {
		return priceupdateuser_userrealname;
	}

	public void setPriceupdateuser_userrealname(String priceupdateuser_userrealname) {
		this.priceupdateuser_userrealname = priceupdateuser_userrealname;
	}

	public String getPriceupdateuser_usersex() {
		return priceupdateuser_usersex;
	}

	public void setPriceupdateuser_usersex(String priceupdateuser_usersex) {
		this.priceupdateuser_usersex = priceupdateuser_usersex;
	}

	public String getPriceupdateuser_userphone() {
		return priceupdateuser_userphone;
	}

	public void setPriceupdateuser_userphone(String priceupdateuser_userphone) {
		this.priceupdateuser_userphone = priceupdateuser_userphone;
	}

	public String getPriceupdateuser_usercodeid() {
		return priceupdateuser_usercodeid;
	}

	public void setPriceupdateuser_usercodeid(String priceupdateuser_usercodeid) {
		this.priceupdateuser_usercodeid = priceupdateuser_usercodeid;
	}

	public String getPriceupdateuser_useremail() {
		return priceupdateuser_useremail;
	}

	public void setPriceupdateuser_useremail(String priceupdateuser_useremail) {
		this.priceupdateuser_useremail = priceupdateuser_useremail;
	}

	public String getPriceupdateuser_userrole() {
		return priceupdateuser_userrole;
	}

	public void setPriceupdateuser_userrole(String priceupdateuser_userrole) {
		this.priceupdateuser_userrole = priceupdateuser_userrole;
	}

	public String getPriceupdateuser_userstate() {
		return priceupdateuser_userstate;
	}

	public void setPriceupdateuser_userstate(String priceupdateuser_userstate) {
		this.priceupdateuser_userstate = priceupdateuser_userstate;
	}

	public Double getPriceupdateuser_usercredit() {
		return priceupdateuser_usercredit;
	}

	public void setPriceupdateuser_usercredit(Double priceupdateuser_usercredit) {
		this.priceupdateuser_usercredit = priceupdateuser_usercredit;
	}

	public Double getPriceupdateuser_usermoney() {
		return priceupdateuser_usermoney;
	}

	public void setPriceupdateuser_usermoney(Double priceupdateuser_usermoney) {
		this.priceupdateuser_usermoney = priceupdateuser_usermoney;
	}

	public String getPriceupdateuser_useropenid() {
		return priceupdateuser_useropenid;
	}

	public void setPriceupdateuser_useropenid(String priceupdateuser_useropenid) {
		this.priceupdateuser_useropenid = priceupdateuser_useropenid;
	}

	public String getPriceupdateuser_userposition() {
		return priceupdateuser_userposition;
	}

	public void setPriceupdateuser_userposition(String priceupdateuser_userposition) {
		this.priceupdateuser_userposition = priceupdateuser_userposition;
	}

	public Double getPriceupdateuser_userlatitude() {
		return priceupdateuser_userlatitude;
	}

	public void setPriceupdateuser_userlatitude(Double priceupdateuser_userlatitude) {
		this.priceupdateuser_userlatitude = priceupdateuser_userlatitude;
	}

	public Double getPriceupdateuser_userlongitude() {
		return priceupdateuser_userlongitude;
	}

	public void setPriceupdateuser_userlongitude(Double priceupdateuser_userlongitude) {
		this.priceupdateuser_userlongitude = priceupdateuser_userlongitude;
	}

	public String getPriceupdateuser_userremark() {
		return priceupdateuser_userremark;
	}

	public void setPriceupdateuser_userremark(String priceupdateuser_userremark) {
		this.priceupdateuser_userremark = priceupdateuser_userremark;
	}

	public Double getInterceptrealmoney() {
		return interceptrealmoney;
	}

	public void setInterceptrealmoney(Double interceptrealmoney) {
		this.interceptrealmoney = interceptrealmoney;
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

	public String getSelectpricecreateuserstarttime() {
		return selectpricecreateuserstarttime;
	}

	public void setSelectpricecreateuserstarttime(String selectpricecreateuserstarttime) {
		this.selectpricecreateuserstarttime = selectpricecreateuserstarttime;
	}

	public String getSelectpricecreateuserendtime() {
		return selectpricecreateuserendtime;
	}

	public void setSelectpricecreateuserendtime(String selectpricecreateuserendtime) {
		this.selectpricecreateuserendtime = selectpricecreateuserendtime;
	}

	public String getPriceareacode() {
		return priceareacode;
	}

	public void setPriceareacode(String priceareacode) {
		this.priceareacode = priceareacode;
	}

	public String getPricebuildingtypeid() {
		return pricebuildingtypeid;
	}

	public void setPricebuildingtypeid(String pricebuildingtypeid) {
		this.pricebuildingtypeid = pricebuildingtypeid;
	}

	public String getPricepropertycompanyid() {
		return pricepropertycompanyid;
	}

	public void setPricepropertycompanyid(String pricepropertycompanyid) {
		this.pricepropertycompanyid = pricepropertycompanyid;
	}

	public String getPriceareaprovincecode() {
		return priceareaprovincecode;
	}

	public void setPriceareaprovincecode(String priceareaprovincecode) {
		this.priceareaprovincecode = priceareaprovincecode;
	}

	public String getPriceareaprovincename() {
		return priceareaprovincename;
	}

	public void setPriceareaprovincename(String priceareaprovincename) {
		this.priceareaprovincename = priceareaprovincename;
	}

	public String getPriceareaname() {
		return priceareaname;
	}

	public void setPriceareaname(String priceareaname) {
		this.priceareaname = priceareaname;
	}

	public String getPricebuildingtypename() {
		return pricebuildingtypename;
	}

	public void setPricebuildingtypename(String pricebuildingtypename) {
		this.pricebuildingtypename = pricebuildingtypename;
	}

	public String getPricepropertycompanyname() {
		return pricepropertycompanyname;
	}

	public void setPricepropertycompanyname(String pricepropertycompanyname) {
		this.pricepropertycompanyname = pricepropertycompanyname;
	}
}
