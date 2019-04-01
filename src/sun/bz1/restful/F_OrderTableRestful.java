package sun.bz1.restful;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import sun.LayuiDataTemplet;
import sun.bz1.entity.*;
import sun.bz1.service.*;
import util.Config;
import util.ImportExcelUtil;
import util.VersionCompare;
import util.WechatFormUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 维修单表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/07
 */

@Controller
@RequestMapping("/order_table")
public class F_OrderTableRestful {
	
	@Autowired
	private OrderTableService orderTableService;

	@Autowired
	private UserService userService;

	@Autowired
	private WechatFormService wechatFormService;

	@Autowired
	private WechatService wechatService;

	@Autowired
	private ApplyCheckService applyCheckService;

	@Autowired
	private UserPaymentService userPaymentService;

	@Autowired
	private OrderPriceService orderPriceService;
	
	// 格式化时间
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	private Logger logger = Logger.getLogger(F_OperationOrderRestful.class);

	/**
	 * 根据OrderTable实体添加
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/insertByOrderTable")
	public LayuiDataTemplet<OrderTable> insertByOrderTable(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		// 维修单ID(D+年月日+时分秒+6位随机数)
		// 生成6位随机数
		String random = String.valueOf((Math.random() * 1000000)).substring(0, 6);
		if (random.indexOf(".") != -1) {
			random = random.replace(".", "0"); // 将随机数中的.转换为0
		}
		String orderId = "D" + sdf.format(new Date()) + random;
		
		orderTable.setOrderid(orderId); // 维修单ID(D+年月日+时分秒+6位随机数)
		orderTable.setOrdercreatetime(new Date()); // 维修单创建时间
		orderTable.setOrderupdateuserid(orderTable.getOrdercreateuserid()); // 维修单更新人ID(YHBG+UUID)
		orderTable.setOrderupdatetime(new Date()); // 维修单更新时间
		
		// 添加
		int count = 0;
		count = orderTableService.insertByOrderTable(orderTable);
		
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
	 * 根据OrderTable实体更新
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/updateByOrderTable")
	public LayuiDataTemplet<OrderTable> updateByOrderTable(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getId() == null || orderTable.getId() == 0) {
			if (orderTable.getOrderid() == null || orderTable.getOrderid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		orderTable.setOrderupdatetime(new Date()); // 维修单更新时间
				
		// 更新
		int count = 0;
		count = orderTableService.updateByOrderTable(orderTable);
		
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
	 * 根据OrderTable实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/selectByOrderTable")
	public LayuiDataTemplet<OrderTable> selectByOrderTable(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getPagenumber() != null) {
			// 计算偏移量
			if (orderTable.getPagenumber() != -1) {
				if (orderTable.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = orderTable.getPagenumber();
				int pageSize = orderTable.getPagesize();
				orderTable.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				orderTable.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = orderTableService.selectCountByOrderTable(orderTable); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTable> orderList = orderTableService.selectByOrderTable(orderTable); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据OrderTable实体模糊查询
   	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<OrderTable> selectBySelectData(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getPagenumber() != null) {
			// 计算偏移量
			if (orderTable.getPagenumber() != -1) {
				if (orderTable.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = orderTable.getPagenumber();
				int pageSize = orderTable.getPagesize();
				orderTable.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				orderTable.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = orderTableService.selectCountBySelectData(orderTable); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTable> orderList = orderTableService.selectBySelectData(orderTable); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据OrderTable实体查询，返回OrderTableAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTable分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/12
	 */
	@ResponseBody
	@RequestMapping("/selectTwoTablestByOrderTable")
	public LayuiDataTemplet<OrderTableAndOrderDetailList> selectTwoTablestByOrderTable(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTableAndOrderDetailList> returnData = new LayuiDataTemplet<OrderTableAndOrderDetailList>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getPagenumber() != null) {
			// 计算偏移量
			if (orderTable.getPagenumber() != -1) {
				if (orderTable.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = orderTable.getPagenumber();
				int pageSize = orderTable.getPagesize();
				orderTable.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				orderTable.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = orderTableService.selectTwoTablesCountByOrderTable(orderTable); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndOrderDetailList> orderList = orderTableService.selectTwoTablesByOrderTable(orderTable); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据OrderTable实体查询，返回OrderTableAndOrderDetailConfirmList实体类
	 *
	 * 可以进行分页查询(按OrderTable分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/11/26
	 */
	@ResponseBody
	@RequestMapping("/selectOrderTableConfirmByOrderTable")
	public LayuiDataTemplet<OrderTableAndOrderDetailConfirmList> selectOrderTableConfirmByOrderTable(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTableAndOrderDetailConfirmList> returnData = new LayuiDataTemplet<OrderTableAndOrderDetailConfirmList>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getPagenumber() != null) {
			// 计算偏移量
			if (orderTable.getPagenumber() != -1) {
				if (orderTable.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = orderTable.getPagenumber();
				int pageSize = orderTable.getPagesize();
				orderTable.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				orderTable.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = orderTableService.selectOrderTableConfirmCountByOrderTable(orderTable);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndOrderDetailConfirmList> orderList = orderTableService.selectOrderTableConfirmByOrderTable(orderTable); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/14
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablestByUnionData")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablestByUnionData(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionData(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailList> orderList = orderTableService.selectThreeTablesByUnionData(unionData); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 这个方法主要是针对竞标，显示的单价是维修工自己维护的单价
	 * 
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/10/10
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablestByUnionDataAndPrice")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablestByUnionDataAndPrice(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionDataAndPrice(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailList> orderList = orderTableService.selectThreeTablesByUnionDataAndPrice(unionData); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 在申请验收时的详情中查看本次订单中的单价
	 * 
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/10/10
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablestByUnionDataAndCheckPrice")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablestByUnionDataAndCheckPrice(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionDataAndCheckPrice(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailList> orderList = orderTableService.selectThreeTablestByUnionDataAndCheckPrice(unionData); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 在维修工竞标时如果本人竞标过显示曾经竞标的钱
	 * 
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 * 
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
   	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/10/18
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablestByUnionDataAndSnatchMoney")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablestByUnionDataAndSnatchMoney(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionDataAndSnatchMoney(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailList> orderList = orderTableService.selectThreeTablestByUnionDataAndSnatchMoney(unionData); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 维修人员评价客户时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	@ResponseBody
	@RequestMapping("/selectEvaluateCustomerByOrderTable")
	public LayuiDataTemplet<OrderTable> selectEvaluateCustomerByOrderTable(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getPagenumber() != null) {
			// 计算偏移量
			if (orderTable.getPagenumber() != -1) {
				if (orderTable.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = orderTable.getPagenumber();
				int pageSize = orderTable.getPagesize();
				orderTable.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				orderTable.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = orderTableService.selectEvaluateCustomerCountByOrderTable(orderTable); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTable> orderList = orderTableService.selectEvaluateCustomerByOrderTable(orderTable); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 客户评价维修人员时用到
	 * 
	 * 根据OrderTable实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/10/11
	 */
	@ResponseBody
	@RequestMapping("/selectEvaluateServiceByOrderTable")
	public LayuiDataTemplet<OrderTable> selectEvaluateServiceByOrderTable(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getPagenumber() != null) {
			// 计算偏移量
			if (orderTable.getPagenumber() != -1) {
				if (orderTable.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = orderTable.getPagenumber();
				int pageSize = orderTable.getPagesize();
				orderTable.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				orderTable.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = orderTableService.selectEvaluateServiceCountByOrderTable(orderTable); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTable> orderList = orderTableService.selectEvaluateServiceByOrderTable(orderTable); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据idList删除信息
   	 * 
	 * @param orderTable
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<OrderTable> deleteByIdList(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		count = orderTableService.deleteByIdList(orderTable.getIdlist());

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
	 * 根据OrderTable实体更新平台确认收款
	 *
	 * 添加平台收支金额明细表
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/10/26
	 */
	@ResponseBody
	@RequestMapping("/updateByOrderTableAndBackMoney")
	public LayuiDataTemplet<OrderTable> updateByOrderTableAndBackMoney(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getId() == null || orderTable.getId() == 0) {
			if (orderTable.getOrderid() == null || orderTable.getOrderid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		orderTable.setOrderupdatetime(new Date()); // 维修单更新时间

		//通过订单编号查询该订单详情
		OrderTable orderTable1 = new OrderTable();
		orderTable1.setPagenumber(-1);//不分页
		orderTable1.setOrderid(orderTable.getOrderid());//订单编号
		List<OrderTable> orderTables = null;
		orderTables = orderTableService.selectByOrderTable(orderTable1);
		if (orderTables.size() == 0 ) {
			returnData.setMsg("未找到该订单！");
			return returnData;
		}
		if (orderTables.get(0).getOrderstate().equals("QRFK") || orderTables.get(0).getOrderstate().equals("YSK") ||
				orderTables.get(0).getOrderstate().equals("KHYPJ") || orderTables.get(0).getOrderstate().equals("DDJS")){

			returnData.setMsg("该订单已经确认收款，请勿重复确认！");
			return returnData;
		}

		UserPayment userPayment1 = new UserPayment();
		userPayment1.setPagenumber(-1);//不分页
		userPayment1.setOrderid(orderTable.getOrderid());//订单编号
		userPayment1.setRemitteruserid(orderTables.get(0).getOrdercreateuserid());//付款人
		List<UserPayment> userPaymentList = null;
		userPaymentList = userPaymentService.selectByUserPaymentTimeDesc(userPayment1);

		//更新用户付款表状态为已审核
		UserPayment userPayment2 = new UserPayment();
		userPayment2.setUserpaymentid(userPaymentList.get(0).getUserpaymentid());//用户付款表ID
		userPayment2.setOrderremark("YSH");//状态 已审核
        userPayment2.setUserpaymentplatformaccountname(orderTable.getUserpaymentplatformaccountname());//公司账号开户人
        userPayment2.setUserpaymentplatformaccount(orderTable.getUserpaymentplatformaccount());//公司账号
        userPayment2.setUserpaymentserviceaccount(orderTable.getUserpaymentserviceaccount());//公司账号开户地址
		userPayment2.setUserpaymentupdatetime(new Date());//更新时间

		// 添加平台_收益金额表
		BackMoney backMoney = new BackMoney();
		// 数据
		backMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 金额变化ID(PTJE+UUID)
		backMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		backMoney.setBackmoneyother1("DGZH"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户)/DGZH:线下对公账户))
        backMoney.setBackmoneyother2(userPaymentList.get(0).getUserpaymentid());//平台收益中和用户付款建立关系
		if (orderTables.get(0).getOrdertype().equals("PD")){
		backMoney.setBackmoney(userPaymentList.get(0).getOrderpricetotalmoney()); // 金额(可正可负)
			} else if (orderTables.get(0).getOrdertype().equals("QD")){
			 backMoney.setBackmoney(userPaymentList.get(0).getOrderpricetotalmoney());// 金额(可正可负;
		}
		backMoney.setBackmoneycreatetime(new Date()); // 金额创建时间

		// 更新
		int count = 0;
		count = orderTableService.updateByOrderTableAndBackMoney(orderTable, backMoney, userPayment2);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("更新失败！");
		} else {

			//通知客户付款的服务通知
			// 查询数据
			UserAndUserServiceAndUserCustomer selectDataPay = new UserAndUserServiceAndUserCustomer();
			selectDataPay.setUserid(orderTables.get(0).getOrdercreateuserid()); // 查询
			selectDataPay.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			// 查询数据
			List<UserAndUserServiceAndUserCustomer> userPayList = userService.selectThreeTablesBySelectData(selectDataPay);
			if (userPayList.size() != 0){
				for (int i = 0; i < userPayList.size(); i ++){
					WechatForm wechatForm = new WechatForm();
					wechatForm.setPagenumber(-1);//不分页
					wechatForm.setUserid(userPayList.get(i).getUserid());
					List<WechatForm> wechatFormList = wechatFormService.selectByWechatForm(wechatForm); // 查询数据
					if (wechatFormList.size() != 0) {
						String openId = "";
						String templateId = "";
						String formId = "";
						for (int j = 0; j < wechatFormList.size(); j ++) {
							Date createTime = wechatFormList.get(j).getCreatedate();
							Date nowTime = new Date();
							long cha = nowTime.getTime() - createTime.getTime();
							if (cha / (1000 * 60 * 60 * 24) > 6){
								List<Integer> deleteList = new ArrayList<Integer>();
								deleteList.add(wechatFormList.get(j).getId());
								wechatFormList.remove(j);
								wechatFormService.deleteByIdList(deleteList);
							}
						}
						openId = wechatFormList.get(0).getOpenid();
						templateId = "MTizY8yuXuaqZvdWXsdgpR4MgG6d-2bLMTqVepgADYk";
						formId = wechatFormList.get(0).getFormid();

						SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String token = F_WechatRestful.getAccessToken(wechatService);
						//String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
						String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", "平台确认收款：" + userPaymentList.get(0).getOrderpricetotalmoney() + "元", orderTables.get(0).getOrderid(), orderTables.get(0).getOrderprojectname(), "");
						boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
						List<Integer> idList = new ArrayList<Integer>();
						idList.add(wechatFormList.get(0).getId());
						wechatFormService.deleteByIdList(idList);

						logger.error("服务通知result" + result);
						System.out.println("服务通知result" + result);

					}
				}
			}
			returnData.setCount(count);
			returnData.setMsg("更新成功！");
		}

		return returnData;
	}

	/**
	 * 根据OrderTable实体更新维修人员确认收款
	 *
	 * 添加平台收支金额明细表
	 *
	 * 添加维修人员收支金额明细表记录
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/10/20
	 */
	@ResponseBody
	@RequestMapping("/updateByOrderTableAndServiceMoney")
	public LayuiDataTemplet<OrderTable> updateByOrderTableAndServiceMoney(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getId() == null || orderTable.getId() == 0) {
			if (orderTable.getOrderid() == null || orderTable.getOrderid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		orderTable.setOrderupdatetime(new Date()); // 维修单更新时间


		//通过订单编号查询该订单详情
		OrderTable orderTable1 = new OrderTable();
		orderTable1.setPagenumber(-1);//不分页
		orderTable1.setOrderid(orderTable.getOrderid());//订单编号
		List<OrderTable> orderTables = null;
		orderTables = orderTableService.selectByOrderTable(orderTable1);
		if (orderTables.size() == 0 ) {
			returnData.setMsg("未找到该订单！");
			return returnData;
		}
		if (orderTables.get(0).getOrderstate().equals("YSK") || orderTables.get(0).getOrderstate().equals("KHYPJ") || orderTables.get(0).getOrderstate().equals("DDJS") || orderTables.get(0).getOrderstate().equals("ALL_CHECK")){

			returnData.setMsg("您已经确认收款，请勿重复确认！");
			return returnData;
		}
		//更新订单状态是否为全部验收 如果是就更改订单状态
		if (null != orderTables.get(0).getOrdercheckallstate() && orderTables.get(0).getOrdercheckallstate().equals("ALL_CHECK")) {
			orderTable.setOrderstate("ALL_CHECK");
		}

		UserPayment userPayment1 = new UserPayment();
		userPayment1.setPagenumber(-1);//不分页
		userPayment1.setOrderid(orderTable.getOrderid());//订单编号
		userPayment1.setRemitteruserid(orderTables.get(0).getOrdercreateuserid());//付款人
		List<UserPayment> userPaymentList = null;
		userPaymentList = userPaymentService.selectByUserPaymentTimeDesc(userPayment1);

        //更新用户付款表状态(平台付给维修工钱)为已审核
        UserPayment userPayment2 = new UserPayment();
        userPayment2.setUserpaymentid(userPaymentList.get(0).getUserpaymentid());//用户付款表ID
        userPayment2.setUserpaymentplatformservicestate("YSH");//状态 已审核
        userPayment2.setUserpaymentupdatetime(new Date());//更新时间

		// 添加平台_收益金额表
		BackMoney backMoney = new BackMoney();
		// 数据
		backMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 金额变化ID(PTJE+UUID)
		backMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		backMoney.setBackmoneyother1("DGZH"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户)/DGZH:线下对公账户))
        backMoney.setBackmoneyother2(userPaymentList.get(0).getUserpaymentid());//用户付款表ID
		backMoney.setBackmoney(-userPaymentList.get(0).getOrderpricetotalmoney()); // 金额(可正可负)
		backMoney.setBackmoneycreatetime(new Date()); // 金额创建时间

		// 添加用户_维修工、安装队_收入_金额表
		UserServiceIncomeMoney serviceIncomeMoney = new UserServiceIncomeMoney();
		// 数据
		serviceIncomeMoney.setUserserviceincomemoneyid(Config.SIGN_USERSERVICEINCOMEMONEY + UUID.randomUUID().toString()); // 维修人员收入金额ID(WXSR+UUID)
		serviceIncomeMoney.setUserserviceincomemoneyuserid(orderTables.get(0).getOrderserviceuserid()); // 维修人员ID(YHBG+UUID)
		serviceIncomeMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		serviceIncomeMoney.setUserserviceincomemoneysource("DGZH"); // 金额来源(QRQXWX:确认取消(维修人员收入金额)/YSHG:验收合格(维修人员收入金额)/PTXTWX:平台协调(维修人员收入金额)/WXCZ:维修人员充值(维修人员收入金额)/DGZH:对公账户)
		serviceIncomeMoney.setUserserviceincomemoney(userPaymentList.get(0).getUserpaymentother2()); // 金额(可正可负)
		serviceIncomeMoney.setUserserviceincomemoneycreatetime(new Date()); // 金额创建时间

		// 更新
		int count = 0;
		count = orderTableService.updateByOrderTableAndServiceMoney(orderTable, backMoney, serviceIncomeMoney, userPayment2);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("更新失败！");
		} else {
			//通知维修付款的服务通知
			// 查询数据
			UserAndUserServiceAndUserCustomer selectDataPay = new UserAndUserServiceAndUserCustomer();
			selectDataPay.setUserid(orderTables.get(0).getOrderserviceuserid()); // 查询
			selectDataPay.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			// 查询数据
			List<UserAndUserServiceAndUserCustomer> userPayList = userService.selectThreeTablesBySelectData(selectDataPay);
			if (userPayList.size() != 0){
				for (int i = 0; i < userPayList.size(); i ++){
					WechatForm wechatForm = new WechatForm();
					wechatForm.setPagenumber(-1);//不分页
					wechatForm.setUserid(userPayList.get(i).getUserid());
					List<WechatForm> wechatFormList = wechatFormService.selectByWechatForm(wechatForm); // 查询数据
					if (wechatFormList.size() != 0) {
						String openId = "";
						String templateId = "";
						String formId = "";
						for (int j = 0; j < wechatFormList.size(); j ++) {
							Date createTime = wechatFormList.get(j).getCreatedate();
							Date nowTime = new Date();
							long cha = nowTime.getTime() - createTime.getTime();
							if (cha / (1000 * 60 * 60 * 24) > 6){
								List<Integer> deleteList = new ArrayList<Integer>();
								deleteList.add(wechatFormList.get(j).getId());
								wechatFormList.remove(j);
								wechatFormService.deleteByIdList(deleteList);
							}
						}
						openId = wechatFormList.get(0).getOpenid();
						templateId = "MTizY8yuXuaqZvdWXsdgpR4MgG6d-2bLMTqVepgADYk";
						formId = wechatFormList.get(0).getFormid();

						SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String token = F_WechatRestful.getAccessToken(wechatService);
						//String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
						String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", "确认收款：" + (orderTables.get(0).getOrderpricetotalmoney() + orderTables.get(0).getOrdertraveltotalmoney()) + "元", orderTables.get(0).getOrderid(), orderTables.get(0).getOrderprojectname(), "");
						boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
						List<Integer> idList = new ArrayList<Integer>();
						idList.add(wechatFormList.get(0).getId());
						wechatFormService.deleteByIdList(idList);

						logger.error("服务通知result" + result);
						System.out.println("服务通知result" + result);

					}
				}
			}

            //通知维修人员等待客户评价
            // 查询数据
            UserAndUserServiceAndUserCustomer selectServiceDataPay = new UserAndUserServiceAndUserCustomer();
            selectServiceDataPay.setUserid(orderTables.get(0).getOrderserviceuserid()); // 查询
            selectServiceDataPay.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

            // 查询数据
            List<UserAndUserServiceAndUserCustomer> userServiceList = userService.selectThreeTablesBySelectData(selectServiceDataPay);
            if (userServiceList.size() != 0){
                for (int i = 0; i < userServiceList.size(); i ++){
                    WechatForm wechatForm = new WechatForm();
                    wechatForm.setPagenumber(-1);//不分页
                    wechatForm.setUserid(userServiceList.get(i).getUserid());
                    List<WechatForm> wechatFormList = wechatFormService.selectByWechatForm(wechatForm); // 查询数据
                    if (wechatFormList.size() != 0) {
                        String openId = "";
                        String templateId = "";
                        String formId = "";
                        for (int j = 0; j < wechatFormList.size(); j ++) {
                            Date createTime = wechatFormList.get(j).getCreatedate();
                            Date nowTime = new Date();
                            long cha = nowTime.getTime() - createTime.getTime();
                            if (cha / (1000 * 60 * 60 * 24) > 6){
                                List<Integer> deleteList = new ArrayList<Integer>();
                                deleteList.add(wechatFormList.get(j).getId());
                                wechatFormList.remove(j);
                                wechatFormService.deleteByIdList(deleteList);
                            }
                        }
                        openId = wechatFormList.get(0).getOpenid();
                        templateId = "2raCYGVRR9xf_65McOO7-HA8IdufpLcZBIww9v-Z1d0";
                        formId = wechatFormList.get(0).getFormid();

                        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String token = F_WechatRestful.getAccessToken(wechatService);
                        //String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
                        String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", orderTables.get(0).getOrderid(), orderTables.get(0).getOrderprojectname(), dateFormate.format(orderTables.get(0).getOrderupdatetime()), "收款完成，请等待客户评价！");
                        boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
                        List<Integer> idList = new ArrayList<Integer>();
                        idList.add(wechatFormList.get(0).getId());
                        wechatFormService.deleteByIdList(idList);

                        logger.error("服务通知result" + result);
                        System.out.println("服务通知result" + result);

                    }
                }
            }

            //通知客户评价维修人员
            // 查询数据
            UserAndUserServiceAndUserCustomer selectCustomerDataPay = new UserAndUserServiceAndUserCustomer();
            selectCustomerDataPay.setUserid(orderTables.get(0).getOrdercreateuserid()); // 查询
            selectCustomerDataPay.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

            // 查询数据
            List<UserAndUserServiceAndUserCustomer> userCustomerList = userService.selectThreeTablesBySelectData(selectCustomerDataPay);
            if (userCustomerList.size() != 0){
                for (int i = 0; i < userCustomerList.size(); i ++){
                    WechatForm wechatForm = new WechatForm();
                    wechatForm.setPagenumber(-1);//不分页
                    wechatForm.setUserid(userCustomerList.get(i).getUserid());
                    List<WechatForm> wechatFormList = wechatFormService.selectByWechatForm(wechatForm); // 查询数据
                    if (wechatFormList.size() != 0) {
                        String openId = "";
                        String templateId = "";
                        String formId = "";
                        for (int j = 0; j < wechatFormList.size(); j ++) {
                            Date createTime = wechatFormList.get(j).getCreatedate();
                            Date nowTime = new Date();
                            long cha = nowTime.getTime() - createTime.getTime();
                            if (cha / (1000 * 60 * 60 * 24) > 6){
                                List<Integer> deleteList = new ArrayList<Integer>();
                                deleteList.add(wechatFormList.get(j).getId());
                                wechatFormList.remove(j);
                                wechatFormService.deleteByIdList(deleteList);
                            }
                        }
                        openId = wechatFormList.get(0).getOpenid();
                        templateId = "2raCYGVRR9xf_65McOO7-HA8IdufpLcZBIww9v-Z1d0";
                        formId = wechatFormList.get(0).getFormid();

                        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String token = F_WechatRestful.getAccessToken(wechatService);
                        //String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
                        String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", orderTables.get(0).getOrderid(), orderTables.get(0).getOrderprojectname(), dateFormate.format(orderTables.get(0).getOrderupdatetime()), "请对服务人员进行评价！");
                        boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
                        List<Integer> idList = new ArrayList<Integer>();
                        idList.add(wechatFormList.get(0).getId());
                        wechatFormService.deleteByIdList(idList);

                        logger.error("服务通知result" + result);
                        System.out.println("服务通知result" + result);

                    }
                }
            }
			returnData.setCount(count);
			returnData.setMsg("更新成功！");
		}

		return returnData;
	}

	/**
	 * 根据OrderTable实体更新平台确认收款
	 *
	 * 添加线上付款平台收益记录
	 *
	 * 添加维修工首页记录
	 *
	 * 添加维修工余额
	 *
	 *
	 * @param orderTable
	 * @return
	 *
	 * @author ZY on 2018/10/26
	 */
	@ResponseBody
	@RequestMapping("/updateByOrderTableAndBackMoneyAndServiceMoney")
	public LayuiDataTemplet<OrderTable> updateByOrderTableAndBackMoneyAndServiceMoney(@RequestBody OrderTable orderTable) {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderTable.getVersion() != null && !orderTable.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderTable.getVersion(), Config.VERSION);
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
		if (orderTable.getId() == null || orderTable.getId() == 0) {
			if (orderTable.getOrderid() == null || orderTable.getOrderid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		orderTable.setOrderstate("YSK");//更新订单表状态(线上或者线下交易维修工已收款)
		orderTable.setOrderupdatetime(new Date()); // 维修单更新时间

		//通过订单编号查询该订单详情
		OrderTable orderTable1 = new OrderTable();
		orderTable1.setPagenumber(-1);//不分页
		orderTable1.setOrderid(orderTable.getOrderid());//订单编号
		List<OrderTable> orderTables = null;
		orderTables = orderTableService.selectByOrderTable(orderTable1);
		if (orderTables.size() == 0 ) {
			returnData.setMsg("未找到该订单！");
			return returnData;
		}
		if (orderTables.get(0).getOrderstate().equals("YSK") ||
				orderTables.get(0).getOrderstate().equals("KHYPJ") || orderTables.get(0).getOrderstate().equals("DDJS")|| orderTables.get(0).getOrderstate().equals("ALL_CHECK")){

			returnData.setMsg("该订单已经付款给维修、安装人员，请勿重复确认！");
			return returnData;
		}

		//更新订单状态是否为全部验收 如果是就更改订单状态
		if (null != orderTables.get(0).getOrdercheckallstate() && orderTables.get(0).getOrdercheckallstate().equals("ALL_CHECK")) {
			orderTable.setOrderstate("ALL_CHECK");
		}

		//查询最新验收时付款金额
		ApplyCheckAndOrderTableAndUser applyCheckAndOrderTableAndUser = new ApplyCheckAndOrderTableAndUser();
		applyCheckAndOrderTableAndUser.setPagenumber(-1);//不分页
		applyCheckAndOrderTableAndUser.setOrderid(orderTable.getOrderid());//订单编号
		List<ApplyCheckAndOrderTableAndUser> applyCheckList = applyCheckService.selectThreeTablesByUnionDataByTimeDesc(applyCheckAndOrderTableAndUser); // 查询数据

		UserPayment userPayment1 = new UserPayment();
		userPayment1.setPagenumber(-1);//不分页
		userPayment1.setOrderid(orderTable.getOrderid());//订单编号
		userPayment1.setRemitteruserid(orderTables.get(0).getOrdercreateuserid());//付款人
		List<UserPayment> userPaymentList = null;
		userPaymentList = userPaymentService.selectByUserPaymentTimeDesc(userPayment1);

		//更新用户付款表状态为已审核
		UserPayment userPayment2 = new UserPayment();
		userPayment2.setUserpaymentid(userPaymentList.get(0).getUserpaymentid());//用户付款表ID
		userPayment2.setOrderremark("YSH");//状态 已审核
        userPayment2.setUserpaymentplatformservicestate("YSH");//付款给维修人员状态已审核
        userPayment2.setUserpaymentplatformaccountname(orderTable.getUserpaymentplatformaccountname());//公司账号开户人
        userPayment2.setUserpaymentplatformaccount(orderTable.getUserpaymentplatformaccount());//公司账号
        userPayment2.setUserpaymentserviceaccount(orderTable.getUserpaymentserviceaccount());//公司账号开户行地址
		userPayment2.setUserpaymentupdatetime(new Date());//更新时间


		// 添加平台_收益金额表
		BackMoney backMoney = new BackMoney();
		// 数据
		backMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 金额变化ID(PTJE+UUID)
		backMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		backMoney.setBackmoneyother1("XSFK"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户)/DGZH:线下对公账户))
        backMoney.setBackmoneyother2(userPaymentList.get(0).getUserpaymentid());//用户付款表ID
		backMoney.setBackmoney(-userPaymentList.get(0).getUserpaymentother2());// 金额(可正可负;
		backMoney.setBackmoneycreatetime(new Date()); // 金额创建时间

		// 添加用户_维修工、安装队_收入_金额表
		UserServiceIncomeMoney serviceIncomeMoney = new UserServiceIncomeMoney();
		// 数据
		serviceIncomeMoney.setUserserviceincomemoneyid(Config.SIGN_USERSERVICEINCOMEMONEY + UUID.randomUUID().toString()); // 维修人员收入金额ID(WXSR+UUID)
		serviceIncomeMoney.setUserserviceincomemoneyuserid(orderTables.get(0).getOrderserviceuserid()); // 维修人员ID(YHBG+UUID)
		serviceIncomeMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		serviceIncomeMoney.setUserserviceincomemoneysource("XSFK"); // 金额来源(QRQXWX:确认取消(维修人员收入金额)/YSHG:验收合格(维修人员收入金额)/PTXTWX:平台协调(维修人员收入金额)/WXCZ:维修人员充值(维修人员收入金额)/DGZH:对公账户)
		serviceIncomeMoney.setUserserviceincomemoney(userPaymentList.get(0).getUserpaymentother2()); // 金额(可正可负)
		serviceIncomeMoney.setUserserviceincomemoneycreatetime(new Date()); // 金额创建时间


		//double money = orderTables.get(0).getOrderpricetotalmoney() + orderTables.get(0).getOrdertraveltotalmoney();
		double money = userPaymentList.get(0).getUserpaymentother2();
		// 更新用户的余额
		// 查询的实体
		UserAndUserServiceAndUserCustomer unionData = new UserAndUserServiceAndUserCustomer();
		unionData.setUserid(orderTables.get(0).getOrderserviceuserid()); // 用户ID(YHBG+UUID)
		unionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

		// 查询
		List<UserAndUserServiceAndUserCustomer> unionDataList = userService.selectThreeTablesBySelectData(unionData);
		User updateUser = new User();
		if (unionDataList != null  && unionDataList.size() != 0) {
			unionData = unionDataList.get(0);

			// 更新的实体

			updateUser.setUserid(orderTables.get(0).getOrderserviceuserid()); // 用户ID(YHBG+UUID)
			updateUser.setUserrole(unionData.getUserrole()); // 用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
			updateUser.setUsermoney(unionData.getUsermoney() + money); // 用户余额
		}

		// 更新
		int count = 0;
		count = orderTableService.updateByOrderTableAndBackMoneyAndServiceMoney(orderTable, backMoney, serviceIncomeMoney, updateUser,userPayment2);

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
	 * 在订单变更时回显针对订单维护的价钱时使用
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/09/14
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesAndOrderPriceByUnionData")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablesAndOrderPriceByUnionData(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionData(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailList> orderList = orderTableService.selectThreeTablesByUnionData(unionData); // 查询数据
				for (int i = 0; i < orderList.get(0).getOrderDetailList().size(); i++){
					OrderPriceAndGoodsAndProjectAndUser orderPrice = new OrderPriceAndGoodsAndProjectAndUser();
					orderPrice.setOrderid(unionData.getOrderid());//订单编号
					orderPrice.setOrderpriceupdateuserid(orderList.get(0).getOrderTableAndBuildingType().getOrderserviceuserid());//维修人员userid
					orderPrice.setGoodsid(orderList.get(0).getOrderDetailList().get(i).getGoodsid());//产品id
					orderPrice.setProjectid(orderList.get(0).getOrderDetailList().get(i).getProjectid());//清单id
					orderPrice.setOrderpricestate("Y");//有效
					List<OrderPriceAndGoodsAndProjectAndUser> orderPriceList = new ArrayList<OrderPriceAndGoodsAndProjectAndUser>();
					orderPriceList = orderPriceService.selectFourTablesByUnionData(orderPrice);

					if (orderPriceList.size() > 0) {
						if (orderPriceList.get(0).getGoodsid().equals(orderList.get(0).getOrderDetailList().get(i).getGoodsid()) && orderPriceList.get(0).getProjectid().equals(orderList.get(0).getOrderDetailList().get(i).getProjectid())){
							orderList.get(0).getOrderDetailList().get(i).setOrderdetailpricemoney(orderPriceList.get(0).getOrderpricemoney());
						}
					}
				}
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


	/**
	 * 生成订单报表时用到
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/4
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionDataReport")
	public LayuiDataTemplet<OrderTableAndBuildingType> selectThreeTablesByUnionDataReport(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingType> returnData = new LayuiDataTemplet<OrderTableAndBuildingType>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionData(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingType> orderList = orderTableService.selectThreeTablesByUnionDataReport(unionData); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


	/**
	 * 生成订单报表时用到(导出报表)
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/4
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionDataReportExcel")
	public LayuiDataTemplet<OrderTableAndBuildingType> selectThreeTablesByUnionDataReportExcel(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingType> returnData = new LayuiDataTemplet<OrderTableAndBuildingType>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionData(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingType> orderList = orderTableService.selectThreeTablesByUnionDataReportExcel(unionData); // 查询数据
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 用户报表中查看清单次数的详情页面
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/7
	 */
	@ResponseBody
	@RequestMapping("/selectFourTablesDetailRport")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> selectFourTablesDetailRport(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject>(); // 返回数据
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
			count = orderTableService.selectFourTablesCountDetailRport(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> orderList = orderTableService.selectFourTablesDetailRport(unionData); // 查询数据


				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


	/**
	 * 订单报表中查询金额的详情数据（清单的分别报价）
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/09/14
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesAndOrderPriceByUnionDataReport")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablesAndOrderPriceByUnionDataReport(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionData(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailList> orderList = orderTableService.selectThreeTablesByUnionData(unionData); // 查询数据
				for (int i = 0; i < orderList.get(0).getOrderDetailList().size(); i++){
					OrderPriceAndGoodsAndProjectAndUser orderPrice = new OrderPriceAndGoodsAndProjectAndUser();
					orderPrice.setOrderid(unionData.getOrderid());//订单编号
					orderPrice.setOrderpriceupdateuserid(orderList.get(0).getOrderTableAndBuildingType().getOrderserviceuserid());//维修人员userid
					orderPrice.setGoodsid(orderList.get(0).getOrderDetailList().get(i).getGoodsid());//产品id
					orderPrice.setProjectid(orderList.get(0).getOrderDetailList().get(i).getProjectid());//清单id
					orderPrice.setOrderpricestate("Y");//有效
					List<OrderPriceAndGoodsAndProjectAndUser> orderPriceList = new ArrayList<OrderPriceAndGoodsAndProjectAndUser>();
					orderPriceList = orderPriceService.selectFourTablesByUnionData(orderPrice);

					if (orderPriceList.size() > 0) {
						if (orderPriceList.get(0).getGoodsid().equals(orderList.get(0).getOrderDetailList().get(i).getGoodsid()) && orderPriceList.get(0).getProjectid().equals(orderList.get(0).getOrderDetailList().get(i).getProjectid())){
							orderList.get(0).getOrderDetailList().get(i).setOrderdetailpricemoney(orderPriceList.get(0).getOrderpricemoney());
						}
					}
				}
				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


	/**
	 * 区域报表中该维修收入总价的详情
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/09/14
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionDataTotalMoneyReport")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablesByUnionDataTotalMoneyReport(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionData(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailList> orderList = orderTableService.selectThreeTablesByUnionData(unionData); // 查询数据
				for (int j = 0; j < orderList.size(); j++) {
					if (orderList.get(j).getOrderTableAndBuildingType().getOrdertype().equals("QD")){
					for (int i = 0; i < orderList.get(j).getOrderDetailList().size(); i++) {
						OrderPriceAndGoodsAndProjectAndUser orderPrice = new OrderPriceAndGoodsAndProjectAndUser();
						orderPrice.setOrderid(orderList.get(j).getOrderTableAndBuildingType().getOrderid());//订单编号
						orderPrice.setOrderpriceupdateuserid(orderList.get(j).getOrderTableAndBuildingType().getOrderserviceuserid());//维修人员userid
						orderPrice.setGoodsid(orderList.get(j).getOrderDetailList().get(i).getGoodsid());//产品id
						orderPrice.setProjectid(orderList.get(j).getOrderDetailList().get(i).getProjectid());//清单id
						orderPrice.setOrderpricestate("Y");//有效
						List<OrderPriceAndGoodsAndProjectAndUser> orderPriceList = new ArrayList<OrderPriceAndGoodsAndProjectAndUser>();
						orderPriceList = orderPriceService.selectFourTablesByUnionData(orderPrice);

						if (orderPriceList.size() > 0) {
							if (orderPriceList.get(0).getGoodsid().equals(orderList.get(j).getOrderDetailList().get(i).getGoodsid()) && orderPriceList.get(0).getProjectid().equals(orderList.get(j).getOrderDetailList().get(i).getProjectid())) {
								orderList.get(j).getOrderDetailList().get(i).setOrderdetailpricemoney(orderPriceList.get(0).getOrderpricemoney());
							}
						}
					}
					} else {
						for (int i = 0; i < orderList.get(j).getOrderDetailList().size(); i++) {
							orderList.get(j).getOrderDetailList().get(i).setOrderdetailpricemoney(orderList.get(j).getOrderDetailList().get(i).getOrderdetailintercepttotalmoney()/orderList.get(j).getOrderDetailList().get(i).getOrderdetailnumber());
						}
					}
				}
					returnData.setData(orderList);

			}

		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 区域报表中该区域采购总价的详情
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/09/14
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionDataPurchaseTotalMoneyReport")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> selectThreeTablesByUnionDataPurchaseTotalMoneyReport(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailList>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionData(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailList> orderList = orderTableService.selectThreeTablesByUnionData(unionData); // 查询数据
				for (int j = 0; j < orderList.size(); j++) {
					for (int i = 0; i < orderList.get(j).getOrderDetailList().size(); i++) {
						OrderPriceAndGoodsAndProjectAndUser orderPrice = new OrderPriceAndGoodsAndProjectAndUser();
						orderPrice.setOrderid(orderList.get(j).getOrderTableAndBuildingType().getOrderid());//订单编号
						orderPrice.setOrderpriceupdateuserid(orderList.get(j).getOrderTableAndBuildingType().getOrderserviceuserid());//维修人员userid
						orderPrice.setGoodsid(orderList.get(j).getOrderDetailList().get(i).getGoodsid());//产品id
						orderPrice.setProjectid(orderList.get(j).getOrderDetailList().get(i).getProjectid());//清单id
						orderPrice.setOrderpricestate("Y");//有效
						List<OrderPriceAndGoodsAndProjectAndUser> orderPriceList = new ArrayList<OrderPriceAndGoodsAndProjectAndUser>();
						orderPriceList = orderPriceService.selectFourTablesByUnionData(orderPrice);

						if (orderPriceList.size() > 0) {
							if (orderPriceList.get(0).getGoodsid().equals(orderList.get(j).getOrderDetailList().get(i).getGoodsid()) && orderPriceList.get(0).getProjectid().equals(orderList.get(j).getOrderDetailList().get(i).getProjectid())) {
								orderList.get(j).getOrderDetailList().get(i).setOrderdetailpricemoney(orderPriceList.get(0).getOrderpricemoney());
							}
						}
					}
				}
					returnData.setData(orderList);

			}

		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


	/**
	 * 区域报表中该区域利润总价的详情
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailList实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/09/14
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionDataProfitTotalMoneyReport")
	public LayuiDataTemplet<OrderTableAndBuildingType> selectThreeTablesByUnionDataProfitTotalMoneyReport(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingType> returnData = new LayuiDataTemplet<OrderTableAndBuildingType>(); // 返回数据
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
			count = orderTableService.selectThreeTablesCountByUnionDataProfitTotalMoneyReport(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingType> orderList = orderTableService.selectThreeTablesByUnionDataProfitTotalMoneyReport(unionData); // 查询数据
				for (int j = 0; j < orderList.size(); j++) {
					if (orderList.get(j).getOrdertype().equals("QD")){
						orderList.get(j).setProfittotalmoney(orderList.get(j).getOrderpricetotalmoney() - orderList.get(j).getOrderpricetotalmoney() );
					} else {
						orderList.get(j).setProfittotalmoney(orderList.get(j).getOrderintercepttotalmoney() - orderList.get(j).getOrderpricetotalmoney() );
					}

				}
					returnData.setData(orderList);

			}

		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 区域报表中该维修收入总价的详情
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/7
	 */
	@ResponseBody
	@RequestMapping("/selectFourTablesTotalMoneyReport")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> selectFourTablesTotalMoneyReport(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject>(); // 返回数据
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
			count = orderTableService.selectFourTablesCountDetailRport(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> orderList = orderTableService.selectFourTablesDetailRport(unionData); // 查询数据

				for (int j = 0; j < orderList.size(); j++) {
					if (orderList.get(j).getOrdertype().equals("QD")){
							OrderPriceAndGoodsAndProjectAndUser orderPrice = new OrderPriceAndGoodsAndProjectAndUser();
							orderPrice.setOrderid(orderList.get(j).getOrderid());//订单编号
							orderPrice.setOrderpriceupdateuserid(orderList.get(j).getOrderserviceuserid());//维修人员userid
							orderPrice.setGoodsid(orderList.get(j).getGoodsid());//产品id
							orderPrice.setProjectid(orderList.get(j).getProjectid());//清单id
							orderPrice.setOrderpricestate("Y");//有效
							List<OrderPriceAndGoodsAndProjectAndUser> orderPriceList = new ArrayList<OrderPriceAndGoodsAndProjectAndUser>();
							orderPriceList = orderPriceService.selectFourTablesByUnionData(orderPrice);

							if (orderPriceList.size() > 0) {
								if (orderPriceList.get(0).getGoodsid().equals(orderList.get(j).getGoodsid()) && orderPriceList.get(0).getProjectid().equals(orderList.get(j).getProjectid())) {
									orderList.get(j).setOrderdetailpricemoney(orderPriceList.get(0).getOrderpricemoney());
									orderList.get(j).setOrderdetailintercepttotalmoney(orderPriceList.get(0).getOrderpricemoney() * orderList.get(j).getOrderdetailnumber());
								}
							}

					} else {
						for (int i = 0; i < orderList.size(); i++) {
							orderList.get(j).setOrderdetailpricemoney(orderList.get(j).getOrderdetailintercepttotalmoney()/orderList.get(j).getOrderdetailnumber());
						}
					}
				}


				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


	/**
	 * 区域报表中该区域采购总价的详情
	 *
	 * 根据OrderTableAndBuildingType实体查询，返回OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject实体类
	 *
	 * 可以进行分页查询(按OrderTableAndBuildingType分页查询)
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param unionData
	 * @return
	 *
	 * @author ZY on 2018/12/7
	 */
	@ResponseBody
	@RequestMapping("/selectFourTablesPurchaseTotalMoneyReport")
	public LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> selectFourTablesPurchaseTotalMoneyReport(@RequestBody OrderTableAndBuildingType unionData) {
		LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> returnData = new LayuiDataTemplet<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject>(); // 返回数据
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
			count = orderTableService.selectFourTablesCountDetailRport(unionData);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderTableAndBuildingTypeAndOrderDetailListAndGoodsAndProject> orderList = orderTableService.selectFourTablesDetailRport(unionData); // 查询数据

				for (int j = 0; j < orderList.size(); j++) {
						OrderPriceAndGoodsAndProjectAndUser orderPrice = new OrderPriceAndGoodsAndProjectAndUser();
						orderPrice.setOrderid(orderList.get(j).getOrderid());//订单编号
						orderPrice.setOrderpriceupdateuserid(orderList.get(j).getOrderserviceuserid());//维修人员userid
						orderPrice.setGoodsid(orderList.get(j).getGoodsid());//产品id
						orderPrice.setProjectid(orderList.get(j).getProjectid());//清单id
						orderPrice.setOrderpricestate("Y");//有效
						List<OrderPriceAndGoodsAndProjectAndUser> orderPriceList = new ArrayList<OrderPriceAndGoodsAndProjectAndUser>();
						orderPriceList = orderPriceService.selectFourTablesByUnionData(orderPrice);

						if (orderPriceList.size() > 0) {
							if (orderPriceList.get(0).getGoodsid().equals(orderList.get(j).getGoodsid()) && orderPriceList.get(0).getProjectid().equals(orderList.get(j).getProjectid())) {
								orderList.get(j).setOrderdetailpricemoney(orderPriceList.get(0).getOrderpricemoney());
								orderList.get(j).setOrderdetailintercepttotalmoney(orderPriceList.get(0).getOrderpricemoney() * orderList.get(j).getOrderdetailnumber());
							}
						}

				}


				returnData.setData(orderList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}




	/**
	 *导入订单excel
	 *
	 * 解析文件
	 *
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 *
	 * @author ZY on 2019/02/15
	 */
	@ResponseBody
	@RequestMapping("/importOrderTable")
	public LayuiDataTemplet<OrderTable> importOrderTable(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
		LayuiDataTemplet<OrderTable> returnData = new LayuiDataTemplet<OrderTable>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		OrderTable data = new OrderTable(); // 解析结果
		List<OrderTable> dataList = new ArrayList<OrderTable>(); // 返回的数据

		try {
			ImportExcelUtil util = new ImportExcelUtil(); // 导入Excel解析工具

			String realPath = request.getSession().getServletContext().getRealPath("/resources/upload");
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));

			// 解析文件
			/*// 首页
			B_S_Basic basic = new B_S_Basic(); // 基本表数据
			List<BasicImportFile> basicImportFileList = new ArrayList<BasicImportFile>();
			basicImportFileList = util.convertSheetToList(new FileInputStream(new File(realPath, file.getName())), 1, BasicImportFile.class, true, 0, 500); // 文件流，第1个工作表格(从0开始)，解析的实体类对象，是否为Excel2003，开始解析行数，最大行数
			if (basicImportFileList.size() == 0) {
				returnData.setMessage("数据为空，解析失败！");
				returnData.setDataList(null);
				return returnData;
			} else {
				// 解析
				for (int i = 0; i < basicImportFileList.size(); i++) {
					if (i == 0) {
						// 判断不为空
						String write1 = basicImportFileList.get(i).getWrite1(); // 扣除年度
						if (write1 == null || write1.isEmpty()) {
							returnData.setIsPass(0);
							returnData.setMessage("请按要求填写完整！");
							returnData.setDataList(null);
							return returnData;
						}
						basic.setBasicyear(basicImportFileList.get(i).getWrite1()); // 扣除年度
						basic.setBasicname(basicImportFileList.get(i).getWrite2()); // 纳税人姓名
					} else if (i == 1) {
						basic.setBasiccredentialstype(basicImportFileList.get(i).getWrite1()); // 纳税人身份证件类型
						basic.setBasiccredentialsnumber(basicImportFileList.get(i).getWrite2()); // 纳税人身份证件号码
					} else if (i == 2) {
						basic.setBasicphone(basicImportFileList.get(i).getWrite1()); // 手机号码
						basic.setBasicidentifynumber(basicImportFileList.get(i).getWrite2()); // 纳税人识别号码
					} else if (i == 3) {
						basic.setBasicaddress(basicImportFileList.get(i).getWrite1()); // 联系地址
						basic.setBasicemail(basicImportFileList.get(i).getWrite2()); // 电子邮箱
					} else if (i == 4) {
						basic.setBasiccompanyname(basicImportFileList.get(i).getWrite1()); // 扣缴义务人名称(支付工资薪金的单位)
						basic.setBasiccompanyidentifynumber(basicImportFileList.get(i).getWrite2()); // 扣缴义务人纳税人识别号(统一社会信用代码)
					} else if (i == 5) {
						basic.setBasicspousestate(basicImportFileList.get(i).getWrite1()); // 配偶情况
						basic.setBasicspousename(basicImportFileList.get(i).getWrite2()); // 配偶姓名
					} else if (i == 6) {
						basic.setBasicspousecredentialstype(basicImportFileList.get(i).getWrite1()); // 配偶身份证件类型
						basic.setBasicspousecredentialsnumber(basicImportFileList.get(i).getWrite2()); // 配偶身份证件号码
					}
				}
			}*/

			// 子女教育支出
			List<OrderTableImportFile> childrenImportFileList = new ArrayList<OrderTableImportFile>();
			childrenImportFileList = util.convertSheetToList(new FileInputStream(new File(realPath, file.getOriginalFilename())), 0, OrderTableImportFile.class, true, 1, 500); // 文件流，第2个工作表格(从0开始)，解析的实体类对象，是否为Excel2003，开始解析行数，最大行数
			if (childrenImportFileList.size() != 0) {
				if (childrenImportFileList.get(0).getWrite1() != null && !childrenImportFileList.get(0).getWrite1().isEmpty()) {
					// 子女教育支出项不为空
					//basic.setBasictype(1); // 专项附加扣除类型(1:子女教育支出  2:住房租金支出  3:住房贷款利息支出  4:赡养老人支出  5:继续教育支出)

					// 解析
					List<OrderTable> childrenList = new ArrayList<OrderTable>();
					for (int i = 0; i < childrenImportFileList.size(); i++) {
						// 判断表格数据
						String write1 = childrenImportFileList.get(i).getWrite1();
						if (write1 == null || write1.isEmpty()) {
							break;
						}

						// 判断不为空
						boolean flag = false;
						/*String write2 = childrenImportFileList.get(i).getWrite2(); // 身份证件类型
						String write3 = childrenImportFileList.get(i).getWrite3(); // 身份证号码(出生日期)
						String write4 = childrenImportFileList.get(i).getWrite4(); // 出生日期
						String write7 = childrenImportFileList.get(i).getWrite7(); // 当前受教育阶段起始时间
						if ("居民身份证".equals(write2)) {
							if (write3 == null || write3.isEmpty()) {
								flag = true;
							}
						} else {
							if (write4 == null || write4.isEmpty()) {
								flag = true;
							}
						}
						if (write7 == null || write7.isEmpty()) {
							flag = true;
						}
						if (flag) {
							returnData.setIsPass(0);
							returnData.setMessage("请按要求填写完整！");
							returnData.setDataList(null);
							return returnData;
						}*/

						OrderTable children = new OrderTable(); // 子女教育支出表数据

						children.setOrderprojectname(childrenImportFileList.get(i).getWrite1()); // 子女姓名
						children.setOrdercontactperson(childrenImportFileList.get(i).getWrite2());
						/*children.setChildrencredentialstype(childrenImportFileList.get(i).getWrite2()); // 身份证件类型
						children.setChildrencredentialsnumber(childrenImportFileList.get(i).getWrite3()); // 身份证件号码
						// children.setChildrenbirthday(sdfOfDate.parse(childrenImportFileList.get(i).getWrite4())); // 出生日期
						children.setChildrennationality(childrenImportFileList.get(i).getWrite5()); // 国籍(地区)
						children.setChildrenstage(childrenImportFileList.get(i).getWrite6()); // 当前受教育阶段
						children.setChildrenstagestarttime(sdfOfDate.parse(childrenImportFileList.get(i).getWrite7())); // 当前受教育阶段起始时间
						children.setChildreneducationcountry(childrenImportFileList.get(i).getWrite10()); // 当前就读国家(地区)
						children.setChildreneducationschool(childrenImportFileList.get(i).getWrite11()); // 当前就读学校
						children.setChildrendeductionratio(childrenImportFileList.get(i).getWrite12()); // 本人扣除比例
						// 出生日期
						if ("居民身份证".equals(childrenImportFileList.get(i).getWrite2())) {
							String birthday = childrenImportFileList.get(i).getWrite3().substring(6, 14);
							String year = birthday.substring(0, 4);
							String month = birthday.substring(4, 6);
							String day = birthday.substring(6, 8);
							children.setChildrenbirthday(sdfOfDate.parse(year + "-" + month + "-" + day));
						} else {
							children.setChildrenbirthday(sdfOfDate.parse(childrenImportFileList.get(i).getWrite4()));
						}
						// 当前受教育阶段结束时间
						String write8 = childrenImportFileList.get(i).getWrite8();
						if (write8 != null && !write8.isEmpty()) {
							children.setChildrenstageendtime(sdfOfDate.parse(write8));
						}
						// 教育终止时间
						String write9 = childrenImportFileList.get(i).getWrite9();
						if (write9 != null && !write9.isEmpty()) {
							children.setChildrenstageendtime(sdfOfDate.parse(write9));
						}*/

						childrenList.add(children);
					}

					/*// 添加数据
					data.setChildrenList(childrenList);*/
					dataList.addAll(childrenList);
				}
			}

			/*// 添加数据
			data.setBasic(basic); // 基本表数据*/
			//dataList.add(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回数据
		if (dataList.size() == 0) {
			//returnData.setIsPass(0);
			returnData.setMsg("未找到相关数据！");
			returnData.setData(null);
		} else {
			//returnData.setIsPass(1);
			returnData.setMsg("找到相关数据！");
			returnData.setData(dataList); // 添加查询到的数据
		}

		return returnData;
	}

}
