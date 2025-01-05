package com.lullaby.springlibrary.security.oauth2;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class GoogleOauth2User implements OAuth2User {

    private final Long sub;
    private final String name;
    private final String givenName;
    private final String familyName;
    private final String picture;
    private final String email;
    private final Boolean emailVerified;
    private final Map<String, Object> attributes;


    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return "";
    }
}
