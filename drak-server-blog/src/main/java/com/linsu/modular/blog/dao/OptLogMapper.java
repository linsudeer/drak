package com.linsu.modular.blog.dao;

import com.linsu.modular.blog.model.OptLog;

public interface OptLogMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(OptLog record);

    int insertSelective(OptLog record);

    OptLog selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(OptLog record);

    int updateByPrimaryKeyWithBLOBs(OptLog record);

    int updateByPrimaryKey(OptLog record);
}