package com.linsu.modular.blog.bean;

import com.linsu.core.support.IdGenerator;
import com.linsu.modular.blog.model.User;

import java.io.Serializable;

/**
 * 存储session有关
 * @author lisonglin
 * @date 2018年3月19日 下午6:10:08
 */
public class SessionInfo implements Serializable {
	
	/**
	 * token信息，每次请求必须携带cookie
	 */
	private static String token = IdGenerator.generate32UUID();

	/**
	 * sessionid
	 */
	private String sessionid = "";

	/**
	 * 用户类
	 */
	private User user;
	
	/**
	 * 在线人数
	 */
	private int pcu;

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		SessionInfo.token = token;
	}

	public int getPcu() {
		return pcu;
	}

	public void setPcu(int pcu) {
		this.pcu = pcu;
	}
	
}
