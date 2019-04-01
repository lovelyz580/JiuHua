package sun.bz1.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.LayuiDataTemplet;
import sun.bz1.entity.ShareVideo;
import sun.bz1.service.ShareVideoService;
import util.Config;
import util.VersionCompare;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 分享视频表
 * 
 * Restful
 * 
 * @author ZY on 2018/11/30
 */

@Controller
@RequestMapping("/shareVideo")
public class F_ShareVideoRestful {

	@Autowired
	private ShareVideoService imageService;

	/**
	 * 根据ShareVideo实体添加
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	@ResponseBody
	@RequestMapping("/insertByShareVideo")
	public LayuiDataTemplet<ShareVideo> insertByShareVideo(@RequestBody ShareVideo image) {
		LayuiDataTemplet<ShareVideo> returnData = new LayuiDataTemplet<ShareVideo>();
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不能为空，验证版本号
		try {
			if (null != image.getVersion() && !image.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(image.getVersion(), Config.VERSION);
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
		image.setSharevideoid(Config.SIGN_SHAREVIDEO + UUID.randomUUID().toString()); // 分享视频ID(JFBG+UUID)
		image.setSharevideostate("Y"); // 视频状态(Y:有效/N:无效)
		image.setSharevideocreatetime(new Date()); // 视频创建时间

		// 添加
		int count = 0;
		count = imageService.insertByShareVideo(image);

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
	 * 根据ShareVideo实体更新
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	@ResponseBody
	@RequestMapping("/updateByShareVideo")
	public LayuiDataTemplet<ShareVideo> updateByShareVideo(@RequestBody ShareVideo image) {
		LayuiDataTemplet<ShareVideo> returnData = new LayuiDataTemplet<ShareVideo>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (image.getVersion() != null && !image.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(image.getVersion(), Config.VERSION);
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
		if (image.getId() == null || image.getId() == 0) {
			if (image.getSharevideoid() == null || image.getSharevideoid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		image.setSharevideocreatetime(new Date()); // 更新时间

		// 更新
		int count = 0;
		count = imageService.updateByShareVideo(image);

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
	 * 根据ShareVideo实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	@ResponseBody
	@RequestMapping("/selectByShareVideo")
	public LayuiDataTemplet<ShareVideo> selectByShareVideo(@RequestBody ShareVideo image) {
		LayuiDataTemplet<ShareVideo> returnData = new LayuiDataTemplet<ShareVideo>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (image.getVersion() != null && !image.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(image.getVersion(), Config.VERSION);
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
		if (image.getPagenumber() != null) {
			// 计算偏移量
			if (image.getPagenumber() != -1) {
				if (image.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = image.getPagenumber();
				int pageSize = image.getPagesize();
				image.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				image.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = imageService.selectCountByShareVideo(image);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<ShareVideo> imageList = imageService.selectByShareVideo(image); // 查询数据
				returnData.setData(imageList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据ShareVideo实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<ShareVideo> selectBySelectData(@RequestBody ShareVideo image) {
		LayuiDataTemplet<ShareVideo> returnData = new LayuiDataTemplet<ShareVideo>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (image.getVersion() != null && !image.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(image.getVersion(), Config.VERSION);
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
		if (image.getPagenumber() != null) {
			// 计算偏移量
			if (image.getPagenumber() != -1) {
				if (image.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = image.getPagenumber();
				int pageSize = image.getPagesize();
				image.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				image.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = imageService.selectCountBySelectData(image); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<ShareVideo> imageList = imageService.selectBySelectData(image); // 查询数据
				returnData.setData(imageList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/30
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<ShareVideo> deleteByIdList(@RequestBody ShareVideo image) {
		LayuiDataTemplet<ShareVideo> returnData = new LayuiDataTemplet<ShareVideo>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (image.getVersion() != null && !image.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(image.getVersion(), Config.VERSION);
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
		count = imageService.deleteByIdList(image.getIdlist());

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
