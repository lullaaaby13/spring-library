package com.lullaby.springlibrary.security;

import com.lullaby.springlibrary.application.user.domain.UserRole;
import com.lullaby.springlibrary.application.user.dto.UserResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthenticatedUser implements UserDetails {

    private final Long id;
    private final String account;
    private final String userName;
    private final UserRole userRole;
    private final String email;
    private final List<GrantedAuthority> userRoles;

    public AuthenticatedUser(UserResponse entity) {
        this.id = entity.getId();
        this.account = entity.getAccount();
        this.userName = entity.getUserName();
        this.userRole = entity.getUserRole();
        this.email = entity.getEmail();
        this.userRoles = List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return this.id.toString();
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getUserName() {
        return userName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getEmail() {
        return email;
    }

}
