package com.linsu.modular.blog.constant;
/**
 * 常量
 * @author lisonglin
 * @date 2018年3月19日 下午6:12:54
 */
public class BlogConstant {

	/**
	 * session key
	 */
	public static final String SESSION_KEY = "BLOG_SESSION";

	public static final String SHIRO_COKIE = "SHIRO_COKIE";
	
	/**
	 * 在线状态：在线
	 */
	public static final String USER_ONLINE = "ON";
	
	/**
	 * 在线状态：离线
	 */
	public static final String USER_OFFLINE = "OFF";
	
	/**
	 * 文章类型：草稿
	 */
	public static final Integer ARTICLE_TYPE_DRAFT = 1;
	
	/**
	 * 文章类型：发布
	 */
	public static final Integer ARTICLE_TYPE_PUBLISH = 2;

	
}
