package com.carving.manager.exception.token;

import com.carving.manager.exception.token.base.TokenException;

/**
 * token 验证异常
 */
public class TokenValidateException extends TokenException {

    public TokenValidateException(String msg) {
        super(msg);
    }
}
