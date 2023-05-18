package com.friend.furry.comment.service;

import com.friend.furry.member.entity.Member;
import com.friend.furry.product.entity.Product;
import com.friend.furry.comment.entity.Comment;
import com.friend.furry.member.repository.MemberRepository;
import com.friend.furry.comment.repository.CommentRepository;
import com.friend.furry.comment.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    private final MemberRepository memberRepository;

    @Override
    public List<CommentDTO> getList(Long pid) {
        Product product = Product.builder().pid(pid).build();
        List<Comment> result = commentRepository.findByProduct(product);
        return result.stream().map(comment -> entityToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public Long register(CommentDTO commentDTO) {
        Member member = memberRepository.findByMid(commentDTO.getMid());
        Comment comment = dtoToEntity(commentDTO, member);
        commentRepository.save(comment);
        return comment.getRid();
    }

    //수정과 삽입은 동일하다.
    @Override
    public Long modify(CommentDTO commentDTO) {
        Member member = memberRepository.findByMid(commentDTO.getMid());
        Comment comment = dtoToEntity(commentDTO, member);
        commentRepository.save(comment);
        return comment.getRid();
    }

    @Override
    public Long remove(Long rnum) {
        commentRepository.deleteById(rnum);
        return rnum;
    }
}