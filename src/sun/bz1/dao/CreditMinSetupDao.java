package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.CreditMinSetup;

/**
 * 信用值_最低值_设置表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/04
 */

@Repository
public class CreditMinSetupDao {

	private Logger logger = Logger.getLogger(CreditMinSetupDao.class);

	public int updateByPrimaryKeySelective(SqlSession session, CreditMinSetup setup) {
		int num = 0;

		try {
			logger.info("CreditMinSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditMinSetup.updateByPrimaryKeySelective", setup);
		} catch (Exception e) {
			logger.error("CreditMinSetupDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

	public int updateByPrimaryKey(SqlSession session, CreditMinSetup setup) {
		int num = 0;

		try {
			logger.info("CreditMinSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditMinSetup.updateByPrimaryKey", setup);
		} catch (Exception e) {
			logger.error("CreditMinSetupDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	public CreditMinSetup selectByPrimaryKey(SqlSession session, Integer id) {
		CreditMinSetup setup = new CreditMinSetup();

		try {
			logger.info("CreditMinSetupDao");

			setup = session.selectOne("sun.bz1.dao.mapper.CreditMinSetup.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CreditMinSetupDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return setup;
	}

	public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("CreditMinSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditMinSetup.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CreditMinSetupDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditMinSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateBySetup(SqlSession session, CreditMinSetup setup) {
		int num = 0;

		try {
			logger.info("CreditMinSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditMinSetup.updateBySetup", setup);
		} catch (Exception e) {
			logger.error("CreditMinSetupDao--updateBySetup--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CreditMinSetup实体查询
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public List<CreditMinSetup> selectBySetup(SqlSession session, CreditMinSetup setup) {
		List<CreditMinSetup> setupList = new ArrayList<CreditMinSetup>();

		try {
			logger.info("CreditMinSetupDao");

			setupList = session.selectList("sun.bz1.dao.mapper.CreditMinSetup.selectBySetup", setup);
		} catch (Exception e) {
			logger.error("CreditMinSetupDao--selectBySetup--error:" + e.getMessage());
		}

		return setupList;
	}

}