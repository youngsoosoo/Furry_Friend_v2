package com.friend.furry.member.security.util;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.friend.furry.member.entity.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtUtil {

    // 안전한 256비트 이상의 키 생성
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 키를 문자열로 변환하여 저장 또는 사용할 수 있습니다.
    private final String key = Base64.getEncoder().encodeToString(secretKey.getEncoded());

    public String generateToken(Map<String, Object> valueMap, int minutes, Long memberId) {

        // Header Part
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        // Payload Part Setting
        Map<String, Object> payloads = new HashMap<>();

        payloads.putAll(valueMap);
        payloads.put("memberId", memberId);

        int time = 30 * minutes; // 30분

        String jwtStr = Jwts.builder()
            .setHeader(headers)
            .setClaims(payloads)
            .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
            .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()))
            .signWith(SignatureAlgorithm.HS256, key.getBytes())
            .compact();
        return jwtStr;
    }

    // 토큰의 유효성 검사 메서드
    public Map<String, Object> validateToken(String token)throws JwtException {

        Map<String, Object> claim = null;
        log.warn(token);
        claim = Jwts.parser()
            .setSigningKey(key.getBytes()) // Set Key
            .parseClaimsJws(token) // 파싱 및 검증, 실패시 에러
            .getBody();
        return claim;
    }

    // 토큰에서 ID를 출력하는 메서드
    public Long extractUserId(String token) {
        try {
            Map<String, Object> claim = validateToken(token);
            // 유효한 토큰이면 클레임을 가져와서 필요한 작업을 수행합니다.
            // 클레임은 Map<String, Object> 형식으로 반환됩니다.
            // memberId를 이용하여 필요한 작업 수행
            Integer memberIdInteger = (Integer) claim.get("memberId");

            return memberIdInteger.longValue();
            // 유효성 검사 및 클레임 활용 후의 추가 작업
        } catch (JwtException e) {
            // 토큰이 유효하지 않은 경우 예외 처리
            // 예외 처리에 맞게 적절히 대응합니다.
            log.error("JwtException:" + e);
            return null;
        }
    }

    // // 토큰을 출력하는 메서드
    // public String extractTokenFromHeader(HttpServletRequest request) {
    //     //토큰 출력
    //     return request.getHeader("Authorization");
    // }

}