package com.friend.furry.service;

import com.friend.furry.model.Member;
import com.friend.furry.model.Product;
import com.friend.furry.model.Review;
import com.friend.furry.repository.MemberRepository;
import com.friend.furry.repository.ReviewRepository;
import com.friend.furry.security.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    @Override
    public List<ReviewDTO> getList(Long pid) {
        Product product = Product.builder().pid(pid).build();
        List<Review> result = reviewRepository.findByProduct(product);
        return result.stream().map(review -> entityToDTO(review)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        Member member = memberRepository.findByMid(reviewDTO.getMid());
        Review review = dtoToEntity(reviewDTO, member);
        reviewRepository.save(review);
        return review.getRid();
    }

    //수정과 삽입은 동일하다.
    @Override
    public Long modify(ReviewDTO reviewDTO) {
        Member member = memberRepository.findByMid(reviewDTO.getMid());
        Review review = dtoToEntity(reviewDTO, member);
        reviewRepository.save(review);
        return review.getRid();
    }

    @Override
    public Long remove(Long rnum) {
        reviewRepository.deleteById(rnum);
        return rnum;
    }
}