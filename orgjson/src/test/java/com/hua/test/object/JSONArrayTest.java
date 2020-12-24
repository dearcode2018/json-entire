/**
 * 描述: 
 * JSONArrayTest.java
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

import org.json.JSONArray;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JSONArrayTest
 */
public final class JSONArrayTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJSONArray() {
		try {
			
			
		} catch (Exception e) {
			log.error("testJSONArray =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPut() {
		try {
			JSONArray jsonArray = new JSONArray();
			/**
			 * 放入是没有顺序的
			 */
			jsonArray.put(0, "zhangsan");
			jsonArray.put(1, 25);
			jsonArray.put(2, true);
			jsonArray.put(3, new Date());
			jsonArray.put(4, 12.52);
			jsonArray.put(5, "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonArray.put(6, "zhangsan_override");
			
			System.out.println(jsonArray.toString());
			
		} catch (Exception e) {
			log.error("testPut =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGet() {
		try {
			JSONArray jsonArray = new JSONArray();
			/**
			 * 放入是没有顺序的
			 */
			jsonArray.put(0, "zhangsan");
			jsonArray.put(1, 25);
			jsonArray.put(2, true);
			jsonArray.put(3, new Date());
			jsonArray.put(4, 12.52);
			jsonArray.put(5, "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonArray.put(6, "zhangsan_override");
			
			System.out.println(jsonArray.toString());
			
			System.out.println(jsonArray.get(1));
			// 越界会抛异常，exception: org.json.JSONException: JSONArray[7] not found.
			//System.out.println(jsonArray.get(7));
			
		} catch (Exception e) {
			log.error("testGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testOpt() {
		try {
			JSONArray jsonArray = new JSONArray();
			/**
			 * 放入是没有顺序的
			 */
			jsonArray.put(0, "zhangsan");
			jsonArray.put(1, 25);
			jsonArray.put(2, true);
			jsonArray.put(3, new Date());
			jsonArray.put(4, 12.52);
			jsonArray.put(5, "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonArray.put(6, "zhangsan_override");
			
			System.out.println(jsonArray.toString());
			// 越界不会抛异常，返回null
			System.out.println(jsonArray.opt(7));
			
		} catch (Exception e) {
			log.error("testOpt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJoin() {
		try {
			/**
			 * join 用指定的字符串连接起来，输出一个字符串
			 * 其值保持原本样式，是字符串会带有2哥双引号
			 * 类似:"zhangsan"||25||true||"Fri Jul 10 17:05:44 CST 2015"||12.52
			 */
			JSONArray jsonArray = new JSONArray();
			/**
			 * 放入是没有顺序的
			 */
			jsonArray.put(0, "zhangsan");
			jsonArray.put(1, 25);
			jsonArray.put(2, true);
			jsonArray.put(3, new Date());
			jsonArray.put(4, 12.52);
			jsonArray.put(5, "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonArray.put(6, "zhangsan_override");
			
			System.out.println(jsonArray.toString());
			//
			System.out.println(jsonArray.join("||"));
		} catch (Exception e) {
			log.error("testJoin =====> ", e);
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
