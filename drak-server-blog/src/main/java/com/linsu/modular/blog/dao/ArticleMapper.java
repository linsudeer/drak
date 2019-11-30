package com.linsu.modular.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linsu.modular.blog.dto.ArticleDTO;
import com.linsu.modular.blog.model.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 查找文章列表
     * @param categoryId
     * @param offset
     * @param limit
     * @param order
     * @return
     */
	List<ArticleDTO> listByCategory(@Param("categoryId") Long categoryId, @Param("offset") Integer offset, @Param("limit") Integer limit, @Param("order")  String order);

	/**
	 * 根据标签id查找文章列表
	 * @param tagId
	 * @param offset
	 * @param limit
	 * @param order
	 * @return
	 */
	List<ArticleDTO> listByTag(@Param("tagId") Long tagId, @Param("offset") Integer offset, @Param("limit") Integer limit, @Param("order")  String order);

	/**
	 * 根据id查找文章
	 * @param oid
	 * @return
	 */
	ArticleDTO getByOid(@Param("oid") Long oid);

	/**
	 * 更新阅读量
	 * @param articleId
	 * @param step 增加的步长
	 */
	void updateViewCnt(@Param("articleId") Long articleId, @Param("step") int step);

	/**
	 * 点赞数量+1
	 * @param articleId
	 */
    void updateGoodCnt(@Param("articleId") Long articleId, @Param("step")  Integer step);
}