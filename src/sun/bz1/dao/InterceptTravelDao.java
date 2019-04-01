package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.InterceptTravel;

/**
 * 差旅费(每公里)拦标价表
 * 
 * Dao
 * 
 * @author WJF on 2018/10/09
 */

@Repository
public class InterceptTravelDao {
	
	private Logger logger = Logger.getLogger(InterceptTravelDao.class);

	public int insert(SqlSession session, InterceptTravel interceptTravel) {
		int num = 0;

		try {
			logger.info("InterceptTravelDao");

			num = session.insert("sun.bz1.dao.mapper.InterceptTravel.insert", interceptTravel);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, InterceptTravel interceptTravel) {
		int num = 0;

		try {
			logger.info("InterceptTravelDao");

			num = session.insert("sun.bz1.dao.mapper.InterceptTravel.insertSelective", interceptTravel);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, InterceptTravel interceptTravel) {
		int num = 0;

		try {
			logger.info("InterceptTravelDao");

			num = session.update("sun.bz1.dao.mapper.InterceptTravel.updateByPrimaryKeySelective", interceptTravel);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, InterceptTravel interceptTravel) {
		int num = 0;

		try {
			logger.info("InterceptTravelDao");

			num = session.update("sun.bz1.dao.mapper.InterceptTravel.updateByPrimaryKey", interceptTravel);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("InterceptTravelDao");

			num = session.selectOne("sun.bz1.dao.mapper.InterceptTravel.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public InterceptTravel selectByPrimaryKey(SqlSession session, Integer id) {
		InterceptTravel InterceptTravel = new InterceptTravel();

		try {
			logger.info("InterceptTravelDao");

			InterceptTravel = session.selectOne("sun.bz1.dao.mapper.InterceptTravel.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return InterceptTravel;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("InterceptTravelDao");

			num = session.delete("sun.bz1.dao.mapper.InterceptTravel.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptTravel实体添加
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int insertByInterceptTravel(SqlSession session, InterceptTravel interceptTravel) {
		int num = 0;

		try {
			logger.info("InterceptTravelDao");

			num = session.insert("sun.bz1.dao.mapper.InterceptTravel.insertByInterceptTravel", interceptTravel);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--insertByInterceptTravel--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptTravel实体更新
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int updateByInterceptTravel(SqlSession session, InterceptTravel interceptTravel) {
		int num = 0;

		try {
			logger.info("InterceptTravelDao");

			num = session.update("sun.bz1.dao.mapper.InterceptTravel.updateByInterceptTravel", interceptTravel);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--updateByInterceptTravel--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptTravel实体查询
	 * 
	 * 查询数量
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int selectCountByInterceptTravel(SqlSession session, InterceptTravel interceptTravel) {
		int num = 0;

		try {
			logger.info("InterceptTravelDao");

			num = session.selectOne("sun.bz1.dao.mapper.InterceptTravel.selectCountByInterceptTravel", interceptTravel);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--selectCountByInterceptTravel--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptTravel实体查询
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public List<InterceptTravel> selectByInterceptTravel(SqlSession session, InterceptTravel interceptTravel) {
		List<InterceptTravel> interceptTravelList = new ArrayList<InterceptTravel>();

		try {
			logger.info("InterceptTravelDao");

			interceptTravelList = session.selectList("sun.bz1.dao.mapper.InterceptTravel.selectByInterceptTravel", interceptTravel);
		} catch (Exception e) {
			logger.error("InterceptTravelDao--selectByInterceptTravel--error:" + e.getMessage());
		}

		return interceptTravelList;
	}

}