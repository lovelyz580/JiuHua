package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.ProjectDao;
import sun.bz1.entity.OrderDetail;
import sun.bz1.entity.Project;
import util.MyBatisUtil;

/**
 * 项目表
 * 
 * Service
 * 
 * @author WJF on 2018/09/04
 */

@Service
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	private Logger logger = Logger.getLogger(ProjectService.class);
	
	/**
   	 * 根据Project实体添加
   	 * 
   	 * @param project
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/04
   	 */
	public int insertByProject(Project project) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = projectDao.insertByProject(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--insertByProject--error:" + e.getMessage());
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
	public int updateByProject(Project project) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = projectDao.updateByProject(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--updateByProject--error:" + e.getMessage());
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
	public int selectCountByProject(Project project) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = projectDao.selectCountByProject(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--selectCountByProject--error:" + e.getMessage());
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
	public List<Project> selectByProject(Project project) {
		List<Project> projectList = new ArrayList<Project>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			projectList = projectDao.selectByProject(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--selectByProject--error:" + e.getMessage());
		}

		return projectList;
	}

	/**
	 * 根据Project实体模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectData(Project project) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = projectDao.selectCountBySelectData(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--selectCountBySelectData--error:" + e.getMessage());
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
	public List<Project> selectBySelectData(Project project) {
		List<Project> projectList = new ArrayList<Project>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			projectList = projectDao.selectBySelectData(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--selectBySelectData--error:" + e.getMessage());
		}

		return projectList;
	}

	/**
	 * 根据Project实体模糊查询(报表)
	 *
	 * 查询数量
	 *
	 * @param project
	 * @return
	 *
	 * @author ZY on 2018/12/25
	 */
	public int selectCountBySelectDataReport(Project project) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = projectDao.selectCountBySelectDataReport(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--selectCountBySelectDataReport--error:" + e.getMessage());
		}

		return num;
	}
	/**
	 * 根据Project实体查询  生成报表
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
	public List<Project> selectBySelectDataReport(Project project) {
		List<Project> projectList = new ArrayList<Project>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			projectList = projectDao.selectBySelectDataReport(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--selectBySelectDataReport--error:" + e.getMessage());
		}

		return projectList;
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
	public int selectProjectDetailCountByProjectReport(Project project) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = projectDao.selectProjectDetailCountByProjectReport(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--selectProjectDetailCountByProjectReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Project实体模糊查询(查询平均采购（维修价格）的详情数据)
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
	public List<Project> selectProjectDetailByProjectReport(Project project) {
		List<Project> projectList = new ArrayList<Project>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			projectList = projectDao.selectProjectDetailByProjectReport(session, project);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--selectProjectDetailByProjectReport--error:" + e.getMessage());
		}

		return projectList;
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
				deleteNum = deleteNum + projectDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ProjectService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
