package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.TBase;
import sun.bz1.entity.department;
import util.MyBatisUtil;


/**
 * 部门控制层
 * @author Administrator
 *
 */
@Service
public class departmentService {
	private Logger logger=Logger.getLogger(systemfunctionService.class);
	@Autowired
    private	sun.bz1.dao.departmentDao departmentDao;
	
	/**
	 * 部门查询
	 * @param Tbase
	 * @return
	 */
	public List<department> selectDepartment(department department){
		List<department> data=null;
		try {
			
		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
           data=departmentDao.selectDepartment(session, department);

            session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return data;
		
	}
	
	/**
	 * 部门数量查询
	 * @param Tbase
	 * @return
	 */
	public Integer selectDepartmentCount(department department){
		Integer count=0;
	try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
            count=departmentDao.selectDepartmentCount(session, department);

            session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return count;
		
	}
	
	/**
	 * 部门新增
	 * @param tbase
	 * @return
	 */
	public boolean insertDepartment(department department)
	{ 
		Boolean bR = false;
		try {
			
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			departmentDao.insertDepartment(session, department);
            session.commit();
			session.close();

			bR = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return bR;
		
	}
	
	/**
	 * 部门修改
	 * @param tbase
	 * @return
	 */
	public boolean updataDepartment(department department)
	{ 
		Boolean bR = false;
		try {
			
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			departmentDao.updataDepartment(session, department);
            session.commit();
			session.close();

			bR = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return bR;
		
	}
	/**
	 * 部门删除
	 * @param tbase
	 * @return
	 */
	public Boolean delDsepartment(List<Integer> delList)
	{ 
		Boolean bR = false;
		try {
			
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			departmentDao.delDsepartment(session, delList);
            session.commit();
			session.close();

			bR = true;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return bR;
		
	}
}
