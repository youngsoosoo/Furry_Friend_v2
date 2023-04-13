package com.friend.furry.controller;

import com.friend.furry.security.dto.ReviewDTO;
import com.friend.furry.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    private final HttpSession session;

    //상품 번호에 해당하는 댓글 목록을 처리
    @GetMapping("/{pid}/list")
    public ResponseEntity<List<ReviewDTO>> list(@PathVariable("pid") Long pid){
        List<ReviewDTO> result = reviewService.getList(pid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //댓글 추가
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{pid}")
    public ResponseEntity<Long> addReview(@PathVariable("pid") Long pid, @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setMid((Long)session.getAttribute("mid"));
        //@RequestBody는 json데이터를 받아서 ReviewDTO로 받고 이름이 일치하는 것끼리 매칭을 한다.
        Long result = reviewService.register(reviewDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    //댓글 수정
    @PutMapping("/{pid}/{rid}")
    public ResponseEntity<Long> addReview(@PathVariable("pid") Long pid, @PathVariable("rid") Long rid,
                                          @RequestBody ReviewDTO reviewDTO){
        reviewDTO.setMid((Long)session.getAttribute("mid"));
        Long result = reviewService.modify(reviewDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/{pid}/{rid}")
    public ResponseEntity<Long> addReview(@PathVariable("pid") Long pid, @PathVariable("rid") Long rid){
        Long result = reviewService.remove(rid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
