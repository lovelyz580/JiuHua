package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单表、建筑类型表
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/14
 */

public class OrderTableAndBuildingType {

	/**
	 * 维修单表-序号(自增主键)
	 */
	private Integer id;

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
	 * 维修单表-维修位置信息(当前位置信息)
	 */
	private String orderposition;
	
	/**
	 * 维修单表-维修位置纬度
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
	 * 维修人员自己看的没有报价系数
	 * 
	 * 维修单表-维修总价(根据维修单价计算得出)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中，维修总价+维修差旅费总价)
	 */
	private Double orderpriceservicetotalmoney;
	
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
	 * 维修申请验收时间(申请验收后，才会添加这个字段的数据)
	 */
	private Date orderapplychecktime;

	/**
	 * 维修单表-维修单备注
	 */
	private String orderremark;

	/**
	 * 维修单表-多次申请验收，是否全部申请验收：ALL_CHECK
	 */
	private String ordercheckallstate;
	/**
	 * 维修单表-订单中维修的日工资
	 */
	private Double orderdaymoney;

	/**
	 * 维修单表-订单中维修的天数
	 */
	private Integer orderday;

	/**
	 * 维修单表-订单中维修的人数
	 */
	private Integer orderpeoplenumber;

	/**
	 * 维修单表-订单中的材料费
	 */
	private Double ordermaterialmoney;

	/**
	 * 建筑类型表-建筑类型名称
	 */
	private String buildingtypename;

	/**
	 * 建筑类型表-建筑类型状态(Y:有效/N:无效)
	 */
	private String buildingtypestate;

	/**
	 * 建筑类型表-建筑类型系数
	 */
	private Double buildingtypecoefficient;
	
	/**
	 * 建筑类型表-建筑类型创建人ID(YHBG+UUID)
	 */
	private String buildingtypecreateuserid;

	/**
	 * 建筑类型表-建筑类型创建时间
	 */
	private Date buildingtypecreatetime;

	/**
	 * 建筑类型表-建筑类型更新人ID(YHBG+UUID)
	 */
	private String buildingtypeupdateuserid;

	/**
	 * 建筑类型表-建筑类型更新时间
	 */
	private Date buildingtypeupdatetime;

	/**
	 * 建筑类型表-建筑类型备注
	 */
	private String buildingtyperemark;

	/**
	 * 地产公司ID(DCGS+UUID)
	 */
	private String orderpropertycompanyid;
	/**
	 * 地产公司名称
	 */
	private String propertycompanyname;

	/**
	 * 付款方式内容
	 */
	private String orderpaymentmethodcontent;

	/**
	 * 订单的区域名称
	 */
	private String areaname;

	/**
	 * 维修单表-维修人员的姓名
	 */
	private String orderserviceusername;

	/**
	 * 维修单表-验收情况
	 */
	private String orderapplycheckstate;

	/**
	 * 维修单表-利润
	 */
	private Double profittotalmoney;

	/**
	 * 维修单表-清单列表（拼接的字符串）
	 */
	private String orderallproject;

	/**
	 * 报表中客户评价维修人员字符串
	 */
	private String evaluateServiceSetup;

	/**
	 * 报表中维修人员评价客户字符串
	 */
	private String evaluateCustomerSetup;

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
	 * 当前操作人员ID(YHBG+UUID)
	 */
	private String currentuserid;
	
