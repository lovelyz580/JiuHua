package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 客户评价维修人员表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/05
 */

public class EvaluateService {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 评价ID(PJWX+UUID)
	 */
    private String evaluateserviceid;
    
    /**
   	 * 维修人员ID(被评价用户)(YHBG+UUID)
   	 */
    private String serviceuserid;

    /**
	 * 客户ID(评价用户)(YHBG+UUID)
	 */
    private String customeruserid;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 总得分
	 */
    private Double evaluateservicescore;

    /**
	 * 评价意见
	 */
    private String evaluateserviceopinion;

    /**
	 * 评价创建时间
	 */
    private Date evaluateservicecreatetime;

    /**
	 * 评价备注
	 */
    private String evaluateserviceremark;

	/**
	 * 评价项ID(WPJX+UUID)多个用,隔开
	 */
	private String evaluateservicesetupid;

	/**
	 * 评价项名称多个用,隔开
	 */
	private String evaluateservicesetupname;

	/**
	 * 得分用于展示实体星星多个用,隔开
	 */
	private String evaluateservicesetupscore;


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
	 * 根据评价创建时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectstarttime;
	
	/**
	 * 根据评价创建时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectendtime;
	
  	/**
  	 * 根据idlist删除信息时使用
  	 */
  	private List<Integer> idlist;
  	
  	/**
	 * 根据uuidlist删除信息时使用
	 */
	private List<String> uuidlist;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvaluateserviceid() {
		return evaluateserviceid;
	}

	public void setEvaluateserviceid(String evaluateserviceid) {
		this.evaluateserviceid = evaluateserviceid;
	}

	public String getServiceuserid() {
		return serviceuserid;
	}

	public void setServiceuserid(String serviceuserid) {
		this.serviceuserid = serviceuserid;
	}

	public String getCustomeruserid() {
		return customeruserid;
	}

	public void setCustomeruserid(String customeruserid) {
		this.customeruserid = customeruserid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Double getEvaluateservicescore() {
		return evaluateservicescore;
	}

	public void setEvaluateservicescore(Double evaluateservicescore) {
		this.evaluateservicescore = evaluateservicescore;
	}

	public String getEvaluateserviceopinion() {
		return evaluateserviceopinion;
	}

	public void setEvaluateserviceopinion(String evaluateserviceopinion) {
		this.evaluateserviceopinion = evaluateserviceopinion;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getEvaluateservicecreatetime() {
		return evaluateservicecreatetime;
	}

	public void setEvaluateservicecreatetime(Date evaluateservicecreatetime) {
		this.evaluateservicecreatetime = evaluateservicecreatetime;
	}

	public String getEvaluateserviceremark() {
		return evaluateserviceremark;
	}

	public void setEvaluateserviceremark(String evaluateserviceremark) {
		this.evaluateserviceremark = evaluateserviceremark;
	}

	public String getEvaluateservicesetupid() {
		return evaluateservicesetupid;
	}

	public void setEvaluateservicesetupid(String evaluateservicesetupid) {
		this.evaluateservicesetupid = evaluateservicesetupid;
	}

	public String getEvaluateservicesetupname() {
		return evaluateservicesetupname;
	}

	public void setEvaluateservicesetupname(String evaluateservicesetupname) {
		this.evaluateservicesetupname = evaluateservicesetupname;
	}

	public String getEvaluateservicesetupscore() {
		return evaluateservicesetupscore;
	}

	public void setEvaluateservicesetupscore(String evaluateservicesetupscore) {
		this.evaluateservicesetupscore = evaluateservicesetupscore;
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
	
	public String getSelectstarttime() {
		return selectstarttime;
	}

	public void setSelectstarttime(String selectstarttime) {
		this.selectstarttime = selectstarttime;
	}

	public String getSelectendtime() {
		return selectendtime;
	}

	public void setSelectendtime(String selectendtime) {
		this.selectendtime = selectendtime;
	}

	public List<Integer> getIdlist() {
		return idlist;
	}

	public void setIdlist(List<Integer> idlist) {
		this.idlist = idlist;
	}

	public List<String> getUuidlist() {
		return uuidlist;
	}

	public void setUuidlist(List<String> uuidlist) {
		this.uuidlist = uuidlist;
	}

}