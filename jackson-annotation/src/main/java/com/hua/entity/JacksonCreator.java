/**
 * 描述: 
 * JacksonCreator.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.entity;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hua.bean.BaseBean;
import com.hua.constant.sys.UserType;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * JacksonCreator
 */
/*
 * JavaBean中缺少字段，json中含有更多的字段，
 * 方法1: 在JavaBean中标注: @JsonIgnoreProperties(ignoreUnknown = true)
 * 方法2: ObjectMapper.disable或ObjectMapper.without方法即可
或者调用ObjectMapper.configure(Feature, true/false设置)
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public final class JacksonCreator extends BaseBean {

	/**
	 * @JsonCreator 搭配 @JsonProperty使用
	 * 可以用在构造方法、静态工厂方法上，通过特定的构造方式
	 * 来反序列化.
	 * 方式1: 构造方法，默认是无参构造方法，可以搭配 @JsonProperty 用在有参构造方法中
	 * 方式2: 授权式构造，构造器只有一个参数，例如 Map，不能使用@JsonProperty注解
	 * 方式3: 有参、无参静态工厂方法
	 * 若3种方式都存在，则以构造方法优先
	 * 
	 * 通过这2种方式构造对象，构造对象之后，再去设置其他属性，而不仅仅只是初始化 参数列表中
	 * 中的字段
	 * 
	 */
	
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
	
	/** 无参构造方法 */
	public JacksonCreator() {}
	
	/**
	 * 
	 * @description 构造方法
	 * @param username
	 * @param lastLoginTime
	 * @author qianye.zheng
	 */
	//@JsonCreator
	// 多个参数 作为构造方法参数时，必须用 @JsonProperty注解，否则 无法识别，单个参数无需注解
	public JacksonCreator(@JsonProperty("full_name") String username, @JsonProperty("lastLoginTime") Date lastLoginTime) 
	{
		super();
		System.out.println("JacksonCreator.JacksonCreator1()");
		this.username = username;
		/**
		 * username已经被使用了，因此不再设置 this.username的值，
		 * 而lastLoginIp在json串中还没使用，因此还会去设置值
		 * 当然，这样做是没有意义的
		 */
		//this.lastLoginIp = username;
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 
	 * @description 构造方法
	 * @param map
	 * @author qianye.zheng
	 */
	@JsonCreator
	// 单个参数无需注解
	public JacksonCreator(final Map<String, Object> map) 
	{
		/**
		 * 通过 map 参数这种方式，需要手工去设置值，jackson不会再干预其中
		 */
		System.out.println("JacksonCreator.JacksonCreator2()");
		this.username = (String) map.get("full_name");
		/**
		 * 注意，json中都是字符串形式，需要进行类型转换
		 * 字符串 数字、布尔 都是可以直接强转类型的，但是日期时间/货币等格式文本作为字符串是不能直接强转的，
		 * 需要有一个格式化的过程.
		 */
		//this.lastLoginTime = (Date) map.get("lastLoginTime");
	}	
	
	/**
	 * 
	 * @description 静态工厂方法
	 * @param username
	 * @param lastLoginTime
	 * @return
	 * @author qianye.zheng
	 */
	@JsonCreator
	public static final JacksonCreator getInstance(@JsonProperty("full_name") String username, @JsonProperty("lastLoginTime") Date lastLoginTime)
	{
		System.out.println("JacksonCreator.getInstance()");
		return new JacksonCreator(username, lastLoginTime);
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

	
}
