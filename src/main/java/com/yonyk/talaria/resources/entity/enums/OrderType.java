package com.yonyk.talaria.resources.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
  BUY("BUY"),
  SELL("SELL");

  private final String orderType;
}
