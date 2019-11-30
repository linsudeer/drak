package com.carving.manager.exception.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Li Songlin
 * @date 2019/9/18 16:56
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {


    /**
     * 将Throwable异常转换为字符串
     *
     * @param cause 需要转换的异常
     * @return 转换后的异常
     */
    public static String transform(final Throwable cause) {
        if(cause == null) {
            return "";
        }

        StringWriter result = new StringWriter();
        try(PrintWriter writer = new PrintWriter(result)) {
            cause.printStackTrace(writer);
        }

        return result.toString();
    }
}
