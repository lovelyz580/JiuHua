package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.EvaluatePlatformService;
import sun.bz1.entity.EvaluateServiceAndOrderTableAndUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户评价平台_维修人员表
 *
 * Dao
 *
 * @author ZY on 2019/01/21
 */
@Repository
public class EvaluatePlatformServiceDao {
    private Logger logger = Logger.getLogger(EvaluatePlatformServiceDao.class);

    public int insert(SqlSession session, EvaluatePlatformService evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.insert("sun.bz1.dao.mapper.EvaluatePlatformService.insert", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, EvaluatePlatformService evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.insert("sun.bz1.dao.mapper.EvaluatePlatformService.insertSelective", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, EvaluatePlatformService evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.update("sun.bz1.dao.mapper.EvaluatePlatformService.updateByPrimaryKeySelective", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, EvaluatePlatformService evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.update("sun.bz1.dao.mapper.EvaluatePlatformService.updateByPrimaryKey", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.selectOne("sun.bz1.dao.mapper.EvaluatePlatformService.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public EvaluatePlatformService selectByPrimaryKey(SqlSession session, Integer id) {
        EvaluatePlatformService evaluate = new EvaluatePlatformService();

        try {
            logger.info("EvaluatePlatformServiceDao");

            evaluate = session.selectOne("sun.bz1.dao.mapper.EvaluatePlatformService.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return evaluate;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.delete("sun.bz1.dao.mapper.EvaluatePlatformService.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluatePlatformService实体添加
     *
     * @param evaluate
     * @return
     *
     * @author ZY on 2019/01/21
     */
    public int insertByEvaluatePlatformService(SqlSession session, EvaluatePlatformService evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.insert("sun.bz1.dao.mapper.EvaluatePlatformService.insertByEvaluatePlatformService", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--insertByEvaluatePlatformService--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluatePlatformService实体更新
     *
     * @param evaluate
     * @return
     *
     * @author ZY on 2019/01/21
     */
    public int updateByEvaluatePlatformService(SqlSession session, EvaluatePlatformService evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.update("sun.bz1.dao.mapper.EvaluatePlatformService.updateByEvaluatePlatformService", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--updateByEvaluatePlatformService--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluatePlatformServiceAndOrderTableAndUser实体联表查询
     *
     * 查询数量
     *
     * @param unionData
     * @return
     *
     * @author ZY on 2019/01/21
     */
    public int selectThreeTablesCountByUnionData(SqlSession session, EvaluateServiceAndOrderTableAndUser unionData) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.selectOne("sun.bz1.dao.mapper.EvaluateService.selectThreeTablesCountByUnionData", unionData);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluateServiceAndOrderTableAndUser实体联表查询
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
     * @author ZY on 2019/01/21
     */
    public List<EvaluateServiceAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, EvaluateServiceAndOrderTableAndUser unionData) {
        List<EvaluateServiceAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateServiceAndOrderTableAndUser>();

        try {
            logger.info("EvaluatePlatformServiceDao");

            evaluateList = session.selectList("sun.bz1.dao.mapper.EvaluateService.selectThreeTablesByUnionData", unionData);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--selectThreeTablesByUnionData--error:" + e.getMessage());
        }

        return evaluateList;
    }

    /**
     * 根据EvaluatePlatformServiceAndOrderTableAndUser实体联表模糊查询
     *
     * 查询数量
     *
     * @param unionData
     * @return
     *
     * @author ZY on 2019/01/21
     */
    public int selectThreeTablesCountBySelectData(SqlSession session, EvaluateServiceAndOrderTableAndUser unionData) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.selectOne("sun.bz1.dao.mapper.EvaluateService.selectThreeTablesCountBySelectData", unionData);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据EvaluateServiceAndOrderTableAndUser实体联表模糊查询
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
     * @author ZY on 2019/01/21
     */
    public List<EvaluateServiceAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, EvaluateServiceAndOrderTableAndUser unionData) {
        List<EvaluateServiceAndOrderTableAndUser> evaluateList = new ArrayList<EvaluateServiceAndOrderTableAndUser>();

        try {
            logger.info("EvaluatePlatformServiceDao");

            evaluateList = session.selectList("sun.bz1.dao.mapper.EvaluateService.selectThreeTablesBySelectData", unionData);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--selectThreeTablesBySelectData--error:" + e.getMessage());
        }

        return evaluateList;
    }

    /**
     * 根据EvaluatePlatformService实体删除
     *
     * @param evaluate
     * @return
     *
     * @author WJF on 2018/09/19
     */
    public int deleteByEvaluatePlatformService(SqlSession session, EvaluatePlatformService evaluate) {
        int num = 0;

        try {
            logger.info("EvaluatePlatformServiceDao");

            num = session.delete("sun.bz1.dao.mapper.EvaluatePlatformService.deleteByEvaluatePlatformService", evaluate);
        } catch (Exception e) {
            logger.error("EvaluatePlatformServiceDao--deleteByEvaluatePlatformService--error:" + e.getMessage());
        }

        return num;
    }
}