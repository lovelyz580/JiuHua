package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户_维修工、安装队_收入_金额表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/19
 */

public class UserServiceIncomeMoney {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 维修人员收入金额ID(WXSR+UUID)
	 */
	private String userserviceincomemoneyid;

	/**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 金额用户ID(YHBG+UUID)
	 */
	private String userserviceincomemoneyuserid;

	/**
	 * 金额(可正可负)
	 */
	private Double userserviceincomemoney;

	/**
	 * 金额来源(QRQXWX:确认取消(维修人员收入金额)/YSHG:验收合格(维修人员收入金额)/PTXTWX:平台协调(维修人员收入金额)/WXCZ:维修人员充值(维修人员收入金额))
	 */
	private String userserviceincomemoneysource;

	/**
	 * 金额创建时间
	 */
	private Date userserviceincomemoneycreatetime;

	/**
	 * 金额其他字段1
	 */
	private String userserviceincomemoneyother1;

	/**
	 * 金额其他字段2
	 */
	private String userserviceincomemoneyother2;

	/**
	 * 金额备注
	 */
	private String userserviceincomemoneyremark;

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

	public String getUserserviceincomemoneyid() {
		return userserviceincomemoneyid;
	}

	public void setUserserviceincomemoneyid(String userserviceincomemoneyid) {
		this.userserviceincomemoneyid = userserviceincomemoneyid == null ? null : userserviceincomemoneyid.trim();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid == null ? null : orderid.trim();
	}

	public String getUserserviceincomemoneyuserid() {
		return userserviceincomemoneyuserid;
	}

	public void setUserserviceincomemoneyuserid(String userserviceincomemoneyuserid) {
		this.userserviceincomemoneyuserid = userserviceincomemoneyuserid == null ? null
				: userserviceincomemoneyuserid.trim();
	}

	public Double getUserserviceincomemoney() {
		return userserviceincomemoney;
	}

	public void setUserserviceincomemoney(Double userserviceincomemoney) {
		this.userserviceincomemoney = userserviceincomemoney;
	}

	public String getUserserviceincomemoneysource() {
		return userserviceincomemoneysource;
	}

	public void setUserserviceincomemoneysource(String userserviceincomemoneysource) {
		this.userserviceincomemoneysource = userserviceincomemoneysource == null ? null
				: userserviceincomemoneysource.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getUseserviceincomemoneycreatetime() {
		return userserviceincomemoneycreatetime;
	}

	public void setUserserviceincomemoneycreatetime(Date userserviceincomemoneycreatetime) {
		this.userserviceincomemoneycreatetime = userserviceincomemoneycreatetime;
	}

	public String getUserserviceincomemoneyother1() {
		return userserviceincomemoneyother1;
	}

	public void setUserserviceincomemoneyother1(String userserviceincomemoneyother1) {
		this.userserviceincomemoneyother1 = userserviceincomemoneyother1 == null ? null
				: userserviceincomemoneyother1.trim();
	}

	public String getUserserviceincomemoneyother2() {
		return userserviceincomemoneyother2;
	}

	public void setUserserviceincomemoneyother2(String userserviceincomemoneyother2) {
		this.userserviceincomemoneyother2 = userserviceincomemoneyother2 == null ? null
				: userserviceincomemoneyother2.trim();
	}

	public String getUserserviceincomemoneyremark() {
		return userserviceincomemoneyremark;
	}

	public void setUserserviceincomemoneyremark(String userserviceincomemoneyremark) {
		this.userserviceincomemoneyremark = userserviceincomemoneyremark == null ? null
				: userserviceincomemoneyremark.trim();
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