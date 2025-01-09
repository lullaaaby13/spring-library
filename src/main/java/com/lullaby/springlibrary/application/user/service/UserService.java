package com.lullaby.springlibrary.application.user.service;

import com.lullaby.springlibrary.application.user.domain.UserEntity;
import com.lullaby.springlibrary.application.user.domain.UserRole;
import com.lullaby.springlibrary.application.user.dto.UserCommand;
import com.lullaby.springlibrary.application.user.dto.UserResponse;
import com.lullaby.springlibrary.application.user.repository.UserRepository;
import com.lullaby.springlibrary.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserResponse> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserResponse::new);
    }

    public UserResponse findByAccount(String account) {
        UserEntity userEntity = userRepository.findByAccount(account)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        return new UserResponse(userEntity);
    }

    public UserResponse createByLocal(UserCommand.CreateByLocal command) {
        UserEntity userEntity = UserEntity.byLocal(
                command.account()
                , passwordEncoder.encode(command.password())
                , command.userName()
        );

        userEntity = userRepository.save(userEntity);

        return new UserResponse(userEntity);
    }

    public UserResponse createByOAuth(UserCommand.CreateByOAuth command) {
        UserEntity userEntity = UserEntity.byOauth(
                command.email()
                , command.userName()
                , command.oauthProvider()
                , command.oauthId()
        );

        userEntity = userRepository.save(userEntity);

        return new UserResponse(userEntity);
    }

}
