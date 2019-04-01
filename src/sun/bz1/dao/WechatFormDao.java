package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.WechatForm;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信form表单
 *
 * Dao
 *
 * @author ZY on 2018/11/12
 */
@Repository
public class WechatFormDao {
    private Logger logger = Logger.getLogger(WechatFormDao.class);

    public int insert(SqlSession session, WechatForm wechatForm) {
        int num = 0;

        try {
            logger.info("WechatFormDao");

            num = session.insert("sun.bz1.dao.mapper.WechatForm.insert", wechatForm);
        } catch (Exception e) {
            logger.error("WechatFormDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, WechatForm wechatForm) {
        int num = 0;

        try {
            logger.info("WechatFormDao");

            num = session.insert("sun.bz1.dao.mapper.WechatForm.insertSelective", wechatForm);
        } catch (Exception e) {
            logger.error("WechatFormDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, WechatForm wechatForm) {
        int num = 0;

        try {
            logger.info("WechatFormDao");

            num = session.update("sun.bz1.dao.mapper.WechatForm.updateByPrimaryKeySelective", wechatForm);
        } catch (Exception e) {
            logger.error("WechatFormDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, WechatForm wechatForm) {
        int num = 0;

        try {
            logger.info("WechatFormDao");

            num = session.update("sun.bz1.dao.mapper.WechatForm.updateByPrimaryKey", wechatForm);
        } catch (Exception e) {
            logger.error("WechatFormDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public WechatForm selectByPrimaryKey(SqlSession session, Integer id) {
        WechatForm wechatForm = new WechatForm();

        try {
            logger.info("WechatFormDao");

            wechatForm = session.selectOne("sun.bz1.dao.mapper.WechatForm.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("WechatFormDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return wechatForm;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("WechatFormDao");

            num = session.delete("sun.bz1.dao.mapper.WechatForm.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("WechatFormDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据WechatForm实体添加
     *
     * @param wechatForm
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public int insertByWechatForm(SqlSession session, WechatForm wechatForm) {
        int num = 0;

        try {
            logger.info("WechatFormDao");

            num = session.insert("sun.bz1.dao.mapper.WechatForm.insertByWechatForm", wechatForm);
        } catch (Exception e) {
            logger.error("WechatFormDao--insertByWechatForm--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据WechatForm实体更新
     *
     * @param wechatForm
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public int updateByWechatForm(SqlSession session, WechatForm wechatForm) {
        int num = 0;

        try {
            logger.info("WechatFormDao");

            num = session.update("sun.bz1.dao.mapper.WechatForm.updateByWechatForm", wechatForm);
        } catch (Exception e) {
            logger.error("WechatFormDao--updateByWechatForm--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据WechatForm实体查询
     *
     * 查询数量
     *
     * @param wechatForm
     * @return
     *
     * @author ZY on 2018/11/12
     */
    public int selectCountByWechatForm(SqlSession session, WechatForm wechatForm) {
        int num = 0;

        try {
            logger.info("WechatFormDao");

            num = session.selectOne("sun.bz1.dao.mapper.WechatForm.selectCountByWechatForm", wechatForm);
        } catch (Exception e) {
            logger.error("WechatFormDao--selectCountByWechatForm--error:" + e.getMessage());
        }

        return num;
    }
    /**
     * 根据WechatForm实体查询
     *
     * @param wechatForm
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public List<WechatForm> selectByWechatForm(SqlSession session, WechatForm wechatForm) {
        List<WechatForm> wechatFormList = new ArrayList<WechatForm>();

        try {
            logger.info("WechatFormDao");

            wechatFormList = session.selectList("sun.bz1.dao.mapper.WechatForm.selectByWechatForm", wechatForm);
        } catch (Exception e) {
            logger.error("WechatFormDao--selectByWechatForm--error:" + e.getMessage());
        }

        return wechatFormList;
    }

    /**
     * 根据WechatForm实体删除
     *
     * @param wechatForm
     * @return
     *
     * @author ZY on 2018/11/08
     */
    /*public int deleteByWechatForm(SqlSession session, WechatForm wechatForm) {
        int num = 0;

        try {
            logger.info("WechatFormDao");

            num = session.delete("sun.bz1.dao.mapper.WechatForm.deleteByWechatForm", wechatForm);
        } catch (Exception e) {
            logger.error("WechatFormDao--deleteByWechatForm--error:" + e.getMessage());
        }

        return num;
    }*/
}