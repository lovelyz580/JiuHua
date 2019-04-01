package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.CreditUserServiceInitialSetupDao;
import sun.bz1.entity.CreditUserServiceInitialSetup;
import util.MyBatisUtil;

/**
 * 信用值_用户_维修工、安装队_初始值_设置表
 * 
 * Service
 * 
 * @author WJF on 2018/09/04
 */

@Service
public class CreditUserServiceInitialSetupService {

	@Autowired
	private CreditUserServiceInitialSetupDao setupDao;

	private Logger logger = Logger.getLogger(CreditUserServiceInitialSetupService.class);
	
	/**
	 * 根据CreditUserServiceInitialSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateBySetup(CreditUserServiceInitialSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.updateBySetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditUserServiceInitialSetupService--updateBySetup--error:" + e.getMessage());
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
	public List<CreditUserServiceInitialSetup> selectBySetup(CreditUserServiceInitialSetup setup) {
		List<CreditUserServiceInitialSetup> setupList = new ArrayList<CreditUserServiceInitialSetup>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			setupList = setupDao.selectBySetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditUserServiceInitialSetupService--selectBySetup--error:" + e.getMessage());
		}

		return setupList;
	}
	
}
