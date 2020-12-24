/**
 * 描述: 
 * JSONObjectTest.java
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

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Set;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.bean.User;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * JSONObjectTest
 */
public final class JSONObjectTest extends BaseTest {

	/**
	 * JSONObject
	 * 1) JSON对象，通过字符串 和 对象/map 来进行转换
	 * 可以从字符串或对象/map中直接获取当前根下的结点，
	 * 根据已经知道的类型来调用相应的方法.
	 * 可以从对象或字符串
	 * 
	 * 2) 获取值
	 * get获取指定的值，若不存在则抛出运行时异常，
	 * opt没有默认值的调用有默认值的方法，默认值具体看源码，
	 * opt有默认值的try-catch中调用get方法，然后捕获异常后直接返回默认值.
	 * 
	 * 调用关系: get调用opt，optXx调用getXx，getXx调用get获取值
	 * 
	 * 3) 填充值
	 * 
	 * 4) 判断
	 * 是否是空值、是否是
	 * 
	 * 
	 * 10)静态工具方法
	 * 从字符串、对象/map中获取当前根下的字段名数组
	 * 类型转换、json字符串和对象转换
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJSONObject() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testJSONObject =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJSONObjectBuild() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testJSONObjectBuild =====> ", e);
		}
	}		

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPut() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			jsonObject.put("user", user);
			
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testPut =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPutOpt() {
		try {
			/**
			 * putOpt 和 一般put的区别
			 * putOpt规定key和value都不能为空
			 * put规定key不能null，value可以为null
			 * 只有符合规定的key/value对才会放入JSONObject
			 * 对象中.
			 */
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			Object obj = null;
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("emptyObj", obj);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.putOpt("nullField", null);
			//jsonObject.putOpt("nullField", Double.NaN);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testPutOpt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPutOnce() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			// 第一次调用 putOnce方法
			jsonObject.putOnce("name", "zhangsan");
			
			/*
			 * 第二次调用 putOnce方法会抛异常，
			 * org.json.JSONException: Duplicate key "name"
			 * 可以调用put方法去覆盖值
			 * 
			 */
			//jsonObject.putOnce("name", "zhangsan");
			
