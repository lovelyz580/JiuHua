package wechat;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

/**
 * 微信相关类
 * 
 * 获取推广二维码
 * 
 * 小程序
 * 
 * @author WJF on 2018/10/12
 */

public class ExtensionQRCode {

	private static Logger logger = Logger.getLogger(ExtensionQRCode.class);

	/**
	 * 获取推广二维码
	 * 
	 * @param accessToken 微信access_token
	 * @param userId 推广人id
	 * @return
	 */
	public static String getExtensionQRCode(String accessToken, String userId) {
		String path = null;
		
		try {
			// 获取推广二维码的URL
			URL url = new URL(WechatConfig.WECHAT_GET_EXTENTSION_QRCODE_URL + "?access_token=" + accessToken);
			
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			// POST请求必须设置如下两行数据
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			
			// 发送请求参数
			JSONObject paramJson = new JSONObject();
			paramJson.put("path", "pages/register/register?userid=" + userId);
			paramJson.put("width", 430);
			//paramJson.put("scene", userId);
			// 输出
			logger.error("ExtensionQRCode:paramJson:=============================" + paramJson);
			System.out.println("ExtensionQRCode:paramJson:=============================" + paramJson);
					
			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			// 获取URLConnection对象对应的输出流
			printWriter.write(paramJson.toString());
			printWriter.flush(); // flush输出流的缓冲
			// 输出
			logger.error("ExtensionQRCode:printWriter:=============================" + printWriter);
			System.out.println("ExtensionQRCode:printWriter:=============================" + printWriter);
			
			// 开始获取数据
			BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			
			// buff用于存放循环读取的临时数据
			byte[] buff = new byte[100];
			int rc = 0;
			while ((rc = bis.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			
			// 获取图片、裁剪原图
			ByteArrayInputStream inputStream = new ByteArrayInputStream(swapStream.toByteArray());
			BufferedImage image = ImageIO.read(inputStream);
			BufferedImage subImage = image.getSubimage(0, 0, image.getWidth(), (int) (image.getHeight() * 1)); // 裁剪原图  微信返回的是470*535 像素的图片
			BufferedImage inputbig = new BufferedImage(256, 256, BufferedImage.TYPE_INT_BGR);
			Graphics2D g = (Graphics2D) inputbig.getGraphics();
			g.drawImage(subImage, 0, 0, 256, 256, null); // 画图
			g.dispose();
			inputbig.flush();
			
			// 存放图片
			path = WechatConfig.WECHAT_EXTENTSION_QRCODE_PATH + userId + ".jpg"; // 服务器地址
			File file = new File(path);
			ImageIO.write(inputbig, "jpg", file);
//			
//			FileImageInputStream input = new FileImageInputStream(file);
//			ByteArrayOutputStream output = new ByteArrayOutputStream();
//			
//			byte[] buf = new byte[1024];
//			int numBytesRead = 0;
//			while ((numBytesRead = input.read(buf)) != -1) {
//				output.write(buf, 0, numBytesRead);
//			}
//			
//			byte[] QRCode = output.toByteArray(); // 二维码图片字节型数组
//			
//			// 关闭
//			output.close();
//			input.close();
			
			// 关闭
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 返回数据
		return path;
	}

}
