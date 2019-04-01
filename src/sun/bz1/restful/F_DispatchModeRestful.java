package sun.bz1.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.LayuiDataTemplet;
import sun.bz1.entity.DispatchMode;
import sun.bz1.entity.News;
import sun.bz1.service.DispatchModeService;
import sun.bz1.service.NewsService;
import util.Config;
import util.VersionCompare;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 派单方式表
 * 
 * Restful
 * 
 * @author ZY on 2018/09/19
 */

@Controller
@RequestMapping("/dispatchMode")
public class F_DispatchModeRestful {

	@Autowired
	private DispatchModeService newsService;

	/**
	 * 根据DispatchMode实体添加
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/insertByDispatchMode")
	public LayuiDataTemplet<DispatchMode> insertByDispatchMode(@RequestBody DispatchMode news) {
		LayuiDataTemplet<DispatchMode> returnData = new LayuiDataTemplet<DispatchMode>();
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不能为空，验证版本号
		try {
			if (null != news.getVersion() && !news.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(news.getVersion(), Config.VERSION);
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
		news.setDispatchmodeupdatetime(new Date()); // 新闻创建时间

		// 添加
		int count = 0;
		count = newsService.insertByDispatchMode(news);

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
	 * 根据DispatchMode实体更新
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/updateByDispatchMode")
	public LayuiDataTemplet<DispatchMode> updateByDispatchMode(@RequestBody DispatchMode news) {
		LayuiDataTemplet<DispatchMode> returnData = new LayuiDataTemplet<DispatchMode>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (news.getVersion() != null && !news.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(news.getVersion(), Config.VERSION);
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
		if (news.getId() == null || news.getId() == 0) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
		}

		// 更新数据
		news.setDispatchmodeupdatetime(new Date()); // 更新时间

		// 更新
		int count = 0;
		count = newsService.updateByDispatchMode(news);

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
	 * 根据DispatchMode实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/selectByDispatchMode")
	public LayuiDataTemplet<DispatchMode> selectByDispatchMode(@RequestBody DispatchMode news) {
		LayuiDataTemplet<DispatchMode> returnData = new LayuiDataTemplet<DispatchMode>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (news.getVersion() != null && !news.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(news.getVersion(), Config.VERSION);
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
		if (news.getPagenumber() != null) {
			// 计算偏移量
			if (news.getPagenumber() != -1) {
				if (news.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = news.getPagenumber();
				int pageSize = news.getPagesize();
				news.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				news.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = newsService.selectCountByDispatchMode(news);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<DispatchMode> newsList = newsService.selectByDispatchMode(news); // 查询数据
				returnData.setData(newsList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据DispatchMode实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<DispatchMode> selectBySelectData(@RequestBody DispatchMode news) {
		LayuiDataTemplet<DispatchMode> returnData = new LayuiDataTemplet<DispatchMode>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (news.getVersion() != null && !news.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(news.getVersion(), Config.VERSION);
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
		if (news.getPagenumber() != null) {
			// 计算偏移量
			if (news.getPagenumber() != -1) {
				if (news.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = news.getPagenumber();
				int pageSize = news.getPagesize();
				news.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				news.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = newsService.selectCountBySelectData(news); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<DispatchMode> newsList = newsService.selectBySelectData(news); // 查询数据
				returnData.setData(newsList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param news
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<DispatchMode> deleteByIdList(@RequestBody DispatchMode news) {
		LayuiDataTemplet<DispatchMode> returnData = new LayuiDataTemplet<DispatchMode>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (news.getVersion() != null && !news.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(news.getVersion(), Config.VERSION);
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
		count = newsService.deleteByIdList(news.getIdlist());

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
