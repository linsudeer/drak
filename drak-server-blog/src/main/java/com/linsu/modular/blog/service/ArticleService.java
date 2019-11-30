package com.linsu.modular.blog.service;

import com.linsu.core.page.Page;
import com.linsu.modular.blog.bean.ArticleBean;
import com.linsu.modular.blog.dto.ArticleDTO;
import com.linsu.modular.blog.model.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文章类接口
 * @author lisonglin
 * @date 2018年3月8日 下午5:16:30
 */
public interface ArticleService {

	/**
	 * 新增一篇文章
	 * @param article
	 * @return 新增的文章ID
	 */
	long add(ArticleBean article);
	
	/**
	 * 更新文章
	 * @param article
	 * @return 是否更新成功
	 */
	boolean update(ArticleBean article);

	/**
	 * 上传图片
	 * @param bytes 图片字节数组
	 * @param ext 扩展名
	 * @return
	 */
	String uploadImage(MultipartFile file);
	
	/**
	 * 根据作者查找文章
	 * @param userId
	 * @return
	 */
	List<Article> listByAuthorId(String authorId);

	/**
	 * 根据文章id查找文章
	 * @param articleId
	 * @return
	 */
	Article getById(Long articleId);

	/**
	 * 根据分类查找文章(文章状态为正常，发布状态为已发布)
	 * @param categoryId
	 * @param page
	 * @return
	 */
	List<ArticleDTO> listByCategory(Long categoryId, Page page);

	/**
	 * 根据标签查找文章（根据分类查找文章(文章状态为正常，发布状态为已发布)）
	 * @param tagId
	 * @param page
	 * @return
	 */
	List<ArticleDTO> listByTag(Long tagId, Page page);

	/**
	 * wx端添加
	 * @param article
	 */
    void addByWX(ArticleBean article);

	/**
	 * 更新阅读量
	 * @param articleId
	 */
	void updateViewCnt(Long articleId);

	/**
	 * 文章点赞
	 * @param articleId
	 * @param userId
	 */
	void zan(Long articleId, Long userId);
}
