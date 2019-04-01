package sun.bz1.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户金额变化列表
 * 
 * 微信小程序查询用户金额变化时用到
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/20
 */

public class WechatUserMoney {

	/**
	 * 项目名称
	 */
	private String projectname;
	
	/**
	 * 金额
	 */
	private Double money;
	
	/**
	 * 金额变化时间
	 */
	private Date createtime;
	
	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
