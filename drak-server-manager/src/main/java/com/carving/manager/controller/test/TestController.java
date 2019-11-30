package com.carving.manager.controller.test;

import com.carving.manager.service.UserService;
import com.carving.manager.util.ResultTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Li Songlin
 * @date 2019/9/20 14:26
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String test() {
        return "Hello demo!";
    }

    @GetMapping("/user")
    public Object getUser(Principal user) {
        return user;
    }

    @GetMapping("/whoIm")
    public Object whoIm() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/{account}")
    public ResultTip getUserByAccount(@PathVariable("account") String account) {
        return ResultTip.success(userService.findByAccount(account));
    }




}
