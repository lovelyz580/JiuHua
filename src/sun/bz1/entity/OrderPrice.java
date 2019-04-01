package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单对应维修单价表
 * 
 * 实体类
 * 
 * @author WJF on 2018/10/09
 */

public class OrderPrice {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 维修单对应维修单价的维护人(维修人员)ID(YHBG+UUID)
	 */
    private String orderpriceupdateuserid;

    /**
	 * 维修单对应维修单价的产品ID(CPBG+UUID)
	 */
    private String goodsid;

    /**
	 * 维修单对应维修单价的项目ID(XMBG+UUID)
	 */
    private String projectid;

    /**
	 * 维修单对应维修单价的类型(WX:维修工/AZ:安装队)
	 */
    private String orderpricetype;

    /**
	 * 维修单对应维修单价的单价
	 */
    private Double orderpricemoney;

    /**
	 * 维修单对应维修单价的状态(Y:有效/N:无效)
	 */
    private String orderpricestate;

    /**
	 * 维修单对应维修单价的创建人ID(YHBG+UUID)
	 */
    private String orderpricecreateuserid;

    /**
	 * 维修单对应维修单价的创建时间
	 */
    private Date orderpricecreatetime;

    /**
	 * 维修单对应维修单价的更新时间
	 */
    private Date orderpriceupdatetime;

    /**
	 * 维修单对应维修单价的备注
	 */
    private String orderpriceremark;

    /**
     * 维修单对应维修单价的省级区域代码
     */
    private String orderpriceareaprovincecode;

    /**
     * 维修单对应维修单价的省级区域名称
     */
    private String orderpriceareaprovincename;

    /**
     * 维修单对应维修单价的区域代码
     */
    private String orderpriceareacode;

    /**
     * 维修单对应维修单价的区域名称
     */
    private String orderpriceareaname;

    /**
     * 维修单对应维修单价的建筑类型ID
     */
    private String orderpricebuildingtypeid;

    /**
     * 维修单对应维修单价的建筑类型名称
     */
    private String orderpricebuildingtypename;

    /**
     * 维修单对应维修单价的地产公司ID
     */
    private String orderpricepropertycompanyid;

    /**
     * 维修单对应维修单价的地产公司名称
     */
    private String orderpricepropertycompanyname;

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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getOrderpriceupdateuserid() {
        return orderpriceupdateuserid;
    }

    public void setOrderpriceupdateuserid(String orderpriceupdateuserid) {
        this.orderpriceupdateuserid = orderpriceupdateuserid == null ? null : orderpriceupdateuserid.trim();
    }

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getOrderpricetype() {
        return orderpricetype;
    }

    public void setOrderpricetype(String orderpricetype) {
        this.orderpricetype = orderpricetype == null ? null : orderpricetype.trim();
    }

    public Double getOrderpricemoney() {
        return orderpricemoney;
    }

    public void setOrderpricemoney(Double orderpricemoney) {
        this.orderpricemoney = orderpricemoney;
    }

    public String getOrderpricestate() {
        return orderpricestate;
    }

    public void setOrderpricestate(String orderpricestate) {
        this.orderpricestate = orderpricestate == null ? null : orderpricestate.trim();
    }

    public String getOrderpricecreateuserid() {
        return orderpricecreateuserid;
    }

    public void setOrderpricecreateuserid(String orderpricecreateuserid) {
        this.orderpricecreateuserid = orderpricecreateuserid == null ? null : orderpricecreateuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getOrderpricecreatetime() {
        return orderpricecreatetime;
    }

    public void setOrderpricecreatetime(Date orderpricecreatetime) {
        this.orderpricecreatetime = orderpricecreatetime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getOrderpriceupdatetime() {
        return orderpriceupdatetime;
    }

    public void setOrderpriceupdatetime(Date orderpriceupdatetime) {
        this.orderpriceupdatetime = orderpriceupdatetime;
    }

    public String getOrderpriceremark() {
        return orderpriceremark;
    }

    public void setOrderpriceremark(String orderpriceremark) {
        this.orderpriceremark = orderpriceremark == null ? null : orderpriceremark.trim();
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

	public List<String> getUuidlist() {
		return uuidlist;
	}

	public void setUuidlist(List<String> uuidlist) {
		this.uuidlist = uuidlist;
	}

    public String getOrderpriceareacode() {
        return orderpriceareacode;
    }

    public void setOrderpriceareacode(String orderpriceareacode) {
        this.orderpriceareacode = orderpriceareacode;
    }

    public String getOrderpricebuildingtypeid() {
        return orderpricebuildingtypeid;
    }

    public void setOrderpricebuildingtypeid(String orderpricebuildingtypeid) {
        this.orderpricebuildingtypeid = orderpricebuildingtypeid;
    }

    public String getOrderpricepropertycompanyid() {
        return orderpricepropertycompanyid;
    }

    public void setOrderpricepropertycompanyid(String orderpricepropertycompanyid) {
        this.orderpricepropertycompanyid = orderpricepropertycompanyid;
    }

    public String getOrderpriceareaname() {
        return orderpriceareaname;
    }

    public void setOrderpriceareaname(String orderpriceareaname) {
        this.orderpriceareaname = orderpriceareaname;
    }

    public String getOrderpricebuildingtypename() {
        return orderpricebuildingtypename;
    }

    public void setOrderpricebuildingtypename(String orderpricebuildingtypename) {
        this.orderpricebuildingtypename = orderpricebuildingtypename;
    }

    public String getOrderpricepropertycompanyname() {
        return orderpricepropertycompanyname;
    }

    public void setOrderpricepropertycompanyname(String orderpricepropertycompanyname) {
        this.orderpricepropertycompanyname = orderpricepropertycompanyname;
    }

    public String getOrderpriceareaprovincecode() {
        return orderpriceareaprovincecode;
    }

    public void setOrderpriceareaprovincecode(String orderpriceareaprovincecode) {
        this.orderpriceareaprovincecode = orderpriceareaprovincecode;
    }

    public String getOrderpriceareaprovincename() {
        return orderpriceareaprovincename;
    }

    public void setOrderpriceareaprovincename(String orderpriceareaprovincename) {
        this.orderpriceareaprovincename = orderpriceareaprovincename;
    }
}