package com.friend.furry;

import com.friend.furry.member.dto.MemberJoinDTO;
import com.friend.furry.member.service.MemberService;
import com.friend.furry.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    @Test
    public void testJoin() throws MemberService.MidExistException {
        MemberJoinDTO dto = MemberJoinDTO.builder()
                .mpw("1111")
                .email("soo@naver.com")
                .name("soo")
                .address("인천")
                .phone("01082488674").build();
        memberService.join(dto);
    }

    @Test
    public void getProduct(){
        System.out.println(productService.getProduct(10L));
    }

}
