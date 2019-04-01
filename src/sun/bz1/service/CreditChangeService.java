package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.CreditChangeDao;
import sun.bz1.entity.CreditChange;
import util.MyBatisUtil;

/**
 * 信用值_变化表
 * 
 * Service
 * 
 * @author WJF on 2018/09/05
 */

@Service
public class CreditChangeService {

	@Autowired
	private CreditChangeDao creditChangeDao;

	private Logger logger = Logger.getLogger(CreditChangeService.class);
	
	/**
   	 * 根据CreditChange实体添加
   	 * 
   	 * @param creditChange
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/05
   	 */
	public int insertByCreditChange(CreditChange creditChange) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = creditChangeDao.insertByCreditChange(session, creditChange);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditChangeService--insertByCreditChange--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditChange实体更新
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByCreditChange(CreditChange creditChange) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = creditChangeDao.updateByCreditChange(session, creditChange);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditChangeService--updateByCreditChange--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditChange实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectCountByCreditChange(CreditChange creditChange) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = creditChangeDao.selectCountByCreditChange(session, creditChange);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditChangeService--selectCountByCreditChange--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditChange实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public List<CreditChange> selectByCreditChange(CreditChange creditChange) {
		List<CreditChange> creditChangeList = new ArrayList<CreditChange>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			creditChangeList = creditChangeDao.selectByCreditChange(session, creditChange);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditChangeService--selectByCreditChange--error:" + e.getMessage());
		}

		return creditChangeList;
	}

	/**
	 * 根据CreditChange实体模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectCountBySelectData(CreditChange creditChange) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = creditChangeDao.selectCountBySelectData(session, creditChange);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditChangeService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditChange实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public List<CreditChange> selectBySelectData(CreditChange creditChange) {
		List<CreditChange> creditChangeList = new ArrayList<CreditChange>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			creditChangeList = creditChangeDao.selectBySelectData(session, creditChange);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditChangeService--selectBySelectData--error:" + e.getMessage());
		}

		return creditChangeList;
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
				deleteNum = deleteNum + creditChangeDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditChangeService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
