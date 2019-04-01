package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.UserExtractMoneyDao;
import sun.bz1.entity.UserExtractMoney;
import sun.bz1.entity.UserExtractMoneyAndUser;
import util.MyBatisUtil;

/**
 * 用户_提现表
 * 
 * Service
 * 
 * @author WJF on 2018/09/10
 */

@Service
public class UserExtractMoneyService {

	@Autowired
	private UserExtractMoneyDao userExtractMoneyDao;

	private Logger logger = Logger.getLogger(UserExtractMoneyService.class);
	
	/**
   	 * 根据UserExtractMoney实体添加
   	 * 
   	 * @param userExtractMoney
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/10
   	 */
	public int insertByUserExtractMoney(UserExtractMoney userExtractMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userExtractMoneyDao.insertByUserExtractMoney(session, userExtractMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserExtractMoneyService--insertByUserExtractMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserExtractMoney实体更新
	 * 
	 * @param userExtractMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int updateByUserExtractMoney(UserExtractMoney userExtractMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userExtractMoneyDao.updateByUserExtractMoney(session, userExtractMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserExtractMoneyService--updateByUserExtractMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserExtractMoneyAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int selectTwoTablesCountByUnionData(UserExtractMoneyAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userExtractMoneyDao.selectTwoTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserExtractMoneyService--selectTwoTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserExtractMoneyAndUser实体联表查询
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
	 * @author WJF on 2018/09/10
	 */
	public List<UserExtractMoneyAndUser> selectTwoTablesByUnionData(UserExtractMoneyAndUser unionData) {
		List<UserExtractMoneyAndUser> applyCheckList = new ArrayList<UserExtractMoneyAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			applyCheckList = userExtractMoneyDao.selectTwoTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserExtractMoneyService--selectTwoTablesByUnionData--error:" + e.getMessage());
		}

		return applyCheckList;
	}

	/**
	 * 根据UserExtractMoneyAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int selectTwoTablesCountBySelectData(UserExtractMoneyAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userExtractMoneyDao.selectTwoTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserExtractMoneyService--selectTwoTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserExtractMoneyAndUser实体联表模糊查询
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
	 * @author WJF on 2018/09/10
	 */
	public List<UserExtractMoneyAndUser> selectTwoTablesBySelectData(UserExtractMoneyAndUser unionData) {
		List<UserExtractMoneyAndUser> applyCheckList = new ArrayList<UserExtractMoneyAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			applyCheckList = userExtractMoneyDao.selectTwoTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserExtractMoneyService--selectTwoTablesBySelectData--error:" + e.getMessage());
		}

		return applyCheckList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + userExtractMoneyDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserExtractMoneyService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
