package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 客户取消维修单表(客户取消维修单时在该表中添加数据)
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/08
 */
public class CancelTable {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 维修取消ID(KHQX+UUID)
	 */
	private String cancelid;

	/**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 维修取消内容(原因)
	 */
	private String cancelcontent;

	/**
	 * 维修取消维修人员确认是否扣除已发生的费用状态(Y:扣除/N:不扣除)
	 */
	private String cancelstate;

	/**
	 * 维修取消已发生的费用总价
	 */
	private Double cancelhappenedmoney;

	/**
	 * 维修取消平台违约金总价
	 */
	private Double canceldefaultmoney;

	/**
	 * 维修取消人ID(YHBG+UUID)
	 */
	private String canceluserid;

	/**
	 * 维修取消时间
	 */
	private Date canceltime;

	/**
	 * 维修取消备注
	 */
	private String cancelremark;

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
	 * 根据维修单uuidlist删除信息时使用
	 */
	private List<String> orderuuidlist;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCancelid() {
		return cancelid;
	}

	public void setCancelid(String cancelid) {
		this.cancelid = cancelid == null ? null : cancelid.trim();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid == null ? null : orderid.trim();
	}

	public String getCancelcontent() {
		return cancelcontent;
	}

	public void setCancelcontent(String cancelcontent) {
		this.cancelcontent = cancelcontent == null ? null : cancelcontent.trim();
	}

	public String getCancelstate() {
		return cancelstate;
	}

	public void setCancelstate(String cancelstate) {
		this.cancelstate = cancelstate == null ? null : cancelstate.trim();
	}

	public Double getCancelhappenedmoney() {
		return cancelhappenedmoney;
	}

	public void setCancelhappenedmoney(Double cancelhappenedmoney) {
		this.cancelhappenedmoney = cancelhappenedmoney;
	}

	public Double getCanceldefaultmoney() {
		return canceldefaultmoney;
	}

	public void setCanceldefaultmoney(Double canceldefaultmoney) {
		this.canceldefaultmoney = canceldefaultmoney;
	}

	public String getCanceluserid() {
		return canceluserid;
	}

	public void setCanceluserid(String canceluserid) {
		this.canceluserid = canceluserid == null ? null : canceluserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getCanceltime() {
		return canceltime;
	}

	public void setCanceltime(Date canceltime) {
		this.canceltime = canceltime;
	}

	public String getCancelremark() {
		return cancelremark;
	}

	public void setCancelremark(String cancelremark) {
		this.cancelremark = cancelremark == null ? null : cancelremark.trim();
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

	public List<String> getOrderuuidlist() {
		return orderuuidlist;
	}

	public void setOrderuuidlist(List<String> orderuuidlist) {
		this.orderuuidlist = orderuuidlist;
	}

}