package com.friend.furry.security.handler;

import com.friend.furry.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
//스프링 시큐리티 중 로그인 성공을 하고 이후의 작업을 커스터 마이징한 클래스
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("----------------------------------------------------------");
        log.info("CustomLoginSuccessHandler onAuthenticationSuccess ..........");
        log.info(authentication.getPrincipal());
        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO)
                authentication.getPrincipal();
        String encodedPw = memberSecurityDTO.getMpw();
        //소셜로그인이고 회원의 패스워드가 1111이라면
        if (memberSecurityDTO.isSocial()
                && (encodedPw.equals("1111")
                || passwordEncoder.matches("1111", encodedPw)
        )) {
            log.info("Should Change Password");
            log.info("Redirect to Member Modify ");
            //비밀번호 수정 페이지로 리다이렉트
            response.sendRedirect("/member/modify");
        } else {
            response.sendRedirect("/product/list");
        }
    }
}
