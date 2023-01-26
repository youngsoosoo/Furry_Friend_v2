package com.friend.furry.service;

import com.friend.furry.model.Member;
import com.friend.furry.model.MemberRole;
import com.friend.furry.repository.MemberRepository;
import com.friend.furry.security.dto.MemberJoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
//member관련 서비스를 재정의 해주는 클래스
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private Long sequence = 101L;
    @Override
    //회원가입
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {
        //이메일 중복 확인
        String email = memberJoinDTO.getEmail();

        boolean exist = memberRepository.existsByEmail(email);
        if(exist){
            throw new MidExistException();
        }

        //회원 가입을 위해서 입력 받은 정보를 가지고 Member Entity를 생성
        Member member = Member.builder()
                .mpw(memberJoinDTO.getMpw())
                .email(memberJoinDTO.getEmail())
                .name(memberJoinDTO.getName())
                .address(memberJoinDTO.getAddress())
                .phone(memberJoinDTO.getPhone())
                .del(memberJoinDTO.isDel())
                .social(memberJoinDTO.isSocial())
                .build();
        //비밀번호 암호화
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        //권한 설정
        member.addRole(MemberRole.USER);
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);
    }
}