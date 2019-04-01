package sun.bz1.entity;

import java.util.List;

/**
 * 维修单详情确认表List，维修单保存时用到
 * 
 * 实体类
 * 
 * @author ZY on 2018/11/26
 */

public class OrderDetailConfirmListEntity {

	/**
	 * 维修单详情确认表List
	 */
    private List<OrderDetailConfirm> orderDetailConfirmList;

	public List<OrderDetailConfirm> getOrderDetailConfirmList() {
		return orderDetailConfirmList;
	}

	public void setOrderDetailConfirmList(List<OrderDetailConfirm> orderDetailConfirmList) {
		this.orderDetailConfirmList = orderDetailConfirmList;
	}
}
