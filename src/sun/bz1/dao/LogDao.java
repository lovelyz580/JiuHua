package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.Log;
import sun.bz1.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志表
 *
 * Dao
 *
 * @author ZY on 2018/11/28
 */

@Repository
public class LogDao {
    private Logger logger = Logger.getLogger(LogDao.class);

    public int insert(SqlSession session, Log log) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.insert("sun.bz1.dao.mapper.Log.insert", log);
        } catch (Exception e) {
            logger.error("LogDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, Log log) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.insert("sun.bz1.dao.mapper.Log.insertSelective", log);
        } catch (Exception e) {
            logger.error("LogDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, Log log) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.update("sun.bz1.dao.mapper.Log.updateByPrimaryKeySelective", log);
        } catch (Exception e) {
            logger.error("LogDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, Log log) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.update("sun.bz1.dao.mapper.Log.updateByPrimaryKey", log);
        } catch (Exception e) {
            logger.error("LogDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.selectOne("sun.bz1.dao.mapper.Log.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("LogDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public User selectByPrimaryKey(SqlSession session, Integer id) {
        User user = new User();

        try {
            logger.info("LogDao");

            user = session.selectOne("sun.bz1.dao.mapper.Log.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("LogDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return user;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.delete("sun.bz1.dao.mapper.Log.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("LogDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Log实体添加
     *
     * @param log
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int insertByLog(SqlSession session, Log log) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.insert("sun.bz1.dao.mapper.Log.insertByLog", log);
        } catch (Exception e) {
            logger.error("LogDao--insertByLog--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Log实体更新
     *
     * @param log
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int updateByLog(SqlSession session, Log log) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.update("sun.bz1.dao.mapper.Log.updateByLog", log);
        } catch (Exception e) {
            logger.error("LogDao--updateByLog--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Log实体查询
     *
     * 查询数量
     *
     * @param log
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int selectCountByLog(SqlSession session, Log log) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.selectOne("sun.bz1.dao.mapper.Log.selectCountByLog", log);
        } catch (Exception e) {
            logger.error("LogDao--selectCountByLog--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Log实体查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param log
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public List<Log> selectByLog(SqlSession session, Log log) {
        List<Log> logList = new ArrayList<Log>();

        try {
            logger.info("LogDao");

            logList = session.selectList("sun.bz1.dao.mapper.Log.selectByLog", log);
        } catch (Exception e) {
            logger.error("LogDao--selectByLog--error:" + e.getMessage());
        }

        return logList;
    }

    /**
     * 根据Log实体模糊查询
     *
     * 查询数量
     *
     * @param log
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int selectCountBySelectData(SqlSession session, Log log) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.selectOne("sun.bz1.dao.mapper.Log.selectCountBySelectData", log);
        } catch (Exception e) {
            logger.error("LogDao--selectCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据Log实体模糊查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param log
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public List<Log> selectBySelectData(SqlSession session, Log log) {
        List<Log> logList = new ArrayList<Log>();

        try {
            logger.info("LogDao");

            logList = session.selectList("sun.bz1.dao.mapper.Log.selectBySelectData", log);
        } catch (Exception e) {
            logger.error("LogDao--selectBySelectData--error:" + e.getMessage());
        }

        return logList;
    }

    /**
     * 根据Log实体删除
     *
     * @param log
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int deleteByLog(SqlSession session, Log log) {
        int num = 0;

        try {
            logger.info("LogDao");

            num = session.delete("sun.bz1.dao.mapper.Log.deleteByLog", log);
        } catch (Exception e) {
            logger.error("LogDao--deleteByLog--error:" + e.getMessage());
        }

        return num;
    }

}