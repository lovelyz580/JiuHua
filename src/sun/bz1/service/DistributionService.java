package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.DistributionDao;
import sun.bz1.entity.Distribution;
import sun.bz1.entity.DistributionAndOrderTableAndUser;
import util.MyBatisUtil;

/**
 * 维修单分配记录表
 * 
 * Service
 * 
 * @author WJF on 2018/09/08
 */

@Service
public class DistributionService {

	@Autowired
	private DistributionDao distributionDao;

	private Logger logger = Logger.getLogger(DistributionService.class);
	
	/**
   	 * 根据Distribution实体添加
   	 * 
   	 * @param distribution
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/08
   	 */
	public int insertByDistribution(Distribution distribution) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = distributionDao.insertByDistribution(session, distribution);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributionService--insertByDistribution--error:" + e.getMessage());
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
	public int updateByDistribution(Distribution distribution) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = distributionDao.updateByDistribution(session, distribution);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributionService--updateByDistribution--error:" + e.getMessage());
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
	public int selectThreeTablesCountByUnionData(DistributionAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = distributionDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributionService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
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
	public List<DistributionAndOrderTableAndUser> selectThreeTablesByUnionData(DistributionAndOrderTableAndUser unionData) {
		List<DistributionAndOrderTableAndUser> distributionList = new ArrayList<DistributionAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			distributionList = distributionDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributionService--selectThreeTablesByUnionData--error:" + e.getMessage());
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
	public int selectThreeTablesCountBySelectData(DistributionAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = distributionDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributionService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
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
	public List<DistributionAndOrderTableAndUser> selectThreeTablesBySelectData(DistributionAndOrderTableAndUser unionData) {
		List<DistributionAndOrderTableAndUser> distributionList = new ArrayList<DistributionAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			distributionList = distributionDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributionService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return distributionList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + distributionDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributionService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				Distribution distribution = new Distribution();
				distribution.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + distributionDao.deleteByDistribution(session, distribution);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributionService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
