package com.carving.manager.repository;

import com.carving.manager.dmo.Resource;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Li Songlin
 * @date 2019/10/10 15:20
 **/
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    /**
     * 根据用户名查找资源
     * @param username
     * @return
     */
    @Query(nativeQuery = true, value = "select re.* from t_resource re left join t_role_resource rr on re.id=rr.resource_id left join t_role r on rr.role_id=r.id left join t_user_role ur on r.id= ur.role_id left join t_user u on ur.user_id=u.id where u.username= ?")
    List<Resource> findByUsername(String username);

    /**
     * 根据角色id 资源
     * @param roleId
     * @return
     */
    @Query(nativeQuery = true, value = "select re.id,re.name,re.url from t_resource re left join t_role_resource rr on re.id=rr.resource_id where rr.role_id= ?")
    List<Resource> findByRoleId(Integer roleId);
}
