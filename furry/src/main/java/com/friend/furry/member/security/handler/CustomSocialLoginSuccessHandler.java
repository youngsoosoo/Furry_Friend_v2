package com.friend.furry.member.security.handler;

import com.friend.furry.member.dto.MemberSecurityDTO;
import com.friend.furry.member.entity.Member;
import com.friend.furry.member.repository.MemberRepository;
import com.friend.furry.member.security.util.JwtUtil;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
//스프링 시큐리티 중 로그인 성공을 하고 이후의 작업을 커스터 마이징한 클래스
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    /**
     * HttpServletRequest 객체 - 로그인 요청 정보를 담고 있음
     * HttpServletResponse 객체 - 로그인 응답 정보를 담고 있음
     * Authentication 객체 - 인증에 성공한 사용자 정보를 담고 있음.
     */

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {

        /**
         * onAuthenticationSuccess() 메소드에서 response.setContentType()을 통해
         * 로그인 성공 시 전송되는 응답 정보의 ContentType을 JSON으로 설정
         */
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        log.info("💡 authentication =====> " + authentication);
        log.info("💡 LOGINID =====> " + authentication.getName());

        // Member 엔티티를 조회합니다. 로그인한 사용자의 ID를 기준으로 조회합니다.
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("등록된 아이디가 없습니다."));

        Map<String, Object> claim = Map.of("loginId", authentication.getName());
        // AccessToken 유효기간 30분
        String accessToken = jwtUtil.generateToken(claim, 30, member.getMid());
        // RefreshToken 유효기간 7일
        String refreshToken = jwtUtil.generateToken(claim, 7 * 24 * 60, member.getMid());

        log.warn("accessToken" + accessToken);
        log.warn("refreshToken" + refreshToken);

        // Member 엔티티에 RefreshToken 값을 저장합니다.
        member.setRefreshToken(refreshToken);
        memberRepository.save(member);

        // response.setHeader("Authorization", accessToken);
        
        // 엑세스 토큰을 쿠키에 저장
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setMaxAge(7*24*60); // 쿠키의 만료 시간 설정
        accessTokenCookie.setPath("/"); // 쿠키의 유효 경로 설정 (옵션)
        response.addCookie(accessTokenCookie);

        // JSON으로 클라이언트에 보낼때 사용
        // Gson gson = new Gson();
        //
        // Map<String, String> keyMap = Map.of(
        //     "accessToken", accessToken,
        //     "refreshToken", refreshToken,
        //     "memberId", String.valueOf(member.getMid()),
        //     "email", member.getEmail(),
        //     "nickname", member.getName()
        // );
        //
        // String jsonStr = gson.toJson(keyMap);
        // response.getWriter().println(jsonStr);
        
        response.sendRedirect("/product/list");
    }
}
