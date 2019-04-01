package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.UserServiceDao;
import sun.bz1.entity.UserServiceEntity;
import util.MyBatisUtil;

/**
 * 用户_维修工、安装队表
 * 
 * Service
 * 
 * @author WJF on 2018/09/05
 */

@Service
public class UserServiceService {

	@Autowired
	private UserServiceDao userServiceDao;

	private Logger logger = Logger.getLogger(UserServiceService.class);
	
	/**
   	 * 根据UserService实体添加
   	 * 
   	 * @param userService
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/05
   	 */
	public int insertByUserService(UserServiceEntity userService) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userServiceDao.insertByUserService(session, userService);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceService--insertByUserService--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserService实体更新
	 * 
	 * @param userService
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByUserService(UserServiceEntity userService) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userServiceDao.updateByUserService(session, userService);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceService--updateByUserService--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserService实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param userService
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectCountByUserService(UserServiceEntity userService) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = userServiceDao.selectCountByUserService(session, userService);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceService--selectCountByUserService--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据UserService实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param userService
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public List<UserServiceEntity> selectByUserService(UserServiceEntity userService) {
		List<UserServiceEntity> userServiceList = new ArrayList<UserServiceEntity>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			userServiceList = userServiceDao.selectByUserService(session, userService);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceService--selectByUserService--error:" + e.getMessage());
		}

		return userServiceList;
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
				deleteNum = deleteNum + userServiceDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceService--deleteByIdList--error:" + e.getMessage());
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
				UserServiceEntity userService = new UserServiceEntity();
				userService.setUserid(userIdList.get(i));
				
				deleteNum = deleteNum + userServiceDao.deleteByUserService(session, userService);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("UserServiceService--deleteByUserIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
