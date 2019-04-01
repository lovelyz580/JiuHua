package sun.bz1.entity;

import java.util.Date;
import java.util.List;

/**
 * 平台_收益金额表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/10
 */

public class BackMoney {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;

    /**
	 * 平台金额ID(PTJE+UUID)
	 */
    private String backmoneyid;

    /**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 金额(可正可负)
	 */
    private Double backmoney;

    /**
	 * 金额创建时间
	 */
    private Date backmoneycreatetime;

    /**
	 * 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户))
	 */
    private String backmoneyother1;

    /**
	 * 金额其他字段2
	 */
    private String backmoneyother2;

    /**
	 * 金额备注
	 */
    private String backmoneyremark;
    
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

    public String getBackmoneyid() {
        return backmoneyid;
    }

    public void setBackmoneyid(String backmoneyid) {
        this.backmoneyid = backmoneyid == null ? null : backmoneyid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Double getBackmoney() {
        return backmoney;
    }

    public void setBackmoney(Double backmoney) {
        this.backmoney = backmoney;
    }

    public Date getBackmoneycreatetime() {
        return backmoneycreatetime;
    }

    public void setBackmoneycreatetime(Date backmoneycreatetime) {
        this.backmoneycreatetime = backmoneycreatetime;
    }

    public String getBackmoneyother1() {
        return backmoneyother1;
    }

    public void setBackmoneyother1(String backmoneyother1) {
        this.backmoneyother1 = backmoneyother1 == null ? null : backmoneyother1.trim();
    }

    public String getBackmoneyother2() {
        return backmoneyother2;
    }

    public void setBackmoneyother2(String backmoneyother2) {
        this.backmoneyother2 = backmoneyother2 == null ? null : backmoneyother2.trim();
    }

    public String getBackmoneyremark() {
        return backmoneyremark;
    }

    public void setBackmoneyremark(String backmoneyremark) {
        this.backmoneyremark = backmoneyremark == null ? null : backmoneyremark.trim();
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