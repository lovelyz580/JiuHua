package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.AnnouncementDao;
import sun.bz1.entity.Announcement;
import sun.bz1.entity.AnnouncementAndOrderTableAndBuildingType;
import util.MyBatisUtil;

/**
 * 维修公告表(发布公告时在该表中添加数据，公告结束时修改公告状态)
 * 
 * Service
 * 
 * @author WJF on 2018/09/07
 */

@Service
public class AnnouncementService {

	@Autowired
	private AnnouncementDao announcementDao;

	private Logger logger = Logger.getLogger(AnnouncementService.class);
	
	/**
   	 * 根据Announcement实体添加
   	 * 
   	 * @param announcement
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/07
   	 */
	public int insertByAnnouncement(Announcement announcement) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = announcementDao.insertByAnnouncement(session, announcement);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AnnouncementService--insertByAnnouncement--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Announcement实体更新
	 * 
	 * @param announcement
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int updateByAnnouncement(Announcement announcement) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = announcementDao.updateByAnnouncement(session, announcement);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AnnouncementService--updateByAnnouncement--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据AnnouncementAndOrderTableAndBuildingType实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int selectThreeTablesCountByUnionData(AnnouncementAndOrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = announcementDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AnnouncementService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据AnnouncementAndOrderTableAndBuildingType实体联表查询
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
	 * @author WJF on 2018/09/07
	 */
	public List<AnnouncementAndOrderTableAndBuildingType> selectThreeTablesByUnionData(AnnouncementAndOrderTableAndBuildingType unionData) {
		List<AnnouncementAndOrderTableAndBuildingType> announcementList = new ArrayList<AnnouncementAndOrderTableAndBuildingType>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			announcementList = announcementDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AnnouncementService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return announcementList;
	}

	/**
	 * 根据AnnouncementAndOrderTableAndBuildingType实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int selectThreeTablesCountBySelectData(AnnouncementAndOrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = announcementDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AnnouncementService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据AnnouncementAndOrderTableAndBuildingType实体联表模糊查询
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
	 * @author WJF on 2018/09/07
	 */
	public List<AnnouncementAndOrderTableAndBuildingType> selectThreeTablesBySelectData(AnnouncementAndOrderTableAndBuildingType unionData) {
		List<AnnouncementAndOrderTableAndBuildingType> announcementList = new ArrayList<AnnouncementAndOrderTableAndBuildingType>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			announcementList = announcementDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AnnouncementService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return announcementList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + announcementDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AnnouncementService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				Announcement announcement = new Announcement();
				announcement.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + announcementDao.deleteByAnnouncement(session, announcement);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AnnouncementService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
