package com.yonyk.talaria.resources.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusType {
  ORDER_COMPLETED("주문완료"),
  PAYMENT_COMPLETED("입금완료"),
  SHIPPED("발송완료"),
  TRANSFER_COMPLETED("송금완료"),
  REEIVED("수령완료");

  private final String orderStatusType;
}
