package com.carving.manager.repository;

import com.carving.manager.dmo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户类数据库调用接口
 *
 * @author Li Songlin
 * @date 2019/10/9 18:21
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据登录名查找
     * @param username
     * @return
     */
    User findByUsername(String username);
}
