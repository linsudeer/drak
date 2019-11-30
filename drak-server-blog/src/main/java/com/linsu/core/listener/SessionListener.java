package com.linsu.core.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.linsu.modular.blog.bean.SessionInfo;
import com.linsu.modular.blog.constant.BlogConstant;
import com.linsu.modular.blog.model.User;
import com.linsu.modular.blog.service.UserService;
/**
 * session 监听器
 * @author lisonglin
 * @date 2018年3月26日 上午11:17:45
 */
@WebListener
public class SessionListener implements HttpSessionListener{
	
	private Logger logger = LoggerFactory.getLogger(SessionListener.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 在线人数
	 */
	private static int count = 0;

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(BlogConstant.SESSION_KEY);
		if(sessionInfo != null) {
			sessionInfo.setPcu(count++);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		//修改在线标志
		HttpSession session = sessionEvent.getSession();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(BlogConstant.SESSION_KEY);
		if(sessionInfo != null) {
			User user = sessionInfo.getUser();
			sessionInfo.setPcu(count--);
			if(user!=null) {
				userService.setOnLine(user.getOid(), false);
				if(logger.isDebugEnabled()) {
					logger.debug("用户退出");
				}
			}
		}
		
	}

}
