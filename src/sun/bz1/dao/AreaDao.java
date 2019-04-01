package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Area;

/**
 * 接单区域表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/04
 */

@Repository
public class AreaDao {

	private Logger logger = Logger.getLogger(AreaDao.class);

	public int insert(SqlSession session, Area area) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.insert("sun.bz1.dao.mapper.Area.insert", area);
		} catch (Exception e) {
			logger.error("AreaDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Area area) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.insert("sun.bz1.dao.mapper.Area.insertSelective", area);
		} catch (Exception e) {
			logger.error("AreaDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Area area) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.update("sun.bz1.dao.mapper.Area.updateByPrimaryKeySelective", area);
		} catch (Exception e) {
			logger.error("AreaDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Area area) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.update("sun.bz1.dao.mapper.Area.updateByPrimaryKey", area);
		} catch (Exception e) {
			logger.error("AreaDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.selectOne("sun.bz1.dao.mapper.Area.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("AreaDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Area selectByPrimaryKey(SqlSession session, Integer id) {
		Area area = new Area();

		try {
			logger.info("AreaDao");

			area = session.selectOne("sun.bz1.dao.mapper.Area.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("AreaDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return area;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.delete("sun.bz1.dao.mapper.Area.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("AreaDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体添加
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int insertByArea(SqlSession session, Area area) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.insert("sun.bz1.dao.mapper.Area.insertByArea", area);
		} catch (Exception e) {
			logger.error("AreaDao--insertByArea--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体更新
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateByArea(SqlSession session, Area area) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.update("sun.bz1.dao.mapper.Area.updateByArea", area);
		} catch (Exception e) {
			logger.error("AreaDao--updateByArea--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体查询
	 * 
	 * 查询数量
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByArea(SqlSession session, Area area) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.selectOne("sun.bz1.dao.mapper.Area.selectCountByArea", area);
		} catch (Exception e) {
			logger.error("AreaDao--selectCountByArea--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Area> selectByArea(SqlSession session, Area area) {
		List<Area> areaList = new ArrayList<Area>();

		try {
			logger.info("AreaDao");

			areaList = session.selectList("sun.bz1.dao.mapper.Area.selectByArea", area);
		} catch (Exception e) {
			logger.error("AreaDao--selectByArea--error:" + e.getMessage());
		}

		return areaList;
	}

	/**
	 * 根据Area实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectData(SqlSession session, Area area) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.selectOne("sun.bz1.dao.mapper.Area.selectCountBySelectData", area);
		} catch (Exception e) {
			logger.error("AreaDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Area> selectBySelectData(SqlSession session, Area area) {
		List<Area> areaList = new ArrayList<Area>();

		try {
			logger.info("AreaDao");

			areaList = session.selectList("sun.bz1.dao.mapper.Area.selectBySelectData", area);
		} catch (Exception e) {
			logger.error("AreaDao--selectBySelectData--error:" + e.getMessage());
		}

		return areaList;
	}

	/**
	 * 根据Area实体模糊查询 （区域报表）
	 *
	 * 查询数量
	 *
	 * @param area
	 * @return
	 *
	 * @author ZY on 2018/12/03
	 */
	public int selectCountBySelectDataReport(SqlSession session, Area area) {
		int num = 0;

		try {
			logger.info("AreaDao");

			num = session.selectOne("sun.bz1.dao.mapper.Area.selectCountBySelectDataReport", area);
		} catch (Exception e) {
			logger.error("AreaDao--selectCountBySelectDataReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体模糊查询 (区域报表)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param area
	 * @return
	 *
	 * @author ZY on 2018/12/03
	 */
	public List<Area> selectBySelectDataReportCount(SqlSession session, Area area) {
		List<Area> areaList = new ArrayList<Area>();

		try {
			logger.info("AreaDao");

			areaList = session.selectList("sun.bz1.dao.mapper.Area.selectBySelectDataReportCount", area);
		} catch (Exception e) {
			logger.error("AreaDao--selectBySelectDataReportCount--error:" + e.getMessage());
		}

		return areaList;
	}

	/**
	 * 根据Area实体模糊查询 (区域报表)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param area
	 * @return
	 *
	 * @author ZY on 2018/12/03
	 */
	public List<Area> selectBySelectDataReport(SqlSession session, Area area) {
		List<Area> areaList = new ArrayList<Area>();

		try {
			logger.info("AreaDao");

			areaList = session.selectList("sun.bz1.dao.mapper.Area.selectBySelectDataReport", area);
		} catch (Exception e) {
			logger.error("AreaDao--selectBySelectDataReport--error:" + e.getMessage());
		}

		return areaList;
	}

}