package com.lullaby.springlibrary.security;

public class AuthenticationResponse {

    public record Login(String accessToken, String refreshToken) {
    }

}
