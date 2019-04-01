package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 客户评价维修人员_评价项_设置表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/17
 */

public class EvaluateServiceSetup {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 评价项ID(WPJX+UUID)
	 */
	private String evaluateservicesetupid;

	/**
	 * 评价项名称
	 */
	private String evaluateservicesetupname;

	/**
	 * 评价项权重
	 */
	private Double evaluateservicesetup;

	/**
	 * 评价项状态(Y:有效/N:无效)
	 */
	private String evaluateservicesetupstate;

	/**
	 * 评价项创建人ID(YHBG+UUID)
	 */
	private String evaluateservicesetupcreateuserid;

	/**
	 * 评价项创建时间
	 */
	private Date evaluateservicesetupcreatetime;

	/**
	 * 评价项更新人ID(YHBG+UUID)
	 */
	private String evaluateservicesetupupdateuserid;

	/**
	 * 评价项更新时间
	 */
	private Date evaluateservicesetupupdatetime;

	/**
	 * 评价得分（显示实体星星）
	 */
	private Integer evaluateservicesetupscore;

	/**
	 * 评价得分（显示空星星）
	 */
	private Integer evaluateservicesetuplossscore;

	/**
	 * 评价项备注
	 */
	private String evaluateservicesetupremark;

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

	public String getEvaluateservicesetupid() {
		return evaluateservicesetupid;
	}

	public void setEvaluateservicesetupid(String evaluateservicesetupid) {
		this.evaluateservicesetupid = evaluateservicesetupid == null ? null : evaluateservicesetupid.trim();
	}

	public String getEvaluateservicesetupname() {
		return evaluateservicesetupname;
	}

	public void setEvaluateservicesetupname(String evaluateservicesetupname) {
		this.evaluateservicesetupname = evaluateservicesetupname == null ? null : evaluateservicesetupname.trim();
	}

	public Double getEvaluateservicesetup() {
		return evaluateservicesetup;
	}

	public void setEvaluateservicesetup(Double evaluateservicesetup) {
		this.evaluateservicesetup = evaluateservicesetup;
	}

	public String getEvaluateservicesetupstate() {
		return evaluateservicesetupstate;
	}

	public void setEvaluateservicesetupstate(String evaluateservicesetupstate) {
		this.evaluateservicesetupstate = evaluateservicesetupstate == null ? null : evaluateservicesetupstate.trim();
	}

	public String getEvaluateservicesetupcreateuserid() {
		return evaluateservicesetupcreateuserid;
	}

	public void setEvaluateservicesetupcreateuserid(String evaluateservicesetupcreateuserid) {
		this.evaluateservicesetupcreateuserid = evaluateservicesetupcreateuserid == null ? null
				: evaluateservicesetupcreateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getEvaluateservicesetupcreatetime() {
		return evaluateservicesetupcreatetime;
	}

	public void setEvaluateservicesetupcreatetime(Date evaluateservicesetupcreatetime) {
		this.evaluateservicesetupcreatetime = evaluateservicesetupcreatetime;
	}

	public String getEvaluateservicesetupupdateuserid() {
		return evaluateservicesetupupdateuserid;
	}

	public void setEvaluateservicesetupupdateuserid(String evaluateservicesetupupdateuserid) {
		this.evaluateservicesetupupdateuserid = evaluateservicesetupupdateuserid == null ? null
				: evaluateservicesetupupdateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getEvaluateservicesetupupdatetime() {
		return evaluateservicesetupupdatetime;
	}

	public void setEvaluateservicesetupupdatetime(Date evaluateservicesetupupdatetime) {
		this.evaluateservicesetupupdatetime = evaluateservicesetupupdatetime;
	}

	public String getEvaluateservicesetupremark() {
		return evaluateservicesetupremark;
	}

	public void setEvaluateservicesetupremark(String evaluateservicesetupremark) {
		this.evaluateservicesetupremark = evaluateservicesetupremark == null ? null : evaluateservicesetupremark.trim();
	}

	public Integer getEvaluateservicesetupscore() {
		return evaluateservicesetupscore;
	}

	public void setEvaluateservicesetupscore(Integer evaluateservicesetupscore) {
		this.evaluateservicesetupscore = evaluateservicesetupscore;
	}

	public Integer getEvaluateservicesetuplossscore() {
		return evaluateservicesetuplossscore;
	}

	public void setEvaluateservicesetuplossscore(Integer evaluateservicesetuplossscore) {
		this.evaluateservicesetuplossscore = evaluateservicesetuplossscore;
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