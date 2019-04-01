package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 信用值_用户_维修工、安装队_初始值_设置表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class CreditUserServiceInitialSetup {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 初始值_用户真实姓名
	 */
	private Integer credituserservicerealname;

	/**
	 * 初始值_用户性别
	 */
	private Integer credituserservicesex;

	/**
	 * 初始值_用户手机号码
	 */
	private Integer credituserservicephone;

	/**
	 * 初始值_用户身份证号码
	 */
	private Integer credituserservicecodeid;

	/**
	 * 初始值_用户邮箱
	 */
	private Integer credituserserviceemail;

	/**
	 * 初始值_用户接单区域
	 */
	private Integer credituserservicearea;

	/**
	 * 初始值_用户接单规模
	 */
	private Integer credituserservicescale;

	/**
	 * 初始值_用户接单状态(Y:接单/N:不接单)
	 */
	private Integer credituserservicestate;

	/**
	 * 初始值_更新人ID(YHBG+UUID)
	 */
	private String credituserserviceupdateuserid;

	/**
	 * 初始值_更新时间
	 */
	private Date credituserserviceupdatetime;

	/**
	 * 初始值_备注
	 */
	private String credituserserviceremark;

	// 版本信息
	/**
	 * 版本号
	 */
	private String version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCredituserservicerealname() {
		return credituserservicerealname;
	}

	public void setCredituserservicerealname(Integer credituserservicerealname) {
		this.credituserservicerealname = credituserservicerealname;
	}

	public Integer getCredituserservicesex() {
		return credituserservicesex;
	}

	public void setCredituserservicesex(Integer credituserservicesex) {
		this.credituserservicesex = credituserservicesex;
	}

	public Integer getCredituserservicephone() {
		return credituserservicephone;
	}

	public void setCredituserservicephone(Integer credituserservicephone) {
		this.credituserservicephone = credituserservicephone;
	}

	public Integer getCredituserservicecodeid() {
		return credituserservicecodeid;
	}

	public void setCredituserservicecodeid(Integer credituserservicecodeid) {
		this.credituserservicecodeid = credituserservicecodeid;
	}

	public Integer getCredituserserviceemail() {
		return credituserserviceemail;
	}

	public void setCredituserserviceemail(Integer credituserserviceemail) {
		this.credituserserviceemail = credituserserviceemail;
	}

	public Integer getCredituserservicearea() {
		return credituserservicearea;
	}

	public void setCredituserservicearea(Integer credituserservicearea) {
		this.credituserservicearea = credituserservicearea;
	}

	public Integer getCredituserservicescale() {
		return credituserservicescale;
	}

	public void setCredituserservicescale(Integer credituserservicescale) {
		this.credituserservicescale = credituserservicescale;
	}

	public Integer getCredituserservicestate() {
		return credituserservicestate;
	}

	public void setCredituserservicestate(Integer credituserservicestate) {
		this.credituserservicestate = credituserservicestate;
	}

	public String getCredituserserviceupdateuserid() {
		return credituserserviceupdateuserid;
	}

	public void setCredituserserviceupdateuserid(String credituserserviceupdateuserid) {
		this.credituserserviceupdateuserid = credituserserviceupdateuserid == null ? null : credituserserviceupdateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getCredituserserviceupdatetime() {
		return credituserserviceupdatetime;
	}

	public void setCredituserserviceupdatetime(Date credituserserviceupdatetime) {
		this.credituserserviceupdatetime = credituserserviceupdatetime;
	}

	public String getCredituserserviceremark() {
		return credituserserviceremark;
	}

	public void setCredituserserviceremark(String credituserserviceremark) {
		this.credituserserviceremark = credituserserviceremark == null ? null : credituserserviceremark.trim();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}