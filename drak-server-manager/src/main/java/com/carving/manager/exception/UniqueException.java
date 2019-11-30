package com.carving.manager.exception;

/**
 * 唯一性存在异常，针对业务中必须保持唯一的数据异常检测
 * @author Li Songlin
 * @date 2019/10/10 15:32
 **/
public class UniqueException extends RuntimeException {

    public UniqueException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param args
     */
    public UniqueException(String message, Object... args) {
        super(String.format(message, args));
    }
}
