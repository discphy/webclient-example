package com.discphy.webclient.internal.api.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.function.Consumer;

@Getter
public class ApiRequestDto<T> {

    private final String url; // API 호출 uri
    private final Object body; // API 요청 바디
    private final HttpMethod method; // API 요청 메소드

    private final Class<T> returnType; // API 응답 클래스 타입
    private final Consumer<T> callback; // 비동기로 인한 응답 후 콜백 처리

    @Builder
    public ApiRequestDto(String url, Object body, HttpMethod method, Class<T> returnType, Consumer<T> callback) {
        this.url = url;
        this.body = body;
        this.method = method;
        this.returnType = returnType;
        this.callback = callback;
    }
}
