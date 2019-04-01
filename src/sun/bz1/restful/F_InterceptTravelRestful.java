package sun.bz1.restful;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.InterceptTravel;
import sun.bz1.service.InterceptTravelService;
import util.Config;
import util.VersionCompare;

/**
 * 差旅费(每公里)拦标价表
 * 
 * Restful
 * 
 * @author WJF on 2018/10/09
 */

@Controller
@RequestMapping("/intercept_travel")
public class F_InterceptTravelRestful {
	
	@Autowired
	private InterceptTravelService interceptTravelService;

	/**
	 * 根据InterceptTravel实体添加
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	@ResponseBody
	@RequestMapping("/insertByInterceptTravel")
	public LayuiDataTemplet<InterceptTravel> insertByInterceptTravel(@RequestBody InterceptTravel interceptTravel) {
		LayuiDataTemplet<InterceptTravel> returnData = new LayuiDataTemplet<InterceptTravel>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (interceptTravel.getVersion() != null && !interceptTravel.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(interceptTravel.getVersion(), Config.VERSION);
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
//		interceptTravel.setIntercepttravelstate("Y"); // 差旅费(每公里)拦标价状态(Y:有效/N:无效)
		interceptTravel.setIntercepttravelupdatetime(new Date()); // 差旅费(每公里)拦标价更新时间
		
		// 添加
		int count = 0;
		count = interceptTravelService.insertByInterceptTravel(interceptTravel);
		
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
	 * 根据InterceptTravel实体更新
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	@ResponseBody
	@RequestMapping("/updateByInterceptTravel")
	public LayuiDataTemplet<InterceptTravel> updateByInterceptTravel(@RequestBody InterceptTravel interceptTravel) {
		LayuiDataTemplet<InterceptTravel> returnData = new LayuiDataTemplet<InterceptTravel>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (interceptTravel.getVersion() != null && !interceptTravel.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(interceptTravel.getVersion(), Config.VERSION);
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
		if (interceptTravel.getId() == null || interceptTravel.getId() == 0) {
			returnData.setMsg("缺少更新条件，更新失败！");
			return returnData;
		}

		// 更新数据
		interceptTravel.setIntercepttravelupdatetime(new Date()); // 拦标价更新时间
				
		// 更新
		int count = 0;
		count = interceptTravelService.updateByInterceptTravel(interceptTravel);
		
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
	 * 根据InterceptTravel实体查询
	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	@ResponseBody
	@RequestMapping("/selectByInterceptTravel")
	public LayuiDataTemplet<InterceptTravel> selectByInterceptTravel(@RequestBody InterceptTravel interceptTravel) {
		LayuiDataTemplet<InterceptTravel> returnData = new LayuiDataTemplet<InterceptTravel>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (interceptTravel.getVersion() != null && !interceptTravel.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(interceptTravel.getVersion(), Config.VERSION);
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


		// 查询数量
		int count = 0;
		count = interceptTravelService.selectCountByInterceptTravel(interceptTravel); 

		// 返回数据
		if (count == 0) {
			returnData.setMsg("暂无数据！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("查询成功！");
			List<InterceptTravel> interceptTravelList = interceptTravelService.selectByInterceptTravel(interceptTravel); // 查询数据
			returnData.setData(interceptTravelList);
		}

		return returnData;
	}
	
	/**
	 * 根据idList删除信息
   	 * 
	 * @param interceptTravel
	 * @return
	 * 
	 * @author WJF on 2018/10/09
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<InterceptTravel> deleteByIdList(@RequestBody InterceptTravel interceptTravel) {
		LayuiDataTemplet<InterceptTravel> returnData = new LayuiDataTemplet<InterceptTravel>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (interceptTravel.getVersion() != null && !interceptTravel.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(interceptTravel.getVersion(), Config.VERSION);
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
		count = interceptTravelService.deleteByIdList(interceptTravel.getIdlist());

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
