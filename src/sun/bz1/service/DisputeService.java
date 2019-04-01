package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.DisputeDao;
import sun.bz1.entity.Dispute;
import sun.bz1.entity.DisputeAndUser;
import util.MyBatisUtil;

/**
 * 纠纷表
 * 
 * Service
 * 
 * @author ZY on 2018/09/07
 */

@Service
public class DisputeService {

	@Autowired
	private DisputeDao disputeDao;

	private Logger logger = Logger.getLogger(DisputeService.class);

	/**
	 * 根据Dispute实体添加
	 * 
	 * @param dispute
	 * @return
	 * 
	 * @author ZY on 2018/09/07
	 */
	public int insertByDispute(Dispute dispute) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			num = disputeDao.insertByDispute(session, dispute);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DisputeService--insertByDispute--error:" + e.getMessage());
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
	public int updateByDispute(Dispute dispute) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = disputeDao.updateByDispute(session, dispute);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DisputeService--updateByDispute--error:" + e.getMessage());
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
	public int selectTwoTablesCountByUnionData(DisputeAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = disputeDao.selectTwoTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DisputeService--selectTwoTablesCountByUnionData--error:" + e.getMessage());
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
	public List<DisputeAndUser> selectTwoTablesByUnionData(DisputeAndUser unionData) {
		List<DisputeAndUser> disputeList = new ArrayList<DisputeAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			disputeList = disputeDao.selectTwoTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("GoodsService--selectTwoTablesByUnionData--error:" + e.getMessage());
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
	public int selectTwoTablesCountBySelectData(DisputeAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = disputeDao.selectTwoTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DisputeService--selectTwoTablesCountBySelectData--error:" + e.getMessage());
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
	public List<DisputeAndUser> selectTwoTablesBySelectData(DisputeAndUser unionData) {
		List<DisputeAndUser> disputeList = new ArrayList<DisputeAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			disputeList = disputeDao.selectTwoTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DisputeService--selectTwoTablesBySelectData--error:" + e.getMessage());
		}

		return disputeList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/09/07
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + disputeDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DisputeService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
