/**
 * OrgJsonUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
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
	
	/* read value 返回JSONObject ================================= */
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param jsonString
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONObject readValue(final String jsonString)
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
	public static final JSONObject readValue(final InputStream inputStream)
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
	public static final JSONObject readValue(final Reader reader)
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
	public static final JSONObject readValue(final File file)
	{
		InputStream inputStream = null;
		JSONObject jsonObject = null;
		try {
			inputStream = new FileInputStream(file);
			jsonObject = readValue(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(inputStream);
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
	public static final JSONObject readValue(final URL url)
	{
		InputStream inputStream =null;
		JSONObject jsonObject = null;
		try {
			inputStream = url.openStream();
			jsonObject = readValue(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally
		{
			IOUtil.close(inputStream);
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
	public static final JSONObject readValue(final byte[] data)
	{
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return readValue(jsonString);
	}
	
	/* read values 返回 JSONArray ================================= */	
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param jsonString
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValues(final String jsonString)
	{
		final JSONTokener tokener = new JSONTokener(jsonString);
		final JSONArray jsonArray = new JSONArray(tokener);
		
		return jsonArray;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param inputStream
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValues(final InputStream inputStream)
	{
		final JSONTokener tokener = new JSONTokener(inputStream);
		final JSONArray jsonArray = new JSONArray(tokener);
		
		IOUtil.close(inputStream);
		
		return jsonArray;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param reader
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValues(final Reader reader)
	{
		final JSONTokener tokener = new JSONTokener(reader);
		final JSONArray jsonArray = new JSONArray(tokener);
		IOUtil.close(reader);
		
		return jsonArray;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param file
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValues(final File file)
	{
		InputStream inputStream = null;
		JSONArray jsonArray = null;
		try {
			inputStream = new FileInputStream(file);
			jsonArray = readValues(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param url
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValues(final URL url)
	{
		InputStream inputStream =null;
		JSONArray jsonArray = null;
		try {
			inputStream = url.openStream();
			jsonArray = readValues(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param inputStream
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValues(final byte[] data)
	{
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return readValues(jsonString);
	}
	
	
	/* TODO 读 特定功能  ================================= */			
	
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
			if (null == jsonObject || 0 == jsonObject.length())
			{
				// 没有到达目的结点
				return null;
			}
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
			if (null == jsonObject || 0 == jsonObject.length())
			{
				// 没有到达目的结点
				return null;
			}
			// 读取为结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		IOUtil.close(inputStream);
		
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
			if (null == jsonObject || 0 == jsonObject.length())
			{
				// 没有到达目的结点
				return null;
			}
			// 读取为结点
			jsonObject = jsonObject.optJSONObject(fieldName);

		}
		IOUtil.close(reader);
		
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
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param jsonString
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValuesByPath(final String jsonString, final String path)
	{
		final JSONTokener tokener = new JSONTokener(jsonString);
		// 读取为结点
		JSONObject jsonObject = new JSONObject(tokener);
		JSONArray jsonArray = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		String fieldName = null;
		for (int i = 0; i < fieldNames.length; i++)
		{
			if (null == jsonObject || 0 == jsonObject.length())
			{
				// 没有到达目的结点
				return null;
			}
			fieldName = fieldNames[i];
			if ((fieldNames.length - 1) == i)
			{ // 最后一个
				jsonArray = jsonObject.optJSONArray(fieldName);
				
				break;
			}
			// 读取为对象结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		
		return jsonArray;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param inputStream
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValuesByPath(final InputStream inputStream, final String path)
	{
		final JSONTokener tokener = new JSONTokener(inputStream);
		// 读取为结点
		JSONObject jsonObject = new JSONObject(tokener);
		JSONArray jsonArray = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		String fieldName = null;
		for (int i = 0; i < fieldNames.length; i++)
		{
			if (null == jsonObject || 0 == jsonObject.length())
			{
				// 没有到达目的结点
				return null;
			}
			fieldName = fieldNames[i];
			if ((fieldNames.length - 1) == i)
			{ // 最后一个
				jsonArray = jsonObject.optJSONArray(fieldName);
				
				break;
			}
			// 读取为对象结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		IOUtil.close(inputStream);
		
		return jsonArray;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param reader
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValuesByPath(final Reader reader, final String path)
	{
		final JSONTokener tokener = new JSONTokener(reader);
		// 读取为结点
		JSONObject jsonObject = new JSONObject(tokener);
		JSONArray jsonArray = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		String fieldName = null;
		for (int i = 0; i < fieldNames.length; i++)
		{
			if (null == jsonObject || 0 == jsonObject.length())
			{
				// 没有到达目的结点
				return null;
			}
			fieldName = fieldNames[i];
			if ((fieldNames.length - 1) == i)
			{ // 最后一个
				jsonArray = jsonObject.optJSONArray(fieldName);
				
				break;
			}
			// 读取为对象结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		IOUtil.close(reader);
		
		return jsonArray;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param file
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValuesByPath(final File file, final String path)
	{
		InputStream inputStream = null;
		JSONArray jsonObject = null;
		try {
			inputStream = new FileInputStream(file);
			jsonObject = readValuesByPath(inputStream, path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param url
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValuesByPath(final URL url, final String path)
	{
		InputStream inputStream =null;
		JSONArray jsonObject = null;
		try {
			inputStream = url.openStream();
			jsonObject = readValuesByPath(inputStream, path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	/**
	 * 
	 * @description 解析为 JSONArray 对象
	 * @param data
	 * @param path 路径，例如 a.b.c 或 a
	 * @return
	 * @author qianye.zheng
	 */
	public static final JSONArray readValuesByPath(final byte[] data, final String path)
	{
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return readValuesByPath(jsonString, path);
	}	
	
	
	/* write object ================================= */
	
	/**
	 * 
	 * 描述: 输出对象到文件
	 * @author  qye.zheng
	 * @param outputStream
	 * @param value
	 * @return
	 */
	public static final boolean writeValue(final OutputStream outputStream, final Object value)
	{
		boolean flag = false;
		try
		{
			final JSONObject jsonObject = new JSONObject(value);
			final String jsonString = jsonObject.toString();
			IOUtil.write(jsonString, outputStream);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			IOUtil.close(outputStream);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 输出对象到文件
	 * @author  qye.zheng
	 * @param writer
	 * @param value
	 * @return
	 */
	public static final boolean writeValue(final Writer writer, final Object value)
	{
		boolean flag = false;
		try
		{
			final JSONObject jsonObject = new JSONObject(value);
			jsonObject.write(writer);
			writer.flush();
			IOUtil.close(writer);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			IOUtil.close(writer);
		}
		
		return flag;
	}
	
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
		boolean flag = false;
		try
		{
			final JSONObject jsonObject = new JSONObject(value);
			final OutputStream outputStream = new FileOutputStream(file);
			final String jsonString = jsonObject.toString();
			IOUtil.write(jsonString, outputStream);
			IOUtil.close(outputStream);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
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
			final JSONObject jsonObject = new JSONObject(value);
			final String jsonString = jsonObject.toString();
			data = jsonString.getBytes(Constant.CHART_SET_UTF_8);
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
			final JSONObject jsonObject = new JSONObject(value);
			data = jsonObject.toString();
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
		boolean flag = false;
		try
		{
			final JSONObject jsonObject = new JSONObject(value);
			final Set<String> nameSet = jsonObject.keySet();
			/**
			 * 使用新的set，才能避免 同步修改异常
			 */
			final Set<String> newNameSet = new HashSet<String>(nameSet);
			for (String includeField : includeFields)
			{
				// 移除掉包含的，剩下的就是要排除的
				newNameSet.remove(includeField);
			}
			for (String excludeField : newNameSet)
			{
				jsonObject.remove(excludeField);
			}
			
			final Writer writer = new FileWriter(file);
			jsonObject.write(writer);
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
			final JSONObject jsonObject = new JSONObject(value);
			final Set<String> nameSet = jsonObject.keySet();
			/**
			 * 使用新的set，才能避免 同步修改异常
			 */
			final Set<String> newNameSet = new HashSet<String>(nameSet);
			for (String includeField : includeFields)
			{
				// 移除掉包含的，剩下的就是要排除的
				newNameSet.remove(includeField);
			}
			for (String excludeField : newNameSet)
			{
				jsonObject.remove(excludeField);
			}
			
			final Writer writer = new OutputStreamWriter(outputStream);
			jsonObject.write(writer);
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
			final JSONObject jsonObject = new JSONObject(value);
			final Set<String> nameSet = jsonObject.keySet();
			/**
			 * 使用新的set，才能避免 同步修改异常
			 */
			final Set<String> newNameSet = new HashSet<String>(nameSet);
			for (String includeField : includeFields)
			{
				// 移除掉包含的，剩下的就是要排除的
				newNameSet.remove(includeField);
			}
			for (String excludeField : newNameSet)
			{
				jsonObject.remove(excludeField);
			}
			jsonObject.write(writer);
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
			final JSONObject jsonObject = new JSONObject(value);
			final Set<String> nameSet = jsonObject.keySet();
			/**
			 * 使用新的set，才能避免 同步修改异常
			 */
			final Set<String> newNameSet = new HashSet<String>(nameSet);
			for (String includeField : includeFields)
			{
				// 移除掉包含的，剩下的就是要排除的
				newNameSet.remove(includeField);
			}
			for (String excludeField : newNameSet)
			{
				jsonObject.remove(excludeField);
			}
			final String jsonString = jsonObject.toString();
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
			final JSONObject jsonObject = new JSONObject(value);
			final Set<String> nameSet = jsonObject.keySet();
			/**
			 * 使用新的set，才能避免 同步修改异常
			 */
			final Set<String> newNameSet = new HashSet<String>(nameSet);
			for (String includeField : includeFields)
			{
				// 移除掉包含的，剩下的就是要排除的
				newNameSet.remove(includeField);
			}
			for (String excludeField : newNameSet)
			{
				jsonObject.remove(excludeField);
			}
			result = jsonObject.toString();
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
			final JSONObject jsonObject = new JSONObject(value);
			for (String excludeField : excludeFields)
			{
				jsonObject.remove(excludeField);
			}
			final Writer writer = new FileWriter(file);
			jsonObject.write(writer);
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
			final JSONObject jsonObject = new JSONObject(value);
			for (String excludeField : excludeFields)
			{
				jsonObject.remove(excludeField);
			}
			
			final Writer writer = new OutputStreamWriter(outputStream);
			jsonObject.write(writer);
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
			final JSONObject jsonObject = new JSONObject(value);
			for (String excludeField : excludeFields)
			{
				jsonObject.remove(excludeField);
			}
			jsonObject.write(writer);
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
			final JSONObject jsonObject = new JSONObject(value);
			for (String excludeField : excludeFields)
			{
				jsonObject.remove(excludeField);
			}
			final String jsonString = jsonObject.toString();
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
			final JSONObject jsonObject = new JSONObject(value);
			for (String excludeField : excludeFields)
			{
				jsonObject.remove(excludeField);
			}
			result = jsonObject.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
}
