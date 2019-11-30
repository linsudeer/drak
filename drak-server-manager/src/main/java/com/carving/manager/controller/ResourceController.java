package com.carving.manager.controller;

import com.carving.manager.dmo.Resource;
import com.carving.manager.service.ResourceService;
import com.carving.manager.util.ResultTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 请求资源
 * @author Li Songlin
 * @date 2019/11/15 15:08
 **/
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 查找所有资源
     * @return
     */
    @GetMapping("/list")
    public ResultTip getResources() {
        List<Resource> list = resourceService.findAll();
        return ResultTip.success(list);
    }
}
