package com.discphy.webclient.external.controller;

import com.discphy.webclient.external.dto.ItemRequestDto;
import com.discphy.webclient.external.dto.ItemResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/external/api/v1/item")
public class ItemController {

    @PostMapping
    public ItemResponseDto create(@RequestBody ItemRequestDto request) {
        log.info("Call create item : {}", request); // 결과값 확인을 위한 로그
        return ItemResponseDto.of(request);
    }
}
