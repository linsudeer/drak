package com.linsu.core.support;
/**
 * 获得请求有关的request,session等
 * @author lisonglin
 * @date 2018年3月17日 上午11:02:35
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.linsu.modular.blog.bean.SessionInfo;
import com.linsu.modular.blog.constant.BlogConstant;
import com.linsu.modular.blog.model.User;

public class HttpServletRequestHolder {
	
	private static final String PROTOCOL = "http://";
	
	/**
	 * get request
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 返回远程项目访问路径
	 * @return
	 */
	public static String getRemoteUrl() {
		HttpServletRequest request = getHttpServletRequest();
		String remoteAddr = request.getRemoteAddr();
		int remotePort = request.getServerPort();
		String contextPath = request.getContextPath();
		return PROTOCOL+remoteAddr + ":"+remotePort+"/"+contextPath+"/";
	}
	
	/**
	 * get session
	 * @return
	 */
	public static HttpSession getHttpSession() {
		HttpSession session = getHttpServletRequest().getSession();
		return session;
	}
	
	/**
	 * 设置session不可用，同时清空SessionInfo对象
	 */
	public static void sessionInvalidate() {
		HttpSession session = getHttpSession();
		session.setAttribute(BlogConstant.SESSION_KEY, null);
		session.invalidate();
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public static SessionInfo getSessionInfo() {
		HttpSession session = getHttpSession();
		return (SessionInfo) session.getAttribute(BlogConstant.SESSION_KEY);
	}
	
	/**
	 * 设置session值
	 * @param sessionInfo
	 */
	public static void setSessionInfo(SessionInfo sessionInfo) {
		HttpSession session = getHttpSession();
		sessionInfo.setSessionid(session.getId());
		session.setAttribute(BlogConstant.SESSION_KEY, sessionInfo);
	}
	
	/**
	 * 返回IP
	 * @return
	 */
	public static String getIp() {
		return getHttpServletRequest().getRemoteHost();
	}
	
	/**
	 * 返回当前登录用户
	 * @return
	 */
	public static User getUser() {
		SessionInfo sessionInfo = getSessionInfo();
		if(sessionInfo != null) {
			return sessionInfo.getUser();
		}
		return null;
	}
	
	/**
	 * 返回当前登录的用户Id
	 * @return
	 */
	public static Long getUserId() {
		User user = getUser();
		if(user != null) 
			return user.getOid();
		return -1L;
	}
	
	/**
	 * 返回用户昵称
	 * @return
	 */
	public static String getNickName() {
		User user = getUser();
		if(user != null) 
			return user.getUserNickname();
		return "";
				
	}
}
