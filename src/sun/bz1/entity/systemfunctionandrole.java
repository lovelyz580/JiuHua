package sun.bz1.entity;


/**
 *关联表实体
 *
 */

public class systemfunctionandrole {
private Integer rid;
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
public Integer getRid() {
	return rid;
}
public void setRid(Integer rid) {
	this.rid = rid;
}
public Integer getSid() {
	return sid;
}
public void setSid(Integer sid) {
	this.sid = sid;
}

}
