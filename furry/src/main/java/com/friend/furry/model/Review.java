package com.friend.furry.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"product", "member"})
public class Review extends BaseEntity{
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
