package sun.bz1.entity;

import java.util.Date;
import java.util.List;

/**
 * 用户付款表
 *
 * 实体类
 *
 * @author ZY on 2018/10/20
 */
public class UserPayment {
    /**
     *用户付款表-序号(自增主键)
     */
    private Integer id;

    /**
     *用户付款ID(YHFK+UUID)
     */
    private String userpaymentid;

    /**
     *维修单ID(D+年月日+时分秒+6位随机数)
     */
    private String orderid;

    /**
     *维修项目名称
     */
    private String orderprojectname;

    /**
     *维修总价(根据维修单价计算得出)
     */
    private Double orderpricetotalmoney;

    /**
     *汇款识别码 (JH+订单编号)
     */
    private String identificationcode;

    /**
     *汇款人ID(YHBG+UUID)
     */
    private String remitteruserid;

    /**
     *付款创建人ID(YHBG+UUID)
     */
    private String userpaymentcreateuserid;

    /**
     *付款创建时间
     */
    private Date userpaymentcreatetime;

    /**
     *付款更新人ID(YHBG+UUID)
     */
    private String userpaymentupdateuserid;

    /**
     *付款更新时间
     */
    private Date userpaymentupdatetime;

    /**
     *付款方式
     */
    private String userpaymentother1;

    /**
     *支付给维修的金额
     */
    private Double userpaymentother2;

    /**
     *备用字段3
     */
    private String userpaymentother3;

    /**
     *付款的状态
     */
    private String orderremark;

    /**
     *付款给维修工的状态
     */
    private String userpaymentplatformservicestate;
    /**
     *平台账号
     */
    private String userpaymentplatformaccount;
    /**
     *平台账号开户行地址
     */
    private String userpaymentserviceaccount;
    /**
     *平台账号开户人
     */
    private String userpaymentplatformaccountname;
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

    public String getUserpaymentid() {
        return userpaymentid;
    }

    public void setUserpaymentid(String userpaymentid) {
        this.userpaymentid = userpaymentid == null ? null : userpaymentid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getOrderprojectname() {
        return orderprojectname;
    }

    public void setOrderprojectname(String orderprojectname) {
        this.orderprojectname = orderprojectname == null ? null : orderprojectname.trim();
    }

    public Double getOrderpricetotalmoney() {
        return orderpricetotalmoney;
    }

    public void setOrderpricetotalmoney(Double orderpricetotalmoney) {
        this.orderpricetotalmoney = orderpricetotalmoney;
    }

    public String getIdentificationcode() {
        return identificationcode;
    }

    public void setIdentificationcode(String identificationcode) {
        this.identificationcode = identificationcode == null ? null : identificationcode.trim();
    }

    public String getRemitteruserid() {
        return remitteruserid;
    }

    public void setRemitteruserid(String remitteruserid) {
        this.remitteruserid = remitteruserid == null ? null : remitteruserid.trim();
    }

    public String getUserpaymentcreateuserid() {
        return userpaymentcreateuserid;
    }

    public void setUserpaymentcreateuserid(String userpaymentcreateuserid) {
        this.userpaymentcreateuserid = userpaymentcreateuserid == null ? null : userpaymentcreateuserid.trim();
    }

    public Date getUserpaymentcreatetime() {
        return userpaymentcreatetime;
    }

    public void setUserpaymentcreatetime(Date userpaymentcreatetime) {
        this.userpaymentcreatetime = userpaymentcreatetime;
    }

    public String getUserpaymentupdateuserid() {
        return userpaymentupdateuserid;
    }

    public void setUserpaymentupdateuserid(String userpaymentupdateuserid) {
        this.userpaymentupdateuserid = userpaymentupdateuserid == null ? null : userpaymentupdateuserid.trim();
    }

    public Date getUserpaymentupdatetime() {
        return userpaymentupdatetime;
    }

    public void setUserpaymentupdatetime(Date userpaymentupdatetime) {
        this.userpaymentupdatetime = userpaymentupdatetime;
    }

    public String getUserpaymentother1() {
        return userpaymentother1;
    }

    public void setUserpaymentother1(String userpaymentother1) {
        this.userpaymentother1 = userpaymentother1 == null ? null : userpaymentother1.trim();
    }

    public Double getUserpaymentother2() {
        return userpaymentother2;
    }

    public void setUserpaymentother2(Double userpaymentother2) {
        this.userpaymentother2 = userpaymentother2;
    }

    public String getUserpaymentother3() {
        return userpaymentother3;
    }

    public void setUserpaymentother3(String userpaymentother3) {
        this.userpaymentother3 = userpaymentother3 == null ? null : userpaymentother3.trim();
    }

    public String getOrderremark() {
        return orderremark;
    }

    public void setOrderremark(String orderremark) {
        this.orderremark = orderremark == null ? null : orderremark.trim();
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

    public String getUserpaymentplatformservicestate() {
        return userpaymentplatformservicestate;
    }

    public void setUserpaymentplatformservicestate(String userpaymentplatformservicestate) {
        this.userpaymentplatformservicestate = userpaymentplatformservicestate;
    }

    public String getUserpaymentplatformaccount() {
        return userpaymentplatformaccount;
    }

    public void setUserpaymentplatformaccount(String userpaymentplatformaccount) {
        this.userpaymentplatformaccount = userpaymentplatformaccount;
    }

    public String getUserpaymentserviceaccount() {
        return userpaymentserviceaccount;
    }

    public void setUserpaymentserviceaccount(String userpaymentserviceaccount) {
        this.userpaymentserviceaccount = userpaymentserviceaccount;
    }

    public String getUserpaymentplatformaccountname() {
        return userpaymentplatformaccountname;
    }

    public void setUserpaymentplatformaccountname(String userpaymentplatformaccountname) {
        this.userpaymentplatformaccountname = userpaymentplatformaccountname;
    }
}