package com.carving.manager.security;

import com.alibaba.fastjson.JSONObject;
import com.carving.manager.exception.token.TokenExipredException;
import com.carving.manager.exception.token.TokenIllegalException;
import com.carving.manager.exception.token.TokenValidateException;
import com.carving.manager.exception.token.base.TokenException;
import com.carving.manager.util.ResultCode;
import com.carving.manager.util.ResultTip;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li Songlin
 * @date 2019/10/23 14:52
 **/
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {

        String message = new String();
        if (exception instanceof TokenExipredException) {
            message = JSONObject.toJSONString(new ResultTip(ResultCode.TOKEN_EXPIRED, exception.getMessage()));
        } else if (exception instanceof TokenIllegalException) {
            message = JSONObject.toJSONString(new ResultTip(ResultCode.TOKEN_ILLEGAL, exception.getMessage()));
        } else if (exception instanceof TokenValidateException) {
            message = JSONObject.toJSONString(new ResultTip(ResultCode.TOKEN_VALIDATE_FAIL, exception.getMessage()));
        } else if (exception instanceof TokenException) {
            message = JSONObject.toJSONString(new ResultTip(ResultCode.TOKEN_ILLEGAL, exception.getMessage()));
        } else {
            message = JSONObject.toJSONString(new ResultTip(ResultCode.ACCESS_DENIED));
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(message);
    }

}
