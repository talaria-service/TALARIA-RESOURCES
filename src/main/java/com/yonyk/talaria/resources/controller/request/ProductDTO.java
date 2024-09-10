package com.yonyk.talaria.resources.controller.request;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.entity.enums.ProductNameType;
import com.yonyk.talaria.resources.entity.enums.ProductType;

public record ProductDTO(
    long productId,
    ProductNameType productName,
    ProductType productType,
    BigDecimal gram,
    int quantity,
    long price) {

  public Product toProduct() {
    return Product.builder()
        .productId(productId != 0 ? productId : null)
        .productName(productName)
        .productType(productType)
        .gram(gram.setScale(2, RoundingMode.HALF_UP))
        .quantity(quantity)
        .price(price)
        .build();
  }

  public static ProductDTO toProductDTO(Product product) {
    return new ProductDTO(
        product.getProductId(),
        product.getProductName(),
        product.getProductType(),
        product.getGram(),
        product.getQuantity(),
        product.getPrice());
  }
}
