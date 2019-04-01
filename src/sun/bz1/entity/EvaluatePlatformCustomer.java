package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 维修人员评价平台_客户表
 *
 * 实体类
 *
 * @author ZY on 2019/01/21
 */
public class EvaluatePlatformCustomer {
    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     * 评价ID(PJKH+UUID)
     */
    private String evaluateplatformcustomerid;
    /**
     * 客户ID(被评价用户)(YHBG+UUID)
     */

    private String customeruserid;

    /**
     * 维修人员ID(评价用户)(YHBG+UUID)
     */
    private String serviceuserid;

    /**
     * 维修单ID(D+年月日+时分秒+6位随机数)
     */
    private String orderid;

    /**
     * 总得分
     */
    private Double evaluateplatformcustomerscore;

    /**
     * 评价意见
     */
    private String evaluateplatformcustomeropinion;

    /**
     * 评价创建时间
     */
    private Date evaluateplatformcustomercreatetime;

    /**
     * 评价备注
     */
    private String evaluateplatformcustomerremark;

    /**
     * 评价项ID(WPJX+UUID)多个用,隔开
     */
    private String evaluateplatformcustomersetupid;

    /**
     * 评价项名称多个用,隔开
     */
    private String evaluateplatformcustomersetupname;

    /**
     * 得分用于展示实体星星多个用,隔开
     */
    private String evaluateplatformcustomersetupscore;

    /**
     * 备份字段1
     */
    private String evaluateplatformcustomerother1;

    /**
     * 备份字段2
     */
    private String evaluateplatformcustomerother2;

    /**
     * 备份字段3
     */
    private String evaluateplatformcustomerother3;

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
     * 根据评价创建时间段查询时使用
     *
     * 查询时间段的开始时间
     */
    private String selectstarttime;

    /**
     * 根据评价创建时间段查询时使用
     *
     * 查询时间段的结束时间
     */
    private String selectendtime;

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

    public String getEvaluateplatformcustomerid() {
        return evaluateplatformcustomerid;
    }

    public void setEvaluateplatformcustomerid(String evaluateplatformcustomerid) {
        this.evaluateplatformcustomerid = evaluateplatformcustomerid == null ? null : evaluateplatformcustomerid.trim();
    }

    public String getCustomeruserid() {
        return customeruserid;
    }

    public void setCustomeruserid(String customeruserid) {
        this.customeruserid = customeruserid == null ? null : customeruserid.trim();
    }

    public String getServiceuserid() {
        return serviceuserid;
    }

    public void setServiceuserid(String serviceuserid) {
        this.serviceuserid = serviceuserid == null ? null : serviceuserid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Double getEvaluateplatformcustomerscore() {
        return evaluateplatformcustomerscore;
    }

    public void setEvaluateplatformcustomerscore(Double evaluateplatformcustomerscore) {
        this.evaluateplatformcustomerscore = evaluateplatformcustomerscore;
    }

    public String getEvaluateplatformcustomeropinion() {
        return evaluateplatformcustomeropinion;
    }

    public void setEvaluateplatformcustomeropinion(String evaluateplatformcustomeropinion) {
        this.evaluateplatformcustomeropinion = evaluateplatformcustomeropinion == null ? null : evaluateplatformcustomeropinion.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getEvaluateplatformcustomercreatetime() {
        return evaluateplatformcustomercreatetime;
    }

    public void setEvaluateplatformcustomercreatetime(Date evaluateplatformcustomercreatetime) {
        this.evaluateplatformcustomercreatetime = evaluateplatformcustomercreatetime;
    }

    public String getEvaluateplatformcustomerremark() {
        return evaluateplatformcustomerremark;
    }

    public void setEvaluateplatformcustomerremark(String evaluateplatformcustomerremark) {
        this.evaluateplatformcustomerremark = evaluateplatformcustomerremark == null ? null : evaluateplatformcustomerremark.trim();
    }

    public String getEvaluateplatformcustomersetupid() {
        return evaluateplatformcustomersetupid;
    }

    public void setEvaluateplatformcustomersetupid(String evaluateplatformcustomersetupid) {
        this.evaluateplatformcustomersetupid = evaluateplatformcustomersetupid == null ? null : evaluateplatformcustomersetupid.trim();
    }

    public String getEvaluateplatformcustomersetupname() {
        return evaluateplatformcustomersetupname;
    }

    public void setEvaluateplatformcustomersetupname(String evaluateplatformcustomersetupname) {
        this.evaluateplatformcustomersetupname = evaluateplatformcustomersetupname == null ? null : evaluateplatformcustomersetupname.trim();
    }

    public String getEvaluateplatformcustomersetupscore() {
        return evaluateplatformcustomersetupscore;
    }

    public void setEvaluateplatformcustomersetupscore(String evaluateplatformcustomersetupscore) {
        this.evaluateplatformcustomersetupscore = evaluateplatformcustomersetupscore == null ? null : evaluateplatformcustomersetupscore.trim();
    }

    public String getEvaluateplatformcustomerother1() {
        return evaluateplatformcustomerother1;
    }

    public void setEvaluateplatformcustomerother1(String evaluateplatformcustomerother1) {
        this.evaluateplatformcustomerother1 = evaluateplatformcustomerother1 == null ? null : evaluateplatformcustomerother1.trim();
    }

    public String getEvaluateplatformcustomerother2() {
        return evaluateplatformcustomerother2;
    }

    public void setEvaluateplatformcustomerother2(String evaluateplatformcustomerother2) {
        this.evaluateplatformcustomerother2 = evaluateplatformcustomerother2 == null ? null : evaluateplatformcustomerother2.trim();
    }

    public String getEvaluateplatformcustomerother3() {
        return evaluateplatformcustomerother3;
    }

    public void setEvaluateplatformcustomerother3(String evaluateplatformcustomerother3) {
        this.evaluateplatformcustomerother3 = evaluateplatformcustomerother3 == null ? null : evaluateplatformcustomerother3.trim();
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

    public String getSelectstarttime() {
        return selectstarttime;
    }

    public void setSelectstarttime(String selectstarttime) {
        this.selectstarttime = selectstarttime;
    }

    public String getSelectendtime() {
        return selectendtime;
    }

    public void setSelectendtime(String selectendtime) {
        this.selectendtime = selectendtime;
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