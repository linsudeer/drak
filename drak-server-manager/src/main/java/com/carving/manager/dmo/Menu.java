package com.carving.manager.dmo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 菜单(由于前后端分离，菜单由前端路由控制跳转，菜单url暂时不做权限控制，权限控制主要放在后端资源请求路径)
 *
 * @author Li Songlin
 * @date 2019/10/10 11:23
 **/
@Getter
@Setter
@Entity
@Table(name = "t_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50, message = "菜单名称最大不能超过50")
    private String title;

//    private Resource resource;

    @Size(max = 200, message = "描述信息最大不超过200")
    private String description;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modify_time")
    private LocalDateTime modifyTime;
}
