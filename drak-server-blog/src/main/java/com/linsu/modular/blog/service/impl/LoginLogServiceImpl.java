package com.linsu.modular.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linsu.core.support.IdGenerator;
import com.linsu.core.support.SqlHelper;
import com.linsu.modular.blog.dao.LoginLogMapper;
import com.linsu.modular.blog.model.LoginLog;
import com.linsu.modular.blog.service.LoginLogService;

@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService{
	
	@Autowired
	private LoginLogMapper logMapper;

	@Override
	public long add(LoginLog log) {
		log.setOid(IdGenerator.generateOId());
		if(SqlHelper.retBool(logMapper.insert(log))) {
			return log.getOid();
		}
		return 0L;
	}

}
