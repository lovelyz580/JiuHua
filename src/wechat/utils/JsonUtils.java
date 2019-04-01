package wechat.utils;

import com.google.gson.Gson;

/**
 * 微信工具类
 * 
 * JSON工具
 * 
 * @author WJF on 2018/06/27
 */

public class JsonUtils {

	/**
	 * 将实体类转换为JSON类型的数据
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		Gson gson = new Gson();
		String result = gson.toJson(object);
		gson = null;
		return result;
	}

	/**
	 * 将JSON类型的数据转换为实体类
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		Gson gson = new Gson();
		T t = gson.fromJson(json, classOfT);
		gson = null;
		return t;
	}

}
