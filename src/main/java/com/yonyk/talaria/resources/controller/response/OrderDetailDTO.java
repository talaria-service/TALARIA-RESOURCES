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

  public OrderDetailDTO toOrderDetailDTO(Order order) {
    return null;
  }
}
