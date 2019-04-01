package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 建筑类型表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class BuildingType {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 建筑类型ID(JZLX+UUID)
	 */
	private String buildingtypeid;

	/**
	 * 建筑类型名称
	 */
	private String buildingtypename;

	/**
	 * 建筑类型状态(Y:有效/N:无效)
	 */
	private String buildingtypestate;

	/**
	 * 建筑类型系数
	 */
	private Double buildingtypecoefficient;
	
	/**
	 * 建筑类型创建人ID(YHBG+UUID)
	 */
	private String buildingtypecreateuserid;

	/**
	 * 建筑类型创建时间
	 */
	private Date buildingtypecreatetime;

	/**
	 * 建筑类型更新人ID(YHBG+UUID)
	 */
	private String buildingtypeupdateuserid;

	/**
	 * 建筑类型更新时间
	 */
	private Date buildingtypeupdatetime;

	/**
	 * 建筑类型备注
	 */
	private String buildingtyperemark;

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

	public String getBuildingtypeid() {
		return buildingtypeid;
	}

	public void setBuildingtypeid(String buildingtypeid) {
		this.buildingtypeid = buildingtypeid == null ? null : buildingtypeid.trim();
	}

	public String getBuildingtypename() {
		return buildingtypename;
	}

	public void setBuildingtypename(String buildingtypename) {
		this.buildingtypename = buildingtypename == null ? null : buildingtypename.trim();
	}

	public String getBuildingtypestate() {
		return buildingtypestate;
	}

	public void setBuildingtypestate(String buildingtypestate) {
		this.buildingtypestate = buildingtypestate == null ? null : buildingtypestate.trim();
	}

	public String getBuildingtypecreateuserid() {
		return buildingtypecreateuserid;
	}

	public void setBuildingtypecreateuserid(String buildingtypecreateuserid) {
		this.buildingtypecreateuserid = buildingtypecreateuserid == null ? null : buildingtypecreateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
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
		this.buildingtypeupdateuserid = buildingtypeupdateuserid == null ? null : buildingtypeupdateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
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
		this.buildingtyperemark = buildingtyperemark == null ? null : buildingtyperemark.trim();
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

	public Double getBuildingtypecoefficient() {
		return buildingtypecoefficient;
	}

	public void setBuildingtypecoefficient(Double buildingtypecoefficient) {
		this.buildingtypecoefficient = buildingtypecoefficient;
	}
	
}