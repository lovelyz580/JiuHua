package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 用户表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/03
 */

public class UserExcel {


	/**
	 * 用户名
	 */
	private String username;


	/**
	 * 用户手机号码
	 */
	private String userphone;


	/**
	 * 用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
	 */
	private String userrole;

	/**
	 * 用户状态(BJ:编辑/TJ:提交/PZ:批准...)
	 */
	private String userstate;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}
}