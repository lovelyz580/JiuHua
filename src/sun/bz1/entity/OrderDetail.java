package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

/**
 * 维修单详情表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/07
 */

public class OrderDetail {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 维修单详情ID(WXXQ+UUID)
	 */
    private String orderdetailid;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 产品ID(CPBG+UUID)
	 */
    private String goodsid;

    /**
	 * 项目ID(XMBG+UUID)
	 */
    private String projectid;

    /**
	 * 维修单详情数量
	 */
    private Integer orderdetailnumber;

    /**
	 * 维修单详情推荐价(拦标价表中的推荐价)
	 */
    private Double orderdetailinterceptmoney;

    /**
	 * 维修单详情推荐总价(数量*推荐价)
	 */
    private Double orderdetailintercepttotalmoney;

    /**
	 * 维修单详情维修单价(确认维修人员后)(维修单价表中的维修单价)(暂时没用到该字段)
	 */
    private Double orderdetailpricemoney;

    /**
	 * 维修单详情维修总价(确认维修人员后)(数量*维修单价)(暂时没用到该字段)
	 */
    private Double orderdetailpricetotalmoney;
    
    /**
	 * 维修单针对竞标的可以维护的单价
	 */
    private Double orderpricemoney;

    /**
	 * 维修单详情备注
	 */
    private String orderdetailremark;
    
    /**
   	 * 产品表-产品名称
   	 */
    private String goodsname;

    /**
   	 * 项目表-项目名称
   	 */
    private String projectname;

	/**
	 * 订单表-订单项目的名称
	 */
	private String orderprojectname;

	/**
	 * 订单表-订单发布人
	 */
	private String ordercontactperson;

	/**
	 * 订单表-订单发布时间
	 */
	private String ordercreatetime;
    
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

    public String getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(String orderdetailid) {
        this.orderdetailid = orderdetailid == null ? null : orderdetailid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }

    public Integer getOrderdetailnumber() {
        return orderdetailnumber;
    }

    public void setOrderdetailnumber(Integer orderdetailnumber) {
        this.orderdetailnumber = orderdetailnumber;
    }

    public Double getOrderdetailinterceptmoney() {
		return orderdetailinterceptmoney;
	}

	public void setOrderdetailinterceptmoney(Double orderdetailinterceptmoney) {
		this.orderdetailinterceptmoney = orderdetailinterceptmoney;
	}

	public Double getOrderdetailintercepttotalmoney() {
        return orderdetailintercepttotalmoney;
    }

    public void setOrderdetailintercepttotalmoney(Double orderdetailintercepttotalmoney) {
        this.orderdetailintercepttotalmoney = orderdetailintercepttotalmoney;
    }

    public Double getOrderdetailpricemoney() {
		return orderdetailpricemoney;
	}

	public void setOrderdetailpricemoney(Double orderdetailpricemoney) {
		this.orderdetailpricemoney = orderdetailpricemoney;
	}

	public Double getOrderdetailpricetotalmoney() {
        return orderdetailpricetotalmoney;
    }

    public void setOrderdetailpricetotalmoney(Double orderdetailpricetotalmoney) {
        this.orderdetailpricetotalmoney = orderdetailpricetotalmoney;
    }

    public String getOrderdetailremark() {
        return orderdetailremark;
    }

    public void setOrderdetailremark(String orderdetailremark) {
        this.orderdetailremark = orderdetailremark == null ? null : orderdetailremark.trim();
    }

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
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

	public Double getOrderpricemoney() {
		return orderpricemoney;
	}

	public void setOrderpricemoney(Double orderpricemoney) {
		this.orderpricemoney = orderpricemoney;
	}

	public String getOrderprojectname() {
		return orderprojectname;
	}

	public void setOrderprojectname(String orderprojectname) {
		this.orderprojectname = orderprojectname;
	}

	public String getOrdercontactperson() {
		return ordercontactperson;
	}

	public void setOrdercontactperson(String ordercontactperson) {
		this.ordercontactperson = ordercontactperson;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public String getOrdercreatetime() {
		return ordercreatetime;
	}

	public void setOrdercreatetime(String ordercreatetime) {
		this.ordercreatetime = ordercreatetime;
	}
}