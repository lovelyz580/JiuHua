package sun.bz1.entity;

import java.util.List;

/**
 * 维修单表、维修单详情表List
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/12
 */

public class OrderTableAndOrderDetailList {
	
	/**
	 * 维修单表
	 */
	private OrderTable orderTable;
	
	/**
	 * 维修单详情表List
	 */
    private List<OrderDetail> orderDetailList;

	public OrderTable getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(OrderTable orderTable) {
		this.orderTable = orderTable;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
    
}
