package com.lullaby.springlibrary.acceptance.book;

import com.lullaby.springlibrary.acceptance.RestAssuredRequestTemplate;
import com.lullaby.springlibrary.acceptance.auth.AuthenticationFixture;
import com.lullaby.springlibrary.application.book.dto.BookCommand;
import com.lullaby.springlibrary.application.book.dto.BookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookFixture {

    public static BookResponse 책_생성(BookCommand.Create command) {
        Response response = RestAssured.given()
                .body(command)
                .header("Authorization", "Bearer " + AuthenticationFixture.accessToken)
                .log().all()
                .post("/api/admin/books");
        assertEquals(200, response.getStatusCode());
        return response.as(BookResponse.class);
    }

    public static List<BookResponse> 책_목록_조회() {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + AuthenticationFixture.accessToken)
                .log().all()
                .get("/api/books");
        assertEquals(200, response.getStatusCode());
        return RestAssuredRequestTemplate.readAsList(response, BookResponse.class);
    }

    public static void 책_수정(Long id, BookCommand.Update command) {
        Response response = RestAssured.given()
                .body(command)
                .header("Authorization", "Bearer " + AuthenticationFixture.accessToken)
                .log().all()
                .put("/api/admin/books/{id}", id);
        assertEquals(200, response.getStatusCode());
    }

    public static void 책_삭제(Long id) {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + AuthenticationFixture.accessToken)
                .log().all()
                .delete("/api/admin/books/{id}", id);
        assertEquals(200, response.getStatusCode());
    }
}
