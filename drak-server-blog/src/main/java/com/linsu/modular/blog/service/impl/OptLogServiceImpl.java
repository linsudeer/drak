package com.linsu.modular.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linsu.core.support.IdGenerator;
import com.linsu.core.support.SqlHelper;
import com.linsu.modular.blog.dao.OptLogMapper;
import com.linsu.modular.blog.model.OptLog;
import com.linsu.modular.blog.service.OptLogService;
@Service("optLogService")
public class OptLogServiceImpl implements OptLogService{
	
	@Autowired
	private OptLogMapper logMapper;

	@Override
	public long add(OptLog log) {
		log.setOid(IdGenerator.generateOId());
		if(SqlHelper.retBool(logMapper.insert(log))) {
			return log.getOid();
		}
		return 0L;
	}

}
