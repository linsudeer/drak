package com.carving.manager.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 校验token是否合法，目前校验放在spring security RBACService中对请求资源校验
 *
 * @author Li Songlin
 * @date 2019/11/14 11:18
 **/
@WebFilter
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
