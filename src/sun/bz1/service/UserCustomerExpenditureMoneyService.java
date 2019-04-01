package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.UserCustomerExpenditureMoneyDao;
import sun.bz1.entity.UserCustomerExpenditureMoney;
import sun.bz1.entity.UserCustomerExpenditureMoneyAndOrderTableAndUser;
import util.MyBatisUtil;

/**
 * 用户_客户_支出_金额表
 * 
 * Service
 * 
 * @author WJF on 2018/09/19
 */

@Service
public class UserCustomerExpenditureMoneyService {

	@Autowired
	private UserCustomerExpenditureMoneyDao expenditureMoneyDao;

	private Logger logger = Logger.getLogger(UserCustomerExpenditureMoneyService.class);
	
	/**
   	 * 根据UserCustomerExpenditureMoney实体添加
   	 * 
   	 * @param expenditureMoney
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/19
   	 */
	public int insertByUserCustomerExpenditureMoney(UserCustomerExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = expenditureMoneyDao.insertByUserCustomerExpenditureMoney(session, expenditureMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyService--insertByUserCustomerExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerExpenditureMoney实体更新
	 * 
	 * @param expenditureMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int updateByUserCustomerExpenditureMoney(UserCustomerExpenditureMoney expenditureMoney) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = expenditureMoneyDao.updateByUserCustomerExpenditureMoney(session, expenditureMoney);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyService--updateByUserCustomerExpenditureMoney--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerExpenditureMoneyAndOrderTableAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountByUnionData(UserCustomerExpenditureMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = expenditureMoneyDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerExpenditureMoneyAndOrderTableAndUser实体联表查询
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
	public List<UserCustomerExpenditureMoneyAndOrderTableAndUser> selectThreeTablesByUnionData(UserCustomerExpenditureMoneyAndOrderTableAndUser unionData) {
		List<UserCustomerExpenditureMoneyAndOrderTableAndUser> UserCustomerExpenditureMoneyList = new ArrayList<UserCustomerExpenditureMoneyAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			UserCustomerExpenditureMoneyList = expenditureMoneyDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return UserCustomerExpenditureMoneyList;
	}

	/**
	 * 根据UserCustomerExpenditureMoneyAndOrderTableAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	public int selectThreeTablesCountBySelectData(UserCustomerExpenditureMoneyAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = expenditureMoneyDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomerExpenditureMoneyAndOrderTableAndUser实体联表模糊查询
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
	public List<UserCustomerExpenditureMoneyAndOrderTableAndUser> selectThreeTablesBySelectData(UserCustomerExpenditureMoneyAndOrderTableAndUser unionData) {
		List<UserCustomerExpenditureMoneyAndOrderTableAndUser> UserCustomerExpenditureMoneyList = new ArrayList<UserCustomerExpenditureMoneyAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			UserCustomerExpenditureMoneyList = expenditureMoneyDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return UserCustomerExpenditureMoneyList;
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
			logger.error("UserCustomerExpenditureMoneyService--deleteByIdList--error:" + e.getMessage());
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
				UserCustomerExpenditureMoney UserCustomerExpenditureMoney = new UserCustomerExpenditureMoney();
				UserCustomerExpenditureMoney.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + expenditureMoneyDao.deleteByUserCustomerExpenditureMoney(session, UserCustomerExpenditureMoney);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerExpenditureMoneyService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
