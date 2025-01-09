package com.lullaby.springlibrary.security.dto;

public class AuthenticationCommand {

    public record Login(String account, String password) {
    }

    public record Join(String account, String password, String userName) {
    }
}
