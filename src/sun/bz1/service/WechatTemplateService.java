package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.WechatTemplateDao;
import sun.bz1.entity.WechatTemplate;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信服务通知模板表
 * 
 * Service
 * 
 * @author ZY on 2018/11/13
 */

@Service
public class WechatTemplateService {
	
	@Autowired
	private WechatTemplateDao wechatTemplateDao;

	private Logger logger = Logger.getLogger(WechatTemplateService.class);
	
	/**
   	 * 根据WechatTemplate实体添加
   	 * 
   	 * @param wechatTemplate
   	 * @return
   	 * 
   	 * @author ZY on 2018/11/08
   	 */
	public int insertByWechatTemplate(WechatTemplate wechatTemplate) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = wechatTemplateDao.insertByWechatTemplate(session, wechatTemplate);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatTemplateService--insertByWechatTemplate--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据WechatTemplate实体更新
	 * 
	 * @param wechatTemplate
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	public int updateByWechatTemplate(WechatTemplate wechatTemplate) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = wechatTemplateDao.updateByWechatTemplate(session, wechatTemplate);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatTemplateService--updateByWechatTemplate--error:" + e.getMessage());
		}

		return num;
	}



	/**
	 * 根据WechatTemplate实体查询
	 *
	 * 查询数量
	 *
	 * @param wechatTemplate
	 * @return
	 *
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByWechatTemplate(WechatTemplate wechatTemplate) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = wechatTemplateDao.selectCountByWechatTemplate(session, wechatTemplate);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatTemplateService--selectCountByWechatTemplate--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据WechatTemplate实体查询
	 * 
	 * @param wechatTemplate
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	public List<WechatTemplate> selectByWechatTemplate(WechatTemplate wechatTemplate) {
		List<WechatTemplate> wechatTemplateList = new ArrayList<WechatTemplate>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			wechatTemplateList = wechatTemplateDao.selectByWechatTemplate(session, wechatTemplate);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatTemplateService--selectByWechatTemplate--error:" + e.getMessage());
		}

		return wechatTemplateList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + wechatTemplateDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatTemplateService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param idList
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	/*public int deleteByIdList(List<Integer> idList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				WechatTemplate wechatTemplate = new WechatTemplate();
				wechatTemplate.setId(orderIdList.get(i));
				
				deleteNum = deleteNum + wechatTemplateDao.deleteByWechatTemplate(session, wechatTemplate);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatTemplateService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}*/

}
