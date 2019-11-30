package com.linsu.modular.blog.controller;

import com.linsu.core.exception.BizException;
import com.linsu.core.exception.ExceptionEnum;
import com.linsu.core.page.Page;
import com.linsu.core.tip.ResultTip;
import com.linsu.core.tip.bean.Tip;
import com.linsu.core.util.BeanUtil;
import com.linsu.modular.blog.bean.ArticleBean;
import com.linsu.modular.blog.bean.ArticleBean.All;
import com.linsu.modular.blog.bean.ArticleBean.Update;
import com.linsu.modular.blog.bean.SessionInfo;
import com.linsu.modular.blog.constant.BlogConstant;
import com.linsu.modular.blog.dto.ArticleDTO;
import com.linsu.modular.blog.model.Article;
import com.linsu.modular.blog.search.article.ArticleSearch;
import com.linsu.modular.blog.search.article.ArticleSearchBean;
import com.linsu.modular.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 发布
	 * @param  articleId 发布的文章ID
	 * @param article 文章实体
	 * @param articleResult
	 * @return
	 */
	@PostMapping(value="/publish/{articleId}")
	public Tip publish(@PathVariable Long articleId, @Validated(value=All.class) ArticleBean article, BindingResult articleResult) {
		if(articleResult.hasErrors()) {
			return ResultTip.error(articleResult);
		}
		article.setOid(articleId);
		article.setArticleType(BlogConstant.ARTICLE_TYPE_PUBLISH);
		articleService.update(article);
		//这里创建文章索引
		// ArticleSearch.createIndex(BeanUtil.convert(article, ArticleSearchBean.class));
		return ResultTip.success();
	}

	/**
	 * 保存草稿
	 * @param article
	 * @return
	 */
	@PostMapping(value = "/draft")
	public Tip draft(ArticleBean article){

		article.setArticleType(BlogConstant.ARTICLE_TYPE_DRAFT);
		Long articleId = articleService.add(article);
		return ResultTip.success(articleId);
	}

	/**
	 * 更新草稿
	 * @param article
	 * @param articleResult
	 * @return
	 */
	@PostMapping(value="/draft/{articleId}")
	public Tip update(@PathVariable Long articleId, @Validated(value= {Update.class, All.class}) ArticleBean article, BindingResult articleResult) {

		if(articleResult.hasErrors()) {
			return ResultTip.error(articleResult);
		}
		article.setOid(articleId);
		articleService.update(article);
		return ResultTip.success();
	}

	/**
	 * 保存
	 * @param article
	 * @param articleResult
	 * @return
	 */
	@PostMapping(value="wx/article")
	public Tip addByApp(@Validated(value=ArticleBean.Wx.class) ArticleBean article, BindingResult articleResult) {
		if(articleResult.hasErrors()) {
			return ResultTip.error(articleResult);
		}
		articleService.addByWX(article);
		return ResultTip.success();
	}

	@PostMapping(value = "/article/uploadImage")
	public Tip uploadImage(MultipartFile file) {
		String url = null;
		try {
			url = articleService.uploadImage(file);
		}catch (BizException e){
			e.printStackTrace();
		}

		return ResultTip.success(url);
	}
	
	/**
	 * 根据id查找文章
	 * @param articleId
	 * @return
	 */
	@GetMapping("/articles/{articleId}")
	public Tip getById(@PathVariable Long articleId, HttpServletRequest request) {
		
		Article vo = articleService.getById(articleId);
		articleService.updateViewCnt(articleId);
		return ResultTip.success(vo);
	}
	
	
	
	/**
	 * 根据分类查找文章列表信息
	 * @param categoryId
	 * @param page
	 * @return
	 */
	@GetMapping("/articles/category/{categoryId}")
	public Tip listByCategory(@PathVariable Long categoryId, @Validated Page page, BindingResult pageResult) {
		if(pageResult.hasErrors()) {
			return ResultTip.error(pageResult);
		}
		List<ArticleDTO> list = articleService.listByCategory(categoryId, page);
		return ResultTip.success(list);
	}
	
	/**
	 * 根据标签id查找文章
	 * @param tagId
	 * @param page
	 * @return
	 */
	@GetMapping("/articles/tag/{tagId}")
	public Tip listByTag(@PathVariable Long tagId, @Validated Page page, BindingResult pageResult) {
		if(pageResult.hasErrors()) {
			return ResultTip.error(pageResult);
		}
		if(tagId == null) {
			throw new BizException(ExceptionEnum.PARAMS_ERROR);
		}
		List<ArticleDTO> list = articleService.listByTag(tagId, page);
		return ResultTip.success(list);
	}

	/**
	 * 点赞
	 * @param articleId
	 * @return
	 */
	@PostMapping("/articles/zan")
	public Tip zan(HttpServletRequest request, Long articleId) {
		SessionInfo session = (SessionInfo)request.getSession().getAttribute(BlogConstant.SESSION_KEY);
		if(session == null) {
			return ResultTip.error(-1, "登录用户可以点赞！");// 这里前端可以直接弹出登录框
		}
		Long userId = session.getUser().getOid();
		articleService.zan(articleId, userId);
		return ResultTip.success();
	}

}
