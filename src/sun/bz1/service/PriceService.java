package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.InterceptDao;
import sun.bz1.dao.PriceDao;
import sun.bz1.entity.InterceptAndGoodsAndProject;
import sun.bz1.entity.Price;
import sun.bz1.entity.PriceAndGoodsAndProjectAndUser;
import sun.bz1.entity.ProjectAndGoods;
import util.MyBatisUtil;

/**
 * 维修单价表
 * 
 * Service
 * 
 * @author WJF on 2018/09/05
 */

@Service
public class PriceService {

	@Autowired
	private PriceDao priceDao;
	
	@Autowired
	private InterceptDao interceptDao;

	private Logger logger = Logger.getLogger(PriceService.class);
	
	/**
   	 * 根据Price实体添加
   	 * 
   	 * @param price
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/05
   	 */
	public int insertByPrice(Price price) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = priceDao.insertByPrice(session, price);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PriceService--insertByPrice--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Price实体更新
	 * 
	 * @param price
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int updateByPrice(Price price) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = priceDao.updateByPrice(session, price);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PriceService--updateByPrice--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据PriceAndGoodsAndProjectAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectFourTablesCountByUnionData(PriceAndGoodsAndProjectAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = priceDao.selectFourTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PriceService--selectFourTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据PriceAndGoodsAndProjectAndUser实体联表查询
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
	 * @author WJF on 2018/09/05
	 */
	public List<PriceAndGoodsAndProjectAndUser> selectFourTablesByUnionData(PriceAndGoodsAndProjectAndUser unionData) {
		List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			priceList = priceDao.selectFourTablesByUnionData(session, unionData);
			
			// 根据产品ID、项目ID、拦标价类型、拦标价状态，查询拦标价
			if (priceList != null && priceList.size() != 0) {
				for (int i = 0; i < priceList.size(); i++) {
					InterceptAndGoodsAndProject interceptUnionData = new InterceptAndGoodsAndProject();
					interceptUnionData.setGoodsid(priceList.get(i).getGoodsid()); // 产品ID
					interceptUnionData.setProjectid(priceList.get(i).getProjectid()); // 项目ID
					interceptUnionData.setIntercepttype(priceList.get(i).getPricetype()); // 拦标价类型(WX:维修工/AZ:安装队)
					interceptUnionData.setInterceptstate("Y"); // 拦标价状态(Y:有效/N:无效)
					interceptUnionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
					
					// 查询
					List<InterceptAndGoodsAndProject> interceptList = interceptDao.selectThreeTablesByUnionData(session, interceptUnionData);

					// 添加拦标价数据
					Double interceptRealMoney = null;
					if (interceptList != null && interceptList.size() != 0) {
						interceptRealMoney = interceptList.get(0).getInterceptrealmoney();
					}
					priceList.get(i).setInterceptrealmoney(interceptRealMoney);
				}
			}
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PriceService--selectFourTablesByUnionData--error:" + e.getMessage());
		}

		return priceList;
	}

	/**
	 * 根据PriceAndGoodsAndProjectAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int selectFourTablesCountBySelectData(PriceAndGoodsAndProjectAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = priceDao.selectFourTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PriceService--selectFourTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据PriceAndGoodsAndProjectAndUser实体联表模糊查询
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
	 * @author WJF on 2018/09/05
	 */
	public List<PriceAndGoodsAndProjectAndUser> selectFourTablesBySelectData(PriceAndGoodsAndProjectAndUser unionData) {
		List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			priceList = priceDao.selectFourTablesBySelectData(session, unionData);
			
			// 根据产品ID、项目ID、拦标价类型、拦标价状态，查询拦标价
			if (priceList != null && priceList.size() != 0) {
				for (int i = 0; i < priceList.size(); i++) {
					InterceptAndGoodsAndProject interceptUnionData = new InterceptAndGoodsAndProject();
					interceptUnionData.setGoodsid(priceList.get(i).getGoodsid()); // 产品ID
					interceptUnionData.setProjectid(priceList.get(i).getProjectid()); // 项目ID
					interceptUnionData.setIntercepttype(priceList.get(i).getPricetype()); // 拦标价类型(WX:维修工/AZ:安装队)
					interceptUnionData.setInterceptstate("Y"); // 拦标价状态(Y:有效/N:无效)
					interceptUnionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
								
					// 查询
					List<InterceptAndGoodsAndProject> interceptList = interceptDao.selectThreeTablesByUnionData(session, interceptUnionData);

					// 添加拦标价数据
					Double interceptRealMoney = null;
					if (interceptList != null && interceptList.size() != 0) {
						interceptRealMoney = interceptList.get(0).getInterceptrealmoney();
					}
					priceList.get(i).setInterceptrealmoney(interceptRealMoney);
				}
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PriceService--selectFourTablesBySelectData--error:" + e.getMessage());
		}

		return priceList;
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
				deleteNum = deleteNum + priceDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PriceService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据PriceAndGoodsAndProjectAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/30
	 */
	public int selectCountProjectAndGoods(PriceAndGoodsAndProjectAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = priceDao.selectCountProjectAndGoods(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PriceService--selectCountProjectAndGoods--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据PriceAndGoodsAndProjectAndUser实体联表查询
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
	 * @author ZY on 2018/09/30
	 */
	public List<ProjectAndGoods> selectProjectAndGoods(PriceAndGoodsAndProjectAndUser unionData) {
		List<ProjectAndGoods> priceList = new ArrayList<ProjectAndGoods>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			priceList = priceDao.selectProjectAndGoods(session, unionData);
			
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PriceService--selectProjectAndGoods--error:" + e.getMessage());
		}

		return priceList;
	}
	
}
