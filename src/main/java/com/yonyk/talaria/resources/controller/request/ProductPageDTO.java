package com.yonyk.talaria.resources.controller.request;

import com.yonyk.talaria.resources.entity.enums.ProductType;

public record ProductPageDTO(int size, int page, ProductType productType) {}
