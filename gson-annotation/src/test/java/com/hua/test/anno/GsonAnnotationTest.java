/**
 * 描述: 
 * GsonAnnotationTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.anno;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import com.hua.bean.ExposeBean;
import com.hua.bean.JsonAdapterBean;
import com.hua.bean.SerializedNameBean;
import com.hua.bean.SinceBean;
import com.hua.bean.UntilBean;
import com.hua.bean.User;
import com.hua.constant.FormatConstant;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * GsonAnnotationTest
 */
public final class GsonAnnotationTest extends BaseTest {

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
	public void testGsonAnnotation() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			/*
			 * 排除掉 没有用com.google.gson.annotations.Expose 标注的字段
			 * 使用 @Expose来配置 序列化、反序列化
			 */
			//gsonBuilder.excludeFieldsWithoutExposeAnnotation();
			
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
			log.error("testGsonAnnotation =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExpose() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			/*
			 * 排除掉 没有用com.google.gson.annotations.Expose 标注的字段
			 * 使用 @Expose来配置 序列化、反序列化
			 * @Expose 暴露/公开
			 */
			gsonBuilder.excludeFieldsWithoutExposeAnnotation();
			
			Gson gson = gsonBuilder.create();
			
			ExposeBean user = new ExposeBean();
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
			log.error("testExpose =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonAdapter() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);

			
			Gson gson = gsonBuilder.create();
			
			JsonAdapterBean user = new JsonAdapterBean();
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
			log.error("testJsonAdapter =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSerializedName() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
	
			
			/**
			 * @SerializedName(value = "otherName")
			 * 使用其他名称来序列化字段，而不是默认使用字段名
			 */
			Gson gson = gsonBuilder.create();
			
			SerializedNameBean user = new SerializedNameBean();
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
			log.error("testSerializedName =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSince() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
	
			/**
			 * 
			 * @Since(value = 1.0)
			 */
			// 忽略该版本数之后(大于)的带有版本标注的字段
			double ignoreVersionsAfter = 2.5;
			gsonBuilder.setVersion(ignoreVersionsAfter);
			
			Gson gson = gsonBuilder.create();
			SinceBean user = new SinceBean();
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
			log.error("testSince =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUntil() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);

			/**
			 * 
			 * @Until(value = 3.3)
			 * 版本值超过 ignoreVersionsAfter 则不参与序列化/反序列化
			 */
			// 忽略该版本数之后(大于)的带有版本标注的字段
			double ignoreVersionsAfter = 2.5;
			gsonBuilder.setVersion(ignoreVersionsAfter);
			
			Gson gson = gsonBuilder.create();
			
			UntilBean user = new UntilBean();
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
			log.error("testUntil =====> ", e);
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
