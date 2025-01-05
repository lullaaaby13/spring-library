package com.lullaby.springlibrary.application.book.controller;

import com.lullaby.springlibrary.security.AuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/books")
@RestController
public class BookRestController {

    @GetMapping
    public List<String> getBooks(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        return List.of("반지의 제왕");
    }

}
