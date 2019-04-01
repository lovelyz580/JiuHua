package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单对应维修单价表、产品表、项目表、维修单价维护人员(维修工、安装队)(用户表)
 * 
 * 实体类
 * 
 * @author WJF on 2018/10/09
 */

public class OrderPriceAndGoodsAndProjectAndUser {

	/**
	 * 维修单对应维修单价表-序号(自增主键)
	 */
    private Integer id;

    /**
	 * 维修单对应维修单价表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
	 */
    private String orderpriceupdateuserid;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的产品ID(CPBG+UUID)
	 */
    private String goodsid;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的项目ID(XMBG+UUID)
	 */
    private String projectid;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的类型(WX:维修工/AZ:安装队)
	 */
    private String orderpricetype;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的单价
	 */
    private Double orderpricemoney;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的状态(Y:有效/N:无效)
	 */
    private String orderpricestate;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的创建人ID(YHBG+UUID)
	 */
    private String orderpricecreateuserid;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的创建时间
	 */
    private Date orderpricecreatetime;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的更新时间
	 */
    private Date orderpriceupdatetime;

    /**
	 * 维修单对应维修单价表-维修单对应维修单价的备注
	 */
    private String orderpriceremark;

	/**
	 * 维修单对应维修单价的省级区域代码
	 */
	private String orderpriceareaprovincecode;

	/**
	 * 维修单对应维修单价的省级区域名称
	 */
	private String orderpriceareaprovincename;

	/**
	 * 维修单对应维修单价的区域代码
	 */
	private String orderpriceareacode;

	/**
	 * 维修单对应维修单价的区域名称
	 */
	private String orderpriceareaname;

	/**
	 * 维修单对应维修单价的建筑类型ID
	 */
	private String orderpricebuildingtypeid;

	/**
	 * 维修单对应维修单价的建筑类型名称
	 */
	private String orderpricebuildingtypename;

	/**
	 * 维修单对应维修单价的地产公司ID
	 */
	private String orderpricepropertycompanyid;

	/**
	 * 维修单对应维修单价的地产公司名称
	 */
	private String orderpricepropertycompanyname;
    
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderpriceupdateuserid() {
		return orderpriceupdateuserid;
	}

	public void setOrderpriceupdateuserid(String orderpriceupdateuserid) {
		this.orderpriceupdateuserid = orderpriceupdateuserid;
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

	public String getOrderpricetype() {
		return orderpricetype;
	}

	public void setOrderpricetype(String orderpricetype) {
		this.orderpricetype = orderpricetype;
	}

	public Double getOrderpricemoney() {
		return orderpricemoney;
	}

	public void setOrderpricemoney(Double orderpricemoney) {
		this.orderpricemoney = orderpricemoney;
	}

	public String getOrderpricestate() {
		return orderpricestate;
	}

	public void setOrderpricestate(String orderpricestate) {
		this.orderpricestate = orderpricestate;
	}

	public String getOrderpricecreateuserid() {
		return orderpricecreateuserid;
	}

	public void setOrderpricecreateuserid(String orderpricecreateuserid) {
		this.orderpricecreateuserid = orderpricecreateuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getOrderpricecreatetime() {
		return orderpricecreatetime;
	}

	public void setOrderpricecreatetime(Date orderpricecreatetime) {
		this.orderpricecreatetime = orderpricecreatetime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getOrderpriceupdatetime() {
		return orderpriceupdatetime;
	}

	public void setOrderpriceupdatetime(Date orderpriceupdatetime) {
		this.orderpriceupdatetime = orderpriceupdatetime;
	}

	public String getOrderpriceremark() {
		return orderpriceremark;
	}

	public void setOrderpriceremark(String orderpriceremark) {
		this.orderpriceremark = orderpriceremark;
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

	public String getOrderpriceareacode() {
		return orderpriceareacode;
	}

	public void setOrderpriceareacode(String orderpriceareacode) {
		this.orderpriceareacode = orderpriceareacode;
	}

	public String getOrderpricebuildingtypeid() {
		return orderpricebuildingtypeid;
	}

	public void setOrderpricebuildingtypeid(String orderpricebuildingtypeid) {
		this.orderpricebuildingtypeid = orderpricebuildingtypeid;
	}

	public String getOrderpricepropertycompanyid() {
		return orderpricepropertycompanyid;
	}

	public void setOrderpricepropertycompanyid(String orderpricepropertycompanyid) {
		this.orderpricepropertycompanyid = orderpricepropertycompanyid;
	}

	public String getOrderpriceareaprovincecode() {
		return orderpriceareaprovincecode;
	}

	public void setOrderpriceareaprovincecode(String orderpriceareaprovincecode) {
		this.orderpriceareaprovincecode = orderpriceareaprovincecode;
	}

	public String getOrderpriceareaprovincename() {
		return orderpriceareaprovincename;
	}

	public void setOrderpriceareaprovincename(String orderpriceareaprovincename) {
		this.orderpriceareaprovincename = orderpriceareaprovincename;
	}

	public String getOrderpriceareaname() {
		return orderpriceareaname;
	}

	public void setOrderpriceareaname(String orderpriceareaname) {
		this.orderpriceareaname = orderpriceareaname;
	}

	public String getOrderpricebuildingtypename() {
		return orderpricebuildingtypename;
	}

	public void setOrderpricebuildingtypename(String orderpricebuildingtypename) {
		this.orderpricebuildingtypename = orderpricebuildingtypename;
	}

	public String getOrderpricepropertycompanyname() {
		return orderpricepropertycompanyname;
	}

	public void setOrderpricepropertycompanyname(String orderpricepropertycompanyname) {
		this.orderpricepropertycompanyname = orderpricepropertycompanyname;
	}
}
