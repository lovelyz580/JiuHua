package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.InterceptDao;
import sun.bz1.dao.OrderPriceDao;
import sun.bz1.entity.InterceptAndGoodsAndProject;
import sun.bz1.entity.OrderPrice;
import sun.bz1.entity.OrderPriceAndGoodsAndProjectAndUser;
import util.MyBatisUtil;

/**
 * 维修单对应维修单价表
 * 
 * Service
 * 
 * @author WJF on 2018/10/09
 */

@Service
public class OrderPriceService {

	@Autowired
	private OrderPriceDao orderPriceDao;
	
	@Autowired
	private InterceptDao interceptDao;
	
	private Logger logger = Logger.getLogger(OrderPriceService.class);
	
	/**
   	 * 根据OrderPrice实体添加
   	 * 
   	 * @param orderPrice
   	 * @return
   	 * 
   	 * @author WJF on 2018/10/09
   	 */
	public int insertByOrderPrice(OrderPrice orderPrice) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderPriceDao.insertByOrderPrice(session, orderPrice);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderPriceService--insertByOrderPrice--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderPrice实体更新
	 * 
	 * @param orderPrice
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int updateByOrderPrice(OrderPrice orderPrice) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderPriceDao.updateByOrderPrice(session, orderPrice);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderPriceService--updateByOrderPrice--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderPriceAndGoodsAndProjectAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int selectFourTablesCountByUnionData(OrderPriceAndGoodsAndProjectAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = orderPriceDao.selectFourTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderPriceService--selectFourTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据OrderPriceAndGoodsAndProjectAndUser实体联表查询
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public List<OrderPriceAndGoodsAndProjectAndUser> selectFourTablesByUnionData(OrderPriceAndGoodsAndProjectAndUser unionData) {
		List<OrderPriceAndGoodsAndProjectAndUser> OrderPriceList = new ArrayList<OrderPriceAndGoodsAndProjectAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			OrderPriceList = orderPriceDao.selectFourTablesByUnionData(session, unionData);
			
			// 根据产品ID、项目ID、拦标价类型、拦标价状态，查询拦标价
			if (OrderPriceList != null && OrderPriceList.size() != 0) {
				for (int i = 0; i < OrderPriceList.size(); i++) {
					InterceptAndGoodsAndProject interceptUnionData = new InterceptAndGoodsAndProject();
					interceptUnionData.setGoodsid(OrderPriceList.get(i).getGoodsid()); // 产品ID
					interceptUnionData.setProjectid(OrderPriceList.get(i).getProjectid()); // 项目ID
					interceptUnionData.setIntercepttype(OrderPriceList.get(i).getOrderpricetype()); // 拦标价类型(WX:维修工/AZ:安装队)
					interceptUnionData.setInterceptstate("Y"); // 拦标价状态(Y:有效/N:无效)
					interceptUnionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
					
					// 查询
					List<InterceptAndGoodsAndProject> interceptList = interceptDao.selectThreeTablesByUnionData(session, interceptUnionData);

					// 添加拦标价数据
					Double interceptRealMoney = null;
					if (interceptList != null && interceptList.size() != 0) {
						interceptRealMoney = interceptList.get(0).getInterceptrealmoney();
					}
					OrderPriceList.get(i).setInterceptrealmoney(interceptRealMoney);
				}
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderPriceService--selectFourTablesByUnionData--error:" + e.getMessage());
		}

		return OrderPriceList;
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
				deleteNum = deleteNum + orderPriceDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderPriceService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				OrderPrice orderPrice = new OrderPrice();
				orderPrice.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + orderPriceDao.deleteByOrderPrice(session, orderPrice);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("OrderPriceService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
