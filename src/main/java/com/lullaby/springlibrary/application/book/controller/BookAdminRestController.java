package com.lullaby.springlibrary.application.book.controller;

import com.lullaby.springlibrary.application.book.dto.BookCommand;
import com.lullaby.springlibrary.application.book.dto.BookResponse;
import com.lullaby.springlibrary.application.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/admin/books")
@RequiredArgsConstructor
@RestController
public class BookAdminRestController {

    private final BookService bookService;

    @PostMapping
    public BookResponse createBook(@RequestBody BookCommand.Create command) {
        return bookService.create(command);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody BookCommand.Update command) {
        return bookService.update(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

}
