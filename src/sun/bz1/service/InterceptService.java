package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.InterceptDao;
import sun.bz1.entity.Intercept;
import sun.bz1.entity.InterceptAndGoodsAndProject;
import util.MyBatisUtil;

/**
 * 拦标价表
 * 
 * Service
 * 
 * @author WJF on 2018/09/05
 */

@Service
public class InterceptService {
	
	@Autowired
	private InterceptDao interceptDao;

	private Logger logger = Logger.getLogger(InterceptService.class);
	
	/**
   	 * 根据Intercept实体添加
   	 * 
   	 * @param intercept
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/05
   	 */
	public int insertByIntercept(Intercept intercept) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = interceptDao.insertByIntercept(session, intercept);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptService--insertByIntercept--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Intercept实体更新
	 * 
	 * @param intercept
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByIntercept(Intercept intercept) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = interceptDao.updateByIntercept(session, intercept);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptService--updateByIntercept--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptAndGoodsAndProject实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountByUnionData(InterceptAndGoodsAndProject unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = interceptDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptAndGoodsAndProject实体联表查询
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
	 * @author WJF on 2018/09/05
	 */
	public List<InterceptAndGoodsAndProject> selectThreeTablesByUnionData(InterceptAndGoodsAndProject unionData) {
		List<InterceptAndGoodsAndProject> interceptList = new ArrayList<InterceptAndGoodsAndProject>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			interceptList = interceptDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return interceptList;
	}

	/**
	 * 根据InterceptAndGoodsAndProject实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectThreeTablesCountBySelectData(InterceptAndGoodsAndProject unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = interceptDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptAndGoodsAndProject实体联表模糊查询
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
	 * @author WJF on 2018/09/05
	 */
	public List<InterceptAndGoodsAndProject> selectThreeTablesBySelectData(InterceptAndGoodsAndProject unionData) {
		List<InterceptAndGoodsAndProject> interceptList = new ArrayList<InterceptAndGoodsAndProject>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			interceptList = interceptDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return interceptList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + interceptDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
