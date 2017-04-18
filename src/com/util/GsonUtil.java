package com.util;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
    
    
    public static void main(String[] args) {
    	List<String> solution =  new LinkedList<String>();
		for (int i=0;i<3;i++) {
			solution.add(0, i+"");
		}
		for (int i=0;i<solution.size();i++) {
			System.out.println(solution);
		}
		
    }
    
	
}
