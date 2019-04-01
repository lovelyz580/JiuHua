package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.OrderDetailConfirm;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修单详情确认表
 *
 * Dao
 *
 * @author ZY on 2018/11/26
 */

@Repository
public class OrderDetailConfirmDao {

    private Logger logger = Logger.getLogger(OrderDetailConfirmDao.class);

    public int insert(SqlSession session, OrderDetailConfirm orderDetailConfirm) {
        int num = 0;

        try {
            logger.info("OrderDetailConfirmDao");

            num = session.insert("sun.bz1.dao.mapper.OrderDetailConfirm.insert", orderDetailConfirm);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, OrderDetailConfirm orderDetailConfirm) {
        int num = 0;

        try {
            logger.info("OrderDetailConfirmDao");

            num = session.insert("sun.bz1.dao.mapper.OrderDetailConfirm.insertSelective", orderDetailConfirm);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, OrderDetailConfirm orderDetailConfirm) {
        int num = 0;

        try {
            logger.info("OrderDetailConfirmDao");

            num = session.update("sun.bz1.dao.mapper.OrderDetailConfirm.updateByPrimaryKeySelective", orderDetailConfirm);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, OrderDetailConfirm orderDetailConfirm) {
        int num = 0;

        try {
            logger.info("OrderDetailConfirmDao");

            num = session.update("sun.bz1.dao.mapper.OrderDetailConfirm.updateByPrimaryKey", orderDetailConfirm);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public OrderDetailConfirm selectByPrimaryKey(SqlSession session, Integer id) {
        OrderDetailConfirm orderDetailConfirm = new OrderDetailConfirm();

        try {
            logger.info("OrderDetailConfirmDao");

            orderDetailConfirm = session.selectOne("sun.bz1.dao.mapper.OrderDetailConfirm.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return orderDetailConfirm;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("OrderDetailConfirmDao");

            num = session.delete("sun.bz1.dao.mapper.OrderDetailConfirm.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderDetailConfirm实体添加
     *
     * @param orderDetailConfirm
     * @return
     *
     * @author ZY on 2018/11/26
     */
    public int insertByOrderDetailConfirm(SqlSession session, OrderDetailConfirm orderDetailConfirm) {
        int num = 0;

        try {
            logger.info("OrderDetailConfirmDao");

            num = session.insert("sun.bz1.dao.mapper.OrderDetailConfirm.insertByOrderDetailConfirm", orderDetailConfirm);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--insertByOrderDetailConfirm--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderDetailConfirm实体更新
     *
     * @param orderDetailConfirm
     * @return
     *
     * @author ZY on 2018/11/26
     */
    public int updateByOrderDetailConfirm(SqlSession session, OrderDetailConfirm orderDetailConfirm) {
        int num = 0;

        try {
            logger.info("OrderDetailConfirmDao");

            num = session.update("sun.bz1.dao.mapper.OrderDetailConfirm.updateByOrderDetailConfirm", orderDetailConfirm);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--updateByOrderDetailConfirm--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderDetailConfirm实体查询
     *
     * @param orderDetailConfirm
     * @return
     *
     * @author ZY on 2018/11/26
     */
    public List<OrderDetailConfirm> selectByOrderDetailConfirm(SqlSession session, OrderDetailConfirm orderDetailConfirm) {
        List<OrderDetailConfirm> orderDetailConfirmList = new ArrayList<OrderDetailConfirm>();

        try {
            logger.info("OrderDetailConfirmDao");

            orderDetailConfirmList = session.selectList("sun.bz1.dao.mapper.OrderDetailConfirm.selectByOrderDetailConfirm", orderDetailConfirm);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--selectByOrderDetailConfirm--error:" + e.getMessage());
        }

        return orderDetailConfirmList;
    }

    /**
     * 根据OrderDetailConfirm实体删除
     *
     * @param orderDetailConfirm
     * @return
     *
     * @author ZY on 2018/11/26
     */
    public int deleteByOrderDetailConfirm(SqlSession session, OrderDetailConfirm orderDetailConfirm) {
        int num = 0;

        try {
            logger.info("OrderDetailConfirmDao");

            num = session.delete("sun.bz1.dao.mapper.OrderDetailConfirm.deleteByOrderDetailConfirm", orderDetailConfirm);
        } catch (Exception e) {
            logger.error("OrderDetailConfirmDao--deleteByOrderDetailConfirm--error:" + e.getMessage());
        }

        return num;
    }

}