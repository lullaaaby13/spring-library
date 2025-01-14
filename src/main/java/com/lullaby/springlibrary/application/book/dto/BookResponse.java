package com.lullaby.springlibrary.application.book.dto;

import com.lullaby.springlibrary.application.book.domain.BookEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private LocalDate publishedAt;

    public BookResponse(BookEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.isbn = entity.getIsbn();
        this.publisher = entity.getPublisher();
        this.publishedAt = entity.getPublishDate();
    }
}
