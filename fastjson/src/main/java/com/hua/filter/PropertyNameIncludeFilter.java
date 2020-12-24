/**
  * @filename PropertyNameIncludeFilter.java
  * @description  
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.filter;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.hua.util.EmptyUtil;

 /**
 * @type PropertyNameIncludeFilter
 * @description 属性/字段名称 包含 过滤器
 * 
 * @author qianye.zheng
 */
public class PropertyNameIncludeFilter implements PropertyFilter {

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
	 * @description 返回false-不序列化该属性
	 * @param object
	 * @param name
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean apply(Object object, String name, Object value) {
		if (!EmptyUtil.isEmpty(includeFields))
		{
			for (String includeFiled : includeFields)
			{
				if (includeFiled.equals(name))
				{
					return true;
				}
			}
		}
		
		// 为空或没有匹配的 则所有字段都不序列化
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
