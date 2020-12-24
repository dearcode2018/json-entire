/**
 * 描述: 
 * JsonGeneratorTest.java
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

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JsonGeneratorTest
 */
public final class JsonGeneratorTest extends BaseTest {

	/**
	 * JsonGenerator: json生成器，做json各种输出，可以设定输出格式、输出内容等...
	 * 
	 * 通过该对象可以很详细控制json的输出，向一个指定的输出流输出很多东西，但是
	 * 输出方向在构造该对象之前已经确定，此后不能再改变输出目的.
	 * 
	 * 输出流: 文件、网络、存储器、控制台...
	 * 
	 * JsonGenerator可以向输出流输出任意内容，不管是否组成json格式，当然最终目的是为了
	 * 能够输出满足目的地的数据及其格式.
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonGenerator() {
		JsonGenerator jsonGenerator = null;
		try {
			// 输出流
			OutputStream outputStream = System.out;
			ObjectMapper objectMapper = new ObjectMapper();
			// 设置默认的日期处理格式
			objectMapper.setDateFormat(dateTimeFormat);
			// 输出流 + 指定编码
			jsonGenerator = objectMapper.getFactory().createGenerator(outputStream, JsonEncoding.UTF8);
			
			//
			jsonGenerator.writeString("hi, JsonGenerator");
			
			/*
			 * 调用该方法 就会主动刷新缓存，然后输出
			 * 没有调用该方法，则在缓存填满了之后 再输出填满的部分
			 */
			jsonGenerator.flush();
			
			System.out.println("--");
			// 输出布尔值
			jsonGenerator.writeBooleanField("flag", true);
			
			// 刷新缓存
			jsonGenerator.flush();
		} catch (Exception e) {
			log.error("testJsonGenerator =====> ", e);
		} finally
		{
			// 关闭 JsonGenerator
			JacksonUtil.close(jsonGenerator);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteObject() {
		JsonGenerator jsonGenerator = null;
		try { 
			// 输出流
			OutputStream outputStream = System.out;
			ObjectMapper objectMapper = new ObjectMapper();
			// 设置默认的日期处理格式
			objectMapper.setDateFormat(dateTimeFormat);
			// 输出流 + 指定编码
			jsonGenerator = objectMapper.getFactory().createGenerator(outputStream, JsonEncoding.UTF8);
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			jsonGenerator.writeObject(user);
			
			// 刷新缓存
			jsonGenerator.flush();
		} catch (Exception e) {
			log.error("testWriteObject =====> ", e);
		} finally
		{
			// 关闭 JsonGenerator
			JacksonUtil.close(jsonGenerator);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteRaw() {
		JsonGenerator jsonGenerator = null;
		try {
			// 输出流
			OutputStream outputStream = System.out;
			ObjectMapper objectMapper = new ObjectMapper();
			// 设置默认的日期处理格式
			objectMapper.setDateFormat(dateTimeFormat);
			// 输出流 + 指定编码
			jsonGenerator = objectMapper.getFactory().createGenerator(outputStream, JsonEncoding.UTF8);
			
			jsonGenerator.writeRaw("abccd", 1, 2);
			
			// 刷新缓存
			jsonGenerator.flush();
		} catch (Exception e) {
			log.error("testWriteRaw =====> ", e);
		} finally
		{
			// 关闭 JsonGenerator
			JacksonUtil.close(jsonGenerator);
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
		JsonGenerator jsonGenerator = null;
		try {
			// 输出流
			OutputStream outputStream = System.out;
			ObjectMapper objectMapper = new ObjectMapper();
			// 设置默认的日期处理格式
			objectMapper.setDateFormat(dateTimeFormat);
			// 输出流 + 指定编码
			jsonGenerator = objectMapper.getFactory().createGenerator(outputStream, JsonEncoding.UTF8);
			
			// 刷新缓存
			jsonGenerator.flush();
		} catch (Exception e) {
			log.error("testJsonGenerator =====> ", e);
		} finally
		{
			// 关闭 JsonGenerator
			JacksonUtil.close(jsonGenerator);
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
