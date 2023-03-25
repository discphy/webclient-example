package com.discphy.webclient.internal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemCallRequestDto {

    private String name;
    private Integer price;
}
