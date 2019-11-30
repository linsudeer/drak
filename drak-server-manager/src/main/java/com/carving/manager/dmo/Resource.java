package com.carving.manager.dmo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 资源
 *
 * @author Li Songlin
 * @date 2019/10/10 9:52
 **/
@Getter
@Setter
@Entity
@Table(name = "t_resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 资源名称
     */
    @Size(max = 50, message = "资源名称最大不超过50")
    private String name;

    /**
     * 资源的请求路径
     */
    @Size(max = 100, message = "地址最大不超过100")
    private String url;

    @Size(max = 200, message = "描述信息最大不超过200")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "resources")
    private List<Role> roles;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modify_time")
    private LocalDateTime modifyTime;
}
