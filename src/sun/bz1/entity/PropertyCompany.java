package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class PropertyCompany {

    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     *地产公司ID(DCGS+UUID)
     */
    private String propertycompanyid;

    /**
     * 地产公司名称
     */
    private String propertycompanyname;

    /**
     * 地产公司状态(Y:有效/N:无效)
     */
    private String propertycompanystate;

    /**
     * 地产公司创建人ID(YHBG+UUID)'
     */
    private String propertycompanycreateuserid;

    /**
     * 地产公司创建时间
     */
    private Date propertycompanycreatetime;

    /**
     * 地产公司更新人ID(YHBG+UUID)
     */
    private String propertycompanyupdateuserid;

    /**
     * 地产公司更新时间
     */
    private Date propertycompanyupdatetime;

    /**
     * 地产公司备注
     */
    private String propertycompanyremark;

    /**
     * 备份字段1
     */
    private String propertycompanyother1;

    /**
     * 备份字段2
     */
    private String propertycompanyother2;

    /**
     * 备份字段3
     */
    private String propertycompanyother3;

    // 版本信息
    /**
     * 版本号
     */
    private String version;

    // 分页数据
    /**
     * 当前页数(如果不进行分页，该条数据默认为-1)
     */
    private Integer pagenumber;

    /**
     * 每页的数据量
     */
    private Integer pagesize;

    // 操作数据
    /**
     * 根据idlist删除信息时使用
     */
    private List<Integer> idlist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertycompanyid() {
        return propertycompanyid;
    }

    public void setPropertycompanyid(String propertycompanyid) {
        this.propertycompanyid = propertycompanyid == null ? null : propertycompanyid.trim();
    }

    public String getPropertycompanyname() {
        return propertycompanyname;
    }

    public void setPropertycompanyname(String propertycompanyname) {
        this.propertycompanyname = propertycompanyname == null ? null : propertycompanyname.trim();
    }

    public String getPropertycompanystate() {
        return propertycompanystate;
    }

    public void setPropertycompanystate(String propertycompanystate) {
        this.propertycompanystate = propertycompanystate == null ? null : propertycompanystate.trim();
    }

    public String getPropertycompanycreateuserid() {
        return propertycompanycreateuserid;
    }

    public void setPropertycompanycreateuserid(String propertycompanycreateuserid) {
        this.propertycompanycreateuserid = propertycompanycreateuserid == null ? null : propertycompanycreateuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getPropertycompanycreatetime() {
        return propertycompanycreatetime;
    }

    public void setPropertycompanycreatetime(Date propertycompanycreatetime) {
        this.propertycompanycreatetime = propertycompanycreatetime;
    }

    public String getPropertycompanyupdateuserid() {
        return propertycompanyupdateuserid;
    }

    public void setPropertycompanyupdateuserid(String propertycompanyupdateuserid) {
        this.propertycompanyupdateuserid = propertycompanyupdateuserid == null ? null : propertycompanyupdateuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getPropertycompanyupdatetime() {
        return propertycompanyupdatetime;
    }

    public void setPropertycompanyupdatetime(Date propertycompanyupdatetime) {
        this.propertycompanyupdatetime = propertycompanyupdatetime;
    }

    public String getPropertycompanyremark() {
        return propertycompanyremark;
    }

    public void setPropertycompanyremark(String propertycompanyremark) {
        this.propertycompanyremark = propertycompanyremark == null ? null : propertycompanyremark.trim();
    }

    public String getPropertycompanyother1() {
        return propertycompanyother1;
    }

    public void setPropertycompanyother1(String propertycompanyother1) {
        this.propertycompanyother1 = propertycompanyother1 == null ? null : propertycompanyother1.trim();
    }

    public String getPropertycompanyother2() {
        return propertycompanyother2;
    }

    public void setPropertycompanyother2(String propertycompanyother2) {
        this.propertycompanyother2 = propertycompanyother2 == null ? null : propertycompanyother2.trim();
    }

    public String getPropertycompanyother3() {
        return propertycompanyother3;
    }

    public void setPropertycompanyother3(String propertycompanyother3) {
        this.propertycompanyother3 = propertycompanyother3 == null ? null : propertycompanyother3.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

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

    public List<Integer> getIdlist() {
        return idlist;
    }

    public void setIdlist(List<Integer> idlist) {
        this.idlist = idlist;
    }
}