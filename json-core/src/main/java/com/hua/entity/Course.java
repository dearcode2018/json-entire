/**
* Course.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.entity;

import java.io.Serializable;

/**
 * 描述: 
 * @author qye.zheng
 * Course
 */
public final class Course extends BaseEntity implements Serializable {

	/* long */
	private static final long serialVersionUID = -5443210690507880081L;
	
	/* 课程名称 */
	private String name;
	
	/* 该课程的学分 */
	private Double credit;
	
	/* 课程描述 */
	private String description;
	
	/** 无参构造方法 */
	public Course() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the credit
	 */
	public Double getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Double credit) {
		this.credit = credit;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	

}
