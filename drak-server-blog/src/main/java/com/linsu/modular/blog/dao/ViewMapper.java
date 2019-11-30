package com.linsu.modular.blog.dao;

import com.linsu.modular.blog.model.View;

public interface ViewMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(View record);

    int insertSelective(View record);

    View selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(View record);

    int updateByPrimaryKey(View record);
}