package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.OrderDetail;
import sun.bz1.entity.Project;

/**
 * 项目表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/04
 */

@Repository
public class ProjectDao {
	
	private Logger logger = Logger.getLogger(ProjectDao.class);

	public int insert(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.insert("sun.bz1.dao.mapper.Project.insert", project);
		} catch (Exception e) {
			logger.error("ProjectDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.insert("sun.bz1.dao.mapper.Project.insertSelective", project);
		} catch (Exception e) {
			logger.error("ProjectDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.update("sun.bz1.dao.mapper.Project.updateByPrimaryKeySelective", project);
		} catch (Exception e) {
			logger.error("ProjectDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.update("sun.bz1.dao.mapper.Project.updateByPrimaryKey", project);
		} catch (Exception e) {
			logger.error("ProjectDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.selectOne("sun.bz1.dao.mapper.Project.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ProjectDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Project selectByPrimaryKey(SqlSession session, Integer id) {
		Project project = new Project();

		try {
			logger.info("ProjectDao");

			project = session.selectOne("sun.bz1.dao.mapper.Project.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ProjectDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return project;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.delete("sun.bz1.dao.mapper.Project.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("ProjectDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Project实体添加
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int insertByProject(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.insert("sun.bz1.dao.mapper.Project.insertByProject", project);
		} catch (Exception e) {
			logger.error("ProjectDao--insertByProject--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Project实体更新
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateByProject(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.update("sun.bz1.dao.mapper.Project.updateByProject", project);
		} catch (Exception e) {
			logger.error("ProjectDao--updateByProject--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Project实体查询
	 * 
	 * 查询数量
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByProject(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.selectOne("sun.bz1.dao.mapper.Project.selectCountByProject", project);
		} catch (Exception e) {
			logger.error("ProjectDao--selectCountByProject--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Project实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Project> selectByProject(SqlSession session, Project project) {
		List<Project> areaList = new ArrayList<Project>();

		try {
			logger.info("ProjectDao");

			areaList = session.selectList("sun.bz1.dao.mapper.Project.selectByProject", project);
		} catch (Exception e) {
			logger.error("ProjectDao--selectByProject--error:" + e.getMessage());
		}

		return areaList;
	}

	/**
	 * 根据Project实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param project
	 * @return
	 * 
	 * @author ZY on 2018/12/25
	 */
	public int selectCountBySelectData(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.selectOne("sun.bz1.dao.mapper.Project.selectCountBySelectData", project);
		} catch (Exception e) {
			logger.error("ProjectDao--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Project实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Project> selectBySelectData(SqlSession session, Project project) {
		List<Project> areaList = new ArrayList<Project>();

		try {
			logger.info("ProjectDao");

			areaList = session.selectList("sun.bz1.dao.mapper.Project.selectBySelectData", project);
		} catch (Exception e) {
			logger.error("ProjectDao--selectBySelectData--error:" + e.getMessage());
		}

		return areaList;
	}

	/**
	 * 根据Project实体模糊查询(报表)
	 *
	 * 查询数量
	 *
	 * @param project
	 * @return
	 *
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectDataReport(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.selectOne("sun.bz1.dao.mapper.Project.selectCountBySelectDataReport", project);
		} catch (Exception e) {
			logger.error("ProjectDao--selectCountBySelectDataReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Project实体模糊查询(报表)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param project
	 * @return
	 *
	 * @author ZY on 2018/12/06
	 */
	public List<Project> selectBySelectDataReport(SqlSession session, Project project) {
		List<Project> areaList = new ArrayList<Project>();

		try {
			logger.info("ProjectDao");

			areaList = session.selectList("sun.bz1.dao.mapper.Project.selectBySelectDataReport", project);
		} catch (Exception e) {
			logger.error("ProjectDao--selectBySelectDataReport--error:" + e.getMessage());
		}

		return areaList;
	}

	/**
	 * 根据Project实体模糊查询(查询平均成交价的详情数据)
	 *
	 * 查询数量
	 *
	 * @param project
	 * @return
	 *
	 * @author ZY on 2018/12/10
	 */
	public int selectProjectDetailCountByProjectReport(SqlSession session, Project project) {
		int num = 0;

		try {
			logger.info("ProjectDao");

			num = session.selectOne("sun.bz1.dao.mapper.Project.selectProjectDetailCountByProjectReport", project);
		} catch (Exception e) {
			logger.error("ProjectDao--selectProjectDetailCountByProjectReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Project实体模糊查询(查询平均成交价的详情数据)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param project
	 * @return
	 *
	 * @author ZY on 2018/12/10
	 */
	public List<Project> selectProjectDetailByProjectReport(SqlSession session, Project project) {
		List<Project> areaList = new ArrayList<Project>();

		try {
			logger.info("ProjectDao");

			areaList = session.selectList("sun.bz1.dao.mapper.Project.selectProjectDetailByProjectReport", project);
		} catch (Exception e) {
			logger.error("ProjectDao--selectProjectDetailByProjectReport--error:" + e.getMessage());
		}

		return areaList;
	}

	
}