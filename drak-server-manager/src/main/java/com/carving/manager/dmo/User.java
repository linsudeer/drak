package com.carving.manager.dmo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Li Songlin
 * @date 2019/9/20 15:26
 **/
@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 真实姓名
     */
    @Size(max = 50, message = "用户名最大不超过50", groups = LoginValide.class)
    private String username;

    /**
     * 密码
     */
    @Size(min = 8, max = 20, message = "密码长度在8-20之间", groups = LoginValide.class)
    private String password;

    /**
     * 账户是否过期
     */
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    /**
     * 账户是否被锁（冻结）
     */
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    /**
     * 账户凭证是否过期
     */
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    /**
     * 昵称
     */
    @Size(max = 50, message = "用户名最大不超过50")
    private String nickname;

    /**
     * 是否可用
     */
    private boolean enabled = true;

    /**
     * 排序
     */
    private Integer seq = 0;

    /**
     * 拥有的角色
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return authorities;
    }


    public interface LoginValide {}
}
