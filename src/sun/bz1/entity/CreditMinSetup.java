package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 信用值_最低值_设置表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class CreditMinSetup {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 信用值最低值(用户的信用值低于这个值后不能下单、接单、抢单)
	 */
	private Double creditmin;

	/**
	 * 信用值设置更新人ID(YHBG+UUID)
	 */
	private String creditminupdateuserid;

	/**
	 * 信用值设置更新时间
	 */
	private Date creditminupdatetime;

	/**
	 * 信用值设置备注
	 */
	private String creditminremark;

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

	public Double getCreditmin() {
		return creditmin;
	}

	public void setCreditmin(Double creditmin) {
		this.creditmin = creditmin;
	}

	public String getCreditminupdateuserid() {
		return creditminupdateuserid;
	}

	public void setCreditminupdateuserid(String creditminupdateuserid) {
		this.creditminupdateuserid = creditminupdateuserid == null ? null : creditminupdateuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getCreditminupdatetime() {
		return creditminupdatetime;
	}

	public void setCreditminupdatetime(Date creditminupdatetime) {
		this.creditminupdatetime = creditminupdatetime;
	}

	public String getCreditminremark() {
		return creditminremark;
	}

	public void setCreditminremark(String creditminremark) {
		this.creditminremark = creditminremark == null ? null : creditminremark.trim();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}