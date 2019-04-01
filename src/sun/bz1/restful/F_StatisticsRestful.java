package sun.bz1.restful;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.Statistics;
import sun.bz1.service.StatisticsService;
import util.Config;
import util.VersionCompare;

/**
 * 统计数据
 * 
 * Restful
 * 
 * @author WJF on 2018/09/25
 */

@Controller
@RequestMapping("/statistics")
public class F_StatisticsRestful {

	@Autowired
	private StatisticsService statisticsService;
	
	/**
	 * 根据Statistics实体模糊查询(查询项目)
   	 * 
	 * @param statistics
	 * @return
	 * 
	 * @author WJF on 2018/09/25
	 */
	@ResponseBody
	@RequestMapping("/selectProject")
	public LayuiDataTemplet<Statistics> selectProject(@RequestBody Statistics statistics) {
		LayuiDataTemplet<Statistics> returnData = new LayuiDataTemplet<Statistics>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (statistics.getVersion() != null && !statistics.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(statistics.getVersion(), Config.VERSION);
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

		// 查询数据
		statistics.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		
		// 查询数量
		int count = 0;
		count = statisticsService.selectCountBySelectData(statistics); // 查询数量

		// 返回数据
		if (count == 0) {
			returnData.setMsg("暂无数据！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("查询成功！");
			List<Statistics> statisticsList = null;
			statisticsList = statisticsService.selectBySelectData(statistics); // 查询数据
			
			// 统计项目
			List<Statistics> projectList = new ArrayList<Statistics>(); // 最终返回的数据
			for (int i = 0; i < statisticsList.size(); i++) {
				if (i == 0) {
					// 添加维修次数的初始值
					if (statisticsList.get(0).getServicenumber() == null) {
						statisticsList.get(0).setServicenumber(1);
					}
					
					// 为最终返回的数据添加数据
					projectList.add(statisticsList.get(0)); 
				} else {
					boolean flag = false; // 标识
					
					for (int j = 0; j < projectList.size(); j++) {
						Statistics statisticsCirculation = statisticsList.get(i);
						Statistics projectCirculation = projectList.get(j);
						
						if (statisticsCirculation.getGoodsid().equals(projectCirculation.getGoodsid()) && 
								statisticsCirculation.getProjectid().equals(projectCirculation.getProjectid())) {
							// 最终返回的数据中已有该产品、该项目，则只需增加相对应的维修次数、维修总价
							flag = true; // 标识
							
							// 增加相对应的维修次数
							projectCirculation.setServicenumber(projectCirculation.getServicenumber() + 1);
							
							// 增加相对应的维修总价
							if (projectCirculation.getTotalmoney() == null) {
								projectCirculation.setTotalmoney(0.0);
							}
							if (statisticsCirculation.getTotalmoney() == null) {
								statisticsCirculation.setTotalmoney(0.0);
							}
							double totalmoney = projectCirculation.getTotalmoney() + statisticsCirculation.getTotalmoney();
							projectCirculation.setTotalmoney(totalmoney);
							
							// 为最终返回的数据添加数据
							projectList.remove(j);
							projectList.add(j, projectCirculation); 
						
							break;
						} 
					}
					
					// 最终返回的数据中没有该产品、该项目，则添加数据
					if (!flag) {
						// 添加维修次数的初始值
						if (statisticsList.get(i).getServicenumber() == null) {
							statisticsList.get(i).setServicenumber(1);
						}
						
						projectList.add(statisticsList.get(i)); // 为最终返回的数据添加数据
					}
				}
			}
			
			returnData.setData(projectList);
		}

		return returnData;
	}
	
	/**
	 * 根据Statistics实体模糊查询(查询维修人员)
   	 * 
	 * @param statistics
	 * @return
	 * 
	 * @author WJF on 2018/09/25
	 */
	@ResponseBody
	@RequestMapping("/selectService")
	public LayuiDataTemplet<Statistics> selectService(@RequestBody Statistics statistics) {
		LayuiDataTemplet<Statistics> returnData = new LayuiDataTemplet<Statistics>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (statistics.getVersion() != null && !statistics.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(statistics.getVersion(), Config.VERSION);
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

		// 查询数据
		statistics.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		
		// 查询数量
		int count = 0;
		count = statisticsService.selectCountBySelectData(statistics); // 查询数量

		// 返回数据
		if (count == 0) {
			returnData.setMsg("暂无数据！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("查询成功！");
			List<Statistics> statisticsList = null;
			statisticsList = statisticsService.selectBySelectData(statistics); // 查询数据
			
			// 统计项目
			List<Statistics> serviceList = new ArrayList<Statistics>(); // 最终返回的数据
			for (int i = 0; i < statisticsList.size(); i++) {
				if (i == 0) {
					// 添加维修次数的初始值
					if (statisticsList.get(0).getServicenumber() == null) {
						statisticsList.get(0).setServicenumber(1);
					}
					
					// 为最终返回的数据添加数据
					serviceList.add(statisticsList.get(0)); 
				} else {
					boolean flag = false; // 标识
					
					for (int j = 0; j < serviceList.size(); j++) {
						Statistics statisticsCirculation = statisticsList.get(i);
						Statistics serviceCirculation = serviceList.get(j);
						
						if (statisticsCirculation.getUserid().equals(serviceCirculation.getUserid()) && 
								statisticsCirculation.getGoodsid().equals(serviceCirculation.getGoodsid()) && 
								statisticsCirculation.getProjectid().equals(serviceCirculation.getProjectid())) {
							// 最终返回的数据中已有该维修人员、该产品、该项目，则只需增加相对应的维修次数、维修总价
							flag = true; // 标识
							
							// 增加相对应的维修次数
							serviceCirculation.setServicenumber(serviceCirculation.getServicenumber() + 1);
							
							// 增加相对应的维修总价
							if (serviceCirculation.getTotalmoney() == null) {
								serviceCirculation.setTotalmoney(0.0);
							}
							if (statisticsCirculation.getTotalmoney() == null) {
								statisticsCirculation.setTotalmoney(0.0);
							}
							double totalmoney = serviceCirculation.getTotalmoney() + statisticsCirculation.getTotalmoney();
							serviceCirculation.setTotalmoney(totalmoney);

							// 为最终返回的数据添加数据
							serviceList.remove(j);
							serviceList.add(j, serviceCirculation); 
						
							break;
						} 
					}
					
					// 最终返回的数据中没有该维修人员、该产品、该项目，则添加数据
					if (!flag) {
						// 添加维修次数的初始值
						if (statisticsList.get(i).getServicenumber() == null) {
							statisticsList.get(i).setServicenumber(1);
						}
						
						serviceList.add(statisticsList.get(i)); // 为最终返回的数据添加数据
					}
				}
			}
			
			returnData.setData(serviceList);
		}

		return returnData;
	}
	
	/**
	 * 根据Statistics实体模糊查询(查询维修单号)
   	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param statistics
	 * @return
	 * 
	 * @author WJF on 2018/09/25
	 */
	@ResponseBody
	@RequestMapping("/selectOrder")
	public LayuiDataTemplet<Statistics> selectOrder(@RequestBody Statistics statistics) {
		LayuiDataTemplet<Statistics> returnData = new LayuiDataTemplet<Statistics>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (statistics.getVersion() != null && !statistics.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(statistics.getVersion(), Config.VERSION);
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
		if (statistics.getPagenumber() != null) {
			// 计算偏移量
			if (statistics.getPagenumber() != -1) {
				if (statistics.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = statistics.getPagenumber();
				int pageSize = statistics.getPagesize();
				statistics.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				statistics.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = statisticsService.selectCountBySelectData(statistics); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Statistics> statisticsList = statisticsService.selectBySelectData(statistics); // 查询数据
				returnData.setData(statisticsList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
}
