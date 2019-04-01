package sun.bz1.entity;

/**
 *
 * 系统功能实体类
 * 
 * 2018-10-7
 *          -zz
 *
 */

public class systemfunction {
//应用系统
private String applicationSystem;
//应用系统名称
private String applicationSystemName;
//逻辑路径
private String addres;
//界面
private String interFace;
//功能
private String action;
//自增id
private Integer sid;

//分页用品
private Integer pagenumber;
private Integer pagesize;



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
public String getApplicationSystem() {
	return applicationSystem;
}
public void setApplicationSystem(String applicationSystem) {
	this.applicationSystem = applicationSystem;
}
public String getApplicationSystemName() {
	return applicationSystemName;
}
public void setApplicationSystemName(String applicationSystemName) {
	this.applicationSystemName = applicationSystemName;
}
public String getAddres() {
	return addres;
}
public void setAddres(String addres) {
	this.addres = addres;
}
public String getInterFace() {
	return interFace;
}
public void setInterFace(String interFace) {
	this.interFace = interFace;
}
public String getAction() {
	return action;
}
public void setAction(String action) {
	this.action = action;
}
public Integer getSid() {
	return sid;
}
public void setSid(Integer sid) {
	this.sid = sid;
}



}
