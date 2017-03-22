package com.xhh.framework.bean.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;


/**
 * Created by 001354 on 2015/7/27.
 */
public class GsonUtil {
	private static Gson gson = new Gson();
	private GsonUtil() {
		
	}
    public static String fromObjToJson(Object obj) {
        return gson.toJson(obj);
    }

    public static Object fromJsonToObj(String json, Class clazz) {
        return gson.fromJson(json, clazz);
    }
    
    public static <T>T fromJsonToObj(String json, Type type) {
        return gson.fromJson(json, type);
    }
}
