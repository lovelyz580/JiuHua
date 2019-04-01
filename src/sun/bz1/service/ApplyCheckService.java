package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.ApplyCheckDao;
import sun.bz1.entity.ApplyCheck;
import sun.bz1.entity.ApplyCheckAndOrderTableAndUser;
import util.MyBatisUtil;

/**
 * 维修验收表(验收成功或失败时在该表中添加数据)
 * 
 * Service
 * 
 * @author WJF on 2018/09/08
 */

@Service
public class ApplyCheckService {

	@Autowired
	private ApplyCheckDao applyCheckDao;

	private Logger logger = Logger.getLogger(ApplyCheckService.class);
	
	/**
   	 * 根据ApplyCheck实体添加
   	 * 
   	 * @param applyCheck
   	 * @return
   	 * 
   	 * @author WJF on 2018/09/08
   	 */
	public int insertByApplyCheck(ApplyCheck applyCheck) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = applyCheckDao.insertByApplyCheck(session, applyCheck);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckService--insertByApplyCheck--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ApplyCheck实体更新
	 * 
	 * @param applyCheck
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int updateByApplyCheck(ApplyCheck applyCheck) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = applyCheckDao.updateByApplyCheck(session, applyCheck);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckService--updateByApplyCheck--error:" + e.getMessage());
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
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(ApplyCheckAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = applyCheckDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
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
	 * @author WJF on 2018/09/08
	 */
	public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesByUnionData(ApplyCheckAndOrderTableAndUser unionData) {
		List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			applyCheckList = applyCheckDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckService--selectThreeTablesByUnionData--error:" + e.getMessage());
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

			applyCheckList = applyCheckDao.selectThreeTablesByUnionDataByTimeDesc(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckService--selectThreeTablesByUnionDataByTimeDescselectThreeTablesByUnionDataByTimeDesc--error:" + e.getMessage());
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
	 * @author WJF on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(ApplyCheckAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = applyCheckDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
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
	 * @author WJF on 2018/09/08
	 */
	public List<ApplyCheckAndOrderTableAndUser> selectThreeTablesBySelectData(ApplyCheckAndOrderTableAndUser unionData) {
		List<ApplyCheckAndOrderTableAndUser> applyCheckList = new ArrayList<ApplyCheckAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			applyCheckList = applyCheckDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return applyCheckList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + applyCheckDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author WJF on 2018/09/08
	 */
	public int deleteByOrderIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				ApplyCheck applyCheck = new ApplyCheck();
				applyCheck.setOrderid(orderIdList.get(i));
				
				deleteNum = deleteNum + applyCheckDao.deleteByApplyCheck(session, applyCheck);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ApplyCheckService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}
	
}
