package sun.bz1.restful;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.role;
import sun.bz1.entity.roleanddepartment;

/**
 * 角色和部门
 * 
 * @author Administrator
 * 
 */

@Controller
@RequestMapping("/roleanddepartment")
public class roleanddepartmentPage {
	@Autowired
	private sun.bz1.service.roleanddepartmentService roleanddepartmentService;
	@Autowired
	private sun.bz1.service.roleService roleService;

	/**
	 * 角色部门表关联查询 最后查询出 部门对应的角色
	 * 
	 * @param o
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/roleanddepartmentSelect")
	public LayuiDataTemplet<role> get(
			@RequestBody roleanddepartment roleanddepartment) {
		LayuiDataTemplet<role> role = new LayuiDataTemplet<role>();
		List<roleanddepartment> list =new ArrayList<roleanddepartment>();
		List<Integer> roleList = new ArrayList<Integer>();
		//分页
		if(roleanddepartment.getPagenumber()!=-1){
			int s=roleanddepartment.getPagesize();
			int n=roleanddepartment.getPagenumber();
			roleanddepartment.setPagenumber((n-1)*s);
		}
		//查询关联数量
		Integer count=roleanddepartmentService.selectRoleanddepartmentCount(roleanddepartment);
		role.setCount(count);
		if (count > 0) {
		// 先查询所有的关联对象
		list = roleanddepartmentService
				.selectRoleanddepartment(roleanddepartment);
		// 遍历出来所有的rid
		for (int i = 0; i < list.size(); i++) {
			roleList.add(list.get(i).getRid());
		}
		    List<role> r = roleService.selectRoleMore(roleList);
			role.setData(r);
			role.setMsg("查询成功");
		} else {
			role.setMsg("暂无关联数据");
		}
		return role;
	}

	/**
	 * 部门的角色删除和新增
	 */
	@ResponseBody
	@RequestMapping("/roleanddepartmentChange")
	public LayuiDataTemplet<role> roleanddepartmentChange(
			@RequestBody List<roleanddepartment> roleanddepartmentList) {
		LayuiDataTemplet<role> role = new LayuiDataTemplet<role>();
		// 先删除原有的
		Boolean a = roleanddepartmentService
				.deleteRoleanddepartment(roleanddepartmentList.get(0));
		// 先看传进来的数组长度 第一个对象为 选中的部门编号 为防止清空角色 而数据库不清空的情况
		Boolean b = true;
		if (roleanddepartmentList.size() > 1) {
			//移除第一个用于标识的对象
			roleanddepartmentList.remove(0);
			b = roleanddepartmentService
					.insertRoleanddepartment(roleanddepartmentList);
		}

		if (a && b) {
			role.setMsg("修改成功");
		} else {
			role.setMsg("修改失败");
		}
		return role;
	}

}
