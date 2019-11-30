package com.linsu.modular.blog.controller;
/**
 * 用户
 * @author lisonglin
 * @date 2018年3月17日 下午2:35:55
 */

import com.linsu.core.exception.BizException;
import com.linsu.core.exception.ExceptionEnum;
import com.linsu.core.log.LogManager;
import com.linsu.core.log.bean.LogSucceed;
import com.linsu.core.log.factory.LogTaskFactory;
import com.linsu.core.support.HttpServletRequestHolder;
import com.linsu.core.tip.ResultTip;
import com.linsu.core.tip.bean.Tip;
import com.linsu.core.util.BeanUtil;
import com.linsu.modular.blog.bean.SessionInfo;
import com.linsu.modular.blog.bean.UserSignBean;
import com.linsu.modular.blog.model.User;
import com.linsu.modular.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 注册
	 * @param signBean
	 * @return
	 */
	@PostMapping("/user/register")
	public Tip register(@Validated UserSignBean signBean, BindingResult signResult) {
		if(signResult.hasErrors()) {
			return ResultTip.error(signResult);
		}
		if(userService.isExistEmail(signBean.getUserEmail())) {
			throw new BizException(ExceptionEnum.EMAIL_EXIST);
		}
		if(userService.isExistNickName(signBean.getUserNickname())) {
			throw new BizException(ExceptionEnum.NICKNAME_EXIST);
		}
		if(!signBean.getUserPassword().equals(signBean.getUserPassword2())) {
			throw new BizException(ExceptionEnum.TWO_PWD_NOT_MATCH);
		}
		userService.register(BeanUtil.convert(signBean, User.class));
		return ResultTip.success();
	}
	
	/**
	 * 登陆
	 * @param userName
	 * @param userEmail
	 * @param password
	 * @return
	 */
	@PostMapping("/user/login")
	public Tip login(String userName, String userEmail, String password) {
		String loginName = "";
		if(StringUtils.isBlank(userName) && StringUtils.isBlank(userEmail)) {
			throw new BizException(ExceptionEnum.LOGIN_NAME_NOT_NULL);
		}
		if(StringUtils.isBlank(password)) {
			throw new BizException(ExceptionEnum.LOGIN_PASSWORD_NOT_NULL);
		}
		userService.login(userName, userEmail, password);


		SessionInfo sessionInfo = HttpServletRequestHolder.getSessionInfo();
		LogManager.getInstance().executeLog(LogTaskFactory.loginLog(userName==null?userEmail:userName, "", HttpServletRequestHolder.getIp(), LogSucceed.SUCCESS));
		return ResultTip.success(sessionInfo);
	}
	
	/**
	 * 检查用户是否登录
	 * @return
	 */
	@PostMapping("/user/checkLogin")
	public Tip checkLogin() {
		SessionInfo sessionInfo = HttpServletRequestHolder.getSessionInfo();
		if(sessionInfo == null) {
			return ResultTip.error(ExceptionEnum.NO_LOGIN.getCode(),ExceptionEnum.NO_LOGIN.getMessage());
		}else {
			return ResultTip.success(sessionInfo);
		}
	}
	
	/**
	 * 退出
	 * @return
	 */
	@PostMapping("/user/logout")
	public Tip logout() {
		User user = HttpServletRequestHolder.getUser();
		if(user != null) {
			userService.setOnLine(user.getOid(), false);
		}
		HttpServletRequestHolder.sessionInvalidate();
		return ResultTip.success();
	}
}
