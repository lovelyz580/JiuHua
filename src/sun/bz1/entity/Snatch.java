package sun.bz1.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修单抢单记录表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/08
 */

public class Snatch {

	/**
	 * 序号(自增主键)
	 */
	private Integer id;

	/**
	 * 抢单记录ID(QDJL+UUID)
	 */
	private String snatchid;

	/**
	 * 维修单ID(D+年月日+时分秒+6位随机数)
	 */
	private String orderid;

	/**
	 * 抢单人员ID(维修工、安装队)(YHBG+UUID)
	 */
	private String serviceuserid;
	
	/**
	 * 抢单维修总价
	 */
	private Double snatchmoney;
	
	/**
	 * 抢单维修差旅费总价
	 */
	private Double snatchtravelmoney;
	
	/**
	 * 总价(维修总价+维修差旅费总价)
	 * 
	 * 业务字段，数据表中没有该字段
	 */
	private Double snatchtotalmoney;

	/**
	 * 抢单状态(Y:已抢到/N:未抢到)
	 */
	private String snatchstate;

	/**
	 * 抢单报名时间
	 */
	private Date snatchenrolltime;

	/**
	 * 抢单确认人员ID(YHBG+UUID)(只有抢到维修单的才会添加这个字段的数据)
	 */
	private String snatchconfirmuserid;

	/**
	 * 抢单确认时间(只有抢到维修单的才会添加这个字段的数据)
	 */
	private Date snatchconfirmtime;

	/**
	 * 抢单备注
	 */
	private String snatchremark;

	/**
	 * 竞标中的日工资
	 */
	private Double snatchdaymoney;

	/**
	 * 竞标中的天数
	 */
	private Integer snatchday;

	/**
	 * 竞标中的人数
	 */
	private Integer snatchpeoplenumber;

    /**
     * 竞标中的材料费
     */
    private Double snatchmaterialmoney;

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

	public String getSnatchid() {
		return snatchid;
	}

	public void setSnatchid(String snatchid) {
		this.snatchid = snatchid == null ? null : snatchid.trim();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid == null ? null : orderid.trim();
	}

	public String getServiceuserid() {
		return serviceuserid;
	}

	public void setServiceuserid(String serviceuserid) {
		this.serviceuserid = serviceuserid == null ? null : serviceuserid.trim();
	}

	public Double getSnatchmoney() {
		return snatchmoney;
	}

	public void setSnatchmoney(Double snatchmoney) {
		this.snatchmoney = snatchmoney;
	}

	public Double getSnatchtravelmoney() {
		return snatchtravelmoney;
	}

	public void setSnatchtravelmoney(Double snatchtravelmoney) {
		this.snatchtravelmoney = snatchtravelmoney;
	}

	public Double getSnatchtotalmoney() {
		Double money = snatchmoney;
		Double travelmoney = snatchtravelmoney;
		
		if (money == null) {
			money = 0.0;
		} 
		
		if (travelmoney == null) {
			travelmoney = 0.0;
		}
		
		snatchtotalmoney = money + travelmoney;
		
		return snatchtotalmoney;
	}

	public void setSnatchtotalmoney(Double snatchtotalmoney) {
		this.snatchtotalmoney = snatchtotalmoney;
	}

	public String getSnatchstate() {
		return snatchstate;
	}

	public void setSnatchstate(String snatchstate) {
		this.snatchstate = snatchstate == null ? null : snatchstate.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getSnatchenrolltime() {
		return snatchenrolltime;
	}

	public void setSnatchenrolltime(Date snatchenrolltime) {
		this.snatchenrolltime = snatchenrolltime;
	}

	public String getSnatchconfirmuserid() {
		return snatchconfirmuserid;
	}

	public void setSnatchconfirmuserid(String snatchconfirmuserid) {
		this.snatchconfirmuserid = snatchconfirmuserid == null ? null : snatchconfirmuserid.trim();
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getSnatchconfirmtime() {
		return snatchconfirmtime;
	}

	public void setSnatchconfirmtime(Date snatchconfirmtime) {
		this.snatchconfirmtime = snatchconfirmtime;
	}

	public String getSnatchremark() {
		return snatchremark;
	}

	public void setSnatchremark(String snatchremark) {
		this.snatchremark = snatchremark == null ? null : snatchremark.trim();
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

	public Double getSnatchdaymoney() {
		return snatchdaymoney;
	}

	public void setSnatchdaymoney(Double snatchdaymoney) {
		this.snatchdaymoney = snatchdaymoney;
	}

	public Integer getSnatchday() {
		return snatchday;
	}

	public void setSnatchday(Integer snatchday) {
		this.snatchday = snatchday;
	}

	public Integer getSnatchpeoplenumber() {
		return snatchpeoplenumber;
	}

	public void setSnatchpeoplenumber(Integer snatchpeoplenumber) {
		this.snatchpeoplenumber = snatchpeoplenumber;
	}

    public Double getSnatchmaterialmoney() {
        return snatchmaterialmoney;
    }

    public void setSnatchmaterialmoney(Double snatchmaterialmoney) {
        this.snatchmaterialmoney = snatchmaterialmoney;
    }
}