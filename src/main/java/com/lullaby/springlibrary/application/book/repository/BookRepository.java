package com.lullaby.springlibrary.application.book.repository;

import com.lullaby.springlibrary.application.book.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
