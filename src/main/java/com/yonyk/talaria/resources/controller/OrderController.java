package com.yonyk.talaria.resources.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.yonyk.talaria.resources.common.security.details.PrincipalDetails;
import com.yonyk.talaria.resources.common.swagger.OrderControllerSwagger;
import com.yonyk.talaria.resources.controller.request.OrderDTO;
import com.yonyk.talaria.resources.controller.request.OrderListRequestDTO;
import com.yonyk.talaria.resources.controller.request.OrderStatusDTO;
import com.yonyk.talaria.resources.controller.response.OrderDetailDTO;
import com.yonyk.talaria.resources.controller.response.OrderListResponseDTO;
import com.yonyk.talaria.resources.entity.enums.OrderType;
import com.yonyk.talaria.resources.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController implements OrderControllerSwagger {

  private final OrderService orderService;

  // 주문생성
  @PostMapping
  public ResponseEntity<String> createOrder(
      @AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody OrderDTO orderDTO) {
    orderService.creatOrder(principalDetails.getUsername(), orderDTO);
    return ResponseEntity.ok("주문이 성공적으로 완료되었습니다.");
  }

  // 주문 상세 조회
  @GetMapping("/{orderId}")
  public ResponseEntity<OrderDetailDTO> orderDetails(
      @AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long orderId) {
    OrderDetailDTO orderDetailDTO =
        orderService.getOrderDetail(principalDetails.getUsername(), orderId);
    return ResponseEntity.ok(orderDetailDTO);
  }

  // 주문 목록 조회
  @GetMapping
  public ResponseEntity<OrderListResponseDTO> orderList(
      @AuthenticationPrincipal PrincipalDetails principalDetails,
      @RequestParam("date") LocalDate date,
      @RequestParam("size") int size,
      @RequestParam("page") int page,
      @RequestParam("orderType") OrderType orderType) {
    OrderListRequestDTO orderListRequestDTO = new OrderListRequestDTO(date, size, page, orderType);
    OrderListResponseDTO orderListResponseDTO =
        orderService.getOrderList(principalDetails.getUsername(), orderListRequestDTO);
    return ResponseEntity.ok(orderListResponseDTO);
  }

  // 주문 상태 변경
  @PatchMapping
  public ResponseEntity<String> updateOrderStatus(
      @AuthenticationPrincipal PrincipalDetails principalDetails,
      @RequestBody OrderStatusDTO orderStatusDTO) {
    orderService.updateOrderStatus(principalDetails.getUsername(), orderStatusDTO);
    return ResponseEntity.ok("주문상태 수정이 성공적으로 완료되었습니다.");
  }

  // 주문 삭제
  @DeleteMapping("/{orderId}")
  public ResponseEntity<String> deleteOrder(
      @AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long orderId) {
    orderService.deleteOrder(principalDetails.getUsername(), orderId);
    return ResponseEntity.ok("주문취소가 성공적으로 완료되었습니다.");
  }
}
