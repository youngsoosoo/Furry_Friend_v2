package com.friend.furry.comment.service;

import com.friend.furry.member.entity.Member;
import com.friend.furry.product.entity.Product;
import com.friend.furry.comment.entity.Comment;
import com.friend.furry.comment.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    //상품 번호에 해당하는 리뷰를 전부 가져오기
    List<CommentDTO> getList(Long pid);
    //리뷰 등록
    Long register(CommentDTO commentDTO);
    //리뷰 수정
    Long modify(CommentDTO commentDTO);
    //리뷰 삭제
    Long remove(Long rid);
    //DTO를 ENTITY로 변환해주는 메서드
    default Comment dtoToEntity(CommentDTO commentDTO, Member member){
        Comment comment = Comment.builder()
                .rid(commentDTO.getRid())
                .rgrade(commentDTO.getGrade())
                .rtext(commentDTO.getText())
                .product(Product.builder().pid(commentDTO.getPid()).build())
                .member(member)
                .build();
        return comment;

    }
    //ENTITY를 DTO로 변환해주는 메서드
    default CommentDTO entityToDTO(Comment comment){
        CommentDTO commentDTO = CommentDTO.builder()
                .rid(comment.getRid())
                .pid(comment.getProduct().getPid())
                .mid(comment.getMember().getMid())
                .email(comment.getMember().getEmail())
                .nickname(comment.getMember().getName())
                .grade(comment.getRgrade())
                .text(comment.getRtext())
                .regDate(comment.getRegDate())
                .modDate(comment.getModDate()).build();
        return commentDTO;
    }
}
