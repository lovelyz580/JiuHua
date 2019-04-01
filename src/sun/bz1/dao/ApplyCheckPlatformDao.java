package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.ApplyCheckAndOrderTableAndUser;
import sun.bz1.entity.ApplyCheckPlatform;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修验收平台_维修人员表(验收成功或失败时在该表中添加数据)
 *
 * Dao
 *
 * @author ZY on 2019/01/22
 */
@Repository
public class ApplyCheckPlatformDao {
    private Logger logger = Logger.getLogger(ApplyCheckPlatformDao.class);

    public int insert(SqlSession session, ApplyCheckPlatform applyCheckPlatform) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.insert("sun.bz1.dao.mapper.ApplyCheckPlatform.insert", applyCheckPlatform);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, ApplyCheckPlatform applyCheckPlatform) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.insert("sun.bz1.dao.mapper.ApplyCheckPlatform.insertSelective", applyCheckPlatform);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, ApplyCheckPlatform applyCheckPlatform) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.update("sun.bz1.dao.mapper.ApplyCheckPlatform.updateByPrimaryKeySelective", applyCheckPlatform);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, ApplyCheckPlatform applyCheckPlatform) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.update("sun.bz1.dao.mapper.ApplyCheckPlatform.updateByPrimaryKey", applyCheckPlatform);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.selectOne("sun.bz1.dao.mapper.ApplyCheckPlatform.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public ApplyCheckPlatform selectByPrimaryKey(SqlSession session, Integer id) {
        ApplyCheckPlatform applyCheckPlatform = new ApplyCheckPlatform();

        try {
            logger.info("ApplyCheckPlatformDao");

            applyCheckPlatform = session.selectOne("sun.bz1.dao.mapper.ApplyCheckPlatform.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return applyCheckPlatform;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.delete("sun.bz1.dao.mapper.ApplyCheckPlatform.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据ApplyCheckPlatform实体添加
     *
     * @param applyCheckPlatform
     * @return
     *
     * @author ZY on 2019/01/22
     */
    public int insertByApplyCheckPlatform(SqlSession session, ApplyCheckPlatform applyCheckPlatform) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.insert("sun.bz1.dao.mapper.ApplyCheckPlatform.insertByApplyCheckPlatform", applyCheckPlatform);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--insertByApplyCheckPlatform--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据ApplyCheckPlatform实体更新
     *
     * @param applyCheckPlatform
     * @return
     *
     * @author ZY on 2019/01/22
     */
    public int updateByApplyCheckPlatform(SqlSession session, ApplyCheckPlatform applyCheckPlatform) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.update("sun.bz1.dao.mapper.ApplyCheckPlatform.updateByApplyCheckPlatform", applyCheckPlatform);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--updateByApplyCheckPlatform--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据ApplyCheckAndOrderTableAndUser实体联表查询
     *
     * 查询数量
     *
     * @param unionData
     * @return
     *
     * @author ZY on 2019/01/22
     */
    public int selectThreeTablesCountByUnionData(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
        int num = 0;

        try {
            logger.info("ApplyCheckDao");

            num = session.selectOne("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesCountByUnionData", unionData);
        } catch (Exception e) {
            logger.error("ApplyCheckDao--selectThreeTablesCountByUnionData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据ApplyCheckAndOrderTableAndUser实体联表查询
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
     * @author ZY on 2019/01/22
     */
    public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesByUnionData(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
        List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

        try {
            logger.info("ApplyCheckDao");

            applyCheckList = session.selectList("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesByUnionData", unionData);
        } catch (Exception e) {
            logger.error("ApplyCheckDao--selectThreeTablesByUnionData--error:" + e.getMessage());
        }

        return applyCheckList;
    }

    /**
     * 根据ApplyCheckAndOrderTableAndUser实体联表查询 申请时间倒序
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
     * @author ZY on 2019/01/22
     */
    public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesByUnionDataByTimeDesc(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
        List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

        try {
            logger.info("ApplyCheckPlatformDao");

            applyCheckList = session.selectList("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesByUnionDataByTimeDesc", unionData);
        } catch (Exception e) {
            logger.error("ApplyCheckDao--selectThreeTablesByUnionDataByTimeDesc--error:" + e.getMessage());
        }

        return applyCheckList;
    }

    /**
     * 根据ApplyCheckAndOrderTableAndUser实体联表模糊查询
     *
     * 查询数量
     *
     * @param unionData
     * @return
     *
     * @author ZY on 2019/01/22
     */
    public int selectThreeTablesCountBySelectData(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.selectOne("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesCountBySelectData", unionData);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据ApplyCheckAndOrderTableAndUser实体联表模糊查询
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
     * @author ZY on 2019/01/22
     */
    public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, ApplyCheckAndOrderTableAndUser unionData) {
        List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

        try {
            logger.info("ApplyCheckPlatformDao");

            applyCheckList = session.selectList("sun.bz1.dao.mapper.ApplyCheck.selectThreeTablesBySelectData", unionData);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--selectThreeTablesBySelectData--error:" + e.getMessage());
        }

        return applyCheckList;
    }

    /**
     * 根据ApplyCheckPlatform实体删除
     *
     * @param applyCheckPlatform
     * @return
     *
     * @author ZY on 2019/01/22
     */
    public int deleteByApplyCheckPlatform(SqlSession session, ApplyCheckPlatform applyCheckPlatform) {
        int num = 0;

        try {
            logger.info("ApplyCheckPlatformDao");

            num = session.delete("sun.bz1.dao.mapper.ApplyCheckPlatform.deleteByApplyCheckPlatform", applyCheckPlatform);
        } catch (Exception e) {
            logger.error("ApplyCheckPlatformDao--deleteByApplyCheckPlatform--error:" + e.getMessage());
        }

        return num;
    }
}