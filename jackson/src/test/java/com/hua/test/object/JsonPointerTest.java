/**
 * 描述: 
 * JsonPointerTest.java
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

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hua.test.BaseTest;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JsonPointerTest
 */
public final class JsonPointerTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonPointer() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonFactory factory = objectMapper.getFactory();
			String content = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28 20:57:58\",\"lastLoginIp\":\"192.168.5.66\"}";

			content = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginIp\":\"192.168.5.66\"}";
			
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/UserBean3.json", true));
			JsonNode jsonNode = objectMapper.readTree(inputStream);
			
			/**
			 * JsonPointer 表达式
			 * 
			 */
			String input = "/user2/id";
			JsonPointer jsonPointer = JsonPointer.compile(input);
			System.out.println(jsonPointer.getMatchingProperty());
			JsonNode n = jsonNode.at(jsonPointer);
			System.out.println(n.asText());
			
		} catch (Exception e) {
			log.error("testJsonPointer =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonPointerExpression() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonFactory factory = objectMapper.getFactory();
			String content = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28 20:57:58\",\"lastLoginIp\":\"192.168.5.66\"}";

			content = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginIp\":\"192.168.5.66\"}";
			
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/UserBean3.json", true));
			JsonNode jsonNode = objectMapper.readTree(inputStream);
			
			/**
			 * JsonPointer 表达式
			 * 
			 */
			String jsonPointerExpression = "/user2/id";
			JsonNode n = jsonNode.at(jsonPointerExpression);
			System.out.println(n.asText());
			
		} catch (Exception e) {
			log.error("testJsonPointer =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonPointerExpression2() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonFactory factory = objectMapper.getFactory();
			String content = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28 20:57:58\",\"lastLoginIp\":\"192.168.5.66\"}";

			content = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginIp\":\"192.168.5.66\"}";
			
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/UserBean3.json", true));
			JsonNode jsonNode = objectMapper.readTree(inputStream);
			
			/**
			 * JsonPointer 表达式
			 * 
			 * 数组中的某个元素: 例如: /users/0 /users/1
			 * 
			 * 访问某个的值，直接根据 指针表达式去获取即可
			 * 数组的形式，就是后面加下标即可
			 * 
			 */
			String jsonPointerExpression = "/users/1";
			JsonNode n = jsonNode.at(jsonPointerExpression);
			System.out.println(n.toString());
			
		} catch (Exception e) {
			log.error("testJsonPointerExpression2 =====> ", e);
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
