package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class Project {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 项目ID(XMBG+UUID)
	 */
	private String projectid;
	
	/**
	 * 产品ID(CPBG+UUID)
	 */
	private String goodsid;

	/**
	 * 项目名称
	 */
	private String projectname;

	/**
	 * 项目类型(WX:维修工/AZ:安装队)
	 */
	private String projecttype;

	/**
	 * 项目状态(Y:有效/N:无效)
	 */
	private String projectstate;

	/**
	 * 项目创建人ID(YHBG+UUID)
	 */
	private String projectcreateuserid;

	/**
	 * 项目创建时间
	 */
	private Date projectcreatetime;

	/**
	 * 项目更新人ID(YHBG+UUID)
	 */
	private String projectupdateuserid;

	/**
	 * 项目更新时间
	 */
	private Date projectupdatetime;

	/**
	 * 项目备注
	 */
	private String projectremark;

	//生成报表时用到的字段
	/**
	 * 项目名称
	 */
	private String goodsname;

	/**
	 * 清单平台推荐价
	 */
	private Double interceptmoney;

	/**
	 * 清单拦标价
	 */
	private Double interceptrealmoney;

	/**
	 * 清单的平均成交价
	 */
	private Double averagetransactionmoney;

	/**
	 * 清单的采购成交价（维修工的价格）
	 */
	private Double averagepurchasemoney;

	/**
	 * 清单的维修备案平均价格
	 */
	private Double averageservicenmoney;

	/**
	 * 清单的维修次数
	 */
	private Double repairnumber;

	/**
	 * 清单的维修总价格
	 */
	private Double repairtotalmoney;

	/**
	 * 清单的维修支出总价格（支付给维修工的钱）
	 */
	private Double paytotalmoney;

	/**
	 * 利润
	 */
	private Double profittotalmoney;


	/**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;
	/**
	 * 订单表-订单项目的名称
	 */
	private String orderprojectname;

	/**
	 * 订单表-订单发布人
	 */
	private String ordercontactperson;

	/**
	 * 订单表-订单发布时间
	 */
	private String ordercreatetime;
	/**
	 * 成交价格
	 */
	private Double orderpricemoney;
	/**
	 * 采购价格
	 */
	private Double purchasemoney;

	/**
	 * 维修总收入价格（详情中数据）
	 */
	private Double orderpricetotalmoney;

	/**
	 * 维修总支出价格（详情中数据）
	 */
	private Double purchasetotalmoney;


	/**
	 * 用户表-用户姓名
	 */
	private String username;

	/**
	 * 订单详情表-清单的数量
	 */
	private Integer orderdetailnumber;

	/**
	 * 按区域查询
	 */
	private String areacode;
	/**
	 * 按订单开始时间
	 */
	private String starttime;
	/**
	 * 按订单结束时间
	 */
	private String endtime;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid == null ? null : projectid.trim();
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname == null ? null : projectname.trim();
	}

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype == null ? null : projecttype.trim();
	}

	public String getProjectstate() {
		return projectstate;
	}

	public void setProjectstate(String projectstate) {
		this.projectstate = projectstate == null ? null : projectstate.trim();
	}

	public String getProjectcreateuserid() {
		return projectcreateuserid;
	}

	public void setProjectcreateuserid(String projectcreateuserid) {
		this.projectcreateuserid = projectcreateuserid == null ? null : projectcreateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getProjectcreatetime() {
		return projectcreatetime;
	}

	public void setProjectcreatetime(Date projectcreatetime) {
		this.projectcreatetime = projectcreatetime;
	}

	public String getProjectupdateuserid() {
		return projectupdateuserid;
	}

	public void setProjectupdateuserid(String projectupdateuserid) {
		this.projectupdateuserid = projectupdateuserid == null ? null : projectupdateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getProjectupdatetime() {
		return projectupdatetime;
	}

	public void setProjectupdatetime(Date projectupdatetime) {
		this.projectupdatetime = projectupdatetime;
	}

	public String getProjectremark() {
		return projectremark;
	}

	public void setProjectremark(String projectremark) {
		this.projectremark = projectremark == null ? null : projectremark.trim();
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

	public Double getInterceptmoney() {
		return interceptmoney;
	}

	public void setInterceptmoney(Double interceptmoney) {
		this.interceptmoney = interceptmoney;
	}

	public Double getInterceptrealmoney() {
		return interceptrealmoney;
	}

	public void setInterceptrealmoney(Double interceptrealmoney) {
		this.interceptrealmoney = interceptrealmoney;
	}

	public Double getAveragetransactionmoney() {
		return averagetransactionmoney;
	}

	public void setAveragetransactionmoney(Double averagetransactionmoney) {
		this.averagetransactionmoney = averagetransactionmoney;
	}

	public Double getAverageservicenmoney() {
		return averageservicenmoney;
	}

	public void setAverageservicenmoney(Double averageservicenmoney) {
		this.averageservicenmoney = averageservicenmoney;
	}

	public Double getRepairnumber() {
		return repairnumber;
	}

	public void setRepairnumber(Double repairnumber) {
		this.repairnumber = repairnumber;
	}

	public Double getRepairtotalmoney() {
		return repairtotalmoney;
	}

	public void setRepairtotalmoney(Double repairtotalmoney) {
		this.repairtotalmoney = repairtotalmoney;
	}

	public Double getAveragepurchasemoney() {
		return averagepurchasemoney;
	}

	public void setAveragepurchasemoney(Double averagepurchasemoney) {
		this.averagepurchasemoney = averagepurchasemoney;
	}

	public Double getPaytotalmoney() {
		return paytotalmoney;
	}

	public void setPaytotalmoney(Double paytotalmoney) {
		this.paytotalmoney = paytotalmoney;
	}

	public Double getProfittotalmoney() {
		return profittotalmoney;
	}

	public void setProfittotalmoney(Double profittotalmoney) {
		this.profittotalmoney = profittotalmoney;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

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

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public String getOrdercreatetime() {
		return ordercreatetime;
	}

	public void setOrdercreatetime(String ordercreatetime) {
		this.ordercreatetime = ordercreatetime;
	}

	public Double getOrderpricemoney() {
		return orderpricemoney;
	}

	public void setOrderpricemoney(Double orderpricemoney) {
		this.orderpricemoney = orderpricemoney;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getOrderdetailnumber() {
		return orderdetailnumber;
	}

	public void setOrderdetailnumber(Integer orderdetailnumber) {
		this.orderdetailnumber = orderdetailnumber;
	}

	public Double getPurchasemoney() {
		return purchasemoney;
	}

	public void setPurchasemoney(Double purchasemoney) {
		this.purchasemoney = purchasemoney;
	}

	public Double getOrderpricetotalmoney() {
		return orderpricetotalmoney;
	}

	public void setOrderpricetotalmoney(Double orderpricetotalmoney) {
		this.orderpricetotalmoney = orderpricetotalmoney;
	}

	public Double getPurchasetotalmoney() {
		return purchasetotalmoney;
	}

	public void setPurchasetotalmoney(Double purchasetotalmoney) {
		this.purchasetotalmoney = purchasetotalmoney;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
}
