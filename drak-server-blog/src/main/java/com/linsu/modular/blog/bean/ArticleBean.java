package com.linsu.modular.blog.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 文章录入模型
 * @author lisonglin
 * @date 2018年3月27日 下午2:00:00
 */
public class ArticleBean {
	
	/**
	 * 文章ID
	 */
	@NotNull(message="文章ID不能为空", groups=Update.class)
	private Long oid;

	/**
	 * 文章标题
	 */
	@NotBlank(message="标题不能为空", groups=All.class)
    @Length(max=100, message="用户名最大长度为100", groups=All.class)
	private String articleTitle;

	/**
	 * 标签
	 */
    @Length(max=100, message="标签最大长度为50", groups=All.class)
    private String articleTags;
    
    /**
     * 所在城市
     */
    private String articleCity;
    
    /**
     * 分类ID
     */
    @NotNull(message="分类不能为空", groups=All.class)
    private Long articleCategoryId;
    
    /**
     * 文章类型 1 草稿 2 发布
     */
    private Integer articleType;
    
    @NotBlank(message="文章内容不能为空", groups=All.class)
    @Length(max=20000, message="文章内容最大长度为20000", groups=All.class)
    private String articleContent;

    private String articleContentHtml;

	@NotBlank(message="链接不能为空", groups=Wx.class)
	private String articleOriginlink;

	/**
	 * 来源
	 */
	private String articleSource;
    
    
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

	public String getArticleTags() {
		return articleTags;
	}

	public void setArticleTags(String articleTags) {
		this.articleTags = articleTags;
	}

	public String getArticleCity() {
		return articleCity;
	}

	public void setArticleCity(String articleCity) {
		this.articleCity = articleCity;
	}

	public Long getArticleCategoryId() {
		return articleCategoryId;
	}

	public void setArticleCategoryId(Long articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}

	public Integer getArticleType() {
		return articleType;
	}

	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}
	
	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleOriginlink() {
		return articleOriginlink;
	}

	public void setArticleOriginlink(String articleOriginlink) {
		this.articleOriginlink = articleOriginlink;
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

	public interface Update{}
	
	public interface All{}

	public interface Wx{}
    
}
