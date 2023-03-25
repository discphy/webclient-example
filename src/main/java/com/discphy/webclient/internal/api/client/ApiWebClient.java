package com.discphy.webclient.internal.api.client;

import com.discphy.webclient.internal.api.dto.ApiRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class ApiWebClient {

    private final WebClient webClient;

    public ApiWebClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8080") // (1) 외부 API Base URl
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON.toString()) // (2) DEFAULT HTTP 헤더
                .build();
    }

    public <T> void call(ApiRequestDto<T> request) {
        execute(request).subscribe(request.getCallback()); // (3)
    }

    private <T> Mono<T> execute(ApiRequestDto<T> request) {
        return webClient.method(request.getMethod())
                .uri(request.getUrl())
                .bodyValue(request.getBody())
                .retrieve() // (4)
                .bodyToMono(request.getReturnType()); // (5)
    }
}
