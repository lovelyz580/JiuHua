package sun.bz1.restful;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.roleanddepartment;
import sun.bz1.entity.roleandstaff;
import sun.bz1.entity.staff;
import sun.bz1.entity.systemfunction;
import sun.bz1.entity.systemfunctionandrole;



@Controller
@RequestMapping("/staff")
public class staffPage {
	
	@Autowired
	private sun.bz1.service.staffService staffService;
	@Autowired
	private sun.bz1.service.roleanddepartmentService roleanddepartmentService;
	@Autowired
	private sun.bz1.service.roleandstaffService roleandstaffService;
	@Autowired
	private sun.bz1.service.systemfunctionandroleService systemfunctionandroleService;
	@Autowired
	private sun.bz1.service.systemfunctionService systemfunctionService;
	
	/**
	 * 人员查看
	 */
	@ResponseBody
	@RequestMapping("/staffSelect")
	public  LayuiDataTemplet<staff> selectStaff(@RequestBody staff staff){
		LayuiDataTemplet<staff> lay=new LayuiDataTemplet<staff>();
		//查询数量
		Integer count=staffService.selectStaffCount(staff);
		lay.setCount(count);
		if(count>0){
			//分页
			if(staff.getPagenumber()!=-1){
				int s=staff.getPagesize();
				int n=staff.getPagenumber();
				staff.setPagenumber((n-1)*s);
			}
			lay.setData(staffService.selectStaff(staff));
			lay.setMsg("查询成功");
		}else{
			lay.setMsg("暂无人员数据");
		}
		
		return lay;
	}
	
	
	
	/**
	 * 人员新增
	 */
	@ResponseBody
	@RequestMapping("/staffInsert")
	public LayuiDataTemplet<staff> insertStaff(@RequestBody staff staff)
	{
		LayuiDataTemplet<staff> lay=new LayuiDataTemplet<staff>();
		//新增人员表
		Boolean a=staffService.insertStaff(staff);
		Boolean b=false;
		//查询刚添加的人员 取出id
		staff.setPagenumber(-1);
		List<staff> staffList=staffService.selectStaff(staff);
		Integer staffId=staffList.get(0).getId();
		//如果部门不为空 那么需要给人员添加部门的角色 
		roleanddepartment roleanddepartment=new roleanddepartment();
		roleanddepartment.setPagenumber(-1);
		roleanddepartment.setDid(staff.getDid());
		//查询部门角色 
		List<roleanddepartment> rd=roleanddepartmentService.selectRoleanddepartment(roleanddepartment);
		if(rd.size()!=0){
			//重新组成关系表的List
			List<roleandstaff> roleandstaffList=new ArrayList<roleandstaff>();
			for(int i=0;i<rd.size();i++){
					roleandstaff e=new roleandstaff();
					e.setPid(staffId);
					e.setRid(rd.get(i).getRid());
					roleandstaffList.add(e);
			}
			//给人员添加部门的角色
			b=roleandstaffService.insertRoleandstaff(roleandstaffList);
		}else{
			lay.setMsg("新增成功，但部门无对应角色导致人员无法给予默认角色");
			return lay;
		}
		if(a&&b){
			lay.setMsg("新增成功");
		}else{
			lay.setMsg("新增失败");
		}
		return lay;
	}
	
	/**
	 * 人员修改
	 */
	@ResponseBody
	@RequestMapping("/staffUpdata")
	public LayuiDataTemplet<staff> updataStaff(@RequestBody staff staff)
	{
         LayuiDataTemplet<staff> lay=new LayuiDataTemplet<staff>();
		
		Boolean a=staffService.updataStaff(staff);
		if(a){
			lay.setMsg("修改成功");
		}else{
			lay.setMsg("修改失败");
		}
		return lay;
	}
	
	/**
	 * 人员删除
	 */
	@ResponseBody
	@RequestMapping("/staffDelect")
	public LayuiDataTemplet<staff> del(@RequestBody List<Integer> delList)
	{
		    LayuiDataTemplet<staff> lay=new LayuiDataTemplet<staff>();
			//更改员工表
			boolean a=staffService.delStaff(delList);
			//删除关系表中关联的角色
			boolean b=false;
			for(int i=0;i<delList.size();i++){
				roleandstaff roleandstaff=new roleandstaff();
				roleandstaff.setPid(delList.get(i));
				b=roleandstaffService.deleteRoleandstaff(roleandstaff);
			}
			if(a&&b){
				lay.setMsg("删除成功");
			}else{
				lay.setMsg("删除失败");
			}
			return lay;
	}
	
	
	
	
	/**
	 * 查询人员所有功能
	 */
	@ResponseBody
	@RequestMapping("/selectStaffSystemfunction")
	public LayuiDataTemplet<systemfunction> selectStaffSystemfunction(@RequestBody staff staff)
	{
		    LayuiDataTemplet<systemfunction> lay=new LayuiDataTemplet<systemfunction>();
			//创建查询对象
		    roleandstaff ras=new roleandstaff();
		    ras.setPid(staff.getId());
		    //不分页
		    ras.setPagenumber(-1);
		    //查询具体数量
		    Integer count=roleandstaffService.selectRoleandstaffCount(ras);
		    if(count>0){
		    	//查询角色
		    	List<roleandstaff> list=roleandstaffService.selectRoleandstaff(ras);
		    	//遍历把为Integer类型的集合
		    	List<Integer> rid=new ArrayList<Integer>();
		    	for(int i=0;i<list.size();i++){
		    		rid.add(list.get(i).getRid());
		    	}
			    //查询在集合中的功能id
		    	List<Integer> sid=new ArrayList<Integer>();
		    	for(int z=0;z<rid.size();z++){
		    		systemfunctionandrole sar=new systemfunctionandrole();
		    		sar.setRid(rid.get(z));
		    		sar.setPagenumber(-1);
		    		List<systemfunctionandrole> sarList=systemfunctionandroleService.selectSystemfunctionandrole(sar);
		    		for(int x=0;x<sarList.size();x++){
		    			sid.add(sarList.get(x).getSid());
		    		}
		    	}
		    	//查询所有的功能
		    	List<systemfunction> sys=systemfunctionService.selectSystemFunctionMore(sid);
		    	lay.setData(sys);
		    	lay.setMsg("查询成功");
		    	return lay;
		    }else{
		    	lay.setMsg("您还没有对应的权限，请联系管理员");
		    	return lay;
		    }
	}
	
	
	
}
