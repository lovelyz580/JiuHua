package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.CreditChange;

/**
 * 信用值_变化表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/05
 */

@Repository
public class CreditChangeDao {
	
	private Logger logger = Logger.getLogger(CreditChangeDao.class);

	public int insert(SqlSession session, CreditChange creditChange) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.insert("sun.bz1.dao.mapper.CreditChange.insert", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, CreditChange creditChange) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.insert("sun.bz1.dao.mapper.CreditChange.insertSelective", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, CreditChange creditChange) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.update("sun.bz1.dao.mapper.CreditChange.updateByPrimaryKeySelective", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, CreditChange creditChange) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.update("sun.bz1.dao.mapper.CreditChange.updateByPrimaryKey", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.selectOne("sun.bz1.dao.mapper.CreditChange.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CreditChangeDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public CreditChange selectByPrimaryKey(SqlSession session, Integer id) {
		CreditChange creditChange = new CreditChange();

		try {
			logger.info("CreditChangeDao");

			creditChange = session.selectOne("sun.bz1.dao.mapper.CreditChange.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CreditChangeDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return creditChange;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.delete("sun.bz1.dao.mapper.CreditChange.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CreditChangeDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditChange实体添加
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int insertByCreditChange(SqlSession session, CreditChange creditChange) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.insert("sun.bz1.dao.mapper.CreditChange.insertByCreditChange", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--insertByCreditChange--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditChange实体更新
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByCreditChange(SqlSession session, CreditChange creditChange) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.update("sun.bz1.dao.mapper.CreditChange.updateByCreditChange", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--updateByCreditChange--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditChange实体查询
	 * 
	 * 查询数量
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectCountByCreditChange(SqlSession session, CreditChange creditChange) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.selectOne("sun.bz1.dao.mapper.CreditChange.selectCountByCreditChange", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--selectCountByCreditChange--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditChange实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public List<CreditChange> selectByCreditChange(SqlSession session, CreditChange creditChange) {
		List<CreditChange> creditChangeList = new ArrayList<CreditChange>();

		try {
			logger.info("CreditChangeDao");

			creditChangeList = session.selectList("sun.bz1.dao.mapper.CreditChange.selectByCreditChange", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--selectByCreditChange--error:" + e.getMessage());
		}

		return creditChangeList;
	}

	/**
	 * 根据CreditChange实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectCountBySelectData(SqlSession session, CreditChange creditChange) {
		int num = 0;

		try {
			logger.info("CreditChangeDao");

			num = session.selectOne("sun.bz1.dao.mapper.CreditChange.selectCountBySelectData", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditChange实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public List<CreditChange> selectBySelectData(SqlSession session, CreditChange creditChange) {
		List<CreditChange> creditChangeList = new ArrayList<CreditChange>();

		try {
			logger.info("CreditChangeDao");

			creditChangeList = session.selectList("sun.bz1.dao.mapper.CreditChange.selectBySelectData", creditChange);
		} catch (Exception e) {
			logger.error("CreditChangeDao--selectBySelectData--error:" + e.getMessage());
		}

		return creditChangeList;
	}
	
}