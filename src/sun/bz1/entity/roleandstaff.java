package sun.bz1.entity;



/**
 * 角色和人员实体类
 *
 */

public class roleandstaff {
private Integer rid;
private Integer pid;


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
public Integer getRid() {
	return rid;
}
public void setRid(Integer rid) {
	this.rid = rid;
}
public Integer getPid() {
	return pid;
}
public void setPid(Integer pid) {
	this.pid = pid;
}


}
