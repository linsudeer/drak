package com.linsu.modular.blog.search.article;

/**
 * 文章搜索尸实体
 */
public class ArticleSearchBean {

    /**
     * 文章ID
     */
    private Long oid;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 这里要换成摘要
     */
    private String articleContentHtml;


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
        this.articleTitle = articleTitle;
    }

    public String getArticleContentHtml() {
        return articleContentHtml;
    }

    public void setArticleContentHtml(String articleContentHtml) {
        this.articleContentHtml = articleContentHtml;
    }
}
