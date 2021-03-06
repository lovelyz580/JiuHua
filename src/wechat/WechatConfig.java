package wechat;

/**
 * 微信支付 
 * 
 * 全局设置
 * 
 * @author WJF on 2018/06/26
 */

public class WechatConfig {
	
	/**
	 * 字符编码
	 */
	public static final String CHARACTER_ENCODING = "UTF-8";
	
	/**
	 * 微信相关数据
	 */
//	public static final String WECHAT_APPID = ""; // 微信开发应用ID(AppID)
//	public static final String WECHAT_APPSECRET = "d"; // 微信开发应用秘钥(AppSecret)
	
	/**
	 * 微信相关URL
	 */
	public static final String WECHAT_GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token"; // 获取access_token的URL
	public static final String WECHAT_GET_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session"; // 获取openId的URL
	public static final String WECHAT_GET_EXTENTSION_QRCODE_URL = "https://api.weixin.qq.com/wxa/getwxacode"; // 获取推广二维码的URL
	
	/**
	 * 微信相关数据
	 */
	public static final String WECHAT_EXTENTSION_QRCODE_PATH = "D:\\wwwroot\\fhmpt.cn\\wwwroot\\sunweb\\resources\\extensionqrcode\\"; // 存放推广二维码图片的路径
	//public static final String WECHAT_EXTENTSION_QRCODE_PATH = "E:\\workspase\\.metadata\\.me_tcat7\\webapps\\sunweb\\resources\\extensionqrcode\\"; // 存放推广二维码图片的路径
	
	/**
	 * 微信支付相关数据
	 */
	public final static String WECHAT_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 微信支付URL
	public final static String WECHAT_PAY_IP = "211.149.250.146"; // 发起支付IP
	public final static String WECHAT_PAY_NOTIFY_URL = "https://www.fhmpt.cn/sunweb/wechatpay/notify"; // 微信支付回调接口

	/**
	 * 返回状态
	 */
	public final static String WECHAT_PAY_STATE_SUCCESS = "0"; // 成功
	public final static String WECHAT_PAY_STATE_ERROR = "1"; // 失败
	public final static String WECHAT_PAY_STATE_SYSTEM_ERROR = "-1"; // 系统错误
	
}
