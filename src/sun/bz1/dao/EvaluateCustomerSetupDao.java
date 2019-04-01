package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.EvaluateCustomer;
import sun.bz1.entity.EvaluateCustomerSetup;

/**
 * 维修人员评价客户_评价项_设置表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/17
 */

@Repository
public class EvaluateCustomerSetupDao {
	
	private Logger logger = Logger.getLogger(EvaluateCustomerSetupDao.class);

	public int insert(SqlSession session, EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateCustomerSetup.insert", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateCustomerSetup.insertSelective", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateCustomerSetup.updateByPrimaryKeySelective", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateCustomerSetup.updateByPrimaryKey", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomerSetup.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public EvaluateCustomer selectByPrimaryKey(SqlSession session, Integer id) {
		EvaluateCustomer evaluate = new EvaluateCustomer();

		try {
			logger.info("EvaluateCustomerSetupDao");

			evaluate = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomerSetup.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return evaluate;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.delete("sun.bz1.dao.mapper.EvaluateCustomerSetup.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerSetup实体添加
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int insertByEvaluateCustomerSetup(SqlSession session, EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateCustomerSetup.insertByEvaluateCustomerSetup", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--insertByEvaluateCustomerSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int updateByEvaluateCustomerSetup(SqlSession session, EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateCustomerSetup.updateByEvaluateCustomerSetup", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--updateByEvaluateCustomerSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerSetup实体查询
	 * 
	 * 查询数量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int selectCountByEvaluateCustomerSetup(SqlSession session, EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomerSetup.selectCountByEvaluateCustomerSetup", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--selectCountByEvaluateCustomerSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerSetup实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public List<EvaluateCustomerSetup> selectByEvaluateCustomerSetup(SqlSession session, EvaluateCustomerSetup setup) {
		List<EvaluateCustomerSetup> setupList = new ArrayList<EvaluateCustomerSetup>();

		try {
			logger.info("EvaluateCustomerSetupDao");

			setupList = session.selectList("sun.bz1.dao.mapper.EvaluateCustomerSetup.selectByEvaluateCustomerSetup", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--selectByEvaluateCustomerSetup--error:" + e.getMessage());
		}

		return setupList;
	}

	/**
	 * 根据EvaluateCustomerSetup实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int selectCountBySelectData(SqlSession session, EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateCustomerSetupDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomerSetup.selectCountBySelectData", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerSetup实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public List<EvaluateCustomerSetup> selectBySelectData(SqlSession session, EvaluateCustomerSetup setup) {
		List<EvaluateCustomerSetup> setupList = new ArrayList<EvaluateCustomerSetup>();

		try {
			logger.info("EvaluateCustomerSetupDao");

			setupList = session.selectList("sun.bz1.dao.mapper.EvaluateCustomerSetup.selectBySelectData", setup);
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupDao--selectBySelectData--error:" + e.getMessage());
		}

		return setupList;
	}
	
}