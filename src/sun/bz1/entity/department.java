package sun.bz1.entity;

/**
 * 部门实体类
 * @author Administrator
 *
 */

public class department {
//自身id
private Integer did;
//部门名
private String  departmentName;
//上级部门id
private Integer cid;
//部门编码
private String code;
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
public String getDepartmentName() {
	return departmentName;
}
public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
@Override
public String toString() {
	return "department [did=" + did + ", departmentName=" + departmentName
			+ ", cid=" + cid + ", code=" + code + "]";
}


}
