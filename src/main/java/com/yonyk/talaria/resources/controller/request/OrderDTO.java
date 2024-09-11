package com.yonyk.talaria.resources.controller.request;

import com.yonyk.talaria.resources.entity.enums.OrderType;

public record OrderDTO(OrderType orderType, long productId, int quantity, String address) {}
