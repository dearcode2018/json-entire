/**
 * FastJsonUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hua.constant.Constant;
import com.hua.constant.FormatConstant;
import com.hua.filter.PropertyNameExcludeFilter;
import com.hua.filter.PropertyNameIncludeFilter;

/**
 * FastJsonUtil
 * 描述: Fast Json - 工具类
 * @author  qye.zheng
 */
public final class FastJsonUtil
{
	
	// 日期时间格式-默认采用
/*	private static final DateFormat dateTimeFormat = 
			new SimpleDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);*/

	/* 路径拆分正则表达式 */
	private static final String PATH_SPLIT_REGEX = "\\.";

	static
	{
		// 设置默认的日期时间格式
		setDefaultDateFormat();
		
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
	}
	
	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private FastJsonUtil()
	{
	}

	/* TODO  公共部分  ================================= */	
	
	/**
	 * 
	 * @description 设置默认的日期时间格式
	 * 该设置会影响全局反序列化的格式解析
	 * @author qianye.zheng
	 */
	public static final void setDefaultDateFormat()
	{
		JSON.DEFFAULT_DATE_FORMAT = FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss;
	}
	
	/**
	 * 
	 * @description 设置日期时间格式
	 * 该设置会影响全局反序列化的格式解析
	 * @param pattern
	 * @author qianye.zheng
	 */
	public static final void setDateFormat(final String pattern)
	{
		JSON.DEFFAULT_DATE_FORMAT = pattern;
	}
	
	/* read value 返回单个对象 ================================= */
	
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
		final T t = JSON.parseObject(jsonString, clazz);
	
