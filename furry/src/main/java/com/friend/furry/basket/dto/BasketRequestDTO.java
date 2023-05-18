package com.friend.furry.basket.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BasketRequestDTO {

    private Long bid;
    private Long pid;
}
