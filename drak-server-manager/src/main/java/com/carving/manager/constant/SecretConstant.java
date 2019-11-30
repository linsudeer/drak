package com.carving.manager.constant;

/**
 * @author Li Songlin
 * @date 2019/11/14 10:07
 **/
public interface SecretConstant {

    /**
     * jwt 加密密钥
     */
    String DATAKEY = "37aacc689d5340a791e6d5a4f114f69e";

    /**
     * 过期时间 30分钟
     */
    long EXPIRE = 30*60*1000;
}
