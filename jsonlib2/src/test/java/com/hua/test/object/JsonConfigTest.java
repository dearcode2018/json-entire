/**
 * 描述: 
 * JsonConfigTest.java
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
import java.util.Date;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.bean.User;
import com.hua.processor.DateValueProcessor;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.IOUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JsonConfigTest
 */
public final class JsonConfigTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonConfig() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			JsonConfig jsonConfig = new JsonConfig();
			/**
			 * 添加需要忽略掉的字段
			 */
			jsonConfig.registerPropertyExclusion(User.class, "id");
			jsonConfig.registerPropertyExclusion(User.class, "username");
			//jsonConfig.registerJsonValueProcessor(beanClass, propertyType, jsonValueProcessor);
			
			JSONObject jsonObject = JSONObject.fromObject(user, jsonConfig);
			
			System.out.println(jsonObject.toString(1));
			
			//System.out.println(jsonObject.toString());
			// 等效: System.out.println(jsonObject.toString(1, 0));
			//System.out.println(jsonObject.toString(1));
			// 
			//System.out.println(jsonObject.toString(1, 0));
			
		} catch (Exception e) {
			log.error("testJsonConfig =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonConfigSerializer() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			JsonConfig jsonConfig = new JsonConfig();
			/**
			 * 添加需要忽略掉的字段
			 */
			jsonConfig.registerPropertyExclusion(User.class, "id");
			jsonConfig.registerPropertyExclusion(User.class, "username");
			// 或者多个
			jsonConfig.registerPropertyExclusions(User.class, new String[] {"id", "username"});
			//jsonConfig.registerJsonValueProcessor(beanClass, propertyType, jsonValueProcessor);
			//jsonConfig.registerJsonValueProcessor(User.class, Date.class, new JsDateJsonValueProcessor());
			JSONObject jsonObject = JSONObject.fromObject(user, jsonConfig);
			
			System.out.println(jsonObject.toString(1));
			
			//System.out.println(jsonObject.toString());
			// 等效: System.out.println(jsonObject.toString(1, 0));
			//System.out.println(jsonObject.toString(1));
			// 
			//System.out.println(jsonObject.toString(1, 0));
			
		} catch (Exception e) {
			log.error("testJsonConfigSerializer =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonConfigSerializer2() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setSqlDate(DateTimeUtil.getDate());
			user.setLastLoginIp("192.168.5.66");
			
			JsonConfig jsonConfig = new JsonConfig();
			/**
			 * 添加需要忽略掉的字段
			 */
			jsonConfig.registerPropertyExclusion(User.class, "id");
			jsonConfig.registerPropertyExclusion(User.class, "username");
			// 或者多个
			jsonConfig.registerPropertyExclusions(User.class, new String[] {"id", "username"});
			//jsonConfig.registerJsonValueProcessor(beanClass, propertyType, jsonValueProcessor);
			//jsonConfig.registerJsonValueProcessor(User.class, Date.class, new JsDateJsonValueProcessor());
			JsonValueProcessor processor = new DateValueProcessor();
			// 注意要 sqlDate 和utilDate类型要注册了 值处理器才能正常序列化
			//jsonConfig.registerJsonValueProcessor(Date.class, processor);
			jsonConfig.registerJsonValueProcessor(java.sql.Date.class, processor);
			JSONObject jsonObject = JSONObject.fromObject(user, jsonConfig);
			
			System.out.println(jsonObject.toString(1));
			
			//System.out.println(jsonObject.toString());
			// 等效: System.out.println(jsonObject.toString(1, 0));
			//System.out.println(jsonObject.toString(1));
			// 
			//System.out.println(jsonObject.toString(1, 0));
			
		} catch (Exception e) {
			log.error("testJsonConfigSerializer =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonConfigDeserializer() {
		try {
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/User.json", true));
			String jsonString = IOUtil.getString(inputStream);
			
			
			JsonConfig jsonConfig = new JsonConfig();
			/**
			 * 序列化: 添加需要忽略掉的字段
			 */
			jsonConfig.registerPropertyExclusion(User.class, "id");
			jsonConfig.registerPropertyExclusion(User.class, "username");
			// 或者多个
			jsonConfig.registerPropertyExclusions(User.class, new String[] {"id", "username"});
		
			
			JSONObject jsonObject = JSONObject.fromObject(jsonString, jsonConfig);
			
			System.out.println(jsonObject.toString(1));
			
			//System.out.println(jsonObject.toString());
			// 等效: System.out.println(jsonObject.toString(1, 0));
			//System.out.println(jsonObject.toString(1));
			// 
			//System.out.println(jsonObject.toString(1, 0));
			
		} catch (Exception e) {
			log.error("testJsonConfigDeserializer =====> ", e);
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
