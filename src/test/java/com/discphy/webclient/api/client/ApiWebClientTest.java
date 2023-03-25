package com.discphy.webclient.api.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureWebClient
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApiWebClientTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void helloWebClient() {
        webTestClient.method(HttpMethod.GET)
                .uri("/hello")
                .exchange()
                .expectStatus().isOk() // 응답 코드 기대값
                .expectBody(String.class) // 응답 body 클래스 타입 기대값
                .value(response -> { // 응답 바디 response
                    System.out.println("response = " + response);
                    assertThat(response).isEqualToIgnoringCase("Hello World");
                });
    }
}