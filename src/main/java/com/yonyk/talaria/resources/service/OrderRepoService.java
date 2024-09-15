package com.yonyk.talaria.resources.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyk.talaria.resources.controller.request.OrderDTO;
import com.yonyk.talaria.resources.controller.request.OrderListRequestDTO;
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
  @Transactional
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

  // 페이지네이션을 활용한 주문 목록 조회
  public Page<Order> getProductList(String memberName, OrderListRequestDTO orderListRequestDTO) {
    // 페이지 객체 생성
    Pageable pageable = PageRequest.of(orderListRequestDTO.page() - 1, orderListRequestDTO.size());
    // 주문 목록 가져오기
    return orderRepository.findOrderList(
        pageable, memberName, orderListRequestDTO.orderType(), orderListRequestDTO.date());
  }

  // 주문 목록 메세지 생성
  public String getMessage(long listSize) {
    if (listSize == 0) return "주문이 없습니다.";
    else return "총 " + listSize + "개의 주문이 있습니다.";
  }

  // 주문 목록 success 값 생성
  public boolean getSuccess(long listSize) {
    if (listSize == 0) return false;
    else return true;
  }

  // 주문 목록 links 생성
  public Map<String, String> getLinks(int totalPage, OrderListRequestDTO orderListRequestDTO) {
    Map<String, String> links = new HashMap<>();

    if (totalPage == 0) return links;

    // 현재 요청 파라미터 가져오기
    LocalDate date = orderListRequestDTO.date();
    int size = orderListRequestDTO.size();
    int page = orderListRequestDTO.page();
    String orderType = orderListRequestDTO.orderType().getOrderType();

    // 이전 페이지, 다음 페이지 계산
    int prevPage = page != 1 ? page - 1 : 0;
    int nextPage = page != totalPage ? page + 1 : 0;

    // 기본 요청 주소
    String baseUrl = "/api/order?date=%s&size=%d&page=%d&orderType=%s";

    // 현재 페이지 링크 생성
    links.put("crr", String.format(baseUrl, date, size, page, orderType));
    // 이전 페이지 링크 생성
    if (prevPage != 0) links.put("prev", String.format(baseUrl, date, size, prevPage, orderType));
    // 이후 페이지 링크 설정
    if (nextPage != 0) links.put("next", String.format(baseUrl, date, size, nextPage, orderType));

    return links;
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
  @Transactional
  public void updateOrderStatus(Order order, OrderStatusType orderStatusType) {
    order.setOrderStatusType(orderStatusType);
    orderRepository.save(order);
  }

  // deletedAt에 날짜를 추가하여 소프트 딜맅트
  @Transactional
  public void softDelete(Order order) {
    // 주문 상태 변경
    updateOrderStatus(order, OrderStatusType.CANCLE_ORDER);
    // 주문 상태 변경한 Order 객체 다시 가져오기
    Order updatedOrder = getOrder(order.getMemberName(), order.getOrderId());
    // 소프트 딜리트
    orderRepository.deleteById(updatedOrder.getOrderId());
  }
}
