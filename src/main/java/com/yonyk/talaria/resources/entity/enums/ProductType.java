package com.yonyk.talaria.resources.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
  @JsonProperty("매입")
  PURCHASE("매입"),

  @JsonProperty("매출")
  SALE("매출");

  private final String productType;
}
