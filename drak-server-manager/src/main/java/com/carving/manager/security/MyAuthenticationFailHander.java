package com.carving.manager.security;

import com.alibaba.fastjson.JSONObject;
import com.carving.manager.exception.util.ExceptionUtil;
import com.carving.manager.util.ResultTip;
import com.carving.manager.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li Songlin
 * <p>
 * 登录失败的操作 TODO
 * @date 2019/9/20 18:03
 **/
@Slf4j
@Component(value = "myAuthenticationFailHander")
public class MyAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.debug(ExceptionUtil.transform(exception));
        // 这里登录失败跳转的页面有controller控制
//        super.onAuthenticationFailure(request, response, exception);
        //以Json格式返回
        ResultTip result = new ResultTip();
        if (exception instanceof UsernameNotFoundException) {
            result.setCode(ResultCode.USERNAME_OR_PASSWORD_ERROR.getCode());
            result.setMessage(exception.getMessage());
        } else if (exception instanceof BadCredentialsException) {
            result.setCode(ResultCode.USERNAME_OR_PASSWORD_ERROR.getCode());
            result.setMessage(ExceptionUtil.transform(exception));
        } else if (exception instanceof DisabledException) {
            result.setCode(ResultCode.USER_DISABLE.getCode());
            result.setMessage("用户已被禁用");
        } else if (exception instanceof LockedException) {
            result.setCode(ResultCode.ACCOUNT_LOCKED.getCode());
            result.setMessage("账户被锁定");
        } else if (exception instanceof AccountExpiredException) {
            result.setCode(ResultCode.TOKEN_EXPIRED.getCode());
            result.setMessage("账户过期");
        } else if (exception instanceof CredentialsExpiredException) {
            result.setCode(ResultCode.CREDENTIALS_EXPIRED.getCode());
            result.setMessage("证书过期");
        } else {
            result.setMessage("登录失败");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(result));

    }
}
