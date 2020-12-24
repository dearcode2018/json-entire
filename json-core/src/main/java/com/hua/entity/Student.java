/**
* Student.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述: 
 * @author qye.zheng
 * Student
 */
public final class Student extends BaseEntity implements Serializable {

	/* long */
	private static final long serialVersionUID = 995693725312587589L;
	
	/* 学生姓名 */
	private String name;
	
	/* 性别 : true-男, false - 女 */
	private boolean sex = true;
	
	/* 学分 */
	private Double credit;
	
	/* 出生日期 yyyy-MM-dd */
	private Date birthday;
	
	/* 住址 */
	private String address;
	
	/* 课程 */
	private List<Course> courses = new ArrayList<Course>();
	
	/** 无参构造方法 */
	public Student() {}

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
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param course
	 * @return
	 */
	public boolean addCourse(final Course course) {
		
		return this.courses.add(course);
	}
	
	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	/**
	 * @return the sex
	 */
	public boolean isSex()
	{
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(boolean sex)
	{
		this.sex = sex;
	}
	
}
