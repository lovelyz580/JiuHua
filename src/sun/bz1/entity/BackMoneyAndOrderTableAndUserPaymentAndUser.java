package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 平台收付款报表的生成数据
 * 
 * 实体类
 * 
 * @author ZY on 2018/12/13
 */

public class BackMoneyAndOrderTableAndUserPaymentAndUser {

	/**
	 * 平台_收益金额表-序号(自增主键)
	 */
    private Integer id;

    /**
	 * 平台收入状态
	 */
    private String platformincomestate;

	/**
	 * 平台付款给维修人员状态
	 */
	private String platformservicestate;
    /**
	 * 平台_收益金额表-维修单ID(D+年月日+时分秒+6位随机数)
	 */
    private String orderid;

    /**
	 * 维修单表-维修项目名称
	 */
	private String orderprojectname;

	/**
	 * 客户-付款人
	 */
	private String customername;

	/**
	 * 维修人-平台给维修付款的人
	 */
	private String servicename;

	/**
	 * 用户付款金额
	 */
	private Double platformimcomemoney;

	/**
	 * 平台给维修人员付款金额
	 */
	private Double platformtoservicemoney;

	/**
	 * 平台账号开户人
	 */
	private String userpaymentplatformaccountname;

	/**
	 * 平台账号开户账号
	 */
	private String userpaymentplatformaccount;

	/**
	 * 平台账号开户账号开户地址
	 */
	private String userpaymentplatformaccountaddress;

	/**
	 * 平台账号账号总信息（拼接）
	 */
	private String userpaymentplatformdetail;

	/**
	 * 维修人员开户账号
	 */
	private String serviceaccount;

	/**
	 * 维修人员开户行地址
	 */
	private String serviceaccountaddress;

	/**
	 * 维修人员开户总信息（拼接）
	 */
	private String servicedetail;

	/**
	 * 客户给平台付款时间
	 */
	private Date platformincometime;
	/**
	 * 平台给维修人员付款的时间
	 */
	private Date platformservicetime;




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
	 * 根据平台_收益金额表的金额创建时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectbackmoneycreatestarttime;

	/**
	 * 根据平台_收益金额表的金额创建时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectbackmoneycreateendtime;
	
	/**
	 * 根据维修单表的维修计划时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectorderplanstarttime;

	/**
	 * 根据维修单表的维修计划时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectorderplanendtime;
	
	/**
	 * 根据维修单表的维修竞标结束时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectorderbiddingendstarttime;

	/**
	 * 根据维修单表的维修竞标结束时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectorderbiddingendendtime;

	/**
	 * 维修单表-维修项目名称(关键字搜索)
	 */
	private String orderprojectnamekeyword;

	/**
	 * 客户-付款人(关键字)
	 */
	private String customernamekeyword;

	/**
	 * 维修人-平台给维修付款的人(关键字)
	 */
	private String servicenamekeyword;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlatformincomestate() {
		return platformincomestate;
	}

	public void setPlatformincomestate(String platformincomestate) {
		this.platformincomestate = platformincomestate;
	}

	public String getPlatformservicestate() {
		return platformservicestate;
	}

