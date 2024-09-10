package com.yonyk.talaria.resources.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.yonyk.talaria.resources.entity.enums.OrderStatusType;
import com.yonyk.talaria.resources.entity.enums.OrderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column(nullable = false)
  private String orderNumber;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderType orderType;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderStatusType orderStatusType;

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

  @Column private LocalDateTime deletedAt;
}
