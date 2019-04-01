package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.InterceptTravelDao;
import sun.bz1.entity.InterceptTravel;
import util.MyBatisUtil;

/**
 * 差旅费(每公里)拦标价表
 * 
 * Service
 * 
 * @author WJF on 2018/10/09
 */

@Service
public class InterceptTravelService {
	
	@Autowired
	private InterceptTravelDao interceptTravelDao;

	private Logger logger = Logger.getLogger(InterceptTravelService.class);
	
	/**
   	 * 根据InterceptTravel实体添加
   	 * 
   	 * @param interceptTravel
   	 * @return
   	 * 
   	 * @author WJF on 2018/10/09
   	 */
	public int insertByInterceptTravel(InterceptTravel interceptTravel) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = interceptTravelDao.insertByInterceptTravel(session, interceptTravel);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptTravelService--insertByInterceptTravel--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptTravel实体更新
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int updateByInterceptTravel(InterceptTravel interceptTravel) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = interceptTravelDao.updateByInterceptTravel(session, interceptTravel);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptTravelService--updateByInterceptTravel--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptTravel实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int selectCountByInterceptTravel(InterceptTravel interceptTravel) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = interceptTravelDao.selectCountByInterceptTravel(session, interceptTravel);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptTravelService--selectCountByInterceptTravel--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据InterceptTravel实体查询
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public List<InterceptTravel> selectByInterceptTravel(InterceptTravel interceptTravel) {
		List<InterceptTravel> interceptTravelList = new ArrayList<InterceptTravel>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			interceptTravelList = interceptTravelDao.selectByInterceptTravel(session, interceptTravel);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptTravelService--selectByInterceptTravel--error:" + e.getMessage());
		}

		return interceptTravelList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + interceptTravelDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("InterceptTravelService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
