/**
 * 描述: 
 * JacksonUtilTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.jackson;

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
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.hua.bean.User;
import com.hua.bean.UserBean;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.FileUtil;
import com.hua.util.JacksonUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JacksonUtilTest
 */
public final class JacksonUtilTest extends BaseTest {

	private String json = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
			+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28 20:57:58\",\"lastLoginIp\":\"192.168.5.66\"}";

	
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
			
			User user = JacksonUtil.readValue(inputStream, User.class);
			
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
			
			List<User> users =  JacksonUtil.readValues(inputStream, User.class);
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
			
			User user = JacksonUtil.readValueByPath(inputStream, path, User.class);
			
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
			
			List<User> users = JacksonUtil.readValuesByPath(inputStream, path, User.class);
			
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
			
			JacksonUtil.writeValueInclude(file, user, includeFields);
			
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
			
			JacksonUtil.writeValueExclude(file, user, excludeFields);
			
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
	public void testReadValue2() {
		try {
			String json = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28 20:57:58\",\"lastLoginIp\":\"192.168.5.66\"}";
			User user = JacksonUtil.readValue(json, User.class);
			
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
	public void testReadValueSetDf() {
		try {
			String json = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28\",\"lastLoginIp\":\"192.168.5.66\"}";
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			*/
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			User user = JacksonUtil.readValue(json, User.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testReadValueSetDf =====> ", e);
		}
	}
	
	/**
	 * 根据字段名称读取为JsonNode，ObjectReader.treeAsTokens()
	 * 得到JsonParser，然后ObjectReader.readValue(JsonParser jp, Class<?>)
	 */
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadTree() {
		try {
			/**
			 * null 表示空值
			 */
			String json = "{\"e\":{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28 12:01:01\","
					+ "\"lastLoginIp\":\"192.168.5.66\", \"emptyField\": null}, \"c\":null}";
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			*/
			/**
			 * 根据字段名称读取为JsonNode，ObjectReader.treeAsTokens()
			 * 得到JsonParser，然后ObjectReader.readValue(JsonParser jp, Class<?>)
			 */
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			*/
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//// JacksonUtil.setDateFormat(format);
			User user = JacksonUtil.readValueByPath(json, "e", User.class);	
			
		} catch (Exception e) {
			log.error("testReadTree =====> ", e);
		}
	}		

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadSomeProperty() {
		try {
			/**
			 * null 表示空值
			 */
			String json = "{\"e\":{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28\","
					+ "\"lastLoginIp\":\"192.168.5.66\"}, \"c\":null}";
			/**
			 * 根据字段名称读取为JsonNode，ObjectReader.treeAsTokens()
			 * 得到JsonParser，然后ObjectReader.readValue(JsonParser jp, Class<?>)
			 */
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			User user = JacksonUtil.readValueByPath(json, "e", User.class);
			
			System.out.println(user.toString());
			
			
		} catch (Exception e) {
			log.error("testReadSomeProperty =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteValue() {
		try {
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			/**
			 * 定制化日期输出
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			String json = JacksonUtil.writeAsString(user);
			System.out.println(json);
		} catch (Exception e) {
			log.error("testWriteValue =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNode() {
		try {
			String json = "{\"e\":{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28\","
					+ "\"lastLoginIp\":\"192.168.5.66\"}, \"c\":\"content\"}";
			JsonNode node = null;
			String fieldName = "c";
			ObjectMapper objectMapper = new ObjectMapper();
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			// 对象读取器
			ObjectReader objectReader = objectMapper.reader();
			// 读取为结点
			node = objectReader.readTree(json);
			fieldName = "c";
			// 根据字段名称，找出下一级的结点
			node = node.findValue(fieldName);
			String value = objectMapper.treeToValue(node, String.class);
			
			log.info("testNode =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testNode =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testFindPath() {
		try {
			String json = "{\"e\":{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28\","
					+ "\"lastLoginIp\":\"192.168.5.66\"}, \"c\":\"content\"}";
			JsonNode node = null;
			String fieldName = "c";
			ObjectMapper objectMapper = new ObjectMapper();
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			// 对象读取器
			ObjectReader objectReader = objectMapper.reader();
			// 读取为结点
			node = objectReader.readTree(json);
			fieldName = "c";
			// 根据字段名称，找出下一级的结点
			node = node.findPath("c");
			String value = objectMapper.treeToValue(node, String.class);
			
			log.info("testFindPath =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testFindPath =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadValueByPath5() {
		try {
			String json = "{\"e\":{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28\","
					+ "\"lastLoginIp\":\"192.168.5.66\"}, \"c\":\"content\"}";
			JsonNode node = null;
			String path = "e.id";
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			String value = JacksonUtil.readValueByPath(json, path, String.class);
			
			log.info("testReadValueByPath =====> value = " + value);
			
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
	public void testReadValueByPath2() {
		try {
			String json = "{\"e\":{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28\","
					+ "\"lastLoginIp\":\"192.168.5.66\", \"a\":{\"flag\":true}}, \"c\":\"content\"}";
			JsonNode node = null;
			String path = "e.lastLoginTime";
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			Date value = JacksonUtil.readValueByPath(json, path, Date.class);
			
			log.info("testReadValueByPath =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testReadValueByPath2 =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadValueByPath3() {
		try {
			String json = "{\"e\":{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28\","
					+ "\"lastLoginIp\":\"192.168.5.66\", \"a\":{\"flag\":true}}, \"c\":\"content\"}";
			JsonNode node = null;
			String path = "c";
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			String value = JacksonUtil.readValueByPath(json, path, String.class);
			
			log.info("testReadValueByPath =====> value = " + value);
			
		} catch (Exception e) {
			log.error("testReadValueByPath3 =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadValuesByPath10() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/Users-Test.json", true));
			List<User> users = JacksonUtil.readValuesByPath(inputStream, "array", User.class);
			
			System.out.println(users.get(0).toString());
			System.out.println(users.get(1).toString());
			
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
	public void testReadValuesByPath2() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/Users-Test.json", true));
			List<User> users = JacksonUtil.readValuesByPath(inputStream, "array", User.class);
			
			System.out.println(users.get(0).toString());
			System.out.println(users.get(1).toString());
			
		} catch (Exception e) {
			log.error("testReadValuesByPath2 =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadFromFile() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			File file = new File(ProjectUtil.getAbsolutePath("/doc/User.json", true));
			User user = JacksonUtil.readValue(file, User.class);
			
			System.out.println(user.toString());
			
			
		} catch (Exception e) {
			log.error("testReadFromFile =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadFromInputStream() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/User.json", true));
			User user = JacksonUtil.readValue(inputStream, User.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testReadFromInputStream =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadFromReader() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			InputStream inputStream = new FileInputStream(ProjectUtil.getAbsolutePath("/doc/User.json", true));
			Reader reader = new InputStreamReader(inputStream);
			User user = JacksonUtil.readValue(reader, User.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testReadFromReader =====> ", e);
		}
	}		

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadFromUrl() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			URL url = new URL("file:///" + ProjectUtil.getAbsolutePath("/doc/User.json", true));
			User user = JacksonUtil.readValue(url, User.class);
			
			System.out.println(user.toString());
			
			
		} catch (Exception e) {
			log.error("testReadFromUrl =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadFromByteArray() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			File file = new File(ProjectUtil.getAbsolutePath("/doc/User.json", true));
			byte[] array = FileUtil.getByteArray(file);
			User user = JacksonUtil.readValue(array, User.class);
			
			System.out.println(user.toString());
			
			
		} catch (Exception e) {
			log.error("testReadFromByteArray =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadValues2() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			File file = new File(ProjectUtil.getAbsolutePath("/doc/Users.json", true));
			List<User> users = JacksonUtil.readValues(file, User.class);
			
			System.out.println(users.get(0).toString());
			System.out.println(users.get(1).toString());
			
		} catch (Exception e) {
			log.error("testReadValues =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: json少字符串的时候，该字段在对象中为空，
	 * 并不影响解析
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJsonLackField() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			File file = new File(ProjectUtil.getAbsolutePath("/doc/User.json", true));
			User user = JacksonUtil.readValue(file, User.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testJsonLackField =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJavaObjectLackField() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			File file = new File(ProjectUtil.getAbsolutePath("/doc/UserObjectLackField.json", true));
			User user = JacksonUtil.readValue(file, User.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testJavaObjectLackField =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testEmptyString1() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			File file = new File(ProjectUtil.getAbsolutePath("/doc/User.json", true));
			User user = JacksonUtil.readValue(file, User.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testEmptyString1 =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUserBean() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			File file = new File(ProjectUtil.getAbsolutePath("/doc/UserBean.json", true));
			UserBean user = JacksonUtil.readValue(file, UserBean.class);
			
			System.out.println(user.toString());
			
		} catch (Exception e) {
			log.error("testUserBean =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteString() {
		try {

			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			UserBean user = new UserBean();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			//user.setPassword("123456");
			user.setPassword("");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			user.setUsers(new ArrayList<User>());
			String json = JacksonUtil.writeAsString(user);
			
			System.out.println(json);
		} catch (Exception e) {
			log.error("testWriteString =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteString2() {
		try {

			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			UserBean user = new UserBean();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			//user.setPassword("123456");
			user.setPassword("");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			user.setUsers(new ArrayList<User>());
			String json = JacksonUtil.writeAsString(user);
			
			System.out.println(json);
		} catch (Exception e) {
			log.error("testWriteString2 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteBytes() {
		try {
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			UserBean user = new UserBean();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			
			byte[] data = JacksonUtil.writeAsBytes(user);
			
			System.out.println(data.length);
		} catch (Exception e) {
			log.error("testWriteBytes =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteIncludeAsString() {
		try {

			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			UserBean user = new UserBean();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			//user.setPassword("123456");
			user.setPassword("");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			user.setUsers(new ArrayList<User>());
			String json = JacksonUtil.writeIncludeAsString(user, "id", "username");
			
			System.out.println(json);
		} catch (Exception e) {
			log.error("testWriteIncludeAsString =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWriteExcludeAsString() {
		try {

			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			UserBean user = new UserBean();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			//user.setPassword("123456");
			user.setPassword("");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			user.setUsers(new ArrayList<User>());
			String json = JacksonUtil.writeExcludeAsString(user, "id", "username");
			
			System.out.println(json);
		} catch (Exception e) {
			log.error("testWriteExcludeAsString =====> ", e);
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
			/**
			 * 设置日期格式，若有特殊日期时间格式
			 * 在调用读、写方法之前，先调用此方法设置格式
			 */
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// JacksonUtil.setDateFormat(format);
			File file = new File(ProjectUtil.getAbsolutePath("/doc/User.json", true));
			User user = JacksonUtil.readValue(file, User.class);
			
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
	public void testTemp() {
		try {
			String json = "{\"id\":\"20150628\",\"username\":\"张三\", \"nickname\":\"少年张三丰\","
					+ "\"password\":\"123456\",\"valid\":true,\"lastLoginTime\":\"2015-06-28\",\"lastLoginIp\":\"192.168.5.66\"}";
			System.out.println(json);
			
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
