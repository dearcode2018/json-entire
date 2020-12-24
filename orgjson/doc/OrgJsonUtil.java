/**
 * OrgJsonUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.hua.constant.Constant;
import com.hua.constant.FormatConstant;

/**
 * OrgJsonUtil
 * 描述: Org Json - 工具类
 * @author  qye.zheng
 */
public final class OrgJsonUtil
{

	// 日期时间格式-默认采用
	private static DateFormat dateTimeFormat = 
			new SimpleDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
	
	/* 路径拆分正则表达式 */
	private static final String PATH_SPLIT_REGEX = "\\.";
	
	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private OrgJsonUtil()
	{
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param jsonString
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject read(final String jsonString)
	{
		final JSONTokener tokener = new JSONTokener(jsonString);
		final JSONObject jsonObject = new JSONObject(tokener);
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param inputStream
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject read(final InputStream inputStream)
	{
		final JSONTokener tokener = new JSONTokener(inputStream);
		final JSONObject jsonObject = new JSONObject(tokener);
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param reader
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject read(final Reader reader)
	{
		final JSONTokener tokener = new JSONTokener(reader);
		final JSONObject jsonObject = new JSONObject(tokener);
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param file
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject read(final File file)
	{
		InputStream inputStream = null;
		JSONObject jsonObject = null;
		try {
			inputStream = new FileInputStream(file);
			jsonObject = read(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param url
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject read(final URL url)
	{
		InputStream inputStream =null;
		JSONObject jsonObject = null;
		try {
			inputStream = url.openStream();
			jsonObject = read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param inputStream
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject read(final byte[] data)
	{
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return read(jsonString);
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param jsonString
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject readValueByPath(final String jsonString, final String path)
	{
		final JSONTokener tokener = new JSONTokener(jsonString);
		// 读取为结点
		JSONObject jsonObject = new JSONObject(tokener);
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		for (String fieldName : fieldNames)
		{
			// 读取为结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param inputStream
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject readValueByPath(final InputStream inputStream, final String path)
	{
		final JSONTokener tokener = new JSONTokener(inputStream);
		JSONObject jsonObject = new JSONObject(tokener);
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		for (String fieldName : fieldNames)
		{
			// 读取为结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param reader
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject readValueByPath(final Reader reader, final String path)
	{
		final JSONTokener tokener = new JSONTokener(reader);
		JSONObject jsonObject = new JSONObject(tokener);
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		for (String fieldName : fieldNames)
		{
			// 读取为结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param file
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject readValueByPath(final File file, final String path)
	{
		InputStream inputStream = null;
		JSONObject jsonObject = null;
		try {
			inputStream = new FileInputStream(file);
			jsonObject = readValueByPath(inputStream, path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param url
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject readValueByPath(final URL url, final String path)
	{
		InputStream inputStream =null;
		JSONObject jsonObject = null;
		try {
			inputStream = url.openStream();
			jsonObject = readValueByPath(inputStream, path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param data
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject readValueByPath(final byte[] data, final String path)
	{
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return readValueByPath(jsonString, path);
	}	
	
	/* read value 返回单个对象 ================================= */
	
	/**
	 * JSON中有的字段，而JavaBean中没有，则忽略不会抛异常
	 * 反之，JavaBean中有而JSON中没有的字段也不会抛异常
	 * 只有类型转换设置时会发生异常，例如 类型不兼容、不匹配
	 */
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final String jsonString, final Class<T> clazz)
	{
		T t = null;
		try
		{
			// json对象
			final JSONObject jsonObject = new JSONObject(jsonString);
			// 字段名数组
			final String[] fieldNames = JSONObject.getNames(jsonObject);
			t = clazz.newInstance();
			Method setter = null;
			String setterName = null;
			Class<?> paramType = null;
			JSONObject subJsonObject = null;
			Class<?> subClazz = null;
			for (String name : fieldNames)
			{
				
		
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}
	
	/**
	 * 
	 * @description 
	 * @param clazz
	 * @param target
	 * @param name
	 * @param value
	 * @author qianye.zheng
	 */
	private static final void invoke(final Class<?> clazz, final Object target, final String name, final Object value)
	{
		Method setter = null;
		String setterName = null;
		Class<?> paramType = null;
		JSONObject subJsonObject = null;
		Class<?> subClazz = null;
		try {
			/*
			 * 组装 setter方法名
			 * 可以尝试 setXx
			 */
			setterName = "set" + StringUtil.capitalize(name);
			// 获取指定字段的setter方法的参数类型
			paramType = ReflectUtil.getSetterParameterType(clazz, setterName);
			setter = clazz.getMethod(setterName, paramType);
			if (paramType.isPrimitive() || paramType == String.class)
			{ // 基本类型 + String 类型
				setter.invoke(target, value);
			} else
			{ // 引用类型 (暂时不包括 数组类型)
				subClazz = paramType;
				Object subObject = subClazz.newInstance();
				//invoke(subClazz, subObject, );
				//setter.invoke(target, jsonObject.opt(name));
			}
		} catch (Exception e) {

		}
	}
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param file
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final File file, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param inputStream
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final InputStream inputStream, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param reader
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final Reader reader, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param url
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final URL url, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final byte[] data, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}

	/* read values 返回List ================================= */	
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValues(final String jsonString, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param file
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValues(final File file, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param inputStream
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValues(final InputStream inputStream, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param reader
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValues(final Reader reader, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param url
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValues(final URL url, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValues(final byte[] data, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static final <T> T read(final String jsonString, final Class<T> clazz)
	{
		T t = null;
		try
		{

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return t;
	}
	
	
	
	/* write object ================================= */
	
	
	/* TODO 读 特定功能  ================================= */		
	
	/* TODO 写 特定功能  ================================= */

	
	
	/* TODO  公共部分  ================================= */
	/**
	 * 
	 * 描述: 设置日期格式，若有特殊日期时间格式
	 * 在调用读、写方法之前，先调用此方法设置格式
	 * 该设置会影响全局日期解析
	 * @author  qye.zheng
	 * @param dateFormat
	 */
	public static final void setDateFormat(final DateFormat dateFormat)
	{
		dateTimeFormat = dateFormat;
	}	
	
}
