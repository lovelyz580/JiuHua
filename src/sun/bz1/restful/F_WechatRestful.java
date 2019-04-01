package sun.bz1.restful;

import java.util.Date;
import java.util.List;

import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.User;
import sun.bz1.entity.UserAndUserServiceAndUserCustomer;
import sun.bz1.entity.Wechat;
import sun.bz1.service.UserService;
import sun.bz1.service.WechatService;
import wechat.AccessToken;
import wechat.ExtensionQRCode;
import wechat.OpenId;
import wechat.WechatConfig;

/**
 * 微信
 * 
 * Restful
 * 
 * @author WJF on 2018/09/25
 */

@Controller
@RequestMapping("/wechat")
public class F_WechatRestful {
	
	@Autowired
	private WechatService wechatService;
	
	@Autowired
	private UserService userService;
	
	private static Logger logger = Logger.getLogger(F_WechatRestful.class);
	
	/**
	 * 获取openId
	 * 
	 * @param code
	 * @return
	 * 
	 * @author WJF on 2018/09/25
	 */
	@ResponseBody
	@RequestMapping("/getOpenId")
	public LayuiDataTemplet<User> getOpenId(String code) {
		LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		try {
			if (code == null || code.isEmpty()) {
				returnData.setMsg("code值为空！");
				return returnData;
			}
			
			// 输出
	        logger.error("WechatRestful:queryOpenId:code:=============================" + code);
	        System.out.println("WechatRestful:queryOpenId:code:=============================" + code);
					
	        String openId; // 返回数据
	        
	        // 获取微信相关数据
	        // 查询的实体
	     	Wechat wechat = new Wechat();
	     	
	     	// 查询
	     	List<Wechat> wechatList = wechatService.selectByWechat(wechat); 
	     	
	     	if (wechatList == null || wechatList.size() == 0) {
	     		returnData.setMsg("openId获取失败！");
	     	} else {
	     		// 获取数据
	     		wechat = wechatList.get(0);
	     		String AppID = wechat.getAppid();
	     		String AppSecret = wechat.getAppsecret();
	     					
				// 获取openId
				openId = OpenId.getOpenId(AppID, AppSecret, code).getOpenid();
	
				// 返回数据
				if (openId == null || TextUtils.isEmpty(openId)) {
					// 输出
			        logger.error("WechatRestful:queryOpenId:openId:=============================openId获取失败");
			        System.out.println("WechatRestful:queryOpenId:openId:=============================openId获取失败");
				
			        returnData.setMsg("openId获取失败！");
				} else {
					// 输出
			        logger.error("WechatRestful:queryOpenId:openId:=============================" + openId);
			        System.out.println("WechatRestful:queryOpenId:openId:=============================" + openId);
				
			        returnData.setCount(1);
			        returnData.setMsg(openId);
				}
	     	}
		} catch (Exception e) {
			e.printStackTrace();
			
			returnData.setMsg("openId获取失败！");
		}

		return returnData;
	}
	
	/**
	 * 获取access_token
	 * 
	 * @param request
	 * @return
	 * 
	 * @author WJF on 2018/10/12
	 */
	@ResponseBody
	@RequestMapping("/getAccessToken")
	public LayuiDataTemplet<User> getAccessToken() {
		LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 获取access_token
		String access_token = getAccessToken(wechatService); 

		// 返回数据
		if (access_token == null || TextUtils.isEmpty(access_token)) {
			returnData.setMsg("access_token获取失败！");
		} else {
			returnData.setCount(1);
			returnData.setMsg(access_token);
		}

		return returnData;
	}
	
