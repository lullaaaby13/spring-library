package com.lullaby.springlibrary.application.user.dto;

public class UserCommand {

    public record Create(String account, String password, String userName) {}

}
