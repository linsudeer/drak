package com.carving.manager.exception;

/**
 * 数据不存在异常，应该存在的数据但是不存在
 * @author Li Songlin
 * @date 2019/11/15 14:30
 **/
public class DataNotExistException extends RuntimeException {

    public DataNotExistException(String message) {
        super(message);
    }

    public DataNotExistException(String message, Object ...args) {
        super(String.format(message, args));
    }
}
