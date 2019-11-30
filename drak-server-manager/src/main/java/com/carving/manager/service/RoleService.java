package com.carving.manager.service;

import com.carving.manager.dmo.Role;

import java.util.List;

/**
 * @author Li Songlin
 * @date 2019/10/9 18:35
 **/
public interface RoleService {

    Role add(Role role);

    Role update(Integer id, Role role);

    List<Role> getRoles();

    void delete(Integer id);
}
