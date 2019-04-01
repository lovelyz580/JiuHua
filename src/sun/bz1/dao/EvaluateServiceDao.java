package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.EvaluateService;
import sun.bz1.entity.EvaluateServiceAndOrderTableAndUser;

/**
 * 客户评价维修人员表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/05
 */

@Repository
public class EvaluateServiceDao {

	private Logger logger = Logger.getLogger(EvaluateServiceDao.class);

	public int insert(SqlSession session, EvaluateService evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateService.insert", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, EvaluateService evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateService.insertSelective", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, EvaluateService evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateService.updateByPrimaryKeySelective", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, EvaluateService evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateService.updateByPrimaryKey", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateService.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public EvaluateService selectByPrimaryKey(SqlSession session, Integer id) {
		EvaluateService evaluate = new EvaluateService();

		try {
			logger.info("EvaluateServiceDao");

			evaluate = session.selectOne("sun.bz1.dao.mapper.EvaluateService.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return evaluate;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.delete("sun.bz1.dao.mapper.EvaluateService.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateService实体添加
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int insertByEvaluateService(SqlSession session, EvaluateService evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.insert("sun.bz1.dao.mapper.EvaluateService.insertByEvaluateService", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--insertByEvaluateService--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateService实体更新
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByEvaluateService(SqlSession session, EvaluateService evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.update("sun.bz1.dao.mapper.EvaluateService.updateByEvaluateService", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--updateByEvaluateService--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, EvaluateServiceAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateService.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表查询
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
	public List<EvaluateServiceAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, EvaluateServiceAndOrderTableAndUser unionData) {
		List<EvaluateServiceAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateServiceAndOrderTableAndUser>();

		try {
			logger.info("EvaluateServiceDao");

			evaluateList = session.selectList("sun.bz1.dao.mapper.EvaluateService.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return evaluateList;
	}

	/**
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, EvaluateServiceAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.selectOne("sun.bz1.dao.mapper.EvaluateService.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表模糊查询
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
	public List<EvaluateServiceAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, EvaluateServiceAndOrderTableAndUser unionData) {
		List<EvaluateServiceAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateServiceAndOrderTableAndUser>();

		try {
			logger.info("EvaluateServiceDao");

			evaluateList = session.selectList("sun.bz1.dao.mapper.EvaluateService.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return evaluateList;
	}
	
	/**
	 * 根据EvaluateService实体删除
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByEvaluateService(SqlSession session, EvaluateService evaluate) {
		int num = 0;

		try {
			logger.info("EvaluateServiceDao");

			num = session.delete("sun.bz1.dao.mapper.EvaluateService.deleteByEvaluateService", evaluate);
		} catch (Exception e) {
			logger.error("EvaluateServiceDao--deleteByEvaluateService--error:" + e.getMessage());
		}

		return num;
	}
	
}