package com.yonyk.talaria.resources.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyk.talaria.resources.controller.request.OrderDTO;
import com.yonyk.talaria.resources.controller.response.OrderDetailDTO;
import com.yonyk.talaria.resources.entity.Order;
import com.yonyk.talaria.resources.exception.CustomException;
import com.yonyk.talaria.resources.exception.enums.OrderExceptionType;
import com.yonyk.talaria.resources.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderRepoService orderRepoService;

  // 주문 생성
  @Transactional
  public void creatOrder(String memberName, OrderDTO orderDTO) {
    // 수량 체크
    orderRepoService.checkQuantity(orderDTO);
    // 주문 생성
    Order order = orderRepoService.createOrder(memberName, orderDTO);
    // 제품의 수량 조절
    orderRepoService.manageQunatity(orderDTO.productId(), orderDTO.quantity(), false);
    // 주문 저장
    orderRepository.save(order);
  }

  // 주문 조회(단건)
  public OrderDetailDTO getOrderDetail(String memberName, long orderId) {
    Order findOrder = orderRepository.findByOrderIdAndMemberName(orderId, memberName);
    if (findOrder == null) {
      throw new CustomException(OrderExceptionType.ORDER_NOT_FOUND);
    }
    return OrderDetailDTO.toOrderDetailDTO(findOrder);
  }
}
