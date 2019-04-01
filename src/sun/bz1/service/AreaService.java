package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.AreaDao;
import sun.bz1.entity.Area;
import util.MyBatisUtil;

/**
 * 接单区域表
 * 
 * Service
 * 
 * @author WJF on 2018/09/04
 */

@Service
public class AreaService {

	@Autowired
	private AreaDao areaDao;

	private Logger logger = Logger.getLogger(AreaService.class);
	
	/**
   	 * 根据Area实体添加
   	 * 
   	 * @param area
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/04
   	 */
	public int insertByArea(Area area) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = areaDao.insertByArea(session, area);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--insertByArea--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体更新
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateByArea(Area area) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = areaDao.updateByArea(session, area);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--updateByArea--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByArea(Area area) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = areaDao.selectCountByArea(session, area);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--selectCountByArea--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Area> selectByArea(Area area) {
		List<Area> areaList = new ArrayList<Area>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			areaList = areaDao.selectByArea(session, area);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--selectByArea--error:" + e.getMessage());
		}

		return areaList;
	}

	/**
	 * 根据Area实体模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectData(Area area) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = areaDao.selectCountBySelectData(session, area);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Area> selectBySelectData(Area area) {
		List<Area> areaList = new ArrayList<Area>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			areaList = areaDao.selectBySelectData(session, area);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--selectBySelectData--error:" + e.getMessage());
		}

		return areaList;
	}

	/**
	 * 根据Area实体模糊查询
	 *
	 * 查询数量
	 *
	 * @param area
	 * @return
	 *
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectDataReport(Area area) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = areaDao.selectCountBySelectDataReport(session, area);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--selectCountBySelectDataReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Area实体模糊查询 (区域报表)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param area
	 * @return
	 *
	 * @author ZY on 2018/12/03
	 */
	public List<Area> selectBySelectDataReportCount(Area area) {
		List<Area> areaList = new ArrayList<Area>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			areaList = areaDao.selectBySelectDataReportCount(session, area);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--selectBySelectDataReportCount--error:" + e.getMessage());
		}

		return areaList;
	}

	/**
	 * 根据Area实体模糊查询 (区域报表)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param area
	 * @return
	 *
	 * @author ZY on 2018/12/03
	 */
	public List<Area> selectBySelectDataReport(Area area) {
		List<Area> areaList = new ArrayList<Area>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			areaList = areaDao.selectBySelectDataReport(session, area);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--selectBySelectDataReport--error:" + e.getMessage());
		}

		return areaList;
	}


	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + areaDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("AreaService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
