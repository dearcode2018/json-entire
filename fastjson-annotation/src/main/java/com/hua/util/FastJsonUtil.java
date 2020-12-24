/**
 * FastJsonUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * FastJsonUtil
 * 描述: Fast Json - 工具类
 * @author  qye.zheng
 */
public final class FastJsonUtil
{

	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private FastJsonUtil()
	{
	}

	/* read value 返回单个对象 ================================= */
	
	/**
	 * 
	 * 描述: 解析为对象
	 * @author  qye.zheng
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static final <T> T parseObject(final String json, final Class<T> clazz)
	{
		final T t = JSON.parseObject(json, clazz);
		
		return t;
	}
	
	/* read values 返回单个对象 ================================= */
	
	
	/**
	 * 
	 * 描述: 解析为对象的集合
	 * @author  qye.zheng
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> parseArray(final String json, final Class<T> clazz)
	{
		final List<T> list = JSON.parseArray(json, clazz);
		
		return list;
	}
	
	/* write object 返回单个对象 ================================= */
	
	/**
	 * 
	 * 描述: 将对象转成json字符串
	 * @author  qye.zheng
	 * @param value
	 * @return
	 */
	public static final String toJson(final Object value)
	{
		final String json = JSON.toJSONString(value);
		
		return json;
	}
	
	/* 特定功能 ================================= */
	
	
}
