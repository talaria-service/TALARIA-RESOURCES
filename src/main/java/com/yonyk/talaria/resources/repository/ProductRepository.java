package com.yonyk.talaria.resources.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.entity.enums.ProductType;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Page<Product> findByProductType(Pageable pageable, ProductType productType);

  // 수량 추가
  @Modifying
  @Query("UPDATE Product p SET p.quantity = p.quantity + :quantity WHERE p.productId = :productId")
  void increaseQunatity(@Param("productId") long productId, @Param("quantity") int quantity);

  // 수량 감소
  @Modifying
  @Query("UPDATE Product p SET p.quantity = p.quantity - :quantity WHERE p.productId = :productId")
  void decreaseQunatity(@Param("productId") long productId, @Param("quantity") int quantity);
}
