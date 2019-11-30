package com.linsu.modular.blog.service;

import com.linsu.modular.blog.model.ArticleTag;

/**
 * 文章和标签关系
 * @author lisonglin
 * @date 2018年3月27日 下午5:00:02
 */
public interface ArticleTagService {

	long save(ArticleTag atricleTag);

	boolean deleteByArticleId(Long articleId);
}
