package com.yonyk.talaria.resources.controller.request;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.entity.enums.ProductNameType;
import com.yonyk.talaria.resources.entity.enums.ProductType;

public record ProductDTO(
    ProductNameType productName,
    ProductType productType,
    BigDecimal gram,
    int quantity,
    long price) {

  public Product toProduct() {
    return Product.builder()
        .productName(productName)
        .productType(productType)
        .gram(gram.setScale(2, RoundingMode.HALF_UP))
        .quantity(quantity)
        .price(price)
        .build();
  }
}
