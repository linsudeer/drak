package com.carving.manager.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Li Songlin
 * @date 2019/9/20 17:11
 **/
@Getter
@Setter
public class ResultTip {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据对象
     */
    private Object data;

    public ResultTip() {}

    public ResultTip(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public ResultTip(ResultCode code, String message) {
        this.code = code.getCode();
        this.message = message;
    }

    public ResultTip(ResultCode code, Object obj) {
        this.code = code.getCode();
        this.data = obj;
        this.message = code.getMessage();
    }


    public static ResultTip success() {
        return new ResultTip(ResultCode.SUCCESS);
    }

    public static ResultTip success(Object obj) {
        return new ResultTip(ResultCode.SUCCESS, obj);
    }

    public static ResultTip success(String message) {
        return new ResultTip(ResultCode.SUCCESS, message);
    }

    public static ResultTip error() {
        return new ResultTip(ResultCode.ERROR);
    }

    public static ResultTip error(ResultCode code, String message) {
        return new ResultTip(code, message);
    }


    //  builder

    public static ResultTip build() {
        return new ResultTip();
    }

    public static ResultTip code(ResultCode code) {
        return new ResultTip(code);
    }

    public static ResultTip data(Object obj) {
        return success(obj);
    }

    public static ResultTip message(String message) {
        return success(message);
    }


}
