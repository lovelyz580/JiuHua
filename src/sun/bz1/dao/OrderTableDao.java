package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.OrderTable;
import sun.bz1.entity.OrderTableAndBuildingType;
import sun.bz1.entity.OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject;

/**
 * 维修单表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/07
 */

@Repository
public class OrderTableDao {
	
	private Logger logger = Logger.getLogger(OrderTableDao.class);

	public int insert(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.insert("sun.bz1.dao.mapper.OrderTable.insert", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.insert("sun.bz1.dao.mapper.OrderTable.insertSelective", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.update("sun.bz1.dao.mapper.OrderTable.updateByPrimaryKeySelective", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.update("sun.bz1.dao.mapper.OrderTable.updateByPrimaryKey", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.OrderTable.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public OrderTable selectByPrimaryKey(SqlSession session, Integer id) {
		OrderTable order = new OrderTable();

		try {
			logger.info("OrderTableDao");

			order = session.selectOne("sun.bz1.dao.mapper.OrderTable.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return order;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.delete("sun.bz1.dao.mapper.OrderTable.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("OrderTableDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体添加
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int insertByOrderTable(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.insert("sun.bz1.dao.mapper.OrderTable.insertByOrderTable", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--insertByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体更新
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int updateByOrderTable(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.update("sun.bz1.dao.mapper.OrderTable.updateByOrderTable", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--updateByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体查询
	 * 
	 * 查询数量
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int selectCountByOrderTable(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.OrderTable.selectCountByOrderTable", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectCountByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public List<OrderTable> selectByOrderTable(SqlSession session, OrderTable order) {
		List<OrderTable> orderList = new ArrayList<OrderTable>();

		try {
			logger.info("OrderTableDao");

			orderList = session.selectList("sun.bz1.dao.mapper.OrderTable.selectByOrderTable", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectByOrderTable--error:" + e.getMessage());
		}

		return orderList;
	}
	
	/**
	 * 根据OrderTable实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int selectCountBySelectData(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.OrderTable.selectCountBySelectData", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTable实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public List<OrderTable> selectBySelectData(SqlSession session, OrderTable order) {
		List<OrderTable> orderList = new ArrayList<OrderTable>();

		try {
			logger.info("OrderTableDao");

			orderList = session.selectList("sun.bz1.dao.mapper.OrderTable.selectBySelectData", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectBySelectData--error:" + e.getMessage());
		}

		return orderList;
	}
	
	/**
	 * 根据OrderTableAndBuildingType实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/14
	 */
	public int selectTwoTablesCountByUnionData(SqlSession session, OrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.OrderTable.selectTwoTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectTwoTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTableAndBuildingType实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/14
	 */
	public List<OrderTableAndBuildingType> selectTwoTablesByUnionData(SqlSession session, OrderTableAndBuildingType unionData) {
		List<OrderTableAndBuildingType> orderList = new ArrayList<OrderTableAndBuildingType>();

		try {
			logger.info("OrderTableDao");

			orderList = session.selectList("sun.bz1.dao.mapper.OrderTable.selectTwoTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectTwoTablesByUnionData--error:" + e.getMessage());
		}

		return orderList;
	}

	/**
	 * 根据OrderTableAndBuildingType实体模糊查询
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/07
	 */
	public int selectFourTablesCountDetailRport(SqlSession session, OrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.OrderTable.selectFourTablesCountDetailRport", unionData);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectFourTablesCountDetailRport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderTableAndBuildingType实体模糊查询
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/07
	 */
	public List<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> selectFourTablesDetailRport(SqlSession session, OrderTableAndBuildingType unionData) {
		List<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> orderList = new ArrayList<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject>();

		try {
			logger.info("OrderTableDao");

			orderList = session.selectList("sun.bz1.dao.mapper.OrderTable.selectFourTablesDetailRport", unionData);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectFourTablesDetailRport--error:" + e.getMessage());
		}

		return orderList;
	}
	
	/**
	 * 维修人员评价客户时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
	 * 查询数量
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	public int selectEvaluateCustomerCountByOrderTable(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.OrderTable.selectEvaluateCustomerCountByOrderTable", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectEvaluateCustomerCountByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 维修人员评价客户时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	public List<OrderTable> selectEvaluateCustomerByOrderTable(SqlSession session, OrderTable order) {
		List<OrderTable> orderList = new ArrayList<OrderTable>();

		try {
			logger.info("OrderTableDao");

			orderList = session.selectList("sun.bz1.dao.mapper.OrderTable.selectEvaluateCustomerByOrderTable", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectEvaluateCustomerByOrderTable--error:" + e.getMessage());
		}

		return orderList;
	}
	
	/**
	 * 客户评价维修人员时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
	 * 查询数量
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	public int selectEvaluateServiceCountByOrderTable(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.OrderTable.selectEvaluateServiceCountByOrderTable", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectEvaluateServiceCountByOrderTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 客户评价维修人员时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	public List<OrderTable> selectEvaluateServiceByOrderTable(SqlSession session, OrderTable order) {
		List<OrderTable> orderList = new ArrayList<OrderTable>();

		try {
			logger.info("OrderTableDao");

			orderList = session.selectList("sun.bz1.dao.mapper.OrderTable.selectEvaluateServiceByOrderTable", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--selectEvaluateServiceByOrderTable--error:" + e.getMessage());
		}

		return orderList;
	}
	
	/**
	 * 根据OrderTable实体删除
	 * 
	 * @param order
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int deleteByOrderTable(SqlSession session, OrderTable order) {
		int num = 0;

		try {
			logger.info("OrderTableDao");

			num = session.delete("sun.bz1.dao.mapper.OrderTable.deleteByOrderTable", order);
		} catch (Exception e) {
			logger.error("OrderTableDao--deleteByOrderTable--error:" + e.getMessage());
		}

		return num;
	}
	
}