package sun.bz1.entity;

import java.util.Date;
import java.util.List;

/**
 * 订单打卡表
 *
 * 实体类
 *
 * @author ZY on 2018/12/15
 */
public class OrderBreakCard {

    /**
     * 序号(自增主键)
     */
    private Integer id;

    /**
     * 打卡ID（DDDK+UUID）
     */
    private String orderbreakcardid;

    /**
     * 订单打卡ID(D+年月日+时分秒+6位随机数)
     */
    private String orderid;

    /**
     * 打卡人userid(UUID)
     */
    private String orderbreakcarduserid;

    /**
     * 打卡人姓名
     */
    private String orderbreakcardusername;

    /**
     * 打卡创建时间
     */
    private Date orderbreakcardcreatetime;

    /**
     * 打卡具体地址
     */
    private String orderbreakcardaddress;

    /**
     * 备份字段1
     */
    private String orderbreakcardother1;

    /**
     * 备份字段2
     */
    private String orderbreakcardother2;

    /**
     * 备份字段3
     */
    private String orderbreakcardother3;

    /**
     * 备份字段4
     */
    private String orderbreakcardother4;

    /**
     * 备份字段5
     */
    private String orderbreakcardother5;
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

    /**
     * 根据idlist删除信息时使用
     */
    private List<Integer> idlist;

    /**
     * 根据uuidlist删除信息时使用
     */
    private List<String> uuidlist;

    /**
     * 按时间查询  今天的打卡数量
     */
    private String breakcardtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderbreakcardid() {
        return orderbreakcardid;
    }

    public void setOrderbreakcardid(String orderbreakcardid) {
        this.orderbreakcardid = orderbreakcardid == null ? null : orderbreakcardid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getOrderbreakcarduserid() {
        return orderbreakcarduserid;
    }

    public void setOrderbreakcarduserid(String orderbreakcarduserid) {
        this.orderbreakcarduserid = orderbreakcarduserid == null ? null : orderbreakcarduserid.trim();
    }

    public String getOrderbreakcardusername() {
        return orderbreakcardusername;
    }

    public void setOrderbreakcardusername(String orderbreakcardusername) {
        this.orderbreakcardusername = orderbreakcardusername == null ? null : orderbreakcardusername.trim();
    }

    public Date getOrderbreakcardcreatetime() {
        return orderbreakcardcreatetime;
    }

    public void setOrderbreakcardcreatetime(Date orderbreakcardcreatetime) {
        this.orderbreakcardcreatetime = orderbreakcardcreatetime;
    }

    public String getOrderbreakcardaddress() {
        return orderbreakcardaddress;
    }

    public void setOrderbreakcardaddress(String orderbreakcardaddress) {
        this.orderbreakcardaddress = orderbreakcardaddress == null ? null : orderbreakcardaddress.trim();
    }

    public String getOrderbreakcardother1() {
        return orderbreakcardother1;
    }

    public void setOrderbreakcardother1(String orderbreakcardother1) {
        this.orderbreakcardother1 = orderbreakcardother1 == null ? null : orderbreakcardother1.trim();
    }

    public String getOrderbreakcardother2() {
        return orderbreakcardother2;
    }

    public void setOrderbreakcardother2(String orderbreakcardother2) {
        this.orderbreakcardother2 = orderbreakcardother2 == null ? null : orderbreakcardother2.trim();
    }

    public String getOrderbreakcardother3() {
        return orderbreakcardother3;
    }

    public void setOrderbreakcardother3(String orderbreakcardother3) {
        this.orderbreakcardother3 = orderbreakcardother3 == null ? null : orderbreakcardother3.trim();
    }

    public String getOrderbreakcardother4() {
        return orderbreakcardother4;
    }

    public void setOrderbreakcardother4(String orderbreakcardother4) {
        this.orderbreakcardother4 = orderbreakcardother4 == null ? null : orderbreakcardother4.trim();
    }

    public String getOrderbreakcardother5() {
        return orderbreakcardother5;
    }

    public void setOrderbreakcardother5(String orderbreakcardother5) {
        this.orderbreakcardother5 = orderbreakcardother5 == null ? null : orderbreakcardother5.trim();
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

    public String getBreakcardtime() {
        return breakcardtime;
    }

    public void setBreakcardtime(String breakcardtime) {
        this.breakcardtime = breakcardtime;
    }
}