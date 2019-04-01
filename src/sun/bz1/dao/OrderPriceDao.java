package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.OrderPrice;
import sun.bz1.entity.OrderPriceAndGoodsAndProjectAndUser;

/**
 * 维修单对应维修单价表
 * 
 * 实体类
 * 
 * @author WJF on 2018/10/09
 */

@Repository
public class OrderPriceDao {
	
	private Logger logger = Logger.getLogger(OrderPriceDao.class);

	public int insert(SqlSession session, OrderPrice orderPrice) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.insert("sun.bz1.dao.mapper.OrderPrice.insert", orderPrice);
		} catch (Exception e) {
			logger.error("OrderPriceDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, OrderPrice orderPrice) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.insert("sun.bz1.dao.mapper.OrderPrice.insertSelective", orderPrice);
		} catch (Exception e) {
			logger.error("OrderPriceDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, OrderPrice orderPrice) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.update("sun.bz1.dao.mapper.OrderPrice.updateByPrimaryKeySelective", orderPrice);
		} catch (Exception e) {
			logger.error("OrderPriceDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, OrderPrice orderPrice) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.update("sun.bz1.dao.mapper.OrderPrice.updateByPrimaryKey", orderPrice);
		} catch (Exception e) {
			logger.error("OrderPriceDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.selectOne("sun.bz1.dao.mapper.OrderPrice.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("OrderPriceDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public OrderPrice selectByPrimaryKey(SqlSession session, Integer id) {
		OrderPrice OrderPrice = new OrderPrice();

		try {
			logger.info("OrderPriceDao");

			OrderPrice = session.selectOne("sun.bz1.dao.mapper.OrderPrice.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("OrderPriceDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return OrderPrice;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.delete("sun.bz1.dao.mapper.OrderPrice.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("OrderPriceDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderPrice实体添加
	 * 
	 * @param orderPrice
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int insertByOrderPrice(SqlSession session, OrderPrice orderPrice) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.insert("sun.bz1.dao.mapper.OrderPrice.insertByOrderPrice", orderPrice);
		} catch (Exception e) {
			logger.error("OrderPriceDao--insertByOrderPrice--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderPrice实体更新
	 * 
	 * @param orderPrice
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int updateByOrderPrice(SqlSession session, OrderPrice orderPrice) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.update("sun.bz1.dao.mapper.OrderPrice.updateByOrderPrice", orderPrice);
		} catch (Exception e) {
			logger.error("OrderPriceDao--updateByOrderPrice--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderPriceAndGoodsAndProjectAndUser实体查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int selectFourTablesCountByUnionData(SqlSession session, OrderPriceAndGoodsAndProjectAndUser unionData) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.selectOne("sun.bz1.dao.mapper.OrderPrice.selectFourTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("OrderPriceDao--selectFourTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderPriceAndGoodsAndProjectAndUser实体查询
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public List<OrderPriceAndGoodsAndProjectAndUser> selectFourTablesByUnionData(SqlSession session, OrderPriceAndGoodsAndProjectAndUser unionData) {
		List<OrderPriceAndGoodsAndProjectAndUser> orderPriceList = new ArrayList<OrderPriceAndGoodsAndProjectAndUser>();

		try {
			logger.info("OrderPriceDao");

			orderPriceList = session.selectList("sun.bz1.dao.mapper.OrderPrice.selectFourTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("OrderPriceDao--selectFourTablesByUnionData--error:" + e.getMessage());
		}

		return orderPriceList;
	}
    
	/**
	 * 根据OrderPrice实体删除
	 * 
	 * @param orderPrice
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int deleteByOrderPrice(SqlSession session, OrderPrice orderPrice) {
		int num = 0;

		try {
			logger.info("OrderPriceDao");

			num = session.delete("sun.bz1.dao.mapper.OrderPrice.deleteByOrderPrice", orderPrice);
		} catch (Exception e) {
			logger.error("OrderPriceDao--deleteByOrderPrice--error:" + e.getMessage());
		}

		return num;
	}
	
}