package com.yonyk.talaria.resources.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductNameType {
  @JsonProperty("금 99.9%")
  GOLD_999("금 99.9%"),

  @JsonProperty("금 99.99%")
  GOLD_9999("금 99.99%");

  private final String productNameType;
}
