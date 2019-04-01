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
import sun.bz1.entity.Scale;
import sun.bz1.service.ScaleService;
import util.Config;
import util.VersionCompare;

/**
 * 接单规模表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/04
 */

@Controller
@RequestMapping("/scale")
public class F_ScaleRestful {

	@Autowired
	private ScaleService scaleService;

	/**
	 * 根据Scale实体添加
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/insertByScale")
	public LayuiDataTemplet<Scale> insertByScale(@RequestBody Scale scale) {
		LayuiDataTemplet<Scale> returnData = new LayuiDataTemplet<Scale>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (scale.getVersion() != null && !scale.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(scale.getVersion(), Config.VERSION);
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
		scale.setScaleid(Config.SIGN_SCALE + UUID.randomUUID().toString()); // 接单规模ID(JDGM+UUID)
//		scale.setScalestate("Y"); // 接单规模状态(Y:有效/N:无效)
		scale.setScalecreatetime(new Date()); // 接单规模创建时间
//		scale.setScaleupdateuserid(scale.getScalecreateuserid()); // 接单规模更新人ID(YHBG+UUID)
//		scale.setScaleupdatetime(new Date()); // 接单规模更新时间
		
		// 添加
		int count = 0;
		count = scaleService.insertByScale(scale);
		
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
	 * 根据Scale实体更新
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/updateByScale")
	public LayuiDataTemplet<Scale> updateByScale(@RequestBody Scale scale) {
		LayuiDataTemplet<Scale> returnData = new LayuiDataTemplet<Scale>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (scale.getVersion() != null && !scale.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(scale.getVersion(), Config.VERSION);
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
		if (scale.getId() == null || scale.getId() == 0) {
			if (scale.getScaleid() == null || scale.getScaleid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		scale.setScaleupdatetime(new Date()); // 接单规模更新时间
				
		// 更新
		int count = 0;
		count = scaleService.updateByScale(scale);
		
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
	 * 根据Scale实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectByScale")
	public LayuiDataTemplet<Scale> selectByScale(@RequestBody Scale scale) {
		LayuiDataTemplet<Scale> returnData = new LayuiDataTemplet<Scale>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (scale.getVersion() != null && !scale.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(scale.getVersion(), Config.VERSION);
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
		if (scale.getPagenumber() != null) {
			// 计算偏移量
			if (scale.getPagenumber() != -1) {
				if (scale.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = scale.getPagenumber();
				int pageSize = scale.getPagesize();
				scale.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				scale.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = scaleService.selectCountByScale(scale); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Scale> scaleList = scaleService.selectByScale(scale); // 查询数据
				for (int i = 0; i < scaleList.size(); i++) {
					scaleList.get(i).setScaleminmax(scaleList.get(i).getScalemin() + "-" + scaleList.get(i).getScalemax());
					
				}
				returnData.setData(scaleList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据Scale实体模糊查询
   	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<Scale> selectBySelectData(@RequestBody Scale scale) {
		LayuiDataTemplet<Scale> returnData = new LayuiDataTemplet<Scale>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (scale.getVersion() != null && !scale.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(scale.getVersion(), Config.VERSION);
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
		if (scale.getPagenumber() != null) {
			// 计算偏移量
			if (scale.getPagenumber() != -1) {
				if (scale.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = scale.getPagenumber();
				int pageSize = scale.getPagesize();
				scale.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				scale.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = scaleService.selectCountBySelectData(scale); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Scale> scaleList = scaleService.selectBySelectData(scale); // 查询数据
				returnData.setData(scaleList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param scale
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<Scale> deleteByIdList(@RequestBody Scale scale) {
		LayuiDataTemplet<Scale> returnData = new LayuiDataTemplet<Scale>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (scale.getVersion() != null && !scale.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(scale.getVersion(), Config.VERSION);
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
		count = scaleService.deleteByIdList(scale.getIdlist());

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
