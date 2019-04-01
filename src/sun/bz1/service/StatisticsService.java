package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.StatisticsDao;
import sun.bz1.entity.Statistics;
import util.MyBatisUtil;

/**
 * 统计数据
 * 
 * Service
 * 
 * @author WJF on 2018/09/25
 */

@Service
public class StatisticsService {
	
	@Autowired
	private StatisticsDao statisticsDao;

	private Logger logger = Logger.getLogger(StatisticsService.class);

	/**
	 * 根据Statistics实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param statistics
	 * @return
	 * 
	 * @author WJF on 2018/09/25
	 */
	public int selectCountBySelectData(Statistics statistics) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = statisticsDao.selectCountBySelectData(session, statistics);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("StatisticsService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Statistics实体联表模糊查询
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
	public List<Statistics> selectBySelectData(Statistics statistics) {
		List<Statistics> statisticsList = new ArrayList<Statistics>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			statisticsList = statisticsDao.selectBySelectData(session, statistics);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("StatisticsService--selectBySelectData--error:" + e.getMessage());
		}

		return statisticsList;
	}
	
}
