package sun.bz1.entity;


/**
 * 角色实体类
 * @author Administrator
 *
 */

public class role {
//自增
private Integer rid;
//角色名
private String roleName;

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
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
@Override
public String toString() {
	return "role [rid=" + rid + ", roleName=" + roleName + "]";
}


}
