package sun.bz1.entity;

import java.util.Date;
import java.util.List;


/**
 * 派单确认表
 *
 * 实体类
 *
 * @author ZY on 2018/09/19
 */
public class DistributeConfirm {
    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     * 派单确认表ID(PDQR+UUID)
     */
    private String distributeconfirmid;

    /**
     * 维修单ID(D+年月日+时分秒+6位随机数)
     */
    private String orderid;

    /**
     * 派单维修人员ID(YHBG+UUID)
     */
    private String distributeconfirmserviceuserid;

    /**
     * 维修距离(确认维修人员后，才会添加这个字段的数据)
     */
    private Double distributeconfirmorderdistance;

    /**
     * 维修推荐总价(下单时，对应的推荐总价)
     */
    private Double distributeconfirmorderintercepttotalmoney;

    /**
     * 维修总价(根据维修单价计算得出)(最终结算总价，在维修验证表中)(确认维修人员后，才会添加这个字段的数据)
     */
    private Double distributeconfirmorderpricetotalmoney;

    /**
     * 维修差旅费总价(根据差旅费*距离计算得出)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中，维修总价+维修差旅费总价)
     */
    private Double distributeconfirmordertraveltotalmoney;

    /**
     * 派单确认创建人ID(YHBG+UUID)
     */
    private String distributeconfirmcreateuserid;

    /**
     * 派单确认创建时间
     */
    private Date distributeconfirmcreatetime;

    /**
     * 维修、安装确认派单：SY，客户确认派单：CY，未确认派单：N，维修、安装取消派单：SQX，客户取消：CQX，
     */
    private String distributeconfirmstate;

    /**
     * 预留字段1
     */
    private String distributeconfirmother1;

    /**
     * 预留字段2
     */
    private String distributeconfirmother2;

    /**
     * 预留字段3
     */
    private String distributeconfirmother3;

    /**
     * 差率费推荐总价
     */
    private Double distributeconfirmother4;

    /**
     * 派单确认更新人ID(YHBG+UUID)
     */
    private String distributeconfirmupdateuserid;

    /**
     * 派单确认更新时间
     */
    private Date distributeconfirmupdatetime;

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

    public String getDistributeconfirmid() {
        return distributeconfirmid;
    }

    public void setDistributeconfirmid(String distributeconfirmid) {
        this.distributeconfirmid = distributeconfirmid == null ? null : distributeconfirmid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getDistributeconfirmserviceuserid() {
        return distributeconfirmserviceuserid;
    }

    public void setDistributeconfirmserviceuserid(String distributeconfirmserviceuserid) {
        this.distributeconfirmserviceuserid = distributeconfirmserviceuserid == null ? null : distributeconfirmserviceuserid.trim();
    }

    public Double getDistributeconfirmorderdistance() {
        return distributeconfirmorderdistance;
    }

    public void setDistributeconfirmorderdistance(Double distributeconfirmorderdistance) {
        this.distributeconfirmorderdistance = distributeconfirmorderdistance;
    }

    public Double getDistributeconfirmorderintercepttotalmoney() {
        return distributeconfirmorderintercepttotalmoney;
    }

    public void setDistributeconfirmorderintercepttotalmoney(Double distributeconfirmorderintercepttotalmoney) {
        this.distributeconfirmorderintercepttotalmoney = distributeconfirmorderintercepttotalmoney;
    }

    public Double getDistributeconfirmorderpricetotalmoney() {
        return distributeconfirmorderpricetotalmoney;
    }

    public void setDistributeconfirmorderpricetotalmoney(Double distributeconfirmorderpricetotalmoney) {
        this.distributeconfirmorderpricetotalmoney = distributeconfirmorderpricetotalmoney;
    }

    public Double getDistributeconfirmordertraveltotalmoney() {
        return distributeconfirmordertraveltotalmoney;
    }

    public void setDistributeconfirmordertraveltotalmoney(Double distributeconfirmordertraveltotalmoney) {
        this.distributeconfirmordertraveltotalmoney = distributeconfirmordertraveltotalmoney;
    }

    public String getDistributeconfirmcreateuserid() {
        return distributeconfirmcreateuserid;
    }

    public void setDistributeconfirmcreateuserid(String distributeconfirmcreateuserid) {
        this.distributeconfirmcreateuserid = distributeconfirmcreateuserid == null ? null : distributeconfirmcreateuserid.trim();
    }

    public Date getDistributeconfirmcreatetime() {
        return distributeconfirmcreatetime;
    }

    public void setDistributeconfirmcreatetime(Date distributeconfirmcreatetime) {
        this.distributeconfirmcreatetime = distributeconfirmcreatetime;
    }

    public String getDistributeconfirmstate() {
        return distributeconfirmstate;
    }

    public void setDistributeconfirmstate(String distributeconfirmstate) {
        this.distributeconfirmstate = distributeconfirmstate == null ? null : distributeconfirmstate.trim();
    }

    public String getDistributeconfirmother1() {
        return distributeconfirmother1;
    }

    public void setDistributeconfirmother1(String distributeconfirmother1) {
        this.distributeconfirmother1 = distributeconfirmother1 == null ? null : distributeconfirmother1.trim();
    }

    public String getDistributeconfirmother2() {
        return distributeconfirmother2;
    }

    public void setDistributeconfirmother2(String distributeconfirmother2) {
        this.distributeconfirmother2 = distributeconfirmother2 == null ? null : distributeconfirmother2.trim();
    }

    public String getDistributeconfirmother3() {
        return distributeconfirmother3;
    }

    public void setDistributeconfirmother3(String distributeconfirmother3) {
        this.distributeconfirmother3 = distributeconfirmother3 == null ? null : distributeconfirmother3.trim();
    }

    public Double getDistributeconfirmother4() {
        return distributeconfirmother4;
    }

    public void setDistributeconfirmother4(Double distributeconfirmother4) {
        this.distributeconfirmother4 = distributeconfirmother4;
    }

    public String getDistributeconfirmupdateuserid() {
        return distributeconfirmupdateuserid;
    }

    public void setDistributeconfirmupdateuserid(String distributeconfirmupdateuserid) {
        this.distributeconfirmupdateuserid = distributeconfirmupdateuserid == null ? null : distributeconfirmupdateuserid.trim();
    }

    public Date getDistributeconfirmupdatetime() {
        return distributeconfirmupdatetime;
    }

    public void setDistributeconfirmupdatetime(Date distributeconfirmupdatetime) {
        this.distributeconfirmupdatetime = distributeconfirmupdatetime;
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