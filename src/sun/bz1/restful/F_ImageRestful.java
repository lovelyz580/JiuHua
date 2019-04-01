package sun.bz1.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.LayuiDataTemplet;
import sun.bz1.entity.Image;
import sun.bz1.service.ImageService;
import util.Config;
import util.VersionCompare;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 首页图片表
 * 
 * Restful
 * 
 * @author ZY on 2018/11/29
 */

@Controller
@RequestMapping("/image")
public class F_ImageRestful {

	@Autowired
	private ImageService imageService;

	/**
	 * 根据Image实体添加
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/29
	 */
	@ResponseBody
	@RequestMapping("/insertByImage")
	public LayuiDataTemplet<Image> insertByImage(@RequestBody Image image) {
		LayuiDataTemplet<Image> returnData = new LayuiDataTemplet<Image>();
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

		Image selectImage = new Image();
		selectImage.setPagenumber(-1);//不分页
		if (!image.getImagetype().equals("SYTP")){
			selectImage.setImagetype(image.getImagetype());//添加类别    除轮播图外只能添加一条信息
		}
		List<Image> imageList = new ArrayList<Image>();
		imageList = imageService.selectByImage(selectImage);
		if (imageList.size() >= 1) {
			returnData.setMsg("该类型的数据只能添加一条！");
			return returnData;
		}

		// 添加数据
		image.setImageid(Config.SIGN_IMAGE + UUID.randomUUID().toString()); // 首页图片ID(JFBG+UUID)
		image.setImagestate("Y"); // 新闻状态(Y:有效/N:无效)
		image.setImagecreatetime(new Date()); // 新闻创建时间

		// 添加
		int count = 0;
		count = imageService.insertByImage(image);

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
	 * 根据Image实体更新
	 * 
	 * @param image
	 * @return
	 * 
	 * @author ZY on 2018/11/29
	 */
	@ResponseBody
	@RequestMapping("/updateByImage")
	public LayuiDataTemplet<Image> updateByImage(@RequestBody Image image) {
		LayuiDataTemplet<Image> returnData = new LayuiDataTemplet<Image>(); // 返回数据
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
			if (image.getImageid() == null || image.getImageid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		//查询当前imageid的数据
		Image thisImage = new Image();
		thisImage.setPagenumber(-1);//
		thisImage.setImageid(image.getImageid());
		List<Image> thisImageList = imageService.selectByImage(thisImage);


		Image selectImage = new Image();
		selectImage.setPagenumber(-1);//不分页
		if (!image.getImagetype().equals("SYTP")){
			selectImage.setImagetype(image.getImagetype());//添加类别    除轮播图外只能添加一条信息
		}
		List<Image> imageList = new ArrayList<Image>();
		imageList = imageService.selectByImage(selectImage);
		if (imageList.size() >0) {
			if (!thisImageList.get(0).getImagetype().equals(image.getImagetype())) {
				if (imageList.size() >= 1) {
					returnData.setMsg("该类型的数据只能存在一条！");
					return returnData;
				}
			}
		}

		// 更新数据
		image.setImagecreatetime(new Date()); // 更新时间

		// 更新
		int count = 0;
		count = imageService.updateByImage(image);

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
	 * 根据Image实体联表查询
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
	 * @author ZY on 2018/11/29
	 */
	@ResponseBody
	@RequestMapping("/selectByImage")
	public LayuiDataTemplet<Image> selectByImage(@RequestBody Image image) {
		LayuiDataTemplet<Image> returnData = new LayuiDataTemplet<Image>(); // 返回数据
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
			count = imageService.selectCountByImage(image);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Image> imageList = imageService.selectByImage(image); // 查询数据
				returnData.setData(imageList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据Image实体联表模糊查询
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
	 * @author ZY on 2018/11/29
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<Image> selectBySelectData(@RequestBody Image image) {
		LayuiDataTemplet<Image> returnData = new LayuiDataTemplet<Image>(); // 返回数据
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
				List<Image> imageList = imageService.selectBySelectData(image); // 查询数据
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
	 * @author ZY on 2018/11/29
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<Image> deleteByIdList(@RequestBody Image image) {
		LayuiDataTemplet<Image> returnData = new LayuiDataTemplet<Image>(); // 返回数据
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
