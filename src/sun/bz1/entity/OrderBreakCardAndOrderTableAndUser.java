package sun.bz1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 订单打卡表-订单表-用户表
 *
 * 实体类
 *
 * @author ZY on 2018/12/15
 */
public class OrderBreakCardAndOrderTableAndUser {

    /**
     * 订单打卡表-序号(自增主键)
     */
    private Integer id;

    /**
     * 订单打卡表-打卡ID（DDDK+UUID）
     */
    private String orderbreakcardid;

    /**
     * 订单打卡表-订单打卡ID(D+年月日+时分秒+6位随机数)
     */
    private String orderid;

    /**
     * 订单打卡表-打卡人userid(UUID)
     */
    private String orderbreakcarduserid;

    /**
     * 订单打卡表-打卡人姓名
     */
    private String orderbreakcardusername;

    /**
     * 订单打卡表-打卡创建时间
     */
    private Date orderbreakcardcreatetime;

    /**
     * 订单打卡表-打卡具体地址
     */
    private String orderbreakcardaddress;

    /**
     * 订单打卡表-备份字段1
     */
    private String orderbreakcardother1;

    /**
     * 订单打卡表-备份字段2
     */
    private String orderbreakcardother2;

    /**
     * 订单打卡表-备份字段3
     */
    private String orderbreakcardother3;

    /**
     * 订单打卡表-备份字段4
     */
    private String orderbreakcardother4;

    /**
     * 订单打卡表-备份字段5
     */
    private String orderbreakcardother5;

    /**
     * 维修单表-维修项目名称
     */
    private String orderprojectname;

    /**
     * 维修单表-维修联系人(报修人)名称
     */
    private String ordercontactperson;

    /**
     * 维修单表-维修联系电话
     */
    private String ordercontactphone;

    /**
     * 维修单表-接单区域ID(JDQY+UUID)
     */
    private String orderaddress;

    /**
     * 维修单表-维修建筑类型ID(JZLX+UUID)
     */
    private String buildingtypeid;

    /**
     * 维修单表-维修计划时间
     */
    private Date orderplantime;

    /**
     * 维修单表-维修竞标结束时间(抢单类型时，才会添加这个字段的数据
     */
    private Date orderbiddingendtime;

    /**
     * 维修单表-维修项目类型(WX:维修工/AZ:安装队)
     */
    private String orderprojecttype;

    /**
     * 维修单表-维修位置信息(当前位置信息)
     */
    private String orderposition;

    /**
     * 维修单表-维修位置纬度
     */
    private Double orderlatitude;

    /**
     * 维修单表-维修位置经度
     */
    private Double orderlongitude;

    /**
     * 维修单表-维修距离(确认维修人员后，才会添加这个字段的数据)
     */
    private Double orderdistance;

    /**
     * 维修单表-维修拦标总价(下单时，对应的拦标总价)
     */
    private Double orderintercepttotalmoney;

    /**
     * 维修单表-维修差旅费推荐总价(根据差旅费推荐价*距离计算得出)(确认维修人员后，才会添加这个字段的数据)
     */
    private Double orderintercepttraveltotalmoney;


    /**
     * 维修单表-维修总价(根据维修单价计算得出)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中，维修总价+维修差旅费总价)
     */
    private Double orderpricetotalmoney;

    /**
     * 维修人员自己看的没有报价系数
     *
     * 维修单表-维修总价(根据维修单价计算得出)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中，维修总价+维修差旅费总价)
     */
    private Double orderpriceservicetotalmoney;

    /**
     * 维修单表-维修差旅费总价(根据差旅费*距离计算得出)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中，维修总价+维修差旅费总价)
     */
    private Double ordertraveltotalmoney;

    /**
     * 维修单表-总价(维修总价+维修差旅费总价)(确认维修人员后，才会添加这个字段的数据)(最终结算总价，在维修验证表中)
     *
     * 业务字段，数据表中没有该字段
     */
    private Double ordertotalmoney;

    /**
     * 维修单表-维修类型(PD:派单/QD:抢单)
     */
    private String ordertype;

    /**
     * 维修单表-维修状态(BJ:编辑/FB:发布/QD:抢单/JS:接收/WXTH:维修人员退回/KHQX:客户取消/QRQX:维修人员确认取消/SQYS:申请验收/YSHG:验收合格/YSBHG:验收不合格/GBDD:关闭订单)
     */
    private String orderstate;

    /**
     * 维修单表-维修人员ID(YHBG+UUID)(确认维修人员后，才会添加这个字段的数据)
     */
    private String orderserviceuserid;

