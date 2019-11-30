package com.linsu.modular.blog.model;

import java.util.Date;

public class Reward {
    private Long oid;

    private Float rewardAmount;

    private Long rewardUserId;

    private Long rewardAuthorId;

    private Long rewardArticleId;

    private String rewardUserName;

    private Date rewardCreateTime;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Float getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Float rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Long getRewardUserId() {
        return rewardUserId;
    }

    public void setRewardUserId(Long rewardUserId) {
        this.rewardUserId = rewardUserId;
    }

    public Long getRewardAuthorId() {
        return rewardAuthorId;
    }

    public void setRewardAuthorId(Long rewardAuthorId) {
        this.rewardAuthorId = rewardAuthorId;
    }

    public Long getRewardArticleId() {
        return rewardArticleId;
    }

    public void setRewardArticleId(Long rewardArticleId) {
        this.rewardArticleId = rewardArticleId;
    }

    public String getRewardUserName() {
        return rewardUserName;
    }

    public void setRewardUserName(String rewardUserName) {
        this.rewardUserName = rewardUserName == null ? null : rewardUserName.trim();
    }

    public Date getRewardCreateTime() {
        return rewardCreateTime;
    }

    public void setRewardCreateTime(Date rewardCreateTime) {
        this.rewardCreateTime = rewardCreateTime;
    }
}