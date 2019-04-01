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
import sun.bz1.entity.Announcement;
import sun.bz1.entity.AnnouncementAndOrderTableAndBuildingType;
import sun.bz1.entity.SnatchAndOrderTableAndUser;
import sun.bz1.entity.UserServiceEntity;
import sun.bz1.service.AnnouncementService;
import sun.bz1.service.SnatchService;
import sun.bz1.service.UserServiceService;
import util.Config;
import util.VersionCompare;

/**
 * 维修公告表(发布公告时在该表中添加数据，公告结束时修改公告状态)
 * 
 * Restful
 * 
 * @author WJF on 2018/09/07
 */

@Controller
@RequestMapping("/announcement")
public class F_AnnouncementRestful {

	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private UserServiceService userServiceService;
	
	@Autowired
	private SnatchService snatchService;

	/**
	 * 根据Announcement实体添加
	 * 
	 * @param announcement
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/insertByAnnouncement")
	public LayuiDataTemplet<Announcement> insertByAnnouncement(@RequestBody Announcement announcement) {
		LayuiDataTemplet<Announcement> returnData = new LayuiDataTemplet<Announcement>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (announcement.getVersion() != null && !announcement.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(announcement.getVersion(), Config.VERSION);
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
		announcement.setAnnouncementid(Config.SIGN_ANNOUNCEMENT + UUID.randomUUID().toString()); // 维修公告ID(WXGG+UUID)
		announcement.setAnnouncementstate("Y"); // 维修公告状态(Y:进行中/N:已完成)
		
		// 添加
		int count = 0;
		count = announcementService.insertByAnnouncement(announcement);
		
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
	 * 根据Announcement实体更新
	 * 
	 * @param announcement
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/updateByAnnouncement")
	public LayuiDataTemplet<Announcement> updateByAnnouncement(@RequestBody Announcement announcement) {
		LayuiDataTemplet<Announcement> returnData = new LayuiDataTemplet<Announcement>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (announcement.getVersion() != null && !announcement.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(announcement.getVersion(), Config.VERSION);
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
		if (announcement.getId() == null || announcement.getId() == 0) {
			if (announcement.getAnnouncementid() == null || announcement.getAnnouncementid().isEmpty()) {
				if (announcement.getOrderid() == null || announcement.getOrderid().isEmpty()) {
					returnData.setMsg("缺少更新条件，更新失败！");
					return returnData;
				}
			}
		}
				
		// 更新数据
		
		// 更新
		int count = 0;
		count = announcementService.updateByAnnouncement(announcement);
		
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
	 * 根据AnnouncementAndOrderTableAndBuildingType实体联表查询
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
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesByUnionData")
	public LayuiDataTemplet<AnnouncementAndOrderTableAndBuildingType> selectThreeTablesByUnionData(@RequestBody AnnouncementAndOrderTableAndBuildingType unionData) {
		LayuiDataTemplet<AnnouncementAndOrderTableAndBuildingType> returnData = new LayuiDataTemplet<AnnouncementAndOrderTableAndBuildingType>(); // 返回数据
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
			count = announcementService.selectThreeTablesCountByUnionData(unionData); 

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<AnnouncementAndOrderTableAndBuildingType> announcementList = announcementService.selectThreeTablesByUnionData(unionData); // 查询数据
				
				// 查询抢单竞标人数
				// 查询维修单的竞标人数
				UserServiceEntity userServiceEntity = new UserServiceEntity();
				/*userServiceEntity.setUserservicerole("WX"); // 用户接单类别(WX:维修工/AZ:安装队)
				userServiceEntity.setUserservicestate("Y"); // 用户接单状态(Y:接单/N:不接单)
				int serviceCount = userServiceService.selectCountByUserService(userServiceEntity);*/
				
				/*// 查询安装单的竞标人数
				userServiceEntity = new UserServiceEntity();
				userServiceEntity.setUserservicerole("AZ"); // 用户接单类别(WX:维修工/AZ:安装队)
				userServiceEntity.setUserservicestate("Y"); // 用户接单状态(Y:接单/N:不接单)
				int installCount = userServiceService.selectCountByUserService(userServiceEntity);*/
				
