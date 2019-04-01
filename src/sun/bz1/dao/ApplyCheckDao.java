package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.ApplyCheck;
import sun.bz1.entity.ApplyCheckAndOrderTableAndUser;

/**
 * 维修验收表(验收成功或失败时在该表中添加数据)
 * 
 * Dao
 * 
 * @author WJF on 2018/09/08
 */

@Repository
public class ApplyCheckDao {
	
	private Logger logger = Logger.getLogger(ApplyCheckDao.class);

	public int insert(SqlSession session, ApplyCheck applyCheck) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.insert("sun.bz1.dao.mapper.ApplyCheck.insert", applyCheck);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, ApplyCheck applyCheck) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.insert("sun.bz1.dao.mapper.ApplyCheck.insertSelective", applyCheck);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, ApplyCheck applyCheck) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.update("sun.bz1.dao.mapper.ApplyCheck.updateByPrimaryKeySelective", applyCheck);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, ApplyCheck applyCheck) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.update("sun.bz1.dao.mapper.ApplyCheck.updateByPrimaryKey", applyCheck);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.selectOne("sun.bz1.dao.mapper.ApplyCheck.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public ApplyCheck selectByPrimaryKey(SqlSession session, Integer id) {
		ApplyCheck applyCheck = new ApplyCheck();

		try {
			logger.info("ApplyCheckDao");

			applyCheck = session.selectOne("sun.bz1.dao.mapper.ApplyCheck.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return applyCheck;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.delete("sun.bz1.dao.mapper.ApplyCheck.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ApplyCheck实体添加
	 * 
	 * @param applyCheck
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int insertByApplyCheck(SqlSession session, ApplyCheck applyCheck) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.insert("sun.bz1.dao.mapper.ApplyCheck.insertByApplyCheck", applyCheck);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--insertByApplyCheck--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ApplyCheck实体更新
	 * 
	 * @param applyCheck
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int updateByApplyCheck(SqlSession session, ApplyCheck applyCheck) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.update("sun.bz1.dao.mapper.ApplyCheck.updateByApplyCheck", applyCheck);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--updateByApplyCheck--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.selectOne("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表查询
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
	public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
		List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

		try {
			logger.info("ApplyCheckDao");

			applyCheckList = session.selectList("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return applyCheckList;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表查询 申请时间倒序
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
	public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesByUnionDataByTimeDesc(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
		List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

		try {
			logger.info("ApplyCheckDao");

			applyCheckList = session.selectList("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesByUnionDataByTimeDesc", unionData);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--selectThreeTablesByUnionDataByTimeDesc--error:" + e.getMessage());
		}

		return applyCheckList;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.selectOne("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表模糊查询
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
	public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
		List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

		try {
			logger.info("ApplyCheckDao");

			applyCheckList = session.selectList("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return applyCheckList;
	}
	
	/**
	 * 根据ApplyCheck实体删除
	 * 
	 * @param applyCheck
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int deleteByApplyCheck(SqlSession session, ApplyCheck applyCheck) {
		int num = 0;

		try {
			logger.info("ApplyCheckDao");

			num = session.delete("sun.bz1.dao.mapper.ApplyCheck.deleteByApplyCheck", applyCheck);
		} catch (Exception e) {
			logger.error("ApplyCheckDao--deleteByApplyCheck--error:" + e.getMessage());
		}

		return num;
	}
	
}