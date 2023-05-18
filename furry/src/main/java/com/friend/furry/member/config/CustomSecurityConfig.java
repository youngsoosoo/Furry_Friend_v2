package com.friend.furry.member.config;

import com.friend.furry.member.security.CustomUserDetailService;
import com.friend.furry.member.security.handler.Custom403Handler;
import com.friend.furry.member.security.handler.CustomSocialLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig{

    private final DataSource dataSource;
    private final CustomUserDetailService userDetailService;

    //자동로그인이 가능하도록 하는 기능을 이용해 DB 생성 및 자동 로그인 기능 구현.
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    //403에러가 발생했을 때 이동시켜줄 페이지 설정
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new Custom403Handler();
    }

    //소셜 로그인 후 처리를 위한 클래스 설정
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        log.info("필터 환경 설정");
        //인증이나 인가에 문제가 발생하면 로그인 폼 출력
        http.formLogin().loginPage("/member/login");
        //Oauth2가 사용할 로그인 성공 후 처리
        http.oauth2Login().loginPage("/member/login").successHandler(authenticationSuccessHandler());
        http.csrf().disable();

        http.rememberMe().key("12345678").tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailService).tokenValiditySeconds(60*60*24*30);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //정적 파일 요청은 동작하지 않도록 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources()
                .atCommonLocations());
    }

}
