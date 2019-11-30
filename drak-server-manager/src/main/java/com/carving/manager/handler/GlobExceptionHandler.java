package com.carving.manager.handler;

import com.carving.manager.exception.util.ExceptionUtil;
import com.carving.manager.exception.UniqueException;
import com.carving.manager.exception.DataNotExistException;
import com.carving.manager.exception.token.base.TokenException;
import com.carving.manager.util.ResultTip;
import com.carving.manager.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Li Songlin
 *
 * 统一异常处理器
 * @date 2019/9/24 11:12
 **/
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobExceptionHandler {

    /**
     * 验证异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultTip methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append("；"));

        log.error("参数校验异常："+ ExceptionUtil.transform(exception));

        return ResultTip.error(ResultCode.ARGS_ERROR, errorMsg.toString());
    }

    /**
     * 名称已存在异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = UniqueException.class)
    public ResultTip nameExistExceptionHandler(UniqueException exception) {
        return ResultTip.error(ResultCode.NAME_EXIST, ExceptionUtil.transform(exception));
    }


    /**
     * token 异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = TokenException.class)
    public ResultTip tokenParseErrorHandler(TokenException exception) {
        return ResultTip.error(ResultCode.TOKEN_EXPIRED, ExceptionUtil.transform(exception));
    }

    /**
     * 数据不存在
     * @param exception
     * @return
     */
    @ExceptionHandler(value = DataNotExistException.class)
    public ResultTip notExistExceptionHandler(DataNotExistException exception) {
        return ResultTip.error(ResultCode.ARGS_ILLEAGL, ExceptionUtil.transform(exception));
    }

    /**
     * 其他运行时异常（开发注意避免发生）
     * @param exception
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResultTip runTimeExceptionHandler(RuntimeException exception) {
        return ResultTip.error(ResultCode.ERROR, ExceptionUtil.transform(exception));

    }


}
