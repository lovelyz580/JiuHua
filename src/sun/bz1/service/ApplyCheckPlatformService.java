package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.ApplyCheckDao;
import sun.bz1.dao.ApplyCheckPlatformDao;
import sun.bz1.entity.ApplyCheck;
import sun.bz1.entity.ApplyCheckAndOrderTableAndUser;
import sun.bz1.entity.ApplyCheckPlatform;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修验收表(验收成功或失败时在该表中添加数据)
 * 
 * Service
 * 
 * @author ZY on 2019/01/22
 */

@Service
public class ApplyCheckPlatformService {

	@Autowired
	private ApplyCheckPlatformDao applyCheckPlatformDao;

	private Logger logger = Logger.getLogger(ApplyCheckPlatformService.class);
	
	/**
   	 * 根据ApplyCheckPlatform实体添加
   	 * 
   	 * @param applyCheckPlatform
   	 * @return
   	 * 
   	 * @author ZY on 2019/01/22
   	 */
	public int insertByApplyCheckPlatform(ApplyCheckPlatform applyCheckPlatform) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = applyCheckPlatformDao.insertByApplyCheckPlatform(session, applyCheckPlatform);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckPlatformService--insertByApplyCheckPlatform--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ApplyCheckPlatform实体更新
	 * 
	 * @param applyCheckPlatform
	 * @return
	 * 
	 * @author ZY on 2019/01/22
	 */
	public int updateByApplyCheckPlatform(ApplyCheckPlatform applyCheckPlatform) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = applyCheckPlatformDao.updateByApplyCheckPlatform(session, applyCheckPlatform);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckPlatformService--updateByApplyCheckPlatform--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2019/01/22
	 */
	public int selectThreeTablesCountByUnionData(ApplyCheckAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = applyCheckPlatformDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckPlatformService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2019/01/22
	 */
	public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesByUnionData(ApplyCheckAndOrderTableAndUser unionData) {
		List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			applyCheckList = applyCheckPlatformDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckPlatformService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return applyCheckList;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表查询 申请时间倒序
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/11/16
	 */
	public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesByUnionDataByTimeDesc(ApplyCheckAndOrderTableAndUser unionData) {
		List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			applyCheckList = applyCheckPlatformDao.selectThreeTablesByUnionDataByTimeDesc(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckPlatformService--selectThreeTablesByUnionDataByTimeDescselectThreeTablesByUnionDataByTimeDesc--error:" + e.getMessage());
		}

		return applyCheckList;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表模糊查询
	 * 
   	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2019/01/22
	 */
	public int selectThreeTablesCountBySelectData(ApplyCheckAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = applyCheckPlatformDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckPlatformService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ApplyCheckAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2019/01/22
	 */
	public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesBySelectData(ApplyCheckAndOrderTableAndUser unionData) {
		List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			applyCheckList = applyCheckPlatformDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckPlatformService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return applyCheckList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2019/01/22
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + applyCheckPlatformDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckPlatformService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author ZY on 2019/01/22
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				ApplyCheckPlatform applyCheckPlatform = new ApplyCheckPlatform();
				applyCheckPlatform.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + applyCheckPlatformDao.deleteByApplyCheckPlatform(session, applyCheckPlatform);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckPlatformService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
