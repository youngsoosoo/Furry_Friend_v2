package com.friend.furry.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;       //상품 번호
    private String pcategory;//카테고리

    private String pname;   //상품명

    private String pexplain;//상품설명

    private Long pview;     //조회수

    private Long pprice;    //원가

    private Long pdiscount; //할인율

    private boolean del;    //판매여부
}
