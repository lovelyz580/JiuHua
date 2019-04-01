package util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 上传文件
 * 
 * @author ZY on 2018/09/22
 */

@Controller
@RequestMapping("/upload")
@MultipartConfig
public class Upload extends HttpServlet {

	/**
	 * 用户上传图片
	 * 
	 * @param file
	 * @param request
	 * @param stream
	 * @return
	 * @throws IOException
	 * 
	 * @author ZY on 2018/09/22
	 */
	@RequestMapping("/uploadImg")
	@ResponseBody
	public JSONObject uploadHeadImage(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request, InputStream stream) throws IOException {
		Assert.notNull(file, "上传文件不能为空");
		String src = "/resources/upload";
		
		// 获取真实路径
		String path = request.getServletContext().getRealPath("/" + src);
		String currentProjectName = request.getContextPath(); // 获取当前项目名称

		JSONObject json = new JSONObject();
		// System.currentTimeMillis() 根据系统时间产生随机数，保证上传的图片名字不一样
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String name = UUID.randomUUID().toString() + System.currentTimeMillis() + suffix;
		File dir = new File(path, name);
		src = currentProjectName + src + "/" + name;
		if (!dir.exists()) {
			dir.mkdirs();
			json.put("msg", "上传成功");
			json.put("code", 0);
			json.put("srcImg", src);
			json.put("name", name);
			json.put("path", path);
		} else {
			json.put("msg", "上传失败");
			json.put("code", 1);
		}
		file.transferTo(dir);
		
		return json;
	}

	/**
	 * 用户上传视频
	 * 
	 * @param file
	 * @param request
	 * @param stream
	 * @return
	 * @throws IOException
	 * 
	 * @author ZY on 2018/09/22
	 */
	@RequestMapping("/uploadVideo")
	@ResponseBody
	public JSONObject uploadHeadVideo(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request, InputStream stream) throws IOException {
		Assert.notNull(file, "上传文件不能为空");
		String src = "/resources/upload";
		
		// 获取真实路径
		String path = request.getServletContext().getRealPath("/" + src);
		String currentProjectName = request.getContextPath(); // 获取当前项目名称

		JSONObject json = new JSONObject();
		// System.currentTimeMillis() 根据系统时间产生随机数，保证上传的图片名字不一样
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String name = UUID.randomUUID().toString() + System.currentTimeMillis() + suffix;
		File dir = new File(path, name);
		src = currentProjectName + src + "/" + name;
		if (!dir.exists()) {
			dir.mkdirs();
			json.put("msg", "上传成功");
			json.put("code", 0);
			json.put("srcVideo", src);
		} else {
			json.put("msg", "上传失败");
			json.put("code", 1);
		}
		file.transferTo(dir);
		
		return json;
	}

}
