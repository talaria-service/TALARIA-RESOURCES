package com.yonyk.talaria.resources.controller.response;

import java.util.List;
import java.util.Map;

public record OrderListResponseDTO(
    boolean success, String message, List<OrderDetailDTO> data, Map<String, String> links) {}
