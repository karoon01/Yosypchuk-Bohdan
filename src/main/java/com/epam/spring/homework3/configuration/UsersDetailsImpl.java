package com.epam.spring.homework3.configuration;

import com.epam.spring.homework3.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsersDetailsImpl implements UserDetails {

    private final String email;
    private final String password;
    private final boolean enabled;
    private final List<GrantedAuthority> grantedAuthorities;

    public UsersDetailsImpl(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = true;
        this.grantedAuthorities = List.of(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return enabled;
    }
}
