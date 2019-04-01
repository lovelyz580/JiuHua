package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.TBase;
import sun.bz1.entity.department;
import sun.bz1.entity.staff;
import util.MyBatisUtil;

/**
 * 人员业务层
 * @author Administrator
 *
 */
@Service
public class staffService {
	private Logger logger=Logger.getLogger(staffService.class);
	@Autowired
	private sun.bz1.dao.staffDao staffDao;
	
	/**
	 * 查询人员
	 */
	public List<staff> selectStaff(staff staff)
	{
		List<staff> data = null;
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
            
            data=staffDao.selectStaff(session,staff);
            session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return data;
		
	}
	/**
	 * 查询人员数量
	 */
	public Integer selectStaffCount(staff staff)
	{
		Integer count = 0;
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
            
			count=staffDao.selectStaffCount(session,staff);
            session.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return count;
		
	}
	
	/**
	 * 新增人员
	 * @param staff
	 * @return
	 */
    public boolean insertStaff(staff staff)
	{ 
		Boolean bR = false;
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			staffDao.insertStaff(session, staff);
            session.commit();
			session.close();
            bR = true;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bR;
		
	}
    
    /**
	 * 修改人员
	 * @param staff
	 * @return
	 */
    public boolean updataStaff(staff staff)
	{ 
		Boolean bR = false;
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			staffDao.updataStaff(session, staff);
            session.commit();
			session.close();
            bR = true;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return bR;
		
	}
	/**
	 * 删除人员
	 * @param tbase
	 * @return
	 */
	public Boolean delStaff(List<Integer> delList)
	{ 
		Boolean bR = false;
		try {
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			staffDao.delStaff(session, delList);
            session.commit();
			session.close();
            bR = true;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return bR;
		
	}
}
