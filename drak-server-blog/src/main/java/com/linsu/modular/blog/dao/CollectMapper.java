package com.linsu.modular.blog.dao;

import com.linsu.modular.blog.model.Collect;

public interface CollectMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}