		return t;
	}
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param data
	 * @return
	 */
	public static final <T> T readValue(final byte[] data, final Class<T> clazz)
	{
		final T t = JSON.parseObject(data, clazz);
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final File file, final Class<T> clazz)
	{
		final String jsonString = FileUtil.getString(file);
		T t = JSON.parseObject(jsonString, clazz);
		
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
		try {
			String jsonString = IOUtil.getString(inputStream);
			t = JSON.parseObject(jsonString, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(inputStream);
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
		try {
			final String jsonString = IOUtil.getString(reader);
			t = JSON.parseObject(jsonString, clazz);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(reader);
		}
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param url 链接对象
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final URL url, final Class<T> clazz)
	{
		InputStream inputStream = null;
		String jsonString = null;
		T t = null;
		try {
			inputStream = url.openStream();
			jsonString = IOUtil.getString(inputStream);
			t = JSON.parseObject(jsonString, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	/* read values 返回集合对象 ================================= */
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValues(final String jsonString, final Class<T> clazz)
	{
		final List<T> list = JSON.parseArray(jsonString, clazz);
		
		return list;
	}
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValues(final byte[] data, final Class<T> clazz)
	{
		List<T> list = null;
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
			list = JSON.parseArray(jsonString, clazz);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValues(final File file, final Class<T> clazz)
	{
		final String jsonString = FileUtil.getString(file);
		final List<T> list = JSON.parseArray(jsonString, clazz);
		
		return list;
	}

	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param inputStream
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValues(final InputStream inputStream, final Class<T> clazz)
	{
		List<T> list = null;
		try {
			final String jsonString = IOUtil.getString(inputStream);
			list = JSON.parseArray(jsonString, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(inputStream);
		}
		
		return list;
	}
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param reader
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValues(final Reader reader, final Class<T> clazz)
	{
		final String jsonString = IOUtil.getString(reader);
		final List<T> list = JSON.parseArray(jsonString, clazz);
		
		return list;
	}
	
	/**
	 * 
	 * 描述: 解析为对象集合
	 * @author  qye.zheng
	 * @param url 链接对象
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValues(final URL url, final Class<T> clazz)
	{
		List<T> list = null;
		InputStream inputStream = null;
		String jsonString = null;
		try {
			inputStream = url.openStream();
			jsonString = IOUtil.getString(inputStream);
			JSON.parseArray(jsonString, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	/* TODO 读 特定功能  ================================= */	
	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param jsonString
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValueByPath(final String jsonString, final String path, final Class<T> clazz)
	{
		T t = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		JSONObject jsonObject = JSON.parseObject(jsonString);
		
		for (String fieldName : fieldNames)
		{
			if (null == jsonObject || jsonObject.isEmpty())
			{
				return t;
			}
			// 读取为结点
			jsonObject = jsonObject.getJSONObject(fieldName);
		}
		// 解析结点为一个对象
		t = JSON.toJavaObject(jsonObject, clazz);

		return t;
	}

	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param inputStream
	 * @param path 路径，例如 a.b.c
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValueByPath(final InputStream inputStream, final String path, final Class<T> clazz)
	{
		T t = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		final String jsonString = IOUtil.getString(inputStream);
		// 对象读取器
		JSONObject jsonObject = JSON.parseObject(jsonString);
		
		for (String fieldName : fieldNames)
		{
			if (null == jsonObject || jsonObject.isEmpty())
			{
				return t;
			}
			// 读取为结点
			jsonObject = jsonObject.getJSONObject(fieldName);
		}
		// 解析结点为一个对象
		t = JSON.toJavaObject(jsonObject, clazz);

		return t;
	}	
	
	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param reader
	 * @param path 路径，例如 a.b.c
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValueByPath(final Reader reader, final String path, final Class<T> clazz)
	{
		T t = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		final String jsonString = IOUtil.getString(reader);
		// 对象读取器
		JSONObject jsonObject = JSON.parseObject(jsonString);
		
		for (String fieldName : fieldNames)
		{
			if (null == jsonObject || jsonObject.isEmpty())
			{
				return t;
			}
			// 读取为结点
			jsonObject = jsonObject.getJSONObject(fieldName);
		}
		// 解析结点为一个对象
		t = JSON.toJavaObject(jsonObject, clazz);

		return t;
	}		

	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param file
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValueByPath(final File file, final String path, final Class<T> clazz)
	{
		T t = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		final String jsonString = FileUtil.getString(file);
		// 对象读取器
		JSONObject jsonObject = JSON.parseObject(jsonString);
		
		for (String fieldName : fieldNames)
		{
			if (null == jsonObject || jsonObject.isEmpty())
			{
				return t;
			}
			// 读取为结点
			jsonObject = jsonObject.getJSONObject(fieldName);
		}
		// 解析结点为一个对象
		t = JSON.toJavaObject(jsonObject, clazz);

		return t;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param data
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValueByPath(final byte[] data, final String path, final Class<T> clazz)
	{
		T t = null;
		String jsonString = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
			// 对象读取器
			JSONObject jsonObject = JSON.parseObject(jsonString);
			
			for (String fieldName : fieldNames)
			{
				if (null == jsonObject || jsonObject.isEmpty())
				{
					return t;
				}
				// 读取为结点
				jsonObject = jsonObject.getJSONObject(fieldName);
			}
			// 解析结点为一个对象
			t = JSON.toJavaObject(jsonObject, clazz);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return t;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param url
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValueByPath(final URL url, final String path, final Class<T> clazz)
	{
		T t = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		InputStream inputStream = null;
		try {
			inputStream = url.openStream();
			final String jsonString = IOUtil.getString(inputStream);
			// 对象读取器
			JSONObject jsonObject = JSON.parseObject(jsonString);
			
			for (String fieldName : fieldNames)
			{
				if (null == jsonObject || jsonObject.isEmpty())
				{
					return t;
				}
				// 读取为结点
				jsonObject = jsonObject.getJSONObject(fieldName);
			}
			// 解析结点为一个对象
			t = JSON.toJavaObject(jsonObject, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(inputStream);
		}

		return t;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param jsonString
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(String jsonString, final String path, final Class<T> clazz)
	{
		List<T> list = null;
		try
		{
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			String fieldName = null;
			JSONArray jsonArray = null;
			JSONObject jsonObject = JSON.parseObject(jsonString);
			for (int i = 0; i < fieldNames.length; i++) {
				if (null == jsonObject || jsonObject.isEmpty())
				{
					return list;
				}
				fieldName = fieldNames[i];
				if ((fieldNames.length - 1) == i) { // 最后一个
					jsonArray = jsonObject.getJSONArray(fieldName);

					break;
				}
				// 读取为对象结点
				jsonObject = jsonObject.getJSONObject(fieldName);
			}
			// 将Json数组转成 json字符串
			jsonString = jsonArray.toJSONString();
			list = JSON.parseArray(jsonString, clazz);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param inputStream
	 * @param path 路径，例如 a.b.c
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final InputStream inputStream, final String path, final Class<T> clazz)
	{
		List<T> list = null;
		try
		{
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			String fieldName = null;
			JSONArray jsonArray = null;
			String jsonString = IOUtil.getString(inputStream);
			JSONObject jsonObject = JSON.parseObject(jsonString);
			for (int i = 0; i < fieldNames.length; i++) {
				if (null == jsonObject || jsonObject.isEmpty())
				{
					return list;
				}
				fieldName = fieldNames[i];
				if ((fieldNames.length - 1) == i) { // 最后一个
					jsonArray = jsonObject.getJSONArray(fieldName);

					break;
				}
				// 读取为对象结点
				jsonObject = jsonObject.getJSONObject(fieldName);
			}
			// 将Json数组转成 json字符串
			jsonString = jsonArray.toJSONString();
			list = JSON.parseArray(jsonString, clazz);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtil.close(inputStream);
		}
		
		return list;
	}	
	
	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param reader
	 * @param path 路径，例如 a.b.c
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final Reader reader, final String path, final Class<T> clazz)
	{
		List<T> list = null;
		try
		{
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			String fieldName = null;
			JSONArray jsonArray = null;
			String jsonString = IOUtil.getString(reader);
			JSONObject jsonObject = JSON.parseObject(jsonString);
			for (int i = 0; i < fieldNames.length; i++) {
				if (null == jsonObject || jsonObject.isEmpty())
				{
					return list;
				}
				fieldName = fieldNames[i];
				if ((fieldNames.length - 1) == i) { // 最后一个
					jsonArray = jsonObject.getJSONArray(fieldName);

					break;
				}
				// 读取为对象结点
				jsonObject = jsonObject.getJSONObject(fieldName);
			}
			// 将Json数组转成 json字符串
			jsonString = jsonArray.toJSONString();
			list = JSON.parseArray(jsonString, clazz);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtil.close(reader);
		}
		
		return list;
	}	

	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param data
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final byte[] data, final String path, final Class<T> clazz)
	{
		List<T> list = null;
		try {
			// 默认使用 UTF-8编码
			final String jsonString = new String(data, Constant.CHART_SET_UTF_8);
			list = readValuesByPath(jsonString, path, clazz);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param file
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final File file, final String path, final Class<T> clazz)
	{
		List<T> list = null;
		try {
			final InputStream inputStream = new FileInputStream(file);
			list = readValuesByPath(inputStream, path, clazz);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param url
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final URL url, final String path, final Class<T> clazz)
	{
		List<T> list = null;
		try {
			final InputStream inputStream = url.openStream();
			list = readValuesByPath(inputStream, path, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/* write object ================================= */	
	
	/**
	 * 
	 * 描述: 输出对象到文件
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @param value
	 * @return
	 */
	public static final boolean writeValue(final File file, final Object value)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		boolean flag = false;
		try
		{
			final boolean prettyFormat = true;
			final String jsonString = JSON.toJSONString(value, prettyFormat);
			FileUtil.writeString(file, jsonString, true);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出对象到输出流
	 * @author  qye.zheng
	 * @param outputStream
	 * @param value
	 * @return
	 */
	public static final boolean writeValue(final OutputStream outputStream, final Object value)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		boolean flag = false;
		try
		{
			final boolean prettyFormat = true;
			final String jsonString = JSON.toJSONString(value, prettyFormat);
			IOUtil.write(jsonString, outputStream);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtil.close(outputStream);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出对象到 写入器
	 * @author  qye.zheng
	 * @param writer
	 * @param value
	 * @return
	 */
	public static final boolean writeValue(final Writer writer, final Object value)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		boolean flag = false;
		try
		{
			final boolean prettyFormat = true;
			final String jsonString = JSON.toJSONString(value, prettyFormat);
			IOUtil.write(jsonString, writer);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtil.close(writer);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出到内存(字节数组)
	 * @author  qye.zheng
	 * @param value
	 * @return
	 */
	public static final byte[] writeAsBytes(final Object value)
	{
		byte[] data = null;
		try
		{
			// 输出null属性
			//SerializerFeature.WriteMapNullValue
			// TODO 是否需要指定日期时间格式 ?
			data = JSON.toJSONBytes(value, SerializerFeature.WriteMapNullValue);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 
	 * 描述: 输出到内存(字符串)
	 * @author  qye.zheng
	 * @param value
	 * @return
	 */
	public static final String writeAsString(final Object value)
	{
		String data = null;
		try
		{
			// 输出null属性
			//SerializerFeature.WriteMapNullValue
			// 对象写出器，TODO 日期格式暂时未能指定
			data = JSON.toJSONString(value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	/* TODO 写 特定功能  ================================= */
	
	/**
	 * 
	 * 描述: 输出对象到文件
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @param value
	 * @param includeFields 包含指定字段，为空包含所有字段
	 * @return
	 */
	public static final boolean writeValueInclude(final File file, final Object value, 
			final String... includeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		boolean flag = false;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameIncludeFilter(includeFields);
			final String jsonString = JSON.toJSONString(value, filter);
			FileUtil.writeString(file, jsonString, true);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出对象到输出流
	 * @author  qye.zheng
	 * @param outputStream
	 * @param value
	 * @param includeFields 包含指定字段，为空包含所有字段
	 * @return
	 */
	public static final boolean writeValueInclude(final OutputStream outputStream, final Object value, 
			final String... includeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		boolean flag = false;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameIncludeFilter(includeFields);
			final String jsonString = JSON.toJSONString(value, filter);
			IOUtil.write(jsonString, outputStream);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtil.close(outputStream);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出对象到 写入器
	 * @author  qye.zheng
	 * @param writer
	 * @param value
	 * @return
	 */
	public static final boolean writeValueInclude(final Writer writer, final Object value, 
			final String... includeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		boolean flag = false;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameIncludeFilter(includeFields);
			final String jsonString = JSON.toJSONString(value, filter);
			IOUtil.write(jsonString, writer);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtil.close(writer);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出到内存(字节数组)
	 * @author  qye.zheng
	 * @param value
	 * @param includeFields 包含指定字段，为空包含所有字段
	 * @return
	 */
	public static final byte[] writeIncludeAsBytes(final Object value, 
			final String... includeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		 byte[] result = null;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameIncludeFilter(includeFields);
			final String jsonString = JSON.toJSONString(value, filter);
			result = jsonString.getBytes(Constant.CHART_SET_UTF_8);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 输出到内存(字符串)
	 * @author  qye.zheng
	 * @param value
	 * @param includeFields 包含指定字段，为空包含所有字段
	 * @return
	 */
	public static final String writeIncludeAsString(final Object value, 
			final String... includeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		String result = null;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameIncludeFilter(includeFields);
			result = JSON.toJSONString(value, filter);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 
	 * 描述: 输出对象到文件
	 * @author  qye.zheng
	 * @param file 文件对象
	 * @param value
	 * @param excludeFields 排除指定字段，为空则包含所有字段
	 * @return
	 */
	public static final boolean writeValueExclude(final File file, final Object value, 
			final String... excludeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		boolean flag = false;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameExcludeFilter(excludeFields);
			final String jsonString = JSON.toJSONString(value, filter);
			FileUtil.writeString(file, jsonString, true);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出对象到输出流
	 * @author  qye.zheng
	 * @param outputStream
	 * @param value
	 * @param excludeFields 排除指定字段，为空则包含所有字段
	 * @return
	 */
	public static final boolean writeValueExclude(final OutputStream outputStream, final Object value, 
			final String... excludeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		boolean flag = false;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameExcludeFilter(excludeFields);
			final String jsonString = JSON.toJSONString(value, filter);
			IOUtil.write(jsonString, outputStream);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtil.close(outputStream);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出对象到 写入器
	 * @author  qye.zheng
	 * @param writer
	 * @param value
	 * @param excludeFields 排除指定字段，为空则包含所有字段
	 * @return
	 */
	public static final boolean writeValueExclude(final Writer writer, final Object value, 
			final String... excludeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		boolean flag = false;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameExcludeFilter(excludeFields);
			final String jsonString = JSON.toJSONString(value, filter);
			IOUtil.write(jsonString, writer);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtil.close(writer);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出到内存(字节数组)
	 * @author  qye.zheng
	 * @param value
	 * @param excludeFields 排除指定字段，为空则包含所有字段
	 * @return
	 */
	public static final byte[] writeExcludeAsBytes(final Object value, 
			final String... excludeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		byte[] result = null;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameExcludeFilter(excludeFields);
			final String jsonString = JSON.toJSONString(value, filter);
			result = jsonString.getBytes(Constant.CHART_SET_UTF_8);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 输出到内存(字符串)
	 * @author  qye.zheng
	 * @param value
	 * @param excludeFields 排除指定字段，为空则包含所有字段
	 * @return
	 */
	public static final String writeExcludeAsString(final Object value, 
			final String... excludeFields)
	{
		// 输出null属性
		//SerializerFeature.WriteMapNullValue
		String result = null;
		try
		{
			// 字段名称过滤器
			final SerializeFilter filter = new PropertyNameExcludeFilter(excludeFields);
			result = JSON.toJSONString(value, filter);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
}
