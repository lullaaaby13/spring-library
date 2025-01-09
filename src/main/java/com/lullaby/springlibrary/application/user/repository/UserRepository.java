package com.lullaby.springlibrary.application.user.repository;

import com.lullaby.springlibrary.application.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByAccount(String account);

    boolean existsByAccount(String account);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByOauthId(String oauthId);

}
