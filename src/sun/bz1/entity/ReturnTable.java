package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修人员退回维修单表(维修人员退回维修单时在该表中添加数据)
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/08
 */

public class ReturnTable {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 维修退回ID(WXTH+UUID)
	 */
	private String returnid;

	/**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 维修退回内容(原因)
	 */
	private String returncontent;

	/**
	 * 维修退回人ID(YHBG+UUID)
	 */
	private String returnuserid;

	/**
	 * 维修退回时间
	 */
	private Date returntime;

	/**
	 * 维修退回备注
	 */
	private String returnremark;

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

	public String getReturnid() {
		return returnid;
	}

	public void setReturnid(String returnid) {
		this.returnid = returnid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid == null ? null : orderid.trim();
	}

	public String getReturncontent() {
		return returncontent;
	}

	public void setReturncontent(String returncontent) {
		this.returncontent = returncontent == null ? null : returncontent.trim();
	}

	public String getReturnuserid() {
		return returnuserid;
	}

	public void setReturnuserid(String returnuserid) {
		this.returnuserid = returnuserid == null ? null : returnuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getReturntime() {
		return returntime;
	}

	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}

	public String getReturnremark() {
		return returnremark;
	}

	public void setReturnremark(String returnremark) {
		this.returnremark = returnremark == null ? null : returnremark.trim();
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