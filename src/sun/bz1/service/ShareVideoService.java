package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.ShareVideoDao;
import sun.bz1.entity.ShareVideo;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享视频表
 * 
 * Service
 * 
 * @author ZY on 2018/11/30
 */

@Service
public class ShareVideoService {

	@Autowired
	private ShareVideoDao shareVideoDao;

	private Logger logger = Logger.getLogger(ShareVideoService.class);

	/**
	 * 根据ShareVideo实体添加
	 * 
	 * @param shareVideo
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	public int insertByShareVideo(ShareVideo shareVideo) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = shareVideoDao.insertByShareVideo(session, shareVideo);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ShareVideoService--insertByShareVideo--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ShareVideo实体更新
	 * 
	 * @param shareVideo
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	public int updateByShareVideo(ShareVideo shareVideo) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = shareVideoDao.updateByShareVideo(session, shareVideo);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ShareVideoService--updateByShareVideo--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ShareVideo实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param shareVideo
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	public int selectCountByShareVideo(ShareVideo shareVideo) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = shareVideoDao.selectCountByShareVideo(session, shareVideo);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ShareVideoService--selectCountByShareVideo--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ShareVideo实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param shareVideo
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	public List<ShareVideo> selectByShareVideo(ShareVideo shareVideo) {
		List<ShareVideo> shareVideoList = new ArrayList<ShareVideo>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			shareVideoList = shareVideoDao.selectByShareVideo(session, shareVideo);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ShareVideoService--selectByShareVideo--error:" + e.getMessage());
		}

		return shareVideoList;
	}

	/**
	 * 根据ShareVideo实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param shareVideo
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	public int selectCountBySelectData(ShareVideo shareVideo) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = shareVideoDao.selectCountBySelectData(session, shareVideo);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ShareVideoService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ShareVideo实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param shareVideo
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	public List<ShareVideo> selectBySelectData(ShareVideo shareVideo) {
		List<ShareVideo> shareVideoList = new ArrayList<ShareVideo>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			shareVideoList = shareVideoDao.selectBySelectData(session, shareVideo);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ShareVideoService--selectBySelectData--error:" + e.getMessage());
		}

		return shareVideoList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + shareVideoDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ShareVideoService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
