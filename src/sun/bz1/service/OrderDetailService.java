package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.OrderDetailDao;
import sun.bz1.entity.OrderDetail;
import util.MyBatisUtil;

/**
 * 维修单详情表
 * 
 * Service
 * 
 * @author WJF on 2018/09/07
 */

@Service
public class OrderDetailService {
	
	@Autowired
	private OrderDetailDao orderDetailDao;

	private Logger logger = Logger.getLogger(OrderDetailService.class);
	
	/**
   	 * 根据OrderDetail实体添加
   	 * 
   	 * @param orderDetail
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/07
   	 */
	public int insertByOrderDetail(OrderDetail orderDetail) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderDetailDao.insertByOrderDetail(session, orderDetail);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailService--insertByOrderDetail--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderDetail实体更新
	 * 
	 * @param orderDetail
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int updateByOrderDetail(OrderDetail orderDetail) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderDetailDao.updateByOrderDetail(session, orderDetail);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailService--updateByOrderDetail--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderDetail实体查询
	 * 
	 * @param orderDetail
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public List<OrderDetail> selectByOrderDetail(OrderDetail orderDetail) {
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderDetailList = orderDetailDao.selectByOrderDetail(session, orderDetail);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailService--selectByOrderDetail--error:" + e.getMessage());
		}

		return orderDetailList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + orderDetailDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + orderDetailDao.deleteByOrderDetail(session, orderDetail);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

	/**
	 * 根据 维修单orderDetail 删除信息
	 *
	 * @param orderDetail
	 * @return
	 *
	 * @author ZY on 2018/11/08
	 */
	public int deleteByOrDetail(OrderDetail orderDetail) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

				deleteNum = orderDetailDao.deleteByOrderDetail(session, orderDetail);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailService--deleteByOrderDetail--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
