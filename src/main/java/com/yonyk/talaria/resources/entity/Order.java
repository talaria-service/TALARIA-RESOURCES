package com.yonyk.talaria.resources.entity;

import jakarta.persistence.*;

import org.hibernate.annotations.SQLDelete;

import com.yonyk.talaria.resources.entity.enums.OrderStatusType;
import com.yonyk.talaria.resources.entity.enums.OrderType;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE orders SET deleted_at = CURRENT_TIMESTAMP WHERE order_id = ?")
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

  @ManyToOne
  @JoinColumn(name = "productId", nullable = false)
  private Product product;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  private long totalPrice;

  @Column(nullable = false)
  private String address;

  public void setOrderStatusType(OrderStatusType orderStatusType) {
    this.orderStatusType = orderStatusType;
  }
}
