package com.friend.furry.member.service;

import org.springframework.stereotype.Service;

import com.friend.furry.member.security.exception.AccessTokenException;
import com.friend.furry.member.security.util.JwtUtil;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class TokenService {

    private final JwtUtil jwtUtil;

    public Long usageToken(String token) {

        try {
            // JWT 토큰에서 사용자 아이디 추출하는 로직 작성
            return jwtUtil.extractUserId(token);
        }catch (JwtException e){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
        }

    }

    // private boolean isValidToken(String token) {
    //     // 토큰의 유효성을 검사하는 로직 작성
    //     // 서명 검증, 만료 일자 확인 등을 수행하여 유효한 토큰인지 확인
    //     // 반환값은 boolean 형식으로 유효 여부를 나타냄
    //     try {
    //         jwtUtil.validateToken(token);
    //         return true;
    //     }catch (MalformedJwtException malformedJwtException) {
    //         log.error("🚨 MalformedJwtException -------------------- 🚨");
    //         throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.MALFORM);
    //     }catch(SignatureException signatureException){
    //         log.error("🚨 SignatureException -------------------- 🚨");
    //         throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADSIGN);
    //     }catch(ExpiredJwtException expiredJwtException){
    //         log.error("🚨 ExpiredJwtException -------------------- 🚨");
    //         throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.EXPIRED);
    //     }
    //     // 필요에 따라 추가적인 검사 로직을 구현할 수 있습니다.
    // }
    //
    // private UserDetails getUserDetailsFromToken(String token) {
    //     // 토큰에서 사용자 정보를 추출하는 로직 작성
    //     // 토큰에서 클레임 정보를 읽어와 UserDetails 객체를 생성 또는 조회
    //     // 반환값은 UserDetails 객체
    //     // return jwtUtil.extractUserDetails(token);
    //     // 필요에 따라 토큰 디코딩, 클레임 파싱 등을 수행할 수 있습니다.
    // }
    //
    // private boolean isUserAuthenticated(UserDetails userDetails) {
    //     // 사용자 인증 여부를 확인하는 로직 작성
    //     // UserDetails 객체를 사용하여 인증 여부를 판단
    //     // 반환값은 boolean 형식으로 인증 여부를 나타냄
    //     // return userDetails.isAuthenticated();
    // }
    //
    // private boolean hasSufficientPermission(UserDetails userDetails) {
    //     // 사용자의 권한이 충분한지 검사하는 로직 작성
    //     // UserDetails 객체에서 권한 정보를 확인하여 필요한 권한이 있는지 판단
    //     // 반환값은 boolean 형식으로 권한 충분 여부를 나타냄
    //     // 예: return userDetails.getAuthorities().contains("ROLE_ADMIN");
    // }
    //
    // private void executeRestrictedAction(UserDetails userDetails) {
    //     // 허가된 작업을 수행하는 로직 작성
    //     // 사용자 정보를 활용하여 필요한 작업을 수행
    //     // 예: SomeService.doRestrictedAction(userDetails.getId());
    // }
}
