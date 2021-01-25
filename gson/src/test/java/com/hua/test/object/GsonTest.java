/**
 * 描述: 
 * GsonTest.java
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

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hua.bean.User;
import com.hua.constant.FormatConstant;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * GsonTest
 */
public final class GsonTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGson() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			Gson gson = gsonBuilder.create();
			
			
		} catch (Exception e) {
			log.error("testGson =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFromJson() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			Gson gson = gsonBuilder.create();
			//			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/UserBean.json", true));
			String path = ProjectUtil.getAbsolutePath("/doc/User.json", true);
			Reader reader = new FileReader(new File(path));
			
			User user = gson.fromJson(reader, User.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testFromJson =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testToJson() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			Gson gson = gsonBuilder.create();
			
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			String jsonString = gson.toJson(user);
			
			System.out.println(jsonString);
			
		} catch (Exception e) {
			log.error("testToJson =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testToString() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			Gson gson = gsonBuilder.create();
			//			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/UserBean.json", true));
			String path = ProjectUtil.getAbsolutePath("/doc/User.json", true);
			Reader reader = new FileReader(new File(path));
			
			User user = gson.fromJson(reader, User.class);
			
			//System.out.println(user.toString());
			
			// 输出 gson对象信息，而不是json字符串
			System.out.println(gson.toString());
			
		} catch (Exception e) {
			log.error("testToString =====> ", e);
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
