package sun.bz1.restful;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.*;
import sun.bz1.service.PriceService;
import sun.bz1.service.UserService;
import util.Config;
import util.JsonUtil;
import util.VersionCompare;

/**
 * 维修单价表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/05
 */

@Controller
@RequestMapping("/price")
public class F_PriceRestful {

	@Autowired
	private PriceService priceService;

	@Autowired
	private UserService userService;

	/**
	 * 根据Price实体添加
	 * 
	 * @param price
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/insertByPrice")
	public LayuiDataTemplet<Price> insertByPrice(@RequestBody Price price) {
		LayuiDataTemplet<Price> returnData = new LayuiDataTemplet<Price>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (price.getVersion() != null && !price.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(price.getVersion(), Config.VERSION);
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
		price.setPriceid(Config.SIGN_PRICE + UUID.randomUUID().toString()); // 维修单价ID(WXDJ+UUID)
//		price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
		price.setPriceupdateuserid(price.getPricecreateuserid()); // 维修单价创建人ID(YHBG+UUID)
		price.setPricecreatetime(new Date()); // 维修单价创建时间
		price.setPriceupdatetime(new Date()); // 维修单价更新时间
		
		// 添加
		int count = 0;
		count = priceService.insertByPrice(price);
		
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
	 * 根据Price实体添加
	 * 
	 * @param price
	 * @return
	 * 
	 * @author ZY on 2018/10/01
	 */
	@ResponseBody
	@RequestMapping("/insertOrUpdateByPrice")
	public LayuiDataTemplet<Price> insertOrUpdateByPrice(@RequestBody Price price) {
		LayuiDataTemplet<Price> returnData = new LayuiDataTemplet<Price>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (price.getVersion() != null && !price.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(price.getVersion(), Config.VERSION);
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
		
		PriceAndGoodsAndProjectAndUser unionData  = new PriceAndGoodsAndProjectAndUser();
		unionData.setPagenumber(-1);//不分页
		unionData.setProjectid(price.getProjectid());//项目ID
		unionData.setPriceupdateuserid(price.getPricecreateuserid());//创建人ID
		List<PriceAndGoodsAndProjectAndUser> priceList = priceService.selectFourTablesByUnionData(unionData); // 查询数据

		int count = 0;
		if (priceList.size() > 0) {
			// 更新数据
			price.setPriceupdatetime(new Date()); // 维修单价更新时间
					
			// 更新
			count = priceService.updateByPrice(price);
			// 返回数据
			if (count == 0) {
				returnData.setMsg("更新失败！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("更新成功！");
			}
		} else {
			// 添加数据
			price.setPriceid(Config.SIGN_PRICE + UUID.randomUUID().toString()); // 维修单价ID(WXDJ+UUID)
			price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
			price.setPriceupdateuserid(price.getPricecreateuserid()); // 维修单价创建人ID(YHBG+UUID)
			price.setPricecreatetime(new Date()); // 维修单价创建时间
			price.setPriceupdatetime(new Date()); // 维修单价更新时间
			count = priceService.insertByPrice(price);
			
			// 返回数据
			if (count == 0) {
				returnData.setMsg("添加失败！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("添加成功！");
			}
		}

		return returnData;
	}


	/**
	 * 根据Price实体添加和修改(最新的小程序端的功能)
	 *
	 * @param price
	 * @return
	 *
	 * @author ZY on 2018/10/01
	 */
	@ResponseBody
	@RequestMapping("/insertOrUpdateByPriceNew")
	public LayuiDataTemplet<Price> insertOrUpdateByPriceNew(@RequestBody Price price) {
		LayuiDataTemplet<Price> returnData = new LayuiDataTemplet<Price>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (price.getVersion() != null && !price.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(price.getVersion(), Config.VERSION);
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

		PriceAndGoodsAndProjectAndUser unionData  = new PriceAndGoodsAndProjectAndUser();
		unionData.setPagenumber(-1);//不分页
		unionData.setProjectid(price.getPriceid());//单价ID
		unionData.setPriceareacode(price.getPriceareacode());//区域代码
		unionData.setPricebuildingtypeid(price.getPricebuildingtypeid());//建筑类型
		unionData.setPricepropertycompanyid(price.getPricepropertycompanyid());//地产公司
		unionData.setGoodsid(price.getGoodsid());//任务类型
		unionData.setProjectid(price.getProjectid());//项目清单
		unionData.setPriceupdateuserid(price.getPriceupdateuserid());//更新人ID
		List<PriceAndGoodsAndProjectAndUser> priceList = priceService.selectFourTablesByUnionData(unionData); // 查询数据

		int count = 0;
		if (priceList.size() > 0) {
			// 更新数据
			price.setPriceid(priceList.get(0).getPriceid());
			price.setPriceupdatetime(new Date()); // 维修单价更新时间

			// 更新
			count = priceService.updateByPrice(price);
			// 返回数据
			if (count == 0) {
				returnData.setMsg("更新失败！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("更新成功！");
			}
		} else {
			//查询  判断是否重复存在
			PriceAndGoodsAndProjectAndUser selectData = new PriceAndGoodsAndProjectAndUser();
			selectData.setPagenumber(-1);//不分页
			selectData.setPriceareacode(price.getPriceareacode());//区域代码
			selectData.setPricebuildingtypeid(price.getPricebuildingtypeid());//建筑类型
			selectData.setPricepropertycompanyid(price.getPricepropertycompanyid());//地产公司
			selectData.setGoodsid(price.getGoodsid());//任务类型
			selectData.setProjectid(price.getProjectid());//项目清单
			selectData.setPriceupdateuserid(price.getPriceupdateuserid());//更新人ID
			List<PriceAndGoodsAndProjectAndUser> selectDataList = new ArrayList<PriceAndGoodsAndProjectAndUser>();
			selectDataList = priceService.selectFourTablesByUnionData(selectData); // 查询数据

			if (selectDataList.size() > 0){
				returnData.setMsg("已存在该维修单价！请使用修改功能！");

				return  returnData;
			}

			// 添加数据
			price.setPriceid(Config.SIGN_PRICE + UUID.randomUUID().toString()); // 维修单价ID(WXDJ+UUID)
			price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
			price.setPriceupdateuserid(price.getPriceupdateuserid()); // 维修单价创建人ID(YHBG+UUID)
			price.setPricecreateuserid(price.getPriceupdateuserid()); // 维修单价创建人ID(YHBG+UUID)
			price.setPricecreatetime(new Date()); // 维修单价创建时间
			price.setPriceupdatetime(new Date()); // 维修单价更新时间
			count = priceService.insertByPrice(price);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("添加失败！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("添加成功！");
			}
		}

		return returnData;
	}
	
	/**
	 * 根据Price实体更新
	 * 
	 * @param price
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/updateByPrice")
	public LayuiDataTemplet<Price> updateByPrice(@RequestBody Price price) {
		LayuiDataTemplet<Price> returnData = new LayuiDataTemplet<Price>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (price.getVersion() != null && !price.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(price.getVersion(), Config.VERSION);
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
		if (price.getId() == null || price.getId() == 0) {
			if (price.getPriceid() == null || price.getPriceid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}
				
		// 更新数据
		price.setPriceupdatetime(new Date()); // 维修单价更新时间
				
		// 更新
		int count = 0;
		count = priceService.updateByPrice(price);
		
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
	 * 根据PriceAndGoodsAndProjectAndUser实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/selectFourTablesByUnionData")
	public LayuiDataTemplet<PriceAndGoodsAndProjectAndUser> selectFourTablesByUnionData(@RequestBody PriceAndGoodsAndProjectAndUser unionData) {
		LayuiDataTemplet<PriceAndGoodsAndProjectAndUser> returnData = new LayuiDataTemplet<PriceAndGoodsAndProjectAndUser>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (unionData.getVersion() != null && !unionData.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(unionData.getVersion(), Config.VERSION);
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
		if (unionData.getPagenumber() != null) {
			// 计算偏移量
			if (unionData.getPagenumber() != -1) {
				if (unionData.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = unionData.getPagenumber();
				int pageSize = unionData.getPagesize();
				unionData.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				unionData.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = priceService.selectFourTablesCountByUnionData(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<PriceAndGoodsAndProjectAndUser> priceList = priceService.selectFourTablesByUnionData(unionData); // 查询数据
				returnData.setData(priceList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据PriceAndGoodsAndProjectAndUser实体联表模糊查询
   	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/selectFourTablesBySelectData")
	public LayuiDataTemplet<PriceAndGoodsAndProjectAndUser> selectFourTablesBySelectData(@RequestBody PriceAndGoodsAndProjectAndUser unionData) {
		LayuiDataTemplet<PriceAndGoodsAndProjectAndUser> returnData = new LayuiDataTemplet<PriceAndGoodsAndProjectAndUser>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (unionData.getVersion() != null && !unionData.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(unionData.getVersion(), Config.VERSION);
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
		if (unionData.getPagenumber() != null) {
			// 计算偏移量
			if (unionData.getPagenumber() != -1) {
				if (unionData.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = unionData.getPagenumber();
				int pageSize = unionData.getPagesize();
				unionData.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				unionData.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = priceService.selectFourTablesCountBySelectData(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<PriceAndGoodsAndProjectAndUser> priceList = priceService.selectFourTablesBySelectData(unionData); // 查询数据
				returnData.setData(priceList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据PriceAndGoodsAndProjectAndUser实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param unionData
	 * @return
	 * 
	 * @author ZY on 2018/09/30
	 */
	@ResponseBody
	@RequestMapping("/selectProjectAndGoods")
	public LayuiDataTemplet<ProjectAndGoods> selectProjectAndGoods(@RequestBody PriceAndGoodsAndProjectAndUser unionData) {
		LayuiDataTemplet<ProjectAndGoods> returnData = new LayuiDataTemplet<ProjectAndGoods>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (unionData.getVersion() != null && !unionData.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(unionData.getVersion(), Config.VERSION);
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
		if (unionData.getPagenumber() != null) {
			// 计算偏移量
			if (unionData.getPagenumber() != -1) {
				if (unionData.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = unionData.getPagenumber();
				int pageSize = unionData.getPagesize();
				unionData.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				unionData.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = priceService.selectCountProjectAndGoods(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<ProjectAndGoods> priceList = new ArrayList<ProjectAndGoods>();
				//维修人员的接单类别
				UserAndUserServiceAndUserCustomer userAndUserServiceAndUserCustomer = new UserAndUserServiceAndUserCustomer();
				userAndUserServiceAndUserCustomer.setPagenumber(-1);//不分页
				userAndUserServiceAndUserCustomer.setUserid(unionData.getPriceupdateuserid());
				List<UserAndUserServiceAndUserCustomer> userList = userService.selectThreeTablesByUnionData(userAndUserServiceAndUserCustomer); // 查询数据
				String [] goodsIds = userList.get(0).getGoodid().split(",");//维修人员的接单类别
				for (int i = 0; i < goodsIds.length; i ++ ){
					String goodsId = goodsIds[i];
					unionData.setGoodsid(goodsId);
					List<ProjectAndGoods> priceListAndOneGoods = priceService.selectProjectAndGoods(unionData); // 查询数据
					priceList.addAll(priceListAndOneGoods);
				}

				returnData.setData(priceList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据Price实体添加
	 *
	 * @param models
	 * @return
	 *
	 * @author ZY on 2018/10/01
	 */
	@ResponseBody
	@RequestMapping("/insertOrUpdateByPriceModel")
	public LayuiDataTemplet<Price> insertOrUpdateByPriceModel(@RequestBody Map<String, Object> models) {
		LayuiDataTemplet<Price> returnData = new LayuiDataTemplet<Price>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		PriceListEntity priceListEntity = null;
		String pricecreateuserid = "";

		try {
			priceListEntity = JsonUtil.json2obj((String)models.get("priceListEntity"), PriceListEntity.class);
		} catch (Exception e1) {
			e1.printStackTrace();

			returnData.setMsg("JSON转换为实体类发生异常！");
			return returnData;
		}

		try {
			pricecreateuserid = (String)models.get("pricecreateuserid");
		} catch (Exception e1) {
			e1.printStackTrace();

			returnData.setMsg("JSON转换错误！");
			return returnData;
		}

		// 判断传递的数据

		// 判断传递的数据
		// OrderDetailListEntity实体(List<OrderDetail>)
		List<Price> priceList = null;
		if (priceListEntity == null) {
			returnData.setMsg("传递的单价类型数据错误！");
			return returnData;
		} else {
			priceList = priceListEntity.getPriceList();
		}
		// 版本号不为空，则验证版本号
		try {
			if (priceListEntity.getVersion() != null && !priceListEntity.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(priceListEntity.getVersion(), Config.VERSION);
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

		int count = 0;
		for (int i = 0; i < priceList.size(); i ++){
			if (null == priceList.get(i).getPriceid() || priceList.get(i).getPriceid().equals("")){
				Price insertPrice = new Price();
				// 添加数据
				insertPrice.setPriceid(Config.SIGN_PRICE + UUID.randomUUID().toString()); // 维修单价ID(WXDJ+UUID)
				insertPrice.setGoodsid(priceList.get(i).getGoodsid());//项目类别
				insertPrice.setProjectid(priceList.get(i).getProjectid());//清单项目
				insertPrice.setPricemoney(priceList.get(i).getPricemoney());//单价
				insertPrice.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
				insertPrice.setPricetype(priceList.get(i).getProjecttype());//类型
				insertPrice.setPricecreateuserid(pricecreateuserid);//维修单价创建人ID(YHBG+UUID)
				insertPrice.setPriceupdateuserid(pricecreateuserid); // 维修单价创建人ID(YHBG+UUID)
				insertPrice.setPricecreatetime(new Date()); // 维修单价创建时间
				insertPrice.setPriceupdatetime(new Date()); // 维修单价更新时间
				count = priceService.insertByPrice(insertPrice);

				// 返回数据
				if (count == 0) {
					returnData.setMsg("第" + i+1 + "条数据添加失败！");
				} else {
					returnData.setCount(count);
					returnData.setMsg("添加成功！");
				}
			} else {
				Price updatePrice = new Price();
				// 更新数据
				updatePrice.setPriceid(priceList.get(i).getPriceid());//单价ID
				updatePrice.setPricemoney(priceList.get(i).getPricemoney());//修改后的单价
				updatePrice.setPriceupdatetime(new Date()); // 维修单价更新时间

				// 更新
				count = priceService.updateByPrice(updatePrice);
				// 返回数据
				if (count == 0) {
					returnData.setMsg("第" + i+1 + "条数据更新失败！");
				} else {
					returnData.setCount(count);
					returnData.setMsg("更新成功！");
				}
			}
		}
		/*PriceAndGoodsAndProjectAndUser unionData  = new PriceAndGoodsAndProjectAndUser();
		unionData.setPagenumber(-1);//不分页
		unionData.setProjectid(price.getProjectid());//项目ID
		unionData.setPriceupdateuserid(price.getPricecreateuserid());//创建人ID
		List<PriceAndGoodsAndProjectAndUser> priceList = priceService.selectFourTablesByUnionData(unionData); // 查询数据

		int count = 0;
		if (priceList.size() > 0) {
			// 更新数据
			price.setPriceupdatetime(new Date()); // 维修单价更新时间

			// 更新
			count = priceService.updateByPrice(price);
			// 返回数据
			if (count == 0) {
				returnData.setMsg("更新失败！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("更新成功！");
			}
		} else {
			// 添加数据
			price.setPriceid(Config.SIGN_PRICE + UUID.randomUUID().toString()); // 维修单价ID(WXDJ+UUID)
			price.setPricestate("Y"); // 维修单价状态(Y:有效/N:无效)
			price.setPriceupdateuserid(price.getPricecreateuserid()); // 维修单价创建人ID(YHBG+UUID)
			price.setPricecreatetime(new Date()); // 维修单价创建时间
			price.setPriceupdatetime(new Date()); // 维修单价更新时间
			count = priceService.insertByPrice(price);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("添加失败！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("添加成功！");
			}
		}*/

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param price
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<Price> deleteByIdList(@RequestBody Price price) {
		LayuiDataTemplet<Price> returnData = new LayuiDataTemplet<Price>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (price.getVersion() != null && !price.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(price.getVersion(), Config.VERSION);
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
		count = priceService.deleteByIdList(price.getIdlist());

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
