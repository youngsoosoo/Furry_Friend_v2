package com.friend.furry.comment.entity;

import lombok.*;

import javax.persistence.*;

import com.friend.furry.entity.BaseEntity;
import com.friend.furry.member.entity.Member;
import com.friend.furry.product.entity.Product;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"product", "member"})
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int rgrade;
    private String rtext;

    public void changeGrade(int grade){
        this.rgrade = grade;
    }
    public void changeText(String text) {
        this.rtext = text;
    }
}
