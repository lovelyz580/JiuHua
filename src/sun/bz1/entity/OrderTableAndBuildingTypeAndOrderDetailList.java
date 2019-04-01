package sun.bz1.entity;

import java.util.List;

/**
 * 维修单表、建筑类型表、维修单详情表List
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/14
 */

public class OrderTableAndBuildingTypeAndOrderDetailList {
	
	/**
	 * 维修单表、建筑类型表
	 */
	private OrderTableAndBuildingType orderTableAndBuildingType;
	
	/**
	 * 维修单详情表List
	 */
    private List<OrderDetail> orderDetailList;

	

	public OrderTableAndBuildingType getOrderTableAndBuildingType() {
		return orderTableAndBuildingType;
	}

	public void setOrderTableAndBuildingType(OrderTableAndBuildingType orderTableAndBuildingType) {
		this.orderTableAndBuildingType = orderTableAndBuildingType;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
    
}
