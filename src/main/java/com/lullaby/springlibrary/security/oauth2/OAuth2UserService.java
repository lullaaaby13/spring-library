package com.lullaby.springlibrary.security.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        switch (registrationId) {
            case "google":
                return new GoogleOauth2User(
                        Long.valueOf(attributes.get("sub"))
                        , (String) attributes.get("name")
                        , (String) attributes.get("given_name")
                        , (String) attributes.get("family_name")
                        , (String) attributes.get("picture")
                        , (String) attributes.get("email")
                        , (Boolean) attributes.get("email_verified")
                );
            case "kakao":
            default:
                return oAuth2User;
        }

        // Role generate
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");

        // nameAttributeKey
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // DB 저장로직이 필요하면 추가

        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName);
    }
}
