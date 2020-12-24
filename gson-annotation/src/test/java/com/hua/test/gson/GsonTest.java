/**
 * 描述: 
 * GsonTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.gson;

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hua.entity.Course;
import com.hua.entity.Student;
import com.hua.test.BaseTest;


/**
 * 描述: Google Json - 测试
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
	public void testGson() {
		try {
			
			
		} catch (Exception e) {
			log.error("testGson =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 从json字符串中直接提取相关属性
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonParser() {
		try {
			/*String json = "{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
					+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"Jul 28, 1981 12:00:00 AM\"}";*/
			String json = "{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
					+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"1988-07-01\"}";
			final JsonParser jsonParser = new JsonParser();
			final JsonElement e = jsonParser.parse(json);
			final JsonObject jsonObj = e.getAsJsonObject();
			log.info("testGetFromString =====> " + jsonObj.get("name").getAsString());

			
		} catch (Exception e) {
			log.error("testGetFromString =====> ", e);
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
			gson = gsonBuilder.setDateFormat("yyyy-MM-dd").create();
			
			
			/*String json = "{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
					+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"Jul 28, 1981 12:00:00 AM\"}";*/
			String json = "{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
					+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"1988-07-01\"}";
			Student stu = gson.fromJson(json, Student.class);
			
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
	 * 描述: json字符串转成对象
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonArrayToList() {
		try {
			gson = gsonBuilder.setDateFormat("yyyy-MM-dd").create();
			
			/*String json = "{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
					+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"Jul 28, 1981 12:00:00 AM\"}";*/
			String json = "[{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
					+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"1988-07-01\"}]";
			Student stu = null;
			List<Student> students = new ArrayList<Student>();
			final JsonParser jsonParser = new JsonParser();
			final JsonElement e = jsonParser.parse(json);
			final JsonArray jsonArray = e.getAsJsonArray();
			JsonElement temp = null;
			for (int i = 0; i < jsonArray.size(); i++)
			{
				temp = jsonArray.get(i);
				stu = gson.fromJson(temp, Student.class);
				students.add(stu);
			}
			
			log.info("testJsonArrayToList =====> " + stu.getName());
			
		} catch (Exception e) {
			log.error("testJsonArrayToList =====> ", e);
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
			Student stu = new Student();
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
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			gson = gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			
			String json = gson.toJson(stu);
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
	public void testJsonObject() {
		try {
			/*String json = "{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
			+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"Jul 28, 1981 12:00:00 AM\"}";*/
			String json = "{\"address\": \"陕西省凌云县风雷震风云路120号\",  \"credit\": 47.8,  "
			+ "\"name\": \"王三春\",  \"oid\": 2008, \"sex\": true,  \"birthday\": \"1988-07-01\", \"com\":{\"nameCom\":1}}";
		final JsonParser jsonParser = new JsonParser();
		final JsonElement e = jsonParser.parse(json);
		final JsonObject jsonObj = e.getAsJsonObject();
		System.out.println(e.isJsonArray());
		System.out.println(e.isJsonObject());
		log.info("testJsonObject =====> " + jsonObj.get("name").getAsString());
		Set<Map.Entry<String, JsonElement>> set = jsonObj.entrySet();
		Iterator<Map.Entry<String, JsonElement>> it = set.iterator();
		Map.Entry<String, JsonElement> entry = null;
		while (it.hasNext())
		{
			entry = it.next();
			System.out.println(entry.getKey() + ": " + entry.getValue() + ", " + entry.getValue().isJsonPrimitive());
			
		}
		
		} catch (Exception e) {
			log.error("testJsonObject =====> ", e);
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
