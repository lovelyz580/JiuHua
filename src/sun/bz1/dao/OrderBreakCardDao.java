package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.OrderBreakCard;
import sun.bz1.entity.OrderBreakCardAndOrderTableAndUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单打卡表
 *
 * Dao
 *
 * @author ZY on 2018/12/15
 */

@Repository
public class OrderBreakCardDao {
    private Logger logger = Logger.getLogger(OrderBreakCardDao.class);

    public int insert(SqlSession session, OrderBreakCard orderBreakCard) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.insert("sun.bz1.dao.mapper.OrderBreakCard.insert", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, OrderBreakCard orderBreakCard) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.insert("sun.bz1.dao.mapper.OrderBreakCard.insertSelective", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, OrderBreakCard orderBreakCard) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.update("sun.bz1.dao.mapper.OrderBreakCard.updateByPrimaryKeySelective", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, OrderBreakCard orderBreakCard) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.update("sun.bz1.dao.mapper.OrderBreakCard.updateByPrimaryKey", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public int selectCountByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.selectOne("sun.bz1.dao.mapper.OrderBreakCard.selectCountByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--selectCountByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public OrderBreakCard selectByPrimaryKey(SqlSession session, Integer id) {
        OrderBreakCard user = new OrderBreakCard();

        try {
            logger.info("OrderBreakCardDao");

            user = session.selectOne("sun.bz1.dao.mapper.OrderBreakCard.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return user;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.delete("sun.bz1.dao.mapper.OrderBreakCard.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderBreakCard实体添加
     *
     * @param orderBreakCard
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int insertByOrderBreakCard(SqlSession session, OrderBreakCard orderBreakCard) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.insert("sun.bz1.dao.mapper.OrderBreakCard.insertByOrderBreakCard", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--insertByOrderBreakCard--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderBreakCard实体更新
     *
     * @param orderBreakCard
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int updateByOrderBreakCard(SqlSession session, OrderBreakCard orderBreakCard) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.update("sun.bz1.dao.mapper.OrderBreakCard.updateByOrderBreakCard", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--updateByOrderBreakCard--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderBreakCard实体查询
     *
     * 查询数量
     *
     * @param orderBreakCard
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int selectCountByOrderBreakCard(SqlSession session, OrderBreakCard orderBreakCard) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.selectOne("sun.bz1.dao.mapper.OrderBreakCard.selectCountByOrderBreakCard", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--selectCountByOrderBreakCard--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderBreakCard实体查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param orderBreakCard
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public List<OrderBreakCard> selectByOrderBreakCard(SqlSession session, OrderBreakCard orderBreakCard) {
        List<OrderBreakCard> orderBreakCardList = new ArrayList<OrderBreakCard>();

        try {
            logger.info("OrderBreakCardDao");

            orderBreakCardList = session.selectList("sun.bz1.dao.mapper.OrderBreakCard.selectByOrderBreakCard", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--selectByOrderBreakCard--error:" + e.getMessage());
        }

        return orderBreakCardList;
    }

    /**
     * 根据OrderBreakCard实体模糊查询
     *
     * 查询数量
     *
     * @param orderBreakCard
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int selectCountBySelectData(SqlSession session, OrderBreakCard orderBreakCard) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.selectOne("sun.bz1.dao.mapper.OrderBreakCard.selectCountBySelectData", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--selectCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderBreakCard实体模糊查询
     *
     * 可以进行分页查询
     *
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     *
     * pageSize 每页的数据量
     *
     * @param orderBreakCard
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public List<OrderBreakCard> selectBySelectData(SqlSession session, OrderBreakCard orderBreakCard) {
        List<OrderBreakCard> orderBreakCardList = new ArrayList<OrderBreakCard>();

        try {
            logger.info("OrderBreakCardDao");

            orderBreakCardList = session.selectList("sun.bz1.dao.mapper.OrderBreakCard.selectBySelectData", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--selectBySelectData--error:" + e.getMessage());
        }

        return orderBreakCardList;
    }

    /**
     * 根据OrderBreakCardAndOrderTableAndUser实体联表模糊查询
     *
     * 查询数量
     *
     * @param unionData
     * @return
     *
     * @author ZY on 2018/12/18
     */
    public int selectThreeTablesCountBySelectData(SqlSession session, OrderBreakCardAndOrderTableAndUser unionData) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.selectOne("sun.bz1.dao.mapper.OrderBreakCard.selectThreeTablesCountBySelectData", unionData);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--selectThreeTablesCountBySelectData--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderBreakCardAndOrderTableAndUser实体联表模糊查询
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
     * @author ZY on 2018/12/18
     */
    public List<OrderBreakCardAndOrderTableAndUser> selectThreeTablesBySelectData(SqlSession session, OrderBreakCardAndOrderTableAndUser unionData) {
        List<OrderBreakCardAndOrderTableAndUser> interceptList = new ArrayList<OrderBreakCardAndOrderTableAndUser>();

        try {
            logger.info("OrderBreakCardDao");

            interceptList = session.selectList("sun.bz1.dao.mapper.OrderBreakCard.selectThreeTablesBySelectData", unionData);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--selectThreeTablesBySelectData--error:" + e.getMessage());
        }

        return interceptList;
    }

    /**
     * 根据OrderBreakCard实体删除
     *
     * @param orderBreakCard
     * @return
     *
     * @author ZY on 2018/11/28
     */
    public int deleteByOrderBreakCard(SqlSession session, OrderBreakCard orderBreakCard) {
        int num = 0;

        try {
            logger.info("OrderBreakCardDao");

            num = session.delete("sun.bz1.dao.mapper.OrderBreakCard.deleteByOrderBreakCard", orderBreakCard);
        } catch (Exception e) {
            logger.error("OrderBreakCardDao--deleteByOrderBreakCard--error:" + e.getMessage());
        }

        return num;
    }
}