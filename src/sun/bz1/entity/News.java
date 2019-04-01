package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 新闻表
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/19
 */
public class News {

	/**
	 * 维修公告表-序号(自增主键)
	 */
    private Integer id;

	/**
	 * 新闻ID(XWBG+UUID)
	 */
    private String newsid;

	/**
	 * 新闻标题
	 */
    private String newstitle;

	/**
	 * 维修公告表-序号(自增主键)
	 */
    private String newscontent;

	/**
	 * 维修公告表-序号(自增主键)
	 */
    private String newsstate;

	/**
	 * 维修公告表-序号(自增主键)
	 */
    private String newscreateuserid;

	/**
	 * 维修公告表-序号(自增主键)
	 */
    private Date newscreatetime;

	/**
	 * 维修公告表-序号(自增主键)
	 */
    private String newsupdateuserid;

	/**
	 * 维修公告表-序号(自增主键)
	 */
    private Date newsupdatetime;

	/**
	 * 维修公告表-序号(自增主键)
	 */
    private String newsremark;
    
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

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid == null ? null : newsid.trim();
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle == null ? null : newstitle.trim();
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent == null ? null : newscontent.trim();
    }

    public String getNewsstate() {
        return newsstate;
    }

    public void setNewsstate(String newsstate) {
        this.newsstate = newsstate == null ? null : newsstate.trim();
    }

    public String getNewscreateuserid() {
        return newscreateuserid;
    }

    public void setNewscreateuserid(String newscreateuserid) {
        this.newscreateuserid = newscreateuserid == null ? null : newscreateuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getNewscreatetime() {
        return newscreatetime;
    }

    public void setNewscreatetime(Date newscreatetime) {
        this.newscreatetime = newscreatetime;
    }

    public String getNewsupdateuserid() {
        return newsupdateuserid;
    }

    public void setNewsupdateuserid(String newsupdateuserid) {
        this.newsupdateuserid = newsupdateuserid == null ? null : newsupdateuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getNewsupdatetime() {
        return newsupdatetime;
    }

    public void setNewsupdatetime(Date newsupdatetime) {
        this.newsupdatetime = newsupdatetime;
    }

    public String getNewsremark() {
        return newsremark;
    }

    public void setNewsremark(String newsremark) {
        this.newsremark = newsremark == null ? null : newsremark.trim();
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