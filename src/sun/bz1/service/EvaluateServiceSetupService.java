package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.EvaluateServiceSetupDao;
import sun.bz1.entity.EvaluateServiceSetup;
import util.MyBatisUtil;

/**
 * 客户评价维修人员_评价项_设置表 
 * 
 * Service
 * 
 * @author WJF on 2018/09/17
 */

@Service
public class EvaluateServiceSetupService {

	@Autowired
	private EvaluateServiceSetupDao setupDao;

	private Logger logger = Logger.getLogger(EvaluateServiceSetupService.class);
	
	/**
   	 * 根据EvaluateServiceSetup实体添加
   	 * 
   	 * @param setup
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/17
   	 */
	public int insertByEvaluateServiceSetup(EvaluateServiceSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.insertByEvaluateServiceSetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupService--insertByEvaluateServiceSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int updateByEvaluateServiceSetup(EvaluateServiceSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.updateByEvaluateServiceSetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupService--updateByEvaluateServiceSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceSetup实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int selectCountByEvaluateServiceSetup(EvaluateServiceSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.selectCountByEvaluateServiceSetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupService--selectCountByEvaluateServiceSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceSetup实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public List<EvaluateServiceSetup> selectByEvaluateServiceSetup(EvaluateServiceSetup setup) {
		List<EvaluateServiceSetup> evaluateList = new ArrayList<EvaluateServiceSetup>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = setupDao.selectByEvaluateServiceSetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupService--selectByEvaluateServiceSetup--error:" + e.getMessage());
		}

		return evaluateList;
	}

	/**
	 * 根据EvaluateServiceSetup实体模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int selectCountBySelectData(EvaluateServiceSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.selectCountBySelectData(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateServiceSetup实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public List<EvaluateServiceSetup> selectBySelectData(EvaluateServiceSetup setup) {
		List<EvaluateServiceSetup> evaluateList = new ArrayList<EvaluateServiceSetup>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = setupDao.selectBySelectData(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupService--selectBySelectData--error:" + e.getMessage());
		}

		return evaluateList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + setupDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateServiceSetupService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
