package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Task;
import sun.bz1.entity.TaskAndOrderTableAndBuildingType;

/**
 * 维修任务表(接收维修时在该表中添加数据，维修结束时修改任务状态)
 * 
 * Dao
 * 
 * @author WJF on 2018/09/08
 */

@Repository
public class TaskDao {
	
	private Logger logger = Logger.getLogger(TaskDao.class);

	public int insert(SqlSession session, Task task) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.insert("sun.bz1.dao.mapper.Task.insert", task);
		} catch (Exception e) {
			logger.error("TaskDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Task task) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.insert("sun.bz1.dao.mapper.Task.insertSelective", task);
		} catch (Exception e) {
			logger.error("TaskDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Task task) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.update("sun.bz1.dao.mapper.Task.updateByPrimaryKeySelective", task);
		} catch (Exception e) {
			logger.error("TaskDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Task task) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.update("sun.bz1.dao.mapper.Task.updateByPrimaryKey", task);
		} catch (Exception e) {
			logger.error("TaskDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.selectOne("sun.bz1.dao.mapper.Task.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("TaskDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Task selectByPrimaryKey(SqlSession session, Integer id) {
		Task task = new Task();

		try {
			logger.info("TaskDao");

			task = session.selectOne("sun.bz1.dao.mapper.Task.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("TaskDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return task;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.delete("sun.bz1.dao.mapper.Task.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("TaskDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Task实体添加
	 * 
	 * @param task
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int insertByTask(SqlSession session, Task task) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.insert("sun.bz1.dao.mapper.Task.insertByTask", task);
		} catch (Exception e) {
			logger.error("TaskDao--insertByTask--error:" + e.getMessage());
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
	public int updateByTask(SqlSession session, Task task) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.update("sun.bz1.dao.mapper.Task.updateByTask", task);
		} catch (Exception e) {
			logger.error("TaskDao--updateByTask--error:" + e.getMessage());
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
	public int selectThreeTablesCountByUnionData(SqlSession session, TaskAndOrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.selectOne("sun.bz1.dao.mapper.Task.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("TaskDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
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
	public List<TaskAndOrderTableAndBuildingType> selectThreeTablesByUnionData(SqlSession session, TaskAndOrderTableAndBuildingType unionData) {
		List<TaskAndOrderTableAndBuildingType> taskList = new ArrayList<TaskAndOrderTableAndBuildingType>();

		try {
			logger.info("TaskDao");

			taskList = session.selectList("sun.bz1.dao.mapper.Task.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("TaskDao--selectThreeTablesByUnionData--error:" + e.getMessage());
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
	public int selectThreeTablesCountBySelectData(SqlSession session, TaskAndOrderTableAndBuildingType unionData) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.selectOne("sun.bz1.dao.mapper.Task.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("TaskDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
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
	public List<TaskAndOrderTableAndBuildingType> selectThreeTablesBySelectData(SqlSession session, TaskAndOrderTableAndBuildingType unionData) {
		List<TaskAndOrderTableAndBuildingType> taskList = new ArrayList<TaskAndOrderTableAndBuildingType>();

		try {
			logger.info("TaskDao");

			taskList = session.selectList("sun.bz1.dao.mapper.Task.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("TaskDao--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return taskList;
	}
	
	/**
	 * 根据Task实体删除
	 * 
	 * @param task
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int deleteByTask(SqlSession session, Task task) {
		int num = 0;

		try {
			logger.info("TaskDao");

			num = session.delete("sun.bz1.dao.mapper.Task.deleteByTask", task);
		} catch (Exception e) {
			logger.error("TaskDao--deleteByTask--error:" + e.getMessage());
		}

		return num;
	}
	
}