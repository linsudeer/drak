package com.carving.manager.service;

import com.carving.manager.dmo.Resource;

import java.util.List;

/**
 * @author Li Songlin
 * @date 2019/10/10 15:21
 **/
public interface ResourceService {

    List<Resource> findAll();

    /**
     * 查找账号所拥有的资源权限
     * @param account
     * @return
     */
    List<Resource> findByAccount(String account);

    Resource add(Resource resource);

    Resource update(Integer id, Resource resource);

    void delete(Integer id);
}
