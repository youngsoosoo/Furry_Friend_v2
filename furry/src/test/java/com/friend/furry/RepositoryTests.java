package com.friend.furry;

import com.friend.furry.model.Member;
import com.friend.furry.model.MemberRole;
import com.friend.furry.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class RepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //샘플 데이터 삽입
    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .mpw(passwordEncoder.encode("1111"))
                    .email("user" + i + "@gmail.com")
                    .name("사용자" + i)
                    .address("주소" + i)
                    .phone("010" + i)
                    .social(false)
                    .roleSet(new HashSet<MemberRole>())
                    .build();
            member.addRole(MemberRole.USER);
            if(i > 90){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    //mid를 이용해서 조회하는 메서드
    @Test
    @Transactional
    public void testRead(){
        Optional<Member> result = memberRepository.getWithRoles("user95@gmail.com");
        if(result.isPresent()){
            System.out.println(result);
            System.out.println(result.get().getRoleSet());
        }else{
            System.out.println("존재하지 않는 아이디");
        }
    }

    @Test
    public void testReadEmail(){
        Optional<Member> clubMember = memberRepository.findByEmail("user95@gmail.com");
        System.out.println(clubMember.get().getRoleSet());
    }

}
