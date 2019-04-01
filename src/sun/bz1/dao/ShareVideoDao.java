package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.ShareVideo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享视频表
 *
 * Dao
 *
 * @author ZY on 2018/11/30
 */
@Repository
public class ShareVideoDao {
    private Logger logger = Logger.getLogger(ShareVideoDao.class);

    public int insert(SqlSession session, ShareVideo shareVideo) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.insert("sun.bz1.dao.mapper.ShareVideo.insert", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, ShareVideo shareVideo) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.insert("sun.bz1.dao.mapper.ShareVideo.insertSelective", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, ShareVideo shareVideo) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.update("sun.bz1.dao.mapper.ShareVideo.updateByPrimaryKeySelective", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, ShareVideo shareVideo) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.update("sun.bz1.dao.mapper.ShareVideo.updateByPrimaryKey", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.selectOne("sun.bz1.dao.mapper.ShareVideo.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("ShareVideoDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public ShareVideo selectByPrimaryKey(SqlSession session, Integer id) {
        ShareVideo user = new ShareVideo();

        try {
            logger.info("ShareVideoDao");

            user = session.selectOne("sun.bz1.dao.mapper.ShareVideo.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("ShareVideoDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return user;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.delete("sun.bz1.dao.mapper.ShareVideo.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("ShareVideoDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据ShareVideo实体添加
     *
     * @param shareVideo
     * @return
     *
     * @author ZY on 2018/11/30
     */
    public int insertByShareVideo(SqlSession session, ShareVideo shareVideo) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.insert("sun.bz1.dao.mapper.ShareVideo.insertByShareVideo", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--insertByShareVideo--error:" + e.getMessage());
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
    public int updateByShareVideo(SqlSession session, ShareVideo shareVideo) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.update("sun.bz1.dao.mapper.ShareVideo.updateByShareVideo", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--updateByShareVideo--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据ShareVideo实体查询
     *
     * 查询数量
     *
     * @param shareVideo
     * @return
     *
     * @author ZY on 2018/11/30
     */
    public int selectCountByShareVideo(SqlSession session, ShareVideo shareVideo) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.selectOne("sun.bz1.dao.mapper.ShareVideo.selectCountByShareVideo", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--selectCountByShareVideo--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据ShareVideo实体查询
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
    public List<ShareVideo> selectByShareVideo(SqlSession session, ShareVideo shareVideo) {
        List<ShareVideo> shareVideoList = new ArrayList<ShareVideo>();

        try {
            logger.info("ShareVideoDao");

            shareVideoList = session.selectList("sun.bz1.dao.mapper.ShareVideo.selectByShareVideo", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--selectByShareVideo--error:" + e.getMessage());
        }

        return shareVideoList;
    }

    /**
     * 根据ShareVideo实体模糊查询
     *
     * 查询数量
     *
     * @param shareVideo
     * @return
     *
     * @author ZY on 2018/11/30
     */
    public int selectCountBySelectData(SqlSession session, ShareVideo shareVideo) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.selectOne("sun.bz1.dao.mapper.ShareVideo.selectCountBySelectData", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--selectCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据ShareVideo实体模糊查询
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
    public List<ShareVideo> selectBySelectData(SqlSession session, ShareVideo shareVideo) {
        List<ShareVideo> shareVideoList = new ArrayList<ShareVideo>();

        try {
            logger.info("ShareVideoDao");

            shareVideoList = session.selectList("sun.bz1.dao.mapper.ShareVideo.selectBySelectData", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--selectBySelectData--error:" + e.getMessage());
        }

        return shareVideoList;
    }

    /**
     * 根据ShareVideo实体删除
     *
     * @param shareVideo
     * @return
     *
     * @author ZY on 2018/11/30
     */
    public int deleteByShareVideo(SqlSession session, ShareVideo shareVideo) {
        int num = 0;

        try {
            logger.info("ShareVideoDao");

            num = session.delete("sun.bz1.dao.mapper.ShareVideo.deleteByShareVideo", shareVideo);
        } catch (Exception e) {
            logger.error("ShareVideoDao--deleteByShareVideo--error:" + e.getMessage());
        }

        return num;
    }
}