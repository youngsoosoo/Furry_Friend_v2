package com.friend.furry.basket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friend.furry.basket.entity.Basket;
import com.friend.furry.member.entity.Member;
import com.friend.furry.member.service.TokenService;
import com.friend.furry.product.dto.ProductDTO;
import com.friend.furry.product.dto.ProductImageDTO;
import com.friend.furry.product.entity.Product;
import com.friend.furry.basket.repository.BasketRepository;
import com.friend.furry.member.repository.MemberRepository;
import com.friend.furry.product.entity.ProductImage;
import com.friend.furry.product.repository.ProductRepository;
import com.friend.furry.basket.dto.BasketRequestDTO;
import com.friend.furry.basket.dto.BasketResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BasketService {

    private final BasketRepository basketRepository;

    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;

    private final TokenService tokenService;

    // 현재 사용자 찾기
    public Member findMember(Long mid){
        return memberRepository.findByMid(mid);
    }

    // 장바구니 읽어오기
    public BasketResponseDTO findBasketList(String token){
        try {
            Long mid = tokenService.usageToken(token);
            List<Object []> result = basketRepository.basketByMember(mid);

            Product product = (Product) result.get(0)[1];

            List<ProductImage> productImageList = new ArrayList<>();
            result.forEach(arr -> {
                ProductImage productImage = (ProductImage) arr[2];
                productImageList.add(productImage);
            });
            return entityToDTO((long) result.get(0)[0], product, productImageList, mid);
        }
        catch (Exception e){
            log.error(e);
            return null;
        }

    }

    // 장바구니 삭제하기
    @Transactional
    public void deleteBasketItem(BasketRequestDTO basketRequestDTO, String token){
        basketRepository.deleteBasketByBasket_id(basketRequestDTO.getBid(), tokenService.usageToken(token));
    }

    public void saveBasket(String pname, String token){
        Product product = productRepository.findProductByPname(pname);
        Member member = findMember(tokenService.usageToken(token));
        Basket basket = Basket.builder()
            .product(product)
            .member(member).build();
        basketRepository.save(basket);
    }

    // Entity를 DTO로 변경해주는 메서드
    public BasketResponseDTO entityToDTO(Long bid, Product product, List<ProductImage> productImageList, Long mid){

        List<ProductImageDTO> productImageDTOList = new ArrayList<>();
        productImageList.forEach(arr -> {
            ProductImageDTO productImageDTO = ProductImageDTO.builder()
                .imgName(arr.getImgName())
                .path(arr.getPath())
                .uuid(arr.getUuid())
                .build();
            productImageDTOList.add(productImageDTO);
        });

        ProductDTO productDTO = ProductDTO.builder()
            .pid(product.getPid())
            .pname(product.getPname())
            .imageDTOList(productImageDTOList)
            .build();

        return BasketResponseDTO.builder()
            .bid(bid)
            .productDTO(productDTO)
            .mid(mid)
            .build();
    }
}
