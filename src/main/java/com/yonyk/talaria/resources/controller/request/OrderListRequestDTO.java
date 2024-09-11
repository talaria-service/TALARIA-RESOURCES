package com.yonyk.talaria.resources.controller.request;

import java.time.LocalDate;

import com.yonyk.talaria.resources.entity.enums.OrderType;

public record OrderListRequestDTO(LocalDate date, int size, int page, OrderType orderType) {}
