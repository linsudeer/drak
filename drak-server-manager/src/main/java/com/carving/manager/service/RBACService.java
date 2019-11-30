package com.carving.manager.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Li Songlin
 *
 * role-Based-access control
 *
 * @date 2019/9/20 18:26
 **/
public interface RBACService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);

    // 返回资源权限
}
