package com.yonyk.talaria.resources.controller.response;

import com.yonyk.talaria.resources.entity.Order;

public record OrderDetailDTO(
    long orderId,
    String orderNumber,
    String orderType,
    String orderStatusType,
    String membername,
    String productName,
    int quantity,
    long totalPrice,
    String address) {

  public static OrderDetailDTO toOrderDetailDTO(Order order) {
    return new OrderDetailDTO(
        order.getOrderId(),
        order.getOrderNumber(),
        order.getOrderType().getOrderType(),
        order.getOrderStatusType().getOrderStatusType(),
        order.getMemberName(),
        order.getProduct().getProductName().getProductNameType(),
        order.getQuantity(),
        order.getTotalPrice(),
        order.getAddress());
  }
}
