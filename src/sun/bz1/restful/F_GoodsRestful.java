package sun.bz1.restful;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.Goods;
import sun.bz1.service.GoodsService;
import util.Config;
import util.VersionCompare;

/**
 * 产品表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/04
 */

@Controller
@RequestMapping("/goods")
public class F_GoodsRestful {
	
	@Autowired
	private GoodsService goodsService;

	/**
	 * 根据Goods实体添加
	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/insertByGoods")
	public LayuiDataTemplet<Goods> insertByGoods(@RequestBody Goods goods) {
		LayuiDataTemplet<Goods> returnData = new LayuiDataTemplet<Goods>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (goods.getVersion() != null && !goods.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(goods.getVersion(), Config.VERSION);
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
		goods.setGoodsid(Config.SIGN_GOODS + UUID.randomUUID().toString()); // 产品ID(CPBG+UUID)
//		goods.setGoodsstate("Y"); // 产品状态(Y:有效/N:无效)
		goods.setGoodscreatetime(new Date()); // 产品创建时间
//		goods.setGoodsupdateuserid(goods.getGoodscreateuserid()); // 产品更新人ID(YHBG+UUID)
//		goods.setGoodsupdatetime(new Date()); // 产品更新时间
		
		// 添加
		int count = 0;
		count = goodsService.insertByGoods(goods);
		
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
	 * 根据Goods实体更新
	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/updateByGoods")
	public LayuiDataTemplet<Goods> updateByGoods(@RequestBody Goods goods) {
		LayuiDataTemplet<Goods> returnData = new LayuiDataTemplet<Goods>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (goods.getVersion() != null && !goods.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(goods.getVersion(), Config.VERSION);
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
		if (goods.getId() == null || goods.getId() == 0) {
			if (goods.getGoodsid() == null || goods.getGoodsid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据
		goods.setGoodsupdatetime(new Date()); // 产品更新时间
				
		// 更新
		int count = 0;
		count = goodsService.updateByGoods(goods);
		
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
	 * 根据Goods实体查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectByGoods")
	public LayuiDataTemplet<Goods> selectByGoods(@RequestBody Goods goods) {
		LayuiDataTemplet<Goods> returnData = new LayuiDataTemplet<Goods>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (goods.getVersion() != null && !goods.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(goods.getVersion(), Config.VERSION);
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
		if (goods.getPagenumber() != null) {
			// 计算偏移量
			if (goods.getPagenumber() != -1) {
				if (goods.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = goods.getPagenumber();
				int pageSize = goods.getPagesize();
				goods.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				goods.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = goodsService.selectCountByGoods(goods); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Goods> goodsList = goodsService.selectByGoods(goods); // 查询数据
				returnData.setData(goodsList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据Goods实体模糊查询
   	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<Goods> selectBySelectData(@RequestBody Goods goods) {
		LayuiDataTemplet<Goods> returnData = new LayuiDataTemplet<Goods>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (goods.getVersion() != null && !goods.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(goods.getVersion(), Config.VERSION);
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
		if (goods.getPagenumber() != null) {
			// 计算偏移量
			if (goods.getPagenumber() != -1) {
				if (goods.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = goods.getPagenumber();
				int pageSize = goods.getPagesize();
				goods.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				goods.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = goodsService.selectCountBySelectData(goods); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<Goods> goodsList = goodsService.selectBySelectData(goods); // 查询数据
				returnData.setData(goodsList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param goods
	 * @return
	 * 
	 * @author WJF on 2018/09/04
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<Goods> deleteByIdList(@RequestBody Goods goods) {
		LayuiDataTemplet<Goods> returnData = new LayuiDataTemplet<Goods>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (goods.getVersion() != null && !goods.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(goods.getVersion(), Config.VERSION);
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
		count = goodsService.deleteByIdList(goods.getIdlist());

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
