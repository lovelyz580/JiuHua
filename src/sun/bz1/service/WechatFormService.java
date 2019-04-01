package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.WechatFormDao;
import sun.bz1.entity.Goods;
import sun.bz1.entity.WechatForm;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修单详情记录表
 * 
 * Service
 * 
 * @author ZY on 2018/11/08
 */

@Service
public class WechatFormService {
	
	@Autowired
	private WechatFormDao wechatFormDao;

	private Logger logger = Logger.getLogger(WechatFormService.class);
	
	/**
   	 * 根据WechatForm实体添加
   	 * 
   	 * @param wechatForm
   	 * @return
   	 * 
   	 * @author ZY on 2018/11/08
   	 */
	public int insertByWechatForm(WechatForm wechatForm) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = wechatFormDao.insertByWechatForm(session, wechatForm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatFormService--insertByWechatForm--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据WechatForm实体更新
	 * 
	 * @param wechatForm
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	public int updateByWechatForm(WechatForm wechatForm) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = wechatFormDao.updateByWechatForm(session, wechatForm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatFormService--updateByWechatForm--error:" + e.getMessage());
		}

		return num;
	}



	/**
	 * 根据WechatForm实体查询
	 *
	 * 查询数量
	 *
	 * @param wechatForm
	 * @return
	 *
	 * @author WJF on 2018/09/04
	 */
	public int selectCountByWechatForm(WechatForm wechatForm) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = wechatFormDao.selectCountByWechatForm(session, wechatForm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatFormService--selectCountByWechatForm--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据WechatForm实体查询
	 * 
	 * @param wechatForm
	 * @return
	 * 
	 * @author ZY on 2018/11/08
	 */
	public List<WechatForm> selectByWechatForm(WechatForm wechatForm) {
		List<WechatForm> wechatFormList = new ArrayList<WechatForm>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			wechatFormList = wechatFormDao.selectByWechatForm(session, wechatForm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatFormService--selectByWechatForm--error:" + e.getMessage());
		}

		return wechatFormList;
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
				deleteNum = deleteNum + wechatFormDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatFormService--deleteByIdList--error:" + e.getMessage());
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
				WechatForm wechatForm = new WechatForm();
				wechatForm.setId(orderIdList.get(i));
				
				deleteNum = deleteNum + wechatFormDao.deleteByWechatForm(session, wechatForm);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("WechatFormService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}*/

}
