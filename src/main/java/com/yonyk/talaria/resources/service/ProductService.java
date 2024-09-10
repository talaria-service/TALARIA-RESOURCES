package com.yonyk.talaria.resources.service;

import org.springframework.stereotype.Service;

import com.yonyk.talaria.resources.controller.request.ProductDTO;
import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.exception.CustomException;
import com.yonyk.talaria.resources.exception.enums.ProductExceptionType;
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

  // 제품 조회(단건)
  public ProductDTO getProduct(long id) {
    Product findProduct =
        productRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(ProductExceptionType.PRODUCT_NOT_FOUND));
    return ProductDTO.toProductDTO(findProduct);
  }

  // 제품 수정
  public void UpdateProduct(ProductDTO productDTO) {
    boolean isExists = productRepository.existsById(productDTO.productId());
    if (!isExists) throw new CustomException(ProductExceptionType.PRODUCT_NOT_FOUND);
    productRepository.save(productDTO.toProduct());
  }
}
