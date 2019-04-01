package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.TBase;
import sun.bz1.entity.role;
import sun.bz1.entity.roleanddepartment;


/**
 * 角色和部门关联表
 * @author Administrator
 *
 */

@Repository
public class roleanddepartmentDao {
	private Logger logger=Logger.getLogger(systemfunctionandroleDao.class);
	
	
	
	/**
	 * 获取全部关系
	 * @param session
	 * @param data
	 * @param tbase
	 * @return
	 */
	public List<roleanddepartment> selectRoleanddepartment(SqlSession session,roleanddepartment roleanddepartment)
	{
		List<roleanddepartment> data=new ArrayList<roleanddepartment>();
		try{
			logger.info("roleanddepartmentDao");
			data=session.selectList("sun.bz1.dao.mapper.roleanddepartmentMapper.selectText",roleanddepartment);
						
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return data;
	}
	
	/**
	 * 获取全部关系的数量
	 * @param session
	 * @param data
	 * @param tbase
	 * @return
	 */
	public Integer selectRoleanddepartmentCount(SqlSession session,roleanddepartment roleanddepartment)
	{
		Integer count=0;
		try{
			logger.info("roleanddepartmentDao");
			count=session.selectOne("sun.bz1.dao.mapper.roleanddepartmentMapper.selectTextCount",roleanddepartment);
						
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return count;
	}
	
	
    /**
     * 关系表的新增
     * @param session
     * @param tbase
     * @return
     */
	
	public Boolean insertRoleanddepartment(SqlSession session,List<roleanddepartment> roleanddepartmentList)
	{
		Boolean bR=false;
		try{
			logger.info("roleanddepartmentDao");
			session.insert("sun.bz1.dao.mapper.roleanddepartmentMapper.addText",roleanddepartmentList);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	
	/**
     * 关系表的删除
     * @param session
     * @param tbase
     * @return
     */
	
	public Boolean deleteRoleanddepartment(SqlSession session,roleanddepartment roleanddepartment)
	{
		Boolean bR=false;
		try{
			logger.info("roleanddepartmentDao");
			
			session.delete("sun.bz1.dao.mapper.roleanddepartmentMapper.deletText",roleanddepartment);
			
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	
}
