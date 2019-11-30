package com.carving.manager.exception.token;

import com.carving.manager.exception.token.base.TokenException;

/**
 * token格式错误
 * @author Li Songlin
 * @date 2019/11/14 15:06
 **/
public class TokenIllegalException extends TokenException {

    public TokenIllegalException(String message) {
        super(message);
    }

}
