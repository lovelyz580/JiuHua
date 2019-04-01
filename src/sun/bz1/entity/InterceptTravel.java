package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 差旅费(每公里)拦标价表
 * 
 * 实体类
 * 
 * @author WJF on 2018/10/09
 */

public class InterceptTravel {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 差旅费(每公里)拦标价类型(WX:维修工/AZ:安装队)
	 */
    private String intercepttraveltype;

    /**
	 * 差旅费(每公里)推荐价(派单时，平台按推荐价收入金额)
	 */
    private Double intercepttravelmoney;

    /**
	 * 差旅费(每公里)拦标价(用户维护维修单价时不能超过拦标价)
	 */
    private Double intercepttravelrealmoney;
    
    /**
	 * 差旅费(每公里)拦标价状态(Y:有效/N:无效)
	 */
	private String intercepttravelstate;

    /**
	 * 差旅费(每公里)拦标价更新人ID(YHBG+UUID)
	 */
    private String intercepttravelupdateuserid;

    /**
	 * 差旅费(每公里)拦标价更新时间
	 */
    private Date intercepttravelupdatetime;

    /**
	 * 差旅费(每公里)拦标价备注
	 */
    private String intercepttravelremark;
    
    // 版本信息
 	/**
 	 * 版本号
 	 */
 	private String version;

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

    public String getIntercepttraveltype() {
        return intercepttraveltype;
    }

    public void setIntercepttraveltype(String intercepttraveltype) {
        this.intercepttraveltype = intercepttraveltype == null ? null : intercepttraveltype.trim();
    }

    public Double getIntercepttravelmoney() {
        return intercepttravelmoney;
    }

    public void setIntercepttravelmoney(Double intercepttravelmoney) {
        this.intercepttravelmoney = intercepttravelmoney;
    }

    public Double getIntercepttravelrealmoney() {
        return intercepttravelrealmoney;
    }

    public void setIntercepttravelrealmoney(Double intercepttravelrealmoney) {
        this.intercepttravelrealmoney = intercepttravelrealmoney;
    }

    public String getIntercepttravelstate() {
		return intercepttravelstate;
	}

	public void setIntercepttravelstate(String intercepttravelstate) {
		this.intercepttravelstate = intercepttravelstate;
	}

	public String getIntercepttravelupdateuserid() {
        return intercepttravelupdateuserid;
    }

    public void setIntercepttravelupdateuserid(String intercepttravelupdateuserid) {
        this.intercepttravelupdateuserid = intercepttravelupdateuserid == null ? null : intercepttravelupdateuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getIntercepttravelupdatetime() {
        return intercepttravelupdatetime;
    }

    public void setIntercepttravelupdatetime(Date intercepttravelupdatetime) {
        this.intercepttravelupdatetime = intercepttravelupdatetime;
    }

    public String getIntercepttravelremark() {
        return intercepttravelremark;
    }

    public void setIntercepttravelremark(String intercepttravelremark) {
        this.intercepttravelremark = intercepttravelremark == null ? null : intercepttravelremark.trim();
    }

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Integer> getIdlist() {
		return idlist;
	}

	public void setIdlist(List<Integer> idlist) {
		this.idlist = idlist;
	}
    
}