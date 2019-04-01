package sun.bz1.entity;

import java.util.Date;
import java.util.List;

/**
 * 日志表
 *
 * 实体类
 *
 * @author ZY on 2018/11/28
 */
public class Log {

    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     * 日志ID（RZ+ UUID）
     */
    private String logid;

    /**
     * 日志操作人userid
     */
    private String loguserid;

    /**
     * 日志创建时间
     */
    private Date logcreatetime;

    /**
     * 状态（/DL:登录）
     */
    private String logstate;

    /**
     * 备份字段1
     */
    private String logother1;

    /**
     * 备份字段2
     */
    private String logother2;

    /**
     * 备份字段3
     */
    private String logother3;

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

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid == null ? null : logid.trim();
    }

    public String getLoguserid() {
        return loguserid;
    }

    public void setLoguserid(String loguserid) {
        this.loguserid = loguserid == null ? null : loguserid.trim();
    }

    public Date getLogcreatetime() {
        return logcreatetime;
    }

    public void setLogcreatetime(Date logcreatetime) {
        this.logcreatetime = logcreatetime;
    }

    public String getLogstate() {
        return logstate;
    }

    public void setLogstate(String logstate) {
        this.logstate = logstate == null ? null : logstate.trim();
    }

    public String getLogother1() {
        return logother1;
    }

    public void setLogother1(String logother1) {
        this.logother1 = logother1 == null ? null : logother1.trim();
    }

    public String getLogother2() {
        return logother2;
    }

    public void setLogother2(String logother2) {
        this.logother2 = logother2 == null ? null : logother2.trim();
    }

    public String getLogother3() {
        return logother3;
    }

    public void setLogother3(String logother3) {
        this.logother3 = logother3 == null ? null : logother3.trim();
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