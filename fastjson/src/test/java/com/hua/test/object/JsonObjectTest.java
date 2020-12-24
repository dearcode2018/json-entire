/**
 * 描述: 
 * JsonObjectTest.java
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

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.IOUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JsonObjectTest
 */
public final class JsonObjectTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonObject() {
		try {
			
			
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
	public void testToJsonString() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			// 修改该全局变量，注意 该变量只是用来控制反序列化用，某些序列化用此变量无效
			//JSON.DEFFAULT_DATE_FORMAT = "yyyyMMdd HH:mm:ss";
			String jsonString = JSON.toJSONString(user);
			// 优美格式输出
			jsonString = JSON.toJSONString(user, true);

			// 带有日期时间格式
			//jsonString =JSON.toJSONStringWithDateFormat(user, FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			System.out.println(jsonString);
			
		} catch (Exception e) {
			log.error("testToJsonString =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteTo() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			JSON.DEFFAULT_DATE_FORMAT = "yyyyMMdd HH:mm:ss";
			JSON json = new JSONObject();

			Appendable appendable = new OutputStreamWriter(System.out);
			json.writeJSONString(appendable);
			
			// 修改该全局变量，注意 该变量只是用来控制反序列化用，某些序列化用此变量无效
			//JSON.DEFFAULT_DATE_FORMAT = "yyyyMMdd HH:mm:ss";
			String jsonString = JSON.toJSONString(user);
			// 优美格式输出
			jsonString = JSON.toJSONString(user, true);

			// 带有日期时间格式
			//jsonString =JSON.toJSONStringWithDateFormat(user, FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			System.out.println(jsonString);
			
		} catch (Exception e) {
			log.error("testWriteTo =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testParseObject() {
		try {
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/User.json", true));
			String jsonString = IOUtil.getString(inputStream);
			// 修改该全局变量，注意 该变量只是用来控制反序列化用，某些序列化用此变量无效
			JSON.DEFFAULT_DATE_FORMAT = "yyyyMMdd HH:mm:ss";
			User user = JSON.parseObject(jsonString, User.class);
			
			System.out.println(user.toString());
			
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
