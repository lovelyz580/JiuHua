package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.NewsDao;
import sun.bz1.entity.News;
import util.MyBatisUtil;

/**
 * 新闻表
 * 
 * Service
 * 
 * @author ZY on 2018/09/19
 */

@Service
public class NewsService {

	@Autowired
	private NewsDao newsDao;

	private Logger logger = Logger.getLogger(NewsService.class);

	/**
	 * 根据News实体添加
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int insertByNews(News news) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = newsDao.insertByNews(session, news);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("NewsService--insertByNews--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据News实体更新
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int updateByNews(News news) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = newsDao.updateByNews(session, news);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("NewsService--updateByNews--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据News实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int selectCountByNews(News news) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = newsDao.selectCountByNews(session, news);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("NewsService--selectCountByNews--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据News实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public List<News> selectByNews(News news) {
		List<News> newsList = new ArrayList<News>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			newsList = newsDao.selectByNews(session, news);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("NewsService--selectByNews--error:" + e.getMessage());
		}

		return newsList;
	}

	/**
	 * 根据News实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int selectCountBySelectData(News news) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = newsDao.selectCountBySelectData(session, news);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("NewsService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据News实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public List<News> selectBySelectData(News news) {
		List<News> newsList = new ArrayList<News>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			newsList = newsDao.selectBySelectData(session, news);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("NewsService--selectBySelectData--error:" + e.getMessage());
		}

		return newsList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + newsDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("NewsService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
