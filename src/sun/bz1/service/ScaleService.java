package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.ScaleDao;
import sun.bz1.entity.Scale;
import util.MyBatisUtil;

/**
 * 接单规模表
 * 
 * Service
 * 
 * @author WJF on 2018/09/04
 */

@Service
public class ScaleService {

	@Autowired
	private ScaleDao scaleDao;

	private Logger logger = Logger.getLogger(ScaleService.class);
	
	/**
   	 * 根据Scale实体添加
   	 * 
   	 * @param scale
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/04
   	 */
	public int insertByScale(Scale scale) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = scaleDao.insertByScale(session, scale);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ScaleService--insertByScale--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Scale实体更新
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateByScale(Scale scale) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = scaleDao.updateByScale(session, scale);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ScaleService--updateByScale--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Scale实体查询
	 * 
   	 * 查询数量
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByScale(Scale scale) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = scaleDao.selectCountByScale(session, scale);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ScaleService--selectCountByScale--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Scale实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Scale> selectByScale(Scale scale) {
		List<Scale> scaleList = new ArrayList<Scale>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			scaleList = scaleDao.selectByScale(session, scale);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ScaleService--selectByScale--error:" + e.getMessage());
		}

		return scaleList;
	}

	/**
	 * 根据Scale实体模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int selectCountBySelectData(Scale scale) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = scaleDao.selectCountBySelectData(session, scale);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ScaleService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Scale实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param scaleDao
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<Scale> selectBySelectData(Scale scale) {
		List<Scale> scaleList = new ArrayList<Scale>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			scaleList = scaleDao.selectBySelectData(session, scale);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ScaleService--selectBySelectData--error:" + e.getMessage());
		}

		return scaleList;
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
				deleteNum = deleteNum + scaleDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ScaleService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
