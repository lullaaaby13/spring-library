package com.lullaby.springlibrary.acceptance;


import com.lullaby.springlibrary.utils.DatabaseCleanUp;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.lullaby.springlibrary.acceptance.auth.AuthenticationFixture.사용자_생성_및_로그인;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.25")
            .withDatabaseName("library")
            .withUsername("root")
            .withPassword("root");


    @Autowired
    DatabaseCleanUp databaseCleanUp;

    @LocalServerPort
    private int port;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
    }

    @BeforeAll
    static void setUpAll() {
        mysqlContainer.start();
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.requestSpecification = RestAssured
                .given()
                .header("Content-Type", "application/json");

        databaseCleanUp.execute();
        사용자_생성_및_로그인("guest", "guest", "guest");
    }

}
