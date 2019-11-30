package com.linsu.modular.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linsu.core.support.IdGenerator;
import com.linsu.core.support.SqlHelper;
import com.linsu.modular.blog.dao.ArticleTagMapper;
import com.linsu.modular.blog.model.ArticleTag;
import com.linsu.modular.blog.service.ArticleTagService;
@Service
public class ArticleTagServiceImpl implements ArticleTagService{
	
	@Autowired
	private ArticleTagMapper articleTagMapper;

	@Override
	@Transactional
	public long save(ArticleTag atricleTag) {
		long oId = IdGenerator.generateOId();
		atricleTag.setOid(oId);
		if(SqlHelper.retBool(articleTagMapper.insertSelective(atricleTag))) {
			return atricleTag.getOid();
		}
		return 0L;
	}

	@Override
	public boolean deleteByArticleId(Long articleId) {
		return SqlHelper.delBool(articleTagMapper.deleteByArticleId(articleId));
	}

}
