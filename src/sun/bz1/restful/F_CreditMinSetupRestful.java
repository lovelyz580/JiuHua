package sun.bz1.restful;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.CreditMinSetup;
import sun.bz1.service.CreditMinSetupService;
import util.Config;
import util.VersionCompare;

/**
 * 信用值_用户_客户_初始值_设置表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/04
 */

@Controller
@RequestMapping("/credit_min_setup")
public class F_CreditMinSetupRestful {

	@Autowired
	private CreditMinSetupService setupService;
	
	/**
	 * 根据CreditMinSetup实体更新
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/updateBySetup")
	public LayuiDataTemplet<CreditMinSetup> updateBySetup(@RequestBody CreditMinSetup setup) {
		LayuiDataTemplet<CreditMinSetup> returnData = new LayuiDataTemplet<CreditMinSetup>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (setup.getVersion() != null && !setup.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(setup.getVersion(), Config.VERSION);
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
		if (setup.getId() == null || setup.getId() == 0) {
			returnData.setMsg("缺少更新条件，更新失败！");
			return returnData;
		}

		// 更新数据
		setup.setCreditminupdatetime(new Date()); // 信用值设置更新时间
				
		// 更新
		int count = 0;
		count = setupService.updateBySetup(setup);
		
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
	 * 根据CreditMinSetup实体查询
	 * 
	 * @param setup
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectBySetup")
	public LayuiDataTemplet<CreditMinSetup> selectBySetup(@RequestBody CreditMinSetup setup) {
		LayuiDataTemplet<CreditMinSetup> returnData = new LayuiDataTemplet<CreditMinSetup>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (setup.getVersion() != null && !setup.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(setup.getVersion(), Config.VERSION);
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

		// 查询数据
		List<CreditMinSetup> setupList = setupService.selectBySetup(setup); 

		// 返回数据
		if (setupList == null || setupList.size() == 0) {
			returnData.setMsg("暂无数据！");
		} else {
			returnData.setCount(setupList.size());
			returnData.setMsg("查询成功！");
			returnData.setData(setupList);
		}

		return returnData;
	}
	
	/**
	 * 查询
	 * 
	 * @return
	 * 
	 * @author WJF on 2018/09/15
	 */
	@ResponseBody
	@RequestMapping("/select")
	public LayuiDataTemplet<CreditMinSetup> select() {
		LayuiDataTemplet<CreditMinSetup> returnData = new LayuiDataTemplet<CreditMinSetup>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 查询数据
		List<CreditMinSetup> setupList = setupService.selectBySetup(new CreditMinSetup()); 

		// 返回数据
		if (setupList == null || setupList.size() == 0) {
			returnData.setMsg("暂无数据！");
		} else {
			returnData.setCount(setupList.size());
			returnData.setMsg("查询成功！");
			returnData.setData(setupList);
		}

		return returnData;
	}
	
}
