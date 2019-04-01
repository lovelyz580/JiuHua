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
import sun.bz1.entity.CreditChange;
import sun.bz1.service.CreditChangeService;
import util.Config;
import util.VersionCompare;

/**
 * 信用值变化表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/05
 */

@Controller
@RequestMapping("/credit_change")
public class F_CreditChangeRestful {

	@Autowired
	private CreditChangeService creditChangeService;

	/**
	 * 根据CreditChange实体添加
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/insertByCreditChange")
	public LayuiDataTemplet<CreditChange> insertByCreditChange(@RequestBody CreditChange creditChange) {
		LayuiDataTemplet<CreditChange> returnData = new LayuiDataTemplet<CreditChange>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (creditChange.getVersion() != null && !creditChange.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(creditChange.getVersion(), Config.VERSION);
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
		creditChange.setCreditchangeid(Config.SIGN_CREDITCHANGE + UUID.randomUUID().toString()); // 信用值变化ID(XYBH+UUID)
		creditChange.setCreditchangecreatetime(new Date()); // 信用值变化创建时间
		
		// 添加
		int count = 0;
		count = creditChangeService.insertByCreditChange(creditChange);
		
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
	 * 根据CreditChange实体更新
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/updateByCreditChange")
	public LayuiDataTemplet<CreditChange> updateByCreditChange(@RequestBody CreditChange creditChange) {
		LayuiDataTemplet<CreditChange> returnData = new LayuiDataTemplet<CreditChange>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (creditChange.getVersion() != null && !creditChange.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(creditChange.getVersion(), Config.VERSION);
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
		if (creditChange.getId() == null || creditChange.getId() == 0) {
			if (creditChange.getCreditchangeid() == null || creditChange.getCreditchangeid().isEmpty()) {
				if (creditChange.getOrderid() == null || creditChange.getOrderid().isEmpty()) {
					if (creditChange.getEvaluatid() == null || creditChange.getEvaluatid().isEmpty()) {
						returnData.setMsg("缺少更新条件，更新失败！");
						return returnData;
					}
				}
			}
		}
				
		// 更新数据
				
		// 更新
		int count = 0;
		count = creditChangeService.updateByCreditChange(creditChange);
		
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
	 * 根据CreditChange实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/selectByCreditChange")
	public LayuiDataTemplet<CreditChange> selectByCreditChange(@RequestBody CreditChange creditChange) {
		LayuiDataTemplet<CreditChange> returnData = new LayuiDataTemplet<CreditChange>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (creditChange.getVersion() != null && !creditChange.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(creditChange.getVersion(), Config.VERSION);
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
		if (creditChange.getPagenumber() != null) {
			// 计算偏移量
			if (creditChange.getPagenumber() != -1) {
				if (creditChange.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = creditChange.getPagenumber();
				int pageSize = creditChange.getPagesize();
				creditChange.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				creditChange.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = creditChangeService.selectCountByCreditChange(creditChange); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<CreditChange> creditChangeList = creditChangeService.selectByCreditChange(creditChange); // 查询数据
				returnData.setData(creditChangeList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据CreditChange实体模糊查询
   	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<CreditChange> selectBySelectData(@RequestBody CreditChange creditChange) {
		LayuiDataTemplet<CreditChange> returnData = new LayuiDataTemplet<CreditChange>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (creditChange.getVersion() != null && !creditChange.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(creditChange.getVersion(), Config.VERSION);
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
		if (creditChange.getPagenumber() != null) {
			// 计算偏移量
			if (creditChange.getPagenumber() != -1) {
				if (creditChange.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = creditChange.getPagenumber();
				int pageSize = creditChange.getPagesize();
				creditChange.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				creditChange.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = creditChangeService.selectCountBySelectData(creditChange); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<CreditChange> creditChangeList = creditChangeService.selectBySelectData(creditChange); // 查询数据
				returnData.setData(creditChangeList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param creditChange
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<CreditChange> deleteByIdList(@RequestBody CreditChange creditChange) {
		LayuiDataTemplet<CreditChange> returnData = new LayuiDataTemplet<CreditChange>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (creditChange.getVersion() != null && !creditChange.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(creditChange.getVersion(), Config.VERSION);
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
		count = creditChangeService.deleteByIdList(creditChange.getIdlist());

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
