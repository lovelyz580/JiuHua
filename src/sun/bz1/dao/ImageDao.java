package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页图片表
 *
 * Dao
 *
 * @author ZY on 2018/11/28
 */

@Repository
public class ImageDao {
    private Logger logger = Logger.getLogger(ImageDao.class);

    public int insert(SqlSession session, Image image) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.insert("sun.bz1.dao.mapper.Image.insert", image);
        } catch (Exception e) {
            logger.error("ImageDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, Image image) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.insert("sun.bz1.dao.mapper.Image.insertSelective", image);
        } catch (Exception e) {
            logger.error("ImageDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, Image image) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.update("sun.bz1.dao.mapper.Image.updateByPrimaryKeySelective", image);
        } catch (Exception e) {
            logger.error("ImageDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, Image image) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.update("sun.bz1.dao.mapper.Image.updateByPrimaryKey", image);
        } catch (Exception e) {
            logger.error("ImageDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.selectOne("sun.bz1.dao.mapper.Image.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("ImageDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public Image selectByPrimaryKey(SqlSession session, Integer id) {
        Image user = new Image();

        try {
            logger.info("ImageDao");

            user = session.selectOne("sun.bz1.dao.mapper.Image.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("ImageDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return user;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.delete("sun.bz1.dao.mapper.Image.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("ImageDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Image实体添加
     *
     * @param image
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int insertByImage(SqlSession session, Image image) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.insert("sun.bz1.dao.mapper.Image.insertByImage", image);
        } catch (Exception e) {
            logger.error("ImageDao--insertByImage--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Image实体更新
     *
     * @param image
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int updateByImage(SqlSession session, Image image) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.update("sun.bz1.dao.mapper.Image.updateByImage", image);
        } catch (Exception e) {
            logger.error("ImageDao--updateByImage--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Image实体查询
     *
     * 查询数量
     *
     * @param image
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int selectCountByImage(SqlSession session, Image image) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.selectOne("sun.bz1.dao.mapper.Image.selectCountByImage", image);
        } catch (Exception e) {
            logger.error("ImageDao--selectCountByImage--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Image实体查询
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
     * @author ZY on 2018/11/28
     */
    public List<Image> selectByImage(SqlSession session, Image image) {
        List<Image> imageList = new ArrayList<Image>();

        try {
            logger.info("ImageDao");

            imageList = session.selectList("sun.bz1.dao.mapper.Image.selectByImage", image);
        } catch (Exception e) {
            logger.error("ImageDao--selectByImage--error:" + e.getMessage());
        }

        return imageList;
    }

    /**
     * 根据Image实体模糊查询
     *
     * 查询数量
     *
     * @param image
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int selectCountBySelectData(SqlSession session, Image image) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.selectOne("sun.bz1.dao.mapper.Image.selectCountBySelectData", image);
        } catch (Exception e) {
            logger.error("ImageDao--selectCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Image实体模糊查询
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
     * @author ZY on 2018/11/28
     */
    public List<Image> selectBySelectData(SqlSession session, Image image) {
        List<Image> imageList = new ArrayList<Image>();

        try {
            logger.info("ImageDao");

            imageList = session.selectList("sun.bz1.dao.mapper.Image.selectBySelectData", image);
        } catch (Exception e) {
            logger.error("ImageDao--selectBySelectData--error:" + e.getMessage());
        }

        return imageList;
    }

    /**
     * 根据Image实体删除
     *
     * @param image
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int deleteByImage(SqlSession session, Image image) {
        int num = 0;

        try {
            logger.info("ImageDao");

            num = session.delete("sun.bz1.dao.mapper.Image.deleteByImage", image);
        } catch (Exception e) {
            logger.error("ImageDao--deleteByImage--error:" + e.getMessage());
        }

        return num;
    }
}