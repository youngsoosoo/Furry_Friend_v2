package com.friend.furry.repository;

import com.friend.furry.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
