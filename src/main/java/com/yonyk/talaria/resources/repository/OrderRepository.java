package com.yonyk.talaria.resources.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yonyk.talaria.resources.entity.Order;
import com.yonyk.talaria.resources.entity.enums.OrderType;

public interface OrderRepository extends JpaRepository<Order, Long> {

  // 사용자가 작성한 order 엔티티를 리턴
  Order findByOrderIdAndMemberName(Long orderId, String memberName);

  // 페이지네이션을 이용해 특정 OrderStatusType 의 order List 가져오기
  @Query(
      "SELECT o FROM Order o WHERE o.memberName = :memberName "
          + "AND o.orderType = :orderType "
          + "AND DATE(o.createdAt) = :date")
  Page<Order> findOrderList(
      Pageable pageable,
      @Param("memberName") String memberName,
      @Param("orderType") OrderType orderType,
      @Param("date") LocalDate date);
}
