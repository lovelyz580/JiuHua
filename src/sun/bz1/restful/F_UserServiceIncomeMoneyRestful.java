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
import sun.bz1.entity.UserServiceIncomeMoney;
import sun.bz1.entity.UserServiceIncomeMoneyAndOrderTableAndUser;
import sun.bz1.service.UserServiceIncomeMoneyService;
import util.Config;
import util.VersionCompare;

/**
 * 用户_维修工、安装队_收入_金额表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/19
 */

@Controller
@RequestMapping("/user_service_income_money")
public class F_UserServiceIncomeMoneyRestful {

	@Autowired
	private UserServiceIncomeMoneyService incomeMoneyService;

	/**
	 * 根据UserServiceIncomeMoney实体添加
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/insertByUserServiceIncomeMoney")
	public LayuiDataTemplet<UserServiceIncomeMoney> insertByUserServiceIncomeMoney(@RequestBody UserServiceIncomeMoney incomeMoney) {
		LayuiDataTemplet<UserServiceIncomeMoney> returnData = new LayuiDataTemplet<UserServiceIncomeMoney>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (incomeMoney.getVersion() != null && !incomeMoney.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(incomeMoney.getVersion(), Config.VERSION);
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
		incomeMoney.setUserserviceincomemoneyid(Config.SIGN_USERSERVICEINCOMEMONEY + UUID.randomUUID().toString()); // 维修人员收入金额ID(WXSR+UUID)
		incomeMoney.setUserserviceincomemoneycreatetime(new Date()); // 金额创建时间
		
		// 添加
		int count = 0;
		count = incomeMoneyService.insertByUserServiceIncomeMoney(incomeMoney);
		
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
	 * 根据UserServiceIncomeMoney实体更新
	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/updateByUserServiceIncomeMoney")
	public LayuiDataTemplet<UserServiceIncomeMoney> updateByUserServiceIncomeMoney(@RequestBody UserServiceIncomeMoney incomeMoney) {
		LayuiDataTemplet<UserServiceIncomeMoney> returnData = new LayuiDataTemplet<UserServiceIncomeMoney>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (incomeMoney.getVersion() != null && !incomeMoney.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(incomeMoney.getVersion(), Config.VERSION);
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
		if (incomeMoney.getId() == null || incomeMoney.getId() == 0) {
			if (incomeMoney.getUserserviceincomemoneyid() == null || incomeMoney.getUserserviceincomemoneyid().isEmpty()) {
				if (incomeMoney.getOrderid() == null || incomeMoney.getOrderid().isEmpty()) {
					returnData.setMsg("缺少更新条件，更新失败！");
					return returnData;
				}
			}
		}

		// 更新数据
		
		// 更新
		int count = 0;
		count = incomeMoneyService.updateByUserServiceIncomeMoney(incomeMoney);
		
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
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表查询
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
	 * @author WJF on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionData")
	public LayuiDataTemplet<UserServiceIncomeMoneyAndOrderTableAndUser> selectThreeTablesByUnionData(@RequestBody UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		LayuiDataTemplet<UserServiceIncomeMoneyAndOrderTableAndUser> returnData = new LayuiDataTemplet<UserServiceIncomeMoneyAndOrderTableAndUser>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (unionData.getVersion() != null && !unionData.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(unionData.getVersion(), Config.VERSION);
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
		if (unionData.getPagenumber() != null) {
			// 计算偏移量
			if (unionData.getPagenumber() != -1) {
				if (unionData.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = unionData.getPagenumber();
				int pageSize = unionData.getPagesize();
				unionData.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				unionData.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = incomeMoneyService.selectThreeTablesCountByUnionData(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<UserServiceIncomeMoneyAndOrderTableAndUser> UserServiceIncomeMoneyList = incomeMoneyService.selectThreeTablesByUnionData(unionData); // 查询数据
				returnData.setData(UserServiceIncomeMoneyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据UserServiceIncomeMoneyAndOrderTableAndUser实体联表模糊查询
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
	 * @author WJF on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesBySelectData")
	public LayuiDataTemplet<UserServiceIncomeMoneyAndOrderTableAndUser> selectThreeTablesBySelectData(@RequestBody UserServiceIncomeMoneyAndOrderTableAndUser unionData) {
		LayuiDataTemplet<UserServiceIncomeMoneyAndOrderTableAndUser> returnData = new LayuiDataTemplet<UserServiceIncomeMoneyAndOrderTableAndUser>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (unionData.getVersion() != null && !unionData.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(unionData.getVersion(), Config.VERSION);
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
		if (unionData.getPagenumber() != null) {
			// 计算偏移量
			if (unionData.getPagenumber() != -1) {
				if (unionData.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = unionData.getPagenumber();
				int pageSize = unionData.getPagesize();
				unionData.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				unionData.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = incomeMoneyService.selectThreeTablesCountBySelectData(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<UserServiceIncomeMoneyAndOrderTableAndUser> UserServiceIncomeMoneyList = incomeMoneyService.selectThreeTablesBySelectData(unionData); // 查询数据
				returnData.setData(UserServiceIncomeMoneyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据idList删除信息
   	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<UserServiceIncomeMoney> deleteByIdList(@RequestBody UserServiceIncomeMoney incomeMoney) {
		LayuiDataTemplet<UserServiceIncomeMoney> returnData = new LayuiDataTemplet<UserServiceIncomeMoney>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (incomeMoney.getVersion() != null && !incomeMoney.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(incomeMoney.getVersion(), Config.VERSION);
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
		count = incomeMoneyService.deleteByIdList(incomeMoney.getIdlist());

		// 返回数据
		if (count == 0) {
			returnData.setMsg("删除失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("删除成功！");
		}

		return returnData;
	}
	
	/**
	 * 根据 维修单ID List 删除信息
   	 * 
	 * @param incomeMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/deleteByOrderIdList")
	public LayuiDataTemplet<UserServiceIncomeMoney> deleteByOrderIdList(@RequestBody UserServiceIncomeMoney incomeMoney) {
		LayuiDataTemplet<UserServiceIncomeMoney> returnData = new LayuiDataTemplet<UserServiceIncomeMoney>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (incomeMoney.getVersion() != null && !incomeMoney.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(incomeMoney.getVersion(), Config.VERSION);
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
		count = incomeMoneyService.deleteByOrderIdList(incomeMoney.getUuidlist());

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
