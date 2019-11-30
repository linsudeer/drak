package com.linsu.modular.blog.model;

import java.util.Date;

public class View {
    private Long oid;

    private Long readUserId;

    private Long readArticleId;

    private String readArticleTitle;

    private Integer readStayTime;

    private Date readCreateTime;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getReadUserId() {
        return readUserId;
    }

    public void setReadUserId(Long readUserId) {
        this.readUserId = readUserId;
    }

    public Long getReadArticleId() {
        return readArticleId;
    }

    public void setReadArticleId(Long readArticleId) {
        this.readArticleId = readArticleId;
    }

    public String getReadArticleTitle() {
        return readArticleTitle;
    }

    public void setReadArticleTitle(String readArticleTitle) {
        this.readArticleTitle = readArticleTitle == null ? null : readArticleTitle.trim();
    }

    public Integer getReadStayTime() {
        return readStayTime;
    }

    public void setReadStayTime(Integer readStayTime) {
        this.readStayTime = readStayTime;
    }

    public Date getReadCreateTime() {
        return readCreateTime;
    }

    public void setReadCreateTime(Date readCreateTime) {
        this.readCreateTime = readCreateTime;
    }
}