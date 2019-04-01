package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.UserCustomerDao;
import sun.bz1.entity.UserCustomer;
import util.MyBatisUtil;

/**
 * 用户_客户表
 * 
 * Service
 * 
 * @author WJF on 2018/09/05
 */

@Service
public class UserCustomerService {

	@Autowired
	private UserCustomerDao userCustomerDao;

	private Logger logger = Logger.getLogger(UserCustomerService.class);
	
	/**
   	 * 根据UserCustomer实体添加
   	 * 
   	 * @param userCustomer
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/05
   	 */
	public int insertByUserCustomer(UserCustomer userCustomer) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userCustomerDao.insertByUserCustomer(session, userCustomer);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerService--insertByUserCustomer--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomer实体更新
	 * 
	 * @param userCustomer
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByUserCustomer(UserCustomer userCustomer) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userCustomerDao.updateByUserCustomer(session, userCustomer);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerService--updateByUserCustomer--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomer实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param userCustomer
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectCountByUserCustomer(UserCustomer userCustomer) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userCustomerDao.selectCountByUserCustomer(session, userCustomer);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerService--selectCountByUserCustomer--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserCustomer实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param userCustomer
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public List<UserCustomer> selectByUserCustomer(UserCustomer userCustomer) {
		List<UserCustomer> userCustomerList = new ArrayList<UserCustomer>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userCustomerList = userCustomerDao.selectByUserCustomer(session, userCustomer);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerService--selectByUserCustomer--error:" + e.getMessage());
		}

		return userCustomerList;
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
				deleteNum = deleteNum + userCustomerDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 用户ID List 删除信息
	 * 
	 * @param userIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int deleteByUserIdList(List<String> userIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < userIdList.size(); i++) {
				UserCustomer userCustomer = new UserCustomer();
				userCustomer.setUserid(userIdList.get(i));
				
				deleteNum = deleteNum + userCustomerDao.deleteByUserCustomer(session, userCustomer);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserCustomerService--deleteByUserIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
