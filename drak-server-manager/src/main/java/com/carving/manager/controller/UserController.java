package com.carving.manager.controller;

import com.carving.manager.dmo.User;
import com.carving.manager.service.UserService;
import com.carving.manager.util.JwtUtil;
import com.carving.manager.util.ResultCode;
import com.carving.manager.util.ResultTip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 *
 * @author Li Songlin
 * @date 2019/9/24 14:26
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/loginPage")
    public ResultTip loginPage() {
        return new ResultTip(ResultCode.NO_LOGIN);
    }

    @GetMapping("/loginRe")
    public ResultTip loginRe() {
        return new ResultTip(ResultCode.NO_LOGIN);
    }

    /**
     * 根据token 获取用户信息，token在请求时已经被校验
     * @param token
     * @return
     */
    @GetMapping("/info")
    public ResultTip info(String token) {
        String username = JwtUtil.getUserNameFromToken(token);

        User user = userService.findByAccount(username);

        return ResultTip.success(user);
    }

}
