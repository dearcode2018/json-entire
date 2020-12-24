/**
 * 描述: 
 * UserBean.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.hua.constant.sys.UserType;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * UserBean
 */
/*
 * JavaBean中缺少字段，json中含有更多的字段，
 * 方法1: 在JavaBean中标注: @JsonIgnoreProperties(ignoreUnknown = true)
 * 方法2: ObjectMapper.disable或ObjectMapper.without方法即可
或者调用ObjectMapper.configure(Feature, true/false设置)
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
/*
 * 配合 JacksonUtil.writeExcludexx 和 writeExclude xx 来使用
 */
@JsonFilter("com.hua.bean.UserBean")
public final class UserBean extends BaseBean {

	 /* long */
	private static final long serialVersionUID = 1L;
	
	/* 登录-用户名 (唯一) */
	private String username;
	
	/* 昵称 (用于显示) */
	/**
	 * 属性标注了@JsonIgnore后，不会参与序列化
	 */
	//@JsonIgnore
	private String nickname;
	
	/* 登录-密码 */
	private String password;
	
	/* 用户类型 */
	private UserType type;
	
	/* 用户状态 - 是否有效 默认 : 有效 */
	private boolean valid = true;
	
	/* 上一次登录-时间 */
	private Date lastLoginTime;
	
	/* 上一次登录-IP地址 */
	private String lastLoginIp;
	
	private List<User> users;
	
	/** 无参构造方法 */
	public UserBean() {}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the type
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(UserType type) {
		this.type = type;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime()
	{
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the lastLoginIp
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * @param lastLoginIp the lastLoginIp to set
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname()
	{
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid()
	{
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	/**
	 * @return the users
	 */
	public final List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public final void setUsers(List<User> users) {
		this.users = users;
	}

}
