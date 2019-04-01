package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.LogAndUser;
import sun.bz1.entity.User;
import sun.bz1.entity.UserAndUserServiceAndUserCustomer;
import sun.bz1.entity.WechatUserMoney;

/**
 * 用户表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/03
 */

@Repository
public class UserDao {
	
	private Logger logger = Logger.getLogger(UserDao.class);

	public int insert(SqlSession session, User user) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.insert("sun.bz1.dao.mapper.User.insert", user);
		} catch (Exception e) {
			logger.error("UserDao--insert--error:" + e.getMessage());
		}

		return num;
	}
	
    public int insertSelective(SqlSession session, User user) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.insert("sun.bz1.dao.mapper.User.insertSelective", user);
		} catch (Exception e) {
			logger.error("UserDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

    public int updateByPrimaryKeySelective(SqlSession session, User user) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.update("sun.bz1.dao.mapper.User.updateByPrimaryKeySelective", user);
		} catch (Exception e) {
			logger.error("UserDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

    public int updateByPrimaryKey(SqlSession session, User user) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.update("sun.bz1.dao.mapper.User.updateByPrimaryKey", user);
		} catch (Exception e) {
			logger.error("UserDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
    	int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.selectOne("sun.bz1.dao.mapper.User.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    public User selectByPrimaryKey(SqlSession session, Integer id) {
    	User user = new User();
		
		try {
			logger.info("UserDao");
			
			user = session.selectOne("sun.bz1.dao.mapper.User.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return user;
	}
    
    public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.delete("sun.bz1.dao.mapper.User.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("UserDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    /**
   	 * 根据User实体添加
   	 * 
   	 * @param user
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/03
   	 */
   	public int insertByUser(SqlSession session, User user) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.insert("sun.bz1.dao.mapper.User.insertByUser", user);
		} catch (Exception e) {
			logger.error("UserDao--insertByUser--error:" + e.getMessage());
		}

		return num;
	}

   	/**
   	 * 根据User实体更新
   	 * 
   	 * @param user
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/03
   	 */
   	public int updateByUser(SqlSession session, User user) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.update("sun.bz1.dao.mapper.User.updateByUser", user);
		} catch (Exception e) {
			logger.error("UserDao--updateByUser--error:" + e.getMessage());
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
   	public int updateByUserPhone(SqlSession session, User user) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.update("sun.bz1.dao.mapper.User.updateByUserPhone", user);
		} catch (Exception e) {
			logger.error("UserDao--updateByUserPhone--error:" + e.getMessage());
		}

		return num;
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
   	public int selectThreeTablesCountByUnionData(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.selectOne("sun.bz1.dao.mapper.User.selectThreeTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
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
   	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesByUnionData(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();
		
		try {
			logger.info("UserDao");
			
			userList = session.selectList("sun.bz1.dao.mapper.User.selectThreeTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return userList;
	}


	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询 小程序首页排序
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/01
	 */
	public int selectThreeTablesCountByUnionDataOrderBySort(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			logger.info("UserDao");

			num = session.selectOne("sun.bz1.dao.mapper.User.selectThreeTablesCountByUnionDataOrderBySort", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesCountByUnionDataOrderBySort--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询 小程序首页排序
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
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesByUnionDataOrderBySort(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			logger.info("UserDao");

			userList = session.selectList("sun.bz1.dao.mapper.User.selectThreeTablesByUnionDataOrderBySort", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesByUnionDataOrderBySort--error:" + e.getMessage());
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
   	public int selectThreeTablesCountBySelectData(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.selectOne("sun.bz1.dao.mapper.User.selectThreeTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
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
   	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesBySelectData(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();
		
		try {
			logger.info("UserDao");
			
			userList = session.selectList("sun.bz1.dao.mapper.User.selectThreeTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesBySelectData--error:" + e.getMessage());
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
	public int selectThreeTablesCountBySelectDataNewUser(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			logger.info("UserDao");

			num = session.selectOne("sun.bz1.dao.mapper.User.selectThreeTablesCountBySelectDataNewUser", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesCountBySelectDataNewUser--error:" + e.getMessage());
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
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesBySelectDataNewUser(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			logger.info("UserDao");

			userList = session.selectList("sun.bz1.dao.mapper.User.selectThreeTablesBySelectDataNewUser", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesBySelectDataNewUser--error:" + e.getMessage());
		}

		return userList;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询(客户-生成报表)
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
	public int selectThreeTablesCountBySelectDataKHReport(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			logger.info("UserDao");

			num = session.selectOne("sun.bz1.dao.mapper.User.selectThreeTablesCountBySelectDataKHReport", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesCountBySelectDataKHReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询(客户-生成报表)
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
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesBySelectDataKHReport(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			logger.info("UserDao");

			userList = session.selectList("sun.bz1.dao.mapper.User.selectThreeTablesBySelectDataKHReport", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesBySelectDataKHReport--error:" + e.getMessage());
		}

		return userList;
	}


	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询(客户-生成报表)
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
	public int selectThreeTablesCountBySelectDataWXReport(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		int num = 0;

		try {
			logger.info("UserDao");

			num = session.selectOne("sun.bz1.dao.mapper.User.selectThreeTablesCountBySelectDataWXReport", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesCountBySelectDataWXReport--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserAndUserServiceAndUserCustomer实体联表查询(客户-生成报表)
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
	public List<UserAndUserServiceAndUserCustomer> selectThreeTablesBySelectDataWXReport(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();

		try {
			logger.info("UserDao");

			userList = session.selectList("sun.bz1.dao.mapper.User.selectThreeTablesBySelectDataWXReport", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectThreeTablesBySelectDataWXReport--error:" + e.getMessage());
		}

		return userList;
	}



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
	public int selectLogAndUserCount(SqlSession session, LogAndUser unionData) {
		int num = 0;

		try {
			logger.info("UserDao");

			num = session.selectOne("sun.bz1.dao.mapper.User.selectLogAndUserCount", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectLogAndUserCount--error:" + e.getMessage());
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
	public List<LogAndUser> selectLogAndUser(SqlSession session, LogAndUser unionData) {
		List<LogAndUser> userList = new ArrayList<LogAndUser>();

		try {
			logger.info("UserDao");

			userList = session.selectList("sun.bz1.dao.mapper.User.selectLogAndUser", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectLogAndUser--error:" + e.getMessage());
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
   	public List<UserAndUserServiceAndUserCustomer> selectBackDistributionServiceUser(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		List<UserAndUserServiceAndUserCustomer> userList = new ArrayList<UserAndUserServiceAndUserCustomer>();
		
		try {
			logger.info("UserDao");
			
			userList = session.selectList("sun.bz1.dao.mapper.User.selectBackDistributionServiceUser", unionData);
		} catch (Exception e) {
			logger.error("UserDao--selectBackDistributionServiceUser--error:" + e.getMessage());
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
   	public int wechatSelectCustomerMoneyListCountByUnionData(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
   		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.selectOne("sun.bz1.dao.mapper.User.wechatSelectCustomerMoneyListCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserDao--wechatSelectCustomerMoneyListCountByUnionData--error:" + e.getMessage());
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
   	public List<WechatUserMoney> wechatSelectCustomerMoneyListByUnionData(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		List<WechatUserMoney> moneyList = new ArrayList<WechatUserMoney>();
		
		try {
			logger.info("UserDao");
			
			moneyList = session.selectList("sun.bz1.dao.mapper.User.wechatSelectCustomerMoneyListByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserDao--wechatSelectCustomerMoneyListByUnionData--error:" + e.getMessage());
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
   	public int wechatSelectServiceMoneyListCountByUnionData(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
   		int num = 0;
		
		try {
			logger.info("UserDao");
			
			num = session.selectOne("sun.bz1.dao.mapper.User.wechatSelectServiceMoneyListCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserDao--wechatSelectServiceMoneyListCountByUnionData--error:" + e.getMessage());
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
   	public List<WechatUserMoney> wechatSelectServiceMoneyListByUnionData(SqlSession session, UserAndUserServiceAndUserCustomer unionData) {
		List<WechatUserMoney> moneyList = new ArrayList<WechatUserMoney>();
		
		try {
			logger.info("UserDao");
			
			moneyList = session.selectList("sun.bz1.dao.mapper.User.wechatSelectServiceMoneyListByUnionData", unionData);
		} catch (Exception e) {
			logger.error("UserDao--wechatSelectServiceMoneyListByUnionData--error:" + e.getMessage());
		}

		return moneyList;
	}
   	
   	/**
	 * 根据User实体删除
	 * 
	 * @param user
	 * @return
	 * 
	 * @author WJF on 2018/09/06
	 */
	public int deleteByUser(SqlSession session, User user) {
		int num = 0;

		try {
			logger.info("UserDao");

			num = session.delete("sun.bz1.dao.mapper.User.deleteByUser", user);
		} catch (Exception e) {
			logger.error("UserDao--deleteByUser--error:" + e.getMessage());
		}

		return num;
	}


}