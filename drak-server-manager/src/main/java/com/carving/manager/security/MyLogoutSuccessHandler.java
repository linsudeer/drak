package com.carving.manager.security;

import com.alibaba.fastjson.JSONObject;
import com.carving.manager.constant.RedisKey;
import com.carving.manager.constant.RequestHeaderKey;
import com.carving.manager.service.RedisService;
import com.carving.manager.util.JwtUtil;
import com.carving.manager.util.ResultTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li Songlin
 * @date 2019/11/14 17:31
 **/
@Component(value = "myLogoutSuccessHandler")
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisService redisService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader(RequestHeaderKey.JWT_TOKEN);
        String username = JwtUtil.getUserNameFromToken(token);
        redisService.del(RedisKey.RESOURCE_PREFIX + username);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ResultTip.success()));
    }
}
