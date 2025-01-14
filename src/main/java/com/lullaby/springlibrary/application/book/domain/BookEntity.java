package com.lullaby.springlibrary.application.book.domain;

import com.lullaby.springlibrary.common.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

import static com.lullaby.springlibrary.util.ValidationUtils.notBlank;
import static com.lullaby.springlibrary.util.ValidationUtils.notNull;

@Getter
@Table(name = "books")
@Entity
public class BookEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String publisher;

    private LocalDate publishDate;

    public BookEntity() {
    }

    public BookEntity(String title, String author, String isbn, String publisher, LocalDate publishDate) {
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setPublisher(publisher);
        setPublishDate(publishDate);
    }

    public void setTitle(String title) {
        notBlank(title, "제목은 필수 입니다.");
        this.title = title;
    }

    public void setAuthor(String author) {
        notBlank(author, "저자는 필수 입니다.");
        this.author = author;
    }

    public void setIsbn(String isbn) {
        notBlank(isbn, "ISBN은 필수 입니다.");
        this.isbn = isbn;
    }

    public void setPublisher(String publisher) {
        notBlank(publisher, "출판사는 필수 입니다.");
        this.publisher = publisher;
    }

    public void setPublishDate(LocalDate publishDate) {
        notNull(publishDate, "출판일은 필수 입니다.");
        this.publishDate = publishDate;
    }
}
