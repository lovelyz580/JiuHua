package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修人员评价客户_评价项_设置表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/17
 */

public class EvaluateCustomerSetup {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 评价项ID(KPJX+UUID)
	 */
	private String evaluatecustomersetupid;

	/**
	 * 评价项名称
	 */
	private String evaluatecustomersetupname;

	/**
	 * 评价项权重
	 */
	private Double evaluatecustomersetup;

	/**
	 * 评价项状态(Y:有效/N:无效)
	 */
	private String evaluatecustomersetupstate;

	/**
	 * 评价项创建人ID(YHBG+UUID)
	 */
	private String evaluatecustomersetupcreateuserid;

	/**
	 * 评价项创建时间
	 */
	private Date evaluatecustomersetupcreatetime;

	/**
	 * 评价项更新人ID(YHBG+UUID)
	 */
	private String evaluatecustomersetupupdateuserid;

	/**
	 * 评价项更新时间
	 */
	private Date evaluatecustomersetupupdatetime;

	/**
	 * 评价项备注
	 */
	private String evaluatecustomersetupremark;

	/**
	 * 得分用于展示实体星星
	 */
	private int evaluatecustomersetupscore;

	/**
	 * 得分用于展示空的星星
	 */
	private int evaluatecustomersetuplossscore;
	
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

	public String getEvaluatecustomersetupid() {
		return evaluatecustomersetupid;
	}

	public void setEvaluatecustomersetupid(String evaluatecustomersetupid) {
		this.evaluatecustomersetupid = evaluatecustomersetupid == null ? null : evaluatecustomersetupid.trim();
	}

	public String getEvaluatecustomersetupname() {
		return evaluatecustomersetupname;
	}

	public void setEvaluatecustomersetupname(String evaluatecustomersetupname) {
		this.evaluatecustomersetupname = evaluatecustomersetupname == null ? null : evaluatecustomersetupname.trim();
	}

	public Double getEvaluatecustomersetup() {
		return evaluatecustomersetup;
	}

	public void setEvaluatecustomersetup(Double evaluatecustomersetup) {
		this.evaluatecustomersetup = evaluatecustomersetup;
	}

	public String getEvaluatecustomersetupstate() {
		return evaluatecustomersetupstate;
	}

	public void setEvaluatecustomersetupstate(String evaluatecustomersetupstate) {
		this.evaluatecustomersetupstate = evaluatecustomersetupstate == null ? null : evaluatecustomersetupstate.trim();
	}

	public String getEvaluatecustomersetupcreateuserid() {
		return evaluatecustomersetupcreateuserid;
	}

	public void setEvaluatecustomersetupcreateuserid(String evaluatecustomersetupcreateuserid) {
		this.evaluatecustomersetupcreateuserid = evaluatecustomersetupcreateuserid == null ? null
				: evaluatecustomersetupcreateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getEvaluatecustomersetupcreatetime() {
		return evaluatecustomersetupcreatetime;
	}

	public void setEvaluatecustomersetupcreatetime(Date evaluatecustomersetupcreatetime) {
		this.evaluatecustomersetupcreatetime = evaluatecustomersetupcreatetime;
	}

	public String getEvaluatecustomersetupupdateuserid() {
		return evaluatecustomersetupupdateuserid;
	}

	public void setEvaluatecustomersetupupdateuserid(String evaluatecustomersetupupdateuserid) {
		this.evaluatecustomersetupupdateuserid = evaluatecustomersetupupdateuserid == null ? null
				: evaluatecustomersetupupdateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getEvaluatecustomersetupupdatetime() {
		return evaluatecustomersetupupdatetime;
	}

	public void setEvaluatecustomersetupupdatetime(Date evaluatecustomersetupupdatetime) {
		this.evaluatecustomersetupupdatetime = evaluatecustomersetupupdatetime;
	}

	public String getEvaluatecustomersetupremark() {
		return evaluatecustomersetupremark;
	}

	public void setEvaluatecustomersetupremark(String evaluatecustomersetupremark) {
		this.evaluatecustomersetupremark = evaluatecustomersetupremark == null ? null
				: evaluatecustomersetupremark.trim();
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

	public int getEvaluatecustomersetupscore() {
		return evaluatecustomersetupscore;
	}

	public void setEvaluatecustomersetupscore(int evaluatecustomersetupscore) {
		this.evaluatecustomersetupscore = evaluatecustomersetupscore;
	}

	public int getEvaluatecustomersetuplossscore() {
		return evaluatecustomersetuplossscore;
	}

	public void setEvaluatecustomersetuplossscore(int evaluatecustomersetuplossscore) {
		this.evaluatecustomersetuplossscore = evaluatecustomersetuplossscore;
	}
}