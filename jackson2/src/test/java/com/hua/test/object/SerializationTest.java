/**
 * 描述: 
 * SerializationTest.java
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

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SerializationTest
 */
public final class SerializationTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSerializationFeature() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			ObjectMapper objectMapper = new ObjectMapper();
			/**
			 * SerializationFeature.INDENT_OUTPUT 缩进，默认是false
			 */
			// 以下2句等效 开启缩进
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			//objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			
			// 以下2句等效 关闭缩进 (输出一行，没有换行缩进格式)
			//objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
			//objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
			
			String jsonString = objectMapper.writeValueAsString(user);
			
			System.out.println(jsonString);
			
			
		} catch (Exception e) {
			log.error("testSerializationFeature =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSerializationConfig() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			ObjectMapper objectMapper = new ObjectMapper();
			SerializationConfig config = objectMapper.getSerializationConfig();
			// config.with(df) // 设置日期时间格式
			/**
			 * 包含  或 去除 一个/多个特征
			 */
			// 包含该特征
			//config = config.with(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			
			// 去除该特征
			//config = config.without(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			
			//config = config.without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			config = config.with(dateTimeFormat);
			/**
			 * 注意，虽然 SerializationConfig 是通过ObjectMapper获取的，
			 * 但是在操作完SerializationConfig对象之后，一定要手动设置一次，
			 * 其配置才能再次刷新生效
			 */
			objectMapper.setConfig(config);
			String jsonString = objectMapper.writeValueAsString(user);
			
			System.out.println(jsonString);
			
		} catch (Exception e) {
			log.error("testSerializationConfig =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSerializerProvider() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			ObjectMapper objectMapper = new ObjectMapper();
			SerializationConfig config = objectMapper.getSerializationConfig();
			// config.with(df) // 设置日期时间格式
			/**
			 * 包含  或 去除 一个/多个特征
			 */
			// 包含该特征
			//config = config.with(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			
			// 去除该特征
			//config = config.without(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			
			//config = config.without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			config = config.with(dateTimeFormat);
			/**
			 * 注意，虽然 SerializationConfig 是通过ObjectMapper获取的，
			 * 但是在操作完SerializationConfig对象之后，一定要手动设置一次，
			 * 其配置才能再次刷新生效
			 */
			objectMapper.setConfig(config);
			/**
			 * 序列化 提供者
			 */
			SerializerProvider provider = objectMapper.getSerializerProvider();
			
			String jsonString = objectMapper.writeValueAsString(user);
			
			
			
			System.out.println(jsonString);
			
		} catch (Exception e) {
			log.error("testSerializerProvider =====> ", e);
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
