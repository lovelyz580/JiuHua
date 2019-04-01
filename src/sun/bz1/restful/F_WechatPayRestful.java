package sun.bz1.restful;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.LayuiDataTemplet;
import sun.bz1.entity.*;
import sun.bz1.service.*;
import util.Config;
import wechat.WechatConfig;
import wechat.pay.PayToolUtils;
import wechat.utils.HttpPayUtils;
import wechat.utils.XMLUtil4jdom;

/**
 * 微信支付
 * 
 * Controller
 * 
 * @author WJF on 2018/10/13
 */

@Controller
@RequestMapping("/wechatpay")
public class F_WechatPayRestful {

	@Autowired
	private WechatService wechatService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private OrderTableService orderTableService;

    @Autowired
    private UserPaymentService userPaymentService;

	@Autowired
	private ApplyCheckService applyCheckService;

	public static Logger logger = Logger.getLogger(F_WechatPayRestful.class);

	/**
	 * 微信支付
	 * 
	 * 公众号支付时，返回预支付交易会话标识 
	 * 扫码支付时，返回二维码链接
	 * 
	 * @param userId 根据userId获取对应的openId，当trade_type为JSAPI时(即公众号支付)，openId参数必传
	 * @param tradeType 交易类型 JSAPI:公众号支付(小程序)  NATIVE:扫码支付  APP:APP支付
	 * @param money 金额 double类型，两位小数，例如：1.00
	 * @return
	 * 
	 * @author WJF on 2018/10/13
	 */
	@ResponseBody
	@RequestMapping("/getPrepayId")
	public LayuiDataTemplet<User> getPrepayId(String userId,String orderId, String tradeType, Double money) {
		LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null
		
		try {
			String message = null;
			
			// 根据userId获取对应的openId
			String openId = null;
			
			// 查询的实体
			UserAndUserServiceAndUserCustomer unionData = new UserAndUserServiceAndUserCustomer();
			unionData.setUserid(userId); // 用户ID(YHBG+UUID)
			unionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
			
			// 查询
			List<UserAndUserServiceAndUserCustomer> userList = userService.selectThreeTablesBySelectData(unionData);
			
			if (userList == null || userList.size() == 0) {
	     		returnData.setMsg("openId获取失败，prepay_id获取失败！");
	     	} else {
	     		openId = userList.get(0).getUseropenid();
	     		
	     		if (openId == null || openId.isEmpty()) {
	     			returnData.setMsg("该用户openId为空，prepay_id获取失败！");
	     		} else {
	     			// 获取微信相关数据
	    			// 查询的实体
	    			Wechat wechat = new Wechat(); 
	    						
	    			// 查询
	    			List<Wechat> wechatList = wechatService.selectByWechat(wechat); 
	    			
	    			if (wechatList != null && wechatList.size() != 0) {
	    				wechat = wechatList.get(0);
	    				
	    				// 随机字符串
	    				String currTime = PayToolUtils.getCurrTime();
	    				String timeStr = currTime.substring(8, currTime.length());
	    				String randomStr = PayToolUtils.buildRandom(4) + "";
	    				String nonce_str = timeStr + randomStr;

	    				// 商品名称
	    				String goodName = "用户充值";
	    				
	    				// 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
	    				// 这里放入的是业务中的用户Id和金额
	    				String attach = userId + ","  + orderId + "," + money;
	    				
	    				// 商户订单号
	    				String out_trade_no = "" + System.currentTimeMillis();

	    				// 商品价格
	    				// 保留2位小数，四舍五入，并将double转化为Stirng
	    				String goodMoneyStr = String.valueOf(Math.round(money * 100));
	    				
	    				// 为微信支付添加参数
	    				SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
	    				packageParams.put("appid", wechat.getAppid()); // 公众账号ID
	    				packageParams.put("mch_id", wechat.getMchid()); // 商户号
	    				packageParams.put("nonce_str", nonce_str); // 随机字符串
	    				packageParams.put("body", goodName); // 商品描述
	    				packageParams.put("attach", attach); // 附加数据，非必填字段
	    				packageParams.put("out_trade_no", out_trade_no); // 商户订单号
	    				packageParams.put("total_fee", goodMoneyStr); // 价格的单位为分
	    				packageParams.put("spbill_create_ip", WechatConfig.WECHAT_PAY_IP); // 终端IP
	    				packageParams.put("notify_url", WechatConfig.WECHAT_PAY_NOTIFY_URL); // 微信支付回调接口
	    				packageParams.put("trade_type", tradeType); // 交易类型 JSAPI:公众号支付  NATIVE:扫码支付  APP:APP支付
	    				packageParams.put("sign_type", "MD5"); // 签名类型，默认为MD5
	    				if ("JSAPI".equals(tradeType)) {
	    					packageParams.put("openid", openId); // 用户标识，当交易类型为JSAPI时(即公众号支付)，此参数必传
	    				}
	    				// 签名
	    				// 如果参数的值为空不参与签名
	    				String sign = PayToolUtils.createSign("UTF-8", packageParams, wechat.getApi());
	    				packageParams.put("sign", sign); // 签名

	    				// 将SortedMap转换为xml类型的String
	    				String requestXML = PayToolUtils.getRequestXml(packageParams);
	    				// 输出
	    				logger.error("WechatPayRestful:支付-requestXML:=============================" + requestXML);
	    				System.out.println("WechatPayRestful:支付-requestXML:=============================" + requestXML);

	    				// 返回信息
	    				String responseXml = HttpPayUtils.postData(WechatConfig.WECHAT_PAY_URL, requestXML);
	    				// 输出
	    				logger.error("WechatPayRestful:支付-responseXml:=============================" + responseXml);
	    				System.out.println("WechatPayRestful:支付-responseXml:=============================" + responseXml);

	    				// 获取数据
	    				if ("JSAPI".equals(tradeType)) {
	    					// 公众号支付时，获取返回的预支付交易会话标识
	    					Map map = XMLUtil4jdom.doXMLParse(responseXml);
	    					message = (String) map.get("prepay_id");
	    					// 输出
	    					logger.error("WechatPayRestful:支付-responseXml:prepay_id:=============================" + returnData);
	    					System.out.println("WechatPayRestful:支付-responseXml:prepay_id:=============================" + returnData);
	    				} else if ("NATIVE".equals(tradeType)) {
	    					// 扫码支付时，获取返回的二维码链接
	    					Map map = XMLUtil4jdom.doXMLParse(responseXml);
	    					message = (String) map.get("code_url");
	    					// 输出
	    					logger.error("WechatPayRestful:支付-responseXml:code_url:=============================" + returnData);
	    					System.out.println("WechatPayRestful:支付-responseXml:code_url:=============================" + returnData);
	    				}
	    			}
	    			
	    			// 返回数据
	    			if (message == null || message.isEmpty()) {
	    		        returnData.setMsg("prepay_id获取失败！");
	    			} else {
	    				returnData.setCount(1);
	    		        returnData.setMsg(message);
	    			}
	     		}
	     	}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnData;
	}
	
	/**
	 * 微信支付
	 * 
	 * @param prepayId 预支付交易会话标识 
	 * @return
	 * 
	 * @author WJF on 2018/10/13
	 */
	@ResponseBody
	@RequestMapping("/pay")
	public LayuiDataTemplet<User> pay(String prepayId) {
		LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
		returnData.setCode(0); // 默认为0
		returnData.setCount(0); // 数据的数量，默认为0
		returnData.setData(null); // 数据List，默认为null

		// 在微信浏览器里面打开H5网页中执行JS调起支付
		// 公众号支付时，根据返回的预支付交易会话标识，获取签名
		String message = null;

		try {
			// 获取微信相关数据
			// 查询的实体
			Wechat wechat = new Wechat(); 
			
			// 查询
			List<Wechat> wechatList = wechatService.selectByWechat(wechat); 
			
			if (wechatList == null || wechatList.size() == 0) {
	     		returnData.setMsg("openId获取失败，微信支付失败！");
	     	} else {
				wechat = wechatList.get(0);

				// 随机字符串
				String currTime = PayToolUtils.getCurrTime();
				String timeStr = currTime.substring(8, currTime.length());
				String randomStr = PayToolUtils.buildRandom(4) + "";
				String nonce_str = timeStr + randomStr;

				// 为微信支付添加参数
				SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
				packageParams.put("appId", wechat.getAppid()); // 公众账号ID
				packageParams.put("timeStamp", "1414587457"); // 时间戳
				packageParams.put("nonceStr", nonce_str); // 随机字符串
				packageParams.put("package", "prepay_id=" + prepayId); // 公众号支付时，获取返回的预支付交易会话标识
				packageParams.put("signType", "MD5"); // 签名类型，默认为MD5
				// 签名
				// 如果参数的值为空不参与签名
				String sign = PayToolUtils.createSign("UTF-8", packageParams, wechat.getApi());
				packageParams.put("paySign", sign); // 签名

				// 返回数据
				message = "appId=" + wechat.getAppid() + "&nonceStr=" + nonce_str + "&paySign=" + sign;
			}

			// 返回数据
			if (message == null || message.isEmpty()) {
				returnData.setMsg("微信支付失败！");
			} else {
				returnData.setCount(1);
				returnData.setMsg(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnData;
	}

	/**
	 * 微信支付回调接口
	 * 
	 * 扫码支付回调接口
	 * 
	 * 业务逻辑：
	 * 1、更新用户的余额    usierid    金额
	 * 2、根据用户身份，添加不同的收入金额表
	 * 
	 * @param request
	 * @param response
	 * @throws JDOMException
	 * @throws Exception
	 * 
	 * @author WJF on 2018/10/13
	 */
	@ResponseBody
	@RequestMapping("/notify")
	public String notify(HttpServletRequest request, HttpServletResponse response) throws JDOMException, Exception {
		// 输出
		logger.error("WechatPayRestful:支付回调=============================进入回调接口");
		System.out.println("WechatPayRestful:支付回调=============================进入回调接口");

		// 读取参数
		StringBuffer sb = new StringBuffer();
		String s;
		InputStream inputStream = request.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		// 解析xml成map
		Map<String, String> map = new HashMap<String, String>();
		map = XMLUtil4jdom.doXMLParse(sb.toString());

		// 过滤空数据
		// 设置TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = map.get(parameter);
			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}
		// 输出
		logger.error("WechatPayRestful:支付回调-packageParams:=============================" + packageParams);
		System.out.println("WechatPayRestful:支付回调-packageParams:=============================" + packageParams);

		// 获取附加数据中的业务中的商户Id和订单Id
		String attach = (String) packageParams.get("attach");
        logger.error("WechatPayRestful:支付回调-业务中的attach:=============================" + attach);
		/*int front = attach.indexOf(","); // 获取分隔符的位置
		String userId = attach.substring(0, front);
        String orderId = attach.substring(front + 1, front + 2);
		String moneyStr = attach.substring(front + 2, attach.length());
		double money = Double.valueOf(moneyStr);*/
        String[] strs=attach.split(",");
        String userId = strs[0].toString();
        String orderId = strs[1].toString();
        String moneyStr = strs[2].toString();
        double money = Double.valueOf(moneyStr);
		// 输出
        logger.error("WechatPayRestful:支付回调-业务中的attach:=============================" + attach);
		logger.error("WechatPayRestful:支付回调-业务中的用户Id:=============================" + userId);
		logger.error("WechatPayRestful:支付回调-业务中的金额:=============================" + money);
        logger.error("WechatPayRestful:支付回调-业务中的订单id:=============================" + orderId);
		System.out.println("WechatPayRestful:支付回调-业务中的用户Id:=============================" + userId);
		System.out.println("WechatPayRestful:支付回调-业务中的金额:=============================" + money);
        System.out.println("WechatPayRestful:支付回调-业务中的订单id:=============================" + orderId);

		// 获取微信相关数据
		// 查询的实体
		Wechat wechat = new Wechat(); 
								
		// 查询
		List<Wechat> wechatList = wechatService.selectByWechat(wechat); 
					
		if (wechatList != null && wechatList.size() != 0) {
			wechat = wechatList.get(0);

			// 根据公众账号ID，判断签名是否正确
			if (PayToolUtils.isTenpaySign("UTF-8", packageParams, wechat.getApi())) {
				// 输出
				logger.error("WechatPayRestful:支付回调=============================签名验证正确，处理业务开始");
				System.out.println("WechatPayRestful:支付回调=============================签名验证正确，处理业务开始");

				// 处理业务开始
				String responseXml = ""; // 通知微信接收回调成功
				if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
					// 输出
					logger.error("WechatPayRestful:支付回调=============================支付成功");
					System.out.println("WechatPayRestful:支付回调=============================支付成功");

					// 支付成功
					// 通知微信，异步确认成功
					// 必写，不然会一直通知后台，八次之后就认为交易失败
					responseXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

					// 业务逻辑

                    if (null != orderId && !orderId.equals("")){
                        OrderTable orderTable = new OrderTable();
                        orderTable.setPagenumber(-1);//不分页
                        orderTable.setOrderid(orderId);//订单编号
                        List<OrderTable> orderTableList = null;
                        orderTableList = orderTableService.selectByOrderTable(orderTable);
                        if (orderTableList.size() == 0) {
                            logger.error("orderTableList:没有该订单=============================no this orderId");
                            return "没有找到该订单!";
                        }
                        //针对人付款人和订单编号查询客户是否已付款
                        UserPayment userPayment1 = new UserPayment();
                        userPayment1.setPagenumber(-1);//不分页
                        userPayment1.setOrderid(orderId);//订单编号
                        userPayment1.setRemitteruserid(orderTableList.get(0).getOrdercreateuserid());//付款人
                        List<UserPayment> userPaymentList = null;
                        userPaymentList = userPaymentService.selectByUserPayment(userPayment1);
                        if (userPaymentList.size() > 0) {
                            logger.error("userPaymentList:您已完成付款操作，请等待平台人员确认，请勿重复点击！=============================userPaymentList have data");
                            return "您已完成付款操作，请等待平台人员确认，请勿重复点击！";
                        }

                        //查询多次付款应付维修工多少钱
						ApplyCheckAndOrderTableAndUser applyCheckAndOrderTableAndUser = new ApplyCheckAndOrderTableAndUser();
						applyCheckAndOrderTableAndUser.setPagenumber(-1);//不分页
						applyCheckAndOrderTableAndUser.setOrderid(orderId);
						List<ApplyCheckAndOrderTableAndUser> applyCheckList = applyCheckService.selectThreeTablesByUnionDataByTimeDesc(applyCheckAndOrderTableAndUser); // 查询数据
                        //更新订单表状态
                        OrderTable orderTable1 = new OrderTable();
                        orderTable1.setOrderid(orderId);//订单编号
                        orderTable1.setOrderstate("YFK");//更新状态已付款
                        orderTable1.setOrderupdatetime(new Date());//更新时间
                        // 添加付款记录数据
                        UserPayment userPayment = new UserPayment();
                        userPayment.setUserpaymentid(Config.SIGN_USERPAYMENT + UUID.randomUUID().toString()); // 用户付款ID(YHFK+UUID)
                        userPayment.setOrderid(orderId);//订单编号
                        userPayment.setOrderprojectname(orderTableList.get(0).getOrderprojectname());//项目名称
                        userPayment.setOrderpricetotalmoney(money);//付款金额
                        userPayment.setRemitteruserid(orderTableList.get(0).getOrdercreateuserid()); // 付款人ID
                        userPayment.setUserpaymentcreateuserid(orderTableList.get(0).getOrdercreateuserid()); // 创建人ID
                        userPayment.setUserpaymentcreatetime(new Date()); // 用户付款创建时间
                        userPayment.setUserpaymentupdateuserid(orderTableList.get(0).getOrdercreateuserid());//用户付款更新人
                        userPayment.setUserpaymentupdatetime(new Date());//更新时间
                        userPayment.setUserpaymentother1("XSFK");// 线下付款(对公账户)：DGZH,线上付款：XSFK
						userPayment.setUserpaymentother2(applyCheckList.get(0).getApplycheckmoney());//应付给维修工的钱
						userPayment.setUserpaymentplatformaccount("微信支付");//平台账户
						userPayment.setUserpaymentplatformaccountname("河北九安防火门制造集团有限公司");//平台账户名称
						userPayment.setUserpaymentserviceaccount("微信支付");//平台账户开户行地址
                        //线上付款增加用户付款记录
                        //添加用户_客户_收入_金额表
                        UserCustomerIncomeMoney customerIncomeMoney = new UserCustomerIncomeMoney();
                        // 数据
                        customerIncomeMoney.setUsercustomerincomemoneyid(Config.SIGN_USERCUSTOMERINCOMEMONEY + UUID.randomUUID().toString()); // 客户收入金额ID(KHSR+UUID)
                        customerIncomeMoney.setUsercustomerincomemoneyuserid(orderTableList.get(0).getOrdercreateuserid()); // 客户ID(YHBG+UUID)
                        customerIncomeMoney.setOrderid(orderId); // 维修单ID(D+年月日+时分秒+6位随机数)
                        customerIncomeMoney.setUsercustomerincomemoneysource("XSFK"); // 金额来源(WXTH:维修人员退回(客户收入金额)/QRQXKH:确认取消(客户收入金额)/GHWX:更换维修人员(客户收入金额)/WTPD:委托平台派单(客户收入金额)/PTXTKH:平台协调(客户收入金额)/KHCZ:客户充值(客户收入金额))
                        customerIncomeMoney.setUsercustomerincomemoney(-money); // 金额(可正可负)
                        customerIncomeMoney.setUsercustomerincomemoneycreatetime(new Date()); // 金额创建时间*/

                        // 添加平台_收益金额表
                        BackMoney backMoney = new BackMoney();
                        // 数据
                        backMoney.setBackmoneyid(Config.SIGN_BACKMONEY + UUID.randomUUID().toString()); // 金额变化ID(PTJE+UUID)
                        backMoney.setOrderid(orderTable.getOrderid()); // 维修单ID(D+年月日+时分秒+6位随机数)
                        backMoney.setBackmoneyother1("XSFK"); // 金额来源(PTPD:平台派单(按维修推荐总价+维修差旅费推荐总价收入金额)/WXTH:维修人员退回(支出金额给客户)/QRQXKH:确认取消(支出金额给客户)/QRQXWX:确认取消(支出金额给维修人员)/YSHG:验收合格(按维修总价+维修差旅费总价支出金额给维修人员)/GHWX:更换维修人员(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/WTPD:委托平台派单(按维修推荐总价+维修差旅费推荐总价支出金额给客户)/PTXTWX:平台协调(支出金额给维修人员)/PTXTKH:平台协调(支出金额给客户)/DGZH:线下对公账户))
						backMoney.setBackmoneyother2(userPayment.getUserpaymentid());//与用户付款表建立关系
                        backMoney.setBackmoney(money);//平台收入 可正可负
                        backMoney.setBackmoneycreatetime(new Date()); // 金额创建时间

                        // 更新、添加
                        int num = wechatService.notifyAndInsert(orderTable1, userPayment, customerIncomeMoney, backMoney);
                        if (num == 0) {
                            return "添加付款记录数据失败！";
                        } else {
                            return "添加付款记录数据成功！";
                        }



                    } else {
					// 更新用户的余额
					// 查询的实体
					UserAndUserServiceAndUserCustomer unionData = new UserAndUserServiceAndUserCustomer();
					unionData.setUserid(userId); // 用户ID(YHBG+UUID)
					unionData.setPagenumber(-1); // 当前页数(如果不进行分页，该条数据默认为-1)
					
					// 查询
					List<UserAndUserServiceAndUserCustomer> unionDataList = userService.selectThreeTablesBySelectData(unionData);
					
					if (unionDataList != null  && unionDataList.size() != 0) {
						unionData = unionDataList.get(0);
						
						// 更新的实体
						User updateUser = new User();
						updateUser.setUserid(userId); // 用户ID(YHBG+UUID)
						updateUser.setUserrole(unionData.getUserrole()); // 用户身份类别(KH:客户/WX:维修工/AZ:安装队)(会有多个，以逗号分隔，以逗号结尾)
						updateUser.setUsermoney(unionData.getUsermoney() + money); // 用户余额
						 
						// 根据用户身份，添加不同的收入金额表
						UserCustomerIncomeMoney customerIncomeMoney = null;
						UserServiceIncomeMoney serviceIncomeMoney = null;
						if ("KH,".equals(unionData.getUserrole())) {
							// 添加用户_客户_收入_金额表
							customerIncomeMoney = new UserCustomerIncomeMoney(); 
							customerIncomeMoney.setUsercustomerincomemoneyid(Config.SIGN_USERCUSTOMERINCOMEMONEY + UUID.randomUUID().toString()); // 客户收入金额ID(KHSR+UUID)
							customerIncomeMoney.setUsercustomerincomemoneyuserid(userId); // 金额用户ID(YHBG+UUID)
                            customerIncomeMoney.setOrderid("KHCZ"); // 订单编号 充值时没有。
							customerIncomeMoney.setUsercustomerincomemoney(money); // 金额(可正可负)
							customerIncomeMoney.setUsercustomerincomemoneysource("KHCZ"); // 金额来源(WXTH:维修人员退回(客户收入金额)/QRQXKH:确认取消(客户收入金额)/GHWX:更换维修人员(客户收入金额)/WTPD:委托平台派单(客户收入金额)/PTXTKH:平台协调(客户收入金额)/KHCZ:客户充值(客户收入金额))
							customerIncomeMoney.setUsercustomerincomemoneycreatetime(new Date()); // 金额创建时间
						} else {
							// 添加用户_维修工、安装队_收入_金额表
							serviceIncomeMoney = new UserServiceIncomeMoney();
							serviceIncomeMoney.setUserserviceincomemoneyid(Config.SIGN_USERSERVICEINCOMEMONEY + UUID.randomUUID().toString()); // 客户收入金额ID(KHSR+UUID)
							serviceIncomeMoney.setUserserviceincomemoneyuserid(userId); // 金额用户ID(YHBG+UUID)
                            serviceIncomeMoney.setOrderid("WXCZ"); // 订单编号 充值时没有。
							serviceIncomeMoney.setUserserviceincomemoney(money); // 金额(可正可负)
							serviceIncomeMoney.setUserserviceincomemoneysource("WXCZ"); // 金额来源(QRQXWX:确认取消(维修人员收入金额)/YSHG:验收合格(维修人员收入金额)/PTXTWX:平台协调(维修人员收入金额)/WXCZ:维修人员充值(维修人员收入金额))
							serviceIncomeMoney.setUserserviceincomemoneycreatetime(new Date()); // 金额创建时间
						}
						
						// 更新、添加
						int num = wechatService.notify(updateUser, customerIncomeMoney, serviceIncomeMoney);
						
						// TODO 跳转页面
						if (num > 0) {
//							return "yh-wechat-pay-success.html";
						}
			     	}
                    }
				} else {
					// 输出
					logger.error("WechatPayRestful:支付回调=============================支付失败");
					System.out.println("WechatPayRestful:支付回调=============================支付失败");

					responseXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
				}

				// 通知微信接收回调成功
				BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
				out.write(responseXml.getBytes());
				out.flush();
				out.close();

				// 处理业务完毕
				// 输出
				logger.error("WechatPayRestful:支付回调=============================签名验证正确，处理业务结束");
				System.out.println("WechatPayRestful:支付回调=============================签名验证正确，处理业务结束");
			} else {
				// 输出
				logger.error("WechatPayRestful:支付回调=============================签名验证失败");
				System.out.println("WechatPayRestful:支付回调=============================签名验证失败");
			}
		}

		// TODO 跳转页面
//		return "yh-wechat-pay-fail.html";
		
		return null;
	}

}
