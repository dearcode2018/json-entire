/**
 * 描述: 
 * JsonIgnoreTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.jackson.annotation;

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

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.hua.entity.ignore.IgnoreBean;
import com.hua.entity.ignore.IgnorePropertiesBean;
import com.hua.entity.ignore.IgnoreTypeBean;
import com.hua.entity.ignore.TypeIgnore;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JsonIgnoreTest
 */
public final class JsonIgnoreTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonSerializeIgnore() {
		try {
			/**
			 * @JsonIgnore(value = true | false)
			 * 直接标注该注解，表示忽略掉某个属性...
			 */
			IgnoreBean item = new IgnoreBean();
			item.setName("订单1");
			item.setCredit(0.5);
			item.setDatetime(new Date());
			item.setDescription("本次订单有效");
			
			/**
			 * 结果示例:
				{"name":"订单1","credit":0.5,"description":"本次订单有效","datetime":"2015-03-17 07:16:02"}
			 */
			// 序列化为 json
			String json = objectMapper.writeValueAsString(item);
			
			System.out.println(json);
			
		} catch (Exception e) {
			log.error("testJsonSerializeIgnore =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonDeserializeIgnore() {
		try {
			/**
			 * @JsonIgnore(value = true | false)
			 * 直接标注该注解，表示忽略掉某个属性...
			 */
			String json = "{\"name\":\"订单1\",\"credit\":0.5,\"description\":\"本次订单有效\",\"datetime\":\"2015-03-17 07:09:14\"}";
			IgnoreBean formatBean = objectMapper.readValue(json, IgnoreBean.class);
			
			System.out.println(formatBean.toString());
		} catch (Exception e) {
			log.error("testJsonDeserializeIgnore =====> ", e);
		}
	}
	
	/**
	 * json字符串转化为对象时，若字符串中含有对象没有的属性，默认情况下会抛异常
	 * 
	 * 解决方法: 
	 * 
	 */
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonDeserializeIgnoreUnknowField() {
		try {
			/**
			 * @JsonIgnore(value = true | false)
			 * 直接标注该注解，表示忽略掉某个属性...
			 */
			String json = "{\"name\":\"订单1\",\"credit\":0.5,\"description\":\"本次订单有效\",\"datetime\":\"2015-03-17 07:09:14\", \"unknowf\":\"12345\"}";
			IgnoreBean formatBean = objectMapper.readValue(json, IgnoreBean.class);
			
			System.out.println(formatBean.toString());
		} catch (Exception e) {
			log.error("testJsonDeserializeIgnoreUnknowField =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonSerializeIgnoreProperties() {
		try {
			/**
			 * @JsonIgnoreProperties
			 * 忽略掉哪些属性，直接通过数组参数来体现
			 * 序列化和反序列化都会忽略掉指定的属性
			 * ignoreUnknown = true 可以在反序列化的时候，多余的json属性将
			 * 会忽略掉而不会抛异常
			 * 
			 * 目标: 类/getter/setter方法/属性/构造器
			 */
			IgnorePropertiesBean item = new IgnorePropertiesBean();
			item.setName("订单1");
			item.setCredit(0.5);
			item.setDatetime(new Date());
			item.setDescription("本次订单有效");
			
			/**
			 * 结果示例:
				{"name":"订单1","credit":0.5,"description":"本次订单有效","datetime":"2015-03-17 07:16:02"}
			 */
			// 序列化为 json
			String json = objectMapper.writeValueAsString(item);
			
			System.out.println(json);
		} catch (Exception e) {
			log.error("testJsonSerializeIgnoreProperties =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonDeserializeIgnoreProperties() {
		try {
			/**
			 * @JsonIgnoreProperties
			 * 忽略掉哪些属性，直接通过数组参数来体现
			 * 序列化和反序列化都会忽略掉指定的属性
			 * ignoreUnknown = true 可以在反序列化的时候，多余的json属性将
			 * 会忽略掉而不会抛异常
			 * 
			 * 目标: 类/getter/setter方法/属性/构造器
			 */
			String json = "{\"name\":\"订单1\",\"credit\":0.5,\"description\":\"本次订单有效\",\"datetime\":\"2015-03-17 07:09:14\"}";
			IgnorePropertiesBean formatBean = objectMapper.readValue(json, IgnorePropertiesBean.class);
			
			System.out.println(formatBean.toString());
		} catch (Exception e) {
			log.error("testJsonDeserializeIgnoreProperties =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonIgnoreType() {
		try {
			/**
			 * @JsonIgnoreType 忽略掉标注的类型
			 */
			IgnoreTypeBean item = new IgnoreTypeBean();
			item.setName("订单1");
			item.setCredit(0.5);
			item.setDatetime(new Date());
			item.setDescription("本次订单有效");
			// 该类型标注了 @JsonIgnoreType，序列化将会被忽略掉
			TypeIgnore ignore = new TypeIgnore();
			ignore.setName("hahaha");
			item.setIgnore(ignore);
			/**
			 * 结果示例:
				{"name":"订单1","credit":0.5,"description":"本次订单有效","datetime":"2015-03-17 07:16:02"}
			 */
			// 序列化为 json
			String json = objectMapper.writeValueAsString(item);
			
			System.out.println(json);
		} catch (Exception e) {
			log.error("testJsonIgnoreType =====> ", e);
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
