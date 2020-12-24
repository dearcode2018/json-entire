/**
 * GsonUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hua.constant.Constant;
import com.hua.constant.FormatConstant;
import com.hua.filter.PropertyNameExcludeFilter;
import com.hua.filter.PropertyNameIncludeFilter;

/**
 * GsonUtil
 * 描述: Google Json - 工具类
 * @author  qye.zheng
 */
public final class GsonUtil
{

	/* 路径拆分正则表达式 */
	private static final String PATH_SPLIT_REGEX = "\\.";
	
	/* gson 构建器 */
	private static final GsonBuilder gsonBuilder = new GsonBuilder();
	
	/* Gson */
	private static Gson gson;
	
	static
	{
		// 设置为优美的输出 (有格式的)
		gsonBuilder.setPrettyPrinting();
		// 允许序列化 空字段，默认是忽略空字段
		gsonBuilder.serializeNulls();
		
		// 使用默认日期格式
		setDefaultDateFormat();
		
		/*
		 * 排除掉 没有用com.google.gson.annotations.Expose 标注的字段
		 * 使用 @Expose来配置 序列化、反序列化
		 */
		//gsonBuilder.excludeFieldsWithoutExposeAnnotation();
	}
	
	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private GsonUtil()
	{
	}
	
	/* TODO  公共部分  ================================= */
	
	/**
	 * 在操作特定日期时间格式之前，需要统一调用此方法来显式设置格式
	 * 此方法会影响到全局日期时间格式
	 */
	/**
	 * 
	 * 描述: 设置日期格式，若有特殊日期时间格式
	 * 在调用读、写方法之前，先调用此方法设置格式
	 * 该设置会影响全局日期解析
	 * @author  qye.zheng
	 * @param dateFormat
	 */
	public static final void setDateFormat(final String dateFormat)
	{
		gson = gsonBuilder.setDateFormat(dateFormat).create();
	}

