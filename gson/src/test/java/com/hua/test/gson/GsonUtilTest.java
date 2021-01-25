/**
 * 描述: 
 * GsonUtilTest.java
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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.GsonUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * GsonUtilTest
 */
public final class GsonUtilTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUtilTotal() {
		try {
			
			testReadValue();
			testReadValues();
			testReadValueByPath();
			testReadValuesByPath();
			
			testWriteIncludeFile();
			testWriteExcludeFile();
			
		} catch (Exception e) {
			log.error("testUtilTotal =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadValue() {
		try {
			String filePath = ProjectUtil.getAbsolutePath("/doc/UserReadTest.json", true);
			InputStream inputStream = new FileInputStream(filePath);
			
			User user = GsonUtil.readValue(inputStream, User.class);
			
			System.out.println(user.toString());
		} catch (Exception e) {
			log.error("testReadValue =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadValues() {
		try {
			String filePath = ProjectUtil.getAbsolutePath("/doc/UsersReadTest.json", true);
			InputStream inputStream = new FileInputStream(filePath);
			
			List<User> users =  GsonUtil.readValues(inputStream, User.class);
			User user1 = users.get(0);
			
			System.out.println(user1.toString());
		} catch (Exception e) {
			log.error("testReadValues =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadValueByPath() {
		try {
			String filePath = ProjectUtil.getAbsolutePath("/doc/UserReadByPathTest.json", true);
			InputStream inputStream = new FileInputStream(filePath);
			
			String path = "user2.user3";
			
			User user = GsonUtil.readValueByPath(inputStream, path, User.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testReadValueByPath =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadValuesByPath() {
		try {
			String filePath = ProjectUtil.getAbsolutePath("/doc/UserReadByPathTest.json", true);
			InputStream inputStream = new FileInputStream(filePath);
			
			String path = "uo.users";
			
			List<User> users = GsonUtil.readValuesByPath(inputStream, path, User.class);
			
			User user1 = users.get(0);
			
			System.out.println(user1.toString());
			
		} catch (Exception e) {
			log.error("testReadValuesByPath =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteIncludeFile() {
		try {
			String filePath = ProjectUtil.getAbsolutePath("/doc/UserWriteIncludeTest.json", true);
			File file = new File(filePath);
			String[] includeFields = {"id", "password"};
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			GsonUtil.writeValueInclude(file, user, includeFields);
			
		} catch (Exception e) {
			log.error("testWriteIncludeFile =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteExcludeFile() {
		try {
			String filePath = ProjectUtil.getAbsolutePath("/doc/UserWriteExcludeTest.json", true);
			File file = new File(filePath);
			String[] excludeFields = {"id", "password"};
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			GsonUtil.writeValueExclude(file, user, excludeFields);
			
		} catch (Exception e) {
			log.error("testWriteExcludeFile =====> ", e);
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
