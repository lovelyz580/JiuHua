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
import sun.bz1.entity.roleandstaff;

/**
 * 人员角色关联
 * 
 * @param o
 * @return
 */
@Controller
@RequestMapping("/roleandstaff")
public class roleandstaffPage {

	@Autowired
	private sun.bz1.service.roleandstaffService roleandstaffService;
	@Autowired
	private sun.bz1.service.roleService roleService;

	/**
	 * 查询对应角色
	 * 
	 * @param roleandstaff
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/roleandstaffSelect")
	public LayuiDataTemplet<role> get(@RequestBody roleandstaff roleandstaff) {
		LayuiDataTemplet<role> role = new LayuiDataTemplet<role>();
		//分页
		if(roleandstaff.getPagenumber()!=-1){
			int s=roleandstaff.getPagesize();
			int n=roleandstaff.getPagenumber();
			roleandstaff.setPagenumber((n-1)*s);
		}
		//查询关联数量
		Integer count=roleandstaffService.selectRoleandstaffCount(roleandstaff);
		role.setCount(count);
		if (count > 0) {
		// 查询全部关联
		List<roleandstaff> list = roleandstaffService
				.selectRoleandstaff(roleandstaff);
		
		// 解析成rid集合
		List<Integer> Rlist = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			Rlist.add(list.get(i).getRid());
		}
		    List<role> _list = roleService.selectRoleMore(Rlist);
			role.setData(_list);
			role.setMsg("查询成功");
		} else {
			role.setMsg("暂无关联数据");
		}
		return role;
	}

	/**
	 * 更改角色
	 */
	@ResponseBody
	@RequestMapping("/roleandstaffChange")
	public LayuiDataTemplet<role> set(
			@RequestBody List<roleandstaff> roleandstaffList) {
		LayuiDataTemplet<role> role = new LayuiDataTemplet<role>();
		// 先删除现有关系
		Boolean a = roleandstaffService.deleteRoleandstaff(roleandstaffList
				.get(0));
	    boolean b=true;
		if(roleandstaffList.size()>1){
			//移除第一个标记
			roleandstaffList.remove(0);
			// 添加新的关系
			 b = roleandstaffService.insertRoleandstaff(roleandstaffList);
		}
		if (a && b) {
			role.setMsg("修改成功");
		} else {
			role.setMsg("修改失败");
		}
		return role;
	}

}
