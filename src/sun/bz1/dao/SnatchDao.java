package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Snatch;
import sun.bz1.entity.SnatchAndOrderTableAndUser;

/**
 * 维修单抢单记录表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/08
 */

@Repository
public class SnatchDao {
	
	private Logger logger = Logger.getLogger(SnatchDao.class);

	public int insert(SqlSession session, Snatch snatch) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.insert("sun.bz1.dao.mapper.Snatch.insert", snatch);
		} catch (Exception e) {
			logger.error("SnatchDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Snatch snatch) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.insert("sun.bz1.dao.mapper.Snatch.insertSelective", snatch);
		} catch (Exception e) {
			logger.error("SnatchDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Snatch snatch) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.update("sun.bz1.dao.mapper.Snatch.updateByPrimaryKeySelective", snatch);
		} catch (Exception e) {
			logger.error("SnatchDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Snatch snatch) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.update("sun.bz1.dao.mapper.Snatch.updateByPrimaryKey", snatch);
		} catch (Exception e) {
			logger.error("SnatchDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.selectOne("sun.bz1.dao.mapper.Snatch.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("SnatchDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Snatch selectByPrimaryKey(SqlSession session, Integer id) {
		Snatch snatch = new Snatch();

		try {
			logger.info("SnatchDao");

			snatch = session.selectOne("sun.bz1.dao.mapper.Snatch.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("SnatchDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return snatch;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.delete("sun.bz1.dao.mapper.Snatch.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("SnatchDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Snatch实体添加
	 * 
	 * @param snatch
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int insertBySnatch(SqlSession session, Snatch snatch) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.insert("sun.bz1.dao.mapper.Snatch.insertBySnatch", snatch);
		} catch (Exception e) {
			logger.error("SnatchDao--insertBySnatch--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Snatch实体更新
	 * 
	 * @param snatch
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int updateBySnatch(SqlSession session, Snatch snatch) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.update("sun.bz1.dao.mapper.Snatch.updateBySnatch", snatch);
		} catch (Exception e) {
			logger.error("SnatchDao--updateBySnatch--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, SnatchAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.selectOne("sun.bz1.dao.mapper.Snatch.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("SnatchDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询
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
	 * @author WJF on 2018/09/08
	 */
	public List<SnatchAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, SnatchAndOrderTableAndUser unionData) {
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();

		try {
			logger.info("SnatchDao");

			snatchList = session.selectList("sun.bz1.dao.mapper.Snatch.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("SnatchDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return snatchList;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询 按时间排序
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/10/26
	 */
	public int selectThreeTablesCountByUnionDataByTime(SqlSession session, SnatchAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.selectOne("sun.bz1.dao.mapper.Snatch.selectThreeTablesCountByUnionDataByTime", unionData);
		} catch (Exception e) {
			logger.error("SnatchDao--selectThreeTablesCountByUnionDataByTime--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询 按时间排序
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
	 * @author ZY on 2018/10/26
	 */
	public List<SnatchAndOrderTableAndUser> selectThreeTablesByUnionDataByTime(SqlSession session, SnatchAndOrderTableAndUser unionData) {
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();

		try {
			logger.info("SnatchDao");

			snatchList = session.selectList("sun.bz1.dao.mapper.Snatch.selectThreeTablesByUnionDataByTime", unionData);
		} catch (Exception e) {
			logger.error("SnatchDao--selectThreeTablesByUnionDataByTime--error:" + e.getMessage());
		}

		return snatchList;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, SnatchAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.selectOne("sun.bz1.dao.mapper.Snatch.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("SnatchDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表模糊查询
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
	 * @author WJF on 2018/09/08
	 */
	public List<SnatchAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, SnatchAndOrderTableAndUser unionData) {
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();

		try {
			logger.info("SnatchDao");

			snatchList = session.selectList("sun.bz1.dao.mapper.Snatch.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("SnatchDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return snatchList;
	}


	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询 模糊查询 按时间排序
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
	 * @author ZY on 2018/10/26
	 */
	public List<SnatchAndOrderTableAndUser> selectThreeTablesBySelectDataByTime(SqlSession session, SnatchAndOrderTableAndUser unionData) {
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();

		try {
			logger.info("SnatchDao");

			snatchList = session.selectList("sun.bz1.dao.mapper.Snatch.selectThreeTablesBySelectDataByTime", unionData);
		} catch (Exception e) {
			logger.error("SnatchDao--selectThreeTablesBySelectDataByTime--error:" + e.getMessage());
		}

		return snatchList;
	}
	
	/**
	 * 根据Snatch实体删除
	 * 
	 * @param snatch
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int deleteBySnatch(SqlSession session, Snatch snatch) {
		int num = 0;

		try {
			logger.info("SnatchDao");

			num = session.delete("sun.bz1.dao.mapper.Snatch.deleteBySnatch", snatch);
		} catch (Exception e) {
			logger.error("SnatchDao--deleteBySnatch--error:" + e.getMessage());
		}

		return num;
	}
    
}