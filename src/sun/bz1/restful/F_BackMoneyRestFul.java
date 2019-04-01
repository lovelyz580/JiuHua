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
import sun.bz1.entity.BackMoney;
import sun.bz1.entity.BackMoneyAndOrderTable;
import sun.bz1.entity.BackMoneyAndOrderTableAndUserPaymentAndUser;
import sun.bz1.service.BackMoneyService;
import util.Config;
import util.VersionCompare;

/**
 * 平台_收益金额表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/10
 */

@Controller
@RequestMapping("/back_money")
public class F_BackMoneyRestFul {

	@Autowired
	private BackMoneyService backMoneyService;

	/**
	 * 根据BackMoney实体添加
	 * 
	 * @param backMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	@ResponseBody
	@RequestMapping("/insertByBackMoney")
	public LayuiDataTemplet<BackMoney> insertByBackMoney(@RequestBody BackMoney backMoney) {
		LayuiDataTemplet<BackMoney> returnData = new LayuiDataTemplet<BackMoney>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (backMoney.getVersion() != null && !backMoney.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(backMoney.getVersion(), Config.VERSION);
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
		backMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 平台金额ID(PTJE+UUID)
		backMoney.setBackmoneycreatetime(new Date()); // 金额创建时间
		
		// 添加
		int count = 0;
		count = backMoneyService.insertByBackMoney(backMoney);
		
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
	 * 根据BackMoney实体更新
	 * 
	 * @param backMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	@ResponseBody
	@RequestMapping("/updateByBackMoney")
	public LayuiDataTemplet<BackMoney> updateByBackMoney(@RequestBody BackMoney backMoney) {
		LayuiDataTemplet<BackMoney> returnData = new LayuiDataTemplet<BackMoney>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (backMoney.getVersion() != null && !backMoney.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(backMoney.getVersion(), Config.VERSION);
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
		if (backMoney.getId() == null || backMoney.getId() == 0) {
			if (backMoney.getBackmoneyid() == null || backMoney.getBackmoneyid().isEmpty()) {
				if (backMoney.getOrderid() == null || backMoney.getOrderid().isEmpty()) {
					returnData.setMsg("缺少更新条件，更新失败！");
					return returnData;
				}
			}
		}
				
		// 更新数据
				
		// 更新
		int count = 0;
		count = backMoneyService.updateByBackMoney(backMoney);
		
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
	 * 根据BackMoneyAndOrderTable实体联表查询
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
	 * @author WJF on 2018/09/10
	 */
	@ResponseBody
	@RequestMapping("/selectTwoTablesByUnionData")
	public LayuiDataTemplet<BackMoneyAndOrderTable> selectTwoTablesByUnionData(@RequestBody BackMoneyAndOrderTable unionData) {
		LayuiDataTemplet<BackMoneyAndOrderTable> returnData = new LayuiDataTemplet<BackMoneyAndOrderTable>(); // 返回数据
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
			count = backMoneyService.selectTwoTablesCountByUnionData(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<BackMoneyAndOrderTable> backMoneyList = backMoneyService.selectTwoTablesByUnionData(unionData); // 查询数据
				returnData.setData(backMoneyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据BackMoneyAndOrderTable实体联表模糊查询
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
	 * @author WJF on 2018/09/10
	 */
	@ResponseBody
	@RequestMapping("/selectTwoTablesBySelectData")
	public LayuiDataTemplet<BackMoneyAndOrderTable> selectTwoTablesBySelectData(@RequestBody BackMoneyAndOrderTable unionData) {
		LayuiDataTemplet<BackMoneyAndOrderTable> returnData = new LayuiDataTemplet<BackMoneyAndOrderTable>(); // 返回数据
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
			count = backMoneyService.selectTwoTablesCountBySelectData(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<BackMoneyAndOrderTable> backMoneyList = backMoneyService.selectTwoTablesBySelectData(unionData); // 查询数据
				returnData.setData(backMoneyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据BackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成平台收款的报表)
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
	 * @author ZY on 2018/12/13
	 */
	@ResponseBody
	@RequestMapping("/selectFourTablesBySelectDataIncomeReport")
	public LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser> selectFourTablesBySelectDataIncomeReport(@RequestBody BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser> returnData = new LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser>(); // 返回数据
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
			count = backMoneyService.selectFourTablesCountBySelectDataIncomeReport(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<BackMoneyAndOrderTableAndUserPaymentAndUser> backMoneyList = backMoneyService.selectFourTablesBySelectDataIncomeReport(unionData); // 查询数据

				for (int i = 0; i < backMoneyList.size(); i++){
					backMoneyList.get(i).setUserpaymentplatformdetail("账户名：" + (backMoneyList.get(i).getUserpaymentplatformaccountname() == null ? "": backMoneyList.get(i).getUserpaymentplatformaccountname()) +
							"\n账户：" + (backMoneyList.get(i).getUserpaymentplatformaccount() == null ? "": backMoneyList.get(i).getUserpaymentplatformaccount()) +
							"\n开户行：" + (backMoneyList.get(i).getUserpaymentplatformaccountaddress() == null ? "": backMoneyList.get(i).getUserpaymentplatformaccountaddress()));
				}

				//总数和平均数
				BackMoneyAndOrderTableAndUserPaymentAndUser usertotalnumber = new BackMoneyAndOrderTableAndUserPaymentAndUser();
				double size = backMoneyList.size();
				//该区域内所有完成的订单的客户给平台付款的总价、平均数
				double platformimcomemoney = 0;

				for (int i = 0; i < backMoneyList.size(); i ++){
					platformimcomemoney = platformimcomemoney + backMoneyList.get(i).getPlatformimcomemoney();
				}
				usertotalnumber.setPlatformimcomemoney(platformimcomemoney);

				//添加合计
				usertotalnumber.setPlatformincomestate("合计");

				backMoneyList.add(usertotalnumber);

				returnData.setData(backMoneyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据BackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成平台收款的报表)
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
	 * @author ZY on 2018/12/13
	 */
	@ResponseBody
	@RequestMapping("/selectFourTablesBySelectDataIncomeReportExport")
	public LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser> selectFourTablesBySelectDataIncomeReportExport(@RequestBody BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser> returnData = new LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser>(); // 返回数据
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
			count = backMoneyService.selectFourTablesCountBySelectDataIncomeReport(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<BackMoneyAndOrderTableAndUserPaymentAndUser> backMoneyList = backMoneyService.selectFourTablesBySelectDataIncomeReport(unionData); // 查询数据

				for (int i = 0; i < backMoneyList.size(); i++){
					backMoneyList.get(i).setUserpaymentplatformdetail("账户名：" + (backMoneyList.get(i).getUserpaymentplatformaccountname() == null ? "": backMoneyList.get(i).getUserpaymentplatformaccountname()) +
							"账户：" + (backMoneyList.get(i).getUserpaymentplatformaccount() == null ? "": backMoneyList.get(i).getUserpaymentplatformaccount()) +
							"开户行：" + (backMoneyList.get(i).getUserpaymentplatformaccountaddress() == null ? "": backMoneyList.get(i).getUserpaymentplatformaccountaddress()));
				}

				//总数和平均数
				BackMoneyAndOrderTableAndUserPaymentAndUser usertotalnumber = new BackMoneyAndOrderTableAndUserPaymentAndUser();
				double size = backMoneyList.size();
				//该区域内所有完成的订单的客户给平台付款的总价、平均数
				double platformimcomemoney = 0;

				for (int i = 0; i < backMoneyList.size(); i ++){
					platformimcomemoney = platformimcomemoney + backMoneyList.get(i).getPlatformimcomemoney();
				}
				usertotalnumber.setPlatformimcomemoney(platformimcomemoney);

				//添加合计
				usertotalnumber.setPlatformincomestate("合计");

				backMoneyList.add(usertotalnumber);

				returnData.setData(backMoneyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据BackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成平台付款给维修的报表)
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
	 * @author ZY on 2018/12/13
	 */
	@ResponseBody
	@RequestMapping("/selectFourTablesBySelectDataServiceReport")
	public LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser> selectFourTablesBySelectDataServiceReport(@RequestBody BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser> returnData = new LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser>(); // 返回数据
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
			count = backMoneyService.selectFourTablesCountBySelectDataServiceReport(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<BackMoneyAndOrderTableAndUserPaymentAndUser> backMoneyList = backMoneyService.selectFourTablesBySelectDataServiceReport(unionData); // 查询数据

				for (int i = 0; i < backMoneyList.size(); i++){
					backMoneyList.get(i).setServicedetail("账户名：" + (backMoneyList.get(i).getServicename() == null ? "": backMoneyList.get(i).getServicename()) +
							"\n账户：" + (backMoneyList.get(i).getServiceaccount() == null ? "": backMoneyList.get(i).getServiceaccount()) +
							"\n开户行：" + (backMoneyList.get(i).getServiceaccountaddress() == null ? "": backMoneyList.get(i).getServiceaccountaddress()));
				}

				//总数和平均数
				BackMoneyAndOrderTableAndUserPaymentAndUser usertotalnumber = new BackMoneyAndOrderTableAndUserPaymentAndUser();
				//该区域内所有完成的订单的客户给平台付款的总价、平均数
				double platformtoservicemoney = 0;

				for (int i = 0; i < backMoneyList.size(); i ++){
					platformtoservicemoney = platformtoservicemoney + backMoneyList.get(i).getPlatformtoservicemoney();
				}
				usertotalnumber.setPlatformtoservicemoney(platformtoservicemoney);

				//添加合计
				usertotalnumber.setPlatformservicestate("合计");

				backMoneyList.add(usertotalnumber);


				returnData.setData(backMoneyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据BackMoneyAndOrderTableAndUserPaymentAndUser实体联表模糊查询(生成平台付款给维修的报表)导出
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
	 * @author ZY on 2018/12/13
	 */
	@ResponseBody
	@RequestMapping("/selectFourTablesBySelectDataServiceReportExport")
	public LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser> selectFourTablesBySelectDataServiceReportExport(@RequestBody BackMoneyAndOrderTableAndUserPaymentAndUser unionData) {
		LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser> returnData = new LayuiDataTemplet<BackMoneyAndOrderTableAndUserPaymentAndUser>(); // 返回数据
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
			count = backMoneyService.selectFourTablesCountBySelectDataServiceReport(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<BackMoneyAndOrderTableAndUserPaymentAndUser> backMoneyList = backMoneyService.selectFourTablesBySelectDataServiceReport(unionData); // 查询数据

				for (int i = 0; i < backMoneyList.size(); i++){
					backMoneyList.get(i).setServicedetail("账户名：" + (backMoneyList.get(i).getServicename() == null ? "": backMoneyList.get(i).getServicename()) +
							"账户：" + (backMoneyList.get(i).getServiceaccount() == null ? "": backMoneyList.get(i).getServiceaccount()) +
							"开户行：" + (backMoneyList.get(i).getServiceaccountaddress() == null ? "": backMoneyList.get(i).getServiceaccountaddress()));
				}

				//总数和平均数
				BackMoneyAndOrderTableAndUserPaymentAndUser usertotalnumber = new BackMoneyAndOrderTableAndUserPaymentAndUser();
				//该区域内所有完成的订单的客户给平台付款的总价、平均数
				double platformtoservicemoney = 0;

				for (int i = 0; i < backMoneyList.size(); i ++){
					platformtoservicemoney = platformtoservicemoney + backMoneyList.get(i).getPlatformtoservicemoney();
				}
				usertotalnumber.setPlatformtoservicemoney(platformtoservicemoney);

				//添加合计
				usertotalnumber.setPlatformservicestate("合计");

				backMoneyList.add(usertotalnumber);


				returnData.setData(backMoneyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param backMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<BackMoney> deleteByIdList(@RequestBody BackMoney backMoney) {
		LayuiDataTemplet<BackMoney> returnData = new LayuiDataTemplet<BackMoney>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (backMoney.getVersion() != null && !backMoney.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(backMoney.getVersion(), Config.VERSION);
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
		count = backMoneyService.deleteByIdList(backMoney.getIdlist());

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
	 * @param backMoney
	 * @return
	 * 
	 * @author WJF on 2018/09/10
	 */
	@ResponseBody
	@RequestMapping("/deleteByOrderIdList")
	public LayuiDataTemplet<BackMoney> deleteByOrderIdList(@RequestBody BackMoney backMoney) {
		LayuiDataTemplet<BackMoney> returnData = new LayuiDataTemplet<BackMoney>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (backMoney.getVersion() != null && !backMoney.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(backMoney.getVersion(), Config.VERSION);
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
		count = backMoneyService.deleteByOrderIdList(backMoney.getUuidlist());

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
