package com.linsu.modular.blog.service.impl;

import com.linsu.core.status.UserStatus;
import com.linsu.core.support.HttpServletRequestHolder;
import com.linsu.core.support.IdGenerator;
import com.linsu.core.support.SqlHelper;
import com.linsu.core.util.MD5Util;
import com.linsu.modular.blog.bean.SessionInfo;
import com.linsu.modular.blog.constant.BlogConstant;
import com.linsu.modular.blog.dao.UserMapper;
import com.linsu.modular.blog.model.User;
import com.linsu.modular.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional
	public long register(User user) {
		user.setOid(IdGenerator.generateOId());
		user.setUserStatus(UserStatus.OK.getCode());
		user.setUserPassword(MD5Util.encrypt(user.getUserPassword()));
		if(SqlHelper.retBool(userMapper.insertSelective(user))){
			return user.getOid();
		}
		return 0L;
	}

	@Override
	@Transactional
	public boolean update(User user) {
		if(user != null && user.getOid()!=null) {
			return SqlHelper.retBool(userMapper.updateByPrimaryKey(user));
		}
		return false;
	}

	@Override
	public boolean isExistEmail(String email) {
		return SqlHelper.retBool(userMapper.isExistEmail(email));
	}

	@Override
	public boolean isExistNickName(String nickName) {
		return SqlHelper.retBool(userMapper.isExistNickName(nickName));
	}

	@Override
	public void login(String userName, String userEmail, String password) {

		SessionInfo sessionInfo = new SessionInfo();
		User user = userMapper.getByLogin(userName, userEmail, MD5Util.encrypt(password));
		sessionInfo.setUser(user);
		sessionInfo.setPcu(sessionInfo.getPcu()+1);
		HttpServletRequestHolder.setSessionInfo(sessionInfo);
	}

	@Override
	public void setOnLine(Long userId, Boolean status) {
		userMapper.setOnLine(userId,status?BlogConstant.USER_ONLINE:BlogConstant.USER_OFFLINE);
	}

	@Override
	public User getByLoginName(String loginName) {

		return userMapper.getByLoginName(loginName);
	}

}
