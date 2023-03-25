package com.discphy.webclient.external.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemResponseDto {

    private String name;
    private Integer price;
    private LocalDateTime createdDate;

    @Builder
    public ItemResponseDto(String name, Integer price) {
        this.name = name;
        this.price = price;
        this.createdDate = LocalDateTime.now();
    }

    public static ItemResponseDto of(ItemRequestDto request) {
        return ItemResponseDto.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }
}
