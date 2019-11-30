package com.carving.manager.security;

import com.alibaba.fastjson.JSONObject;
import com.carving.manager.dmo.User;
import com.carving.manager.util.JwtUtil;
import com.carving.manager.util.ResultTip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Li Songlin
 *
 * 登录成功处理，是跳转页面还是返回json   TODO
 *
 * @date 2019/9/20 17:58
 **/
@Slf4j
@Component(value = "myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //什么都不做的话，那就直接调用父类的方法
        //super.onAuthenticationSuccess(request, response, authentication);

        // 设置token
        Object principal = authentication.getPrincipal();
        // 这里只有一个UserDetails 所以知道是User类
        User user = ((User) principal);
        String username = user.getUsername();
        String ua = request.getHeader("User-Agent");
        String domain = request.getRemoteHost();
        log.info(String.format("登录信息：【登录用户名： %s, 浏览器： %s, 域名： %s】", username, ua, domain));
        String token = JwtUtil.generalToken(username, System.currentTimeMillis(), ua, domain);

        Map<String, String> resData = new HashMap<>();
        resData.put("token", token);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ResultTip.build().message("登录成功").data(resData)));


        //如果是要跳转到某个页面的，比如我们的那个whoim的则
//        new DefaultRedirectStrategy().sendRedirect(request, response, "/whoim");
    }

}
