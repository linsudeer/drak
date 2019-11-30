package com.carving.manager.exception.token.base;

import org.springframework.security.access.AccessDeniedException;

/**
 * @author Li Songlin
 * @date 2019/11/14 10:54
 **/
public class TokenException extends AccessDeniedException {

    public TokenException(String message) {
        super(message);
    }
}
