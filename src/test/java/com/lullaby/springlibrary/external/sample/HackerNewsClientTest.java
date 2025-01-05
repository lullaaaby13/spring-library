package com.lullaby.springlibrary.external.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HackerNewsClientTest {

    @Autowired
    HackerNewsClient hackerNewsClient;

    @Test
    void name() {
        List<Long> longs = hackerNewsClient.topStories();

    }
}
