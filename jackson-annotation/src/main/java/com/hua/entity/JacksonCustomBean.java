/**
  * @filename JacksonCustomBean.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hua.serializer.DateDeserializer;
import com.hua.serializer.DateSerializer;

 /**
 * @type JacksonCustomBean
 * @description 
 * @author qye.zheng
 */
public class JacksonCustomBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 序列化: 在getter方法上标注解(实现 JsonSerializer 接口)
	 * 反序列化: 在setter方法上标注解(实现 JsonDeserializer 接口)
	 * 需要格式化处理: 日期-时间/数字/货币/文本... 就需要在指定的字段上添加相应的注解.
	 * 
	 */
	
	/* 姓名 */
	private String name;
	
	/* 性别 : true-男, false - 女 */
	private boolean sex = true;
	
	/* 积分 */
	private Double credit;
	
	/* 出生日期 yyyy-MM-dd */
	private Date birthday;
	
	/* 住址 */
	private String address;
	
	private Collection<JacksonItemBean> items = new ArrayList<JacksonItemBean>();

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
	 * @return the sex
	 */
	public final boolean isSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public final void setSex(boolean sex) {
		this.sex = sex;
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
	 * @JsonSerialize(using = DateSerializer.class)
	 * @return the birthday
	 */
	@JsonSerialize(using = DateSerializer.class)
	public final Date getBirthday() {
		return birthday;
	}

	/**
	 * @JsonDeserialize(using = DateDeserializer.class)
	 * @param birthday the birthday to set
	 */
	@JsonDeserialize(using = DateDeserializer.class)
	public final void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the address
	 */
	public final String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public final void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the items
	 */
	public final Collection<JacksonItemBean> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public final void setItems(Collection<JacksonItemBean> items) {
		this.items = items;
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
