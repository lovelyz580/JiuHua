package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.OrderTableDao;
import sun.bz1.dao.UserCustomerIncomeMoneyDao;
import sun.bz1.dao.UserPaymentDao;
import sun.bz1.entity.OrderTable;
import sun.bz1.entity.UserCustomerIncomeMoney;
import sun.bz1.entity.UserPayment;
import sun.bz1.entity.UserPaymentAndOrderTableAndUser;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户付款表
 * 
 * Service
 * 
 * @author ZY on 2018/10/20
 */

@Service
public class UserPaymentService {

	@Autowired
	private UserPaymentDao userPaymentDao;

	@Autowired
	private OrderTableDao orderTableDao;

	@Autowired
	private UserCustomerIncomeMoneyDao userCustomerIncomeMoneyDao;

	private Logger logger = Logger.getLogger(UserPaymentService.class);

	/**
	 * 根据UserPayment实体添加
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	public int insertByUserPayment(UserPayment userPayment) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userPaymentDao.insertByUserPayment(session, userPayment);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--insertByUserPayment--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserPayment实体更新
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	public int updateByUserPayment(UserPayment userPayment) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userPaymentDao.updateByUserPayment(session, userPayment);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--updateByUserPayment--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserPayment实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	public int selectCountByUserPayment(UserPayment userPayment) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userPaymentDao.selectCountByUserPayment(session, userPayment);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--selectCountByUserPayment--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserPayment实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	public List<UserPayment> selectByUserPayment(UserPayment userPayment) {
		List<UserPayment> userPaymentList = new ArrayList<UserPayment>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userPaymentList = userPaymentDao.selectByUserPayment(session, userPayment);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--selectByUserPayment--error:" + e.getMessage());
		}

		return userPaymentList;
	}

	/**
	 * 根据UserPayment实体联表查询 创建时间倒序
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param userPayment
	 * @return
	 *
	 * @author ZY on 2018/10/20
	 */
	public List<UserPayment> selectByUserPaymentTimeDesc(UserPayment userPayment) {
		List<UserPayment> userPaymentList = new ArrayList<UserPayment>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userPaymentList = userPaymentDao.selectByUserPaymentTimeDesc(session, userPayment);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--selectByUserPaymentTimeDesc--error:" + e.getMessage());
		}

		return userPaymentList;
	}

	/**
	 * 根据UserPayment实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	public int selectCountBySelectData(UserPayment userPayment) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userPaymentDao.selectCountBySelectData(session, userPayment);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserPayment实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param userPayment
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	public List<UserPayment> selectBySelectData(UserPayment userPayment) {
		List<UserPayment> userPaymentList = new ArrayList<UserPayment>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userPaymentList = userPaymentDao.selectBySelectData(session, userPayment);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--selectBySelectData--error:" + e.getMessage());
		}

		return userPaymentList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/10/20
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + userPaymentDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

	/**
	 * 根据UserPayment实体添加
	 *
	 * 更新订单状态
	 *
	 * 添加用户收入金额明细表
	 *
	 * @param userPayment
	 * @return
	 *
	 * @author ZY on 2018/10/20
	 */
	public int insertByUserPaymentAndUpdateOrderAndUpdateMoney(UserPayment userPayment, OrderTable orderTable, UserCustomerIncomeMoney customerIncomeMoney) {
		int num = 0;
		int orderTableNum = 0;
		int customerIncomeMoneyNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			//添加用户付款表
			num = userPaymentDao.insertByUserPayment(session, userPayment);

			//更改订单状态用户已付款
			orderTableNum = orderTableDao.updateByOrderTable(session, orderTable);

			//添加金额明细表
			customerIncomeMoneyNum = userCustomerIncomeMoneyDao.insertByUserCustomerIncomeMoney(session, customerIncomeMoney);

			if (num == 0 || orderTableNum == 0 || customerIncomeMoneyNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--insertByUserPayment--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserPaymentAndOrderTableAndUser实体联表查询
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/10/22
	 */
	public int selectThreeTablesCountByUnionData(UserPaymentAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userPaymentDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserPaymentAndOrderTableAndUser实体联表查询
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
	 * @author ZY on 2018/10/22
	 */
	public List<UserPaymentAndOrderTableAndUser> selectThreeTablesByUnionData(UserPaymentAndOrderTableAndUser unionData) {
		List<UserPaymentAndOrderTableAndUser> userList = new ArrayList<UserPaymentAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userList = userPaymentDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return userList;
	}

	/**
	 * 根据UserPayment实体添加
	 *
	 * 添加用户收入金额明细表
	 *
	 * @param userPayment
	 * @return
	 *
	 * @author ZY on 2018/10/20
	 */
	public int insertByUserPaymentAndUpdateMoney(UserPayment userPayment, UserCustomerIncomeMoney customerIncomeMoney) {
		int num = 0;
		int customerIncomeMoneyNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userPaymentDao.insertByUserPayment(session, userPayment);//添加用户付款表
			customerIncomeMoneyNum = userCustomerIncomeMoneyDao.insertByUserCustomerIncomeMoney(session, customerIncomeMoney);//添加客户收入明细表

			if (num == 0 || customerIncomeMoneyNum == 0) {
				return 0;
			}
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserPaymentService--insertByUserPaymentAndUpdateMoney--error:" + e.getMessage());
		}

		return num;
	}

}
