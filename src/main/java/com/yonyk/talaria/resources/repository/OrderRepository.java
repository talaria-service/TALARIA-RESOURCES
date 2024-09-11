package com.yonyk.talaria.resources.repository;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yonyk.talaria.resources.entity.Order;
import com.yonyk.talaria.resources.entity.enums.OrderStatusType;

public interface OrderRepository extends JpaRepository<Order, Long> {

  // 사용자가 작성한 order 엔티티를 리턴
  Order findByOrderIdAndMemberName(Long orderId, String memberName);

  // 사용자가 작성한 order가 있는지 확인
  boolean existsByMemberNameAndOrderId(long orderId, String memberName);

  // 주문 변경이 가능한 상태인지 확인
  boolean canChangeStatus(long orderId);

  // 주문 상태 변경
  @Transactional
  @Modifying
  @Query("UPDATE Order o SET o.orderStatusType = :orderStatusType WHERE o.orderId = :orderId")
  int updateOrderStatus(
      @Param("orderId") Long orderId, @Param("orderStatusType") OrderStatusType orderStatusType);
}
