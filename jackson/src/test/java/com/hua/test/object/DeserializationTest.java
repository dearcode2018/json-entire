/**
 * 描述: 
 * DeserializationTest.java
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

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hua.bean.UserBean;
import com.hua.test.BaseTest;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * DeserializationTest
 */
public final class DeserializationTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDeserializationFeature() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/UserObjectLackField.json", true));
			
			// 以下2句等效 未知属性将导致解析失败 - 关闭
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			// 以下2句等效 未知属性将导致解析失败 - 开启
			//objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			//objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);	
			UserBean  user = objectMapper.readValue(inputStream, UserBean.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testDeserializationFeature =====> ", e);
		}
	}			

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDeserializationConfig() {
		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/UserObjectLackField.json", true));
			
			// 以下2句等效 未知属性将导致解析失败 - 关闭
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			// 以下2句等效 未知属性将导致解析失败 - 开启
			//objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			//objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);	
			
			DeserializationConfig config = objectMapper.getDeserializationConfig();
			// config.with(df) // 设置日期时间格式
			/**
			 * 包含  或 去除 一个/多个特征
			 */
			// 包含该特征
			//config = config.with(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			
			// 去除该特征
			//config = config.without(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			
			//config = config.without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			config = config.with(dateFormat);
			/**
			 * 注意，虽然 SerializationConfig 是通过ObjectMapper获取的，
			 * 但是在操作完SerializationConfig对象之后，一定要手动设置一次，
			 * 其配置才能再次刷新生效
			 */
			objectMapper.setConfig(config);
			UserBean  user = objectMapper.readValue(inputStream, UserBean.class);
			
			System.out.println(user.toString());
			
			
		} catch (Exception e) {
			log.error("testDeserializationConfig =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDeserializationContext() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/UserObjectLackField.json", true));
			
			// 以下2句等效 未知属性将导致解析失败 - 关闭
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			// 以下2句等效 未知属性将导致解析失败 - 开启
			//objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			//objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);	
			
			DeserializationConfig config = objectMapper.getDeserializationConfig();
			// config.with(df) // 设置日期时间格式
			/**
			 * 包含  或 去除 一个/多个特征
			 */
			// 包含该特征
			//config = config.with(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			
			// 去除该特征
			//config = config.without(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			
			//config = config.without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			config = config.with(dateFormat);
			/**
			 * 注意，虽然 SerializationConfig 是通过ObjectMapper获取的，
			 * 但是在操作完SerializationConfig对象之后，一定要手动设置一次，
			 * 其配置才能再次刷新生效
			 */
			objectMapper.setConfig(config);
			/**
			 * 反序列化 上下文
			 */
			DeserializationContext context = objectMapper.getDeserializationContext();
			
			UserBean  user = objectMapper.readValue(inputStream, UserBean.class);
			
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testDeserializationContext =====> ", e);
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
