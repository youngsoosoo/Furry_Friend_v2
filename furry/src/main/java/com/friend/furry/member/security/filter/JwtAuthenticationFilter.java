package com.friend.furry.member.security.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }
    private Map<String,String> parseRequestJSON(HttpServletRequest request) {

        // JSON 데이터를 분석해 loginId, loginPassword 전달 값을 Map으로 처리
        try (Reader reader = new InputStreamReader(request.getInputStream())) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Map.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Authentication attemptAuthentication (HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException, IOException {

        log.info("🛠️ MemberLoginFilter -------------------- 🛠️");

        if (request.getMethod().equalsIgnoreCase("GET")) {
            log.info("============ GET METHOD NOT SUPPORT ============");
            return null;
        }

        Map<String, String> jsonData = parseRequestJSON(request);

        log.info("💡 jsonData =====> " + jsonData);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            jsonData.get("loginId"), jsonData.get("loginPassword"));

        return getAuthenticationManager().authenticate(authenticationToken);
    }
}
