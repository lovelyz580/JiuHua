package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.*;
import sun.bz1.entity.*;
import util.MyBatisUtil;

/**
 * 微信相关数据表
 * 
 * Service
 * 
 * @author WJF on 2018/10/12
 */

@Service
public class WechatService {

	@Autowired
	private WechatDao WechatDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserCustomerIncomeMoneyDao customerIncomeMoneyDao;
	
	@Autowired
	private UserServiceIncomeMoneyDao serviceIncomeMoneyDao;

	@Autowired
	private UserPaymentDao userPaymentDao;

	@Autowired
	private BackMoneyDao backMoneyDao;

	@Autowired
	private OrderTableDao orderTableDao;

	private Logger logger = Logger.getLogger(WechatService.class);
	
	/**
   	 * 根据Wechat实体添加
   	 * 
   	 * @param wechat
   	 * @return
   	 * 
   	 * @author WJF on 2018/10/12
   	 */
	public int insertByWechat(Wechat wechat) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = WechatDao.insertByWechat(session, wechat);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatService--insertByWechat--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Wechat实体更新
	 * 
	 * @param wechat
	 * @return
	 * 
	 * @author WJF on 2018/10/12
	 */
	public int updateByWechat(Wechat wechat) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = WechatDao.updateByWechat(session, wechat);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatService--updateByWechat--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Wechat实体查询
	 * 
	 * @param wechat
	 * @return
	 * 
	 * @author WJF on 2018/10/12
	 */
	public List<Wechat> selectByWechat(Wechat wechat) {
		List<Wechat> wechatList = new ArrayList<Wechat>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			wechatList = WechatDao.selectByWechat(session, wechat);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatService--selectByWechat--error:" + e.getMessage());
		}

		return wechatList;
	}
	
	/**
	 * 微信支付回调接口
	 * 
	 * 扫码支付回调接口
	 * 
	 * 业务逻辑：
	 * 1、更新用户的余额
	 * 2、根据用户身份，添加不同的收入金额表
	 * 
	 * @param user
	 * @param customerIncomeMoney
	 * @param serviceIncomeMoney
	 * 
	 * @author WJF on 2018/10/13
	 */
	public int notify(User user, UserCustomerIncomeMoney customerIncomeMoney, UserServiceIncomeMoney serviceIncomeMoney) {
		int updateUserNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			
			updateUserNum = userDao.updateByUser(session, user);
			
			if ("KH,".equals(user.getUserrole())) {
				// 添加用户_客户_收入_金额表
				customerIncomeMoneyDao.insertByUserCustomerIncomeMoney(session, customerIncomeMoney);
			} else {
				// 添加用户_维修工、安装队_收入_金额表
				serviceIncomeMoneyDao.insertByUserServiceIncomeMoney(session, serviceIncomeMoney);
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatService--notify--error:" + e.getMessage());
		}

		return updateUserNum;
	}

	/**
	 * 微信支付回调接口
	 *
	 * 扫码支付回调接口
	 *
	 * 业务逻辑：
	 * 根据订单号
	 * 1、更新用户的余额
	 * 2、根据用户身份，添加不同的收入金额表
	 *
	 * @param userPayment
	 * @param customerIncomeMoney
	 * @param backMoney
	 *
	 * @author ZY on 2018/10/27
	 */
	public int notifyAndInsert(OrderTable orderTable, UserPayment userPayment, UserCustomerIncomeMoney customerIncomeMoney, BackMoney backMoney) {
		int orderTableNum = 0;
		int userPaymentNum = 0;
		int customerIncomeMoneyNum = 0;
		int backMoneyNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderTableNum = orderTableDao.updateByOrderTable(session, orderTable);
			//付款记录表
			userPaymentNum = userPaymentDao.insertByUserPayment(session, userPayment);

			//客户收入表
			customerIncomeMoneyNum = customerIncomeMoneyDao.insertByUserCustomerIncomeMoney(session, customerIncomeMoney);

			//平台收入表
			backMoneyNum = backMoneyDao.insertByBackMoney(session, backMoney);

			if (orderTableNum == 0 ||userPaymentNum == 0 || customerIncomeMoneyNum == 0 || backMoneyNum == 0) {
				return 0;
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatService--notifyAndInsert--error:" + e.getMessage());
		}

		return userPaymentNum;
	}

}
