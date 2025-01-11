package com.api.glovoCRM.Models.UserModels;

import com.api.glovoCRM.Models.BaseEntity;
import com.api.glovoCRM.Models.OrderDetailModels.Cart;
import com.api.glovoCRM.Models.OrderDetailModels.OrderDetail;
import com.api.glovoCRM.constants.EUserStatuses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


//todo В будущем повесить более детальные аннотации типа @Length @NotBlank @Email(для email) и тд
//todo также надо orphanremoval jsonbackreference(надо вспомнить, что эта за тема)
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EUserStatuses status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderDetail> orders = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName().name())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status != EUserStatuses.USER_STATUS_INACTIVE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != EUserStatuses.USER_STATUS_BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        // мб в будущем добавить логику для смены пароля каждые 3 месяца, возможно надо будет создать доп пермемнную для трека последней смены пароля
    }

    @Override
    public boolean isEnabled() {
        return this.status == EUserStatuses.USER_STATUS_ACTIVE;
    }
}