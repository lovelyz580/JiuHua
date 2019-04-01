package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.UserServiceExpenditureMoneyDao;
import sun.bz1.entity.UserServiceExpenditureMoney;
import sun.bz1.entity.UserServiceExpenditureMoneyAndOrderTableAndUser;
import util.MyBatisUtil;

/**
 * 用户_维修工、安装队_支出_金额表
 * 
 * Service
 * 
 * @author WJF on 2018/09/19
 */

@Service
public class UserServiceExpenditureMoneyService {

	@Autowired
	private UserServiceExpenditureMoneyDao expenditureMoneyDao;

	private Logger logger = Logger.getLogger(UserServiceExpenditureMoneyService.class);
	
	/**
   	 * 根据UserServiceExpenditureMoney实体添加
   	 * 
   	 * @param expenditureMoney
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/19
   	 */
	public int insertByUserServiceExpenditureMoney(UserServiceExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = expenditureMoneyDao.insertByUserServiceExpenditureMoney(session, expenditureMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyService--insertByUserServiceExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceExpenditureMoney实体更新
	 * 
	 * @param expenditureMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int updateByUserServiceExpenditureMoney(UserServiceExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = expenditureMoneyDao.updateByUserServiceExpenditureMoney(session, expenditureMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyService--updateByUserServiceExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceExpenditureMoneyAndOrderTableAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountByUnionData(UserServiceExpenditureMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = expenditureMoneyDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceExpenditureMoneyAndOrderTableAndUser实体联表查询
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
	public List<UserServiceExpenditureMoneyAndOrderTableAndUser> selectThreeTablesByUnionData(UserServiceExpenditureMoneyAndOrderTableAndUser unionData) {
		List<UserServiceExpenditureMoneyAndOrderTableAndUser> UserServiceExpenditureMoneyList = new ArrayList<UserServiceExpenditureMoneyAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			UserServiceExpenditureMoneyList = expenditureMoneyDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return UserServiceExpenditureMoneyList;
	}

	/**
	 * 根据UserServiceExpenditureMoneyAndOrderTableAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountBySelectData(UserServiceExpenditureMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = expenditureMoneyDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserServiceExpenditureMoneyAndOrderTableAndUser实体联表模糊查询
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
	public List<UserServiceExpenditureMoneyAndOrderTableAndUser> selectThreeTablesBySelectData(UserServiceExpenditureMoneyAndOrderTableAndUser unionData) {
		List<UserServiceExpenditureMoneyAndOrderTableAndUser> UserServiceExpenditureMoneyList = new ArrayList<UserServiceExpenditureMoneyAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			UserServiceExpenditureMoneyList = expenditureMoneyDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return UserServiceExpenditureMoneyList;
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
				deleteNum = deleteNum + expenditureMoneyDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyService--deleteByIdList--error:" + e.getMessage());
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
				UserServiceExpenditureMoney UserServiceExpenditureMoney = new UserServiceExpenditureMoney();
				UserServiceExpenditureMoney.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + expenditureMoneyDao.deleteByUserServiceExpenditureMoney(session, UserServiceExpenditureMoney);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceExpenditureMoneyService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
