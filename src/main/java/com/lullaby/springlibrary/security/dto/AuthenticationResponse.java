package com.lullaby.springlibrary.security.dto;

public class AuthenticationResponse {

    public record Login(String accessToken, String refreshToken) {
    }

}
