package com.yonyk.talaria.resources.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
  @JsonProperty("구매")
  BUY("구매"),
  @JsonProperty("판매")
  SELL("판매");

  private final String orderType;
}
