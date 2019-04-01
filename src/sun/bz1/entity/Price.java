package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单价表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/05
 */

public class Price {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 维修单价ID(WXDJ+UUID)
	 */
	private String priceid;

	/**
	 * 维修单价维护人(维修工、安装队)ID(YHBG+UUID)
	 */
	private String priceupdateuserid;

	/**
	 * 产品ID(CPBG+UUID)
	 */
	private String goodsid;

	/**
	 * 项目ID(XMBG+UUID)
	 */
	private String projectid;

	/**
	 * 维修单价类型(WX:维修工/AZ:安装队)
	 */
	private String pricetype;

	/**
	 * 项目类型(WX:维修工/AZ:安装队)
	 */
	private String projecttype;

	/**
	 * 维修单价
	 */
	private Double pricemoney;

	/**
	 * 维修单价状态(Y:有效/N:无效)
	 */
	private String pricestate;

	/**
	 * 维修单价创建人ID(YHBG+UUID)
	 */
	private String pricecreateuserid;

	/**
	 * 维修单价创建时间
	 */
	private Date pricecreatetime;

	/**
	 * 维修单价更新时间
	 */
	private Date priceupdatetime;

	/**
	 * 维修单价备注
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

	public String getPriceid() {
		return priceid;
	}

	public void setPriceid(String priceid) {
		this.priceid = priceid == null ? null : priceid.trim();
	}

	public String getPriceupdateuserid() {
		return priceupdateuserid;
	}

	public void setPriceupdateuserid(String priceupdateuserid) {
		this.priceupdateuserid = priceupdateuserid == null ? null : priceupdateuserid.trim();
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid == null ? null : goodsid.trim();
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid == null ? null : projectid.trim();
	}

	public String getPricetype() {
		return pricetype;
	}

	public void setPricetype(String pricetype) {
		this.pricetype = pricetype == null ? null : pricetype.trim();
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
		this.pricestate = pricestate == null ? null : pricestate.trim();
	}

	public String getPricecreateuserid() {
		return pricecreateuserid;
	}

	public void setPricecreateuserid(String pricecreateuserid) {
		this.pricecreateuserid = pricecreateuserid == null ? null : pricecreateuserid.trim();
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
		this.priceremark = priceremark == null ? null : priceremark.trim();
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

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
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