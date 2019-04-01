package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 信用值_用户_客户_初始值_设置表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class CreditUserCustomerInitialSetup {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 初始值_用户真实姓名
	 */
	private Integer creditusercustomerrealname;

	/**
	 * 初始值_用户性别
	 */
	private Integer creditusercustomersex;

	/**
	 * 初始值_用户手机号码
	 */
	private Integer creditusercustomerphone;

	/**
	 * 初始值_用户身份证号码
	 */
	private Integer creditusercustomercodeid;

	/**
	 * 初始值_用户邮箱
	 */
	private Integer creditusercustomeremail;

	/**
	 * 初始值_用户城市
	 */
	private Integer creditusercustomercity;

	/**
	 * 初始值_用户单位
	 */
	private Integer creditusercustomercompany;

	/**
	 * 初始值_用户所属组织
	 */
	private Integer creditusercustomerorganization;

	/**
	 * 初始值_用户地址
	 */
	private Integer creditusercustomeraddress;

	/**
	 * 初始值_更新人ID(YHBG+UUID)
	 */
	private String creditusercustomerupdateuserid;

	/**
	 * 初始值_更新时间
	 */
	private Date creditusercustomerupdatetime;

	/**
	 * 初始值_备注
	 */
	private String creditusercustomerremark;

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

	public Integer getCreditusercustomerrealname() {
		return creditusercustomerrealname;
	}

	public void setCreditusercustomerrealname(Integer creditusercustomerrealname) {
		this.creditusercustomerrealname = creditusercustomerrealname;
	}

	public Integer getCreditusercustomersex() {
		return creditusercustomersex;
	}

	public void setCreditusercustomersex(Integer creditusercustomersex) {
		this.creditusercustomersex = creditusercustomersex;
	}

	public Integer getCreditusercustomerphone() {
		return creditusercustomerphone;
	}

	public void setCreditusercustomerphone(Integer creditusercustomerphone) {
		this.creditusercustomerphone = creditusercustomerphone;
	}

	public Integer getCreditusercustomercodeid() {
		return creditusercustomercodeid;
	}

	public void setCreditusercustomercodeid(Integer creditusercustomercodeid) {
		this.creditusercustomercodeid = creditusercustomercodeid;
	}

	public Integer getCreditusercustomeremail() {
		return creditusercustomeremail;
	}

	public void setCreditusercustomeremail(Integer creditusercustomeremail) {
		this.creditusercustomeremail = creditusercustomeremail;
	}

	public Integer getCreditusercustomercity() {
		return creditusercustomercity;
	}

	public void setCreditusercustomercity(Integer creditusercustomercity) {
		this.creditusercustomercity = creditusercustomercity;
	}

	public Integer getCreditusercustomercompany() {
		return creditusercustomercompany;
	}

	public void setCreditusercustomercompany(Integer creditusercustomercompany) {
		this.creditusercustomercompany = creditusercustomercompany;
	}

	public Integer getCreditusercustomerorganization() {
		return creditusercustomerorganization;
	}

	public void setCreditusercustomerorganization(Integer creditusercustomerorganization) {
		this.creditusercustomerorganization = creditusercustomerorganization;
	}

	public Integer getCreditusercustomeraddress() {
		return creditusercustomeraddress;
	}

	public void setCreditusercustomeraddress(Integer creditusercustomeraddress) {
		this.creditusercustomeraddress = creditusercustomeraddress;
	}

	public String getCreditusercustomerupdateuserid() {
		return creditusercustomerupdateuserid;
	}

	public void setCreditusercustomerupdateuserid(String creditusercustomerupdateuserid) {
		this.creditusercustomerupdateuserid = creditusercustomerupdateuserid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getCreditusercustomerupdatetime() {
		return creditusercustomerupdatetime;
	}

	public void setCreditusercustomerupdatetime(Date creditusercustomerupdatetime) {
		this.creditusercustomerupdatetime = creditusercustomerupdatetime;
	}

	public String getCreditusercustomerremark() {
		return creditusercustomerremark;
	}

	public void setCreditusercustomerremark(String creditusercustomerremark) {
		this.creditusercustomerremark = creditusercustomerremark;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}