package com.lullaby.springlibrary.security;

import com.lullaby.springlibrary.application.user.domain.UserEntity;
import com.lullaby.springlibrary.application.user.domain.UserRole;
import com.lullaby.springlibrary.application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthenticationResponse.Login login(AuthenticationCommand.Login command) {
        UserEntity userEntity = userRepository.findByAccount(command.account())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "계정이 존재하지 않거나 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(command.password(), userEntity.getPassword())) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "계정이 존재하지 않거나 비밀번호가 올바르지 않습니다.");
        }

        String accessToken = jwtProvider.createAccessToken(userEntity.getId());
        String refreshToken = jwtProvider.createRefreshToken(userEntity.getId());

        return new AuthenticationResponse.Login(accessToken, refreshToken);
    }


    public void join(AuthenticationCommand.Join command) {
        if (userRepository.existsByAccount(command.account())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "이미 존재하는 계정입니다.");
        }

        UserEntity userEntity = new UserEntity(
                command.account()
                , passwordEncoder.encode(command.password())
                , command.userName()
                , UserRole.USER);
        userRepository.save(userEntity);
    }
}
