package sun.bz1.entity;


/**
 *角色和部门实体类
 *
 */

public class roleanddepartment {
private Integer did;
private Integer rid;
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
public Integer getDid() {
	return did;
}
public void setDid(Integer did) {
	this.did = did;
}
public Integer getRid() {
	return rid;
}
public void setRid(Integer rid) {
	this.rid = rid;
}

}
