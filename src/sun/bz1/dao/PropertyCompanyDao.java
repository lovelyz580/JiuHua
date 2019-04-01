package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.BuildingType;
import sun.bz1.entity.PropertyCompany;

import java.util.ArrayList;
import java.util.List;

/**
 * 地产公司表
 *
 * Dao
 *
 * @author ZY on 2019/01/09
 */

@Repository
public class PropertyCompanyDao {

    private Logger logger = Logger.getLogger(PropertyCompanyDao.class);

    public int insert(SqlSession session, PropertyCompany propertyCompany) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.insert("sun.bz1.dao.mapper.PropertyCompany.insert", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, PropertyCompany propertyCompany) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.insert("sun.bz1.dao.mapper.PropertyCompany.insertSelective", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, PropertyCompany propertyCompany) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.update("sun.bz1.dao.mapper.PropertyCompany.updateByPrimaryKeySelective", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, PropertyCompany propertyCompany) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.update("sun.bz1.dao.mapper.PropertyCompany.updateByPrimaryKey", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.selectOne("sun.bz1.dao.mapper.PropertyCompany.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public PropertyCompany selectByPrimaryKey(SqlSession session, Integer id) {
        PropertyCompany propertyCompany = new PropertyCompany();

        try {
            logger.info("PropertyCompanyDao");

            propertyCompany = session.selectOne("sun.bz1.dao.mapper.PropertyCompany.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return propertyCompany;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.delete("sun.bz1.dao.mapper.PropertyCompany.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据PropertyCompany实体添加
     *
     * @param propertyCompany
     * @return
     *
     * @author ZY on 2019/01/09
     */
    public int insertByPropertyCompany(SqlSession session, PropertyCompany propertyCompany) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.insert("sun.bz1.dao.mapper.PropertyCompany.insertByPropertyCompany", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--insertByPropertyCompany--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据PropertyCompany实体更新
     *
     * @param propertyCompany
     * @return
     *
     * @author ZY on 2019/01/09
     */
    public int updateByPropertyCompany(SqlSession session, PropertyCompany propertyCompany) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.update("sun.bz1.dao.mapper.PropertyCompany.updateByPropertyCompany", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--updateByPropertyCompany--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据PropertyCompany实体查询
     *
     * 查询数量
     *
     * @param propertyCompany
     * @return
     *
     * @author ZY on 2019/01/09
     */
    public int selectCountByPropertyCompany(SqlSession session, PropertyCompany propertyCompany) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.selectOne("sun.bz1.dao.mapper.PropertyCompany.selectCountByPropertyCompany", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--selectCountByPropertyCompany--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据PropertyCompany实体查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param propertyCompany
     * @return
     *
     * @author ZY on 2019/01/09
     */
    public List<PropertyCompany> selectByPropertyCompany(SqlSession session, PropertyCompany propertyCompany) {
        List<PropertyCompany> propertyCompanyList = new ArrayList<PropertyCompany>();

        try {
            logger.info("PropertyCompanyDao");

            propertyCompanyList = session.selectList("sun.bz1.dao.mapper.PropertyCompany.selectByPropertyCompany", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--selectByPropertyCompany--error:" + e.getMessage());
        }

        return propertyCompanyList;
    }

    /**
     * 根据PropertyCompany实体模糊查询
     *
     * 查询数量
     *
     * @param propertyCompany
     * @return
     *
     * @author ZY on 2019/01/09
     */
    public int selectCountBySelectData(SqlSession session, PropertyCompany propertyCompany) {
        int num = 0;

        try {
            logger.info("PropertyCompanyDao");

            num = session.selectOne("sun.bz1.dao.mapper.PropertyCompany.selectCountBySelectData", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--selectCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据PropertyCompany实体模糊查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param propertyCompany
     * @return
     *
     * @author ZY on 2019/01/09
     */
    public List<PropertyCompany> selectBySelectData(SqlSession session, PropertyCompany propertyCompany) {
        List<PropertyCompany> propertyCompanyList = new ArrayList<PropertyCompany>();

        try {
            logger.info("PropertyCompanyDao");

            propertyCompanyList = session.selectList("sun.bz1.dao.mapper.PropertyCompany.selectBySelectData", propertyCompany);
        } catch (Exception e) {
            logger.error("PropertyCompanyDao--selectBySelectData--error:" + e.getMessage());
        }

        return propertyCompanyList;
    }

}