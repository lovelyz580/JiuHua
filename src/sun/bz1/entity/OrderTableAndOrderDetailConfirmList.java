package sun.bz1.entity;

import java.util.List;

/**
 * 维修单表、维修单详情确认表List
 * 
 * 实体类
 * 
 * @author ZY on 2018/11/26
 */

public class OrderTableAndOrderDetailConfirmList {
	
	/**
	 * 维修单表
	 */
	private OrderTable orderTable;
	
	/**
	 * 维修单详情确认表List
	 */
    private List<OrderDetailConfirm> orderDetailConfirmList;

	public OrderTable getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(OrderTable orderTable) {
		this.orderTable = orderTable;
	}

	public List<OrderDetailConfirm> getOrderDetailConfirmList() {
		return orderDetailConfirmList;
	}

	public void setOrderDetailConfirmList(List<OrderDetailConfirm> orderDetailConfirmList) {
		this.orderDetailConfirmList = orderDetailConfirmList;
	}
}
