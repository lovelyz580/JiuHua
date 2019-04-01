package sun.bz1.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.LayuiDataTemplet;
import sun.bz1.entity.DistributeConfirm;
import sun.bz1.service.DistributeConfirmService;
import util.Config;
import util.VersionCompare;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 派单确认表
 * 
 * Restful
 * 
 * @author ZY on 2018/11/22
 */

@Controller
@RequestMapping("/distributeConfirm")
public class F_DistributeConfirmRestful {

	@Autowired
	private DistributeConfirmService distributeConfirmService;

	/**
	 * 根据DistributeConfirm实体添加
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	@ResponseBody
	@RequestMapping("/insertByDistributeConfirm")
	public LayuiDataTemplet<DistributeConfirm> insertByDistributeConfirm(@RequestBody DistributeConfirm distributeConfirm) {
		LayuiDataTemplet<DistributeConfirm> returnData = new LayuiDataTemplet<DistributeConfirm>();
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不能为空，验证版本号
		try {
			if (null != distributeConfirm.getVersion() && !distributeConfirm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(distributeConfirm.getVersion(), Config.VERSION);
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
		distributeConfirm.setDistributeconfirmid(Config.SIGN_DISTRIBUTECONFIRM + UUID.randomUUID().toString()); // 纠纷ID(JFBG+UUID)
		//distributeConfirm.setDistributeconfirmstate("Y"); // 状态(Y:有效/N:无效)
		distributeConfirm.setDistributeconfirmcreatetime(new Date()); // 创建时间

		// 添加
		int count = 0;
		count = distributeConfirmService.insertByDistributeConfirm(distributeConfirm);

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
	 * 根据DistributeConfirm实体更新
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	@ResponseBody
	@RequestMapping("/updateByDistributeConfirm")
	public LayuiDataTemplet<DistributeConfirm> updateByDistributeConfirm(@RequestBody DistributeConfirm distributeConfirm) {
		LayuiDataTemplet<DistributeConfirm> returnData = new LayuiDataTemplet<DistributeConfirm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (distributeConfirm.getVersion() != null && !distributeConfirm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(distributeConfirm.getVersion(), Config.VERSION);
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


		// 更新数据
		distributeConfirm.setDistributeconfirmupdatetime(new Date()); // 更新时间

		// 更新
		int count = 0;
		count = distributeConfirmService.updateByDistributeConfirm(distributeConfirm);

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
	 * 根据DistributeConfirm实体更新(维修工确认接收订单是的判断重复点击事件)
	 *
	 * @param distributeConfirm
	 * @return
	 *
	 * @author ZY on 2018/11/22
	 */
	@ResponseBody
	@RequestMapping("/updateByDistributeConfirmService")
	public LayuiDataTemplet<DistributeConfirm> updateByDistributeConfirmService(@RequestBody DistributeConfirm distributeConfirm) {
		LayuiDataTemplet<DistributeConfirm> returnData = new LayuiDataTemplet<DistributeConfirm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (distributeConfirm.getVersion() != null && !distributeConfirm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(distributeConfirm.getVersion(), Config.VERSION);
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
		//查询是否存在更新当前更新过得数据
		DistributeConfirm distributeConfirm1 = new DistributeConfirm();
		distributeConfirm1.setPagenumber(-1);//不分页
		distributeConfirm1.setOrderid(distributeConfirm.getOrderid());//订单编号
		distributeConfirm1.setDistributeconfirmserviceuserid(distributeConfirm.getDistributeconfirmserviceuserid());//派单维修人员ID(YHBG+UUID)
		List<DistributeConfirm> distributeConfirmList = distributeConfirmService.selectByDistributeConfirm(distributeConfirm1);

		if (distributeConfirmList.size() > 0){
			if (distributeConfirmList.get(0).getDistributeconfirmstate().equals("SY")) {
				returnData.setMsg("您已确认，请勿重复确认！请等待管理员确认！");

				return returnData;
			}
		}


		// 更新数据
		distributeConfirm.setDistributeconfirmupdatetime(new Date()); // 更新时间

		// 更新
		int count = 0;
		count = distributeConfirmService.updateByDistributeConfirm(distributeConfirm);

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
	 * 根据DistributeConfirm实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	@ResponseBody
	@RequestMapping("/selectByDistributeConfirm")
	public LayuiDataTemplet<DistributeConfirm> selectByDistributeConfirm(@RequestBody DistributeConfirm distributeConfirm) {
		LayuiDataTemplet<DistributeConfirm> returnData = new LayuiDataTemplet<DistributeConfirm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (distributeConfirm.getVersion() != null && !distributeConfirm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(distributeConfirm.getVersion(), Config.VERSION);
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
		if (distributeConfirm.getPagenumber() != null) {
			// 计算偏移量
			if (distributeConfirm.getPagenumber() != -1) {
				if (distributeConfirm.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = distributeConfirm.getPagenumber();
				int pageSize = distributeConfirm.getPagesize();
				distributeConfirm.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				distributeConfirm.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = distributeConfirmService.selectCountByDistributeConfirm(distributeConfirm);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<DistributeConfirm> distributeConfirmList = distributeConfirmService.selectByDistributeConfirm(distributeConfirm); // 查询数据
				returnData.setData(distributeConfirmList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据DistributeConfirm实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<DistributeConfirm> selectBySelectData(@RequestBody DistributeConfirm distributeConfirm) {
		LayuiDataTemplet<DistributeConfirm> returnData = new LayuiDataTemplet<DistributeConfirm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (distributeConfirm.getVersion() != null && !distributeConfirm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(distributeConfirm.getVersion(), Config.VERSION);
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
		if (distributeConfirm.getPagenumber() != null) {
			// 计算偏移量
			if (distributeConfirm.getPagenumber() != -1) {
				if (distributeConfirm.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = distributeConfirm.getPagenumber();
				int pageSize = distributeConfirm.getPagesize();
				distributeConfirm.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				distributeConfirm.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = distributeConfirmService.selectCountBySelectData(distributeConfirm); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<DistributeConfirm> distributeConfirmList = distributeConfirmService.selectBySelectData(distributeConfirm); // 查询数据
				returnData.setData(distributeConfirmList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param distributeConfirm
	 * @return
	 * 
	 * @author ZY on 2018/11/22
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<DistributeConfirm> deleteByIdList(@RequestBody DistributeConfirm distributeConfirm) {
		LayuiDataTemplet<DistributeConfirm> returnData = new LayuiDataTemplet<DistributeConfirm>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (distributeConfirm.getVersion() != null && !distributeConfirm.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(distributeConfirm.getVersion(), Config.VERSION);
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
		count = distributeConfirmService.deleteByIdList(distributeConfirm.getIdlist());

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
