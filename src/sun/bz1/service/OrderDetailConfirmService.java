package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.OrderDetailConfirmDao;
import sun.bz1.entity.OrderDetailConfirm;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修单详情确认表
 * 
 * Service
 * 
 * @author ZY on 2018/11/26
 */

@Service
public class OrderDetailConfirmService {
	
	@Autowired
	private OrderDetailConfirmDao orderDetailConfirmDao;

	private Logger logger = Logger.getLogger(OrderDetailConfirmService.class);
	
	/**
   	 * 根据OrderDetailConfirm实体添加
   	 * 
   	 * @param orderDetailConfirm
   	 * @return
   	 * 
   	 * @author ZY on 2018/11/26
   	 */
	public int insertByOrderDetailConfirm(OrderDetailConfirm orderDetailConfirm) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderDetailConfirmDao.insertByOrderDetailConfirm(session, orderDetailConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailConfirmService--insertByOrderDetailConfirm--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderDetailConfirm实体更新
	 * 
	 * @param orderDetailConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/26
	 */
	public int updateByOrderDetailConfirm(OrderDetailConfirm orderDetailConfirm) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderDetailConfirmDao.updateByOrderDetailConfirm(session, orderDetailConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailConfirmService--updateByOrderDetailConfirm--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderDetailConfirm实体查询
	 * 
	 * @param orderDetailConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/26
	 */
	public List<OrderDetailConfirm> selectByOrderDetailConfirm(OrderDetailConfirm orderDetailConfirm) {
		List<OrderDetailConfirm> orderDetailConfirmList = new ArrayList<OrderDetailConfirm>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderDetailConfirmList = orderDetailConfirmDao.selectByOrderDetailConfirm(session, orderDetailConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailConfirmService--selectByOrderDetailConfirm--error:" + e.getMessage());
		}

		return orderDetailConfirmList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/11/26
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + orderDetailConfirmDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailConfirmService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author ZY on 2018/11/26
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				OrderDetailConfirm orderDetailConfirm = new OrderDetailConfirm();
				orderDetailConfirm.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + orderDetailConfirmDao.deleteByOrderDetailConfirm(session, orderDetailConfirm);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailConfirmService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

	/**
	 * 根据 维修单orderDetailConfirm 删除信息
	 *
	 * @param orderDetailConfirm
	 * @return
	 *
	 * @author ZY on 2018/11/08
	 */
	public int deleteByOrDetail(OrderDetailConfirm orderDetailConfirm) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

				deleteNum = orderDetailConfirmDao.deleteByOrderDetailConfirm(session, orderDetailConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailConfirmService--deleteByOrderDetailConfirm--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
