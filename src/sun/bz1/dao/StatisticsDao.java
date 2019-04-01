package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Statistics;

/**
 * 统计数据
 * 
 * Dao
 * 
 * @author WJF on 2018/09/25
 */

@Repository
public class StatisticsDao {

	private Logger logger = Logger.getLogger(SnatchDao.class);
	
	
	/**
	 * 根据Statistics实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param statistics
	 * @return
	 * 
	 * @author WJF on 2018/09/25
	 */
	public int selectCountBySelectData(SqlSession session, Statistics statistics) {
		int num = 0;

		try {
			logger.info("StatisticsDao");

			num = session.selectOne("sun.bz1.dao.mapper.Statistics.selectCountBySelectData", statistics);
		} catch (Exception e) {
			logger.error("StatisticsDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Statistics实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param statistics
	 * @return
	 * 
	 * @author WJF on 2018/09/25
	 */
	public List<Statistics> selectBySelectData(SqlSession session, Statistics statistics) {
		List<Statistics> statisticsList = new ArrayList<Statistics>();

		try {
			logger.info("StatisticsDao");

			statisticsList = session.selectList("sun.bz1.dao.mapper.Statistics.selectBySelectData", statistics);
		} catch (Exception e) {
			logger.error("StatisticsDao--selectBySelectData--error:" + e.getMessage());
		}

		return statisticsList;
	}
	
}
