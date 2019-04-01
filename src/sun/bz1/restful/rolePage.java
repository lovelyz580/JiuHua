package sun.bz1.restful;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.TBase;
import sun.bz1.entity.role;


/**
 * 角色业务层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/role")
public class  rolePage{
    private Logger logger=Logger.getLogger(rolePage.class);
			
	@Autowired
	private	sun.bz1.service.roleService roleService;
			
	
	/**
	 * 查看角色表数据
	 * @param o
	 * @return
	 */
	        @ResponseBody
			@RequestMapping("/roleSelect")
			public LayuiDataTemplet<role> selectRole(@RequestBody role role)
			{
				LayuiDataTemplet<role> roleReturn=new LayuiDataTemplet<role>();
				Integer count=0;
				count=roleService.selectRoleCount(role);
				roleReturn.setCount(count);
				List<role> list=null;
				if(count>0){
					//分页
					if(role.getPagenumber()!=-1){
						int s=role.getPagesize();
						int n=role.getPagenumber();
						role.setPagenumber((n-1)*s);
					}
					//查询
					list=roleService.selectRole(role);
					roleReturn.setData(list);
					roleReturn.setMsg("查询成功");
				}else{
					roleReturn.setMsg("暂无数据");
				}
				return roleReturn;
			}
			

			
			
			/**
			 * 添加角色
			 */
			@ResponseBody
			@RequestMapping("/roleInsert")
			public LayuiDataTemplet<role> insertRole(@RequestBody role role)
			{
				LayuiDataTemplet<role> roleReturn=new LayuiDataTemplet<role>();
				Boolean a=roleService.insertRole(role);
				if(a){
					roleReturn.setMsg("新增成功");
				}else{
					roleReturn.setMsg("新增失败");
				}
				return roleReturn;
			}
			
			/**
			 * 修改角色
			 */
			@ResponseBody
			@RequestMapping("/roleUpdata")
			public LayuiDataTemplet<role> updataRole(@RequestBody role role)
			{
				LayuiDataTemplet<role> roleReturn=new LayuiDataTemplet<role>();
				Boolean a=roleService.updataRole(role);
				if(a){
					roleReturn.setMsg("修改成功");
				}else{
					roleReturn.setMsg("修改失败");
				}
				return roleReturn;
			}
			
			/**
			 * 删除角色
			 */
			@ResponseBody
			@RequestMapping("/roleDelete")
			public LayuiDataTemplet<role> delRole(@RequestBody List<Integer> delList)
			{
                LayuiDataTemplet<role> roleReturn=new LayuiDataTemplet<role>();
				Boolean a=roleService.delRole(delList);
				if(a){
					roleReturn.setMsg("删除成功");
				}else{
					roleReturn.setMsg("删除失败");
				}
				return roleReturn;
			}
			
			
			

}
