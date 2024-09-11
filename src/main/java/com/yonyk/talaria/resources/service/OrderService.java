package com.yonyk.talaria.resources.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyk.talaria.resources.controller.request.OrderDTO;
import com.yonyk.talaria.resources.controller.request.OrderStatusDTO;
import com.yonyk.talaria.resources.controller.response.OrderDetailDTO;
import com.yonyk.talaria.resources.entity.Order;
import com.yonyk.talaria.resources.entity.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

  private final OrderRepoService orderRepoService;
  private final ProductRepoService productRepoService;

  // 주문 생성
  @Transactional
  public void creatOrder(String memberName, OrderDTO orderDTO) {
    // 수량 체크
    productRepoService.checkQuantity(orderDTO);
    // 제품의 수량 조절(true 면 수량 추가, flase 면 수량 감소)
    productRepoService.manageQunatity(orderDTO.productId(), orderDTO.quantity(), false);
    // 주문할 제품 가져오기
    Product findProduct = productRepoService.getProduct(orderDTO.productId());
    // 주문 생성
    Order order = orderRepoService.createOrder(memberName, orderDTO, findProduct);
    // 주문 저장
    orderRepoService.saveOrder(order);
  }

  // 주문 조회(단건)
  public OrderDetailDTO getOrderDetail(String memberName, long orderId) {
    // 주문 찾아오기
    Order findOrder = orderRepoService.getOrder(memberName, orderId);
    // OrderDetailDTO로 변환해서 리턴
    return OrderDetailDTO.toOrderDetailDTO(findOrder);
  }

  // 주문 상태 수정
  public void updateOrderStatus(String memberName, OrderStatusDTO orderStatusDTO) {
    // 사용자가 요청한 order 가져오기
    // 사용자가 작성한 order가 아니거나 주문이 없다면 예외 발생
    Order findOrder = orderRepoService.getOrder(memberName, orderStatusDTO.orderId());
    // 주문 상태 변경이 가능한 상태인지 확인
    orderRepoService.canChangeStatus(findOrder);
    // 주문 상태 수정
    orderRepoService.updateOrderStatus(orderStatusDTO);
  }
}
