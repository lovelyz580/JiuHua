package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.bz1.entity.role;

/**
 * 角色
 * @author Administrator
 *
 */

@Repository
public class roleDao {

private Logger logger=Logger.getLogger(roleDao.class);
    
    /**
	 * 查看角色表
	 */
	public List<role> selectRole(SqlSession session,role role)
	{
		List<role> data=null;
		try{
			logger.info("roleDao");
			data=session.selectList("sun.bz1.dao.mapper.roleMapper.selectText",role);
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return data;
	}
	
	
	
	/**
	 * 查看角色数量
	 */
	public Integer selectRoleCount(SqlSession session,role role)
	{
		Integer count=0;
		try{
			logger.info("roleDao");
			count=session.selectOne("sun.bz1.dao.mapper.roleMapper.selectCount",role);
						
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return count;
	}
	
	/**
	 * 查看包含id的角色
	 */
	public List<role> selectRoleMore(SqlSession session,List<Integer> roleRid)
	{
		List<role> data=null;
		try{
			logger.info("roleDao");
			data=session.selectList("sun.bz1.dao.mapper.roleMapper.selectMore",roleRid);
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		return data;
	}
	

	/**
	 * 新增角色表
	 */
	public Boolean insertRole(SqlSession session,role role)
	{
		Boolean bR=false;
		try{
			logger.info("roleDao");
			session.insert("sun.bz1.dao.mapper.roleMapper.addText",role);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	/**
	 * 修改角色表
	 */
	public Boolean updataRole(SqlSession session,role role)
	{
		Boolean bR=false;
		try{
			logger.info("roleDao");
			session.update("sun.bz1.dao.mapper.roleMapper.updataText",role);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	
	
	
	
	/**
	 * 删除角色表
	 */
	 public Boolean delRole(SqlSession session,List<Integer> delList)
		{
			Boolean bR=false;
			try{
				logger.info("roleDao");
				session.delete("sun.bz1.dao.mapper.roleMapper.deletText",delList);
				bR=true;			
			}catch(Exception e)
			{
				logger.error(e.getMessage());			
			}
			return bR;
		}
		
		
}
