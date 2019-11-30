package com.linsu.modular.blog.model;

import java.util.Date;

public class Article {
    private Long oid;

    private String articleTitle;

    private String articleTags;

    private String articleAuthorEmail;

    private Long articleAuthorId;

    private Long articleCategoryId;

    private String articleAuthorNickname;

    private Integer articleCommentCount;

    private Integer articleViewCount;

    private Integer articleRewardPoint;

    private String articlePermalink;

    private String articleOriginlink;

    private Date articleLatestCmtTime;

    private String articleLatestCmterName;

    private Integer articleCommentable;

    private Integer articleStatus;

    private Integer articleType;

    private Integer articleGoodCount;

    private Integer articleBadCount;

    private Integer articleCollectCount;

    private String articleCity;

    private String articleIp;

    private Integer articlePerfect;

    private Date articleCreateTime;

    private Date articleUpdateTime;

    private String articleContent;

    private String articleContentHtml;

    private String articleSource;

    /**
     * 文章缩略图，默认取第一张图片
     */
    private String articleThumb;
    
    public Article() {}
    
    public Article(Long oid) {
    	this.oid = oid;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags == null ? null : articleTags.trim();
    }

    public String getArticleAuthorEmail() {
        return articleAuthorEmail;
    }

    public void setArticleAuthorEmail(String articleAuthorEmail) {
        this.articleAuthorEmail = articleAuthorEmail == null ? null : articleAuthorEmail.trim();
    }

    public Long getArticleAuthorId() {
        return articleAuthorId;
    }

    public void setArticleAuthorId(Long articleAuthorId) {
        this.articleAuthorId = articleAuthorId;
    }

    public Long getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Long articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public Integer getArticleRewardPoint() {
        return articleRewardPoint;
    }

    public void setArticleRewardPoint(Integer articleRewardPoint) {
        this.articleRewardPoint = articleRewardPoint;
    }

    public String getArticlePermalink() {
        return articlePermalink;
    }

    public void setArticlePermalink(String articlePermalink) {
        this.articlePermalink = articlePermalink == null ? null : articlePermalink.trim();
    }

    public String getArticleOriginlink() {
        return articleOriginlink;
    }

    public void setArticleOriginlink(String articleOriginlink) {
        this.articleOriginlink = articleOriginlink == null ? null : articleOriginlink.trim();
    }

    public Date getArticleLatestCmtTime() {
        return articleLatestCmtTime;
    }

    public void setArticleLatestCmtTime(Date articleLatestCmtTime) {
        this.articleLatestCmtTime = articleLatestCmtTime;
    }

    public String getArticleLatestCmterName() {
        return articleLatestCmterName;
    }

    public void setArticleLatestCmterName(String articleLatestCmterName) {
        this.articleLatestCmterName = articleLatestCmterName == null ? null : articleLatestCmterName.trim();
    }

    public Integer getArticleCommentable() {
        return articleCommentable;
    }

    public void setArticleCommentable(Integer articleCommentable) {
        this.articleCommentable = articleCommentable;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public Integer getArticleGoodCount() {
        return articleGoodCount;
    }

    public void setArticleGoodCount(Integer articleGoodCount) {
        this.articleGoodCount = articleGoodCount;
    }

    public Integer getArticleBadCount() {
        return articleBadCount;
    }

    public void setArticleBadCount(Integer articleBadCount) {
        this.articleBadCount = articleBadCount;
    }

    public Integer getArticleCollectCount() {
        return articleCollectCount;
    }

    public void setArticleCollectCount(Integer articleCollectCount) {
        this.articleCollectCount = articleCollectCount;
    }

    public String getArticleCity() {
        return articleCity;
    }

    public void setArticleCity(String articleCity) {
        this.articleCity = articleCity == null ? null : articleCity.trim();
    }

    public String getArticleIp() {
        return articleIp;
    }

    public void setArticleIp(String articleIp) {
        this.articleIp = articleIp == null ? null : articleIp.trim();
    }

    public Integer getArticlePerfect() {
        return articlePerfect;
    }

    public void setArticlePerfect(Integer articlePerfect) {
        this.articlePerfect = articlePerfect;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleAuthorNickname() {
        return articleAuthorNickname;
    }

    public void setArticleAuthorNickname(String articleAuthorNickname) {
        this.articleAuthorNickname = articleAuthorNickname;
    }

    public String getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource;
    }

    public String getArticleContentHtml() {
        return articleContentHtml;
    }

    public void setArticleContentHtml(String articleContentHtml) {
        this.articleContentHtml = articleContentHtml;
    }

    public String getArticleThumb() {
        return articleThumb;
    }

    public void setArticleThumb(String articleThumb) {
        this.articleThumb = articleThumb;
    }
}