package com.friend.furry.product.entity;

import lombok.*;

import javax.persistence.*;

import com.friend.furry.product.entity.Product;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "product")//toString을 할 때 product는 제외
//지연 생성이기 때문에 get을 하지 않은 상태에서 toString을 호출하면 NullPointerException이 발생

@Embeddable//부모 테이블을 만들 때 이 속성의 값을 포함시켜 생성
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    //파일 이름이 겹치지 않도록 하기 위해서 추가
    private String uuid;
    //파일 이름
    private String imgName;
    //하나의 디렉토리에 너무 많은 파일이 저장되지 않도록
    //업로드 한 날짜 별로 파일을 기록하기 위한 디렉토리 이름
    private String path;

    //하나의 Product가 여러 개의 ProductImage를 소유
    //데이터를 불러올 떄 product를 불러오지 않고 사용할 때 불러옵니다.
    //외래키의 이름은 product_pid로 만들어집니다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
