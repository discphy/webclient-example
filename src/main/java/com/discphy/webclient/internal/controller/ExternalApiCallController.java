package com.discphy.webclient.internal.controller;

import com.discphy.webclient.internal.service.ExternalApiCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external/api/call")
public class ExternalApiCallController {

    private final ExternalApiCallService callService;

    @GetMapping("/create-item/count/{count}") // {count}는 비동기로 호출할 개수를 말한다.
    public String createItemOne(@PathVariable int count) {
        callService.execute(count);
        return "OK";
    }
}
