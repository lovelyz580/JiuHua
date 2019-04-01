package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.User;
import sun.bz1.entity.UserPayment;
import sun.bz1.entity.UserPaymentAndOrderTableAndUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户付款表
 *
 * Dao
 *
 * @author ZY on 2018/10/20
 */
@Repository
public class UserPaymentDao {
    private Logger logger = Logger.getLogger(UserPaymentDao.class);

    public int insert(SqlSession session, UserPayment userPayment) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.insert("sun.bz1.dao.mapper.UserPayment.insert", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, UserPayment userPayment) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.insert("sun.bz1.dao.mapper.UserPayment.insertSelective", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, UserPayment userPayment) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.update("sun.bz1.dao.mapper.UserPayment.updateByPrimaryKeySelective", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, UserPayment userPayment) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.update("sun.bz1.dao.mapper.UserPayment.updateByPrimaryKey", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.selectOne("sun.bz1.dao.mapper.UserPayment.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("UserPaymentDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public User selectByPrimaryKey(SqlSession session, Integer id) {
        User user = new User();

        try {
            logger.info("UserPaymentDao");

            user = session.selectOne("sun.bz1.dao.mapper.UserPayment.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("UserPaymentDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return user;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.delete("sun.bz1.dao.mapper.UserPayment.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("UserPaymentDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据UserPayment实体添加
     *
     * @param userPayment
     * @return
     *
     * @author ZY on 2018/10/20
     */
    public int insertByUserPayment(SqlSession session, UserPayment userPayment) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.insert("sun.bz1.dao.mapper.UserPayment.insertByUserPayment", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--insertByUserPayment--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据UserPayment实体更新
     *
     * @param userPayment
     * @return
     *
     * @author ZY on 2018/10/20
     */
    public int updateByUserPayment(SqlSession session, UserPayment userPayment) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.update("sun.bz1.dao.mapper.UserPayment.updateByUserPayment", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--updateByUserPayment--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据UserPayment实体查询
     *
     * 查询数量
     *
     * @param userPayment
     * @return
     *
     * @author ZY on 2018/10/20
     */
    public int selectCountByUserPayment(SqlSession session, UserPayment userPayment) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.selectOne("sun.bz1.dao.mapper.UserPayment.selectCountByUserPayment", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--selectCountByUserPayment--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据UserPayment实体查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param userPayment
     * @return
     *
     * @author ZY on 2018/10/20
     */
    public List<UserPayment> selectByUserPayment(SqlSession session, UserPayment userPayment) {
        List<UserPayment> userPaymentList = new ArrayList<UserPayment>();

        try {
            logger.info("UserPaymentDao");

            userPaymentList = session.selectList("sun.bz1.dao.mapper.UserPayment.selectByUserPayment", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--selectByUserPayment--error:" + e.getMessage());
        }

        return userPaymentList;
    }

    /**
     * 根据UserPayment实体查询 创建时间倒序
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param userPayment
     * @return
     *
     * @author ZY on 2018/10/20
     */
    public List<UserPayment> selectByUserPaymentTimeDesc(SqlSession session, UserPayment userPayment) {
        List<UserPayment> userPaymentList = new ArrayList<UserPayment>();

        try {
            logger.info("UserPaymentDao");

            userPaymentList = session.selectList("sun.bz1.dao.mapper.UserPayment.selectByUserPaymentTimeDesc", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--selectByUserPaymentTimeDesc--error:" + e.getMessage());
        }

        return userPaymentList;
    }

    /**
     * 根据UserPayment实体模糊查询
     *
     * 查询数量
     *
     * @param userPayment
     * @return
     *
     * @author ZY on 2018/10/20
     */
    public int selectCountBySelectData(SqlSession session, UserPayment userPayment) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.selectOne("sun.bz1.dao.mapper.UserPayment.selectCountBySelectData", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--selectCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据UserPayment实体模糊查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param userPayment
     * @return
     *
     * @author ZY on 2018/10/20
     */
    public List<UserPayment> selectBySelectData(SqlSession session, UserPayment userPayment) {
        List<UserPayment> userPaymentList = new ArrayList<UserPayment>();

        try {
            logger.info("UserPaymentDao");

            userPaymentList = session.selectList("sun.bz1.dao.mapper.UserPayment.selectBySelectData", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--selectBySelectData--error:" + e.getMessage());
        }

        return userPaymentList;
    }

    /**
     * 根据UserPayment实体删除
     *
     * @param userPayment
     * @return
     *
     * @author ZY on 2018/10/20
     */
    public int deleteByUserPayment(SqlSession session, UserPayment userPayment) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.delete("sun.bz1.dao.mapper.UserPayment.deleteByUserPayment", userPayment);
        } catch (Exception e) {
            logger.error("UserPaymentDao--deleteByUserPayment--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据UserPaymentAndOrderTableAndUser实体联表查询
     *
     * 查询数量
     *
     * @param unionData
     * @return
     *
     * @author WJF on 2018/09/03
     */
    public int selectThreeTablesCountByUnionData(SqlSession session, UserPaymentAndOrderTableAndUser unionData) {
        int num = 0;

        try {
            logger.info("UserPaymentDao");

            num = session.selectOne("sun.bz1.dao.mapper.UserPayment.selectThreeTablesCountByUnionData", unionData);
        } catch (Exception e) {
            logger.error("UserPaymentDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据UserPaymentAndOrderTableAndUser实体联表查询
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
     * @author WJF on 2018/09/03
     */
    public List<UserPaymentAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, UserPaymentAndOrderTableAndUser unionData) {
        List<UserPaymentAndOrderTableAndUser> userList = new ArrayList<UserPaymentAndOrderTableAndUser>();

        try {
            logger.info("UserPaymentDao");

            userList = session.selectList("sun.bz1.dao.mapper.UserPayment.selectThreeTablesByUnionData", unionData);
        } catch (Exception e) {
            logger.error("UserPaymentDao--selectThreeTablesByUnionData--error:" + e.getMessage());
        }

        return userList;
    }
}