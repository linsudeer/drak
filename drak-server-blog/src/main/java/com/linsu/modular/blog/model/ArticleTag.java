package com.linsu.modular.blog.model;

import java.util.Date;

public class ArticleTag {
    private Long oid;

    private Long tagId;

    private Long articleId;

    private Date createTime;
    
    public ArticleTag() {}

    public ArticleTag(Long tagId, Long articleId) {
		super();
		this.tagId = tagId;
		this.articleId = articleId;
	}

	public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}