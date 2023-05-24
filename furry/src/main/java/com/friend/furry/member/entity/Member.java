package com.friend.furry.member.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.friend.furry.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    private String mpw;

    //위의 아이디는 고유 번호이고 email을 아이디로 쓸 예정
    private String email;

    private String name;

    private String address;

    private String phone;
    
    //삭제 여부
    private boolean del;

    //로그인 정보
    private boolean social;

    private String refreshToken;

    //권한 -여러 개의 권한을 소유
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String mpw){
        this.mpw = mpw;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    //권한 추가
    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }
}
