package com.linsu.modular.blog.model;

import java.util.Date;

public class Tag {
    private Long oid;

    private Integer tagCommentCount;

    private Integer tagFollowerCount;

    private Integer tagLinkCount;

    private String tagTitle;

    private String tagUri;

    private Long tagCategoryId;

    private Date tagCreateTime;
    
    public Tag() {}
    
    public Tag(String tagTitle) {
    	this.tagTitle = tagTitle;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Integer getTagCommentCount() {
        return tagCommentCount;
    }

    public void setTagCommentCount(Integer tagCommentCount) {
        this.tagCommentCount = tagCommentCount;
    }

    public Integer getTagFollowerCount() {
        return tagFollowerCount;
    }

    public void setTagFollowerCount(Integer tagFollowerCount) {
        this.tagFollowerCount = tagFollowerCount;
    }

    public Integer getTagLinkCount() {
        return tagLinkCount;
    }

    public void setTagLinkCount(Integer tagLinkCount) {
        this.tagLinkCount = tagLinkCount;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle == null ? null : tagTitle.trim();
    }

    public String getTagUri() {
        return tagUri;
    }

    public void setTagUri(String tagUri) {
        this.tagUri = tagUri == null ? null : tagUri.trim();
    }

    public Long getTagCategoryId() {
        return tagCategoryId;
    }

    public void setTagCategoryId(Long tagCategoryId) {
        this.tagCategoryId = tagCategoryId;
    }

    public Date getTagCreateTime() {
        return tagCreateTime;
    }

    public void setTagCreateTime(Date tagCreateTime) {
        this.tagCreateTime = tagCreateTime;
    }
}