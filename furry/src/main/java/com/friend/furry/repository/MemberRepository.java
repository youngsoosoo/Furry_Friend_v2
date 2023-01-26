package com.friend.furry.repository;

import com.friend.furry.model.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
        //mid를 매개변수로 받아서
        //social의 값이 false인 데이터를 전부 찾아오는 메서드
        //SQL
        //select * from club_member m, club_member_role_set s
        //where m.mid = s.mid and m.mid=? and social=false
        @Query("select m from Member m where m.email = :email and m.social = false")
        Optional<Member> getWithRoles(String email);

        @EntityGraph(attributePaths = "roleSet", type = EntityGraph.EntityGraphType.LOAD)
        @Query("select m from Member m where m.email = :email")
        Optional<Member> findByEmail(@Param("email") String email);

        //이메일 중복확인
        boolean existsByEmail(@Param("email") String email);
}

