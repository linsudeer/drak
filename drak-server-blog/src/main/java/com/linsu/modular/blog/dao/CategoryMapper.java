package com.linsu.modular.blog.dao;

import com.linsu.modular.blog.model.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectAllAvailable();
}