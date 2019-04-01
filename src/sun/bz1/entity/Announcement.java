package sun.bz1.entity;

import java.util.List;

/**
 * 维修公告表(发布公告时在该表中添加数据，公告结束时修改公告状态)
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/07
 */

public class Announcement {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 维修公告ID(WXGG+UUID)
	 */
    private String announcementid;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 维修公告状态(Y:进行中/N:已完成)
	 */
    private String announcementstate;

    /**
	 * 维修公告备注
	 */
    private String announcementremark;
    
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

    public String getAnnouncementid() {
        return announcementid;
    }

    public void setAnnouncementid(String announcementid) {
        this.announcementid = announcementid == null ? null : announcementid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getAnnouncementstate() {
        return announcementstate;
    }

    public void setAnnouncementstate(String announcementstate) {
        this.announcementstate = announcementstate == null ? null : announcementstate.trim();
    }

    public String getAnnouncementremark() {
        return announcementremark;
    }

    public void setAnnouncementremark(String announcementremark) {
        this.announcementremark = announcementremark == null ? null : announcementremark.trim();
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