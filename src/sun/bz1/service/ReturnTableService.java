package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.dao.ReturnTableDao;
import sun.bz1.entity.ReturnTable;
import sun.bz1.entity.ReturnTableAndOrderTableAndUser;
import util.MyBatisUtil;

/**
 * 维修人员退回维修单表(维修人员退回维修单时在该表中添加数据)
 * 
 * Service
 * 
 * @author ZY on 2018/09/08
 */

@Service
public class ReturnTableService {

	@Autowired
	private ReturnTableDao returnTableDao;

	private Logger logger = Logger.getLogger(ReturnTableService.class);

	/**
	 * 根据ReturnTable实体添加
	 * 
	 * @param returnTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int insertByReturnTable(ReturnTable returnTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = returnTableDao.insertByReturnTable(session, returnTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ReturnTableService--insertByReturnTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ReturnTable实体更新
	 * 
	 * @param returnTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int updateByReturnTable(ReturnTable returnTable) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = returnTableDao.updateByReturnTable(session, returnTable);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ReturnTableService--updateByReturnTable--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ReturnTableAndOrderTable实体联表查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int selectThreeTablesCountByUnionData(ReturnTableAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = returnTableDao.selectThreeTablesCountByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ReturnTableService--selectThreeTablesCountByUnionData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ReturnTableAndOrderTableAndUser实体联表查询
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
	public List<ReturnTableAndOrderTableAndUser> selectThreeTablesByUnionData(
			ReturnTableAndOrderTableAndUser unionData) {
		List<ReturnTableAndOrderTableAndUser> returnTableList = new ArrayList<ReturnTableAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			returnTableList = returnTableDao.selectThreeTablesByUnionData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ReturnTableService--selectThreeTablesByUnionData--error:" + e.getMessage());
		}

		return returnTableList;
	}

	/**
	 * 根据ReturnTableAndOrderTableAndUser实体联表模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	public int selectThreeTablesCountBySelectData(ReturnTableAndOrderTableAndUser unionData) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = returnTableDao.selectThreeTablesCountBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ReturnTableService--selectThreeTablesCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据ReturnTableAndOrderTableAndUser实体联表模糊查询
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
	public List<ReturnTableAndOrderTableAndUser> selectThreeTablesBySelectData(ReturnTableAndOrderTableAndUser unionData) {
		List<ReturnTableAndOrderTableAndUser> returnTableList = new ArrayList<ReturnTableAndOrderTableAndUser>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			returnTableList = returnTableDao.selectThreeTablesBySelectData(session, unionData);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ReturnTableService--selectThreeTablesBySelectData--error:" + e.getMessage());
		}

		return returnTableList;
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
				deleteNum = deleteNum + returnTableDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ReturnTableService--deleteByIdList--error:" + e.getMessage());
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
	public int deleteByOrderIdOrReturnUserIdList(List<String> orderIdList) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int j = 0; j < orderIdList.size(); j++) {
				ReturnTable returnTable = new ReturnTable();
				returnTable.setOrderid(orderIdList.get(j));
				
				deleteNum = deleteNum + returnTableDao.deleteByReturnTable(session, returnTable);
			}
			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("ReturnTableService--deleteByOrderIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
