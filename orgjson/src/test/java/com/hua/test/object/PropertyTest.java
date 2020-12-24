/**
 * 描述: 
 * PropertyTest.java
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
import java.util.Properties;

import org.json.JSONObject;
import org.json.Property;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * PropertyTest
 */
public final class PropertyTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testProperty() {
		try {
			
			
		} catch (Exception e) {
			log.error("testProperty =====> ", e);
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
			 * java.util.Properties 是一个Map<Object, Object>结构
			 * 将Map 转成 JSONObject对象
			 */
			// java.util.Properties
			Properties props = new Properties();
			props.put("name", "zhangsan");
			props.put("age", 25);
			props.put("sex", true);
			props.put("birthday", new Date());
			props.put("salary", 12.52);
			props.put("address", "广东省广州市天河区科韵路123号");
			JSONObject jsonObject = Property.toJSONObject(props);
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
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
	public void testToProperties() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			/**
			 * 所有类型都要转成 字符串类型
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", String.valueOf(25));
			jsonObject.put("sex", String.valueOf(true));
			jsonObject.put("birthday", String.valueOf(new Date()));
			jsonObject.put("salary", String.valueOf(12.52));
			//jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			/**
			 * 注意，日期、布尔、数值类型需要 转成 字符串
			 * 否则会抛异常: org.json.JSONException: JSONObject["salary"] not a string.
			 */
			Properties props = Property.toProperties(jsonObject);
			
			System.out.println(props);
			
		} catch (Exception e) {
			log.error("testToProperties =====> ", e);
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
