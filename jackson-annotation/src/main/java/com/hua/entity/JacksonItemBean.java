/**
  * @filename JacksonItemBean.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hua.serializer.DateTimeDeserializer;
import com.hua.serializer.DateTimeSerializer;

 /**
 * @type JacksonItemBean
 * @description 
 * @author qye.zheng
 */
public class JacksonItemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 序列化: 在getter方法上标注解(实现 JsonSerializer 接口)
	 * 反序列化: 在setter方法上标注解(实现 JsonDeserializer 接口)
	 * 需要格式化处理: 日期-时间/数字/货币/文本... 就需要在指定的字段上添加相应的注解.
	 * 
	 */
	
	/* 名称 */
	private String name;
	
	/* 积分分 */
	private Double credit;
	
	/* 描述 */
	private String description;
	
	/* 下单日期时间 yyyy-MM-dd HH:mm:ss */
	private Date datetime;

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

	/**
	 * @return the credit
	 */
	public final Double getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to set
	 */
	public final void setCredit(Double credit) {
		this.credit = credit;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @JsonSerialize(using = DateTimeSerializer.class)
	 * @return the datetime
	 */
	@JsonSerialize(using = DateTimeSerializer.class)
	public final Date getDatetime() {
		return datetime;
	}

	/**
	 * @JsonDeserialize(using = DateTimeDeserializer.class)
	 * @param datetime the datetime to set
	 */
	@JsonDeserialize(using = DateTimeDeserializer.class)
	public final void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	/**
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public String toString() {
		
		return new ReflectionToStringBuilder(this).toString();
	}
	
}
