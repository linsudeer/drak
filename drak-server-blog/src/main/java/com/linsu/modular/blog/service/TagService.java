package com.linsu.modular.blog.service;

import com.linsu.modular.blog.model.Tag;

import java.util.List;

/**
 * 标签
 * @author lisonglin
 * @date 2018年3月27日 下午2:58:31
 */
public interface TagService {

	long save(Tag tag);
	
	Tag getByName(String name);

	/**
	 * 模糊匹配标签
	 * @param name
	 * @param maxCnt 请求的最大数量
	 * @return
	 */
	List<Tag> getTagsByName(String name, Integer maxCnt);

	/**
	 * 查找所有可用的标签
	 * @return
	 */
	List<Tag> getAllAvailableTags();
	
}