				for (int i = 0; i < announcementList.size(); i++) {
					if ("WX".equals(announcementList.get(i).getOrderprojecttype())) {
						//查询一个维修单有多少人参与竞标
						SnatchAndOrderTableAndUser snatchAndOrderTableAndUser = new SnatchAndOrderTableAndUser();
						snatchAndOrderTableAndUser.setPagenumber(-1);//不分页
						snatchAndOrderTableAndUser.setOrderid(announcementList.get(i).getOrderid());//订单id
						int serviceCount = snatchService.selectThreeTablesCountByUnionData(snatchAndOrderTableAndUser);
						
						// 维修单的竞标人数
						announcementList.get(i).setSnatchcount(serviceCount);
					} else {
						//查询一个维修单有多少人参与竞标
						SnatchAndOrderTableAndUser snatchAndOrderTableAndUser = new SnatchAndOrderTableAndUser();
						snatchAndOrderTableAndUser.setPagenumber(-1);//不分页
						snatchAndOrderTableAndUser.setOrderid(announcementList.get(i).getOrderid());//订单id
						int installCount = snatchService.selectThreeTablesCountByUnionData(snatchAndOrderTableAndUser);
						
						// 安装单的竞标人数
						announcementList.get(i).setSnatchcount(installCount);
					}
					//查询该维修人员是否已经参与竞标  返回状态   YES   NO
					SnatchAndOrderTableAndUser snatchData = new SnatchAndOrderTableAndUser();
					snatchData.setPagenumber(-1);//不分页
					snatchData.setOrderid(announcementList.get(i).getOrderid());//订单ID
					snatchData.setServiceuserid(unionData.getCurrentuserid());//当前操作人，维修工人员
					List<SnatchAndOrderTableAndUser> snatchDataList = null;
					snatchDataList = snatchService.selectThreeTablesByUnionData(snatchData);
					if (snatchDataList.size() > 0 ){
						announcementList.get(i).setSnatchOrNo("YES");//返回状态已经参与竞标
					} else if (snatchDataList.size() == 0) {
						announcementList.get(i).setSnatchOrNo("NO");//未参与竞标
					}
					if (new Date().getTime() > announcementList.get(i).getOrderbiddingendtime().getTime()){
						//判断是否已过期
						announcementList.get(i).setSnatchOrNo("GQ");
					}
				}
				
				returnData.setData(announcementList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}
	
	/**
	 * 根据AnnouncementAndOrderTableAndBuildingType实体联表模糊查询
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
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/selectThreeTablesBySelectData")
	public LayuiDataTemplet<AnnouncementAndOrderTableAndBuildingType> selectThreeTablesBySelectData(@RequestBody AnnouncementAndOrderTableAndBuildingType unionData) {
		LayuiDataTemplet<AnnouncementAndOrderTableAndBuildingType> returnData = new LayuiDataTemplet<AnnouncementAndOrderTableAndBuildingType>(); // 返回数据
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
			count = announcementService.selectThreeTablesCountBySelectData(unionData); // 查询数量

			// 返回数据
			if (count == 0) {
				returnData.setMsg("暂无数据！");
			} else {
				returnData.setCount(count);
				returnData.setMsg("查询成功！");
				List<AnnouncementAndOrderTableAndBuildingType> announcementList = announcementService.selectThreeTablesBySelectData(unionData); // 查询数据

				// 查询抢单竞标人数
				// 查询维修单的竞标人数
				UserServiceEntity userServiceEntity = new UserServiceEntity();
				/*userServiceEntity.setUserservicerole("WX"); // 用户接单类别(WX:维修工/AZ:安装队)
				userServiceEntity.setUserservicestate("Y"); // 用户接单状态(Y:接单/N:不接单)
				int serviceCount = userServiceService.selectCountByUserService(userServiceEntity);*/

