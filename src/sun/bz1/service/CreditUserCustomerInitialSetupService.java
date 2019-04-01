package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.CreditUserCustomerInitialSetupDao;
import sun.bz1.entity.CreditUserCustomerInitialSetup;
import util.MyBatisUtil;

/**
 * 信用值_用户_客户_初始值_设置表
 * 
 * Service
 * 
 * @author WJF on 2018/09/04
 */

@Service
public class CreditUserCustomerInitialSetupService {

	@Autowired
	private CreditUserCustomerInitialSetupDao setupDao;

	private Logger logger = Logger.getLogger(CreditUserCustomerInitialSetupService.class);
	
	/**
	 * 根据CreditUserCustomerInitialSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateBySetup(CreditUserCustomerInitialSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.updateBySetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditUserCustomerInitialSetupService--updateBySetup--error:" + e.getMessage());
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
	public List<CreditUserCustomerInitialSetup> selectBySetup(CreditUserCustomerInitialSetup setup) {
		List<CreditUserCustomerInitialSetup> setupList = new ArrayList<CreditUserCustomerInitialSetup>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			setupList = setupDao.selectBySetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditUserCustomerInitialSetupService--selectBySetup--error:" + e.getMessage());
		}

		return setupList;
	}
	
}
