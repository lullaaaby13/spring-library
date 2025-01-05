package com.lullaby.springlibrary.application.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN("관리자"),
    MANAGER("매니저"),
    USER("사용자");

    private final String description;

}
