package com.discphy.webclient.internal.service;

import com.discphy.webclient.internal.api.client.ApiWebClient;
import com.discphy.webclient.internal.api.dto.ApiRequestDto;
import com.discphy.webclient.internal.dto.ItemCallRequestDto;
import com.discphy.webclient.internal.dto.ItemCallResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

import static org.springframework.http.HttpMethod.POST;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalApiCallService {

    private final ApiWebClient webClient;

    public void execute(int count) {
        validate(count); // count 검증

        getTargets(count).boxed()
                .map(this::createRequest) // API 호출에 사용할 요청 dto 세팅
                .forEach(webClient::call); // API 호출
    }

    private void validate(int count) {
        if (count < 1) throw new IllegalArgumentException("Greater than 1");
    }

    private ApiRequestDto<ItemCallResponseDto> createRequest(Integer index) {
        return ApiRequestDto.<ItemCallResponseDto>builder()
                .returnType(ItemCallResponseDto.class) // 응답 바디 타입 지정
                .url("/external/api/v1/item") // 외부 가상 API URL
                .method(POST) // 외부 가상 API 메소드
                .body(createBody(index)) // 요청 바디 생성
                .callback(this::callback)
                .build();
    }

    private void callback(ItemCallResponseDto response) {
        log.info("Success create item : {}", response); // 결과값 확인 위해 로그
    }

    private ItemCallRequestDto createBody(Integer index) { // 요청 바디 생성
        return ItemCallRequestDto.builder()
                .name("product" + index)
                .price(index * 10000)
                .build();
    }

    private IntStream getTargets(int count) {
        return IntStream.range(1, count + 1); // 1부터 파라미터로 들어온 count만큼 API 호출 하는 프로세스
    }
}
