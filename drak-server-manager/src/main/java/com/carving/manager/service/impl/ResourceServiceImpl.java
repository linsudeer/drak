package com.carving.manager.service.impl;

import com.carving.manager.dmo.Resource;
import com.carving.manager.exception.DataNotExistException;
import com.carving.manager.repository.ResourceRepository;
import com.carving.manager.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Li Songlin
 * @date 2019/10/10 15:22
 **/
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    @Override
    public List<Resource> findByAccount(String account) {
        List<Resource> resources = resourceRepository.findByUsername(account);
        if (resources == null) {
            return new ArrayList<>();
        }
        return resources;
    }

    @Override
    public Resource add(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public Resource update(Integer id, Resource resource) {
        Resource dbResource = resourceRepository.findById(id).orElseThrow(() -> new DataNotExistException("ID为【%s】的资源不存在", id));
        dbResource.setDescription(resource.getDescription());
        dbResource.setModifyTime(LocalDateTime.now());
        dbResource.setName(resource.getName());
        dbResource.setUrl(resource.getUrl());
        return resourceRepository.save(dbResource);
    }

    @Override
    public void delete(Integer id) {
        resourceRepository.deleteById(id);
    }


}
