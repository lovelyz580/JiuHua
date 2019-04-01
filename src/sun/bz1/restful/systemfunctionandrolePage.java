package sun.bz1.restful;

import java.util.ArrayList;
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
import sun.bz1.entity.systemfunction;
import sun.bz1.entity.systemfunctionandrole;
import sun.bz1.service.systemfunctionService;

/**
 * 功能和角色关联表
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/systemfunctionandrole")
public class systemfunctionandrolePage {

	private Logger logger = Logger.getLogger(systemfunctionandrolePage.class);

	@Autowired
	private sun.bz1.service.systemfunctionandroleService systemfunctionandroleService;
	@Autowired
	private systemfunctionService systemfunctionService;

	/**
	 * 查看角色所属的功能
	 * 
	 * @param o
	 * @return
	 */

	@ResponseBody
	@RequestMapping("/systemfunctionandroleSelect")
	public LayuiDataTemplet<systemfunction> get(
			@RequestBody systemfunctionandrole systemfunctionandrole) {

		LayuiDataTemplet<systemfunction> system = new LayuiDataTemplet<systemfunction>();
		// 分页
		if (systemfunctionandrole.getPagenumber() != -1) {
			int s = systemfunctionandrole.getPagesize();
			int n = systemfunctionandrole.getPagenumber();
			systemfunctionandrole.setPagenumber((n - 1) * s);
		}
		// 查询出关联数量
		Integer count = systemfunctionandroleService
				.selectSystemfunctionandroleCount(systemfunctionandrole);
		system.setCount(count);
		// 查出关联表所有关联的功能
		if (count > 0) {
			List<systemfunctionandrole> sarList = systemfunctionandroleService
					.selectSystemfunctionandrole(systemfunctionandrole);
			// 用来存放用来查找对应功能的id集合
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < sarList.size(); i++) {
				list.add(sarList.get(i).getSid());
			}
			List<systemfunction> _system = systemfunctionService
					.selectSystemFunctionMore(list);
			system.setData(_system);
			system.setMsg("查询成功");
		} else {
			system.setMsg("暂无关联数据");
		}

		return system;
	}

	/**
	 * 更改角色对应的功能
	 */
	@ResponseBody
	@RequestMapping("/systemfunctionandroleChange")
	public LayuiDataTemplet<systemfunction> set(
			@RequestBody List<systemfunctionandrole> systemfunctionandroleList) {
		LayuiDataTemplet<systemfunction> system = new LayuiDataTemplet<systemfunction>();

		// 先删除后添加
		Boolean a = systemfunctionandroleService
				.deleteSystemfunctionandrole(systemfunctionandroleList.get(0));
		// 防止出现清空传进来的参数也为空的情况 前台默认添加一个对象 用来标识对应角色
		Boolean b = true;
		if (systemfunctionandroleList.size() > 1) {
			// 移除第一个用于标识的对象
			systemfunctionandroleList.remove(0);
			b = systemfunctionandroleService
					.insertSystemfunctionandrole(systemfunctionandroleList);
		}

		if (a && b) {
			system.setMsg("更改成功");
		} else {
			system.setMsg("更改失败");
		}
		return system;
	}

}
