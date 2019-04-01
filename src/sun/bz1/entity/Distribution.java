package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单分配记录表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class Distribution {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 分配记录ID(FPJL+UUID)
	 */
    private String distributionid;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;
    
    /**
	 * 被分配人员ID(维修人员)(YHBG+UUID)
	 */
    private String serviceuserid;

    /**
	 * 分配人员ID(YHBG+UUID)
	 */
    private String distributionuserid;

    /**
	 * 分配时间
	 */
    private Date distributiontime;

    /**
	 * 分配备注
	 */
    private String distributionremark;
    
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

	public String getDistributionid() {
		return distributionid;
	}

	public void setDistributionid(String distributionid) {
		this.distributionid = distributionid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getServiceuserid() {
		return serviceuserid;
	}

	public void setServiceuserid(String serviceuserid) {
		this.serviceuserid = serviceuserid;
	}

	public String getDistributionuserid() {
		return distributionuserid;
	}

	public void setDistributionuserid(String distributionuserid) {
		this.distributionuserid = distributionuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getDistributiontime() {
		return distributiontime;
	}

	public void setDistributiontime(Date distributiontime) {
		this.distributiontime = distributiontime;
	}

	public String getDistributionremark() {
		return distributionremark;
	}

	public void setDistributionremark(String distributionremark) {
		this.distributionremark = distributionremark;
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

	public List<String> getUuidlist() {
		return uuidlist;
	}

	public void setUuidlist(List<String> uuidlist) {
		this.uuidlist = uuidlist;
	}

}