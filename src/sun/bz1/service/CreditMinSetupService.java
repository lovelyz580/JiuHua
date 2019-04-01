package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.CreditMinSetupDao;
import sun.bz1.entity.CreditMinSetup;
import util.MyBatisUtil;

/**
 * 信用值_用户_客户_初始值_设置表
 * 
 * Service
 * 
 * @author WJF on 2018/09/04
 */

@Service
public class CreditMinSetupService {

	@Autowired
	private CreditMinSetupDao setupDao;

	private Logger logger = Logger.getLogger(CreditMinSetupService.class);
	
	/**
	 * 根据CreditMinSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	public int updateBySetup(CreditMinSetup setup) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = setupDao.updateBySetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditMinSetupService--updateBySetup--error:" + e.getMessage());
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
	public List<CreditMinSetup> selectBySetup(CreditMinSetup setup) {
		List<CreditMinSetup> setupList = new ArrayList<CreditMinSetup>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			setupList = setupDao.selectBySetup(session, setup);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CreditMinSetupService--selectBySetup--error:" + e.getMessage());
		}

		return setupList;
	}
	
}
