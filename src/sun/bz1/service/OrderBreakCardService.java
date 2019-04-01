package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.NewsDao;
import sun.bz1.dao.OrderBreakCardDao;
import sun.bz1.entity.News;
import sun.bz1.entity.OrderBreakCard;
import sun.bz1.entity.OrderBreakCardAndOrderTableAndUser;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单打卡表
 *
 * Service
 *
 * @author ZY on 2018/12/15
 */

@Service
public class OrderBreakCardService {

	@Autowired
	private OrderBreakCardDao orderBreakCardDao;

	private Logger logger = Logger.getLogger(OrderBreakCardService.class);

	/**
	 * 根据OrderBreakCard实体添加
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/12/18
	 */
	public int insertByOrderBreakCard(OrderBreakCard orderBreakCard) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderBreakCardDao.insertByOrderBreakCard(session, orderBreakCard);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderBreakCardService--insertByOrderBreakCard--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderBreakCard实体更新
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/12/18
	 */
	public int updateByOrderBreakCard(OrderBreakCard orderBreakCard) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderBreakCardDao.updateByOrderBreakCard(session, orderBreakCard);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderBreakCardService--updateByOrderBreakCard--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderBreakCard实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/12/18
	 */
	public int selectCountByOrderBreakCard(OrderBreakCard orderBreakCard) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderBreakCardDao.selectCountByOrderBreakCard(session, orderBreakCard);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderBreakCardService--selectCountByOrderBreakCard--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderBreakCard实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/12/18
	 */
	public List<OrderBreakCard> selectByOrderBreakCard(OrderBreakCard orderBreakCard) {
		List<OrderBreakCard> orderBreakCardList = new ArrayList<OrderBreakCard>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderBreakCardList = orderBreakCardDao.selectByOrderBreakCard(session, orderBreakCard);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderBreakCardService--selectByOrderBreakCard--error:" + e.getMessage());
		}

		return orderBreakCardList;
	}

	/**
	 * 根据OrderBreakCard实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/12/18
	 */
	public int selectCountBySelectData(OrderBreakCard orderBreakCard) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderBreakCardDao.selectCountBySelectData(session, orderBreakCard);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderBreakCardService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderBreakCard实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/12/18
	 */
	public List<OrderBreakCard> selectBySelectData(OrderBreakCard orderBreakCard) {
		List<OrderBreakCard> orderBreakCardList = new ArrayList<OrderBreakCard>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			orderBreakCardList = orderBreakCardDao.selectBySelectData(session, orderBreakCard);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderBreakCardService--selectBySelectData--error:" + e.getMessage());
		}

		return orderBreakCardList;
	}

	/**
	 * 根据OrderBreakCardAndOrderTableAndUser实体联表模糊查询
	 *
	 * 查询数量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/18
	 */
	public int selectThreeTablesCountBySelectData(OrderBreakCardAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderBreakCardDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderBreakCardService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderBreakCardAndOrderTableAndUser实体联表模糊查询
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
	 * @author ZY on 2018/12/18
	 */
	public List<OrderBreakCardAndOrderTableAndUser> selectThreeTablesBySelectData(OrderBreakCardAndOrderTableAndUser unionData) {
		List<OrderBreakCardAndOrderTableAndUser> interceptList = new ArrayList<OrderBreakCardAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			interceptList = orderBreakCardDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderBreakCardService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return interceptList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/12/18
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + orderBreakCardDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderBreakCardService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
