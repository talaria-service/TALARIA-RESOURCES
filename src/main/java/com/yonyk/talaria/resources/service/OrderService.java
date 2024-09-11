package com.yonyk.talaria.resources.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyk.talaria.resources.controller.request.OrderDTO;
import com.yonyk.talaria.resources.controller.request.OrderListRequestDTO;
import com.yonyk.talaria.resources.controller.request.OrderStatusDTO;
import com.yonyk.talaria.resources.controller.response.OrderDetailDTO;
import com.yonyk.talaria.resources.controller.response.OrderListResponseDTO;
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

  // 주문 조회(목록)
  public OrderListResponseDTO getOrderList(
      String memberName, OrderListRequestDTO orderListRequestDTO) {
    // data 에 넣어줄 주문 목록 가져오기
    Page<Order> orderList = orderRepoService.getProductList(memberName, orderListRequestDTO);
    List<OrderDetailDTO> orderDetailDTOList =
        orderList.stream().map(OrderDetailDTO::toOrderDetailDTO).toList();
    // message에 넣을 메세지 생성
    String message = orderRepoService.getMessage(orderList.getTotalElements());
    // success에 넣을 값 생성
    boolean success = orderRepoService.getSuccess(orderList.getTotalElements());

    // 링크 가져오기
    Map<String, String> links =
        orderRepoService.getLinks(orderList.getTotalPages(), orderListRequestDTO);

    return new OrderListResponseDTO(success, message, orderDetailDTOList, links);
  }

  // 주문 상태 수정
  @Transactional
  public void updateOrderStatus(String memberName, OrderStatusDTO orderStatusDTO) {
    // 사용자가 요청한 order 가져오기
    // 사용자가 작성한 order가 아니거나 주문이 없다면 예외 발생
    Order findOrder = orderRepoService.getOrder(memberName, orderStatusDTO.orderId());
    // 주문 상태 변경이 가능한 상태인지 확인
    orderRepoService.canChangeStatus(findOrder);
    // 주문 상태 수정
    orderRepoService.updateOrderStatus(findOrder, orderStatusDTO.orderStatusType());
  }

  // 주문 취소
  @Transactional
  public void deleteOrder(String memberName, long orderId) {
    // 사용자가 작성한 order가 아니거나 주문이 없다면 예외 발생
    Order findOrder = orderRepoService.getOrder(memberName, orderId);
    // 주문 상태 변경이 가능한 상태인지 확인
    orderRepoService.canChangeStatus(findOrder);
    // 주문 취소 시간 생성
    LocalDateTime deletedAt = LocalDateTime.now();
    // 주문 삭제
    orderRepoService.softDelete(findOrder, deletedAt);
  }
}
