package com.lullaby.springlibrary.security.oauth2;

import com.lullaby.springlibrary.application.user.domain.OauthProvider;
import com.lullaby.springlibrary.application.user.domain.UserEntity;
import com.lullaby.springlibrary.application.user.dto.UserCommand;
import com.lullaby.springlibrary.application.user.dto.UserResponse;
import com.lullaby.springlibrary.application.user.repository.UserRepository;
import com.lullaby.springlibrary.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@RequiredArgsConstructor
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        return switch (registrationId) {
            case "google" -> ofGoogle(oAuth2User);
            case "kakao" -> ofKakao(oAuth2User);
            case "naver" -> ofNaver(oAuth2User);
            default -> throw new IllegalArgumentException("지원하지 않는 OAuth2 인증입니다.");
        };
    }

    private OAuth2User ofGoogle(OAuth2User oAuth2User) {

        Map<String, Object> response = oAuth2User.getAttributes();
        String oauthId = (String) response.get("sub");

        UserResponse userResponse = userRepository.findByOauthId(oauthId)
                .map(UserResponse::new)
                .orElseGet(() -> join(
                        (String) response.get("email")
                        , (String) response.get("name")
                        , OauthProvider.GOOGLE
                        , oauthId
                ));

        return new GoogleOauth2User(userResponse, oAuth2User);
    }

    private OAuth2User ofKakao(OAuth2User oAuth2User) {

        Map<String, Object> response = oAuth2User.getAttributes();
        String oauthId = String.valueOf(response.get("id"));

        Map<String, String> properties = (LinkedHashMap<String, String>) response.get("properties");

        UserResponse userResponse = userRepository.findByOauthId(oauthId)
                .map(UserResponse::new)
                .orElseGet(() -> join(
                        null
                        , properties.get("nickname")
                        , OauthProvider.KAKAO
                        , oauthId
                ));

        return new KakaoOauth2User(userResponse, oAuth2User);
    }

    private OAuth2User ofNaver(OAuth2User oAuth2User) {

        Map<String, String> response = (LinkedHashMap<String, String>) oAuth2User.getAttributes().get("response");

        UserResponse userResponse = userRepository.findByOauthId(response.get("id"))
                .map(UserResponse::new)
                .orElseGet(() -> join(
                        response.get("email")
                        , response.get("name")
                        , OauthProvider.NAVER
                        , response.get("id")
                ));

        return new NaverOauth2User(userResponse, oAuth2User);
    }

    private UserResponse join(String email, String userName, OauthProvider oauthProvider, String oauthId) {
        UserEntity userEntity = UserEntity.byOauth(email, userName, oauthProvider, oauthId);
        userEntity = userRepository.save(userEntity);
        return new UserResponse(userEntity);

    }
}
