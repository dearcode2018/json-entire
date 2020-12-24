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
public class JacksonFormatBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 序列化: 在getter方法上标注解(实现 JsonSerializer 接口)
	 * 反序列化: 在setter方法上标注解(实现 JsonDeserializer 接口)
	 * 需要格式化处理: 日期-时间/数字/货币/文本... 就需要在指定的字段上添加相应的注解.
	 * 
	 * @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
	 * 
	 */
	
	/* 名称 */
	private String name;
	
	/* 积分分 */
	private Double credit;
	
	/* 描述 */
	private String description;
	
	/**
	 * 一般 序列化和反序列化格式相同，
	 * @JsonFormat可以直接标注在字段上
	 * 或者 @JsonSerialize @JsonDeserialize
	 * 同时标注在字段上
	 */
	/* 下单日期时间 yyyy-MM-dd HH:mm:ss */
	//@JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
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
	/*
	 * 若没有格式化注解，则默认输出为类似: "datetime":1426576090205
	 * 序列化时，使用 @JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
	 * 或 @JsonSerialize(using = DateTimeSerializer.class) 均可以
	 */
	// @JsonSerialize(using = DateTimeSerializer.class)
	//@JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
	public final Date getDatetime() {
		return datetime;
	}

	/**
	 * @JsonDeserialize(using = DateTimeDeserializer.class)
	 * 在反序列化时，使用 @JsonDeserialize 或 @JsonFormat 均可以
	 * @param datetime the datetime to set
	 */
	//@JsonDeserialize(using = DateTimeDeserializer.class)
	//@JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
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
