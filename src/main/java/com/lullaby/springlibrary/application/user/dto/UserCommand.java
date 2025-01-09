package com.lullaby.springlibrary.application.user.dto;

import com.lullaby.springlibrary.application.user.domain.OauthProvider;

public class UserCommand {

    public record CreateByLocal(String account, String password, String userName) {}

    public record CreateByOAuth(String email, String userName, OauthProvider oauthProvider, String oauthId) {}

}
