package com.linsu.modular.blog.dao;

import com.linsu.modular.blog.model.Invitecode;

public interface InvitecodeMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(Invitecode record);

    int insertSelective(Invitecode record);

    Invitecode selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(Invitecode record);

    int updateByPrimaryKey(Invitecode record);
}