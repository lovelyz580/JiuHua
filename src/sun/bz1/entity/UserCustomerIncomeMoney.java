package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户_客户_收入_金额表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/19
 */

public class UserCustomerIncomeMoney {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 客户收入金额ID(KHSR+UUID)
	 */
    private String usercustomerincomemoneyid;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 金额用户ID(YHBG+UUID)
	 */
    private String usercustomerincomemoneyuserid;

    /**
	 * 金额(可正可负)
	 */
    private Double usercustomerincomemoney;

    /**
	 * 金额来源(WXTH:维修人员退回(客户收入金额)/QRQXKH:确认取消(客户收入金额)/GHWX:更换维修人员(客户收入金额)/WTPD:委托平台派单(客户收入金额)/PTXTKH:平台协调(客户收入金额)/KHCZ:客户充值(客户收入金额))
	 */
    private String usercustomerincomemoneysource;

    /**
	 * 金额创建时间
	 */
    private Date usercustomerincomemoneycreatetime;

    /**
	 * 金额其他字段1
	 */
    private String usercustomerincomemoneyother1;

    /**
	 * 金额其他字段2
	 */
    private String usercustomerincomemoneyother2;

    /**
	 * 金额备注
	 */
    private String usercustomerincomemoneyremark;
    
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

    public String getUsercustomerincomemoneyid() {
        return usercustomerincomemoneyid;
    }

    public void setUsercustomerincomemoneyid(String usercustomerincomemoneyid) {
        this.usercustomerincomemoneyid = usercustomerincomemoneyid == null ? null : usercustomerincomemoneyid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getUsercustomerincomemoneyuserid() {
        return usercustomerincomemoneyuserid;
    }

    public void setUsercustomerincomemoneyuserid(String usercustomerincomemoneyuserid) {
        this.usercustomerincomemoneyuserid = usercustomerincomemoneyuserid == null ? null : usercustomerincomemoneyuserid.trim();
    }

    public Double getUsercustomerincomemoney() {
        return usercustomerincomemoney;
    }

    public void setUsercustomerincomemoney(Double usercustomerincomemoney) {
        this.usercustomerincomemoney = usercustomerincomemoney;
    }

    public String getUsercustomerincomemoneysource() {
        return usercustomerincomemoneysource;
    }

    public void setUsercustomerincomemoneysource(String usercustomerincomemoneysource) {
        this.usercustomerincomemoneysource = usercustomerincomemoneysource == null ? null : usercustomerincomemoneysource.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getUsercustomerincomemoneycreatetime() {
        return usercustomerincomemoneycreatetime;
    }

    public void setUsercustomerincomemoneycreatetime(Date usercustomerincomemoneycreatetime) {
        this.usercustomerincomemoneycreatetime = usercustomerincomemoneycreatetime;
    }

    public String getUsercustomerincomemoneyother1() {
        return usercustomerincomemoneyother1;
    }

    public void setUsercustomerincomemoneyother1(String usercustomerincomemoneyother1) {
        this.usercustomerincomemoneyother1 = usercustomerincomemoneyother1 == null ? null : usercustomerincomemoneyother1.trim();
    }

    public String getUsercustomerincomemoneyother2() {
        return usercustomerincomemoneyother2;
    }

    public void setUsercustomerincomemoneyother2(String usercustomerincomemoneyother2) {
        this.usercustomerincomemoneyother2 = usercustomerincomemoneyother2 == null ? null : usercustomerincomemoneyother2.trim();
    }

    public String getUsercustomerincomemoneyremark() {
        return usercustomerincomemoneyremark;
    }

    public void setUsercustomerincomemoneyremark(String usercustomerincomemoneyremark) {
        this.usercustomerincomemoneyremark = usercustomerincomemoneyremark == null ? null : usercustomerincomemoneyremark.trim();
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