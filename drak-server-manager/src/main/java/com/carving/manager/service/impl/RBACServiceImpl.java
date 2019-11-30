package com.carving.manager.service.impl;

import com.carving.manager.constant.RedisKey;
import com.carving.manager.constant.SecretConstant;
import com.carving.manager.dmo.Resource;
import com.carving.manager.dmo.Role;
import com.carving.manager.dmo.User;
import com.carving.manager.exception.token.TokenExipredException;
import com.carving.manager.exception.token.TokenIllegalException;
import com.carving.manager.exception.token.TokenValidateException;
import com.carving.manager.exception.token.base.TokenException;
import com.carving.manager.service.RBACService;
import com.carving.manager.service.RedisService;
import com.carving.manager.service.ResourceService;
import com.carving.manager.util.JwtUtil;
import com.google.common.collect.Sets;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @author Li Songlin
 * <p>
 * 验证资源，是否有资源权限，这里的资源指的是请求地址（包括url页面路径和功能方法调用地址，主要是后者，因为前后端分离，页面地址在前端控制。）
 * @date 2019/9/20 18:28
 **/
@Service(value = "rbacService")
public class RBACServiceImpl implements RBACService {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RedisService redisService;

    AntPathMatcher mathcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        boolean hasPermission = false;

        Object principal = authentication.getPrincipal();

        // 这里只有一个UserDetails 所以知道是User类
        if (principal instanceof UserDetails) {
            User user = ((User) principal);
            String username = user.getUsername();

            // 每次请求都会经过token校验，校验token
            String jwtToken = request.getHeader("X-Token");
            Long refreshTime = Long.valueOf(request.getHeader("X-Token-Refresh-Time"));
            String ua = request.getHeader("User-Agent");
            String domain = request.getRemoteHost();

            try {
                if (!JwtUtil.validateToken(jwtToken, refreshTime, ua, domain)) {
                    throw new TokenValidateException("token验证失败");
                }
            } catch (ExpiredJwtException e) {
                throw new TokenExipredException("token过期");
            } catch (IllegalArgumentException e) {
                throw new TokenIllegalException("token格式错误");
            } catch (Exception e) {
                throw new TokenException("身份 token 不合法，请判断它的有效性");
            }

            // 判断是否admin，admin不验证资源权限
            List<Role> roles = user.getRoles();
            for(Role role : roles) {
                if("admin".equals(role.getKey())) {
                    hasPermission = true;
                    break;
                }
            }
            if(hasPermission) {
                return true;
            }

            //这里根据用户登录名或者角色去查找拥有的权限
            String key = RedisKey.RESOURCE_PREFIX + username;
            Set<Object> urls = redisService.sGet(key);
            // 如果没有资源权限，则从数据库查找,并加入缓存
            if (urls == null || urls.size() == 0) {
                List<Resource> resources = resourceService.findByAccount(username);
                urls = Sets.newHashSet();
                for (Resource resource : resources) {
                    urls.add(resource.getUrl());
                    redisService.sSet(key, resource.getUrl());
                }
                redisService.expire(key, SecretConstant.EXPIRE);
            }

            for (Object url : urls) {
                if (mathcher.match(url.toString(), request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
