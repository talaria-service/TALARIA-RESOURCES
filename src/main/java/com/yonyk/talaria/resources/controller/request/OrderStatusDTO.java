package com.yonyk.talaria.resources.controller.request;

import com.yonyk.talaria.resources.entity.enums.OrderStatusType;

public record OrderStatusDTO(long orderId, OrderStatusType orderStatusType) {}
