package com.lullaby.springlibrary.acceptance.book;

import com.lullaby.springlibrary.acceptance.AcceptanceTest;
import com.lullaby.springlibrary.application.book.dto.BookCommand;
import com.lullaby.springlibrary.application.book.dto.BookResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.lullaby.springlibrary.acceptance.book.BookFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("책")
public class BookAcceptanceTest extends AcceptanceTest {

    @DisplayName("생성하고 조회하면 생성된 책을 조회할 수 있다.")
    @Test
    void test() {
        BookCommand.Create command = new BookCommand.Create("인스타브레인", "안데르스 한센", "9791190382174", "인플루엔셜", LocalDate.of(2021, 7, 21));
        BookResponse response = 책_생성(command);
        List<BookResponse> responses = 책_목록_조회();
        assertEquals(1, responses.size());
        assertEquals(response.getTitle(), responses.get(0).getTitle());
        assertEquals(response.getAuthor(), responses.get(0).getAuthor());
        assertEquals(response.getIsbn(), responses.get(0).getIsbn());
        assertEquals(response.getPublisher(), responses.get(0).getPublisher());
        assertEquals(response.getPublishedAt(), responses.get(0).getPublishedAt());
    }

    @DisplayName("생성된 책을 수정할 수 있다.")
    @Test
    void test2() {
        BookCommand.Create command = new BookCommand.Create("인스타브레인", "안데르스 한센", "9791190382174", "인플루엔셜", LocalDate.of(2021, 7, 21));
        BookResponse bookResponse = 책_생성(command);
        책_수정(bookResponse.getId(), new BookCommand.Update("반지의제왕", "외국사람", "1234", "출판사", LocalDate.of(2022, 10, 13)));

        List<BookResponse> responses = 책_목록_조회();
        assertEquals(1, responses.size());
        assertEquals("반지의제왕", responses.get(0).getTitle());
        assertEquals("외국사람", responses.get(0).getAuthor());
        assertEquals("1234", responses.get(0).getIsbn());
        assertEquals("출판사", responses.get(0).getPublisher());
        assertEquals(LocalDate.of(2022, 10, 13), responses.get(0).getPublishedAt());
    }

    @DisplayName("생성된 책을 삭제할 수 있다.")
    @Test
    void test3() {
        BookCommand.Create command = new BookCommand.Create("인스타브레인", "안데르스 한센", "9791190382174", "인플루엔셜", LocalDate.of(2021, 7, 21));
        BookResponse bookResponse = 책_생성(command);
        책_삭제(bookResponse.getId());
        List<BookResponse> responses = 책_목록_조회();
        assertEquals(0, responses.size());
    }

}

