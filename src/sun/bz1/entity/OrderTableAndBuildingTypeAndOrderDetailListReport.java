package sun.bz1.entity;

import java.util.List;

/**
 * 维修单表、建筑类型表、维修单详情表List
 * 
 * 实体类
 * 
 * @author ZY on 2018/09/14
 */

public class OrderTableAndBuildingTypeAndOrderDetailListReport {
	
	/**
	 * 维修单表、建筑类型表
	 */
	private OrderTableAndBuildingType orderTableAndBuildingType;
	
	/**
	 * 维修单详情表List
	 */
    private List<OrderDetail> orderDetailList;

	/**
	 * 客户评价维修人员
	 */
	private List<EvaluateServiceSetup> evaluateServiceSetupList;

	/**
	 * 维修人员评价客户
	 */
	private List<EvaluateCustomerSetup> evaluateCustomerSetupList;


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

	public List<EvaluateServiceSetup> getEvaluateServiceSetupList() {
		return evaluateServiceSetupList;
	}

	public void setEvaluateServiceSetupList(List<EvaluateServiceSetup> evaluateServiceSetupList) {
		this.evaluateServiceSetupList = evaluateServiceSetupList;
	}

	public List<EvaluateCustomerSetup> getEvaluateCustomerSetupList() {
		return evaluateCustomerSetupList;
	}

	public void setEvaluateCustomerSetupList(List<EvaluateCustomerSetup> evaluateCustomerSetupList) {
		this.evaluateCustomerSetupList = evaluateCustomerSetupList;
	}
}
