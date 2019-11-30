package com.linsu.modular.blog.dao;

import com.linsu.modular.blog.model.LoginLog;

public interface LoginLogMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKeyWithBLOBs(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}