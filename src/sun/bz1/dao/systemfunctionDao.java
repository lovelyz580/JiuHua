package sun.bz1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import sun.TBase;
import sun.bz1.entity.systemfunction;


/**
 * 系统功能Dao层
 * 
 *  2018-10-7 
 *           -zz
 */
@Repository
public class systemfunctionDao {

	private Logger logger=Logger.getLogger(systemfunctionDao.class);
	
	
	
	/**
	 * 查询系统功能
	 * 
	 */
	public List<systemfunction> selectSystemFunction(SqlSession session,systemfunction systemfunction)
	{
		List<systemfunction> data=null;
		try{
			logger.info("systemfunctionDao");
			 data=session.selectList("sun.bz1.dao.mapper.systemfunctionMapper.selectText",systemfunction);
					
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return data;
	}
	
	/**
	 * 查询数量
	 */
	public Integer selectCount(SqlSession session,systemfunction systemfunction)
	{
		Integer count=0;
		try{
			logger.info("systemfunctionDao");
            count=session.selectOne("sun.bz1.dao.mapper.systemfunctionMapper.selectCount",systemfunction);
						
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return count;
	}
	
	
	
	/**
	 * 查询id包含的系统功能
	 * 
	 */
	public List<systemfunction> selectSystemFunctionMore(SqlSession session,List<Integer> systemfunctionSid)
	{
		List<systemfunction> data=null;
		try{
			logger.info("systemfunctionDao");
			 data=session.selectList("sun.bz1.dao.mapper.systemfunctionMapper.selectMore",systemfunctionSid);
					
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return data;
	}
	
	/**
	 * 查询id包含的系统功能数量
	 */
	public Integer selectSystemFunctionMoreCount(SqlSession session,List<Integer> systemfunctionSid)
	{
		Integer count=0;
		try{
			logger.info("systemfunctionDao");
            count=session.selectOne("sun.bz1.dao.mapper.systemfunctionMapper.selectMoreCount",systemfunctionSid);
						
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return count;
	}
	
	
	/**
	 * 新增系统功能
	 * 
	 */
	public Boolean insertSystemFunction(SqlSession session,systemfunction systemfunction)
	{
		Boolean bR=false;
		try{
			logger.info("systemfunctionDao");
			session.insert("sun.bz1.dao.mapper.systemfunctionMapper.addText",systemfunction);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	
	/**
	 * 修改系统功能
	 * 
	 */
	public Boolean updataSystemFunction(SqlSession session,systemfunction systemfunction)
	{
		Boolean bR=false;
		try{
			logger.info("systemfunctionDao");
			session.selectList("sun.bz1.dao.mapper.systemfunctionMapper.updataText",systemfunction);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	
	/**
	 * 删除系统功能
	 * 
	 */
	public Boolean delSystemFunction(SqlSession session,List<Integer> delList)
		{
			Boolean bR=false;
			try{
				logger.info("systemfunctionDao");
				session.delete("sun.bz1.dao.mapper.systemfunctionMapper.deletText",delList);
				bR=true;			
			}catch(Exception e)
			{
				logger.error(e.getMessage());			
			}
			
			return bR;
		}
	
	/**
	 * 查看多个功能
	 */
	
	public Boolean selectSystemFunctionMore(SqlSession session,List<systemfunction> data,TBase<Integer> tbase)
	{
		Boolean bR=false;
		try{
			logger.info("systemfunctionDao");
			List<systemfunction> _data=session.selectList("sun.bz1.dao.mapper.systemfunctionMapper.selectMore",tbase.getData());
			data.addAll(_data);
			
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	/**
	 * 查看多个功能的数量
	 */
	
	public Integer selectSystemFunctionMoreCount(SqlSession session,TBase<Integer> tbase)
	{
		Integer count=0;
		try{
			logger.info("systemfunctionDao");
			count=session.selectOne("sun.bz1.dao.mapper.systemfunctionMapper.selectMoreCount",tbase.getData());
				
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return count;
	}
}
