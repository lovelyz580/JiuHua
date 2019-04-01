package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.OrderDetail;

/**
 * 维修单详情表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/07
 */

@Repository
public class OrderDetailDao {
	
	private Logger logger = Logger.getLogger(OrderDetailDao.class);

	public int insert(SqlSession session, OrderDetail orderDetail) {
		int num = 0;

		try {
			logger.info("OrderDetailDao");

			num = session.insert("sun.bz1.dao.mapper.OrderDetail.insert", orderDetail);
		} catch (Exception e) {
			logger.error("OrderDetailDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, OrderDetail orderDetail) {
		int num = 0;

		try {
			logger.info("OrderDetailDao");

			num = session.insert("sun.bz1.dao.mapper.OrderDetail.insertSelective", orderDetail);
		} catch (Exception e) {
			logger.error("OrderDetailDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, OrderDetail orderDetail) {
		int num = 0;

		try {
			logger.info("OrderDetailDao");

			num = session.update("sun.bz1.dao.mapper.OrderDetail.updateByPrimaryKeySelective", orderDetail);
		} catch (Exception e) {
			logger.error("OrderDetailDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, OrderDetail orderDetail) {
		int num = 0;

		try {
			logger.info("OrderDetailDao");

			num = session.update("sun.bz1.dao.mapper.OrderDetail.updateByPrimaryKey", orderDetail);
		} catch (Exception e) {
			logger.error("OrderDetailDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public OrderDetail selectByPrimaryKey(SqlSession session, Integer id) {
		OrderDetail orderDetail = new OrderDetail();

		try {
			logger.info("OrderDetailDao");

			orderDetail = session.selectOne("sun.bz1.dao.mapper.OrderDetail.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("OrderDetailDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return orderDetail;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("OrderDetailDao");

			num = session.delete("sun.bz1.dao.mapper.OrderDetail.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("OrderDetailDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderDetail实体添加
	 * 
	 * @param orderDetail
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int insertByOrderDetail(SqlSession session, OrderDetail orderDetail) {
		int num = 0;

		try {
			logger.info("OrderDetailDao");

			num = session.insert("sun.bz1.dao.mapper.OrderDetail.insertByOrderDetail", orderDetail);
		} catch (Exception e) {
			logger.error("OrderDetailDao--insertByOrderDetail--error:" + e.getMessage());
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
	public int updateByOrderDetail(SqlSession session, OrderDetail orderDetail) {
		int num = 0;

		try {
			logger.info("OrderDetailDao");

			num = session.update("sun.bz1.dao.mapper.OrderDetail.updateByOrderDetail", orderDetail);
		} catch (Exception e) {
			logger.error("OrderDetailDao--updateByOrderDetail--error:" + e.getMessage());
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
	public List<OrderDetail> selectByOrderDetail(SqlSession session, OrderDetail orderDetail) {
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

		try {
			logger.info("OrderDetailDao");

			orderDetailList = session.selectList("sun.bz1.dao.mapper.OrderDetail.selectByOrderDetail", orderDetail);
		} catch (Exception e) {
			logger.error("OrderDetailDao--selectByOrderDetail--error:" + e.getMessage());
		}

		return orderDetailList;
	}

	/**
	 * 根据OrderDetail实体删除
	 * 
	 * @param orderDetail
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int deleteByOrderDetail(SqlSession session, OrderDetail orderDetail) {
		int num = 0;

		try {
			logger.info("OrderDetailDao");

			num = session.delete("sun.bz1.dao.mapper.OrderDetail.deleteByOrderDetail", orderDetail);
		} catch (Exception e) {
			logger.error("OrderDetailDao--deleteByOrderDetail--error:" + e.getMessage());
		}

		return num;
	}
    
}