package com.yonyk.talaria.resources.service;

import org.springframework.stereotype.Service;

import com.yonyk.talaria.resources.controller.request.ProductDTO;
import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  // 제품 등록
  public void registration(ProductDTO productDTO) {
    Product product = productDTO.toProduct();
    productRepository.save(product);
  }
}
