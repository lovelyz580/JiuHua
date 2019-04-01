package sun.bz1.entity;

import java.util.List;

/**
 * 修改单价的列表，修改时需要
 * 
 * 实体类
 * 
 * @author ZY on 2018/11/08
 */

public class PriceListEntity {

	/**
	 * 单价List
	 */
    private List<Price> priceList;

	/**
	 * 操作人
	 */
	private String pricecreateuserid;
	/**
	 * 版本号
	 */
	private String version;

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPricecreateuserid() {
		return pricecreateuserid;
	}

	public void setPricecreateuserid(String pricecreateuserid) {
		this.pricecreateuserid = pricecreateuserid;
	}
}
