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
import sun.bz1.entity.BuildingType;
import sun.bz1.service.BuildingTypeService;
import util.Config;
import util.VersionCompare;

/**
 * 建筑类型表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/04
 */

@Controller
@RequestMapping("/building_type")
public class F_BuildingTypeRestful {

	@Autowired
	private BuildingTypeService buildingTypeService;

	/**
	 * 根据BuildingType实体添加
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/insertByBuildingType")
	public LayuiDataTemplet<BuildingType> insertByBuildingType(@RequestBody BuildingType type) {
		LayuiDataTemplet<BuildingType> returnData = new LayuiDataTemplet<BuildingType>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (type.getVersion() != null && !type.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(type.getVersion(), Config.VERSION);
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
		type.setBuildingtypeid(Config.SIGN_BUILDINGTYPE + UUID.randomUUID().toString()); // 建筑类型ID(JZLX+UUID)
//		type.setBuildingtypestate("Y"); // 建筑类型状态(Y:有效/N:无效)
		type.setBuildingtypecreatetime(new Date()); // 建筑类型创建时间
//		type.setBuildingtypeupdateuserid(type.getBuildingtypecreateuserid()); // 建筑类型更新人ID(YHBG+UUID)
//		type.setBuildingtypeupdatetime(new Date()); // 建筑类型更新时间

		// 添加
		int count = 0;
		count = buildingTypeService.insertByBuildingType(type);

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
	 * 根据BuildingType实体更新
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/updateByBuildingType")
	public LayuiDataTemplet<BuildingType> updateByBuildingType(@RequestBody BuildingType type) {
		LayuiDataTemplet<BuildingType> returnData = new LayuiDataTemplet<BuildingType>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (type.getVersion() != null && !type.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(type.getVersion(), Config.VERSION);
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
		if (type.getId() == null || type.getId() == 0) {
			if (type.getBuildingtypeid() == null || type.getBuildingtypeid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		type.setBuildingtypeupdatetime(new Date()); // 建筑类型更新时间

		// 更新
		int count = 0;
		count = buildingTypeService.updateByBuildingType(type);

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
	 * 根据BuildingType实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectByBuildingType")
	public LayuiDataTemplet<BuildingType> selectByBuildingType(@RequestBody BuildingType type) {
		LayuiDataTemplet<BuildingType> returnData = new LayuiDataTemplet<BuildingType>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (type.getVersion() != null && !type.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(type.getVersion(), Config.VERSION);
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
		if (type.getPagenumber() != null) {
			// 计算偏移量
			if (type.getPagenumber() != -1) {
				if (type.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = type.getPagenumber();
				int pageSize = type.getPagesize();
				type.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				type.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = buildingTypeService.selectCountByBuildingType(type);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<BuildingType> typeList = buildingTypeService.selectByBuildingType(type); // 查询数据
				returnData.setData(typeList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据BuildingType实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<BuildingType> selectBySelectData(@RequestBody BuildingType type) {
		LayuiDataTemplet<BuildingType> returnData = new LayuiDataTemplet<BuildingType>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (type.getVersion() != null && !type.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(type.getVersion(), Config.VERSION);
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
		if (type.getPagenumber() != null) {
			// 计算偏移量
			if (type.getPagenumber() != -1) {
				if (type.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = type.getPagenumber();
				int pageSize = type.getPagesize();
				type.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				type.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = buildingTypeService.selectCountBySelectData(type); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<BuildingType> typeList = buildingTypeService.selectBySelectData(type); // 查询数据
				returnData.setData(typeList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param type
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<BuildingType> deleteByIdList(@RequestBody BuildingType type) {
		LayuiDataTemplet<BuildingType> returnData = new LayuiDataTemplet<BuildingType>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (type.getVersion() != null && !type.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(type.getVersion(), Config.VERSION);
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
		count = buildingTypeService.deleteByIdList(type.getIdlist());

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
