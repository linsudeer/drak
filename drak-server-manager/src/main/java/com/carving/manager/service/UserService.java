package com.carving.manager.service;

import com.carving.manager.dmo.User;

/**
 * @author Li Songlin
 *
 * 用户服务
 * @date 2019/9/20 16:01
 **/
public interface UserService {

    User findById(Integer userId);

    User findByAccount(String account);

    User save(User user);
}
