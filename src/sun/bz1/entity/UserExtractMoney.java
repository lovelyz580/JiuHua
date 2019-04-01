package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户_提现表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/10
 */

public class UserExtractMoney {
	
	/**
	 * 序号(自增主键)
	 */
    private Integer id;
    
    /**
	 * 用户提现ID(YHTX+UUID)
	 */
    private String userextractmoneyid;

    /**
	 * 提现用户ID(YHBG+UUID)
	 */
    private String userextractmoneyuserid;

    /**
	 * 提现金额
	 */
    private Double userextractmoney;

    /**
	 * 提现姓名
	 */
    private String userextractmoneyname;

    /**
	 * 提现银行卡号
	 */
    private String userextractmoneybankcard;

    /**
	 * 提现开户行
	 */
    private String userextractmoneybankaddress;

    /**
	 * 提现状态(Y:有效/N:无效)
	 */
    private String userextractmoneystate;

    /**
	 * 提现时间
	 */
    private Date userextractmoneytime;

    /**
	 * 提现审核人员ID(YHBG+UUID)
	 */
    private String userextractmoneycheckuserid;

    /**
	 * 提现审核通过状态(Y:通过/N:未通过)
	 */
    private String userextractmoneycheckstate;

    /**
	 * 提现审核通过未通过原因
	 */
    private String userextractmoneycheckcontent;

    /**
	 * 提现审核时间
	 */
    private Date userextractmoneychecktime;

    /**
	 * 提现备注
	 */
    private String userextractmoneyremark;

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

    public String getUserextractmoneyid() {
		return userextractmoneyid;
	}

	public void setUserextractmoneyid(String userextractmoneyid) {
		this.userextractmoneyid = userextractmoneyid;
	}

	public String getUserextractmoneyuserid() {
		return userextractmoneyuserid;
	}

	public void setUserextractmoneyuserid(String userextractmoneyuserid) {
		this.userextractmoneyuserid = userextractmoneyuserid;
	}

	public Double getUserextractmoney() {
        return userextractmoney;
    }

    public void setUserextractmoney(Double userextractmoney) {
        this.userextractmoney = userextractmoney;
    }

    public String getUserextractmoneyname() {
        return userextractmoneyname;
    }

    public void setUserextractmoneyname(String userextractmoneyname) {
        this.userextractmoneyname = userextractmoneyname == null ? null : userextractmoneyname.trim();
    }

    public String getUserextractmoneybankcard() {
        return userextractmoneybankcard;
    }

    public void setUserextractmoneybankcard(String userextractmoneybankcard) {
        this.userextractmoneybankcard = userextractmoneybankcard == null ? null : userextractmoneybankcard.trim();
    }

    public String getUserextractmoneybankaddress() {
        return userextractmoneybankaddress;
    }

    public void setUserextractmoneybankaddress(String userextractmoneybankaddress) {
        this.userextractmoneybankaddress = userextractmoneybankaddress == null ? null : userextractmoneybankaddress.trim();
    }

    public String getUserextractmoneystate() {
        return userextractmoneystate;
    }

    public void setUserextractmoneystate(String userextractmoneystate) {
        this.userextractmoneystate = userextractmoneystate == null ? null : userextractmoneystate.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getUserextractmoneytime() {
        return userextractmoneytime;
    }

    public void setUserextractmoneytime(Date userextractmoneytime) {
        this.userextractmoneytime = userextractmoneytime;
    }

    public String getUserextractmoneycheckuserid() {
        return userextractmoneycheckuserid;
    }

    public void setUserextractmoneycheckuserid(String userextractmoneycheckuserid) {
        this.userextractmoneycheckuserid = userextractmoneycheckuserid == null ? null : userextractmoneycheckuserid.trim();
    }

    public String getUserextractmoneycheckstate() {
        return userextractmoneycheckstate;
    }

    public void setUserextractmoneycheckstate(String userextractmoneycheckstate) {
        this.userextractmoneycheckstate = userextractmoneycheckstate == null ? null : userextractmoneycheckstate.trim();
    }

    public String getUserextractmoneycheckcontent() {
        return userextractmoneycheckcontent;
    }

    public void setUserextractmoneycheckcontent(String userextractmoneycheckcontent) {
        this.userextractmoneycheckcontent = userextractmoneycheckcontent == null ? null : userextractmoneycheckcontent.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getUserextractmoneychecktime() {
        return userextractmoneychecktime;
    }

    public void setUserextractmoneychecktime(Date userextractmoneychecktime) {
        this.userextractmoneychecktime = userextractmoneychecktime;
    }

    public String getUserextractmoneyremark() {
        return userextractmoneyremark;
    }

    public void setUserextractmoneyremark(String userextractmoneyremark) {
        this.userextractmoneyremark = userextractmoneyremark == null ? null : userextractmoneyremark.trim();
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

}