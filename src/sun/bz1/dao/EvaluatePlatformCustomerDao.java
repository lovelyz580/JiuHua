package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.EvaluateCustomerAndOrderTableAndUser;
import sun.bz1.entity.EvaluatePlatformCustomer;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修人员评价平台_客户表
 *
 * Dao
 *
 * @author ZY on 2019/01/21
 */
@Repository
public class EvaluatePlatformCustomerDao {
    private Logger logger = Logger.getLogger(EvaluatePlatformCustomerDao.class);

    public int insert(SqlSession session, EvaluatePlatformCustomer evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.insert("sun.bz1.dao.mapper.EvaluatePlatformCustomer.insert", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, EvaluatePlatformCustomer evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.insert("sun.bz1.dao.mapper.EvaluatePlatformCustomer.insertSelective", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, EvaluatePlatformCustomer evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.update("sun.bz1.dao.mapper.EvaluatePlatformCustomer.updateByPrimaryKeySelective", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, EvaluatePlatformCustomer evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.update("sun.bz1.dao.mapper.EvaluatePlatformCustomer.updateByPrimaryKey", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.selectOne("sun.bz1.dao.mapper.EvaluatePlatformCustomer.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public EvaluatePlatformCustomer selectByPrimaryKey(SqlSession session, Integer id) {
        EvaluatePlatformCustomer evaluate = new EvaluatePlatformCustomer();

        try {
            logger.info("EvaluatePlatformCustomerDao");

            evaluate = session.selectOne("sun.bz1.dao.mapper.EvaluatePlatformCustomer.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return evaluate;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.delete("sun.bz1.dao.mapper.EvaluatePlatformCustomer.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluatePlatformCustomer实体添加
     *
     * @param evaluate
     * @return
     *
     * @author WJF on 2018/09/05
     */
    public int insertByEvaluatePlatformCustomer(SqlSession session, EvaluatePlatformCustomer evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.insert("sun.bz1.dao.mapper.EvaluatePlatformCustomer.insertByEvaluatePlatformCustomer", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--insertByEvaluatePlatformCustomer--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluatePlatformCustomer实体更新
     *
     * @param evaluate
     * @return
     *
     * @author WJF on 2018/09/05
     */
    public int updateByEvaluatePlatformCustomer(SqlSession session, EvaluatePlatformCustomer evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.update("sun.bz1.dao.mapper.EvaluatePlatformCustomer.updateByEvaluatePlatformCustomer", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--updateByEvaluatePlatformCustomer--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluatePlatformCustomerAndOrderTableAndUser实体联表查询
     *
     * 查询数量
     *
     * @param unionData
     * @return
     *
     * @author WJF on 2018/09/05
     */
    public int selectThreeTablesCountByUnionData(SqlSession session, EvaluateCustomerAndOrderTableAndUser unionData) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomer.selectThreeTablesCountByUnionData", unionData);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluateCustomerAndOrderTableAndUser实体联表查询
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
    public List<EvaluateCustomerAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, EvaluateCustomerAndOrderTableAndUser unionData) {
        List<EvaluateCustomerAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateCustomerAndOrderTableAndUser>();

        try {
            logger.info("EvaluatePlatformCustomerDao");

            evaluateList = session.selectList("sun.bz1.dao.mapper.EvaluateCustomer.selectThreeTablesByUnionData", unionData);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--selectThreeTablesByUnionData--error:" + e.getMessage());
        }

        return evaluateList;
    }

    /**
     * 根据EvaluateCustomerAndOrderTableAndUser实体联表模糊查询
     *
     * 查询数量
     *
     * @param unionData
     * @return
     *
     * @author WJF on 2018/09/05
     */
    public int selectThreeTablesCountBySelectData(SqlSession session, EvaluateCustomerAndOrderTableAndUser unionData) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.selectOne("sun.bz1.dao.mapper.EvaluateCustomer.selectThreeTablesCountBySelectData", unionData);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluateCustomerAndOrderTableAndUser实体联表模糊查询
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
    public List<EvaluateCustomerAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, EvaluateCustomerAndOrderTableAndUser unionData) {
        List<EvaluateCustomerAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateCustomerAndOrderTableAndUser>();

        try {
            logger.info("EvaluatePlatformCustomerDao");

            evaluateList = session.selectList("sun.bz1.dao.mapper.EvaluateCustomer.selectThreeTablesBySelectData", unionData);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--selectThreeTablesBySelectData--error:" + e.getMessage());
        }

        return evaluateList;
    }

    /**
     * 根据EvaluatePlatformCustomer实体删除
     *
     * @param evaluate
     * @return
     *
     * @author WJF on 2018/09/19
     */
    public int deleteByEvaluatePlatformCustomer(SqlSession session, EvaluatePlatformCustomer evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformCustomerDao");

            num = session.delete("sun.bz1.dao.mapper.EvaluatePlatformCustomer.deleteByEvaluatePlatformCustomer", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformCustomerDao--deleteByEvaluatePlatformCustomer--error:" + e.getMessage());
        }

        return num;
    }
}