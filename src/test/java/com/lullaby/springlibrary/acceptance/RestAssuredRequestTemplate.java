package com.lullaby.springlibrary.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class RestAssuredRequestTemplate {

    public static Response put(String path, Object body, String accessToken) {
        return RestAssured.given()
                .body(body)
                .header("Authorization", "Bearer " + accessToken)
                .log().all()
                .put(path);
    }

    public static Response delete(String path, String accessToken) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .log().all()
                .delete(path);
    }

    public static <T> List<T> readAsList(Response response, Class<T> genericType) {
        return response.jsonPath().getList(".", genericType);
    }

}
