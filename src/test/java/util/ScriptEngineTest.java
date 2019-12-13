package util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

import java.time.LocalDateTime;

public class ScriptEngineTest {
	
	static ScriptEngineManager manager = new ScriptEngineManager(); // 创建一个ScriptEngineManager对象
	static ScriptEngine engine = manager.getEngineByName("js"); // 通过ScriptEngineManager获得ScriptEngine对象
	
	public static  String calculate(String formula) {
		String result = null;// 计算结果
		
		
		try {
			 Double resultNum = (Double) engine.eval(formula);// 用ScriptEngine的eval方法执行脚本
			 result = resultNum.toString();
		} catch (ScriptException e) {
		 
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	//@Test
	public void testScriptEngine() throws ScriptException {
		long start = System.currentTimeMillis();
		for(int i = 0;i < 1000000; i++) {
		calculate("(143123 - 121123)/121123 * 100");
//		 System.out.println(result);
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void test1(){
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(1000*60*60*24*3);
	}
}
