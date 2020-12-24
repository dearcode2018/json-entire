/**
  * @filename DateValueProcessor.java
  * @description  
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.processor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.hua.constant.FormatConstant;

 /**
 * @type DateValueProcessor
 * @description  java.util.Date 序列化值 输出处理器
 * 反序列化，将日期时间 字符串 转成 java.util.Date类型
 * @author qianye.zheng
 */
public class DateValueProcessor implements JsonValueProcessor {

	private DateFormat format;
	
	/**
	 * 
	 * @description 构造方法
	 * @param pattern 日期时间格式
	 * @author qianye.zheng
	 */
	public DateValueProcessor(final String pattern) {
		format = new SimpleDateFormat(pattern);
	}
	
	/**
	 * 
	 * @description 默认构造方法
	 * @author qianye.zheng
	 */
	public DateValueProcessor() {
		// 默认的日期时间格式
		this(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
	}
	
	/**
	 * @description 
	 * @param value
	 * @param jsonConfig
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return null;
	}

	/**
	 * @description 
	 * @param key
	 * @param value
	 * @param jsonConfig
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		/**
		 * 序列化支持 java.util.Date java.sql.Date
		 * 反序列化: 将指定格式日期时间转成 java.util.Date对象
		 */
		// java.util.Date java.sql.Date 都会被识别
		if (value instanceof Date)
		{
			final String result = format.format(value);
			
			return result;
		} else if (value instanceof String)
		{ // 日期时间 反序列化
			try {
				final Date date = format.parse((String) value);
				
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		} else
		{
			return null;
		}
		final String result = format.format(value);
		
		return result;
	}

}
