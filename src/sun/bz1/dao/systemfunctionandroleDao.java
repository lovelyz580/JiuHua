package sun.bz1.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.TBase;
import sun.bz1.entity.systemfunction;
import sun.bz1.entity.systemfunctionandrole;

/**
 * 角色和功能关联表
 * @author Administrator
 *
 */
@Repository
public class systemfunctionandroleDao {

	

	private Logger logger=Logger.getLogger(systemfunctionandroleDao.class);
	
	
	/**
	 * 查询关联表
	 * 
	 */
	public List<systemfunctionandrole> selectSystemfunctionandrole(SqlSession session,systemfunctionandrole systemfunctionandrole)
	{
		List<systemfunctionandrole> _data= new ArrayList<systemfunctionandrole>();
		try{
			logger.info("systemfunctionandroleDao");
			 _data=session.selectList("sun.bz1.dao.mapper.systemfunctionandroleMapper.selectText",systemfunctionandrole);
						
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return _data;
	}
	
	/**
	 * 查询关联表数量
	 * 
	 */
	public Integer selectSystemfunctionandroleCount(SqlSession session,systemfunctionandrole systemfunctionandrole)
	{
		Integer count=0;
		try{
			logger.info("systemfunctionandroleDao");
			count=session.selectOne("sun.bz1.dao.mapper.systemfunctionandroleMapper.selectTextCount",systemfunctionandrole);
						
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return count;
	}
	
	
	/**
	 * 新增角色和功能对应关系
	 * 
	 */
	
	public Boolean insertSystemfunctionandrole(SqlSession session,List<systemfunctionandrole> systemfunctionandrole)
	{
		Boolean bR=false;
		try{
			logger.info("systemfunctionandroleDao");
			session.insert("sun.bz1.dao.mapper.systemfunctionandroleMapper.addText",systemfunctionandrole);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	
	/**
	 * 删除角色和功能对应关系
	 * 
	 */
	public Boolean deleteSystemfunctionandrole(SqlSession session,systemfunctionandrole systemfunctionandrole)
		{
			Boolean bR=false;
			try{
				logger.info("systemfunctionandroleDao");
				session.delete("sun.bz1.dao.mapper.systemfunctionandroleMapper.deletText",systemfunctionandrole);
				
				
				bR=true;			
			}catch(Exception e)
			{
				logger.error(e.getMessage());			
			}
			
			return bR;
		}
}
