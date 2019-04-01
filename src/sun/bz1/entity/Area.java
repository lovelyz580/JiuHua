package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 接单区域表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class Area {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 接单区域ID(JDQY+UUID)
	 */
    private String areaid;

    /**
	 * 接单区域名称
	 */
    private String areaname;

    /**
	 * 接单区域状态(Y:有效/N:无效)
	 */
    private String areastate;

    /**
	 * 接单区域创建人ID(YHBG+UUID)
	 */
    private String areacreateuserid;
    
    /**
	 * 接单区域创建时间
	 */
    private Date areacreatetime;
    
    /**
	 * 接单区域更新人ID(YHBG+UUID)
	 */
    private String areaupdateuserid;

    /**
	 * 接单区域更新时间
	 */
    private Date areaupdatetime;

    /**
	 * 接单区域备注
	 */
    private String arearemark;

    /**
     * 省份名称
     */
    private String areaprovince;

    /**
     * 省份代码
     */
    private String areaprovincecode;

    /**
     * 城市名称
     */
    private String areacity;

    /**
     * 上级代码
     */
    private String areacitycode;

    /**
     * 模糊查询code
     */
    private String selectareaprovincecode;

    /**
     *
     * 查询时间段的开始时间
     */
    private String selectorderstarttime;

    /**
     *
     * 查询时间段的结束时间
     */
    private String selectorderendtime;

    /**
     * 订单数量
     */
    private Integer ordertablenumber;

    /**
     * 维修人员的数量
     */
    private Integer wxnumber;

    /**
     * 安装人员的数量
     */
    private Integer aznumber;

    /**
     * 该区域内所有完成的订单的客户给平台付款的总价
     */
    private Double areapricetotalmoney;

    /**
     * 支付给维修人员的支出总价
     */
    private Double expendituremoney;

    /**
     * 利润
     */
    private Double profitmoney;


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

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getAreastate() {
        return areastate;
    }

    public void setAreastate(String areastate) {
        this.areastate = areastate == null ? null : areastate.trim();
    }

    public String getAreacreateuserid() {
        return areacreateuserid;
    }

    public void setAreacreateuserid(String areacreateuserid) {
        this.areacreateuserid = areacreateuserid == null ? null : areacreateuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getAreacreatetime() {
        return areacreatetime;
    }

    public void setAreacreatetime(Date areacreatetime) {
        this.areacreatetime = areacreatetime;
    }

    public String getAreaupdateuserid() {
        return areaupdateuserid;
    }

    public void setAreaupdateuserid(String areaupdateuserid) {
        this.areaupdateuserid = areaupdateuserid == null ? null : areaupdateuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getAreaupdatetime() {
        return areaupdatetime;
    }

    public void setAreaupdatetime(Date areaupdatetime) {
        this.areaupdatetime = areaupdatetime;
    }

    public String getArearemark() {
        return arearemark;
    }

    public void setArearemark(String arearemark) {
        this.arearemark = arearemark == null ? null : arearemark.trim();
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

    public String getAreaprovince() {
        return areaprovince;
    }

    public void setAreaprovince(String areaprovince) {
        this.areaprovince = areaprovince;
    }

    public String getAreaprovincecode() {
        return areaprovincecode;
    }

    public void setAreaprovincecode(String areaprovincecode) {
        this.areaprovincecode = areaprovincecode;
    }

    public String getAreacity() {
        return areacity;
    }

    public void setAreacity(String areacity) {
        this.areacity = areacity;
    }

    public String getAreacitycode() {
        return areacitycode;
    }

    public void setAreacitycode(String areacitycode) {
        this.areacitycode = areacitycode;
    }



    public String getSelectorderendtime() {
        return selectorderendtime;
    }

    public void setSelectorderendtime(String selectorderendtime) {
        this.selectorderendtime = selectorderendtime;
    }

    public String getSelectorderstarttime() {
        return selectorderstarttime;
    }

    public void setSelectorderstarttime(String selectorderstarttime) {
        this.selectorderstarttime = selectorderstarttime;
    }


    public Integer getWxnumber() {
        return wxnumber;
    }

    public void setWxnumber(Integer wxnumber) {
        this.wxnumber = wxnumber;
    }

    public Integer getAznumber() {
        return aznumber;
    }

    public void setAznumber(Integer aznumber) {
        this.aznumber = aznumber;
    }

    public Double getAreapricetotalmoney() {
        return areapricetotalmoney;
    }

    public void setAreapricetotalmoney(Double areapricetotalmoney) {
        this.areapricetotalmoney = areapricetotalmoney;
    }

    public Double getExpendituremoney() {
        return expendituremoney;
    }

    public void setExpendituremoney(Double expendituremoney) {
        this.expendituremoney = expendituremoney;
    }

    public Double getProfitmoney() {
        return profitmoney;
    }

    public void setProfitmoney(Double profitmoney) {
        this.profitmoney = profitmoney;
    }

    public Integer getOrdertablenumber() {
        return ordertablenumber;
    }

    public void setOrdertablenumber(Integer ordertablenumber) {
        this.ordertablenumber = ordertablenumber;
    }

    public String getSelectareaprovincecode() {
        return selectareaprovincecode;
    }

    public void setSelectareaprovincecode(String selectareaprovincecode) {
        this.selectareaprovincecode = selectareaprovincecode;
    }
}