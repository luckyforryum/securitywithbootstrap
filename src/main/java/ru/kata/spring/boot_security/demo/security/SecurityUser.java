package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final String email;
    private final long id;
    private final List<Role> roles;
    private final List<SimpleGrantedAuthority> authorities;
    private final  boolean isActive;

    public SecurityUser(String username, String password, String email, long id, List<Role> roles, List<SimpleGrantedAuthority> authorities, boolean isActive) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
        this.roles = roles;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean isActive() {
        return isActive;
    }
}
