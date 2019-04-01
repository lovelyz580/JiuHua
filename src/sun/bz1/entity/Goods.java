package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 产品表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class Goods {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 产品ID(CPBG+UUID)
	 */
	private String goodsid;

	/**
	 * 产品名称
	 */
	private String goodsname;

	/**
	 * 产品类型(WX:维修工/AZ:安装队)
	 */
	private String goodstype;

	/**
	 * 产品状态(Y:有效/N:无效)
	 */
	private String goodsstate;

	/**
	 * 产品创建人ID(YHBG+UUID)
	 */
	private String goodscreateuserid;

	/**
	 * 产品创建时间
	 */
	private Date goodscreatetime;

	/**
	 * 产品更新人ID(YHBG+UUID)
	 */
	private String goodsupdateuserid;

	/**
	 * 产品更新时间
	 */
	private Date goodsupdatetime;

	/**
	 * 产品备注
	 */
	private String goodsremark;

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

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid == null ? null : goodsid.trim();
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname == null ? null : goodsname.trim();
	}

	public String getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype == null ? null : goodstype.trim();
	}

	public String getGoodsstate() {
		return goodsstate;
	}

	public void setGoodsstate(String goodsstate) {
		this.goodsstate = goodsstate == null ? null : goodsstate.trim();
	}

	public String getGoodscreateuserid() {
		return goodscreateuserid;
	}

	public void setGoodscreateuserid(String goodscreateuserid) {
		this.goodscreateuserid = goodscreateuserid == null ? null : goodscreateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getGoodscreatetime() {
		return goodscreatetime;
	}

	public void setGoodscreatetime(Date goodscreatetime) {
		this.goodscreatetime = goodscreatetime;
	}

	public String getGoodsupdateuserid() {
		return goodsupdateuserid;
	}

	public void setGoodsupdateuserid(String goodsupdateuserid) {
		this.goodsupdateuserid = goodsupdateuserid == null ? null : goodsupdateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getGoodsupdatetime() {
		return goodsupdatetime;
	}

	public void setGoodsupdatetime(Date goodsupdatetime) {
		this.goodsupdatetime = goodsupdatetime;
	}

	public String getGoodsremark() {
		return goodsremark;
	}

	public void setGoodsremark(String goodsremark) {
		this.goodsremark = goodsremark == null ? null : goodsremark.trim();
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