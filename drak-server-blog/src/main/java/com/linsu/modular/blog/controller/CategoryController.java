package com.linsu.modular.blog.controller;

import com.linsu.core.tip.ResultTip;
import com.linsu.core.tip.bean.Tip;
import com.linsu.modular.blog.model.Category;
import com.linsu.modular.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Li Songlin
 * @date 2019/8/23 14:42
 **/
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categorys")
    public Tip getAllAvailableCategorys() {
        List<Category> categorys = categoryService.getAllAvailableCategorys();
        return ResultTip.success(categorys);
    }
}
