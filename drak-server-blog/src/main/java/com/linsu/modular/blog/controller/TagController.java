package com.linsu.modular.blog.controller;

import com.linsu.core.tip.ResultTip;
import com.linsu.core.tip.bean.Tip;
import com.linsu.modular.blog.model.Tag;
import com.linsu.modular.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签
 * @author lisonglin
 * @date 2018年3月27日 下午3:40:19
 */
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{name}")
    public Tip getTagsByName(@PathVariable("name") String name, Integer maxCnt) {
        List<Tag> tags = tagService.getTagsByName(name, maxCnt);
        return ResultTip.success(tags);
    }

    @GetMapping("/tags")
    public Tip getAllAvailableTags() {
        List<Tag> tags = tagService.getAllAvailableTags();
        return ResultTip.success(tags);
    }

}
