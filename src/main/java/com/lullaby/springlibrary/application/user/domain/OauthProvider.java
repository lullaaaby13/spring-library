package com.lullaby.springlibrary.application.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OauthProvider {
    GOOGLE("구글"),
    KAKAO("카카오"),
    NAVER("네이버");

    private final String description;
}
