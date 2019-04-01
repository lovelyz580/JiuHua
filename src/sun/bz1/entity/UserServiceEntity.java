package sun.bz1.entity;

import java.util.List;

/**
 * 用户_维修工、安装队表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/05
 */

public class UserServiceEntity {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 用户ID(YHBG+UUID)
	 */
    private String userid;

    /**
	 * 接单区域接单区域名称(省市区，地点文字)(会有多个，以逗号分隔，以逗号结尾)
	 */
    private String areaid;

    /**
	 * 接单规模ID(JDGM+UUID)
	 */
    private String scaleid;
    
    /**
	 * 用户接单类别(WX:维修工/AZ:安装队)
	 */
    private String userservicerole;

    /**
	 * 用户接单状态(Y:接单/N:不接单)
	 */
    private String userservicestate;
    
    /**
	 * 用户差旅费(每公里)
	 */
    private Double userservicetravelmoney;
    
    /**
	 * 用户维修类型
	 */
    private String userservicetype;
    
    /**
	 * 用户维修优势
	 */
    private String userserviceadvantage;

    /**
	 * 用户_维修工、安装队备注
	 */
    private String userserviceremark;

	/**
	 * 接单区域代码
	 */
	private String areacode;
	/**
	 * 接单区域名称
	 */
	private String areaname;

	/**
	 * 接单项目id
	 */
	private String goodid;

	/**
	 * 接单项目名称
	 */
	private String goodsname;

	/**
	 * 是否为年度安装队Y/是、N/否
	 */
	private String useryearteam;
	/**
	 * 年协议量
	 */
	private Integer useryearnumber;
	/**
	 * 年百分比
	 */
	private Double useryearpercent;
    
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getScaleid() {
        return scaleid;
    }

    public void setScaleid(String scaleid) {
        this.scaleid = scaleid == null ? null : scaleid.trim();
    }

    public String getUserservicerole() {
		return userservicerole;
	}

	public void setUserservicerole(String userservicerole) {
		this.userservicerole = userservicerole;
	}

	public String getUserservicestate() {
        return userservicestate;
    }

    public void setUserservicestate(String userservicestate) {
        this.userservicestate = userservicestate == null ? null : userservicestate.trim();
    }

	public Double getUserservicetravelmoney() {
		return userservicetravelmoney;
	}

	public void setUserservicetravelmoney(Double userservicetravelmoney) {
		this.userservicetravelmoney = userservicetravelmoney;
	}

	public String getUserservicetype() {
		return userservicetype;
	}

	public void setUserservicetype(String userservicetype) {
		this.userservicetype = userservicetype;
	}

	public String getUserserviceadvantage() {
		return userserviceadvantage;
	}

	public void setUserserviceadvantage(String userserviceadvantage) {
		this.userserviceadvantage = userserviceadvantage;
	}

	public String getUserserviceremark() {
        return userserviceremark;
    }

    public void setUserserviceremark(String userserviceremark) {
        this.userserviceremark = userserviceremark == null ? null : userserviceremark.trim();
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

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getGoodid() {
		return goodid;
	}

	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}

	public String getUseryearteam() {
		return useryearteam;
	}

	public void setUseryearteam(String useryearteam) {
		this.useryearteam = useryearteam;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public Integer getUseryearnumber() {
		return useryearnumber;
	}

	public void setUseryearnumber(Integer useryearnumber) {
		this.useryearnumber = useryearnumber;
	}

	public Double getUseryearpercent() {
		return useryearpercent;
	}

	public void setUseryearpercent(Double useryearpercent) {
		this.useryearpercent = useryearpercent;
	}
}