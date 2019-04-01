package sun.bz1.restful;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.*;
import sun.bz1.service.*;
import util.Config;
import util.VersionCompare;

/**
 * 项目表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/04
 */

@Controller
@RequestMapping("/project")
public class F_ProjectRestful {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private InterceptService interceptService;

	@Autowired
	private OrderTableService orderTableService;

	@Autowired
	private OrderPriceService orderPriceService;

	@Autowired
	private UserService userService;

	@Autowired
	private PriceService priceService;

	@Autowired
	private OrderDetailService orderDetailService;

	/**
	 * 根据Project实体添加
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/insertByProject")
	public LayuiDataTemplet<Project> insertByProject(@RequestBody Project project) {
		LayuiDataTemplet<Project> returnData = new LayuiDataTemplet<Project>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (project.getVersion() != null && !project.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(project.getVersion(), Config.VERSION);
				if (compare < 0) {
					returnData.setMsg("版本较低，请更新版本！");
					return returnData;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			returnData.setMsg("版本比较发生异常！");
			return returnData;
		}

		// 添加数据
		project.setProjectid(Config.SIGN_PROJECT + UUID.randomUUID().toString()); // 项目ID(XMBG+UUID)
//		project.setProjectstate("Y"); // 项目状态(Y:有效/N:无效)
		project.setProjectcreatetime(new Date()); // 项目创建时间
//		project.setProjectupdateuserid(project.getProjectcreateuserid()); // 项目更新人ID(YHBG+UUID)
//		project.setProjectupdatetime(new Date()); // 项目更新时间
		
		// 添加
		int count = 0;
		count = projectService.insertByProject(project);
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("添加失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("添加成功！");
		}

		return returnData;
	}
	
	/**
	 * 根据Project实体更新
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/updateByProject")
	public LayuiDataTemplet<Project> updateByProject(@RequestBody Project project) {
		LayuiDataTemplet<Project> returnData = new LayuiDataTemplet<Project>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (project.getVersion() != null && !project.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(project.getVersion(), Config.VERSION);
				if (compare < 0) {
					returnData.setMsg("版本较低，请更新版本！");
					return returnData;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			returnData.setMsg("版本比较发生异常！");
			return returnData;
		}

		// 更新判断
		if (project.getId() == null || project.getId() == 0) {
			if (project.getProjectid() == null || project.getProjectid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}
				
		// 更新数据
		project.setProjectupdatetime(new Date()); // 项目更新时间
				
		// 更新
		int count = 0;
		count = projectService.updateByProject(project);
		
		// 返回数据
		if (count == 0) {
			returnData.setMsg("更新失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("更新成功！");
		}

		return returnData;
	}

	/**
	 * 根据Project实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectByProject")
	public LayuiDataTemplet<Project> selectByProject(@RequestBody Project project) {
		LayuiDataTemplet<Project> returnData = new LayuiDataTemplet<Project>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (project.getVersion() != null && !project.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(project.getVersion(), Config.VERSION);
				if (compare < 0) {
					returnData.setMsg("版本较低，请更新版本！");
					return returnData;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			returnData.setMsg("版本比较发生异常！");
			return returnData;
		}

		// 分页数据
		// 使用limit分页查询，根据偏移量查询
		// 第一个参数为第一个返回记录行的偏移量，第二个参数为返回记录行的最大数目
		// MySQL > SELECT * FROM table LIMIT 5, 10; // 查询第6-15行数据
		if (project.getPagenumber() != null) {
			// 计算偏移量
			if (project.getPagenumber() != -1) {
				if (project.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = project.getPagenumber();
				int pageSize = project.getPagesize();
				project.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				project.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = projectService.selectCountByProject(project); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Project> projectList = projectService.selectByProject(project); // 查询数据
				returnData.setData(projectList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据Project实体模糊查询
   	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<Project> selectBySelectData(@RequestBody Project project) {
		LayuiDataTemplet<Project> returnData = new LayuiDataTemplet<Project>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (project.getVersion() != null && !project.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(project.getVersion(), Config.VERSION);
				if (compare < 0) {
					returnData.setMsg("版本较低，请更新版本！");
					return returnData;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			returnData.setMsg("版本比较发生异常！");
			return returnData;
		}

		// 分页数据
		// 使用limit分页查询，根据偏移量查询
		// 第一个参数为第一个返回记录行的偏移量，第二个参数为返回记录行的最大数目
		// MySQL > SELECT * FROM table LIMIT 5, 10; // 查询第6-15行数据
		if (project.getPagenumber() != null) {
			// 计算偏移量
			if (project.getPagenumber() != -1) {
				if (project.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = project.getPagenumber();
				int pageSize = project.getPagesize();
				project.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				project.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = projectService.selectCountBySelectData(project); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Project> projectList = projectService.selectBySelectData(project); // 查询数据
				returnData.setData(projectList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


	/**
	 * 根据Project实体模糊查询(生成报表)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param project
	 * @return
	 *
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectDataReport")
	public LayuiDataTemplet<Project> selectBySelectDataReport(@RequestBody Project project) {
		LayuiDataTemplet<Project> returnData = new LayuiDataTemplet<Project>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (project.getVersion() != null && !project.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(project.getVersion(), Config.VERSION);
				if (compare < 0) {
					returnData.setMsg("版本较低，请更新版本！");
					return returnData;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			returnData.setMsg("版本比较发生异常！");
			return returnData;
		}

		// 分页数据
		// 使用limit分页查询，根据偏移量查询
		// 第一个参数为第一个返回记录行的偏移量，第二个参数为返回记录行的最大数目
		// MySQL > SELECT * FROM table LIMIT 5, 10; // 查询第6-15行数据
		if (project.getPagenumber() != null) {
			// 计算偏移量
			if (project.getPagenumber() != -1) {
				if (project.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = project.getPagenumber();
				int pageSize = project.getPagesize();
				project.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				project.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = projectService.selectCountBySelectDataReport(project); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Project> projectList = projectService.selectBySelectDataReport(project); // 查询数据

				//总数和平均数
				double size = projectList.size();
				Project usertotalnumber = new Project();
				Project usersvgnumber = new Project();
				//维修次数总数、平均数
				double repairnumber = 0;
				for (int i = 0; i < projectList.size(); i ++){
					repairnumber = repairnumber + projectList.get(i).getRepairnumber();
				}
				usersvgnumber.setRepairnumber(repairnumber/size);

				//清单的维修总价格总数、平均数
				double repairtotalmoney = 0;
				for (int i = 0; i < projectList.size(); i ++){
					repairtotalmoney = repairtotalmoney + projectList.get(i).getRepairtotalmoney();
				}
				usertotalnumber.setRepairtotalmoney(repairtotalmoney);
				usersvgnumber.setRepairtotalmoney(repairtotalmoney/size);

				//清单的维修支出总价格总数、平均数
				double paytotalmoney = 0;
				for (int i = 0; i < projectList.size(); i ++){
					paytotalmoney = paytotalmoney + projectList.get(i).getPaytotalmoney();
				}
				usertotalnumber.setPaytotalmoney(paytotalmoney);
				usersvgnumber.setPaytotalmoney(paytotalmoney/size);

				//利润总数、平均数
				double profittotalmoney = 0;
				for (int i = 0; i < projectList.size(); i ++){
					profittotalmoney = profittotalmoney + projectList.get(i).getProfittotalmoney();
				}
				usertotalnumber.setProfittotalmoney(profittotalmoney);
				usersvgnumber.setProfittotalmoney(profittotalmoney/size);

				//添加合计、平均数
				usertotalnumber.setProjectstate("合计");
				usersvgnumber.setProjectstate("平台平均值");

				projectList.add(usertotalnumber);
				projectList.add(usersvgnumber);

				returnData.setData(projectList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


	/**
	 * 根据Project实体模糊查询(查询平均成交价的详情数据)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param project
	 * @return
	 *
	 * @author ZY on 2018/12/10
	 */
	@ResponseBody
	@RequestMapping("/selectProjectDetailByProjectReport")
	public LayuiDataTemplet<Project> selectProjectDetailByProjectReport(@RequestBody Project project) {
		LayuiDataTemplet<Project> returnData = new LayuiDataTemplet<Project>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (project.getVersion() != null && !project.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(project.getVersion(), Config.VERSION);
				if (compare < 0) {
					returnData.setMsg("版本较低，请更新版本！");
					return returnData;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			returnData.setMsg("版本比较发生异常！");
			return returnData;
		}

		// 分页数据
		// 使用limit分页查询，根据偏移量查询
		// 第一个参数为第一个返回记录行的偏移量，第二个参数为返回记录行的最大数目
		// MySQL > SELECT * FROM table LIMIT 5, 10; // 查询第6-15行数据
		if (project.getPagenumber() != null) {
			// 计算偏移量
			if (project.getPagenumber() != -1) {
				if (project.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = project.getPagenumber();
				int pageSize = project.getPagesize();
				project.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				project.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = projectService.selectProjectDetailCountByProjectReport(project);
			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");

				List<Project> orderDetailList = projectService.selectProjectDetailByProjectReport(project);
				returnData.setData(orderDetailList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param project
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<Project> deleteByIdList(@RequestBody Project project) {
		LayuiDataTemplet<Project> returnData = new LayuiDataTemplet<Project>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (project.getVersion() != null && !project.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(project.getVersion(), Config.VERSION);
				if (compare < 0) {
					returnData.setMsg("版本较低，请更新版本！");
					return returnData;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			returnData.setMsg("版本比较发生异常！");
			return returnData;
		}

		// 删除
		int count = 0;
		count = projectService.deleteByIdList(project.getIdlist());

		// 返回数据
		if (count == 0) {
			returnData.setMsg("删除失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("删除成功！");
		}

		return returnData;
	}
	
}
