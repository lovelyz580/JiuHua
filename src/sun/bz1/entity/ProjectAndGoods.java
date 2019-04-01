package sun.bz1.entity;

/**
 * 所有项目和维修工对单价的维护
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/30
 */

public class ProjectAndGoods {

	/**
	 * 项目id
	 */
	private String projectid;

	/**
	 * 产品id
	 */
	private String goodsid;

	/**
	 * 单价id
	 */
	private String priceid;

	/**
	 * 项目名称
	 */
	private String projectname;

	/**
	 * 产品名称
	 */
	private String goodsname;

	/**
	 * 产品状态
	 */
	private String goodstype;

	/**
	 * 项目状态
	 */
	private String projecttype;

	/**
	 * 单价
	 */
	private Double pricemoney;

	/**
	 * 拦标价推荐价格
	 */
	private Double interceptmoney;

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

	public Double getInterceptmoney() {
		return interceptmoney;
	}

	public void setInterceptmoney(Double interceptmoney) {
		this.interceptmoney = interceptmoney;
	}

	public Double getPricemoney() {
		return pricemoney;
	}

	public void setPricemoney(Double pricemoney) {
		this.pricemoney = pricemoney;
	}

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getPriceid() {
		return priceid;
	}

	public void setPriceid(String priceid) {
		this.priceid = priceid;
	}

}
