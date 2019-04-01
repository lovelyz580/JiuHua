package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.OrderDetailRecordDao;
import sun.bz1.entity.OrderDetailRecord;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修单详情记录表
 * 
 * Service
 * 
 * @author ZY on 2018/11/08
 */

@Service
public class OrderDetailRecordService {
	
	@Autowired
	private OrderDetailRecordDao orderDetailRecordDao;

	private Logger logger = Logger.getLogger(OrderDetailRecordService.class);
	
	/**
   	 * 根据OrderDetailRecord实体添加
   	 * 
   	 * @param orderDetailRecord
   	 * @return
   	 * 
   	 * @author ZY on 2018/11/08
   	 */
	public int insertByOrderDetailRecord(OrderDetailRecord orderDetailRecord) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderDetailRecordDao.insertByOrderDetailRecord(session, orderDetailRecord);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailRecordService--insertByOrderDetailRecord--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderDetailRecord实体更新
	 * 
	 * @param orderDetailRecord
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	public int updateByOrderDetailRecord(OrderDetailRecord orderDetailRecord) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderDetailRecordDao.updateByOrderDetailRecord(session, orderDetailRecord);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailRecordService--updateByOrderDetailRecord--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderDetailRecord实体查询
	 * 
	 * @param orderDetailRecord
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	public List<OrderDetailRecord> selectByOrderDetailRecord(OrderDetailRecord orderDetailRecord) {
		List<OrderDetailRecord> orderDetailRecordList = new ArrayList<OrderDetailRecord>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderDetailRecordList = orderDetailRecordDao.selectByOrderDetailRecord(session, orderDetailRecord);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailRecordService--selectByOrderDetailRecord--error:" + e.getMessage());
		}

		return orderDetailRecordList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + orderDetailRecordDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailRecordService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				OrderDetailRecord orderDetailRecord = new OrderDetailRecord();
				orderDetailRecord.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + orderDetailRecordDao.deleteByOrderDetailRecord(session, orderDetailRecord);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderDetailRecordService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
