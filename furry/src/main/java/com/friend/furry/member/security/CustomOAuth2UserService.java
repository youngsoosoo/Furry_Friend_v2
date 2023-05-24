package com.friend.furry.member.security;

import com.friend.furry.member.entity.Member;
import com.friend.furry.member.entity.MemberRole;
import com.friend.furry.member.repository.MemberRepository;
import com.friend.furry.member.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    //카카오 로그인 성공 후 넘어오는 데이터를 이용해서 email과 name을 추출해서 리턴하는 메서드
    private String[] getKakaoEmailAndName(Map<String, Object> paramMap){
        //카카오 계정 정보가 있는 Map을 추출
        Object value = paramMap.get("kakao_account");
        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String)accountMap.get("email");

        value = accountMap.get("profile");
        accountMap = (LinkedHashMap) value;
        String name = (String) accountMap.get("nickname");
        String[] li = {email, name};
        log.info("카카오 계정 이메일: " + email);
        log.info("카카오 계정 이름: " + name);
        return li;
    }

    //email정보가 있으면 그에 해당하는 DTO를 리턴하고 없으면
    //회원가입하고 리턴하는 사용자 정의 메서드
    private MemberSecurityDTO generateDTO(String email, String name, Map<String, Object> params){
        //email을 가지고 데이터 찾아오기
        Optional<Member> result = memberRepository.findByEmail(email);
        if(result.isEmpty()){
        //email이 존재하지 않는 경우로 회원 가입
            Member member = Member.builder()
                    .mpw(passwordEncoder.encode("1111"))
                    .email(email)
                    .name(name)
                    .del(false)
                    .social(true)
                    .build();
            System.out.println("!!!" + member);
            member.addRole(MemberRole.USER);
            memberRepository.save(member);

            //회원가입에 성공한 정보 리턴
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO("1111", email, name, null, null, false, true, Arrays.asList(new SimpleGrantedAuthority(
                    "Role_USER"
            )));
            memberSecurityDTO.setProps(params);
            return memberSecurityDTO;
        }else{
            //email이 존재하는 경우
            Member member = result.get();
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                    member.getMpw(),
                    member.getEmail(),
                    member.getName(),
                    member.getAddress(),
                    member.getPhone(),
                    member.isDel(),
                    member.isSocial(),
                    member.getRoleSet().stream().map(memberRole ->
                            new SimpleGrantedAuthority("ROLE_" + memberRole.name())
                    ).collect(Collectors.toList()));
            return memberSecurityDTO;
        }
    }

    //로그인 성공했을 때 호출되는 메서드
    //이메일을 가진 사용자를 찾아보고 존재하지 않다면 자동으로 회원가입 시키는 메서드 반환 generateDTO
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        //로그인에 성공한 서비스의 정보가져오기
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();
        log.info("Service Name: " + clientName);

        //계정에 대한 정보 가져오기
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;
        String name = null;
        switch (clientName.toLowerCase()){
            case "kakao":
                String[] li = getKakaoEmailAndName(paramMap);
                email = li[0];
                name = li[1];
                break;
            case "google":
                email = (String)paramMap.get("email");
                break;
        }

        MemberSecurityDTO memberSecurityDTO = generateDTO(email, name, paramMap);

        return memberSecurityDTO;
    }
}
