package com.anynak.diary.config.security.data;

import com.anynak.diary.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {

    private final long userId;
    private final String userName;
    private final String password;
    private final boolean active;
    private final List<GrantedAuthority> authority;

    public UserDetailsImpl(User user) {
        this.userId = user.getUserId();
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authority = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
        System.out.println("UserDetailsImpl " + authority);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getUserId() {
        return userId;
    }
}
