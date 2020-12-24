/**
  * @filename TypeIgnore.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.entity.ignore;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

 /**
 * @type TypeIgnore
 * @description 
 * @author qye.zheng
 */
/**
 * 该类型标注了@JsonIgnoreType 之后，被A引用了之后，A在序列化的时候，
 * 将忽略掉该类型的属性
 */
@JsonIgnoreType
public class TypeIgnore {

	/* 名称 */
	private String name;

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	
}
