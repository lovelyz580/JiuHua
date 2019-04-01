package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.DistributeConfirm;
import sun.bz1.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 派单确认表
 *
 * Dao
 *
 * @author ZY on 2018/11/22
 */
@Repository
public class DistributeConfirmDao {
    private Logger logger = Logger.getLogger(DistributeConfirmDao.class);

    public int insert(SqlSession session, DistributeConfirm distributeConfirm) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.insert("sun.bz1.dao.mapper.DistributeConfirm.insert", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, DistributeConfirm distributeConfirm) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.insert("sun.bz1.dao.mapper.DistributeConfirm.insertSelective", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, DistributeConfirm distributeConfirm) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.update("sun.bz1.dao.mapper.DistributeConfirm.updateByPrimaryKeySelective", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, DistributeConfirm distributeConfirm) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.update("sun.bz1.dao.mapper.DistributeConfirm.updateByPrimaryKey", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.selectOne("sun.bz1.dao.mapper.DistributeConfirm.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public User selectByPrimaryKey(SqlSession session, Integer id) {
        User user = new User();

        try {
            logger.info("DistributeConfirmDao");

            user = session.selectOne("sun.bz1.dao.mapper.DistributeConfirm.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return user;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.delete("sun.bz1.dao.mapper.DistributeConfirm.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DistributeConfirm实体添加
     *
     * @param distributeConfirm
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int insertByDistributeConfirm(SqlSession session, DistributeConfirm distributeConfirm) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.insert("sun.bz1.dao.mapper.DistributeConfirm.insertByDistributeConfirm", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--insertByDistributeConfirm--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DistributeConfirm实体更新
     *
     * @param distributeConfirm
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int updateByDistributeConfirm(SqlSession session, DistributeConfirm distributeConfirm) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.update("sun.bz1.dao.mapper.DistributeConfirm.updateByDistributeConfirm", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--updateByDistributeConfirm--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DistributeConfirm实体查询
     *
     * 查询数量
     *
     * @param distributeConfirm
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int selectCountByDistributeConfirm(SqlSession session, DistributeConfirm distributeConfirm) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.selectOne("sun.bz1.dao.mapper.DistributeConfirm.selectCountByDistributeConfirm", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--selectCountByDistributeConfirm--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DistributeConfirm实体查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param distributeConfirm
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public List<DistributeConfirm> selectByDistributeConfirm(SqlSession session, DistributeConfirm distributeConfirm) {
        List<DistributeConfirm> distributeConfirmList = new ArrayList<DistributeConfirm>();

        try {
            logger.info("DistributeConfirmDao");

            distributeConfirmList = session.selectList("sun.bz1.dao.mapper.DistributeConfirm.selectByDistributeConfirm", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--selectByDistributeConfirm--error:" + e.getMessage());
        }

        return distributeConfirmList;
    }

    /**
     * 根据DistributeConfirm实体模糊查询
     *
     * 查询数量
     *
     * @param distributeConfirm
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int selectCountBySelectData(SqlSession session, DistributeConfirm distributeConfirm) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.selectOne("sun.bz1.dao.mapper.DistributeConfirm.selectCountBySelectData", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--selectCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DistributeConfirm实体模糊查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param distributeConfirm
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public List<DistributeConfirm> selectBySelectData(SqlSession session, DistributeConfirm distributeConfirm) {
        List<DistributeConfirm> distributeConfirmList = new ArrayList<DistributeConfirm>();

        try {
            logger.info("DistributeConfirmDao");

            distributeConfirmList = session.selectList("sun.bz1.dao.mapper.DistributeConfirm.selectBySelectData", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--selectBySelectData--error:" + e.getMessage());
        }

        return distributeConfirmList;
    }

    /**
     * 根据DistributeConfirm实体删除
     *
     * @param distributeConfirm
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int deleteByDistributeConfirm(SqlSession session, DistributeConfirm distributeConfirm) {
        int num = 0;

        try {
            logger.info("DistributeConfirmDao");

            num = session.delete("sun.bz1.dao.mapper.DistributeConfirm.deleteByDistributeConfirm", distributeConfirm);
        } catch (Exception e) {
            logger.error("DistributeConfirmDao--deleteByDistributeConfirm--error:" + e.getMessage());
        }

        return num;
    }
}