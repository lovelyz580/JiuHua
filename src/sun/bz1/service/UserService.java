package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.CreditChangeDao;
import sun.bz1.dao.UserCustomerDao;
import sun.bz1.dao.UserDao;
import sun.bz1.dao.UserServiceDao;
import sun.bz1.entity.*;
import util.MyBatisUtil;

/**
 * 用户表
 * 
 * Service
 * 
 * @author WJF on 2018/09/03
 */

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserServiceDao userServiceDao;
	
	@Autowired
	private UserCustomerDao userCustomerDao;
	
	@Autowired
	private CreditChangeDao creditChangeDao;

	private Logger logger = Logger.getLogger(UserService.class);

	/**
   	 * 根据User实体添加
   	 * 
   	 * @param user
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/03
   	 */
	public int insertByUser(User user) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.insertByUser(session, user);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--insertByUser--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
   	 * 添加用户表、用户_维修工、安装队表、信用值_变化表
   	 * 
   	 * @param user
   	 * @param userService
   	 * @param creditChange
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/06
   	 */
	public int insertUserAndUserServiceAndCreditChange(User user, UserServiceEntity userService, CreditChange creditChange) {
		int insertUserNum = 0;
		int insertUserServiceNum = 0;
		int insertCreditChangeNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			insertUserNum = userDao.insertByUser(session, user); // 添加用户表
			insertUserServiceNum = userServiceDao.insertByUserService(session, userService); // 添加用户_维修工、安装队表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange); // 添加信用值_变化表
			
			if (insertUserNum == 0 || insertUserServiceNum == 0 || insertCreditChangeNum == 0) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--insertUserAndUserServiceAndCreditChange--error:" + e.getMessage());
		}

		return insertUserNum;
	}
	
	/**
   	 * 添加用户表、用户_客户表、信用值_变化表
   	 * 
   	 * @param user
   	 * @param userCustomer
   	 * @param creditChange
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/06
   	 */
	public int insertUserAndUserCustomerAndCreditChange(User user, UserCustomer userCustomer, CreditChange creditChange) {
		int insertUserNum = 0;
		int insertUserCustomerNum = 0;
		int insertCreditChangeNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			insertUserNum = userDao.insertByUser(session, user); // 添加用户表
			insertUserCustomerNum = userCustomerDao.insertByUserCustomer(session, userCustomer); // 添加用户_客户表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange); // 添加信用值_变化表
			
			if (insertUserNum == 0 || insertUserCustomerNum == 0 || insertCreditChangeNum == 0) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--insertUserAndUserCustomerAndCreditChange--error:" + e.getMessage());
		}

		return insertUserNum;
	}
	
	/**
	 * 根据User实体更新
	 * 
	 * @param user
	 * @return
	 * 
	 * @author WJF on 2018/09/03
	 */
	public int updateByUser(User user) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.updateByUser(session, user);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--updateByUser--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据User实体的手机号码更新
	 * 
	 * @param user
	 * @return
	 * 
	 * @author WJF on 2018/09/21
	 */
	public int updateByUserPhone(User user) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.updateByUserPhone(session, user);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--updateByUserPhone--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
   	 * 更新用户表和用户_维修工、安装队表
   	 * 
   	 * @param user
   	 * @param userService
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/06
   	 */
	public int updateUserAndUserService(User user, UserServiceEntity userService) {
		int updateUserNum = 0;
		int updateUserServiceNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			updateUserNum = userDao.updateByUser(session, user); // 更新用户表
			updateUserServiceNum = userServiceDao.updateByUserService(session, userService); // 更新用户_维修工、安装队表

			if (updateUserNum == 0 || updateUserServiceNum == 0) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--updateUserAndUserService--error:" + e.getMessage());
		}

		return updateUserNum;
	}
	
	/**
   	 * 更新用户表和用户_客户表
   	 * 
   	 * @param user
   	 * @param userCustomer
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/06
   	 */
	public int updateUserAndUserCustomer(User user, UserCustomer userCustomer) {
		int updateUserNum = 0;
		int updateUserCustomerNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			updateUserNum = userDao.updateByUser(session, user); // 更新用户表
			updateUserCustomerNum = userCustomerDao.updateByUserCustomer(session, userCustomer); // 更新用户_客户表

			if (updateUserNum == 0 || updateUserCustomerNum == 0) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--updateUserAndUserCustomer--error:" + e.getMessage());
		}

		return updateUserNum;
	}
	
	/**
   	 * 更新用户表、添加用户_客户表、添加信用值_变化表
   	 * 
   	 * @param user
   	 * @param userCustomer
   	 * @param creditChange
   	 * @return
   	 * 
   	 * @author ZY on 2018/09/18
   	 */
	public int updateUserAndUserCustomerAndCreditChange(User user, UserCustomer userCustomer, CreditChange creditChange) {
		int updateUserNum = 0;
		int insertUserCustomerNum = 0;
		int insertCreditChangeNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			updateUserNum = userDao.updateByUser(session, user); // 更新用户表
			insertUserCustomerNum = userCustomerDao.insertByUserCustomer(session, userCustomer); // 添加用户_客户表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange); // 添加信用值_变化表
			
			if (updateUserNum == 0 || insertUserCustomerNum == 0 || insertCreditChangeNum == 0) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--updateUserAndUserCustomerAndCreditChange--error:" + e.getMessage());
		}

		return updateUserNum;
	}
	
	/**
   	 * 更新用户表、添加用户_维修工、安装队表、添加信用值_变化表
   	 * 
   	 * @param user
   	 * @param userServiceEntity
   	 * @param creditChange
   	 * @return
   	 * 
   	 * @author ZY on 2018/09/18
   	 */
	public int updateUserAndUserServiceAndCreditChange(User user, UserServiceEntity userServiceEntity, CreditChange creditChange) {
		int updateUserNum = 0;
		int insertUserServiceNum = 0;
		int insertCreditChangeNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			updateUserNum = userDao.updateByUser(session, user); // 更新用户表
			insertUserServiceNum = userServiceDao.insertByUserService(session, userServiceEntity); // 添加用户_维修工、安装队表
			insertCreditChangeNum = creditChangeDao.insertByCreditChange(session, creditChange); // 添加信用值_变化表
			
			if (updateUserNum == 0 || insertUserServiceNum == 0 || insertCreditChangeNum == 0) {
				return 0;
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--updateUserAndUserCustomerAndCreditChange--error:" + e.getMessage());
		}

		return updateUserNum;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询
   	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/03
	 */
	public int selectThreeTablesCountByUnionData(UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询
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
	 * @author WJF on 2018/09/03
	 */
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesByUnionData(UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userList = userDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return userList;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表模糊查询
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author WJF on 2018/09/03
	 */
	public int selectThreeTablesCountByUnionDataOrderBySort(UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.selectThreeTablesCountByUnionDataOrderBySort(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesCountByUnionDataOrderBySort--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询
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
	 * @author ZY on 2018/12/01
	 */
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesByUnionDataOrderBySort(UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userList = userDao.selectThreeTablesByUnionDataOrderBySort(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesByUnionDataOrderBySort--error:" + e.getMessage());
		}

		return userList;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表模糊查询
   	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/03
	 */
	public int selectThreeTablesCountBySelectData(UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表模糊查询
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
	 * @author WJF on 2018/09/03
	 */
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesBySelectData(UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userList = userDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return userList;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表模糊查询（查询派单人时用到）
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2019/01/15
	 */
	public int selectThreeTablesCountBySelectDataNewUser(UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.selectThreeTablesCountBySelectDataNewUser(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesCountBySelectDataNewUser--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表模糊查询(查询派单人时用到)
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
	 * @author ZY on 2019/01/15
	 */
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesBySelectDataNewUser(UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userList = userDao.selectThreeTablesBySelectDataNewUser(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesBySelectDataNewUser--error:" + e.getMessage());
		}

		return userList;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询（客户-生成报表）
	 *
	 * 查询数量
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/06
	 */
	public int selectThreeTablesCountBySelectDataKHReport(UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.selectThreeTablesCountBySelectDataKHReport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesCountBySelectDataKHReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询（客户-生成报表）
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
	 * @author ZY on 2018/12/06
	 */
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesBySelectDataKHReport(UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userList = userDao.selectThreeTablesBySelectDataKHReport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesBySelectDataKHReport--error:" + e.getMessage());
		}

		return userList;
	}


	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询（客户-生成报表）
	 *
	 * 查询数量
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/06
	 */
	public int selectThreeTablesCountBySelectDataWXReport(UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.selectThreeTablesCountBySelectDataWXReport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesCountBySelectDataWXReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询（ 维修-生成报表）
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
	 * @author ZY on 2018/12/06
	 */
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesBySelectDataWXReport(UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userList = userDao.selectThreeTablesBySelectDataWXReport(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesBySelectDataWXReport--error:" + e.getMessage());
		}

		return userList;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表模糊查询   导出
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
	 * @author ZY on 2018/12/01
	 */
	/*public List<UserExcel> selectThreeTablesBySelectDataExcel(UserAndUserServiceAndUserCustomer unionData) {
		List<UserExcel> userExcelList = new ArrayList<UserExcel>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();
			userList = userDao.selectThreeTablesBySelectData(session, unionData);

			for (UserAndUserServiceAndUserCustomer userAndUserServiceAndUserCustomer : userList) {
				UserExcel userExcel = new UserExcel();
				userExcel.setUsername(userAndUserServiceAndUserCustomer.getUsername());
				userExcel.setUserphone(userAndUserServiceAndUserCustomer.getUserphone());
				userExcel.setUserrole(userAndUserServiceAndUserCustomer.getUserrole());
				userExcel.setUserstate(userAndUserServiceAndUserCustomer.getUserstate());
				userExcelList.add(userExcel);
			}
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return userExcelList;
	}*/

	/**
	 * 根据LogAndUser实体联表模糊查询
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/11/28
	 */
	public int selectLogAndUserCount(LogAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.selectLogAndUserCount(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectLogAndUserCount--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据LogAndUser实体联表模糊查询
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
	 * @author ZY on 2018/11/28
	 */
	public List<LogAndUser> selectLogAndUser(LogAndUser unionData) {
		List<LogAndUser> userList = new ArrayList<LogAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userList = userDao.selectLogAndUser(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectLogAndUser--error:" + e.getMessage());
		}

		return userList;
	}

	/**
   	 * 根据UserAndUserServiceAndUserCustomer实体联表查询
   	 * 
   	 * 平台派单时用到
   	 * 
   	 * @param unionData
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/12
   	 */
   	public List<UserAndUserServiceAndUserCustomer> selectBackDistributionServiceUser(UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userList = userDao.selectBackDistributionServiceUser(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--selectBackDistributionServiceUser--error:" + e.getMessage());
		}

		return userList;
	}
   	
   	/**
	 * 客户金额变化列表
   	 * 
   	 * 微信小程序查询用户金额变化时用到
   	 * 
   	 * 根据UserAndUserServiceAndUserCustomer实体联表查询
   	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/20
	 */
	public int wechatSelectCustomerMoneyListCountByUnionData(UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.wechatSelectCustomerMoneyListCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--wechatSelectCustomerMoneyListCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
   	
   	/**
   	 * 客户金额变化列表
   	 * 
   	 * 微信小程序查询用户金额变化时用到
   	 * 
   	 * 根据UserAndUserServiceAndUserCustomer实体联表查询
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
   	 * @author WJF on 2018/09/20
   	 */
   	public List<WechatUserMoney> wechatSelectCustomerMoneyListByUnionData(UserAndUserServiceAndUserCustomer unionData) {
		List<WechatUserMoney> moneyList = new ArrayList<WechatUserMoney>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			moneyList = userDao.wechatSelectCustomerMoneyListByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--wechatSelectCustomerMoneyListByUnionData--error:" + e.getMessage());
		}

		return moneyList;
	}
   	
   	/**
	 * 维修工、安装队金额变化列表
   	 * 
   	 * 微信小程序查询用户金额变化时用到
   	 * 
   	 * 根据UserAndUserServiceAndUserCustomer实体联表查询
   	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/20
	 */
	public int wechatSelectServiceMoneyListCountByUnionData(UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userDao.wechatSelectServiceMoneyListCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--wechatSelectServiceMoneyListCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}
   	
   	/**
   	 * 维修工、安装队金额变化列表
   	 * 
   	 * 微信小程序查询用户金额变化时用到
   	 * 
   	 * 根据UserAndUserServiceAndUserCustomer实体联表查询
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
   	 * @author WJF on 2018/09/20
   	 */
   	public List<WechatUserMoney> wechatSelectServiceMoneyListByUnionData(UserAndUserServiceAndUserCustomer unionData) {
		List<WechatUserMoney> moneyList = new ArrayList<WechatUserMoney>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			moneyList = userDao.wechatSelectServiceMoneyListByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--wechatSelectServiceMoneyListByUnionData--error:" + e.getMessage());
		}

		return moneyList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param idList
	 * @return
	 * 
	 * @author WJF on 2018/09/03
	 */
	public int deleteByIdList(List<Integer> idList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < idList.size(); i++) {
				deleteNum = deleteNum + userDao.deleteByPrimaryKey(session, idList.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 用户ID List 删除信息
	 * 
	 * @param userIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/06
	 */
	public int deleteThreeTablesByUserIdList(List<String> userIdList) {
		int deleteUserNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < userIdList.size(); i++) {
				// 删除用户表
				User user = new User();
				user.setUserid(userIdList.get(i));
				deleteUserNum = deleteUserNum + userDao.deleteByUser(session, user);
				
				// 删除用户_维修工、安装队表
				UserServiceEntity serviceUser = new UserServiceEntity();
				serviceUser.setUserid(userIdList.get(i));
				if (userServiceDao.selectCountByUserService(session, serviceUser) != 0) {
					// 如果查询到数据就进行删除
					UserServiceEntity userServiceEntity = new UserServiceEntity();
					userServiceEntity.setUserid(userIdList.get(i));
					
					// 删除
					userServiceDao.deleteByUserService(session, userServiceEntity);
				}
				
				// 删除用户_客户表
				UserCustomer customereUser = new UserCustomer();
				customereUser.setUserid(userIdList.get(i));
				if (userCustomerDao.selectCountByUserCustomer(session, customereUser) != 0) {
					// 如果查询到数据就进行删除
					UserCustomer userCustomer = new UserCustomer();
					userCustomer.setUserid(userIdList.get(i));
					
					// 删除
					userCustomerDao.deleteByUserCustomer(session, userCustomer);
				}
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--deleteThreeTablesByUserIdList--error:" + e.getMessage());
		}

		return deleteUserNum;
	}


	/**
	 * 根据User实体更新(批量修改状态)
	 *
	 * @param user
	 * @return
	 *
	 * @author ZY on 2018/12/11
	 */
	public int updateByUserState(User user) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < user.getUuidlist().size(); i++) {
				user.setUserid(user.getUuidlist().get(i));
				num = userDao.updateByUser(session, user);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserService--updateByUserState--error:" + e.getMessage());
		}

		return num;
	}

}
