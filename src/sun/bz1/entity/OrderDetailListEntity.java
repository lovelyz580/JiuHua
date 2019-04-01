package sun.bz1.entity;

import java.util.List;

/**
 * 维修单详情表List，维修单保存时用到
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/12
 */

public class OrderDetailListEntity {

	/**
	 * 维修单详情表List
	 */
    private List<OrderDetail> orderDetailList;

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
    
}
