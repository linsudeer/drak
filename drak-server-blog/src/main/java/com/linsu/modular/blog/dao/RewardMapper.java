package com.linsu.modular.blog.dao;

import com.linsu.modular.blog.model.Reward;

public interface RewardMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(Reward record);

    int insertSelective(Reward record);

    Reward selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(Reward record);

    int updateByPrimaryKey(Reward record);
}