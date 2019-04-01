package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 纠纷表
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/07
 */

public class Dispute {
	
	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 纠纷ID(JFBG+UUID)
	 */
	private String disputeid;

	/**
	 * 客户ID(YHBG+UUID)
	 */
	private String customeruserid;

	/**
	 * 维修工、安装队ID(YHBG+UUID)
	 */
	private String serviceuserid;

	/**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 纠纷标题
	 */
	private String disputetitle;

	/**
	 * 纠纷状态(Y:已处理/N:未处理)
	 */
	private String disputestate;

	/**
	 * 纠纷内容
	 */
	private String dispute;

	/**
	 * 纠纷创建时间
	 */
	private Date disputecreatetime;

	/**
	 * 纠纷回复内容
	 */
	private String disputereplycontent;

	/**
	 * 纠纷回复用户(YHBG+UUID)
	 */
	private String disputereplyuserid;

	/**
	 * 纠纷回复时间
	 */
	private Date disputereplytime;

	/**
	 * 纠纷备注
	 */
	private String disputeremark;

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
	 * 根据纠纷创建时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectcreatestarttime;

	/**
	 * 根据纠纷创建时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectcreateendtime;

	/**
	 * 根据纠纷回复时间段查询时使用
	 * 
	 * 查询时间段的开始时间
	 */
	private String selectreplystarttime;

	/**
	 * 根据纠纷回复时间段查询时使用
	 * 
	 * 查询时间段的结束时间
	 */
	private String selectreplyendtime;

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

	public String getDisputeid() {
		return disputeid;
	}

	public void setDisputeid(String disputeid) {
		this.disputeid = disputeid == null ? null : disputeid.trim();
	}

	public String getCustomeruserid() {
		return customeruserid;
	}

	public void setCustomeruserid(String customeruserid) {
		this.customeruserid = customeruserid == null ? null : customeruserid.trim();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid == null ? null : orderid.trim();
	}

	public String getDisputetitle() {
		return disputetitle;
	}

	public void setDisputetitle(String disputetitle) {
		this.disputetitle = disputetitle == null ? null : disputetitle.trim();
	}

	public String getDisputestate() {
		return disputestate;
	}

	public void setDisputestate(String disputestate) {
		this.disputestate = disputestate == null ? null : disputestate.trim();
	}

	public String getDispute() {
		return dispute;
	}

	public void setDispute(String dispute) {
		this.dispute = dispute == null ? null : dispute.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getDisputecreatetime() {
		return disputecreatetime;
	}

	public void setDisputecreatetime(Date disputecreatetime) {
		this.disputecreatetime = disputecreatetime;
	}

	public String getDisputereplycontent() {
		return disputereplycontent;
	}

	public void setDisputereplycontent(String disputereplycontent) {
		this.disputereplycontent = disputereplycontent == null ? null : disputereplycontent.trim();
	}

	public String getDisputereplyuserid() {
		return disputereplyuserid;
	}

	public void setDisputereplyuserid(String disputereplyuserid) {
		this.disputereplyuserid = disputereplyuserid == null ? null : disputereplyuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getDisputereplytime() {
		return disputereplytime;
	}

	public void setDisputereplytime(Date disputereplytime) {
		this.disputereplytime = disputereplytime;
	}

	public String getDisputeremark() {
		return disputeremark;
	}

	public void setDisputeremark(String disputeremark) {
		this.disputeremark = disputeremark == null ? null : disputeremark.trim();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getServiceuserid() {
		return serviceuserid;
	}

	public void setServiceuserid(String serviceuserid) {
		this.serviceuserid = serviceuserid;
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

	public String getSelectcreatestarttime() {
		return selectcreatestarttime;
	}

	public void setSelectcreatestarttime(String selectcreatestarttime) {
		this.selectcreatestarttime = selectcreatestarttime;
	}

	public String getSelectcreateendtime() {
		return selectcreateendtime;
	}

	public void setSelectcreateendtime(String selectcreateendtime) {
		this.selectcreateendtime = selectcreateendtime;
	}

	public String getSelectreplystarttime() {
		return selectreplystarttime;
	}

	public void setSelectreplystarttime(String selectreplystarttime) {
		this.selectreplystarttime = selectreplystarttime;
	}

	public String getSelectreplyendtime() {
		return selectreplyendtime;
	}

	public void setSelectreplyendtime(String selectreplyendtime) {
		this.selectreplyendtime = selectreplyendtime;
	}

	public List<Integer> getIdlist() {
		return idlist;
	}

	public void setIdlist(List<Integer> idlist) {
		this.idlist = idlist;
	}
	
}