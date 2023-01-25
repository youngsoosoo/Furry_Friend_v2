package com.friend.furry.repository;

import com.friend.furry.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
        //mid를 매개변수로 받아서
        //social의 값이 false인 데이터를 전부 찾아오는 메서드
        //SQL
        //select * from club_member m, club_member_role_set s
        //where m.mid = s.mid and m.mid=? and social=false
        @Query("select m from Member m where m.mid = :mid and m.social = false")
        Optional<Member> getWithRoles(Long mid);
}

