package com.friend.furry.repository;

import com.friend.furry.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //상품의 데이터를 입력하고 받아오는 메서드

    @Query("select p, pi, avg(coalesce(r.rgrade, 0)), count(r) from Product p left outer join ProductImage pi on pi.product = p" +
            " left outer join Review r on r.product = p where p.pid = :pid group by pi")
    List<Object []> getProductWithAll(@Param("pid") Long pid);
}
