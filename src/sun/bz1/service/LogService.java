package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.LogDao;
import sun.bz1.entity.Log;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志表
 * 
 * Service
 * 
 * @author ZY on 2018/11/28
 */

@Service
public class LogService {

	@Autowired
	private LogDao logDao;

	private Logger logger = Logger.getLogger(LogService.class);

	/**
	 * 根据Log实体添加
	 * 
	 * @param log
	 * @return
	 * 
	 * @author ZY on 2018/11/28
	 */
	public int insertByLog(Log log) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = logDao.insertByLog(session, log);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("LogService--insertByLog--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Log实体更新
	 * 
	 * @param log
	 * @return
	 * 
	 * @author ZY on 2018/11/28
	 */
	public int updateByLog(Log log) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = logDao.updateByLog(session, log);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("LogService--updateByLog--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Log实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param log
	 * @return
	 * 
	 * @author ZY on 2018/11/28
	 */
	public int selectCountByLog(Log log) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = logDao.selectCountByLog(session, log);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("LogService--selectCountByLog--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Log实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param log
	 * @return
	 * 
	 * @author ZY on 2018/11/28
	 */
	public List<Log> selectByLog(Log log) {
		List<Log> logList = new ArrayList<Log>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			logList = logDao.selectByLog(session, log);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("LogService--selectByLog--error:" + e.getMessage());
		}

		return logList;
	}

	/**
	 * 根据Log实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param log
	 * @return
	 * 
	 * @author ZY on 2018/11/28
	 */
	public int selectCountBySelectData(Log log) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = logDao.selectCountBySelectData(session, log);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("LogService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Log实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param log
	 * @return
	 * 
	 * @author ZY on 2018/11/28
	 */
	public List<Log> selectBySelectData(Log log) {
		List<Log> logList = new ArrayList<Log>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			logList = logDao.selectBySelectData(session, log);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("LogService--selectBySelectData--error:" + e.getMessage());
		}

		return logList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/11/28
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + logDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("LogService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
