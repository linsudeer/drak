package com.carving.manager.controller;

import com.carving.manager.dmo.Role;
import com.carving.manager.service.RoleService;
import com.carving.manager.util.ResultTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色
 * @author Li Songlin
 * @date 2019/11/15 14:22
 **/
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public ResultTip addRole(@Validated  Role role) {
        Role res = roleService.add(role);
        return ResultTip.success(res);
    }

    @PutMapping("/role/{id}")
    public ResultTip updateRole(@PathVariable Integer id, @Validated Role role) {
        Role res = roleService.update(id, role);
        return ResultTip.success(res);
    }

    @GetMapping("/roles")
    public ResultTip getRoles() {
        List<Role> list = roleService.getRoles();
        return ResultTip.success(list);
    }

    @DeleteMapping("/role/{id}")
    public ResultTip updateRole(@PathVariable Integer id) {
        roleService.delete(id);
        return ResultTip.success();
    }
}
