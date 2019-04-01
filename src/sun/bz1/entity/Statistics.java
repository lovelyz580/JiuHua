package sun.bz1.entity;

/**
 * 统计数据
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/25
 */

public class Statistics {

	/**
	 * 维修单ID
	 */
	private String orderid;

	/**
	 * 维修单详情ID
	 */
	private String orderdetailid;

	/**
	 * 维修人员ID
	 */
	private String userid;

	/**
	 * 维修人员名称
	 */
	private String username;

	/**
	 * 产品ID
	 */
	private String goodsid;

	/**
	 * 产品名称
	 */
	private String goodsname;

	/**
	 * 项目ID
	 */
	private String projectid;

	/**
	 * 项目名称
	 */
	private String projectname;

	/**
	 * 维修数量
	 */
	private int orderdetailnumber;

	/**
	 * 维修单价
	 */
	private Double pricemoney;

	/**
	 * 维修总价(维修数量*维修单价)
	 */
	private Double totalmoney;
	
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
	 * 维修次数
	 */
	private Integer servicenumber;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderdetailid() {
		return orderdetailid;
	}

	public void setOrderdetailid(String orderdetailid) {
		this.orderdetailid = orderdetailid;
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

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public int getOrderdetailnumber() {
		return orderdetailnumber;
	}

	public void setOrderdetailnumber(int orderdetailnumber) {
		this.orderdetailnumber = orderdetailnumber;
	}

	public Double getPricemoney() {
		return pricemoney;
	}

	public void setPricemoney(Double pricemoney) {
		this.pricemoney = pricemoney;
	}

	public Double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
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

	public Integer getServicenumber() {
		return servicenumber;
	}

	public void setServicenumber(Integer servicenumber) {
		this.servicenumber = servicenumber;
	}

}
