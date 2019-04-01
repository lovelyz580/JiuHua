package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.DispatchMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 派单方式表
 *
 * Dao
 *
 * @author ZY on 2018/09/19
 */

@Repository
public class DispatchModeDao {

    private Logger logger = Logger.getLogger(DispatchModeDao.class);

    public int insert(SqlSession session, DispatchMode dispatchMode) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.insert("sun.bz1.dao.mapper.DispatchMode.insert", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, DispatchMode dispatchMode) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.insert("sun.bz1.dao.mapper.DispatchMode.insertSelective", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, DispatchMode dispatchMode) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.update("sun.bz1.dao.mapper.DispatchMode.updateByPrimaryKeySelective", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, DispatchMode dispatchMode) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.update("sun.bz1.dao.mapper.DispatchMode.updateByPrimaryKey", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.selectOne("sun.bz1.dao.mapper.DispatchMode.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("DispatchModeDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public DispatchMode selectByPrimaryKey(SqlSession session, Integer id) {
        DispatchMode user = new DispatchMode();

        try {
            logger.info("DispatchModeDao");

            user = session.selectOne("sun.bz1.dao.mapper.DispatchMode.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("DispatchModeDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return user;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.delete("sun.bz1.dao.mapper.DispatchMode.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("DispatchModeDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DispatchMode实体添加
     *
     * @param dispatchMode
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int insertByDispatchMode(SqlSession session, DispatchMode dispatchMode) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.insert("sun.bz1.dao.mapper.DispatchMode.insertByDispatchMode", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--insertByDispatchMode--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DispatchMode实体更新
     *
     * @param dispatchMode
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int updateByDispatchMode(SqlSession session, DispatchMode dispatchMode) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.update("sun.bz1.dao.mapper.DispatchMode.updateByDispatchMode", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--updateByDispatchMode--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DispatchMode实体查询
     *
     * 查询数量
     *
     * @param dispatchMode
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int selectCountByDispatchMode(SqlSession session, DispatchMode dispatchMode) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.selectOne("sun.bz1.dao.mapper.DispatchMode.selectCountByDispatchMode", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--selectCountByDispatchMode--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DispatchMode实体查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param dispatchMode
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public List<DispatchMode> selectByDispatchMode(SqlSession session, DispatchMode dispatchMode) {
        List<DispatchMode> dispatchModeList = new ArrayList<DispatchMode>();

        try {
            logger.info("DispatchModeDao");

            dispatchModeList = session.selectList("sun.bz1.dao.mapper.DispatchMode.selectByDispatchMode", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--selectByDispatchMode--error:" + e.getMessage());
        }

        return dispatchModeList;
    }

    /**
     * 根据DispatchMode实体模糊查询
     *
     * 查询数量
     *
     * @param dispatchMode
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int selectCountBySelectData(SqlSession session, DispatchMode dispatchMode) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.selectOne("sun.bz1.dao.mapper.DispatchMode.selectCountBySelectData", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--selectCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据DispatchMode实体模糊查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param dispatchMode
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public List<DispatchMode> selectBySelectData(SqlSession session, DispatchMode dispatchMode) {
        List<DispatchMode> dispatchModeList = new ArrayList<DispatchMode>();

        try {
            logger.info("DispatchModeDao");

            dispatchModeList = session.selectList("sun.bz1.dao.mapper.DispatchMode.selectBySelectData", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--selectBySelectData--error:" + e.getMessage());
        }

        return dispatchModeList;
    }

    /**
     * 根据DispatchMode实体删除
     *
     * @param dispatchMode
     * @return
     *
     * @author ZY on 2018/09/19
     */
    public int deleteByDispatchMode(SqlSession session, DispatchMode dispatchMode) {
        int num = 0;

        try {
            logger.info("DispatchModeDao");

            num = session.delete("sun.bz1.dao.mapper.DispatchMode.deleteByDispatchMode", dispatchMode);
        } catch (Exception e) {
            logger.error("DispatchModeDao--deleteByDispatchMode--error:" + e.getMessage());
        }

        return num;
    }
}