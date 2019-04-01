package sun.bz1.entity;

import java.util.List;

/**
 * 维修单详情确认表
 *
 * 实体类
 *
 * @author ZY on 2018/11/26
 */
public class OrderDetailConfirm {

    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     * 维修单详情确认ID(WXXQ+UUID)
     */
    private String orderdetailconfirmid;

    /**
     * 维修单ID(D+年月日+时分秒+6位随机数)
     */
    private String orderid;

    /**
     * 产品ID(CPBG+UUID)
     */
    private String goodsid;

    /**
     * 项目ID(XMBG+UUID)
     */
    private String projectid;

    /**
     * 维修单详情确认数量
     */
    private Integer orderdetailconfirmnumber;

    /**
     * 维修单详情确认推荐价(拦标价表中的推荐价)
     */
    private String interceptid;

    /**
     * 维修单详情推荐价(拦标价表中的推荐价)
     */
    private Double orderdetailconfirminterceptmoney;

    /**
     * 维修单详情确认推荐总价(数量*推荐价)
     */
    private Double orderdetailconfirmintercepttotalmoney;

    /**
     * 维修单详情确认维修单价(确认维修人员后)(维修单价表中的维修单价)(暂时没用到该字段)
     */
    private String priceid;

    /**
     * 维修单详情维修单价(确认维修人员后)(维修单价表中的维修单价)(暂时没用到该字段)
     */
    private Double orderdetailconfirmpricemoney;

    /**
     * 维修单详情确认维修总价(确认维修人员后)(数量*维修单价)(暂时没用到该字段)
     */
    private Double orderdetailconfirmpricetotalmoney;

    /**
     * 维修单详情确认备注
     */
    private String orderdetailconfirmremark;

    /**
     * 产品表-产品名称
     */
    private String goodsname;

    /**
     * 项目表-项目名称
     */
    private String projectname;

    // 版本信息
    /**
     * 版本号
     */
    private String version;

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

    public String getOrderdetailconfirmid() {
        return orderdetailconfirmid;
    }

    public void setOrderdetailconfirmid(String orderdetailconfirmid) {
        this.orderdetailconfirmid = orderdetailconfirmid == null ? null : orderdetailconfirmid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }

    public Integer getOrderdetailconfirmnumber() {
        return orderdetailconfirmnumber;
    }

    public void setOrderdetailconfirmnumber(Integer orderdetailconfirmnumber) {
        this.orderdetailconfirmnumber = orderdetailconfirmnumber;
    }

    public String getInterceptid() {
        return interceptid;
    }

    public void setInterceptid(String interceptid) {
        this.interceptid = interceptid == null ? null : interceptid.trim();
    }

    public Double getOrderdetailconfirmintercepttotalmoney() {
        return orderdetailconfirmintercepttotalmoney;
    }

    public void setOrderdetailconfirmintercepttotalmoney(Double orderdetailconfirmintercepttotalmoney) {
        this.orderdetailconfirmintercepttotalmoney = orderdetailconfirmintercepttotalmoney;
    }

    public String getPriceid() {
        return priceid;
    }

    public void setPriceid(String priceid) {
        this.priceid = priceid == null ? null : priceid.trim();
    }

    public Double getOrderdetailconfirmpricetotalmoney() {
        return orderdetailconfirmpricetotalmoney;
    }

    public void setOrderdetailconfirmpricetotalmoney(Double orderdetailconfirmpricetotalmoney) {
        this.orderdetailconfirmpricetotalmoney = orderdetailconfirmpricetotalmoney;
    }

    public String getOrderdetailconfirmremark() {
        return orderdetailconfirmremark;
    }

    public void setOrderdetailconfirmremark(String orderdetailconfirmremark) {
        this.orderdetailconfirmremark = orderdetailconfirmremark == null ? null : orderdetailconfirmremark.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public Double getOrderdetailconfirminterceptmoney() {
        return orderdetailconfirminterceptmoney;
    }

    public void setOrderdetailconfirminterceptmoney(Double orderdetailconfirminterceptmoney) {
        this.orderdetailconfirminterceptmoney = orderdetailconfirminterceptmoney;
    }

    public Double getOrderdetailconfirmpricemoney() {
        return orderdetailconfirmpricemoney;
    }

    public void setOrderdetailconfirmpricemoney(Double orderdetailconfirmpricemoney) {
        this.orderdetailconfirmpricemoney = orderdetailconfirmpricemoney;
    }
}