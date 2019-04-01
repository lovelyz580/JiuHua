package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;


/**
 * 派单方式表
 *
 * 实体类
 *
 * @author ZY on 2018/09/19
 */
public class DispatchMode {

    /**
     * 派单方式表-序号(自增主键)
     */
    private Integer id;

    /**
     * 派单方式表-序号(自增主键)
     */
    private String dispatchmode;

    /**
     * 派单方式表-序号(自增主键)
     */
    private String dispatchmodeuserid;

    /**
     * 派单方式表-序号(自增主键)
     */
    private Date dispatchmodeupdatetime;

    /**
     * 派单方式表-序号(自增主键)
     */
    private String dispatchmoderemark;

    /**
     * 派单方式表-序号(自增主键)
     */
    private String dispatchmodeother1;

    /**
     * 派单方式表-序号(自增主键)
     */
    private String dispatchmodeother2;

    /**
     * 派单方式表-序号(自增主键)
     */
    private String dispatchmodeother3;

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

    public String getDispatchmode() {
        return dispatchmode;
    }

    public void setDispatchmode(String dispatchmode) {
        this.dispatchmode = dispatchmode == null ? null : dispatchmode.trim();
    }

    public String getDispatchmodeuserid() {
        return dispatchmodeuserid;
    }

    public void setDispatchmodeuserid(String dispatchmodeuserid) {
        this.dispatchmodeuserid = dispatchmodeuserid == null ? null : dispatchmodeuserid.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getDispatchmodeupdatetime() {
        return dispatchmodeupdatetime;
    }

    public void setDispatchmodeupdatetime(Date dispatchmodeupdatetime) {
        this.dispatchmodeupdatetime = dispatchmodeupdatetime;
    }

    public String getDispatchmoderemark() {
        return dispatchmoderemark;
    }

    public void setDispatchmoderemark(String dispatchmoderemark) {
        this.dispatchmoderemark = dispatchmoderemark == null ? null : dispatchmoderemark.trim();
    }

    public String getDispatchmodeother1() {
        return dispatchmodeother1;
    }

    public void setDispatchmodeother1(String dispatchmodeother1) {
        this.dispatchmodeother1 = dispatchmodeother1 == null ? null : dispatchmodeother1.trim();
    }

    public String getDispatchmodeother2() {
        return dispatchmodeother2;
    }

    public void setDispatchmodeother2(String dispatchmodeother2) {
        this.dispatchmodeother2 = dispatchmodeother2 == null ? null : dispatchmodeother2.trim();
    }

    public String getDispatchmodeother3() {
        return dispatchmodeother3;
    }

    public void setDispatchmodeother3(String dispatchmodeother3) {
        this.dispatchmodeother3 = dispatchmodeother3 == null ? null : dispatchmodeother3.trim();
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