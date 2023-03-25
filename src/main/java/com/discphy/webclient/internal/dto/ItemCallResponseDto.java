package com.discphy.webclient.internal.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemCallResponseDto {

    private String name;
    private Integer price;
    private LocalDateTime createdDate;
}
