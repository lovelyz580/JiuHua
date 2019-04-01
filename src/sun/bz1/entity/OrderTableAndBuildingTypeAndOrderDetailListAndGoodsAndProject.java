package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 维修单表、建筑类型表、维修单详情表List 项目表、清单表
 * 
 * 实体类
 * 
 * @author ZY on 2018/12/07
 */

public class OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject {

	/**
	 * 维修单表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

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
	 * 维修单表-订单创建人
	 */
	private String ordercreateuserid;

	/**
	 * 维修单表-订单维修人
	 */
	private String orderserviceuserid;

	/**
	 * 维修单表-清单数量
	 */
	private Integer orderdetailnumber;

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
	 * 维修总价
	 */
	private Double orderintercepttotalmoney;

	/**
	 * 维修支出总价
	 */
	private Double orderpricetotalmoney;

	/**
	 * 维修清单推荐价总价
	 */
	private Double orderdetailintercepttotalmoney;

	/**
	 * 维修清单单价
	 */
	private Double orderdetailpricemoney;

	/**
	 * 任务类别ID
	 */
	private String goodsid;

	/**
	 * 清单ID
	 */
	private String projectid;
	/**
	 * 订单类别
	 */
	private String ordertype;

	/**
	 * 维修创建时间
	 */
	private Date ordercreatetime;

	/**
	 * 维修位置信息(区域代码)
	 */
	private String orderposition;


	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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

	public String getOrdercreateuserid() {
		return ordercreateuserid;
	}

	public void setOrdercreateuserid(String ordercreateuserid) {
		this.ordercreateuserid = ordercreateuserid;
	}

	public String getOrderserviceuserid() {
		return orderserviceuserid;
	}

	public void setOrderserviceuserid(String orderserviceuserid) {
		this.orderserviceuserid = orderserviceuserid;
	}

	public Integer getOrderdetailnumber() {
		return orderdetailnumber;
	}

	public void setOrderdetailnumber(Integer orderdetailnumber) {
		this.orderdetailnumber = orderdetailnumber;
	}

	public Double getOrderintercepttotalmoney() {
		return orderintercepttotalmoney;
	}

	public void setOrderintercepttotalmoney(Double orderintercepttotalmoney) {
		this.orderintercepttotalmoney = orderintercepttotalmoney;
	}

	public Double getOrderpricetotalmoney() {
		return orderpricetotalmoney;
	}

	public void setOrderpricetotalmoney(Double orderpricetotalmoney) {
		this.orderpricetotalmoney = orderpricetotalmoney;
	}

	public Double getOrderdetailintercepttotalmoney() {
		return orderdetailintercepttotalmoney;
	}

	public void setOrderdetailintercepttotalmoney(Double orderdetailintercepttotalmoney) {
		this.orderdetailintercepttotalmoney = orderdetailintercepttotalmoney;
	}

	public Double getOrderdetailpricemoney() {
		return orderdetailpricemoney;
	}

	public void setOrderdetailpricemoney(Double orderdetailpricemoney) {
		this.orderdetailpricemoney = orderdetailpricemoney;
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

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getOrdercreatetime() {
		return ordercreatetime;
	}

	public void setOrdercreatetime(Date ordercreatetime) {
		this.ordercreatetime = ordercreatetime;
	}

	public String getOrderposition() {
		return orderposition;
	}

	public void setOrderposition(String orderposition) {
		this.orderposition = orderposition;
	}
}
