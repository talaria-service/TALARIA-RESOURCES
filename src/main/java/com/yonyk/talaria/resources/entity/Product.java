package com.yonyk.talaria.resources.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

import org.hibernate.annotations.SQLDelete;

import com.yonyk.talaria.resources.entity.enums.ProductNameType;
import com.yonyk.talaria.resources.entity.enums.ProductType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE product SET deleted_at = CURRENT_TIMESTAMP WHERE product_id = ?")
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductNameType productName;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductType productType;

  @Column(precision = 7, scale = 2, nullable = false)
  private BigDecimal gram;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  private long price;

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
