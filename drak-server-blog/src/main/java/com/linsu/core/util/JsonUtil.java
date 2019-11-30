package com.linsu.core.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	/**
	 * 转换对象为json字符串
	 * @param obj
	 * @return
	 */
	public static String toJsonString(Object obj) {
		return JSONObject.toJSONString(obj);
	}
}
