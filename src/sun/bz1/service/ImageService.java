package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.ImageDao;
import sun.bz1.entity.Image;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页图片表
 * 
 * Service
 * 
 * @author ZY on 2018/11/29
 */

@Service
public class ImageService {

	@Autowired
	private ImageDao imageDao;

	private Logger logger = Logger.getLogger(ImageService.class);

	/**
	 * 根据Image实体添加
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/29
	 */
	public int insertByImage(Image image) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = imageDao.insertByImage(session, image);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ImageService--insertByImage--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Image实体更新
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/29
	 */
	public int updateByImage(Image image) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = imageDao.updateByImage(session, image);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ImageService--updateByImage--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Image实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/29
	 */
	public int selectCountByImage(Image image) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = imageDao.selectCountByImage(session, image);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ImageService--selectCountByImage--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Image实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/29
	 */
	public List<Image> selectByImage(Image image) {
		List<Image> imageList = new ArrayList<Image>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			imageList = imageDao.selectByImage(session, image);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ImageService--selectByImage--error:" + e.getMessage());
		}

		return imageList;
	}

	/**
	 * 根据Image实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/29
	 */
	public int selectCountBySelectData(Image image) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = imageDao.selectCountBySelectData(session, image);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ImageService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Image实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/29
	 */
	public List<Image> selectBySelectData(Image image) {
		List<Image> imageList = new ArrayList<Image>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			imageList = imageDao.selectBySelectData(session, image);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ImageService--selectBySelectData--error:" + e.getMessage());
		}

		return imageList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/11/29
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + imageDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ImageService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