	public void setPlatformservicestate(String platformservicestate) {
		this.platformservicestate = platformservicestate;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderprojectname() {
		return orderprojectname;
	}

	public void setOrderprojectname(String orderprojectname) {
		this.orderprojectname = orderprojectname;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public Double getPlatformimcomemoney() {
		return platformimcomemoney;
	}

	public void setPlatformimcomemoney(Double platformimcomemoney) {
		this.platformimcomemoney = platformimcomemoney;
	}

	public Double getPlatformtoservicemoney() {
		return platformtoservicemoney;
	}

	public void setPlatformtoservicemoney(Double platformtoservicemoney) {
		this.platformtoservicemoney = platformtoservicemoney;
	}

	public String getUserpaymentplatformaccountname() {
		return userpaymentplatformaccountname;
	}

	public void setUserpaymentplatformaccountname(String userpaymentplatformaccountname) {
		this.userpaymentplatformaccountname = userpaymentplatformaccountname;
	}

	public String getUserpaymentplatformaccount() {
		return userpaymentplatformaccount;
	}

	public void setUserpaymentplatformaccount(String userpaymentplatformaccount) {
		this.userpaymentplatformaccount = userpaymentplatformaccount;
	}

	public String getUserpaymentplatformaccountaddress() {
		return userpaymentplatformaccountaddress;
	}

	public void setUserpaymentplatformaccountaddress(String userpaymentplatformaccountaddress) {
		this.userpaymentplatformaccountaddress = userpaymentplatformaccountaddress;
	}

	public String getServiceaccount() {
		return serviceaccount;
	}

	public void setServiceaccount(String serviceaccount) {
		this.serviceaccount = serviceaccount;
	}

	public String getServiceaccountaddress() {
		return serviceaccountaddress;
	}

	public void setServiceaccountaddress(String serviceaccountaddress) {
		this.serviceaccountaddress = serviceaccountaddress;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getPlatformincometime() {
		return platformincometime;
	}

	public void setPlatformincometime(Date platformincometime) {
		this.platformincometime = platformincometime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getPlatformservicetime() {
		return platformservicetime;
	}

	public void setPlatformservicetime(Date platformservicetime) {
		this.platformservicetime = platformservicetime;
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

	public String getSelectbackmoneycreatestarttime() {
		return selectbackmoneycreatestarttime;
	}

	public void setSelectbackmoneycreatestarttime(String selectbackmoneycreatestarttime) {
		this.selectbackmoneycreatestarttime = selectbackmoneycreatestarttime;
	}

	public String getSelectbackmoneycreateendtime() {
		return selectbackmoneycreateendtime;
	}

	public void setSelectbackmoneycreateendtime(String selectbackmoneycreateendtime) {
		this.selectbackmoneycreateendtime = selectbackmoneycreateendtime;
	}

	public String getSelectorderplanstarttime() {
		return selectorderplanstarttime;
	}

	public void setSelectorderplanstarttime(String selectorderplanstarttime) {
		this.selectorderplanstarttime = selectorderplanstarttime;
	}

	public String getSelectorderplanendtime() {
		return selectorderplanendtime;
	}

	public void setSelectorderplanendtime(String selectorderplanendtime) {
		this.selectorderplanendtime = selectorderplanendtime;
	}

	public String getSelectorderbiddingendstarttime() {
		return selectorderbiddingendstarttime;
	}

	public void setSelectorderbiddingendstarttime(String selectorderbiddingendstarttime) {
		this.selectorderbiddingendstarttime = selectorderbiddingendstarttime;
	}

	public String getSelectorderbiddingendendtime() {
		return selectorderbiddingendendtime;
	}

	public void setSelectorderbiddingendendtime(String selectorderbiddingendendtime) {
		this.selectorderbiddingendendtime = selectorderbiddingendendtime;
	}

	public String getUserpaymentplatformdetail() {
		return userpaymentplatformdetail;
	}

	public void setUserpaymentplatformdetail(String userpaymentplatformdetail) {
		this.userpaymentplatformdetail = userpaymentplatformdetail;
	}

	public String getServicedetail() {
		return servicedetail;
	}

	public void setServicedetail(String servicedetail) {
		this.servicedetail = servicedetail;
	}

	public String getOrderprojectnamekeyword() {
		return orderprojectnamekeyword;
	}

	public void setOrderprojectnamekeyword(String orderprojectnamekeyword) {
		this.orderprojectnamekeyword = orderprojectnamekeyword;
	}

	public String getCustomernamekeyword() {
		return customernamekeyword;
	}

	public void setCustomernamekeyword(String customernamekeyword) {
		this.customernamekeyword = customernamekeyword;
	}

	public String getServicenamekeyword() {
		return servicenamekeyword;
	}

	public void setServicenamekeyword(String servicenamekeyword) {
		this.servicenamekeyword = servicenamekeyword;
	}
}
