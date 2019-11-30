package com.linsu.modular.blog.service.impl;

import com.linsu.core.support.IdGenerator;
import com.linsu.modular.blog.dao.CollectMapper;
import com.linsu.modular.blog.model.Collect;
import com.linsu.modular.blog.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Li Songlin
 * @date 2019/8/23 9:52
 **/
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public void add(Collect collect) {
        collect.setOid(IdGenerator.generateOId());
        collect.setCollectCreateTime(new Date());
        collectMapper.insert(collect);
    }
}
