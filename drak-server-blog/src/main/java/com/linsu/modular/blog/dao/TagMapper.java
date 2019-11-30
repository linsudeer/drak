package com.linsu.modular.blog.dao;

import com.linsu.modular.blog.model.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Long oid);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long oid);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    /**
     * 根据名称查找tag
     * @param name
     * @return
     */
    Tag getByName(@Param("name") String name);

    /**
     * 根据名称模糊匹配tag
     * @param name
     * @return
     */
    List<Tag> getTagsByName(@Param("name") String name, @Param("maxCnt") Integer maxCnt);

    /**
     * 查找可使用标签
     * @return
     */
    List<Tag> getAllAvailableTags();
}