/**
 * JsonLibUtil.java
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONTokener;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

import com.hua.constant.Constant;
import com.hua.constant.FormatConstant;
import com.hua.filter.PropertyNameExcludeFilter;
import com.hua.filter.PropertyNameIncludeFilter;
import com.hua.processor.DateMorpherEx;

/**
 *@type JsonLibUtil 
 * 描述: Json library - 工具类
 * 
 * @author qye.zheng
 */
@SuppressWarnings({"unchecked"})
public final class JsonLibUtil {

	// 日期时间格式-默认采用
	private static DateFormat dateTimeFormat = new SimpleDateFormat(
			FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);

	/* 路径拆分正则表达式 */
	private static final String PATH_SPLIT_REGEX = "\\.";

	static
	{
		/**
		 * 解决日期时间 类型的解析问题
		 * 需要扩充类型，可以填入数组
		 */
		final String[] dateTimeFormat = {
				FormatConstant.DATE_FORMAT_yyyy_MM_dd, 
				FormatConstant.DATE_FORMAT_yyyyMMdd,
				FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss,
				FormatConstant.DATE_TIME_FORMAT_yyyyMMddHHmmss,
				FormatConstant.TIMESTAMP_FORMAT_yyyy_MM_dd_HH_mm_ss_SSS
				};
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpherEx(dateTimeFormat, (Date) null));
	}
	
	/**
	 * 构造方法 描述: 私有 - 禁止实例化
	 * 
	 * @author qye.zheng
	 */
	private JsonLibUtil() {
	}

	/* TODO 公共部分 ================================= */

	/**
	 * 
	 * 描述: 设置日期格式，若有特殊日期时间格式 在调用读、写方法之前，先调用此方法设置格式 该设置会影响全局日期解析
	 * 
	 * @author qye.zheng
	 * @param dateFormat
	 */
	public static final void setDateFormat(final DateFormat dateFormat) {
		dateTimeFormat = dateFormat;
	}
	
	/* read value 返回单个对象 ================================= */

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param jsonString
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValue(final String jsonString, final Class<T> clazz) {
		final JSONTokener tokener = new JSONTokener(jsonString);
		final JSONObject jsonObject = JSONObject.fromObject(tokener);
		final T t = (T) JSONObject.toBean(jsonObject, clazz);
		
		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param inputStream
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValue(final InputStream inputStream, final Class<T> clazz) {
		final String jsonString = IOUtil.getString(inputStream);
		final JSONObject jsonObject = JSONObject.fromObject(jsonString);
		final T t = (T) JSONObject.toBean(jsonObject, clazz);
		
		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param reader
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValue(final Reader reader, final Class<T> clazz) {
		final String jsonString = IOUtil.getString(reader);
		final JSONObject jsonObject = JSONObject.fromObject(jsonString);
		final T t = (T) JSONObject.toBean(jsonObject, clazz);
		
		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param file
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValue(final File file, final Class<T> clazz) {
		InputStream inputStream = null;
		T t = null;
		try {
			inputStream = new FileInputStream(file);
			t = readValue(inputStream, clazz);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(inputStream);
		}

		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param url
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValue(final URL url, final Class<T> clazz) {
		InputStream inputStream = null;
		T t = null;
		try {
			inputStream = url.openStream();
			t = readValue(inputStream, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(inputStream);
		}

		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param inputStream
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValue(final byte[] data, final Class<T> clazz) {
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return readValue(jsonString, clazz);
	}


	/* read values 返回 集合对象 ================================= */

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param jsonString
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValues(final String jsonString, final Class<T> clazz) {
		final List<T> list = new ArrayList<T>();
		final JSONTokener tokener = new JSONTokener(jsonString);
		final JSONArray jsonArray = JSONArray.fromObject(tokener);
		T t= null;
		JSONObject jsonObject = null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			jsonObject = jsonArray.getJSONObject(i);
			t = (T) JSONObject.toBean(jsonObject, clazz);
			list.add(t);
		}
		
		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param inputStream
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValues(final InputStream inputStream, final Class<T> clazz) {
		final String jsonString = IOUtil.getString(inputStream);
		final List<T> list = new ArrayList<T>();
		final JSONTokener tokener = new JSONTokener(jsonString);
		final JSONArray jsonArray = JSONArray.fromObject(tokener);
		T t= null;
		JSONObject jsonObject = null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			jsonObject = jsonArray.getJSONObject(i);
			t = (T) JSONObject.toBean(jsonObject, clazz);
			list.add(t);
		}
		
		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param reader
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValues(final Reader reader, final Class<T> clazz) {
		final String jsonString = IOUtil.getString(reader);
		final List<T> list = new ArrayList<T>();
		final JSONTokener tokener = new JSONTokener(jsonString);
		final JSONArray jsonArray = JSONArray.fromObject(tokener);
		T t= null;
		JSONObject jsonObject = null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			jsonObject = jsonArray.getJSONObject(i);
			t = (T) JSONObject.toBean(jsonObject, clazz);
			list.add(t);
		}
		
		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param file
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValues(final File file, final Class<T> clazz) {
		InputStream inputStream = null;
		List<T> list = null;
		try {
			inputStream = new FileInputStream(file);
			list = readValues(inputStream, clazz);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param url
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValues(final URL url, final Class<T> clazz) {
		InputStream inputStream = null;
		List<T> list = null;
		try {
			inputStream = url.openStream();
			list = readValues(inputStream, clazz);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param inputStream
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValues(final byte[] data, final Class<T> clazz) {
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return readValues(jsonString, clazz);
	}

	/* TODO 读 特定功能 ================================= */	
	
	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param jsonString
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz            
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValueByPath(final String jsonString,
			final String path, final Class<T> clazz) {
		final JSONTokener tokener = new JSONTokener(jsonString);
		T t = null;
		// 读取为结点
		JSONObject jsonObject = JSONObject.fromObject(tokener);
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		for (String fieldName : fieldNames) {
			if (null == jsonObject || jsonObject.isNullObject())
			{
				return t;
			}
			// 读取为结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		t = (T) JSONObject.toBean(jsonObject, clazz);

		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param inputStream
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValueByPath(
			final InputStream inputStream, final String path, final Class<T> clazz) {
		final String jsonString = IOUtil.getString(inputStream);
		final JSONTokener tokener = new JSONTokener(jsonString);
		T t = null;
		JSONObject jsonObject = JSONObject.fromObject(tokener);
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		for (String fieldName : fieldNames) {
			if (null == jsonObject || jsonObject.isNullObject())
			{
				return t;
			}
			// 读取为结点
			jsonObject = jsonObject.optJSONObject(fieldName);

		}
		t = (T) JSONObject.toBean(jsonObject, clazz);
		IOUtil.close(inputStream);

		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param reader
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValueByPath(final Reader reader,
			final String path, final Class<T> clazz) {
		final String jsonString = IOUtil.getString(reader);
		final JSONTokener tokener = new JSONTokener(jsonString);
		JSONObject jsonObject = JSONObject.fromObject(tokener);
		T t = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		for (String fieldName : fieldNames) {
			if (null == jsonObject || jsonObject.isNullObject())
			{
				return t;
			}
			// 读取为结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		t = (T) JSONObject.toBean(jsonObject, clazz);
		IOUtil.close(reader);

		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param file
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValueByPath(final File file,
			final String path, final Class<T> clazz) {
		InputStream inputStream = null;
		T t = null;
		try {
			inputStream = new FileInputStream(file);
			t = readValueByPath(inputStream, path, clazz);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param url
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValueByPath(final URL url,
			final String path, final Class<T> clazz) {
		InputStream inputStream = null;
		T t = null;
		try {
			inputStream = url.openStream();
			t = readValueByPath(inputStream, path, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return t;
	}

	/**
	 * 
	 * @description 解析为 JSONObject 对象
	 * @param data
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> T readValueByPath(final byte[] data,
			final String path, final Class<T> clazz) {
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return readValueByPath(jsonString, path, clazz);
	}	
	
	/**
	 * 
	 * @description 解析为 集合对象
	 * @param jsonString
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValuesByPath(final String jsonString,
			final String path, final Class<T> clazz) {
		final JSONTokener tokener = new JSONTokener(jsonString);
		// 读取为结点
		JSONObject jsonObject = JSONObject.fromObject(tokener);
		List<T> list = null;
		JSONArray jsonArray = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		String fieldName = null;
		for (int i = 0; i < fieldNames.length; i++) {
			if (null == jsonObject || jsonObject.isNullObject())
			{
				return null;
			}
			fieldName = fieldNames[i];
			if ((fieldNames.length - 1) == i) { // 最后一个
				jsonArray = jsonObject.optJSONArray(fieldName);

				break;
			}
			// 读取为对象结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		list = new ArrayList<T>();
		T t= null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			jsonObject = jsonArray.getJSONObject(i);
			t = (T) JSONObject.toBean(jsonObject, clazz);
			list.add(t);
		}
		
		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param inputStream
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValuesByPath(
			final InputStream inputStream, final String path, final Class<T> clazz) {
		final String jsonString = IOUtil.getString(inputStream);
		final JSONTokener tokener = new JSONTokener(jsonString);
		// 读取为结点
		JSONObject jsonObject = JSONObject.fromObject(tokener);
		List<T> list = null;
		JSONArray jsonArray = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		String fieldName = null;
		for (int i = 0; i < fieldNames.length; i++) {
			if (null == jsonObject || jsonObject.isNullObject())
			{
				return null;
			}
			fieldName = fieldNames[i];
			if ((fieldNames.length - 1) == i) { // 最后一个
				jsonArray = jsonObject.optJSONArray(fieldName);

				break;
			}
			// 读取为对象结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		list = new ArrayList<T>();
		T t= null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			jsonObject = jsonArray.getJSONObject(i);
			t = (T) JSONObject.toBean(jsonObject, clazz);
			list.add(t);
		}
		IOUtil.close(inputStream);

		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param reader
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValuesByPath(final Reader reader,
			final String path, final Class<T> clazz) {
		final String jsonString = IOUtil.getString(reader);
		final JSONTokener tokener = new JSONTokener(jsonString);
		// 读取为结点
		JSONObject jsonObject = JSONObject.fromObject(tokener);
		List<T> list = null;
		JSONArray jsonArray = null;
		// 将 path 拆开
		final String[] fieldNames = path.split(PATH_SPLIT_REGEX);
		String fieldName = null;
		for (int i = 0; i < fieldNames.length; i++) {
			if (null == jsonObject || jsonObject.isNullObject())
			{
				return null;
			}
			fieldName = fieldNames[i];
			if ((fieldNames.length - 1) == i) { // 最后一个
				jsonArray = jsonObject.optJSONArray(fieldName);

				break;
			}
			// 读取为对象结点
			jsonObject = jsonObject.optJSONObject(fieldName);
		}
		list = new ArrayList<T>();
		T t= null;
		for (int i = 0; i < jsonArray.size(); i++)
		{
			jsonObject = jsonArray.getJSONObject(i);
			t = (T) JSONObject.toBean(jsonObject, clazz);
			list.add(t);
		}
		IOUtil.close(reader);

		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param file
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValuesByPath(final File file,
			final String path, final Class<T> clazz) {
		InputStream inputStream = null;
		List<T> list = null;
		try {
			inputStream = new FileInputStream(file);
			list = readValuesByPath(inputStream, path, clazz);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param url
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValuesByPath(final URL url,
			final String path, final Class<T> clazz) {
		InputStream inputStream = null;
		List<T> list = null;
		try {
			inputStream = url.openStream();
			list = readValuesByPath(inputStream, path, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 
	 * @description 解析为 集合对象
	 * @param data
	 * @param path
	 *            路径，例如 a.b.c 或 a
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	public static final <T> List<T> readValuesByPath(final byte[] data,
			final String path, final Class<T> clazz) {
		String jsonString = null;
		try {
			jsonString = new String(data, Constant.CHART_SET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return readValuesByPath(jsonString, path, clazz);
	}

	/* write object ================================= */

	/**
	 * 
	 * 描述: 输出对象到文件
	 * 
	 * @author qye.zheng
	 * @param outputStream
	 * @param value
	 * @return
	 */
	public static final boolean writeValue(final OutputStream outputStream,
			final Object value) {
		boolean flag = false;
		try {
			final JSONObject jsonObject = JSONObject.fromObject(value);
			final String jsonString = jsonObject.toString();
			IOUtil.write(jsonString, outputStream);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(outputStream);
		}

		return flag;
	}

	/**
	 * 
	 * 描述: 输出对象到文件
	 * 
	 * @author qye.zheng
	 * @param writer
	 * @param value
	 * @return
	 */
	public static final boolean writeValue(final Writer writer,
			final Object value) {
		boolean flag = false;
		try {
			final JSONObject jsonObject = JSONObject.fromObject(value);
			jsonObject.write(writer);
			writer.flush();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(writer);
		}

		return flag;
	}

	/**
	 * 
	 * 描述: 输出对象到文件
	 * 
	 * @author qye.zheng
	 * @param file
	 *            文件对象
	 * @param value
	 * @return
	 */
	public static final boolean writeValue(final File file, final Object value) {
		boolean flag = false;
		try {
			final JSONObject jsonObject = JSONObject.fromObject(value);
			final OutputStream outputStream = new FileOutputStream(file);
			final String jsonString = jsonObject.toString();
			IOUtil.write(jsonString, outputStream);
			IOUtil.close(outputStream);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 
	 * 描述: 输出到内存(字节数组)
	 * 
	 * @author qye.zheng
	 * @param value
	 * @return
	 */
	public static final byte[] writeAsBytes(final Object value) {
		byte[] data = null;
		try {
			final JSONObject jsonObject = JSONObject.fromObject(value);
			final String jsonString = jsonObject.toString();
			data = jsonString.getBytes(Constant.CHART_SET_UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * 
	 * 描述: 输出到内存(字符串)
	 * 
	 * @author qye.zheng
	 * @param value
	 * @return
	 */
	public static final String writeAsString(final Object value) {
		String data = null;
		try {
			final JSONObject jsonObject = JSONObject.fromObject(value);
			data = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	/* TODO 写 特定功能 ================================= */
	/**
	 * TODO json-lib 序列化时，对于系统式的对象类型也会做详细的输出，例如 Date类型，
	 * 会输出详细的信息而不只是一个日期字符串，可能需要构建一个处理器进行处理 
	 */
	
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameIncludeFilter(includeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameIncludeFilter(includeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameIncludeFilter(includeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameIncludeFilter(includeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameIncludeFilter(includeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameExcludeFilter(excludeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameExcludeFilter(excludeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameExcludeFilter(excludeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameExcludeFilter(excludeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
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
			final JsonConfig jsonConfig = new JsonConfig();
			final PropertyFilter filter = new PropertyNameExcludeFilter(excludeFields);
			// 序列化 过滤器
			jsonConfig.setJsonPropertyFilter(filter);
			// 反序列化过滤器
			//jsonConfig.setJavaPropertyFilter(filter);
			
			final JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
			result = jsonObject.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
}
