package com.friend.furry.basket.dto;

import java.util.ArrayList;
import java.util.List;

import com.friend.furry.product.dto.ProductDTO;
import com.friend.furry.product.dto.ProductImageDTO;
import com.friend.furry.product.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BasketResponseDTO {

    private Long bid;
    private ProductDTO productDTO;
    private Long mid;
}
