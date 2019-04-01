package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.DistributeConfirmDao;
import sun.bz1.entity.DistributeConfirm;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *派单确认表
 * 
 * Service
 * 
 * @author ZY on 2018/11/22
 */

@Service
public class DistributeConfirmService {

	@Autowired
	private DistributeConfirmDao distributeConfirmDao;

	private Logger logger = Logger.getLogger(DistributeConfirmService.class);

	/**
	 * 根据DistributeConfirm实体添加
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	public int insertByDistributeConfirm(DistributeConfirm distributeConfirm) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = distributeConfirmDao.insertByDistributeConfirm(session, distributeConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributeConfirmService--insertByDistributeConfirm--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DistributeConfirm实体更新
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	public int updateByDistributeConfirm(DistributeConfirm distributeConfirm) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = distributeConfirmDao.updateByDistributeConfirm(session, distributeConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributeConfirmService--updateByDistributeConfirm--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DistributeConfirm实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	public int selectCountByDistributeConfirm(DistributeConfirm distributeConfirm) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = distributeConfirmDao.selectCountByDistributeConfirm(session, distributeConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributeConfirmService--selectCountByDistributeConfirm--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DistributeConfirm实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	public List<DistributeConfirm> selectByDistributeConfirm(DistributeConfirm distributeConfirm) {
		List<DistributeConfirm> distributeConfirmList = new ArrayList<DistributeConfirm>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			distributeConfirmList = distributeConfirmDao.selectByDistributeConfirm(session, distributeConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributeConfirmService--selectByDistributeConfirm--error:" + e.getMessage());
		}

		return distributeConfirmList;
	}

	/**
	 * 根据DistributeConfirm实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	public int selectCountBySelectData(DistributeConfirm distributeConfirm) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = distributeConfirmDao.selectCountBySelectData(session, distributeConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributeConfirmService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据DistributeConfirm实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	public List<DistributeConfirm> selectBySelectData(DistributeConfirm distributeConfirm) {
		List<DistributeConfirm> distributeConfirmList = new ArrayList<DistributeConfirm>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			distributeConfirmList = distributeConfirmDao.selectBySelectData(session, distributeConfirm);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributeConfirmService--selectBySelectData--error:" + e.getMessage());
		}

		return distributeConfirmList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + distributeConfirmDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("DistributeConfirmService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
