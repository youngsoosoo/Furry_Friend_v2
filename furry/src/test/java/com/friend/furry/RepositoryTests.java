package com.friend.furry;

import com.friend.furry.model.Member;
import com.friend.furry.model.MemberRole;
import com.friend.furry.model.Product;
import com.friend.furry.model.ProductImage;
import com.friend.furry.repository.MemberRepository;
import com.friend.furry.repository.ProductImageRepository;
import com.friend.furry.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;
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
    public void testMemberRead(){
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

    @Test
    public void testUpdate(){
        String email = "lovepys01@naver.com";
        String mpw = passwordEncoder.encode("1234");
        memberRepository.updatePassword(mpw, email);
    }

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Test
    public void insertproduct(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Product product = Product.builder()
                    .pcategory("간식")
                    .pexplain("설명" + i)
                    .pname("이름" + i)
                    .pprice(1000L)
                    .pdiscount(10L)
                    .del(false)
                    .build();
            productRepository.save(product);

            int count = (int)(Math.random() * 5) + 1;
            for(int j=0; j<count; j++){
                ProductImage productImage =ProductImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .product(product)
                        .imgName("test" + j + ".jpg")
                        .build();
                productImageRepository.save(productImage);
            }
        });
    }

    

}
