package com.carving.manager.security;

import com.carving.manager.dmo.User;
import com.carving.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Li Songlin
 * @date 2019/9/20 16:01
 **/
@Component(value = "myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查找user
        User user = userService.findByAccount(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("用户名【%s】不存在", username));
        }
        return user;
    }
}
