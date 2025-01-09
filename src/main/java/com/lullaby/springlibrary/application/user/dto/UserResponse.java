package com.lullaby.springlibrary.application.user.dto;

import com.lullaby.springlibrary.application.user.domain.UserEntity;
import com.lullaby.springlibrary.application.user.domain.UserRole;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String account;
    private final String userName;
    private final String email;
    private final UserRole userRole;

    public UserResponse(UserEntity entity) {
        this.id = entity.getId();
        this.account = entity.getAccount();
        this.userName = entity.getUserName();
        this.email = entity.getEmail();
        this.userRole = entity.getUserRole();
    }

}
