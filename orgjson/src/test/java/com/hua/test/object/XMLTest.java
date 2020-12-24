/**
 * 描述: 
 * XMLTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.object;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * XMLTest
 */
public final class XMLTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testXML() {
		try {
			
			
		} catch (Exception e) {
			log.error("testXML =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testEscape() {
		try {
			/**
			 * XML.escape(String)
			 * xml特殊字符串进行转义替换
			 * < > &
			 */
			String str = "<a>link</a> &amp; &lt; ^";
			String result = XML.escape(str);
			
			System.out.println(result);
		} catch (Exception e) {
			log.error("testEscape =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNoSpace() {
		try {
			/**
			 * XML.noSpace(String)
			 * 含有空格则抛异常
			 * org.json.JSONException: 'a b c' contains a space character.
			 */
			String str = "abc";
			str = "a b c";
			XML.noSpace(str);
			
		} catch (Exception e) {
			log.error("testNoSpace =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testToJSONObject() {
		try {
			/**
			 * XML.toJSONObject(String)
			 * 将 xml 字符串 转成 JSONObject对象
			 * 
			 */
			String xmlStr = "<username>张三</username>"
					+ "<nickname>少年张三丰</nickname>"
					+ "<password>123456</password>";
			JSONObject jsonObject = XML.toJSONObject(xmlStr);
			System.out.println(jsonObject.toString());
			
		} catch (Exception e) {
			log.error("testToJSONObject =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testToString1() {
		try {
			/**
			 * 将 JSONObject 对象转成 xml字符串
			 */
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			// 直接输出多个标签，外部没有统一包围一个标签
			String xmlStr = XML.toString(jsonObject);
			
			System.out.println(xmlStr);
		} catch (Exception e) {
			log.error("testToString1 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testToString2() {
		try {
			/**
			 * 将 JSONObject 对象转成 xml字符串
			 */
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			// 使用指定的标签 包围整个对象
			String xmlStr = XML.toString(jsonObject, "user");
			
			System.out.println(xmlStr);
		} catch (Exception e) {
			log.error("testToString2 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
