package com.linsu.core.tip;

import com.linsu.core.exception.ExceptionEnum;
import com.linsu.core.tip.bean.Tip;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 响应消息
 * @author lisonglin
 * @date 2018年4月13日 下午1:31:10
 */
public class ResultTip extends Tip{
	
	private static final int OK = 200;
	
	private static final String MESSAGE = "操作成功";
	
	public static Tip success() {
		return success(OK, MESSAGE, null);
	}
	
	public static Tip success(Object data) {
		return success(OK, MESSAGE, data);
	}

	public static Tip success(String message, Object data){
		return success(OK, message, data);
	}
	
	public static Tip success(int code, String message) {
		return success(code, message, null);
	}
	
	public static Tip success(int code, String message, Object data) {
		return new Tip(code, message, data);
	}

	public static Tip error(ExceptionEnum exception){
		return error(exception.getCode(), exception.getMessage());
	}
	
	public static Tip error(int code, String message) {
		return new Tip(code, message, null);
	}

	public static Tip error(BindingResult signResult) {
		StringBuilder msg = new StringBuilder();
		List<ObjectError> allErrors = signResult.getAllErrors();
		for (ObjectError objectError : allErrors) {
			msg.append(objectError.getDefaultMessage()).append(",");
		}
		return error(400, msg.subSequence(0, msg.length()-1).toString());
	}
}
