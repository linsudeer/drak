package com.carving.manager.context;

import com.carving.manager.dmo.Resource;
import com.carving.manager.dmo.Role;
import com.carving.manager.dmo.User;
import com.carving.manager.exception.UniqueException;
import com.carving.manager.service.ResourceService;
import com.carving.manager.service.RoleService;
import com.carving.manager.service.UserService;
import com.carving.manager.util.ManagerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 容器启动后初始化数据。有三种方式
 * 方式一：继承 InitializingBean
 * 方式二：继承 ApplicationListener<ContextRefreshedEvent>
 * 在web 项目中（spring mvc），系统会存在两个容器，一个是root application context ,另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
 * 这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免上面提到的问题，我们可以只在root application context初始化完成后调用逻辑代码（event.getApplicationContext().getParent() == null），其他的容器的初始化完成，则不做任何处理。
 * 方式三：@PostConstruct（本例使用）
 *
 * 1）在bean创建的过程中，初始化时会先调用@PostConstruct注解标注的方法，而后调用实现InitializingBean接口的afterPropertiesSet方法
 * 2）在finishRefresh()会分发事件，
 *
 * @author Li Songlin
 * @date 2019/10/9 17:42
 **/
@Slf4j
@Component
public class InitData {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        initAdmin();
    }

    /**
     * 这里分配默认管理员
     */
    private void initAdmin() {
        log.info("---------------- 开始初始化系统默认用户 ----------------");
        try {
            // 生成默认角色
            Role role = new Role();
            role.setName("admin");
            role.setCreateTime(LocalDateTime.now());

            // 生成默认的角色和权限的关系
            List<Resource> resources = resourceService.findAll();
            if(resources != null && resources.size()>0) {
                role.setResources(resources);
            }
            Role dbRole = roleService.add(role);

            // 生成默认管理员
            User user = new User();
            user.setUsername("admin");
            user.setNickname("超级管理员");
            String password = ManagerUtil.generatePassword();
            user.setPassword(passwordEncoder.encode(password));

            List<Role> roles = new ArrayList<>();
            roles.add(dbRole);
            user.setRoles(roles);
            userService.save(user);
            log.info("-----------------------------------------------");
            log.info("系统管理员账号");
            log.info("用户名：admin");
            log.info("密码："+password);
            log.info("-----------------------------------------------");
        }catch (UniqueException e) {
            log.info(e.getMessage());
        }
    }
}
