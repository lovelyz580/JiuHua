package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.News;
import sun.bz1.entity.User;

/**
 * 新闻表
 * 
 * Dao
 * 
 * @author ZY on 2018/09/19
 */

@Repository
public class NewsDao {
	
	private Logger logger = Logger.getLogger(NewsDao.class);

	public int insert(SqlSession session, News news) {
		int num = 0;
		
		try {
			logger.info("NewsDao");
			
			num = session.insert("sun.bz1.dao.mapper.News.insert", news);
		} catch (Exception e) {
			logger.error("NewsDao--insert--error:" + e.getMessage());
		}

		return num;
	}
	
    public int insertSelective(SqlSession session, News news) {
		int num = 0;
		
		try {
			logger.info("NewsDao");
			
			num = session.insert("sun.bz1.dao.mapper.News.insertSelective", news);
		} catch (Exception e) {
			logger.error("NewsDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

    public int updateByPrimaryKeySelective(SqlSession session, News news) {
		int num = 0;
		
		try {
			logger.info("NewsDao");
			
			num = session.update("sun.bz1.dao.mapper.News.updateByPrimaryKeySelective", news);
		} catch (Exception e) {
			logger.error("NewsDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

    public int updateByPrimaryKey(SqlSession session, News news) {
		int num = 0;
		
		try {
			logger.info("NewsDao");
			
			num = session.update("sun.bz1.dao.mapper.News.updateByPrimaryKey", news);
		} catch (Exception e) {
			logger.error("NewsDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
    	int num = 0;
		
		try {
			logger.info("NewsDao");
			
			num = session.selectOne("sun.bz1.dao.mapper.News.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("NewsDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    public User selectByPrimaryKey(SqlSession session, Integer id) {
    	User user = new User();
		
		try {
			logger.info("NewsDao");
			
			user = session.selectOne("sun.bz1.dao.mapper.News.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("NewsDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return user;
	}
    
    public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;
		
		try {
			logger.info("NewsDao");
			
			num = session.delete("sun.bz1.dao.mapper.News.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("NewsDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    /**
	 * 根据News实体添加
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int insertByNews(SqlSession session, News news) {
		int num = 0;

		try {
			logger.info("NewsDao");

			num = session.insert("sun.bz1.dao.mapper.News.insertByNews", news);
		} catch (Exception e) {
			logger.error("NewsDao--insertByNews--error:" + e.getMessage());
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
	public int updateByNews(SqlSession session, News news) {
		int num = 0;

		try {
			logger.info("NewsDao");

			num = session.update("sun.bz1.dao.mapper.News.updateByNews", news);
		} catch (Exception e) {
			logger.error("NewsDao--updateByNews--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据News实体查询
	 * 
	 * 查询数量
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int selectCountByNews(SqlSession session, News news) {
		int num = 0;

		try {
			logger.info("NewsDao");

			num = session.selectOne("sun.bz1.dao.mapper.News.selectCountByNews", news);
		} catch (Exception e) {
			logger.error("NewsDao--selectCountByNews--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据News实体查询
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
	public List<News> selectByNews(SqlSession session, News news) {
		List<News> newsList = new ArrayList<News>();

		try {
			logger.info("NewsDao");

			newsList = session.selectList("sun.bz1.dao.mapper.News.selectByNews", news);
		} catch (Exception e) {
			logger.error("NewsDao--selectByNews--error:" + e.getMessage());
		}

		return newsList;
	}

	/**
	 * 根据News实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int selectCountBySelectData(SqlSession session, News news) {
		int num = 0;

		try {
			logger.info("NewsDao");

			num = session.selectOne("sun.bz1.dao.mapper.News.selectCountBySelectData", news);
		} catch (Exception e) {
			logger.error("NewsDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据News实体模糊查询
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
	public List<News> selectBySelectData(SqlSession session, News news) {
		List<News> newsList = new ArrayList<News>();

		try {
			logger.info("NewsDao");

			newsList = session.selectList("sun.bz1.dao.mapper.News.selectBySelectData", news);
		} catch (Exception e) {
			logger.error("NewsDao--selectBySelectData--error:" + e.getMessage());
		}

		return newsList;
	}
	
	/**
	 * 根据News实体删除
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int deleteByNews(SqlSession session, News news) {
		int num = 0;

		try {
			logger.info("NewsDao");

			num = session.delete("sun.bz1.dao.mapper.News.deleteByNews", news);
		} catch (Exception e) {
			logger.error("NewsDao--deleteByNews--error:" + e.getMessage());
		}

		return num;
	}
    
}