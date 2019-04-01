package sun.bz1.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.LayuiDataTemplet;
import sun.bz1.entity.BuildingType;
import sun.bz1.entity.PropertyCompany;
import sun.bz1.service.BuildingTypeService;
import sun.bz1.service.PropertyCompanyService;
import util.Config;
import util.VersionCompare;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 地产公司表
 * 
 * Restful
 * 
 * @author ZY on 2019/01/09
 */

@Controller
@RequestMapping("/property_company")
public class F_PropertyCompanyRestful {

	@Autowired
	private PropertyCompanyService propertyCompanyService;

	/**
	 * 根据PropertyCompany实体添加
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	@ResponseBody
	@RequestMapping("/insertByPropertyCompany")
	public LayuiDataTemplet<PropertyCompany> insertByPropertyCompany(@RequestBody PropertyCompany propertyCompany) {
		LayuiDataTemplet<PropertyCompany> returnData = new LayuiDataTemplet<PropertyCompany>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (propertyCompany.getVersion() != null && !propertyCompany.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(propertyCompany.getVersion(), Config.VERSION);
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
		propertyCompany.setPropertycompanyid(Config.SIGN_PROPERTYCOMPANY + UUID.randomUUID().toString()); // 地产公司ID(DCGS+UUID)
		propertyCompany.setPropertycompanycreatetime(new Date()); // 地产公司创建时间

		// 添加
		int count = 0;
		count = propertyCompanyService.insertByPropertyCompany(propertyCompany);

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
	 * 根据PropertyCompany实体更新
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	@ResponseBody
	@RequestMapping("/updateByPropertyCompany")
	public LayuiDataTemplet<PropertyCompany> updateByPropertyCompany(@RequestBody PropertyCompany propertyCompany) {
		LayuiDataTemplet<PropertyCompany> returnData = new LayuiDataTemplet<PropertyCompany>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (propertyCompany.getVersion() != null && !propertyCompany.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(propertyCompany.getVersion(), Config.VERSION);
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
		if (propertyCompany.getId() == null || propertyCompany.getId() == 0) {
			if (propertyCompany.getPropertycompanyid() == null || propertyCompany.getPropertycompanyid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		propertyCompany.setPropertycompanyupdatetime(new Date()); // 建筑类型更新时间

		// 更新
		int count = 0;
		count = propertyCompanyService.updateByPropertyCompany(propertyCompany);

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
	 * 根据PropertyCompany实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	@ResponseBody
	@RequestMapping("/selectByPropertyCompany")
	public LayuiDataTemplet<PropertyCompany> selectByPropertyCompany(@RequestBody PropertyCompany propertyCompany) {
		LayuiDataTemplet<PropertyCompany> returnData = new LayuiDataTemplet<PropertyCompany>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (propertyCompany.getVersion() != null && !propertyCompany.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(propertyCompany.getVersion(), Config.VERSION);
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
		if (propertyCompany.getPagenumber() != null) {
			// 计算偏移量
			if (propertyCompany.getPagenumber() != -1) {
				if (propertyCompany.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = propertyCompany.getPagenumber();
				int pageSize = propertyCompany.getPagesize();
				propertyCompany.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				propertyCompany.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = propertyCompanyService.selectCountByPropertyCompany(propertyCompany);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<PropertyCompany> propertyCompanyList = propertyCompanyService.selectByPropertyCompany(propertyCompany); // 查询数据
				returnData.setData(propertyCompanyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据PropertyCompany实体模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<PropertyCompany> selectBySelectData(@RequestBody PropertyCompany propertyCompany) {
		LayuiDataTemplet<PropertyCompany> returnData = new LayuiDataTemplet<PropertyCompany>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (propertyCompany.getVersion() != null && !propertyCompany.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(propertyCompany.getVersion(), Config.VERSION);
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
		if (propertyCompany.getPagenumber() != null) {
			// 计算偏移量
			if (propertyCompany.getPagenumber() != -1) {
				if (propertyCompany.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = propertyCompany.getPagenumber();
				int pageSize = propertyCompany.getPagesize();
				propertyCompany.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				propertyCompany.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = propertyCompanyService.selectCountBySelectData(propertyCompany); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<PropertyCompany> propertyCompanyList = propertyCompanyService.selectBySelectData(propertyCompany); // 查询数据
				returnData.setData(propertyCompanyList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param propertyCompany
	 * @return
	 * 
	 * @author ZY on 2019/01/09
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<PropertyCompany> deleteByIdList(@RequestBody PropertyCompany propertyCompany) {
		LayuiDataTemplet<PropertyCompany> returnData = new LayuiDataTemplet<PropertyCompany>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (propertyCompany.getVersion() != null && !propertyCompany.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(propertyCompany.getVersion(), Config.VERSION);
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
		count = propertyCompanyService.deleteByIdList(propertyCompany.getIdlist());

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
