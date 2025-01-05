package com.lullaby.springlibrary.application.book.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookCommand {

    public record Creat(String title, LocalDate publishDate, LocalDateTime createdAt) {
    }
}