				/*// 查询安装单的竞标人数
				userServiceEntity = new UserServiceEntity();
				userServiceEntity.setUserservicerole("AZ"); // 用户接单类别(WX:维修工/AZ:安装队)
				userServiceEntity.setUserservicestate("Y"); // 用户接单状态(Y:接单/N:不接单)
				int installCount = userServiceService.selectCountByUserService(userServiceEntity);*/

				for (int i = 0; i < announcementList.size(); i++) {
					if ("WX".equals(announcementList.get(i).getOrderprojecttype())) {
						//查询一个维修单有多少人参与竞标
						SnatchAndOrderTableAndUser snatchAndOrderTableAndUser = new SnatchAndOrderTableAndUser();
						snatchAndOrderTableAndUser.setPagenumber(-1);//不分页
						snatchAndOrderTableAndUser.setOrderid(announcementList.get(i).getOrderid());//订单id
						int serviceCount = snatchService.selectThreeTablesCountByUnionData(snatchAndOrderTableAndUser);

						// 维修单的竞标人数
						announcementList.get(i).setSnatchcount(serviceCount);
					} else {
						//查询一个维修单有多少人参与竞标
						SnatchAndOrderTableAndUser snatchAndOrderTableAndUser = new SnatchAndOrderTableAndUser();
						snatchAndOrderTableAndUser.setPagenumber(-1);//不分页
						snatchAndOrderTableAndUser.setOrderid(announcementList.get(i).getOrderid());//订单id
						int installCount = snatchService.selectThreeTablesCountByUnionData(snatchAndOrderTableAndUser);

						// 安装单的竞标人数
						announcementList.get(i).setSnatchcount(installCount);
					}
					//查询该维修人员是否已经参与竞标  返回状态   YES   NO
					SnatchAndOrderTableAndUser snatchData = new SnatchAndOrderTableAndUser();
					snatchData.setPagenumber(-1);//不分页
					snatchData.setOrderid(announcementList.get(i).getOrderid());//订单ID
					snatchData.setServiceuserid(unionData.getCurrentuserid());//当前操作人，维修工人员
					List<SnatchAndOrderTableAndUser> snatchDataList = null;
					snatchDataList = snatchService.selectThreeTablesByUnionData(snatchData);
					if (snatchDataList.size() > 0 ){
						announcementList.get(i).setSnatchOrNo("YES");//返回状态已经参与竞标
					} else if (snatchDataList.size() == 0) {
						announcementList.get(i).setSnatchOrNo("NO");//未参与竞标
					}
					if (new Date().getTime() > announcementList.get(i).getOrderbiddingendtime().getTime()){
						//判断是否已过期
						announcementList.get(i).setSnatchOrNo("GQ");
					}
				}

				returnData.setData(announcementList);
			}
		} else {
			returnData.setMsg("传递的分页数据(页数)错误！");
		}

		return returnData;
	}

	/**
	 * 根据idList删除信息
   	 * 
	 * @param announcement
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/deleteByIdList")
	public LayuiDataTemplet<Announcement> deleteByIdList(@RequestBody Announcement announcement) {
		LayuiDataTemplet<Announcement> returnData = new LayuiDataTemplet<Announcement>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (announcement.getVersion() != null && !announcement.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(announcement.getVersion(), Config.VERSION);
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
		count = announcementService.deleteByIdList(announcement.getIdlist());

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
	 * 根据 维修单ID List 删除信息
   	 * 
	 * @param announcement
	 * @return
	 * 
	 * @author WJF on 2018/09/07
	 */
	@ResponseBody
	@RequestMapping("/deleteByOrderIdList")
	public LayuiDataTemplet<Announcement> deleteByOrderIdList(@RequestBody Announcement announcement) {
		LayuiDataTemplet<Announcement> returnData = new LayuiDataTemplet<Announcement>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 版本号不为空，则验证版本号
		try {
			if (announcement.getVersion() != null && !announcement.getVersion().isEmpty()) {
				// 前者大则返回一个正数，后者大返回一个负数，相等则返回0
				int compare = VersionCompare.compare(announcement.getVersion(), Config.VERSION);
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
		count = announcementService.deleteByOrderIdList(announcement.getUuidlist());

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
