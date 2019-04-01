package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.EvaluateCustomerSetupDao;
import sun.bz1.entity.EvaluateCustomerSetup;
import util.MyBatisUtil;

/**
 * 维修人员评价客户_评价项_设置表
 * 
 * Service
 * 
 * @author WJF on 2018/09/17
 */

@Service
public class EvaluateCustomerSetupService {

	@Autowired
	private EvaluateCustomerSetupDao setupDao;

	private Logger logger = Logger.getLogger(EvaluateCustomerSetupService.class);
	
	/**
   	 * 根据EvaluateCustomerSetup实体添加
   	 * 
   	 * @param setup
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/17
   	 */
	public int insertByEvaluateCustomerSetup(EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.insertByEvaluateCustomerSetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupService--insertByEvaluateCustomerSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int updateByEvaluateCustomerSetup(EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.updateByEvaluateCustomerSetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupService--updateByEvaluateCustomerSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerSetup实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int selectCountByEvaluateCustomerSetup(EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.selectCountByEvaluateCustomerSetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupService--selectCountByEvaluateCustomerSetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerSetup实体查询
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
	public List<EvaluateCustomerSetup> selectByEvaluateCustomerSetup(EvaluateCustomerSetup setup) {
		List<EvaluateCustomerSetup> evaluateList = new ArrayList<EvaluateCustomerSetup>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = setupDao.selectByEvaluateCustomerSetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupService--selectByEvaluateCustomerSetup--error:" + e.getMessage());
		}

		return evaluateList;
	}

	/**
	 * 根据EvaluateCustomerSetup实体模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	public int selectCountBySelectData(EvaluateCustomerSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.selectCountBySelectData(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据EvaluateCustomerSetup实体模糊查询
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
	public List<EvaluateCustomerSetup> selectBySelectData(EvaluateCustomerSetup setup) {
		List<EvaluateCustomerSetup> evaluateList = new ArrayList<EvaluateCustomerSetup>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			evaluateList = setupDao.selectBySelectData(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("EvaluateCustomerSetupService--selectBySelectData--error:" + e.getMessage());
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
			logger.error("EvaluateCustomerSetupService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
