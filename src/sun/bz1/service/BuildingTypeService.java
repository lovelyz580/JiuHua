package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.BuildingTypeDao;
import sun.bz1.entity.BuildingType;
import util.MyBatisUtil;

/**
 * 建筑类型表
 * 
 * Service
 * 
 * @author WJF on 2018/09/04
 */

@Service
public class BuildingTypeService {

	@Autowired
	private BuildingTypeDao buildingTypeDao;

	private Logger logger = Logger.getLogger(BuildingTypeService.class);

	/**
	 * 根据BuildingType实体添加
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int insertByBuildingType(BuildingType type) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = buildingTypeDao.insertByBuildingType(session, type);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BuildingTypeService--insertByBuildingType--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BuildingType实体更新
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateByBuildingType(BuildingType type) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = buildingTypeDao.updateByBuildingType(session, type);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BuildingTypeService--updateByBuildingType--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BuildingType实体查询
	 * 
	 * 查询数量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByBuildingType(BuildingType type) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = buildingTypeDao.selectCountByBuildingType(session, type);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BuildingTypeService--selectCountByBuildingType--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BuildingType实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<BuildingType> selectByBuildingType(BuildingType type) {
		List<BuildingType> typeList = new ArrayList<BuildingType>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			typeList = buildingTypeDao.selectByBuildingType(session, type);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BuildingTypeService--selectByBuildingType--error:" + e.getMessage());
		}

		return typeList;
	}

	/**
	 * 根据BuildingType实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectData(BuildingType type) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = buildingTypeDao.selectCountBySelectData(session, type);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BuildingTypeService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据BuildingType实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<BuildingType> selectBySelectData(BuildingType type) {
		List<BuildingType> typeList = new ArrayList<BuildingType>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			typeList = buildingTypeDao.selectBySelectData(session, type);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BuildingTypeService--selectBySelectData--error:" + e.getMessage());
		}

		return typeList;
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
				deleteNum = deleteNum + buildingTypeDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("BuildingTypeService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
