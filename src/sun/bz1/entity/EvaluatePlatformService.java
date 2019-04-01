package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 客户评价平台_维修人员表
 *
 * 实体类
 *
 * @author ZY on 2019/01/21
 */
public class EvaluatePlatformService {

    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     * 评价ID(PJWX+UUID)
     */
    private String evaluateplatformserviceid;

    /**
     * 维修人员ID(被评价用户)(YHBG+UUID)
     */
    private String serviceuserid;

    /**
     * 客户ID(评价用户)(YHBG+UUID)
     */
    private String customeruserid;

    /**
     * 维修单ID(D+年月日+时分秒+6位随机数)
     */
    private String orderid;

    /**
     * 总得分
     */
    private Double evaluateplatformservicescore;

    /**
     * 评价意见
     */
    private String evaluateplatformserviceopinion;

    /**
     * 评价创建时间
     */
    private Date evaluateplatformservicecreatetime;


    /**
     * 评价备注
     */
    private String evaluateplatformserviceremark;

    /**
     * 评价项ID(WPJX+UUID)多个用,隔开
     */
    private String evaluateplatformservicesetupid;

    /**
     * 评价项名称多个用,隔开
     */
    private String evaluateplatformservicesetupname;

    /**
     * 得分用于展示实体星星多个用,隔开
     */
    private String evaluateplatformservicesetupscore;

    /**
     * 备份字段1
     */
    private String evaluateplatformserviceother1;

    /**
     * 备份字段2
     */
    private String evaluateplatformserviceother2;

    /**
     * 备份字段3
     */
    private String evaluateplatformserviceother3;

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

    public String getEvaluateplatformserviceid() {
        return evaluateplatformserviceid;
    }

    public void setEvaluateplatformserviceid(String evaluateplatformserviceid) {
        this.evaluateplatformserviceid = evaluateplatformserviceid == null ? null : evaluateplatformserviceid.trim();
    }

    public String getServiceuserid() {
        return serviceuserid;
    }

    public void setServiceuserid(String serviceuserid) {
        this.serviceuserid = serviceuserid == null ? null : serviceuserid.trim();
    }

    public String getCustomeruserid() {
        return customeruserid;
    }

    public void setCustomeruserid(String customeruserid) {
        this.customeruserid = customeruserid == null ? null : customeruserid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Double getEvaluateplatformservicescore() {
        return evaluateplatformservicescore;
    }

    public void setEvaluateplatformservicescore(Double evaluateplatformservicescore) {
        this.evaluateplatformservicescore = evaluateplatformservicescore;
    }

    public String getEvaluateplatformserviceopinion() {
        return evaluateplatformserviceopinion;
    }

    public void setEvaluateplatformserviceopinion(String evaluateplatformserviceopinion) {
        this.evaluateplatformserviceopinion = evaluateplatformserviceopinion == null ? null : evaluateplatformserviceopinion.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getEvaluateplatformservicecreatetime() {
        return evaluateplatformservicecreatetime;
    }

    public void setEvaluateplatformservicecreatetime(Date evaluateplatformservicecreatetime) {
        this.evaluateplatformservicecreatetime = evaluateplatformservicecreatetime;
    }

    public String getEvaluateplatformserviceremark() {
        return evaluateplatformserviceremark;
    }

    public void setEvaluateplatformserviceremark(String evaluateplatformserviceremark) {
        this.evaluateplatformserviceremark = evaluateplatformserviceremark == null ? null : evaluateplatformserviceremark.trim();
    }

    public String getEvaluateplatformservicesetupid() {
        return evaluateplatformservicesetupid;
    }

    public void setEvaluateplatformservicesetupid(String evaluateplatformservicesetupid) {
        this.evaluateplatformservicesetupid = evaluateplatformservicesetupid == null ? null : evaluateplatformservicesetupid.trim();
    }

    public String getEvaluateplatformservicesetupname() {
        return evaluateplatformservicesetupname;
    }

    public void setEvaluateplatformservicesetupname(String evaluateplatformservicesetupname) {
        this.evaluateplatformservicesetupname = evaluateplatformservicesetupname == null ? null : evaluateplatformservicesetupname.trim();
    }

    public String getEvaluateplatformservicesetupscore() {
        return evaluateplatformservicesetupscore;
    }

    public void setEvaluateplatformservicesetupscore(String evaluateplatformservicesetupscore) {
        this.evaluateplatformservicesetupscore = evaluateplatformservicesetupscore == null ? null : evaluateplatformservicesetupscore.trim();
    }

    public String getEvaluateplatformserviceother1() {
        return evaluateplatformserviceother1;
    }

    public void setEvaluateplatformserviceother1(String evaluateplatformserviceother1) {
        this.evaluateplatformserviceother1 = evaluateplatformserviceother1 == null ? null : evaluateplatformserviceother1.trim();
    }

    public String getEvaluateplatformserviceother2() {
        return evaluateplatformserviceother2;
    }

    public void setEvaluateplatformserviceother2(String evaluateplatformserviceother2) {
        this.evaluateplatformserviceother2 = evaluateplatformserviceother2 == null ? null : evaluateplatformserviceother2.trim();
    }

    public String getEvaluateplatformserviceother3() {
        return evaluateplatformserviceother3;
    }

    public void setEvaluateplatformserviceother3(String evaluateplatformserviceother3) {
        this.evaluateplatformserviceother3 = evaluateplatformserviceother3 == null ? null : evaluateplatformserviceother3.trim();
    }
}