package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.DispatchModeDao;
import sun.bz1.dao.NewsDao;
import sun.bz1.entity.DispatchMode;
import sun.bz1.entity.News;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 派单方式表
 * 
 * Service
 * 
 * @author ZY on 2018/09/19
 */

@Service
public class DispatchModeService {

	@Autowired
	private DispatchModeDao dispatchModeDao;

	private Logger logger = Logger.getLogger(DispatchModeService.class);

	/**
	 * 根据DispatchMode实体添加
	 * 
	 * @param dispatchMode
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int insertByDispatchMode(DispatchMode dispatchMode) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = dispatchModeDao.insertByDispatchMode(session, dispatchMode);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DispatchModeService--insertByDispatchMode--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DispatchMode实体更新
	 * 
	 * @param dispatchMode
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int updateByDispatchMode(DispatchMode dispatchMode) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = dispatchModeDao.updateByDispatchMode(session, dispatchMode);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DispatchModeService--updateByDispatchMode--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DispatchMode实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param dispatchMode
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int selectCountByDispatchMode(DispatchMode dispatchMode) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = dispatchModeDao.selectCountByDispatchMode(session, dispatchMode);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DispatchModeService--selectCountByDispatchMode--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DispatchMode实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param dispatchMode
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public List<DispatchMode> selectByDispatchMode(DispatchMode dispatchMode) {
		List<DispatchMode> dispatchModeList = new ArrayList<DispatchMode>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			dispatchModeList = dispatchModeDao.selectByDispatchMode(session, dispatchMode);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DispatchModeService--selectByDispatchMode--error:" + e.getMessage());
		}

		return dispatchModeList;
	}

	/**
	 * 根据DispatchMode实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param dispatchMode
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int selectCountBySelectData(DispatchMode dispatchMode) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = dispatchModeDao.selectCountBySelectData(session, dispatchMode);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DispatchModeService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DispatchMode实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param dispatchMode
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public List<DispatchMode> selectBySelectData(DispatchMode dispatchMode) {
		List<DispatchMode> dispatchModeList = new ArrayList<DispatchMode>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			dispatchModeList = dispatchModeDao.selectBySelectData(session, dispatchMode);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DispatchModeService--selectBySelectData--error:" + e.getMessage());
		}

		return dispatchModeList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + dispatchModeDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DispatchModeService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
