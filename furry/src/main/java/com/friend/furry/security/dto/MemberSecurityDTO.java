package com.friend.furry.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
//로그인 처리 결과로 사용할 클래스
public class MemberSecurityDTO extends User implements OAuth2User {
    private String mpw;
    private String email;
    private String name;
    private String address;
    private String phone;
    private boolean del;
    private boolean social;


    public MemberSecurityDTO(String password, String username, String name, String address, String phone,
                             boolean del, boolean social, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        
        this.mpw = password;
        this.email = username;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.del = del;
        this.social = social;
    }

    //소셜 로그인을 위한 코드
    private Map<String, Object> props;

    @Override
    public Map<String, Object> getAttributes(){
        return this.getProps();
    }

    @Override
    public String getName(){
        return this.email;
    }
}