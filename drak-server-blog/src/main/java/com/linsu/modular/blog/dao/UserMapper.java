package com.linsu.modular.blog.dao;

import org.apache.ibatis.annotations.Param;

import com.linsu.modular.blog.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 登陆
     * @param userName
     * @param userEmail
     * @param encryptPwd
     * @return
     */
	User getByLogin(@Param("userName") String userName, @Param("userEmail") String userEmail, @Param("password") String encryptPwd);

	/**
	 * 判断email 是否存在
	 * @param email
	 * @return
	 */
	Integer isExistEmail(@Param("email") String email);

	/**
	 * 判断昵称是否存在
	 * @param nickName
	 * @return
	 */
	Integer isExistNickName(@Param("nickName") String nickName);


	/**
	 * 更新用户在线状态
	 * @param userId
	 * @param status "ON":在线 "OFF" 离线
	 * @return
	 */
	void setOnLine(@Param("userId") Long userId, @Param("status") String status);

	/**
	 * 根据登陆名获取用户信息
	 * @param loginName
	 * @return
	 */
    User getByLoginName(@Param("loginName") String loginName);
}