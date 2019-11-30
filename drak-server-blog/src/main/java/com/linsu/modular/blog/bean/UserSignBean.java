package com.linsu.modular.blog.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * 注册bean
 * @author lisonglin
 * @date 2018年3月19日 下午1:36:54
 */
public class UserSignBean {
	
	@NotBlank(message="用户名不能为空")
    @Length(max=50, message="用户名最大长度为50")
    private String userNickname;
	
	@NotBlank(message="密码不能为空")
    @Length(max=20, message="密码最大长度为20")
    private String userPassword;
	
    @Length(max=20, message="密码最大长度为20")
	private String userPassword2;
	
	@NotBlank(message="邮箱不能为空")
    @Email(message="邮箱格式不正确")
    private String userEmail;
	
	/**
	 * 邀请码
	 */
	@Length(max=8, message="邀请码最大长度为8")
	private String inviteCode;

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getUserPassword2() {
		return userPassword2;
	}

	public void setUserPassword2(String userPassword2) {
		this.userPassword2 = userPassword2;
	}
	
	public interface Login{}

}
