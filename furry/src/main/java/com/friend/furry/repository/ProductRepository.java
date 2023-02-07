package com.friend.furry.repository;

import com.friend.furry.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //상품 정보를 가지고 상품 이미지 정보와 리뷰개수 및 grade의 평균을 구해주는 메서드 페이지 단위로 구하기
    @Query("select p, pi, avg(coalesce(r.rgrade, 0)), count(distinct r) from Product p left outer join ProductImage pi on pi.product = p" +
            " left outer join Review r on r.product = p group by p")
    Page<Object []> getList(Pageable pageable);

    @Query("select p from Product p")
    List<Product> findAll();

    //영화 상세 보기를 위해서 특정 영화 아이디를 이용해서 위와 동일한 데이터를 찾아오는 쿼리
    @Query("select p, pi, avg(coalesce(r.rgrade, 0)), count(r) from Product p left outer join ProductImage pi on pi.product = p" +
            " left outer join Review r on r.product = p where p.pid = :pid group by pi")
    List<Object []> getProductWithAll(@Param("pid") Long pid);
}