    /**
     * 维修单表-维修创建人ID(YHBG+UUID)
     */
    private String ordercreateuserid;

    /**
     * 维修单表-维修创建时间
     */
    private Date ordercreatetime;

    /**
     * 维修单表-维修更新人ID(YHBG+UUID)
     */
    private String orderupdateuserid;

    /**
     * 维修单表-维修更新时间
     */
    private Date orderupdatetime;

    /**
     * 维修单表-维修申请验收人ID(YHBG+UUID)(申请验收后，才会添加这个字段的数据)
     */
    private String orderapplycheckuserid;

    /**
     * 维修申请验收时间(申请验收后，才会添加这个字段的数据)
     */
    private Date orderapplychecktime;

    /**
     * 维修单表-维修单备注
     */
    private String orderremark;

    /**
     * 维修单表-多次申请验收，是否全部申请验收：ALL_CHECK
     */
    private String ordercheckallstate;
    /**
     * 维修单表-订单中维修的日工资
     */
    private Double orderdaymoney;

    /**
     * 维修单表-订单中维修的天数
     */
    private Integer orderday;

    /**
     * 维修单表-订单中维修的人数
     */
    private Integer orderpeoplenumber;

    /**
     * 维修单表-订单中的材料费
     */
    private Double ordermaterialmoney;

    /**
     * 用户表-用户ID(YHBG+UUID)
     */
    private String userid;

    /**
     * 用户表-用户名
     */
    private String username;

    /**
     * 用户表-用户密码
     */
    private String userpassword;

    /**
     * 用户表-用户密码盐
     */
    private String usersalt;

    /**
     * 用户表-用户加密密码
     */
    private String userencryptpassword;

    /**
     * 用户表-用户真实姓名
     */
    private String userrealname;

    /**
     * 用户表-用户性别(Y:男/X:女)
     */
    private String usersex;

    /**
     * 用户表-用户手机号码
     */
    private String userphone;

    /**
     * 用户表-用户身份证号码
     */
    private String usercodeid;

    /**
     * 用户表-用户邮箱
     */
    private String useremail;

    /**
     * 用户表-用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
     */
    private String userrole;

    /**
     * 用户表-用户状态(BJ:编辑/TJ:提交/PZ:批准...)
     */
    private String userstate;

    /**
     * 用户表-用户信用值
     */
    private Double usercredit;

    /**
     * 用户表-用户余额
     */
    private Double usermoney;

    /**
     * 用户表-用户短信验证码
     */
    private String usersms;

    /**
     * 用户表-用户短信验证码创建时间
     */
    private Date usersmstime;

    /**
     * 用户表-用户微信OpenId
     */
    private String useropenid;

    /**
     * 用户表-用户位置信息(当前位置信息)
     */
    private String userposition;

    /**
     * 用户表-用户位置纬度
     */
    private Double userlatitude;

    /**
     * 用户表-用户位置经度
     */
    private Double userlongitude;

    /**
     * 用户表-用户创建人ID(YHBG+UUID)
     */
    private String usercreateuserid;

    /**
     * 用户表-用户创建时间
     */
    private Date usercreatetime;

    /**
     * 用户表-用户更新人ID(YHBG+UUID)
     */
    private String userupdateuserid;

    /**
     * 用户表-用户更新时间
     */
    private Date userupdatetime;

    /**
     * 用户表-用户备注
     */
    private String userremark;

    /**
     * 用户表-用户邀请人ID
     */
    private String userinvitationuserid;
    /**
     * 用户表-用户邀请人姓名
     */
    private String userinvitationusername;
    /**
     *  用户表-排序
     */
    private Integer usersort;

    /**
     * 银行卡号
     */
    private String useraccount;
    /**
     * 银行开户行地址
     */
    private String useraccountaddress;

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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
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

    public String getOrderprojectname() {
        return orderprojectname;
    }

    public void setOrderprojectname(String orderprojectname) {
        this.orderprojectname = orderprojectname;
    }

    public String getOrdercontactperson() {
        return ordercontactperson;
    }

    public void setOrdercontactperson(String ordercontactperson) {
        this.ordercontactperson = ordercontactperson;
    }

    public String getOrdercontactphone() {
        return ordercontactphone;
    }

    public void setOrdercontactphone(String ordercontactphone) {
        this.ordercontactphone = ordercontactphone;
    }

    public String getOrderaddress() {
        return orderaddress;
    }

