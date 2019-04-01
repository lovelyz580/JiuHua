package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修验收表(验收成功或失败时在该表中添加数据)
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class ApplyCheck {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 维修验收ID(WXYS+UUID)
	 */
    private String applycheckid;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 维修验收状态(SQYS:申请验收)(HG:合格)((派单模式)GHWX:更换维修人员)((抢单模式)WTPD:委托平台派单/PTXT:平台协调)(ZDYS:自动验收)
	 */
    private String applycheckstate;

    /**
	 * 维修验收图片路径
	 */
    private String applycheckimage;

    /**
	 * 维修验收视频路径
	 */
    private String applycheckvideo;
    
    /**
	 * 维修验收申请时间
	 */
    private Date applycheckapplytime;
    
    /**
	 * 维修自动验收时间(维修申请14天后，客户不验收，则平台自动验收)
	 */
    private Date applycheckautomatictime;

    /**
	 * 维修验收最终结算总价(合格、平台协调状态时，才会添加这个字段的数据)
	 */
    private Double applycheckmoney;
    
    /**
	 * 维修验收平台协调服务最终结算总价(平台协调状态时，才会添加这个字段的数据)
	 */
    private Double applycheckbackmoney;
    
    /**
	 * 维修验收平台协调最终过错方(WX:维修工/AZ:安装队)(平台协调状态时，才会添加这个字段的数据)
	 */
    private String applycheckbackfault;

    /**
	 * 维修验收平台协调标题(平台协调状态时，才会添加这个字段的数据)
	 */
    private String applychecktitle;

    /**
	 * 维修验收平台协调内容(平台协调状态时，才会添加这个字段的数据)
	 */
    private String applycheckcontent;

    /**
	 * 维修验收人ID(YHBG+UUID)
	 */
    private String applycheckuserid;

    /**
	 * 维修验收时间
	 */
    private Date applychecktime;

    /**
	 * 维修平台操作人ID(YHBG+UUID)(平台协调状态并且平台人员强制操作时，才会添加这个字段的数据)
	 */
    private String applycheckbackuserid;

    /**
	 * 维修平台操作时间(平台协调状态并且平台人员强制操作时，才会添加这个字段的数据)
	 */
    private Date applycheckbacktime;

    /**
	 * 维修验收备注
	 */
    private String applycheckremark;
    
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

    public String getApplycheckid() {
        return applycheckid;
    }

    public void setApplycheckid(String applycheckid) {
        this.applycheckid = applycheckid == null ? null : applycheckid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getApplycheckstate() {
        return applycheckstate;
    }

    public void setApplycheckstate(String applycheckstate) {
        this.applycheckstate = applycheckstate == null ? null : applycheckstate.trim();
    }

    public String getApplycheckimage() {
        return applycheckimage;
    }

    public void setApplycheckimage(String applycheckimage) {
        this.applycheckimage = applycheckimage == null ? null : applycheckimage.trim();
    }

    public String getApplycheckvideo() {
        return applycheckvideo;
    }

    public void setApplycheckvideo(String applycheckvideo) {
        this.applycheckvideo = applycheckvideo == null ? null : applycheckvideo.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getApplycheckapplytime() {
		return applycheckapplytime;
	}

	public void setApplycheckapplytime(Date applycheckapplytime) {
		this.applycheckapplytime = applycheckapplytime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getApplycheckautomatictime() {
		return applycheckautomatictime;
	}

	public void setApplycheckautomatictime(Date applycheckautomatictime) {
		this.applycheckautomatictime = applycheckautomatictime;
	}

	public Double getApplycheckmoney() {
        return applycheckmoney;
    }

    public void setApplycheckmoney(Double applycheckmoney) {
        this.applycheckmoney = applycheckmoney;
    }

    public Double getApplycheckbackmoney() {
		return applycheckbackmoney;
	}

	public void setApplycheckbackmoney(Double applycheckbackmoney) {
		this.applycheckbackmoney = applycheckbackmoney;
	}

	public String getApplycheckbackfault() {
		return applycheckbackfault;
	}

	public void setApplycheckbackfault(String applycheckbackfault) {
		this.applycheckbackfault = applycheckbackfault;
	}

	public String getApplychecktitle() {
        return applychecktitle;
    }

    public void setApplychecktitle(String applychecktitle) {
        this.applychecktitle = applychecktitle == null ? null : applychecktitle.trim();
    }

    public String getApplycheckcontent() {
        return applycheckcontent;
    }

    public void setApplycheckcontent(String applycheckcontent) {
        this.applycheckcontent = applycheckcontent == null ? null : applycheckcontent.trim();
    }

    public String getApplycheckuserid() {
        return applycheckuserid;
    }

    public void setApplycheckuserid(String applycheckuserid) {
        this.applycheckuserid = applycheckuserid == null ? null : applycheckuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getApplychecktime() {
        return applychecktime;
    }

    public void setApplychecktime(Date applychecktime) {
        this.applychecktime = applychecktime;
    }

    public String getApplycheckbackuserid() {
        return applycheckbackuserid;
    }

    public void setApplycheckbackuserid(String applycheckbackuserid) {
        this.applycheckbackuserid = applycheckbackuserid == null ? null : applycheckbackuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getApplycheckbacktime() {
        return applycheckbacktime;
    }

    public void setApplycheckbacktime(Date applycheckbacktime) {
        this.applycheckbacktime = applycheckbacktime;
    }

    public String getApplycheckremark() {
        return applycheckremark;
    }

    public void setApplycheckremark(String applycheckremark) {
        this.applycheckremark = applycheckremark == null ? null : applycheckremark.trim();
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