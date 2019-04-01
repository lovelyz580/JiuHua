package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class WechatTemplate {

    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     * 微信服务通知模板ID
     */
    private String wechattemplateid;

    /**
     * 微信服务通知模板标题
     */
    private String wechattemplatename;

    /**
     * 模板状态针对单子的状态用不同的模板ID
     */
    private String wechattemplateidstate;

    /**
     * 模板ID的创建时间
     */
    private Date createdate;

    /**
     * 备注
     */
    private String wechattemplateremark;

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

    public String getWechattemplateid() {
        return wechattemplateid;
    }

    public void setWechattemplateid(String wechattemplateid) {
        this.wechattemplateid = wechattemplateid;
    }

    public String getWechattemplatename() {
        return wechattemplatename;
    }

    public void setWechattemplatename(String wechattemplatename) {
        this.wechattemplatename = wechattemplatename;
    }

    public String getWechattemplateidstate() {
        return wechattemplateidstate;
    }

    public void setWechattemplateidstate(String wechattemplateidstate) {
        this.wechattemplateidstate = wechattemplateidstate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getWechattemplateremark() {
        return wechattemplateremark;
    }

    public void setWechattemplateremark(String wechattemplateremark) {
        this.wechattemplateremark = wechattemplateremark;
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