package com.lullaby.springlibrary.security.oauth2;

import com.lullaby.springlibrary.application.user.dto.UserResponse;
import com.lullaby.springlibrary.security.AuthenticatedUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class GoogleOauth2User extends AuthenticatedUser implements OAuth2User {

    private final String oauthId;
    private final String name;
    private final String givenName;
    private final String familyName;
    private final String picture;
    private final String email;
    private final Boolean emailVerified;
    private final Map<String, Object> attributes;

    public GoogleOauth2User(UserResponse entity, OAuth2User oAuth2User) {
        super(entity);
        this.attributes = oAuth2User.getAttributes();
        this.oauthId = (String) attributes.get("sub");
        this.name = (String) attributes.get("name");
        this.givenName = (String) attributes.get("given_name");
        this.familyName = (String) attributes.get("family_name");
        this.picture = (String) attributes.get("picture");
        this.email = (String) attributes.get("email");
        this.emailVerified = (Boolean) attributes.get("email_verified");
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