	/**
	 * 没有特殊格式要求，在操作日期时间之前先调用此方法
	 * 该设置会影响全局日期解析
	 * 描述: 使用默认的日期格式 
	 * @author  qye.zheng
	 */
	public static final void setDefaultDateFormat()
	{
		gson = gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss).create();
	}
	
	/* read value 返回单个对象 ================================= */
	
	
	/**
	 * 
	 * 描述: 根据json字符串 解析对象
	 * @author  qye.zheng
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final String json, final Class<T> clazz)
	{
		final T t = gson.fromJson(json, clazz);
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 从 reader 中读取 并 解析对象
	 * @author  qye.zheng
	 * @param reader
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final Reader reader, final Class<T> clazz)
	{
		final T t = gson.fromJson(reader, clazz);
		IOUtil.close(reader);
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 从 inputStream 中读取 并 解析对象
	 * @author  qye.zheng
	 * @param inputStream
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final InputStream inputStream, final Class<T> clazz)
	{
		final Reader reader = IOUtil.streamToReader(inputStream);
		final T t = gson.fromJson(reader, clazz);
		IOUtil.close(inputStream);
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 从 data 中读取 并 解析对象
	 * @author  qye.zheng
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final byte[] data, final Class<T> clazz)
	{
		T t = null;
		try {
			final String jsonString = new String(data, Constant.CHART_SET_UTF_8);
			t = gson.fromJson(jsonString, clazz);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 从 file 中读取 并 解析对象
	 * @author  qye.zheng
	 * @param file
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final File file, final Class<T> clazz)
	{
		Reader reader = null;
		T t = null;
		try {
			reader = new FileReader(file);
			t = gson.fromJson(reader, clazz);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(reader);
		}
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 从 url 中读取 并 解析对象
	 * @author  qye.zheng
	 * @param url
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValue(final URL url, final Class<T> clazz)
	{
		InputStream inputStream = null;
		T t = null;
		try {
			inputStream = url.openStream();
			final Reader reader = IOUtil.streamToReader(inputStream);
			t = gson.fromJson(reader, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(inputStream);
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
		final List<T> list = new ArrayList<T>();
		final JsonParser jsonParser = new JsonParser();
		final JsonElement jsonElement = jsonParser.parse(jsonString);
		// 读取为json数组
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		T t = null;
		// 单个对象
		JsonElement each = null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			/*
			 * 逐个解析数组中的单个对象
			 * 解析完成后 放入对象集合
			 */
			each = jsonArray.get(i);
			t = gson.fromJson(each, clazz);
			list.add(t);
		}
		
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
		final List<T> list = new ArrayList<T>();
		final JsonParser jsonParser = new JsonParser();
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
			final JsonElement jsonElement = jsonParser.parse(jsonString);
			// 读取为json数组
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			T t = null;
			// 单个对象
			JsonElement each = null;
			for (int i = 0; i < jsonArray.size(); i++)
			{
				/*
				 * 逐个解析数组中的单个对象
				 * 解析完成后 放入对象集合
				 */
				each = jsonArray.get(i);
				t = gson.fromJson(each, clazz);
				list.add(t);
			}
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
		final List<T> list = new ArrayList<T>();
		final JsonParser jsonParser = new JsonParser();
		final String jsonString = FileUtil.getString(file);
		final JsonElement jsonElement = jsonParser.parse(jsonString);
		// 读取为json数组
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		T t = null;
		// 单个对象
		JsonElement each = null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			/*
			 * 逐个解析数组中的单个对象
			 * 解析完成后 放入对象集合
			 */
			each = jsonArray.get(i);
			t = gson.fromJson(each, clazz);
			list.add(t);
		}
		
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
		final List<T> list = new ArrayList<T>();
		final JsonParser jsonParser = new JsonParser();
		final Reader reader = IOUtil.streamToReader(inputStream);
		final JsonElement jsonElement = jsonParser.parse(reader);
		// 读取为json数组
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		T t = null;
		// 单个对象
		JsonElement each = null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			/*
			 * 逐个解析数组中的单个对象
			 * 解析完成后 放入对象集合
			 */
			each = jsonArray.get(i);
			t = gson.fromJson(each, clazz);
			list.add(t);
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
		final List<T> list = new ArrayList<T>();
		final JsonParser jsonParser = new JsonParser();
		final JsonElement jsonElement = jsonParser.parse(reader);
		// 读取为json数组
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		T t = null;
		// 单个对象
		JsonElement each = null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			/*
			 * 逐个解析数组中的单个对象
			 * 解析完成后 放入对象集合
			 */
			each = jsonArray.get(i);
			t = gson.fromJson(each, clazz);
			list.add(t);
		}
		
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
		try {
			inputStream = url.openStream();
			list = readValues(inputStream, clazz);
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
		try {
			final JsonParser jsonParser = new JsonParser();
			final JsonElement jsonElement = jsonParser.parse(jsonString);
			// 读取为结点
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			for (String fieldName : fieldNames)
			{
				// 若为空，不再继续导航
				if (null == jsonObj || jsonObj.isJsonNull())
				{
					return t;
				}
				// 读取为结点
				jsonObj = jsonObj.getAsJsonObject(fieldName);
			}
			t = gson.fromJson(jsonObj, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param inputStream
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValueByPath(final InputStream inputStream, final String path, final Class<T> clazz)
	{
		T t = null;
		try {
			final Reader reader = IOUtil.streamToReader(inputStream);
			final JsonParser jsonParser = new JsonParser();
			final JsonElement jsonElement = jsonParser.parse(reader);
			// 读取为结点
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			for (String fieldName : fieldNames)
			{
				// 若为空，不再继续导航
				if (null == jsonObj || jsonObj.isJsonNull())
				{
					return t;
				}
				// 读取为结点
				jsonObj = jsonObj.getAsJsonObject(fieldName);
			}
			t = gson.fromJson(jsonObj, clazz);
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
	 * 描述: 根据字段名称返回对象
	 * @author  qye.zheng
	 * @param reader
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> T readValueByPath(final Reader reader, final String path, final Class<T> clazz)
	{
		T t = null;
		try {
			final JsonParser jsonParser = new JsonParser();
			final JsonElement jsonElement = jsonParser.parse(reader);
			// 读取为结点
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			for (String fieldName : fieldNames)
			{
				// 若为空，不再继续导航
				if (null == jsonObj || jsonObj.isJsonNull())
				{
					return t;
				}
				// 读取为结点
				jsonObj = jsonObj.getAsJsonObject(fieldName);
			}
			t = gson.fromJson(jsonObj, clazz);
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
		try {
			// 默认使用 UTF-8编码
			final String jsonString = new String(data, Constant.CHART_SET_UTF_8);
			t = readValueByPath(jsonString, path, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		Reader reader = null;
		try {
			reader = new FileReader(file);
			final JsonParser jsonParser = new JsonParser();
			final JsonElement jsonElement = jsonParser.parse(reader);
			// 读取为结点
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			for (String fieldName : fieldNames)
			{
				// 若为空，不再继续导航
				if (null == jsonObj || jsonObj.isJsonNull())
				{
					return t;
				}
				// 读取为结点
				jsonObj = jsonObj.getAsJsonObject(fieldName);
			}
			t = gson.fromJson(jsonObj, clazz);
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
		InputStream inputStream = null;
		try
		{
			inputStream = url.openStream();
			t = readValueByPath(inputStream, path, clazz);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			IOUtil.close(inputStream);
		}
		
		return t;
	}	
	
	/**
	 * 
	 * 描述: 根据字段名称返回集合对象
	 * @author  qye.zheng
	 * @param jsonString
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final String jsonString, final String path, final Class<T> clazz)
	{
		final List<T> list = new ArrayList<T>();
		try {
			final JsonParser jsonParser = new JsonParser();
			final JsonElement jsonElement = jsonParser.parse(jsonString);
			// 读取为结点
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			JsonArray jsonArray = null;
			String fieldName = null;
			for (int i = 0; i < fieldNames.length; i++)
			{
				// 若为空，不再继续导航
				if (null == jsonObj || jsonObj.isJsonNull())
				{
					return list;
				}
				fieldName = fieldNames[i];
				if ((fieldNames.length - 1) == i)
				{ // 最后一个 解析为一个 JsonArray
					jsonArray = jsonObj.getAsJsonArray(fieldName);
					
					// 跳出循环
					break;
				}
				jsonObj = jsonObj.getAsJsonObject(fieldName);
			}
			T t = null;
			// 单个对象
			JsonElement each = null;
			for (int i = 0; i < jsonArray.size(); i++)
			{
				/*
				 * 逐个解析数组中的单个对象
				 * 解析完成后 放入对象集合
				 */
				each = jsonArray.get(i);
				t = gson.fromJson(each, clazz);
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回集合对象
	 * @author  qye.zheng
	 * @param inputStream
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final InputStream inputStream, final String path, final Class<T> clazz)
	{
		final List<T> list = new ArrayList<T>();
		try {
			final Reader reader = IOUtil.streamToReader(inputStream);
			final JsonParser jsonParser = new JsonParser();
			final JsonElement jsonElement = jsonParser.parse(reader);
			// 读取为结点
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			JsonArray jsonArray = null;
			String fieldName = null;
			for (int i = 0; i < fieldNames.length; i++)
			{
				// 若为空，不再继续导航
				if (null == jsonObj || jsonObj.isJsonNull())
				{
					return list;
				}
				fieldName = fieldNames[i];
				if ((fieldNames.length - 1) == i)
				{ // 最后一个 解析为一个 JsonArray
					jsonArray = jsonObj.getAsJsonArray(fieldName);
					
					// 跳出循环
					break;
				}
				jsonObj = jsonObj.getAsJsonObject(fieldName);
			}
			T t = null;
			// 单个对象
			JsonElement each = null;
			for (int i = 0; i < jsonArray.size(); i++)
			{
				/*
				 * 逐个解析数组中的单个对象
				 * 解析完成后 放入对象集合
				 */
				each = jsonArray.get(i);
				t = gson.fromJson(each, clazz);
				list.add(t);
			}
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
	 * 描述: 根据字段名称返回集合对象
	 * @author  qye.zheng
	 * @param reader
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final Reader reader, final String path, final Class<T> clazz)
	{
		final List<T> list = new ArrayList<T>();
		try {
			final JsonParser jsonParser = new JsonParser();
			final JsonElement jsonElement = jsonParser.parse(reader);
			// 读取为结点
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			JsonArray jsonArray = null;
			String fieldName = null;
			for (int i = 0; i < fieldNames.length; i++)
			{
				// 若为空，不再继续导航
				if (null == jsonObj || jsonObj.isJsonNull())
				{
					return list;
				}
				fieldName = fieldNames[i];
				if ((fieldNames.length - 1) == i)
				{ // 最后一个 解析为一个 JsonArray
					jsonArray = jsonObj.getAsJsonArray(fieldName);
					
					// 跳出循环
					break;
				}
				jsonObj = jsonObj.getAsJsonObject(fieldName);
			}
			T t = null;
			// 单个对象
			JsonElement each = null;
			for (int i = 0; i < jsonArray.size(); i++)
			{
				/*
				 * 逐个解析数组中的单个对象
				 * 解析完成后 放入对象集合
				 */
				each = jsonArray.get(i);
				t = gson.fromJson(each, clazz);
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(reader);
		}
		
		return list;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回集合对象
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回集合对象
	 * @author  qye.zheng
	 * @param file
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final File file, final String path, final Class<T> clazz)
	{
		final List<T> list = new ArrayList<T>();
		Reader reader = null;
		try {
			reader = new FileReader(file);
			final JsonParser jsonParser = new JsonParser();
			final JsonElement jsonElement = jsonParser.parse(reader);
			// 读取为结点
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			// 将 path 拆开
			final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
			JsonArray jsonArray = null;
			String fieldName = null;
			for (int i = 0; i < fieldNames.length; i++)
			{
				// 若为空，不再继续导航
				if (null == jsonObj || jsonObj.isJsonNull())
				{
					return list;
				}
				fieldName = fieldNames[i];
				if ((fieldNames.length - 1) == i)
				{ // 最后一个 解析为一个 JsonArray
					jsonArray = jsonObj.getAsJsonArray(fieldName);
					
					// 跳出循环
					break;
				}
				jsonObj = jsonObj.getAsJsonObject(fieldName);
			}
			T t = null;
			// 单个对象
			JsonElement each = null;
			for (int i = 0; i < jsonArray.size(); i++)
			{
				/*
				 * 逐个解析数组中的单个对象
				 * 解析完成后 放入对象集合
				 */
				each = jsonArray.get(i);
				t = gson.fromJson(each, clazz);
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(reader);
		}
		
		return list;
	}
	
	/**
	 * 
	 * 描述: 根据字段名称返回集合对象
	 * @author  qye.zheng
	 * @param url
	 * @param path 路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> readValuesByPath(final URL url, final String path, final Class<T> clazz)
	{
		List<T> list = null;
		InputStream inputStream = null;
		try
		{
			inputStream = url.openStream();
			list = readValuesByPath(inputStream, path, clazz);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}	
	
	/* write object 返回单个对象 ================================= */
	
	/**
	 * 
	 * 描述: 转成 json 输出到指定可附加对象
	 * @author  qye.zheng
	 * @param value
	 * @param appendable
	 */
	public static final void writeValue(final Object value, final Appendable appendable)
	{
		gson.toJson(value, appendable);
	}
	
	/**
	 * 
	 * 描述: 转成 json 输出到 outputStream对象
	 * @author  qye.zheng
	 * @param value
	 * @param outputStream
	 */
	public static final void writeValue(final Object value, final OutputStream outputStream)
	{
		final Writer writer = IOUtil.streamToWriter(outputStream);
		gson.toJson(value, writer);
		IOUtil.close(outputStream);
		IOUtil.close(writer);
	}
	
	/**
	 * 
	 * 描述: 转成 json 输出到指定可附加对象
	 * @author  qye.zheng
	 * @param value
	 * @param appendable
	 */
	public static final void writeValue(final Object value, final File file)
	{
		Writer writer = null;
		try {
			writer = new FileWriter(file);
			gson.toJson(value, writer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(writer);
		}
	}
	
	/**
	 * 
	 * 描述: 将对象输出为 json字符串 
	 * @author  qye.zheng
	 * @param value
	 * @return
	 */
	public static final String writeAsString(final Object value)
	{
		final String json = gson.toJson(value);
		
		return json;
	}
	
	/**
	 * 
	 * 描述: 将对象输出为 json字符串 
	 * @author  qye.zheng
	 * @param value
	 * @return
	 */
	public static final byte[] writeAsBytes(final Object value)
	{
		final String json = gson.toJson(value);
		byte[] data = null;
		try {
			data = json.getBytes(Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
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
		boolean flag = false;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameIncludeFilter(includeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			final Writer writer = new FileWriter(file);
			gson.toJson(value, writer);
			IOUtil.close(writer);
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
		boolean flag = false;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameIncludeFilter(includeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			final Writer writer = new OutputStreamWriter(outputStream);
			gson.toJson(value, writer);	
			IOUtil.close(writer);
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
		boolean flag = false;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameIncludeFilter(includeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			gson.toJson(value, writer);		
			IOUtil.close(writer);
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
		 byte[] result = null;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameIncludeFilter(includeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			final String jsonString = gson.toJson(value);
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
		String result = null;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameIncludeFilter(includeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			result = gson.toJson(value);
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
		boolean flag = false;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameExcludeFilter(excludeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			final Writer writer = new FileWriter(file);
			gson.toJson(value, writer);
			IOUtil.close(writer);
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
		boolean flag = false;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameExcludeFilter(excludeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			final Writer writer = new OutputStreamWriter(outputStream);
			gson.toJson(value, writer);
			IOUtil.close(writer);
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
		boolean flag = false;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameExcludeFilter(excludeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			gson.toJson(value, writer);
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
		byte[] result = null;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameExcludeFilter(excludeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			final String jsonString = gson.toJson(value);
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
		String result = null;
		try
		{
			// gson构建器
			final GsonBuilder gsonBuilder = new GsonBuilder();
			
			// 设置为优美的输出 (有格式的)
			gsonBuilder.setPrettyPrinting();
			
			// 使用默认的日期格式 
			gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			
			final ExclusionStrategy filter = new PropertyNameExcludeFilter(excludeFields);
			gsonBuilder.addSerializationExclusionStrategy(filter);
			
			final Gson gson = gsonBuilder.create();
			result = gson.toJson(value);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
