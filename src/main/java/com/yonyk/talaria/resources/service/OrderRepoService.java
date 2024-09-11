package com.yonyk.talaria.resources.service;

import java.time.Instant;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.yonyk.talaria.resources.controller.request.OrderDTO;
import com.yonyk.talaria.resources.controller.request.OrderStatusDTO;
import com.yonyk.talaria.resources.entity.Order;
import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.entity.enums.OrderStatusType;
import com.yonyk.talaria.resources.exception.CustomException;
import com.yonyk.talaria.resources.exception.enums.OrderExceptionType;
import com.yonyk.talaria.resources.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderRepoService {

  private final OrderRepository orderRepository;

  // 주문 저장
  public void saveOrder(Order order) {
    orderRepository.save(order);
  }

  // 주문생성
  public Order createOrder(String memberName, OrderDTO orderDTO, Product product) {
    // 주문 타입(구매, 판매)
    String orderType = orderDTO.orderType().toString();
    // 수량 * 제품 가격인 총 가격
    long totalPrice = orderDTO.quantity() * product.getPrice();

    return Order.builder()
        .orderNumber(createOrderNumber(orderType))
        .orderType(orderDTO.orderType())
        .orderStatusType(OrderStatusType.ORDER_COMPLETED)
        .memberName(memberName)
        .product(product)
        .quantity(orderDTO.quantity())
        .totalPrice(totalPrice)
        .address(orderDTO.address())
        .build();
  }

  // 주문번호 생성
  private String createOrderNumber(String orderType) {
    // 랜덤 6숫자
    Random random = new Random();
    int randomInt = random.nextInt(999999) + 100000;
    // 현재 시간
    long timestamp = Instant.now().toEpochMilli();

    return orderType + "_" + timestamp + "_" + randomInt;
  }

  // 주문 id로 Order 가져오기
  public Order getOrder(String memberName, long orderId) {
    Order findOrder = orderRepository.findByOrderIdAndMemberName(orderId, memberName);
    if (findOrder == null) {
      throw new CustomException(OrderExceptionType.ORDER_NOT_FOUND);
    }
    return findOrder;
  }

  // 사용자가 작성한 주문이 존재하는지 확인
  public void isExist(String memberName, long orderId) {
    boolean isExist = orderRepository.existsByOrderIdAndMemberName(orderId, memberName);
    if (!isExist) throw new CustomException(OrderExceptionType.ORDER_NOT_FOUND);
  }

  // 주문 상태 변경이 가능한 상태인지 확인
  public void canChangeStatus(Order order) {
    // 주문 상태가 주문취소, 수령완료, 발송완료일 경우 예외 발생
    if (order.getOrderStatusType().equals(OrderStatusType.CANCLE_ORDER)
        || order.getOrderStatusType().equals(OrderStatusType.REEIVED)
        || order.getOrderStatusType().equals(OrderStatusType.SHIPPED)) {
      throw new CustomException(OrderExceptionType.STATUS_CHANGE_NOT_ALLOWED);
    }
  }

  // 주문 상태 수정
  public void updateOrderStatus(OrderStatusDTO orderStatusDTO) {
    orderRepository.updateOrderStatus(orderStatusDTO.orderId(), orderStatusDTO.orderStatusType());
  }
}