	/**
	 * 获取access_token
	 * 
	 * @param wechatService
	 * @param merchantId
	 * @return
	 * 
	 * @author WJF on 2018/10/12
	 */
	public static String getAccessToken(WechatService wechatService) {
		try {
			// 获取微信相关数据
			// 查询的实体
			Wechat wechat = new Wechat();
	
			// 查询
			List<Wechat> wechatList = wechatService.selectByWechat(wechat);
	
			// 返回数据
			if (wechatList == null || wechatList.size() == 0) {
				return null;
			} else {
				if (wechatList.get(0).getAccesstoken() == null || TextUtils.isEmpty(wechatList.get(0).getAccesstoken())) {
					// 输出
			        logger.error("WechatRestful:getAccessToken:=============================数据库中没有accesss_token信息，重新获取");
			        System.out.println("WechatRestful:getAccessToken:=============================数据库中没有accesss_token信息，重新获取");
					
					// 数据库中没有accesss_token信息
					// 获取accesss_token
					wechat = wechatList.get(0);
					String AppID = wechat.getAppid();
					String AppSecret = wechat.getAppsecret();
					
					// 获取
					String access_token = AccessToken.getAccessToken(AppID, AppSecret).getAccess_token();
					// 输出
			        logger.error("WechatRestful:getAccessToken:=============================" + access_token);
			        System.out.println("WechatRestful:getAccessToken:=============================" + access_token);
			        
					if (access_token != null && !TextUtils.isEmpty(access_token)) {
						// 成功获取accesss_token后，更新数据库
						wechat.setAccesstoken(access_token);
						wechat.setTokenbuildtime(new Date());
						
						// 更新
						wechatService.updateByWechat(wechat); 
	
						// 返回accesss_token
						return access_token;
					}
				} else {
					// 输出
			        logger.error("WechatRestful:getAccessToken:=============================数据库中有accesss_token信息");
			        System.out.println("WechatRestful:getAccessToken:=============================数据库中有accesss_token信息");
			        
					if (new Date().getTime() - wechatList.get(0).getTokenbuildtime().getTime() > 1.5 * 60 * 60 * 1000) {
						// 输出
				        logger.error("WechatRestful:getAccessToken:=============================数据库中有accesss_token信息，但超过1.5个小时，重新获取");
				        System.out.println("WechatRestful:getAccessToken:=============================数据库中有accesss_token信息，但超过1.5个小时，重新获取");
						
						// 数据库中有accesss_token信息，但已经超过有效时间
						// 获取accesss_token
						wechat = wechatList.get(0);
						String AppID = wechat.getAppid();
						String AppSecret = wechat.getAppsecret();
						
						// 获取
						String access_token = AccessToken.getAccessToken(AppID, AppSecret).getAccess_token();
						// 输出
				        logger.error("WechatRestful:getAccessToken:=============================" + access_token);
				        System.out.println("WechatRestful:getAccessToken:=============================" + access_token);
				        
						if (access_token != null && !TextUtils.isEmpty(access_token)) {
							// 成功获取accesss_token后，更新数据库
							wechat.setAccesstoken(access_token);
							wechat.setTokenbuildtime(new Date());
							
							// 更新
							wechatService.updateByWechat(wechat);
	
							// 返回accesss_token
							return access_token;
						}
					} else {
						// 输出
				        logger.error("WechatRestful:getAccessToken:=============================数据库中有accesss_token信息，并且没有超过1.5个小时");
				        System.out.println("WechatRestful:getAccessToken:=============================数据库中有accesss_token信息，并且没有超过1.5个小时");
				        logger.error("WechatRestful:getAccessToken:=============================" + wechatList.get(0).getAccesstoken());
				        System.out.println("WechatRestful:getAccessToken:=============================" + wechatList.get(0).getAccesstoken());
				        
						// 数据库中有accesss_token信息，并且没有已经超过有效时间
						return wechatList.get(0).getAccesstoken();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 默认返回null
		return null;
	}
	
	/**
	 * 获取推广二维码
	 * 
	 * @param request
	 * @return
	 * 
	 * @author WJF on 2018/10/12
	 */
	@ResponseBody
	@RequestMapping("/getExtensionQRCode")
	public LayuiDataTemplet<User> getExtensionQRCode(String userId) {
		LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null
		
//		userId = "YHBG74d621db-c90d-4514-ab5d-fca612801201";
		
		// 更新判断
		if (userId == null || userId.isEmpty()) {
			returnData.setMsg("缺少获取条件，推广二维码获取失败！");
			return returnData;
		}
		
		// 查询该用户是否已获取过推广二维码图片
		UserAndUserServiceAndUserCustomer unionData = new UserAndUserServiceAndUserCustomer();
		unionData.setUserid(userId); // 用户ID(YHBG+UUID)
		unionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
		List<UserAndUserServiceAndUserCustomer> unionDataList = userService.selectThreeTablesByUnionData(unionData); // 查询

		if (unionDataList == null || unionDataList.size() == 0) {
			returnData.setMsg("该用户不存在，推广二维码获取失败！");
			return returnData;
		}

		if (unionDataList.get(0).getUserremark() != null && !unionDataList.get(0).getUserremark().isEmpty()) {
			// 如果已获取过就直接返回保存的图片路径
			returnData.setCount(1);
			returnData.setMsg(unionDataList.get(0).getUserremark());
		} else {
			// 如果没有获取过就获取推广二维码图片
			// 获取access_token
			String access_token = getAccessToken(wechatService); 
			
			// 获取推广二维码
			String path = ExtensionQRCode.getExtensionQRCode(access_token, userId);
			path = "/sunweb/resources/extensionqrcode/" + userId + ".jpg";

			// 返回数据
			if (path == null || path.isEmpty()) {
				returnData.setMsg("推广二维码获取失败！");
			} else {
				// 更新用户推广二维码图片路径
				User user = new User();
				user.setUserid(userId); // 用户ID(YHBG+UUID)
				user.setUserremark(path); // 用户备注(用户推广二维码图片路径)
				
				userService.updateByUser(user); // 更新
				
				// 返回数据
				returnData.setCount(1);
				returnData.setMsg(path);
			}
		}

		return returnData;
	}
	
}
