package com.friend.furry.service;

import com.friend.furry.model.Member;
import com.friend.furry.model.Product;
import com.friend.furry.model.ProductImage;
import com.friend.furry.security.dto.PageRequestDTO;
import com.friend.furry.security.dto.PageResponseDTO;
import com.friend.furry.security.dto.ProductDTO;
import com.friend.furry.security.dto.ProductImageDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ProductService {
    //데이터 삽입을 위한 메서드
    Long register(ProductDTO productDTO);

    //데이터 목록을 위한 메서드
    PageResponseDTO<ProductDTO, Object []> getList(PageRequestDTO requestDTO);

    ProductDTO getProduct(Long pid);

    //DTO를 Entity로 변환
    //하나의 Entity가 아니라 Movie와 MovieImage로 변환이 되어야 해서
    //Map으로 리턴
    default Map<String, Object> dtoToEntity(ProductDTO productDTO, Member member){
        Map<String, Object> entityMap = new HashMap<>();

        Product product = Product.builder()
                .pcategory(productDTO.getPcategory())
                .pexplain(productDTO.getPexplain())
                .pname(productDTO.getPname())
                .pprice(productDTO.getPprice())
                .pdiscount(productDTO.getPdiscount())
                .member(member)
                .del(productDTO.isDel())
                .build();
        entityMap.put("product", product);

        //MovieImageDTO의 List
        List<ProductImageDTO> imageDTOList = productDTO.getImageDTOList();

        //MOvieImageDTO의 List를 MovieImage Entity의 List로 변환
        if(imageDTOList != null && imageDTOList.size() > 0){
            List<ProductImage> productImageList = imageDTOList.stream().map(productImageDTO -> {
                ProductImage productImage = ProductImage.builder()
                        .path(productImageDTO.getPath())
                        .imgName(productImageDTO.getImgName())
                        .uuid(productImageDTO.getUuid())
                        .product(product)
                        .build();
                return productImage;
            }).collect(Collectors.toList());
            entityMap.put("imgList",productImageList);
        }

        return entityMap;
    }

    //검색 결과를 DTO로 변환해주는 메서드
    default ProductDTO entitiesToDTO(Product product, List<ProductImage> productImages, double avg, Long reviewCnt){
        ProductDTO productDTO = ProductDTO.builder()
                .pid(product.getPid())
                .pcategory(product.getPcategory())
                .pexplain(product.getPexplain())
                .pname(product.getPname())
                .pprice(product.getPprice())
                .pdiscount(product.getPdiscount())
                .del(product.isDel())
                .regDate(product.getRegDate())
                .modDate(product.getModDate())
                .build();
        List<ProductImageDTO> productImageDTOList = productImages.stream().map(productImage -> {
            return ProductImageDTO.builder()
                    .imgName(productImage.getImgName())
                    .path(productImage.getPath())
                    .uuid(productImage.getUuid())
                    .build();
        }).collect(Collectors.toList());
        productDTO.setImageDTOList(productImageDTOList);
        productDTO.setAvg(avg);
        productDTO.setReviewCnt(reviewCnt);

        return productDTO;
    }
}
