package com.friend.furry;

import com.friend.furry.model.*;
import com.friend.furry.repository.MemberRepository;
import com.friend.furry.repository.ProductImageRepository;
import com.friend.furry.repository.ProductRepository;
import com.friend.furry.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

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
    
    @Test
    public void readproduct() {
        List<Object[]> list = productRepository.getProductWithAll(10L);
        for(Object [] ar: list){
            System.out.println(Arrays.toString(ar));
        }
    }

    @Test
    public void ProductfindAll(){
        List<Product> products = productRepository.findAll();
        for(Product ar : products){
            System.out.println(ar);
        }
    }
    @Test
    //JOIN 연습
    public void joinTest(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "pid"));
        Page<Object []> result = productRepository.getList(pageable);

        for(Object [] objects:result.getContent()){
            System.out.println(Arrays.toString(objects));
        }
    }

    @Autowired
    private ReviewRepository reviewRepository;



    @Test
    public void insertReview(){
        IntStream.rangeClosed(1, 20).forEach(i -> {
            //상품 번호
            Long pid = (long)(Math.random() * 10) + 1;
            //회원번호
            Long mid = (long)(Math.random() * 3) + 1;

            Product product = Product.builder()
                    .pid(pid)
                    .build();
            Member member = Member.builder()
                    .mid(mid)
                    .build();
            Review review = Review.builder()
                    .product(product)
                    .member(member)
                    .rgrade((int)(Math.random()*5)+1)
                    .rtext("상품 리뷰..." + i)
                    .build();
            reviewRepository.save(review);
        });
    }

    @Test
    @Transactional
    public void getReviews(){
        Product product =Product.builder()
                .pid(10L).build();
        List<Review> result = reviewRepository.findByProduct(product);
        result.forEach(review -> {
            System.out.println(review.getRid());
            System.out.println(review.getMember().getEmail());
        });
    }
}
