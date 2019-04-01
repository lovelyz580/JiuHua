package sun.bz1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.TBase;
import sun.bz1.entity.department;


/**
 * 部门
 * @author Administrator
 *
 */

@Repository
public class departmentDao {

private Logger logger=Logger.getLogger(departmentDao.class);

/**
 * 部门查询
 * 
 */
	public List<department> selectDepartment(SqlSession session,department department)
	{
		List<department> data=null;
		try{
			logger.info("departmentDao");
			data=session.selectList("sun.bz1.dao.mapper.departmentMapper.selectText",department);
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return data;
	}
	

/**
 * 部门数量查询
 * 
 */
	public Integer selectDepartmentCount(SqlSession session,department department)
	{
		Integer count=0;
		try{
			logger.info("departmentDao");
			count=session.selectOne("sun.bz1.dao.mapper.departmentMapper.selectTextCount",department);
					
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return count;
	}
	
	/**
	 * 部门新增
	 * 
	 */
	public Boolean insertDepartment(SqlSession session,department department)
	{
		Boolean bR=false;
		try{
			logger.info("departmentDao");
			session.insert("sun.bz1.dao.mapper.departmentMapper.addText",department);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	/**
	 * 部门修改
	 * 
	 */
	public Boolean updataDepartment(SqlSession session,department department)
	{
		Boolean bR=false;
		try{
			logger.info("departmentDao");
			session.update("sun.bz1.dao.mapper.departmentMapper.updataText",department);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	   /**
	    * 部门删除
	    * 
	    */
		public Boolean delDsepartment(SqlSession session,List<Integer> delList)
		{
			Boolean bR=false;
			try{
				logger.info("departmentDao");
				session.delete("sun.bz1.dao.mapper.departmentMapper.deletText",delList);
				bR=true;			
			}catch(Exception e)
			{
				logger.error(e.getMessage());			
			}
			
			return bR;
		}
}
