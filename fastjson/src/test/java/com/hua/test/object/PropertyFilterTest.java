/**
 * 描述: 
 * PropertyFilterTest.java
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.EmptyUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * PropertyFilterTest
 */
public final class PropertyFilterTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPropertyFilter() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			// 不序列化 数组中的字段，若为空，则序列化所有的字段
			String[] excludeFields = {"id", "nickname", "notExistName"};
			final SerializeFilter filter = new PropertyFilter() {
				
				/**
				 * 
				 * @description 
				 * @param object
				 * @param name
				 * @param value
				 * @return 返回false-不序列化该属性
				 * @author qianye.zheng
				 */
				@Override
				public boolean apply(Object object, String name, Object value) {
					if (!EmptyUtil.isEmpty(excludeFields))
					{
						for (String excludeField : excludeFields)
						{
							if (excludeField.equals(name))
							{
								return false;
							}
						}
					}
					
					return true;
				}
			};
			
			// 修改该全局变量，注意 该变量只是用来控制反序列化用，某些序列化用此变量无效
			//JSON.DEFFAULT_DATE_FORMAT = "yyyyMMdd HH:mm:ss";
			String jsonString = JSON.toJSONString(user, filter);
			System.out.println(jsonString);
			// 优美格式输出
			//jsonString = JSON.toJSONString(user, true);
			
		} catch (Exception e) {
			log.error("testPropertyFilter =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPropertyInclude() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			// 为空或没有匹配的 则所有字段都不序列化
			// 只序列化 数组中的字段，为空则所有字段都不序列化
			String[] includeFields = {"id", "valid", "notExistName"};
			final SerializeFilter filter = new PropertyFilter() {
				
				/**
				 * 
				 * @description 
				 * @param object
				 * @param name
				 * @param value
				 * @return 返回false-不序列化该属性
				 * @author qianye.zheng
				 */
				@Override
				public boolean apply(Object object, String name, Object value) {
					if (!EmptyUtil.isEmpty(includeFields))
					{
						for (String includeField : includeFields)
						{
							if (includeField.equals(name))
							{
								return true;
							}
						}
					}
					
					return false;
				}
			};
			
			// 修改该全局变量，注意 该变量只是用来控制反序列化用，某些序列化用此变量无效
			//JSON.DEFFAULT_DATE_FORMAT = "yyyyMMdd HH:mm:ss";
			String jsonString = JSON.toJSONString(user, filter);
			System.out.println(jsonString);
			// 优美格式输出
			//jsonString = JSON.toJSONString(user, true);
			
		} catch (Exception e) {
			log.error("testPropertyInclude =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPropertyExclude() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			// 不序列化 数组中的字段，若为空，则序列化所有的字段
			String[] excludeFields = {"id", "password", "notExistName"};
			final SerializeFilter filter = new PropertyFilter() {
				
				/**
				 * 
				 * @description 
				 * @param object
				 * @param name
				 * @param value
				 * @return 返回false-不序列化该属性
				 * @author qianye.zheng
				 */
				@Override
				public boolean apply(Object object, String name, Object value) {
					if (!EmptyUtil.isEmpty(excludeFields))
					{
						for (String excludeField : excludeFields)
						{
							if (excludeField.equals(name))
							{
								return false;
							}
						}
					}
					
					return true;
				}
			};
			
			// 修改该全局变量，注意 该变量只是用来控制反序列化用，某些序列化用此变量无效
			//JSON.DEFFAULT_DATE_FORMAT = "yyyyMMdd HH:mm:ss";
			String jsonString = JSON.toJSONString(user, filter);
			System.out.println(jsonString);
			// 优美格式输出
			//jsonString = JSON.toJSONString(user, true);
			
		} catch (Exception e) {
			log.error("testPropertyExclude =====> ", e);
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
