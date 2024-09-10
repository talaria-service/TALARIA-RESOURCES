package com.yonyk.talaria.resources.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusType {
  @JsonProperty("주문완료")
  ORDER_COMPLETED("주문완료"),

  @JsonProperty("입금완료")
  PAYMENT_COMPLETED("입금완료"),

  @JsonProperty("발송완료")
  SHIPPED("발송완료"),

  @JsonProperty("송금완료")
  TRANSFER_COMPLETED("송금완료"),

  @JsonProperty("수령완료")
  REEIVED("수령완료"),

  @JsonProperty("주문취소")
  CANCLE_ORDER("주문취소");

  private final String orderStatusType;
}
