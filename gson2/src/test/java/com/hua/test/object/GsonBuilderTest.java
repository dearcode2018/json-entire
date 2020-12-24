/**
 * 描述: 
 * GsonBuilderTest.java
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

import com.google.gson.GsonBuilder;
import com.hua.constant.FormatConstant;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * GsonBuilderTest
 */
public final class GsonBuilderTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGsonBuilder() {
		try {
			// gson构建器
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			// 禁用 html 字符转义
			gsonBuilder.disableHtmlEscaping();
			
			// 禁用 内部类 序列化
			gsonBuilder.disableInnerClassSerialization();
			
			// 启用 复杂Map键序列化
			gsonBuilder.enableComplexMapKeySerialization();
			
			// 排除哪些 修饰符 字段 java.lang.reflect.Modifier
			//gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.FINAL, java.lang.reflect.Modifier.INTERFACE);
			
			/*
			 * 排除掉 没有用com.google.gson.annotations.Expose 标注的字段
			 * 使用 @Expose来配置 序列化、反序列化
			 */
			gsonBuilder.excludeFieldsWithoutExposeAnnotation();
			
			/*
			 * 排除掉 没有用com.google.gson.annotations.Expose 标注的字段
			 * 使用 @Expose来配置 序列化、反序列化
			 */
			//gsonBuilder.excludeFieldsWithoutExposeAnnotation();
			
			// 允许序列化 空字段，默认是忽略空字段
			gsonBuilder.serializeNulls();
			
			// 设置 日期时间 格式
			//gsonBuilder.setDateFormat(java.text.DateFormat.DEFAULT);
			//gsonBuilder.setDateFormat(java.text.DateFormat.DEFAULT, java.text.DateFormat.HOUR_OF_DAY1_FIELD);
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			// 根据版本 ignoreVersionsAfter 忽略参数之后的版本
			//gsonBuilder.setVersion(0.01);
			
		} catch (Exception e) {
			log.error("testGsonBuilder =====> ", e);
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
