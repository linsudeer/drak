package com.carving.manager;

import com.carving.manager.dmo.User;
import com.carving.manager.repository.UserRepository;
import com.carving.manager.security.MyUserDetailsService;
import com.carving.manager.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerApplicationTests {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    public void contextLoads() {
        User user = userService.findByAccount("admin");
        System.out.println(user.getRoles().size());
}

    @Test
    public void generalPassword() {
        String p = passwordEncoder.encode("8rvyZrx4");
        System.out.println(p);
        System.out.println(passwordEncoder.matches("8rvyZrx4", "$2a$10$CKHECzqMRjPpckmAyyk3Z.kMpdvLwRR50nY1b5H1nmcDRWAcU3bOq"));
    }


}
