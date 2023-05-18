package com.friend.furry.product.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.friend.furry.entity.BaseEntity;
import com.friend.furry.member.entity.Member;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;       //상품 번호
    private String pcategory;//카테고리

    private String pname;   //상품명

    private String pexplain;//상품설명

    private Long pprice;    //원가

    private Long pdiscount; //할인율

    private boolean del;    //판매여부

    //하나의 Member가 여러 개의 Product를 소유
    //데이터를 불러올 떄 member를 불러오지 않고 사용할 때 불러옵니다.
    //외래키의 이름은 member_mid로 만들어집니다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
