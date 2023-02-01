package com.friend.furry.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long pid;       //상품 번호
    private String pcategory;//카테고리
    private String pname;   //상품명
    private String pexplain;//상품설명
    private Long pview;     //조회수
    private Long pprice;    //원가
    private Long pdiscount; //할인율
    private boolean del;    //판매여부

    //review의 grade 평균
    private double avg;
    //리뷰 개수
    private Long reviewCnt;
    //등록일과 수정
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    //builder()라는 메서드를 이용해서 생성할 때 기본으로 사용
    @Builder.Default
    private List<ProductImageDTO> imageDTOList = new ArrayList<>();
}
