package com.yonyk.talaria.resources.service;

import java.time.Instant;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.yonyk.talaria.resources.controller.request.OrderDTO;
import com.yonyk.talaria.resources.entity.Order;
import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.entity.enums.OrderStatusType;
import com.yonyk.talaria.resources.exception.CustomException;
import com.yonyk.talaria.resources.exception.enums.ProductExceptionType;
import com.yonyk.talaria.resources.repository.OrderRepository;
import com.yonyk.talaria.resources.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderRepoService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  // 주문생성
  public Order createOrder(String memberName, OrderDTO orderDTO) {
    // 주문 타입(구매, 판매)
    String orderType = orderDTO.orderType().toString();
    // 주문할 제품
    Product findProduct = findProductById(orderDTO.productId());
    // 수량 * 제품 가격인 총 가격
    long totalPrice = orderDTO.quantity() * findProduct.getPrice();

    return Order.builder()
        .orderNumber(createOrderNumber(orderType))
        .orderType(orderDTO.orderType())
        .orderStatusType(OrderStatusType.ORDER_COMPLETED)
        .memberName(memberName)
        .product(findProduct)
        .quantity(orderDTO.quantity())
        .totalPrice(totalPrice)
        .address(orderDTO.address())
        .build();
  }

  // 주문 제품 수량
  public void manageQunatity(long productId, int quantity, boolean plus) {
    // 주문할 제품의 수량 조절
    if (plus) productRepository.increaseQunatity(productId, quantity);
    else productRepository.decreaseQunatity(productId, quantity);
  }

  // 주문할 제품 가져오기
  private Product findProductById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new CustomException(ProductExceptionType.PRODUCT_NOT_FOUND));
  }

  // 주문번호 생성
  private String createOrderNumber(String orderType) {
    Random random = new Random();
    int randomInt = random.nextInt(999999) + 100000;
    long timestamp = Instant.now().toEpochMilli();

    return orderType + "_" + timestamp + "_" + randomInt;
  }
}
