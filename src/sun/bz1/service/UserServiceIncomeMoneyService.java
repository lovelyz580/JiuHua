package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.UserServiceIncomeMoneyDao;
import sun.bz1.entity.UserServiceIncomeMoney;
import sun.bz1.entity.UserServiceIncomeMoneyAndOrderTableAndUser;
import util.MyBatisUtil;

/**
 * 用户_维修工、安装队_收入_金额表
 * 
 * Service
 * 
 * @author WJF on 2018/09/19
 */

@Service
public class UserServiceIncomeMoneyService {

	@Autowired
	private UserServiceIncomeMoneyDao incomeMoneyDao;

	private Logger logger = Logger.getLogger(UserServiceIncomeMoneyService.class);
	
	/**
   	 * 根据UserServiceIncomeMoney实体添加
   	 * 
   	 * @param incomeMoney
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/19
   	 */
	public int insertByUserServiceIncomeMoney(UserServiceIncomeMoney incomeMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = incomeMoneyDao.insertByUserServiceIncomeMoney(session, incomeMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyService--insertByUserServiceIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceIncomeMoney实体更新
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int updateByUserServiceIncomeMoney(UserServiceIncomeMoney incomeMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = incomeMoneyDao.updateByUserServiceIncomeMoney(session, incomeMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyService--updateByUserServiceIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountByUnionData(UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = incomeMoneyDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表查询
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
	 * @author WJF on 2018/09/19
	 */
	public List<UserServiceIncomeMoneyAndOrderTableAndUser> selectThreeTablesByUnionData(UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		List<UserServiceIncomeMoneyAndOrderTableAndUser> UserServiceIncomeMoneyList = new ArrayList<UserServiceIncomeMoneyAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			UserServiceIncomeMoneyList = incomeMoneyDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return UserServiceIncomeMoneyList;
	}

	/**
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountBySelectData(UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = incomeMoneyDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表模糊查询
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
	 * @author WJF on 2018/09/19
	 */
	public List<UserServiceIncomeMoneyAndOrderTableAndUser> selectThreeTablesBySelectData(UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		List<UserServiceIncomeMoneyAndOrderTableAndUser> UserServiceIncomeMoneyList = new ArrayList<UserServiceIncomeMoneyAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			UserServiceIncomeMoneyList = incomeMoneyDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return UserServiceIncomeMoneyList;
	}
	
	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + incomeMoneyDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				UserServiceIncomeMoney UserServiceIncomeMoney = new UserServiceIncomeMoney();
				UserServiceIncomeMoney.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + incomeMoneyDao.deleteByUserServiceIncomeMoney(session, UserServiceIncomeMoney);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceIncomeMoneyService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
