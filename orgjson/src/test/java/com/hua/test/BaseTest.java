/**
 * 描述: 
 * BaseTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hua.log.BaseLog;

/**
 * 描述: 测试基类
 * 包含多个测试示例
 * 
 * @author qye.zheng
 * BaseTest
 */
//@RunWith(JUnitPlatform.class)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("BaseTest")
public class BaseTest extends BaseLog {
   public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    public static GsonBuilder gsonBuilder = new GsonBuilder();
    
    public static Gson gson = new Gson();
    
    public static JsonGenerator jsonGenerator = null;
    
    /* 共用 对象映射器 */
    public static ObjectMapper objectMapper = new ObjectMapper();
    
    public static ObjectReader objectReader = objectMapper.reader();
    
    public static ObjectWriter objectWriter = objectMapper.writer();    
    
    protected JSONObject jsonObject;
    
    protected String json;
    
	/**
	 * 
	 * 描述: [所有测试]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeClass")
	@BeforeAll
	public static void beforeClass() {
		System.out.println("beforeClass()");
	}
	
	/**
	 * 
	 * 描述: [所有测试]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterClass")
	@AfterAll
	public static void afterClass() {
		System.out.println("afterClass()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}

}
