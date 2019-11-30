package com.linsu.core.aop;

import com.linsu.core.exception.BizException;
import com.linsu.core.exception.ExceptionEnum;
import com.linsu.core.log.LogManager;
import com.linsu.core.log.factory.LogTaskFactory;
import com.linsu.core.support.HttpServletRequestHolder;
import com.linsu.core.tip.ResultTip;
import com.linsu.core.tip.bean.Tip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 全局的异常拦截器（拦截所有的控制器，带有@RequestMappin 注解的方法都会拦截）
 * @author lisonglin
 * @date 2018年3月17日 上午10:14:50
 */
@ControllerAdvice
@Order(1)
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 拦截业务异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BizException.class)
	@ResponseBody
	public Tip bizException(BizException e) {
		Long userId = HttpServletRequestHolder.getUserId();
		String nickName = HttpServletRequestHolder.getNickName();
		LogManager.getInstance().executeLog(LogTaskFactory.exceptionLog(userId, nickName, e));
		logger.error("业务异常", e);
		return ResultTip.error(e.getCode(), e.getMessage());
	}

	/**
	 * 无权访问该资源异常
	 */
	@ExceptionHandler(UndeclaredThrowableException.class)
	@ResponseBody
	public Tip credentials(UndeclaredThrowableException e, Model model) {
		model.addAttribute("tip", "权限异常");
		logger.error("权限异常!", e);
		return ResultTip.error(ExceptionEnum.NO_PERMITION.getCode(), ExceptionEnum.NO_PERMITION.getMessage());
	}

	/**
	 * 拦截未知的运行时异常
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Tip notFount(RuntimeException e, Model model) {
		Long userId = HttpServletRequestHolder.getUserId();
		String nickName = HttpServletRequestHolder.getNickName();
		LogManager.getInstance().executeLog(LogTaskFactory.exceptionLog(userId, nickName, e));
		model.addAttribute("tip", "服务器未知运行时异常");
		logger.error("运行时异常:", e);
		return ResultTip.error(ExceptionEnum.SERVER_ERROR.getCode(), ExceptionEnum.SERVER_ERROR.getMessage());
	}

}
