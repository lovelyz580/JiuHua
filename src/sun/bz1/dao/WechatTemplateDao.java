package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.WechatTemplate;

import java.util.ArrayList;
import java.util.List;

/**
    * 微信服务通知模板表
    *
    * Dao
    *
    * @author ZY on 2018/11/13
    */
@Repository
public class WechatTemplateDao {
    private Logger logger = Logger.getLogger(WechatTemplateDao.class);

    public int insert(SqlSession session, WechatTemplate wechatTemplate) {
        int num = 0;

        try {
            logger.info("WechatTemplateDao");

            num = session.insert("sun.bz1.dao.mapper.WechatTemplate.insert", wechatTemplate);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, WechatTemplate wechatTemplate) {
        int num = 0;

        try {
            logger.info("WechatTemplateDao");

            num = session.insert("sun.bz1.dao.mapper.WechatTemplate.insertSelective", wechatTemplate);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, WechatTemplate wechatTemplate) {
        int num = 0;

        try {
            logger.info("WechatTemplateDao");

            num = session.update("sun.bz1.dao.mapper.WechatTemplate.updateByPrimaryKeySelective", wechatTemplate);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, WechatTemplate wechatTemplate) {
        int num = 0;

        try {
            logger.info("WechatTemplateDao");

            num = session.update("sun.bz1.dao.mapper.WechatTemplate.updateByPrimaryKey", wechatTemplate);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public WechatTemplate selectByPrimaryKey(SqlSession session, Integer id) {
        WechatTemplate wechatTemplate = new WechatTemplate();

        try {
            logger.info("WechatTemplateDao");

            wechatTemplate = session.selectOne("sun.bz1.dao.mapper.WechatTemplate.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return wechatTemplate;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("WechatTemplateDao");

            num = session.delete("sun.bz1.dao.mapper.WechatTemplate.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据WechatTemplate实体添加
     *
     * @param wechatTemplate
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public int insertByWechatTemplate(SqlSession session, WechatTemplate wechatTemplate) {
        int num = 0;

        try {
            logger.info("WechatTemplateDao");

            num = session.insert("sun.bz1.dao.mapper.WechatTemplate.insertByWechatTemplate", wechatTemplate);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--insertByWechatTemplate--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据WechatTemplate实体更新
     *
     * @param wechatTemplate
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public int updateByWechatTemplate(SqlSession session, WechatTemplate wechatTemplate) {
        int num = 0;

        try {
            logger.info("WechatTemplateDao");

            num = session.update("sun.bz1.dao.mapper.WechatTemplate.updateByWechatTemplate", wechatTemplate);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--updateByWechatTemplate--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据WechatTemplate实体查询
     *
     * 查询数量
     *
     * @param wechatTemplate
     * @return
     *
     * @author ZY on 2018/11/12
     */
    public int selectCountByWechatTemplate(SqlSession session, WechatTemplate wechatTemplate) {
        int num = 0;

        try {
            logger.info("WechatTemplateDao");

            num = session.selectOne("sun.bz1.dao.mapper.WechatTemplate.selectCountByWechatTemplate", wechatTemplate);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--selectCountByWechatTemplate--error:" + e.getMessage());
        }

        return num;
    }
    /**
     * 根据WechatTemplate实体查询
     *
     * @param wechatTemplate
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public List<WechatTemplate> selectByWechatTemplate(SqlSession session, WechatTemplate wechatTemplate) {
        List<WechatTemplate> wechatTemplateList = new ArrayList<WechatTemplate>();

        try {
            logger.info("WechatTemplateDao");

            wechatTemplateList = session.selectList("sun.bz1.dao.mapper.WechatTemplate.selectByWechatTemplate", wechatTemplate);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--selectByWechatTemplate--error:" + e.getMessage());
        }

        return wechatTemplateList;
    }

    /**
     * 根据WechatTemplate实体删除
     *
     * @param wechatTemplate
     * @return
     *
     * @author ZY on 2018/11/08
     */
    /*public int deleteByWechatTemplate(SqlSession session, WechatTemplate wechatTemplate) {
        int num = 0;

        try {
            logger.info("WechatTemplateDao");

            num = session.delete("sun.bz1.dao.mapper.WechatTemplate.deleteByWechatTemplate", wechatTemplate);
        } catch (Exception e) {
            logger.error("WechatTemplateDao--deleteByWechatTemplate--error:" + e.getMessage());
        }

        return num;
    }*/
}