package com.friend.furry.service;

import com.friend.furry.model.Product;
import com.friend.furry.model.ProductImage;
import com.friend.furry.repository.MemberRepository;
import com.friend.furry.repository.ProductImageRepository;
import com.friend.furry.repository.ProductRepository;
import com.friend.furry.security.dto.PageRequestDTO;
import com.friend.furry.security.dto.PageResponseDTO;
import com.friend.furry.security.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final MemberRepository memberRepository;

    @Override
    public Long register(ProductDTO productDTO) {
        log.warn("productDTO:" + productDTO);

        Map<String, Object> entityMap = dtoToEntity(productDTO, memberRepository.findByMid(productDTO.getMid()));
        //상품과 상품 이미지 정보 찾아오기
        Product product = (Product) entityMap.get("product");
        List<ProductImage> productImageList = (List<ProductImage>) entityMap.get("imgList");
        productRepository.save(product);
        productImageRepository.saveAll(productImageList);
        return product.getPid();
    }

    @Override
    public PageResponseDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("pid").descending());
        //데이터베이스에 요청
        Page<Object []> result = productRepository.getList(pageable);
        //Object 배열을 ProductDTO 타입으로 변경하기 위해서
        //함수를 생성
        //첫 번째 데이터가 Product
        //두 번째 데이터가 List<ProductImage>
        //세 번째 데이터가 평점의 평균
        //네 번째 데이터가 리뷰의 개수
        Function<Object [], ProductDTO> fn = (arr -> entitiesToDTO((Product) arr[0],
                (List<ProductImage>) (Arrays.asList((ProductImage)arr[1])), (Double) arr[2], (Long) arr[3]));
        System.out.println(fn);
        return new PageResponseDTO<>(result, fn);
    }

    @Override
    public ProductDTO getProduct(Long pid) {
        //데이터 베이스에서 결과 가져오기
        List<Object []> result = productRepository.getProductWithAll(pid);
        Product product = (Product) result.get(0)[0];
        System.out.println(result);
        List<ProductImage> productImageList = new ArrayList<>();
        result.forEach(arr -> {
            ProductImage productImage = (ProductImage) arr[1];
            productImageList.add(productImage);
        });
        double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long)result.get(0)[3];

        return entitiesToDTO(product, productImageList, avg, reviewCnt);
    }
}
