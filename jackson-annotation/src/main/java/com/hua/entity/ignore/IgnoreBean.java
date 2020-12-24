/**
  * @filename IgnoreBean.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.entity.ignore;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

 /**
 * @type IgnoreBean
 * @description 
 * @author qye.zheng
 */
//@JsonIgnoreProperties --无法解决Json串包含多余字段问题
// 设置ignoreUnknown 为true才能解决Json串包含多余字段问题
@JsonIgnoreProperties(ignoreUnknown = true)
// @JsonAutoDetect --无法解决Json串包含多余字段问题
public final class IgnoreBean {
	
	/**
	 * 在字段、setter/getter方法上标注@JsonIgnore 都会
	 * 消除序列化/反序列化的效果
	 */
	
	//@JsonIgnore(false)
	//@JsonIgnore(value = false)
	//@JsonIgnore()
	@JsonIgnore
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
	//@JsonIgnore
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	//@JsonIgnore
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
	@JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
	public final Date getDatetime() {
		return datetime;
	}

	/**
	 * @JsonDeserialize(using = DateTimeDeserializer.class)
	 * 在反序列化时，使用 @JsonDeserialize 或 @JsonFormat 均可以
	 * @param datetime the datetime to set
	 */
	//@JsonDeserialize(using = DateTimeDeserializer.class)
	@JsonFormat(locale = "zh", pattern = "yyyy-MM-dd HH:mm:ss")
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
