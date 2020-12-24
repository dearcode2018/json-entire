/**
  * @filename PropertyNameExcludeFilter.java
  * @description  
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.filter;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.hua.util.EmptyUtil;

 /**
 * @type PropertyNameExcludeFilter
 * @description  
 * @author qianye.zheng
 */
public final class PropertyNameExcludeFilter implements PropertyFilter {

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
	 * @description 返回false-不序列化该属性
	 * @param object
	 * @param name
	 * @param value
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public boolean apply(Object object, String name, Object value) {
		if (!EmptyUtil.isEmpty(excludeFields))
		{
			for (String excludeField : excludeFields)
			{
				if (excludeField.equals(name))
				{
					return false;
				}
			}
		}
		
		// 为空或没有匹配的 则序列化所有的字段
		return true;
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
