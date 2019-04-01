package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Price;
import sun.bz1.entity.PriceAndGoodsAndProjectAndUser;
import sun.bz1.entity.ProjectAndGoods;

/**
 * 维修单价表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/05
 */

@Repository
public class PriceDao {

	private Logger logger = Logger.getLogger(PriceDao.class);

	public int insert(SqlSession session, Price price) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.insert("sun.bz1.dao.mapper.Price.insert", price);
		} catch (Exception e) {
			logger.error("PriceDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Price price) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.insert("sun.bz1.dao.mapper.Price.insertSelective", price);
		} catch (Exception e) {
			logger.error("PriceDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Price price) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.update("sun.bz1.dao.mapper.Price.updateByPrimaryKeySelective", price);
		} catch (Exception e) {
			logger.error("PriceDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Price price) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.update("sun.bz1.dao.mapper.Price.updateByPrimaryKey", price);
		} catch (Exception e) {
			logger.error("PriceDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public int selectCountByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.selectOne("sun.bz1.dao.mapper.Price.selectCountByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("PriceDao--selectCountByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Price selectByPrimaryKey(SqlSession session, Integer id) {
		Price price = new Price();

		try {
			logger.info("PriceDao");

			price = session.selectOne("sun.bz1.dao.mapper.Price.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("PriceDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return price;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.delete("sun.bz1.dao.mapper.Price.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("PriceDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Price实体添加
	 * 
	 * @param price
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	public int insertByPrice(SqlSession session, Price price) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.insert("sun.bz1.dao.mapper.Price.insertByPrice", price);
		} catch (Exception e) {
			logger.error("PriceDao--insertByPrice--error:" + e.getMessage());
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
	public int updateByPrice(SqlSession session, Price price) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.update("sun.bz1.dao.mapper.Price.updateByPrice", price);
		} catch (Exception e) {
			logger.error("PriceDao--updateByPrice--error:" + e.getMessage());
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
	public int selectFourTablesCountByUnionData(SqlSession session, PriceAndGoodsAndProjectAndUser unionData) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.selectOne("sun.bz1.dao.mapper.Price.selectFourTablesCountByUnionData", unionData);
		} catch (Exception e) {
			logger.error("PriceDao--selectFourTablesCountByUnionData--error:" + e.getMessage());
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
	public List<PriceAndGoodsAndProjectAndUser> selectFourTablesByUnionData(SqlSession session, PriceAndGoodsAndProjectAndUser unionData) {
		List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();

		try {
			logger.info("PriceDao");

			priceList = session.selectList("sun.bz1.dao.mapper.Price.selectFourTablesByUnionData", unionData);
		} catch (Exception e) {
			logger.error("PriceDao--selectFourTablesByUnionData--error:" + e.getMessage());
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
	public int selectFourTablesCountBySelectData(SqlSession session, PriceAndGoodsAndProjectAndUser unionData) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.selectOne("sun.bz1.dao.mapper.Price.selectFourTablesCountBySelectData", unionData);
		} catch (Exception e) {
			logger.error("PriceDao--selectFourTablesCountBySelectData--error:" + e.getMessage());
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
	public List<PriceAndGoodsAndProjectAndUser> selectFourTablesBySelectData(SqlSession session, PriceAndGoodsAndProjectAndUser unionData) {
		List<PriceAndGoodsAndProjectAndUser> priceList = new ArrayList<PriceAndGoodsAndProjectAndUser>();

		try {
			logger.info("PriceDao");

			priceList = session.selectList("sun.bz1.dao.mapper.Price.selectFourTablesBySelectData", unionData);
		} catch (Exception e) {
			logger.error("PriceDao--selectFourTablesBySelectData--error:" + e.getMessage());
		}

		return priceList;
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
	public int selectCountProjectAndGoods(SqlSession session, PriceAndGoodsAndProjectAndUser unionData) {
		int num = 0;

		try {
			logger.info("PriceDao");

			num = session.selectOne("sun.bz1.dao.mapper.Price.selectCountProjectAndGoods", unionData);
		} catch (Exception e) {
			logger.error("PriceDao--selectCountProjectAndGoods--error:" + e.getMessage());
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
	public List<ProjectAndGoods> selectProjectAndGoods(SqlSession session, PriceAndGoodsAndProjectAndUser unionData) {
		List<ProjectAndGoods> priceList = new ArrayList<ProjectAndGoods>();

		try {
			logger.info("PriceDao");

			priceList = session.selectList("sun.bz1.dao.mapper.Price.selectProjectAndGoods", unionData);
		} catch (Exception e) {
			logger.error("PriceDao--selectProjectAndGoods--error:" + e.getMessage());
		}

		return priceList;
	}

}