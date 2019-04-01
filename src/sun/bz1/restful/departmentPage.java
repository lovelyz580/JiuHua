package sun.bz1.restful;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.department;

/**
 * 部门控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/department")
public class departmentPage {
	
	@Autowired
	private sun.bz1.service.departmentService departmentService;
	/**
	 * 部门的查询
	 * @param o
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/departmentSelect")
	public LayuiDataTemplet<department> selectDepartment(@RequestBody department department)
	{
		LayuiDataTemplet<department> dep=new LayuiDataTemplet<department>();
		List<department> list=new ArrayList<department>();
		//分页
		if(department.getPagenumber()!=-1){
			int s=department.getPagesize();
			int n=department.getPagenumber();
			department.setPagenumber((n-1)*s);
		}
		//查询数量
		Integer count=departmentService.selectDepartmentCount(department);
		
		if(count>0){
			list=departmentService.selectDepartment(department);
			dep.setCount(count);
			dep.setData(list);
			dep.setMsg("查询成功");
		}else{
			dep.setMsg("暂无部门数据");
		}
		return dep;
     }
	
	
	/**
	 * 部门的新增
	 */
	@ResponseBody
	@RequestMapping("/departmentInsert")
	public LayuiDataTemplet<department> insertDepartment(@RequestBody department department)
	{
		LayuiDataTemplet<department> dep=new LayuiDataTemplet<department>();
		Boolean a=departmentService.insertDepartment(department);
		if(a){
			dep.setMsg("新增成功");
		}else{
			dep.setMsg("新增失败");
		}
		return dep;
	}
	
	/**
	 * 部门的修改
	 */
	@ResponseBody
	@RequestMapping("/departmentUpdata")
	public LayuiDataTemplet<department> updataDepartment(@RequestBody department department)
	{
		LayuiDataTemplet<department> dep=new LayuiDataTemplet<department>();
		Boolean a=departmentService.updataDepartment(department);
		if(a){
			dep.setMsg("修改成功");
		}else{
			dep.setMsg("修改失败");
		}
		return dep;
	}
	
	/**
	 * 部门的删除
	 */
	@ResponseBody
	@RequestMapping("/departmentDelect")
	public LayuiDataTemplet<department> delDep(@RequestBody List<Integer> delList)
	{
		LayuiDataTemplet<department> dep=new LayuiDataTemplet<department>();
		Boolean a=departmentService.delDsepartment(delList);
		if(a){
			dep.setMsg("删除成功");
		}else{
			dep.setMsg("删除失败");
		}
		return dep;
	}
	
	
	
}
