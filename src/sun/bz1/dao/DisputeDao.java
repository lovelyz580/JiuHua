package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Dispute;
import sun.bz1.entity.DisputeAndUser;

/**
 * 纠纷表
 * 
 * Dao
 * 
 * @author zy on 2018/09/07
 */

@Repository
public class DisputeDao {

	private Logger logger = Logger.getLogger(GoodsDao.class);

	public int insert(SqlSession session, Dispute dispute) {
		int num = 0;
		
		try {
			logger.info("DisputeDao");

			num = session.insert("sun.bz1.dao.mapper.Dispute.insert", dispute);

		} catch (Exception e) {
			logger.error("DisputeDao -- insert--error:" + e.getMessage());
		}
		
		return num;
	}

	public int insertSelective(SqlSession session, Dispute dispute) {
		int num = 0;
		
		try {
			logger.info("DisputeDao");

			num = session.insert("sun.bz1.dao.mapper.Dispute.insertSelective", dispute);

		} catch (Exception e) {
			logger.error("DisputeDao -- insertSelective--error:" + e.getMessage());
		}
		
		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Dispute dispute) {
		int num = 0;

		try {
			logger.info("DisputeDao");

			num = session.update("sun.bz1.dao.mapper.Dispute.updateByPrimaryKeySelective", dispute);
		} catch (Exception e) {
			logger.error("DisputeDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Dispute dispute) {
		int num = 0;

		try {
			logger.info("DisputeDao");

			num = session.update("sun.bz1.dao.mapper.Dispute.updateByPrimaryKey", dispute);
		} catch (Exception e) {
			logger.error("DisputeDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("DisputeDao");

			num = session.delete("sun.bz1.dao.mapper.Dispute.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("DisputeDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("DisputeDao");

			num = session.selectOne("sun.bz1.dao.mapper.Dispute.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("DisputeDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Dispute selectByPrimaryKey(SqlSession session, Integer id) {
		Dispute dispute = new Dispute();

		try {
			logger.info("DisputeDao");

			dispute = session.selectOne("sun.bz1.dao.mapper.Dispute.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("DisputeDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return dispute;
	}

	/**
	 * 根据Dispute实体添加
	 * 
	 * @param dispute
	 * @return
	 * 
	 * @author zy on 2018/09/07
	 */
	public int insertByDispute(SqlSession session, Dispute dispute) {
		int num = 0;
		
		try {
			logger.info("DisputeDao");
			num = session.insert("sun.bz1.dao.mapper.Dispute.insertByDispute", dispute);
		} catch (Exception e) {
			logger.error("DisputeDao--insertByDispute--error:" + e.getMessage());
		}
		
		return num;
	}

	/**
	 * 根据Dispute实体更新
	 * 
	 * @param dispute
	 * @return
	 * 
	 * @author ZY on 2018/09/07
	 */
	public int updateByDispute(SqlSession session, Dispute dispute) {
		int num = 0;

		try {
			logger.info("DisputeDao");

			num = session.update("sun.bz1.dao.mapper.Dispute.updateByDispute", dispute);
		} catch (Exception e) {
			logger.error("DisputeDao--updateByDispute--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DisputeAndUser实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/07
	 */
	public int selectTwoTablesCountByUnionData(SqlSession session, DisputeAndUser unionData) {
		int num = 0;

		try {
			logger.info("DisputeDao");

			num = session.selectOne("sun.bz1.dao.mapper.Dispute.selectTwoTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("DisputeDao--selectTwoTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DisputeAndUser实体联表查询
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
	 * @author ZY on 2018/09/07
	 */
	public List<DisputeAndUser> selectTwoTablesByUnionData(SqlSession session, DisputeAndUser unionData) {
		List<DisputeAndUser> disputeList = new ArrayList<DisputeAndUser>();

		try {
			logger.info("DisputeDao");

			disputeList = session.selectList("sun.bz1.dao.mapper.Dispute.selectTwoTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("DisputeDao--selectTwoTablesByUnionData--error:" + e.getMessage());
		}

		return disputeList;
	}

	/**
	 * 根据DisputeAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/07
	 */
	public int selectTwoTablesCountBySelectData(SqlSession session, DisputeAndUser unionData) {
		int num = 0;

		try {
			logger.info("DisputeDao");

			num = session.selectOne("sun.bz1.dao.mapper.Dispute.selectTwoTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("DisputeDao--selectTwoTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DisputeAndUser实体联表模糊查询
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
	 * @author ZY on 2018/09/07
	 */
	public List<DisputeAndUser> selectTwoTablesBySelectData(SqlSession session, DisputeAndUser unionData) {
		List<DisputeAndUser> disputeList = new ArrayList<DisputeAndUser>();

		try {
			logger.info("DisputeDao");

			disputeList = session.selectList("sun.bz1.dao.mapper.Dispute.selectTwoTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("DisputeDao--selectTwoTablesBySelectData--error:" + e.getMessage());
		}

		return disputeList;
	}

}