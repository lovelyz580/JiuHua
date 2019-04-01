package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Goods;

/**
 * 产品表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/04
 */

@Repository
public class GoodsDao {

	private Logger logger = Logger.getLogger(GoodsDao.class);

	public int insert(SqlSession session, Goods goods) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.insert("sun.bz1.dao.mapper.Goods.insert", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Goods goods) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.insert("sun.bz1.dao.mapper.Goods.insertSelective", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Goods goods) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.update("sun.bz1.dao.mapper.Goods.updateByPrimaryKeySelective", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Goods goods) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.update("sun.bz1.dao.mapper.Goods.updateByPrimaryKey", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.selectOne("sun.bz1.dao.mapper.Goods.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("GoodsDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Goods selectByPrimaryKey(SqlSession session, Integer id) {
		Goods goods = new Goods();

		try {
			logger.info("GoodsDao");

			goods = session.selectOne("sun.bz1.dao.mapper.Goods.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("GoodsDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return goods;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.delete("sun.bz1.dao.mapper.Goods.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("GoodsDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Goods实体添加
	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int insertByGoods(SqlSession session, Goods goods) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.insert("sun.bz1.dao.mapper.Goods.insertByGoods", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--insertByGoods--error:" + e.getMessage());
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
	public int updateByGoods(SqlSession session, Goods goods) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.update("sun.bz1.dao.mapper.Goods.updateByGoods", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--updateByGoods--error:" + e.getMessage());
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
	public int selectCountByGoods(SqlSession session, Goods goods) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.selectOne("sun.bz1.dao.mapper.Goods.selectCountByGoods", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--selectCountByGoods--error:" + e.getMessage());
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
	public List<Goods> selectByGoods(SqlSession session, Goods goods) {
		List<Goods> goodsList = new ArrayList<Goods>();

		try {
			logger.info("GoodsDao");

			goodsList = session.selectList("sun.bz1.dao.mapper.Goods.selectByGoods", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--selectByGoods--error:" + e.getMessage());
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
	public int selectCountBySelectData(SqlSession session, Goods goods) {
		int num = 0;

		try {
			logger.info("GoodsDao");

			num = session.selectOne("sun.bz1.dao.mapper.Goods.selectCountBySelectData", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--selectCountBySelectData--error:" + e.getMessage());
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
	public List<Goods> selectBySelectData(SqlSession session, Goods goods) {
		List<Goods> goodsList = new ArrayList<Goods>();

		try {
			logger.info("GoodsDao");

			goodsList = session.selectList("sun.bz1.dao.mapper.Goods.selectBySelectData", goods);
		} catch (Exception e) {
			logger.error("GoodsDao--selectBySelectData--error:" + e.getMessage());
		}

		return goodsList;
	}
	
}