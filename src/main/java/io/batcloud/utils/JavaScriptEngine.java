package io.batcloud.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.alibaba.fastjson.JSONObject;
import com.zz.common.log.LogService;

public class JavaScriptEngine {
	private static ScriptEngineManager manager = new ScriptEngineManager(); // 创建一个ScriptEngineManager对象
	private static ScriptEngine engine = manager.getEngineByName("js"); // 通过ScriptEngineManager获得ScriptEngine对象

	public static boolean getBoolean(String expressionStr,boolean defaultVal) {
		
		try {
			Boolean result = (boolean) engine.eval(expressionStr);// 用ScriptEngine的eval方法执行脚本
			return result;
		} catch (ScriptException e) {
			
			LogService.error("JavaScriptEngine.getBoolean error,expression(" + expressionStr+ ")");
			LogService.error(e.getMessage());
			e.printStackTrace();
		}
		
		return defaultVal;
	}
	
	public static Integer getInt(String expressionStr,Integer defaultVal) {
		
		try {
			Integer result = (Integer) engine.eval(expressionStr);// 用ScriptEngine的eval方法执行脚本
			return result;
		} catch (ScriptException e) {
			
			LogService.error("JavaScriptEngine.getBoolean error,expression(" + expressionStr+ ")");
			LogService.error(e.getMessage());
			e.printStackTrace();
		}
		
		return defaultVal;
	}
	
	public static Double getDouble(String expressionStr,Double defaultVal) {
		
		try {
			Double result = (Double) engine.eval(expressionStr);// 用ScriptEngine的eval方法执行脚本
			return result;
		} catch (ScriptException e) {
			
			LogService.error("JavaScriptEngine.getBoolean error,expression(" + expressionStr+ ")");
			LogService.error(e.getMessage());
			e.printStackTrace();
		}
		
		return defaultVal;
	}
	
	public static String getString(String expressionStr,String defaultVal) {
		
		try {
			if(expressionStr.split(" ").length == 1 && expressionStr.matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
				expressionStr = "'"+ expressionStr + "'";//字符串放入js中，要加引号，不然就当成变量
			}
			Object result = (Object) engine.eval(expressionStr);// 用ScriptEngine的eval方法执行脚本
		
			return null == result ?"":result.toString();
		} catch (ScriptException e) {
			
			LogService.error("JavaScriptEngine.getBoolean error,expression(" + expressionStr+ ")");
			LogService.error(e.getMessage());
			e.printStackTrace();
		}
		
		return defaultVal;
	}
	
	/**
	 * 给定一个json 对象，var a = {b:{c:{e:"hello world"}}} 以a.b.c.e 得到属性的值
	 * @param json
	 * @param jsonAttr
	 * @param defaultVal
	 * @return
	 */
	public static String getJsonObjAttributeVal(JSONObject json,String jsonAttr,String defaultVal) {
		String[] var = jsonAttr.split("\\.");
	
		String exp = "var "+ var[0] + "="+ json.toJSONString() + ";"+ jsonAttr;
		try {
			
			Object result = (Object) engine.eval(exp);// 用ScriptEngine的eval方法执行脚本
			return result.toString();
		} catch (ScriptException e) {
			
			LogService.error("JavaScriptEngine.getBoolean error,expression(" + exp + ")");
			LogService.error(e.getMessage());
			e.printStackTrace();
		}
		
		return defaultVal;
	}
	
	
	public static String getJsonObjAttributeVal(JSONObject json,String jsonName,String attr,String defaultVal) {
		String exp = "var "+ jsonName + "="+ json.toJSONString() + "; "+ jsonName+ "." + attr+ ";";
		try {
			
			Object result = (Object) engine.eval(exp);// 用ScriptEngine的eval方法执行脚本
			return result.toString();
		} catch (ScriptException e) {
			
			LogService.error("JavaScriptEngine.getBoolean error,expression(" + exp+ ")");
			LogService.error(e.getMessage());
			e.printStackTrace();
		}
		
		return defaultVal;
	}
	
	
}
