package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修人员评价客户表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/05
 */

public class EvaluateCustomer {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 评价ID(PJKH+UUID)
	 */
    private String evaluatecustomerid;

    /**
	 * 客户ID(被评价用户)(YHBG+UUID)
	 */
    private String customeruserid;

    /**
	 * 维修人员ID(评价用户)(YHBG+UUID)
	 */
    private String serviceuserid;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 总得分
	 */
    private Double evaluatecustomerscore;

    /**
	 * 评价意见
	 */
    private String evaluatecustomeropinion;

    /**
	 * 评价创建时间
	 */
    private Date evaluatecustomercreatetime;

    /**
	 * 评价备注
	 */
    private String evaluatecustomerremark;

	/**
	 * 评价项ID(WPJX+UUID)多个用,隔开
	 */
	private String evaluatecustomersetupid;

	/**
	 * 评价项名称多个用,隔开
	 */
	private String evaluatecustomersetupname;

	/**
	 * 得分用于展示实体星星多个用,隔开
	 */
	private String evaluatecustomersetupscore;

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

    public String getEvaluatecustomerid() {
        return evaluatecustomerid;
    }

    public void setEvaluatecustomerid(String evaluatecustomerid) {
        this.evaluatecustomerid = evaluatecustomerid == null ? null : evaluatecustomerid.trim();
    }

    public String getCustomeruserid() {
        return customeruserid;
    }

    public void setCustomeruserid(String customeruserid) {
        this.customeruserid = customeruserid == null ? null : customeruserid.trim();
    }

    public String getServiceuserid() {
        return serviceuserid;
    }

    public void setServiceuserid(String serviceuserid) {
        this.serviceuserid = serviceuserid == null ? null : serviceuserid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Double getEvaluatecustomerscore() {
        return evaluatecustomerscore;
    }

    public void setEvaluatecustomerscore(Double evaluatecustomerscore) {
        this.evaluatecustomerscore = evaluatecustomerscore;
    }

    public String getEvaluatecustomeropinion() {
        return evaluatecustomeropinion;
    }

    public void setEvaluatecustomeropinion(String evaluatecustomeropinion) {
        this.evaluatecustomeropinion = evaluatecustomeropinion == null ? null : evaluatecustomeropinion.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getEvaluatecustomercreatetime() {
        return evaluatecustomercreatetime;
    }

    public void setEvaluatecustomercreatetime(Date evaluatecustomercreatetime) {
        this.evaluatecustomercreatetime = evaluatecustomercreatetime;
    }

    public String getEvaluatecustomerremark() {
        return evaluatecustomerremark;
    }

    public void setEvaluatecustomerremark(String evaluatecustomerremark) {
        this.evaluatecustomerremark = evaluatecustomerremark == null ? null : evaluatecustomerremark.trim();
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

	public String getEvaluatecustomersetupid() {
		return evaluatecustomersetupid;
	}

	public void setEvaluatecustomersetupid(String evaluatecustomersetupid) {
		this.evaluatecustomersetupid = evaluatecustomersetupid;
	}

	public String getEvaluatecustomersetupname() {
		return evaluatecustomersetupname;
	}

	public void setEvaluatecustomersetupname(String evaluatecustomersetupname) {
		this.evaluatecustomersetupname = evaluatecustomersetupname;
	}

	public String getEvaluatecustomersetupscore() {
		return evaluatecustomersetupscore;
	}

	public void setEvaluatecustomersetupscore(String evaluatecustomersetupscore) {
		this.evaluatecustomersetupscore = evaluatecustomersetupscore;
	}
}