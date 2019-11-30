package com.linsu.modular.blog.model;

import java.util.Date;

public class Comment {
    private Long oid;

    private String commentAuthorId;

    private String commentAuthorEmail;

    private Long commentOntArticleId;

    private Integer commentStatus;

    private String commentIp;

    private Integer commentGoodCount;

    private Integer commentBadCount;

    private Integer commentReplyCount;

    private String commentContent;

    private Date commentCreateTime;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getCommentAuthorId() {
        return commentAuthorId;
    }

    public void setCommentAuthorId(String commentAuthorId) {
        this.commentAuthorId = commentAuthorId == null ? null : commentAuthorId.trim();
    }

    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }

    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail == null ? null : commentAuthorEmail.trim();
    }

    public Long getCommentOntArticleId() {
        return commentOntArticleId;
    }

    public void setCommentOntArticleId(Long commentOntArticleId) {
        this.commentOntArticleId = commentOntArticleId;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp == null ? null : commentIp.trim();
    }

    public Integer getCommentGoodCount() {
        return commentGoodCount;
    }

    public void setCommentGoodCount(Integer commentGoodCount) {
        this.commentGoodCount = commentGoodCount;
    }

    public Integer getCommentBadCount() {
        return commentBadCount;
    }

    public void setCommentBadCount(Integer commentBadCount) {
        this.commentBadCount = commentBadCount;
    }

    public Integer getCommentReplyCount() {
        return commentReplyCount;
    }

    public void setCommentReplyCount(Integer commentReplyCount) {
        this.commentReplyCount = commentReplyCount;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }
}