package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.CancelTableDao;
import sun.bz1.entity.CancelTable;
import sun.bz1.entity.CancelTableAndOrderTableAndUser;
import util.MyBatisUtil;

/**
 * 客户取消维修单表(客户取消维修单时在该表中添加数据)
 * 
 * Service
 * 
 * @author ZY on 2018/09/08
 */

@Service
public class CancelTableService {

	@Autowired
	private CancelTableDao cancelTableDao;

	private Logger logger = Logger.getLogger(CancelTableService.class);

	/**
	 * 根据CancelTable实体添加
	 * 
	 * @param cancelTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int insertByCancelTable(CancelTable cancelTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = cancelTableDao.insertByCancelTable(session, cancelTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CancelTableService--insertByCancelTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CancelTable实体更新
	 * 
	 * @param cancelTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int updateByCancelTable(CancelTable cancelTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = cancelTableDao.updateByCancelTable(session, cancelTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CancelTableService--updateByCancelTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CancelTableAndOrderTable实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(CancelTableAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = cancelTableDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CancelTableService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CancelTableAndOrderTableAndUser实体联表查询
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
	 * @author ZY on 2018/09/08
	 */
	public List<CancelTableAndOrderTableAndUser> selectThreeTablesByUnionData(
			CancelTableAndOrderTableAndUser unionData) {
		List<CancelTableAndOrderTableAndUser> cancelTableList = new ArrayList<CancelTableAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			cancelTableList = cancelTableDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CancelTableService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return cancelTableList;
	}

	/**
	 * 根据CancelTableAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(CancelTableAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = cancelTableDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CancelTableService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据CancelTableAndOrderTableAndUser实体联表模糊查询
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
	 * @author ZY on 2018/09/08
	 */
	public List<CancelTableAndOrderTableAndUser> selectThreeTablesBySelectData(
			CancelTableAndOrderTableAndUser unionData) {
		List<CancelTableAndOrderTableAndUser> cancelTableList = new ArrayList<CancelTableAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			cancelTableList = cancelTableDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CancelTableService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return cancelTableList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + cancelTableDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CancelTableService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

	/**
	 * 根据 维修单ID List 删除信息
	 * 
	 * @param orderIdList
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int deleteByOrderIdOrCancelUserIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < orderIdList.size(); i++) {
				CancelTable cancelTable = new CancelTable();
				cancelTable.setOrderid(orderIdList.get(i));
				deleteNum = deleteNum + cancelTableDao.deleteByCancelTable(session, cancelTable);
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("CancelTableService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
