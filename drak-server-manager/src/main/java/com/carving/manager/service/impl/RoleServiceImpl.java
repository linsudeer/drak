package com.carving.manager.service.impl;

import com.carving.manager.dmo.Role;
import com.carving.manager.exception.UniqueException;
import com.carving.manager.exception.DataNotExistException;
import com.carving.manager.repository.RoleRepository;
import com.carving.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Li Songlin
 * @date 2019/10/9 18:35
 **/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        // 先判断是否存在
        Role dbRole = roleRepository.findByName(role.getName());
        if(dbRole != null) {
            throw new UniqueException("角色名称【%s】已存在！", role.getName());
        }
        return roleRepository.save(role);
    }

    @Override
    public Role update(Integer id, Role role) {
        Role dbRole = roleRepository.findById(id).orElseThrow(() -> new DataNotExistException("ID为【%s】的角色不存在", id));
        dbRole.setModifyTime(LocalDateTime.now());
        dbRole.setResources(role.getResources());
        dbRole.setName(role.getName());
        dbRole.setDescription(role.getDescription());
        dbRole.setSeq(role.getSeq());
        return roleRepository.save(dbRole);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
