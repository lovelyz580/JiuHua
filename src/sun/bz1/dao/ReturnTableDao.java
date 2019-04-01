package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.ReturnTable;
import sun.bz1.entity.ReturnTableAndOrderTableAndUser;

/**
 * 维修人员退回维修单表(维修人员退回维修单时在该表中添加数据)
 * 
 * Dao
 * 
 * @author ZY on 2018/09/07
 */

@Repository
public class ReturnTableDao {

	private Logger logger = Logger.getLogger(ReturnTableDao.class);

	public int insert(SqlSession session, ReturnTable returnTable) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.insert("sun.bz1.dao.mapper.ReturnTable.insert", returnTable);
		} catch (Exception e) {
			logger.error("ReturnTableDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, ReturnTable returnTable) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.insert("sun.bz1.dao.mapper.ReturnTable.insertSelective", returnTable);
		} catch (Exception e) {
			logger.error("ReturnTableDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, ReturnTable returnTable) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.update("sun.bz1.dao.mapper.ReturnTable.updateByPrimaryKeySelective", returnTable);
		} catch (Exception e) {
			logger.error("ReturnTableDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, ReturnTable returnTable) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.update("sun.bz1.dao.mapper.ReturnTable.updateByPrimaryKey", returnTable);
		} catch (Exception e) {
			logger.error("ReturnTableDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.ReturnTable.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ReturnTableDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public ReturnTable selectByPrimaryKey(SqlSession session, Integer id) {
		ReturnTable returnTable = new ReturnTable();

		try {
			logger.info("ReturnTableDao");

			returnTable = session.selectOne("sun.bz1.dao.mapper.ReturnTable.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ReturnTableDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return returnTable;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.delete("sun.bz1.dao.mapper.ReturnTable.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ReturnTableDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ReturnTable实体添加
	 * 
	 * @param returnTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int insertByReturnTable(SqlSession session, ReturnTable returnTable) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.insert("sun.bz1.dao.mapper.ReturnTable.insertByReturnTable", returnTable);
		} catch (Exception e) {
			logger.error("ReturnTableDao--insertByReturnTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ReturnTable实体更新
	 * 
	 * @param returnTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int updateByReturnTable(SqlSession session, ReturnTable returnTable) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.update("sun.bz1.dao.mapper.ReturnTable.updateByReturnTable", returnTable);
		} catch (Exception e) {
			logger.error("ReturnTableDao--updateByReturnTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ReturnTableAndOrderTableAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(SqlSession session, ReturnTableAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.ReturnTable.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("ReturnTableDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ReturnTableAndOrderTableAndUser实体联表查询
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
	public List<ReturnTableAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, ReturnTableAndOrderTableAndUser unionData) {
		List<ReturnTableAndOrderTableAndUser> returnTableList = new ArrayList<ReturnTableAndOrderTableAndUser>();

		try {
			logger.info("ReturnTableDao");

			returnTableList = session.selectList("sun.bz1.dao.mapper.ReturnTable.selectThreeTablesByUnionData",
					unionData);
		} catch (Exception e) {
			logger.error("ReturnTableDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return returnTableList;
	}

	/**
	 * 根据ReturnTableAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(SqlSession session, ReturnTableAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.selectOne("sun.bz1.dao.mapper.ReturnTable.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("ReturnTableDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ReturnTableAndOrderTableAndUser实体联表模糊查询
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
	public List<ReturnTableAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, ReturnTableAndOrderTableAndUser unionData) {
		List<ReturnTableAndOrderTableAndUser> returnTableList = new ArrayList<ReturnTableAndOrderTableAndUser>();

		try {
			logger.info("ReturnTableDao");

			returnTableList = session.selectList("sun.bz1.dao.mapper.ReturnTable.selectThreeTablesBySelectData",
					unionData);
		} catch (Exception e) {
			logger.error("ReturnTableDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return returnTableList;
	}

	/**
	 * 根据ReturnTable实体删除
	 * 
	 * @param returnTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int deleteByReturnTable(SqlSession session, ReturnTable returnTable) {
		int num = 0;

		try {
			logger.info("ReturnTableDao");

			num = session.delete("sun.bz1.dao.mapper.ReturnTable.deleteByReturnTable", returnTable);
		} catch (Exception e) {
			logger.error("ReturnTableDao--deleteByReturnTable--error:" + e.getMessage());
		}

		return num;
	}
	
}