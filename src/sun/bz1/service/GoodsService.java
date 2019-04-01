package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.GoodsDao;
import sun.bz1.entity.Goods;
import util.MyBatisUtil;

/**
 * 产品表
 * 
 * Service
 * 
 * @author WJF on 2018/09/04
 */

@Service
public class GoodsService {
	
	@Autowired
	private GoodsDao goodsDao;

	private Logger logger = Logger.getLogger(GoodsService.class);
	
	/**
   	 * 根据Goods实体添加
   	 * 
   	 * @param goods
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/04
   	 */
	public int insertByGoods(Goods goods) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = goodsDao.insertByGoods(session, goods);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("GoodsService--insertByGoods--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Goods实体更新
	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateByGoods(Goods goods) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = goodsDao.updateByGoods(session, goods);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("GoodsService--updateByGoods--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Goods实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByGoods(Goods goods) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = goodsDao.selectCountByGoods(session, goods);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("GoodsService--selectCountByGoods--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Goods实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Goods> selectByGoods(Goods goods) {
		List<Goods> goodsList = new ArrayList<Goods>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			goodsList = goodsDao.selectByGoods(session, goods);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("GoodsService--selectByGoods--error:" + e.getMessage());
		}

		return goodsList;
	}

	/**
	 * 根据Goods实体模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectData(Goods goods) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = goodsDao.selectCountBySelectData(session, goods);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("GoodsService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Goods实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Goods> selectBySelectData(Goods goods) {
		List<Goods> goodsList = new ArrayList<Goods>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			goodsList = goodsDao.selectBySelectData(session, goods);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("GoodsService--selectBySelectData--error:" + e.getMessage());
		}

		return goodsList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + goodsDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("GoodsService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
