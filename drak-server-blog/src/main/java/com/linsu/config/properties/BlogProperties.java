package com.linsu.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="blog")
public class BlogProperties {

	/**
	 * session过期时间
	 */
	private Integer sessionInvalidateTime = 30*60;


	/**
	 * session 验证失效时间（默认为15分钟 单位：秒）
	 */
	private Integer sessionValidationInterval = 15 * 60;

	public Integer getSessionInvalidateTime() {
		return sessionInvalidateTime;
	}

	public void setSessionInvalidateTime(Integer sessionInvalidateTime) {
		this.sessionInvalidateTime = sessionInvalidateTime;
	}

	public Integer getSessionValidationInterval() {
		return sessionValidationInterval;
	}

	public void setSessionValidationInterval(Integer sessionValidationInterval) {
		this.sessionValidationInterval = sessionValidationInterval;
	}
}
