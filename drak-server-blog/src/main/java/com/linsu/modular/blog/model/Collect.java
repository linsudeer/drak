package com.linsu.modular.blog.model;

import java.util.Date;

public class Collect {
    private Long oid;

    private Long collectUserId;

    private Long collectArticleId;

    private String collectArticleTitle;

    private Date collectCreateTime;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getCollectUserId() {
        return collectUserId;
    }

    public void setCollectUserId(Long collectUserId) {
        this.collectUserId = collectUserId;
    }

    public Long getCollectArticleId() {
        return collectArticleId;
    }

    public void setCollectArticleId(Long collectArticleId) {
        this.collectArticleId = collectArticleId;
    }

    public String getCollectArticleTitle() {
        return collectArticleTitle;
    }

    public void setCollectArticleTitle(String collectArticleTitle) {
        this.collectArticleTitle = collectArticleTitle == null ? null : collectArticleTitle.trim();
    }

    public Date getCollectCreateTime() {
        return collectCreateTime;
    }

    public void setCollectCreateTime(Date collectCreateTime) {
        this.collectCreateTime = collectCreateTime;
    }
}