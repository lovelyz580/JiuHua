package sun.bz1.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.LayuiDataTemplet;
import sun.bz1.entity.UserAndUserServiceAndUserCustomer;
import sun.bz1.entity.WechatForm;
import sun.bz1.entity.WechatForm;
import sun.bz1.service.UserService;
import sun.bz1.service.WechatFormService;
import sun.bz1.service.WechatFormService;
import util.Config;
import util.VersionCompare;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 产品表
 * 
 * Restful
 * 
 * @author ZY on 2018/11/12
 */

@Controller
@RequestMapping("/wechat_form")
public class F_WechatFormRestful {
	
	@Autowired
	private WechatFormService wechatFormService;
	@Autowired
	private UserService userService;

	/**
	 * 根据WechatForm实体添加
	 * 
	 * @param wechatForm
	 * @return
	 * 
	 * @author ZY on 2018/11/12
	 */
	@ResponseBody
	@RequestMapping("/insertByWechatForm")
	public LayuiDataTemplet<WechatForm> insertByWechatForm(@RequestBody WechatForm wechatForm) {
		LayuiDataTemplet<WechatForm> returnData = new LayuiDataTemplet<WechatForm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (wechatForm.getVersion() != null && !wechatForm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(wechatForm.getVersion(), Config.VERSION);
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

		UserAndUserServiceAndUserCustomer selectUser = new UserAndUserServiceAndUserCustomer();
		selectUser.setPagenumber(-1);//不分页
		selectUser.setUserid(wechatForm.getUserid());//通过userid查询
		List<UserAndUserServiceAndUserCustomer> userList = userService.selectThreeTablesByUnionData(selectUser); // 查询

		if (null == userList.get(0).getUseropenid() || userList.get(0).getUseropenid().equals("")){
			returnData.setMsg("此账号未关联微信账号，请到编辑信息页面关联！");
			return returnData;
		}

		wechatForm.setOpenid(userList.get(0).getUseropenid());//微信openid
		wechatForm.setCreatedate(new Date());//formId创建时间
		
		// 添加
		int count = 0;
		count = wechatFormService.insertByWechatForm(wechatForm);
		
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
	 * 根据WechatForm实体更新
	 * 
	 * @param wechatForm
	 * @return
	 * 
	 * @author ZY on 2018/11/12
	 */
	@ResponseBody
	@RequestMapping("/updateByWechatForm")
	public LayuiDataTemplet<WechatForm> updateByWechatForm(@RequestBody WechatForm wechatForm) {
		LayuiDataTemplet<WechatForm> returnData = new LayuiDataTemplet<WechatForm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (wechatForm.getVersion() != null && !wechatForm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(wechatForm.getVersion(), Config.VERSION);
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
		if (wechatForm.getId() == null || wechatForm.getId() == 0) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
		}

				
		// 更新
		int count = 0;
		count = wechatFormService.updateByWechatForm(wechatForm);
		
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
	 * 根据WechatForm实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param wechatForm
	 * @return
	 * 
	 * @author ZY on 2018/11/12
	 */
	@ResponseBody
	@RequestMapping("/selectByWechatForm")
	public LayuiDataTemplet<WechatForm> selectByWechatForm(@RequestBody WechatForm wechatForm) {
		LayuiDataTemplet<WechatForm> returnData = new LayuiDataTemplet<WechatForm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (wechatForm.getVersion() != null && !wechatForm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(wechatForm.getVersion(), Config.VERSION);
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
		if (wechatForm.getPagenumber() != null) {
			// 计算偏移量
			if (wechatForm.getPagenumber() != -1) {
				if (wechatForm.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = wechatForm.getPagenumber();
				int pageSize = wechatForm.getPagesize();
				wechatForm.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				wechatForm.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = wechatFormService.selectCountByWechatForm(wechatForm);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<WechatForm> wechatFormList = wechatFormService.selectByWechatForm(wechatForm); // 查询数据
				returnData.setData(wechatFormList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	/*
	*//**
	 * 根据WechatForm实体模糊查询
   	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param wechatForm
	 * @return
	 * 
	 * @author ZY on 2018/11/12
	 *//*
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<WechatForm> selectBySelectData(@RequestBody WechatForm wechatForm) {
		LayuiDataTemplet<WechatForm> returnData = new LayuiDataTemplet<WechatForm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (wechatForm.getVersion() != null && !wechatForm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(wechatForm.getVersion(), Config.VERSION);
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
		if (wechatForm.getPagenumber() != null) {
			// 计算偏移量
			if (wechatForm.getPagenumber() != -1) {
				if (wechatForm.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = wechatForm.getPagenumber();
				int pageSize = wechatForm.getPagesize();
				wechatForm.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				wechatForm.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = wechatFormService.selectCountBySelectData(wechatForm); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<WechatForm> wechatFormList = wechatFormService.selectBySelectData(wechatForm); // 查询数据
				returnData.setData(wechatFormList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}*/

	/**
	 * 根据idList删除信息
   	 * 
	 * @param wechatForm
	 * @return
	 * 
	 * @author ZY on 2018/11/12
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<WechatForm> deleteByIdList(@RequestBody WechatForm wechatForm) {
		LayuiDataTemplet<WechatForm> returnData = new LayuiDataTemplet<WechatForm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (wechatForm.getVersion() != null && !wechatForm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(wechatForm.getVersion(), Config.VERSION);
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
		count = wechatFormService.deleteByIdList(wechatForm.getIdlist());

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
