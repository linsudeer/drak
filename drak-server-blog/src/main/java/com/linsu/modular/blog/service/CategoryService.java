package com.linsu.modular.blog.service;

import com.linsu.modular.blog.model.Category;

import java.util.List;

/**
 * 分类
 */
public interface CategoryService {

    List<Category> getAllAvailableCategorys();
}
