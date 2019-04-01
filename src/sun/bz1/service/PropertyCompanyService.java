package sun.bz1.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.bz1.dao.BuildingTypeDao;
import sun.bz1.dao.PropertyCompanyDao;
import sun.bz1.entity.BuildingType;
import sun.bz1.entity.PropertyCompany;
import util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 地产公司表
 * 
 * Service
 * 
 * @author ZY on 2019/01/09
 */

@Service
public class PropertyCompanyService {

	@Autowired
	private PropertyCompanyDao propertyCompanyDao;

	private Logger logger = Logger.getLogger(PropertyCompanyService.class);

	/**
	 * 根据PropertyCompany实体添加
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	public int insertByPropertyCompany(PropertyCompany propertyCompany) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = propertyCompanyDao.insertByPropertyCompany(session, propertyCompany);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PropertyCompanyService--insertByPropertyCompany--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据PropertyCompany实体更新
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	public int updateByPropertyCompany(PropertyCompany propertyCompany) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = propertyCompanyDao.updateByPropertyCompany(session, propertyCompany);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PropertyCompanyService--updateByPropertyCompany--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据PropertyCompany实体查询
	 * 
	 * 查询数量
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	public int selectCountByPropertyCompany(PropertyCompany propertyCompany) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = propertyCompanyDao.selectCountByPropertyCompany(session, propertyCompany);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PropertyCompanyService--selectCountByPropertyCompany--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据PropertyCompany实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	public List<PropertyCompany> selectByPropertyCompany(PropertyCompany propertyCompany) {
		List<PropertyCompany> propertyCompanyList = new ArrayList<PropertyCompany>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			propertyCompanyList = propertyCompanyDao.selectByPropertyCompany(session, propertyCompany);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PropertyCompanyService--selectByPropertyCompany--error:" + e.getMessage());
		}

		return propertyCompanyList;
	}

	/**
	 * 根据PropertyCompany实体模糊查询
	 * 
	 * 查询数量
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	public int selectCountBySelectData(PropertyCompany propertyCompany) {
		int num = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			num = propertyCompanyDao.selectCountBySelectData(session, propertyCompany);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PropertyCompanyService--selectCountBySelectData--error:" + e.getMessage());
		}

		return num;
	}

	/**
	 * 根据PropertyCompany实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	public List<PropertyCompany> selectBySelectData(PropertyCompany propertyCompany) {
		List<PropertyCompany> propertyCompanyList = new ArrayList<PropertyCompany>();

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			propertyCompanyList = propertyCompanyDao.selectBySelectData(session, propertyCompany);

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PropertyCompanyService--selectBySelectData--error:" + e.getMessage());
		}

		return propertyCompanyList;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param list
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	public int deleteByIdList(List<Integer> list) {
		int deleteNum = 0;

		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();

			for (int i = 0; i < list.size(); i++) {
				deleteNum = deleteNum + propertyCompanyDao.deleteByPrimaryKey(session, list.get(i));
			}

			session.commit();
			session.close();
		} catch (Exception e) {
			logger.error("PropertyCompanyService--deleteByIdList--error:" + e.getMessage());
		}

		return deleteNum;
	}

}
