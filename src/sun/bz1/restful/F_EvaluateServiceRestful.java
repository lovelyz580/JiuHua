package sun.bz1.restful;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.*;
import sun.bz1.service.*;
import util.Config;
import util.VersionCompare;
import util.WechatFormUtil;

/**
 * 客户评价维修人员表
 * 
 * Restful
 * 
 * @author WJF on 2018/09/05
 */

@Controller
@RequestMapping("/evaluate_service")
public class F_EvaluateServiceRestful {

	@Autowired
	private EvaluateServiceService evaluateService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CreditChangeService creditChangeService;

	@Autowired
	private WechatFormService wechatFormService;

	@Autowired
	private WechatService wechatService;

	@Autowired
	private OrderTableService orderTableService;

	private Logger logger = Logger.getLogger(F_OperationOrderRestful.class);

	/**
	 * 根据EvaluateService实体添加
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/insertByEvaluateService")
	public LayuiDataTemplet<EvaluateService> insertByEvaluateService(@RequestBody EvaluateService evaluate) {
		LayuiDataTemplet<EvaluateService> returnData = new LayuiDataTemplet<EvaluateService>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (evaluate.getVersion() != null && !evaluate.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(evaluate.getVersion(), Config.VERSION);
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

		// 被评价的维修人员ID
		if (evaluate.getServiceuserid() == null || evaluate.getServiceuserid().isEmpty()) {
			returnData.setMsg("被评价的维修人员ID为空，无法评价！");
			return returnData;
		}
				
		// 添加客户评价维修人员表
		// 数据
		evaluate.setEvaluateserviceid(Config.SIGN_EVALUATESESRVICE + UUID.randomUUID().toString()); // 评价ID(PJWX+UUID)
		evaluate.setEvaluateservicecreatetime(new Date()); // 评价创建时间
				
		// 更新用户表(维修人员信用值)
		User serviceUser = new User();
		// 查询
		UserAndUserServiceAndUserCustomer selectServiceUser = new UserAndUserServiceAndUserCustomer();
		selectServiceUser.setUserid(evaluate.getServiceuserid()); // 维修人员ID(YHBG+UUID)
		selectServiceUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> serviceUserList = null;
		serviceUserList = userService.selectThreeTablesByUnionData(selectServiceUser); // 查询
		if (serviceUserList == null || serviceUserList.size() == 0) {
			returnData.setMsg("找不到该维修人员，无法确认取消！");
			return returnData;
		} else {
			// 数据
			serviceUser.setUserid(serviceUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			serviceUser.setUsercredit(serviceUserList.get(0).getUsercredit() + evaluate.getEvaluateservicescore()); // 信用值
			serviceUser.setUserupdateuserid(evaluate.getCustomeruserid()); // 当前操作人员(客户)ID // 用户更新人ID(YHBG+UUID)
			serviceUser.setUserupdatetime(new Date()); // 用户更新时间
		}
						
		// 添加信用值变化表
		// 数据
		CreditChange creditChange = new CreditChange();
		creditChange.setCreditchangeid(Config.SIGN_CREDITCHANGE + UUID.randomUUID().toString()); // 信用值变化ID(XYBH+UUID)
		creditChange.setUserid(serviceUser.getUserid()); // 维修人员ID(YHBG+UUID)
		creditChange.setOrderid(evaluate.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		creditChange.setEvaluatid(evaluate.getEvaluateserviceid()); // 评价ID(PJWX+UUID)
		creditChange.setCreditchangetype("PLWX"); // 信用值变化类型信用值变化类型(CSZ:初始值/WXTHDD:维修人员退回订单/KHQXQR:客户取消订单/KHYSHG:客户验收合格/KHGHWX:客户更换维修人员/KHWTPD:客户委托平台派单/PLWX:客户评论维修人员/PLKH:维修人员评论客户)
		creditChange.setCreditchange(evaluate.getEvaluateservicescore()); // 信用值变化值
		creditChange.setCreditchangecreatetime(new Date()); // 信用值变化创建时间
				
		// 添加
		int count = 0;
		count = evaluateService.insertByEvaluateService(evaluate, serviceUser, creditChange);
		
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
	 * 根据EvaluateService实体更新
	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/05
	 */
	@ResponseBody
	@RequestMapping("/updateByEvaluateService")
	public LayuiDataTemplet<EvaluateService> updateByEvaluateService(@RequestBody EvaluateService evaluate) {
		LayuiDataTemplet<EvaluateService> returnData = new LayuiDataTemplet<EvaluateService>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (evaluate.getVersion() != null && !evaluate.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(evaluate.getVersion(), Config.VERSION);
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
		if (evaluate.getId() == null || evaluate.getId() == 0) {
			if (evaluate.getEvaluateserviceid() == null || evaluate.getEvaluateserviceid().isEmpty()) {
				if (evaluate.getOrderid() == null || evaluate.getOrderid().isEmpty()) {
					returnData.setMsg("缺少更新条件，更新失败！");
					return returnData;
				}
			}
		}

		// 被评价的维修人员ID
		if (evaluate.getServiceuserid() == null || evaluate.getServiceuserid().isEmpty()) {
			returnData.setMsg("被评价的维修人员ID为空，无法评价！");
			return returnData;
		}

		// 更新客户评价维修人员表
		// 更新数据
				
		// 更新用户表(维修人员信用值)
		User serviceUser = new User();
		// 查询
		UserAndUserServiceAndUserCustomer selectServiceUser = new UserAndUserServiceAndUserCustomer();
		selectServiceUser.setUserid(evaluate.getServiceuserid()); // 维修人员ID(YHBG+UUID)
		selectServiceUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> customerUserList = null;
		customerUserList = userService.selectThreeTablesByUnionData(selectServiceUser); // 查询
		if (customerUserList == null || customerUserList.size() == 0) {
			returnData.setMsg("找不到该维修人员，无法确认取消！");
			return returnData;
		} else {
			// 数据
			serviceUser.setUserid(customerUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
			serviceUser.setUsercredit(customerUserList.get(0).getUsercredit()); // 信用值
			serviceUser.setUserupdateuserid(evaluate.getCustomeruserid()); // 当前操作人员(客户)ID // 用户更新人ID(YHBG+UUID)
			serviceUser.setUserupdatetime(new Date()); // 用户更新时间
		}
						
		// 查询之前添加的信用值变化表，并更新
		CreditChange creditChange = new CreditChange();
		// 查询
		CreditChange selectCreditChange = new CreditChange();
		selectCreditChange.setEvaluatid(evaluate.getEvaluateserviceid()); // 维修人员ID(YHBG+UUID)
		selectCreditChange.setOrderid(evaluate.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
		selectCreditChange.setCreditchangetype("PLWX"); // 信用值变化类型(CSZ:初始值/WXTHDD:维修人员退回订单/KHQXQR:客户取消订单/KHYSHG:客户验收合格/KHGHWX:客户更换维修人员/KHWTPD:客户委托平台派单/PLWX:客户评论维修人员/PLKH:维修人员评论客户)
		selectCreditChange.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<CreditChange> creditChangeList = null;
		creditChangeList = creditChangeService.selectByCreditChange(selectCreditChange);
		if (creditChangeList != null && creditChangeList.size() != 0) {
			// 获取数据
			creditChange = creditChangeList.get(0);
					
			// 客户信用值 = 信用值 - 原信用值 + 新信用值
			serviceUser.setUsercredit(serviceUser.getUsercredit() - creditChange.getCreditchange() + evaluate.getEvaluateservicescore()); 
			
			// 数据
			creditChange.setCreditchange(evaluate.getEvaluateservicescore()); // 信用值变化值
		}
				
		// 更新
		int count = 0;
		count = evaluateService.updateByEvaluateService(evaluate, serviceUser, creditChange);
		
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
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表查询
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
	@RequestMapping("/selectThreeTablesByUnionData")
	public LayuiDataTemplet<EvaluateServiceAndOrderTableAndUser> selectThreeTablesByUnionData(@RequestBody EvaluateServiceAndOrderTableAndUser unionData) {
		LayuiDataTemplet<EvaluateServiceAndOrderTableAndUser> returnData = new LayuiDataTemplet<EvaluateServiceAndOrderTableAndUser>(); // 返回数据
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
			count = evaluateService.selectThreeTablesCountByUnionData(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<EvaluateServiceAndOrderTableAndUser> evaluateList = evaluateService.selectThreeTablesByUnionData(unionData); // 查询数据
				returnData.setData(evaluateList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据EvaluateServiceAndOrderTableAndUser实体联表模糊查询
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
	@RequestMapping("/selectThreeTablesBySelectData")
	public LayuiDataTemplet<EvaluateServiceAndOrderTableAndUser> selectThreeTablesBySelectData(@RequestBody EvaluateServiceAndOrderTableAndUser unionData) {
		LayuiDataTemplet<EvaluateServiceAndOrderTableAndUser> returnData = new LayuiDataTemplet<EvaluateServiceAndOrderTableAndUser>(); // 返回数据
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
			count = evaluateService.selectThreeTablesCountBySelectData(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<EvaluateServiceAndOrderTableAndUser> evaluateList = evaluateService.selectThreeTablesBySelectData(unionData); // 查询数据
				returnData.setData(evaluateList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

//	/**
//	 * 根据idList删除信息
//   	 * 
//	 * @param evaluate
//	 * @return
//	 * 
//	 * @author WJF on 2018/09/05
//	 */
//	@ResponseBody
//	@RequestMapping("/deleteByIdList")
//	public LayuiDataTemplet<EvaluateService> deleteByIdList(@RequestBody EvaluateService evaluate) {
//		LayuiDataTemplet<EvaluateService> returnData = new LayuiDataTemplet<EvaluateService>(); // 返回数据
//		returnData.setCode(0); // 默认为0
//		returnData.setCount(0); // 数据的数量，默认为0
//		returnData.setData(null); // 数据List，默认为null
//
//		// 版本号不为空，则验证版本号
//		try {
//			if (evaluate.getVersion() != null && !evaluate.getVersion().isEmpty()) {
//				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
//				int compare = VersionCompare.compare(evaluate.getVersion(), Config.VERSION);
//				if (compare < 0) {
//					returnData.setMsg("版本较低，请更新版本！");
//					return returnData;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//			returnData.setMsg("版本比较发生异常！");
//			return returnData;
//		}
//
//		// 删除
//		int count = 0;
//		count = evaluateService.deleteByIdList(evaluate.getIdlist());
//
//		// 返回数据
//		if (count == 0) {
//			returnData.setMsg("删除失败！");
//		} else {
//			returnData.setCount(count);
//			returnData.setMsg("删除成功！");
//		}
//
//		return returnData;
//	}
	
	/**
	 * 根据UUIDList删除信息
   	 * 
	 * @param evaluate
	 * @return
	 * 
	 * @author WJF on 2018/09/19
	 */
	@ResponseBody
	@RequestMapping("/deleteByUUIDList")
	public LayuiDataTemplet<EvaluateService> deleteByUUIDList(@RequestBody EvaluateService evaluate) {
		LayuiDataTemplet<EvaluateService> returnData = new LayuiDataTemplet<EvaluateService>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (evaluate.getVersion() != null && !evaluate.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(evaluate.getVersion(), Config.VERSION);
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
		count = evaluateService.deleteByUUIDList(evaluate.getUuidlist());

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
     * 根据EvaluateService实体添加
     *
	 * 更新订单表状态
	 *
     * @param evaluate
     * @return
     *
     * @author ZY on 2018/10/23
     */
    @ResponseBody
    @RequestMapping("/insertByEvaluateServiceUpdateOrderState")
    public LayuiDataTemplet<EvaluateService> insertByEvaluateServiceUpdateOrderState(@RequestBody EvaluateService evaluate) {
        LayuiDataTemplet<EvaluateService> returnData = new LayuiDataTemplet<EvaluateService>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null

        // 版本号不为空，则验证版本号
        try {
            if (evaluate.getVersion() != null && !evaluate.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(evaluate.getVersion(), Config.VERSION);
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

        // 被评价的维修人员ID
        if (evaluate.getServiceuserid() == null || evaluate.getServiceuserid().isEmpty()) {
            returnData.setMsg("被评价的维修人员ID为空，无法评价！");
            return returnData;
        }

        // 添加客户评价维修人员表
        // 数据
        evaluate.setEvaluateserviceid(Config.SIGN_EVALUATESESRVICE + UUID.randomUUID().toString()); // 评价ID(PJWX+UUID)
        evaluate.setEvaluateservicecreatetime(new Date()); // 评价创建时间

		//添加客户评价平台_维修人员表
		EvaluatePlatformService evaluatePlatformService = new EvaluatePlatformService();
		evaluatePlatformService.setEvaluateplatformserviceid(evaluate.getEvaluateserviceid());
		evaluatePlatformService.setServiceuserid(evaluate.getServiceuserid());//维修人员（被评价人ID）
		evaluatePlatformService.setCustomeruserid(evaluate.getCustomeruserid());//客户ID
		evaluatePlatformService.setOrderid(evaluate.getOrderid());//订单ID
		evaluatePlatformService.setEvaluateplatformservicescore(evaluate.getEvaluateservicescore());//总得分
		evaluatePlatformService.setEvaluateplatformserviceopinion(evaluate.getEvaluateserviceopinion());//评论内容
		evaluatePlatformService.setEvaluateplatformserviceremark(evaluate.getEvaluateserviceremark());//评论备注
		evaluatePlatformService.setEvaluateplatformservicesetupid(evaluate.getEvaluateservicesetupid());//评价项ID(WPJX+UUID)多个用,隔开
		evaluatePlatformService.setEvaluateplatformservicesetupname(evaluate.getEvaluateservicesetupname());//评价项名称多个用,隔开
		evaluatePlatformService.setEvaluateplatformservicesetupscore(evaluate.getEvaluateservicesetupscore());//得分用于展示实体星星多个用,隔开
		evaluatePlatformService.setEvaluateplatformservicecreatetime(evaluate.getEvaluateservicecreatetime());//创建时间

		//查询客户是否针对该订单已经评论过维修人员
		/*EvaluateServiceAndOrderTableAndUser evaluate1 = new EvaluateServiceAndOrderTableAndUser();
		evaluate1.setPagenumber(-1);//不分页
		evaluate1.setOrderid(evaluate.getOrderid());//订单编号
		List<EvaluateServiceAndOrderTableAndUser> list = null;
		list = evaluateService.selectThreeTablesByUnionData(evaluate1);
		if (list.size() > 0) {
			returnData.setMsg("您已经评价，请勿重复提交！");
			return returnData;
		}*/

        // 更新用户表(维修人员信用值)
        User serviceUser = new User();
        // 查询
        UserAndUserServiceAndUserCustomer selectServiceUser = new UserAndUserServiceAndUserCustomer();
        selectServiceUser.setUserid(evaluate.getServiceuserid()); // 维修人员ID(YHBG+UUID)
        selectServiceUser.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
        List<UserAndUserServiceAndUserCustomer> serviceUserList = null;
        serviceUserList = userService.selectThreeTablesByUnionData(selectServiceUser); // 查询
        if (serviceUserList == null || serviceUserList.size() == 0) {
            returnData.setMsg("找不到该维修人员，无法确认取消！");
            return returnData;
        } else {
            // 数据
            serviceUser.setUserid(serviceUserList.get(0).getUserid()); // 用户ID(YHBG+UUID)
            serviceUser.setUsercredit(serviceUserList.get(0).getUsercredit() + evaluate.getEvaluateservicescore()); // 信用值
            serviceUser.setUserupdateuserid(evaluate.getCustomeruserid()); // 当前操作人员(客户)ID // 用户更新人ID(YHBG+UUID)
            serviceUser.setUserupdatetime(new Date()); // 用户更新时间
        }

        // 添加信用值变化表
        // 数据
        CreditChange creditChange = new CreditChange();
        creditChange.setCreditchangeid(Config.SIGN_CREDITCHANGE + UUID.randomUUID().toString()); // 信用值变化ID(XYBH+UUID)
        creditChange.setUserid(serviceUser.getUserid()); // 维修人员ID(YHBG+UUID)
        creditChange.setOrderid(evaluate.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
        creditChange.setEvaluatid(evaluate.getEvaluateserviceid()); // 评价ID(PJWX+UUID)
        creditChange.setCreditchangetype("PLWX"); // 信用值变化类型信用值变化类型(CSZ:初始值/WXTHDD:维修人员退回订单/KHQXQR:客户取消订单/KHYSHG:客户验收合格/KHGHWX:客户更换维修人员/KHWTPD:客户委托平台派单/PLWX:客户评论维修人员/PLKH:维修人员评论客户)
        creditChange.setCreditchange(evaluate.getEvaluateservicescore()); // 信用值变化值
        creditChange.setCreditchangecreatetime(new Date()); // 信用值变化创建时间

        OrderTable orderTable = new OrderTable();
        orderTable.setOrderid(evaluate.getOrderid());//订单编号
        orderTable.setOrderstate("KHYPJ");//修改订单状态
        // 添加
        int count = 0;
        count = evaluateService.insertByEvaluateServiceUpdateOrderState(evaluate,evaluatePlatformService, serviceUser, creditChange,orderTable);

        // 返回数据
        if (count == 0) {
            returnData.setMsg("添加失败！");
        } else {

			OrderTable selectOrderTable = new OrderTable();
			selectOrderTable.setOrderid(evaluate.getOrderid());
			selectOrderTable.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<OrderTable> orderList = null;
			orderList = orderTableService.selectByOrderTable(selectOrderTable);
			//通知维修人员客户已评价的服务通知
			// 查询数据
			UserAndUserServiceAndUserCustomer selectDataPay = new UserAndUserServiceAndUserCustomer();
			selectDataPay.setUserid(orderList.get(0).getOrderserviceuserid()); // 查询
			selectDataPay.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)

			// 用户评价维修时查询用户的数据数据
			UserAndUserServiceAndUserCustomer selectDataCustomeruserId = new UserAndUserServiceAndUserCustomer();
			selectDataCustomeruserId.setUserid(evaluate.getCustomeruserid()); // 查询
			selectDataCustomeruserId.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			List<UserAndUserServiceAndUserCustomer> customerUserList = userService.selectThreeTablesBySelectData(selectDataCustomeruserId);

			// 查询数据
			List<UserAndUserServiceAndUserCustomer> userPayList = userService.selectThreeTablesBySelectData(selectDataPay);
			if (userPayList.size() != 0){
				for (int i = 0; i < userPayList.size(); i ++){
					WechatForm wechatForm = new WechatForm();
					wechatForm.setPagenumber(-1);//不分页
					wechatForm.setUserid(userPayList.get(i).getUserid());
					List<WechatForm> wechatFormList = wechatFormService.selectByWechatForm(wechatForm); // 查询数据
					if (wechatFormList.size() != 0) {
						String openId = "";
						String templateId = "";
						String formId = "";
						for (int j = 0; j < wechatFormList.size(); j ++) {
							Date createTime = wechatFormList.get(j).getCreatedate();
							Date nowTime = new Date();
							long cha = nowTime.getTime() - createTime.getTime();
							if (cha / (1000 * 60 * 60 * 24) > 6){
								List<Integer> deleteList = new ArrayList<Integer>();
								deleteList.add(wechatFormList.get(j).getId());
								wechatFormList.remove(j);
								wechatFormService.deleteByIdList(deleteList);
							}
						}
						openId = wechatFormList.get(0).getOpenid();
						templateId = "2raCYGVRR9xf_65McOO7-KDXmX92bCOEhtA2sb3P_vw";
						formId = wechatFormList.get(0).getFormid();

						SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String token = F_WechatRestful.getAccessToken(wechatService);
						//String token = "15_UmIZU3I9v8a7awi1Wd-fhTNzaRP2qFujMRMtDgCmoZgxDDxMghRXg7GH57Fr_k28RCbJI5tznJO6tvH927dCRYIoARmaDBx4ZOjCQsZrjI59QfDHZ6YiYCeusDFYTirha23vtI-SnvFptzvTOHNiAIAZSY";
						String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId, "", "#ff6600", customerUserList.get(0).getUserrealname(), orderList.get(0).getOrderprojectname(), "总得分：" + evaluate.getEvaluateservicescore(), evaluate.getEvaluateserviceopinion());
						boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
						List<Integer> idList = new ArrayList<Integer>();
						idList.add(wechatFormList.get(0).getId());
						wechatFormService.deleteByIdList(idList);

						logger.error("服务通知result" + result);
						System.out.println("服务通知result" + result);

					}
				}
			}
            returnData.setCount(count);
            returnData.setMsg("添加成功！");
        }

        return returnData;
    }
	
}
