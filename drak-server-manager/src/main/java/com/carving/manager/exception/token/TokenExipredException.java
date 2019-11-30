package com.carving.manager.exception.token;

import com.carving.manager.exception.token.base.TokenException;

/**
 * token过期
 */
public class TokenExipredException extends TokenException {

    public TokenExipredException(String msg) {
        super(msg);
    }
}
