package com.carving.manager.repository;

import com.carving.manager.dmo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色数据库接口
 *
 * @author Li Songlin
 * @date 2019/10/9 18:22
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * 根据角色名查找
     * @param name
     * @return
     */
    Role findByName(String name);
}
