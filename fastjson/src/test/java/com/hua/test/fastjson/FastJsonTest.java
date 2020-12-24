/**
 * 描述: 
 * FastJsonTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.fastjson;

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

import com.hua.entity.Course;
import com.hua.entity.Student;
import com.hua.test.BaseTest;


/**
 * 描述: Fast Json - 测试
 * 
 * @author qye.zheng
 * FastJsonTest
 */
public final class FastJsonTest extends BaseTest {

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
	public void testFastJson() {
		try {
			
			
		} catch (Exception e) {
			log.error("testFastJson =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: json字符串转成对象
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testToObject() {
		try {
			
			
			/*String json = "{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
					+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"Jul 28, 1981 12:00:00 AM\"}";*/
			final String json = "{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
					+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"1988-07-01 13:30:34\"}";
			
			
			// 设置日期时间格式
			Student stu = null;
			
			log.info("testToObject =====> name = " + stu.getName());
			log.info("testToObject =====> oid = " + stu.getOid());
			log.info("testToObject =====> sex = " + stu.isSex());
			log.info("testToObject =====> birthday = " + stu.getBirthday());
			
		} catch (Exception e) {
			log.error("testToObject =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 对象转成 json 字符串
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testToJson() {
		try {
			final Student stu = new Student();
			stu.setOid(2008L);
			stu.setName("王三春");
			stu.setBirthday(dateFormat.parse("1981-07-28"));
			stu.setAddress("陕西省凌云县风雷震风云路120号");
			stu.setCredit(Double.valueOf(47.8));
			stu.setSex(true);
			
			Course c = new Course();
			c.setName("高等数学");
			c.setDescription("大学数学课程");
			c.setCredit(Double.valueOf(5.0));
			stu.addCourse(c);
			
			c = new Course();
			c.setName("高等语文");
			c.setDescription("大学语文课程");
			c.setCredit(Double.valueOf(4.5));
			stu.addCourse(c);
			
			c = new Course();
			c.setName("政治与哲学");
			c.setDescription("大学政治课程");
			c.setCredit(Double.valueOf(4.0));
			stu.addCourse(c);
			
			// 初始化jsonObject
			//initJsonObject(stu);
			
			
			// 设置日期格式
			String json = null;
			log.info("testToJson =====> json --> " + json);
			
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
