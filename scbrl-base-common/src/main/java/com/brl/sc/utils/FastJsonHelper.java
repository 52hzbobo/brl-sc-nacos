package com.brl.sc.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Acmen_z
 *
 */
public class FastJsonHelper {

	public static String jsonEncode(Object obj)
	{
		return JSON.toJSONString(obj,
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteMapNullValue);
	}

	public static <T> T jsonDecode(String json, Class<T> clazz)
	{
		return (T) JSON.parseObject(json, clazz);
	}

	public static JSONObject toJSONObject(String jsonString) {
		JSONObject json = null;
		try {
			json = JSON.parseObject(jsonString);
		} catch (Exception e) {
		}
		return json;
	}

	public static <T> List<T> parseArray(String json, Class<T> clazz)
	{
		try {
			return JSONObject.parseArray(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String jsonString) {
		Map<String, Object> map = JSON.parseObject(jsonString, Map.class);
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> T mapToBean(Map<String, String> map, Class<T> clazz) {
		try{
			JSON.toJavaObject((JSON) JSON.toJSON(map), clazz);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("test", null);
		System.out.println(jsonEncode(json));
	}

}
