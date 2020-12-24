/**
  * @filename PropertyNameIncludeFilter.java
  * @description  
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.filter;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.hua.util.EmptyUtil;

 /**
 * @type PropertyNameIncludeFilter
 * @description 属性/字段名称 包含 过滤器
 * 
 * @author qianye.zheng
 */
public class PropertyNameIncludeFilter implements ExclusionStrategy {

	/* 只序列化 数组中的字段，为空则所有字段都不序列化 */
	private String[] includeFields;
	
	/**
	 * 
	 * @description 构造方法
	 * @param includeFields
	 * @author qianye.zheng
	 */
	public PropertyNameIncludeFilter(String[] includeFields) {
		super();
		this.includeFields = includeFields;
	}

	/**
	 * @description 返回true-不序列化该属性
	 * @param field
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes field) {
		// 获取字段名称
		final String name = field.getName();
		if (!EmptyUtil.isEmpty(includeFields))
		{
			for (String includeFiled : includeFields)
			{
				if (includeFiled.equals(name))
				{
					return false;
				}
			}
		}
		
		// 为空或没有匹配的 则所有字段都不序列化
		return true;
	}

	/**
	 * @description 
	 * @param clazz
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}
	
	/**
	 * @return the includeFields
	 */
	public final String[] getIncludeFields() {
		return includeFields;
	}

	/**
	 * @param includeFields the includeFields to set
	 */
	public final void setIncludeFields(String[] includeFields) {
		this.includeFields = includeFields;
	}

}
