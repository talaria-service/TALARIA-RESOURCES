package com.yonyk.talaria.resources.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
  PURCHASE("PURCHASE"),
  SALE("SALE");

  private final String productType;
}
