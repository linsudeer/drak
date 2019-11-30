package com.carving.manager.dmo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Li Songlin
 * @date 2019/9/20 15:31
 **/
@Data
@Table(name = "t_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "角色标识不能为空")
    private String key;

    @NotBlank(message = "角色名称不能为空")
    @Size(max = 50, message = "角色名称最大不超过50")
    private String name;

    private Integer seq = 0;

    @Size(max = 200, message = "描述信息最大不超过200")
    private String description;

    @ManyToMany
    @JoinTable(name = "t_role_resource", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private List<Resource> resources;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modify_time")
    private LocalDateTime modifyTime;
}
