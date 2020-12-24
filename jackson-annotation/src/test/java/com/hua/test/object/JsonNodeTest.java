/**
 * 描述: 
 * JsonNodeTest.java
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
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hua.test.BaseTest;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JsonNodeTest
 */
public final class JsonNodeTest extends BaseTest {

	/**
	 * 可以在循环中 findValue，从而获取多个路径下的JsonNode对象
	 */

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonNode() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonFactory factory = objectMapper.getFactory();
			String content = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28 20:57:58\",\"lastLoginIp\":\"192.168.5.66\"}";

			content = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginIp\":\"192.168.5.66\"}";
			
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/UserBean3.json", true));
			JsonNode jsonNode = objectMapper.readTree(inputStream);
			
			JsonNode node1 = jsonNode.findPath("id");
			System.out.println(node1.asText());
			
			JsonNode node2 = jsonNode.findPath("user2.id");
			System.out.println(node2.asText());
			/**
			 * 数组也是通过 findValue获得，而不是findValues，
			 * 通过findValues 获取的List的长度为1.
			 */
			List<JsonNode> nodes = jsonNode.findValues("users");
			log.info("testJsonNode =====> size: " + nodes.size());
			log.info("testJsonNode =====> " + nodes.get(0).toString());
			
		} catch (Exception e) {
			log.error("testJsonNode =====> ", e);
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
