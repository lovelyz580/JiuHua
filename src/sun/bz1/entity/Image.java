package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
* 首页图片表
*
* 实体类
*
* @author ZY on 2018/11/29
*/
public class Image {

    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     * 首页图片ID(SYTP+UUID)
     */
    private String imageid;

    /**
     * 首页图片存放地址
     */
    private String imageurl;

    /**
     * 首页跳转URL
     */
    private String imageskipurl;

    /**
     * /Y：有效，/N：无效
     */
    private String imagestate;

    /**
     * 图片备注
     */
    private String imageremark;

    /**
     * 图片类型(SYTP:首页图片/ZMHHR:招募合伙人/QTFW:其他服务；如果是首页详情中的图片会有首页图片的imageid)
     */
    private String imagetype;

    /**
     * 创建时间
     */
    private Date imagecreatetime;

    /**
     * 图片备份字段1
     */
    private String imageother1;

    /**
     * 图片备份字段2
     */
    private String imageother2;

    /**
     * 这个字段没有什么用
     */
    private String file;

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

    /**
     * 根据uuidlist删除信息时使用
     */
    private List<String> uuidlist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid == null ? null : imageid.trim();
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }

    public String getImageskipurl() {
        return imageskipurl;
    }

    public void setImageskipurl(String imageskipurl) {
        this.imageskipurl = imageskipurl == null ? null : imageskipurl.trim();
    }

    public String getImagestate() {
        return imagestate;
    }

    public void setImagestate(String imagestate) {
        this.imagestate = imagestate == null ? null : imagestate.trim();
    }

    public String getImageremark() {
        return imageremark;
    }

    public void setImageremark(String imageremark) {
        this.imageremark = imageremark == null ? null : imageremark.trim();
    }

    public String getImagetype() {
        return imagetype;
    }

    public void setImagetype(String imagetype) {
        this.imagetype = imagetype == null ? null : imagetype.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getImagecreatetime() {
        return imagecreatetime;
    }

    public void setImagecreatetime(Date imagecreatetime) {
        this.imagecreatetime = imagecreatetime;
    }

    public String getImageother1() {
        return imageother1;
    }

    public void setImageother1(String imageother1) {
        this.imageother1 = imageother1 == null ? null : imageother1.trim();
    }

    public String getImageother2() {
        return imageother2;
    }

    public void setImageother2(String imageother2) {
        this.imageother2 = imageother2 == null ? null : imageother2.trim();
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

    public List<String> getUuidlist() {
        return uuidlist;
    }

    public void setUuidlist(List<String> uuidlist) {
        this.uuidlist = uuidlist;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}