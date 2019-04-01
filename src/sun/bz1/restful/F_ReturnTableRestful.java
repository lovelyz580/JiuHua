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
import sun.bz1.entity.ReturnTable;
import sun.bz1.entity.ReturnTableAndOrderTableAndUser;
import sun.bz1.service.ReturnTableService;
import util.Config;
import util.VersionCompare;

/**
 * 维修人员退回维修单表(维修人员退回维修单时在该表中添加数据)
 * 
 * Restful
 * 
 * @author ZY on 2018/09/08
 */

@Controller
@RequestMapping("/return_table")
public class F_ReturnTableRestful {

	@Autowired
	private ReturnTableService returnTableService;

	/**
	 * 根据ReturnTable实体添加
	 * 
	 * @param returnTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/insertByReturnTable")
	public LayuiDataTemplet<ReturnTable> insertByReturnTable(@RequestBody ReturnTable returnTable) {
		LayuiDataTemplet<ReturnTable> returnData = new LayuiDataTemplet<ReturnTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (returnTable.getVersion() != null && !returnTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(returnTable.getVersion(), Config.VERSION);
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
		returnTable.setReturnid(Config.SIGN_RETURNTABLE + UUID.randomUUID().toString()); // 维修退回ID(WXTH+UUID)
		returnTable.setReturntime(new Date()); // 维修退回时间

		// 添加
		int count = 0;
		count = returnTableService.insertByReturnTable(returnTable);

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
	 * 根据ReturnTable实体更新
	 * 
	 * @param returnTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/updateByReturnTable")
	public LayuiDataTemplet<ReturnTable> updateByReturnTable(@RequestBody ReturnTable returnTable) {
		LayuiDataTemplet<ReturnTable> returnData = new LayuiDataTemplet<ReturnTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (returnTable.getVersion() != null && !returnTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(returnTable.getVersion(), Config.VERSION);
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
		if (returnTable.getId() == null || returnTable.getId() == 0) {
			if (returnTable.getReturnid() == null || returnTable.getReturnid().isEmpty()) {
				if (returnTable.getOrderid() == null || returnTable.getOrderid().isEmpty()) {
					returnData.setMsg("缺少更新条件，更新失败！");
					return returnData;
				}
			}
		}
				
		// 更新数据

		// 更新
		int count = 0;
		count = returnTableService.updateByReturnTable(returnTable);

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
	 * 根据ReturnTableAndOrderTableAndUser实体联表查询
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
	 * @author ZY on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionData")
	public LayuiDataTemplet<ReturnTableAndOrderTableAndUser> selectThreeTablesByUnionData(
			@RequestBody ReturnTableAndOrderTableAndUser unionData) {
		LayuiDataTemplet<ReturnTableAndOrderTableAndUser> returnData = new LayuiDataTemplet<ReturnTableAndOrderTableAndUser>(); // 返回数据
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
			count = returnTableService.selectThreeTablesCountByUnionData(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<ReturnTableAndOrderTableAndUser> returnTableList = returnTableService
						.selectThreeTablesByUnionData(unionData); // 查询数据
				returnData.setData(returnTableList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据ReturnTableAndOrderTableAndUser实体联表模糊查询
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
	 * @author ZY on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesBySelectData")
	public LayuiDataTemplet<ReturnTableAndOrderTableAndUser> selectThreeTablesBySelectData(
			@RequestBody ReturnTableAndOrderTableAndUser unionData) {
		LayuiDataTemplet<ReturnTableAndOrderTableAndUser> returnData = new LayuiDataTemplet<ReturnTableAndOrderTableAndUser>(); // 返回数据
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
			count = returnTableService.selectThreeTablesCountBySelectData(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<ReturnTableAndOrderTableAndUser> returnTableList = returnTableService
						.selectThreeTablesBySelectData(unionData); // 查询数据
				returnData.setData(returnTableList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param returnTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<ReturnTable> deleteByIdList(@RequestBody ReturnTable returnTable) {
		LayuiDataTemplet<ReturnTable> returnData = new LayuiDataTemplet<ReturnTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (returnTable.getVersion() != null && !returnTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(returnTable.getVersion(), Config.VERSION);
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
		count = returnTableService.deleteByIdList(returnTable.getIdlist());

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
	 * @param returnTable
	 * @return
	 * 
	 * @author ZY on 2018/09/08
	 */
	@ResponseBody
	@RequestMapping("/deleteByOrderIdOrReturnUserIdList")
	public LayuiDataTemplet<ReturnTable> deleteByOrderIdList(@RequestBody ReturnTable returnTable) {
		LayuiDataTemplet<ReturnTable> returnData = new LayuiDataTemplet<ReturnTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (returnTable.getVersion() != null && !returnTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(returnTable.getVersion(), Config.VERSION);
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
		count = returnTableService.deleteByOrderIdOrReturnUserIdList(returnTable.getOrderuuidlist());

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
