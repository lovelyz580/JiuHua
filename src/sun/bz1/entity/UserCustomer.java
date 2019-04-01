package sun.bz1.entity;

import java.util.List;

/**
 * 用户_客户表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/05
 */

public class UserCustomer {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 用户ID(YHBG+UUID)
	 */
	private String userid;

	/**
	 * 用户城市
	 */
	private String usercustomercity;

	/**
	 * 用户单位
	 */
	private String usercustomercompany;

	/**
	 * 用户所属组织
	 */
	private String usercustomerorganization;

	/**
	 * 用户地址
	 */
	private String usercustomeraddress;

	/**
	 * 用户_客户备注
	 */
	private String usercustomerremark;

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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsercustomercity() {
		return usercustomercity;
	}

	public void setUsercustomercity(String usercustomercity) {
		this.usercustomercity = usercustomercity;
	}

	public String getUsercustomercompany() {
		return usercustomercompany;
	}

	public void setUsercustomercompany(String usercustomercompany) {
		this.usercustomercompany = usercustomercompany;
	}

	public String getUsercustomerorganization() {
		return usercustomerorganization;
	}

	public void setUsercustomerorganization(String usercustomerorganization) {
		this.usercustomerorganization = usercustomerorganization;
	}

	public String getUsercustomeraddress() {
		return usercustomeraddress;
	}

	public void setUsercustomeraddress(String usercustomeraddress) {
		this.usercustomeraddress = usercustomeraddress;
	}

	public String getUsercustomerremark() {
		return usercustomerremark;
	}

	public void setUsercustomerremark(String usercustomerremark) {
		this.usercustomerremark = usercustomerremark;
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