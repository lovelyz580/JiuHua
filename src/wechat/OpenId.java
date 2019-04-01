package wechat;

import java.util.TreeMap;

import org.apache.log4j.Logger;

import wechat.entity.OpenIdEntity;
import wechat.utils.HttpUtils;
import wechat.utils.JsonUtils;

/**
 * openId
 * 
 * @author WJF on 2018/06/26
 */

public class OpenId {

	private static Logger logger = Logger.getLogger(OpenId.class);

	/**
	 * 获取openId
	 * 
	 * @param AppID 微信开发应用ID
	 * @param AppSecret 微信开发应用秘钥
	 * @param code 
	 * @return
	 */
	public static OpenIdEntity getOpenId(String AppID, String AppSecret, String code) {
		// SortedMap方式
// 		// 参数
//		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
//		packageParams.put("appid", AppID);
//		packageParams.put("secret", AppSecret);
//		packageParams.put("code", code);
//		packageParams.put("grant_type", "authorization_code");
//
//		// 将SortedMap转换为xml类型的String
//		String requestXML = PayToolUtils.getRequestXml(packageParams);
//		// 输出
//		logger.error("OpenId:requestXML:=============================" + requestXML);
//		System.out.println("OpenId:requestXML:=============================" + requestXML);
//
//		// 获取openId
//		String responseXml = HttpUtils.getData(WechatConfig.WECHAT_GET_OPENID_URL, requestXML);
//		// 输出
//		logger.error("OpenId:responseXml:=============================" + responseXml);
//		System.out.println("OpenId:responseXml:=============================" + responseXml);
//
//		// 将JSON类型的数据转换为实体类
//		OpenIdEntity openId = JsonUtils.fromJson(responseXml, OpenIdEntity.class);
// 
//		// 返回数据
//		return openId == null ? null : openId;
				
		// TreeMap方式
		// 参数
		TreeMap<String, String> requestMap = new TreeMap<String, String>();
		requestMap.put("appid", AppID);
		requestMap.put("secret", AppSecret);
		requestMap.put("js_code", code);
		requestMap.put("grant_type", "authorization_code");
		// 输出
		logger.error("OpenId:requestMap:=============================" + requestMap);
		System.out.println("OpenId:requestMap:=============================" + requestMap);
		
		String responseXml = HttpUtils.HttpsDefaultExecute("GET", WechatConfig.WECHAT_GET_OPENID_URL, requestMap, null);
		// 输出
		logger.error("OpenId:responseXml:=============================" + responseXml);
		System.out.println("OpenId:responseXml:=============================" + responseXml);

		// 将JSON类型的数据转换为实体类
		OpenIdEntity openId = JsonUtils.fromJson(responseXml, OpenIdEntity.class);
		
		// 返回数据
		return openId == null ? null : openId;
	}

}
