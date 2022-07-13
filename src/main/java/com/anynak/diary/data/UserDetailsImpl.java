package com.anynak.diary.data;

import antlr.StringUtils;
import com.anynak.diary.RoleName;
import com.anynak.diary.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final String userName;
    private final String password;
    private final boolean active;
    private List<GrantedAuthority> authority;

    public UserDetailsImpl(User user) {
        this.userName = user.getLogin();
        this.password = user.getPasswordHash();
        this.active = true;
        this.authority = Arrays.stream(user.getRoles().toArray()).map(Object::toString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        System.out.println("UserDetailsImpl "+authority);

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
}