			//jsonObject.put("name", "zhangsan_override");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testPutOnce =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGet() {
		try {
			/**
			 * get方法 key为空、key不存在、获取的值为空，都会抛异常
			 * 其中key不存在或者获取值为空，都抛出key没找到异常
			 */
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			System.out.println(jsonObject.get("name"));
			System.out.println(jsonObject.getString("name"));
			System.out.println(jsonObject.getInt("age"));
			System.out.println(jsonObject.getBoolean("sex"));
			System.out.println(jsonObject.get("birthday"));
			System.out.println(jsonObject.getDouble("salary"));
			// 抛异常: org.json.JSONException: JSONObject["key_no_exist"] not found.
			System.out.println(jsonObject.get("key_no_exist"));
			//System.out.println(jsonObject.getDouble("key_no_exist"));
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			//System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testOpt() {
		try {
			/**
			 * optXx 获取
			 */
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			System.out.println(jsonObject.opt("name"));
			System.out.println(jsonObject.optString("name"));
			System.out.println(jsonObject.optInt("age"));
			System.out.println(jsonObject.optBoolean("sex"));
			// 返回默认值
			System.out.println(jsonObject.optBoolean("key_no_exist1"));
			System.out.println(jsonObject.opt("birthday"));
			System.out.println(jsonObject.optDouble("salary"));
			// 不存在 直接返回 null
			System.out.println(jsonObject.opt("key_no_exist2"));
			//System.out.println(jsonObject.getDouble("key_no_exist"));
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			//System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testOpt =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testOptWithDefault() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			//jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			System.out.println(jsonObject.opt("name"));
			System.out.println(jsonObject.optString("name", "default"));
			System.out.println(jsonObject.optInt("age", 1));
			System.out.println(jsonObject.optBoolean("sex", false));
			// 返回默认值
			System.out.println(jsonObject.optBoolean("key_no_exist1", true));
			System.out.println(jsonObject.opt("birthday"));
			System.out.println(jsonObject.optDouble("salary"));
			// 不存在 直接返回 null
			System.out.println(jsonObject.opt("key_no_exist2"));
			System.out.println(jsonObject.optString("address", "默认地址"));
			//System.out.println(jsonObject.getDouble("key_no_exist"));
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			//System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testOptWithDefault =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWrite() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			//System.out.println(jsonObject.toString(2));
			OutputStream outputStream = System.out;
			Writer writer = new OutputStreamWriter(outputStream);
			
			jsonObject.write(writer);
			
			writer.flush();
			
			writer.close();
		} catch (Exception e) {
			log.error("testWrite =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRemove() {
		try {
			
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			Object removedValue = jsonObject.remove("address");
			log.info("testRemove =====> removedValue = " + removedValue);
			System.out.println(jsonObject.optString("address", "默认地址"));
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
		} catch (Exception e) {
			log.error("testRemove =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAccumulate() {
		try {
			/**
			 * 调用 accumulate 之后，原来是一个对象的会构成一个数组
			 * 之前没有存在的，会构成单个值对象
			 * 不会去重，应该是用List来实现的
			 */
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			jsonObject.accumulate("name", "_add_");
			jsonObject.accumulate("name_new", "_add_");
			jsonObject.accumulate("age", 29);
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testAccumulate =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAppend() {
		try {
			/**
			 * append，会在之前的基础上添加值，
			 * 注意，目标类型，必须是JSONArray才可以append，
			 * 否则会抛异常: 
			 * org.json.JSONException: JSONObject[name] is not a JSONArray.
			 */
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			// 执行accumulate之后，name会变成 JSONArray
			jsonObject.accumulate("name", "_add_");
			jsonObject.append("name", "_value_append_");
			//jsonObject.append("age", 13);
			//jsonObject.append("sex", false);
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testAppend =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testIncrement() {
		try {
			/**
			 * increment 目标key若不存在，则默认放入value为1的对
			 * 若key存在 key必须是 Integer/Float/Double/Long类型
			 */
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			// 不是 Integer/Float/Double/Long类型
			//jsonObject.increment("name");
			
			jsonObject.increment("age");
			
			// 不存在，默认是 1
			jsonObject.increment("un_exist_key");
			
			// 不是 Integer/Float/Double/Long类型
			//jsonObject.increment("sex");
			
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testIncrement =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimilar() {
		try {
			/**
			 * 检查2个JSONObject是否相似
			 * key和value必须都相似
			 */
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			JSONObject jsonObject2 = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject2.put("name", "zhangsan");
			jsonObject2.put("age", 25);
			jsonObject2.put("sex", true);
			jsonObject2.put("birthday", new Date());
			jsonObject2.put("salary", 12.52);
			jsonObject2.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject2.put("name", "zhangsan_override");
			
			log.info("testSimilar =====> " + jsonObject.similar(jsonObject2));
			
		} catch (Exception e) {
			log.error("testSimilar =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testKey() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			Set<String> names = jsonObject.keySet();
			
			for (String name : names)
			{
				System.out.print(name + ",");
			}
			System.out.println();
			
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			//System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testKey =====> ", e);
		}
	}	

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHas() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			log.info("testHas =====> " + jsonObject.has("name"));
			log.info("testHas =====> " + jsonObject.has("name1"));
		} catch (Exception e) {
			log.error("testHas =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLength() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.length());
			
		} catch (Exception e) {
			log.error("testLength =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testIsNull() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			jsonObject.put("name", "");
			//System.out.println(jsonObject.toString());
			// 判断是否为 null，key不存在，或值为null 都会是 true
			System.out.println(jsonObject.isNull("name"));
			System.out.println(jsonObject.isNull("name1"));
			
		} catch (Exception e) {
			log.error("testIsNull =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testQuote() {
		try {
			/**
			 * 字符串转义
			 * 引号、
			 * 斜杠转义成 \/
			 * 输出一个 双引号 围起来的 字符串
			 * 类似: "<a>\"&$%@!()*<\/a>"
			 * 
			 */
			String value = JSONObject.quote("<a>\"&$%@!()*</a>");
			System.out.println(value);
			
		} catch (Exception e) {
			log.error("testQuote =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testStringToValue() {
		try {
			/**
			 * JSONObject.stringToValue
			 * 布尔值、数值
			 */
			log.info("testStringToValue =====> " + JSONObject.stringToValue("false"));
			log.info("testStringToValue =====> " + JSONObject.stringToValue("12.5"));
			log.info("testStringToValue =====> " + JSONObject.stringToValue("22"));
			// 无法转换，直接返回原始字符串
			log.info("testStringToValue =====> " + JSONObject.stringToValue("AD"));
			log.info("testStringToValue =====> " + JSONObject.stringToValue("afasd"));
			
			log.info("testStringToValue =====> " + JSONObject.stringToValue("false"));
			
		} catch (Exception e) {
			log.error("testStringToValue =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testValueToString() {
		try {
			JSONObject jsonObject = new JSONObject();

			
		} catch (Exception e) {
			log.error("testValueToString =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWrap() {
		try {
		/**
		 * 包装，
		 * 常用: 可以把 map/JavaBean 包装为 JSONObject
		 * 数组、集合 包装为 JSONArray
		 * 不支持基本类型
		 */
			
			User user = new User();
			user.setId("20150628");
			user.setUsername("张三");
			user.setNickname("少年张三丰");
			user.setPassword("123456");
			user.setValid(false);
			user.setLastLoginTime(DateTimeUtil.getTimestamp());
			user.setLastLoginIp("192.168.5.66");
			/**
			 * 包装为 JSONObject
			 */
			JSONObject jsonObject = (JSONObject) JSONObject.wrap(user);
			
			System.out.println(jsonObject.toString());
			
		} catch (Exception e) {
			log.error("testWrap =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDoubleToString() {
		try {
			log.info("testDoubleToString =====> " + JSONObject.doubleToString(11.9));
			log.info("testDoubleToString =====> " + JSONObject.doubleToString(41.3));
		} catch (Exception e) {
			log.error("testDoubleToString =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetNames1() {
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
			 * TODO 无法获取，原因未知
			 */
			String[] names = JSONObject.getNames(user);
			for (String name : names)
			{
				System.out.print(name + ",");
			}
			System.out.println();
		} catch (Exception e) {
			log.error("testGetNames1 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetNames2() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			
			String[] names = JSONObject.getNames(jsonObject);
			for (String name : names)
			{
				System.out.print(name + ",");
			}
			System.out.println();
			
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			//System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testGetNames2 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testNumberToString() {
		try {
			System.out.println(JSONObject.numberToString(124545));
			System.out.println(JSONObject.numberToString(12.56));
			System.out.println(JSONObject.numberToString(11L));
			
			
		} catch (Exception e) {
			log.error("testNumberToString =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testToString1() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			System.out.println(jsonObject.toString());
			
		} catch (Exception e) {
			log.error("testToString1 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testToString2() {
		try {
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
		} catch (Exception e) {
			log.error("testToString2 =====> ", e);
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
			JSONObject jsonObject = new JSONObject();
			/**
			 * 放入是没有顺序的
			 */
			jsonObject.put("name", "zhangsan");
			jsonObject.put("age", 25);
			jsonObject.put("sex", true);
			jsonObject.put("birthday", new Date());
			jsonObject.put("salary", 12.52);
			jsonObject.put("address", "广东省广州市天河区科韵路123号");
			// 后面再设置 会覆盖之前的值
			jsonObject.put("name", "zhangsan_override");
			//System.out.println(jsonObject.toString());
			// 设置 缩进系数
			System.out.println(jsonObject.toString(2));
			
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
