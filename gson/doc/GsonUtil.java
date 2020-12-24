/**
 * GsonUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hua.constant.FormatConstant;

/**
 * GsonUtil
 * 描述: Google Json - 工具类
 * @author  qye.zheng
 */
public final class GsonUtil
{

	/* gson 构建器 */
	private static final GsonBuilder gsonBuilder = new GsonBuilder();
	
	/* Gson */
	private static Gson gson;
	
	static
	{
		// 设置为优美的输出 (有格式的)
		gsonBuilder.setPrettyPrinting();
		// 使用默认日期格式
		setDefaultDateFormat();
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
	 * 
	 * 描述: 使用指定的日期格式
	 * @author  qye.zheng
	 * @param dateFormat
	 */
	public static final void setDateFormat(final String dateFormat)
	{
		gson = gsonBuilder.setDateFormat(dateFormat).create();
	}

	/**
	 * 
	 * 描述: 使用默认的日期格式 
	 * @author  qye.zheng
	 */
	private static final void setDefaultDateFormat()
	{
		gson = gsonBuilder.setDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss).create();
	}
	
	
	/* read value 返回单个对象 ================================= */
	
	
	/**
	 * 
	 * 描述: 从 reader 中读取 并 解析对象
	 * @author  qye.zheng
	 * @param reader
	 * @param clazz
	 * @return
	 */
	public static final <T> T fromJson(final Reader reader, final Class<T> clazz)
	{
		final T t = gson.fromJson(reader, clazz);
		
		return t;
	}
	
	/**
	 * 
	 * 描述: 根据json字符串 解析对象
	 * @author  qye.zheng
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static final <T> T fromJson(final String json, final Class<T> clazz)
	{
		final T t = gson.fromJson(json, clazz);
		
		return t;
	}
	
	/* read values 返回单个对象 ================================= */
	
	
	
	/* write object 返回单个对象 ================================= */
	
	/**
	 * 
	 * 描述: 转成 json 输出到指定可附加对象
	 * @author  qye.zheng
	 * @param value
	 * @param appendable
	 */
	public static final void toJson(final Object value, final Appendable appendable)
	{
		gson.toJson(value, appendable);
	}
	
	/**
	 * 
	 * 描述: 将对象输出为 json字符串 
	 * @author  qye.zheng
	 * @param value
	 * @return
	 */
	public static final String toJson(final Object value)
	{
		final String json = gson.toJson(value);
		
		return json;
	}
	
	/* 特定功能 ================================= */
	
}
