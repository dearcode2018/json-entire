/**
 * 描述: 
 * JSONFieldBean.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.hua.constant.Constant;
import com.hua.constant.sys.UserType;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * JSONFieldBean
 */
/*
 * JavaBean中缺少字段，json中含有更多的字段，
 * 方法1: 在JavaBean中标注: @JsonIgnoreProperties(ignoreUnknown = true)
 * 方法2: ObjectMapper.disable或ObjectMapper.without方法即可
或者调用ObjectMapper.configure(Feature, true/false设置)
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public final class JSONFieldBean extends BaseBean {

	 /* long */
	private static final long serialVersionUID = 1L;
	
	/* 登录-用户名 (唯一) */
	/**
	 * @JSONField 可以指定输出字段的名称
	 */
	@JSONField(name = "new_name")
	/* 登录-用户名 (唯一) */
	private String username;
	
	/* 昵称 (用于显示) */
	private String nickname;
	
	/* 登录-密码 */
	private String password;
	
	/* 用户类型 */
	private UserType type;
	
	/* 用户状态 - 是否有效 默认 : 有效 */
	private boolean valid = true;
	
	/**
	 * @JSONField 还可以指定字段的格式
	 * ordinal 指定序列化后的排列顺序
	 */
	/* 上一次登录-时间 */
	@JSONField(format = 
			Constant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss, ordinal = -10)
	private Date lastLoginTime;
	
	/* 上一次登录-IP地址 */
	private String lastLoginIp;
	
	/** 无参构造方法 */
	public JSONFieldBean() {}

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

	
}
