package com.friend.furry.service;

import com.friend.furry.model.Member;
import com.friend.furry.model.Product;
import com.friend.furry.model.Review;
import com.friend.furry.security.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    //상품 번호에 해당하는 리뷰를 전부 가져오기
    List<ReviewDTO> getList(Long pid);
    //리뷰 등록
    Long register(ReviewDTO reviewDTO);
    //리뷰 수정
    Long modify(ReviewDTO reviewDTO);
    //리뷰 삭제
    Long remove(Long rid);
    //DTO를 ENTITY로 변환해주는 메서드
    default Review dtoToEntity(ReviewDTO reviewDTO, Member member){
        Review review = Review.builder()
                .rid(reviewDTO.getRid())
                .rgrade(reviewDTO.getGrade())
                .rtext(reviewDTO.getText())
                .product(Product.builder().pid(reviewDTO.getPid()).build())
                .member(member)
                .build();
        return review;

    }
    //ENTITY를 DTO로 변환해주는 메서드
    default ReviewDTO entityToDTO(Review review){
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .rid(review.getRid())
                .pid(review.getProduct().getPid())
                .mid(review.getMember().getMid())
                .email(review.getMember().getEmail())
                .nickname(review.getMember().getName())
                .grade(review.getRgrade())
                .text(review.getRtext())
                .regDate(review.getRegDate())
                .modDate(review.getModDate()).build();
        return reviewDTO;
    }
}
