/**
 * 描述: 
 * FilterProviderTest.java
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

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * FilterProviderTest
 */
public final class FilterProviderTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFilterProvider() {
		try {
			Object value = null;
			String[] includeFields = null;
			String[] excludeFields = null;
			// 过滤器的id用bean的全限定名
			final String id = value.getClass().getName();
			// 过滤掉所有，除了指定的包含字段
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(includeFields);
			
			/**
			 * 2种不能的过滤器
			 */
			SimpleBeanPropertyFilter filterInclude = SimpleBeanPropertyFilter.filterOutAllExcept(includeFields);
			SimpleBeanPropertyFilter filterExclude = SimpleBeanPropertyFilter.serializeAllExcept(excludeFields);
			
			// 过滤器提供者
			final FilterProvider filterProvider = new SimpleFilterProvider().addFilter(id, filter);
			// 设置过滤器提供者
			objectMapper.setFilterProvider(filterProvider);
			
		} catch (Exception e) {
			log.error("testFilterProvider =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFilterProviderInclude() {
		try {
			String[] includeFields = {"id", "password"};
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			// 过滤器的id用bean的全限定名
			final String id = user.getClass().getName();
			// 过滤掉所有，除了指定的包含字段
			//SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(includeFields);
			
			/**
			 * 2种不能的过滤器
			 */
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(includeFields);
			
			// 过滤器提供者
			final FilterProvider filterProvider = new SimpleFilterProvider().addFilter(id, filter);
			// 设置过滤器提供者
			objectMapper.setFilterProvider(filterProvider);
			
			String jsonString = objectMapper.writeValueAsString(user);
			
			System.out.println(jsonString);
			
		} catch (Exception e) {
			log.error("testFilterProviderInclude =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFilterProviderExclude() {
		try {
			Object value = null;
			String[] includeFields = null;
			String[] excludeFields = null;
			// 过滤器的id用bean的全限定名
			final String id = value.getClass().getName();
			// 过滤掉所有，除了指定的包含字段
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(includeFields);
			
			/**
			 * 2种不能的过滤器
			 */
			SimpleBeanPropertyFilter filterInclude = SimpleBeanPropertyFilter.filterOutAllExcept(includeFields);
			SimpleBeanPropertyFilter filterExclude = SimpleBeanPropertyFilter.serializeAllExcept(excludeFields);
			
			// 过滤器提供者
			final FilterProvider filterProvider = new SimpleFilterProvider().addFilter(id, filter);
			// 设置过滤器提供者
			objectMapper.setFilterProvider(filterProvider);
			
		} catch (Exception e) {
			log.error("testFilterProviderExclude =====> ", e);
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
