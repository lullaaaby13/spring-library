package com.lullaby.springlibrary.acceptance.auth;

import com.lullaby.springlibrary.security.dto.AuthenticationCommand;
import com.lullaby.springlibrary.security.dto.AuthenticationResponse;
import io.restassured.RestAssured;

public class AuthenticationFixture {

    public static String accessToken;

    public static void 사용자_생성_및_로그인(String account, String password, String userName) {
        RestAssured.given()
                .body(new AuthenticationCommand.Join(account, password, userName))
                .post("/api/auth/join");

        accessToken = RestAssured.given()
                .body(new AuthenticationCommand.Login(account, password))
                .post("/api/auth/login")
                .as(AuthenticationResponse.Login.class)
                .accessToken();
    }


}
