package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.CreditUserServiceInitialSetup;

/**
 * 信用值_用户_维修工、安装队_初始值_设置表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/04
 */

@Repository
public class CreditUserServiceInitialSetupDao {
	
	private Logger logger = Logger.getLogger(CreditUserServiceInitialSetupDao.class);
	
    public int updateByPrimaryKeySelective(SqlSession session, CreditUserServiceInitialSetup setup) {
		int num = 0;

		try {
			logger.info("CreditUserServiceInitialSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditUserServiceInitialSetup.updateByPrimaryKeySelective", setup);
		} catch (Exception e) {
			logger.error("CreditUserServiceInitialSetupDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

    public int updateByPrimaryKey(SqlSession session, CreditUserServiceInitialSetup setup) {
		int num = 0;

		try {
			logger.info("CreditUserServiceInitialSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditUserServiceInitialSetup.updateByPrimaryKey", setup);
		} catch (Exception e) {
			logger.error("CreditUserServiceInitialSetupDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    public CreditUserServiceInitialSetup selectByPrimaryKey(SqlSession session, Integer id) {
    	CreditUserServiceInitialSetup setup = new CreditUserServiceInitialSetup();

		try {
			logger.info("CreditUserServiceInitialSetupDao");

			setup = session.selectOne("sun.bz1.dao.mapper.CreditUserServiceInitialSetup.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CreditUserServiceInitialSetupDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return setup;
	}
    
    public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("CreditUserServiceInitialSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditUserServiceInitialSetup.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CreditUserServiceInitialSetupDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    /**
	 * 根据CreditUserServiceInitialSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateBySetup(SqlSession session, CreditUserServiceInitialSetup setup) {
		int num = 0;

		try {
			logger.info("CreditUserServiceInitialSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditUserServiceInitialSetup.updateBySetup", setup);
		} catch (Exception e) {
			logger.error("CreditUserServiceInitialSetupDao--updateBySetup--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据CreditUserServiceInitialSetup实体查询
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
    public List<CreditUserServiceInitialSetup> selectBySetup(SqlSession session, CreditUserServiceInitialSetup setup) {
    	List<CreditUserServiceInitialSetup> setupList = new ArrayList<CreditUserServiceInitialSetup>();

		try {
			logger.info("CreditUserServiceInitialSetupDao");

			setupList = session.selectList("sun.bz1.dao.mapper.CreditUserServiceInitialSetup.selectBySetup", setup);
		} catch (Exception e) {
			logger.error("CreditUserServiceInitialSetupDao--selectBySetup--error:" + e.getMessage());
		}

		return setupList;
	}
    
}