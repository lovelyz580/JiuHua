package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 分享视频表
 *
 * 实体类
 *
 * @author ZY on 2018/11/30
 */
public class ShareVideo {

    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     * 视频ID(FXSP+UUID)
     */
    private String sharevideoid;

    /**
     * 视频名称
     */
    private String sharevideoname;

    /**
     * 视频存放地址
     */
    private String sharevideourl;

    /**
     * 首页跳转URL
     */
    private String sharevideoskipurl;

    /**
     * /Y：有效，/N：无效
     */
    private String sharevideostate;

    /**
     * 视频备注
     */
    private String sharevideoremark;

    /**
     * 视频类型/WX：维修，/AZ：安装
     */
    private String sharevideotype;

    /**
     * 创建时间
     */
    private Date sharevideocreatetime;

    /**
     * 视频备份字段1
     */
    private String sharevideoother1;

    /**
     * 视频备份字段2
     */
    private String sharevideoother2;

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

    public String getSharevideoid() {
        return sharevideoid;
    }

    public void setSharevideoid(String sharevideoid) {
        this.sharevideoid = sharevideoid == null ? null : sharevideoid.trim();
    }

    public String getSharevideoname() {
        return sharevideoname;
    }

    public void setSharevideoname(String sharevideoname) {
        this.sharevideoname = sharevideoname == null ? null : sharevideoname.trim();
    }

    public String getSharevideourl() {
        return sharevideourl;
    }

    public void setSharevideourl(String sharevideourl) {
        this.sharevideourl = sharevideourl == null ? null : sharevideourl.trim();
    }

    public String getSharevideoskipurl() {
        return sharevideoskipurl;
    }

    public void setSharevideoskipurl(String sharevideoskipurl) {
        this.sharevideoskipurl = sharevideoskipurl == null ? null : sharevideoskipurl.trim();
    }

    public String getSharevideostate() {
        return sharevideostate;
    }

    public void setSharevideostate(String sharevideostate) {
        this.sharevideostate = sharevideostate == null ? null : sharevideostate.trim();
    }

    public String getSharevideoremark() {
        return sharevideoremark;
    }

    public void setSharevideoremark(String sharevideoremark) {
        this.sharevideoremark = sharevideoremark == null ? null : sharevideoremark.trim();
    }

    public String getSharevideotype() {
        return sharevideotype;
    }

    public void setSharevideotype(String sharevideotype) {
        this.sharevideotype = sharevideotype == null ? null : sharevideotype.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getSharevideocreatetime() {
        return sharevideocreatetime;
    }

    public void setSharevideocreatetime(Date sharevideocreatetime) {
        this.sharevideocreatetime = sharevideocreatetime;
    }

    public String getSharevideoother1() {
        return sharevideoother1;
    }

    public void setSharevideoother1(String sharevideoother1) {
        this.sharevideoother1 = sharevideoother1 == null ? null : sharevideoother1.trim();
    }

    public String getSharevideoother2() {
        return sharevideoother2;
    }

    public void setSharevideoother2(String sharevideoother2) {
        this.sharevideoother2 = sharevideoother2 == null ? null : sharevideoother2.trim();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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
}