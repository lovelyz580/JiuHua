package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 信用值_变化表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/05
 */

public class CreditChange {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 信用值变化ID(XYBH+UUID)
	 */
	private String creditchangeid;

	/**
	 * 用户ID(YHBG+UUID)
	 */
	private String userid;

	/**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;
	
	/**
	 * 评价ID(PJWX+UUID或PJKH+UUID)(评论修改信用值时，才会添加这个字段的数据)
	 */
	private String evaluatid;

	/**
	 * 信用值变化类型信用值变化类型(CSZ:初始值/WXTHDD:维修人员退回订单/KHQXQR:客户取消订单/KHYSHG:客户验收合格/KHGHWX:客户更换维修人员/KHWTPD:客户委托平台派单/PLWX:客户评论维修人员/PLKH:维修人员评论客户)
	 */
	private String creditchangetype;

	/**
	 * 信用值变化值
	 */
	private Double creditchange;

	/**
	 * 信用值变化创建时间
	 */
	private Date creditchangecreatetime;

	/**
	 * 信用值变化备注
	 */
	private String creditchangeremark;

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
	 * 根据信用值变化创建时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectstarttime;
	
	/**
	 * 根据信用值变化创建时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectendtime;
	
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

	public String getCreditchangeid() {
		return creditchangeid;
	}

	public void setCreditchangeid(String creditchangeid) {
		this.creditchangeid = creditchangeid == null ? null : creditchangeid.trim();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid == null ? null : orderid.trim();
	}

	public String getEvaluatid() {
		return evaluatid;
	}

	public void setEvaluatid(String evaluatid) {
		this.evaluatid = evaluatid;
	}

	public String getCreditchangetype() {
		return creditchangetype;
	}

	public void setCreditchangetype(String creditchangetype) {
		this.creditchangetype = creditchangetype == null ? null : creditchangetype.trim();
	}

	public Double getCreditchange() {
		return creditchange;
	}

	public void setCreditchange(Double creditchange) {
		this.creditchange = creditchange;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getCreditchangecreatetime() {
		return creditchangecreatetime;
	}

	public void setCreditchangecreatetime(Date creditchangecreatetime) {
		this.creditchangecreatetime = creditchangecreatetime;
	}

	public String getCreditchangeremark() {
		return creditchangeremark;
	}

	public void setCreditchangeremark(String creditchangeremark) {
		this.creditchangeremark = creditchangeremark == null ? null : creditchangeremark.trim();
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

	public String getSelectstarttime() {
		return selectstarttime;
	}

	public void setSelectstarttime(String selectstarttime) {
		this.selectstarttime = selectstarttime;
	}

	public String getSelectendtime() {
		return selectendtime;
	}

	public void setSelectendtime(String selectendtime) {
		this.selectendtime = selectendtime;
	}

	public void setIdlist(List<Integer> idlist) {
		this.idlist = idlist;
	}
	
	public List<Integer> getIdlist() {
		return idlist;
	}

	public List<String> getUuidlist() {
		return uuidlist;
	}

	public void setUuidlist(List<String> uuidlist) {
		this.uuidlist = uuidlist;
	}

}