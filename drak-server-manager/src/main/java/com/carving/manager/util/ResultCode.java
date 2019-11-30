package com.carving.manager.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Li Songlin
 * @date 2019/9/20 17:17
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(20000, "成功"),

    USERNAME_OR_PASSWORD_ERROR(60204, "用户名密码错误"),

    USER_DISABLE(20002, "用户被禁用"),

    ACCOUNT_LOCKED(20003, "账户被锁定"),

    CREDENTIALS_EXPIRED(20005, "证书过期"),

    NAME_EXIST(2006, "名称已存在"),

    TOKEN_ILLEGAL(50008, "Token不合法"),

    TOKEN_VALIDATE_FAIL(5009, "Token验证失败"),

    OTHER_LOGIN(50012, "其他账户登录"),

    TOKEN_EXPIRED(50014, "Token过期"),

    NO_LOGIN(2009, "没有登录"),

    ARGS_ILLEAGL(4002, "参数非法"),


    ARGS_ERROR(40001, "表单验证失败"),

    ACCESS_DENIED(4003, "没有访问权限"),


    /**
     * 系统内部错误
     */
    ERROR(50000, "系统出错");

    @Getter
    private Integer code;

    @Getter
    private String message;


}
