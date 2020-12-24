/**
 * 描述: 
 * DateTimeTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.json;

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

import java.util.Arrays;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;
import com.hua.util.SerializationUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * DateTimeTest
 */
public final class DateTimeTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDate() {
		try {
			
			
		} catch (Exception e) {
			log.error("testDate =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDateSerialize() {
		try {
			Date date = new Date();
			String str = date.toString();
			byte[] d = str.getBytes(Constant.CHART_SET_UTF_8);
			System.out.println(Arrays.toString(d));
			
			System.out.println(date.toString());
			byte[] data = SerializationUtil.serialize(date);
			
			System.out.println(Arrays.toString(data));
			
		} catch (Exception e) {
			log.error("testDateSerialize =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDateDeserialize() {
		try {
			 byte[] data = {-84, -19, 0, 5, 115, 114, 0, 14, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 68, 97, 116, 101, 104, 106, -127, 1, 75, 89, 
			                116, 25, 3, 0, 0, 120, 112, 119, 8, 0, 0, 1, 78, -111, 34, -19, 25, 120};
			 
			 byte[] d = {87, 101, 100, 32, 74, 117, 108, 32, 49, 53, 32, 49, 55, 58, 53, 56, 58, 53, 50, 32, 67, 83, 84, 32, 50, 48, 49, 53};
			 
			Date date = SerializationUtil.deserialize(d);
			System.out.println(date.toString());
		} catch (Exception e) {
			log.error("testDateDeserialize =====> ", e);
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
