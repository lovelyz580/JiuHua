package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 拦标价表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/05
 */

public class Intercept {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 拦标价ID(LBJB+UUID)
	 */
	private String interceptid;

	/**
	 * 产品ID(CPBG+UUID)
	 */
	private String goodsid;

	/**
	 * 项目ID(XMBG+UUID)
	 */
	private String projectid;

	/**
	 * 拦标价类型(WX:维修工/AZ:安装队)
	 */
	private String intercepttype;

	/**
	 * 推荐价
	 */
	private Double interceptmoney;
	
	/**
	 * 拦标价
	 */
	private Double interceptrealmoney;

	/**
	 * 拦标价状态(Y:有效/N:无效)
	 */
	private String interceptstate;

	/**
	 * 拦标价创建人ID(YHBG+UUID)
	 */
	private String interceptcreateuserid;

	/**
	 * 拦标价创建时间
	 */
	private Date interceptcreatetime;

	/**
	 * 拦标价更新人ID(YHBG+UUID)
	 */
	private String interceptupdateuserid;

	/**
	 * 拦标价更新时间
	 */
	private Date interceptupdatetime;

	/**
	 * 拦标价备注
	 */
	private String interceptremark;

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

	public String getInterceptid() {
		return interceptid;
	}

	public void setInterceptid(String interceptid) {
		this.interceptid = interceptid == null ? null : interceptid.trim();
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

	public String getIntercepttype() {
		return intercepttype;
	}

	public void setIntercepttype(String intercepttype) {
		this.intercepttype = intercepttype == null ? null : intercepttype.trim();
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
		this.interceptstate = interceptstate == null ? null : interceptstate.trim();
	}

	public String getInterceptcreateuserid() {
		return interceptcreateuserid;
	}

	public void setInterceptcreateuserid(String interceptcreateuserid) {
		this.interceptcreateuserid = interceptcreateuserid == null ? null : interceptcreateuserid.trim();
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
		this.interceptupdateuserid = interceptupdateuserid == null ? null : interceptupdateuserid.trim();
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
		this.interceptremark = interceptremark == null ? null : interceptremark.trim();
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

}