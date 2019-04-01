package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Scale;

/**
 * 接单规模表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/04
 */

@Repository
public class ScaleDao {

	private Logger logger = Logger.getLogger(ScaleDao.class);

	public int insert(SqlSession session, Scale scale) {
		int num = 0;

		try {
			logger.info("ScaleDao");

			num = session.insert("sun.bz1.dao.mapper.Scale.insert", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Scale scale) {
		int num = 0;

		try {
			logger.info("ScaleDao");

			num = session.insert("sun.bz1.dao.mapper.Scale.insertSelective", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Scale scale) {
		int num = 0;

		try {
			logger.info("ScaleDao");

			num = session.update("sun.bz1.dao.mapper.Scale.updateByPrimaryKeySelective", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Scale scale) {
		int num = 0;

		try {
			logger.info("ScaleDao");

			num = session.update("sun.bz1.dao.mapper.Scale.updateByPrimaryKey", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Scale selectCountByPrimaryKey(SqlSession session, Integer id) {
		Scale scale = new Scale();

		try {
			logger.info("ScaleDao");

			scale = session.selectOne("sun.bz1.dao.mapper.Scale.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ScaleDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return scale;
	}

	public Scale selectByPrimaryKey(SqlSession session, Integer id) {
		Scale scale = new Scale();

		try {
			logger.info("ScaleDao");

			scale = session.selectOne("sun.bz1.dao.mapper.Scale.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ScaleDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return scale;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("ScaleDao");

			num = session.update("sun.bz1.dao.mapper.Scale.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ScaleDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Scale实体添加
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int insertByScale(SqlSession session, Scale scale) {
		int num = 0;

		try {
			logger.info("ScaleDao");

			num = session.insert("sun.bz1.dao.mapper.Scale.insertByScale", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--insertByScale--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Scale实体更新
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateByScale(SqlSession session, Scale scale) {
		int num = 0;

		try {
			logger.info("ScaleDao");

			num = session.update("sun.bz1.dao.mapper.Scale.updateByScale", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--updateByScale--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Scale实体查询
	 * 
	 * 查询数量
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByScale(SqlSession session, Scale scale) {
		int num = 0;

		try {
			logger.info("ScaleDao");

			num = session.selectOne("sun.bz1.dao.mapper.Scale.selectCountByScale", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--selectCountByScale--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Scale实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Scale> selectByScale(SqlSession session, Scale scale) {
		List<Scale> scaleList = new ArrayList<Scale>();

		try {
			logger.info("ScaleDao");

			scaleList = session.selectList("sun.bz1.dao.mapper.Scale.selectByScale", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--selectByScale--error:" + e.getMessage());
		}

		return scaleList;
	}

	/**
	 * 根据Scale实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectData(SqlSession session, Scale scale) {
		int num = 0;

		try {
			logger.info("ScaleDao");

			num = session.selectOne("sun.bz1.dao.mapper.Scale.selectCountBySelectData", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Scale实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Scale> selectBySelectData(SqlSession session, Scale scale) {
		List<Scale> scaleList = new ArrayList<Scale>();

		try {
			logger.info("ScaleDao");

			scaleList = session.selectList("sun.bz1.dao.mapper.Scale.selectBySelectData", scale);
		} catch (Exception e) {
			logger.error("ScaleDao--selectBySelectData--error:" + e.getMessage());
		}

		return scaleList;
	}

}