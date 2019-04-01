package sun.bz1.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.LayuiDataTemplet;
import sun.bz1.entity.News;
import sun.bz1.entity.OrderBreakCard;
import sun.bz1.entity.OrderBreakCardAndOrderTableAndUser;
import sun.bz1.service.NewsService;
import sun.bz1.service.OrderBreakCardService;
import util.Config;
import util.VersionCompare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 订单打卡表
 *
 * Restful
 *
 * @author ZY on 2018/12/15
 */

@Controller
@RequestMapping("/orderBreakCard")
public class F_OrderBreakCardRestful {

	@Autowired
	private OrderBreakCardService orderBreakCardService;

	/**
	 * 根据OrderBreakCard实体添加
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/insertByOrderBreakCard")
	public LayuiDataTemplet<OrderBreakCard> insertByOrderBreakCard(@RequestBody OrderBreakCard orderBreakCard) {
		LayuiDataTemplet<OrderBreakCard> returnData = new LayuiDataTemplet<OrderBreakCard>();
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不能为空，验证版本号
		try {
			if (null != orderBreakCard.getVersion() && !orderBreakCard.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderBreakCard.getVersion(), Config.VERSION);
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
		orderBreakCard.setOrderbreakcardid(Config.SIGN_ORDERBREAKCARD + UUID.randomUUID().toString()); // 维修价格ID(JFBG+UUID)
		orderBreakCard.setOrderbreakcardcreatetime(new Date()); // 创建时间

		// 添加
		int count = 0;
		count = orderBreakCardService.insertByOrderBreakCard(orderBreakCard);

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
	 * 根据OrderBreakCard实体更新
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/updateByOrderBreakCard")
	public LayuiDataTemplet<OrderBreakCard> updateByOrderBreakCard(@RequestBody OrderBreakCard orderBreakCard) {
		LayuiDataTemplet<OrderBreakCard> returnData = new LayuiDataTemplet<OrderBreakCard>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderBreakCard.getVersion() != null && !orderBreakCard.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderBreakCard.getVersion(), Config.VERSION);
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
		if (orderBreakCard.getId() == null || orderBreakCard.getId() == 0) {
			if (orderBreakCard.getOrderbreakcardid() == null || orderBreakCard.getOrderbreakcardid().isEmpty()) {
				returnData.setMsg("缺少更新条件，更新失败！");
				return returnData;
			}
		}

		// 更新数据

		// 更新
		int count = 0;
		count = orderBreakCardService.updateByOrderBreakCard(orderBreakCard);

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
	 * 根据OrderBreakCard实体联表查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/selectByOrderBreakCard")
	public LayuiDataTemplet<OrderBreakCard> selectByOrderBreakCard(@RequestBody OrderBreakCard orderBreakCard) {
		LayuiDataTemplet<OrderBreakCard> returnData = new LayuiDataTemplet<OrderBreakCard>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderBreakCard.getVersion() != null && !orderBreakCard.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderBreakCard.getVersion(), Config.VERSION);
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
		if (orderBreakCard.getPagenumber() != null) {
			// 计算偏移量
			if (orderBreakCard.getPagenumber() != -1) {
				if (orderBreakCard.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = orderBreakCard.getPagenumber();
				int pageSize = orderBreakCard.getPagesize();
				orderBreakCard.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				orderBreakCard.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = orderBreakCardService.selectCountByOrderBreakCard(orderBreakCard);

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderBreakCard> orderBreakCardList = orderBreakCardService.selectByOrderBreakCard(orderBreakCard); // 查询数据
				returnData.setData(orderBreakCardList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据OrderBreakCard实体联表模糊查询
	 * 
	 * 可以进行分页查询
	 * 
	 * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
	 * 
	 * pageSize 每页的数据量
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/selectBySelectData")
	public LayuiDataTemplet<OrderBreakCard> selectBySelectData(@RequestBody OrderBreakCard orderBreakCard) {
		LayuiDataTemplet<OrderBreakCard> returnData = new LayuiDataTemplet<OrderBreakCard>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderBreakCard.getVersion() != null && !orderBreakCard.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderBreakCard.getVersion(), Config.VERSION);
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
		if (orderBreakCard.getPagenumber() != null) {
			// 计算偏移量
			if (orderBreakCard.getPagenumber() != -1) {
				if (orderBreakCard.getPagesize() == null) {
					returnData.setMsg("传递的分页数据(每页数量)错误！");
					return returnData;
				}

				// 获取传递过来的数据
				int pageNumber = orderBreakCard.getPagenumber();
				int pageSize = orderBreakCard.getPagesize();
				orderBreakCard.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
				orderBreakCard.setPagesize(pageSize); // 每页的数据量
			}

			// 查询数量
			int count = 0;
			count = orderBreakCardService.selectCountBySelectData(orderBreakCard); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderBreakCard> orderBreakCardList = orderBreakCardService.selectBySelectData(orderBreakCard); // 查询数据
				returnData.setData(orderBreakCardList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据OrderBreakCardAndOrderTableAndUser实体联表模糊查询
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
	 * @author ZY on 2018/12/18
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesBySelectData")
	public LayuiDataTemplet<OrderBreakCardAndOrderTableAndUser> selectThreeTablesBySelectData(@RequestBody OrderBreakCardAndOrderTableAndUser unionData) {
		LayuiDataTemplet<OrderBreakCardAndOrderTableAndUser> returnData = new LayuiDataTemplet<OrderBreakCardAndOrderTableAndUser>(); // 返回数据
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
			count = orderBreakCardService.selectThreeTablesCountBySelectData(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<OrderBreakCardAndOrderTableAndUser> orderBreakCardList = orderBreakCardService.selectThreeTablesBySelectData(unionData); // 查询数据
				returnData.setData(orderBreakCardList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
	 * 
	 * @param orderBreakCard
	 * @return
	 * 
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<OrderBreakCard> deleteByIdList(@RequestBody OrderBreakCard orderBreakCard) {
		LayuiDataTemplet<OrderBreakCard> returnData = new LayuiDataTemplet<OrderBreakCard>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (orderBreakCard.getVersion() != null && !orderBreakCard.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderBreakCard.getVersion(), Config.VERSION);
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
		count = orderBreakCardService.deleteByIdList(orderBreakCard.getIdlist());

		// 返回数据
		if (count == 0) {
			returnData.setMsg("删除失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("删除成功！");
		}

		return returnData;
	}


	/**
	 * 根据OrderBreakCard实体添加（小程序中打卡记录）
	 *
	 * @param orderBreakCard
	 * @return
	 *
	 * @author ZY on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/insertByOrderBreakCardWechat")
	public LayuiDataTemplet<OrderBreakCard> insertByOrderBreakCardWechat(@RequestBody OrderBreakCard orderBreakCard) throws Exception {
		LayuiDataTemplet<OrderBreakCard> returnData = new LayuiDataTemplet<OrderBreakCard>();
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不能为空，验证版本号
		try {
			if (null != orderBreakCard.getVersion() && !orderBreakCard.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(orderBreakCard.getVersion(), Config.VERSION);
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
		orderBreakCard.setOrderbreakcardid(Config.SIGN_ORDERBREAKCARD + UUID.randomUUID().toString()); // 维修价格ID(JFBG+UUID)
		orderBreakCard.setOrderbreakcardcreatetime(new Date()); // 创建时间

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");

		OrderBreakCard selectorBreakCard = new OrderBreakCard();
		selectorBreakCard.setPagenumber(-1);//不分页
		selectorBreakCard.setOrderid(orderBreakCard.getOrderid());//订单id
		selectorBreakCard.setOrderbreakcarduserid(orderBreakCard.getOrderbreakcarduserid());//打卡人
		selectorBreakCard.setBreakcardtime(sdf.format(new Date()));//通过这个查询当天打卡数量
		List<OrderBreakCard> orderBreakCardList = new ArrayList<OrderBreakCard>();
		orderBreakCardList = orderBreakCardService.selectByOrderBreakCard(selectorBreakCard);
		if (orderBreakCardList.size() >= 2) {
			returnData.setMsg("今日已打卡两次，不能使用打卡功能！");
			return  returnData;
		} else if (orderBreakCardList.size() == 1 ){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date sourcedate = format.parse(format.format(orderBreakCard.getOrderbreakcardcreatetime()));//现在的时间
			Date tragetdate = format.parse("12:00");//中午12点的时间
			int ret = sourcedate.compareTo(tragetdate);
			if (ret <= 0){
				returnData.setMsg("今日早上已打卡，中午之前不能使用打卡功能！");
				return  returnData;
			}
			Date selectdate = format.parse(format.format(orderBreakCardList.get(0).getOrderbreakcardcreatetime()));//查询出来的打卡时间
			int pmret = selectdate.compareTo(tragetdate);//对比是不是下午
			if (pmret == 1) {
				returnData.setMsg("今日下午已打卡，今天不能再使用打卡功能！");
				return  returnData;
			}
		}

		// 添加
		int count = 0;
		count = orderBreakCardService.insertByOrderBreakCard(orderBreakCard);

		// 返回数据
		if (count == 0) {
			returnData.setMsg("添加失败！");
		} else {
			returnData.setCount(count);
			returnData.setMsg("添加成功！");
		}

		return returnData;
	}

}
