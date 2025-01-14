package com.lullaby.springlibrary.application.book.dto;

import java.time.LocalDate;

public class BookCommand {

    public record Create(String title, String author, String isbn, String publisher, LocalDate publishDate) {
    }

    public record Update(String title, String author, String isbn, String publisher, LocalDate publishDate) {
    }
}
