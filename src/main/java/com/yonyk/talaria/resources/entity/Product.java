package com.yonyk.talaria.resources.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

import com.yonyk.talaria.resources.entity.enums.ProductType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @Column(nullable = false)
  private String productName;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductType productType;

  @Column(precision = 7, scale = 2, nullable = false)
  private BigDecimal gram;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  private long price;
}
