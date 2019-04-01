package sun.bz1.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.OrderDetailRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修单详情记录表
 *
 * Dao
 *
 * @author ZY on 2018/11/08
 */

@Repository
public class OrderDetailRecordDao {

    private Logger logger = Logger.getLogger(OrderDetailRecordDao.class);

    public int insert(SqlSession session, OrderDetailRecord orderDetailRecord) {
        int num = 0;

        try {
            logger.info("OrderDetailRecordDao");

            num = session.insert("sun.bz1.dao.mapper.OrderDetailRecord.insert", orderDetailRecord);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--insert--error:" + e.getMessage());
        }

        return num;
    }

    public int insertSelective(SqlSession session, OrderDetailRecord orderDetailRecord) {
        int num = 0;

        try {
            logger.info("OrderDetailRecordDao");

            num = session.insert("sun.bz1.dao.mapper.OrderDetailRecord.insertSelective", orderDetailRecord);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--insertSelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKeySelective(SqlSession session, OrderDetailRecord orderDetailRecord) {
        int num = 0;

        try {
            logger.info("OrderDetailRecordDao");

            num = session.update("sun.bz1.dao.mapper.OrderDetailRecord.updateByPrimaryKeySelective", orderDetailRecord);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--updateByPrimaryKeySelective--error:" + e.getMessage());
        }

        return num;
    }

    public int updateByPrimaryKey(SqlSession session, OrderDetailRecord orderDetailRecord) {
        int num = 0;

        try {
            logger.info("OrderDetailRecordDao");

            num = session.update("sun.bz1.dao.mapper.OrderDetailRecord.updateByPrimaryKey", orderDetailRecord);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--updateByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    public OrderDetailRecord selectByPrimaryKey(SqlSession session, Integer id) {
        OrderDetailRecord orderDetailRecord = new OrderDetailRecord();

        try {
            logger.info("OrderDetailRecordDao");

            orderDetailRecord = session.selectOne("sun.bz1.dao.mapper.OrderDetailRecord.selectByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--selectByPrimaryKey--error:" + e.getMessage());
        }

        return orderDetailRecord;
    }

    public int deleteByPrimaryKey(SqlSession session, Integer id) {
        int num = 0;

        try {
            logger.info("OrderDetailRecordDao");

            num = session.delete("sun.bz1.dao.mapper.OrderDetailRecord.deleteByPrimaryKey", id);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--deleteByPrimaryKey--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderDetailRecord实体添加
     *
     * @param orderDetailRecord
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public int insertByOrderDetailRecord(SqlSession session, OrderDetailRecord orderDetailRecord) {
        int num = 0;

        try {
            logger.info("OrderDetailRecordDao");

            num = session.insert("sun.bz1.dao.mapper.OrderDetailRecord.insertByOrderDetailRecord", orderDetailRecord);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--insertByOrderDetailRecord--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderDetailRecord实体更新
     *
     * @param orderDetailRecord
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public int updateByOrderDetailRecord(SqlSession session, OrderDetailRecord orderDetailRecord) {
        int num = 0;

        try {
            logger.info("OrderDetailRecordDao");

            num = session.update("sun.bz1.dao.mapper.OrderDetailRecord.updateByOrderDetailRecord", orderDetailRecord);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--updateByOrderDetailRecord--error:" + e.getMessage());
        }

        return num;
    }

    /**
     * 根据OrderDetailRecord实体查询
     *
     * @param orderDetailRecord
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public List<OrderDetailRecord> selectByOrderDetailRecord(SqlSession session, OrderDetailRecord orderDetailRecord) {
        List<OrderDetailRecord> orderDetailRecordList = new ArrayList<OrderDetailRecord>();

        try {
            logger.info("OrderDetailRecordDao");

            orderDetailRecordList = session.selectList("sun.bz1.dao.mapper.OrderDetailRecord.selectByOrderDetailRecord", orderDetailRecord);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--selectByOrderDetailRecord--error:" + e.getMessage());
        }

        return orderDetailRecordList;
    }

    /**
     * 根据OrderDetailRecord实体删除
     *
     * @param orderDetailRecord
     * @return
     *
     * @author ZY on 2018/11/08
     */
    public int deleteByOrderDetailRecord(SqlSession session, OrderDetailRecord orderDetailRecord) {
        int num = 0;

        try {
            logger.info("OrderDetailRecordDao");

            num = session.delete("sun.bz1.dao.mapper.OrderDetailRecord.deleteByOrderDetailRecord", orderDetailRecord);
        } catch (Exception e) {
            logger.error("OrderDetailRecordDao--deleteByOrderDetailRecord--error:" + e.getMessage());
        }

        return num;
    }
}