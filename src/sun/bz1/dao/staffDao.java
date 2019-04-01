package sun.bz1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import sun.bz1.entity.staff;


/**
 * 人员
 * @author Administrator
 *
 */
@Repository
public class staffDao {
     private Logger logger=Logger.getLogger(staffDao.class);
	
     
     /**
	 * 查询对应部门的员工
	 */
	public List<staff> selectStaff(SqlSession session,staff staff)
	{
		List<staff> _data=null;
		try{
			logger.info("staffDao");
			_data=session.selectList("sun.bz1.dao.mapper.staffMapper.selectText",staff);
			
			
					
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return _data;
	}
	
	/**
	 * 查询对应部门员工的数量
	 */
	public Integer selectStaffCount(SqlSession session,staff staff)
	{
		Integer count=0;
		try{
			logger.info("staffDao");
			count=session.selectOne("sun.bz1.dao.mapper.staffMapper.selectTextCount",staff);
						
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return count;
	}
	/**
	 * 新增对应部门员工
	 */
	public Boolean insertStaff(SqlSession session,staff staff)
	{
		Boolean bR=false;
		try{
			logger.info("staffDao");
			session.insert("sun.bz1.dao.mapper.staffMapper.addText",staff);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	
	/**
	 * 修改对应部门员工
	 */
	public Boolean updataStaff(SqlSession session,staff staff)
	{
		Boolean bR=false;
		try{
			logger.info("staffDao");
			session.selectList("sun.bz1.dao.mapper.staffMapper.updataText",staff);
			bR=true;			
		}catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		
		return bR;
	}
	/**
	 * 删除对应部门员工
	 */
		public Boolean delStaff(SqlSession session,List<Integer> delList)
		{
			Boolean bR=false;
			try{
				logger.info("staffDao");
				session.delete("sun.bz1.dao.mapper.staffMapper.deletText", delList);
				bR=true;			
			}catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			
			return bR;
		}
}
