package com.linsu.modular.blog.dto;

import java.util.List;

import com.linsu.modular.blog.model.Article;
import com.linsu.modular.blog.model.Category;
import com.linsu.modular.blog.model.Comment;
import com.linsu.modular.blog.model.Tag;

/**
 * 文章展示模型
 * @author lisonglin
 * @date 2018年3月27日 下午6:22:41
 */
public class ArticleDTO extends Article{

	/**
	 * 文章对应的标签
	 */
	private List<Tag> tags;
	
	/**
	 * 文章对应的分类
	 */
    private Category category;
    
    public ArticleDTO() {};

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    
}
