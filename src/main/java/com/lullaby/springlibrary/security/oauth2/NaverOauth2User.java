package com.lullaby.springlibrary.security.oauth2;

import com.lullaby.springlibrary.application.user.dto.UserResponse;
import com.lullaby.springlibrary.security.AuthenticatedUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class NaverOauth2User extends AuthenticatedUser implements OAuth2User {

    private final String oauthId;
    private final String nickname;
    private final String profileImage;
    private final String email;
    private final String mobile;
    private final String name;
    private final Map<String, Object> attributes;

    public NaverOauth2User(UserResponse entity, OAuth2User oAuth2User) {
        super(entity);
        this.attributes = oAuth2User.getAttributes();
        Map<String, String> response = (LinkedHashMap<String, String>) oAuth2User.getAttributes().get("response");
        this.oauthId = response.get("id");
        this.nickname = response.get("nickname");
        this.profileImage = response.get("profile_image");
        this.email = response.get("email");
        this.mobile = response.get("mobile");
        this.name = response.get("name");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getName() {
        return super.getId().toString();
    }

}
