package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 拦标价表、产品表、项目表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class InterceptAndGoodsAndProject {

	/**
	 * 拦标价表-序号(自增主键)
	 */
	private Integer id;

	/**
	 * 拦标价表-拦标价ID(LBJB+UUID)
	 */
	private String interceptid;

	/**
	 * 拦标价表-产品ID(CPBG+UUID)
	 */
	private String goodsid;

	/**
	 * 拦标价表-项目ID(XMBG+UUID)
	 */
	private String projectid;

	/**
	 * 拦标价表-拦标价类型(WX:维修工/AZ:安装队)
	 */
	private String intercepttype;

	/**
	 * 拦标价表-推荐价
	 */
	private Double interceptmoney;
	
	/**
	 * 拦标价表-拦标价
	 */
	private Double interceptrealmoney;

	/**
	 * 拦标价表-拦标价状态(Y:有效/N:无效)
	 */
	private String interceptstate;

	/**
	 * 拦标价表-拦标价创建人ID(YHBG+UUID)
	 */
	private String interceptcreateuserid;

	/**
	 * 拦标价表-拦标价创建时间
	 */
	private Date interceptcreatetime;

	/**
	 * 拦标价表-拦标价更新人ID(YHBG+UUID)
	 */
	private String interceptupdateuserid;

	/**
	 * 拦标价表-拦标价更新时间
	 */
	private Date interceptupdatetime;

	/**
	 * 拦标价表-拦标价备注
	 */
	private String interceptremark;

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
	 * 根据拦标价表的拦标价创建时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectinterceptcreatestarttime;

	/**
	 * 根据拦标价表的拦标价创建时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectinterceptcreateendtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInterceptid() {
		return interceptid;
	}

	public void setInterceptid(String interceptid) {
		this.interceptid = interceptid;
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

	public String getIntercepttype() {
		return intercepttype;
	}

	public void setIntercepttype(String intercepttype) {
		this.intercepttype = intercepttype;
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

	public String getInterceptstate() {
		return interceptstate;
	}

	public void setInterceptstate(String interceptstate) {
		this.interceptstate = interceptstate;
	}

	public String getInterceptcreateuserid() {
		return interceptcreateuserid;
	}

	public void setInterceptcreateuserid(String interceptcreateuserid) {
		this.interceptcreateuserid = interceptcreateuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getInterceptcreatetime() {
		return interceptcreatetime;
	}

	public void setInterceptcreatetime(Date interceptcreatetime) {
		this.interceptcreatetime = interceptcreatetime;
	}

	public String getInterceptupdateuserid() {
		return interceptupdateuserid;
	}

	public void setInterceptupdateuserid(String interceptupdateuserid) {
		this.interceptupdateuserid = interceptupdateuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getInterceptupdatetime() {
		return interceptupdatetime;
	}

	public void setInterceptupdatetime(Date interceptupdatetime) {
		this.interceptupdatetime = interceptupdatetime;
	}

	public String getInterceptremark() {
		return interceptremark;
	}

	public void setInterceptremark(String interceptremark) {
		this.interceptremark = interceptremark;
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

	public String getSelectinterceptcreatestarttime() {
		return selectinterceptcreatestarttime;
	}

	public void setSelectinterceptcreatestarttime(String selectinterceptcreatestarttime) {
		this.selectinterceptcreatestarttime = selectinterceptcreatestarttime;
	}

	public String getSelectinterceptcreateendtime() {
		return selectinterceptcreateendtime;
	}

	public void setSelectinterceptcreateendtime(String selectinterceptcreateendtime) {
		this.selectinterceptcreateendtime = selectinterceptcreateendtime;
	}
	
}
