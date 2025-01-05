package com.lullaby.springlibrary.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtProviderTest {

    @Autowired
    JwtProvider jwtProvider;

    @Test
    void name() {
        String token = jwtProvider.createAccessToken(1L);
        System.out.println(token);
        System.out.println(jwtProvider.validateToken(token));
        Long parse = jwtProvider.parse(token);
        System.out.println(parse);
    }
}

