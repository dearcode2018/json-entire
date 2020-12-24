/**
  * @filename PropertyNameExcludeFilter.java
  * @description  
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.filter;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.hua.util.EmptyUtil;

 /**
 * @type PropertyNameExcludeFilter
 * @description  
 * @author qianye.zheng
 */
public final class PropertyNameExcludeFilter implements ExclusionStrategy {

	/* 不序列化 数组中的字段，若为空，则序列化所有的字段 */
	private String[] excludeFields;
	
	/**
	 * 
	 * @description 构造方法
	 * @param excludeFields
	 * @author qianye.zheng
	 */
	public PropertyNameExcludeFilter(String[] excludeFields) {
		super();
		this.excludeFields = excludeFields;
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
		if (!EmptyUtil.isEmpty(excludeFields))
		{
			for (String excludeField : excludeFields)
			{
				if (excludeField.equals(name))
				{
					return true;
				}
			}
		}
		
		// 为空或没有匹配的 则序列化所有的字段
		return false;
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
	 * @return the excludeFields
	 */
	public final String[] getExcludeFields() {
		return excludeFields;
	}

	/**
	 * @param excludeFields the excludeFields to set
	 */
	public final void setExcludeFields(String[] excludeFields) {
		this.excludeFields = excludeFields;
	}

}
