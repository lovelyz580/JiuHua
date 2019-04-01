package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.EvaluateCustomer;
import sun.bz1.entity.EvaluateCustomerAndOrderTableAndUser;

/**
 * 维修人员评价客户表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/05
 */

@Repository
public class EvaluateCustomerDao {
	
	private Logger logger = Logger.getLogger(EvaluateCustomerDao.class);

	public int insert(SqlSession session, EvaluateCustomer evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateCustomer.insert", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, EvaluateCustomer evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateCustomer.insertSelective", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, EvaluateCustomer evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateCustomer.updateByPrimaryKeySelective", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, EvaluateCustomer evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateCustomer.updateByPrimaryKey", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomer.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public EvaluateCustomer selectByPrimaryKey(SqlSession session, Integer id) {
		EvaluateCustomer evaluate = new EvaluateCustomer();

		try {
			logger.info("EvaluateCustomerDao");

			evaluate = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomer.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return evaluate;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.delete("sun.bz1.dao.mapper.EvaluateCustomer.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomer实体添加
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int insertByEvaluateCustomer(SqlSession session, EvaluateCustomer evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateCustomer.insertByEvaluateCustomer", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--insertByEvaluateCustomer--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomer实体更新
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByEvaluateCustomer(SqlSession session, EvaluateCustomer evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateCustomer.updateByEvaluateCustomer", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--updateByEvaluateCustomer--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, EvaluateCustomerAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomer.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerAndOrderTableAndUser实体联表查询
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
	 * @author WJF on 2018/09/05
	 */
	public List<EvaluateCustomerAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, EvaluateCustomerAndOrderTableAndUser unionData) {
		List<EvaluateCustomerAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateCustomerAndOrderTableAndUser>();

		try {
			logger.info("EvaluateCustomerDao");

			evaluateList = session.selectList("sun.bz1.dao.mapper.EvaluateCustomer.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return evaluateList;
	}

	/**
	 * 根据EvaluateCustomerAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, EvaluateCustomerAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomer.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerAndOrderTableAndUser实体联表模糊查询
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
	 * @author WJF on 2018/09/05
	 */
	public List<EvaluateCustomerAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, EvaluateCustomerAndOrderTableAndUser unionData) {
		List<EvaluateCustomerAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateCustomerAndOrderTableAndUser>();

		try {
			logger.info("EvaluateCustomerDao");

			evaluateList = session.selectList("sun.bz1.dao.mapper.EvaluateCustomer.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return evaluateList;
	}
	
	/**
	 * 根据EvaluateCustomer实体删除
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByEvaluateCustomer(SqlSession session, EvaluateCustomer evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerDao");

			num = session.delete("sun.bz1.dao.mapper.EvaluateCustomer.deleteByEvaluateCustomer", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateCustomerDao--deleteByEvaluateCustomer--error:" + e.getMessage());
		}

		return num;
	}
    
}