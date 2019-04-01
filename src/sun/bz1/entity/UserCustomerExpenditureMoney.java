package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户_客户_支出_金额表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/19
 */

public class UserCustomerExpenditureMoney {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 客户支出金额ID(KHZC+UUID)
	 */
	private String usercustomerexpendituremoneyid;

	/**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 金额用户ID(YHBG+UUID)
	 */
	private String usercustomerexpendituremoneyuserid;

	/**
	 * 金额(可正可负)
	 */
	private Double usercustomerexpendituremoney;

	/**
	 * 金额来源(PTPD:平台派单(客户按维修推荐总价+维修差旅费推荐总价支出金额)/KHTX:客户提现(客户支出金额))
	 */
	private String usercustomerexpendituremoneysource;

	/**
	 * 金额创建时间
	 */
	private Date usercustomerexpendituremoneycreatetime;

	/**
	 * 金额其他字段1
	 */
	private String usercustomerexpendituremoneyother1;

	/**
	 * 金额其他字段2
	 */
	private String usercustomerexpendituremoneyother2;

	/**
	 * 金额备注
	 */
	private String usercustomerexpendituremoneyremark;

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

	public String getUsercustomerexpendituremoneyid() {
		return usercustomerexpendituremoneyid;
	}

	public void setUsercustomerexpendituremoneyid(String usercustomerexpendituremoneyid) {
		this.usercustomerexpendituremoneyid = usercustomerexpendituremoneyid == null ? null
				: usercustomerexpendituremoneyid.trim();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid == null ? null : orderid.trim();
	}

	public String getUsercustomerexpendituremoneyuserid() {
		return usercustomerexpendituremoneyuserid;
	}

	public void setUsercustomerexpendituremoneyuserid(String usercustomerexpendituremoneyuserid) {
		this.usercustomerexpendituremoneyuserid = usercustomerexpendituremoneyuserid == null ? null
				: usercustomerexpendituremoneyuserid.trim();
	}

	public Double getUsercustomerexpendituremoney() {
		return usercustomerexpendituremoney;
	}

	public void setUsercustomerexpendituremoney(Double usercustomerexpendituremoney) {
		this.usercustomerexpendituremoney = usercustomerexpendituremoney;
	}

	public String getUsercustomerexpendituremoneysource() {
		return usercustomerexpendituremoneysource;
	}

	public void setUsercustomerexpendituremoneysource(String usercustomerexpendituremoneysource) {
		this.usercustomerexpendituremoneysource = usercustomerexpendituremoneysource == null ? null
				: usercustomerexpendituremoneysource.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getUsercustomerexpendituremoneycreatetime() {
		return usercustomerexpendituremoneycreatetime;
	}

	public void setUsercustomerexpendituremoneycreatetime(Date usercustomerexpendituremoneycreatetime) {
		this.usercustomerexpendituremoneycreatetime = usercustomerexpendituremoneycreatetime;
	}

	public String getUsercustomerexpendituremoneyother1() {
		return usercustomerexpendituremoneyother1;
	}

	public void setUsercustomerexpendituremoneyother1(String usercustomerexpendituremoneyother1) {
		this.usercustomerexpendituremoneyother1 = usercustomerexpendituremoneyother1 == null ? null
				: usercustomerexpendituremoneyother1.trim();
	}

	public String getUsercustomerexpendituremoneyother2() {
		return usercustomerexpendituremoneyother2;
	}

	public void setUsercustomerexpendituremoneyother2(String usercustomerexpendituremoneyother2) {
		this.usercustomerexpendituremoneyother2 = usercustomerexpendituremoneyother2 == null ? null
				: usercustomerexpendituremoneyother2.trim();
	}

	public String getUsercustomerexpendituremoneyremark() {
		return usercustomerexpendituremoneyremark;
	}

	public void setUsercustomerexpendituremoneyremark(String usercustomerexpendituremoneyremark) {
		this.usercustomerexpendituremoneyremark = usercustomerexpendituremoneyremark == null ? null
				: usercustomerexpendituremoneyremark.trim();
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