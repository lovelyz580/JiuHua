package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.CancelTable;
import sun.bz1.entity.CancelTableAndOrderTableAndUser;

/**
 * 客户取消维修单表(客户取消维修单时在该表中添加数据)
 * 
 * Dao
 * 
 * @author ZY on 2018/09/08
 */

@Repository
public class CancelTableDao {

	private Logger logger = Logger.getLogger(CancelTableDao.class);

	public int insert(SqlSession session, CancelTable cancelTable) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.insert("sun.bz1.dao.mapper.CancelTable.insert", cancelTable);
		} catch (Exception e) {
			logger.error("CancelTableDao--insert--error:" + e.getMessage());
		}
		
		return num;
	}

	public int insertSelective(SqlSession session, CancelTable cancelTable) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.insert("sun.bz1.dao.mapper.CancelTable.insertSelective", cancelTable);
		} catch (Exception e) {
			logger.error("CancelTableDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, CancelTable cancelTable) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.update("sun.bz1.dao.mapper.CancelTable.updateByPrimaryKeySelective", cancelTable);
		} catch (Exception e) {
			logger.error("CancelTableDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, CancelTable cancelTable) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.update("sun.bz1.dao.mapper.CancelTable.updateByPrimaryKey", cancelTable);
		} catch (Exception e) {
			logger.error("CancelTableDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.CancelTable.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CancelTableDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public CancelTable selectByPrimaryKey(SqlSession session, Integer id) {
		CancelTable cancelTable = new CancelTable();

		try {
			logger.info("CancelTableDao");

			cancelTable = session.selectOne("sun.bz1.dao.mapper.CancelTable.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CancelTableDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return cancelTable;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.delete("sun.bz1.dao.mapper.CancelTable.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CancelTableDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CancelTable实体添加
	 * 
	 * @param cancelTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int insertByCancelTable(SqlSession session, CancelTable cancelTable) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.insert("sun.bz1.dao.mapper.CancelTable.insertByCancelTable", cancelTable);
		} catch (Exception e) {
			logger.error("CancelTableDao--insertByCancelTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CancelTable实体更新
	 * 
	 * @param cancelTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int updateByCancelTable(SqlSession session, CancelTable cancelTable) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.update("sun.bz1.dao.mapper.CancelTable.updateByCancelTable", cancelTable);
		} catch (Exception e) {
			logger.error("CancelTableDao--updateByCancelTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CancelTableAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, CancelTableAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.CancelTable.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("CancelTableDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CancelTableAndOrderTableAndUser实体联表查询
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
	 * @author ZY on 2018/09/08
	 */
	public List<CancelTableAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session,
			CancelTableAndOrderTableAndUser unionData) {
		List<CancelTableAndOrderTableAndUser> cancelTableList = new ArrayList<CancelTableAndOrderTableAndUser>();

		try {
			logger.info("CancelTableDao");

			cancelTableList = session.selectList("sun.bz1.dao.mapper.CancelTable.selectThreeTablesByUnionData",
					unionData);
		} catch (Exception e) {
			logger.error("CancelTableDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return cancelTableList;
	}

	/**
	 * 根据CancelTableAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, CancelTableAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.CancelTable.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("CancelTableDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CancelTableAndOrderTableAndUser实体联表模糊查询
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
	 * @author ZY on 2018/09/08
	 */
	public List<CancelTableAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session,
			CancelTableAndOrderTableAndUser unionData) {
		List<CancelTableAndOrderTableAndUser> cancelTableList = new ArrayList<CancelTableAndOrderTableAndUser>();

		try {
			logger.info("CancelTableDao");

			cancelTableList = session.selectList("sun.bz1.dao.mapper.CancelTable.selectThreeTablesBySelectData",
					unionData);
		} catch (Exception e) {
			logger.error("CancelTableDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return cancelTableList;
	}

	/**
	 * 根据CancelTable实体删除
	 * 
	 * @param cancelTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int deleteByCancelTable(SqlSession session, CancelTable cancelTable) {
		int num = 0;

		try {
			logger.info("CancelTableDao");

			num = session.delete("sun.bz1.dao.mapper.CancelTable.deleteByCancelTable", cancelTable);
		} catch (Exception e) {
			logger.error("CancelTableDao--deleteByCancelTable--error:" + e.getMessage());
		}

		return num;
	}
	
}