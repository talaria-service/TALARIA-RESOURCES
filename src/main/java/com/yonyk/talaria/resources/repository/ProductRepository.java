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

  // 제품 조회(목록) 페이지네이션 적용
  Page<Product> findByProductType(Pageable pageable, ProductType productType);

  // 수량 체크
  @Query(
      "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END "
          + "FROM Product p WHERE p.productId = :productId AND p.quantity >= :quantity")
  boolean isStockAvailable(@Param("productId") long productId, @Param("quantity") int quantity);

  // 수량 추가
  @Modifying
  @Query("UPDATE Product p SET p.quantity = p.quantity + :quantity WHERE p.productId = :productId")
  void increaseQunatity(@Param("productId") long productId, @Param("quantity") int quantity);

  // 수량 감소
  @Modifying
  @Query("UPDATE Product p SET p.quantity = p.quantity - :quantity WHERE p.productId = :productId")
  void decreaseQunatity(@Param("productId") long productId, @Param("quantity") int quantity);
}
