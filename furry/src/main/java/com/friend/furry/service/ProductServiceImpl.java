package com.friend.furry.service;

import com.friend.furry.model.Product;
import com.friend.furry.model.ProductImage;
import com.friend.furry.repository.ProductImageRepository;
import com.friend.furry.repository.ProductRepository;
import com.friend.furry.security.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    @Override
    public Long register(ProductDTO productDTO) {
        log.warn("productDTO:" + productDTO);

        Map<String, Object> entityMap = dtoToEntity(productDTO);
        //상품과 상품 이미지 정보 찾아오기
        Product product = (Product) entityMap.get("product");
        List<ProductImage> movieImageList = (List<ProductImage>) entityMap.get("imgList");
        productRepository.save(product);
        movieImageList.forEach(productImage -> {
            productImageRepository.save(productImage);
        });
        return product.getPid();
    }

    @Override
    public ProductDTO getProduct(Long pid) {
        //데이터 베이스에서 결과 가져오기
        List<Object []> result = productRepository.getProductWithAll(pid);
        Product product = (Product) result.get(0)[0];

        List<ProductImage> productImageList = new ArrayList<>();
        result.forEach(arr -> {
            ProductImage productImage = (ProductImage) arr[1];
            productImageList.add(productImage);
        });
        System.out.println(Arrays.toString(result.get(2)));//#??????????????
        double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long)result.get(0)[3];

        return entitiesToDTO(product, productImageList, avg, reviewCnt);
    }
}
