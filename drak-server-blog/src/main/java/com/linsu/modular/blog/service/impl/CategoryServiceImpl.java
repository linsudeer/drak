package com.linsu.modular.blog.service.impl;

import com.linsu.modular.blog.dao.CategoryMapper;
import com.linsu.modular.blog.model.Category;
import com.linsu.modular.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Li Songlin
 * @date 2019/8/23 14:38
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllAvailableCategorys() {
        return categoryMapper.selectAllAvailable();
    }
}
