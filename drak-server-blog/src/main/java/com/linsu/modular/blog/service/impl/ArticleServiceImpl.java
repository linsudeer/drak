package com.linsu.modular.blog.service.impl;

import com.linsu.core.exception.NoLoginException;
import com.linsu.core.fdfs.FastDFSClient;
import com.linsu.core.page.Page;
import com.linsu.core.support.HttpServletRequestHolder;
import com.linsu.core.support.IdGenerator;
import com.linsu.core.support.SqlHelper;
import com.linsu.core.util.BeanUtil;
import com.linsu.core.util.DateUtil;
import com.linsu.core.util.HttpUtil;
import com.linsu.core.util.RegexUtils;
import com.linsu.modular.blog.bean.ArticleBean;
import com.linsu.modular.blog.constant.ArticleSource;
import com.linsu.modular.blog.dao.ArticleMapper;
import com.linsu.modular.blog.dto.ArticleDTO;
import com.linsu.modular.blog.model.*;
import com.linsu.modular.blog.service.ArticleService;
import com.linsu.modular.blog.service.ArticleTagService;
import com.linsu.modular.blog.service.CollectService;
import com.linsu.modular.blog.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private ArticleTagService articleTagService;

	@Autowired
	private FastDFSClient fastDFSClient;

	@Autowired
	private CollectService collectService;

	@Override
	@Transactional
	public long add(ArticleBean article) {
		User user = HttpServletRequestHolder.getUser();
		if(user == null) {
			throw new NoLoginException();
		}
		//保存文章
		article.setArticleSource(ArticleSource.PC.getSource());
		Article dbArticle = BeanUtil.convert(article, Article.class);
		dbArticle.setOid(IdGenerator.generateOId());
		//设置发布者
		dbArticle.setArticleAuthorId(user.getOid());
		dbArticle.setArticleAuthorNickname(user.getUserNickname());
		dbArticle.setArticleAuthorEmail(user.getUserEmail());
		// 保存缩略图
		dbArticle.setArticleThumb(RegexUtils.getImgUrl(article.getArticleContentHtml()));
		//设置连接
		String permaklink = "/articles/"+dbArticle.getOid();
		dbArticle.setArticlePermalink(permaklink);
		dbArticle.setArticleOriginlink(permaklink);
		dbArticle.setArticleTags(article.getArticleTags());
		if(SqlHelper.retBool(articleMapper.insertSelective(dbArticle))){
			//检查标签是否存在, 关于标签和分类的区分以及保存形势待定 TODO
			String articleTags = article.getArticleTags();
			saveTagByArticle(dbArticle.getOid(), articleTags);
			return dbArticle.getOid();
		}
		return 0L;
	}

	private void saveTagByArticle(Long articleId, String articleTags) {
		if(!StringUtils.isBlank(articleTags)) {
			String[] tagArray = articleTags.split(",");
			for (String tagName : tagArray) {
				Tag tag = tagService.getByName(tagName);
				if(tag == null) {
					//新建标签
					tag = new Tag(tagName);
					tagService.save(tag);
				}
				// 保存标签和文章的关系
				articleTagService.save(new ArticleTag(tag.getOid(), articleId));
			}
		}
	}

	@Override
	@Transactional
	public boolean update(ArticleBean article) {
		Long oid = article.getOid();
		if(oid == null) {
			oid = this.add(article);
		}
		//更新文章
		Article dbArticle = articleMapper.selectByPrimaryKey(oid);
		dbArticle.setArticleContent(article.getArticleContent());
		dbArticle.setArticleContentHtml(article.getArticleContentHtml());
		dbArticle.setArticleTitle(article.getArticleTitle());
		dbArticle.setArticleUpdateTime(DateUtil.getDate());
		dbArticle.setArticleType(article.getArticleType());
		dbArticle.setArticleTags(article.getArticleTags());
		// 保存缩略图
		dbArticle.setArticleThumb(RegexUtils.getImgUrl(article.getArticleContentHtml()));
		if(SqlHelper.retBool(articleMapper.updateByPrimaryKeyWithBLOBs(dbArticle))){
			//检查标签是否存在, 关于标签和分类的区分以及保存形势待定
			//删除文章和标签的关系
			articleTagService.deleteByArticleId(oid);
			String articleTags = article.getArticleTags();
			saveTagByArticle(oid, articleTags);
		}
		return true;
	}

	@Override
	@Transactional
	public String uploadImage(MultipartFile file) {
		return fastDFSClient.uploadFile(file);
	}

	@Override
	public List<Article> listByAuthorId(String authorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article getById(Long articleId) {
		return articleMapper.selectByPrimaryKey(articleId);
	}

	@Override
	public List<ArticleDTO> listByCategory(Long categoryId, Page page) {
		return articleMapper.listByCategory(categoryId, page.getOffset(), page.getLimit(), page.getOrder());
	}

	@Override
	public List<ArticleDTO> listByTag(Long tagId, Page page) {
		return articleMapper.listByTag(tagId, page.getOffset(), page.getLimit(), page.getOrder());
	}

	@Override
	@Transactional
	public void addByWX(ArticleBean article) {
		String html = HttpUtil.sendGet(article.getArticleOriginlink(),new HashMap<>());
		article.setArticleContent(html);
		article.setArticleSource(ArticleSource.WX.getSource());
		this.add(article);
	}

	@Override
	@Transactional
	public void updateViewCnt(Long articleId) {
		articleMapper.updateViewCnt(articleId, 1);
	}

	@Override
	@Transactional
	public void zan(Long articleId, Long userId) {
		Article article = getById(articleId);
		if(article != null) {
			Integer good = article.getArticleGoodCount();
			good = (good == null?0:good);
			articleMapper.updateGoodCnt(articleId, good+1);

			Collect collect = new Collect();
			collect.setCollectArticleId(articleId);
			collect.setCollectArticleTitle(article.getArticleTitle());
			collect.setCollectUserId(userId);
			collectService.add(collect);
		}
	}


}
