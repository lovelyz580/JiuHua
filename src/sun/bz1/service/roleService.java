package sun.bz1.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.bz1.entity.role;
import util.MyBatisUtil;

/**
 *角色业务层
 * @author Administrator
 *
 */
@Service
public class roleService {

	private Logger logger=Logger.getLogger(roleService.class);
	@Autowired
	sun.bz1.dao.roleDao  roleDao;
	
	/**
	 * 查询角色
	 * @param tbase
	 * @return
	 */
	public List<role> selectRole(role role)
	{
		List<role> data=null;
		try {
			
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
            
            data=roleDao.selectRole(session,role);
            
            session.commit();
			session.close();
        } catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return data;
		
	}
	
	/**
	 * 查询角色数量
	 * @param tbase
	 * @return
	 */
	public Integer selectRoleCount(role role)
	{
		Integer count=0;
		try {
			
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			count=roleDao.selectRoleCount(session,role);
            session.commit();
			session.close();
        } catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return count;
		
	}
	
	/**
	 * 查询id包含的角色
	 * @param tbase
	 * @return
	 */
	public List<role> selectRoleMore(List<Integer> roleRid)
	{
		List<role> data=null;
		try {
			
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
            
            data=roleDao.selectRoleMore(session,roleRid);
            
            session.commit();
			session.close();
        } catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return data;
		
	}

/**
 *新增角色 
 *
 */
	public Boolean insertRole(role role)
	{ 
		Boolean bR = false;
		try {
			
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			roleDao.insertRole(session, role);
            session.commit();
			session.close();

			bR = true;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return bR;
		
	}
	
	
	/**
	 *修改角色 
	 * @param tbase
	 * @return
	 */
		public Boolean updataRole(role role)
		{ 
			Boolean bR = false;
			try {
				
				SqlSession session = MyBatisUtil.getInstance().getSqlSession();
				roleDao.updataRole(session, role);
	            session.commit();
				session.close();

				bR = true;
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			return bR;
			
		}
	
	
	
	/**
	 * 删除角色
	 * @param tbase
	 * @return
	 */
	public Boolean delRole(List<Integer> delList)
	{ 
		Boolean bR = false;
		try {
			
			SqlSession session = MyBatisUtil.getInstance().getSqlSession();
			roleDao.delRole(session,delList);
            session.commit();
			session.close();

			bR = true;
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return bR;
		
	}

}
