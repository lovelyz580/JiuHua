package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户_维修工、安装队_支出_金额表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/19
 */

public class UserServiceExpenditureMoney {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 维修人员支出金额ID(WXZC+UUID)
	 */
    private String userserviceexpendituremoneyid;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 金额用户ID(YHBG+UUID)
	 */
    private String userserviceexpendituremoneyuserid;

    /**
	 * 金额(可正可负)
	 */
    private Double userserviceexpendituremoney;

    /**
	 * 金额来源(WXTX:维修人员提现(维修人员支出金额))
	 */
    private String userserviceexpendituremoneysource;

    /**
	 * 金额创建时间
	 */
    private Date userserviceexpendituremoneycreatetime;

    /**
	 * 金额其他字段1
	 */
    private String userserviceexpendituremoneyother1;

    /**
	 * 金额其他字段2
	 */
    private String userserviceexpendituremoneyother2;

    /**
	 * 金额备注
	 */
    private String userserviceexpendituremoneyremark;
    
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

    public String getUserserviceexpendituremoneyid() {
        return userserviceexpendituremoneyid;
    }

    public void setUserserviceexpendituremoneyid(String userserviceexpendituremoneyid) {
        this.userserviceexpendituremoneyid = userserviceexpendituremoneyid == null ? null : userserviceexpendituremoneyid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getUserserviceexpendituremoneyuserid() {
        return userserviceexpendituremoneyuserid;
    }

    public void setUserserviceexpendituremoneyuserid(String userserviceexpendituremoneyuserid) {
        this.userserviceexpendituremoneyuserid = userserviceexpendituremoneyuserid == null ? null : userserviceexpendituremoneyuserid.trim();
    }

    public Double getUserserviceexpendituremoney() {
        return userserviceexpendituremoney;
    }

    public void setUserserviceexpendituremoney(Double userserviceexpendituremoney) {
        this.userserviceexpendituremoney = userserviceexpendituremoney;
    }

    public String getUserserviceexpendituremoneysource() {
        return userserviceexpendituremoneysource;
    }

    public void setUserserviceexpendituremoneysource(String userserviceexpendituremoneysource) {
        this.userserviceexpendituremoneysource = userserviceexpendituremoneysource == null ? null : userserviceexpendituremoneysource.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getUserserviceexpendituremoneycreatetime() {
        return userserviceexpendituremoneycreatetime;
    }

    public void setUserserviceexpendituremoneycreatetime(Date userserviceexpendituremoneycreatetime) {
        this.userserviceexpendituremoneycreatetime = userserviceexpendituremoneycreatetime;
    }

    public String getUserserviceexpendituremoneyother1() {
        return userserviceexpendituremoneyother1;
    }

    public void setUserserviceexpendituremoneyother1(String userserviceexpendituremoneyother1) {
        this.userserviceexpendituremoneyother1 = userserviceexpendituremoneyother1 == null ? null : userserviceexpendituremoneyother1.trim();
    }

    public String getUserserviceexpendituremoneyother2() {
        return userserviceexpendituremoneyother2;
    }

    public void setUserserviceexpendituremoneyother2(String userserviceexpendituremoneyother2) {
        this.userserviceexpendituremoneyother2 = userserviceexpendituremoneyother2 == null ? null : userserviceexpendituremoneyother2.trim();
    }

    public String getUserserviceexpendituremoneyremark() {
        return userserviceexpendituremoneyremark;
    }

    public void setUserserviceexpendituremoneyremark(String userserviceexpendituremoneyremark) {
        this.userserviceexpendituremoneyremark = userserviceexpendituremoneyremark == null ? null : userserviceexpendituremoneyremark.trim();
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