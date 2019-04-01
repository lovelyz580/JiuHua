package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.EvaluateService;
import sun.bz1.entity.EvaluateServiceSetup;

/**
 * 客户评价维修人员_评价项_设置表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/17
 */

@Repository
public class EvaluateServiceSetupDao {
	
	private Logger logger = Logger.getLogger(EvaluateServiceSetupDao.class);

	public int insert(SqlSession session, EvaluateServiceSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateServiceSetup.insert", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, EvaluateServiceSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateServiceSetup.insertSelective", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, EvaluateServiceSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateServiceSetup.updateByPrimaryKeySelective", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, EvaluateServiceSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateServiceSetup.updateByPrimaryKey", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateServiceSetup.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public EvaluateService selectByPrimaryKey(SqlSession session, Integer id) {
		EvaluateService evaluate = new EvaluateService();

		try {
			logger.info("EvaluateServiceSetupDao");

			evaluate = session.selectOne("sun.bz1.dao.mapper.EvaluateServiceSetup.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return evaluate;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.delete("sun.bz1.dao.mapper.EvaluateServiceSetup.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceSetup实体添加
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int insertByEvaluateServiceSetup(SqlSession session, EvaluateServiceSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateServiceSetup.insertByEvaluateServiceSetup", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--insertByEvaluateServiceSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int updateByEvaluateServiceSetup(SqlSession session, EvaluateServiceSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateServiceSetup.updateByEvaluateServiceSetup", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--updateByEvaluateServiceSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceSetup实体查询
	 * 
	 * 查询数量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int selectCountByEvaluateServiceSetup(SqlSession session, EvaluateServiceSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateServiceSetup.selectCountByEvaluateServiceSetup", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--selectCountByEvaluateServiceSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceSetup实体查询
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
	public List<EvaluateServiceSetup> selectByEvaluateServiceSetup(SqlSession session, EvaluateServiceSetup setup) {
		List<EvaluateServiceSetup> setupList = new ArrayList<EvaluateServiceSetup>();

		try {
			logger.info("EvaluateServiceSetupDao");

			setupList = session.selectList("sun.bz1.dao.mapper.EvaluateServiceSetup.selectByEvaluateServiceSetup", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--selectByEvaluateServiceSetup--error:" + e.getMessage());
		}

		return setupList;
	}

	/**
	 * 根据EvaluateServiceSetup实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int selectCountBySelectData(SqlSession session, EvaluateServiceSetup setup) {
		int num = 0;

		try {
			logger.info("EvaluateServiceSetupDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateServiceSetup.selectCountBySelectData", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceSetup实体联表模糊查询
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
	public List<EvaluateServiceSetup> selectBySelectData(SqlSession session, EvaluateServiceSetup setup) {
		List<EvaluateServiceSetup> setupList = new ArrayList<EvaluateServiceSetup>();

		try {
			logger.info("EvaluateServiceSetupDao");

			setupList = session.selectList("sun.bz1.dao.mapper.EvaluateServiceSetup.selectBySelectData", setup);
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupDao--selectBySelectData--error:" + e.getMessage());
		}

		return setupList;
	}
	
}