    public void setOrderaddress(String orderaddress) {
        this.orderaddress = orderaddress;
    }

    public String getBuildingtypeid() {
        return buildingtypeid;
    }

    public void setBuildingtypeid(String buildingtypeid) {
        this.buildingtypeid = buildingtypeid;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getOrderplantime() {
        return orderplantime;
    }

    public void setOrderplantime(Date orderplantime) {
        this.orderplantime = orderplantime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getOrderbiddingendtime() {
        return orderbiddingendtime;
    }

    public void setOrderbiddingendtime(Date orderbiddingendtime) {
        this.orderbiddingendtime = orderbiddingendtime;
    }

    public String getOrderprojecttype() {
        return orderprojecttype;
    }

    public void setOrderprojecttype(String orderprojecttype) {
        this.orderprojecttype = orderprojecttype;
    }

    public String getOrderposition() {
        return orderposition;
    }

    public void setOrderposition(String orderposition) {
        this.orderposition = orderposition;
    }

    public Double getOrderlatitude() {
        return orderlatitude;
    }

    public void setOrderlatitude(Double orderlatitude) {
        this.orderlatitude = orderlatitude;
    }

    public Double getOrderlongitude() {
        return orderlongitude;
    }

    public void setOrderlongitude(Double orderlongitude) {
        this.orderlongitude = orderlongitude;
    }

    public Double getOrderdistance() {
        return orderdistance;
    }

    public void setOrderdistance(Double orderdistance) {
        this.orderdistance = orderdistance;
    }

    public Double getOrderintercepttotalmoney() {
        return orderintercepttotalmoney;
    }

    public void setOrderintercepttotalmoney(Double orderintercepttotalmoney) {
        this.orderintercepttotalmoney = orderintercepttotalmoney;
    }

    public Double getOrderintercepttraveltotalmoney() {
        return orderintercepttraveltotalmoney;
    }

    public void setOrderintercepttraveltotalmoney(Double orderintercepttraveltotalmoney) {
        this.orderintercepttraveltotalmoney = orderintercepttraveltotalmoney;
    }

    public Double getOrderpricetotalmoney() {
        return orderpricetotalmoney;
    }

    public void setOrderpricetotalmoney(Double orderpricetotalmoney) {
        this.orderpricetotalmoney = orderpricetotalmoney;
    }

    public Double getOrderpriceservicetotalmoney() {
        return orderpriceservicetotalmoney;
    }

    public void setOrderpriceservicetotalmoney(Double orderpriceservicetotalmoney) {
        this.orderpriceservicetotalmoney = orderpriceservicetotalmoney;
    }

    public Double getOrdertraveltotalmoney() {
        return ordertraveltotalmoney;
    }

    public void setOrdertraveltotalmoney(Double ordertraveltotalmoney) {
        this.ordertraveltotalmoney = ordertraveltotalmoney;
    }

    public Double getOrdertotalmoney() {
        return ordertotalmoney;
    }

    public void setOrdertotalmoney(Double ordertotalmoney) {
        this.ordertotalmoney = ordertotalmoney;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(String orderstate) {
        this.orderstate = orderstate;
    }

    public String getOrderserviceuserid() {
        return orderserviceuserid;
    }

    public void setOrderserviceuserid(String orderserviceuserid) {
        this.orderserviceuserid = orderserviceuserid;
    }

    public String getOrdercreateuserid() {
        return ordercreateuserid;
    }

    public void setOrdercreateuserid(String ordercreateuserid) {
        this.ordercreateuserid = ordercreateuserid;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getOrdercreatetime() {
        return ordercreatetime;
    }

    public void setOrdercreatetime(Date ordercreatetime) {
        this.ordercreatetime = ordercreatetime;
    }

    public String getOrderupdateuserid() {
        return orderupdateuserid;
    }

    public void setOrderupdateuserid(String orderupdateuserid) {
        this.orderupdateuserid = orderupdateuserid;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getOrderupdatetime() {
        return orderupdatetime;
    }

    public void setOrderupdatetime(Date orderupdatetime) {
        this.orderupdatetime = orderupdatetime;
    }

    public String getOrderapplycheckuserid() {
        return orderapplycheckuserid;
    }

    public void setOrderapplycheckuserid(String orderapplycheckuserid) {
        this.orderapplycheckuserid = orderapplycheckuserid;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getOrderapplychecktime() {
        return orderapplychecktime;
    }

    public void setOrderapplychecktime(Date orderapplychecktime) {
        this.orderapplychecktime = orderapplychecktime;
    }

    public String getOrderremark() {
        return orderremark;
    }

    public void setOrderremark(String orderremark) {
        this.orderremark = orderremark;
    }

    public String getOrdercheckallstate() {
        return ordercheckallstate;
    }

    public void setOrdercheckallstate(String ordercheckallstate) {
        this.ordercheckallstate = ordercheckallstate;
    }

    public Double getOrderdaymoney() {
        return orderdaymoney;
    }

    public void setOrderdaymoney(Double orderdaymoney) {
        this.orderdaymoney = orderdaymoney;
    }

    public Integer getOrderday() {
        return orderday;
    }

    public void setOrderday(Integer orderday) {
        this.orderday = orderday;
    }

    public Integer getOrderpeoplenumber() {
        return orderpeoplenumber;
    }

    public void setOrderpeoplenumber(Integer orderpeoplenumber) {
        this.orderpeoplenumber = orderpeoplenumber;
    }

    public Double getOrdermaterialmoney() {
        return ordermaterialmoney;
    }

    public void setOrdermaterialmoney(Double ordermaterialmoney) {
        this.ordermaterialmoney = ordermaterialmoney;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsersalt() {
        return usersalt;
    }

    public void setUsersalt(String usersalt) {
        this.usersalt = usersalt;
    }

    public String getUserencryptpassword() {
        return userencryptpassword;
    }

    public void setUserencryptpassword(String userencryptpassword) {
        this.userencryptpassword = userencryptpassword;
    }

    public String getUserrealname() {
        return userrealname;
    }

    public void setUserrealname(String userrealname) {
        this.userrealname = userrealname;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUsercodeid() {
        return usercodeid;
    }

    public void setUsercodeid(String usercodeid) {
        this.usercodeid = usercodeid;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }

    public Double getUsercredit() {
        return usercredit;
    }

    public void setUsercredit(Double usercredit) {
        this.usercredit = usercredit;
    }

    public Double getUsermoney() {
        return usermoney;
    }

    public void setUsermoney(Double usermoney) {
        this.usermoney = usermoney;
    }

    public String getUsersms() {
        return usersms;
    }

    public void setUsersms(String usersms) {
        this.usersms = usersms;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getUsersmstime() {
        return usersmstime;
    }

    public void setUsersmstime(Date usersmstime) {
        this.usersmstime = usersmstime;
    }

    public String getUseropenid() {
        return useropenid;
    }

    public void setUseropenid(String useropenid) {
        this.useropenid = useropenid;
    }

    public String getUserposition() {
        return userposition;
    }

    public void setUserposition(String userposition) {
        this.userposition = userposition;
    }

    public Double getUserlatitude() {
        return userlatitude;
    }

    public void setUserlatitude(Double userlatitude) {
        this.userlatitude = userlatitude;
    }

    public Double getUserlongitude() {
        return userlongitude;
    }

    public void setUserlongitude(Double userlongitude) {
        this.userlongitude = userlongitude;
    }

    public String getUsercreateuserid() {
        return usercreateuserid;
    }

    public void setUsercreateuserid(String usercreateuserid) {
        this.usercreateuserid = usercreateuserid;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getUsercreatetime() {
        return usercreatetime;
    }

    public void setUsercreatetime(Date usercreatetime) {
        this.usercreatetime = usercreatetime;
    }

    public String getUserupdateuserid() {
        return userupdateuserid;
    }

    public void setUserupdateuserid(String userupdateuserid) {
        this.userupdateuserid = userupdateuserid;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    public Date getUserupdatetime() {
        return userupdatetime;
    }

    public void setUserupdatetime(Date userupdatetime) {
        this.userupdatetime = userupdatetime;
    }

    public String getUserremark() {
        return userremark;
    }

    public void setUserremark(String userremark) {
        this.userremark = userremark;
    }

    public String getUserinvitationuserid() {
        return userinvitationuserid;
    }

    public void setUserinvitationuserid(String userinvitationuserid) {
        this.userinvitationuserid = userinvitationuserid;
    }

    public String getUserinvitationusername() {
        return userinvitationusername;
    }

    public void setUserinvitationusername(String userinvitationusername) {
        this.userinvitationusername = userinvitationusername;
    }

    public Integer getUsersort() {
        return usersort;
    }

    public void setUsersort(Integer usersort) {
        this.usersort = usersort;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public String getUseraccountaddress() {
        return useraccountaddress;
    }

    public void setUseraccountaddress(String useraccountaddress) {
        this.useraccountaddress = useraccountaddress;
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