package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 接单规模表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class Scale {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 接单规模ID(JDGM+UUID)
	 */
	private String scaleid;

	/**
	 * 接单规模金额初始值(小数值)
	 */
	private Double scalemin;
	
	/**
	 * 接单规模金额结束值(大数值)
	 */
	private Double scalemax;

	/**
	 * 接单规模状态(Y:有效/N:无效)
	 */
	private String scalestate;

	/**
	 * 接单规模创建人ID(YHBG+UUID)
	 */
	private String scalecreateuserid;

	/**
	 * 接单规模创建时间
	 */
	private Date scalecreatetime;

	/**
	 * 接单规模更新人ID(YHBG+UUID)
	 */
	private String scaleupdateuserid;

	/**
	 * 接单规模更新时间
	 */
	private Date scaleupdatetime;

	/**
	 * 接单规模备注
	 */
	private String scaleremark;

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
	 * 接单规模金额最小值-最大值
	 */
	private String scaleminmax;
	
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

	public String getScaleid() {
		return scaleid;
	}

	public void setScaleid(String scaleid) {
		this.scaleid = scaleid == null ? null : scaleid.trim();
	}

	public Double getScalemin() {
		return scalemin;
	}

	public void setScalemin(Double scalemin) {
		this.scalemin = scalemin;
	}

	public Double getScalemax() {
		return scalemax;
	}

	public void setScalemax(Double scalemax) {
		this.scalemax = scalemax;
	}

	public String getScalestate() {
		return scalestate;
	}

	public void setScalestate(String scalestate) {
		this.scalestate = scalestate == null ? null : scalestate.trim();
	}

	public String getScalecreateuserid() {
		return scalecreateuserid;
	}

	public void setScalecreateuserid(String scalecreateuserid) {
		this.scalecreateuserid = scalecreateuserid == null ? null : scalecreateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getScalecreatetime() {
		return scalecreatetime;
	}

	public void setScalecreatetime(Date scalecreatetime) {
		this.scalecreatetime = scalecreatetime;
	}

	public String getScaleupdateuserid() {
		return scaleupdateuserid;
	}

	public void setScaleupdateuserid(String scaleupdateuserid) {
		this.scaleupdateuserid = scaleupdateuserid == null ? null : scaleupdateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getScaleupdatetime() {
		return scaleupdatetime;
	}

	public void setScaleupdatetime(Date scaleupdatetime) {
		this.scaleupdatetime = scaleupdatetime;
	}

	public String getScaleremark() {
		return scaleremark;
	}

	public void setScaleremark(String scaleremark) {
		this.scaleremark = scaleremark == null ? null : scaleremark.trim();
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

	public String getScaleminmax() {
		return scaleminmax;
	}

	public void setScaleminmax(String scaleminmax) {
		this.scaleminmax = scaleminmax;
	}
	

}