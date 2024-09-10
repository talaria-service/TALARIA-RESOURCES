package com.yonyk.talaria.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonyk.talaria.resources.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Order findByOrderIdAndMemberName(Long orderId, String memberName);

  boolean existsByOrderNumber(String orderNumber);
}