	/**
	 * 根据维修计划时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectstarttime;

	/**
	 * 根据维修计划时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectendtime;
	
	/**
	 * 根据维修竞标结束时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectorderbiddingendstarttime;

	/**
	 * 根据维修竞标结束时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectorderbiddingendendtime;

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

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid == null ? null : orderid.trim();
	}

	public String getOrderprojectname() {
		return orderprojectname;
	}

	public void setOrderprojectname(String orderprojectname) {
		this.orderprojectname = orderprojectname == null ? null : orderprojectname.trim();
	}

	public String getOrdercontactperson() {
		return ordercontactperson;
	}

	public void setOrdercontactperson(String ordercontactperson) {
		this.ordercontactperson = ordercontactperson == null ? null : ordercontactperson.trim();
	}

	public String getOrdercontactphone() {
		return ordercontactphone;
	}

	public void setOrdercontactphone(String ordercontactphone) {
		this.ordercontactphone = ordercontactphone == null ? null : ordercontactphone.trim();
	}

	public String getOrderaddress() {
		return orderaddress;
	}

	public void setOrderaddress(String orderaddress) {
		this.orderaddress = orderaddress == null ? null : orderaddress.trim();
	}

	public String getBuildingtypeid() {
		return buildingtypeid;
	}

	public void setBuildingtypeid(String buildingtypeid) {
		this.buildingtypeid = buildingtypeid == null ? null : buildingtypeid.trim();
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
		this.ordertype = ordertype == null ? null : ordertype.trim();
	}

	public String getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate == null ? null : orderstate.trim();
	}

	public String getOrderserviceuserid() {
		return orderserviceuserid;
	}

	public void setOrderserviceuserid(String orderserviceuserid) {
		this.orderserviceuserid = orderserviceuserid == null ? null : orderserviceuserid.trim();
	}

	public String getOrdercreateuserid() {
		return ordercreateuserid;
	}

	public void setOrdercreateuserid(String ordercreateuserid) {
		this.ordercreateuserid = ordercreateuserid == null ? null : ordercreateuserid.trim();
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
		this.orderupdateuserid = orderupdateuserid == null ? null : orderupdateuserid.trim();
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
		this.orderapplycheckuserid = orderapplycheckuserid == null ? null : orderapplycheckuserid.trim();
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
		this.orderremark = orderremark == null ? null : orderremark.trim();
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
	
	public String getCurrentuserid() {
		return currentuserid;
	}

	public void setCurrentuserid(String currentuserid) {
		this.currentuserid = currentuserid;
	}

	public String getSelectstarttime() {
		return selectstarttime;
	}

	public void setSelectstarttime(String selectstarttime) {
		this.selectstarttime = selectstarttime;
	}

	public String getSelectendtime() {
		return selectendtime;
	}

	public void setSelectendtime(String selectendtime) {
		this.selectendtime = selectendtime;
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

	public String getBuildingtypename() {
		return buildingtypename;
	}

	public void setBuildingtypename(String buildingtypename) {
		this.buildingtypename = buildingtypename;
	}

	public String getBuildingtypestate() {
		return buildingtypestate;
	}

	public void setBuildingtypestate(String buildingtypestate) {
		this.buildingtypestate = buildingtypestate;
	}

	public Double getBuildingtypecoefficient() {
		return buildingtypecoefficient;
	}

	public void setBuildingtypecoefficient(Double buildingtypecoefficient) {
		this.buildingtypecoefficient = buildingtypecoefficient;
	}

	public String getBuildingtypecreateuserid() {
		return buildingtypecreateuserid;
	}

	public void setBuildingtypecreateuserid(String buildingtypecreateuserid) {
		this.buildingtypecreateuserid = buildingtypecreateuserid;
	}

	public Date getBuildingtypecreatetime() {
		return buildingtypecreatetime;
	}

	public void setBuildingtypecreatetime(Date buildingtypecreatetime) {
		this.buildingtypecreatetime = buildingtypecreatetime;
	}

	public String getBuildingtypeupdateuserid() {
		return buildingtypeupdateuserid;
	}

	public void setBuildingtypeupdateuserid(String buildingtypeupdateuserid) {
		this.buildingtypeupdateuserid = buildingtypeupdateuserid;
	}

	public Date getBuildingtypeupdatetime() {
		return buildingtypeupdatetime;
	}

	public void setBuildingtypeupdatetime(Date buildingtypeupdatetime) {
		this.buildingtypeupdatetime = buildingtypeupdatetime;
	}

	public String getBuildingtyperemark() {
		return buildingtyperemark;
	}

	public void setBuildingtyperemark(String buildingtyperemark) {
		this.buildingtyperemark = buildingtyperemark;
	}

	public Double getOrderpriceservicetotalmoney() {
		return orderpriceservicetotalmoney;
	}

	public void setOrderpriceservicetotalmoney(Double orderpriceservicetotalmoney) {
		this.orderpriceservicetotalmoney = orderpriceservicetotalmoney;
	}

	public String getOrdercheckallstate() {
		return ordercheckallstate;
	}

	public void setOrdercheckallstate(String ordercheckallstate) {
		this.ordercheckallstate = ordercheckallstate;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getOrderserviceusername() {
		return orderserviceusername;
	}

	public void setOrderserviceusername(String orderserviceusername) {
		this.orderserviceusername = orderserviceusername;
	}

	public String getOrderapplycheckstate() {
		return orderapplycheckstate;
	}

	public void setOrderapplycheckstate(String orderapplycheckstate) {
		this.orderapplycheckstate = orderapplycheckstate;
	}

	public Double getProfittotalmoney() {
		return profittotalmoney;
	}

	public void setProfittotalmoney(Double profittotalmoney) {
		this.profittotalmoney = profittotalmoney;
	}

	public Double getOrderdaymoney() {
		return orderdaymoney;
	}

	public void setOrderdaymoney(Double orderdaymoney) {
		this.orderdaymoney = orderdaymoney;
	}

	public Integer getOrderday() {
		return orderday;
	}

	public void setOrderday(Integer orderday) {
		this.orderday = orderday;
	}

	public Integer getOrderpeoplenumber() {
		return orderpeoplenumber;
	}

	public void setOrderpeoplenumber(Integer orderpeoplenumber) {
		this.orderpeoplenumber = orderpeoplenumber;
	}

	public Double getOrdermaterialmoney() {
		return ordermaterialmoney;
	}

	public void setOrdermaterialmoney(Double ordermaterialmoney) {
		this.ordermaterialmoney = ordermaterialmoney;
	}

	public String getOrderallproject() {
		return orderallproject;
	}

	public void setOrderallproject(String orderallproject) {
		this.orderallproject = orderallproject;
	}

	public String getEvaluateServiceSetup() {
		return evaluateServiceSetup;
	}

	public void setEvaluateServiceSetup(String evaluateServiceSetup) {
		this.evaluateServiceSetup = evaluateServiceSetup;
	}

	public String getEvaluateCustomerSetup() {
		return evaluateCustomerSetup;
	}

	public void setEvaluateCustomerSetup(String evaluateCustomerSetup) {
		this.evaluateCustomerSetup = evaluateCustomerSetup;
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

	public String getPropertycompanyname() {
		return propertycompanyname;
	}

	public void setPropertycompanyname(String propertycompanyname) {
		this.propertycompanyname = propertycompanyname;
	}
}