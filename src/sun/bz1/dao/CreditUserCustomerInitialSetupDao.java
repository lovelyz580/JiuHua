package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.CreditUserCustomerInitialSetup;

/**
 * 信用值_用户_客户_初始值_设置表
 * 
 * Dao
 * 
 * @author WJF on 2018/09/04
 */

@Repository
public class CreditUserCustomerInitialSetupDao {
	
	private Logger logger = Logger.getLogger(CreditUserCustomerInitialSetupDao.class);
	
    public int updateByPrimaryKeySelective(SqlSession session, CreditUserCustomerInitialSetup setup) {
		int num = 0;

		try {
			logger.info("CreditUserCustomerInitialSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditUserCustomerInitialSetup.updateByPrimaryKeySelective", setup);
		} catch (Exception e) {
			logger.error("CreditUserCustomerInitialSetupDao--updateByPrimaryKeySelective--error:" + e.getMessage());
		}

		return num;
	}

    public int updateByPrimaryKey(SqlSession session, CreditUserCustomerInitialSetup setup) {
		int num = 0;

		try {
			logger.info("CreditUserCustomerInitialSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditUserCustomerInitialSetup.updateByPrimaryKey", setup);
		} catch (Exception e) {
			logger.error("CreditUserCustomerInitialSetupDao--updateByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    public CreditUserCustomerInitialSetup selectByPrimaryKey(SqlSession session, Integer id) {
    	CreditUserCustomerInitialSetup setup = new CreditUserCustomerInitialSetup();

		try {
			logger.info("CreditUserCustomerInitialSetupDao");

			setup = session.selectOne("sun.bz1.dao.mapper.CreditUserCustomerInitialSetup.selectByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CreditUserCustomerInitialSetupDao--selectByPrimaryKey--error:" + e.getMessage());
		}

		return setup;
	}
    
    public int deleteByPrimaryKey(SqlSession session, Integer id) {
		int num = 0;

		try {
			logger.info("CreditUserCustomerInitialSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditUserCustomerInitialSetup.deleteByPrimaryKey", id);
		} catch (Exception e) {
			logger.error("CreditUserCustomerInitialSetupDao--deleteByPrimaryKey--error:" + e.getMessage());
		}

		return num;
	}
    
    /**
	 * 根据CreditUserCustomerInitialSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateBySetup(SqlSession session, CreditUserCustomerInitialSetup setup) {
		int num = 0;

		try {
			logger.info("CreditUserCustomerInitialSetupDao");

			num = session.update("sun.bz1.dao.mapper.CreditUserCustomerInitialSetup.updateBySetup", setup);
		} catch (Exception e) {
			logger.error("CreditUserCustomerInitialSetupDao--updateBySetup--error:" + e.getMessage());
		}

		return num;
	}
	
	/**
	 * 根据CreditUserCustomerInitialSetup实体查询
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
    public List<CreditUserCustomerInitialSetup> selectBySetup(SqlSession session, CreditUserCustomerInitialSetup setup) {
    	List<CreditUserCustomerInitialSetup> setupList = new ArrayList<CreditUserCustomerInitialSetup>();

		try {
			logger.info("CreditUserCustomerInitialSetupDao");

			setupList = session.selectList("sun.bz1.dao.mapper.CreditUserCustomerInitialSetup.selectBySetup", setup);
		} catch (Exception e) {
			logger.error("CreditUserCustomerInitialSetupDao--selectBySetup--error:" + e.getMessage());
		}

		return setupList;
	}
    
}