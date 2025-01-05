package com.lullaby.springlibrary.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse.Login login(@RequestBody AuthenticationCommand.Login command) {
        return authenticationService.login(command);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/join")
    public void join(@RequestBody AuthenticationCommand.Join command) {
        authenticationService.join(command);
    }
}
