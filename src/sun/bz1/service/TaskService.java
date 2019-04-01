package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.TaskDao;
import sun.bz1.entity.Task;
import sun.bz1.entity.TaskAndOrderTableAndBuildingType;
import util.MyBatisUtil;

/**
 * 维修任务表(接收维修时在该表中添加数据，维修结束时修改任务状态)
 * 
 * Service
 * 
 * @author WJF on 2018/09/08
 */

@Service
public class TaskService {

	@Autowired
	private TaskDao taskDao;

	private Logger logger = Logger.getLogger(TaskService.class);
	
	/**
   	 * 根据Task实体添加
   	 * 
   	 * @param task
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/08
   	 */
	public int insertByTask(Task task) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = taskDao.insertByTask(session, task);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("TaskService--insertByTask--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Task实体更新
	 * 
	 * @param task
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int updateByTask(Task task) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = taskDao.updateByTask(session, task);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("TaskService--updateByTask--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据TaskAndOrderTableAndBuildingType实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(TaskAndOrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = taskDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("TaskService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据TaskAndOrderTableAndBuildingType实体联表查询
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
	public List<TaskAndOrderTableAndBuildingType> selectThreeTablesByUnionData(TaskAndOrderTableAndBuildingType unionData) {
		List<TaskAndOrderTableAndBuildingType> taskList = new ArrayList<TaskAndOrderTableAndBuildingType>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			taskList = taskDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("TaskService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return taskList;
	}

	/**
	 * 根据TaskAndOrderTableAndBuildingType实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(TaskAndOrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = taskDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("TaskService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据TaskAndOrderTableAndBuildingType实体联表模糊查询
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
	public List<TaskAndOrderTableAndBuildingType> selectThreeTablesBySelectData(TaskAndOrderTableAndBuildingType unionData) {
		List<TaskAndOrderTableAndBuildingType> taskList = new ArrayList<TaskAndOrderTableAndBuildingType>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			taskList = taskDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("TaskService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return taskList;
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
				deleteNum = deleteNum + taskDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("TaskService--deleteByIdList--error:" + e.getMessage());
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
				Task task = new Task();
				task.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + taskDao.deleteByTask(session, task);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("TaskService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
