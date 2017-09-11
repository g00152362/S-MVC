package com.dot.utils;

import java.io.Console;
import java.sql.Timestamp;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class JsonUtils {
	/**
	 * json convert to pojo bean
	 * <p>Title: jsonString2Object</p>
	 * <p>Description: </p>
	 * @param jsonString
	 * @param pojoCalss
	 * @return pojo bean object
	 */
	  @SuppressWarnings("rawtypes")
	public static Object jsonString2Object(String jsonString, Class pojoCalss) {
	

	        
	        String[] formats = { "yyyy-MM-dd't'HH:mm:ss'z'" , "yyyy-MM-dd HH:mm:ss"};
	        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(formats), true);	        
	        JSONObject jsonObject = JSONObject.fromObject(jsonString);	        
//	        String[] dateFormats = new String[]{"yyyy-MM-dd HH:mm:ss"};  
//	        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats)); 	        

	        Object pojo = JSONObject.toBean(jsonObject, pojoCalss);

	        return pojo;

	    }
	  

	  
	   public static String beanToJson(Object obj) {
	        JsonConfig config = new JsonConfig();
//	        config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor(dateFormat));
	        JSONObject json = JSONObject.fromObject(obj, config);
	        return json.toString();
	    }
	  
}


