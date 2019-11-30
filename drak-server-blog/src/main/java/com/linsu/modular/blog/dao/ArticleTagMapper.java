package com.linsu.modular.blog.dao;

import com.linsu.modular.blog.model.ArticleTag;
import org.apache.ibatis.annotations.Param;

public interface ArticleTagMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(ArticleTag record);

    int insertSelective(ArticleTag record);

    ArticleTag selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(ArticleTag record);

    int updateByPrimaryKey(ArticleTag record);

    /**
     * 根据文章id删除关系
     * @param articleId
     * @return
     */
    Integer deleteByArticleId(@Param("articleId") Long articleId);
}