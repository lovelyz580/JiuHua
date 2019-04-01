package sun.bz1.restful;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.EvaluateCustomerSetup;
import sun.bz1.service.EvaluateCustomerSetupService;
import util.Config;
import util.VersionCompare;

/**
 * 维修人员评价客户_评价项_设置表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/17
 */

@Controller
@RequestMapping("/evaluate_customer_setup")
public class F_EvaluateCustomerSetupRestful {
	
	@Autowired
	private EvaluateCustomerSetupService setupService;

	/**
	 * 根据EvaluateCustomerSetup实体添加
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	@ResponseBody
	@RequestMapping("/insertByEvaluateCustomerSetup")
	public LayuiDataTemplet<EvaluateCustomerSetup> insertByEvaluateCustomerSetup(@RequestBody EvaluateCustomerSetup setup) {
		LayuiDataTemplet<EvaluateCustomerSetup> returnData = new LayuiDataTemplet<EvaluateCustomerSetup>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (setup.getVersion() != null && !setup.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(setup.getVersion(), Config.VERSION);
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
		setup.setEvaluatecustomersetupid(Config.SIGN_EVALUATECUSTOMERSETUP + UUID.randomUUID().toString()); // 评价项ID(KPJX+UUID)
		setup.setEvaluatecustomersetupstate("Y"); // 评价项状态(Y:有效/N:无效)
		setup.setEvaluatecustomersetupcreatetime(new Date()); // 评价项创建时间
//		setup.setEvaluatecustomersetupupdateuserid(setup.getEvaluatecustomersetupcreateuserid()); // 评价项更新人ID(YHBG+UUID)
//		setup.setEvaluatecustomersetupupdatetime(new Date()); // 评价项更新时间

		// 添加
		int count = 0;
		count = setupService.insertByEvaluateCustomerSetup(setup);

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
	 * 根据EvaluateCustomerSetup实体更新
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	@ResponseBody
	@RequestMapping("/updateByEvaluateCustomerSetup")
	public LayuiDataTemplet<EvaluateCustomerSetup> updateByEvaluateCustomerSetup(@RequestBody EvaluateCustomerSetup setup) {
		LayuiDataTemplet<EvaluateCustomerSetup> returnData = new LayuiDataTemplet<EvaluateCustomerSetup>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (setup.getVersion() != null && !setup.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(setup.getVersion(), Config.VERSION);
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
		if (setup.getId() == null || setup.getId() == 0) {
			if (setup.getEvaluatecustomersetupid() == null || setup.getEvaluatecustomersetupid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}
				
		// 更新数据
		setup.setEvaluatecustomersetupstate("Y"); // 评价项状态(Y:有效/N:无效)
		setup.setEvaluatecustomersetupupdatetime(new Date()); // 评价项更新时间
		
		// 更新
		int count = 0;
		count = setupService.updateByEvaluateCustomerSetup(setup);

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
	 * 根据EvaluateCustomerSetup实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	@ResponseBody
	@RequestMapping("/selectByEvaluateCustomerSetup")
	public LayuiDataTemplet<EvaluateCustomerSetup> selectByEvaluateCustomerSetup(@RequestBody EvaluateCustomerSetup setup) {
		LayuiDataTemplet<EvaluateCustomerSetup> returnData = new LayuiDataTemplet<EvaluateCustomerSetup>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (setup.getVersion() != null && !setup.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(setup.getVersion(), Config.VERSION);
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
		if (setup.getPagenumber() != null) {
			// 计算偏移量
			if (setup.getPagenumber() != -1) {
				if (setup.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = setup.getPagenumber();
				int pageSize = setup.getPagesize();
				setup.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				setup.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = setupService.selectCountByEvaluateCustomerSetup(setup);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<EvaluateCustomerSetup> setupList = setupService.selectByEvaluateCustomerSetup(setup); // 查询数据
				returnData.setData(setupList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据EvaluateCustomerSetup实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<EvaluateCustomerSetup> selectBySelectData(@RequestBody EvaluateCustomerSetup setup) {
		LayuiDataTemplet<EvaluateCustomerSetup> returnData = new LayuiDataTemplet<EvaluateCustomerSetup>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (setup.getVersion() != null && !setup.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(setup.getVersion(), Config.VERSION);
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
		if (setup.getPagenumber() != null) {
			// 计算偏移量
			if (setup.getPagenumber() != -1) {
				if (setup.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = setup.getPagenumber();
				int pageSize = setup.getPagesize();
				setup.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				setup.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = setupService.selectCountBySelectData(setup); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<EvaluateCustomerSetup> setupList = setupService.selectBySelectData(setup); // 查询数据
				returnData.setData(setupList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/17
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<EvaluateCustomerSetup> deleteByIdList(@RequestBody EvaluateCustomerSetup setup) {
		LayuiDataTemplet<EvaluateCustomerSetup> returnData = new LayuiDataTemplet<EvaluateCustomerSetup>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (setup.getVersion() != null && !setup.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(setup.getVersion(), Config.VERSION);
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
		count = setupService.deleteByIdList(setup.getIdlist());

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
