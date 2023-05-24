package com.friend.furry.member.config;

import java.util.Arrays;

import com.friend.furry.member.repository.MemberRepository;
import com.friend.furry.member.security.CustomUserDetailService;
import com.friend.furry.member.security.filter.JwtAuthenticationFilter;
import com.friend.furry.member.security.filter.RefreshTokenFilter;
import com.friend.furry.member.security.filter.TokenCheckFilter;
import com.friend.furry.member.security.handler.Custom403Handler;
import com.friend.furry.member.security.handler.CustomSocialLoginSuccessHandler;
import com.friend.furry.member.security.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class CustomSecurityConfig{

    private final DataSource dataSource;
    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    //자동로그인이 가능하도록 하는 기능을 이용해 DB 생성 및 자동 로그인 기능 구현.
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    // CORS 설정을 위한 Bean을 생성
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration configuration = new CorsConfiguration();
        // 모든 요청에 설정
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        // 메서드 설정
        configuration.setAllowedMethods(Arrays.asList(
            "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // 헤더 설정
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization", "Cache-Control", "Content-Type"));
        //인증 설정
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private TokenCheckFilter tokenCheckFilter(JwtUtil jwtUtil) {
        return new TokenCheckFilter(jwtUtil);
    }

    // 소셜 로그인 성공 후 처리를 위한 핸들러
    private CustomSocialLoginSuccessHandler customSocialLoginSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder(), jwtUtil, memberRepository);
    }

    //403에러가 발생했을 때 이동시켜줄 페이지 설정
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new Custom403Handler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        /**
         * AuthenticationManagerBuilder
         * 인증 정보를 제공하는 userDetailsService와 비밀번호 인코딩을 위한 passwordEncoder를 설정하는 빌더 클래스
         */
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.authenticationManager(authenticationManager);

        /**
         * authenticationManager는 인증 처리를 위한 AuthenticationManager 객체를 의미
         * MemberDetailsService 클래스를 통해 유저 정보를 조회하고, 인증 처리를 수행
         * Spring Security를 사용할 경우 로그인 처리 로직을 직접 작성하지 않고,
         * Spring Security가 제공하는 로그인 기능을 사용
         */
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter("**/login");
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);

        /**
         * MemberLoginFilter의 setAuthenticationSuccessHandler() 메서드를 호출
         * LoginSuccessHandler 객체를 등록함으로써 로그인 성공 시 LoginSuccessHandler 클래스의 onAuthenticationSuccess() 메서드가 호출되도록 설정
         */
        CustomSocialLoginSuccessHandler loginSuccessHandler = new CustomSocialLoginSuccessHandler(passwordEncoder(), jwtUtil, memberRepository);
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(tokenCheckFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(new RefreshTokenFilter("**/login", jwtUtil), TokenCheckFilter.class);

        // 소셜 로그인 처리를 위한 설정
        http.oauth2Login().loginPage("/member/login").successHandler(customSocialLoginSuccessHandler()); // 소셜 로그인 성공 후 처리를 담당하는 핸들러
        http.formLogin().loginPage("/member/login").successHandler(customSocialLoginSuccessHandler());

        // 로그아웃
        http.logout().disable();

        http.csrf().disable();

        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });

        http.rememberMe().key("12345678").tokenRepository(persistentTokenRepository())
                .userDetailsService(customUserDetailService).tokenValiditySeconds(60*60*24*30);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        return http.build();
    }

    //정적 파일 요청은 동작하지 않도록 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources()
                .atCommonLocations());
    }

}
