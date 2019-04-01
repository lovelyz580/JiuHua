package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.Wechat;

/**
 * 微信相关数据表
 * 
 * Dao
 * 
 * @author WJF on 2018/10/12
 */

@Repository
public class WechatDao {
	
	private Logger logger = Logger.getLogger(WechatDao.class);

	public int insert(SqlSession session, Wechat wechat) {
		int num = 0;

		try {
			logger.info("WechatDao");

			num = session.insert("sun.bz1.dao.mapper.Wechat.insert", wechat);
		} catch (Exception e) {
			logger.error("WechatDao--insert--error:" + e.getMessage());
		}

		return num;
	}

	public int insertSelective(SqlSession session, Wechat wechat) {
		int num = 0;

		try {
			logger.info("WechatDao");

			num = session.insert("sun.bz1.dao.mapper.Wechat.insertSelective", wechat);
		} catch (Exception e) {
			logger.error("WechatDao--insertSelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKeySelective(SqlSession session, Wechat wechat) {
		int num = 0;

		try {
			logger.info("WechatDao");

			num = session.update("sun.bz1.dao.mapper.Wechat.updateByPrimaryKeySelective", wechat);
		} catch (Exception e) {
			logger.error("WechatDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, Wechat wechat) {
		int num = 0;

		try {
			logger.info("WechatDao");

			num = session.update("sun.bz1.dao.mapper.Wechat.updateByPrimaryKey", wechat);
		} catch (Exception e) {
			logger.error("WechatDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public Wechat selectByPrimaryKey(SqlSession session, Integer id) {
		Wechat Wechat = new Wechat();

		try {
			logger.info("WechatDao");

			Wechat = session.selectOne("sun.bz1.dao.mapper.Wechat.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("WechatDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return Wechat;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("WechatDao");

			num = session.delete("sun.bz1.dao.mapper.Wechat.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("WechatDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Wechat实体添加
	 * 
	 * @param wechat
	 * @return
	 * 
	 * @author WJF on 2018/10/12
	 */
	public int insertByWechat(SqlSession session, Wechat wechat) {
		int num = 0;

		try {
			logger.info("WechatDao");

			num = session.insert("sun.bz1.dao.mapper.Wechat.insertByWechat", wechat);
		} catch (Exception e) {
			logger.error("WechatDao--insertByWechat--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Wechat实体更新
	 * 
	 * @param wechat
	 * @return
	 * 
	 * @author WJF on 2018/10/12
	 */
	public int updateByWechat(SqlSession session, Wechat wechat) {
		int num = 0;

		try {
			logger.info("WechatDao");

			num = session.update("sun.bz1.dao.mapper.Wechat.updateByWechat", wechat);
		} catch (Exception e) {
			logger.error("WechatDao--updateByWechat--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据Wechat实体联表查询
	 * 
	 * @param wechat
	 * @return
	 * 
	 * @author WJF on 2018/10/12
	 */
	public List<Wechat> selectByWechat(SqlSession session, Wechat wechat) {
		List<Wechat> wechatList = new ArrayList<Wechat>();

		try {
			logger.info("WechatDao");

			wechatList = session.selectList("sun.bz1.dao.mapper.Wechat.selectByWechat", wechat);
		} catch (Exception e) {
			logger.error("WechatDao--selectByWechat--error:" + e.getMessage());
		}

		return wechatList;
	}

}