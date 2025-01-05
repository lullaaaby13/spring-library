package com.lullaby.springlibrary.config;

import com.lullaby.springlibrary.security.AuthenticatedUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@RequiredArgsConstructor
@Configuration
public class JpaConfig {

    public AuditorAware<Long> auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
            if (authenticatedUser == null) {
                return Optional.empty();
            }
            return Optional.of(authenticatedUser.getId());
        };
    }


    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

}
