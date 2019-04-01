package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Intercept;
import sun.bz1.entity.InterceptAndGoodsAndProject;

/**
 * 拦标价表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/05
 */

@Repository
public class InterceptDao {

	private Logger logger = Logger.getLogger(InterceptDao.class);

	public int insert(SqlSession session, Intercept intercept) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.insert("sun.bz1.dao.mapper.Intercept.insert", intercept);
		} catch (Exception e) {
			logger.error("InterceptDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Intercept intercept) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.insert("sun.bz1.dao.mapper.Intercept.insertSelective", intercept);
		} catch (Exception e) {
			logger.error("InterceptDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Intercept intercept) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.update("sun.bz1.dao.mapper.Intercept.updateByPrimaryKeySelective", intercept);
		} catch (Exception e) {
			logger.error("InterceptDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Intercept intercept) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.update("sun.bz1.dao.mapper.Intercept.updateByPrimaryKey", intercept);
		} catch (Exception e) {
			logger.error("InterceptDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.selectOne("sun.bz1.dao.mapper.Intercept.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("InterceptDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Intercept selectByPrimaryKey(SqlSession session, Integer id) {
		Intercept intercept = new Intercept();

		try {
			logger.info("InterceptDao");

			intercept = session.selectOne("sun.bz1.dao.mapper.Intercept.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("InterceptDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return intercept;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.delete("sun.bz1.dao.mapper.Intercept.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("InterceptDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Intercept实体添加
	 * 
	 * @param intercept
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int insertByIntercept(SqlSession session, Intercept intercept) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.insert("sun.bz1.dao.mapper.Intercept.insertByIntercept", intercept);
		} catch (Exception e) {
			logger.error("InterceptDao--insertByIntercept--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Intercept实体更新
	 * 
	 * @param intercept
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByIntercept(SqlSession session, Intercept intercept) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.update("sun.bz1.dao.mapper.Intercept.updateByIntercept", intercept);
		} catch (Exception e) {
			logger.error("InterceptDao--updateByIntercept--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptAndGoodsAndProject实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, InterceptAndGoodsAndProject unionData) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.selectOne("sun.bz1.dao.mapper.Intercept.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("InterceptDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptAndGoodsAndProject实体联表查询
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
	public List<InterceptAndGoodsAndProject> selectThreeTablesByUnionData(SqlSession session, InterceptAndGoodsAndProject unionData) {
		List<InterceptAndGoodsAndProject> interceptList = new ArrayList<InterceptAndGoodsAndProject>();

		try {
			logger.info("InterceptDao");

			interceptList = session.selectList("sun.bz1.dao.mapper.Intercept.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("InterceptDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return interceptList;
	}

	/**
	 * 根据InterceptAndGoodsAndProject实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, InterceptAndGoodsAndProject unionData) {
		int num = 0;

		try {
			logger.info("InterceptDao");

			num = session.selectOne("sun.bz1.dao.mapper.Intercept.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("InterceptDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptAndGoodsAndProject实体联表模糊查询
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
	public List<InterceptAndGoodsAndProject> selectThreeTablesBySelectData(SqlSession session, InterceptAndGoodsAndProject unionData) {
		List<InterceptAndGoodsAndProject> interceptList = new ArrayList<InterceptAndGoodsAndProject>();

		try {
			logger.info("InterceptDao");

			interceptList = session.selectList("sun.bz1.dao.mapper.Intercept.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("InterceptDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return interceptList;
	}
	
}