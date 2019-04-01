package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.UserCustomerIncomeMoneyDao;
import sun.bz1.entity.UserCustomerIncomeMoney;
import sun.bz1.entity.UserCustomerIncomeMoneyAndOrderTableAndUser;
import util.MyBatisUtil;

/**
 * 用户_客户_收入_金额表
 * 
 * Service
 * 
 * @author WJF on 2018/09/19
 */

@Service
public class UserCustomerIncomeMoneyService {

	@Autowired
	private UserCustomerIncomeMoneyDao incomeMoneyDao;

	private Logger logger = Logger.getLogger(UserCustomerIncomeMoneyService.class);
	
	/**
   	 * 根据UserCustomerIncomeMoney实体添加
   	 * 
   	 * @param incomeMoney
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/19
   	 */
	public int insertByUserCustomerIncomeMoney(UserCustomerIncomeMoney incomeMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = incomeMoneyDao.insertByUserCustomerIncomeMoney(session, incomeMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyService--insertByUserCustomerIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerIncomeMoney实体更新
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int updateByUserCustomerIncomeMoney(UserCustomerIncomeMoney incomeMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = incomeMoneyDao.updateByUserCustomerIncomeMoney(session, incomeMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyService--updateByUserCustomerIncomeMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerIncomeMoneyAndOrderTableAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountByUnionData(UserCustomerIncomeMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = incomeMoneyDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerIncomeMoneyAndOrderTableAndUser实体联表查询
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
	public List<UserCustomerIncomeMoneyAndOrderTableAndUser> selectThreeTablesByUnionData(UserCustomerIncomeMoneyAndOrderTableAndUser unionData) {
		List<UserCustomerIncomeMoneyAndOrderTableAndUser> UserCustomerIncomeMoneyList = new ArrayList<UserCustomerIncomeMoneyAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			UserCustomerIncomeMoneyList = incomeMoneyDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return UserCustomerIncomeMoneyList;
	}

	/**
	 * 根据UserCustomerIncomeMoneyAndOrderTableAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountBySelectData(UserCustomerIncomeMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = incomeMoneyDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerIncomeMoneyAndOrderTableAndUser实体联表模糊查询
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
	public List<UserCustomerIncomeMoneyAndOrderTableAndUser> selectThreeTablesBySelectData(UserCustomerIncomeMoneyAndOrderTableAndUser unionData) {
		List<UserCustomerIncomeMoneyAndOrderTableAndUser> UserCustomerIncomeMoneyList = new ArrayList<UserCustomerIncomeMoneyAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			UserCustomerIncomeMoneyList = incomeMoneyDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return UserCustomerIncomeMoneyList;
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
			logger.error("UserCustomerIncomeMoneyService--deleteByIdList--error:" + e.getMessage());
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
				UserCustomerIncomeMoney UserCustomerIncomeMoney = new UserCustomerIncomeMoney();
				UserCustomerIncomeMoney.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + incomeMoneyDao.deleteByUserCustomerIncomeMoney(session, UserCustomerIncomeMoney);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerIncomeMoneyService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
