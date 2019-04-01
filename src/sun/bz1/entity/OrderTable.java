package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/07
 */

public class OrderTable {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 维修项目名称
	 */
	private String orderprojectname;

	/**
	 * 维修联系人(报修人)名称
	 */
	private String ordercontactperson;

	/**
	 * 维修联系电话
	 */
	private String ordercontactphone;

	/**
	 * 接单区域ID(JDQY+UUID)
	 */
	private String orderaddress;

	/**
	 * 维修建筑类型ID(JZLX+UUID)
	 */
	private String buildingtypeid;

	/**
	 * 维修计划时间
	 */
	private Date orderplantime;
	
	/**
	 * 维修竞标结束时间(抢单类型时，才会添加这个字段的数据
	 */
	private Date orderbiddingendtime;
	
	/**
	 * 维修项目类型(WX:维修工/AZ:安装队)
	 */
	private String orderprojecttype;
	
	/**
	 * 维修位置信息(当前位置信息)
	 */
	private String orderposition;
	
	/**
	 * 维修位置纬度
	 */
	private Double orderlatitude;
	
	/**
	 * 维修位置经度
	 */
	private Double orderlongitude;
	
	/**
	 * 维修距离(确认维修人员后，才会添加这个字段的数据)
	 */
	private Double orderdistance;

	/**
	 * 维修拦标总价(下单时，对应的拦标总价)
	 */
	private Double orderintercepttotalmoney;
	
	/**
	 * 维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)(确认维修人员后，才会添加这个字段的数据)
	 */
	private Double orderintercepttraveltotalmoney;

	/**
	 * 维修总价(根据维修单价计算得出)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中，维修总价+维修差旅费总价)
	 */
	private Double orderpricetotalmoney;
	
	/**
	 * 维修差旅费总价(根据差旅费*距离计算得出)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中，维修总价+维修差旅费总价)
	 */
	private Double ordertraveltotalmoney;
	
	/**
	 * 从前端传过来的差旅费总价（在竞标时维修工填写）
	 */
	private Double accepttraveltotalmoney;
	
	/**
	 * 总价(维修总价+维修差旅费总价)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中)
	 * 
	 * 业务字段，数据表中没有该字段
	 */
	private Double ordertotalmoney;

	/**
	 * 维修类型(PD:派单/QD:抢单)
	 */
	private String ordertype;

	/**
	 * 维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
	 */
	private String orderstate;

	/**
	 * 维修人员ID(YHBG+UUID)(确认维修人员后，才会添加这个字段的数据)
	 */
	private String orderserviceuserid;

	/**
	 * 维修创建人ID(YHBG+UUID)
	 */
	private String ordercreateuserid;

	/**
	 * 维修创建时间
	 */
	private Date ordercreatetime;

	/**
	 * 维修更新人ID(YHBG+UUID)
	 */
	private String orderupdateuserid;

	/**
	 * 维修更新时间
	 */
	private Date orderupdatetime;

	/**
	 * 维修申请验收人ID(YHBG+UUID)(申请验收后，才会添加这个字段的数据)
	 */
	private String orderapplycheckuserid;

	/**
	 * 维修申请验收时间(申请验收后，才会添加这个字段的数据)
	 */
	private Date orderapplychecktime;

	/**
	 * 维修单备注
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
	 * 多次申请验收，是否全部申请验收：ALL_CHECK
	 */
	private String ordercheckallstate;

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
	 *
	 * 查询时间段的订单开始时间
	 */
	private String selectorderstarttime;

	/**
	 *
	 * 查询时间段的订单结束时间
	 */
	private String selectorderendtime;

	/**
	 *平台账号
	 */
	private String userpaymentplatformaccount;
	/**
	 *平台账号开户行地址
	 */
	private String userpaymentserviceaccount;
	/**
	 *平台账号开户人
	 */
	private String userpaymentplatformaccountname;

	/**
	 * 订单中维修的日工资
	 */
	private Double orderdaymoney;

	/**
	 * 订单中维修的天数
	 */
	private Integer orderday;

	/**
	 * 订单中维修的人数
	 */
	private Integer orderpeoplenumber;

    /**
     * 订单中的材料费
     */
    private Double ordermaterialmoney;

	/**
	 * 模糊查询code
	 */
	private String selectareaprovincecode;

	/**
	 * 验收的不合格原因
	 */
	private String applycheckcontent;

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

	public Double getAccepttraveltotalmoney() {
		return accepttraveltotalmoney;
	}

	public void setAccepttraveltotalmoney(Double accepttraveltotalmoney) {
		this.accepttraveltotalmoney = accepttraveltotalmoney;
	}

	public String getOrdercheckallstate() {
		return ordercheckallstate;
	}

	public void setOrdercheckallstate(String ordercheckallstate) {
		this.ordercheckallstate = ordercheckallstate;
	}

	public String getSelectorderstarttime() {
		return selectorderstarttime;
	}

	public void setSelectorderstarttime(String selectorderstarttime) {
		this.selectorderstarttime = selectorderstarttime;
	}

	public String getSelectorderendtime() {
		return selectorderendtime;
	}

	public void setSelectorderendtime(String selectorderendtime) {
		this.selectorderendtime = selectorderendtime;
	}

	public String getUserpaymentplatformaccount() {
		return userpaymentplatformaccount;
	}

	public void setUserpaymentplatformaccount(String userpaymentplatformaccount) {
		this.userpaymentplatformaccount = userpaymentplatformaccount;
	}

	public String getUserpaymentserviceaccount() {
		return userpaymentserviceaccount;
	}

	public void setUserpaymentserviceaccount(String userpaymentserviceaccount) {
		this.userpaymentserviceaccount = userpaymentserviceaccount;
	}

	public String getUserpaymentplatformaccountname() {
		return userpaymentplatformaccountname;
	}

	public void setUserpaymentplatformaccountname(String userpaymentplatformaccountname) {
		this.userpaymentplatformaccountname = userpaymentplatformaccountname;
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

	public String getSelectareaprovincecode() {
		return selectareaprovincecode;
	}

	public void setSelectareaprovincecode(String selectareaprovincecode) {
		this.selectareaprovincecode = selectareaprovincecode;
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

	public String getApplycheckcontent() {
		return applycheckcontent;
	}

	public void setApplycheckcontent(String applycheckcontent) {
		this.applycheckcontent = applycheckcontent;
	}
}