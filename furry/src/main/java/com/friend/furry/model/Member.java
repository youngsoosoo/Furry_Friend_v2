package com.friend.furry.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    //권한 -여러 개의 권한을 소유
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String mpw){
        this.mpw = mpw;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void changeDel(boolean del){
        this.del = del;
    }

    //권한 추가
    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }
    //권한 삭제
    public void clearRoles(){
        this.roleSet.clear();
    }

    public void changeSocial(boolean social){
        this.social = social;
    }
}
