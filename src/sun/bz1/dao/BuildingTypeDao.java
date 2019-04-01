package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.BuildingType;

/**
 * 建筑类型表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/04
 */

@Repository
public class BuildingTypeDao {

	private Logger logger = Logger.getLogger(BuildingTypeDao.class);

	public int insert(SqlSession session, BuildingType type) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.insert("sun.bz1.dao.mapper.BuildingType.insert", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, BuildingType type) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.insert("sun.bz1.dao.mapper.BuildingType.insertSelective", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, BuildingType type) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.update("sun.bz1.dao.mapper.BuildingType.updateByPrimaryKeySelective", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, BuildingType type) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.update("sun.bz1.dao.mapper.BuildingType.updateByPrimaryKey", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.selectOne("sun.bz1.dao.mapper.BuildingType.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public BuildingType selectByPrimaryKey(SqlSession session, Integer id) {
		BuildingType type = new BuildingType();

		try {
			logger.info("BuildingTypeDao");

			type = session.selectOne("sun.bz1.dao.mapper.BuildingType.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return type;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.delete("sun.bz1.dao.mapper.BuildingType.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BuildingType实体添加
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int insertByBuildingType(SqlSession session, BuildingType type) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.insert("sun.bz1.dao.mapper.BuildingType.insertByBuildingType", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--insertByBuildingType--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BuildingType实体更新
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateByBuildingType(SqlSession session, BuildingType type) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.update("sun.bz1.dao.mapper.BuildingType.updateByBuildingType", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--updateByBuildingType--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BuildingType实体查询
	 * 
	 * 查询数量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByBuildingType(SqlSession session, BuildingType type) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.selectOne("sun.bz1.dao.mapper.BuildingType.selectCountByBuildingType", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--selectCountByBuildingType--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BuildingType实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<BuildingType> selectByBuildingType(SqlSession session, BuildingType type) {
		List<BuildingType> typeList = new ArrayList<BuildingType>();

		try {
			logger.info("BuildingTypeDao");

			typeList = session.selectList("sun.bz1.dao.mapper.BuildingType.selectByBuildingType", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--selectByBuildingType--error:" + e.getMessage());
		}

		return typeList;
	}

	/**
	 * 根据BuildingType实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectData(SqlSession session, BuildingType type) {
		int num = 0;

		try {
			logger.info("BuildingTypeDao");

			num = session.selectOne("sun.bz1.dao.mapper.BuildingType.selectCountBySelectData", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BuildingType实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<BuildingType> selectBySelectData(SqlSession session, BuildingType type) {
		List<BuildingType> typeList = new ArrayList<BuildingType>();

		try {
			logger.info("BuildingTypeDao");

			typeList = session.selectList("sun.bz1.dao.mapper.BuildingType.selectBySelectData", type);
		} catch (Exception e) {
			logger.error("BuildingTypeDao--selectBySelectData--error:" + e.getMessage());
		}

		return typeList;
	}
	
}