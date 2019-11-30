package com.linsu.modular.blog.service;

import com.linsu.modular.blog.model.User;

/**
 * 用户类接口
 * @author lisonglin
 * @date 2018年3月17日 下午2:26:43
 */
public interface UserService {
	
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	long register(User user);
	
	/**
	 * 修改
	 * @param user
	 * @return true 成功
	 */
	boolean update(User user);
	
	/**
	 * 判断邮箱是否已经存在
	 * @param email
	 * @return true 存在， false 不存在
	 */
	boolean isExistEmail(String email);
	
	/**
	 * 判断昵称是否存在
	 * @param nickName
	 * @return true 存在， false 不存在
	 */
	boolean isExistNickName(String nickName);

	/**
	 * 登陆操作
	 * @param userName
	 * @param userEmail
	 * @param password
	 */
	void login(String userName, String userEmail, String password);
	
	/**
	 * 设置在线状态
	 * @param userId 用户Id
	 * @param status true:在线，false：离线
	 */
	void setOnLine(Long userId, Boolean status);

	/**
	 * 根据登陆名获得用户信息
	 * @param account
	 * @return
	 */
	User getByLoginName(String account);
}
