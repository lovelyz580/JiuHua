package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Distribution;
import sun.bz1.entity.DistributionAndOrderTableAndUser;

/**
 * 维修单分配记录表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/08
 */

@Repository
public class DistributionDao {
	
	private Logger logger = Logger.getLogger(DistributionDao.class);

	public int insert(SqlSession session, Distribution distribution) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.insert("sun.bz1.dao.mapper.Distribution.insert", distribution);
		} catch (Exception e) {
			logger.error("DistributionDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Distribution distribution) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.insert("sun.bz1.dao.mapper.Distribution.insertSelective", distribution);
		} catch (Exception e) {
			logger.error("DistributionDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Distribution distribution) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.update("sun.bz1.dao.mapper.Distribution.updateByPrimaryKeySelective", distribution);
		} catch (Exception e) {
			logger.error("DistributionDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Distribution distribution) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.update("sun.bz1.dao.mapper.Distribution.updateByPrimaryKey", distribution);
		} catch (Exception e) {
			logger.error("DistributionDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.selectOne("sun.bz1.dao.mapper.Distribution.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("DistributionDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Distribution selectByPrimaryKey(SqlSession session, Integer id) {
		Distribution distribution = new Distribution();

		try {
			logger.info("DistributionDao");

			distribution = session.selectOne("sun.bz1.dao.mapper.Distribution.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("DistributionDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return distribution;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.delete("sun.bz1.dao.mapper.Distribution.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("DistributionDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Distribution实体添加
	 * 
	 * @param distribution
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int insertByDistribution(SqlSession session, Distribution distribution) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.insert("sun.bz1.dao.mapper.Distribution.insertByDistribution", distribution);
		} catch (Exception e) {
			logger.error("DistributionDao--insertByDistribution--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Distribution实体更新
	 * 
	 * @param distribution
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int updateByDistribution(SqlSession session, Distribution distribution) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.update("sun.bz1.dao.mapper.Distribution.updateByDistribution", distribution);
		} catch (Exception e) {
			logger.error("DistributionDao--updateByDistribution--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DistributionAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, DistributionAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.selectOne("sun.bz1.dao.mapper.Distribution.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("DistributionDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据DistributionAndOrderTableAndUser实体联表查询
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
	public List<DistributionAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, DistributionAndOrderTableAndUser unionData) {
		List<DistributionAndOrderTableAndUser> distributionList = new ArrayList<DistributionAndOrderTableAndUser>();

		try {
			logger.info("DistributionDao");

			distributionList = session.selectList("sun.bz1.dao.mapper.Distribution.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("DistributionDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return distributionList;
	}

	/**
	 * 根据DistributionAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, DistributionAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.selectOne("sun.bz1.dao.mapper.Distribution.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("DistributionDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DistributionAndOrderTableAndUser实体联表模糊查询
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
	public List<DistributionAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, DistributionAndOrderTableAndUser unionData) {
		List<DistributionAndOrderTableAndUser> distributionList = new ArrayList<DistributionAndOrderTableAndUser>();

		try {
			logger.info("DistributionDao");

			distributionList = session.selectList("sun.bz1.dao.mapper.Distribution.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("DistributionDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return distributionList;
	}
	
	/**
	 * 根据Distribution实体删除
	 * 
	 * @param distribution
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int deleteByDistribution(SqlSession session, Distribution distribution) {
		int num = 0;

		try {
			logger.info("DistributionDao");

			num = session.delete("sun.bz1.dao.mapper.Distribution.deleteByDistribution", distribution);
		} catch (Exception e) {
			logger.error("DistributionDao--deleteByDistribution--error:" + e.getMessage());
		}

		return num;
	}
    
}