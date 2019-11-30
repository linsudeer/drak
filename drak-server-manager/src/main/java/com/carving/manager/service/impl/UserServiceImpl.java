package com.carving.manager.service.impl;

import com.carving.manager.dmo.User;
import com.carving.manager.exception.UniqueException;
import com.carving.manager.repository.UserRepository;
import com.carving.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Li Songlin
 * @date 2019/9/24 13:20
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer userId) {
        return null;
    }

    @Override
    public User findByAccount(String account) {
        return userRepository.findByUsername(account);
    }

    @Override
    public User save(User user) {
        User dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser != null) {
            throw new UniqueException("账号【%s】已存在！", user.getUsername());
        }
        return userRepository.save(user);
    }
}
