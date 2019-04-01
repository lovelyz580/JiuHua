package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.SnatchDao;
import sun.bz1.entity.Snatch;
import sun.bz1.entity.SnatchAndOrderTableAndUser;
import util.MyBatisUtil;

/**
 * 维修单抢单记录表
 * 
 * Service
 * 
 * @author WJF on 2018/09/08
 */

@Service
public class SnatchService {

	@Autowired
	private SnatchDao snatchDao;

	private Logger logger = Logger.getLogger(SnatchService.class);
	
	/**
   	 * 根据Snatch实体添加
   	 * 
   	 * @param snatch
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/08
   	 */
	public int insertBySnatch(Snatch snatch) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = snatchDao.insertBySnatch(session, snatch);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--insertBySnatch--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Snatch实体更新
	 * 
	 * @param snatch
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int updateBySnatch(Snatch snatch) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = snatchDao.updateBySnatch(session, snatch);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--updateBySnatch--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(SnatchAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = snatchDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询
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
	public List<SnatchAndOrderTableAndUser> selectThreeTablesByUnionData(SnatchAndOrderTableAndUser unionData) {
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			snatchList = snatchDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return snatchList;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询 按时间排序
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/10/26
	 */
	public int selectThreeTablesCountByUnionDataByTime(SnatchAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = snatchDao.selectThreeTablesCountByUnionDataByTime(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--selectThreeTablesCountByUnionDataByTime--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询 按时间排序
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
	 * @author ZY on 2018/10/26
	 */
	public List<SnatchAndOrderTableAndUser> selectThreeTablesByUnionDataByTime(SnatchAndOrderTableAndUser unionData) {
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			snatchList = snatchDao.selectThreeTablesByUnionDataByTime(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--selectThreeTablesByUnionDataByTime--error:" + e.getMessage());
		}

		return snatchList;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(SnatchAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = snatchDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表模糊查询
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
	public List<SnatchAndOrderTableAndUser> selectThreeTablesBySelectData(SnatchAndOrderTableAndUser unionData) {
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			snatchList = snatchDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return snatchList;
	}

	/**
	 * 根据SnatchAndOrderTableAndUser实体联表查询 模糊查询 按时间排序
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
	 * @author ZY on 2018/10/26
	 */
	public List<SnatchAndOrderTableAndUser> selectThreeTablesBySelectDataByTime(SnatchAndOrderTableAndUser unionData) {
		List<SnatchAndOrderTableAndUser> snatchList = new ArrayList<SnatchAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			snatchList = snatchDao.selectThreeTablesBySelectDataByTime(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--selectThreeTablesBySelectDataByTime--error:" + e.getMessage());
		}

		return snatchList;
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
				deleteNum = deleteNum + snatchDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--deleteByIdList--error:" + e.getMessage());
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
				Snatch snatch = new Snatch();
				snatch.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + snatchDao.deleteBySnatch(session, snatch);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("SnatchService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
