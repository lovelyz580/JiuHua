package sun.bz1.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.LayuiDataTemplet;
import sun.bz1.entity.UserAndUserServiceAndUserCustomer;
import sun.bz1.entity.WechatTemplate;
import sun.bz1.entity.WechatTemplate;
import sun.bz1.service.UserService;
import sun.bz1.service.WechatTemplateService;
import sun.bz1.service.WechatTemplateService;
import util.Config;
import util.VersionCompare;

import java.util.Date;
import java.util.List;

/**
 * 微信服务通知模板表
 *
 * Service
 *
 * @author ZY on 2018/11/13
 */

@Controller
@RequestMapping("/wechat_template")
public class F_WechatTemplateRestful {
	
	@Autowired
	private WechatTemplateService wechatTemplateService;
	@Autowired
	private UserService userService;

	/**
	 * 根据WechatTemplate实体添加
	 * 
	 * @param wechatTemplate
	 * @return
	 * 
	 * @author ZY on 2018/11/13
	 */
	@ResponseBody
	@RequestMapping("/insertByWechatTemplate")
	public LayuiDataTemplet<WechatTemplate> insertByWechatTemplate(@RequestBody WechatTemplate wechatTemplate) {
		LayuiDataTemplet<WechatTemplate> returnData = new LayuiDataTemplet<WechatTemplate>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (wechatTemplate.getVersion() != null && !wechatTemplate.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(wechatTemplate.getVersion(), Config.VERSION);
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


		wechatTemplate.setCreatedate(new Date());//formId创建时间
		
		// 添加
		int count = 0;
		count = wechatTemplateService.insertByWechatTemplate(wechatTemplate);
		
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
	 * 根据WechatTemplate实体更新
	 * 
	 * @param wechatTemplate
	 * @return
	 * 
	 * @author ZY on 2018/11/13
	 */
	@ResponseBody
	@RequestMapping("/updateByWechatTemplate")
	public LayuiDataTemplet<WechatTemplate> updateByWechatTemplate(@RequestBody WechatTemplate wechatTemplate) {
		LayuiDataTemplet<WechatTemplate> returnData = new LayuiDataTemplet<WechatTemplate>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (wechatTemplate.getVersion() != null && !wechatTemplate.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(wechatTemplate.getVersion(), Config.VERSION);
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
		if (wechatTemplate.getId() == null || wechatTemplate.getId() == 0) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
		}

				
		// 更新
		int count = 0;
		count = wechatTemplateService.updateByWechatTemplate(wechatTemplate);
		
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
	 * 根据WechatTemplate实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param wechatTemplate
	 * @return
	 * 
	 * @author ZY on 2018/11/13
	 */
	@ResponseBody
	@RequestMapping("/selectByWechatTemplate")
	public LayuiDataTemplet<WechatTemplate> selectByWechatTemplate(@RequestBody WechatTemplate wechatTemplate) {
		LayuiDataTemplet<WechatTemplate> returnData = new LayuiDataTemplet<WechatTemplate>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (wechatTemplate.getVersion() != null && !wechatTemplate.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(wechatTemplate.getVersion(), Config.VERSION);
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
		if (wechatTemplate.getPagenumber() != null) {
			// 计算偏移量
			if (wechatTemplate.getPagenumber() != -1) {
				if (wechatTemplate.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = wechatTemplate.getPagenumber();
				int pageSize = wechatTemplate.getPagesize();
				wechatTemplate.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				wechatTemplate.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = wechatTemplateService.selectCountByWechatTemplate(wechatTemplate);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<WechatTemplate> wechatTemplateList = wechatTemplateService.selectByWechatTemplate(wechatTemplate); // 查询数据
				returnData.setData(wechatTemplateList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param wechatTemplate
	 * @return
	 * 
	 * @author ZY on 2018/11/13
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<WechatTemplate> deleteByIdList(@RequestBody WechatTemplate wechatTemplate) {
		LayuiDataTemplet<WechatTemplate> returnData = new LayuiDataTemplet<WechatTemplate>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (wechatTemplate.getVersion() != null && !wechatTemplate.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(wechatTemplate.getVersion(), Config.VERSION);
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
		count = wechatTemplateService.deleteByIdList(wechatTemplate.getIdlist());

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
