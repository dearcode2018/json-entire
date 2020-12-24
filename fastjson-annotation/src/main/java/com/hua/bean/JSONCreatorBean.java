/**
 * 描述: 
 * JSONCreatorBean.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.hua.constant.sys.UserType;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * JSONCreatorBean
 */
/*
 * JavaBean中缺少字段，json中含有更多的字段，
 * 方法1: 在JavaBean中标注: @JsonIgnoreProperties(ignoreUnknown = true)
 * 方法2: ObjectMapper.disable或ObjectMapper.without方法即可
或者调用ObjectMapper.configure(Feature, true/false设置)
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public final class JSONCreatorBean extends BaseBean {

	 /* long */
	private static final long serialVersionUID = 1L;
	
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
	
	/* 上一次登录-时间 */
	private Date lastLoginTime;
	
	/* 上一次登录-IP地址 */
	private String lastLoginIp;
	
	private String full_name;
	
	/**
	 * 使用了 @JSONCreator 之后，应该注释掉此方法
	 */
	/** 无参构造方法 */
	//public JSONCreatorBean() {}

	/**
	 * 
	 * @description 构造方法
	 * @param username
	 * @param lastLoginTime
	 * @author qianye.zheng
	 */
	/*
	 * 通过 @JSONCreator 指定 Bean的默认构造方法
	 * 此时，不应该再包含有无参默认构造方法，应将其注释掉
	 * @JSONField(name = "username")
	 */
	/*
	 * @JSONCreator 与 jackson 的 @JsonCreator 不同
	 * @JSONCreator 只注入 @JSONField标注的值，而且必须存在
	 * 该属性，否则注入失败.
	 * 在注入到构造方法或静态工厂方法后，可以另行赋值
	 */
	@JSONCreator
	// 多个参数 作为构造方法参数时，必须指定参数名称
	public JSONCreatorBean(@JSONField(name = "full_name") String username, 
			@JSONField(name = "lastLoginTime") Date lastLoginTime)
	{
		/**
		 * 最终，哪些属性有值，取决于此方法的具体设置操作了
		 */
		// 为空，说明是通过 属性直接注入的
		System.out.println("this.full_name: " + this.full_name);
		System.out.println("full_name: " + username);
		System.out.println("JSONCreatorBean.JSONCreatorBean()");
		this.username = username;
		this.lastLoginTime = lastLoginTime;
	}
	
	/**
	 * 构造方法没有标注@JSONCreator时，静态工厂方法 发挥作用.
	 */
	/**
	 * 
	 * @description 静态工厂方法
	 * @param username
	 * @param lastLoginTime
	 * @return
	 * @author qianye.zheng
	 */
	@JSONCreator
	public static final JSONCreatorBean getInstance(@JSONField(name = "full_name") String username, 
			@JSONField(name = "lastLoginTime") Date lastLoginTime)
	{
		System.out.println("JSONCreatorBean.getInstance()");
		return new JSONCreatorBean(username, lastLoginTime);
	}
	
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
	 * @return the full_name
	 */
	public final String getFull_name() {
		return full_name;
	}

	/**
	 * @param full_name the full_name to set
	 */
	public final void setFull_name(String full_name) {
		this.full_name = full_name;
	}

}
