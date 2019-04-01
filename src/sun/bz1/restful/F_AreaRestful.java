package sun.bz1.restful;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.*;
import sun.bz1.service.AreaService;
import sun.bz1.service.OrderTableService;
import sun.bz1.service.UserService;
import util.Config;
import util.VersionCompare;

/**
 * 接单区域表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/04
 */

@Controller
@RequestMapping("/area")
public class F_AreaRestful {

	@Autowired
	private AreaService areaService;

    @Autowired
    private OrderTableService orderTableService;

    @Autowired
    private UserService userService;

	/**
	 * 根据Area实体添加
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/insertByArea")
	public LayuiDataTemplet<Area> insertByArea(@RequestBody Area area) {
		LayuiDataTemplet<Area> returnData = new LayuiDataTemplet<Area>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (area.getVersion() != null && !area.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(area.getVersion(), Config.VERSION);
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
		area.setAreaid(Config.SIGN_AREA + UUID.randomUUID().toString()); // 接单区域ID(JDQY+UUID)
		//area.setAreastate("Y"); // 接单区域状态(Y:有效/N:无效)
		area.setAreacreatetime(new Date()); // 接单区域创建时间
//		area.setAreaupdateuserid(area.getAreacreateuserid()); // 接单区域更新人ID(YHBG+UUID)
//		area.setAreaupdatetime(new Date()); // 接单区域更新时间
		
		// 添加
		int count = 0;
		count = areaService.insertByArea(area);
		
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
	 * 根据Area实体更新
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/updateByArea")
	public LayuiDataTemplet<Area> updateByArea(@RequestBody Area area) {
		LayuiDataTemplet<Area> returnData = new LayuiDataTemplet<Area>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (area.getVersion() != null && !area.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(area.getVersion(), Config.VERSION);
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
		if (area.getId() == null || area.getId() == 0) {
			if (area.getAreaid() == null || area.getAreaid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		area.setAreaupdatetime(new Date()); // 接单区域更新时间
				
		// 更新
		int count = 0;
		count = areaService.updateByArea(area);
		
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
	 * 根据Area实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectByArea")
	public LayuiDataTemplet<Area> selectByArea(@RequestBody Area area) {
		LayuiDataTemplet<Area> returnData = new LayuiDataTemplet<Area>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (area.getVersion() != null && !area.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(area.getVersion(), Config.VERSION);
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
		if (area.getPagenumber() != null) {
			// 计算偏移量
			if (area.getPagenumber() != -1) {
				if (area.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = area.getPagenumber();
				int pageSize = area.getPagesize();
				area.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				area.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = areaService.selectCountByArea(area); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Area> areaList = areaService.selectByArea(area); // 查询数据
				returnData.setData(areaList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据Area实体模糊查询
   	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<Area> selectBySelectData(@RequestBody Area area) {
		LayuiDataTemplet<Area> returnData = new LayuiDataTemplet<Area>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (area.getVersion() != null && !area.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(area.getVersion(), Config.VERSION);
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
		if (area.getPagenumber() != null) {
			// 计算偏移量
			if (area.getPagenumber() != -1) {
				if (area.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = area.getPagenumber();
				int pageSize = area.getPagesize();
				area.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				area.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = areaService.selectCountBySelectData(area); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Area> areaList = areaService.selectBySelectData(area); // 查询数据
				returnData.setData(areaList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据Area实体查询(查出来的数据没有全国)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param area
	 * @return
	 *
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectByAreaNoAll")
	public LayuiDataTemplet<Area> selectByAreaNoAll(@RequestBody Area area) {
		LayuiDataTemplet<Area> returnData = new LayuiDataTemplet<Area>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (area.getVersion() != null && !area.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(area.getVersion(), Config.VERSION);
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
		if (area.getPagenumber() != null) {
			// 计算偏移量
			if (area.getPagenumber() != -1) {
				if (area.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = area.getPagenumber();
				int pageSize = area.getPagesize();
				area.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				area.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = areaService.selectCountByArea(area);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Area> areaList = areaService.selectByArea(area); // 查询数据

				for (int i =0; i < areaList.size(); i++){
					if (areaList.get(i).getAreaprovince().equals("全国")){
						areaList.remove(i);
					}
				}
				returnData.setData(areaList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param area
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<Area> deleteByIdList(@RequestBody Area area) {
		LayuiDataTemplet<Area> returnData = new LayuiDataTemplet<Area>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (area.getVersion() != null && !area.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(area.getVersion(), Config.VERSION);
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
		count = areaService.deleteByIdList(area.getIdlist());

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
	 * 根据Area实体查询
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param area
	 * @return
	 *
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectByAreaCascade")
	public LayuiDataTemplet<AreaProvice> selectByAreaCascade(@RequestBody Area area) {
		LayuiDataTemplet<AreaProvice> returnData = new LayuiDataTemplet<AreaProvice>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (area.getVersion() != null && !area.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(area.getVersion(), Config.VERSION);
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
		if (area.getPagenumber() != null) {
			// 计算偏移量
			if (area.getPagenumber() != -1) {
				if (area.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = area.getPagenumber();
				int pageSize = area.getPagesize();
				area.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				area.setPagesize(pageSize); // 每页的数据量
			}

			area.setAreacitycode("156");
			// 查询数量
			int count = 0;
			count = areaService.selectCountByArea(area);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				area.setAreastate("Y");
				List<Area> areaList = areaService.selectByArea(area); // 查询数据
				List<AreaProvice> areaProviceList = new ArrayList<AreaProvice>();
				for (int i =0 ; i < areaList.size(); i ++) {
					AreaProvice areaProvice = new AreaProvice();
					areaProvice.setId(areaList.get(i).getId());
					areaProvice.setAreaid(areaList.get(i).getAreaid());
					areaProvice.setAreastate(areaList.get(i).getAreastate());
					areaProvice.setAreaprovince(areaList.get(i).getAreaprovince());
					areaProvice.setAreaprovincecode(areaList.get(i).getAreaprovincecode());
					areaProvice.setAreacitycode(areaList.get(i).getAreacitycode());
					if (!areaList.get(i).getAreaprovincecode().equals("156")){
					Area area1 = new Area();
					area1.setPagenumber(-1);
					area1.setAreastate("Y");
					area1.setAreacitycode(areaList.get(i).getAreaprovincecode());
					List<Area> areaCityList = areaService.selectByArea(area1);
					if (areaCityList.size() > 0) {
						List<AreaCity> areaCities = new ArrayList<AreaCity>();
						for (int j = 0; j < areaCityList.size(); j++) {
							AreaCity areaCity = new AreaCity();
							areaCity.setId(areaCityList.get(j).getId());
							areaCity.setAreaid(areaCityList.get(j).getAreaid());
							areaCity.setAreastate(areaCityList.get(j).getAreastate());
							areaCity.setAreaprovince(areaCityList.get(j).getAreaprovince());
							areaCity.setAreaprovincecode(areaCityList.get(j).getAreaprovincecode());
							areaCity.setAreacitycode(areaCityList.get(j).getAreacitycode());
							areaCities.add(areaCity);
						}
						areaProvice.setAreaCityList(areaCities);
					}
				}
					areaProviceList.add(areaProvice);
					}

				returnData.setData(areaProviceList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据Area实体模糊查询(区域报表)
	 *
	 * 可以进行分页查询
	 *
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 *
	 * pageSize 每页的数据量
	 *
	 * @param area
	 * @return
	 *
	 * @author ZY on 2018/12/03
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectDataReport")
	public LayuiDataTemplet<Area> selectBySelectDataReport(@RequestBody Area area) {
		LayuiDataTemplet<Area> returnData = new LayuiDataTemplet<Area>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (area.getVersion() != null && !area.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(area.getVersion(), Config.VERSION);
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
		if (area.getPagenumber() != null) {
			// 计算偏移量
			if (area.getPagenumber() != -1) {
				if (area.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = area.getPagenumber();
				int pageSize = area.getPagesize();
				area.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				area.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = areaService.selectCountBySelectDataReport(area); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setMsg("查询成功！");


				List<Area> areaList = areaService.selectBySelectDataReport(area); // 查询数据
                for (int i = 0; i < areaList.size(); i ++){
                    if (areaList.get(i).getAreacitycode().equals("156")){
                        Area selectArea = new Area();
                        selectArea.setPagenumber(-1);//不分页
                        selectArea.setAreacitycode(areaList.get(i).getAreaprovincecode());
                        List<Area> cityList = areaService.selectByArea(selectArea);
                        if (cityList.size() > 0){
                            areaList.remove(i);
                        }
                    }
                }
                //查询全部
                Area allArea = new Area();
                allArea.setPagenumber(-1);//不分页
                allArea.setAreacitycode("156");//查询省
                List<Area> allAreaList = areaService.selectByArea(allArea);
                for (int j =0; j < areaList.size(); j++){
                    for (int i = 0;i < allAreaList.size(); i++){
                        if (allAreaList.get(i).getAreaprovincecode().equals(areaList.get(j).getAreacitycode())){
                            areaList.get(j).setAreacity(allAreaList.get(i).getAreaprovince());
                        }
                    }
                }

				//总数和平均数
				Area usertotalnumber = new Area();
				Area usersvgnumber = new Area();
				double size = areaList.size();
				//该区域内所有完成的订单的客户给平台付款的总价、平均数
				double areapricetotalmoney = 0;

				for (int i = 0; i < areaList.size(); i ++){
					areapricetotalmoney = areapricetotalmoney + areaList.get(i).getAreapricetotalmoney();
				}
				usertotalnumber.setAreapricetotalmoney(areapricetotalmoney);

				usersvgnumber.setAreapricetotalmoney((areapricetotalmoney/size));

				//支出总价、平均数
				double expendituremoney = 0;

				for (int i = 0; i < areaList.size(); i ++){
					expendituremoney = expendituremoney + areaList.get(i).getExpendituremoney();
				}
				usertotalnumber.setExpendituremoney(expendituremoney);

				usersvgnumber.setExpendituremoney((expendituremoney/size));

				//支出总价、平均数
				double profitmoney = 0;

				for (int i = 0; i < areaList.size(); i ++){
					profitmoney = profitmoney + areaList.get(i).getProfitmoney();
				}
				usertotalnumber.setProfitmoney(profitmoney);

				usersvgnumber.setProfitmoney((profitmoney/size));

				//添加合计、平均数
				usertotalnumber.setAreastate("合计");
				usersvgnumber.setAreastate("平台平均值");

				areaList.add(usertotalnumber);
				areaList.add(usersvgnumber);



				//用于查询所有数据的数量
				area.setPagenumber(-1);//不分页的数量
				List<Area> areaList1 = areaService.selectBySelectDataReport(area); // 查询数据
				for (int i = 0; i < areaList1.size(); i ++){
					if (areaList1.get(i).getAreacitycode().equals("156")){
						Area selectArea1 = new Area();
						selectArea1.setPagenumber(-1);//不分页
						selectArea1.setAreacitycode(areaList1.get(i).getAreaprovincecode());
						List<Area> cityList1 = areaService.selectByArea(selectArea1);
						if (cityList1.size() > 0){
							areaList1.remove(i);
						}
					}
				}
				//查询全部
				Area allArea1 = new Area();
				allArea1.setPagenumber(-1);//不分页
				allArea1.setAreacitycode("156");//查询省
				List<Area> allAreaList1 = areaService.selectByArea(allArea1);
				for (int j =0; j < areaList1.size(); j++){
					for (int i = 0;i < allAreaList1.size(); i++){
						if (allAreaList1.get(i).getAreaprovincecode().equals(areaList1.get(j).getAreacitycode())){
							areaList1.get(j).setAreacity(allAreaList1.get(i).getAreaprovince());
						}
					}
				}


                returnData.setCount(areaList1.size());
				returnData.setData(areaList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}


}
