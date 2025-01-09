package com.lullaby.springlibrary.security.oauth2;

import com.lullaby.springlibrary.application.user.dto.UserResponse;
import com.lullaby.springlibrary.security.AuthenticatedUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class KakaoOauth2User extends AuthenticatedUser implements OAuth2User {

    private final Long oauthId;
    private final ZonedDateTime connectedAt;
    private final String nickname;
    private final String profileImage;
    private final String thumbnailImage;
    private final Map<String, Object> attributes;

    public KakaoOauth2User(UserResponse entity, OAuth2User oAuth2User) {
        super(entity);
        this.attributes = oAuth2User.getAttributes();
        this.oauthId = (Long) attributes.get("id");
        this.connectedAt = ZonedDateTime.parse((String) attributes.get("connected_at"));

        Map<String, String> properties = (LinkedHashMap<String, String>) this.attributes.get("properties");

        this.nickname = properties.get("nickname");
        this.profileImage = properties.get("profile_image");
        this.thumbnailImage = properties.get("thumbnail_image");
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
        return "";
    }
}
