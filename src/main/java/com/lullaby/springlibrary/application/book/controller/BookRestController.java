package com.lullaby.springlibrary.application.book.controller;

import com.lullaby.springlibrary.application.book.dto.BookCommand;
import com.lullaby.springlibrary.security.AuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/books")
@RestController
public class BookRestController {

    @GetMapping
    public List<String> getBooks(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        return List.of("반지의 제왕");
    }

    @PostMapping
    public void createBook(@RequestBody BookCommand.Creat command) {
        System.out.println(command);
    }

}
