package com.lullaby.springlibrary.application.book.controller;

import com.lullaby.springlibrary.application.book.dto.BookResponse;
import com.lullaby.springlibrary.application.book.service.BookService;
import com.lullaby.springlibrary.security.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/books")
@RequiredArgsConstructor
@RestController
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getBooks(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @PathVariable Long id) {
        return bookService.getBook(id);
    }

}
