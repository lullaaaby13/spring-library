package com.lullaby.springlibrary.application.book.service;

import com.lullaby.springlibrary.application.book.domain.BookEntity;
import com.lullaby.springlibrary.application.book.dto.BookCommand;
import com.lullaby.springlibrary.application.book.dto.BookResponse;
import com.lullaby.springlibrary.application.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponse> getBooks() {
        return bookRepository.findAll().stream()
                .map(BookResponse::new)
                .toList();
    }

    public BookResponse create(BookCommand.Create command) {
        BookEntity bookEntity = new BookEntity(command.title(), command.author(), command.isbn(), command.publisher(), command.publishDate());
        log.info("[Book] 생성 [title = {}]", command.title());
        return new BookResponse(bookRepository.save(bookEntity));
    }

    public BookResponse update(Long id, BookCommand.Update command) {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "책을 찾을 수 없습니다."));
        bookEntity.setTitle(command.title());
        bookEntity.setAuthor(command.author());
        bookEntity.setIsbn(command.isbn());
        bookEntity.setPublisher(command.publisher());
        bookEntity.setPublishDate(command.publishDate());
        log.info("[Book] 수정 [id = {}]", id);
        return new BookResponse(bookEntity);

    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
        log.info("[Book] 삭제 [id = {}]", id);
    }

    public BookResponse getBook(Long id) {
        return bookRepository.findById(id)
                .map(BookResponse::new)
                .orElse(null);
    }
}
