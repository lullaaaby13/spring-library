package com.lullaby.springlibrary.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ValidationUtils {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, message);
        }
    }

    public static void notBlank(String string, String message) {
        if (string == null || string.isBlank()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, message);
        }
    }

    public static void longgerThan(String string, int length, String message) {
        if (string == null || string.length() < length) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, message);
        }
    }

    public static void shorterThan(String string, int length, String message) {
        if (string == null || string.length() > length) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, message);
        }
    }

    public static void isEmail(String email, String message) {
        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, message);
        }
    }

}
