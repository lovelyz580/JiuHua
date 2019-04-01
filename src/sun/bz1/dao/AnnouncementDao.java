package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Announcement;
import sun.bz1.entity.AnnouncementAndOrderTableAndBuildingType;

/**
 * 维修公告表(发布公告时在该表中添加数据，公告结束时修改公告状态)
 * 
 * Dao
 * 
 * @author WJF on 2018/09/07
 */

@Repository
public class AnnouncementDao {

	private Logger logger = Logger.getLogger(AnnouncementDao.class);

	public int insert(SqlSession session, Announcement announcement) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.insert("sun.bz1.dao.mapper.Announcement.insert", announcement);
		} catch (Exception e) {
			logger.error("AnnouncementDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Announcement announcement) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.insert("sun.bz1.dao.mapper.Announcement.insertSelective", announcement);
		} catch (Exception e) {
			logger.error("AnnouncementDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Announcement announcement) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.update("sun.bz1.dao.mapper.Announcement.updateByPrimaryKeySelective", announcement);
		} catch (Exception e) {
			logger.error("AnnouncementDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Announcement announcement) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.update("sun.bz1.dao.mapper.Announcement.updateByPrimaryKey", announcement);
		} catch (Exception e) {
			logger.error("AnnouncementDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.selectOne("sun.bz1.dao.mapper.Announcement.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("AnnouncementDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Announcement selectByPrimaryKey(SqlSession session, Integer id) {
		Announcement announcement = new Announcement();

		try {
			logger.info("AnnouncementDao");

			announcement = session.selectOne("sun.bz1.dao.mapper.Announcement.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("AnnouncementDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return announcement;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.delete("sun.bz1.dao.mapper.Announcement.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("AnnouncementDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Announcement实体添加
	 * 
	 * @param announcement
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int insertByAnnouncement(SqlSession session, Announcement announcement) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.insert("sun.bz1.dao.mapper.Announcement.insertByAnnouncement", announcement);
		} catch (Exception e) {
			logger.error("AnnouncementDao--insertByAnnouncement--error:" + e.getMessage());
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
	public int updateByAnnouncement(SqlSession session, Announcement announcement) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.update("sun.bz1.dao.mapper.Announcement.updateByAnnouncement", announcement);
		} catch (Exception e) {
			logger.error("AnnouncementDao--updateByAnnouncement--error:" + e.getMessage());
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
	public int selectThreeTablesCountByUnionData(SqlSession session, AnnouncementAndOrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.selectOne("sun.bz1.dao.mapper.Announcement.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("AnnouncementDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
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
	public List<AnnouncementAndOrderTableAndBuildingType> selectThreeTablesByUnionData(SqlSession session, AnnouncementAndOrderTableAndBuildingType unionData) {
		List<AnnouncementAndOrderTableAndBuildingType> announcementList = new ArrayList<AnnouncementAndOrderTableAndBuildingType>();

		try {
			logger.info("AnnouncementDao");

			announcementList = session.selectList("sun.bz1.dao.mapper.Announcement.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("AnnouncementDao--selectThreeTablesByUnionData--error:" + e.getMessage());
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
	public int selectThreeTablesCountBySelectData(SqlSession session, AnnouncementAndOrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.selectOne("sun.bz1.dao.mapper.Announcement.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("AnnouncementDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
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
	public List<AnnouncementAndOrderTableAndBuildingType> selectThreeTablesBySelectData(SqlSession session, AnnouncementAndOrderTableAndBuildingType unionData) {
		List<AnnouncementAndOrderTableAndBuildingType> announcementList = new ArrayList<AnnouncementAndOrderTableAndBuildingType>();

		try {
			logger.info("AnnouncementDao");

			announcementList = session.selectList("sun.bz1.dao.mapper.Announcement.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("AnnouncementDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return announcementList;
	}
	
	/**
	 * 根据Announcement实体删除
	 * 
	 * @param announcement
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	public int deleteByAnnouncement(SqlSession session, Announcement announcement) {
		int num = 0;

		try {
			logger.info("AnnouncementDao");

			num = session.delete("sun.bz1.dao.mapper.Announcement.deleteByAnnouncement", announcement);
		} catch (Exception e) {
			logger.error("AnnouncementDao--deleteByAnnouncement--error:" + e.getMessage());
		}

		return num;
	}

}