package com.yonyk.talaria.resources.entity;

import jakarta.persistence.*;

import com.yonyk.talaria.resources.entity.enums.OrderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column(nullable = false)
  private String orderNumber;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderType orderType;

  @Column(nullable = false)
  private String memberName;

  @OneToOne
  @JoinColumn(name = "productId", nullable = false)
  private Product product;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  private long totalPrice;

  @Column(nullable = false)
  private String address;
}
