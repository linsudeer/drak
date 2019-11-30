package com.linsu.modular.blog.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long oid;
    
    private Long userInviterId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private String userNickname;

    private String userTags;

    private String userQq;

    private String userIntro;

    private String userOnlineFlag;

    private Integer userArticleCount;

    private Integer userCommentCount;

    private Integer userTagCount;

    private Integer userStatus;

    private Integer userPoint;

    private Integer userUsedPoint;

    private Long userLatestArticleTime;

    private Long userLatestCmtTime;

    private Long userLatestLoginTime;

    private String userLatestLatestLoginIp;

    private String userCountry;

    private String userProvince;

    private String userCity;

    private String userSkin;

    private Date userUpdateTime;

    private Date userCreateTime;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserTags() {
        return userTags;
    }

    public void setUserTags(String userTags) {
        this.userTags = userTags == null ? null : userTags.trim();
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq == null ? null : userQq.trim();
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro == null ? null : userIntro.trim();
    }

    public String getUserOnlineFlag() {
        return userOnlineFlag;
    }

    public void setUserOnlineFlag(String userOnlineFlag) {
        this.userOnlineFlag = userOnlineFlag == null ? null : userOnlineFlag.trim();
    }

    public Integer getUserArticleCount() {
        return userArticleCount;
    }

    public void setUserArticleCount(Integer userArticleCount) {
        this.userArticleCount = userArticleCount;
    }

    public Integer getUserCommentCount() {
        return userCommentCount;
    }

    public void setUserCommentCount(Integer userCommentCount) {
        this.userCommentCount = userCommentCount;
    }

    public Integer getUserTagCount() {
        return userTagCount;
    }

    public void setUserTagCount(Integer userTagCount) {
        this.userTagCount = userTagCount;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public Integer getUserUsedPoint() {
        return userUsedPoint;
    }

    public void setUserUsedPoint(Integer userUsedPoint) {
        this.userUsedPoint = userUsedPoint;
    }

    public Long getUserLatestArticleTime() {
        return userLatestArticleTime;
    }

    public void setUserLatestArticleTime(Long userLatestArticleTime) {
        this.userLatestArticleTime = userLatestArticleTime;
    }

    public Long getUserLatestCmtTime() {
        return userLatestCmtTime;
    }

    public void setUserLatestCmtTime(Long userLatestCmtTime) {
        this.userLatestCmtTime = userLatestCmtTime;
    }

    public Long getUserLatestLoginTime() {
        return userLatestLoginTime;
    }

    public void setUserLatestLoginTime(Long userLatestLoginTime) {
        this.userLatestLoginTime = userLatestLoginTime;
    }

    public String getUserLatestLatestLoginIp() {
        return userLatestLatestLoginIp;
    }

    public void setUserLatestLatestLoginIp(String userLatestLatestLoginIp) {
        this.userLatestLatestLoginIp = userLatestLatestLoginIp == null ? null : userLatestLatestLoginIp.trim();
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry == null ? null : userCountry.trim();
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince == null ? null : userProvince.trim();
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity == null ? null : userCity.trim();
    }

    public String getUserSkin() {
        return userSkin;
    }

    public void setUserSkin(String userSkin) {
        this.userSkin = userSkin == null ? null : userSkin.trim();
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

	public Long getUserInviterId() {
		return userInviterId;
	}

	public void setUserInviterId(Long userInviterId) {
		this.userInviterId = userInviterId;
	}
}