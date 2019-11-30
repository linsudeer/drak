package com.linsu.modular.blog.service;

import com.linsu.modular.blog.model.LoginLog;

public interface LoginLogService {

	/**
	 * 添加
	 * @param log
	 * @return
	 */
	long add(LoginLog log);

}
